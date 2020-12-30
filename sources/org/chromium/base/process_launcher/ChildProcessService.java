package org.chromium.base.process_launcher;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.SparseArray;
import java.util.List;
import org.chromium.base.CommandLine;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.MainDex;
import tP0;

@MainDex
/* compiled from: PG */
public abstract class ChildProcessService extends Service {

    /* renamed from: r3 */
    public static boolean f1518r3;

    /* renamed from: a */
    public final rP0 f1519a;

    /* renamed from: b */
    public final Object f1520b = new Object();

    /* renamed from: c */
    public final Object f1521c = new Object();

    /* renamed from: d */
    public boolean f1522d;

    /* renamed from: e */
    public int f1523e;

    /* renamed from: k */
    public Thread f1524k;

    /* renamed from: n */
    public String[] f1525n;

    /* renamed from: p */
    public FileDescriptorInfo[] f1526p;

    /* renamed from: q */
    public boolean f1527q;

    /* renamed from: q3 */
    public final tP0.a f1528q3 = new C0376a();

    /* renamed from: x */
    public boolean f1529x;

    /* renamed from: y */
    public wP0 f1530y;

    /* renamed from: org.chromium.base.process_launcher.ChildProcessService$a */
    /* compiled from: PG */
    public class C0376a extends tP0.a {
        static {
            Class<ChildProcessService> cls = ChildProcessService.class;
        }

        public C0376a() {
        }

        /* renamed from: i */
        public static final /* synthetic */ void m1555i(int i) {
            if (i >= DO0.k.c()) {
                DO0.k.a(i);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
            r4.a(android.os.Process.myPid());
            r0 = r2.f1531a;
            r0.f1530y = r4;
            r0.mo7929a(r3, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
            return;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo7938a(android.os.Bundle r3, wP0 r4, java.util.List<android.os.IBinder> r5) throws android.os.RemoteException {
            /*
                r2 = this;
                org.chromium.base.process_launcher.ChildProcessService r0 = org.chromium.base.process_launcher.ChildProcessService.this
                java.lang.Object r0 = r0.f1520b
                monitor-enter(r0)
                org.chromium.base.process_launcher.ChildProcessService r1 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x0031 }
                boolean r1 = r1.f1522d     // Catch:{ all -> 0x0031 }
                if (r1 == 0) goto L_0x0021
                org.chromium.base.process_launcher.ChildProcessService r1 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x0031 }
                int r1 = r1.f1523e     // Catch:{ all -> 0x0031 }
                if (r1 != 0) goto L_0x0021
                java.lang.String r3 = "ChildProcessService"
                java.lang.String r5 = "Service has not been bound with bindToCaller()"
                r1 = 0
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0031 }
                VN0.a(r3, r5, r1)     // Catch:{ all -> 0x0031 }
                r3 = -1
                r4.a(r3)     // Catch:{ all -> 0x0031 }
                monitor-exit(r0)     // Catch:{ all -> 0x0031 }
                return
            L_0x0021:
                monitor-exit(r0)     // Catch:{ all -> 0x0031 }
                int r0 = android.os.Process.myPid()
                r4.a(r0)
                org.chromium.base.process_launcher.ChildProcessService r0 = org.chromium.base.process_launcher.ChildProcessService.this
                r0.f1530y = r4
                r0.mo7929a(r3, r5)
                return
            L_0x0031:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0031 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.process_launcher.ChildProcessService.C0376a.mo7938a(android.os.Bundle, wP0, java.util.List):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
            return true;
         */
        /* renamed from: c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mo7939c() {
            /*
                r8 = this;
                org.chromium.base.process_launcher.ChildProcessService r0 = org.chromium.base.process_launcher.ChildProcessService.this
                java.lang.Object r0 = r0.f1520b
                monitor-enter(r0)
                int r1 = android.os.Binder.getCallingPid()     // Catch:{ all -> 0x003a }
                org.chromium.base.process_launcher.ChildProcessService r2 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x003a }
                int r2 = r2.f1523e     // Catch:{ all -> 0x003a }
                r3 = 1
                if (r2 != 0) goto L_0x0015
                org.chromium.base.process_launcher.ChildProcessService r2 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x003a }
                r2.f1523e = r1     // Catch:{ all -> 0x003a }
                goto L_0x0038
            L_0x0015:
                org.chromium.base.process_launcher.ChildProcessService r2 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x003a }
                int r2 = r2.f1523e     // Catch:{ all -> 0x003a }
                if (r2 == r1) goto L_0x0038
                java.lang.String r2 = "ChildProcessService"
                java.lang.String r4 = "Service is already bound by pid %d, cannot bind for pid %d"
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x003a }
                org.chromium.base.process_launcher.ChildProcessService r6 = org.chromium.base.process_launcher.ChildProcessService.this     // Catch:{ all -> 0x003a }
                int r6 = r6.f1523e     // Catch:{ all -> 0x003a }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x003a }
                r7 = 0
                r5[r7] = r6     // Catch:{ all -> 0x003a }
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x003a }
                r5[r3] = r1     // Catch:{ all -> 0x003a }
                VN0.a(r2, r4, r5)     // Catch:{ all -> 0x003a }
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                return r7
            L_0x0038:
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                return r3
            L_0x003a:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.process_launcher.ChildProcessService.C0376a.mo7939c():boolean");
        }

        /* renamed from: f */
        public void mo7940f(int i) {
            ThreadUtils.m1457a((Runnable) new qP0(i));
        }

        /* renamed from: q */
        public void mo7941q() {
            synchronized (ChildProcessService.this.f1521c) {
                if (!ChildProcessService.this.f1527q) {
                    VN0.a("ChildProcessService", "Cannot dump process stack before native is loaded", new Object[0]);
                } else {
                    ChildProcessService.nativeDumpProcessStack();
                }
            }
        }

        /* renamed from: v */
        public void mo7942v() {
            Process.killProcess(Process.myPid());
        }
    }

    /* renamed from: org.chromium.base.process_launcher.ChildProcessService$b */
    /* compiled from: PG */
    public class C0377b implements Runnable {
        static {
            Class<ChildProcessService> cls = ChildProcessService.class;
        }

        public C0377b() {
        }

        public void run() {
            boolean z;
            try {
                synchronized (ChildProcessService.this.f1524k) {
                    while (ChildProcessService.this.f1525n == null) {
                        ChildProcessService.this.f1524k.wait();
                    }
                }
                CommandLine.m1383b(ChildProcessService.this.f1525n);
                if (CommandLine.m1384c().mo7859c("renderer-wait-for-java-debugger")) {
                    Debug.waitForDebugger();
                }
                try {
                    z = ChildProcessService.this.f1519a.a(ChildProcessService.this.getApplicationContext());
                } catch (Exception e) {
                    VN0.a("ChildProcessService", "Failed to load native library.", new Object[]{e});
                    z = false;
                }
                if (!z) {
                    System.exit(-1);
                }
                synchronized (ChildProcessService.this.f1521c) {
                    ChildProcessService.this.f1527q = true;
                    ChildProcessService.this.f1521c.notifyAll();
                }
                synchronized (ChildProcessService.this.f1524k) {
                    ChildProcessService.this.f1524k.notifyAll();
                    while (ChildProcessService.this.f1526p == null) {
                        ChildProcessService.this.f1524k.wait();
                    }
                }
                SparseArray c = ChildProcessService.this.f1519a.c();
                int[] iArr = new int[ChildProcessService.this.f1526p.length];
                String[] strArr = new String[ChildProcessService.this.f1526p.length];
                int[] iArr2 = new int[ChildProcessService.this.f1526p.length];
                long[] jArr = new long[ChildProcessService.this.f1526p.length];
                long[] jArr2 = new long[ChildProcessService.this.f1526p.length];
                for (int i = 0; i < ChildProcessService.this.f1526p.length; i++) {
                    FileDescriptorInfo fileDescriptorInfo = ChildProcessService.this.f1526p[i];
                    String str = c != null ? (String) c.get(fileDescriptorInfo.f1533a) : null;
                    if (str != null) {
                        strArr[i] = str;
                    } else {
                        iArr[i] = fileDescriptorInfo.f1533a;
                    }
                    iArr2[i] = fileDescriptorInfo.f1534b.detachFd();
                    jArr[i] = fileDescriptorInfo.f1535c;
                    jArr2[i] = fileDescriptorInfo.f1536d;
                }
                ChildProcessService.nativeRegisterFileDescriptors(strArr, iArr, iArr2, jArr, jArr2);
                ChildProcessService.this.f1519a.a();
                ChildProcessService.this.f1519a.d();
                try {
                    ChildProcessService.this.f1530y.g();
                } catch (RemoteException e2) {
                    VN0.a("ChildProcessService", "Failed to call clean exit callback.", new Object[]{e2});
                }
                ChildProcessService.nativeExitChildProcess();
            } catch (InterruptedException e3) {
                VN0.c("ChildProcessService", "%s startup failed: %s", new Object[]{"ChildProcessMain", e3});
            }
        }
    }

    static {
        Class<ChildProcessService> cls = ChildProcessService.class;
    }

    public ChildProcessService(rP0 rp0) {
        this.f1519a = rp0;
    }

    public static native void nativeDumpProcessStack();

    public static native void nativeExitChildProcess();

    public static native void nativeRegisterFileDescriptors(String[] strArr, int[] iArr, int[] iArr2, long[] jArr, long[] jArr2);

    /* renamed from: a */
    public final /* synthetic */ void mo7928a() {
        this.f1519a.b(getApplicationContext());
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public IBinder onBind(Intent intent) {
        if (this.f1529x) {
            return this.f1528q3;
        }
        stopSelf();
        this.f1522d = intent.getBooleanExtra("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        this.f1529x = true;
        this.f1519a.a(intent);
        new Handler(Looper.getMainLooper()).post(new pP0(this));
        return this.f1528q3;
    }

    public void onCreate() {
        super.onCreate();
        VN0.b("ChildProcessService", "Creating new ChildProcessService pid=%d", new Object[]{Integer.valueOf(Process.myPid())});
        if (!f1518r3) {
            f1518r3 = true;
            RN0.a = getApplicationContext();
            this.f1519a.b();
            this.f1524k = new Thread(new C0377b(), "ChildProcessMain");
            this.f1524k.start();
            return;
        }
        throw new RuntimeException("Illegal child process reuse.");
    }

    public void onDestroy() {
        super.onDestroy();
        VN0.b("ChildProcessService", "Destroying ChildProcessService pid=%d", new Object[]{Integer.valueOf(Process.myPid())});
        System.exit(0);
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: a */
    public final void mo7929a(Bundle bundle, List<IBinder> list) {
        bundle.setClassLoader(getApplicationContext().getClassLoader());
        synchronized (this.f1524k) {
            if (this.f1525n == null) {
                this.f1525n = bundle.getStringArray("org.chromium.base.process_launcher.extra.command_line");
                this.f1524k.notifyAll();
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("org.chromium.base.process_launcher.extra.extraFiles");
            if (parcelableArray != null) {
                this.f1526p = new FileDescriptorInfo[parcelableArray.length];
                System.arraycopy(parcelableArray, 0, this.f1526p, 0, parcelableArray.length);
            }
            this.f1519a.a(bundle, list);
            this.f1524k.notifyAll();
        }
    }
}
