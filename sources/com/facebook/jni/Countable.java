package com.facebook.jni;

import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class Countable {
    @Qw
    public long mInstance = 0;

    static {
        SoLoader.m690a("fb");
    }

    public native void dispose();

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
