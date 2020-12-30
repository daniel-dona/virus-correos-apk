package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class ThreadUtils {

    /* renamed from: a */
    public static final Object f1478a = new Object();

    /* renamed from: b */
    public static Handler f1479b;

    @Deprecated
    /* renamed from: a */
    public static <T> FutureTask<T> m1455a(FutureTask<T> futureTask) {
        if (m1467g()) {
            futureTask.run();
        } else {
            m1465e().post(futureTask);
        }
        return futureTask;
    }

    /* renamed from: a */
    public static void m1456a() {
    }

    @Deprecated
    /* renamed from: b */
    public static <T> T m1459b(Callable<T> callable) {
        try {
            FutureTask futureTask = new FutureTask(callable);
            m1455a(futureTask);
            return futureTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted waiting for callable", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Error occurred waiting for callable", e2);
        }
    }

    /* renamed from: b */
    public static void m1460b() {
    }

    /* renamed from: c */
    public static void m1462c() {
    }

    @Deprecated
    /* renamed from: c */
    public static void m1463c(Runnable runnable) {
        if (m1467g()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, (Object) null);
        m1465e().post(futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while waiting for runnable", e);
        }
    }

    /* renamed from: d */
    public static void m1464d() {
        if (!m1467g()) {
            throw new IllegalStateException("Must be called on the UI thread.");
        }
    }

    /* renamed from: e */
    public static Handler m1465e() {
        Handler handler;
        synchronized (f1478a) {
            if (f1479b == null) {
                f1479b = new Handler(Looper.getMainLooper());
            }
            handler = f1479b;
        }
        return handler;
    }

    /* renamed from: f */
    public static Looper m1466f() {
        return m1465e().getLooper();
    }

    /* renamed from: g */
    public static boolean m1467g() {
        return m1465e().getLooper() == Looper.myLooper();
    }

    @CalledByNative
    public static boolean isThreadPriorityAudio(int i) {
        return Process.getThreadPriority(i) == -16;
    }

    @CalledByNative
    public static void setThreadPriorityAudio(int i) {
        Process.setThreadPriority(i, -16);
    }

    @Deprecated
    /* renamed from: a */
    public static <T> FutureTask<T> m1454a(Callable<T> callable) {
        FutureTask<T> futureTask = new FutureTask<>(callable);
        m1455a(futureTask);
        return futureTask;
    }

    @Deprecated
    /* renamed from: a */
    public static void m1457a(Runnable runnable) {
        m1465e().post(runnable);
    }

    @Deprecated
    /* renamed from: a */
    public static void m1458a(Runnable runnable, long j) {
        m1465e().postDelayed(runnable, j);
    }

    @Deprecated
    /* renamed from: b */
    public static void m1461b(Runnable runnable) {
        if (m1467g()) {
            runnable.run();
        } else {
            m1465e().post(runnable);
        }
    }
}
