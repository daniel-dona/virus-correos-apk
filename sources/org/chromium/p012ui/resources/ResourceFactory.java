package org.chromium.p012ui.resources;

import android.graphics.Rect;

/* renamed from: org.chromium.ui.resources.ResourceFactory */
/* compiled from: PG */
public class ResourceFactory {
    /* renamed from: a */
    public static long m3742a(nK3 nk3) {
        if (nk3 == null) {
            return nativeCreateBitmapResource();
        }
        Rect rect = nk3.c;
        Rect rect2 = nk3.f;
        return nativeCreateNinePatchBitmapResource(rect.left, rect.top, rect.right, rect.bottom, rect2.left, rect2.top, rect2.right, rect2.bottom);
    }

    public static native long nativeCreateBitmapResource();

    public static native long nativeCreateNinePatchBitmapResource(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);
}
