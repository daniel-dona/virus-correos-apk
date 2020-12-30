package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.os.Trace;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class UIViewOperationQueue {

    /* renamed from: a */
    public final int[] f539a = new int[4];

    /* renamed from: b */
    public final CA f540b;

    /* renamed from: c */
    public final fy f541c;

    /* renamed from: d */
    public final Object f542d = new Object();

    /* renamed from: e */
    public final Object f543e = new Object();

    /* renamed from: f */
    public final rB f544f;

    /* renamed from: g */
    public final ReactApplicationContext f545g;

    /* renamed from: h */
    public ArrayList<UIOperation> f546h = new ArrayList<>();

    /* renamed from: i */
    public ArrayList<Runnable> f547i = new ArrayList<>();

    /* renamed from: j */
    public ArrayDeque<UIOperation> f548j = new ArrayDeque<>();

    /* renamed from: k */
    public hC f549k;

    /* renamed from: l */
    public boolean f550l = false;

    /* renamed from: m */
    public boolean f551m = false;

    /* renamed from: n */
    public boolean f552n = false;

    /* renamed from: o */
    public long f553o;

    /* renamed from: p */
    public long f554p;

    /* renamed from: q */
    public long f555q;

    /* renamed from: r */
    public long f556r;

    /* renamed from: s */
    public long f557s;

    /* renamed from: t */
    public long f558t;

    /* renamed from: u */
    public long f559u;

    /* renamed from: v */
    public long f560v;

    /* compiled from: PG */
    public interface UIOperation {
        void execute();
    }

    public UIViewOperationQueue(ReactApplicationContext reactApplicationContext, CA ca, int i) {
        this.f540b = ca;
        this.f541c = ca.a;
        this.f544f = new rB(this, reactApplicationContext, i == -1 ? 8 : i, (iB) null);
        this.f545g = reactApplicationContext;
    }

    /* renamed from: a */
    public void mo1172a(int i, long j, long j2) {
        ArrayList<UIOperation> arrayList;
        ArrayDeque<UIOperation> arrayDeque;
        int i2 = i;
        dF dFVar = cF.a;
        dFVar.a("batchId", i2);
        dF dFVar2 = dFVar;
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            ArrayDeque<UIOperation> arrayDeque2 = null;
            if (!this.f546h.isEmpty()) {
                ArrayList<UIOperation> arrayList2 = this.f546h;
                this.f546h = new ArrayList<>();
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            synchronized (this.f543e) {
                if (!this.f548j.isEmpty()) {
                    arrayDeque2 = this.f548j;
                    this.f548j = new ArrayDeque<>();
                }
                arrayDeque = arrayDeque2;
            }
            if (this.f549k != null) {
                this.f549k.a();
            }
            iB iBVar = new iB(this, i, arrayDeque, arrayList, j, j2, uptimeMillis, currentThreadTimeMillis);
            dF dFVar3 = cF.a;
            dFVar3.a("batchId", i2);
            dF dFVar4 = dFVar3;
            synchronized (this.f542d) {
                Trace.endSection();
                this.f547i.add(iBVar);
            }
            if (!this.f550l) {
                UiThreadUtil.runOnUiThread(new jB(this, this.f545g));
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* renamed from: b */
    public Map<String, Long> mo1177b() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.f554p));
        hashMap.put("LayoutTime", Long.valueOf(this.f555q));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.f556r));
        hashMap.put("RunStartTime", Long.valueOf(this.f557s));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.f558t));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.f559u));
        hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(this.f560v));
        return hashMap;
    }

    /* renamed from: a */
    public void mo1173a(int i, Object obj) {
        this.f546h.add(new FB(this, i, obj));
    }

    /* renamed from: a */
    public void mo1175a(WA wa, int i, String str, PA pa) {
        synchronized (this.f543e) {
            this.f548j.addLast(new oB(this, wa, i, str, pa));
        }
    }

    /* renamed from: a */
    public void mo1171a(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f546h.add(new DB(this, i, i2, i3, i4, i5, i6));
    }

    /* renamed from: a */
    public void mo1174a(int i, int[] iArr, IB[] ibArr, int[] iArr2) {
        this.f546h.add(new tB(this, i, iArr, ibArr, iArr2));
    }

    /* renamed from: a */
    public void mo1176a(ey eyVar) {
        new wB(this, (iB) null);
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r2 = android.os.SystemClock.uptimeMillis();
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r0.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r0.next().run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r4.f552n == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        r4.f558t = android.os.SystemClock.uptimeMillis() - r2;
        r4.f559u = r4.f553o;
        r4.f552n = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        r4.f553o = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1170a() {
        /*
            r4 = this;
            boolean r0 = r4.f551m
            if (r0 == 0) goto L_0x000c
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Not flushing pending UI operations because of previously thrown Exception"
            pq.c(r0, r1)
            return
        L_0x000c:
            java.lang.Object r0 = r4.f542d
            monitor-enter(r0)
            java.util.ArrayList<java.lang.Runnable> r1 = r4.f547i     // Catch:{ all -> 0x0052 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0050
            java.util.ArrayList<java.lang.Runnable> r1 = r4.f547i     // Catch:{ all -> 0x0052 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
            r2.<init>()     // Catch:{ all -> 0x0052 }
            r4.f547i = r2     // Catch:{ all -> 0x0052 }
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            long r2 = android.os.SystemClock.uptimeMillis()
            java.util.Iterator r0 = r1.iterator()
        L_0x0029:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r0.next()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r1.run()
            goto L_0x0029
        L_0x0039:
            boolean r0 = r4.f552n
            if (r0 == 0) goto L_0x004b
            long r0 = android.os.SystemClock.uptimeMillis()
            long r0 = r0 - r2
            r4.f558t = r0
            long r0 = r4.f553o
            r4.f559u = r0
            r0 = 0
            r4.f552n = r0
        L_0x004b:
            r0 = 0
            r4.f553o = r0
            return
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x0052:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            goto L_0x0056
        L_0x0055:
            throw r1
        L_0x0056:
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIViewOperationQueue.mo1170a():void");
    }
}
