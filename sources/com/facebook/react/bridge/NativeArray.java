package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public abstract class NativeArray implements NativeArrayInterface {
    @Qw
    public HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public NativeArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public native String toString();
}
