package com.microsoft.aad.adal;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/* compiled from: PG */
public class AuthenticationRequest implements Serializable {
    public static final int DELIM_NOT_FOUND = -1;
    public static final String UPN_DOMAIN_SUFFIX_DELIM = "@";
    public static final long serialVersionUID = 1;
    public String mAppName;
    public String mAppVersion;
    public String mAuthority;
    public String mBrokerAccountName;
    public String mClaimsChallenge;
    public List<String> mClientCapabilities;
    public String mClientId;
    public UUID mCorrelationId;
    public String mExtraQueryParamsAuthentication;
    public boolean mForceRefresh;
    public UserIdentifierType mIdentifierType;
    public transient InstanceDiscoveryMetadata mInstanceDiscoveryMetadata;
    public boolean mIsExtendedLifetimeEnabled;
    public String mLoginHint;
    public PromptBehavior mPrompt;
    public String mRedirectUri;
    public int mRequestId;
    public String mResource;
    public boolean mSilent;
    public boolean mSkipCache;
    public String mTelemetryRequestId;
    public String mUserId;
    public String mVersion;

    /* compiled from: PG */
    public enum UserIdentifierType {
        UniqueId,
        LoginHint,
        NoUser
    }

    public AuthenticationRequest() {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mIdentifierType = UserIdentifierType.NoUser;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getBrokerAccountName() {
        return this.mBrokerAccountName;
    }

    public String getClaimsChallenge() {
        return this.mClaimsChallenge;
    }

    public List<String> getClientCapabilities() {
        return this.mClientCapabilities;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public UUID getCorrelationId() {
        return this.mCorrelationId;
    }

    public String getExtraQueryParamsAuthentication() {
        return this.mExtraQueryParamsAuthentication;
    }

    public boolean getForceRefresh() {
        return this.mForceRefresh;
    }

    public InstanceDiscoveryMetadata getInstanceDiscoveryMetadata() {
        return this.mInstanceDiscoveryMetadata;
    }

    public boolean getIsExtendedLifetimeEnabled() {
        return this.mIsExtendedLifetimeEnabled;
    }

    public String getLogInfo() {
        return String.format("Request authority:%s clientid:%s", new Object[]{this.mAuthority, this.mClientId});
    }

    public String getLoginHint() {
        return this.mLoginHint;
    }

    public PromptBehavior getPrompt() {
        return this.mPrompt;
    }

    public String getRedirectUri() {
        return this.mRedirectUri;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public String getResource() {
        return this.mResource;
    }

    public boolean getSkipCache() {
        return this.mSkipCache;
    }

    public String getTelemetryRequestId() {
        return this.mTelemetryRequestId;
    }

    public String getUpnSuffix() {
        int lastIndexOf;
        String loginHint = getLoginHint();
        if (loginHint == null || -1 == (lastIndexOf = loginHint.lastIndexOf(UPN_DOMAIN_SUFFIX_DELIM))) {
            return null;
        }
        return loginHint.substring(lastIndexOf + 1);
    }

    public String getUserFromRequest() {
        UserIdentifierType userIdentifierType = UserIdentifierType.LoginHint;
        UserIdentifierType userIdentifierType2 = this.mIdentifierType;
        if (userIdentifierType == userIdentifierType2) {
            return this.mLoginHint;
        }
        if (UserIdentifierType.UniqueId == userIdentifierType2) {
            return this.mUserId;
        }
        return null;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public UserIdentifierType getUserIdentifierType() {
        return this.mIdentifierType;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public boolean isClaimsChallengePresent() {
        return !StringExtensions.isNullOrBlank(getClaimsChallenge());
    }

    public boolean isSilent() {
        return this.mSilent;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public void setAppVersion(String str) {
        this.mAppVersion = str;
    }

    public void setAuthority(String str) {
        this.mAuthority = str;
    }

    public void setBrokerAccountName(String str) {
        this.mBrokerAccountName = str;
    }

    public void setClaimsChallenge(String str) {
        this.mClaimsChallenge = str;
    }

    public void setClientCapabilities(List<String> list) {
        this.mClientCapabilities = list;
    }

    public void setClientId(String str) {
        this.mClientId = str;
    }

    public void setCorrelationId(UUID uuid) {
        this.mCorrelationId = uuid;
    }

    public void setExtraQueryParamsAuthentication(String str) {
        this.mExtraQueryParamsAuthentication = str;
    }

    public void setForceRefresh(boolean z) {
        this.mForceRefresh = z;
    }

    public void setInstanceDiscoveryMetadata(InstanceDiscoveryMetadata instanceDiscoveryMetadata) {
        this.mInstanceDiscoveryMetadata = instanceDiscoveryMetadata;
    }

    public void setLoginHint(String str) {
        this.mLoginHint = str;
    }

    public void setPrompt(PromptBehavior promptBehavior) {
        this.mPrompt = promptBehavior;
    }

    public void setRedirectUri(String str) {
        this.mRedirectUri = str;
    }

    public void setRequestId(int i) {
        this.mRequestId = i;
    }

    public void setResource(String str) {
        this.mResource = str;
    }

    public void setSilent(boolean z) {
        this.mSilent = z;
    }

    public void setSkipCache(boolean z) {
        this.mSkipCache = z;
    }

    public void setTelemetryRequestId(String str) {
        this.mTelemetryRequestId = str;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public void setUserIdentifierType(UserIdentifierType userIdentifierType) {
        this.mIdentifierType = userIdentifierType;
    }

    public void setUserName(String str) {
        this.mLoginHint = str;
        this.mBrokerAccountName = str;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public AuthenticationRequest(String str, String str2, String str3, String str4, String str5, PromptBehavior promptBehavior, String str6, UUID uuid, boolean z, String str7) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mRedirectUri = str4;
        this.mLoginHint = str5;
        this.mBrokerAccountName = this.mLoginHint;
        this.mPrompt = promptBehavior;
        this.mExtraQueryParamsAuthentication = str6;
        this.mCorrelationId = uuid;
        this.mIdentifierType = UserIdentifierType.NoUser;
        this.mIsExtendedLifetimeEnabled = z;
        this.mClaimsChallenge = str7;
    }

    public AuthenticationRequest(String str, String str2, String str3, String str4, String str5, UUID uuid, boolean z) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mRedirectUri = str4;
        this.mLoginHint = str5;
        this.mBrokerAccountName = this.mLoginHint;
        this.mCorrelationId = uuid;
        this.mIsExtendedLifetimeEnabled = z;
    }

    public AuthenticationRequest(String str, String str2, String str3, String str4, String str5, boolean z) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mRedirectUri = str4;
        this.mLoginHint = str5;
        this.mBrokerAccountName = this.mLoginHint;
        this.mIsExtendedLifetimeEnabled = z;
    }

    public AuthenticationRequest(String str, String str2, String str3, boolean z) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mIsExtendedLifetimeEnabled = z;
    }

    public AuthenticationRequest(String str, String str2, String str3, String str4, UUID uuid, boolean z) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mUserId = str4;
        this.mCorrelationId = uuid;
        this.mIsExtendedLifetimeEnabled = z;
    }

    public AuthenticationRequest(String str, String str2, String str3, String str4, UUID uuid, boolean z, boolean z2, String str5) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mResource = str2;
        this.mClientId = str3;
        this.mUserId = str4;
        this.mCorrelationId = uuid;
        this.mIsExtendedLifetimeEnabled = z;
        this.mForceRefresh = z2;
        this.mClaimsChallenge = str5;
    }

    public AuthenticationRequest(String str, String str2, String str3, UUID uuid, boolean z) {
        this.mRequestId = 0;
        this.mAuthority = null;
        this.mRedirectUri = null;
        this.mResource = null;
        this.mClientId = null;
        this.mLoginHint = null;
        this.mUserId = null;
        this.mBrokerAccountName = null;
        this.mSilent = false;
        this.mVersion = null;
        this.mIsExtendedLifetimeEnabled = false;
        this.mForceRefresh = false;
        this.mSkipCache = false;
        this.mAuthority = str;
        this.mClientId = str3;
        this.mResource = str2;
        this.mCorrelationId = uuid;
        this.mIsExtendedLifetimeEnabled = z;
    }
}
