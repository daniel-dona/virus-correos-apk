package org.chromium.chrome.browser;

import org.chromium.base.annotations.MainDex;
import org.chromium.base.library_loader.LibraryLoader;

@MainDex
/* compiled from: PG */
public abstract class ChromeFeatureList {
    /* renamed from: a */
    public static boolean m1784a(String str) {
        if (str != null && "VideoPersistence".equals(str)) {
            return nativeIsEnabled(str);
        }
        if ("TrustedWebActivity".equals(str) || "TrustedWebActivityPostMessage".equals(str) || "TrustedWebActivityNotificationDelegationAutoEnrolment".equals(str)) {
            return nativeIsEnabled(str);
        }
        return false;
    }

    public static native String nativeGetFieldTrialParamByFeature(String str, String str2);

    public static native boolean nativeGetFieldTrialParamByFeatureAsBoolean(String str, String str2, boolean z);

    public static native double nativeGetFieldTrialParamByFeatureAsDouble(String str, String str2, double d);

    public static native int nativeGetFieldTrialParamByFeatureAsInt(String str, String str2, int i);

    public static native boolean nativeIsEnabled(String str);

    public static native boolean nativeIsInitialized();

    /* renamed from: a */
    public static boolean m1783a() {
        if (!LibraryLoader.f1501h.mo7904a()) {
            return false;
        }
        return nativeIsInitialized();
    }
}
