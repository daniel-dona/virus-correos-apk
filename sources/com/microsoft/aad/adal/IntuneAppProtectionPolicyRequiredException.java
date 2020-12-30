package com.microsoft.aad.adal;

/* compiled from: PG */
public class IntuneAppProtectionPolicyRequiredException extends AuthenticationException {
    public final String mAccountUpn;
    public final String mAccountUserId;
    public final String mAuthorityUrl;
    public final String mTenantId;

    public IntuneAppProtectionPolicyRequiredException(String str, String str2, String str3, String str4, String str5) {
        super(ADALError.AUTH_FAILED_INTUNE_POLICY_REQUIRED, str);
        this.mAccountUpn = str2;
        this.mAccountUserId = str3;
        this.mTenantId = str4;
        this.mAuthorityUrl = str5;
    }

    public String getAccountUpn() {
        return this.mAccountUpn;
    }

    public String getAccountUserId() {
        return this.mAccountUserId;
    }

    public String getAuthorityURL() {
        return this.mAuthorityUrl;
    }

    public String getTenantId() {
        return this.mTenantId;
    }
}
