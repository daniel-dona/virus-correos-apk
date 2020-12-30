package com.microsoft.edge_trust.tracking_prevention;

import com.microsoft.mmx.experiment.EdgeNativeFeatureManager;
import com.microsoft.mmx.experiment.FeatureManager$Feature;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class TrackingPreventionBridge {
    /* renamed from: a */
    public static boolean m1268a() {
        return EdgeNativeFeatureManager.a(FeatureManager$Feature.TRACKING_PREVENTION_ROLLOUT) && PrefServiceBridge.m2758o0().mo9073a(15);
    }

    /* renamed from: b */
    public static boolean m1269b() {
        return EdgeNativeFeatureManager.a(FeatureManager$Feature.TRACKING_PREVENTION_ROLLOUT);
    }

    public static native boolean nativeEnabledForWebContents(WebContents webContents);
}
