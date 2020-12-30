package com.facebook.datasource;

import android.util.Pair;
import com.facebook.react.uimanager.BaseViewManager;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* compiled from: PG */
public abstract class AbstractDataSource<T> implements Uq<T> {

    /* renamed from: a */
    public DataSourceStatus f249a = DataSourceStatus.IN_PROGRESS;

    /* renamed from: b */
    public boolean f250b = false;

    /* renamed from: c */
    public T f251c = null;

    /* renamed from: d */
    public Throwable f252d = null;

    /* renamed from: e */
    public float f253e = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: f */
    public final ConcurrentLinkedQueue<Pair<Xq<T>, Executor>> f254f = new ConcurrentLinkedQueue<>();

    /* compiled from: PG */
    public enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    /* renamed from: a */
    public void mo339a(T t) {
    }

    /* renamed from: a */
    public synchronized boolean mo340a() {
        return this.f251c != null;
    }

    /* renamed from: b */
    public synchronized Throwable mo344b() {
        return this.f252d;
    }

    /* renamed from: c */
    public synchronized float mo348c() {
        return this.f253e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        mo339a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (mo352f() != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        mo353g();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.f254f.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r1 == null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.f250b     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            return r0
        L_0x0008:
            r0 = 1
            r3.f250b = r0     // Catch:{ all -> 0x002a }
            T r1 = r3.f251c     // Catch:{ all -> 0x002a }
            r2 = 0
            r3.f251c = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0016
            r3.mo339a(r1)
        L_0x0016:
            boolean r1 = r3.mo352f()
            if (r1 != 0) goto L_0x001f
            r3.mo353g()
        L_0x001f:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<Xq<T>, java.util.concurrent.Executor>> r1 = r3.f254f     // Catch:{ all -> 0x0027 }
            r1.clear()     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            return r0
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.close():boolean");
    }

    /* renamed from: d */
    public synchronized boolean mo350d() {
        return this.f249a == DataSourceStatus.FAILURE;
    }

    /* renamed from: e */
    public synchronized boolean mo351e() {
        return this.f250b;
    }

    /* renamed from: f */
    public synchronized boolean mo352f() {
        return this.f249a != DataSourceStatus.IN_PROGRESS;
    }

    /* renamed from: g */
    public final void mo353g() {
        boolean d = mo350d();
        boolean h = mo355h();
        Iterator<Pair<Xq<T>, Executor>> it = this.f254f.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            ((Executor) next.second).execute(new Rq(this, d, (Xq) next.first, h));
        }
    }

    public synchronized T getResult() {
        return this.f251c;
    }

    /* renamed from: h */
    public final synchronized boolean mo355h() {
        return mo351e() && !mo352f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0031, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0033, code lost:
        r5.execute(new Rq(r3, mo350d(), r4, mo355h()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo338a(Xq<T> r4, java.util.concurrent.Executor r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x004d
            if (r5 == 0) goto L_0x0047
            monitor-enter(r3)
            boolean r0 = r3.f250b     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            return
        L_0x000b:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r3.f249a     // Catch:{ all -> 0x0044 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0044 }
            if (r0 != r1) goto L_0x001a
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<Xq<T>, java.util.concurrent.Executor>> r0 = r3.f254f     // Catch:{ all -> 0x0044 }
            android.util.Pair r1 = android.util.Pair.create(r4, r5)     // Catch:{ all -> 0x0044 }
            r0.add(r1)     // Catch:{ all -> 0x0044 }
        L_0x001a:
            boolean r0 = r3.mo340a()     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x002f
            boolean r0 = r3.mo352f()     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x002f
            boolean r0 = r3.mo355h()     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x002d
            goto L_0x002f
        L_0x002d:
            r0 = 0
            goto L_0x0030
        L_0x002f:
            r0 = 1
        L_0x0030:
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0043
            boolean r0 = r3.mo350d()
            boolean r1 = r3.mo355h()
            Rq r2 = new Rq
            r2.<init>(r3, r0, r4, r1)
            r5.execute(r2)
        L_0x0043:
            return
        L_0x0044:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0044 }
            throw r4
        L_0x0047:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            r4.<init>()
            throw r4
        L_0x004d:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.mo338a(Xq, java.util.concurrent.Executor):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0027, code lost:
        if (r4 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0029, code lost:
        mo339a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002c, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0034, code lost:
        return false;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo346b(T r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x003c }
            boolean r1 = r3.f250b     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x002d
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = r3.f249a     // Catch:{ all -> 0x0039 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0039 }
            if (r1 == r2) goto L_0x000d
            goto L_0x002d
        L_0x000d:
            if (r5 == 0) goto L_0x0017
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r5 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.SUCCESS     // Catch:{ all -> 0x0039 }
            r3.f249a = r5     // Catch:{ all -> 0x0039 }
            r5 = 1065353216(0x3f800000, float:1.0)
            r3.f253e = r5     // Catch:{ all -> 0x0039 }
        L_0x0017:
            T r5 = r3.f251c     // Catch:{ all -> 0x0039 }
            if (r5 == r4) goto L_0x0024
            T r5 = r3.f251c     // Catch:{ all -> 0x0039 }
            r3.f251c = r4     // Catch:{ all -> 0x0021 }
            r4 = r5
            goto L_0x0025
        L_0x0021:
            r4 = move-exception
            r0 = r5
            goto L_0x003a
        L_0x0024:
            r4 = r0
        L_0x0025:
            r5 = 1
            monitor-exit(r3)     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x002c
            r3.mo339a(r4)
        L_0x002c:
            return r5
        L_0x002d:
            r5 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x0034
            r3.mo339a(r4)
        L_0x0034:
            return r5
        L_0x0035:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x003a
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            throw r4     // Catch:{ all -> 0x003c }
        L_0x003c:
            r4 = move-exception
            if (r0 == 0) goto L_0x0042
            r3.mo339a(r0)
        L_0x0042:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.mo346b(java.lang.Object, boolean):boolean");
    }

    /* renamed from: a */
    public boolean mo342a(T t, boolean z) {
        boolean b = mo346b(t, z);
        if (b) {
            mo353g();
        }
        return b;
    }

    /* renamed from: a */
    public boolean mo343a(Throwable th) {
        boolean b = mo347b(th);
        if (b) {
            mo353g();
        }
        return b;
    }

    /* renamed from: b */
    public final synchronized boolean mo347b(Throwable th) {
        if (!this.f250b) {
            if (this.f249a == DataSourceStatus.IN_PROGRESS) {
                this.f249a = DataSourceStatus.FAILURE;
                this.f252d = th;
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo341a(float f) {
        boolean b = mo345b(f);
        if (b) {
            Iterator<Pair<Xq<T>, Executor>> it = this.f254f.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                ((Executor) next.second).execute(new Sq(this, (Xq) next.first));
            }
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        return false;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean mo345b(float r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.f250b     // Catch:{ all -> 0x001c }
            r1 = 0
            if (r0 != 0) goto L_0x001a
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r3.f249a     // Catch:{ all -> 0x001c }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x001c }
            if (r0 == r2) goto L_0x000d
            goto L_0x001a
        L_0x000d:
            float r0 = r3.f253e     // Catch:{ all -> 0x001c }
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0015
            monitor-exit(r3)
            return r1
        L_0x0015:
            r3.f253e = r4     // Catch:{ all -> 0x001c }
            r4 = 1
            monitor-exit(r3)
            return r4
        L_0x001a:
            monitor-exit(r3)
            return r1
        L_0x001c:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.mo345b(float):boolean");
    }
}
