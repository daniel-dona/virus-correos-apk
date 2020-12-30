package com.facebook.react.bridge.queue;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public class NativeRunnable implements Runnable {
    public final HybridData mHybridData;

    @Qw
    public NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public native void run();
}
