package org.chromium.chrome.browser.feature_engagement;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.feature_engagement.Tracker;

/* compiled from: PG */
public final class TrackerFactory {
    /* renamed from: a */
    public static Tracker m2414a(Profile profile) {
        return nativeGetTrackerForProfile(profile);
    }

    public static native Tracker nativeGetTrackerForProfile(Profile profile);
}
