package com.microsoft.identity.common.adal.internal;

import android.net.Uri;
import com.microsoft.identity.common.adal.internal.util.DateExtensions;
import com.microsoft.identity.common.internal.cache.SchemaUtil;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryAccount;
import com.microsoft.identity.common.internal.result.ILocalAuthenticationResult;
import java.util.Date;

/* compiled from: PG */
public class ADALUserInfo {
    public String mDisplayableId;
    public String mFamilyName;
    public String mGivenName;
    public String mIdentityProvider;
    public transient Uri mPasswordChangeUrl;
    public transient Date mPasswordExpiresOn;
    public String mUniqueId;

    public ADALUserInfo(AzureActiveDirectoryAccount azureActiveDirectoryAccount) {
        this.mUniqueId = azureActiveDirectoryAccount.getUserId();
        this.mDisplayableId = azureActiveDirectoryAccount.getDisplayableId();
        this.mGivenName = azureActiveDirectoryAccount.getFirstName();
        this.mFamilyName = azureActiveDirectoryAccount.getFamilyName();
        this.mIdentityProvider = azureActiveDirectoryAccount.getIdentityProvider();
        this.mPasswordChangeUrl = azureActiveDirectoryAccount.getPasswordChangeUrl();
        this.mPasswordExpiresOn = azureActiveDirectoryAccount.getPasswordExpiresOn();
    }

    public String getDisplayableId() {
        return this.mDisplayableId;
    }

    public String getFamilyName() {
        return this.mFamilyName;
    }

    public String getGivenName() {
        return this.mGivenName;
    }

    public String getIdentityProvider() {
        return this.mIdentityProvider;
    }

    public Uri getPasswordChangeUrl() {
        return this.mPasswordChangeUrl;
    }

    public Date getPasswordExpiresOn() {
        return DateExtensions.createCopy(this.mPasswordExpiresOn);
    }

    public String getUserId() {
        return this.mUniqueId;
    }

    public void setDisplayableId(String str) {
        this.mDisplayableId = str;
    }

    public void setUserId(String str) {
        this.mUniqueId = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("ADALUserInfo{mUniqueId='");
        Eo.a(a, this.mUniqueId, '\'', ", mDisplayableId='");
        Eo.a(a, this.mDisplayableId, '\'', ", mGivenName='");
        Eo.a(a, this.mGivenName, '\'', ", mFamilyName='");
        Eo.a(a, this.mFamilyName, '\'', ", mIdentityProvider='");
        Eo.a(a, this.mIdentityProvider, '\'', ", mPasswordChangeUrl=");
        a.append(this.mPasswordChangeUrl);
        a.append(", mPasswordExpiresOn=");
        a.append(this.mPasswordExpiresOn);
        a.append('}');
        return a.toString();
    }

    public ADALUserInfo(ILocalAuthenticationResult iLocalAuthenticationResult) {
        this.mUniqueId = iLocalAuthenticationResult.getUniqueId();
        this.mDisplayableId = iLocalAuthenticationResult.getAccountRecord().getUsername();
        this.mGivenName = iLocalAuthenticationResult.getAccountRecord().getFirstName();
        this.mFamilyName = iLocalAuthenticationResult.getAccountRecord().getFamilyName();
        this.mIdentityProvider = SchemaUtil.getIdentityProvider(iLocalAuthenticationResult.getIdToken());
    }

    public ADALUserInfo(String str, String str2, String str3, String str4, String str5) {
        this.mUniqueId = str;
        this.mGivenName = str2;
        this.mFamilyName = str3;
        this.mIdentityProvider = str4;
        this.mDisplayableId = str5;
    }
}
