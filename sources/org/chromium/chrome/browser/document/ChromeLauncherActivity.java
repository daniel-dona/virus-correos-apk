package org.chromium.chrome.browser.document;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import com.microsoft.intune.mam.client.app.MAMActivity;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.p010vr.VrModuleProvider;

/* compiled from: PG */
public class ChromeLauncherActivity extends MAMActivity {
    static {
        Class<ChromeLauncherActivity> cls = ChromeLauncherActivity.class;
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = ChromeLauncherActivity.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.document.ChromeLauncherActivity, android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity] */
    public AssetManager getAssets() {
        return !gs1.d() ? ChromeLauncherActivity.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.document.ChromeLauncherActivity, android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity] */
    public Resources getResources() {
        return !gs1.d() ? ChromeLauncherActivity.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.document.ChromeLauncherActivity, android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? ChromeLauncherActivity.super.getTheme() : gs1.i(this);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.document.ChromeLauncherActivity, com.microsoft.intune.mam.client.app.MAMActivity, android.app.Activity] */
    public void onMAMCreate(Bundle bundle) {
        mt0.d.a("dispatch_create", "action_start");
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        TraceEvent.m1472c("ChromeLauncherActivity.onCreate", (String) null);
        try {
            ChromeLauncherActivity.super.onMAMCreate(bundle);
            if (VrModuleProvider.m3293c().a(getIntent())) {
                VrModuleProvider.m3290a().a(this, true);
            }
            int a = yu1.a(this, getIntent());
            if (a == 1) {
                finish();
            } else if (a != 2) {
                finish();
            } else {
                ON0.a(this);
            }
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            TraceEvent.m1475z("ChromeLauncherActivity.onCreate");
            mt0.d.a("dispatch_create", "action_end");
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            TraceEvent.m1475z("ChromeLauncherActivity.onCreate");
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.document.ChromeLauncherActivity, android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            ChromeLauncherActivity.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
