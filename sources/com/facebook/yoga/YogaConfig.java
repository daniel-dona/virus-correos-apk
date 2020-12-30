package com.facebook.yoga;

import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class YogaConfig {

    /* renamed from: a */
    public long f852a = jni_YGConfigNew();

    static {
        SoLoader.m690a("yoga");
    }

    public YogaConfig() {
        if (this.f852a == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    @Qw
    private final YogaNode cloneNode(YogaNode yogaNode, YogaNode yogaNode2, int i) {
        throw null;
    }

    private native void jni_YGConfigFree(long j);

    private native long jni_YGConfigNew();

    private native void jni_YGConfigSetExperimentalFeatureEnabled(long j, int i, boolean z);

    private native void jni_YGConfigSetHasCloneNodeFunc(long j, boolean z);

    private native void jni_YGConfigSetLogger(long j, Object obj);

    private native void jni_YGConfigSetPointScaleFactor(long j, float f);

    private native void jni_YGConfigSetPrintTreeFlag(long j, boolean z);

    private native void jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviour(long j, boolean z);

    private native void jni_YGConfigSetUseLegacyStretchBehaviour(long j, boolean z);

    private native void jni_YGConfigSetUseWebDefaults(long j, boolean z);

    /* renamed from: a */
    public void mo1864a(float f) {
        jni_YGConfigSetPointScaleFactor(this.f852a, f);
    }

    public void finalize() throws Throwable {
        try {
            jni_YGConfigFree(this.f852a);
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    public void mo1865a(boolean z) {
        jni_YGConfigSetUseLegacyStretchBehaviour(this.f852a, z);
    }
}
