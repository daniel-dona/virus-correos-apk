package com.microsoft.identity.common.internal.dto;

/* compiled from: PG */
public class RefreshTokenRecord extends Credential {
    @bK("family_id")
    public String mFamilyId;
    @bK("target")
    public String mTarget;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RefreshTokenRecord.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        RefreshTokenRecord refreshTokenRecord = (RefreshTokenRecord) obj;
        String str = this.mFamilyId;
        if (str == null ? refreshTokenRecord.mFamilyId != null : !str.equals(refreshTokenRecord.mFamilyId)) {
            return false;
        }
        String str2 = this.mTarget;
        String str3 = refreshTokenRecord.mTarget;
        if (str2 != null) {
            return str2.equals(str3);
        }
        if (str3 == null) {
            return true;
        }
        return false;
    }

    public String getFamilyId() {
        return this.mFamilyId;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.mFamilyId;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mTarget;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public boolean isExpired() {
        return false;
    }

    public void setFamilyId(String str) {
        this.mFamilyId = str;
    }

    public void setTarget(String str) {
        this.mTarget = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("RefreshToken{mFamilyId='");
        Eo.a(a, this.mFamilyId, '\'', ", mTarget='");
        Eo.a(a, this.mTarget, '\'', "} ");
        a.append(super.toString());
        return a.toString();
    }
}
