package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public class CxxCallbackImpl implements Callback {
    @Qw
    public final HybridData mHybridData;

    @Qw
    public CxxCallbackImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    private native void nativeInvoke(NativeArray nativeArray);

    public void invoke(Object... objArr) {
        nativeInvoke(Arguments.fromJavaArgs(objArr));
    }
}
