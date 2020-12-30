package org.chromium.p012ui.base;

import java.util.Arrays;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.base.ResourceBundle */
/* compiled from: PG */
public final class ResourceBundle {

    /* renamed from: a */
    public static String[] f2606a;

    /* renamed from: b */
    public static String[] f2607b;

    @CalledByNative
    public static String getLocalePakResourcePath(String str) {
        if (Arrays.binarySearch(f2607b, str) >= 0) {
            return Eo.b("assets/stored-locales/", str, ".pak");
        }
        return null;
    }
}
