package org.chromium.chrome.browser.dependency_injection;

import android.content.Context;
import android.support.customtabs.trusted.TrustedWebActivityServiceConnectionManager;
import org.chromium.chrome.browser.WarmupManager;
import org.chromium.chrome.browser.init.ChromeBrowserInitializer;
import org.chromium.chrome.browser.night_mode.SystemNightModeMonitor;
import org.chromium.chrome.browser.preferences.ChromePreferenceManager;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* compiled from: PG */
public class ChromeAppModule {
    /* renamed from: a */
    public ChromeBrowserInitializer mo8482a() {
        return ChromeBrowserInitializer.m2488f();
    }

    /* renamed from: b */
    public Context mo8483b() {
        return RN0.a;
    }

    /* renamed from: c */
    public SystemNightModeMonitor mo8484c() {
        return SystemNightModeMonitor.m2671c();
    }

    /* renamed from: d */
    public WarmupManager mo8485d() {
        return WarmupManager.m1950e();
    }

    /* renamed from: e */
    public ChromePreferenceManager mo8486e() {
        return ChromePreferenceManager.m2734c();
    }

    /* renamed from: f */
    public Sc2 mo8487f() {
        return Sc2.b();
    }

    /* renamed from: g */
    public Xz1 mo8488g() {
        return WebappRegistry.m3296c().mo9592b();
    }

    /* renamed from: a */
    public TrustedWebActivityServiceConnectionManager mo8481a(Context context) {
        return new TrustedWebActivityServiceConnectionManager(context);
    }
}
