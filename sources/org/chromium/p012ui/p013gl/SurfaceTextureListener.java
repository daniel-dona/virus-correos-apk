package org.chromium.p012ui.p013gl;

import android.graphics.SurfaceTexture;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.gl.SurfaceTextureListener */
/* compiled from: PG */
public class SurfaceTextureListener implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a */
    public final long f2669a;

    static {
        Class<SurfaceTextureListener> cls = SurfaceTextureListener.class;
    }

    public SurfaceTextureListener(long j) {
        this.f2669a = j;
    }

    private native void nativeDestroy(long j);

    private native void nativeFrameAvailable(long j);

    public void finalize() throws Throwable {
        try {
            nativeDestroy(this.f2669a);
        } finally {
            super.finalize();
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        nativeFrameAvailable(this.f2669a);
    }
}
