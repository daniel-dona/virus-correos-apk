package com.microsoft.tokenshare;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
public class RefreshToken implements Parcelable {
    public static final Parcelable.Creator<RefreshToken> CREATOR = new 1();
    public final String mAppId;
    public final String mRefreshToken;

    public RefreshToken(String str, String str2) {
        this.mRefreshToken = str;
        this.mAppId = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public String toString() {
        StringBuilder a = Eo.a("RefreshToken{mRefreshToken='");
        Eo.a(a, this.mRefreshToken, '\'', ", mAppId='");
        a.append(this.mAppId);
        a.append('\'');
        a.append('}');
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRefreshToken);
        parcel.writeString(this.mAppId);
    }

    public RefreshToken(Parcel parcel) {
        this.mRefreshToken = parcel.readString();
        this.mAppId = parcel.readString();
    }
}
