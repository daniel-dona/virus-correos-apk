package org.chromium.base.task;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class PostTask {

    /* renamed from: a */
    public static final Object f1537a = new Object();

    /* renamed from: b */
    public static Set<TP0> f1538b = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: c */
    public static final Executor f1539c = new IP0();

    /* renamed from: d */
    public static final SP0[] f1540d;

    static {
        SP0[] sp0Arr = new SP0[5];
        sp0Arr[0] = new KP0();
        f1540d = sp0Arr;
    }

    /* renamed from: a */
    public static LP0 m1561a(VP0 vp0) {
        LP0 c;
        synchronized (f1537a) {
            c = f1540d[vp0.e].c(vp0);
        }
        return c;
    }

    /* renamed from: b */
    public static TP0 m1567b(VP0 vp0) {
        TP0 b;
        synchronized (f1537a) {
            b = f1540d[vp0.e].b(vp0);
        }
        return b;
    }

    public static native void nativePostDelayedTask(boolean z, int i, boolean z2, boolean z3, byte b, byte[] bArr, Runnable runnable, long j);

    @CalledByNative
    public static void onNativeSchedulerReady() {
        synchronized (f1537a) {
            Set<TP0> set = f1538b;
            f1538b = null;
            for (TP0 a : set) {
                a.a();
            }
        }
    }

    @CalledByNative
    public static void onNativeSchedulerShutdown() {
        synchronized (f1537a) {
            f1538b = Collections.newSetFromMap(new WeakHashMap());
        }
    }

    /* renamed from: a */
    public static void m1566a(VP0 vp0, Runnable runnable, long j) {
        synchronized (f1537a) {
            if (f1538b == null) {
                if (!vp0.g) {
                    nativePostDelayedTask(vp0.a, vp0.b, vp0.c, vp0.d, vp0.e, vp0.f, runnable, j);
                }
            }
            f1540d[vp0.e].a(vp0, runnable, j);
        }
    }

    @Deprecated
    /* renamed from: b */
    public static void m1568b(VP0 vp0, Runnable runnable) {
        FutureTask futureTask = new FutureTask(runnable, (Object) null);
        m1565a(vp0, (Runnable) futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    /* renamed from: a */
    public static <T> T m1562a(VP0 vp0, Callable<T> callable) {
        FutureTask futureTask = new FutureTask(callable);
        m1565a(vp0, (Runnable) futureTask);
        try {
            return futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static void m1564a(int i, SP0 sp0) {
        synchronized (f1537a) {
            f1540d[i] = sp0;
        }
    }

    /* renamed from: a */
    public static Executor m1563a() {
        Executor executor;
        synchronized (f1537a) {
            executor = f1539c;
        }
        return executor;
    }

    /* renamed from: a */
    public static void m1565a(VP0 vp0, Runnable runnable) {
        if (f1540d[vp0.e].a(vp0)) {
            runnable.run();
        } else {
            m1566a(vp0, runnable, 0);
        }
    }
}
