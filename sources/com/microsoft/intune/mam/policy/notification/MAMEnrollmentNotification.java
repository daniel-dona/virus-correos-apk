package com.microsoft.intune.mam.policy.notification;

import com.microsoft.intune.mam.client.telemetry.events.ScenarioEvent;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager;
import com.microsoft.intune.mam.policy.MAMWEError;

/* compiled from: PG */
public interface MAMEnrollmentNotification extends MAMUserNotification {
    MAMEnrollmentManager.Result getEnrollmentResult();

    MAMWEError getError();

    ScenarioEvent.Scenario getScenario();

    String getSessionId();
}
