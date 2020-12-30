package org.chromium.chrome.browser.crash;

import java.lang.Thread;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.components.crash.CrashKeys;

@MainDex
/* compiled from: PG */
public class PureJavaExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: c */
    public static boolean f1712c;

    /* renamed from: a */
    public final Thread.UncaughtExceptionHandler f1713a;

    /* renamed from: b */
    public boolean f1714b;

    public PureJavaExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f1713a = uncaughtExceptionHandler;
    }

    @CalledByNative
    public static void uninstallHandler() {
        f1712c = true;
        CrashKeys.getInstance().flushToNative();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0042, code lost:
        qI.a.a(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0047, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r5, java.lang.Throwable r6) {
        /*
            r4 = this;
            boolean r0 = r4.f1714b
            if (r0 != 0) goto L_0x0048
            boolean r0 = f1712c
            if (r0 != 0) goto L_0x0048
            r0 = 1
            r4.f1714b = r0
            LJ1 r1 = new LJ1
            r1.<init>()
            pO0 r2 = pO0.b()
            r1.a(r6)     // Catch:{ all -> 0x003a }
            java.io.FileOutputStream r3 = r1.b     // Catch:{ all -> 0x003a }
            if (r3 == 0) goto L_0x0029
            r3.flush()     // Catch:{ all -> 0x0024 }
            java.io.FileOutputStream r3 = r1.b     // Catch:{ all -> 0x0024 }
            r3.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0029
        L_0x0024:
            r3 = 0
            r1.b = r3     // Catch:{ all -> 0x003a }
            r1.a = r3     // Catch:{ all -> 0x003a }
        L_0x0029:
            java.io.File r1 = r1.a     // Catch:{ all -> 0x003a }
            if (r1 != 0) goto L_0x002e
            goto L_0x0036
        L_0x002e:
            GJ1 r3 = new GJ1     // Catch:{ all -> 0x003a }
            r3.<init>(r1)     // Catch:{ all -> 0x003a }
            r3.a(r0)     // Catch:{ all -> 0x003a }
        L_0x0036:
            r2.close()
            goto L_0x0048
        L_0x003a:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003c }
        L_0x003c:
            r6 = move-exception
            r2.close()     // Catch:{ all -> 0x0041 }
            goto L_0x0047
        L_0x0041:
            r0 = move-exception
            kI r1 = qI.a
            r1.a(r5, r0)
        L_0x0047:
            throw r6
        L_0x0048:
            java.lang.Thread$UncaughtExceptionHandler r0 = r4.f1713a
            if (r0 == 0) goto L_0x004f
            r0.uncaughtException(r5, r6)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.crash.PureJavaExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
