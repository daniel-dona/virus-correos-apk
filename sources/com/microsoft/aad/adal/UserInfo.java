package com.microsoft.aad.adal;

import android.net.Uri;
import android.os.Bundle;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/* compiled from: PG */
public class UserInfo implements Serializable {
    public static final long serialVersionUID = 8790127561636702672L;
    public String mDisplayableId;
    public String mFamilyName;
    public String mGivenName;
    public String mIdentityProvider;
    public transient Uri mPasswordChangeUrl;
    public transient Date mPasswordExpiresOn;
    public String mUniqueId;

    public UserInfo() {
    }

    public static UserInfo getUserInfoFromBrokerResult(Bundle bundle) {
        return new UserInfo(bundle.getString("account.userinfo.userid"), bundle.getString("account.userinfo.given.name"), bundle.getString("account.userinfo.family.name"), bundle.getString("account.userinfo.identity.provider"), bundle.getString("account.userinfo.userid.displayable"));
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

    public void setFamilyName(String str) {
        this.mFamilyName = str;
    }

    public void setGivenName(String str) {
        this.mGivenName = str;
    }

    public void setIdentityProvider(String str) {
        this.mIdentityProvider = str;
    }

    public void setPasswordChangeUrl(Uri uri) {
        this.mPasswordChangeUrl = uri;
    }

    public void setPasswordExpiresOn(Date date) {
        if (date == null) {
            this.mPasswordExpiresOn = null;
        } else {
            this.mPasswordExpiresOn = new Date(date.getTime());
        }
    }

    public void setUserId(String str) {
        this.mUniqueId = str;
    }

    public UserInfo(String str) {
        this.mDisplayableId = str;
    }

    public UserInfo(String str, String str2, String str3, String str4, String str5) {
        this.mUniqueId = str;
        this.mGivenName = str2;
        this.mFamilyName = str3;
        this.mIdentityProvider = str4;
        this.mDisplayableId = str5;
    }

    public UserInfo(IdToken idToken) {
        this.mUniqueId = null;
        this.mDisplayableId = null;
        if (!StringExtensions.isNullOrBlank(idToken.getObjectId())) {
            this.mUniqueId = idToken.getObjectId();
        } else if (!StringExtensions.isNullOrBlank(idToken.getSubject())) {
            this.mUniqueId = idToken.getSubject();
        }
        if (!StringExtensions.isNullOrBlank(idToken.getUpn())) {
            this.mDisplayableId = idToken.getUpn();
        } else if (!StringExtensions.isNullOrBlank(idToken.getEmail())) {
            this.mDisplayableId = idToken.getEmail();
        }
        this.mGivenName = idToken.getGivenName();
        this.mFamilyName = idToken.getFamilyName();
        this.mIdentityProvider = idToken.getIdentityProvider();
        if (idToken.getPasswordExpiration() > 0) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(13, (int) idToken.getPasswordExpiration());
            this.mPasswordExpiresOn = gregorianCalendar.getTime();
        }
        this.mPasswordChangeUrl = null;
        if (!StringExtensions.isNullOrBlank(idToken.getPasswordChangeUrl())) {
            this.mPasswordChangeUrl = Uri.parse(idToken.getPasswordChangeUrl());
        }
    }
}
