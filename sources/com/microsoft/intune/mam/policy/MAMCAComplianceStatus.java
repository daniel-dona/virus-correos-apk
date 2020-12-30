package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum MAMCAComplianceStatus {
    UNKNOWN(-1),
    COMPLIANT(0),
    NOT_COMPLIANT(1),
    SERVICE_FAILURE(2),
    NETWORK_FAILURE(3),
    CLIENT_ERROR(4),
    PENDING(5),
    COMPANY_PORTAL_REQUIRED(6);
    
    public final int mCode;

    /* access modifiers changed from: public */
    MAMCAComplianceStatus(int i) {
        this.mCode = i;
    }

    public static MAMCAComplianceStatus fromCode(int i) {
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].getCode() == i) {
                return values()[i2];
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return this.mCode;
    }
}
