package org.chromium.chrome.browser.metrics;

import android.os.SystemClock;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class UmaUtils {

    /* renamed from: a */
    public static long f2070a;

    /* renamed from: b */
    public static long f2071b;

    /* renamed from: c */
    public static long f2072c;

    /* renamed from: a */
    public static boolean m2540a() {
        return f2072c != 0;
    }

    /* renamed from: b */
    public static boolean m2541b() {
        return f2071b != 0;
    }

    /* renamed from: c */
    public static void m2542c() {
        f2072c = SystemClock.uptimeMillis();
    }

    /* renamed from: d */
    public static void m2543d() {
        long j = f2071b;
        if (j == 0 || j < f2072c) {
            f2071b = SystemClock.uptimeMillis();
        }
    }

    @CalledByNative
    public static long getMainEntryPointTicks() {
        return f2070a;
    }

    public static native boolean nativeIsClientInMetricsReportingSample();

    public static native void nativeRecordMetricsReportingDefaultOptIn(boolean z);

    @CalledByNative
    public static void setUsageAndCrashReportingFromNative(boolean z) {
        UmaSessionStats.m2534a(z);
    }
}
