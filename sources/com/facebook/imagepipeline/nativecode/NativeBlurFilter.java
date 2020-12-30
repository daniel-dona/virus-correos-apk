package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;

@gq
/* compiled from: PG */
public class NativeBlurFilter {
    static {
        Bu.a();
    }

    /* renamed from: a */
    public static void m385a(Bitmap bitmap, int i, int i2) {
        if (bitmap != null) {
            boolean z = true;
            kq.a(i > 0);
            if (i2 <= 0) {
                z = false;
            }
            kq.a(z);
            nativeIterativeBoxBlur(bitmap, i, i2);
            return;
        }
        throw new NullPointerException();
    }

    @gq
    public static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);
}
