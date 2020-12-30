package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public abstract class NativeMap {
    @Qw
    public HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public NativeMap(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public native String toString();
}
