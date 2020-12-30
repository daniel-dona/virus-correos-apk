package com.microsoft.aad.adal;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.net.IWebRequestHandler;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.adal.internal.util.HashMapExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.logging.DiagnosticContext;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAuthorizationResponse;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenResponse;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class Oauth2 {
    public static final String DEFAULT_AUTHORIZE_ENDPOINT = "/oauth2/authorize";
    public static final String DEFAULT_TOKEN_ENDPOINT = "/oauth2/token";
    public static final int DELAY_TIME_PERIOD = 1000;
    public static final String HTTPS_PROTOCOL_STRING = "https";
    public static final int MAX_RESILIENCY_ERROR_CODE = 599;
    public static final String STRING_FORMAT_QUERY_PARAM = "%s&%s=%s";
    public static final String TAG = "Oauth";
    public String mBrokerClientVersion = BuildConfig.FLAVOR;
    public String mClientVersion = BuildConfig.FLAVOR;
    public IJWSBuilder mJWSBuilder = new JWSBuilder();
    public AuthenticationRequest mRequest;
    public boolean mRetryOnce = true;
    public String mTokenEndpoint;
    public IWebRequestHandler mWebRequestHandler;

    public Oauth2(AuthenticationRequest authenticationRequest) {
        this.mRequest = authenticationRequest;
        this.mWebRequestHandler = null;
        this.mJWSBuilder = null;
        setTokenEndpoint(this.mRequest.getAuthority() + DEFAULT_TOKEN_ENDPOINT);
    }

    public static String decodeProtocolState(String str) throws UnsupportedEncodingException {
        if (!StringExtensions.isNullOrBlank(str)) {
            return new String(Base64.decode(str, 9), "UTF-8");
        }
        return null;
    }

    public static void extractJsonObjects(Map<String, String> map, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            map.put(next, jSONObject.getString(next));
        }
    }

    private Map<String, String> getRequestHeaders() {
        return Eo.e(WebRequestHandler.HEADER_ACCEPT, WebRequestHandler.HEADER_ACCEPT_JSON);
    }

    private AuthenticationResult parseJsonResponse(String str) throws JSONException, AuthenticationException {
        HashMap hashMap = new HashMap();
        extractJsonObjects(hashMap, str);
        return processUIResponseParams(hashMap);
    }

    private AuthenticationResult postMessage(String str, Map<String, String> map) throws IOException, AuthenticationException {
        HttpWebResponse sendPost;
        AuthenticationResult authenticationResult;
        String str2;
        HttpEvent startHttpEvent = startHttpEvent();
        URL url = StringExtensions.getUrl(getTokenEndpoint());
        if (url != null) {
            startHttpEvent.setHttpPath(url);
            try {
                this.mWebRequestHandler.setRequestCorrelationId(this.mRequest.getCorrelationId());
                ClientMetrics.INSTANCE.beginClientMetricsRecord(url, this.mRequest.getCorrelationId(), map);
                sendPost = this.mWebRequestHandler.sendPost(url, map, str.getBytes("UTF-8"), OAuth2Strategy.TOKEN_REQUEST_CONTENT_TYPE);
                startHttpEvent.setResponseCode(sendPost.getStatusCode());
                startHttpEvent.setCorrelationId(this.mRequest.getCorrelationId().toString());
                stopHttpEvent(startHttpEvent);
                if (sendPost.getStatusCode() == 401) {
                    if (sendPost.getResponseHeaders() == null || !sendPost.getResponseHeaders().containsKey("WWW-Authenticate")) {
                        Logger.m1250v("Oauth:postMessage", "401 http status code is returned without authorization header.");
                    } else {
                        String str3 = (String) sendPost.getResponseHeaders().get("WWW-Authenticate").get(0);
                        Logger.m1248i("Oauth:postMessage", "Device certificate challenge request. ", "Challenge header: " + str3);
                        if (StringExtensions.isNullOrBlank(str3)) {
                            throw new AuthenticationException(ADALError.DEVICE_CERTIFICATE_REQUEST_INVALID, "Challenge header is empty", sendPost);
                        } else if (StringExtensions.hasPrefixInHeader(str3, "PKeyAuth")) {
                            HttpEvent startHttpEvent2 = startHttpEvent();
                            startHttpEvent2.setHttpPath(url);
                            Logger.m1250v("Oauth:postMessage", "Received pkeyAuth device challenge.");
                            ChallengeResponseBuilder challengeResponseBuilder = new ChallengeResponseBuilder(this.mJWSBuilder);
                            Logger.m1250v("Oauth:postMessage", "Processing device challenge.");
                            map.put("Authorization", challengeResponseBuilder.getChallengeResponseFromHeader(str3, url.toString()).getAuthorizationHeaderValue());
                            Logger.m1250v("Oauth:postMessage", "Sending request with challenge response.");
                            HttpWebResponse sendPost2 = this.mWebRequestHandler.sendPost(url, map, str.getBytes("UTF-8"), OAuth2Strategy.TOKEN_REQUEST_CONTENT_TYPE);
                            startHttpEvent2.setResponseCode(sendPost2.getStatusCode());
                            startHttpEvent2.setCorrelationId(this.mRequest.getCorrelationId().toString());
                            stopHttpEvent(startHttpEvent2);
                            sendPost = sendPost2;
                        }
                    }
                }
                boolean isEmpty = TextUtils.isEmpty(sendPost.getBody());
                if (!isEmpty) {
                    Logger.m1250v("Oauth:postMessage", "Token request does not have exception.");
                    authenticationResult = processTokenResponse(sendPost, startHttpEvent);
                    ClientMetrics.INSTANCE.setLastError((String) null);
                } else {
                    authenticationResult = null;
                }
                if (authenticationResult == null) {
                    if (isEmpty) {
                        str2 = "Status code:" + sendPost.getStatusCode();
                    } else {
                        str2 = sendPost.getBody();
                    }
                    Logger.m1245e("Oauth:postMessage", ADALError.SERVER_ERROR.getDescription(), str2, ADALError.SERVER_ERROR);
                    throw new AuthenticationException(ADALError.SERVER_ERROR, str2, sendPost);
                }
                ClientMetrics.INSTANCE.setLastErrorCodes(authenticationResult.getErrorCodes());
                ClientMetrics.INSTANCE.endClientMetricsRecord("token", this.mRequest.getCorrelationId());
                return authenticationResult;
            } catch (ServerRespondingWithRetryableException e) {
                AuthenticationResult retry = retry(str, map);
                if (retry != null) {
                    ClientMetrics.INSTANCE.endClientMetricsRecord("token", this.mRequest.getCorrelationId());
                    return retry;
                } else if (this.mRequest.getIsExtendedLifetimeEnabled()) {
                    Logger.m1250v("Oauth:postMessage", "WebResponse is not a success due to: " + sendPost.getStatusCode());
                    throw e;
                } else {
                    Logger.m1250v("Oauth:postMessage", "WebResponse is not a success due to: " + sendPost.getStatusCode());
                    throw new AuthenticationException(ADALError.SERVER_ERROR, "WebResponse is not a success due to: " + sendPost.getStatusCode(), sendPost);
                }
            } catch (UnsupportedEncodingException e2) {
                ClientMetrics.INSTANCE.setLastError((String) null);
                Logger.m1246e("Oauth:postMessage", ADALError.ENCODING_IS_NOT_SUPPORTED.getDescription(), e2.getMessage(), ADALError.ENCODING_IS_NOT_SUPPORTED, e2);
                throw e2;
            } catch (SocketTimeoutException e3) {
                AuthenticationResult retry2 = retry(str, map);
                if (retry2 != null) {
                    ClientMetrics.INSTANCE.endClientMetricsRecord("token", this.mRequest.getCorrelationId());
                    return retry2;
                }
                ClientMetrics.INSTANCE.setLastError((String) null);
                if (this.mRequest.getIsExtendedLifetimeEnabled()) {
                    Logger.m1246e("Oauth:postMessage", ADALError.SERVER_ERROR.getDescription(), e3.getMessage(), ADALError.SERVER_ERROR, e3);
                    throw new ServerRespondingWithRetryableException(e3.getMessage(), (Throwable) e3);
                }
                Logger.m1246e("Oauth:postMessage", ADALError.SERVER_ERROR.getDescription(), e3.getMessage(), ADALError.SERVER_ERROR, e3);
                throw e3;
            } catch (IOException e4) {
                try {
                    ClientMetrics.INSTANCE.setLastError((String) null);
                    Logger.m1246e("Oauth:postMessage", ADALError.SERVER_ERROR.getDescription(), e4.getMessage(), ADALError.SERVER_ERROR, e4);
                    throw e4;
                } catch (Throwable th) {
                    ClientMetrics.INSTANCE.endClientMetricsRecord("token", this.mRequest.getCorrelationId());
                    throw th;
                }
            }
        } else {
            stopHttpEvent(startHttpEvent);
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        r1 = r10.getResponseHeaders().get(com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest.CORRELATION_ID);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microsoft.aad.adal.AuthenticationResult processTokenResponse(com.microsoft.identity.common.adal.internal.net.HttpWebResponse r10, com.microsoft.aad.adal.HttpEvent r11) throws com.microsoft.aad.adal.AuthenticationException {
        /*
            r9 = this;
            java.lang.String r0 = ""
            java.util.Map r1 = r10.getResponseHeaders()
            r2 = 0
            java.lang.String r3 = "Oauth:processTokenResponse"
            java.lang.String r4 = "x-ms-clitelem"
            r5 = 0
            if (r1 == 0) goto L_0x00a9
            java.util.Map r1 = r10.getResponseHeaders()
            java.lang.String r6 = "client-request-id"
            boolean r1 = r1.containsKey(r6)
            if (r1 == 0) goto L_0x0033
            java.util.Map r1 = r10.getResponseHeaders()
            java.lang.Object r1 = r1.get(r6)
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x0033
            int r6 = r1.size()
            if (r6 <= 0) goto L_0x0033
            java.lang.Object r1 = r1.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x0034
        L_0x0033:
            r1 = r2
        L_0x0034:
            java.util.Map r6 = r10.getResponseHeaders()
            java.lang.String r7 = "x-ms-request-id"
            boolean r6 = r6.containsKey(r7)
            if (r6 == 0) goto L_0x0071
            java.util.Map r6 = r10.getResponseHeaders()
            java.lang.Object r6 = r6.get(r7)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0071
            int r7 = r6.size()
            if (r7 <= 0) goto L_0x0071
            java.lang.String r7 = "Set request id header. x-ms-request-id: "
            java.lang.StringBuilder r7 = Eo.a(r7)
            java.lang.Object r8 = r6.get(r5)
            java.lang.String r8 = (java.lang.String) r8
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.microsoft.aad.adal.Logger.m1250v(r3, r7)
            java.lang.Object r6 = r6.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            r11.setRequestIdHeader(r6)
        L_0x0071:
            java.util.Map r6 = r10.getResponseHeaders()
            java.lang.Object r6 = r6.get(r4)
            if (r6 == 0) goto L_0x00aa
            java.util.Map r6 = r10.getResponseHeaders()
            java.lang.Object r6 = r6.get(r4)
            java.util.List r6 = (java.util.List) r6
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x00aa
            java.util.Map r6 = r10.getResponseHeaders()
            java.lang.Object r6 = r6.get(r4)
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r6.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            com.microsoft.aad.adal.TelemetryUtils$CliTelemInfo r6 = com.microsoft.aad.adal.TelemetryUtils.parseXMsCliTelemHeader(r6)
            if (r6 == 0) goto L_0x00aa
            r11.setXMsCliTelemData(r6)
            java.lang.String r2 = r6.getSpeRing()
            goto L_0x00aa
        L_0x00a9:
            r1 = r2
        L_0x00aa:
            int r6 = r10.getStatusCode()
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 == r7) goto L_0x00f5
            r7 = 400(0x190, float:5.6E-43)
            if (r6 == r7) goto L_0x00f5
            r7 = 401(0x191, float:5.62E-43)
            if (r6 != r7) goto L_0x00bb
            goto L_0x00f5
        L_0x00bb:
            r11 = 500(0x1f4, float:7.0E-43)
            java.lang.String r0 = " "
            if (r6 < r11) goto L_0x00dc
            r11 = 599(0x257, float:8.4E-43)
            if (r6 > r11) goto L_0x00dc
            com.microsoft.aad.adal.ServerRespondingWithRetryableException r11 = new com.microsoft.aad.adal.ServerRespondingWithRetryableException
            java.lang.String r1 = "Server Error "
            java.lang.StringBuilder r0 = Eo.b(r1, r6, r0)
            java.lang.String r1 = r10.getBody()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r11.<init>((java.lang.String) r0, (com.microsoft.identity.common.adal.internal.net.HttpWebResponse) r10)
            throw r11
        L_0x00dc:
            com.microsoft.aad.adal.AuthenticationException r11 = new com.microsoft.aad.adal.AuthenticationException
            com.microsoft.aad.adal.ADALError r1 = com.microsoft.aad.adal.ADALError.SERVER_ERROR
            java.lang.String r2 = "Unexpected server response "
            java.lang.StringBuilder r0 = Eo.b(r2, r6, r0)
            java.lang.String r2 = r10.getBody()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r11.<init>((com.microsoft.aad.adal.ADALError) r1, (java.lang.String) r0, (com.microsoft.identity.common.adal.internal.net.HttpWebResponse) r10)
            throw r11
        L_0x00f5:
            java.lang.String r6 = r10.getBody()     // Catch:{ JSONException -> 0x0182 }
            com.microsoft.aad.adal.AuthenticationResult r6 = r9.parseJsonResponse(r6)     // Catch:{ JSONException -> 0x0182 }
            if (r6 == 0) goto L_0x011a
            java.lang.String r7 = r6.getErrorCode()     // Catch:{ JSONException -> 0x0182 }
            if (r7 == 0) goto L_0x0108
            r6.setHttpResponse(r10)     // Catch:{ JSONException -> 0x0182 }
        L_0x0108:
            com.microsoft.aad.adal.TelemetryUtils$CliTelemInfo r7 = new com.microsoft.aad.adal.TelemetryUtils$CliTelemInfo     // Catch:{ JSONException -> 0x0182 }
            r7.<init>()     // Catch:{ JSONException -> 0x0182 }
            r7._setSpeRing(r2)     // Catch:{ JSONException -> 0x0182 }
            r6.setCliTelemInfo(r7)     // Catch:{ JSONException -> 0x0182 }
            java.lang.String r2 = r6.getErrorCode()     // Catch:{ JSONException -> 0x0182 }
            r11.setOauthErrorCode(r2)     // Catch:{ JSONException -> 0x0182 }
        L_0x011a:
            if (r1 == 0) goto L_0x015a
            boolean r11 = r1.isEmpty()
            if (r11 != 0) goto L_0x015a
            java.util.UUID r11 = java.util.UUID.fromString(r1)     // Catch:{ IllegalArgumentException -> 0x014e }
            com.microsoft.aad.adal.AuthenticationRequest r2 = r9.mRequest     // Catch:{ IllegalArgumentException -> 0x014e }
            java.util.UUID r2 = r2.getCorrelationId()     // Catch:{ IllegalArgumentException -> 0x014e }
            boolean r11 = r11.equals(r2)     // Catch:{ IllegalArgumentException -> 0x014e }
            if (r11 != 0) goto L_0x0139
            java.lang.String r11 = "CorrelationId is not matching"
            com.microsoft.aad.adal.ADALError r2 = com.microsoft.aad.adal.ADALError.CORRELATION_ID_NOT_MATCHING_REQUEST_RESPONSE     // Catch:{ IllegalArgumentException -> 0x014e }
            com.microsoft.aad.adal.Logger.m1253w(r3, r11, r0, r2)     // Catch:{ IllegalArgumentException -> 0x014e }
        L_0x0139:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x014e }
            r11.<init>()     // Catch:{ IllegalArgumentException -> 0x014e }
            java.lang.String r2 = "Response correlationId:"
            r11.append(r2)     // Catch:{ IllegalArgumentException -> 0x014e }
            r11.append(r1)     // Catch:{ IllegalArgumentException -> 0x014e }
            java.lang.String r11 = r11.toString()     // Catch:{ IllegalArgumentException -> 0x014e }
            com.microsoft.aad.adal.Logger.m1250v(r3, r11)     // Catch:{ IllegalArgumentException -> 0x014e }
            goto L_0x015a
        L_0x014e:
            r11 = move-exception
            java.lang.String r2 = "Wrong format of the correlation ID:"
            java.lang.String r1 = Eo.a(r2, r1)
            com.microsoft.aad.adal.ADALError r2 = com.microsoft.aad.adal.ADALError.CORRELATION_ID_FORMAT
            com.microsoft.aad.adal.Logger.m1246e(r3, r1, r0, r2, r11)
        L_0x015a:
            java.util.Map r11 = r10.getResponseHeaders()
            if (r11 == 0) goto L_0x0181
            java.util.Map r10 = r10.getResponseHeaders()
            java.lang.Object r10 = r10.get(r4)
            java.util.List r10 = (java.util.List) r10
            if (r10 == 0) goto L_0x0181
            boolean r11 = r10.isEmpty()
            if (r11 != 0) goto L_0x0181
            java.lang.Object r10 = r10.get(r5)
            java.lang.String r10 = (java.lang.String) r10
            com.microsoft.aad.adal.TelemetryUtils$CliTelemInfo r10 = com.microsoft.aad.adal.TelemetryUtils.parseXMsCliTelemHeader(r10)
            if (r6 == 0) goto L_0x0181
            r6.setCliTelemInfo(r10)
        L_0x0181:
            return r6
        L_0x0182:
            r11 = move-exception
            com.microsoft.aad.adal.AuthenticationException r0 = new com.microsoft.aad.adal.AuthenticationException
            com.microsoft.aad.adal.ADALError r1 = com.microsoft.aad.adal.ADALError.SERVER_INVALID_JSON_RESPONSE
            java.lang.String r2 = "Can't parse server response. "
            java.lang.StringBuilder r2 = Eo.a(r2)
            java.lang.String r3 = r10.getBody()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r1, r2, r10, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.aad.adal.Oauth2.processTokenResponse(com.microsoft.identity.common.adal.internal.net.HttpWebResponse, com.microsoft.aad.adal.HttpEvent):com.microsoft.aad.adal.AuthenticationResult");
    }

    private AuthenticationResult retry(String str, Map<String, String> map) throws IOException, AuthenticationException {
        if (!this.mRetryOnce) {
            return null;
        }
        this.mRetryOnce = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException unused) {
            Logger.m1250v("Oauth:retry", "The thread is interrupted while it is sleeping. ");
        }
        Logger.m1250v("Oauth:retry", "Try again...");
        return postMessage(str, map);
    }

    private HttpEvent startHttpEvent() {
        HttpEvent httpEvent = new HttpEvent("Microsoft.ADAL.http_event");
        httpEvent.setRequestId(this.mRequest.getTelemetryRequestId());
        httpEvent.setMethod("Microsoft.ADAL.post");
        C0336Telemetry.getInstance().startEvent(this.mRequest.getTelemetryRequestId(), "Microsoft.ADAL.http_event");
        return httpEvent;
    }

    private void stopHttpEvent(HttpEvent httpEvent) {
        C0336Telemetry.getInstance().stopEvent(this.mRequest.getTelemetryRequestId(), httpEvent, "Microsoft.ADAL.http_event");
    }

    public String buildRefreshTokenRequestMessage(String str) throws UnsupportedEncodingException {
        Logger.m1250v(TAG, "Building request message for redeeming token with refresh token.");
        String format = String.format("%s=%s&%s=%s&%s=%s&%s=%s", new Object[]{"grant_type", StringExtensions.urlFormEncode("refresh_token"), "refresh_token", StringExtensions.urlFormEncode(str), "client_id", StringExtensions.urlFormEncode(this.mRequest.getClientId()), "client_info", "1"});
        if (!StringExtensions.isNullOrBlank(this.mRequest.getResource())) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, "resource", StringExtensions.urlFormEncode(this.mRequest.getResource())});
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getRedirectUri()) && !this.mRequest.getClientId().equalsIgnoreCase("29d9ed98-a469-4536-ade2-f981bc1d605e")) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, "redirect_uri", StringExtensions.urlFormEncode(this.mRequest.getRedirectUri())});
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getClaimsChallenge()) || this.mRequest.getClientCapabilities() != null) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLAIMS, StringExtensions.urlFormEncode(AuthenticationContext.mergeClaimsWithClientCapabilities(this.mRequest.getClaimsChallenge(), this.mRequest.getClientCapabilities()))});
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getAppName())) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLIENT_APP_NAME, StringExtensions.urlFormEncode(this.mRequest.getAppName())});
        }
        if (StringExtensions.isNullOrBlank(this.mRequest.getAppVersion())) {
            return format;
        }
        return String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLIENT_APP_VERSION, StringExtensions.urlFormEncode(this.mRequest.getAppVersion())});
    }

    public String buildTokenRequestMessage(String str) throws UnsupportedEncodingException {
        Logger.m1250v(TAG, "Building request message for redeeming token with auth code.");
        String format = String.format("%s=%s&%s=%s&%s=%s&%s=%s&%s=%s", new Object[]{"grant_type", StringExtensions.urlFormEncode("authorization_code"), "code", StringExtensions.urlFormEncode(str), "client_id", StringExtensions.urlFormEncode(this.mRequest.getClientId()), "redirect_uri", StringExtensions.urlFormEncode(this.mRequest.getRedirectUri()), "client_info", "1"});
        if (!StringExtensions.isNullOrBlank(this.mRequest.getClaimsChallenge()) || this.mRequest.getClientCapabilities() != null) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLAIMS, StringExtensions.urlFormEncode(AuthenticationContext.mergeClaimsWithClientCapabilities(this.mRequest.getClaimsChallenge(), this.mRequest.getClientCapabilities()))});
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getAppName())) {
            format = String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLIENT_APP_NAME, StringExtensions.urlFormEncode(this.mRequest.getAppName())});
        }
        if (StringExtensions.isNullOrBlank(this.mRequest.getAppVersion())) {
            return format;
        }
        return String.format(STRING_FORMAT_QUERY_PARAM, new Object[]{format, MicrosoftTokenRequest.CLIENT_APP_VERSION, StringExtensions.urlFormEncode(this.mRequest.getAppVersion())});
    }

    public String encodeProtocolState() throws UnsupportedEncodingException {
        return Base64.encodeToString(String.format("a=%s&r=%s", new Object[]{this.mRequest.getAuthority(), this.mRequest.getResource()}).getBytes("UTF-8"), 9);
    }

    public String getAuthorizationEndpoint() {
        return this.mRequest.getAuthority() + DEFAULT_AUTHORIZE_ENDPOINT;
    }

    public String getAuthorizationEndpointQueryParameters() throws UnsupportedEncodingException {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("response_type", "code").appendQueryParameter("client_id", URLEncoder.encode(this.mRequest.getClientId(), "UTF-8")).appendQueryParameter("resource", URLEncoder.encode(this.mRequest.getResource(), "UTF-8")).appendQueryParameter("redirect_uri", URLEncoder.encode(this.mRequest.getRedirectUri(), "UTF-8")).appendQueryParameter(AuthorizationResultFactory.STATE, encodeProtocolState());
        if (!StringExtensions.isNullOrBlank(this.mRequest.getLoginHint())) {
            builder.appendQueryParameter("login_hint", URLEncoder.encode(this.mRequest.getLoginHint(), "UTF-8"));
        }
        if (StringExtensions.isNullOrBlank(this.mClientVersion)) {
            this.mClientVersion = AuthenticationContext.getVersionName();
        }
        builder.appendQueryParameter("x-client-SKU", "Android").appendQueryParameter("x-client-Ver", URLEncoder.encode(this.mClientVersion, "UTF-8")).appendQueryParameter("x-client-OS", URLEncoder.encode(String.valueOf(Build.VERSION.SDK_INT), "UTF-8")).appendQueryParameter("x-client-DM", URLEncoder.encode(Build.MODEL, "UTF-8"));
        if (!StringExtensions.isNullOrBlank(this.mBrokerClientVersion)) {
            builder.appendQueryParameter("x-client-brkrver", URLEncoder.encode(this.mBrokerClientVersion, "UTF-8"));
        }
        if (this.mRequest.getCorrelationId() != null) {
            builder.appendQueryParameter(MicrosoftTokenRequest.CORRELATION_ID, URLEncoder.encode(this.mRequest.getCorrelationId().toString(), "UTF-8"));
        }
        if (this.mRequest.getPrompt() == PromptBehavior.Always) {
            builder.appendQueryParameter("prompt", URLEncoder.encode("login", "UTF-8"));
        } else if (this.mRequest.getPrompt() == PromptBehavior.REFRESH_SESSION) {
            builder.appendQueryParameter("prompt", URLEncoder.encode("refresh_session", "UTF-8"));
        }
        String extraQueryParamsAuthentication = this.mRequest.getExtraQueryParamsAuthentication();
        if (StringExtensions.isNullOrBlank(extraQueryParamsAuthentication) || !extraQueryParamsAuthentication.contains("haschrome")) {
            builder.appendQueryParameter("haschrome", "1");
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getClaimsChallenge()) || this.mRequest.getClientCapabilities() != null) {
            builder.appendQueryParameter(MicrosoftTokenRequest.CLAIMS, URLEncoder.encode(AuthenticationContext.mergeClaimsWithClientCapabilities(this.mRequest.getClaimsChallenge(), this.mRequest.getClientCapabilities()), "UTF-8"));
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getAppName())) {
            builder.appendQueryParameter(MicrosoftTokenRequest.CLIENT_APP_NAME, this.mRequest.getAppName());
        }
        if (!StringExtensions.isNullOrBlank(this.mRequest.getAppVersion())) {
            builder.appendQueryParameter(MicrosoftTokenRequest.CLIENT_APP_VERSION, this.mRequest.getAppVersion());
        }
        String query = builder.build().getQuery();
        if (StringExtensions.isNullOrBlank(extraQueryParamsAuthentication)) {
            return query;
        }
        if (!extraQueryParamsAuthentication.startsWith("&")) {
            extraQueryParamsAuthentication = Eo.a("&", extraQueryParamsAuthentication);
        }
        return Eo.a(query, extraQueryParamsAuthentication);
    }

    public String getCodeRequestUrl() throws UnsupportedEncodingException {
        return String.format("%s?%s", new Object[]{getAuthorizationEndpoint(), getAuthorizationEndpointQueryParameters()});
    }

    public AuthenticationResult getToken(String str) throws IOException, AuthenticationException {
        if (!StringExtensions.isNullOrBlank(str)) {
            HashMap<String, String> urlParameters = StringExtensions.getUrlParameters(str);
            String decodeProtocolState = decodeProtocolState(urlParameters.get(AuthorizationResultFactory.STATE));
            if (!StringExtensions.isNullOrBlank(decodeProtocolState)) {
                Uri parse = Uri.parse("http://state/path?" + decodeProtocolState);
                String queryParameter = parse.getQueryParameter("a");
                String queryParameter2 = parse.getQueryParameter("r");
                if (StringExtensions.isNullOrBlank(queryParameter) || StringExtensions.isNullOrBlank(queryParameter2) || !queryParameter2.equalsIgnoreCase(this.mRequest.getResource())) {
                    throw new AuthenticationException(ADALError.AUTH_FAILED_BAD_STATE);
                }
                AuthenticationResult processUIResponseParams = processUIResponseParams(urlParameters);
                if (processUIResponseParams == null || processUIResponseParams.getCode() == null || processUIResponseParams.getCode().isEmpty()) {
                    return processUIResponseParams;
                }
                AuthenticationResult tokenForCode = getTokenForCode(processUIResponseParams.getCode());
                if (!StringExtensions.isNullOrBlank(processUIResponseParams.getAuthority())) {
                    tokenForCode.setAuthority(processUIResponseParams.getAuthority());
                } else {
                    tokenForCode.setAuthority(this.mRequest.getAuthority());
                }
                return tokenForCode;
            }
            throw new AuthenticationException(ADALError.AUTH_FAILED_NO_STATE);
        }
        throw new IllegalArgumentException("authorizationUrl");
    }

    public String getTokenEndpoint() {
        return this.mTokenEndpoint;
    }

    public AuthenticationResult getTokenForCode(String str) throws IOException, AuthenticationException {
        if (this.mWebRequestHandler != null) {
            try {
                String buildTokenRequestMessage = buildTokenRequestMessage(str);
                Map<String, String> requestHeaders = getRequestHeaders();
                Logger.m1250v("Oauth:getTokenForCode", "Sending request to redeem token with auth code.");
                return postMessage(buildTokenRequestMessage, requestHeaders);
            } catch (UnsupportedEncodingException e) {
                Logger.m1246e("Oauth:getTokenForCode", ADALError.ENCODING_IS_NOT_SUPPORTED.getDescription(), e.getMessage(), ADALError.ENCODING_IS_NOT_SUPPORTED, e);
                return null;
            }
        } else {
            throw new IllegalArgumentException("webRequestHandler");
        }
    }

    public AuthenticationResult processUIResponseParams(Map<String, String> map) throws AuthenticationException {
        boolean z;
        String str;
        String str2;
        String str3;
        UserInfo userInfo;
        Map<String, String> map2 = map;
        ClientInfo clientInfo = null;
        if (map2.containsKey(AuthorizationResultFactory.ERROR)) {
            String str4 = map2.get(DiagnosticContext.CORRELATION_ID);
            if (!StringExtensions.isNullOrBlank(str4)) {
                try {
                    Logger.setCorrelationId(UUID.fromString(str4));
                } catch (IllegalArgumentException unused) {
                    Logger.m1245e(TAG, Eo.a("CorrelationId is malformed: ", str4), BuildConfig.FLAVOR, ADALError.CORRELATION_ID_FORMAT);
                }
            }
            StringBuilder a = Eo.a("OAuth2 error:");
            a.append(map2.get(AuthorizationResultFactory.ERROR));
            String sb = a.toString();
            StringBuilder a2 = Eo.a(" Description:");
            a2.append(map2.get(AuthorizationResultFactory.ERROR_DESCRIPTION));
            Logger.m1248i(TAG, sb, a2.toString());
            AuthenticationResult authenticationResult = new AuthenticationResult(map2.get(AuthorizationResultFactory.ERROR), map2.get(AuthorizationResultFactory.ERROR_DESCRIPTION), map2.get("error_codes"));
            if (map2.get("response_body") != null) {
                try {
                    extractJsonObjects((Map<String, String>) null, map2.get("response_body"));
                    authenticationResult.setHttpResponseBody((HashMap<String, String>) null);
                } catch (JSONException e) {
                    Logger.m1245e(TAG, "Json exception", ExceptionExtensions.getExceptionMessage(e), ADALError.SERVER_INVALID_JSON_RESPONSE);
                }
            }
            if (map2.get("response_headers") != null) {
                try {
                    authenticationResult.setHttpResponseHeaders(HashMapExtensions.jsonStringAsMapList(map2.get("response_headers")));
                } catch (JSONException e2) {
                    Logger.m1245e(TAG, "Json exception", ExceptionExtensions.getExceptionMessage(e2), ADALError.SERVER_INVALID_JSON_RESPONSE);
                }
            }
            if (map2.get("status_code") != null) {
                authenticationResult.setServiceStatusCode(Integer.parseInt(map2.get("status_code")));
            }
            return authenticationResult;
        } else if (map2.containsKey("code")) {
            AuthenticationResult authenticationResult2 = new AuthenticationResult(this.mRequest.getClientId(), map2.get("code"));
            String str5 = map2.get(MicrosoftAuthorizationResponse.CLOUD_INSTANCE_HOST_NAME);
            if (StringExtensions.isNullOrBlank(str5)) {
                return authenticationResult2;
            }
            String uri = new Uri.Builder().scheme(HTTPS_PROTOCOL_STRING).authority(str5).path(StringExtensions.getUrl(this.mRequest.getAuthority()).getPath()).build().toString();
            setTokenEndpoint(uri + DEFAULT_TOKEN_ENDPOINT);
            authenticationResult2.setAuthority(uri);
            return authenticationResult2;
        } else if (!map2.containsKey("access_token")) {
            return null;
        } else {
            String str6 = map2.get("expires_in");
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            Long valueOf = Long.valueOf((str6 == null || str6.isEmpty()) ? 3600 : Long.parseLong(str6));
            int i = 3600;
            gregorianCalendar.add(13, (str6 == null || str6.isEmpty()) ? 3600 : Integer.parseInt(str6));
            String str7 = map2.get("refresh_token");
            if (!map2.containsKey("resource") || StringExtensions.isNullOrBlank(str7)) {
                str = null;
                z = false;
            } else {
                str = map2.get("resource");
                z = true;
            }
            if (map2.containsKey("id_token")) {
                String str8 = map2.get("id_token");
                if (!StringExtensions.isNullOrBlank(str8)) {
                    Logger.m1250v(TAG, "Id token was returned, parsing id token.");
                    IdToken idToken = new IdToken(str8);
                    String tenantId = idToken.getTenantId();
                    userInfo = new UserInfo(idToken);
                    str2 = str8;
                    str3 = tenantId;
                } else {
                    Logger.m1250v(TAG, "IdToken was not returned from token request.");
                    userInfo = null;
                    str3 = null;
                    str2 = str8;
                }
            } else {
                userInfo = null;
                str3 = null;
                str2 = null;
            }
            String str9 = map2.containsKey(MicrosoftTokenResponse.FAMILY_ID) ? map2.get(MicrosoftTokenResponse.FAMILY_ID) : null;
            if (map2.containsKey("client_info")) {
                try {
                    clientInfo = new ClientInfo(map2.get("client_info"));
                } catch (ServiceException unused2) {
                    Logger.m1252w(TAG, "ClientInfo decoding/parsing failed.");
                }
            }
            String str10 = str9;
            AuthenticationResult authenticationResult3 = new AuthenticationResult(map2.get("access_token"), str7, gregorianCalendar.getTime(), z, userInfo, str3, str2, (Date) null, this.mRequest.getClientId());
            authenticationResult3.setResource(str);
            authenticationResult3.setClientInfo(clientInfo);
            authenticationResult3.setExpiresIn(valueOf);
            authenticationResult3.setResponseReceived(Long.valueOf(System.currentTimeMillis()));
            if (map2.containsKey(MicrosoftTokenResponse.EXT_EXPIRES_IN)) {
                String str11 = map2.get(MicrosoftTokenResponse.EXT_EXPIRES_IN);
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
                if (!StringExtensions.isNullOrBlank(str11)) {
                    i = Integer.parseInt(str11);
                }
                gregorianCalendar2.add(13, i);
                authenticationResult3.setExtendedExpiresOn(gregorianCalendar2.getTime());
            }
            authenticationResult3.setFamilyClientId(str10);
            return authenticationResult3;
        }
    }

    public AuthenticationResult refreshToken(String str) throws IOException, AuthenticationException {
        if (this.mWebRequestHandler != null) {
            try {
                String buildRefreshTokenRequestMessage = buildRefreshTokenRequestMessage(str);
                Map<String, String> requestHeaders = getRequestHeaders();
                requestHeaders.put("x-ms-PKeyAuth", "1.0");
                Logger.m1250v(TAG, "Sending request to redeem token with refresh token.");
                return postMessage(buildRefreshTokenRequestMessage, requestHeaders);
            } catch (UnsupportedEncodingException e) {
                Logger.m1246e(TAG, ADALError.ENCODING_IS_NOT_SUPPORTED.getDescription(), e.getMessage(), ADALError.ENCODING_IS_NOT_SUPPORTED, e);
                return null;
            }
        } else {
            Logger.m1250v(TAG, "Web request is not set correctly.");
            throw new IllegalArgumentException("webRequestHandler is null.");
        }
    }

    public void setBrokerClientVersion(String str) {
        this.mBrokerClientVersion = str;
    }

    public void setClientVersion(String str) {
        this.mClientVersion = str;
        IWebRequestHandler iWebRequestHandler = this.mWebRequestHandler;
        if (iWebRequestHandler != null) {
            iWebRequestHandler.setClientVersion(str);
        }
    }

    public void setTokenEndpoint(String str) {
        this.mTokenEndpoint = str;
    }

    public Oauth2(AuthenticationRequest authenticationRequest, IWebRequestHandler iWebRequestHandler) {
        this.mRequest = authenticationRequest;
        this.mWebRequestHandler = iWebRequestHandler;
        this.mJWSBuilder = null;
        setTokenEndpoint(this.mRequest.getAuthority() + DEFAULT_TOKEN_ENDPOINT);
    }

    public Oauth2(AuthenticationRequest authenticationRequest, IWebRequestHandler iWebRequestHandler, IJWSBuilder iJWSBuilder) {
        this.mRequest = authenticationRequest;
        this.mWebRequestHandler = iWebRequestHandler;
        this.mJWSBuilder = iJWSBuilder;
        setTokenEndpoint(this.mRequest.getAuthority() + DEFAULT_TOKEN_ENDPOINT);
    }
}
