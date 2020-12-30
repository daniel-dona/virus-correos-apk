package com.microsoft.identity.common.internal.providers.oauth2;

/* compiled from: PG */
public class TokenResponse implements ISuccessResponse {
    @bK("access_token")
    public String mAccessToken;
    @ZJ
    @bK("expires_in")
    public Long mExpiresIn;
    @bK("id_token")
    public String mIdToken;
    @bK("refresh_token")
    public String mRefreshToken;
    @ZJ
    public long mResponseReceivedTime;
    @ZJ
    @bK("scope")
    public String mScope;
    @ZJ
    @bK("state")
    public String mState;
    @ZJ
    @bK("token_type")
    public String mTokenType;

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public Long getExpiresIn() {
        return this.mExpiresIn;
    }

    public String getIdToken() {
        return this.mIdToken;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public long getResponseReceivedTime() {
        return this.mResponseReceivedTime;
    }

    public String getScope() {
        return this.mScope;
    }

    public String getState() {
        return this.mState;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public void setExpiresIn(Long l) {
        this.mExpiresIn = l;
    }

    public void setIdToken(String str) {
        this.mIdToken = str;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public void setResponseReceivedTime(Long l) {
        this.mResponseReceivedTime = l.longValue();
    }

    public void setScope(String str) {
        this.mScope = str;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public void setTokenType(String str) {
        this.mTokenType = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("TokenResponse{mExpiresIn=");
        a.append(this.mExpiresIn);
        a.append(", mAccessToken='");
        Eo.a(a, this.mAccessToken, '\'', ", mTokenType='");
        Eo.a(a, this.mTokenType, '\'', ", mRefreshToken='");
        Eo.a(a, this.mRefreshToken, '\'', ", mScope='");
        Eo.a(a, this.mScope, '\'', ", mState='");
        Eo.a(a, this.mState, '\'', ", mIdToken='");
        Eo.a(a, this.mIdToken, '\'', ", mResponseReceivedTime=");
        a.append(this.mResponseReceivedTime);
        a.append('}');
        return a.toString();
    }
}
