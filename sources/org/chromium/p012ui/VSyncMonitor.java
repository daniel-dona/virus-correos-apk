package org.chromium.p012ui;

import android.os.Handler;
import android.view.Choreographer;
import com.facebook.react.uimanager.BaseViewManager;

/* renamed from: org.chromium.ui.VSyncMonitor */
/* compiled from: PG */
public class VSyncMonitor {

    /* renamed from: a */
    public boolean f2590a;

    /* renamed from: b */
    public boolean f2591b;

    /* renamed from: c */
    public Listener f2592c;

    /* renamed from: d */
    public long f2593d;

    /* renamed from: e */
    public boolean f2594e;

    /* renamed from: f */
    public boolean f2595f;

    /* renamed from: g */
    public final Choreographer f2596g;

    /* renamed from: h */
    public final Choreographer.FrameCallback f2597h;

    /* renamed from: i */
    public long f2598i;

    /* renamed from: j */
    public final Handler f2599j = new Handler();

    /* renamed from: org.chromium.ui.VSyncMonitor$Listener */
    /* compiled from: PG */
    public interface Listener {
        void onVSync(VSyncMonitor vSyncMonitor, long j);
    }

    public VSyncMonitor(Listener listener, float f) {
        this.f2592c = listener;
        mo9953a(f);
        this.f2596g = Choreographer.getInstance();
        this.f2597h = new LH3(this);
        this.f2598i = System.nanoTime();
    }

    /* renamed from: a */
    public void mo9953a(float f) {
        this.f2594e = f < 30.0f;
        if (f <= BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            f = 60.0f;
        }
        this.f2593d = (long) (1.0E9f / f);
    }

    /* renamed from: b */
    public void mo9954b() {
        if (!this.f2595f) {
            this.f2595f = true;
            this.f2591b = this.f2590a;
            this.f2596g.postFrameCallback(this.f2597h);
        }
    }

    /* renamed from: a */
    public final long mo9952a() {
        return System.nanoTime();
    }
}
