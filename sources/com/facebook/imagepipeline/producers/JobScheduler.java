package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class JobScheduler {

    /* renamed from: a */
    public final Executor f373a;

    /* renamed from: b */
    public final JobRunnable f374b;

    /* renamed from: c */
    public final Runnable f375c = new tv(this);

    /* renamed from: d */
    public final Runnable f376d = new uv(this);

    /* renamed from: e */
    public final int f377e;

    /* renamed from: f */
    public Xt f378f = null;

    /* renamed from: g */
    public int f379g = 0;

    /* renamed from: h */
    public JobState f380h = JobState.IDLE;

    /* renamed from: i */
    public long f381i = 0;

    /* renamed from: j */
    public long f382j = 0;

    /* compiled from: PG */
    public interface JobRunnable {
        void run(Xt xt, int i);
    }

    /* compiled from: PG */
    public enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int i) {
        this.f373a = executor;
        this.f374b = jobRunnable;
        this.f377e = i;
    }

    /* renamed from: a */
    public final void mo534a(long j) {
        if (j > 0) {
            if (vv.a == null) {
                vv.a = Executors.newSingleThreadScheduledExecutor();
            }
            vv.a.schedule(this.f376d, j, TimeUnit.MILLISECONDS);
            return;
        }
        this.f376d.run();
    }

    /* renamed from: b */
    public final void mo536b() {
        Xt xt;
        int i;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            xt = this.f378f;
            i = this.f379g;
            this.f378f = null;
            this.f379g = 0;
            this.f380h = JobState.RUNNING;
            this.f382j = uptimeMillis;
        }
        try {
            if (m389b(xt, i)) {
                this.f374b.run(xt, i);
            }
        } finally {
            Xt.c(xt);
            mo538d();
        }
    }

    /* renamed from: c */
    public synchronized long mo537c() {
        return this.f382j - this.f381i;
    }

    /* renamed from: d */
    public final void mo538d() {
        boolean z;
        long j;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            if (this.f380h == JobState.RUNNING_AND_PENDING) {
                j = Math.max(this.f382j + ((long) this.f377e), uptimeMillis);
                z = true;
                this.f381i = uptimeMillis;
                this.f380h = JobState.QUEUED;
            } else {
                this.f380h = JobState.IDLE;
                j = 0;
                z = false;
            }
        }
        if (z) {
            mo534a(j - uptimeMillis);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r3 == false) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        mo534a(r5 - r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        return true;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo539e() {
        /*
            r7 = this;
            long r0 = android.os.SystemClock.uptimeMillis()
            monitor-enter(r7)
            Xt r2 = r7.f378f     // Catch:{ all -> 0x0041 }
            int r3 = r7.f379g     // Catch:{ all -> 0x0041 }
            boolean r2 = m389b(r2, r3)     // Catch:{ all -> 0x0041 }
            r3 = 0
            if (r2 != 0) goto L_0x0012
            monitor-exit(r7)     // Catch:{ all -> 0x0041 }
            return r3
        L_0x0012:
            com.facebook.imagepipeline.producers.JobScheduler$JobState r2 = r7.f380h     // Catch:{ all -> 0x0041 }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x0041 }
            r4 = 1
            if (r2 == 0) goto L_0x0028
            if (r2 == r4) goto L_0x0025
            r5 = 2
            if (r2 == r5) goto L_0x0021
            goto L_0x0025
        L_0x0021:
            com.facebook.imagepipeline.producers.JobScheduler$JobState r2 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING     // Catch:{ all -> 0x0041 }
            r7.f380h = r2     // Catch:{ all -> 0x0041 }
        L_0x0025:
            r5 = 0
            goto L_0x0039
        L_0x0028:
            long r2 = r7.f382j     // Catch:{ all -> 0x0041 }
            int r5 = r7.f377e     // Catch:{ all -> 0x0041 }
            long r5 = (long) r5     // Catch:{ all -> 0x0041 }
            long r2 = r2 + r5
            long r5 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x0041 }
            r7.f381i = r0     // Catch:{ all -> 0x0041 }
            com.facebook.imagepipeline.producers.JobScheduler$JobState r2 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED     // Catch:{ all -> 0x0041 }
            r7.f380h = r2     // Catch:{ all -> 0x0041 }
            r3 = 1
        L_0x0039:
            monitor-exit(r7)     // Catch:{ all -> 0x0041 }
            if (r3 == 0) goto L_0x0040
            long r5 = r5 - r0
            r7.mo534a(r5)
        L_0x0040:
            return r4
        L_0x0041:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0041 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.mo539e():boolean");
    }

    /* renamed from: a */
    public void mo533a() {
        Xt xt;
        synchronized (this) {
            xt = this.f378f;
            this.f378f = null;
            this.f379g = 0;
        }
        Xt.c(xt);
    }

    /* renamed from: a */
    public boolean mo535a(Xt xt, int i) {
        Xt xt2;
        if (!m389b(xt, i)) {
            return false;
        }
        synchronized (this) {
            xt2 = this.f378f;
            this.f378f = Xt.b(xt);
            this.f379g = i;
        }
        Xt.c(xt2);
        return true;
    }

    /* renamed from: b */
    public static boolean m389b(Xt xt, int i) {
        return Nu.a(i) || Nu.b(i, 4) || Xt.e(xt);
    }
}
