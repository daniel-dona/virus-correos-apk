package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;

@SuppressLint({"MissingNativeLoadLibrary"})
/* compiled from: PG */
public class EventBeatManager implements iC {
    @Qw
    public final HybridData mHybridData;

    static {
        vy.a();
    }

    private native void beat();

    public static native HybridData initHybrid(long j);

    /* renamed from: a */
    public void mo1062a() {
        throw null;
    }
}
