package com.facebook.yoga;

import com.facebook.react.uimanager.BaseViewManager;

@Qw
/* compiled from: PG */
public class YogaValue {

    /* renamed from: a */
    public final float f858a;

    /* renamed from: b */
    public final YogaUnit f859b;

    static {
        new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
        new YogaValue((float) BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, YogaUnit.POINT);
        new YogaValue(Float.NaN, YogaUnit.AUTO);
    }

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.f858a = f;
        this.f859b = yogaUnit;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof YogaValue)) {
            return false;
        }
        YogaValue yogaValue = (YogaValue) obj;
        YogaUnit yogaUnit = this.f859b;
        if (yogaUnit != yogaValue.f859b) {
            return false;
        }
        if (yogaUnit == YogaUnit.UNDEFINED || yogaUnit == YogaUnit.AUTO || Float.compare(this.f858a, yogaValue.f858a) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f859b.intValue() + Float.floatToIntBits(this.f858a);
    }

    public String toString() {
        int ordinal = this.f859b.ordinal();
        if (ordinal == 0) {
            return "undefined";
        }
        if (ordinal == 1) {
            return Float.toString(this.f858a);
        }
        if (ordinal == 2) {
            return this.f858a + "%";
        } else if (ordinal == 3) {
            return "auto";
        } else {
            throw new IllegalStateException();
        }
    }

    @Qw
    public YogaValue(float f, int i) {
        YogaUnit fromInt = YogaUnit.fromInt(i);
        this.f858a = f;
        this.f859b = fromInt;
    }
}
