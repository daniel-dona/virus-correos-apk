package com.microsoft.identity.common.internal.providers.oauth2;

/* compiled from: PG */
public class TokenRequest {
    @bK("client_assertion")
    public String mClientAssertion;
    @ZJ
    @bK("client_assertion_type")
    public String mClientAssertionType;
    @ZJ
    @bK("client_id")
    public String mClientId;
    @bK("client_secret")
    public String mClientSecret;
    @bK("code")
    public String mCode;
    @ZJ
    @bK("grant_type")
    public String mGrantType;
    @ZJ
    @bK("redirect_uri")
    public String mRedirectUri;
    @bK("refresh_token")
    public String mRefreshToken;
    @ZJ
    @bK("scope")
    public String mScope;

    public String getClientAssertion() {
        return this.mClientAssertion;
    }

    public String getClientAssertionType() {
        return this.mClientAssertionType;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getGrantType() {
        return this.mGrantType;
    }

    public String getRedirectUri() {
        return this.mRedirectUri;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public String getScope() {
        return this.mScope;
    }

    public void setClientAssertion(String str) {
        this.mClientAssertion = str;
    }

    public void setClientAssertionType(String str) {
        this.mClientAssertionType = str;
    }

    public void setClientId(String str) {
        this.mClientId = str;
    }

    public void setClientSecret(String str) {
        this.mClientSecret = str;
    }

    public void setCode(String str) {
        this.mCode = str;
    }

    public void setGrantType(String str) {
        this.mGrantType = str;
    }

    public void setRedirectUri(String str) {
        this.mRedirectUri = str;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public void setScope(String str) {
        this.mScope = str;
    }
}
