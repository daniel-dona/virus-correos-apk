package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.soloader.SoLoader;

/* compiled from: PG */
public class TurboModuleManager implements JSIModule {
    @Qw
    public final HybridData mHybridData;

    /* compiled from: PG */
    public interface ModuleProvider {
        lA getModule(String str, ReactApplicationContext reactApplicationContext);
    }

    static {
        SoLoader.m690a("turbomodulejsijni");
    }

    @Qw
    public lA getJavaModule(String str) {
        throw null;
    }

    public native HybridData initHybrid(long j, MessageQueueThread messageQueueThread);

    public void initialize() {
    }

    public native void installJSIBindings();

    public void onCatalystInstanceDestroy() {
    }
}
