package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum MAMWEError {
    NONE_KNOWN(0),
    APP_DID_NOT_PROVIDE_TOKEN(1),
    NETWORK_ERROR(2),
    CLIENT_EXCEPTION(3);
    
    public int mCode;

    /* access modifiers changed from: public */
    MAMWEError(int i) {
        this.mCode = i;
    }

    public static MAMWEError fromCode(int i) {
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
