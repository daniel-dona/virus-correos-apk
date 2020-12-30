package com.microsoft.tokenshare;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Date;

/* compiled from: PG */
public class AccountInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<AccountInfo> CREATOR = new 1();
    public static final String SERIALIZABLE_VALUE_CODE_NAME = "readSerializable2";
    public static final String VERSION_KEY = "version";
    public static final long serialVersionUID = 1;
    public final String mAccountId;
    public final AccountType mAccountType;
    public final boolean mIsIntOrPpe;
    public String mParcelableVersion;
    public final String mPhoneNumber;
    public final String mPrimaryEmail;
    public String mProviderPackageId;
    public final Date mRefreshTokenAcquireTime;

    public AccountInfo(String str, String str2, AccountType accountType, boolean z, String str3, Date date) {
        this.mAccountId = str;
        this.mPrimaryEmail = str2;
        this.mAccountType = accountType;
        this.mIsIntOrPpe = z;
        this.mPhoneNumber = str3;
        this.mRefreshTokenAcquireTime = date;
        this.mParcelableVersion = SERIALIZABLE_VALUE_CODE_NAME;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountId() {
        return this.mAccountId;
    }

    public AccountType getAccountType() {
        return this.mAccountType;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public String getPrimaryEmail() {
        return this.mPrimaryEmail;
    }

    public String getProviderPackageId() {
        return this.mProviderPackageId;
    }

    public Date getRefreshTokenAcquireTime() {
        return this.mRefreshTokenAcquireTime;
    }

    public boolean isIntOrPpe() {
        return this.mIsIntOrPpe;
    }

    public void setParcelableVersion(String str) {
        this.mParcelableVersion = str;
    }

    public void setProviderPackageId(String str) {
        this.mProviderPackageId = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("AccountInfo{mAccountId='");
        Eo.a(a, this.mAccountId, '\'', ", mPrimaryEmail='");
        Eo.a(a, this.mPrimaryEmail, '\'', ", mAccountType='");
        a.append(this.mAccountType.name());
        a.append('\'');
        a.append(", mIsIntOrPpe='");
        a.append(this.mIsIntOrPpe);
        a.append('\'');
        a.append(", mProviderPackageId='");
        Eo.a(a, this.mProviderPackageId, '\'', ", mPhoneNumber='");
        Eo.a(a, this.mPhoneNumber, '\'', ", mRefreshTokenAcquireTime='");
        a.append(this.mRefreshTokenAcquireTime);
        a.append('\'');
        a.append('}');
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (SERIALIZABLE_VALUE_CODE_NAME.equalsIgnoreCase(this.mParcelableVersion)) {
            parcel.writeString(this.mParcelableVersion);
            parcel.writeSerializable(this);
            return;
        }
        parcel.writeString(this.mAccountId);
        parcel.writeString(this.mPrimaryEmail);
        parcel.writeString(this.mAccountType.name());
        parcel.writeByte(this.mIsIntOrPpe ? (byte) 1 : 0);
    }

    public AccountInfo(Parcel parcel) {
        this.mAccountId = parcel.readString();
        this.mPrimaryEmail = parcel.readString();
        this.mAccountType = AccountType.valueOf(parcel.readString());
        this.mIsIntOrPpe = parcel.readByte() != 0;
        this.mPhoneNumber = null;
        this.mRefreshTokenAcquireTime = null;
        this.mParcelableVersion = null;
    }
}
