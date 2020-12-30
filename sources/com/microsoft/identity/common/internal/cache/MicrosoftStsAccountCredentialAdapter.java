package com.microsoft.identity.common.internal.cache;

import com.microsoft.aad.adal.IdToken;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.dto.AccessTokenRecord;
import com.microsoft.identity.common.internal.dto.AccountRecord;
import com.microsoft.identity.common.internal.dto.CredentialType;
import com.microsoft.identity.common.internal.dto.IdTokenRecord;
import com.microsoft.identity.common.internal.dto.RefreshTokenRecord;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAccount;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftRefreshToken;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.microsoft.microsoftsts.MicrosoftStsAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.microsoft.microsoftsts.MicrosoftStsOAuth2Strategy;
import com.microsoft.identity.common.internal.providers.microsoft.microsoftsts.MicrosoftStsTokenResponse;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import com.microsoft.identity.common.internal.util.StringUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class MicrosoftStsAccountCredentialAdapter implements IAccountCredentialAdapter<MicrosoftStsOAuth2Strategy, MicrosoftStsAuthorizationRequest, MicrosoftStsTokenResponse, MicrosoftAccount, MicrosoftRefreshToken> {
    public static final String TAG = "MicrosoftStsAccountCredentialAdapter";

    private long getCachedAt() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    private long getExpiresOn(MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        return microsoftStsTokenResponse.getExpiresIn().longValue() + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    private String getExtendedExpiresOn(MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + (microsoftStsTokenResponse.getExtExpiresIn() == null ? 0 : microsoftStsTokenResponse.getExtExpiresIn().longValue()));
    }

    private String getRealm(MicrosoftStsOAuth2Strategy microsoftStsOAuth2Strategy, MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        return microsoftStsOAuth2Strategy.createAccount(microsoftStsTokenResponse).getRealm();
    }

    public AccountRecord asAccount(MicrosoftAccount microsoftAccount) {
        return new AccountRecord(microsoftAccount);
    }

    public IdTokenRecord asIdToken(MicrosoftAccount microsoftAccount, MicrosoftRefreshToken microsoftRefreshToken) {
        long cachedAt = getCachedAt();
        IDToken iDToken = microsoftAccount.getIDToken();
        IdTokenRecord idTokenRecord = new IdTokenRecord();
        idTokenRecord.setHomeAccountId(microsoftRefreshToken.getHomeAccountId());
        idTokenRecord.setEnvironment(microsoftRefreshToken.getEnvironment());
        idTokenRecord.setRealm(microsoftAccount.getRealm());
        CredentialType credentialType = CredentialType.IdToken;
        idTokenRecord.setCredentialType(IdToken.TAG);
        idTokenRecord.setClientId(microsoftRefreshToken.getClientId());
        idTokenRecord.setSecret(iDToken.getRawIDToken());
        idTokenRecord.setCachedAt(String.valueOf(cachedAt));
        idTokenRecord.setAuthority(SchemaUtil.getAuthority(iDToken));
        return idTokenRecord;
    }

    public RefreshTokenRecord asRefreshToken(MicrosoftRefreshToken microsoftRefreshToken) {
        RefreshTokenRecord refreshTokenRecord = new RefreshTokenRecord();
        refreshTokenRecord.setHomeAccountId(microsoftRefreshToken.getHomeAccountId());
        refreshTokenRecord.setEnvironment(microsoftRefreshToken.getEnvironment());
        CredentialType credentialType = CredentialType.RefreshToken;
        refreshTokenRecord.setCredentialType("RefreshToken");
        refreshTokenRecord.setClientId(microsoftRefreshToken.getClientId());
        refreshTokenRecord.setSecret(microsoftRefreshToken.getSecret());
        refreshTokenRecord.setTarget(microsoftRefreshToken.getTarget());
        refreshTokenRecord.setCachedAt(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
        refreshTokenRecord.setFamilyId(microsoftRefreshToken.getFamilyId());
        return refreshTokenRecord;
    }

    public AccessTokenRecord createAccessToken(MicrosoftStsOAuth2Strategy microsoftStsOAuth2Strategy, MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest, MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        try {
            long cachedAt = getCachedAt();
            long expiresOn = getExpiresOn(microsoftStsTokenResponse);
            ClientInfo clientInfo = new ClientInfo(microsoftStsTokenResponse.getClientInfo());
            AccessTokenRecord accessTokenRecord = new AccessTokenRecord();
            CredentialType credentialType = CredentialType.AccessToken;
            accessTokenRecord.setCredentialType("AccessToken");
            accessTokenRecord.setHomeAccountId(SchemaUtil.getHomeAccountId(clientInfo));
            accessTokenRecord.setRealm(getRealm(microsoftStsOAuth2Strategy, microsoftStsTokenResponse));
            if (!StringUtil.isEmpty(microsoftStsTokenResponse.getAuthority())) {
                accessTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifierFromAuthority(new URL(microsoftStsTokenResponse.getAuthority())));
            } else {
                accessTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifier(microsoftStsAuthorizationRequest));
            }
            accessTokenRecord.setClientId(microsoftStsAuthorizationRequest.getClientId());
            accessTokenRecord.setTarget(microsoftStsAuthorizationRequest.getScope());
            accessTokenRecord.setCachedAt(String.valueOf(cachedAt));
            accessTokenRecord.setExpiresOn(String.valueOf(expiresOn));
            accessTokenRecord.setSecret(microsoftStsTokenResponse.getAccessToken());
            accessTokenRecord.setExtendedExpiresOn(getExtendedExpiresOn(microsoftStsTokenResponse));
            if (!StringUtil.isEmpty(microsoftStsTokenResponse.getAuthority())) {
                accessTokenRecord.setAuthority(microsoftStsTokenResponse.getAuthority());
            } else {
                accessTokenRecord.setAuthority(microsoftStsAuthorizationRequest.getAuthority().toString());
            }
            accessTokenRecord.setAccessTokenType(microsoftStsTokenResponse.getTokenType());
            return accessTokenRecord;
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountRecord createAccount(MicrosoftStsOAuth2Strategy microsoftStsOAuth2Strategy, MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest, MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        Logger.verbose(TAG, "Creating Account");
        return new AccountRecord(microsoftStsOAuth2Strategy.createAccount(microsoftStsTokenResponse));
    }

    public IdTokenRecord createIdToken(MicrosoftStsOAuth2Strategy microsoftStsOAuth2Strategy, MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest, MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        try {
            ClientInfo clientInfo = new ClientInfo(microsoftStsTokenResponse.getClientInfo());
            IdTokenRecord idTokenRecord = new IdTokenRecord();
            idTokenRecord.setHomeAccountId(SchemaUtil.getHomeAccountId(clientInfo));
            if (microsoftStsTokenResponse.getAuthority() != null) {
                idTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifierFromAuthority(new URL(microsoftStsTokenResponse.getAuthority())));
            } else {
                idTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifier(microsoftStsAuthorizationRequest));
            }
            idTokenRecord.setRealm(getRealm(microsoftStsOAuth2Strategy, microsoftStsTokenResponse));
            idTokenRecord.setCredentialType(SchemaUtil.getCredentialTypeFromVersion(microsoftStsTokenResponse.getIdToken()));
            idTokenRecord.setClientId(microsoftStsAuthorizationRequest.getClientId());
            idTokenRecord.setSecret(microsoftStsTokenResponse.getIdToken());
            idTokenRecord.setAuthority(microsoftStsTokenResponse.getAuthority());
            return idTokenRecord;
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public RefreshTokenRecord createRefreshToken(MicrosoftStsOAuth2Strategy microsoftStsOAuth2Strategy, MicrosoftStsAuthorizationRequest microsoftStsAuthorizationRequest, MicrosoftStsTokenResponse microsoftStsTokenResponse) {
        try {
            long cachedAt = getCachedAt();
            ClientInfo clientInfo = new ClientInfo(microsoftStsTokenResponse.getClientInfo());
            RefreshTokenRecord refreshTokenRecord = new RefreshTokenRecord();
            CredentialType credentialType = CredentialType.RefreshToken;
            refreshTokenRecord.setCredentialType("RefreshToken");
            if (microsoftStsTokenResponse.getAuthority() != null) {
                refreshTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifierFromAuthority(new URL(microsoftStsTokenResponse.getAuthority())));
            } else {
                refreshTokenRecord.setEnvironment(microsoftStsOAuth2Strategy.getIssuerCacheIdentifier(microsoftStsAuthorizationRequest));
            }
            refreshTokenRecord.setHomeAccountId(SchemaUtil.getHomeAccountId(clientInfo));
            refreshTokenRecord.setClientId(microsoftStsAuthorizationRequest.getClientId());
            refreshTokenRecord.setSecret(microsoftStsTokenResponse.getRefreshToken());
            refreshTokenRecord.setFamilyId(microsoftStsTokenResponse.getFamilyId());
            refreshTokenRecord.setTarget(microsoftStsAuthorizationRequest.getScope());
            refreshTokenRecord.setCachedAt(String.valueOf(cachedAt));
            return refreshTokenRecord;
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
