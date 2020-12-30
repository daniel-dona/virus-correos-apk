package org.chromium.p012ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.SparseArray;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.p012ui.resources.ResourceLoader;

@MainDex
/* renamed from: org.chromium.ui.resources.ResourceManager */
/* compiled from: PG */
public class ResourceManager implements ResourceLoader.ResourceLoaderCallback {

    /* renamed from: a */
    public final SparseArray<ResourceLoader> f2672a = new SparseArray<>();

    /* renamed from: b */
    public final SparseArray<SparseArray<ZJ3>> f2673b = new SparseArray<>();

    /* renamed from: c */
    public final float f2674c;

    /* renamed from: d */
    public long f2675d;

    public ResourceManager(Resources resources, int i, long j) {
        this.f2674c = 1.0f / resources.getDisplayMetrics().density;
        qK3 qk3 = new qK3(0, this, resources);
        this.f2672a.put(qk3.f2670a, qk3);
        hK3 hk3 = new hK3(1, this);
        this.f2672a.put(hk3.f2670a, hk3);
        hK3 hk32 = new hK3(2, this);
        this.f2672a.put(hk32.f2670a, hk32);
        sK3 sk3 = new sK3(3, this, i);
        this.f2672a.put(sk3.f2670a, sk3);
        this.f2675d = j;
    }

    @CalledByNative
    public static ResourceManager create(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.mo10017d().get();
        if (context != null) {
            Point point = windowAndroid.mo10018e().f2650c;
            return new ResourceManager(context.getResources(), Math.min(point.x, point.y), j);
        }
        throw new IllegalStateException("Context should not be null during initialization.");
    }

    @CalledByNative
    private void destroy() {
        this.f2675d = 0;
    }

    @CalledByNative
    private long getNativePtr() {
        return this.f2675d;
    }

    private native void nativeClearTintedResourceCache(long j);

    private native void nativeOnResourceReady(long j, int i, int i2, Bitmap bitmap, int i3, int i4, long j2);

    private native void nativeRemoveResource(long j, int i, int i2);

    @CalledByNative
    private void preloadResource(int i, int i2) {
        ResourceLoader resourceLoader = this.f2672a.get(i);
        if (resourceLoader != null) {
            resourceLoader.mo10067b(i2);
        }
    }

    @CalledByNative
    private void resourceRequested(int i, int i2) {
        ResourceLoader resourceLoader = this.f2672a.get(i);
        if (resourceLoader != null) {
            resourceLoader.mo10066a(i2);
        }
    }

    /* renamed from: a */
    public void mo10072a(int i, int[] iArr, int[] iArr2) {
        ResourceLoader resourceLoader = this.f2672a.get(i);
        if (iArr2 != null) {
            for (int valueOf : iArr2) {
                resourceLoader.mo10067b(Integer.valueOf(valueOf).intValue());
            }
        }
        if (iArr != null) {
            for (int valueOf2 : iArr) {
                resourceLoader.mo10066a(Integer.valueOf(valueOf2).intValue());
            }
        }
    }

    /* renamed from: b */
    public hK3 mo10073b() {
        return this.f2672a.get(2);
    }

    /* renamed from: c */
    public hK3 mo10074c() {
        return this.f2672a.get(1);
    }

    public void onResourceLoaded(int i, int i2, aK3 ak3) {
        Bitmap a;
        if (ak3 != null && (a = ak3.a()) != null) {
            SparseArray sparseArray = this.f2673b.get(i);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                this.f2673b.put(i, sparseArray);
            }
            sparseArray.put(i2, new ZJ3(this.f2674c, ak3));
            long j = this.f2675d;
            if (j != 0) {
                nativeOnResourceReady(j, i, i2, a, ak3.d().width(), ak3.d().height(), ak3.b());
            }
        }
    }

    public void onResourceUnregistered(int i, int i2) {
        if (i == 2 || i == 1) {
            long j = this.f2675d;
            if (j != 0) {
                nativeRemoveResource(j, i, i2);
            }
        }
    }

    /* renamed from: a */
    public ZJ3 mo10070a(int i, int i2) {
        SparseArray sparseArray = this.f2673b.get(i);
        if (sparseArray != null) {
            return (ZJ3) sparseArray.get(i2);
        }
        return null;
    }

    /* renamed from: a */
    public void mo10071a() {
        long j = this.f2675d;
        if (j != 0) {
            nativeClearTintedResourceCache(j);
        }
    }
}
