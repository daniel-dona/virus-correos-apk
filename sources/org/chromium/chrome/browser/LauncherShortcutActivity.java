package org.chromium.chrome.browser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import com.microsoft.intune.mam.client.app.MAMActivity;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;

/* compiled from: PG */
public class LauncherShortcutActivity extends MAMActivity {
    @TargetApi(25)
    /* renamed from: a */
    public static void m1943a(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            Intent intent = new Intent("chromium.shortcut.action.OPEN_NEW_TAB");
            intent.setPackage(context.getPackageName());
            intent.setClass(context, LauncherShortcutActivity.class);
            ((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{new ShortcutInfo.Builder(context, "new-tab-shortcut").setShortLabel(context.getResources().getString(wx0.menu_new_tab)).setIcon(Icon.createWithResource(context, lx0.shortcut_newtab)).setIntent(intent).build()}));
        }
    }

    /* renamed from: b */
    public static void m1944b(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            SharedPreferences sharedPreferences = QN0.a;
            if (PrefServiceBridge.m2758o0().mo9051K()) {
                Intent intent = new Intent("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB");
                intent.setPackage(context.getPackageName());
                intent.setClass(context, LauncherShortcutActivity.class);
                if (((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{new ShortcutInfo.Builder(context, "dynamic-new-incognito-tab-shortcut").setShortLabel(context.getResources().getString(wx0.accessibility_tabstrip_incognito_identifier)).setLongLabel(context.getResources().getString(wx0.menu_new_incognito_tab)).setIcon(Icon.createWithResource(context, lx0.btn_private_ruby)).setIntent(intent).build()}))) {
                    Eo.b(sharedPreferences, "incognito-shortcut-added", true);
                }
            } else if (sharedPreferences.getBoolean("incognito-shortcut-added", false) && !PrefServiceBridge.m2758o0().mo9051K()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("dynamic-new-incognito-tab-shortcut");
                ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
                shortcutManager.disableShortcuts(arrayList);
                shortcutManager.removeDynamicShortcuts(arrayList);
                sharedPreferences.edit().putBoolean("incognito-shortcut-added", false).apply();
            }
        }
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = LauncherShortcutActivity.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, org.chromium.chrome.browser.LauncherShortcutActivity, com.microsoft.intune.mam.client.app.MAMActivity] */
    public AssetManager getAssets() {
        return !gs1.d() ? LauncherShortcutActivity.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, org.chromium.chrome.browser.LauncherShortcutActivity, com.microsoft.intune.mam.client.app.MAMActivity] */
    public Resources getResources() {
        return !gs1.d() ? LauncherShortcutActivity.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, org.chromium.chrome.browser.LauncherShortcutActivity, com.microsoft.intune.mam.client.app.MAMActivity] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? LauncherShortcutActivity.super.getTheme() : gs1.i(this);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, org.chromium.chrome.browser.LauncherShortcutActivity, com.microsoft.intune.mam.client.app.MAMActivity, android.app.Activity] */
    public void onMAMCreate(Bundle bundle) {
        LauncherShortcutActivity.super.onMAMCreate(bundle);
        String action = getIntent().getAction();
        if (action.equals("chromium.shortcut.action.OPEN_NEW_TAB") || action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB")) {
            Intent a = IntentHandler.m1905a((Context) this, action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB"));
            a.putExtra("com.android.chrome.invoked_from_shortcut", true);
            StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            try {
                startActivity(a);
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                finish();
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        } else {
            finish();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, org.chromium.chrome.browser.LauncherShortcutActivity, com.microsoft.intune.mam.client.app.MAMActivity] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            LauncherShortcutActivity.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
