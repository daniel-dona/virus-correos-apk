package org.chromium.chrome.browser.init;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Looper;
import android.os.StrictMode;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.ruby.util.RubyBuild;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.CommandLine;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.LibraryPrefetcher;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.ChromeStrictMode;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.dual_identity.DualIdentityUtils;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.components.url_formatter.DefaultSchemeProvider;
import org.chromium.content.browser.SpeechRecognitionImpl;
import org.chromium.content_public.browser.BrowserStartupController;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.policy.CombinedPolicyProvider;

/* compiled from: PG */
public class ChromeBrowserInitializer {

    /* renamed from: g */
    public static ChromeBrowserInitializer f2047g;

    /* renamed from: h */
    public static BrowserStartupController f2048h;

    /* renamed from: a */
    public final Locale f2049a = Locale.getDefault();

    /* renamed from: b */
    public List<Runnable> f2050b;

    /* renamed from: c */
    public boolean f2051c;

    /* renamed from: d */
    public boolean f2052d;

    /* renamed from: e */
    public boolean f2053e;

    /* renamed from: f */
    public boolean f2054f;

    /* renamed from: f */
    public static ChromeBrowserInitializer m2488f() {
        DefaultSchemeProvider.a = new qe0();
        if (!RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.PRODUCT, RubyBuild.BETA, RubyBuild.ALPHA, RubyBuild.SELFHOST))) {
            try {
                Class.forName("com.microsoft.ruby.leakcanary.LeakCanaryUtils").getMethod("initLeakCanary", new Class[]{Application.class}).invoke((Object) null, new Object[]{RN0.a});
            } catch (Exception unused) {
            }
        }
        if (f2047g == null) {
            f2047g = new ChromeBrowserInitializer();
        }
        return f2047g;
    }

    /* renamed from: g */
    public static ChromeBrowserInitializer m2489g() {
        return m2488f();
    }

    /* renamed from: a */
    public void mo8797a(Runnable runnable) {
        if (this.f2053e) {
            runnable.run();
            return;
        }
        if (this.f2050b == null) {
            this.f2050b = new ArrayList();
        }
        this.f2050b.add(runnable);
    }

    /* renamed from: b */
    public void mo8800b() throws ProcessInitException {
        ThreadUtils.m1464d();
        h22 h22 = new h22(this, true);
        mo8796a((R12) h22);
        mo8799a(false, h22);
    }

    /* renamed from: c */
    public boolean mo8803c() {
        return this.f2053e;
    }

    /* renamed from: d */
    public void mo8804d() {
        if (!this.f2054f) {
            this.f2054f = true;
            ThreadUtils.m1462c();
            TraceEvent.m1472c("NetworkChangeNotifier.init", (String) null);
            NetworkChangeNotifier.init();
            NetworkChangeNotifier.m3576a(true);
            TraceEvent.m1475z("NetworkChangeNotifier.init");
        }
    }

    /* renamed from: e */
    public final void mo8805e() {
        ThreadUtils.m1462c();
        if (!this.f2051c) {
            if (!ChromeStrictMode.a) {
                ChromeStrictMode.a = true;
                StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy());
                StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy());
                CommandLine c = CommandLine.m1384c();
                if (RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.DEVELOPMENT)) || c.mo7859c("strict-mode")) {
                    builder.detectAll();
                    if (Build.VERSION.SDK_INT >= 24) {
                        builder2.detectAll();
                    } else {
                        builder2.detectActivityLeaks().detectLeakedClosableObjects().detectLeakedRegistrationObjects().detectLeakedSqlLiteObjects();
                    }
                    builder.penaltyLog().penaltyFlashScreen().penaltyDeathOnNetwork();
                    builder2.penaltyLog();
                    if ("death".equals(c.mo7857b("strict-mode"))) {
                        ChromeStrictMode.a(builder);
                        builder2.penaltyDeath();
                    } else if ("testing".equals(c.mo7857b("strict-mode"))) {
                        ChromeStrictMode.a(builder);
                    }
                }
                if (RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.DEVELOPMENT))) {
                    try {
                        Field declaredField = StrictMode.class.getDeclaredField("violationsBeingTimed");
                        declaredField.setAccessible(true);
                        ((ThreadLocal) declaredField.get((Object) null)).set(new ChromeStrictMode.SnoopingArrayList((ChromeStrictMode.a) null));
                        ChromeStrictMode.c.set(0);
                        Looper.myQueue().addIdleHandler(wt1.a);
                    } catch (Exception e) {
                        VN0.c("ChromeStrictMode", "Could not initialize StrictMode watch.", new Object[]{e});
                    }
                }
                StrictMode.setThreadPolicy(builder.build());
                StrictMode.setVmPolicy(builder2.build());
            }
            rF2.b();
            if (Build.VERSION.SDK_INT >= 24) {
                PostTask.m1566a(VP0.i, Y12.a, 0);
            } else {
                oF2.b();
                DownloadManagerService.o();
            }
            new j22(this).a(Us0.d);
            HM2.a();
            SharedPreferences sharedPreferences = QN0.a;
            if (sharedPreferences.getBoolean("user_agent_is_overridden", false)) {
                CommandLine.m1384c().mo7855a("user-agent", sharedPreferences.getString("user_agent_overriding_value", BuildConfig.FLAVOR));
            }
            boolean A = MicrosoftSigninManager.C0424c.f2104a.mo8866A();
            String e2 = DualIdentityUtils.m2252e();
            if (A && !TextUtils.isEmpty(e2)) {
                CommandLine.m1384c().mo7855a("proxy-pac-url", e2);
            }
            ApplicationStatus.f1398f.mo7868a(new m22(this));
            this.f2051c = true;
        }
    }

    /* renamed from: c */
    public final /* synthetic */ void mo8802c(R12 r12) {
        if (!r12.a()) {
            ThreadUtils.m1462c();
            if (!this.f2052d) {
                dK3.d().a(iQ2.e);
                dK3 d = dK3.d();
                String b = LocaleUtils.m1434b(vt1.a());
                if (d.a == null && !dK3.e()) {
                    d.a = new cK3(d, b);
                    PostTask.m1566a(VP0.l, d.a, 0);
                }
                this.f2052d = true;
            }
            r12.p();
        }
    }

    /* renamed from: b */
    public final /* synthetic */ void mo8801b(R12 r12) {
        r12.o();
        ThreadUtils.m1462c();
        if (!this.f2053e) {
            AppHooks.get().mo7975a(CombinedPolicyProvider.m3619a());
            Context context = RN0.a;
            if (SpeechRecognizer.isRecognitionAvailable(context)) {
                for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 4)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    if (serviceInfo.packageName.equals("com.google.android.googlequicksearchbox") && cO0.a(context, serviceInfo.packageName) >= 300207030) {
                        SpeechRecognitionImpl.g = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo8798a(boolean z) throws ProcessInitException {
        ThreadUtils.m1464d();
        h22 h22 = new h22(this, z);
        mo8796a((R12) h22);
        mo8799a(false, h22);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r0 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        qI.a.a(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        throw r1;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8796a(R12 r4) {
        /*
            r3 = this;
            org.chromium.base.ThreadUtils.m1464d()
            V22 r0 = V22.k()
            r0.f()
            java.lang.String r0 = "ChromeBrowserInitializer.preInflationStartup"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            r3.mo8805e()     // Catch:{ all -> 0x003a }
            r4.A()     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            boolean r0 = r4.a()
            if (r0 == 0) goto L_0x0022
            return
        L_0x0022:
            boolean r0 = org.chromium.base.SysUtils.isLowEndDevice()
            if (r0 == 0) goto L_0x0031
            org.chromium.base.CommandLine r0 = org.chromium.base.CommandLine.m1384c()
            java.lang.String r1 = "disable-domain-reliability"
            r0.mo7854a((java.lang.String) r1)
        L_0x0031:
            W12 r0 = new W12
            r0.<init>(r3, r4)
            r4.a(r0)
            return
        L_0x003a:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003c }
        L_0x003c:
            r1 = move-exception
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ all -> 0x0043 }
            goto L_0x0049
        L_0x0043:
            r0 = move-exception
            kI r2 = qI.a
            r2.a(r4, r0)
        L_0x0049:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.init.ChromeBrowserInitializer.mo8796a(R12):void");
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public void mo8799a(boolean z, R12 r12) throws ProcessInitException {
        if (this.f2052d) {
            U12 u12 = new U12();
            if (!r12.w() && !V22.k().b) {
                u12.a(iQ2.e, Z12.a);
            }
            if (!this.f2054f) {
                u12.a(iQ2.e, new a22(this));
            }
            u12.a(iQ2.e, new b22(this, r12));
            u12.a(iQ2.e, new c22(r12));
            u12.a(iQ2.e, new d22(r12));
            u12.a(iQ2.e, new e22(r12));
            if (!this.f2053e) {
                u12.a(iQ2.a, new f22(this));
            }
            u12.a(iQ2.a, new g22(mo8795a().mo9657a(r12.w())));
            if (z) {
                boolean u = r12.u();
                boolean w = r12.w();
                k22 k22 = new k22(this, r12, u12);
                try {
                    TraceEvent.m1472c("ChromeBrowserInitializer.startChromeBrowserProcessesAsync", (String) null);
                    mo8795a().mo9661a(u, w, k22);
                } finally {
                    TraceEvent.m1475z("ChromeBrowserInitializer.startChromeBrowserProcessesAsync");
                }
            } else {
                try {
                    TraceEvent.m1472c("ChromeBrowserInitializer.startChromeBrowserProcessesSync", (String) null);
                    ThreadUtils.m1462c();
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    LibraryLoader.f1501h.mo7901a(1);
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    LibraryPrefetcher.m1511a();
                    mo8795a().mo9664b(false);
                    TraceEvent.m1475z("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
                    u12.a(true);
                } catch (Throwable th) {
                    TraceEvent.m1475z("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
                    throw th;
                }
            }
        } else {
            throw new IllegalStateException("ChromeBrowserInitializer.handlePostNativeStartup called before ChromeBrowserInitializer.postInflationStartup has been run.");
        }
    }

    /* renamed from: a */
    public final BrowserStartupController mo8795a() {
        if (f2048h == null) {
            f2048h = OP2.a(1);
        }
        return f2048h;
    }
}
