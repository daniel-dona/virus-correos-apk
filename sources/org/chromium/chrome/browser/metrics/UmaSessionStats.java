package org.chromium.chrome.browser.metrics;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.util.UrlUtilities;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class UmaSessionStats {

    /* renamed from: f */
    public static long f2064f;

    /* renamed from: a */
    public TabModelSelector f2065a;

    /* renamed from: b */
    public kx2 f2066b;

    /* renamed from: c */
    public final Context f2067c;

    /* renamed from: d */
    public ComponentCallbacks f2068d;

    /* renamed from: e */
    public boolean f2069e;

    public UmaSessionStats(Context context) {
        this.f2067c = context;
    }

    /* renamed from: b */
    public static boolean m2535b() {
        return OP2.a(1).mo9662a();
    }

    /* renamed from: c */
    public static void m2536c() {
        so2 l = so2.l();
        l.k();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) l.a.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = false;
        if ((activeNetworkInfo != null && activeNetworkInfo.isConnected()) && (l.g() || l.c())) {
            z = true;
        }
        nativeUpdateMetricsServiceState(z);
    }

    public static native void nativeChangeMetricsReportingConsent(boolean z);

    public static native long nativeInit();

    public static native void nativeInitMetricsAndCrashReportingForTesting();

    public static native void nativeRecordPageLoaded(boolean z);

    public static native void nativeRecordPageLoadedWithKeyboard();

    public static native void nativeRecordTabCountPerLoad(int i);

    public static native void nativeRegisterExternalExperiment(String str, int[] iArr);

    public static native void nativeRegisterSyntheticFieldTrial(String str, String str2);

    private native void nativeUmaEndSession(long j);

    private native void nativeUmaResumeSession(long j);

    public static native void nativeUnsetMetricsAndCrashReportingForTesting();

    public static native void nativeUpdateMetricsAndCrashReportingForTesting(boolean z);

    public static native void nativeUpdateMetricsServiceState(boolean z);

    /* renamed from: a */
    public final void mo8842a(Tab tab) {
        WebContents K = tab.mo9302K();
        int i = 0;
        nativeRecordPageLoaded(K != null && K.mo9742F().e());
        if (this.f2069e) {
            nativeRecordPageLoadedWithKeyboard();
        }
        String url = tab.getUrl();
        if (!TextUtils.isEmpty(url) && UrlUtilities.m3285c(url)) {
            PostTask.m1566a(VP0.i, new g82(url), 0);
        }
        TabModelSelector tabModelSelector = this.f2065a;
        if (tabModelSelector != null) {
            TabModel a = tabModelSelector.mo9443a(false);
            if (a != null) {
                i = a.getCount();
            }
            nativeRecordTabCountPerLoad(i);
        }
    }

    /* renamed from: a */
    public void mo8843a(TabModelSelector tabModelSelector) {
        if (f2064f == 0) {
            f2064f = nativeInit();
        }
        this.f2065a = tabModelSelector;
        if (this.f2065a != null) {
            this.f2068d = new UmaSessionStats.a(this);
            this.f2067c.registerComponentCallbacks(this.f2068d);
            boolean z = true;
            if (this.f2067c.getResources().getConfiguration().keyboard == 1) {
                z = false;
            }
            this.f2069e = z;
            this.f2066b = new UmaSessionStats.b(this, this.f2065a);
        }
        nativeUmaResumeSession(f2064f);
        so2 l = so2.l();
        l.i();
        l.b.edit().putBoolean("in_metrics_sample", UmaUtils.nativeIsClientInMetricsReportingSample()).apply();
        l.k();
        m2536c();
        mu1.b();
    }

    /* renamed from: a */
    public void mo8841a() {
        if (this.f2065a != null) {
            this.f2067c.unregisterComponentCallbacks(this.f2068d);
            this.f2066b.destroy();
            this.f2065a = null;
        }
        nativeUmaEndSession(f2064f);
    }

    /* renamed from: a */
    public static void m2534a(boolean z) {
        so2.l().c(z);
        nativeChangeMetricsReportingConsent(z);
        m2536c();
    }
}
