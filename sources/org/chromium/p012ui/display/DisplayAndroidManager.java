package org.chromium.p012ui.display;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.display.DisplayAndroidManager */
/* compiled from: PG */
public class DisplayAndroidManager {

    /* renamed from: e */
    public static DisplayAndroidManager f2660e;

    /* renamed from: a */
    public long f2661a;

    /* renamed from: b */
    public int f2662b;

    /* renamed from: c */
    public final SparseArray<DisplayAndroid> f2663c = new SparseArray<>();

    /* renamed from: d */
    public C0479b f2664d = new C0479b((C0478a) null);

    /* renamed from: org.chromium.ui.display.DisplayAndroidManager$b */
    /* compiled from: PG */
    public class C0479b implements DisplayManager.DisplayListener {
        public /* synthetic */ C0479b(C0478a aVar) {
        }

        /* renamed from: a */
        public void mo10049a() {
            DisplayAndroidManager.m3736c().registerDisplayListener(this, (Handler) null);
        }

        public void onDisplayAdded(int i) {
        }

        public void onDisplayChanged(int i) {
            bI3 bi3 = DisplayAndroidManager.this.f2663c.get(i);
            Display display = DisplayAndroidManager.m3736c().getDisplay(i);
            if (bi3 != null && display != null) {
                bi3.a(display);
            }
        }

        public void onDisplayRemoved(int i) {
            DisplayAndroidManager displayAndroidManager = DisplayAndroidManager.this;
            if (i != displayAndroidManager.f2662b && displayAndroidManager.f2663c.get(i) != null) {
                DisplayAndroidManager displayAndroidManager2 = DisplayAndroidManager.this;
                long j = displayAndroidManager2.f2661a;
                if (j != 0) {
                    displayAndroidManager2.nativeRemoveDisplay(j, i);
                }
                DisplayAndroidManager.this.f2663c.remove(i);
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: c */
    public static DisplayManager m3736c() {
        return (DisplayManager) RN0.a.getSystemService("display");
    }

    /* access modifiers changed from: private */
    public native void nativeRemoveDisplay(long j, int i);

    private native void nativeSetPrimaryDisplayId(long j, int i);

    private native void nativeUpdateDisplay(long j, int i, int i2, int i3, float f, int i4, int i5, int i6, boolean z);

    @CalledByNative
    public static void onNativeSideCreated(long j) {
        ThreadUtils.m1462c();
        if (f2660e == null) {
            f2660e = new DisplayAndroidManager();
            f2660e.mo10047a();
        }
        DisplayAndroidManager displayAndroidManager = f2660e;
        displayAndroidManager.f2661a = j;
        displayAndroidManager.nativeSetPrimaryDisplayId(displayAndroidManager.f2661a, displayAndroidManager.f2662b);
        for (int i = 0; i < displayAndroidManager.f2663c.size(); i++) {
            displayAndroidManager.mo10048a(displayAndroidManager.f2663c.valueAt(i));
        }
    }

    /* renamed from: a */
    public static Display m3733a(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    /* renamed from: a */
    public final void mo10047a() {
        Display display = m3736c().getDisplay(0);
        if (display == null) {
            display = m3733a(RN0.a);
        }
        this.f2662b = display.getDisplayId();
        int displayId = display.getDisplayId();
        bI3 bi3 = new bI3(display);
        this.f2663c.put(displayId, bi3);
        bi3.a(display);
        this.f2664d.mo10049a();
    }

    /* renamed from: a */
    public DisplayAndroid mo10046a(Display display) {
        DisplayAndroid displayAndroid = this.f2663c.get(display.getDisplayId());
        if (displayAndroid != null) {
            return displayAndroid;
        }
        int displayId = display.getDisplayId();
        DisplayAndroid bi3 = new bI3(display);
        this.f2663c.put(displayId, bi3);
        bi3.a(display);
        return bi3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo10048a(org.chromium.p012ui.display.DisplayAndroid r13) {
        /*
            r12 = this;
            long r1 = r12.f2661a
            r3 = 0
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            int r3 = r13.f2649b
            android.graphics.Point r0 = r13.f2650c
            int r4 = r0.x
            int r5 = r0.y
            float r6 = r13.f2651d
            int r0 = r13.f2654g
            r7 = 0
            r8 = 1
            if (r0 == 0) goto L_0x0031
            if (r0 == r8) goto L_0x002c
            r9 = 2
            if (r0 == r9) goto L_0x0027
            r9 = 3
            if (r0 == r9) goto L_0x0022
            goto L_0x0031
        L_0x0022:
            r0 = 270(0x10e, float:3.78E-43)
            r9 = 270(0x10e, float:3.78E-43)
            goto L_0x0032
        L_0x0027:
            r0 = 180(0xb4, float:2.52E-43)
            r9 = 180(0xb4, float:2.52E-43)
            goto L_0x0032
        L_0x002c:
            r0 = 90
            r9 = 90
            goto L_0x0032
        L_0x0031:
            r9 = 0
        L_0x0032:
            int r10 = r13.f2652e
            int r11 = r13.f2653f
            boolean r0 = r13.f2658k
            if (r0 == 0) goto L_0x0040
            boolean r13 = r13.f2659l
            if (r13 == 0) goto L_0x0040
            r13 = 1
            goto L_0x0041
        L_0x0040:
            r13 = 0
        L_0x0041:
            r0 = r12
            r7 = r9
            r8 = r10
            r9 = r11
            r10 = r13
            r0.nativeUpdateDisplay(r1, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.p012ui.display.DisplayAndroidManager.mo10048a(org.chromium.ui.display.DisplayAndroid):void");
    }
}
