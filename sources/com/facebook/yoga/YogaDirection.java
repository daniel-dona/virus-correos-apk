package com.facebook.yoga;

@Qw
/* compiled from: PG */
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    
    public final int mIntValue;

    /* access modifiers changed from: public */
    YogaDirection(int i) {
        this.mIntValue = i;
    }

    public static YogaDirection fromInt(int i) {
        if (i == 0) {
            return INHERIT;
        }
        if (i == 1) {
            return LTR;
        }
        if (i == 2) {
            return RTL;
        }
        throw new IllegalArgumentException(Eo.b("Unknown enum value: ", i));
    }

    public int intValue() {
        return this.mIntValue;
    }
}
