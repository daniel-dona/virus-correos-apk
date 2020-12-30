package org.chromium.chrome.browser.customtabs;

import FO0;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.customtabs.CustomTabsSessionToken;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.Oauth2;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;
import org.chromium.base.CommandLine;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TimeUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ChromeApplication;
import org.chromium.chrome.browser.ChromeFeatureList;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.WarmupManager;
import org.chromium.chrome.browser.customtabs.ClientManager;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.customtabs.dynamicmodule.ModuleLoader;
import org.chromium.chrome.browser.init.ChromeBrowserInitializer;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class CustomTabsConnection {

    /* renamed from: l */
    public static final Set<String> f1743l = new HashSet(Arrays.asList(new String[]{"/bg_non_interactive", "/apps/bg_non_interactive", "/background"}));

    /* renamed from: m */
    public static final String[] f1744m = {"No request", "Success", "Chrome not initialized", "Not authorized", "Invalid URL", "Invalid referrer", "Invalid referrer for session"};

    /* renamed from: n */
    public static final FO0.c f1745n = new FO0.c("CustomTabs.ParallelRequestStatusOnStart", 7);

    /* renamed from: o */
    public static CustomTabsConnection f1746o;

    /* renamed from: a */
    public final fL1 f1747a = new fL1();

    /* renamed from: b */
    public final Hz1 f1748b = ChromeApplication.m1775e().j();

    /* renamed from: c */
    public final ClientManager f1749c = new ClientManager();

    /* renamed from: d */
    public final boolean f1750d = CommandLine.m1384c().mo7859c("custom-tabs-log-service-requests");

    /* renamed from: e */
    public final AtomicBoolean f1751e = new AtomicBoolean();

    /* renamed from: f */
    public final AtomicBoolean f1752f = new AtomicBoolean();

    /* renamed from: g */
    public Callback<CustomTabsSessionToken> f1753g;

    /* renamed from: h */
    public long f1754h;

    /* renamed from: i */
    public boolean f1755i;

    /* renamed from: j */
    public volatile U12 f1756j;

    /* renamed from: k */
    public ModuleLoader f1757k;

    /* renamed from: org.chromium.chrome.browser.customtabs.CustomTabsConnection$a */
    /* compiled from: PG */
    public class C0385a implements ClientManager.DisconnectCallback {
        public C0385a() {
        }

        public void run(CustomTabsSessionToken customTabsSessionToken) {
            CustomTabsConnection.this.mo8415a(customTabsSessionToken);
            Callback<CustomTabsSessionToken> callback = CustomTabsConnection.this.f1753g;
            if (callback != null) {
                callback.onResult(customTabsSessionToken);
            }
            PK1 g = ChromeApplication.m1775e().g();
            if (g.c) {
                Bitmap bitmap = (Bitmap) ((mA1) g.b.get()).a.remove(customTabsSessionToken);
            }
        }
    }

    /* renamed from: l */
    public static CustomTabsConnection m2111l() {
        if (f1746o == null) {
            f1746o = AppHooks.get().mo7979e();
        }
        return f1746o;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r0 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        m2106a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        throw r2;
     */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void m2112m() {
        /*
            r0 = 1
            org.chromium.content_public.browser.BrowserStartupController r0 = OP2.a(r0)
            boolean r0 = r0.mo9662a()
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            java.lang.String r0 = "CreateSpareWebContents"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            r1 = 0
            m2110k()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001b
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x001b:
            return
        L_0x001c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001e }
        L_0x001e:
            r2 = move-exception
            if (r0 == 0) goto L_0x0024
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0024:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.m2112m():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        m2106a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r0 != null) goto L_0x001f;
     */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void m2113n() {
        /*
            java.lang.String r0 = "InitializeViewHierarchy"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.chrome.browser.WarmupManager r1 = org.chromium.chrome.browser.WarmupManager.m1950e()     // Catch:{ all -> 0x001a }
            android.content.Context r2 = RN0.a     // Catch:{ all -> 0x001a }
            int r3 = rx0.custom_tabs_control_container     // Catch:{ all -> 0x001a }
            int r4 = rx0.edge_custom_tabs_toolbar     // Catch:{ all -> 0x001a }
            r1.mo8302a((android.content.Context) r2, (int) r3, (int) r4)     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0019
            r1 = 0
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0019:
            return
        L_0x001a:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001c }
        L_0x001c:
            r2 = move-exception
            if (r0 == 0) goto L_0x0022
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0022:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.m2113n():void");
    }

    public static native void nativeCreateAndStartDetachedResourceRequest(Profile profile, CustomTabsSessionToken customTabsSessionToken, String str, String str2, int i, int i2);

    @CalledByNative
    public static void notifyClientOfDetachedRequestCompletion(CustomTabsSessionToken customTabsSessionToken, String str, int i) {
        if (ChromeFeatureList.m1784a("CCTReportParallelRequestStatus")) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("url", Uri.parse(str));
            bundle.putInt("net_error", i);
            CustomTabsConnection l = m2111l();
            l.mo8434a(customTabsSessionToken, "onDetachedRequestCompleted", bundle);
            if (l.f1750d) {
                l.mo8444b("onDetachedRequestCompleted", (Object) m2108b(bundle).toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        m2106a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r0 != null) goto L_0x001c;
     */
    /* renamed from: o */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void m2114o() {
        /*
            java.lang.String r0 = "WarmupInternalFinishInitialization"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.chrome.browser.profiles.Profile r1 = org.chromium.chrome.browser.profiles.Profile.m2911j()     // Catch:{ all -> 0x0017 }
            org.chromium.chrome.browser.WarmupManager.m1948a((org.chromium.chrome.browser.profiles.Profile) r1)     // Catch:{ all -> 0x0017 }
            oL1.c()     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0016
            r1 = 0
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0016:
            return
        L_0x0017:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r2 = move-exception
            if (r0 == 0) goto L_0x001f
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x001f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.m2114o():void");
    }

    /* renamed from: a */
    public Bundle mo8410a() {
        return null;
    }

    /* renamed from: a */
    public void mo8422a(String str, Object obj) {
        if (this.f1750d) {
            VN0.c("ChromeConnection", "%s = %b, Calling UID = %d", new Object[]{str, obj, Integer.valueOf(Binder.getCallingUid())});
        }
    }

    /* renamed from: b */
    public void mo8444b(String str, Object obj) {
        if (this.f1750d) {
            VN0.c("ChromeConnection", "%s args = %s", new Object[]{str, obj});
        }
    }

    /* renamed from: c */
    public String mo8450c() {
        return null;
    }

    /* renamed from: c */
    public boolean mo8452c(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        return this.f1749c.mo8363a(customTabsSessionToken, intent);
    }

    /* renamed from: d */
    public boolean mo8455d() {
        return this.f1752f.get();
    }

    /* renamed from: e */
    public int mo8456e(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        ThreadUtils.m1462c();
        if (!this.f1749c.mo8372f(customTabsSessionToken)) {
            return 0;
        }
        ArrayList<Uri> parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.support.customtabs.RESOURCE_PREFETCH_URL_LIST");
        Uri uri = (Uri) intent.getParcelableExtra("android.support.customtabs.PARALLEL_REQUEST_REFERRER");
        int intExtra = intent.getIntExtra("android.support.customtabs.PARALLEL_REQUEST_REFERRER_POLICY", 1);
        if (parcelableArrayListExtra == null || uri == null) {
            return 0;
        }
        if (intExtra < 0 || intExtra > 8) {
            intExtra = 1;
        }
        if (!this.f1749c.mo8364a(customTabsSessionToken, new yz1(uri))) {
            return 0;
        }
        String uri2 = uri.toString();
        int i = 0;
        for (Uri uri3 : parcelableArrayListExtra) {
            String uri4 = uri3.toString();
            if (!uri4.isEmpty() && mo8426a(uri3)) {
                nativeCreateAndStartDetachedResourceRequest(Profile.m2911j(), (CustomTabsSessionToken) null, uri4, uri2, intExtra, 1);
                i++;
                if (this.f1750d) {
                    VN0.c("ChromeConnection", "startResourcePrefetch(%s, %s, %d)", new Object[]{uri4, uri2, Integer.valueOf(intExtra)});
                }
            }
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        m2106a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void mo8461f() {
        /*
            r3 = this;
            java.lang.String r0 = "CustomTabsConnection.initializeBrowser()"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            android.content.Context r1 = RN0.a     // Catch:{ all -> 0x001f }
            m2104a((android.content.Context) r1)     // Catch:{ all -> 0x001f }
            org.chromium.chrome.browser.init.ChromeBrowserInitializer r1 = org.chromium.chrome.browser.init.ChromeBrowserInitializer.m2488f()     // Catch:{ all -> 0x001f }
            r1.mo8804d()     // Catch:{ all -> 0x001f }
            java.util.concurrent.atomic.AtomicBoolean r1 = r3.f1752f     // Catch:{ all -> 0x001f }
            r2 = 1
            r1.set(r2)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001e
            r1 = 0
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x001e:
            return
        L_0x001f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r2 = move-exception
            if (r0 == 0) goto L_0x0027
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0027:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.mo8461f():void");
    }

    /* renamed from: g */
    public void mo8462g() {
    }

    /* renamed from: g */
    public void mo8464g(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        String o = IntentHandler.m1930o(intent);
        if (!TextUtils.isEmpty(o)) {
            if (this.f1750d) {
                VN0.c("ChromeConnection", "onHandledIntent, URL: %s, extras: %s", new Object[]{o, m2108b(intent.getExtras())});
            }
            if (this.f1756j != null) {
                this.f1756j.a();
            }
            mo8418a(customTabsSessionToken, o, intent);
            ChromeBrowserInitializer.m2488f().mo8797a((Runnable) new RK1(this, customTabsSessionToken, intent));
            mo8456e(customTabsSessionToken, intent);
        }
    }

    /* renamed from: h */
    public void mo8465h() {
    }

    /* renamed from: h */
    public boolean mo8466h(CustomTabsSessionToken customTabsSessionToken) {
        int i = mo8467i(customTabsSessionToken);
        RecordHistogram.m1539a("CustomTabs.SpeculationStatusOnStart", i, 10);
        return i == 0;
    }

    /* renamed from: i */
    public int mo8467i(CustomTabsSessionToken customTabsSessionToken) {
        if (!BM1.d()) {
            return 5;
        }
        PrefServiceBridge o0 = PrefServiceBridge.m2758o0();
        if (o0.mo9042B()) {
            return 6;
        }
        if (!o0.mo9130l()) {
            return 7;
        }
        if (!DataReductionProxySettings.m2648o().mo8952i() || ChromeFeatureList.m1784a("PredictivePrefetchingAllowedOnAllConnectionTypes")) {
            return (!((ConnectivityManager) RN0.a.getSystemService("connectivity")).isActiveNetworkMetered() || mo8476p(customTabsSessionToken) || ChromeFeatureList.m1784a("PredictivePrefetchingAllowedOnAllConnectionTypes")) ? 0 : 9;
        }
        return 8;
    }

    /* renamed from: i */
    public void mo8468i() {
    }

    /* renamed from: j */
    public boolean mo8470j(CustomTabsSessionToken customTabsSessionToken) {
        boolean k = mo8471k(customTabsSessionToken);
        mo8422a("newSession()", (Object) Boolean.valueOf(k));
        return k;
    }

    /* renamed from: k */
    public final boolean mo8471k(CustomTabsSessionToken customTabsSessionToken) {
        if (customTabsSessionToken == null) {
            return false;
        }
        C0385a aVar = new C0385a();
        B1 b1 = new B1(customTabsSessionToken);
        return this.f1749c.mo8360a(customTabsSessionToken, Binder.getCallingUid(), aVar, new Cz1(b1), b1);
    }

    /* renamed from: p */
    public boolean mo8476p(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8383q(customTabsSessionToken);
    }

    /* renamed from: c */
    public void mo8451c(CustomTabsSessionToken customTabsSessionToken) {
        this.f1749c.mo8370d(customTabsSessionToken);
    }

    /* renamed from: b */
    public static JSONObject m2108b(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            try {
                if (obj instanceof Bundle) {
                    jSONObject.put(str, m2108b((Bundle) obj));
                } else {
                    if (!(obj instanceof Integer) && !(obj instanceof Long)) {
                        if (!(obj instanceof Boolean)) {
                            if (obj == null) {
                                jSONObject.put(str, JSONObject.NULL);
                            } else {
                                jSONObject.put(str, obj.toString());
                            }
                        }
                    }
                    jSONObject.put(str, obj);
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    /* renamed from: c */
    public static void m2109c(int i) {
        RecordHistogram.m1539a("CustomTabs.SpeculationStatusOnSwap", i, 4);
    }

    /* renamed from: a */
    public void mo8414a(Bundle bundle) {
        if (this.f1750d) {
            mo8444b("extraCallback(NavigationMetrics)", (Object) m2108b(bundle).toString());
        }
    }

    /* renamed from: d */
    public String mo8453d(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8375i(customTabsSessionToken);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r0 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        m2106a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        r2 = move-exception;
     */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8469j() {
        /*
            r4 = this;
            java.lang.String r0 = "CustomTabsConnection.warmup"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            r1 = 1
            boolean r1 = r4.mo8437a((boolean) r1)     // Catch:{ all -> 0x001b }
            java.lang.String r2 = "warmup()"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x001b }
            r4.mo8422a((java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x001a
            r2 = 0
            m2106a((java.lang.Throwable) r2, (org.chromium.base.TraceEvent) r0)
        L_0x001a:
            return r1
        L_0x001b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001d }
        L_0x001d:
            r2 = move-exception
            if (r0 == 0) goto L_0x0023
            m2106a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0023:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.mo8469j():boolean");
    }

    /* renamed from: l */
    public boolean mo8472l(CustomTabsSessionToken customTabsSessionToken) {
        return mo8434a(customTabsSessionToken, "onOpenInBrowser", mo8440b());
    }

    /* renamed from: a */
    public void mo8423a(Callback<CustomTabsSessionToken> callback) {
        this.f1753g = callback;
    }

    /* renamed from: a */
    public static void m2104a(Context context) {
        ThreadUtils.m1462c();
        try {
            ChromeBrowserInitializer.m2489g().mo8800b();
        } catch (ProcessInitException unused) {
            VN0.a("ChromeConnection", "ProcessInitException while starting the browser process.", new Object[0]);
            System.exit(-1);
        }
        QP2.a(context, true);
    }

    /* renamed from: k */
    public static void m2110k() {
        if (!SysUtils.isLowEndDevice()) {
            WarmupManager.m1950e().mo8306a(true);
        }
    }

    /* renamed from: m */
    public boolean mo8473m(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8380n(customTabsSessionToken);
    }

    /* renamed from: o */
    public boolean mo8475o(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8382p(customTabsSessionToken);
    }

    /* renamed from: f */
    public String mo8460f(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1747a.b(customTabsSessionToken);
    }

    /* renamed from: n */
    public boolean mo8474n(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8381o(customTabsSessionToken);
    }

    /* renamed from: f */
    public final int mo8459f(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        CustomTabsSessionToken customTabsSessionToken2 = customTabsSessionToken;
        Intent intent2 = intent;
        ThreadUtils.m1462c();
        if (!intent2.hasExtra("android.support.customtabs.PARALLEL_REQUEST_URL")) {
            return 0;
        }
        if (!ChromeBrowserInitializer.m2489g().mo8803c()) {
            return 2;
        }
        if (!this.f1749c.mo8371e(customTabsSessionToken)) {
            return 3;
        }
        Uri uri = (Uri) intent2.getParcelableExtra("android.support.customtabs.PARALLEL_REQUEST_REFERRER");
        Uri uri2 = (Uri) intent2.getParcelableExtra("android.support.customtabs.PARALLEL_REQUEST_URL");
        int intExtra = intent2.getIntExtra("android.support.customtabs.PARALLEL_REQUEST_REFERRER_POLICY", 1);
        if (uri2 == null) {
            return 4;
        }
        if (uri == null) {
            return 5;
        }
        int i = (intExtra < 0 || intExtra > 8) ? 1 : intExtra;
        if (uri2.toString().equals(BuildConfig.FLAVOR) || !mo8426a(uri2)) {
            return 4;
        }
        if (!mo8429a(customTabsSessionToken, uri)) {
            return 6;
        }
        String uri3 = uri2.toString();
        String uri4 = uri.toString();
        nativeCreateAndStartDetachedResourceRequest(Profile.m2911j(), customTabsSessionToken, uri3, uri4, i, 0);
        if (this.f1750d) {
            VN0.c("ChromeConnection", "startParallelRequest(%s, %s, %d)", new Object[]{uri3, uri4, Integer.valueOf(i)});
        }
        return 1;
    }

    /* renamed from: g */
    public final /* synthetic */ void mo8463g(CustomTabsSessionToken customTabsSessionToken) {
        this.f1749c.mo8367b(customTabsSessionToken);
    }

    /* renamed from: a */
    public static /* synthetic */ void m2105a(Throwable th, BufferedReader bufferedReader) {
        if (th != null) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            bufferedReader.close();
        }
    }

    /* renamed from: b */
    public final boolean mo8449b(List<Bundle> list) {
        boolean z = false;
        if (list == null) {
            return false;
        }
        WarmupManager e = WarmupManager.m1950e();
        Profile c = Profile.m2911j().mo9203c();
        for (Bundle b : list) {
            try {
                Uri uri = (Uri) PE2.b(b, "android.support.customtabs.otherurls.URL");
                if (mo8426a(uri)) {
                    e.mo8305a(c, uri.toString());
                    z = true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return z;
    }

    /* renamed from: e */
    public oQ2 mo8457e(CustomTabsSessionToken customTabsSessionToken) {
        return this.f1749c.mo8377k(customTabsSessionToken);
    }

    /* renamed from: a */
    public static /* synthetic */ void m2107a(Throwable th, pO0 po0) {
        if (th != null) {
            try {
                po0.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            po0.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b9, code lost:
        r0 = r0[2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        m2105a((java.lang.Throwable) null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        m2107a((java.lang.Throwable) null, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        m2105a((java.lang.Throwable) null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        m2107a((java.lang.Throwable) null, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        m2105a(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00d0, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00d3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        m2107a(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00d7, code lost:
        throw r1;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo8458e() {
        /*
            r9 = this;
            int r0 = android.os.Binder.getCallingUid()
            int r1 = android.os.Process.myUid()
            r2 = 1
            if (r0 != r1) goto L_0x000c
            return r2
        L_0x000c:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 22
            r4 = 0
            if (r1 >= r3) goto L_0x0053
            android.content.Context r1 = RN0.a
            java.lang.String r3 = "activity"
            java.lang.Object r1 = r1.getSystemService(r3)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            if (r1 != 0) goto L_0x0020
            goto L_0x0053
        L_0x0020:
            java.util.List r1 = r1.getRunningAppProcesses()
            if (r1 != 0) goto L_0x0027
            goto L_0x0053
        L_0x0027:
            java.util.Iterator r1 = r1.iterator()
            r3 = 1
        L_0x002c:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0054
            java.lang.Object r5 = r1.next()
            android.app.ActivityManager$RunningAppProcessInfo r5 = (android.app.ActivityManager.RunningAppProcessInfo) r5
            if (r5 != 0) goto L_0x003b
            goto L_0x002c
        L_0x003b:
            int r6 = r5.uid
            if (r6 != r0) goto L_0x0041
            r6 = 1
            goto L_0x0042
        L_0x0041:
            r6 = 0
        L_0x0042:
            int r5 = r5.importance
            r7 = 100
            if (r5 != r7) goto L_0x004a
            r5 = 1
            goto L_0x004b
        L_0x004a:
            r5 = 0
        L_0x004b:
            r7 = r6 ^ 1
            r3 = r3 & r7
            if (r6 == 0) goto L_0x002c
            if (r5 == 0) goto L_0x002c
            return r2
        L_0x0053:
            r3 = 1
        L_0x0054:
            if (r3 == 0) goto L_0x00de
            int r0 = android.os.Binder.getCallingPid()
            java.lang.String r1 = "/proc/"
            java.lang.String r3 = Eo.b(r1, r0)
            java.io.File r5 = new java.io.File
            r5.<init>(r3)
            boolean r3 = r5.exists()
            if (r3 == 0) goto L_0x0078
            boolean r3 = r5.isDirectory()
            if (r3 == 0) goto L_0x0078
            boolean r3 = r5.canExecute()
            if (r3 == 0) goto L_0x0078
            r4 = 1
        L_0x0078:
            if (r4 != 0) goto L_0x007b
            return r2
        L_0x007b:
            java.util.Set<java.lang.String> r3 = f1743l
            java.lang.String r4 = "/cgroup"
            java.lang.String r0 = Eo.a(r1, r0, r4)
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 26
            if (r1 < r4) goto L_0x008c
            java.lang.String r1 = "cpuset"
            goto L_0x008e
        L_0x008c:
            java.lang.String r1 = "cpu"
        L_0x008e:
            r4 = 0
            pO0 r5 = pO0.a()     // Catch:{ IOException -> 0x00d8 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x00d1 }
            java.io.FileReader r7 = new java.io.FileReader     // Catch:{ all -> 0x00d1 }
            r7.<init>(r0)     // Catch:{ all -> 0x00d1 }
            r6.<init>(r7)     // Catch:{ all -> 0x00d1 }
        L_0x009d:
            java.lang.String r0 = r6.readLine()     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x00c3
            java.lang.String r0 = r0.trim()     // Catch:{ all -> 0x00ca }
            java.lang.String r7 = ":"
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ all -> 0x00ca }
            int r7 = r0.length     // Catch:{ all -> 0x00ca }
            r8 = 3
            if (r7 != r8) goto L_0x009d
            r7 = r0[r2]     // Catch:{ all -> 0x00ca }
            boolean r7 = r7.equals(r1)     // Catch:{ all -> 0x00ca }
            if (r7 == 0) goto L_0x009d
            r1 = 2
            r0 = r0[r1]     // Catch:{ all -> 0x00ca }
            m2105a((java.lang.Throwable) r4, (java.io.BufferedReader) r6)     // Catch:{ all -> 0x00d1 }
            m2107a((java.lang.Throwable) r4, (pO0) r5)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00d9
        L_0x00c3:
            m2105a((java.lang.Throwable) r4, (java.io.BufferedReader) r6)     // Catch:{ all -> 0x00d1 }
            m2107a((java.lang.Throwable) r4, (pO0) r5)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00d8
        L_0x00ca:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00cc }
        L_0x00cc:
            r1 = move-exception
            m2105a((java.lang.Throwable) r0, (java.io.BufferedReader) r6)     // Catch:{ all -> 0x00d1 }
            throw r1     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00d3 }
        L_0x00d3:
            r1 = move-exception
            m2107a((java.lang.Throwable) r0, (pO0) r5)     // Catch:{ IOException -> 0x00d8 }
            throw r1     // Catch:{ IOException -> 0x00d8 }
        L_0x00d8:
            r0 = r4
        L_0x00d9:
            boolean r0 = r3.contains(r0)
            return r0
        L_0x00de:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.mo8458e():boolean");
    }

    /* renamed from: a */
    public static /* synthetic */ void m2106a(Throwable th, TraceEvent traceEvent) {
        if (th != null) {
            try {
                traceEvent.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            traceEvent.close();
        }
    }

    /* renamed from: b */
    public final boolean mo8446b(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list) {
        boolean z = (uri == null || TextUtils.isEmpty(uri.toString())) && list != null;
        String uri2 = mo8426a(uri) ? uri.toString() : null;
        if ((uri != null && uri2 == null && !z) || !mo8437a(false)) {
            return false;
        }
        int callingUid = Binder.getCallingUid();
        if (!this.f1749c.mo8359a(customTabsSessionToken, callingUid, uri2, list != null)) {
            return false;
        }
        PostTask.m1566a(iQ2.a, new XK1(this, z, customTabsSessionToken, callingUid, uri2, bundle, list), 0);
        return true;
    }

    /* renamed from: a */
    public final boolean mo8437a(boolean z) {
        if (!mo8458e()) {
            return false;
        }
        int callingUid = Binder.getCallingUid();
        this.f1749c.mo8353a(callingUid);
        boolean z2 = !this.f1751e.compareAndSet(false, true);
        U12 u12 = new U12();
        if (!z2) {
            u12.a(iQ2.e, new QK1(this));
        }
        if (z && !this.f1747a.a()) {
            u12.a(iQ2.e, TK1.a);
        }
        u12.a(iQ2.e, UK1.a);
        if (!z2) {
            u12.a(iQ2.e, VK1.a);
        }
        u12.a(iQ2.e, new WK1(this, callingUid));
        u12.a(false);
        this.f1756j = u12;
        return true;
    }

    /* renamed from: b */
    public final /* synthetic */ void mo8445b(boolean z, CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List list) {
        mo8425a(z, customTabsSessionToken, i, str, bundle, list, true);
    }

    /* renamed from: b */
    public boolean mo8447b(CustomTabsSessionToken customTabsSessionToken, Bundle bundle) {
        Bundle bundle2;
        Bitmap a;
        String b;
        boolean z = true;
        if (this.f1750d) {
            VN0.c("ChromeConnection", "updateVisuals: %s", new Object[]{m2108b(bundle)});
        }
        Iz1 a2 = this.f1748b.a(customTabsSessionToken);
        if (a2 == null) {
            return false;
        }
        ArrayList<Bundle> arrayList = null;
        try {
            bundle2 = bundle.getBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE");
        } catch (Throwable unused) {
            VN0.a("IntentUtils", "getBundle failed on bundle " + bundle, new Object[0]);
            bundle2 = null;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (bundle2 != null) {
            int a3 = PE2.a(bundle2, "android.support.customtabs.customaction.ID", 0);
            Bitmap a4 = WJ1.a(bundle2);
            String b2 = WJ1.b(bundle2);
            if (!(a4 == null || b2 == null)) {
                arrayList2.add(Integer.valueOf(a3));
                arrayList3.add(b2);
                arrayList4.add(a4);
            }
        }
        try {
            arrayList = bundle.getParcelableArrayList("android.support.customtabs.extra.TOOLBAR_ITEMS");
        } catch (Throwable unused2) {
            VN0.a("IntentUtils", "getParcelableArrayList failed on bundle " + bundle, new Object[0]);
        }
        if (arrayList != null) {
            for (Bundle bundle3 : arrayList) {
                int a5 = PE2.a(bundle3, "android.support.customtabs.customaction.ID", 0);
                if (!(arrayList2.contains(Integer.valueOf(a5)) || (a = WJ1.a(bundle3)) == null || (b = WJ1.b(bundle3)) == null)) {
                    arrayList2.add(Integer.valueOf(a5));
                    arrayList3.add(b);
                    arrayList4.add(a);
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            z = true & ((Boolean) PostTask.m1562a(iQ2.a, new ZK1(arrayList2, a2, arrayList4, arrayList3))).booleanValue();
        }
        if (bundle.containsKey("android.support.customtabs.extra.EXTRA_REMOTEVIEWS")) {
            z &= ((Boolean) PostTask.m1562a(iQ2.a, new aL1(a2, (RemoteViews) PE2.b(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS"), PE2.a(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS"), (PendingIntent) PE2.b(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT")))).booleanValue();
        }
        mo8422a("updateVisuals()", (Object) Boolean.valueOf(z));
        return z;
    }

    /* renamed from: a */
    public final boolean mo8426a(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.normalizeScheme().getScheme();
        return scheme == null || scheme.equals("http") || scheme.equals(Oauth2.HTTPS_PROTOCOL_STRING);
    }

    /* renamed from: a */
    public final void mo8416a(CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List<Bundle> list) {
        ThreadUtils.m1462c();
        if (TextUtils.isEmpty(str)) {
            mo8415a(customTabsSessionToken);
            return;
        }
        String a = DataReductionProxySettings.m2648o().mo8941a(str);
        if (mo8466h(customTabsSessionToken)) {
            mo8419a(customTabsSessionToken, a, this.f1749c.mo8374h(customTabsSessionToken), bundle);
        }
        mo8449b(list);
    }

    /* renamed from: a */
    public boolean mo8436a(List<Bundle> list) {
        ThreadUtils.m1462c();
        if (!mo8449b(list)) {
            return false;
        }
        m2110k();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r0 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        m2106a(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r3 = move-exception;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8431a(android.support.customtabs.CustomTabsSessionToken r2, android.net.Uri r3, android.os.Bundle r4, java.util.List<android.os.Bundle> r5) {
        /*
            r1 = this;
            java.lang.String r0 = "CustomTabsConnection.mayLaunchUrl"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            boolean r2 = r1.mo8446b(r2, r3, r4, r5)     // Catch:{ all -> 0x002e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x002e }
            r4.<init>()     // Catch:{ all -> 0x002e }
            java.lang.String r5 = "mayLaunchUrl("
            r4.append(r5)     // Catch:{ all -> 0x002e }
            r4.append(r3)     // Catch:{ all -> 0x002e }
            java.lang.String r3 = ")"
            r4.append(r3)     // Catch:{ all -> 0x002e }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x002e }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x002e }
            r1.mo8422a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x002d
            r3 = 0
            m2106a((java.lang.Throwable) r3, (org.chromium.base.TraceEvent) r0)
        L_0x002d:
            return r2
        L_0x002e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r3 = move-exception
            if (r0 == 0) goto L_0x0036
            m2106a((java.lang.Throwable) r2, (org.chromium.base.TraceEvent) r0)
        L_0x0036:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.mo8431a(android.support.customtabs.CustomTabsSessionToken, android.net.Uri, android.os.Bundle, java.util.List):boolean");
    }

    /* renamed from: a */
    public final void mo8425a(boolean z, CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List<Bundle> list, boolean z2) {
        Throwable th;
        ThreadUtils.m1462c();
        TraceEvent B = TraceEvent.m1469B("CustomTabsConnection.mayLaunchUrlOnUiThread");
        try {
            if (!OP2.a(1).mo9662a()) {
                if (z2) {
                    PostTask.m1566a(iQ2.a, new YK1(this, z, customTabsSessionToken, i, str, bundle, list), 0);
                }
                if (B != null) {
                    m2106a((Throwable) null, B);
                    return;
                }
                return;
            }
            if (z) {
                try {
                    mo8436a(list);
                } catch (Throwable th2) {
                    th = th2;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        if (B != null) {
                            m2106a(th, B);
                        }
                        throw th4;
                    }
                }
            } else {
                List<Bundle> list2 = list;
                mo8416a(customTabsSessionToken, i, str, bundle, list);
            }
            if (B != null) {
                m2106a((Throwable) null, B);
            }
        } catch (Throwable th5) {
            th = th5;
            th = th;
            throw th;
        }
    }

    /* renamed from: b */
    public final boolean mo8448b(CustomTabsSessionToken customTabsSessionToken, yz1 yz1) {
        if (!this.f1751e.get()) {
            return false;
        }
        if ((!mo8458e() && !this.f1748b.b(customTabsSessionToken)) || !this.f1749c.mo8358a(customTabsSessionToken)) {
            return false;
        }
        PostTask.m1566a(iQ2.a, new bL1(this, customTabsSessionToken, Binder.getCallingUid(), yz1), 0);
        return true;
    }

    /* renamed from: b */
    public Uri mo8439b(int i) {
        if (i == Process.myUid()) {
            return Uri.EMPTY;
        }
        return null;
    }

    /* renamed from: b */
    public void mo8442b(CustomTabsSessionToken customTabsSessionToken, String str) {
        this.f1749c.mo8369c(customTabsSessionToken, str);
    }

    /* renamed from: b */
    public int mo8454d(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        int f = mo8459f(customTabsSessionToken, intent);
        f1745n.a(f);
        if (this.f1750d) {
            StringBuilder a = Eo.a("handleParallelRequest() = ");
            a.append(f1744m[f]);
            VN0.c("ChromeConnection", a.toString(), new Object[0]);
        }
        if (!(f == 0 || f == 2 || f == 3 || !ChromeFeatureList.m1784a("CCTReportParallelRequestStatus"))) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("url", (Uri) intent.getParcelableExtra("android.support.customtabs.PARALLEL_REQUEST_URL"));
            bundle.putInt("status", f);
            mo8434a(customTabsSessionToken, "onDetachedRequestRequested", bundle);
            if (this.f1750d) {
                mo8444b("onDetachedRequestRequested", (Object) m2108b(bundle).toString());
            }
        }
        return f;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8424a(boolean z, CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List list) {
        mo8425a(z, customTabsSessionToken, i, str, bundle, list, false);
    }

    /* renamed from: a */
    public static final /* synthetic */ Boolean m2103a(List list, Iz1 iz1, List list2, List list3) throws Exception {
        boolean z = true;
        for (int i = 0; i < list.size(); i++) {
            z &= ((CustomTabActivity.C0384b) iz1).mo8403a(((Integer) list.get(i)).intValue(), (Bitmap) list2.get(i), (String) list3.get(i));
        }
        return Boolean.valueOf(z);
    }

    /* renamed from: a */
    public boolean mo8435a(CustomTabsSessionToken customTabsSessionToken, yz1 yz1) {
        boolean b = mo8448b(customTabsSessionToken, yz1);
        StringBuilder a = Eo.a("requestPostMessageChannel() with origin ");
        a.append(yz1 != null ? yz1.toString() : BuildConfig.FLAVOR);
        mo8422a(a.toString(), (Object) Boolean.valueOf(b));
        return b;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8417a(CustomTabsSessionToken customTabsSessionToken, int i, yz1 yz1) {
        if (ChromeFeatureList.m1784a("CCTPostMessageAPI")) {
            Uri b = mo8439b(i);
            if (b == null) {
                this.f1749c.mo8356a(customTabsSessionToken, yz1, 1);
            } else {
                this.f1749c.mo8354a(customTabsSessionToken, b);
            }
        }
    }

    /* renamed from: b */
    public void mo8443b(CustomTabsSessionToken customTabsSessionToken, boolean z) {
        this.f1749c.mo8357a(customTabsSessionToken, z);
    }

    /* renamed from: b */
    public Bundle mo8440b() {
        Bundle bundle = new Bundle();
        bundle.putLong("timestampUptimeMillis", SystemClock.uptimeMillis());
        return bundle;
    }

    /* renamed from: b */
    public void mo8441b(CustomTabsSessionToken customTabsSessionToken) {
        PostTask.m1565a(iQ2.a, (Runnable) new SK1(this, customTabsSessionToken));
    }

    /* renamed from: a */
    public int mo8409a(CustomTabsSessionToken customTabsSessionToken, String str) {
        this.f1751e.get();
        if (!mo8458e()) {
            this.f1748b.b(customTabsSessionToken);
        }
        int b = this.f1749c.mo8365b(customTabsSessionToken, str);
        mo8422a("postMessage", (Object) Integer.valueOf(b));
        return b;
    }

    /* renamed from: a */
    public boolean mo8428a(CustomTabsSessionToken customTabsSessionToken, int i, yz1 yz1, Bundle bundle) {
        if (this.f1751e.get()) {
            return this.f1749c.mo8361a(customTabsSessionToken, i, yz1);
        }
        this.f1749c.mo8373g(customTabsSessionToken).a(i, Uri.parse(yz1.toString()), false, (Bundle) null);
        return false;
    }

    /* renamed from: a */
    public void mo8420a(CustomTabsSessionToken customTabsSessionToken, WebContents webContents) {
        this.f1749c.mo8355a(customTabsSessionToken, webContents);
    }

    /* renamed from: a */
    public Tab mo8412a(CustomTabsSessionToken customTabsSessionToken, String str, String str2) {
        return this.f1747a.a(customTabsSessionToken, this.f1749c.mo8376j(customTabsSessionToken), str, str2);
    }

    /* renamed from: a */
    public final void mo8418a(CustomTabsSessionToken customTabsSessionToken, String str, Intent intent) {
        Uri uri;
        if (ChromeBrowserInitializer.m2489g().mo8803c() && ChromeFeatureList.m1784a("CCTRedirectPreconnect") && (uri = (Uri) intent.getParcelableExtra("android.support.customtabs.REDIRECT_ENDPOINT")) != null && mo8426a(uri)) {
            if (this.f1749c.mo8364a(customTabsSessionToken, new yz1(str))) {
                WarmupManager.m1950e().mo8305a(Profile.m2911j(), uri.toString());
            }
        }
    }

    /* renamed from: a */
    public boolean mo8429a(CustomTabsSessionToken customTabsSessionToken, Uri uri) {
        ThreadUtils.m1462c();
        return this.f1749c.mo8364a(customTabsSessionToken, new yz1(uri));
    }

    /* renamed from: a */
    public void mo8421a(CustomTabsSessionToken customTabsSessionToken, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("hidden", z);
        if (mo8434a(customTabsSessionToken, "onBottomBarScrollStateChanged", bundle) && this.f1750d) {
            mo8444b("extraCallback(onBottomBarScrollStateChanged)", (Object) Boolean.valueOf(z));
        }
    }

    /* renamed from: a */
    public boolean mo8427a(CustomTabsSessionToken customTabsSessionToken, int i) {
        g1 g = this.f1749c.mo8373g(customTabsSessionToken);
        if (g == null) {
            return false;
        }
        try {
            g.a(i, mo8440b());
            mo8444b("onNavigationEvent()", (Object) Integer.valueOf(i));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8413a(int i) {
        ThreadUtils.m1462c();
        for (CustomTabsSessionToken a : this.f1749c.mo8366b(i)) {
            mo8434a(a, "onWarmupCompleted", (Bundle) null);
        }
    }

    /* renamed from: a */
    public boolean mo8433a(CustomTabsSessionToken customTabsSessionToken, String str, long j, long j2) {
        if (!this.f1749c.mo8379m(customTabsSessionToken)) {
            return false;
        }
        if (!this.f1755i) {
            this.f1755i = true;
            this.f1754h = TimeUtils.nativeGetTimeTicksNowUs() - (SystemClock.uptimeMillis() * 1000);
        }
        Bundle bundle = new Bundle();
        bundle.putLong(str, j2);
        bundle.putLong("navigationStart", (j - this.f1754h) / 1000);
        return mo8432a(customTabsSessionToken, bundle);
    }

    /* renamed from: a */
    public boolean mo8432a(CustomTabsSessionToken customTabsSessionToken, Bundle bundle) {
        if (!mo8434a(customTabsSessionToken, "NavigationMetrics", bundle)) {
            return false;
        }
        mo8414a(bundle);
        return true;
    }

    /* renamed from: a */
    public boolean mo8434a(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle) {
        g1 g = this.f1749c.mo8373g(customTabsSessionToken);
        if (g == null) {
            return false;
        }
        try {
            g.a(str, bundle);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public void mo8415a(CustomTabsSessionToken customTabsSessionToken) {
        ThreadUtils.m1462c();
        this.f1747a.a(customTabsSessionToken);
    }

    /* renamed from: a */
    public final void mo8419a(CustomTabsSessionToken customTabsSessionToken, String str, boolean z, Bundle bundle) {
        WarmupManager e = WarmupManager.m1950e();
        Profile j = Profile.m2911j();
        mo8415a((CustomTabsSessionToken) null);
        if (z) {
            RecordHistogram.m1539a("CustomTabs.SpeculationStatusOnStart", 3, 10);
            this.f1747a.a(customTabsSessionToken, this.f1749c, str, bundle);
        } else {
            m2110k();
        }
        e.mo8305a(j, str);
    }

    /* renamed from: a */
    public String mo8411a(CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        String l = IntentHandler.m1927l(intent);
        if (l == null && mo8457e(customTabsSessionToken) != null) {
            l = mo8457e(customTabsSessionToken).a();
        }
        return l == null ? BuildConfig.FLAVOR : l;
    }

    /* renamed from: a */
    public boolean mo8430a(CustomTabsSessionToken customTabsSessionToken, Uri uri, int i, Bundle bundle) {
        return ChromeApplication.m1775e().g().a(customTabsSessionToken, uri, i);
    }
}
