package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public class CxxModuleWrapperBase implements NativeModule {
    @Qw
    public HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public CxxModuleWrapperBase(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public native String getName();

    public void initialize() {
    }

    public void onCatalystInstanceDestroy() {
        this.mHybridData.mo569a();
    }

    public void resetModule(HybridData hybridData) {
        HybridData hybridData2 = this.mHybridData;
        if (hybridData != hybridData2) {
            hybridData2.mo569a();
            this.mHybridData = hybridData;
        }
    }
}
