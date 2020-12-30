package org.chromium.base;

import android.app.Activity;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class MemoryPressureListener {

    /* renamed from: a */
    public static final ObserverList<yO0> f1452a = new ObserverList<>();

    /* renamed from: a */
    public static boolean m1435a(Activity activity, String str) {
        if ("org.chromium.base.ACTION_LOW_MEMORY".equals(str)) {
            activity.getApplication().onLowMemory();
            activity.onLowMemory();
            return true;
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY".equals(str)) {
            activity.getApplication().onTrimMemory(80);
            activity.onTrimMemory(80);
            return true;
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL".equals(str)) {
            activity.getApplication().onTrimMemory(15);
            activity.onTrimMemory(15);
            return true;
        } else if (!"org.chromium.base.ACTION_TRIM_MEMORY_MODERATE".equals(str)) {
            return false;
        } else {
            activity.getApplication().onTrimMemory(60);
            activity.onTrimMemory(60);
            return true;
        }
    }

    @CalledByNative
    public static void addNativeCallback() {
        f1452a.mo7868a(WN0.a);
    }

    public static native void nativeOnMemoryPressure(int i);
}
