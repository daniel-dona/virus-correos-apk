package com.facebook.yoga;

@Qw
/* compiled from: PG */
public enum YogaDimension {
    WIDTH(0),
    HEIGHT(1);
    
    public final int mIntValue;

    /* access modifiers changed from: public */
    YogaDimension(int i) {
        this.mIntValue = i;
    }

    public static YogaDimension fromInt(int i) {
        if (i == 0) {
            return WIDTH;
        }
        if (i == 1) {
            return HEIGHT;
        }
        throw new IllegalArgumentException(Eo.b("Unknown enum value: ", i));
    }

    public int intValue() {
        return this.mIntValue;
    }
}
