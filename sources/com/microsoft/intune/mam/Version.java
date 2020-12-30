package com.microsoft.intune.mam;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PG */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new C0343a();

    /* renamed from: a */
    public long[] f1264a;

    /* renamed from: com.microsoft.intune.mam.Version$a */
    /* compiled from: PG */
    public static class C0343a implements Parcelable.Creator<Version> {
        public Object createFromParcel(Parcel parcel) {
            return new Version(parcel, (C0343a) null);
        }

        public Object[] newArray(int i) {
            return new Version[i];
        }
    }

    public Version(long... jArr) {
        this.f1264a = jArr;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            long[] jArr = this.f1264a;
            if (i >= jArr.length) {
                return sb.toString();
            }
            sb.append(jArr[i]);
            i++;
            if (i < this.f1264a.length) {
                sb.append(".");
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int[] iArr = new int[this.f1264a.length];
        int i2 = 0;
        while (true) {
            long[] jArr = this.f1264a;
            if (i2 < jArr.length) {
                iArr[i2] = (int) jArr[i2];
                i2++;
            } else {
                parcel.writeIntArray(iArr);
                return;
            }
        }
    }

    public /* synthetic */ Version(Parcel parcel, C0343a aVar) {
        int[] createIntArray = parcel.createIntArray();
        this.f1264a = new long[createIntArray.length];
        int i = 0;
        while (true) {
            long[] jArr = this.f1264a;
            if (i < jArr.length) {
                jArr[i] = (long) createIntArray[i];
                i++;
            } else {
                return;
            }
        }
    }
}
