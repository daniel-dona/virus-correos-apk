package com.microsoft.intune.mam.policy;

import com.microsoft.intune.mam.client.app.startup.ADALConnectionDetails;

/* compiled from: PG */
public interface MAMEnrollmentManager {

    /* compiled from: PG */
    public enum Result {
        AUTHORIZATION_NEEDED(0),
        NOT_LICENSED(1),
        ENROLLMENT_SUCCEEDED(2),
        ENROLLMENT_FAILED(3),
        WRONG_USER(4),
        MDM_ENROLLED(5),
        UNENROLLMENT_SUCCEEDED(6),
        UNENROLLMENT_FAILED(7),
        PENDING(8),
        COMPANY_PORTAL_REQUIRED(9);
        
        public final int mCode;

        /* access modifiers changed from: public */
        Result(int i) {
            this.mCode = i;
        }

        public static Result fromCode(int i) {
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

    Result getRegisteredAccountStatus(String str);

    @Deprecated
    void registerADALConnectionDetails(String str, ADALConnectionDetails aDALConnectionDetails);

    void registerAccountForMAM(String str, String str2, String str3);

    void registerAccountForMAM(String str, String str2, String str3, String str4);

    void registerAuthenticationCallback(MAMServiceAuthenticationCallback mAMServiceAuthenticationCallback);

    void unregisterAccountForMAM(String str);

    void updateToken(String str, String str2, String str3, String str4);
}
