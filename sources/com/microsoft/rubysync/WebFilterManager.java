package com.microsoft.rubysync;

/* compiled from: PG */
public class WebFilterManager {
    public long nativeRef = nativeCreateWebFilterManager();

    static {
        System.loadLibrary("rubysync");
    }

    public static native long nativeCreateWebFilterManager();

    public static native void nativeDestroyWebFilterManager(long j);

    public static native int nativeInitialize(long j, String str, String str2, String str3, int i);

    public static native boolean nativeIsInitialized(long j);

    public static native boolean nativeIsUrlAllowed(long j, String str);

    public static native boolean nativeIsWebFilterEnabled(long j);

    public static native int nativeUninitialize(long j);

    public static native int nativeUpdateSetting(long j, String str);

    public int initialize(String str, String str2, String str3, LogLevel logLevel) {
        return nativeInitialize(this.nativeRef, str, str2, str3, logLevel.ordinal());
    }

    public boolean isInitialized() {
        return nativeIsInitialized(this.nativeRef);
    }

    public boolean isUrlAllowed(String str) {
        return nativeIsUrlAllowed(this.nativeRef, str);
    }

    public boolean isWebFilterEnabled() {
        return nativeIsWebFilterEnabled(this.nativeRef);
    }

    public int uninitialize() {
        return nativeUninitialize(this.nativeRef);
    }

    public int updateSetting(String str) {
        return nativeUpdateSetting(this.nativeRef, str);
    }
}
