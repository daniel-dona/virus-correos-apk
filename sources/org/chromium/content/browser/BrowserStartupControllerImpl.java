package org.chromium.content.browser;

import android.os.StrictMode;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.task.PostTask;
import org.chromium.content.app.ContentMain;
import org.chromium.content_public.browser.BrowserStartupController;

/* compiled from: PG */
public class BrowserStartupControllerImpl implements BrowserStartupController {

    /* renamed from: m */
    public static BrowserStartupControllerImpl f2390m;

    /* renamed from: n */
    public static boolean f2391n;

    /* renamed from: a */
    public final List<BrowserStartupController.StartupCallback> f2392a = new ArrayList();

    /* renamed from: b */
    public final List<BrowserStartupController.StartupCallback> f2393b = new ArrayList();

    /* renamed from: c */
    public boolean f2394c;

    /* renamed from: d */
    public boolean f2395d;

    /* renamed from: e */
    public boolean f2396e;

    /* renamed from: f */
    public boolean f2397f;

    /* renamed from: g */
    public boolean f2398g;

    /* renamed from: h */
    public int f2399h;

    /* renamed from: i */
    public int f2400i = 0;

    /* renamed from: j */
    public boolean f2401j;

    /* renamed from: k */
    public boolean f2402k;

    /* renamed from: l */
    public TracingControllerAndroidImpl f2403l;

    /* renamed from: org.chromium.content.browser.BrowserStartupControllerImpl$a */
    /* compiled from: PG */
    public class C0435a implements Runnable {
        public C0435a() {
        }

        public void run() {
            BrowserStartupControllerImpl.this.mo9659a((BrowserStartupController.StartupCallback) new rM2(this));
        }
    }

    /* renamed from: org.chromium.content.browser.BrowserStartupControllerImpl$b */
    /* compiled from: PG */
    public class C0436b implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ boolean f2405a;

        public C0436b(boolean z) {
            this.f2405a = z;
        }

        public void run() {
            ThreadUtils.m1462c();
            BrowserStartupControllerImpl browserStartupControllerImpl = BrowserStartupControllerImpl.this;
            if (!browserStartupControllerImpl.f2396e) {
                browserStartupControllerImpl.f2400i = this.f2405a ? 1 : 0;
                if (browserStartupControllerImpl.mo9669d() > 0) {
                    BrowserStartupControllerImpl.this.mo9658a(1);
                }
            }
        }
    }

    /* renamed from: org.chromium.content.browser.BrowserStartupControllerImpl$c */
    /* compiled from: PG */
    public class C0437c implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ int f2407a;

        public C0437c(int i) {
            this.f2407a = i;
        }

        public void run() {
            BrowserStartupControllerImpl.this.mo9663b(this.f2407a);
        }
    }

    /* renamed from: org.chromium.content.browser.BrowserStartupControllerImpl$d */
    /* compiled from: PG */
    public class C0438d implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ boolean f2409a;

        /* renamed from: b */
        public final /* synthetic */ Runnable f2410b;

        public C0438d(boolean z, Runnable runnable) {
            this.f2409a = z;
            this.f2410b = runnable;
        }

        public void run() {
            if (!BrowserStartupControllerImpl.this.f2395d) {
                HM2.a();
                BrowserStartupControllerImpl.nativeSetCommandLineFlags(this.f2409a);
                BrowserStartupControllerImpl.this.f2395d = true;
            }
            Runnable runnable = this.f2410b;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public BrowserStartupControllerImpl(int i) {
        this.f2399h = i;
        if (BuildInfo.m1373b()) {
            PostTask.m1566a(iQ2.a, new C0435a(), 0);
        }
    }

    @CalledByNative
    public static void browserStartupComplete(int i) {
        BrowserStartupControllerImpl browserStartupControllerImpl = f2390m;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.mo9663b(i);
        }
    }

    public static native void nativeFlushStartupTasks();

    public static native boolean nativeIsOfficialBuild();

    public static native void nativeSetCommandLineFlags(boolean z);

    @CalledByNative
    public static void serviceManagerStartupComplete() {
        BrowserStartupControllerImpl browserStartupControllerImpl = f2390m;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.f2402k = true;
            if (browserStartupControllerImpl.f2401j) {
                browserStartupControllerImpl.f2400i = 0;
                if (browserStartupControllerImpl.mo9669d() > 0) {
                    browserStartupControllerImpl.mo9658a(1);
                    return;
                }
                return;
            }
            if (browserStartupControllerImpl.f2400i == 1) {
                browserStartupControllerImpl.mo9667c(-1);
            }
            browserStartupControllerImpl.mo9671f();
        }
    }

    @CalledByNative
    public static boolean shouldStartGpuProcessOnBrowserStartup() {
        return f2391n;
    }

    /* renamed from: a */
    public void mo9661a(boolean z, boolean z2, BrowserStartupController.StartupCallback startupCallback) throws ProcessInitException {
        YM2.b().a(YM2.a(this.f2397f, this.f2402k, z2));
        if (this.f2397f || (z2 && this.f2402k)) {
            PostTask.m1566a(iQ2.e, new sM2(this, startupCallback), 0);
            return;
        }
        if (z2) {
            this.f2393b.add(startupCallback);
        } else {
            this.f2392a.add(startupCallback);
        }
        this.f2401j |= this.f2400i == 1 && !z2;
        if (!this.f2394c) {
            this.f2394c = true;
            f2391n = z;
            mo9660a(false, new C0436b(z2));
        } else if (this.f2402k && this.f2401j) {
            this.f2400i = 0;
            if (mo9669d() > 0) {
                mo9658a(1);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo9664b(boolean r5) throws org.chromium.base.library_loader.ProcessInitException {
        /*
            r4 = this;
            YM2 r0 = YM2.b()
            boolean r1 = r4.f2397f
            boolean r2 = r4.f2402k
            r3 = 0
            int r1 = YM2.a(r1, r2, r3)
            r0.a(r1)
            boolean r0 = r4.f2397f
            if (r0 != 0) goto L_0x0047
            boolean r0 = r4.f2394c
            if (r0 == 0) goto L_0x001c
            boolean r0 = r4.f2395d
            if (r0 != 0) goto L_0x0020
        L_0x001c:
            r0 = 0
            r4.mo9660a(r5, r0)
        L_0x0020:
            boolean r5 = r4.f2396e
            r0 = 1
            if (r5 != 0) goto L_0x0031
            r4.f2400i = r3
            int r5 = r4.mo9669d()
            if (r5 <= 0) goto L_0x0041
            r4.mo9658a((int) r0)
            goto L_0x0042
        L_0x0031:
            int r5 = r4.f2400i
            if (r5 != r0) goto L_0x0041
            r4.f2400i = r3
            int r5 = r4.mo9669d()
            if (r5 <= 0) goto L_0x0041
            r4.mo9658a((int) r0)
            goto L_0x0042
        L_0x0041:
            r3 = 1
        L_0x0042:
            if (r3 == 0) goto L_0x0047
            r4.mo9670e()
        L_0x0047:
            boolean r5 = r4.f2398g
            if (r5 == 0) goto L_0x004c
            return
        L_0x004c:
            org.chromium.base.library_loader.ProcessInitException r5 = new org.chromium.base.library_loader.ProcessInitException
            r0 = 4
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.BrowserStartupControllerImpl.mo9664b(boolean):void");
    }

    /* renamed from: c */
    public int mo9666c(boolean z) {
        return ContentMain.nativeStart(z);
    }

    /* renamed from: d */
    public int mo9669d() {
        boolean z = this.f2400i == 1;
        int c = mo9666c(z);
        if (!z) {
            this.f2401j = false;
        }
        this.f2396e = true;
        return c;
    }

    /* renamed from: e */
    public void mo9670e() {
        nativeFlushStartupTasks();
    }

    /* renamed from: f */
    public void mo9671f() {
        YM2.b().a();
    }

    /* renamed from: c */
    public boolean mo9668c() {
        ThreadUtils.m1462c();
        return this.f2402k && !this.f2397f && this.f2398g;
    }

    /* renamed from: c */
    public final void mo9667c(int i) {
        this.f2398g = i <= 0;
        for (BrowserStartupController.StartupCallback next : this.f2393b) {
            if (this.f2398g) {
                next.onSuccess();
            } else {
                next.onFailure();
            }
        }
        this.f2393b.clear();
    }

    /* renamed from: a */
    public boolean mo9662a() {
        ThreadUtils.m1462c();
        return this.f2397f && this.f2398g;
    }

    /* renamed from: a */
    public void mo9659a(BrowserStartupController.StartupCallback startupCallback) {
        ThreadUtils.m1462c();
        if (this.f2397f) {
            PostTask.m1566a(iQ2.e, new sM2(this, startupCallback), 0);
        } else {
            this.f2392a.add(startupCallback);
        }
    }

    /* renamed from: b */
    public boolean mo9665b() {
        ThreadUtils.m1462c();
        return (this.f2402k || this.f2397f) && this.f2398g;
    }

    /* renamed from: b */
    public final void mo9663b(int i) {
        boolean z = true;
        this.f2397f = true;
        if (i > 0) {
            z = false;
        }
        this.f2398g = z;
        for (BrowserStartupController.StartupCallback next : this.f2392a) {
            if (this.f2398g) {
                next.onSuccess();
            } else {
                next.onFailure();
            }
        }
        this.f2392a.clear();
        mo9667c(i);
        mo9671f();
    }

    /* renamed from: a */
    public int mo9657a(boolean z) {
        return YM2.a(this.f2397f, this.f2402k, z);
    }

    /* renamed from: a */
    public final void mo9658a(int i) {
        PostTask.m1566a(iQ2.e, new C0437c(i), 0);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public void mo9660a(boolean z, Runnable runnable) throws ProcessInitException {
        VN0.b("cr.BrowserStartup", "Initializing chromium process, singleProcess=%b", new Object[]{Boolean.valueOf(z)});
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            LibraryLoader.f1501h.mo7901a(this.f2399h);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            C0438d dVar = new C0438d(z, runnable);
            dK3.d().a(iQ2.e);
            if (runnable == null) {
                dK3.d().c();
                dVar.run();
                return;
            }
            dK3.d().a(dVar);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }
}
