package org.chromium.chrome.browser.edge_feedback;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.ViewGroup;
import com.microsoft.bing.usbsdk.api.BingClientManager;
import com.microsoft.ruby.util.RubyBuild;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;

/* compiled from: PG */
public abstract class FeedbackSessionManager {

    /* renamed from: a */
    public static boolean f1779a;

    /* renamed from: b */
    public static boolean f1780b;

    /* renamed from: c */
    public static boolean f1781c;

    /* renamed from: d */
    public static boolean f1782d;

    /* renamed from: e */
    public static boolean f1783e;

    /* renamed from: a */
    public static void m2261a(ActivationPoint activationPoint) {
        Log.i("FeedbackSessionManager", "setActivation() called with: activation = [" + activationPoint + "]");
        QN0.a.edit().putInt("FeedbackSessionManager.activation_key", activationPoint.ordinal()).apply();
    }

    /* renamed from: b */
    public static void m2264b(boolean z) {
        Log.i("FeedbackSessionManager", "onOverviewStateChanged() called with: visible = [" + z + "]");
        f1780b = z;
        if (!f1780b) {
            m2260a();
        }
    }

    /* renamed from: c */
    public static long m2266c() {
        long j = QN0.a.getLong("FeedbackSessionManager.LastShowTime", 0);
        Log.i("FeedbackSessionManager", "getLastShowTime: " + j);
        return j;
    }

    /* renamed from: d */
    public static void m2268d() {
        BingClientManager.getInstance().registerGlobalOpenBrowserCallBack(new PT1());
    }

    /* renamed from: e */
    public static boolean m2269e() {
        return System.currentTimeMillis() - YT1.a() > TimeUnit.DAYS.toMillis(90);
    }

    /* renamed from: f */
    public static void m2270f() {
        Log.i("FeedbackSessionManager", "markFavoritesFlowEnd: ");
        f1783e = false;
    }

    /* renamed from: g */
    public static void m2271g() {
        RN0.a().edit().putLong("FeedbackSessionManager.LastShowTime", System.currentTimeMillis()).apply();
    }

    /* renamed from: c */
    public static void m2267c(boolean z) {
        Log.i("FeedbackSessionManager", "onSnackbarStateChanged() called with: visible = [" + z + "]");
        f1781c = z;
        if (!f1781c) {
            m2260a();
        }
    }

    /* renamed from: b */
    public static boolean m2265b() {
        StringBuilder a = Eo.a("conditionSatisfy: sChomeTabbedActivityFocus: ");
        a.append(f1782d);
        a.append(" sHubVisible: ");
        a.append(f1779a);
        a.append(" sOverviewVisible: ");
        a.append(f1780b);
        a.append(" sSnackbarVisible: ");
        a.append(f1781c);
        a.append(" sFavoritesInprogress: ");
        a.append(f1783e);
        Log.i("FeedbackSessionManager", a.toString());
        if (!f1782d || f1779a || f1780b || f1781c || f1783e || !m2263a(RN0.d())) {
            return false;
        }
        if ((System.currentTimeMillis() - m2266c() >= TimeUnit.DAYS.toMillis(90)) && m2269e() && !MicrosoftSigninManager.m2574G().mo8866A()) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static void m2262a(boolean z) {
        Log.i("FeedbackSessionManager", "onHubStateChanged() called with: visible = [" + z + "]");
        f1779a = z;
        if (!f1779a) {
            m2260a();
        }
    }

    /* renamed from: a */
    public static void m2260a() {
        Log.i("FeedbackSessionManager", "checkTriggering: ");
        if (QN0.a.contains("FeedbackSessionManager.activation_key")) {
            ActivationPoint valueOf = ActivationPoint.valueOf(QN0.a.getInt("FeedbackSessionManager.activation_key", -1));
            if (!m2265b()) {
                return;
            }
            if (valueOf == ActivationPoint.ADD_BOOKMARK || valueOf == ActivationPoint.CAMERA_SEARCH || valueOf == ActivationPoint.ACCOUNT) {
                m2271g();
                Eo.a(QN0.a, "FeedbackSessionManager.activation_key");
                new OT1().b((ViewGroup) ((ChromeActivity) ChromeActivity.f1556M4.get()).findViewById(16908290));
            }
        }
    }

    /* renamed from: a */
    public static boolean m2263a(Context context) {
        PackageInfo packageInfo;
        long j;
        long j2;
        try {
            packageInfo = Vc0.b(context.getPackageManager(), context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            j = 0;
        } else {
            j = packageInfo.firstInstallTime;
        }
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.PRODUCT, RubyBuild.BETA, RubyBuild.ALPHA, RubyBuild.SELFHOST))) {
            j2 = TimeUnit.DAYS.toMillis(14);
        } else {
            j2 = TimeUnit.SECONDS.toMillis(120);
        }
        if (currentTimeMillis >= j2) {
            return true;
        }
        return false;
    }
}
