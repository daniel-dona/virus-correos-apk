package com.p000BV.LinearGradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;

/* renamed from: com.BV.LinearGradient.LinearGradientView */
/* compiled from: PG */
public class LinearGradientView extends View {

    /* renamed from: a */
    public final Paint f12a = new Paint(1);

    /* renamed from: b */
    public Path f13b;

    /* renamed from: c */
    public RectF f14c;

    /* renamed from: d */
    public LinearGradient f15d;

    /* renamed from: e */
    public float[] f16e;

    /* renamed from: k */
    public float[] f17k = {BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER};

    /* renamed from: n */
    public float[] f18n = {BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, 1.0f};

    /* renamed from: p */
    public int[] f19p;

    /* renamed from: q */
    public boolean f20q = false;

    /* renamed from: q3 */
    public int[] f21q3 = {0, 0};

    /* renamed from: r3 */
    public float[] f22r3 = {BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER};

    /* renamed from: x */
    public float[] f23x = {0.5f, 0.5f};

    /* renamed from: y */
    public float f24y = 45.0f;

    public LinearGradientView(Context context) {
        super(context);
    }

    /* renamed from: a */
    public final void mo28a() {
        int[] iArr = this.f19p;
        if (iArr != null) {
            float[] fArr = this.f16e;
            if (fArr == null || iArr.length == fArr.length) {
                float[] fArr2 = this.f17k;
                float[] fArr3 = this.f18n;
                if (this.f20q && this.f23x != null) {
                    float sqrt = (float) Math.sqrt(2.0d);
                    double d = (double) ((this.f24y - 90.0f) * 0.017453292f);
                    float[] fArr4 = {((float) Math.cos(d)) * sqrt, ((float) Math.sin(d)) * sqrt};
                    float[] fArr5 = this.f23x;
                    fArr2 = new float[]{fArr5[0] - (fArr4[0] / 2.0f), fArr5[1] - (fArr4[1] / 2.0f)};
                    fArr3 = new float[]{(fArr4[0] / 2.0f) + fArr5[0], (fArr4[1] / 2.0f) + fArr5[1]};
                }
                float f = fArr2[0];
                int[] iArr2 = this.f21q3;
                float f2 = ((float) iArr2[0]) * f;
                float f3 = fArr2[1] * ((float) iArr2[1]);
                this.f15d = new LinearGradient(f2, f3, fArr3[0] * ((float) iArr2[0]), fArr3[1] * ((float) iArr2[1]), this.f19p, this.f16e, Shader.TileMode.CLAMP);
                this.f12a.setShader(this.f15d);
                invalidate();
            }
        }
    }

    /* renamed from: b */
    public final void mo29b() {
        if (this.f13b == null) {
            this.f13b = new Path();
            this.f14c = new RectF();
        }
        this.f13b.reset();
        RectF rectF = this.f14c;
        int[] iArr = this.f21q3;
        rectF.set(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, (float) iArr[0], (float) iArr[1]);
        this.f13b.addRoundRect(this.f14c, this.f22r3, Path.Direction.CW);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.f13b;
        if (path == null) {
            canvas.drawPaint(this.f12a);
        } else {
            canvas.drawPath(path, this.f12a);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f21q3 = new int[]{i, i2};
        mo29b();
        mo28a();
    }

    public void setAngle(float f) {
        this.f24y = f;
        mo28a();
    }

    public void setAngleCenter(ReadableArray readableArray) {
        this.f23x = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        mo28a();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = GA.b((float) readableArray.getDouble(i));
        }
        this.f22r3 = fArr;
        mo29b();
        mo28a();
    }

    public void setColors(ReadableArray readableArray) {
        int[] iArr = new int[readableArray.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = readableArray.getInt(i);
        }
        this.f19p = iArr;
        mo28a();
    }

    public void setEndPosition(ReadableArray readableArray) {
        this.f18n = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        mo28a();
    }

    public void setLocations(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = (float) readableArray.getDouble(i);
        }
        this.f16e = fArr;
        mo28a();
    }

    public void setStartPosition(ReadableArray readableArray) {
        this.f17k = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        mo28a();
    }

    public void setUseAngle(boolean z) {
        this.f20q = z;
        mo28a();
    }
}
