package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class JNIUtils {

    /* renamed from: a */
    public static Boolean f1440a;

    /* renamed from: b */
    public static ClassLoader f1441b;

    @CalledByNative
    public static Object getClassLoader() {
        ClassLoader classLoader = f1441b;
        return classLoader == null ? JNIUtils.class.getClassLoader() : classLoader;
    }

    @CalledByNative
    public static boolean isSelectiveJniRegistrationEnabled() {
        if (f1440a == null) {
            f1440a = false;
        }
        return f1440a.booleanValue();
    }
}
