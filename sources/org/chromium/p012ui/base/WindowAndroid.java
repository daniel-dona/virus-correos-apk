package org.chromium.p012ui.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.uimanager.BaseViewManager;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.ObserverList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.p012ui.KeyboardVisibilityDelegate;
import org.chromium.p012ui.VSyncMonitor;
import org.chromium.p012ui.display.DisplayAndroid;
import org.chromium.p012ui.touchless.TouchlessEventHandler;

/* renamed from: org.chromium.ui.base.WindowAndroid */
/* compiled from: PG */
public class WindowAndroid implements RH3, DisplayAndroid.DisplayAndroidObserver {

    /* renamed from: A3 */
    public final vK3 f2621A3;

    /* renamed from: a */
    public KeyboardVisibilityDelegate f2622a;

    /* renamed from: b */
    public long f2623b;

    /* renamed from: c */
    public final VSyncMonitor f2624c;

    /* renamed from: d */
    public final DisplayAndroid f2625d;

    /* renamed from: e */
    public SparseArray<IntentCallback> f2626e;

    /* renamed from: k */
    public WeakReference<Context> f2627k;

    /* renamed from: n */
    public HashMap<Integer, String> f2628n;

    /* renamed from: p */
    public HashSet<Animator> f2629p;

    /* renamed from: q */
    public View f2630q;

    /* renamed from: q3 */
    public C0477d f2631q3;

    /* renamed from: r3 */
    public RH3 f2632r3;

    /* renamed from: s3 */
    public boolean f2633s3;

    /* renamed from: t3 */
    public boolean f2634t3;

    /* renamed from: u3 */
    public List<Display.Mode> f2635u3;

    /* renamed from: v3 */
    public ObserverList<ActivityStateObserver> f2636v3;

    /* renamed from: w3 */
    public boolean f2637w3;

    /* renamed from: x */
    public final AccessibilityManager f2638x;

    /* renamed from: x3 */
    public ObserverList<SelectionHandlesObserver> f2639x3;

    /* renamed from: y */
    public boolean f2640y;

    /* renamed from: y3 */
    public final ObserverList<OnCloseContextMenuListener> f2641y3;

    /* renamed from: z3 */
    public final VSyncMonitor.Listener f2642z3;

    /* renamed from: org.chromium.ui.base.WindowAndroid$ActivityStateObserver */
    /* compiled from: PG */
    public interface ActivityStateObserver {
        void onActivityPaused();

        void onActivityResumed();
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$IntentCallback */
    /* compiled from: PG */
    public interface IntentCallback {
        void onIntentCompleted(WindowAndroid windowAndroid, int i, Intent intent);
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$OnCloseContextMenuListener */
    /* compiled from: PG */
    public interface OnCloseContextMenuListener {
        void onContextMenuClosed();
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$SelectionHandlesObserver */
    /* compiled from: PG */
    public interface SelectionHandlesObserver {
        void onSelectionHandlesStateChanged(boolean z);
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$a */
    /* compiled from: PG */
    public class C0474a implements VSyncMonitor.Listener {
        public C0474a() {
        }

        public void onVSync(VSyncMonitor vSyncMonitor, long j) {
            WindowAndroid windowAndroid = WindowAndroid.this;
            if (windowAndroid.f2634t3) {
                windowAndroid.f2633s3 = true;
                return;
            }
            long j2 = windowAndroid.f2623b;
            if (j2 != 0) {
                windowAndroid.nativeOnVSync(j2, j, windowAndroid.f2624c.f2593d / 1000);
            }
        }
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$b */
    /* compiled from: PG */
    public class C0475b implements vK3 {
        public C0475b(WindowAndroid windowAndroid) {
        }
    }

    /* renamed from: org.chromium.ui.base.WindowAndroid$c */
    /* compiled from: PG */
    public class C0476c extends AnimatorListenerAdapter {
        public C0476c() {
        }

        public void onAnimationEnd(Animator animator) {
            animator.removeListener(this);
            WindowAndroid.this.f2629p.remove(animator);
            WindowAndroid.this.mo10030o();
        }
    }

    @TargetApi(19)
    /* renamed from: org.chromium.ui.base.WindowAndroid$d */
    /* compiled from: PG */
    public class C0477d {

        /* renamed from: a */
        public AccessibilityManager.TouchExplorationStateChangeListener f2645a;

        public C0477d() {
            this.f2645a = new ZH3(this, WindowAndroid.this);
            WindowAndroid.this.f2638x.addTouchExplorationStateChangeListener(this.f2645a);
        }

        /* renamed from: a */
        public void mo10041a() {
            WindowAndroid.this.f2638x.removeTouchExplorationStateChangeListener(this.f2645a);
        }
    }

    public WindowAndroid(Context context) {
        this(context, DisplayAndroid.m3727a(context));
    }

    @CalledByNative
    private void clearNativePointer() {
        this.f2623b = 0;
    }

    @CalledByNative
    public static long createForTesting() {
        return new WindowAndroid(RN0.a).getNativePointer();
    }

    @CalledByNative
    private long getNativePointer() {
        boolean z;
        Window h;
        if (this.f2623b == 0) {
            int i = this.f2625d.f2649b;
            TypedValue typedValue = new TypedValue();
            Context context = (Context) mo10017d().get();
            float dimension = (context == null || !context.getTheme().resolveAttribute(16842829, typedValue, true)) ? BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER : typedValue.getDimension(context.getResources().getDisplayMetrics());
            if (BuildInfo.m1372a() && (h = mo10022h()) != null) {
                z = h.isWideColorGamut();
            } else {
                z = false;
            }
            this.f2623b = nativeInit(i, dimension, z);
            nativeSetVSyncPaused(this.f2623b, false);
        }
        return this.f2623b;
    }

    @CalledByNative
    private float getRefreshRate() {
        return this.f2625d.f2655h;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(23)
    @CalledByNative
    private float[] getSupportedRefreshRates() {
        List<Display.Mode> list = this.f2635u3;
        if (list == null) {
            return null;
        }
        float[] fArr = new float[list.size()];
        for (int i = 0; i < this.f2635u3.size(); i++) {
            fArr[i] = this.f2635u3.get(i).getRefreshRate();
        }
        return fArr;
    }

    private native void nativeDestroy(long j);

    private native long nativeInit(int i, float f, boolean z);

    private native void nativeOnActivityStarted(long j);

    private native void nativeOnActivityStopped(long j);

    private native void nativeOnCursorVisibilityChanged(long j, boolean z);

    private native void nativeOnFallbackCursorModeToggled(long j, boolean z);

    private native void nativeOnSupportedRefreshRatesUpdated(long j, float[] fArr);

    private native void nativeOnUpdateRefreshRate(long j, float f);

    /* access modifiers changed from: private */
    public native void nativeOnVSync(long j, long j2, long j3);

    private native void nativeOnVisibilityChanged(long j, boolean z);

    private native void nativeSetVSyncPaused(long j, boolean z);

    @CalledByNative
    private void onSelectionHandlesStateChanged(boolean z) {
        this.f2637w3 = z;
        Iterator<SelectionHandlesObserver> it = this.f2639x3.iterator();
        while (it.hasNext()) {
            it.next().onSelectionHandlesStateChanged(z);
        }
    }

    @CalledByNative
    private void requestVSyncUpdate() {
        this.f2624c.mo9954b();
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    private void setPreferredRefreshRate(float f) {
        if (this.f2635u3 != null && BuildInfo.m1372a()) {
            int i = 0;
            if (f != BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                float f2 = Float.MAX_VALUE;
                Display.Mode mode = null;
                for (int i2 = 0; i2 < this.f2635u3.size(); i2++) {
                    Display.Mode mode2 = this.f2635u3.get(i2);
                    float abs = Math.abs(f - mode2.getRefreshRate());
                    if (abs < f2) {
                        mode = mode2;
                        f2 = abs;
                    }
                }
                if (f2 > 2.0f) {
                    VN0.a("WindowAndroid", "Refresh rate not supported : " + f, new Object[0]);
                } else {
                    i = mode.getModeId();
                }
            }
            Window h = mo10022h();
            WindowManager.LayoutParams attributes = h.getAttributes();
            if (attributes.preferredDisplayModeId != i) {
                attributes.preferredDisplayModeId = i;
                h.setAttributes(attributes);
            }
        }
    }

    /* renamed from: a */
    public boolean mo10003a(Intent intent) {
        if (Vc0.a(RN0.a.getPackageManager(), intent, 0).size() > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void mo10007b(Intent intent) {
        RN0.a.sendBroadcast(intent);
    }

    /* renamed from: c */
    public int mo10014c() {
        return 6;
    }

    /* renamed from: c */
    public void mo10015c(String str) {
        if (str != null) {
            CK3.a(RN0.a, str, 0).a();
        }
    }

    @CalledByNative
    public final boolean canRequestPermission(String str) {
        RH3 rh3 = this.f2632r3;
        if (rh3 != null) {
            return rh3.canRequestPermission(str);
        }
        VN0.c("WindowAndroid", "Cannot determine the request permission state as the context is not an Activity", new Object[0]);
        return false;
    }

    /* renamed from: d */
    public WeakReference<Context> mo10017d() {
        return new WeakReference<>((Context) this.f2627k.get());
    }

    /* renamed from: e */
    public DisplayAndroid mo10018e() {
        return this.f2625d;
    }

    /* renamed from: f */
    public KeyboardVisibilityDelegate mo10019f() {
        return this.f2622a;
    }

    /* renamed from: g */
    public View mo10020g() {
        return null;
    }

    @CalledByNative
    public IBinder getWindowToken() {
        View peekDecorView;
        Window h = mo10022h();
        if (h == null || (peekDecorView = h.peekDecorView()) == null) {
            return null;
        }
        return peekDecorView.getWindowToken();
    }

    /* renamed from: h */
    public final Window mo10022h() {
        Activity a = m3684a((Context) this.f2627k.get());
        if (a == null) {
            return null;
        }
        return a.getWindow();
    }

    @CalledByNative
    public final boolean hasPermission(String str) {
        RH3 rh3 = this.f2632r3;
        if (rh3 != null) {
            return rh3.hasPermission(str);
        }
        return ON0.a(RN0.a, str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: i */
    public void mo10024i() {
        Iterator<ActivityStateObserver> it = this.f2636v3.iterator();
        while (it.hasNext()) {
            it.next().onActivityPaused();
        }
    }

    /* renamed from: j */
    public void mo10025j() {
        Iterator<ActivityStateObserver> it = this.f2636v3.iterator();
        while (it.hasNext()) {
            it.next().onActivityResumed();
        }
    }

    /* renamed from: k */
    public void mo10026k() {
        long j = this.f2623b;
        if (j != 0) {
            nativeOnActivityStarted(j);
        }
    }

    /* renamed from: l */
    public void mo10027l() {
        long j = this.f2623b;
        if (j != 0) {
            nativeOnActivityStopped(j);
        }
    }

    /* renamed from: m */
    public void mo10028m() {
        Iterator<OnCloseContextMenuListener> it = this.f2641y3.iterator();
        while (it.hasNext()) {
            it.next().onContextMenuClosed();
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(23)
    /* renamed from: n */
    public final void mo10029n() {
        DisplayAndroid displayAndroid = this.f2625d;
        Display.Mode mode = displayAndroid.f2656i;
        List<Display.Mode> list = displayAndroid.f2657j;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (mode.equals(list.get(i))) {
                arrayList.add(list.get(i));
            } else if (mode.getPhysicalWidth() == list.get(i).getPhysicalWidth() && mode.getPhysicalHeight() == list.get(i).getPhysicalHeight() && mode.getRefreshRate() != list.get(i).getRefreshRate()) {
                arrayList.add(list.get(i));
            }
        }
        if (!arrayList.equals(this.f2635u3)) {
            this.f2635u3 = arrayList;
            long j = this.f2623b;
            if (j != 0) {
                nativeOnSupportedRefreshRatesUpdated(j, getSupportedRefreshRates());
            }
        }
    }

    /* renamed from: o */
    public final void mo10030o() {
        boolean z = !this.f2640y && this.f2629p.isEmpty();
        if (this.f2630q.willNotDraw() != z) {
            this.f2630q.setWillNotDraw(z);
        }
    }

    @TargetApi(23)
    public void onCurrentModeChanged(Display.Mode mode) {
        mo10029n();
    }

    public void onDIPScaleChanged(float f) {
    }

    @TargetApi(23)
    public void onDisplayModesChanged(List<Display.Mode> list) {
        mo10029n();
    }

    public void onRefreshRateChanged(float f) {
        this.f2624c.mo9953a(f);
        long j = this.f2623b;
        if (j != 0) {
            nativeOnUpdateRefreshRate(j, f);
        }
    }

    public void onRotationChanged(int i) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b6, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00bc, code lost:
        qI.a.a(r12, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c1, code lost:
        throw r13;
     */
    @android.annotation.SuppressLint({"UseSparseArrays"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WindowAndroid(android.content.Context r12, org.chromium.p012ui.display.DisplayAndroid r13) {
        /*
            r11 = this;
            r11.<init>()
            org.chromium.ui.KeyboardVisibilityDelegate r0 = org.chromium.p012ui.KeyboardVisibilityDelegate.m3625c()
            r11.f2622a = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r11.f2629p = r0
            org.chromium.base.ObserverList r0 = new org.chromium.base.ObserverList
            r0.<init>()
            r11.f2636v3 = r0
            org.chromium.base.ObserverList r0 = new org.chromium.base.ObserverList
            r0.<init>()
            r11.f2639x3 = r0
            org.chromium.base.ObserverList r0 = new org.chromium.base.ObserverList
            r0.<init>()
            r11.f2641y3 = r0
            org.chromium.ui.base.WindowAndroid$a r0 = new org.chromium.ui.base.WindowAndroid$a
            r0.<init>()
            r11.f2642z3 = r0
            org.chromium.ui.base.WindowAndroid$b r0 = new org.chromium.ui.base.WindowAndroid$b
            r0.<init>(r11)
            r11.f2621A3 = r0
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r12)
            r11.f2627k = r0
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            r11.f2626e = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r11.f2628n = r0
            r11.f2625d = r13
            org.chromium.ui.display.DisplayAndroid r0 = r11.f2625d
            java.util.WeakHashMap<org.chromium.ui.display.DisplayAndroid$DisplayAndroidObserver, java.lang.Object> r0 = r0.f2648a
            r1 = 0
            r0.put(r11, r1)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 < r1) goto L_0x005b
            r11.mo10029n()
        L_0x005b:
            pO0 r0 = pO0.a()
            org.chromium.ui.VSyncMonitor r1 = new org.chromium.ui.VSyncMonitor     // Catch:{ all -> 0x00b4 }
            org.chromium.ui.VSyncMonitor$Listener r2 = r11.f2642z3     // Catch:{ all -> 0x00b4 }
            org.chromium.ui.display.DisplayAndroid r3 = r11.f2625d     // Catch:{ all -> 0x00b4 }
            float r3 = r3.f2655h     // Catch:{ all -> 0x00b4 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x00b4 }
            r11.f2624c = r1     // Catch:{ all -> 0x00b4 }
            android.content.Context r1 = RN0.a     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = "accessibility"
            java.lang.Object r1 = r1.getSystemService(r2)     // Catch:{ all -> 0x00b4 }
            android.view.accessibility.AccessibilityManager r1 = (android.view.accessibility.AccessibilityManager) r1     // Catch:{ all -> 0x00b4 }
            r11.f2638x = r1     // Catch:{ all -> 0x00b4 }
            r0.close()
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x00ae
            java.lang.String r0 = android.os.Build.VERSION.RELEASE
            java.lang.String r1 = "8.0.0"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00ae
            android.app.Activity r0 = m3684a((android.content.Context) r12)
            if (r0 == 0) goto L_0x00ae
            android.content.res.Resources r12 = r12.getResources()
            android.content.res.Configuration r12 = r12.getConfiguration()
            boolean r12 = r12.isScreenWideColorGamut()
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r12)
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r0 = r13
            r0.mo10043a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
        L_0x00ae:
            vK3 r12 = r11.f2621A3
            org.chromium.p012ui.touchless.TouchlessEventHandler.m3754a((vK3) r12)
            return
        L_0x00b4:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x00b6 }
        L_0x00b6:
            r13 = move-exception
            r0.close()     // Catch:{ all -> 0x00bb }
            goto L_0x00c1
        L_0x00bb:
            r0 = move-exception
            kI r1 = qI.a
            r1.a(r12, r0)
        L_0x00c1:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.p012ui.base.WindowAndroid.<init>(android.content.Context, org.chromium.ui.display.DisplayAndroid):void");
    }

    /* renamed from: b */
    public boolean mo10012b(PendingIntent pendingIntent, IntentCallback intentCallback, Integer num) {
        return mo9988a(pendingIntent, intentCallback, num) >= 0;
    }

    /* renamed from: b */
    public boolean mo10013b(Intent intent, IntentCallback intentCallback, Integer num) {
        return mo9989a(intent, intentCallback, num) >= 0;
    }

    /* renamed from: a */
    public void mo9991a(int i) {
        mo10015c(RN0.a.getString(i));
    }

    /* renamed from: b */
    public void mo10009b(String str) {
        mo10015c(str);
    }

    /* renamed from: b */
    public WeakReference<Activity> mo10006b() {
        return new WeakReference<>((Object) null);
    }

    /* renamed from: b */
    public void mo10008b(Bundle bundle) {
        bundle.putSerializable("window_callback_errors", this.f2628n);
    }

    /* renamed from: a */
    public static Activity m3684a(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m3684a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* renamed from: b */
    public void mo10010b(ActivityStateObserver activityStateObserver) {
        this.f2636v3.mo7869b(activityStateObserver);
    }

    /* renamed from: b */
    public void mo10011b(OnCloseContextMenuListener onCloseContextMenuListener) {
        this.f2641y3.mo7869b(onCloseContextMenuListener);
    }

    /* renamed from: a */
    public void mo9992a(RH3 rh3) {
        this.f2632r3 = rh3;
    }

    /* renamed from: a */
    public int mo9988a(PendingIntent pendingIntent, IntentCallback intentCallback, Integer num) {
        "Can't show intent as context is not an Activity: " + pendingIntent;
        return -1;
    }

    /* renamed from: a */
    public int mo9989a(Intent intent, IntentCallback intentCallback, Integer num) {
        "Can't show intent as context is not an Activity: " + intent;
        return -1;
    }

    /* renamed from: a */
    public boolean mo10005a(IntentCallback intentCallback) {
        int indexOfValue = this.f2626e.indexOfValue(intentCallback);
        if (indexOfValue < 0) {
            return false;
        }
        this.f2626e.remove(indexOfValue);
        this.f2628n.remove(Integer.valueOf(indexOfValue));
        return true;
    }

    /* renamed from: a */
    public final boolean mo10004a(String str) {
        RH3 rh3 = this.f2632r3;
        if (rh3 != null) {
            return rh3.a(str);
        }
        VN0.c("WindowAndroid", "Cannot determine the policy permission state as the context is not an Activity", new Object[0]);
        return false;
    }

    /* renamed from: a */
    public final void mo10001a(String[] strArr, TH3 th3) {
        RH3 rh3 = this.f2632r3;
        if (rh3 != null) {
            rh3.a(strArr, th3);
        } else {
            VN0.c("WindowAndroid", "Cannot request permissions as the context is not an Activity", new Object[0]);
        }
    }

    /* renamed from: a */
    public boolean mo10002a(int i, String[] strArr, int[] iArr) {
        RH3 rh3 = this.f2632r3;
        if (rh3 != null) {
            return rh3.a(i, strArr, iArr);
        }
        return false;
    }

    /* renamed from: a */
    public void mo9994a(Bundle bundle) {
        if (bundle != null) {
            Serializable serializable = bundle.getSerializable("window_callback_errors");
            if (serializable instanceof HashMap) {
                this.f2628n = (HashMap) serializable;
            }
        }
    }

    /* renamed from: a */
    public void mo10000a(boolean z) {
        long j = this.f2623b;
        if (j != 0) {
            nativeOnVisibilityChanged(j, z);
        }
    }

    /* renamed from: a */
    public void mo9997a(ActivityStateObserver activityStateObserver) {
        this.f2636v3.mo7868a(activityStateObserver);
    }

    /* renamed from: a */
    public void mo9999a(SelectionHandlesObserver selectionHandlesObserver) {
        this.f2639x3.mo7868a(selectionHandlesObserver);
        selectionHandlesObserver.onSelectionHandlesStateChanged(this.f2637w3);
    }

    /* renamed from: a */
    public void mo9990a() {
        long j = this.f2623b;
        if (j != 0) {
            nativeDestroy(j);
        }
        C0477d dVar = this.f2631q3;
        if (dVar != null) {
            dVar.mo10041a();
        }
        TouchlessEventHandler.m3759b(this.f2621A3);
    }

    /* renamed from: a */
    public void mo9995a(View view) {
        this.f2630q = view;
        this.f2640y = this.f2638x.isTouchExplorationEnabled();
        mo10030o();
        this.f2631q3 = new C0477d();
    }

    /* renamed from: a */
    public void mo9996a(KeyboardVisibilityDelegate keyboardVisibilityDelegate) {
        this.f2622a = keyboardVisibilityDelegate;
        KeyboardVisibilityDelegate.m3624a(keyboardVisibilityDelegate);
    }

    /* renamed from: a */
    public void mo9998a(OnCloseContextMenuListener onCloseContextMenuListener) {
        this.f2641y3.mo7868a(onCloseContextMenuListener);
    }

    /* renamed from: a */
    public void mo9993a(Animator animator) {
        if (this.f2630q != null) {
            if (animator.isStarted()) {
                throw new IllegalArgumentException("Already started.");
            } else if (this.f2629p.add(animator)) {
                animator.start();
                mo10030o();
                animator.addListener(new C0476c());
            } else {
                throw new IllegalArgumentException("Already Added.");
            }
        }
    }
}
