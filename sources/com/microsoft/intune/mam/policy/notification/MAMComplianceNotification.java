package com.microsoft.intune.mam.policy.notification;

import com.microsoft.intune.mam.policy.MAMCAComplianceStatus;

/* compiled from: PG */
public interface MAMComplianceNotification extends MAMUserNotification {
    String getComplianceErrorMessage();

    String getComplianceErrorTitle();

    MAMCAComplianceStatus getComplianceStatus();
}
