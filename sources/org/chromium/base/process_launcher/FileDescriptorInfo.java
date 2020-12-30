package org.chromium.base.process_launcher;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public final class FileDescriptorInfo implements Parcelable {
    public static final Parcelable.Creator<FileDescriptorInfo> CREATOR = new C0378a();

    /* renamed from: a */
    public final int f1533a;

    /* renamed from: b */
    public final ParcelFileDescriptor f1534b;

    /* renamed from: c */
    public final long f1535c;

    /* renamed from: d */
    public final long f1536d;

    /* renamed from: org.chromium.base.process_launcher.FileDescriptorInfo$a */
    /* compiled from: PG */
    public class C0378a implements Parcelable.Creator<FileDescriptorInfo> {
        public Object createFromParcel(Parcel parcel) {
            return new FileDescriptorInfo(parcel);
        }

        public Object[] newArray(int i) {
            return new FileDescriptorInfo[i];
        }
    }

    public FileDescriptorInfo(int i, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
        this.f1533a = i;
        this.f1534b = parcelFileDescriptor;
        this.f1535c = j;
        this.f1536d = j2;
    }

    public int describeContents() {
        return 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1533a);
        parcel.writeParcelable(this.f1534b, 1);
        parcel.writeLong(this.f1535c);
        parcel.writeLong(this.f1536d);
    }

    public FileDescriptorInfo(Parcel parcel) {
        this.f1533a = parcel.readInt();
        this.f1534b = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.f1535c = parcel.readLong();
        this.f1536d = parcel.readLong();
    }
}
