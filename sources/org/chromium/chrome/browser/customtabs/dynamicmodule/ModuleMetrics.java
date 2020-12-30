package org.chromium.chrome.browser.customtabs.dynamicmodule;

import org.chromium.base.metrics.RecordHistogram;

/* compiled from: PG */
public final class ModuleMetrics {
    /* renamed from: a */
    public static void m2184a(int i) {
        RecordHistogram.m1539a("CustomTabs.DynamicModule.LoadResult", i, 9);
        if (i != 0 && i != 1) {
            VN0.c("ModuleMetrics", "Did not load module, result: %s", new Object[]{Integer.valueOf(i)});
        }
    }

    public static native void nativeRecordCodeMemoryFootprint(String str, String str2);
}
