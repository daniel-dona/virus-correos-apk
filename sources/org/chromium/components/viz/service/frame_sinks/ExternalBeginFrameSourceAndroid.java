package org.chromium.components.viz.service.frame_sinks;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.p012ui.VSyncMonitor;

@MainDex
/* compiled from: PG */
public class ExternalBeginFrameSourceAndroid {

    /* renamed from: a */
    public final long f2379a;

    /* renamed from: b */
    public boolean f2380b;

    /* renamed from: c */
    public final VSyncMonitor f2381c;

    /* renamed from: d */
    public final VSyncMonitor.Listener f2382d = new C0433a();

    /* renamed from: org.chromium.components.viz.service.frame_sinks.ExternalBeginFrameSourceAndroid$a */
    /* compiled from: PG */
    public class C0433a implements VSyncMonitor.Listener {
        public C0433a() {
        }

        public void onVSync(VSyncMonitor vSyncMonitor, long j) {
            ExternalBeginFrameSourceAndroid externalBeginFrameSourceAndroid = ExternalBeginFrameSourceAndroid.this;
            if (externalBeginFrameSourceAndroid.f2380b) {
                externalBeginFrameSourceAndroid.nativeOnVSync(externalBeginFrameSourceAndroid.f2379a, j, externalBeginFrameSourceAndroid.f2381c.f2593d / 1000);
                ExternalBeginFrameSourceAndroid.this.f2381c.mo9954b();
            }
        }
    }

    @CalledByNative
    public ExternalBeginFrameSourceAndroid(long j, float f) {
        this.f2379a = j;
        this.f2381c = new VSyncMonitor(this.f2382d, f);
    }

    /* access modifiers changed from: private */
    public native void nativeOnVSync(long j, long j2, long j3);

    @CalledByNative
    private void setEnabled(boolean z) {
        if (this.f2380b != z) {
            this.f2380b = z;
            if (this.f2380b) {
                this.f2381c.mo9954b();
            }
        }
    }

    @CalledByNative
    private void updateRefreshRate(float f) {
        this.f2381c.mo9953a(f);
    }
}
