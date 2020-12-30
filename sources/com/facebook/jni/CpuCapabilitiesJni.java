package com.facebook.jni;

import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class CpuCapabilitiesJni {
    static {
        SoLoader.m690a("fb");
    }

    @Qw
    public static native boolean nativeDeviceSupportsNeon();

    @Qw
    public static native boolean nativeDeviceSupportsVFPFP16();

    @Qw
    public static native boolean nativeDeviceSupportsX86();
}
