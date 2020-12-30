package com.microsoft.intune.mam.policy;

import android.content.Intent;
import android.net.Uri;

/* compiled from: PG */
public interface AppPolicy {
    boolean areIntentActivitiesAllowed(Intent intent);

    boolean diagnosticIsFileEncryptionInUse();

    boolean getIsContactSyncAllowed();

    boolean getIsManagedBrowserRequired();

    boolean getIsOpenFromLocationAllowed(OpenLocation openLocation, String str);

    boolean getIsPinRequired();

    boolean getIsSaveToLocationAllowed(Uri uri);

    boolean getIsSaveToLocationAllowed(SaveLocation saveLocation, String str);

    @Deprecated
    boolean getIsSaveToPersonalAllowed();

    boolean getIsScreenCaptureAllowed();

    NotificationRestriction getNotificationRestriction();

    String toString();
}
