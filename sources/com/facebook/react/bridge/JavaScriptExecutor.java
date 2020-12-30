package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public abstract class JavaScriptExecutor {
    public final HybridData mHybridData;

    public JavaScriptExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void close() {
        this.mHybridData.mo569a();
    }

    public abstract String getName();
}
