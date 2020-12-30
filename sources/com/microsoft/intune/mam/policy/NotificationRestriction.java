package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum NotificationRestriction {
    UNRESTRICTED(0),
    BLOCK_ORG_DATA(1),
    BLOCKED(2);
    
    public final int mCode;

    /* access modifiers changed from: public */
    NotificationRestriction(int i) {
        this.mCode = i;
    }

    public static NotificationRestriction fromCode(int i) {
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].getCode() == i) {
                return values()[i2];
            }
        }
        return UNRESTRICTED;
    }

    public int getCode() {
        return this.mCode;
    }
}
