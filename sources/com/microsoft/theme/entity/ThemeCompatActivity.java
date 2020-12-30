package com.microsoft.theme.entity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.WindowManager;
import com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivity;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import java.util.HashMap;

/* compiled from: PG */
public abstract class ThemeCompatActivity extends MAMAppCompatActivity implements zu0 {

    /* renamed from: a */
    public vw2 f1307a;

    /* renamed from: D */
    public int mo4506D() {
        return jx0.edge_window_background_color;
    }

    /* renamed from: E */
    public boolean mo4507E() {
        return true;
    }

    /* renamed from: F */
    public void mo4508F() {
        if (mo4507E()) {
            mo4509a(ThemeManager.f1300h.mo4505b());
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, android.app.Activity, android.support.v4.app.SupportActivity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: a */
    public void mo4509a(Theme theme) {
        if (!isDestroyed() && mo4507E()) {
            int D = mo4506D();
            if (D != 0) {
                getWindow().setBackgroundDrawableResource(du0.a(getResources(), D, theme));
            }
            int c = mo4511c(theme);
            if (c != 0) {
                getTheme().applyStyle(c, true);
                getWindow().getDecorView().getContext().getTheme().applyStyle(c, true);
            }
            vw2 vw2 = this.f1307a;
            if (vw2 != null) {
                vw2.b();
            }
            int b = mo4510b(theme);
            ON0.b(getWindow(), b);
            ON0.a(getWindow().getDecorView().getRootView(), !JE2.d(b));
        }
    }

    /* renamed from: b */
    public int mo4510b(Theme theme) {
        return du0.a(getResources(), jx0.default_status_bar_color);
    }

    /* renamed from: c */
    public int mo4511c(Theme theme) {
        int ordinal = theme.ordinal();
        if (ordinal == 1) {
            return xx0.LightThemeStyle;
        }
        if (ordinal != 2) {
            return 0;
        }
        return xx0.DarkThemeStyle;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onConfigurationChanged(Configuration configuration) {
        ThemeCompatActivity.super.onConfigurationChanged(configuration);
        if (!tq0.f) {
            WindowManager windowManager = (WindowManager) RN0.a.getSystemService("window");
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                tq0.a = displayMetrics.heightPixels;
                tq0.b = displayMetrics.widthPixels;
                tq0.e = displayMetrics.density;
            } else {
                VN0.c("MultiWindowTelemetry", "Cannot get screen size", new Object[0]);
                tq0.a = 0;
                tq0.b = 0;
                tq0.e = 1.0f;
            }
            tq0.c = tq0.a;
            tq0.d = tq0.b;
            tq0.f = true;
        }
        float f = tq0.e;
        int i = (int) (((float) configuration.screenHeightDp) * f);
        int i2 = (int) (((float) configuration.screenWidthDp) * f);
        if (i != tq0.c || i2 != tq0.d) {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            String str = configuration.orientation == 1 ? "portrait" : "landscape";
            HashMap hashMap = new HashMap();
            hashMap.put("device_height", String.valueOf(tq0.a));
            hashMap.put("device_width", String.valueOf(tq0.b));
            hashMap.put("screen_height", String.valueOf(displayMetrics2.heightPixels));
            hashMap.put("screen_width", String.valueOf(displayMetrics2.widthPixels));
            hashMap.put("window_height", String.valueOf(i));
            hashMap.put("window_width", String.valueOf(i2));
            hashMap.put("orientation", str);
            ss0.b("MultiWindow", hashMap, true, 0, (String) null);
            tq0.c = i;
            tq0.d = i2;
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, android.content.Context, zu0, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivity, android.app.Activity, android.support.v4.app.SupportActivity] */
    public void onMAMCreate(Bundle bundle) {
        if (mo4507E()) {
            k9.c.a(LayoutInflater.from(this), new au0(getDelegate()));
            ThemeManager.f1300h.mo4504a((zu0) this);
            int c = mo4511c(ThemeManager.f1300h.mo4505b());
            if (c != 0) {
                getTheme().applyStyle(c, true);
            }
            if (Build.VERSION.SDK_INT >= 27) {
                this.f1307a = new vw2(getWindow());
            }
        }
        ThemeCompatActivity.super.onMAMCreate(bundle);
    }

    public void onMAMDestroy() {
        ThemeCompatActivity.super.onMAMDestroy();
        vw2 vw2 = this.f1307a;
        if (vw2 != null) {
            vw2.a();
        }
    }

    public void onMAMResume() {
        ThemeCompatActivity.super.onMAMResume();
        mo4508F();
    }
}
