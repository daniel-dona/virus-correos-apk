package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator<SurfaceWrapper> CREATOR = new C0440a();

    /* renamed from: a */
    public final Surface f2421a;

    /* renamed from: b */
    public final boolean f2422b;

    /* renamed from: org.chromium.content.common.SurfaceWrapper$a */
    /* compiled from: PG */
    public class C0440a implements Parcelable.Creator<SurfaceWrapper> {
        public Object createFromParcel(Parcel parcel) {
            Surface surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            return new SurfaceWrapper(surface, z);
        }

        public Object[] newArray(int i) {
            return new SurfaceWrapper[i];
        }
    }

    public SurfaceWrapper(Surface surface, boolean z) {
        this.f2421a = surface;
        this.f2422b = z;
    }

    @CalledByNative
    public static SurfaceWrapper create(Surface surface, boolean z) {
        return new SurfaceWrapper(surface, z);
    }

    @CalledByNative
    public boolean canBeUsedWithSurfaceControl() {
        return this.f2422b;
    }

    public int describeContents() {
        return 0;
    }

    @CalledByNative
    public Surface getSurface() {
        return this.f2421a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.f2421a.writeToParcel(parcel, 0);
        parcel.writeInt(this.f2422b ? 1 : 0);
    }
}
