package com.facebook.yoga;

@Qw
/* compiled from: PG */
public enum YogaPrintOptions {
    LAYOUT(1),
    STYLE(2),
    CHILDREN(4);
    
    public final int mIntValue;

    /* access modifiers changed from: public */
    YogaPrintOptions(int i) {
        this.mIntValue = i;
    }

    public static YogaPrintOptions fromInt(int i) {
        if (i == 1) {
            return LAYOUT;
        }
        if (i == 2) {
            return STYLE;
        }
        if (i == 4) {
            return CHILDREN;
        }
        throw new IllegalArgumentException(Eo.b("Unknown enum value: ", i));
    }

    public int intValue() {
        return this.mIntValue;
    }
}
