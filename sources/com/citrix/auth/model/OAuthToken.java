package com.citrix.auth.model;

/* compiled from: PG */
public class OAuthToken extends Token {
    public String accessToken;
    public String accessTokenType;
    public String clientId;
    public long expiresIn;
    public String identityProvider;
    public long issuedAt;
    public String refreshToken;

    public OAuthToken(String str, String str2, String str3, String str4, String str5, long j, long j2) {
        this.accessToken = str;
        this.accessTokenType = str2;
        this.refreshToken = str3;
        this.identityProvider = str4;
        this.clientId = str5;
        this.issuedAt = j;
        this.expiresIn = j2;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAccessTokenType() {
        return this.accessTokenType;
    }

    public String getClientId() {
        return this.clientId;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public String getIdentityProvider() {
        return this.identityProvider;
    }

    public long getIssuedAt() {
        return this.issuedAt;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public Token getToken() {
        throw new UnsupportedOperationException("Method not supported.");
    }
}
