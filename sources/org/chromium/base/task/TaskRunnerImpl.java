package org.chromium.base.task;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.chromium.base.LifetimeAssert;

/* compiled from: PG */
public class TaskRunnerImpl implements TP0 {

    /* renamed from: a */
    public final VP0 f1541a;

    /* renamed from: b */
    public final String f1542b;

    /* renamed from: c */
    public final int f1543c;

    /* renamed from: d */
    public final Object f1544d = new Object();

    /* renamed from: e */
    public long f1545e;

    /* renamed from: f */
    public final Runnable f1546f = new UP0(this);

    /* renamed from: g */
    public boolean f1547g;

    /* renamed from: h */
    public final LifetimeAssert f1548h = null;

    /* renamed from: i */
    public LinkedList<Runnable> f1549i = new LinkedList<>();

    /* renamed from: j */
    public List<Pair<Runnable, Long>> f1550j = new ArrayList();

    public TaskRunnerImpl(VP0 vp0, String str, int i) {
        boolean z;
        this.f1541a = vp0;
        this.f1542b = Eo.a(str, ".PreNativeTask.run");
        this.f1543c = i;
        Set<TP0> set = PostTask.f1538b;
        if (set != null) {
            set.add(this);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            mo7948a();
        }
    }

    private native void nativeDestroy(long j);

    private native long nativeInit(int i, boolean z, int i2, boolean z2, boolean z3, byte b, byte[] bArr);

    private native void nativePostDelayedTask(long j, Runnable runnable, long j2);

    /* renamed from: a */
    public void mo7949a(Runnable runnable) {
        mo7950a(runnable, 0);
    }

    /* renamed from: b */
    public void mo7951b() {
    }

    /* renamed from: b */
    public void mo7952b(Runnable runnable, long j) {
        nativePostDelayedTask(this.f1545e, runnable, j);
    }

    /* renamed from: d */
    public void mo7953d() {
        long j = this.f1545e;
        if (j != 0) {
            nativeDestroy(j);
        }
        this.f1545e = 0;
    }

    public void destroy() {
        synchronized (this.f1544d) {
            this.f1547g = true;
            mo7953d();
        }
    }

    /* renamed from: e */
    public void mo7955e() {
        if (this.f1545e == 0) {
            int i = this.f1543c;
            VP0 vp0 = this.f1541a;
            this.f1545e = nativeInit(i, vp0.a, vp0.b, vp0.c, vp0.d, vp0.e, vp0.f);
        }
    }

    /* renamed from: f */
    public void mo7956f() {
        LinkedList<Runnable> linkedList = this.f1549i;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                mo7952b((Runnable) it.next(), 0);
            }
            for (Pair next : this.f1550j) {
                mo7952b((Runnable) next.first, ((Long) next.second).longValue());
            }
            this.f1549i = null;
            this.f1550j = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r4.f1541a.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        if (r1 == 1) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        if (r1 == 2) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        android.os.Process.setThreadPriority(10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002d, code lost:
        android.os.Process.setThreadPriority(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        android.os.Process.setThreadPriority(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r2.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0044, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0045, code lost:
        if (r0 != null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x004c, code lost:
        qI.a.a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0051, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0.close();
     */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7957g() {
        /*
            r4 = this;
            java.lang.String r0 = r4.f1542b
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            java.lang.Object r1 = r4.f1544d     // Catch:{ all -> 0x0042 }
            monitor-enter(r1)     // Catch:{ all -> 0x0042 }
            java.util.LinkedList<java.lang.Runnable> r2 = r4.f1549i     // Catch:{ all -> 0x003f }
            if (r2 != 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0013
            r0.close()
        L_0x0013:
            return
        L_0x0014:
            java.util.LinkedList<java.lang.Runnable> r2 = r4.f1549i     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x003f }
            java.lang.Runnable r2 = (java.lang.Runnable) r2     // Catch:{ all -> 0x003f }
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            VP0 r1 = r4.f1541a     // Catch:{ all -> 0x0042 }
            int r1 = r1.b     // Catch:{ all -> 0x0042 }
            r3 = 1
            if (r1 == r3) goto L_0x0032
            r3 = 2
            if (r1 == r3) goto L_0x002d
            r1 = 10
            android.os.Process.setThreadPriority(r1)     // Catch:{ all -> 0x0042 }
            goto L_0x0036
        L_0x002d:
            r1 = -1
            android.os.Process.setThreadPriority(r1)     // Catch:{ all -> 0x0042 }
            goto L_0x0036
        L_0x0032:
            r1 = 0
            android.os.Process.setThreadPriority(r1)     // Catch:{ all -> 0x0042 }
        L_0x0036:
            r2.run()     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            return
        L_0x003f:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            throw r2     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r2 = move-exception
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ all -> 0x004b }
            goto L_0x0051
        L_0x004b:
            r0 = move-exception
            kI r3 = qI.a
            r3.a(r1, r0)
        L_0x0051:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.task.TaskRunnerImpl.mo7957g():void");
    }

    /* renamed from: h */
    public void mo7958h() {
        PostTask.m1563a().execute(this.f1546f);
    }

    public native boolean nativeBelongsToCurrentThread(long j);

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7950a(java.lang.Runnable r5, long r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.f1544d
            monitor-enter(r0)
            java.util.LinkedList<java.lang.Runnable> r1 = r4.f1549i     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x000c
            r4.mo7952b(r5, r6)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x000c:
            r1 = 0
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x001b
            java.util.LinkedList<java.lang.Runnable> r6 = r4.f1549i     // Catch:{ all -> 0x002d }
            r6.add(r5)     // Catch:{ all -> 0x002d }
            r4.mo7958h()     // Catch:{ all -> 0x002d }
            goto L_0x0029
        L_0x001b:
            android.util.Pair r1 = new android.util.Pair     // Catch:{ all -> 0x002d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x002d }
            r1.<init>(r5, r6)     // Catch:{ all -> 0x002d }
            java.util.List<android.util.Pair<java.lang.Runnable, java.lang.Long>> r5 = r4.f1550j     // Catch:{ all -> 0x002d }
            r5.add(r1)     // Catch:{ all -> 0x002d }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        L_0x002d:
            r5 = move-exception
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.task.TaskRunnerImpl.mo7950a(java.lang.Runnable, long):void");
    }

    /* renamed from: a */
    public void mo7948a() {
        synchronized (this.f1544d) {
            mo7955e();
            mo7956f();
        }
    }
}
