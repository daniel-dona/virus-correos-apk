package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.lang.Thread;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class JavaHandlerThread {

    /* renamed from: a */
    public final HandlerThread f1444a;

    /* renamed from: b */
    public Throwable f1445b;

    /* renamed from: org.chromium.base.JavaHandlerThread$a */
    /* compiled from: PG */
    public class C0365a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ long f1446a;

        /* renamed from: b */
        public final /* synthetic */ long f1447b;

        public C0365a(long j, long j2) {
            this.f1446a = j;
            this.f1447b = j2;
        }

        public void run() {
            JavaHandlerThread.this.nativeInitializeThread(this.f1446a, this.f1447b);
        }
    }

    /* renamed from: org.chromium.base.JavaHandlerThread$b */
    /* compiled from: PG */
    public class C0366b implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ long f1449a;

        public C0366b(long j) {
            this.f1449a = j;
        }

        public void run() {
            JavaHandlerThread.this.f1444a.quit();
            JavaHandlerThread.this.nativeOnLooperStopped(this.f1449a);
        }
    }

    /* renamed from: org.chromium.base.JavaHandlerThread$c */
    /* compiled from: PG */
    public class C0367c implements Thread.UncaughtExceptionHandler {
        public C0367c() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            JavaHandlerThread.this.f1445b = th;
        }
    }

    public JavaHandlerThread(String str, int i) {
        this.f1444a = new HandlerThread(str, i);
    }

    @CalledByNative
    public static JavaHandlerThread create(String str, int i) {
        return new JavaHandlerThread(str, i);
    }

    @CalledByNative
    private Throwable getUncaughtExceptionIfAny() {
        return this.f1445b;
    }

    @CalledByNative
    private boolean isAlive() {
        return this.f1444a.isAlive();
    }

    @CalledByNative
    private void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.f1444a.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    @CalledByNative
    private void listenForUncaughtExceptionsForTesting() {
        this.f1444a.setUncaughtExceptionHandler(new C0367c());
    }

    /* access modifiers changed from: private */
    public native void nativeInitializeThread(long j, long j2);

    /* access modifiers changed from: private */
    public native void nativeOnLooperStopped(long j);

    @CalledByNative
    private void quitThreadSafely(long j) {
        new Handler(this.f1444a.getLooper()).post(new C0366b(j));
        this.f1444a.getLooper().quitSafely();
    }

    @CalledByNative
    private void startAndInitialize(long j, long j2) {
        mo7863b();
        new Handler(this.f1444a.getLooper()).post(new C0365a(j, j2));
    }

    /* renamed from: b */
    public void mo7863b() {
        if (!(this.f1444a.getState() != Thread.State.NEW)) {
            this.f1444a.start();
        }
    }

    /* renamed from: a */
    public Looper mo7862a() {
        return this.f1444a.getLooper();
    }
}
