package com.microsoft.aad.adal;

import com.microsoft.identity.common.exception.BaseException;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryAccount;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryCloud;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryTokenResponse;

/* compiled from: PG */
public final class CoreAdapter {
    public static AzureActiveDirectoryAccount asAadAccount(UserInfo userInfo) {
        AzureActiveDirectoryAccount azureActiveDirectoryAccount = new AzureActiveDirectoryAccount();
        azureActiveDirectoryAccount.setDisplayableId(userInfo.getDisplayableId());
        azureActiveDirectoryAccount.setName(userInfo.getGivenName());
        azureActiveDirectoryAccount.setIdentityProvider(azureActiveDirectoryAccount.getIdentityProvider());
        azureActiveDirectoryAccount.setUid(userInfo.getUserId());
        return azureActiveDirectoryAccount;
    }

    public static AzureActiveDirectoryCloud asAadCloud(InstanceDiscoveryMetadata instanceDiscoveryMetadata) {
        return new AzureActiveDirectoryCloud(instanceDiscoveryMetadata.getPreferredNetwork(), instanceDiscoveryMetadata.getPreferredCache(), instanceDiscoveryMetadata.getAliases());
    }

    public static AzureActiveDirectoryTokenResponse asAadTokenResponse(AuthenticationResult authenticationResult) {
        AzureActiveDirectoryTokenResponse azureActiveDirectoryTokenResponse = new AzureActiveDirectoryTokenResponse();
        azureActiveDirectoryTokenResponse.setAccessToken(authenticationResult.getAccessToken());
        azureActiveDirectoryTokenResponse.setTokenType(authenticationResult.getAccessTokenType());
        azureActiveDirectoryTokenResponse.setRefreshToken(authenticationResult.getRefreshToken());
        azureActiveDirectoryTokenResponse.setExpiresOn(authenticationResult.getExpiresOn());
        azureActiveDirectoryTokenResponse.setExtExpiresOn(authenticationResult.getExtendedExpiresOn());
        azureActiveDirectoryTokenResponse.setIdToken(authenticationResult.getIdToken());
        azureActiveDirectoryTokenResponse.setExpiresIn(authenticationResult.getExpiresIn());
        azureActiveDirectoryTokenResponse.setResponseReceivedTime(authenticationResult.getResponseReceived());
        azureActiveDirectoryTokenResponse.setFamilyId(authenticationResult.getFamilyClientId());
        azureActiveDirectoryTokenResponse.setClientId(authenticationResult.getClientId());
        azureActiveDirectoryTokenResponse.setResource(authenticationResult.getResource());
        azureActiveDirectoryTokenResponse.setScope(authenticationResult.getResource());
        if (authenticationResult.getClientInfo() != null) {
            azureActiveDirectoryTokenResponse.setClientInfo(authenticationResult.getClientInfo().getRawClientInfo());
        }
        return azureActiveDirectoryTokenResponse;
    }

    public static AuthenticationException asAuthenticationException(BaseException baseException) {
        AuthenticationException fromCommon = ADALError.fromCommon(baseException);
        if (baseException instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) baseException;
            fromCommon.setHttpResponseBody(serviceException.getHttpResponseBody());
            fromCommon.setHttpResponseHeaders(serviceException.getHttpResponseHeaders());
            fromCommon.setServiceStatusCode(serviceException.getHttpStatusCode());
        }
        return fromCommon;
    }

    public static UserInfo asUserInfo(AzureActiveDirectoryAccount azureActiveDirectoryAccount) {
        return new UserInfo(azureActiveDirectoryAccount.getUserId(), azureActiveDirectoryAccount.getName(), azureActiveDirectoryAccount.getFamilyName(), azureActiveDirectoryAccount.getIdentityProvider(), azureActiveDirectoryAccount.getDisplayableId());
    }
}
