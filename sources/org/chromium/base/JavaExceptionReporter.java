package org.chromium.base;

import java.lang.Thread;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class JavaExceptionReporter implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    public final Thread.UncaughtExceptionHandler f1442a;

    /* renamed from: b */
    public boolean f1443b;

    static {
        Class<JavaExceptionReporter> cls = JavaExceptionReporter.class;
    }

    public JavaExceptionReporter(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this.f1442a = uncaughtExceptionHandler;
    }

    @CalledByNative
    public static void installHandler(boolean z) {
        Thread.setDefaultUncaughtExceptionHandler(new JavaExceptionReporter(Thread.getDefaultUncaughtExceptionHandler(), z));
    }

    public static native void nativeReportJavaException(boolean z, Throwable th);

    public static native void nativeReportJavaStackTrace(String str);

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.f1443b) {
            this.f1443b = true;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f1442a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
