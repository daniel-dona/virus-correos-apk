package com.microsoft.intune.mam.policy.notification;

/* compiled from: PG */
public enum MAMNotificationType {
    REFRESH_POLICY(0),
    WIPE_USER_DATA(1),
    MAM_ENROLLMENT_RESULT(2),
    WIPE_USER_AUXILIARY_DATA(3),
    REFRESH_APP_CONFIG(4),
    MANAGEMENT_REMOVED(5),
    COMPLIANCE_STATUS(6);
    
    public final int mCode;

    /* access modifiers changed from: public */
    MAMNotificationType(int i) {
        this.mCode = i;
    }

    public static MAMNotificationType fromCode(int i) {
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
