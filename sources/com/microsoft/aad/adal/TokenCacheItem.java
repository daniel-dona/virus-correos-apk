package com.microsoft.aad.adal;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/* compiled from: PG */
public class TokenCacheItem implements Serializable {
    public static final String TAG = "TokenCacheItem";
    public static final long serialVersionUID = 1;
    public String mAccessToken;
    public String mAuthority;
    public String mClientId;
    public Date mExpiresOn;
    public Date mExtendedExpiresOn;
    public String mFamilyClientId;
    public boolean mIsMultiResourceRefreshToken;
    public String mRawIdToken;
    public String mRefreshtoken;
    public String mResource;
    public String mSpeRing;
    public String mTenantId;
    public Date mTokenUpdatedTime;
    public UserInfo mUserInfo;

    public TokenCacheItem() {
    }

    public static TokenCacheItem createFRRTTokenCacheItem(String str, AuthenticationResult authenticationResult) {
        return new TokenCacheItem(str, authenticationResult);
    }

    public static TokenCacheItem createMRRTTokenCacheItem(String str, String str2, AuthenticationResult authenticationResult) {
        TokenCacheItem tokenCacheItem = new TokenCacheItem(str, authenticationResult);
        tokenCacheItem.setClientId(str2);
        return tokenCacheItem;
    }

    public static TokenCacheItem createRegularTokenCacheItem(String str, String str2, String str3, AuthenticationResult authenticationResult) {
        TokenCacheItem tokenCacheItem = new TokenCacheItem(str, authenticationResult);
        tokenCacheItem.setClientId(str3);
        tokenCacheItem.setResource(str2);
        tokenCacheItem.setAccessToken(authenticationResult.getAccessToken());
        return tokenCacheItem;
    }

    public static boolean isTokenExpired(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.add(13, AuthenticationSettings.INSTANCE.getExpirationBuffer());
        Date time = instance.getTime();
        Logger.m1248i(TAG, "Check token expiration time.", "expiresOn:" + date + " timeWithBuffer:" + instance.getTime() + " Buffer:" + AuthenticationSettings.INSTANCE.getExpirationBuffer());
        return date != null && date.before(time);
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public Date getExpiresOn() {
        return DateExtensions.createCopy(this.mExpiresOn);
    }

    public final Date getExtendedExpiresOn() {
        return DateExtensions.createCopy(this.mExtendedExpiresOn);
    }

    public final String getFamilyClientId() {
        return this.mFamilyClientId;
    }

    public boolean getIsMultiResourceRefreshToken() {
        return this.mIsMultiResourceRefreshToken;
    }

    public String getRawIdToken() {
        return this.mRawIdToken;
    }

    public String getRefreshToken() {
        return this.mRefreshtoken;
    }

    public String getResource() {
        return this.mResource;
    }

    public String getSpeRing() {
        return this.mSpeRing;
    }

    public String getTenantId() {
        return this.mTenantId;
    }

    public TokenEntryType getTokenEntryType() {
        if (!StringExtensions.isNullOrBlank(getResource())) {
            return TokenEntryType.REGULAR_TOKEN_ENTRY;
        }
        if (StringExtensions.isNullOrBlank(getClientId())) {
            return TokenEntryType.FRT_TOKEN_ENTRY;
        }
        return TokenEntryType.MRRT_TOKEN_ENTRY;
    }

    public final Date getTokenUpdateTime() {
        return this.mTokenUpdatedTime;
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    public final boolean isExtendedLifetimeValid() {
        if (this.mExtendedExpiresOn == null || StringExtensions.isNullOrBlank(this.mAccessToken)) {
            return false;
        }
        return !isTokenExpired(this.mExtendedExpiresOn);
    }

    public boolean isFamilyToken() {
        return !StringExtensions.isNullOrBlank(this.mFamilyClientId);
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public void setAuthority(String str) {
        this.mAuthority = str;
    }

    public void setClientId(String str) {
        this.mClientId = str;
    }

    public void setExpiresOn(Date date) {
        this.mExpiresOn = DateExtensions.createCopy(date);
    }

    public final void setExtendedExpiresOn(Date date) {
        this.mExtendedExpiresOn = DateExtensions.createCopy(date);
    }

    public final void setFamilyClientId(String str) {
        this.mFamilyClientId = str;
    }

    public void setIsMultiResourceRefreshToken(boolean z) {
        this.mIsMultiResourceRefreshToken = z;
    }

    public void setRawIdToken(String str) {
        this.mRawIdToken = str;
    }

    public void setRefreshToken(String str) {
        this.mRefreshtoken = str;
    }

    public void setResource(String str) {
        this.mResource = str;
    }

    public void setSpeRing(String str) {
        this.mSpeRing = str;
    }

    public void setTenantId(String str) {
        this.mTenantId = str;
    }

    public final void setTokenUpdateTime(Date date) {
        if (date == null) {
            this.mTokenUpdatedTime = null;
        } else {
            this.mTokenUpdatedTime = new Date(date.getTime());
        }
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public TokenCacheItem(TokenCacheItem tokenCacheItem) {
        this.mAuthority = tokenCacheItem.getAuthority();
        this.mResource = tokenCacheItem.getResource();
        this.mClientId = tokenCacheItem.getClientId();
        this.mAccessToken = tokenCacheItem.getAccessToken();
        this.mRefreshtoken = tokenCacheItem.getRefreshToken();
        this.mRawIdToken = tokenCacheItem.getRawIdToken();
        this.mUserInfo = tokenCacheItem.getUserInfo();
        this.mExpiresOn = tokenCacheItem.getExpiresOn();
        this.mIsMultiResourceRefreshToken = tokenCacheItem.getIsMultiResourceRefreshToken();
        this.mTenantId = tokenCacheItem.getTenantId();
        this.mFamilyClientId = tokenCacheItem.getFamilyClientId();
        this.mExtendedExpiresOn = tokenCacheItem.getExtendedExpiresOn();
        this.mSpeRing = tokenCacheItem.getSpeRing();
    }

    public TokenCacheItem(String str, AuthenticationResult authenticationResult) {
        if (authenticationResult == null) {
            throw new IllegalArgumentException("authenticationResult");
        } else if (!StringExtensions.isNullOrBlank(str)) {
            this.mAuthority = str;
            this.mExpiresOn = authenticationResult.getExpiresOn();
            this.mIsMultiResourceRefreshToken = authenticationResult.getIsMultiResourceRefreshToken();
            this.mTenantId = authenticationResult.getTenantId();
            this.mUserInfo = authenticationResult.getUserInfo();
            this.mRawIdToken = authenticationResult.getIdToken();
            this.mRefreshtoken = authenticationResult.getRefreshToken();
            this.mFamilyClientId = authenticationResult.getFamilyClientId();
            this.mExtendedExpiresOn = authenticationResult.getExtendedExpiresOn();
            if (authenticationResult.getCliTelemInfo() != null) {
                this.mSpeRing = authenticationResult.getCliTelemInfo().getSpeRing();
            }
        } else {
            throw new IllegalArgumentException("authority");
        }
    }
}
