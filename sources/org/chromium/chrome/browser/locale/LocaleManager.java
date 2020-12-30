package org.chromium.chrome.browser.locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import com.citrix.loggersdk.BuildConfig;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ChromeFeatureList;
import org.chromium.chrome.browser.p010vr.VrModuleProvider;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.chrome.browser.snackbar.SnackbarManager;

/* compiled from: PG */
public class LocaleManager {

    /* renamed from: f */
    public static LocaleManager f2058f;

    /* renamed from: a */
    public boolean f2059a;

    /* renamed from: b */
    public boolean f2060b;

    /* renamed from: c */
    public WeakReference<SnackbarManager> f2061c = new WeakReference<>((Object) null);

    /* renamed from: d */
    public LocaleTemplateUrlLoader f2062d;

    /* renamed from: e */
    public SnackbarManager.SnackbarController f2063e = new a(this);

    /* JADX INFO: finally extract failed */
    public LocaleManager() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            int i = QN0.a.getInt("com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN", -1);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    @CalledByNative
    public static LocaleManager getInstance() {
        if (f2058f == null) {
            f2058f = AppHooks.get().mo7992q();
        }
        return f2058f;
    }

    /* renamed from: a */
    public void mo8819a(boolean z) {
        Eo.b(QN0.a, "LocaleManager_PREF_AUTO_SWITCH", z);
    }

    /* renamed from: b */
    public void mo8821b(Activity activity, Callback<Boolean> callback) {
        TemplateUrlServiceFactory.m2927a().mo9618a((Runnable) new b(this, activity, callback));
    }

    /* renamed from: c */
    public int mo8822c() {
        if (mo8829h() && !QN0.a.getBoolean("LocaleManager_PREF_PROMO_SHOWN", false)) {
            return 0;
        }
        return -1;
    }

    /* renamed from: d */
    public List mo8823d() {
        throw new IllegalStateException("Not applicable unless existing or new promos are required");
    }

    /* renamed from: e */
    public String mo8824e() {
        return "US";
    }

    /* renamed from: f */
    public boolean mo8825f() {
        return this.f2059a;
    }

    /* renamed from: g */
    public boolean mo8826g() {
        return QN0.a.getBoolean("LocaleManager_PREF_AUTO_SWITCH", false);
    }

    @CalledByNative
    public String getMailRUReferralId() {
        return BuildConfig.FLAVOR;
    }

    @CalledByNative
    public String getYandexReferralId() {
        return BuildConfig.FLAVOR;
    }

    /* renamed from: h */
    public boolean mo8829h() {
        return false;
    }

    /* renamed from: i */
    public void mo8830i() {
        SharedPreferences sharedPreferences = QN0.a;
        boolean z = sharedPreferences.getBoolean("LocaleManager_WAS_IN_SPECIAL_LOCALE", false);
        boolean h = mo8829h();
        if (z && !h) {
            mo8837p();
            mo8836o();
        } else if (h && !z) {
            mo8813a();
            mo8833l();
        } else if (h) {
            mo8813a();
        }
        Eo.b(sharedPreferences, "LocaleManager_WAS_IN_SPECIAL_LOCALE", h);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: j */
    public boolean mo8831j() {
        if (ChromeFeatureList.m1783a() && !ChromeFeatureList.m1784a("SearchEnginePromo.ExistingDevice")) {
            return false;
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            int i = QN0.a.getInt("com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN", -1);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            if (this.f2060b || i != -1) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    /* renamed from: k */
    public void mo8832k() {
    }

    /* renamed from: l */
    public void mo8833l() {
        if (mo8826g() && mo8829h()) {
            mo8820b().b();
            mo8815a((CharSequence) RN0.a.getString(wx0.using_sogou));
        }
    }

    /* renamed from: m */
    public void mo8834m() {
    }

    /* renamed from: n */
    public void mo8835n() {
    }

    /* renamed from: o */
    public void mo8836o() {
        if (!mo8829h()) {
            mo8820b().c();
        }
    }

    /* renamed from: p */
    public void mo8837p() {
        if (mo8826g() && !mo8829h()) {
            mo8820b().d();
            mo8815a((CharSequence) RN0.a.getString(wx0.using_google));
        }
    }

    /* renamed from: q */
    public void mo8838q() {
        mo8830i();
    }

    /* renamed from: r */
    public void mo8839r() {
    }

    @CalledByNative
    public void recordUserTypeMetrics() {
    }

    /* renamed from: b */
    public final LocaleTemplateUrlLoader mo8820b() {
        if (this.f2062d == null) {
            this.f2062d = new LocaleTemplateUrlLoader(mo8824e());
        }
        return this.f2062d;
    }

    /* renamed from: a */
    public void mo8813a() {
        if (mo8829h()) {
            mo8820b().a();
        }
    }

    /* renamed from: a */
    public final void mo8814a(Activity activity, Callback<Boolean> callback) {
        e eVar;
        c cVar = new c(this, callback);
        if (TemplateUrlServiceFactory.m2927a().mo9629d() || ON0.b(activity)) {
            cVar.onResult(true);
            return;
        }
        int c = mo8822c();
        if (c != -1) {
            if (c == 0) {
                eVar = new d(this, activity, cVar);
            } else if (c == 1 || c == 2) {
                eVar = new e(this, activity, c, cVar);
            } else {
                cVar.onResult(true);
                return;
            }
            if (ApplicationStatus.m1359a(activity) == 6) {
                cVar.onResult(false);
                return;
            }
            if (!VrModuleProvider.m3293c().a(activity, activity.getIntent())) {
                VrModuleProvider.m3290a().e();
                try {
                    ((jH2) eVar.call()).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                VrModuleProvider.m3290a().a(new f62(this, eVar), activity);
            }
            this.f2059a = true;
            return;
        }
        cVar.onResult(true);
    }

    /* renamed from: a */
    public final void mo8817a(Callable<jH2> callable) {
        try {
            callable.call().show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void mo8818a(SnackbarManager snackbarManager) {
        this.f2061c = new WeakReference<>(snackbarManager);
    }

    /* renamed from: a */
    public final void mo8815a(CharSequence charSequence) {
        SnackbarManager snackbarManager = (SnackbarManager) this.f2061c.get();
        if (snackbarManager != null) {
            Context context = RN0.a;
            rt2 a = rt2.a(charSequence, this.f2063e, 1, 14);
            a.k = 6000;
            a.d = context.getString(wx0.preferences);
            a.e = null;
            snackbarManager.a(a);
        }
    }

    /* renamed from: a */
    public void mo8816a(String str) {
        TemplateUrlServiceFactory.m2927a().mo9628d(str);
        Mx1.a(RN0.a, str);
        Eo.b(QN0.a, "com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN", 1);
    }
}
