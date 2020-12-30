package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.os.Trace;
import android.view.View;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.yoga.YogaDirection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public class UIImplementation {

    /* renamed from: a */
    public final qC f519a;

    /* renamed from: b */
    public final ReactApplicationContext f520b;

    /* renamed from: c */
    public final UA f521c = new UA();

    /* renamed from: d */
    public final Set<Integer> f522d = new HashSet();

    /* renamed from: e */
    public final OB f523e;

    /* renamed from: f */
    public final UIViewOperationQueue f524f;

    /* renamed from: g */
    public final EA f525g;

    /* renamed from: h */
    public final int[] f526h = new int[4];

    /* renamed from: i */
    public long f527i = 0;

    /* compiled from: PG */
    public interface LayoutUpdateListener {
        void onLayoutUpdated(NA na);
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, OB ob, qC qCVar, int i) {
        UIViewOperationQueue uIViewOperationQueue = new UIViewOperationQueue(reactApplicationContext, new CA(ob), i);
        this.f520b = reactApplicationContext;
        this.f523e = ob;
        this.f524f = uIViewOperationQueue;
        this.f525g = new EA(this.f524f, this.f521c);
        this.f519a = qCVar;
    }

    /* renamed from: a */
    public void mo1104a(int i) {
        dF dFVar = cF.a;
        dFVar.a("batchId", i);
        dF dFVar2 = dFVar;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            mo1120c();
            this.f525g.c.clear();
            this.f524f.mo1172a(i, uptimeMillis, this.f527i);
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: b */
    public void mo1118b() {
    }

    /* renamed from: b */
    public void mo1119b(NA na) {
        dF dFVar = cF.a;
        dFVar.a("rootTag", ((OA) na).a);
        dF dFVar2 = dFVar;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            ((OA) na).t.mo1881a(Float.NaN, Float.NaN);
        } finally {
            Trace.endSection();
            this.f527i = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        throw r0;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1120c() {
        /*
            r5 = this;
            java.lang.String r0 = "rootTag"
            java.lang.String r1 = "UIImplementation.updateViewHierarchy"
            android.os.Trace.beginSection(r1)
            r1 = 0
        L_0x0008:
            UA r2 = r5.f521c     // Catch:{ all -> 0x005f }
            iy r3 = r2.c     // Catch:{ all -> 0x005f }
            r3.a()     // Catch:{ all -> 0x005f }
            android.util.SparseBooleanArray r2 = r2.b     // Catch:{ all -> 0x005f }
            int r2 = r2.size()     // Catch:{ all -> 0x005f }
            if (r1 >= r2) goto L_0x006e
            UA r2 = r5.f521c     // Catch:{ all -> 0x005f }
            iy r3 = r2.c     // Catch:{ all -> 0x005f }
            r3.a()     // Catch:{ all -> 0x005f }
            android.util.SparseBooleanArray r2 = r2.b     // Catch:{ all -> 0x005f }
            int r2 = r2.keyAt(r1)     // Catch:{ all -> 0x005f }
            UA r3 = r5.f521c     // Catch:{ all -> 0x005f }
            NA r3 = r3.a(r2)     // Catch:{ all -> 0x005f }
            java.util.Set<java.lang.Integer> r4 = r5.f522d     // Catch:{ all -> 0x005f }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x005f }
            boolean r2 = r4.contains(r2)     // Catch:{ all -> 0x005f }
            if (r2 == 0) goto L_0x006b
            cF$a r2 = cF.a     // Catch:{ all -> 0x005f }
            r4 = r3
            OA r4 = (OA) r4     // Catch:{ all -> 0x005f }
            int r4 = r4.a     // Catch:{ all -> 0x005f }
            r2.a(r0, r4)     // Catch:{ all -> 0x005f }
            dF r2 = (dF) r2     // Catch:{ all -> 0x005f }
            r5.mo1121c(r3)     // Catch:{ all -> 0x0066 }
            android.os.Trace.endSection()     // Catch:{ all -> 0x005f }
            r5.mo1119b((NA) r3)     // Catch:{ all -> 0x005f }
            cF$a r2 = cF.a     // Catch:{ all -> 0x005f }
            r4 = r3
            OA r4 = (OA) r4     // Catch:{ all -> 0x005f }
            int r4 = r4.a     // Catch:{ all -> 0x005f }
            r2.a(r0, r4)     // Catch:{ all -> 0x005f }
            dF r2 = (dF) r2     // Catch:{ all -> 0x005f }
            r2 = 0
            r5.mo1113a((NA) r3, (float) r2, (float) r2)     // Catch:{ all -> 0x0061 }
            android.os.Trace.endSection()     // Catch:{ all -> 0x005f }
            goto L_0x006b
        L_0x005f:
            r0 = move-exception
            goto L_0x0072
        L_0x0061:
            r0 = move-exception
            android.os.Trace.endSection()     // Catch:{ all -> 0x005f }
            throw r0     // Catch:{ all -> 0x005f }
        L_0x0066:
            r0 = move-exception
            android.os.Trace.endSection()     // Catch:{ all -> 0x005f }
            throw r0     // Catch:{ all -> 0x005f }
        L_0x006b:
            int r1 = r1 + 1
            goto L_0x0008
        L_0x006e:
            android.os.Trace.endSection()
            return
        L_0x0072:
            android.os.Trace.endSection()
            goto L_0x0077
        L_0x0076:
            throw r0
        L_0x0077:
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIImplementation.mo1120c():void");
    }

    /* renamed from: d */
    public final void mo1122d(NA na) {
        OA oa = (OA) na;
        ArrayList arrayList = oa.l;
        if (arrayList != null) {
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                ((OA) oa.l.get(size)).k = null;
            }
            oa.l.clear();
        }
        UA ua = this.f521c;
        OA oa2 = (OA) na;
        int i = oa2.a;
        ua.c.a();
        if (!ua.b.get(i)) {
            ua.a.remove(i);
            this.f522d.remove(Integer.valueOf(oa2.a));
            for (int f = oa2.f() - 1; f >= 0; f--) {
                mo1122d(oa2.a(f));
            }
            if (oa2.f() != 0) {
                int i2 = 0;
                for (int f2 = oa2.f() - 1; f2 >= 0; f2--) {
                    if (oa2.t != null && !oa2.n()) {
                        oa2.t.mo1878a(f2);
                    }
                    OA a = oa2.a(f2);
                    a.h = null;
                    a.e();
                    i2 += a.i ? a.j : 1;
                }
                ArrayList arrayList2 = oa2.g;
                Kw.a(arrayList2);
                arrayList2.clear();
                oa2.p();
                oa2.j -= i2;
                oa2.e(-i2);
                return;
            }
            return;
        }
        throw new IllegalViewOperationException(Eo.a("Trying to remove root node ", i, " without using removeRootNode!"));
    }

    /* renamed from: a */
    public <T extends SizeMonitoringFrameLayout & gC> void mo1116a(T t, int i, WA wa) {
        OA oa = new OA();
        if (Ty.a().b(this.f520b)) {
            oa.t.mo1883a(YogaDirection.RTL);
        }
        oa.b = "Root";
        oa.a = i;
        oa.a(wa);
        gC gCVar = (gC) t;
        mo1114a((NA) oa, gCVar.a(), gCVar.b());
        wa.runOnNativeModulesQueueThread(new bB(this, oa));
        this.f524f.f540b.a(i, t, wa);
    }

    /* renamed from: b */
    public final NA mo1117b(int i) {
        UA ua = this.f521c;
        ua.c.a();
        return (NA) ua.a.get(i);
    }

    /* renamed from: a */
    public void mo1114a(NA na, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            ((OA) na).t.mo1925k((float) size);
        } else if (mode == 0) {
            ((OA) na).t.mo1933o();
        } else if (mode == 1073741824) {
            ((OA) na).t.mo1936q((float) size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            ((OA) na).t.mo1920i((float) size2);
        } else if (mode2 == 0) {
            ((OA) na).t.mo1931n();
        } else if (mode2 == 1073741824) {
            ((OA) na).t.mo1916g((float) size2);
        }
    }

    /* renamed from: c */
    public final void mo1121c(NA na) {
        OA oa = (OA) na;
        if (oa.m()) {
            for (int i = 0; i < oa.f(); i++) {
                mo1121c(oa.a(i));
            }
            oa.b();
        }
    }

    /* renamed from: a */
    public void mo1107a(int i, PA pa) {
        UiThreadUtil.assertOnUiThread();
        this.f524f.f540b.a(i, pa);
    }

    /* renamed from: a */
    public void mo1109a(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        int i2;
        int i3;
        int i4;
        int[] iArr;
        ReadableArray readableArray6 = readableArray;
        ReadableArray readableArray7 = readableArray2;
        ReadableArray readableArray8 = readableArray3;
        ReadableArray readableArray9 = readableArray4;
        ReadableArray readableArray10 = readableArray5;
        UA ua = this.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (readableArray6 == null) {
            i2 = 0;
        } else {
            i2 = readableArray.size();
        }
        if (readableArray8 == null) {
            i3 = 0;
        } else {
            i3 = readableArray3.size();
        }
        if (readableArray10 == null) {
            i4 = 0;
        } else {
            i4 = readableArray5.size();
        }
        if (i2 != 0 && (readableArray7 == null || i2 != readableArray2.size())) {
            throw new IllegalViewOperationException("Size of moveFrom != size of moveTo!");
        } else if (i3 == 0 || (readableArray9 != null && i3 == readableArray4.size())) {
            IB[] ibArr = new IB[(i2 + i3)];
            int[] iArr2 = new int[(i2 + i4)];
            int[] iArr3 = new int[iArr2.length];
            int[] iArr4 = new int[i4];
            if (i2 > 0) {
                Kw.a(readableArray);
                Kw.a(readableArray2);
                int i5 = 0;
                while (i5 < i2) {
                    int i6 = readableArray6.getInt(i5);
                    int i7 = oa.a(i6).a;
                    ibArr[i5] = new IB(i7, readableArray7.getInt(i5));
                    iArr2[i5] = i6;
                    iArr3[i5] = i7;
                    i5++;
                    int i8 = i;
                    readableArray6 = readableArray;
                    iArr4 = iArr4;
                }
            }
            int[] iArr5 = iArr4;
            if (i3 > 0) {
                Kw.a(readableArray3);
                Kw.a(readableArray4);
                for (int i9 = 0; i9 < i3; i9++) {
                    ibArr[i2 + i9] = new IB(readableArray8.getInt(i9), readableArray9.getInt(i9));
                }
            }
            if (i4 > 0) {
                Kw.a(readableArray5);
                for (int i10 = 0; i10 < i4; i10++) {
                    int i11 = readableArray10.getInt(i10);
                    int i12 = oa.a(i11).a;
                    int i13 = i2 + i10;
                    iArr2[i13] = i11;
                    iArr3[i13] = i12;
                    iArr5[i10] = i12;
                }
            }
            Arrays.sort(ibArr, IB.c);
            Arrays.sort(iArr2);
            int length = iArr2.length - 1;
            int i14 = -1;
            while (length >= 0) {
                if (iArr2[length] != i14) {
                    oa.c(iArr2[length]);
                    i14 = iArr2[length];
                    length--;
                } else {
                    throw new IllegalViewOperationException(Eo.b("Repeated indices in Removal list for view tag: ", i));
                }
            }
            int i15 = 0;
            while (i15 < ibArr.length) {
                IB ib = ibArr[i15];
                NA a = this.f521c.a(ib.a);
                if (a != null) {
                    oa.a(a, ib.b);
                    i15++;
                } else {
                    StringBuilder a2 = Eo.a("Trying to add unknown view tag: ");
                    a2.append(ib.a);
                    throw new IllegalViewOperationException(a2.toString());
                }
            }
            if (oa.a() || oa.c()) {
                iArr = iArr5;
            } else {
                iArr = iArr5;
                this.f525g.a(oa, iArr3, ibArr, iArr);
            }
            for (int a3 : iArr) {
                OA a4 = this.f521c.a(a3);
                mo1122d(a4);
                a4.e();
            }
        } else {
            throw new IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
        }
    }

    /* renamed from: a */
    public void mo1105a(int i, int i2, Callback callback, Callback callback2) {
        try {
            mo1106a(i, i2, this.f526h);
            callback2.invoke(Float.valueOf(GA.a((float) this.f526h[0])), Float.valueOf(GA.a((float) this.f526h[1])), Float.valueOf(GA.a((float) this.f526h[2])), Float.valueOf(GA.a((float) this.f526h[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    /* renamed from: a */
    public void mo1108a(int i, Callback callback, Callback callback2) {
        try {
            mo1111a(i, this.f526h);
            callback2.invoke(Float.valueOf(GA.a((float) this.f526h[0])), Float.valueOf(GA.a((float) this.f526h[1])), Float.valueOf(GA.a((float) this.f526h[2])), Float.valueOf(GA.a((float) this.f526h[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    /* renamed from: a */
    public final void mo1103a() {
        if (this.f524f.f546h.isEmpty()) {
            mo1104a(-1);
        }
    }

    /* renamed from: a */
    public final void mo1106a(int i, int i2, int[] iArr) {
        UA ua = this.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        UA ua2 = this.f521c;
        ua2.c.a();
        OA oa2 = (NA) ua2.a.get(i2);
        if (oa == null || oa2 == null) {
            StringBuilder a = Eo.a("Tag ");
            if (oa != null) {
                i = i2;
            }
            throw new IllegalViewOperationException(Eo.a(a, i, " does not exist"));
        }
        if (oa != oa2) {
            OA oa3 = oa.h;
            while (oa3 != oa2) {
                if (oa3 != null) {
                    oa3 = oa3.h;
                } else {
                    throw new IllegalViewOperationException(Eo.a("Tag ", i2, " is not an ancestor of tag ", i));
                }
            }
        }
        mo1115a((NA) oa, (NA) oa2, iArr);
    }

    /* renamed from: a */
    public final void mo1111a(int i, int[] iArr) {
        UA ua = this.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (oa != null) {
            OA oa2 = oa.h;
            if (oa2 != null) {
                mo1115a((NA) oa, (NA) oa2, iArr);
                return;
            }
            throw new IllegalViewOperationException(Eo.a("View with tag ", i, " doesn't have a parent!"));
        }
        throw new IllegalViewOperationException(Eo.a("No native view for tag ", i, " exists!"));
    }

    /* renamed from: a */
    public final void mo1115a(NA na, NA na2, int[] iArr) {
        int i;
        int i2;
        if (na != na2) {
            OA oa = (OA) na;
            i2 = Math.round(oa.g());
            i = Math.round(oa.h());
            for (OA oa2 = oa.h; oa2 != na2; oa2 = oa2.h) {
                Kw.a(oa2);
                mo1112a((NA) oa2);
                i2 += Math.round(oa2.g());
                i += Math.round(oa2.h());
            }
            mo1112a(na2);
        } else {
            i2 = 0;
            i = 0;
        }
        iArr[0] = i2;
        iArr[1] = i;
        iArr[2] = ((OA) na).o;
        iArr[3] = ((OA) na).p;
    }

    /* renamed from: a */
    public final void mo1110a(int i, String str) {
        UA ua = this.f521c;
        ua.c.a();
        if (((NA) ua.a.get(i)) == null) {
            throw new IllegalViewOperationException("Unable to execute operation " + str + " on view with tag: " + i + ", since the view does not exists");
        }
    }

    /* renamed from: a */
    public final void mo1112a(NA na) {
        OA oa = (OA) na;
        ViewManager a = this.f523e.a(oa.k());
        Kw.a(a);
        ViewManager viewManager = a;
        if (viewManager instanceof ViewGroupManager) {
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            if (viewGroupManager != null && viewGroupManager.needsCustomLayoutForChildren()) {
                StringBuilder a2 = Eo.a("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (");
                a2.append(oa.k());
                a2.append("). Use measure instead.");
                throw new IllegalViewOperationException(a2.toString());
            }
            return;
        }
        StringBuilder a3 = Eo.a("Trying to use view ");
        a3.append(oa.k());
        a3.append(" as a parent, but its Manager doesn't extends ViewGroupManager");
        throw new IllegalViewOperationException(a3.toString());
    }

    /* renamed from: a */
    public void mo1113a(NA na, float f, float f2) {
        OA oa = (OA) na;
        if (oa.m()) {
            boolean z = false;
            if (!oa.c()) {
                for (int i = 0; i < oa.f(); i++) {
                    mo1113a((NA) oa.a(i), oa.g() + f, oa.h() + f2);
                }
            }
            int i2 = oa.a;
            if (!this.f521c.b(i2)) {
                UIViewOperationQueue uIViewOperationQueue = this.f524f;
                EA ea = this.f525g;
                if (oa.f) {
                    oa.a(uIViewOperationQueue);
                }
                if (oa.l()) {
                    float g = oa.g();
                    float h = oa.h();
                    float f3 = f + g;
                    int round = Math.round(f3);
                    float f4 = f2 + h;
                    int round2 = Math.round(f4);
                    int round3 = Math.round(oa.t.mo1908e() + f3);
                    int round4 = Math.round(oa.t.mo1905d() + f4);
                    int round5 = Math.round(g);
                    int round6 = Math.round(h);
                    int i3 = round3 - round;
                    int i4 = round4 - round2;
                    if (!(round5 == oa.m && round6 == oa.n && i3 == oa.o && i4 == oa.p)) {
                        z = true;
                    }
                    oa.m = round5;
                    oa.n = round6;
                    oa.o = i3;
                    oa.p = i4;
                    if (z) {
                        if (ea != null) {
                            ea.a(oa);
                        } else {
                            uIViewOperationQueue.mo1171a(oa.h.a, oa.a, oa.m, oa.n, oa.o, oa.p);
                        }
                    }
                }
                if (z && oa.e) {
                    this.f519a.b(FA.a(i2, oa.m, oa.n, oa.o, oa.p));
                }
            }
            oa.o();
        }
    }
}
