package org.chromium.p012ui.base;

import android.content.Context;
import org.chromium.base.annotations.CalledByNative;

/* renamed from: org.chromium.ui.base.DeviceFormFactor */
/* compiled from: PG */
public class DeviceFormFactor {
    /* renamed from: a */
    public static int m3639a(Context context) {
        return context.getResources().getInteger(px0.min_screen_width_bucket);
    }

    /* renamed from: b */
    public static boolean m3640b(Context context) {
        if (!Og0.d() && context.getResources().getInteger(px0.min_screen_width_bucket) == 3) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m3641c(Context context) {
        if (!Og0.d() && context.getResources().getInteger(px0.min_screen_width_bucket) >= 2) {
            return true;
        }
        return false;
    }

    @CalledByNative
    public static boolean isChromebook() {
        return RN0.a.getPackageManager().hasSystemFeature("org.chromium.arc.device_management");
    }

    @Deprecated
    @CalledByNative
    public static boolean isTablet() {
        if (!Og0.d() && m3639a(RN0.a) >= 2) {
            return true;
        }
        return false;
    }
}
