package org.chromium.chrome.browser;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.theme.entity.ThemeCompatActivity;
import org.chromium.chrome.browser.night_mode.NightModeStateProvider;
import org.chromium.chrome.browser.night_mode.NightModeStateProvider$Observer;

/* compiled from: PG */
public class ChromeBaseAppCompatActivity extends ThemeCompatActivity implements NightModeStateProvider$Observer {

    /* renamed from: b */
    public NightModeStateProvider f1620b;

    /* renamed from: c */
    public int f1621c;

    /* renamed from: G */
    public NightModeStateProvider mo8196G() {
        return gc2.a();
    }

    /* renamed from: H */
    public final NightModeStateProvider mo8197H() {
        return this.f1620b;
    }

    /* renamed from: I */
    public void mo8198I() {
    }

    /* renamed from: a */
    public boolean mo8199a(Context context, Configuration configuration) {
        NightModeStateProvider H = mo8197H();
        if (!H.a()) {
            return false;
        }
        configuration.uiMode = (H.e() ? 32 : 16) | (configuration.uiMode & -49);
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeBaseAppCompatActivity, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase, android.app.Activity] */
    public void attachBaseContext(Context context) {
        ChromeBaseAppCompatActivity.super.attachBaseContext(context);
        this.f1620b = mo8196G();
        Configuration configuration = new Configuration();
        configuration.fontScale = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        if (mo8199a(context, configuration)) {
            applyOverrideConfiguration(configuration);
        }
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        return RN0.a.getSharedPreferences(str, i);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = this.f1621c;
        if ((mo8197H().e() ? 32 : 16) != (configuration.uiMode & 48) && i != 0) {
            setTheme(i);
            if (Build.VERSION.SDK_INT >= 23) {
                getTheme().applyStyle(i, true);
            }
        }
    }

    public void onMAMCreate(Bundle bundle) {
        mo8198I();
        this.f1620b.b(this);
        super.onMAMCreate(bundle);
    }

    public void onMAMDestroy() {
        this.f1620b.a(this);
        super.onMAMDestroy();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeBaseAppCompatActivity, android.app.Activity] */
    public void onNightModeStateChanged() {
        if (!isFinishing()) {
            recreate();
        }
    }

    public void setTheme(int i) {
        ChromeBaseAppCompatActivity.super.setTheme(i);
        this.f1621c = i;
    }
}
