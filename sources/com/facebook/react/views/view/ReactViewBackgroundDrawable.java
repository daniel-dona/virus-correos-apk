package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.facebook.react.uimanager.BaseViewManager;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: PG */
public class ReactViewBackgroundDrawable extends Drawable {

    /* renamed from: a */
    public VA f780a;

    /* renamed from: b */
    public VA f781b;

    /* renamed from: c */
    public VA f782c;

    /* renamed from: d */
    public BorderStyle f783d;

    /* renamed from: e */
    public PathEffect f784e;

    /* renamed from: f */
    public Path f785f;

    /* renamed from: g */
    public Path f786g;

    /* renamed from: h */
    public Path f787h;

    /* renamed from: i */
    public Path f788i;

    /* renamed from: j */
    public Path f789j;

    /* renamed from: k */
    public RectF f790k;

    /* renamed from: l */
    public RectF f791l;

    /* renamed from: m */
    public RectF f792m;

    /* renamed from: n */
    public RectF f793n;

    /* renamed from: o */
    public PointF f794o;

    /* renamed from: p */
    public PointF f795p;

    /* renamed from: q */
    public PointF f796q;

    /* renamed from: r */
    public PointF f797r;

    /* renamed from: s */
    public boolean f798s = false;

    /* renamed from: t */
    public float f799t = Float.NaN;

    /* renamed from: u */
    public final Paint f800u = new Paint(1);

    /* renamed from: v */
    public int f801v = 0;

    /* renamed from: w */
    public int f802w = 255;

    /* renamed from: x */
    public float[] f803x;

    /* renamed from: y */
    public final Context f804y;

    /* renamed from: z */
    public int f805z;

    /* compiled from: PG */
    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    /* compiled from: PG */
    public enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        public static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
            int ordinal = borderStyle.ordinal();
            if (ordinal == 0) {
                return null;
            }
            if (ordinal == 1) {
                float f2 = f * 3.0f;
                return new DashPathEffect(new float[]{f2, f2, f2, f2}, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            } else if (ordinal != 2) {
                return null;
            } else {
                return new DashPathEffect(new float[]{f, f, f, f}, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            }
        }
    }

    public ReactViewBackgroundDrawable(Context context) {
        this.f804y = context;
    }

    /* renamed from: a */
    public void mo1702a(int i, float f) {
        if (this.f780a == null) {
            this.f780a = new VA(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
        if (!sA.a(this.f780a.a[i], f)) {
            this.f780a.a(i, f);
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
                this.f798s = true;
            }
            invalidateSelf();
        }
    }

    /* renamed from: b */
    public void mo1707b(float f, int i) {
        if (this.f803x == null) {
            this.f803x = new float[8];
            Arrays.fill(this.f803x, Float.NaN);
        }
        if (!sA.a(this.f803x[i], f)) {
            this.f803x[i] = f;
            this.f798s = true;
            invalidateSelf();
        }
    }

    /* renamed from: c */
    public boolean mo1709c() {
        if (!eF.a(this.f799t) && this.f799t > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            return true;
        }
        float[] fArr = this.f803x;
        if (fArr != null) {
            for (float f : fArr) {
                if (!eF.a(f) && f > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    public final void mo1710d() {
        if (this.f798s) {
            this.f798s = false;
            if (this.f785f == null) {
                this.f785f = new Path();
            }
            if (this.f786g == null) {
                this.f786g = new Path();
            }
            if (this.f787h == null) {
                this.f787h = new Path();
            }
            if (this.f789j == null) {
                this.f789j = new Path();
            }
            if (this.f790k == null) {
                this.f790k = new RectF();
            }
            if (this.f791l == null) {
                this.f791l = new RectF();
            }
            if (this.f792m == null) {
                this.f792m = new RectF();
            }
            if (this.f793n == null) {
                this.f793n = new RectF();
            }
            this.f785f.reset();
            this.f786g.reset();
            this.f787h.reset();
            this.f789j.reset();
            this.f790k.set(getBounds());
            this.f791l.set(getBounds());
            this.f792m.set(getBounds());
            this.f793n.set(getBounds());
            float b = mo1706b();
            if (b > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                float f = b * 0.5f;
                this.f793n.inset(f, f);
            }
            RectF a = mo1700a();
            RectF rectF = this.f790k;
            rectF.top += a.top;
            rectF.bottom -= a.bottom;
            rectF.left += a.left;
            rectF.right -= a.right;
            float f2 = eF.a(this.f799t) ? BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER : this.f799t;
            float a2 = mo1697a(f2, BorderRadiusLocation.TOP_LEFT);
            float a3 = mo1697a(f2, BorderRadiusLocation.TOP_RIGHT);
            float a4 = mo1697a(f2, BorderRadiusLocation.BOTTOM_LEFT);
            float a5 = mo1697a(f2, BorderRadiusLocation.BOTTOM_RIGHT);
            boolean z = this.f805z == 1;
            float a6 = mo1698a(BorderRadiusLocation.TOP_START);
            float a7 = mo1698a(BorderRadiusLocation.TOP_END);
            float a8 = mo1698a(BorderRadiusLocation.BOTTOM_START);
            float a9 = mo1698a(BorderRadiusLocation.BOTTOM_END);
            if (Ty.a().a(this.f804y)) {
                if (!eF.a(a6)) {
                    a2 = a6;
                }
                if (!eF.a(a7)) {
                    a3 = a7;
                }
                if (!eF.a(a8)) {
                    a4 = a8;
                }
                if (!eF.a(a9)) {
                    a5 = a9;
                }
                float f3 = z ? a3 : a2;
                if (!z) {
                    a2 = a3;
                }
                float f4 = z ? a5 : a4;
                if (z) {
                    a5 = a4;
                }
                a4 = f4;
                a3 = a2;
                a2 = f3;
            } else {
                float f5 = z ? a7 : a6;
                if (!z) {
                    a6 = a7;
                }
                float f6 = z ? a9 : a8;
                if (!z) {
                    a8 = a9;
                }
                if (!eF.a(f5)) {
                    a2 = f5;
                }
                if (!eF.a(a6)) {
                    a3 = a6;
                }
                if (!eF.a(f6)) {
                    a4 = f6;
                }
                if (!eF.a(a8)) {
                    a5 = a8;
                }
            }
            float max = Math.max(a2 - a.left, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max2 = Math.max(a2 - a.top, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max3 = Math.max(a3 - a.right, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max4 = Math.max(a3 - a.top, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max5 = Math.max(a5 - a.right, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max6 = Math.max(a5 - a.bottom, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max7 = Math.max(a4 - a.left, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float max8 = Math.max(a4 - a.bottom, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            float f7 = a4;
            float f8 = a5;
            this.f785f.addRoundRect(this.f790k, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.f786g.addRoundRect(this.f791l, new float[]{a2, a2, a3, a3, f8, f8, f7, f7}, Path.Direction.CW);
            VA va = this.f780a;
            float a10 = va != null ? va.a(8) / 2.0f : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
            float f9 = a2 + a10;
            float f10 = a3 + a10;
            float f11 = f8 + a10;
            float f12 = f7 + a10;
            this.f787h.addRoundRect(this.f792m, new float[]{f9, f9, f10, f10, f11, f11, f12, f12}, Path.Direction.CW);
            this.f789j.addRoundRect(this.f793n, new float[]{max + a10, max2 + a10, max3 + a10, max4 + a10, max5 + a10, max6 + a10, max7 + a10, max8 + a10}, Path.Direction.CW);
            if (this.f794o == null) {
                this.f794o = new PointF();
            }
            PointF pointF = this.f794o;
            PointF pointF2 = pointF;
            RectF rectF2 = this.f790k;
            float f13 = rectF2.left;
            pointF.x = f13;
            float f14 = rectF2.top;
            pointF.y = f14;
            double d = (double) f13;
            double d2 = d;
            double d3 = d;
            double d4 = (double) f14;
            RectF rectF3 = this.f791l;
            m629a(d2, d4, (double) ((max * 2.0f) + f13), (double) ((max2 * 2.0f) + f14), (double) rectF3.left, (double) rectF3.top, d3, d4, pointF2);
            if (this.f797r == null) {
                this.f797r = new PointF();
            }
            PointF pointF3 = this.f797r;
            PointF pointF4 = pointF3;
            RectF rectF4 = this.f790k;
            float f15 = rectF4.left;
            pointF3.x = f15;
            float f16 = rectF4.bottom;
            pointF3.y = f16;
            double d5 = (double) f15;
            double d6 = (double) f16;
            double d7 = d6;
            double d8 = d6;
            RectF rectF5 = this.f791l;
            m629a(d5, (double) (f16 - (max8 * 2.0f)), (double) ((max7 * 2.0f) + f15), d8, (double) rectF5.left, (double) rectF5.bottom, d5, d7, pointF4);
            if (this.f795p == null) {
                this.f795p = new PointF();
            }
            PointF pointF5 = this.f795p;
            PointF pointF6 = pointF5;
            RectF rectF6 = this.f790k;
            float f17 = rectF6.right;
            pointF5.x = f17;
            float f18 = rectF6.top;
            pointF5.y = f18;
            double d9 = (double) f18;
            double d10 = (double) f17;
            RectF rectF7 = this.f791l;
            m629a((double) (f17 - (max3 * 2.0f)), d9, d10, (double) ((max4 * 2.0f) + f18), (double) rectF7.right, (double) rectF7.top, d10, d9, pointF6);
            if (this.f796q == null) {
                this.f796q = new PointF();
            }
            PointF pointF7 = this.f796q;
            PointF pointF8 = pointF7;
            RectF rectF8 = this.f790k;
            float f19 = rectF8.right;
            pointF7.x = f19;
            float f20 = rectF8.bottom;
            pointF7.y = f20;
            double d11 = (double) f19;
            double d12 = (double) f20;
            double d13 = d12;
            double d14 = d12;
            RectF rectF9 = this.f791l;
            m629a((double) (f19 - (max5 * 2.0f)), (double) (f20 - (max6 * 2.0f)), d11, d14, (double) rectF9.right, (double) rectF9.bottom, d11, d13, pointF8);
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2;
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        int i3;
        int i4;
        int i5;
        int i6;
        Canvas canvas3 = canvas;
        BorderStyle borderStyle = this.f783d;
        this.f784e = borderStyle != null ? BorderStyle.getPathEffect(borderStyle, mo1706b()) : null;
        this.f800u.setPathEffect(this.f784e);
        boolean z = false;
        if (!mo1709c()) {
            int a = nE.a(this.f801v, this.f802w);
            if (Color.alpha(a) != 0) {
                this.f800u.setColor(a);
                this.f800u.setStyle(Paint.Style.FILL);
                canvas3.drawRect(getBounds(), this.f800u);
            }
            RectF a2 = mo1700a();
            int round = Math.round(a2.left);
            int round2 = Math.round(a2.top);
            int round3 = Math.round(a2.right);
            int round4 = Math.round(a2.bottom);
            if (round > 0 || round3 > 0 || round2 > 0 || round4 > 0) {
                Rect bounds = getBounds();
                int a3 = mo1699a(0);
                int a4 = mo1699a(1);
                int a5 = mo1699a(2);
                int a6 = mo1699a(3);
                boolean z2 = this.f805z == 1;
                int a7 = mo1699a(4);
                int a8 = mo1699a(5);
                if (Ty.a().a(this.f804y)) {
                    if (mo1708b(4)) {
                        a3 = a7;
                    }
                    if (mo1708b(5)) {
                        a5 = a8;
                    }
                    int i7 = z2 ? a5 : a3;
                    if (z2) {
                        a5 = a3;
                    }
                    i4 = i7;
                    i3 = a5;
                } else {
                    int i8 = z2 ? a8 : a7;
                    if (!z2) {
                        a7 = a8;
                    }
                    boolean b = mo1708b(4);
                    boolean b2 = mo1708b(5);
                    boolean z3 = z2 ? b2 : b;
                    if (z2) {
                        b2 = b;
                    }
                    if (z3) {
                        a3 = i8;
                    }
                    if (b2) {
                        i4 = a3;
                        i3 = a7;
                    } else {
                        i3 = a5;
                        i4 = a3;
                    }
                }
                int i9 = bounds.left;
                int i10 = bounds.top;
                int i11 = -1;
                int i12 = (round > 0 ? i4 : -1) & (round2 > 0 ? a4 : -1) & (round3 > 0 ? i3 : -1);
                if (round4 > 0) {
                    i11 = a6;
                }
                int i13 = i11 & i12;
                if (i13 != ((round > 0 ? i4 : 0) | (round2 > 0 ? a4 : 0) | (round3 > 0 ? i3 : 0) | (round4 > 0 ? a6 : 0))) {
                    i13 = 0;
                }
                if (i13 == 0) {
                    this.f800u.setAntiAlias(false);
                    int width = bounds.width();
                    int height = bounds.height();
                    if (round > 0) {
                        float f5 = (float) i9;
                        float f6 = (float) (i9 + round);
                        int i14 = i10 + height;
                        i5 = i10;
                        i6 = i9;
                        mo1704a(canvas, i4, f5, (float) i10, f6, (float) (i10 + round2), f6, (float) (i14 - round4), f5, (float) i14);
                    } else {
                        i5 = i10;
                        i6 = i9;
                    }
                    if (round2 > 0) {
                        float f7 = (float) i5;
                        float f8 = (float) (i5 + round2);
                        int i15 = i6 + width;
                        mo1704a(canvas, a4, (float) i6, f7, (float) (i6 + round), f8, (float) (i15 - round3), f8, (float) i15, f7);
                    }
                    if (round3 > 0) {
                        int i16 = i6 + width;
                        float f9 = (float) i16;
                        int i17 = i5 + height;
                        float f10 = (float) (i16 - round3);
                        mo1704a(canvas, i3, f9, (float) i5, f9, (float) i17, f10, (float) (i17 - round4), f10, (float) (i5 + round2));
                    }
                    if (round4 > 0) {
                        int i18 = i5 + height;
                        float f11 = (float) i18;
                        int i19 = i6 + width;
                        float f12 = (float) i19;
                        float f13 = (float) (i19 - round3);
                        float f14 = (float) (i18 - round4);
                        mo1704a(canvas, a6, (float) i6, f11, f12, f11, f13, f14, (float) (i6 + round), f14);
                    }
                    this.f800u.setAntiAlias(true);
                } else if (Color.alpha(i13) != 0) {
                    int i20 = bounds.right;
                    int i21 = bounds.bottom;
                    this.f800u.setColor(i13);
                    if (round > 0) {
                        canvas.drawRect((float) i9, (float) i10, (float) (i9 + round), (float) (i21 - round4), this.f800u);
                    }
                    if (round2 > 0) {
                        canvas.drawRect((float) (round + i9), (float) i10, (float) i20, (float) (i10 + round2), this.f800u);
                    }
                    if (round3 > 0) {
                        canvas.drawRect((float) (i20 - round3), (float) (i10 + round2), (float) i20, (float) i21, this.f800u);
                    }
                    if (round4 > 0) {
                        canvas.drawRect((float) i9, (float) (i21 - round4), (float) (i20 - round3), (float) i21, this.f800u);
                    }
                }
            }
        } else {
            mo1710d();
            canvas.save();
            int a9 = nE.a(this.f801v, this.f802w);
            if (Color.alpha(a9) != 0) {
                this.f800u.setColor(a9);
                this.f800u.setStyle(Paint.Style.FILL);
                canvas2 = canvas;
                canvas2.drawPath(this.f785f, this.f800u);
            } else {
                canvas2 = canvas;
            }
            RectF a10 = mo1700a();
            if (a10.top > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a10.bottom > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a10.left > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a10.right > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                float b3 = mo1706b();
                if (a10.top != b3 || a10.bottom != b3 || a10.left != b3 || a10.right != b3) {
                    this.f800u.setStyle(Paint.Style.FILL);
                    canvas2.clipPath(this.f786g, Region.Op.INTERSECT);
                    canvas2.clipPath(this.f785f, Region.Op.DIFFERENCE);
                    int a11 = mo1699a(0);
                    int a12 = mo1699a(1);
                    int a13 = mo1699a(2);
                    int a14 = mo1699a(3);
                    if (this.f805z == 1) {
                        z = true;
                    }
                    int a15 = mo1699a(4);
                    int a16 = mo1699a(5);
                    if (Ty.a().a(this.f804y)) {
                        if (mo1708b(4)) {
                            a11 = a15;
                        }
                        if (mo1708b(5)) {
                            a13 = a16;
                        }
                        int i22 = z ? a13 : a11;
                        if (!z) {
                            a11 = a13;
                        }
                        i = a11;
                        i2 = i22;
                    } else {
                        int i23 = z ? a16 : a15;
                        if (!z) {
                            a15 = a16;
                        }
                        boolean b4 = mo1708b(4);
                        boolean b5 = mo1708b(5);
                        boolean z4 = z ? b5 : b4;
                        if (z) {
                            b5 = b4;
                        }
                        if (z4) {
                            a11 = i23;
                        }
                        i2 = a11;
                        i = b5 ? a15 : a13;
                    }
                    RectF rectF = this.f791l;
                    float f15 = rectF.left;
                    float f16 = rectF.right;
                    float f17 = rectF.top;
                    float f18 = rectF.bottom;
                    if (a10.left > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                        PointF pointF = this.f794o;
                        float f19 = pointF.x;
                        float f20 = pointF.y;
                        PointF pointF2 = this.f797r;
                        f2 = f18;
                        f3 = f17;
                        f4 = f16;
                        f = f15;
                        mo1704a(canvas, i2, f15, f17, f19, f20, pointF2.x, pointF2.y, f15, f2);
                    } else {
                        f2 = f18;
                        f3 = f17;
                        f4 = f16;
                        f = f15;
                    }
                    if (a10.top > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                        PointF pointF3 = this.f794o;
                        float f21 = pointF3.x;
                        float f22 = pointF3.y;
                        PointF pointF4 = this.f795p;
                        mo1704a(canvas, a12, f, f3, f21, f22, pointF4.x, pointF4.y, f4, f3);
                    }
                    if (a10.right > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                        PointF pointF5 = this.f795p;
                        float f23 = pointF5.x;
                        float f24 = pointF5.y;
                        PointF pointF6 = this.f796q;
                        mo1704a(canvas, i, f4, f3, f23, f24, pointF6.x, pointF6.y, f4, f2);
                    }
                    if (a10.bottom > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                        PointF pointF7 = this.f797r;
                        float f25 = pointF7.x;
                        float f26 = pointF7.y;
                        PointF pointF8 = this.f796q;
                        mo1704a(canvas, a14, f, f2, f25, f26, pointF8.x, pointF8.y, f4, f2);
                    }
                } else if (b3 > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                    this.f800u.setColor(nE.a(mo1699a(8), this.f802w));
                    this.f800u.setStyle(Paint.Style.STROKE);
                    this.f800u.setStrokeWidth(b3);
                    canvas2.drawPath(this.f789j, this.f800u);
                }
            }
            canvas.restore();
        }
    }

    public int getAlpha() {
        return this.f802w;
    }

    public int getOpacity() {
        int a = nE.a(this.f801v, this.f802w) >>> 24;
        if (a == 255) {
            return -1;
        }
        return a == 0 ? -2 : -3;
    }

    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT < 21) {
            super.getOutline(outline);
        } else if ((eF.a(this.f799t) || this.f799t <= BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) && this.f803x == null) {
            outline.setRect(getBounds());
        } else {
            mo1710d();
            outline.setConvexPath(this.f787h);
        }
    }

    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f798s = true;
    }

    public void setAlpha(int i) {
        if (i != this.f802w) {
            this.f802w = i;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* renamed from: b */
    public float mo1706b() {
        VA va = this.f780a;
        return (va == null || eF.a(va.a[8])) ? BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER : this.f780a.a[8];
    }

    /* renamed from: a */
    public void mo1703a(int i, float f, float f2) {
        if (this.f781b == null) {
            this.f781b = new VA(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
        if (!sA.a(this.f781b.a[i], f)) {
            this.f781b.a(i, f);
            invalidateSelf();
        }
        if (this.f782c == null) {
            this.f782c = new VA(255.0f);
        }
        if (!sA.a(this.f782c.a[i], f2)) {
            this.f782c.a(i, f2);
            invalidateSelf();
        }
    }

    /* renamed from: b */
    public final boolean mo1708b(int i) {
        VA va = this.f781b;
        float f = Float.NaN;
        float a = va != null ? va.a(i) : Float.NaN;
        VA va2 = this.f782c;
        if (va2 != null) {
            f = va2.a(i);
        }
        return !eF.a(a) && !eF.a(f);
    }

    /* renamed from: a */
    public void mo1705a(String str) {
        BorderStyle borderStyle;
        if (str == null) {
            borderStyle = null;
        } else {
            borderStyle = BorderStyle.valueOf(str.toUpperCase(Locale.US));
        }
        if (this.f783d != borderStyle) {
            this.f783d = borderStyle;
            this.f798s = true;
            invalidateSelf();
        }
    }

    /* renamed from: a */
    public void mo1701a(float f) {
        if (!sA.a(this.f799t, f)) {
            this.f799t = f;
            this.f798s = true;
            invalidateSelf();
        }
    }

    /* renamed from: a */
    public float mo1698a(BorderRadiusLocation borderRadiusLocation) {
        return mo1697a(Float.NaN, borderRadiusLocation);
    }

    /* renamed from: a */
    public float mo1697a(float f, BorderRadiusLocation borderRadiusLocation) {
        float[] fArr = this.f803x;
        if (fArr == null) {
            return f;
        }
        float f2 = fArr[borderRadiusLocation.ordinal()];
        return eF.a(f2) ? f : f2;
    }

    /* renamed from: a */
    public static void m629a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        PointF pointF2 = pointF;
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = (d16 * d13 * d13) + d15;
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(Math.pow(d18 / d20, 2.0d) + d19);
        double d21 = (d13 * sqrt) + d14;
        double d22 = sqrt + d9;
        double d23 = d21 + d10;
        if (!Double.isNaN(d22) && !Double.isNaN(d23)) {
            PointF pointF3 = pointF;
            pointF3.x = (float) d22;
            pointF3.y = (float) d23;
        }
    }

    /* renamed from: a */
    public float mo1696a(float f, int i) {
        VA va = this.f780a;
        if (va == null) {
            return f;
        }
        float f2 = va.a[i];
        return eF.a(f2) ? f : f2;
    }

    /* renamed from: a */
    public final void mo1704a(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i != 0) {
            if (this.f788i == null) {
                this.f788i = new Path();
            }
            this.f800u.setColor(i);
            this.f788i.reset();
            this.f788i.moveTo(f, f2);
            this.f788i.lineTo(f3, f4);
            this.f788i.lineTo(f5, f6);
            this.f788i.lineTo(f7, f8);
            this.f788i.lineTo(f, f2);
            canvas.drawPath(this.f788i, this.f800u);
        }
    }

    /* renamed from: a */
    public final int mo1699a(int i) {
        VA va = this.f781b;
        float a = va != null ? va.a(i) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        VA va2 = this.f782c;
        return ((((int) (va2 != null ? va2.a(i) : 255.0f)) << 24) & -16777216) | (((int) a) & 16777215);
    }

    /* renamed from: a */
    public RectF mo1700a() {
        float a = mo1696a((float) BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, 8);
        boolean z = true;
        float a2 = mo1696a(a, 1);
        float a3 = mo1696a(a, 3);
        float a4 = mo1696a(a, 0);
        float a5 = mo1696a(a, 2);
        if (this.f780a != null) {
            if (this.f805z != 1) {
                z = false;
            }
            float[] fArr = this.f780a.a;
            float f = fArr[4];
            float f2 = fArr[5];
            if (Ty.a().a(this.f804y)) {
                if (!eF.a(f)) {
                    a4 = f;
                }
                if (!eF.a(f2)) {
                    a5 = f2;
                }
                float f3 = z ? a5 : a4;
                if (z) {
                    a5 = a4;
                }
                a4 = f3;
            } else {
                float f4 = z ? f2 : f;
                if (z) {
                    f2 = f;
                }
                if (!eF.a(f4)) {
                    a4 = f4;
                }
                if (!eF.a(f2)) {
                    a5 = f2;
                }
            }
        }
        return new RectF(a4, a2, a5, a3);
    }
}
