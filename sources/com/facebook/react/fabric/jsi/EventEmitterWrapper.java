package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;

@SuppressLint({"MissingNativeLoadLibrary"})
/* compiled from: PG */
public class EventEmitterWrapper {
    @Qw
    public final HybridData mHybridData;

    static {
        vy.a();
    }

    public static native HybridData initHybrid();

    private native void invokeEvent(String str, NativeMap nativeMap);
}
