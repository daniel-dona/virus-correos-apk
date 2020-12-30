package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.BaseViewManager;
import java.util.Arrays;

/* compiled from: PG */
public class RoundedCornersDrawable extends Rr implements Tr {

    /* renamed from: d */
    public Type f277d;

    /* renamed from: e */
    public final RectF f278e;

    /* renamed from: k */
    public RectF f279k;

    /* renamed from: n */
    public Matrix f280n;

    /* renamed from: p */
    public final float[] f281p;

    /* renamed from: q */
    public final float[] f282q;

    /* renamed from: q3 */
    public float f283q3;

    /* renamed from: r3 */
    public int f284r3;

    /* renamed from: s3 */
    public int f285s3;

    /* renamed from: t3 */
    public float f286t3;

    /* renamed from: u3 */
    public boolean f287u3;

    /* renamed from: v3 */
    public final Path f288v3;

    /* renamed from: w3 */
    public final Path f289w3;

    /* renamed from: x */
    public final Paint f290x;

    /* renamed from: x3 */
    public final RectF f291x3;

    /* renamed from: y */
    public boolean f292y;

    /* compiled from: PG */
    public enum Type {
        OVERLAY_COLOR,
        CLIPPING
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoundedCornersDrawable(Drawable drawable) {
        super(drawable);
        if (drawable != null) {
            this.f277d = Type.OVERLAY_COLOR;
            this.f278e = new RectF();
            this.f281p = new float[8];
            this.f282q = new float[8];
            this.f290x = new Paint(1);
            this.f292y = false;
            this.f283q3 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
            this.f284r3 = 0;
            this.f285s3 = 0;
            this.f286t3 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
            this.f287u3 = false;
            this.f288v3 = new Path();
            this.f289w3 = new Path();
            this.f291x3 = new RectF();
            return;
        }
        throw new NullPointerException();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: a */
    public void mo369a(boolean z) {
        this.f292y = z;
        mo371b();
        invalidateSelf();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: b */
    public void mo372b(float f) {
        Arrays.fill(this.f281p, f);
        mo371b();
        invalidateSelf();
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [Rr, com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    public void draw(Canvas canvas) {
        this.f278e.set(getBounds());
        int ordinal = this.f277d.ordinal();
        if (ordinal == 0) {
            if (this.f287u3) {
                RectF rectF = this.f279k;
                if (rectF == null) {
                    this.f279k = new RectF(this.f278e);
                    this.f280n = new Matrix();
                } else {
                    rectF.set(this.f278e);
                }
                RectF rectF2 = this.f279k;
                float f = this.f283q3;
                rectF2.inset(f, f);
                this.f280n.setRectToRect(this.f278e, this.f279k, Matrix.ScaleToFit.FILL);
                int save = canvas.save();
                canvas.clipRect(this.f278e);
                canvas.concat(this.f280n);
                this.a.draw(canvas);
                canvas.restoreToCount(save);
            } else {
                this.a.draw(canvas);
            }
            this.f290x.setStyle(Paint.Style.FILL);
            this.f290x.setColor(this.f285s3);
            this.f290x.setStrokeWidth(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            this.f288v3.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.f288v3, this.f290x);
            if (this.f292y) {
                float width = ((this.f278e.width() - this.f278e.height()) + this.f283q3) / 2.0f;
                float height = ((this.f278e.height() - this.f278e.width()) + this.f283q3) / 2.0f;
                if (width > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                    RectF rectF3 = this.f278e;
                    float f2 = rectF3.left;
                    Canvas canvas2 = canvas;
                    canvas2.drawRect(f2, rectF3.top, f2 + width, rectF3.bottom, this.f290x);
                    RectF rectF4 = this.f278e;
                    float f3 = rectF4.right;
                    canvas2.drawRect(f3 - width, rectF4.top, f3, rectF4.bottom, this.f290x);
                }
                if (height > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                    RectF rectF5 = this.f278e;
                    float f4 = rectF5.left;
                    float f5 = rectF5.top;
                    Canvas canvas3 = canvas;
                    canvas3.drawRect(f4, f5, rectF5.right, f5 + height, this.f290x);
                    RectF rectF6 = this.f278e;
                    float f6 = rectF6.left;
                    float f7 = rectF6.bottom;
                    canvas3.drawRect(f6, f7 - height, rectF6.right, f7, this.f290x);
                }
            }
        } else if (ordinal == 1) {
            int save2 = canvas.save();
            this.f288v3.setFillType(Path.FillType.EVEN_ODD);
            canvas.clipPath(this.f288v3);
            this.a.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (this.f284r3 != 0) {
            this.f290x.setStyle(Paint.Style.STROKE);
            this.f290x.setColor(this.f284r3);
            this.f290x.setStrokeWidth(this.f283q3);
            this.f288v3.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.f289w3, this.f290x);
        }
    }

    public void onBoundsChange(Rect rect) {
        this.a.setBounds(rect);
        mo371b();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: a */
    public void mo370a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.f281p, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        } else {
            kq.a(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.f281p, 0, 8);
        }
        mo371b();
        invalidateSelf();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: b */
    public void mo373b(boolean z) {
        this.f287u3 = z;
        mo371b();
        invalidateSelf();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: b */
    public final void mo371b() {
        float[] fArr;
        this.f288v3.reset();
        this.f289w3.reset();
        this.f291x3.set(getBounds());
        RectF rectF = this.f291x3;
        float f = this.f286t3;
        rectF.inset(f, f);
        this.f288v3.addRect(this.f291x3, Path.Direction.CW);
        if (this.f292y) {
            this.f288v3.addCircle(this.f291x3.centerX(), this.f291x3.centerY(), Math.min(this.f291x3.width(), this.f291x3.height()) / 2.0f, Path.Direction.CW);
        } else {
            this.f288v3.addRoundRect(this.f291x3, this.f281p, Path.Direction.CW);
        }
        RectF rectF2 = this.f291x3;
        float f2 = this.f286t3;
        rectF2.inset(-f2, -f2);
        RectF rectF3 = this.f291x3;
        float f3 = this.f283q3;
        rectF3.inset(f3 / 2.0f, f3 / 2.0f);
        if (this.f292y) {
            this.f289w3.addCircle(this.f291x3.centerX(), this.f291x3.centerY(), Math.min(this.f291x3.width(), this.f291x3.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i = 0;
            while (true) {
                fArr = this.f282q;
                if (i >= fArr.length) {
                    break;
                }
                fArr[i] = (this.f281p[i] + this.f286t3) - (this.f283q3 / 2.0f);
                i++;
            }
            this.f289w3.addRoundRect(this.f291x3, fArr, Path.Direction.CW);
        }
        RectF rectF4 = this.f291x3;
        float f4 = this.f283q3;
        rectF4.inset((-f4) / 2.0f, (-f4) / 2.0f);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: a */
    public void mo368a(int i, float f) {
        this.f284r3 = i;
        this.f283q3 = f;
        mo371b();
        invalidateSelf();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.drawee.drawable.RoundedCornersDrawable, android.graphics.drawable.Drawable] */
    /* renamed from: a */
    public void mo367a(float f) {
        this.f286t3 = f;
        mo371b();
        invalidateSelf();
    }
}
