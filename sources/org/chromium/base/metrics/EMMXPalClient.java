package org.chromium.base.metrics;

import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class EMMXPalClient {

    /* renamed from: a */
    public static NO0 f1515a;

    /* renamed from: b */
    public static MO0 f1516b;

    /* renamed from: a */
    public static void m1535a(NO0 no0) {
        f1515a = no0;
    }

    @CalledByNative
    public static void logEvent(String str, String str2, String str3) {
        qs0 qs0 = f1515a;
        if (qs0 != null) {
            qs0.a(str, str2, str3);
        }
    }

    @CalledByNative
    public static void logSingleFeatureEvent(String str, String str2, String str3, boolean z, int i, String str4, boolean z2) {
        StringBuilder a = Eo.a("[metrics]", str, ":", str2, ":");
        a.append(str3);
        a.toString();
        qs0 qs0 = f1515a;
        if (qs0 != null) {
            qs0.a(str, str2, str3, z, i, str4, z2);
        }
    }

    @CalledByNative
    public static void writeLog(String str) {
        Wp0 wp0 = f1516b;
        if (wp0 != null) {
            wp0.a(str);
        } else {
            VN0.b("EMMXPalClient", "sNativeLoggerProvider is null", new Object[0]);
        }
    }
}
