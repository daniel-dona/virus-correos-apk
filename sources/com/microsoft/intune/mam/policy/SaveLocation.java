package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum SaveLocation {
    ONEDRIVE_FOR_BUSINESS(1),
    SHAREPOINT(2),
    BOX(4),
    DROPBOX(8),
    GOOGLE_DRIVE(16),
    LOCAL(32),
    ACCOUNT_DOCUMENT(64),
    OTHER(Integer.MIN_VALUE);
    
    public final int mCode;

    /* access modifiers changed from: public */
    SaveLocation(int i) {
        this.mCode = i;
    }

    public static SaveLocation fromCode(int i) {
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
