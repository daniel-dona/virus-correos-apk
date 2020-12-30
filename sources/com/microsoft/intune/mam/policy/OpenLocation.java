package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum OpenLocation {
    ONEDRIVE_FOR_BUSINESS(1),
    SHAREPOINT(2),
    CAMERA(4),
    LOCAL(8),
    ACCOUNT_DOCUMENT(16),
    OTHER(Integer.MIN_VALUE);
    
    public final int mCode;

    /* access modifiers changed from: public */
    OpenLocation(int i) {
        this.mCode = i;
    }

    public static OpenLocation fromCode(int i) {
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
