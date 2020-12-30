package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* compiled from: PG */
public abstract class BasePool<V> implements xq<V> {

    /* renamed from: a */
    public final Class<?> f358a = getClass();

    /* renamed from: b */
    public final vq f359b;

    /* renamed from: c */
    public final zu f360c;

    /* renamed from: d */
    public final SparseArray<ju<V>> f361d;

    /* renamed from: e */
    public final Set<V> f362e;

    /* renamed from: f */
    public boolean f363f;

    /* renamed from: g */
    public final eu f364g;

    /* renamed from: h */
    public final eu f365h;

    /* renamed from: i */
    public final Au f366i;

    /* compiled from: PG */
    public static class InvalidSizeException extends RuntimeException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InvalidSizeException(java.lang.Object r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Invalid size: "
                java.lang.StringBuilder r0 = Eo.a(r0)
                java.lang.String r2 = r2.toString()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.InvalidSizeException.<init>(java.lang.Object):void");
        }
    }

    /* compiled from: PG */
    public static class InvalidValueException extends RuntimeException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InvalidValueException(java.lang.Object r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Invalid value: "
                java.lang.StringBuilder r0 = Eo.a(r0)
                java.lang.String r2 = r2.toString()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.InvalidValueException.<init>(java.lang.Object):void");
        }
    }

    /* compiled from: PG */
    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }

    /* compiled from: PG */
    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object obj) {
            super(obj);
        }
    }

    public BasePool(vq vqVar, zu zuVar, Au au) {
        if (vqVar != null) {
            this.f359b = vqVar;
            if (zuVar != null) {
                this.f360c = zuVar;
                if (au != null) {
                    this.f366i = au;
                    this.f361d = new SparseArray<>();
                    if (this.f360c.d) {
                        mo500b();
                    } else {
                        mo497a(new SparseIntArray(0));
                    }
                    this.f362e = Collections.newSetFromMap(new IdentityHashMap());
                    this.f365h = new eu();
                    this.f364g = new eu();
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public abstract V mo495a(int i);

    /* renamed from: a */
    public final synchronized void mo496a() {
        boolean z;
        if (mo506d()) {
            if (this.f365h.b != 0) {
                z = false;
                kq.b(z);
            }
        }
        z = true;
        kq.b(z);
    }

    /* renamed from: a */
    public abstract void mo498a(V v);

    /* renamed from: b */
    public abstract int mo499b(V v);

    /* renamed from: b */
    public final synchronized void mo500b() {
        SparseIntArray sparseIntArray = this.f360c.c;
        if (sparseIntArray != null) {
            this.f361d.clear();
            for (int i = 0; i < sparseIntArray.size(); i++) {
                int keyAt = sparseIntArray.keyAt(i);
                this.f361d.put(keyAt, new ju(mo511g(keyAt), sparseIntArray.valueAt(i), 0, this.f360c.d));
            }
            this.f363f = false;
        } else {
            this.f363f = true;
        }
    }

    /* renamed from: c */
    public void mo503c() {
        this.f359b.a(this);
        this.f366i.a(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        return r0;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized ju<V> mo505d(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.util.SparseArray<ju<V>> r0 = r3.f361d     // Catch:{ all -> 0x002f }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x002f }
            ju r0 = (ju) r0     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x002d
            boolean r1 = r3.f363f     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0010
            goto L_0x002d
        L_0x0010:
            r0 = 2
            boolean r0 = pq.a(r0)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0022
            java.lang.Class<?> r0 = r3.f358a     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "creating new bucket %s"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x002f }
            pq.a(r0, r1, r2)     // Catch:{ all -> 0x002f }
        L_0x0022:
            ju r0 = r3.mo512h(r4)     // Catch:{ all -> 0x002f }
            android.util.SparseArray<ju<V>> r1 = r3.f361d     // Catch:{ all -> 0x002f }
            r1.put(r4, r0)     // Catch:{ all -> 0x002f }
            monitor-exit(r3)
            return r0
        L_0x002d:
            monitor-exit(r3)
            return r0
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.mo505d(int):ju");
    }

    /* renamed from: e */
    public final synchronized ju<V> mo507e(int i) {
        return (ju) this.f361d.get(i);
    }

    /* renamed from: f */
    public abstract int mo509f(int i);

    /* renamed from: f */
    public synchronized void mo510f() {
        if (mo506d()) {
            mo513i(this.f360c.b);
        }
    }

    /* renamed from: g */
    public abstract int mo511g(int i);

    /* renamed from: h */
    public ju<V> mo512h(int i) {
        return new ju<>(mo511g(i), Integer.MAX_VALUE, 0, this.f360c.d);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008b, code lost:
        return;
     */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo513i(int r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            eu r0 = r7.f364g     // Catch:{ all -> 0x008c }
            int r0 = r0.b     // Catch:{ all -> 0x008c }
            eu r1 = r7.f365h     // Catch:{ all -> 0x008c }
            int r1 = r1.b     // Catch:{ all -> 0x008c }
            int r0 = r0 + r1
            int r0 = r0 - r8
            eu r1 = r7.f365h     // Catch:{ all -> 0x008c }
            int r1 = r1.b     // Catch:{ all -> 0x008c }
            int r0 = java.lang.Math.min(r0, r1)     // Catch:{ all -> 0x008c }
            if (r0 > 0) goto L_0x0017
            monitor-exit(r7)
            return
        L_0x0017:
            r1 = 2
            boolean r2 = pq.a(r1)     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x003a
            java.lang.Class<?> r2 = r7.f358a     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x008c }
            eu r5 = r7.f364g     // Catch:{ all -> 0x008c }
            int r5 = r5.b     // Catch:{ all -> 0x008c }
            eu r6 = r7.f365h     // Catch:{ all -> 0x008c }
            int r6 = r6.b     // Catch:{ all -> 0x008c }
            int r5 = r5 + r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x008c }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x008c }
            pq.a(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008c }
        L_0x003a:
            r7.mo508e()     // Catch:{ all -> 0x008c }
            r2 = 0
        L_0x003e:
            android.util.SparseArray<ju<V>> r3 = r7.f361d     // Catch:{ all -> 0x008c }
            int r3 = r3.size()     // Catch:{ all -> 0x008c }
            if (r2 >= r3) goto L_0x0069
            if (r0 > 0) goto L_0x0049
            goto L_0x0069
        L_0x0049:
            android.util.SparseArray<ju<V>> r3 = r7.f361d     // Catch:{ all -> 0x008c }
            java.lang.Object r3 = r3.valueAt(r2)     // Catch:{ all -> 0x008c }
            ju r3 = (ju) r3     // Catch:{ all -> 0x008c }
        L_0x0051:
            if (r0 <= 0) goto L_0x0066
            java.lang.Object r4 = r3.b()     // Catch:{ all -> 0x008c }
            if (r4 != 0) goto L_0x005a
            goto L_0x0066
        L_0x005a:
            r7.mo498a(r4)     // Catch:{ all -> 0x008c }
            int r4 = r3.a     // Catch:{ all -> 0x008c }
            int r0 = r0 - r4
            eu r5 = r7.f365h     // Catch:{ all -> 0x008c }
            r5.a(r4)     // Catch:{ all -> 0x008c }
            goto L_0x0051
        L_0x0066:
            int r2 = r2 + 1
            goto L_0x003e
        L_0x0069:
            r7.mo508e()     // Catch:{ all -> 0x008c }
            boolean r0 = pq.a(r1)     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x008a
            java.lang.Class<?> r0 = r7.f358a     // Catch:{ all -> 0x008c }
            java.lang.String r1 = "trimToSize: TargetSize = %d; Final Size = %d"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x008c }
            eu r2 = r7.f364g     // Catch:{ all -> 0x008c }
            int r2 = r2.b     // Catch:{ all -> 0x008c }
            eu r3 = r7.f365h     // Catch:{ all -> 0x008c }
            int r3 = r3.b     // Catch:{ all -> 0x008c }
            int r2 = r2 + r3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x008c }
            pq.a(r0, r1, r8, r2)     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r7)
            return
        L_0x008c:
            r8 = move-exception
            monitor-exit(r7)
            goto L_0x0090
        L_0x008f:
            throw r8
        L_0x0090:
            goto L_0x008f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.mo513i(int):void");
    }

    public void release(V v) {
        if (v != null) {
            int b = mo499b(v);
            int g = mo511g(b);
            synchronized (this) {
                ju e = mo507e(b);
                boolean z = true;
                if (!this.f362e.remove(v)) {
                    pq.a(this.f358a, "release (free, value unrecognized) (object, size) = (%x, %s)", new Object[]{Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(b)});
                    mo498a(v);
                    this.f366i.b(g);
                } else {
                    if (e != null) {
                        if (e.e + e.c.size() <= e.b) {
                            z = false;
                        }
                        if (!z && !mo506d()) {
                            if (mo504c(v)) {
                                e.b(v);
                                this.f365h.b(g);
                                this.f364g.a(g);
                                this.f366i.c(g);
                                if (pq.a(2)) {
                                    pq.a(this.f358a, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(b));
                                }
                            }
                        }
                    }
                    if (e != null) {
                        e.a();
                    }
                    if (pq.a(2)) {
                        pq.a(this.f358a, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(b));
                    }
                    mo498a(v);
                    this.f364g.a(g);
                    this.f366i.b(g);
                }
                mo508e();
            }
            return;
        }
        throw new NullPointerException();
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    /* renamed from: e */
    public final void mo508e() {
        if (pq.a(2)) {
            pq.a(this.f358a, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.f364g.a), Integer.valueOf(this.f364g.b), Integer.valueOf(this.f365h.a), Integer.valueOf(this.f365h.b));
        }
    }

    /* renamed from: a */
    public final synchronized void mo497a(SparseIntArray sparseIntArray) {
        if (sparseIntArray != null) {
            this.f361d.clear();
            SparseIntArray sparseIntArray2 = this.f360c.c;
            if (sparseIntArray2 != null) {
                for (int i = 0; i < sparseIntArray2.size(); i++) {
                    int keyAt = sparseIntArray2.keyAt(i);
                    this.f361d.put(keyAt, new ju(mo511g(keyAt), sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0), this.f360c.d));
                }
                this.f363f = false;
            } else {
                this.f363f = true;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0 = mo495a(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r5.f364g.a(r2);
        r4 = mo505d(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0086, code lost:
        if (r4 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0088, code lost:
        r4.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008c, code lost:
        oq.b(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008f, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        kq.b(r5.f362e.add(r0));
        mo510f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r5.f366i.a(r2);
        mo508e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00aa, code lost:
        if (pq.a(2) == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        pq.a(r5.f358a, "get (alloc) (object, size) = (%x, %s)", java.lang.Integer.valueOf(java.lang.System.identityHashCode(r0)), java.lang.Integer.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c0, code lost:
        return r0;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V mo502c(int r6) {
        /*
            r5 = this;
            r5.mo496a()
            int r6 = r5.mo509f(r6)
            monitor-enter(r5)
            ju r0 = r5.mo505d(r6)     // Catch:{ all -> 0x00d9 }
            r1 = 2
            if (r0 == 0) goto L_0x005d
            java.lang.Object r2 = r0.b()     // Catch:{ all -> 0x00d9 }
            if (r2 == 0) goto L_0x001b
            int r3 = r0.e     // Catch:{ all -> 0x00d9 }
            int r3 = r3 + 1
            r0.e = r3     // Catch:{ all -> 0x00d9 }
        L_0x001b:
            if (r2 == 0) goto L_0x005d
            java.util.Set<V> r6 = r5.f362e     // Catch:{ all -> 0x00d9 }
            boolean r6 = r6.add(r2)     // Catch:{ all -> 0x00d9 }
            kq.b(r6)     // Catch:{ all -> 0x00d9 }
            int r6 = r5.mo499b(r2)     // Catch:{ all -> 0x00d9 }
            int r0 = r5.mo511g(r6)     // Catch:{ all -> 0x00d9 }
            eu r3 = r5.f364g     // Catch:{ all -> 0x00d9 }
            r3.b(r0)     // Catch:{ all -> 0x00d9 }
            eu r3 = r5.f365h     // Catch:{ all -> 0x00d9 }
            r3.a(r0)     // Catch:{ all -> 0x00d9 }
            Au r3 = r5.f366i     // Catch:{ all -> 0x00d9 }
            tu r3 = (tu) r3
            r3.d(r0)     // Catch:{ all -> 0x00d9 }
            r5.mo508e()     // Catch:{ all -> 0x00d9 }
            boolean r0 = pq.a(r1)     // Catch:{ all -> 0x00d9 }
            if (r0 == 0) goto L_0x005b
            java.lang.Class<?> r0 = r5.f358a     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = "get (reuse) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r2)     // Catch:{ all -> 0x00d9 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00d9 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00d9 }
            pq.a(r0, r1, r3, r6)     // Catch:{ all -> 0x00d9 }
        L_0x005b:
            monitor-exit(r5)     // Catch:{ all -> 0x00d9 }
            return r2
        L_0x005d:
            int r2 = r5.mo511g(r6)     // Catch:{ all -> 0x00d9 }
            boolean r3 = r5.mo501b((int) r2)     // Catch:{ all -> 0x00d9 }
            if (r3 == 0) goto L_0x00c7
            eu r3 = r5.f364g     // Catch:{ all -> 0x00d9 }
            r3.b(r2)     // Catch:{ all -> 0x00d9 }
            if (r0 == 0) goto L_0x0074
            int r3 = r0.e     // Catch:{ all -> 0x00d9 }
            int r3 = r3 + 1
            r0.e = r3     // Catch:{ all -> 0x00d9 }
        L_0x0074:
            monitor-exit(r5)     // Catch:{ all -> 0x00d9 }
            r0 = 0
            java.lang.Object r0 = r5.mo495a((int) r6)     // Catch:{ all -> 0x007b }
            goto L_0x008f
        L_0x007b:
            r3 = move-exception
            monitor-enter(r5)
            eu r4 = r5.f364g     // Catch:{ all -> 0x00c4 }
            r4.a(r2)     // Catch:{ all -> 0x00c4 }
            ju r4 = r5.mo505d(r6)     // Catch:{ all -> 0x00c4 }
            if (r4 == 0) goto L_0x008b
            r4.a()     // Catch:{ all -> 0x00c4 }
        L_0x008b:
            monitor-exit(r5)     // Catch:{ all -> 0x00c4 }
            oq.b(r3)
        L_0x008f:
            monitor-enter(r5)
            java.util.Set<V> r3 = r5.f362e     // Catch:{ all -> 0x00c1 }
            boolean r3 = r3.add(r0)     // Catch:{ all -> 0x00c1 }
            kq.b(r3)     // Catch:{ all -> 0x00c1 }
            r5.mo510f()     // Catch:{ all -> 0x00c1 }
            Au r3 = r5.f366i     // Catch:{ all -> 0x00c1 }
            tu r3 = (tu) r3
            r3.a(r2)     // Catch:{ all -> 0x00c1 }
            r5.mo508e()     // Catch:{ all -> 0x00c1 }
            boolean r1 = pq.a(r1)     // Catch:{ all -> 0x00c1 }
            if (r1 == 0) goto L_0x00bf
            java.lang.Class<?> r1 = r5.f358a     // Catch:{ all -> 0x00c1 }
            java.lang.String r2 = "get (alloc) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r0)     // Catch:{ all -> 0x00c1 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00c1 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00c1 }
            pq.a(r1, r2, r3, r6)     // Catch:{ all -> 0x00c1 }
        L_0x00bf:
            monitor-exit(r5)     // Catch:{ all -> 0x00c1 }
            return r0
        L_0x00c1:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00c1 }
            throw r6
        L_0x00c4:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00c4 }
            throw r6
        L_0x00c7:
            com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException r6 = new com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException     // Catch:{ all -> 0x00d9 }
            zu r0 = r5.f360c     // Catch:{ all -> 0x00d9 }
            int r0 = r0.a     // Catch:{ all -> 0x00d9 }
            eu r1 = r5.f364g     // Catch:{ all -> 0x00d9 }
            int r1 = r1.b     // Catch:{ all -> 0x00d9 }
            eu r3 = r5.f365h     // Catch:{ all -> 0x00d9 }
            int r3 = r3.b     // Catch:{ all -> 0x00d9 }
            r6.<init>(r0, r1, r3, r2)     // Catch:{ all -> 0x00d9 }
            throw r6     // Catch:{ all -> 0x00d9 }
        L_0x00d9:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00d9 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.mo502c(int):java.lang.Object");
    }

    /* renamed from: d */
    public synchronized boolean mo506d() {
        boolean z;
        z = this.f364g.b + this.f365h.b > this.f360c.b;
        if (z) {
            this.f366i.b();
        }
        return z;
    }

    /* renamed from: b */
    public synchronized boolean mo501b(int i) {
        int i2 = this.f360c.a;
        if (i > i2 - this.f364g.b) {
            this.f366i.a();
            return false;
        }
        int i3 = this.f360c.b;
        if (i > i3 - (this.f364g.b + this.f365h.b)) {
            mo513i(i3 - i);
        }
        if (i <= i2 - (this.f364g.b + this.f365h.b)) {
            return true;
        }
        this.f366i.a();
        return false;
    }

    /* renamed from: c */
    public boolean mo504c(V v) {
        if (v != null) {
            return true;
        }
        throw new NullPointerException();
    }
}
