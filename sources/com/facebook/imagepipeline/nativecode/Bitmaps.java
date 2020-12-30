package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import java.nio.ByteBuffer;

@gq
/* compiled from: PG */
public class Bitmaps {
    static {
        Bu.a();
    }

    /* renamed from: a */
    public static void m380a(Bitmap bitmap) {
        if (bitmap != null) {
            nativePinBitmap(bitmap);
            return;
        }
        throw new NullPointerException();
    }

    @gq
    public static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    @gq
    public static native ByteBuffer nativeGetByteBuffer(Bitmap bitmap, long j, long j2);

    @gq
    public static native void nativePinBitmap(Bitmap bitmap);

    @gq
    public static native void nativeReleaseByteBuffer(Bitmap bitmap);

    /* renamed from: a */
    public static void m382a(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = true;
        kq.a(bitmap2.getConfig() == bitmap.getConfig());
        kq.a(bitmap.isMutable());
        kq.a(bitmap.getWidth() == bitmap2.getWidth());
        if (bitmap.getHeight() != bitmap2.getHeight()) {
            z = false;
        }
        kq.a(z);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }

    @TargetApi(19)
    /* renamed from: a */
    public static void m381a(Bitmap bitmap, int i, int i2, Bitmap.Config config) {
        kq.a(bitmap.getAllocationByteCount() >= Fw.a(config) * (i * i2));
        bitmap.reconfigure(i, i2, config);
    }
}
