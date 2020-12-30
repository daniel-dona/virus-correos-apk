package com.microsoft.identity.common.internal.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class AccessTokenRecord extends Credential {
    @bK("access_token_type")
    public String mAccessTokenType;
    @bK("authority")
    public String mAuthority;
    @bK("expires_on")
    public String mExpiresOn;
    @bK("extended_expires_on")
    public String mExtendedExpiresOn;
    @bK("realm")
    public String mRealm;
    @bK("target")
    public String mTarget;

    private boolean isExpired(String str) {
        return new Date(TimeUnit.SECONDS.toMillis(Long.valueOf(str).longValue())).before(Calendar.getInstance().getTime());
    }

    public String getAccessTokenType() {
        return this.mAccessTokenType;
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getExpiresOn() {
        return this.mExpiresOn;
    }

    public String getExtendedExpiresOn() {
        return this.mExtendedExpiresOn;
    }

    public String getRealm() {
        return this.mRealm;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public void setAccessTokenType(String str) {
        this.mAccessTokenType = str;
    }

    public void setAuthority(String str) {
        this.mAuthority = str;
    }

    public void setExpiresOn(String str) {
        this.mExpiresOn = str;
    }

    public void setExtendedExpiresOn(String str) {
        this.mExtendedExpiresOn = str;
    }

    public void setRealm(String str) {
        this.mRealm = str;
    }

    public void setTarget(String str) {
        this.mTarget = str;
    }

    public boolean isExpired() {
        return isExpired(getExpiresOn());
    }
}
