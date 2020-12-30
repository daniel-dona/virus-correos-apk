package com.microsoft.identity.common.internal.dto;

/* compiled from: PG */
public class IdTokenRecord extends Credential {
    @bK("authority")
    public String mAuthority;
    @bK("realm")
    public String mRealm;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IdTokenRecord.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        IdTokenRecord idTokenRecord = (IdTokenRecord) obj;
        String str = this.mRealm;
        if (str == null ? idTokenRecord.mRealm != null : !str.equals(idTokenRecord.mRealm)) {
            return false;
        }
        String str2 = this.mAuthority;
        String str3 = idTokenRecord.mAuthority;
        if (str2 != null) {
            return str2.equals(str3);
        }
        if (str3 == null) {
            return true;
        }
        return false;
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getRealm() {
        return this.mRealm;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.mRealm;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mAuthority;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public boolean isExpired() {
        return false;
    }

    public void setAuthority(String str) {
        this.mAuthority = str;
    }

    public void setRealm(String str) {
        this.mRealm = str;
    }
}
