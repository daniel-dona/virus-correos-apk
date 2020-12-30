package com.microsoft.aad.adal;

import com.citrix.MAM.Android.ManagedAppHelper.Interface.MAMAppInfo;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: PG */
public class ChallengeResponseBuilder {
    public static final String TAG = "ChallengeResponseBuilder";
    public final IJWSBuilder mJWSBuilder;

    /* compiled from: PG */
    public class ChallengeRequest {
        public List<String> mCertAuthorities;
        public String mContext = BuildConfig.FLAVOR;
        public String mNonce = BuildConfig.FLAVOR;
        public String mSubmitUrl = BuildConfig.FLAVOR;
        public String mThumbprint = BuildConfig.FLAVOR;
        public String mVersion = null;

        public ChallengeRequest() {
        }
    }

    /* compiled from: PG */
    public class ChallengeResponse {
        public String mAuthorizationHeaderValue;
        public String mSubmitUrl;

        public ChallengeResponse() {
        }

        public String getAuthorizationHeaderValue() {
            return this.mAuthorizationHeaderValue;
        }

        public String getSubmitUrl() {
            return this.mSubmitUrl;
        }

        public void setAuthorizationHeaderValue(String str) {
            this.mAuthorizationHeaderValue = str;
        }

        public void setSubmitUrl(String str) {
            this.mSubmitUrl = str;
        }
    }

    /* compiled from: PG */
    public enum RequestField {
        Nonce,
        CertAuthorities,
        Version,
        SubmitUrl,
        Context,
        CertThumbprint
    }

    public ChallengeResponseBuilder(IJWSBuilder iJWSBuilder) {
        this.mJWSBuilder = iJWSBuilder;
    }

    private ChallengeRequest getChallengeRequest(String str) throws AuthenticationException {
        if (!StringExtensions.isNullOrBlank(str)) {
            ChallengeRequest challengeRequest = new ChallengeRequest();
            HashMap<String, String> urlParameters = StringExtensions.getUrlParameters(str);
            validateChallengeRequest(urlParameters, true);
            RequestField requestField = RequestField.Nonce;
            String unused = challengeRequest.mNonce = urlParameters.get("Nonce");
            if (StringExtensions.isNullOrBlank(challengeRequest.mNonce)) {
                RequestField requestField2 = RequestField.Nonce;
                String unused2 = challengeRequest.mNonce = urlParameters.get("Nonce".toLowerCase(Locale.US));
            }
            RequestField requestField3 = RequestField.CertAuthorities;
            String str2 = urlParameters.get("CertAuthorities");
            Logger.m1251v("ChallengeResponseBuilder:getChallengeRequest", "Get cert authorities. ", "Authorities: " + str2, (ADALError) null);
            List unused3 = challengeRequest.mCertAuthorities = StringExtensions.getStringTokens(str2, ";");
            RequestField requestField4 = RequestField.Version;
            String unused4 = challengeRequest.mVersion = urlParameters.get(MAMAppInfo.KEY_VERSION);
            RequestField requestField5 = RequestField.SubmitUrl;
            String unused5 = challengeRequest.mSubmitUrl = urlParameters.get("SubmitUrl");
            RequestField requestField6 = RequestField.Context;
            String unused6 = challengeRequest.mContext = urlParameters.get("Context");
            return challengeRequest;
        }
        throw new AuthenticationServerProtocolException("redirectUri");
    }

    private ChallengeRequest getChallengeRequestFromHeader(String str) throws UnsupportedEncodingException, AuthenticationException {
        if (StringExtensions.isNullOrBlank(str)) {
            throw new AuthenticationServerProtocolException("headerValue");
        } else if (StringExtensions.hasPrefixInHeader(str, "PKeyAuth")) {
            ChallengeRequest challengeRequest = new ChallengeRequest();
            String substring = str.substring(8);
            ArrayList<String> splitWithQuotes = StringExtensions.splitWithQuotes(substring, ',');
            HashMap hashMap = new HashMap();
            Iterator<String> it = splitWithQuotes.iterator();
            while (it.hasNext()) {
                ArrayList<String> splitWithQuotes2 = StringExtensions.splitWithQuotes(it.next(), '=');
                if (splitWithQuotes2.size() == 2 && !StringExtensions.isNullOrBlank(splitWithQuotes2.get(0)) && !StringExtensions.isNullOrBlank(splitWithQuotes2.get(1))) {
                    hashMap.put(StringExtensions.urlFormDecode(splitWithQuotes2.get(0)).trim(), StringExtensions.removeQuoteInHeaderValue(StringExtensions.urlFormDecode(splitWithQuotes2.get(1)).trim()));
                } else if (splitWithQuotes2.size() != 1 || StringExtensions.isNullOrBlank(splitWithQuotes2.get(0))) {
                    throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, substring);
                } else {
                    hashMap.put(StringExtensions.urlFormDecode(splitWithQuotes2.get(0)).trim(), StringExtensions.urlFormDecode(BuildConfig.FLAVOR));
                }
            }
            validateChallengeRequest(hashMap, false);
            RequestField requestField = RequestField.Nonce;
            String unused = challengeRequest.mNonce = (String) hashMap.get("Nonce");
            if (StringExtensions.isNullOrBlank(challengeRequest.mNonce)) {
                RequestField requestField2 = RequestField.Nonce;
                String unused2 = challengeRequest.mNonce = (String) hashMap.get("Nonce".toLowerCase(Locale.US));
            }
            if (!isWorkplaceJoined()) {
                Logger.m1250v("ChallengeResponseBuilder:getChallengeRequestFromHeader", "Device is not workplace joined. ");
            } else {
                RequestField requestField3 = RequestField.CertThumbprint;
                if (!StringExtensions.isNullOrBlank((String) hashMap.get("CertThumbprint"))) {
                    Logger.m1250v("ChallengeResponseBuilder:getChallengeRequestFromHeader", "CertThumbprint exists in the device auth challenge.");
                    RequestField requestField4 = RequestField.CertThumbprint;
                    String unused3 = challengeRequest.mThumbprint = (String) hashMap.get("CertThumbprint");
                } else {
                    RequestField requestField5 = RequestField.CertAuthorities;
                    if (hashMap.containsKey("CertAuthorities")) {
                        Logger.m1250v("ChallengeResponseBuilder:getChallengeRequestFromHeader", "CertAuthorities exists in the device auth challenge.");
                        RequestField requestField6 = RequestField.CertAuthorities;
                        List unused4 = challengeRequest.mCertAuthorities = StringExtensions.getStringTokens((String) hashMap.get("CertAuthorities"), ";");
                    } else {
                        throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "Both certThumbprint and certauthorities are not present");
                    }
                }
            }
            RequestField requestField7 = RequestField.Version;
            String unused5 = challengeRequest.mVersion = (String) hashMap.get(MAMAppInfo.KEY_VERSION);
            RequestField requestField8 = RequestField.Context;
            String unused6 = challengeRequest.mContext = (String) hashMap.get("Context");
            return challengeRequest;
        } else {
            throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, str);
        }
    }

    private ChallengeResponse getDeviceCertResponse(ChallengeRequest challengeRequest) throws AuthenticationException {
        ChallengeResponse noDeviceCertResponse = getNoDeviceCertResponse(challengeRequest);
        String unused = noDeviceCertResponse.mSubmitUrl = challengeRequest.mSubmitUrl;
        Class<?> deviceCertificateProxy = AuthenticationSettings.INSTANCE.getDeviceCertificateProxy();
        if (deviceCertificateProxy != null) {
            IDeviceCertificate wPJAPIInstance = getWPJAPIInstance(deviceCertificateProxy);
            if (wPJAPIInstance.isValidIssuer(challengeRequest.mCertAuthorities) || (wPJAPIInstance.getThumbPrint() != null && wPJAPIInstance.getThumbPrint().equalsIgnoreCase(challengeRequest.mThumbprint))) {
                RSAPrivateKey rSAPrivateKey = wPJAPIInstance.getRSAPrivateKey();
                if (rSAPrivateKey != null) {
                    String unused2 = noDeviceCertResponse.mAuthorizationHeaderValue = String.format("%s AuthToken=\"%s\",Context=\"%s\",Version=\"%s\"", new Object[]{"PKeyAuth", this.mJWSBuilder.generateSignedJWT(challengeRequest.mNonce, challengeRequest.mSubmitUrl, rSAPrivateKey, wPJAPIInstance.getRSAPublicKey(), wPJAPIInstance.getCertificate()), challengeRequest.mContext, challengeRequest.mVersion});
                    Logger.m1251v(TAG, "Receive challenge response. ", "Challenge response:" + noDeviceCertResponse.mAuthorizationHeaderValue, (ADALError) null);
                } else {
                    throw new AuthenticationException(ADALError.KEY_CHAIN_PRIVATE_KEY_EXCEPTION);
                }
            }
        }
        return noDeviceCertResponse;
    }

    private ChallengeResponse getNoDeviceCertResponse(ChallengeRequest challengeRequest) {
        ChallengeResponse challengeResponse = new ChallengeResponse();
        String unused = challengeResponse.mSubmitUrl = challengeRequest.mSubmitUrl;
        String unused2 = challengeResponse.mAuthorizationHeaderValue = String.format("%s Context=\"%s\",Version=\"%s\"", new Object[]{"PKeyAuth", challengeRequest.mContext, challengeRequest.mVersion});
        return challengeResponse;
    }

    private IDeviceCertificate getWPJAPIInstance(Class<IDeviceCertificate> cls) throws AuthenticationException {
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance((Object[]) null);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_API_EXCEPTION, "WPJ Api constructor is not defined", e);
        }
    }

    private boolean isWorkplaceJoined() {
        return AuthenticationSettings.INSTANCE.getDeviceCertificateProxy() != null;
    }

    private void validateChallengeRequest(Map<String, String> map, boolean z) throws AuthenticationException {
        RequestField requestField = RequestField.Nonce;
        if (!map.containsKey("Nonce")) {
            RequestField requestField2 = RequestField.Nonce;
            if (!map.containsKey("Nonce".toLowerCase(Locale.US))) {
                throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "Nonce");
            }
        }
        RequestField requestField3 = RequestField.Version;
        if (map.containsKey(MAMAppInfo.KEY_VERSION)) {
            if (z) {
                RequestField requestField4 = RequestField.SubmitUrl;
                if (!map.containsKey("SubmitUrl")) {
                    throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "SubmitUrl");
                }
            }
            RequestField requestField5 = RequestField.Context;
            if (!map.containsKey("Context")) {
                throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "Context");
            } else if (z) {
                RequestField requestField6 = RequestField.CertAuthorities;
                if (!map.containsKey("CertAuthorities")) {
                    throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "CertAuthorities");
                }
            }
        } else {
            throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, MAMAppInfo.KEY_VERSION);
        }
    }

    public ChallengeResponse getChallengeResponseFromHeader(String str, String str2) throws UnsupportedEncodingException, AuthenticationException {
        ChallengeRequest challengeRequestFromHeader = getChallengeRequestFromHeader(str);
        String unused = challengeRequestFromHeader.mSubmitUrl = str2;
        return getDeviceCertResponse(challengeRequestFromHeader);
    }

    public ChallengeResponse getChallengeResponseFromUri(String str) throws AuthenticationException {
        return getDeviceCertResponse(getChallengeRequest(str));
    }
}
