package com.microsoft.identity.common.internal.providers.oauth2;

/* compiled from: PG */
public class AuthorizationResponse implements ISuccessResponse {
    public String mCode;
    @ZJ
    public String mState;

    public AuthorizationResponse(String str) {
        this(str, (String) null);
    }

    public String getCode() {
        return this.mCode;
    }

    public String getState() {
        return this.mState;
    }

    public void setCode(String str) {
        this.mCode = str;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public AuthorizationResponse(String str, String str2) {
        this.mCode = str;
        this.mState = str2;
    }
}
