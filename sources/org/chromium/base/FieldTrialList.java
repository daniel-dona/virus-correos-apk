package org.chromium.base;

import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class FieldTrialList {
    public static native String nativeFindFullName(String str);

    public static native String nativeGetVariationParameter(String str, String str2);

    public static native void nativeLogActiveTrials();

    public static native boolean nativeTrialExists(String str);
}
