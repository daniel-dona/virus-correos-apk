package org.chromium.p012ui.p013gl;

import android.graphics.SurfaceTexture;
import android.util.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.gl.SurfaceTexturePlatformWrapper */
/* compiled from: PG */
public class SurfaceTexturePlatformWrapper {
    @CalledByNative
    public static void attachToGLContext(SurfaceTexture surfaceTexture, int i) {
        surfaceTexture.attachToGLContext(i);
    }

    @CalledByNative
    public static SurfaceTexture create(int i) {
        return new SurfaceTexture(i);
    }

    @CalledByNative
    public static void destroy(SurfaceTexture surfaceTexture) {
        surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
        surfaceTexture.release();
    }

    @CalledByNative
    public static void detachFromGLContext(SurfaceTexture surfaceTexture) {
        surfaceTexture.detachFromGLContext();
    }

    @CalledByNative
    public static void getTransformMatrix(SurfaceTexture surfaceTexture, float[] fArr) {
        surfaceTexture.getTransformMatrix(fArr);
    }

    @CalledByNative
    public static void release(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
    }

    @CalledByNative
    public static void setDefaultBufferSize(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceTexture.setDefaultBufferSize(i, i2);
    }

    @CalledByNative
    public static void setFrameAvailableCallback(SurfaceTexture surfaceTexture, long j) {
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTextureListener(j));
    }

    @CalledByNative
    public static void updateTexImage(SurfaceTexture surfaceTexture) {
        try {
            surfaceTexture.updateTexImage();
        } catch (RuntimeException e) {
            Log.e("SurfaceTexturePlatformWrapper", "Error calling updateTexImage", e);
        }
    }
}
