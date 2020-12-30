package org.chromium.base;

import android.os.Process;
import android.os.StrictMode;
import android.os.SystemClock;
import com.microsoft.aad.adal.AuthenticationRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class EarlyTraceEvent {

    /* renamed from: a */
    public static boolean f1423a;

    /* renamed from: b */
    public static final Object f1424b = new Object();

    /* renamed from: c */
    public static volatile int f1425c;

    /* renamed from: d */
    public static List<C0364b> f1426d;

    /* renamed from: e */
    public static Map<String, C0364b> f1427e;

    /* renamed from: f */
    public static List<C0363a> f1428f;

    /* renamed from: g */
    public static List<String> f1429g;

    /* renamed from: org.chromium.base.EarlyTraceEvent$a */
    /* compiled from: PG */
    public static final class C0363a {

        /* renamed from: a */
        public final boolean f1430a;

        /* renamed from: b */
        public final String f1431b;

        /* renamed from: c */
        public final long f1432c;

        /* renamed from: d */
        public final long f1433d = SystemClock.elapsedRealtimeNanos();

        public C0363a(String str, long j, boolean z) {
            this.f1431b = str;
            this.f1432c = j;
            this.f1430a = z;
        }
    }

    /* renamed from: org.chromium.base.EarlyTraceEvent$b */
    /* compiled from: PG */
    public static final class C0364b {

        /* renamed from: a */
        public final String f1434a;

        /* renamed from: b */
        public final int f1435b = Process.myTid();

        /* renamed from: c */
        public final long f1436c = SystemClock.elapsedRealtimeNanos();

        /* renamed from: d */
        public final long f1437d = SystemClock.currentThreadTimeMillis();

        /* renamed from: e */
        public long f1438e;

        /* renamed from: f */
        public long f1439f;

        public C0364b(String str) {
            this.f1434a = str;
        }
    }

    /* renamed from: a */
    public static void m1416a() {
        synchronized (f1424b) {
            if (m1423c()) {
                f1425c = 2;
                m1427g();
            }
        }
    }

    /* renamed from: b */
    public static void m1419b() {
        synchronized (f1424b) {
            if (f1425c == 0) {
                f1426d = new ArrayList();
                f1427e = new HashMap();
                f1428f = new ArrayList();
                f1429g = new ArrayList();
                f1425c = 1;
            }
        }
    }

    /* renamed from: c */
    public static boolean m1423c() {
        return f1425c == 1;
    }

    /* renamed from: d */
    public static long m1424d() {
        return (TimeUtils.nativeGetTimeTicksNowUs() * 1000) - SystemClock.elapsedRealtimeNanos();
    }

    /* renamed from: e */
    public static boolean m1425e() {
        int i = f1425c;
        return i == 1 || i == 2;
    }

    /* renamed from: f */
    public static void m1426f() {
        boolean z;
        ThreadUtils.m1462c();
        if (f1425c == 0) {
            StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                if (CommandLine.m1384c().mo7859c("trace-startup")) {
                    z = true;
                } else {
                    try {
                        z = new File("/data/local/chrome-trace-config.json").exists();
                    } catch (SecurityException unused) {
                        z = false;
                    }
                }
                if (QN0.a.getBoolean("bg_startup_tracing", false)) {
                    if (z) {
                        setBackgroundStartupTracingFlag(false);
                        f1423a = false;
                    } else {
                        f1423a = true;
                        z = true;
                    }
                }
                if (z) {
                    m1419b();
                }
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
    }

    /* renamed from: g */
    public static void m1427g() {
        if (!f1426d.isEmpty()) {
            List<C0364b> list = f1426d;
            long d = m1424d();
            for (C0364b next : list) {
                nativeRecordEarlyEvent(next.f1434a, next.f1436c + d, next.f1438e + d, next.f1435b, next.f1439f - next.f1437d);
            }
            f1426d.clear();
        }
        if (!f1428f.isEmpty()) {
            List<C0363a> list2 = f1428f;
            long d2 = m1424d();
            for (C0363a next2 : list2) {
                if (next2.f1430a) {
                    nativeRecordEarlyStartAsyncEvent(next2.f1431b, next2.f1432c, next2.f1433d + d2);
                } else {
                    nativeRecordEarlyFinishAsyncEvent(next2.f1431b, next2.f1432c, next2.f1433d + d2);
                }
            }
            f1428f.clear();
        }
        if (f1427e.isEmpty() && f1429g.isEmpty()) {
            f1425c = 3;
            f1427e = null;
            f1426d = null;
            f1429g = null;
            f1428f = null;
        }
    }

    @CalledByNative
    public static boolean getBackgroundStartupTracingFlag() {
        return f1423a;
    }

    public static native void nativeRecordEarlyEvent(String str, long j, long j2, int i, long j3);

    public static native void nativeRecordEarlyFinishAsyncEvent(String str, long j, long j2);

    public static native void nativeRecordEarlyStartAsyncEvent(String str, long j, long j2);

    @CalledByNative
    public static void setBackgroundStartupTracingFlag(boolean z) {
        Eo.b(QN0.a, "bg_startup_tracing", z);
    }

    /* renamed from: c */
    public static String m1422c(String str) {
        StringBuilder c = Eo.c(str, AuthenticationRequest.UPN_DOMAIN_SUFFIX_DELIM);
        c.append(Process.myTid());
        return c.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r0 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        throw new java.lang.IllegalArgumentException(Eo.a("Multiple pending trace events can't have the same name: ", r4));
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1417a(java.lang.String r4) {
        /*
            boolean r0 = m1423c()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            org.chromium.base.EarlyTraceEvent$b r0 = new org.chromium.base.EarlyTraceEvent$b
            r0.<init>(r4)
            java.lang.Object r1 = f1424b
            monitor-enter(r1)
            boolean r2 = m1423c()     // Catch:{ all -> 0x0033 }
            if (r2 != 0) goto L_0x0017
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            return
        L_0x0017:
            java.util.Map<java.lang.String, org.chromium.base.EarlyTraceEvent$b> r2 = f1427e     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = m1422c(r4)     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r2.put(r3, r0)     // Catch:{ all -> 0x0033 }
            org.chromium.base.EarlyTraceEvent$b r0 = (org.chromium.base.EarlyTraceEvent.C0364b) r0     // Catch:{ all -> 0x0033 }
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            if (r0 != 0) goto L_0x0027
            return
        L_0x0027:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Multiple pending trace events can't have the same name: "
            java.lang.String r4 = Eo.a(r1, r4)
            r0.<init>(r4)
            throw r0
        L_0x0033:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.EarlyTraceEvent.m1417a(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        return;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1420b(java.lang.String r3) {
        /*
            boolean r0 = m1425e()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            java.lang.Object r0 = f1424b
            monitor-enter(r0)
            boolean r1 = m1425e()     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return
        L_0x0012:
            java.util.Map<java.lang.String, org.chromium.base.EarlyTraceEvent$b> r1 = f1427e     // Catch:{ all -> 0x003d }
            java.lang.String r3 = m1422c(r3)     // Catch:{ all -> 0x003d }
            java.lang.Object r3 = r1.remove(r3)     // Catch:{ all -> 0x003d }
            org.chromium.base.EarlyTraceEvent$b r3 = (org.chromium.base.EarlyTraceEvent.C0364b) r3     // Catch:{ all -> 0x003d }
            if (r3 != 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return
        L_0x0022:
            long r1 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch:{ all -> 0x003d }
            r3.f1438e = r1     // Catch:{ all -> 0x003d }
            long r1 = android.os.SystemClock.currentThreadTimeMillis()     // Catch:{ all -> 0x003d }
            r3.f1439f = r1     // Catch:{ all -> 0x003d }
            java.util.List<org.chromium.base.EarlyTraceEvent$b> r1 = f1426d     // Catch:{ all -> 0x003d }
            r1.add(r3)     // Catch:{ all -> 0x003d }
            int r3 = f1425c     // Catch:{ all -> 0x003d }
            r1 = 2
            if (r3 != r1) goto L_0x003b
            m1427g()     // Catch:{ all -> 0x003d }
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return
        L_0x003d:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.EarlyTraceEvent.m1420b(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1418a(java.lang.String r2, long r3) {
        /*
            boolean r0 = m1425e()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            org.chromium.base.EarlyTraceEvent$a r0 = new org.chromium.base.EarlyTraceEvent$a
            r1 = 0
            r0.<init>(r2, r3, r1)
            java.lang.Object r3 = f1424b
            monitor-enter(r3)
            boolean r4 = m1425e()     // Catch:{ all -> 0x0031 }
            if (r4 != 0) goto L_0x0018
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0018:
            java.util.List<java.lang.String> r4 = f1429g     // Catch:{ all -> 0x0031 }
            boolean r2 = r4.remove(r2)     // Catch:{ all -> 0x0031 }
            if (r2 != 0) goto L_0x0022
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0022:
            java.util.List<org.chromium.base.EarlyTraceEvent$a> r2 = f1428f     // Catch:{ all -> 0x0031 }
            r2.add(r0)     // Catch:{ all -> 0x0031 }
            int r2 = f1425c     // Catch:{ all -> 0x0031 }
            r4 = 2
            if (r2 != r4) goto L_0x002f
            m1427g()     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.EarlyTraceEvent.m1418a(java.lang.String, long):void");
    }

    /* renamed from: b */
    public static void m1421b(String str, long j) {
        if (m1423c()) {
            C0363a aVar = new C0363a(str, j, true);
            synchronized (f1424b) {
                if (m1423c()) {
                    f1428f.add(aVar);
                    f1429g.add(str);
                }
            }
        }
    }
}
