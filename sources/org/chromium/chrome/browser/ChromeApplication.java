package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.microsoft.intune.mam.client.app.MAMApplication;
import com.microsoft.managedbehavior.CitrixManager;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.ruby.new_item_indicator.NewItemIndicatorManager;
import com.microsoft.theme.ThemeManager;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.dependency_injection.ChromeAppModule;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.edge_feedback.FeedbackSessionManager;
import org.chromium.chrome.browser.init.InvalidStartupDialog;
import org.chromium.chrome.browser.night_mode.SystemNightModeMonitor;
import org.chromium.chrome.browser.p010vr.VrModuleProvider;
import org.chromium.chrome.browser.partnercustomizations.HomepageManager;
import org.chromium.chrome.browser.preferences.ChromePreferenceManager;
import rM1;

/* compiled from: PG */
public class ChromeApplication extends MAMApplication {

    /* renamed from: c */
    public static final Object f1616c = new Object();

    /* renamed from: d */
    public static volatile jM1 f1617d;

    /* renamed from: e */
    public static long f1618e;

    /* renamed from: b */
    public boolean f1619b;

    /* renamed from: a */
    public static final void m1771a(int i) {
        if (i == 1) {
            DO0.k.b();
        } else if (i == 3) {
            DO0.k.a();
        }
    }

    /* renamed from: b */
    public static boolean m1773b(int i) {
        return (i >= 10 && i < 20) || i >= 60;
    }

    /* renamed from: d */
    public static jM1 m1774d() {
        Xp2 xp2;
        ChromeAppModule chromeAppModule = new ChromeAppModule();
        if (Og0.d()) {
            xp2 = new Xp2();
        } else {
            xp2 = new AppHooksModule();
        }
        rM1.a aVar = new rM1.a((qM1) null);
        aVar.a = chromeAppModule;
        aVar.b = xp2;
        if (aVar.a == null) {
            aVar.a = new ChromeAppModule();
        }
        if (aVar.b == null) {
            aVar.b = new AppHooksModule();
        }
        return new rM1(aVar, (qM1) null);
    }

    /* renamed from: e */
    public static jM1 m1775e() {
        if (f1617d == null) {
            synchronized (f1616c) {
                if (f1617d == null) {
                    f1617d = m1774d();
                }
            }
        }
        return f1617d;
    }

    /* renamed from: f */
    public static Boolean m1776f() {
        return Boolean.valueOf(ChromePreferenceManager.m2734c().mo9025a("command_line_on_non_rooted_enabled", false));
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMApplication, android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0105, code lost:
        if (r1 == false) goto L_0x0107;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void attachBaseContext(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = RN0.e()
            java.lang.String r1 = ":"
            boolean r0 = r0.contains(r1)
            r1 = 1
            r0 = r0 ^ r1
            r10.f1619b = r0
            boolean r0 = r10.f1619b
            java.lang.String r2 = "app_bind"
            if (r0 == 0) goto L_0x0021
            long r3 = android.os.SystemClock.uptimeMillis()
            org.chromium.chrome.browser.metrics.UmaUtils.f2070a = r3
            mt0 r0 = mt0.d
            java.lang.String r3 = "action_start"
            r0.a(r2, r3)
        L_0x0021:
            org.chromium.chrome.browser.ChromeApplication.super.attachBaseContext(r11)
            RN0.a = r10
            boolean r11 = r10.f1619b
            java.lang.String r0 = "ChromeApplication.attachBaseContext"
            if (r11 == 0) goto L_0x0177
            Ys0 r11 = Xs0.a
            r11.b()
            int r11 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            r4 = 0
            if (r11 < r3) goto L_0x0039
            goto L_0x008a
        L_0x0039:
            boolean r11 = RN0.f()
            if (r11 == 0) goto L_0x0041
            r11 = 0
            goto L_0x0074
        L_0x0041:
            java.lang.String r11 = RN0.e()
            android.content.pm.PackageManager r3 = r10.getPackageManager()
            java.lang.String r5 = r10.getPackageName()     // Catch:{ NameNotFoundException -> 0x0073 }
            r6 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r3 = Vc0.a(r3, r5, r6)     // Catch:{ NameNotFoundException -> 0x0073 }
            if (r3 == 0) goto L_0x0073
            android.os.Bundle r5 = r3.metaData     // Catch:{ NameNotFoundException -> 0x0073 }
            if (r5 != 0) goto L_0x005a
            goto L_0x0073
        L_0x005a:
            android.os.Bundle r3 = r3.metaData     // Catch:{ NameNotFoundException -> 0x0073 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0073 }
            r5.<init>()     // Catch:{ NameNotFoundException -> 0x0073 }
            r5.append(r11)     // Catch:{ NameNotFoundException -> 0x0073 }
            java.lang.String r11 = ".ignore_multidex"
            r5.append(r11)     // Catch:{ NameNotFoundException -> 0x0073 }
            java.lang.String r11 = r5.toString()     // Catch:{ NameNotFoundException -> 0x0073 }
            boolean r11 = r3.getBoolean(r11, r4)     // Catch:{ NameNotFoundException -> 0x0073 }
            r11 = r11 ^ r1
            goto L_0x0074
        L_0x0073:
            r11 = 1
        L_0x0074:
            java.lang.String r3 = "base_multidex"
            if (r11 != 0) goto L_0x0080
            java.lang.Object[] r11 = new java.lang.Object[r4]
            java.lang.String r5 = "Skipping multidex installation: not needed for process."
            VN0.b(r3, r5, r11)
            goto L_0x008a
        L_0x0080:
            q3.b(r10)
            java.lang.Object[] r11 = new java.lang.Object[r4]
            java.lang.String r5 = "Completed multidex installation."
            VN0.b(r3, r5, r11)
        L_0x008a:
            android.content.res.AssetManager r11 = RN0.c()
            if (r11 != 0) goto L_0x009c
            java.lang.Object[] r11 = new java.lang.Object[r4]
            java.lang.String r3 = "ChromiumApplication"
            java.lang.String r5 = "getResources() null, closing app."
            VN0.a(r3, r5, r11)
            java.lang.System.exit(r4)
        L_0x009c:
            java.lang.String r11 = "chrome"
            org.chromium.base.PathUtils.m1443a(r11)
            com.microsoft.ruby.util.RubyBuild r11 = com.microsoft.ruby.util.RubyBuild.getForCurrentBuild()
            com.microsoft.ruby.util.RubyBuild r3 = com.microsoft.ruby.util.RubyBuild.DAILY
            com.microsoft.ruby.util.RubyBuild r5 = com.microsoft.ruby.util.RubyBuild.DEVELOPMENT
            java.util.EnumSet r3 = java.util.EnumSet.of(r3, r5)
            boolean r11 = r11.checkSupport(r3)
            r3 = 0
            java.lang.String r5 = "/data/local"
            java.lang.String r6 = "chrome-command-line"
            if (r11 == 0) goto L_0x0114
            qO0 r11 = it1.a
            java.io.File r7 = new java.io.File
            java.lang.String r8 = "/data/local/tmp"
            r7.<init>(r8, r6)
            boolean r8 = r7.exists()
            if (r8 == 0) goto L_0x0107
            java.lang.Object r11 = r11.get()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00d4
            goto L_0x0105
        L_0x00d4:
            android.content.Context r11 = RN0.a
            android.content.ContentResolver r8 = r11.getContentResolver()
            java.lang.String r9 = "adb_enabled"
            int r8 = android.provider.Settings.Global.getInt(r8, r9, r4)
            if (r8 != r1) goto L_0x00e4
            r8 = 1
            goto L_0x00e5
        L_0x00e4:
            r8 = 0
        L_0x00e5:
            if (r8 == 0) goto L_0x00f2
            android.content.ContentResolver r8 = r11.getContentResolver()
            java.lang.String r9 = "debug_app"
            java.lang.String r8 = android.provider.Settings.Global.getString(r8, r9)
            goto L_0x00f3
        L_0x00f2:
            r8 = r3
        L_0x00f3:
            java.lang.String r11 = r11.getPackageName()
            boolean r11 = r11.equals(r8)
            if (r11 != 0) goto L_0x0105
            boolean r11 = org.chromium.base.BuildInfo.m1373b()
            if (r11 == 0) goto L_0x0104
            goto L_0x0105
        L_0x0104:
            r1 = 0
        L_0x0105:
            if (r1 != 0) goto L_0x010c
        L_0x0107:
            java.io.File r7 = new java.io.File
            r7.<init>(r5, r6)
        L_0x010c:
            java.lang.String r11 = r7.getPath()
            org.chromium.base.CommandLine.m1385d(r11)
            goto L_0x0120
        L_0x0114:
            java.io.File r11 = new java.io.File
            r11.<init>(r5, r6)
            java.lang.String r11 = r11.getPath()
            org.chromium.base.CommandLine.m1385d(r11)
        L_0x0120:
            org.chromium.chrome.browser.AppHooks r11 = org.chromium.chrome.browser.AppHooks.get()
            org.chromium.base.CommandLine.m1384c()
            r11.mo7964E()
            com.microsoft.ruby.util.RubyBuild r11 = com.microsoft.ruby.util.RubyBuild.getForCurrentBuild()
            com.microsoft.ruby.util.RubyBuild r1 = com.microsoft.ruby.util.RubyBuild.DEVELOPMENT
            java.util.EnumSet r1 = java.util.EnumSet.of(r1)
            boolean r11 = r11.checkSupport(r1)
            if (r11 == 0) goto L_0x014c
            org.chromium.base.EarlyTraceEvent.m1426f()
            boolean r11 = org.chromium.base.EarlyTraceEvent.m1425e()
            if (r11 == 0) goto L_0x014c
            android.os.Looper r11 = org.chromium.base.ThreadUtils.m1466f()
            org.chromium.base.TraceEvent$b r1 = org.chromium.base.TraceEvent.C0373d.f1490a
            r11.setMessageLogging(r1)
        L_0x014c:
            org.chromium.base.TraceEvent.m1472c(r0, r3)
            org.chromium.base.ApplicationStatus.m1362a((android.app.Application) r10)
            DJ1 r11 = new DJ1
            r11.<init>()
            int r1 = org.chromium.base.ApplicationStatus.getStateForApplication()
            r11.onApplicationStateChange(r1)
            org.chromium.base.ApplicationStatus.m1365a((org.chromium.base.ApplicationStatus.ApplicationStateListener) r11)
            gs1 r11 = gs1.c()
            r11.e(r10)
            org.chromium.base.ApplicationStatus$ApplicationStateListener r11 = jt1.a
            org.chromium.base.ApplicationStatus.m1365a((org.chromium.base.ApplicationStatus.ApplicationStateListener) r11)
            tg0.b()
            jL2 r11 = kL2.a
            kL2 r11 = (kL2) r11
            r11.b()
        L_0x0177:
            jL2 r11 = kL2.a
            kL2 r11 = (kL2) r11
            r11.c()
            java.lang.String r11 = ""
            org.chromium.base.BuildInfo.f1402n = r11
            boolean r11 = RN0.f()
            if (r11 != 0) goto L_0x0198
            boolean r11 = org.chromium.chrome.browser.crash.PureJavaExceptionHandler.f1712c
            if (r11 != 0) goto L_0x0198
            org.chromium.chrome.browser.crash.PureJavaExceptionHandler r11 = new org.chromium.chrome.browser.crash.PureJavaExceptionHandler
            java.lang.Thread$UncaughtExceptionHandler r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler()
            r11.<init>(r1)
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r11)
        L_0x0198:
            AP0.g()
            java.lang.ClassLoader r11 = r10.getClassLoader()
            org.chromium.base.JNIUtils.f1441b = r11
            java.lang.String[] r11 = zu1.a
            java.lang.String[] r1 = zu1.b
            org.chromium.p012ui.base.ResourceBundle.f2606a = r11
            org.chromium.p012ui.base.ResourceBundle.f2607b = r1
            boolean r11 = r10.f1619b
            if (r11 == 0) goto L_0x01b7
            org.chromium.base.TraceEvent.m1475z(r0)
            mt0 r11 = mt0.d
            java.lang.String r0 = "action_end"
            r11.a(r2, r0)
        L_0x01b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeApplication.attachBaseContext(android.content.Context):void");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    /* renamed from: b */
    public final /* synthetic */ void mo8184b() {
        Process.setThreadPriority(10);
        Nr2.a(this);
        Lr2.c().b();
        Jq0.c();
        NewItemIndicatorManager.b().a();
        Tl0.g();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    /* renamed from: c */
    public final /* synthetic */ void mo8185c() {
        SharedPreferences sharedPreferences = QN0.a;
        getSharedPreferences("afd_feature_flags", 0);
        getSharedPreferences("feature_flags_manual_overrides", 0);
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = ChromeApplication.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMApplication, org.chromium.chrome.browser.ChromeApplication] */
    public AssetManager getAssets() {
        return !gs1.d() ? ChromeApplication.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMApplication, org.chromium.chrome.browser.ChromeApplication] */
    public Resources getResources() {
        return !gs1.d() ? ChromeApplication.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMApplication, org.chromium.chrome.browser.ChromeApplication] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? ChromeApplication.super.getTheme() : gs1.i(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    public void onConfigurationChanged(Configuration configuration) {
        ChromeApplication.super.onConfigurationChanged(configuration);
        if (!RN0.e().contains(":")) {
            SystemNightModeMonitor.m2671c().mo8962b();
            tJ2.d();
            if (TextUtils.equals(HomepageManager.f2129p, Mx1.d())) {
                Mx1.c(Mx1.e());
            }
            Mm2.d().a(configuration.uiMode);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    public void onMAMCreate() {
        if (this.f1619b) {
            mt0.d.a("app_create", "action_start");
        }
        f1618e = System.currentTimeMillis();
        wK2.a(this);
        DO0.k.d();
        if (this.f1619b) {
            MAMEdgeManager.m1311i();
            zq0.b(this);
            wq0.b(this);
            Va0.a(this);
            ss0.g();
            DualIdentityManager.m2212f();
            Ds1.a().a(this);
            Mx1.a(this);
            ThemeManager.m1319c().mo4501a((Zt0) Mm2.d());
            FeedbackSessionManager.m2268d();
            Eu0.b().a();
            CitrixManager.m1277c().mo4431a((Context) this);
            YT1.c();
            Us0.d.execute(new gt1(this));
            Us0.d.execute(new ht1(this));
            try {
                Og0.f = new Og0(this);
            } catch (Throwable th) {
                VN0.c("DualScreenUtils", "can't init DualScreenUtils properly, fallback to normal device", new Object[]{th});
            }
            jq0.c();
        }
        if (this.f1619b) {
            mt0.d.a("app_create", "action_end");
            aJ1.i().g();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    @MainDex
    public void onTrimMemory(int i) {
        TN0 tn0;
        ChromeApplication.super.onTrimMemory(i);
        if (m1773b(i) && (tn0 = uu1.a) != null) {
            tn0.a();
        }
        if (CustomTabsConnection.f1746o != null) {
            if (m1773b(i)) {
                CustomTabsConnection.m2111l().f1749c.mo8352a();
            }
            if (CustomTabsConnection.m2111l().f1757k != null) {
                CustomTabsConnection.m2111l().f1757k.mo8477a(i);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMApplication, org.chromium.chrome.browser.ChromeApplication] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            ChromeApplication.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    public void startActivity(Intent intent) {
        startActivity(intent, (Bundle) null);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.Application, org.chromium.chrome.browser.ChromeApplication] */
    public void startActivity(Intent intent, Bundle bundle) {
        VrModuleProvider.m3290a().b();
        ChromeApplication.super.startActivity(intent, bundle);
    }

    /* renamed from: a */
    public static void m1772a(ProcessInitException processInitException) {
        Activity a = ApplicationStatus.m1360a();
        if (ApplicationStatus.m1359a(a) != 6) {
            InvalidStartupDialog.m2501a(a, processInitException.getErrorCode());
        }
    }
}
