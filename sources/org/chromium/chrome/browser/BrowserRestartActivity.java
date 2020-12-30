package org.chromium.chrome.browser;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Process;
import com.microsoft.intune.mam.client.app.MAMActivity;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class BrowserRestartActivity extends MAMActivity {
    static {
        Class<BrowserRestartActivity> cls = BrowserRestartActivity.class;
    }

    /* renamed from: a */
    public static Intent m1626a(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), BrowserRestartActivity.class.getName());
        intent.setFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.BrowserRestartActivity.main_pid", Process.myPid());
        intent.putExtra("org.chromium.chrome.browser.BrowserRestartActivity.restart", z);
        return intent;
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = BrowserRestartActivity.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity, org.chromium.chrome.browser.BrowserRestartActivity] */
    public AssetManager getAssets() {
        return !gs1.d() ? BrowserRestartActivity.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity, org.chromium.chrome.browser.BrowserRestartActivity] */
    public Resources getResources() {
        return !gs1.d() ? BrowserRestartActivity.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity, org.chromium.chrome.browser.BrowserRestartActivity] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? BrowserRestartActivity.super.getTheme() : gs1.i(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.microsoft.intune.mam.client.app.MAMActivity, android.app.Activity, org.chromium.chrome.browser.BrowserRestartActivity] */
    public void onMAMCreate(Bundle bundle) {
        BrowserRestartActivity.super.onMAMCreate(bundle);
        Intent intent = getIntent();
        Process.killProcess(PE2.a(intent, "org.chromium.chrome.browser.BrowserRestartActivity.main_pid", -1));
        if (PE2.a(intent, "org.chromium.chrome.browser.BrowserRestartActivity.restart", false)) {
            Context context = RN0.a;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setPackage(context.getPackageName());
            intent2.setFlags(268435456);
            context.startActivity(intent2);
        }
        finish();
        Process.killProcess(Process.myPid());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMActivity, org.chromium.chrome.browser.BrowserRestartActivity] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            BrowserRestartActivity.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
