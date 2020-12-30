package org.chromium.chrome.browser.preferences;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.location.LocationUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.p012ui.base.WindowAndroid;

/* compiled from: PG */
public class LocationSettings {

    /* renamed from: a */
    public static LocationSettings f2177a;

    /* renamed from: c */
    public static LocationSettings m2755c() {
        ThreadUtils.m1462c();
        if (f2177a == null) {
            f2177a = AppHooks.get().mo7993r();
        }
        return f2177a;
    }

    @CalledByNative
    public static boolean canPromptForAndroidLocationPermission(WebContents webContents) {
        WindowAndroid o0 = webContents.mo9782o0();
        if (o0 == null) {
            return false;
        }
        return o0.canRequestPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    @CalledByNative
    public static boolean canPromptToEnableSystemLocationSetting() {
        LocationUtils.f().a();
        return false;
    }

    @CalledByNative
    public static boolean hasAndroidLocationPermission() {
        return LocationUtils.f().c();
    }

    @CalledByNative
    public static boolean isSystemLocationSettingEnabled() {
        return LocationUtils.f().d();
    }

    public static native void nativeOnLocationSettingsDialogOutcome(long j, int i);

    @CalledByNative
    public static void promptToEnableSystemLocationSetting(int i, WebContents webContents, long j) {
        if (webContents.mo9782o0() == null) {
            nativeOnLocationSettingsDialogOutcome(j, 3);
        } else {
            LocationUtils.f().a(new a(j));
        }
    }

    /* renamed from: a */
    public boolean mo9039a() {
        return mo9040b() && LocationUtils.f().d();
    }

    /* renamed from: b */
    public boolean mo9040b() {
        return PrefServiceBridge.m2758o0().mo9154v();
    }
}
