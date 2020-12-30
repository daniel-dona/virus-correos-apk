package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.BaseViewManager;

/* compiled from: PG */
public abstract class ScalingUtils {

    /* compiled from: PG */
    public interface ScaleType {
        public static final ScaleType CENTER = C0062a.f293a;
        public static final ScaleType CENTER_CROP = C0063b.f294a;
        public static final ScaleType CENTER_INSIDE = C0064c.f295a;
        public static final ScaleType FIT_BOTTOM_START = C0065d.f296a;
        public static final ScaleType FIT_CENTER = C0066e.f297a;
        public static final ScaleType FIT_END = C0067f.f298a;
        public static final ScaleType FIT_START = C0068g.f299a;
        public static final ScaleType FIT_XY = C0069h.f300a;
        public static final ScaleType FOCUS_CROP = C0070i.f301a;

        Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2);
    }

    /* compiled from: PG */
    public interface StatefulScaleType {
        Object getState();
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$a */
    /* compiled from: PG */
    public static class C0062a extends Zr {

        /* renamed from: a */
        public static final ScaleType f293a = new C0062a();

        /* renamed from: a */
        public void mo378a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            matrix.setTranslate((float) ((int) ((((float) (rect.width() - i)) * 0.5f) + ((float) rect.left) + 0.5f)), (float) ((int) ((((float) (rect.height() - i2)) * 0.5f) + ((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "center";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$b */
    /* compiled from: PG */
    public static class C0063b extends Zr {

        /* renamed from: a */
        public static final ScaleType f294a = new C0063b();

        /* renamed from: a */
        public void mo380a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float f5;
            float f6;
            if (f4 > f3) {
                f5 = ((((float) rect.width()) - (((float) i) * f4)) * 0.5f) + ((float) rect.left);
                f6 = (float) rect.top;
                f3 = f4;
            } else {
                f5 = (float) rect.left;
                f6 = ((((float) rect.height()) - (((float) i2) * f3)) * 0.5f) + ((float) rect.top);
            }
            matrix.setScale(f3, f3);
            matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
        }

        public String toString() {
            return "center_crop";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$c */
    /* compiled from: PG */
    public static class C0064c extends Zr {

        /* renamed from: a */
        public static final ScaleType f295a = new C0064c();

        /* renamed from: a */
        public void mo382a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(Math.min(f3, f4), 1.0f);
            float width = ((((float) rect.width()) - (((float) i) * min)) * 0.5f) + ((float) rect.left);
            float height = (((float) rect.height()) - (((float) i2) * min)) * 0.5f;
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + ((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "center_inside";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$d */
    /* compiled from: PG */
    public static class C0065d extends Zr {

        /* renamed from: a */
        public static final ScaleType f296a = new C0065d();

        /* renamed from: a */
        public void mo384a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            float height = ((float) rect.height()) - (((float) i2) * min);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (height + ((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "fit_bottom_start";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$e */
    /* compiled from: PG */
    public static class C0066e extends Zr {

        /* renamed from: a */
        public static final ScaleType f297a = new C0066e();

        /* renamed from: a */
        public void mo386a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            float width = ((((float) rect.width()) - (((float) i) * min)) * 0.5f) + ((float) rect.left);
            float height = (((float) rect.height()) - (((float) i2) * min)) * 0.5f;
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + ((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "fit_center";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$f */
    /* compiled from: PG */
    public static class C0067f extends Zr {

        /* renamed from: a */
        public static final ScaleType f298a = new C0067f();

        /* renamed from: a */
        public void mo388a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            float width = ((float) rect.width()) - (((float) i) * min);
            float height = ((float) rect.height()) - (((float) i2) * min);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (width + ((float) rect.left) + 0.5f)), (float) ((int) (height + ((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "fit_end";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$g */
    /* compiled from: PG */
    public static class C0068g extends Zr {

        /* renamed from: a */
        public static final ScaleType f299a = new C0068g();

        /* renamed from: a */
        public void mo390a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "fit_start";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$h */
    /* compiled from: PG */
    public static class C0069h extends Zr {

        /* renamed from: a */
        public static final ScaleType f300a = new C0069h();

        /* renamed from: a */
        public void mo392a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            matrix.setScale(f3, f4);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
        }

        public String toString() {
            return "fit_xy";
        }
    }

    /* renamed from: com.facebook.drawee.drawable.ScalingUtils$i */
    /* compiled from: PG */
    public static class C0070i extends Zr {

        /* renamed from: a */
        public static final ScaleType f301a = new C0070i();

        /* renamed from: a */
        public void mo394a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float f5;
            float f6;
            if (f4 > f3) {
                float f7 = ((float) i) * f4;
                f5 = Math.max(Math.min((((float) rect.width()) * 0.5f) - (f * f7), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), ((float) rect.width()) - f7) + ((float) rect.left);
                f6 = (float) rect.top;
                f3 = f4;
            } else {
                f5 = (float) rect.left;
                float f8 = ((float) i2) * f3;
                f6 = Math.max(Math.min((((float) rect.height()) * 0.5f) - (f2 * f8), BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), ((float) rect.height()) - f8) + ((float) rect.top);
            }
            matrix.setScale(f3, f3);
            matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
        }

        public String toString() {
            return "focus_crop";
        }
    }

    /* renamed from: a */
    public static Yr m288a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof Yr) {
            return (Yr) drawable;
        }
        if (drawable instanceof Nr) {
            return m288a(((Nr) drawable).a());
        }
        if (drawable instanceof Lr) {
            Lr lr = (Lr) drawable;
            int length = lr.c.length;
            for (int i = 0; i < length; i++) {
                Yr a = m288a(lr.a(i));
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }
}
