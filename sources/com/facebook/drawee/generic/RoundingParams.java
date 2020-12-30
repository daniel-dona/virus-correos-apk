package com.facebook.drawee.generic;

import com.facebook.react.uimanager.BaseViewManager;
import java.util.Arrays;

/* compiled from: PG */
public class RoundingParams {

    /* renamed from: a */
    public RoundingMethod f302a = RoundingMethod.BITMAP_ONLY;

    /* renamed from: b */
    public boolean f303b = false;

    /* renamed from: c */
    public float[] f304c = null;

    /* renamed from: d */
    public int f305d = 0;

    /* renamed from: e */
    public float f306e = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: f */
    public int f307f = 0;

    /* renamed from: g */
    public float f308g = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: h */
    public boolean f309h = false;

    /* compiled from: PG */
    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    /* renamed from: a */
    public RoundingParams mo398a(int i) {
        this.f305d = i;
        this.f302a = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    /* renamed from: b */
    public RoundingParams mo399b(float f) {
        if (this.f304c == null) {
            this.f304c = new float[8];
        }
        Arrays.fill(this.f304c, f);
        return this;
    }

    /* renamed from: c */
    public RoundingParams mo400c(float f) {
        kq.a(f >= BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, "the padding cannot be < 0");
        this.f308g = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RoundingParams.class != obj.getClass()) {
            return false;
        }
        RoundingParams roundingParams = (RoundingParams) obj;
        if (this.f303b == roundingParams.f303b && this.f305d == roundingParams.f305d && Float.compare(roundingParams.f306e, this.f306e) == 0 && this.f307f == roundingParams.f307f && Float.compare(roundingParams.f308g, this.f308g) == 0 && this.f302a == roundingParams.f302a && this.f309h == roundingParams.f309h) {
            return Arrays.equals(this.f304c, roundingParams.f304c);
        }
        return false;
    }

    public int hashCode() {
        RoundingMethod roundingMethod = this.f302a;
        int i = 0;
        int hashCode = (((roundingMethod != null ? roundingMethod.hashCode() : 0) * 31) + (this.f303b ? 1 : 0)) * 31;
        float[] fArr = this.f304c;
        int hashCode2 = (((hashCode + (fArr != null ? Arrays.hashCode(fArr) : 0)) * 31) + this.f305d) * 31;
        float f = this.f306e;
        int floatToIntBits = (((hashCode2 + (f != BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER ? Float.floatToIntBits(f) : 0)) * 31) + this.f307f) * 31;
        float f2 = this.f308g;
        if (f2 != BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            i = Float.floatToIntBits(f2);
        }
        return ((floatToIntBits + i) * 31) + (this.f309h ? 1 : 0);
    }

    /* renamed from: a */
    public RoundingParams mo397a(float f, float f2, float f3, float f4) {
        if (this.f304c == null) {
            this.f304c = new float[8];
        }
        float[] fArr = this.f304c;
        fArr[1] = f;
        fArr[0] = f;
        fArr[3] = f2;
        fArr[2] = f2;
        fArr[5] = f3;
        fArr[4] = f3;
        fArr[7] = f4;
        fArr[6] = f4;
        return this;
    }

    /* renamed from: a */
    public RoundingParams mo396a(float f) {
        kq.a(f >= BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, "the border width cannot be < 0");
        this.f306e = f;
        return this;
    }
}
