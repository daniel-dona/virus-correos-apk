package com.facebook.jni;

import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class ThreadScopeSupport {
    static {
        SoLoader.m690a("fb");
    }

    @Qw
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }

    public static native void runStdFunctionImpl(long j);
}
