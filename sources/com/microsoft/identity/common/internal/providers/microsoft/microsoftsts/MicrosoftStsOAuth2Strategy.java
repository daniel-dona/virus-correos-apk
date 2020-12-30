package com.microsoft.identity.common.internal.providers.microsoft.microsoftsts;

import android.net.Uri;
import android.util.Pair;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.controllers.BaseController;
import com.microsoft.identity.common.internal.dto.IAccountRecord;
import com.microsoft.identity.common.internal.logging.DiagnosticContext;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.net.HttpRequest;
import com.microsoft.identity.common.internal.net.HttpResponse;
import com.microsoft.identity.common.internal.net.ObjectMapper;
import com.microsoft.identity.common.internal.platform.Device;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenErrorResponse;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectory;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryCloud;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.microsoft.microsoftsts.MicrosoftStsAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResult;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationStrategy;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import com.microsoft.identity.common.internal.providers.oauth2.TokenErrorResponse;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResult;
import com.microsoft.identity.common.internal.telemetry.CliTelemInfo;
import com.microsoft.identity.common.internal.ui.webview.challengehandlers.PKeyAuthChallengeFactory;
import com.microsoft.identity.common.internal.ui.webview.challengehandlers.PKeyAuthChallengeHandler;
import com.microsoft.identity.common.internal.util.HeaderSerializationUtil;
import com.microsoft.identity.common.internal.util.StringUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/* compiled from: PG */
public class MicrosoftStsOAuth2Strategy extends OAuth2Strategy<MicrosoftStsAccessToken, MicrosoftStsAccount, MicrosoftStsAuthorizationRequest, MicrosoftStsAuthorizationRequest.Builder, AuthorizationStrategy, MicrosoftStsOAuth2Configuration, MicrosoftStsAuthorizationResponse, MicrosoftStsRefreshToken, MicrosoftStsTokenRequest, MicrosoftStsTokenResponse, TokenResult, AuthorizationResult> {
    public static final String TAG = "MicrosoftStsOAuth2Strategy";

    public MicrosoftStsOAuth2Strategy(MicrosoftStsOAuth2Configuration microsoftStsOAuth2Configuration) {
        super(microsoftStsOAuth2Configuration);
        setTokenEndpoint(microsoftStsOAuth2Configuration.getTokenEndpoint().toString());
    }

    private String getCloudSpecificTenantEndpoint(MicrosoftStsAuthorizationResponse microsoftStsAuthorizationResponse) {
        if (!StringUtil.isEmpty(microsoftStsAuthorizationResponse.getCloudGraphHostName())) {
            return Uri.parse(this.mTokenEndpoint).buildUpon().authority(microsoftStsAuthorizationResponse.getCloudInstanceHostName()).build().toString();
        }
        return this.mTokenEndpoint;
    }

    private HttpResponse performPKeyAuthRequest(HttpResponse httpResponse, MicrosoftStsTokenRequest microsoftStsTokenRequest) throws IOException, ClientException {
        String serializeObjectToFormUrlEncoded = ObjectMapper.serializeObjectToFormUrlEncoded(microsoftStsTokenRequest);
        TreeMap treeMap = new TreeMap();
        treeMap.put(MicrosoftTokenRequest.CORRELATION_ID, DiagnosticContext.getRequestContext().get(DiagnosticContext.CORRELATION_ID));
        treeMap.putAll(Device.getPlatformIdParameters());
        String str = (String) httpResponse.getHeaders().get("WWW-Authenticate").get(0);
        Logger.info(TAG + "#performPkeyAuthRequest", "Device certificate challenge request. ");
        String a = Eo.a(new StringBuilder(), TAG, "#performPkeyAuthRequest");
        Logger.infoPII(a, "Challenge header: " + str);
        try {
            PKeyAuthChallengeFactory pKeyAuthChallengeFactory = new PKeyAuthChallengeFactory();
            URL url = StringExtensions.getUrl(this.mTokenEndpoint);
            treeMap.putAll(PKeyAuthChallengeHandler.getChallengeHeader(pKeyAuthChallengeFactory.getPKeyAuthChallenge(str, url.toString())));
            return HttpRequest.sendPost(url, treeMap, serializeObjectToFormUrlEncoded.getBytes("UTF-8"), OAuth2Strategy.TOKEN_REQUEST_CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            throw new ClientException(ClientException.UNSUPPORTED_ENCODING, "Unsupported encoding", e);
        }
    }

    public AuthorizationResultFactory getAuthorizationResultFactory() {
        return new MicrosoftStsAuthorizationResultFactory();
    }

    public String getIssuerCacheIdentifierFromAuthority(URL url) {
        AzureActiveDirectoryCloud azureActiveDirectoryCloud = AzureActiveDirectory.getAzureActiveDirectoryCloud(url);
        if (azureActiveDirectoryCloud == null) {
            return url.getHost();
        }
        String preferredCacheHostName = azureActiveDirectoryCloud.getPreferredCacheHostName();
        Logger.info(TAG + ":getIssuerCacheIdentifierFromAuthority", "Using preferred cache host name...");
        String a = Eo.a(new StringBuilder(), TAG, ":getIssuerCacheIdentifierFromAuthority");
        Logger.infoPII(a, "Preferred cache hostname: [" + preferredCacheHostName + "]");
        return preferredCacheHostName;
    }

    public TokenResult getTokenResultFromHttpResponse(HttpResponse httpResponse) {
        MicrosoftStsTokenResponse microsoftStsTokenResponse;
        List list;
        Logger.verbose(TAG + ":getTokenResultFromHttpResponse", "Getting TokenResult from HttpResponse...");
        TokenErrorResponse tokenErrorResponse = null;
        if (httpResponse.getStatusCode() >= 400) {
            TokenErrorResponse tokenErrorResponse2 = (TokenErrorResponse) ObjectMapper.deserializeJsonStringToObject(httpResponse.getBody(), MicrosoftTokenErrorResponse.class);
            tokenErrorResponse2.setStatusCode(httpResponse.getStatusCode());
            if (httpResponse.getHeaders() != null) {
                tokenErrorResponse2.setResponseHeadersJson(HeaderSerializationUtil.toJson(httpResponse.getHeaders()));
            }
            tokenErrorResponse2.setResponseBody(httpResponse.getBody());
            tokenErrorResponse = tokenErrorResponse2;
            microsoftStsTokenResponse = null;
        } else {
            microsoftStsTokenResponse = (MicrosoftStsTokenResponse) ObjectMapper.deserializeJsonStringToObject(httpResponse.getBody(), MicrosoftStsTokenResponse.class);
        }
        TokenResult tokenResult = new TokenResult(microsoftStsTokenResponse, tokenErrorResponse);
        BaseController.logResult(TAG, tokenResult);
        if (!(httpResponse.getHeaders() == null || (list = httpResponse.getHeaders().get("x-ms-clitelem")) == null || list.isEmpty())) {
            CliTelemInfo fromXMsCliTelemHeader = CliTelemInfo.fromXMsCliTelemHeader((String) list.get(0));
            tokenResult.setCliTelemInfo(fromXMsCliTelemHeader);
            if (!(microsoftStsTokenResponse == null || fromXMsCliTelemHeader == null)) {
                microsoftStsTokenResponse.setSpeRing(fromXMsCliTelemHeader.getSpeRing());
                microsoftStsTokenResponse.setRefreshTokenAge(fromXMsCliTelemHeader.getRefreshTokenAge());
                microsoftStsTokenResponse.setCliTelemErrorCode(fromXMsCliTelemHeader.getServerErrorCode());
                microsoftStsTokenResponse.setCliTelemSubErrorCode(fromXMsCliTelemHeader.getServerSubErrorCode());
            }
        }
        return tokenResult;
    }

    public void validateAuthorizationRequest(MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest) {
    }

    public void validateTokenRequest(MicrosoftStsTokenRequest microsoftStsTokenRequest) {
    }

    public MicrosoftStsAccount createAccount(MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        Eo.d(new StringBuilder(), TAG, ":createAccount", "Creating account from TokenResponse...");
        URL url = null;
        try {
            MicrosoftStsAccount microsoftStsAccount = new MicrosoftStsAccount(new IDToken(microsoftStsTokenResponse.getIdToken()), new ClientInfo(microsoftStsTokenResponse.getClientInfo()));
            try {
                url = new URL(this.mTokenEndpoint);
            } catch (MalformedURLException unused) {
                Eo.d(new StringBuilder(), TAG, ":createAccount", "Creating account from TokenResponse failed due to malformed URL (mTokenEndpoint)...");
            }
            if (url != null) {
                microsoftStsAccount.setEnvironment(getIssuerCacheIdentifierFromAuthority(url));
            }
            return microsoftStsAccount;
        } catch (ServiceException e) {
            Logger.error(TAG + ":createAccount", "Failed to construct IDToken or ClientInfo", (Throwable) null);
            Logger.errorPII(TAG + ":createAccount", "Failed with Exception", e);
            throw new RuntimeException();
        }
    }

    public MicrosoftStsTokenRequest createRefreshTokenRequest() {
        Logger.verbose(TAG + ":createRefreshTokenRequest", "Creating refresh token request");
        MicrosoftStsTokenRequest microsoftStsTokenRequest = new MicrosoftStsTokenRequest();
        microsoftStsTokenRequest.setGrantType("refresh_token");
        return microsoftStsTokenRequest;
    }

    public MicrosoftStsTokenRequest createTokenRequest(MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest, MicrosoftStsAuthorizationResponse microsoftStsAuthorizationResponse) {
        Eo.d(new StringBuilder(), TAG, ":createTokenRequest", "Creating TokenRequest...");
        if (((MicrosoftStsOAuth2Configuration) this.mConfig).getMultipleCloudsSupported().booleanValue() || microsoftStsAuthorizationRequest.getMultipleCloudAware().booleanValue()) {
            Logger.verbose(TAG, "get cloud specific authority based on authorization response.");
            setTokenEndpoint(getCloudSpecificTenantEndpoint(microsoftStsAuthorizationResponse));
        }
        MicrosoftStsTokenRequest microsoftStsTokenRequest = new MicrosoftStsTokenRequest();
        microsoftStsTokenRequest.setCodeVerifier(microsoftStsAuthorizationRequest.getPkceChallenge().getCodeVerifier());
        microsoftStsTokenRequest.setCode(microsoftStsAuthorizationResponse.getCode());
        microsoftStsTokenRequest.setRedirectUri(microsoftStsAuthorizationRequest.getRedirectUri());
        microsoftStsTokenRequest.setClientId(microsoftStsAuthorizationRequest.getClientId());
        try {
            microsoftStsTokenRequest.setCorrelationId(UUID.fromString((String) DiagnosticContext.getRequestContext().get(DiagnosticContext.CORRELATION_ID)));
        } catch (IllegalArgumentException e) {
            Logger.error("MicrosoftSTSOAuth2Strategy", "Correlation id on diagnostic context is not a UUID.", e);
        }
        return microsoftStsTokenRequest;
    }

    public MicrosoftStsAccessToken getAccessTokenFromResponse(MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        Logger.verbose(TAG + ":getAccessTokenFromResponse", "Getting AT from TokenResponse...");
        return new MicrosoftStsAccessToken(microsoftStsTokenResponse);
    }

    public String getIssuerCacheIdentifier(MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest) {
        URL authority = microsoftStsAuthorizationRequest.getAuthority();
        AzureActiveDirectoryCloud azureActiveDirectoryCloud = AzureActiveDirectory.getAzureActiveDirectoryCloud(authority);
        if (azureActiveDirectoryCloud == null) {
            return authority.getHost();
        }
        String preferredCacheHostName = azureActiveDirectoryCloud.getPreferredCacheHostName();
        Logger.info(TAG + ":getIssuerCacheIdentifier", "Using preferred cache host name...");
        String a = Eo.a(new StringBuilder(), TAG, ":getIssuerCacheIdentifier");
        Logger.infoPII(a, "Preferred cache hostname: [" + preferredCacheHostName + "]");
        return preferredCacheHostName;
    }

    public MicrosoftStsRefreshToken getRefreshTokenFromResponse(MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        Logger.verbose(TAG + ":getRefreshTokenFromResponse", "Getting RT from TokenResponse...");
        return new MicrosoftStsRefreshToken(microsoftStsTokenResponse);
    }

    public HttpResponse performTokenRequest(MicrosoftStsTokenRequest microsoftStsTokenRequest) throws IOException, ClientException {
        HttpResponse performTokenRequest = super.performTokenRequest(microsoftStsTokenRequest);
        if (performTokenRequest.getStatusCode() != 401 || performTokenRequest.getHeaders() == null || !performTokenRequest.getHeaders().containsKey("WWW-Authenticate")) {
            return performTokenRequest;
        }
        Logger.info(TAG + ":performTokenRequest", "Receiving device certificate challenge request. ");
        return performPKeyAuthRequest(performTokenRequest, microsoftStsTokenRequest);
    }

    public MicrosoftStsAuthorizationRequest.Builder createAuthorizationRequestBuilder() {
        Logger.verbose(TAG + ":createAuthorizationRequestBuilder", "Creating AuthorizationRequestBuilder...");
        MicrosoftStsAuthorizationRequest.Builder builder = new MicrosoftStsAuthorizationRequest.Builder();
        builder.setAuthority(((MicrosoftStsOAuth2Configuration) this.mConfig).getAuthorityUrl());
        if (((MicrosoftStsOAuth2Configuration) this.mConfig).getSlice() != null) {
            Eo.d(new StringBuilder(), TAG, ":createAuthorizationRequestBuilder", "Setting slice params...");
            builder.setSlice(((MicrosoftStsOAuth2Configuration) this.mConfig).getSlice());
        }
        Map<String, String> platformIdParameters = Device.getPlatformIdParameters();
        builder.setLibraryName(platformIdParameters.get("x-client-SKU"));
        builder.setLibraryVersion(platformIdParameters.get("x-client-Ver"));
        builder.setFlightParameters(((MicrosoftStsOAuth2Configuration) this.mConfig).getFlightParameters());
        builder.setMultipleCloudAware(((MicrosoftStsOAuth2Configuration) this.mConfig).getMultipleCloudsSupported().booleanValue());
        return builder;
    }

    public MicrosoftStsAuthorizationRequest.Builder createAuthorizationRequestBuilder(IAccountRecord iAccountRecord) {
        Logger.verbose(TAG + ":createAuthorizationRequestBuilder", "Creating AuthorizationRequestBuilder");
        MicrosoftStsAuthorizationRequest.Builder createAuthorizationRequestBuilder = createAuthorizationRequestBuilder();
        if (iAccountRecord != null) {
            Pair<String, String> tenantInfo = StringUtil.getTenantInfo(iAccountRecord.getHomeAccountId());
            if (!StringExtensions.isNullOrBlank((String) tenantInfo.first) && !StringExtensions.isNullOrBlank((String) tenantInfo.second)) {
                createAuthorizationRequestBuilder.setUid((String) tenantInfo.first);
                createAuthorizationRequestBuilder.setUtid((String) tenantInfo.second);
                String a = Eo.a(new StringBuilder(), TAG, ":createAuthorizationRequestBuilder");
                StringBuilder a2 = Eo.a("Builder w/ uid: [");
                a2.append((String) tenantInfo.first);
                a2.append("]");
                Logger.verbosePII(a, a2.toString());
                String a3 = Eo.a(new StringBuilder(), TAG, ":createAuthorizationRequestBuilder");
                StringBuilder a4 = Eo.a("Builder w/ utid: [");
                a4.append((String) tenantInfo.second);
                a4.append("]");
                Logger.verbosePII(a3, a4.toString());
            }
        }
        return createAuthorizationRequestBuilder;
    }
}
