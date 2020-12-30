package com.microsoft.intune.mam.policy;

/* compiled from: PG */
public enum WipeReason {
    PIN_MAX_RETRIES_EXCEEDED("adminPolicyMaxPinAttempts"),
    DEVICE_NON_COMPLIANT("adminPolicyJB"),
    APP_OUTDATED("adminPolicyMinApp"),
    OS_OUTDATED("adminPolicyMinOs"),
    OS_PATCH_OUTDATED("adminPolicyMinPatch"),
    CP_OUTDATED("adminPolicyMinCP"),
    UNSUPPORTED_DEVICE("adminPolicyDevice"),
    UNSUPPORTED_DEVICE_MANUFACTURER("adminPolicyDeviceManufacturer"),
    UNSUPPORTED_DEVICE_MODEL("adminPolicyDeviceModel"),
    SERVICE_WIPE("adminRemoteWipe"),
    COMPANY_PORTAL_REMOVED((String) null),
    POLICY_REMOVED((String) null),
    POLICY_REMOVED_APP_UNSTABLE((String) null),
    APP_UNENROLLMENT("userSignedOutWipe"),
    USER_REMOVED_ACCOUNT_AFTER_BLOCK("userSignedOutWipe"),
    RE_ENROLLMENT_FAILED((String) null),
    MISMATCHED_IDENTITIES("mdmDifferentUserWipe"),
    WRONG_USER("userChooseAccountMAMWipe"),
    PORTAL_UNENROLLMENT("mdmUnenrollWipe"),
    DEVICE_ATTESTATION_NON_COMPLIANT("adminPolicySafetyNet"),
    ALLOWED_ACCOUNTS_NOT_ALLOWED("userAccountNotAllowed"),
    NETWORK_CONNECTIVITY_REQUIRED("adminPolicyOfflineWipe"),
    MTD_NON_COMPLIANT("adminPolicyMTD"),
    USER_ACCOUNT_DISABLED("userAccountDisabled");
    
    public String mReasonTag;

    /* access modifiers changed from: public */
    WipeReason(String str) {
        this.mReasonTag = str;
    }

    public String getServiceTag() {
        return this.mReasonTag;
    }

    public boolean isImplicit() {
        switch (ordinal()) {
            case 10:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }
}
