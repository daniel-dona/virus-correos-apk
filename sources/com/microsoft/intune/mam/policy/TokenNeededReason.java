package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum TokenNeededReason {
    NOT_NEEDED(0),
    ENROLLMENT(1),
    CHECKIN(2),
    COMPLIANCE(3);
    
    public final int mCode;

    /* access modifiers changed from: public */
    TokenNeededReason(int i) {
        this.mCode = i;
    }

    public static TokenNeededReason fromCode(int i) {
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].getCode() == i) {
                return values()[i2];
            }
        }
        return null;
    }

    public int getCode() {
        return this.mCode;
    }
}
