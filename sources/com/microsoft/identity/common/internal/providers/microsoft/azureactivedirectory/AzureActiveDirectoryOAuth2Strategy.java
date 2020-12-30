package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import android.net.Uri;
import com.microsoft.aad.adal.Oauth2;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.dto.IAccountRecord;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.net.HttpResponse;
import com.microsoft.identity.common.internal.net.ObjectMapper;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenErrorResponse;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResult;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationStrategy;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import com.microsoft.identity.common.internal.providers.oauth2.TokenErrorResponse;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResponse;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResult;

/* compiled from: PG */
public class AzureActiveDirectoryOAuth2Strategy extends OAuth2Strategy<AzureActiveDirectoryAccessToken, AzureActiveDirectoryAccount, AzureActiveDirectoryAuthorizationRequest, AzureActiveDirectoryAuthorizationRequest.Builder, AuthorizationStrategy, AzureActiveDirectoryOAuth2Configuration, AzureActiveDirectoryAuthorizationResponse, AzureActiveDirectoryRefreshToken, AzureActiveDirectoryTokenRequest, AzureActiveDirectoryTokenResponse, TokenResult, AuthorizationResult> {
    public static final String TAG = "AzureActiveDirectoryOAuth2Strategy";

    public AzureActiveDirectoryOAuth2Strategy(AzureActiveDirectoryOAuth2Configuration azureActiveDirectoryOAuth2Configuration) {
        super(azureActiveDirectoryOAuth2Configuration);
        String str = TAG;
        StringBuilder a = Eo.a("Init: ");
        a.append(TAG);
        Logger.verbose(str, a.toString());
        if (azureActiveDirectoryOAuth2Configuration.getAuthorityUrl() != null) {
            setTokenEndpoint(azureActiveDirectoryOAuth2Configuration.getAuthorityUrl().toString() + Oauth2.DEFAULT_TOKEN_ENDPOINT);
            return;
        }
        setTokenEndpoint("https://login.microsoftonline.com/microsoft.com/oauth2/token");
    }

    public AzureActiveDirectoryTokenRequest createRefreshTokenRequest() {
        return null;
    }

    public AzureActiveDirectoryTokenRequest createTokenRequest(AzureActiveDirectoryAuthorizationRequest azureActiveDirectoryAuthorizationRequest, AzureActiveDirectoryAuthorizationResponse azureActiveDirectoryAuthorizationResponse) {
        return null;
    }

    public AuthorizationResultFactory getAuthorizationResultFactory() {
        throw new UnsupportedOperationException();
    }

    public TokenResult getTokenResultFromHttpResponse(HttpResponse httpResponse) {
        TokenResponse tokenResponse;
        TokenErrorResponse tokenErrorResponse = null;
        if (httpResponse.getStatusCode() >= 400) {
            String a = Eo.a(new StringBuilder(), TAG, ":", "getTokenResultFromHttpResponse");
            StringBuilder a2 = Eo.a("Status code was: ");
            a2.append(httpResponse.getStatusCode());
            Logger.warn(a, a2.toString());
            tokenErrorResponse = (TokenErrorResponse) ObjectMapper.deserializeJsonStringToObject(httpResponse.getBody(), MicrosoftTokenErrorResponse.class);
            tokenResponse = null;
        } else {
            tokenResponse = (TokenResponse) ObjectMapper.deserializeJsonStringToObject(httpResponse.getBody(), AzureActiveDirectoryTokenResponse.class);
        }
        return new TokenResult(tokenResponse, tokenErrorResponse);
    }

    public void validateAuthorizationRequest(AzureActiveDirectoryAuthorizationRequest azureActiveDirectoryAuthorizationRequest) {
    }

    public void validateTokenRequest(AzureActiveDirectoryTokenRequest azureActiveDirectoryTokenRequest) {
    }

    public AzureActiveDirectoryAccount createAccount(AzureActiveDirectoryTokenResponse azureActiveDirectoryTokenResponse) {
        try {
            Logger.info(TAG, "Constructing IDToken from response");
            IDToken iDToken = new IDToken(azureActiveDirectoryTokenResponse.getIdToken());
            Logger.info(TAG, "Constructing ClientInfo from response");
            AzureActiveDirectoryAccount azureActiveDirectoryAccount = new AzureActiveDirectoryAccount(iDToken, new ClientInfo(azureActiveDirectoryTokenResponse.getClientInfo()));
            Logger.info(TAG, "Account created");
            Logger.infoPII(TAG, azureActiveDirectoryAccount.toString());
            return azureActiveDirectoryAccount;
        } catch (ServiceException e) {
            Logger.error(Eo.a(new StringBuilder(), TAG, ":", "createAccount"), "Failed to construct IDToken or ClientInfo", (Throwable) null);
            Logger.errorPII(Eo.a(new StringBuilder(), TAG, ":", "createAccount"), "Failed with Exception", e);
            throw new RuntimeException();
        }
    }

    public AzureActiveDirectoryAccessToken getAccessTokenFromResponse(AzureActiveDirectoryTokenResponse azureActiveDirectoryTokenResponse) {
        return new AzureActiveDirectoryAccessToken(azureActiveDirectoryTokenResponse);
    }

    public String getIssuerCacheIdentifier(AzureActiveDirectoryAuthorizationRequest azureActiveDirectoryAuthorizationRequest) {
        AzureActiveDirectoryCloud azureActiveDirectoryCloud = AzureActiveDirectory.getAzureActiveDirectoryCloud(azureActiveDirectoryAuthorizationRequest.getAuthority());
        if (azureActiveDirectoryCloud != null || ((AzureActiveDirectoryOAuth2Configuration) getOAuth2Configuration()).isAuthorityHostValidationEnabled()) {
            if (!azureActiveDirectoryCloud.isValidated() && ((AzureActiveDirectoryOAuth2Configuration) getOAuth2Configuration()).isAuthorityHostValidationEnabled()) {
                Logger.warn(TAG + ":" + "getIssuerCacheIdentifier", "Authority host validation has been enabled. This data hasn't been validated, though.");
            }
            if (azureActiveDirectoryCloud.isValidated() || ((AzureActiveDirectoryOAuth2Configuration) getOAuth2Configuration()).isAuthorityHostValidationEnabled()) {
                Logger.info(TAG, "Building authority URI");
                String uri = Uri.parse(azureActiveDirectoryAuthorizationRequest.getAuthority().toString()).buildUpon().authority(azureActiveDirectoryCloud.getPreferredCacheHostName()).build().toString();
                String str = TAG;
                Logger.infoPII(str, "Issuer cache identifier created: " + uri);
                return uri;
            }
            Logger.warn(TAG + ":" + "getIssuerCacheIdentifier", "Authority host validation not specified...but there is no cloud...Hence just return the passed in Authority");
            return azureActiveDirectoryAuthorizationRequest.getAuthority().toString();
        }
        String a = Eo.a(new StringBuilder(), TAG, ":", "getIssuerCacheIdentifier");
        StringBuilder a2 = Eo.a("Discovery data does not include cloud authority and validation is off. Returning passed in Authority: ");
        a2.append(azureActiveDirectoryAuthorizationRequest.getAuthority().toString());
        Logger.warn(a, a2.toString());
        return azureActiveDirectoryAuthorizationRequest.getAuthority().toString();
    }

    public AzureActiveDirectoryRefreshToken getRefreshTokenFromResponse(AzureActiveDirectoryTokenResponse azureActiveDirectoryTokenResponse) {
        return new AzureActiveDirectoryRefreshToken(azureActiveDirectoryTokenResponse);
    }

    public AzureActiveDirectoryAuthorizationRequest.Builder createAuthorizationRequestBuilder() {
        return new AzureActiveDirectoryAuthorizationRequest.Builder();
    }

    public AzureActiveDirectoryAuthorizationRequest.Builder createAuthorizationRequestBuilder(IAccountRecord iAccountRecord) {
        return createAuthorizationRequestBuilder();
    }
}
