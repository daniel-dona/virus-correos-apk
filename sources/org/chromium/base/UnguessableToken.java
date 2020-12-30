package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class UnguessableToken implements Parcelable {
    public static final Parcelable.Creator<UnguessableToken> CREATOR = new C0374a();

    /* renamed from: a */
    public final long f1491a;

    /* renamed from: b */
    public final long f1492b;

    /* renamed from: org.chromium.base.UnguessableToken$a */
    /* compiled from: PG */
    public class C0374a implements Parcelable.Creator<UnguessableToken> {
        public Object createFromParcel(Parcel parcel) {
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            if (readLong == 0 || readLong2 == 0) {
                return null;
            }
            return new UnguessableToken(readLong, readLong2);
        }

        public Object[] newArray(int i) {
            return new UnguessableToken[i];
        }
    }

    public UnguessableToken(long j, long j2) {
        this.f1491a = j;
        this.f1492b = j2;
    }

    @CalledByNative
    public static UnguessableToken create(long j, long j2) {
        return new UnguessableToken(j, j2);
    }

    @CalledByNative
    private UnguessableToken parcelAndUnparcelForTesting() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        UnguessableToken createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    public int describeContents() {
        return 0;
    }

    @CalledByNative
    public long getHighForSerialization() {
        return this.f1491a;
    }

    @CalledByNative
    public long getLowForSerialization() {
        return this.f1492b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f1491a);
        parcel.writeLong(this.f1492b);
    }
}
