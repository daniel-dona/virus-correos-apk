package com.microsoft.identity.common.internal.providers.microsoft;

import com.microsoft.identity.common.internal.providers.oauth2.TokenResponse;
import java.util.Date;

/* compiled from: PG */
public class MicrosoftTokenResponse extends TokenResponse {
    public static final String CLIENT_INFO = "client_info";
    public static final String EXT_EXPIRES_IN = "ext_expires_in";
    public static final String FAMILY_ID = "foci";
    public String mAuthority;
    public String mCliTelemErrorCode;
    public String mCliTelemSubErrorCode;
    public transient String mClientId;
    @bK("client_info")
    public String mClientInfo;
    @ZJ
    @bK("cloud_instance_host_name")
    public String mCloudInstanceHostName;
    public Date mExtExpiresOn;
    @ZJ
    @bK("ext_expires_in")
    public Long mExtendedExpiresIn;
    @ZJ
    @bK("foci")
    public String mFamilyId;
    @ZJ
    public String mRefreshTokenAge;
    @ZJ
    public String mSpeRing;

    public final String getAuthority() {
        return this.mAuthority;
    }

    public String getCliTelemErrorCode() {
        return this.mCliTelemErrorCode;
    }

    public String getCliTelemSubErrorCode() {
        return this.mCliTelemSubErrorCode;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getClientInfo() {
        return this.mClientInfo;
    }

    public String getCloudInstanceHostName() {
        return this.mCloudInstanceHostName;
    }

    public Long getExtExpiresIn() {
        return this.mExtendedExpiresIn;
    }

    public Date getExtExpiresOn() {
        return this.mExtExpiresOn;
    }

    public String getFamilyId() {
        return this.mFamilyId;
    }

    public String getRefreshTokenAge() {
        return this.mRefreshTokenAge;
    }

    public String getSpeRing() {
        return this.mSpeRing;
    }

    public void setAuthority(String str) {
        this.mAuthority = str;
    }

    public void setCliTelemErrorCode(String str) {
        this.mCliTelemErrorCode = str;
    }

    public void setCliTelemSubErrorCode(String str) {
        this.mCliTelemSubErrorCode = str;
    }

    public void setClientId(String str) {
        this.mClientId = str;
    }

    public void setClientInfo(String str) {
        this.mClientInfo = str;
    }

    public void setCloudInstanceHostName(String str) {
        this.mCloudInstanceHostName = str;
    }

    public void setExtExpiresIn(Long l) {
        this.mExtendedExpiresIn = l;
    }

    public void setExtExpiresOn(Date date) {
        this.mExtExpiresOn = date;
    }

    public void setFamilyId(String str) {
        this.mFamilyId = str;
    }

    public void setRefreshTokenAge(String str) {
        this.mRefreshTokenAge = str;
    }

    public void setSpeRing(String str) {
        this.mSpeRing = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("MicrosoftTokenResponse{mExtExpiresOn=");
        a.append(this.mExtExpiresOn);
        a.append(", mClientInfo='");
        Eo.a(a, this.mClientInfo, '\'', ", mClientId='");
        Eo.a(a, this.mClientId, '\'', ", mExtendedExpiresIn=");
        a.append(this.mExtendedExpiresIn);
        a.append(", mFamilyId='");
        Eo.a(a, this.mFamilyId, '\'', "} ");
        a.append(super.toString());
        return a.toString();
    }
}
