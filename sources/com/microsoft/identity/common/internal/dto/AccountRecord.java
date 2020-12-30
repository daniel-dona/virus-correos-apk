package com.microsoft.identity.common.internal.dto;

/* compiled from: PG */
public class AccountRecord extends AccountCredentialBase implements IAccountRecord {
    @bK("alternative_account_id")
    public String mAlternativeAccountId;
    @bK("authority_type")
    public String mAuthorityType;
    @bK("avatar_url")
    public String mAvatarUrl;
    @bK("client_info")
    public String mClientInfo;
    @bK("environment")
    public String mEnvironment;
    @bK("family_name")
    public String mFamilyName;
    @bK("first_name")
    public String mFirstName;
    @bK("home_account_id")
    public String mHomeAccountId;
    @bK("local_account_id")
    public String mLocalAccountId;
    @bK("middle_name")
    public String mMiddleName;
    @bK("name")
    public String mName;
    @bK("realm")
    public String mRealm;
    @bK("username")
    public String mUsername;

    public AccountRecord() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccountRecord.class != obj.getClass()) {
            return false;
        }
        AccountRecord accountRecord = (AccountRecord) obj;
        String str = this.mHomeAccountId;
        if (str == null ? accountRecord.mHomeAccountId != null : !str.equals(accountRecord.mHomeAccountId)) {
            return false;
        }
        String str2 = this.mEnvironment;
        if (str2 == null ? accountRecord.mEnvironment != null : !str2.equals(accountRecord.mEnvironment)) {
            return false;
        }
        String str3 = this.mRealm;
        if (str3 == null ? accountRecord.mRealm != null : !str3.equals(accountRecord.mRealm)) {
            return false;
        }
        String str4 = this.mLocalAccountId;
        if (str4 == null ? accountRecord.mLocalAccountId != null : !str4.equals(accountRecord.mLocalAccountId)) {
            return false;
        }
        String str5 = this.mUsername;
        if (str5 == null ? accountRecord.mUsername != null : !str5.equals(accountRecord.mUsername)) {
            return false;
        }
        String str6 = this.mAuthorityType;
        if (str6 == null ? accountRecord.mAuthorityType != null : !str6.equals(accountRecord.mAuthorityType)) {
            return false;
        }
        String str7 = this.mAlternativeAccountId;
        if (str7 == null ? accountRecord.mAlternativeAccountId != null : !str7.equals(accountRecord.mAlternativeAccountId)) {
            return false;
        }
        String str8 = this.mFirstName;
        if (str8 == null ? accountRecord.mFirstName != null : !str8.equals(accountRecord.mFirstName)) {
            return false;
        }
        String str9 = this.mFamilyName;
        if (str9 == null ? accountRecord.mFamilyName != null : !str9.equals(accountRecord.mFamilyName)) {
            return false;
        }
        String str10 = this.mAvatarUrl;
        String str11 = accountRecord.mAvatarUrl;
        if (str10 != null) {
            return str10.equals(str11);
        }
        if (str11 == null) {
            return true;
        }
        return false;
    }

    public String getAlternativeAccountId() {
        return this.mAlternativeAccountId;
    }

    public String getAuthorityType() {
        return this.mAuthorityType;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getClientInfo() {
        return this.mClientInfo;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getFamilyName() {
        return this.mFamilyName;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getHomeAccountId() {
        return this.mHomeAccountId;
    }

    public String getLocalAccountId() {
        return this.mLocalAccountId;
    }

    public String getMiddleName() {
        return this.mMiddleName;
    }

    public String getName() {
        return this.mName;
    }

    public String getRealm() {
        return this.mRealm;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public int hashCode() {
        String str = this.mHomeAccountId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mEnvironment;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mRealm;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.mLocalAccountId;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.mUsername;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.mAuthorityType;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.mAlternativeAccountId;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.mFirstName;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.mFamilyName;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.mAvatarUrl;
        if (str10 != null) {
            i = str10.hashCode();
        }
        return hashCode9 + i;
    }

    public void setAlternativeAccountId(String str) {
        this.mAlternativeAccountId = str;
    }

    public void setAuthorityType(String str) {
        this.mAuthorityType = str;
    }

    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    public void setClientInfo(String str) {
        this.mClientInfo = str;
    }

    public void setEnvironment(String str) {
        this.mEnvironment = str;
    }

    public void setFamilyName(String str) {
        this.mFamilyName = str;
    }

    public void setFirstName(String str) {
        this.mFirstName = str;
    }

    public void setHomeAccountId(String str) {
        this.mHomeAccountId = str;
    }

    public void setLocalAccountId(String str) {
        this.mLocalAccountId = str;
    }

    public void setMiddleName(String str) {
        this.mMiddleName = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setRealm(String str) {
        this.mRealm = str;
    }

    public void setUsername(String str) {
        this.mUsername = str;
    }

    public AccountRecord(IAccountRecord iAccountRecord) {
        setHomeAccountId(iAccountRecord.getHomeAccountId());
        setEnvironment(iAccountRecord.getEnvironment());
        setRealm(iAccountRecord.getRealm());
        setLocalAccountId(iAccountRecord.getLocalAccountId());
        setUsername(iAccountRecord.getUsername());
        setAuthorityType(iAccountRecord.getAuthorityType());
        setClientInfo(iAccountRecord.getClientInfo());
        setAlternativeAccountId(iAccountRecord.getAlternativeAccountId());
        setFirstName(iAccountRecord.getFirstName());
        setFamilyName(iAccountRecord.getFamilyName());
        setMiddleName(iAccountRecord.getMiddleName());
        setName(iAccountRecord.getName());
        setAvatarUrl(iAccountRecord.getAvatarUrl());
    }
}
