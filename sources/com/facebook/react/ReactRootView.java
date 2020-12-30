package com.facebook.react;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;

/* compiled from: PG */
public class ReactRootView extends SizeMonitoringFrameLayout implements SA, gC {

    /* renamed from: b */
    public ReactInstanceManager f442b;

    /* renamed from: c */
    public String f443c;

    /* renamed from: d */
    public Bundle f444d;

    /* renamed from: e */
    public String f445e;

    /* renamed from: k */
    public C0079a f446k;

    /* renamed from: n */
    public ReactRootViewEventListener f447n;

    /* renamed from: p */
    public int f448p;

    /* renamed from: q */
    public boolean f449q;

    /* renamed from: q3 */
    public final gx f450q3 = new gx(this);

    /* renamed from: r3 */
    public boolean f451r3 = false;

    /* renamed from: s3 */
    public int f452s3 = View.MeasureSpec.makeMeasureSpec(0, 0);

    /* renamed from: t3 */
    public int f453t3 = View.MeasureSpec.makeMeasureSpec(0, 0);

    /* renamed from: u3 */
    public int f454u3 = 1;

    /* renamed from: x */
    public boolean f455x;

    /* renamed from: y */
    public uA f456y;

    /* compiled from: PG */
    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    /* renamed from: com.facebook.react.ReactRootView$a */
    /* compiled from: PG */
    public class C0079a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: a */
        public final Rect f457a;

        /* renamed from: b */
        public final int f458b;

        /* renamed from: c */
        public int f459c = 0;

        /* renamed from: d */
        public int f460d = 0;

        /* renamed from: e */
        public DisplayMetrics f461e = new DisplayMetrics();

        /* renamed from: k */
        public DisplayMetrics f462k = new DisplayMetrics();

        public C0079a() {
            rA.b(ReactRootView.this.getContext().getApplicationContext());
            this.f457a = new Rect();
            this.f458b = (int) GA.b(60.0f);
        }

        /* renamed from: a */
        public final boolean mo665a(DisplayMetrics displayMetrics, DisplayMetrics displayMetrics2) {
            return displayMetrics.equals(displayMetrics2);
        }

        public void onGlobalLayout() {
            double d;
            String str;
            String str2;
            double d2;
            ReactRootView reactRootView = ReactRootView.this;
            ReactInstanceManager reactInstanceManager = reactRootView.f442b;
            if (reactInstanceManager != null && reactRootView.f449q && reactInstanceManager.mo615b() != null) {
                ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.f457a);
                int i = rA.a.heightPixels - this.f457a.bottom;
                if (this.f459c != i && i > this.f458b) {
                    this.f459c = i;
                    WritableMap createMap = Arguments.createMap();
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putDouble("screenY", (double) GA.a((float) this.f457a.bottom));
                    createMap2.putDouble("screenX", (double) GA.a((float) this.f457a.left));
                    createMap2.putDouble("width", (double) GA.a((float) this.f457a.width()));
                    createMap2.putDouble("height", (double) GA.a((float) this.f459c));
                    createMap.putMap("endCoordinates", createMap2);
                    ReactRootView.this.mo631a("keyboardDidShow", createMap);
                } else if (this.f459c != 0 && i <= this.f458b) {
                    this.f459c = 0;
                    ReactRootView.this.mo631a("keyboardDidHide", (WritableMap) null);
                }
                int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
                if (this.f460d != rotation) {
                    this.f460d = rotation;
                    boolean z = true;
                    if (rotation != 0) {
                        if (rotation == 1) {
                            d = -90.0d;
                            str = "landscape-primary";
                        } else if (rotation == 2) {
                            d2 = 180.0d;
                            str2 = "portrait-secondary";
                        } else if (rotation == 3) {
                            d = 90.0d;
                            str = "landscape-secondary";
                        }
                        WritableMap createMap3 = Arguments.createMap();
                        createMap3.putString("name", str);
                        createMap3.putDouble("rotationDegrees", d);
                        createMap3.putBoolean("isLandscape", z);
                        ReactRootView.this.mo631a("namedOrientationDidChange", createMap3);
                    } else {
                        d2 = 0.0d;
                        str2 = "portrait-primary";
                    }
                    double d3 = d2;
                    str = str2;
                    d = d3;
                    z = false;
                    WritableMap createMap32 = Arguments.createMap();
                    createMap32.putString("name", str);
                    createMap32.putDouble("rotationDegrees", d);
                    createMap32.putBoolean("isLandscape", z);
                    ReactRootView.this.mo631a("namedOrientationDidChange", createMap32);
                }
                rA.a(ReactRootView.this.getContext());
                if (!mo665a(this.f461e, rA.a) || !mo665a(this.f462k, rA.b)) {
                    this.f461e.setTo(rA.a);
                    this.f462k.setTo(rA.b);
                    ReactRootView.this.f442b.mo615b().getNativeModule(DeviceInfoModule.class).emitUpdateDimensionsEvent();
                }
            }
        }
    }

    public ReactRootView(Context context) {
        super(context);
        setClipChildren(false);
    }

    /* renamed from: a */
    public void mo630a(ReactInstanceManager reactInstanceManager, String str, Bundle bundle, String str2) {
        Trace.beginSection("startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            Kw.a(this.f442b == null, "This root view has already been attached to a catalyst instance manager");
            this.f442b = reactInstanceManager;
            this.f443c = str;
            this.f444d = bundle;
            this.f445e = str2;
            if (!this.f442b.f436r) {
                this.f442b.mo604a();
            }
            mo635c();
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: b */
    public int mo633b() {
        if (this.f451r3 || getLayoutParams() == null || getLayoutParams().height <= 0) {
            return this.f453t3;
        }
        return View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, 1073741824);
    }

    /* renamed from: c */
    public final void mo635c() {
        Trace.beginSection("attachToReactInstanceManager");
        try {
            if (!this.f449q) {
                this.f449q = true;
                ReactInstanceManager reactInstanceManager = this.f442b;
                Kw.a(reactInstanceManager);
                reactInstanceManager.mo609a(this);
                getViewTreeObserver().addOnGlobalLayoutListener(mo640f());
                Trace.endSection();
            }
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: d */
    public final void mo636d() {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null) {
            pq.c("ReactNative", "Unable to enable layout calculation for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext b = reactInstanceManager.mo615b();
        if (b != null) {
            ((UIManagerModule) b.getCatalystInstance().getNativeModule(UIManagerModule.class)).getUIImplementation().f522d.add(Integer.valueOf(mo645j()));
        }
    }

    public void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            mo632a((Throwable) e);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || !this.f449q || reactInstanceManager.mo615b() == null) {
            pq.c("ReactNative", "Unable to handle key event as the catalyst instance has not been attached");
            return super.dispatchKeyEvent(keyEvent);
        }
        this.f450q3.a(keyEvent);
        return super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: e */
    public Bundle mo639e() {
        return this.f444d;
    }

    /* renamed from: f */
    public final C0079a mo640f() {
        if (this.f446k == null) {
            this.f446k = new C0079a();
        }
        return this.f446k;
    }

    public void finalize() throws Throwable {
        super.finalize();
        Kw.a(!this.f449q, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    /* renamed from: g */
    public String mo642g() {
        return this.f445e;
    }

    /* renamed from: h */
    public String mo643h() {
        String str = this.f443c;
        Kw.a(str);
        return str;
    }

    /* renamed from: i */
    public ReactInstanceManager mo644i() {
        return this.f442b;
    }

    /* renamed from: j */
    public int mo645j() {
        return this.f448p;
    }

    /* renamed from: k */
    public int mo646k() {
        return this.f454u3;
    }

    /* renamed from: l */
    public void mo647l() {
        this.f456y = new uA(this);
        ReactRootViewEventListener reactRootViewEventListener = this.f447n;
        if (reactRootViewEventListener != null) {
            reactRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    /* renamed from: m */
    public void mo648m() {
        Trace.beginSection("ReactRootView.runApplication");
        try {
            if (this.f442b != null) {
                if (this.f449q) {
                    ReactContext b = this.f442b.mo615b();
                    if (b == null) {
                        Trace.endSection();
                        return;
                    }
                    CatalystInstance catalystInstance = b.getCatalystInstance();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("rootTag", (double) mo645j());
                    Bundle e = mo639e();
                    if (e != null) {
                        writableNativeMap.putMap("initialProps", Arguments.fromBundle(e));
                    }
                    if (mo646k() == 2) {
                        writableNativeMap.putBoolean("fabric", true);
                    }
                    this.f455x = true;
                    catalystInstance.getJSModule(AppRegistry.class).runApplication(mo643h(), writableNativeMap);
                    Trace.endSection();
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: n */
    public void mo649n() {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager != null && this.f449q) {
            reactInstanceManager.mo620c(this);
            this.f442b = null;
            this.f449q = false;
        }
        this.f455x = false;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f449q) {
            getViewTreeObserver().removeOnGlobalLayoutListener(mo640f());
            getViewTreeObserver().addOnGlobalLayoutListener(mo640f());
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f449q) {
            getViewTreeObserver().removeOnGlobalLayoutListener(mo640f());
        }
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || !this.f449q || reactInstanceManager.mo615b() == null) {
            pq.c("ReactNative", "Unable to handle focus changed event as the catalyst instance has not been attached");
            super.onFocusChanged(z, i, rect);
            return;
        }
        gx gxVar = this.f450q3;
        int i2 = gxVar.a;
        if (i2 != -1) {
            gxVar.a("blur", i2);
        }
        gxVar.a = -1;
        super.onFocusChanged(z, i, rect);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        mo634b(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047 A[Catch:{ all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[Catch:{ all -> 0x00b2 }, LOOP:1: B:18:0x0050->B:20:0x0056, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082 A[Catch:{ all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0089 A[Catch:{ all -> 0x00b2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = "ReactRootView.onMeasure"
            android.os.Trace.beginSection(r0)
            r6.f452s3 = r7     // Catch:{ all -> 0x00b2 }
            r6.f453t3 = r8     // Catch:{ all -> 0x00b2 }
            int r0 = android.view.View.MeasureSpec.getMode(r7)     // Catch:{ all -> 0x00b2 }
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 0
            if (r0 == r1) goto L_0x001a
            if (r0 != 0) goto L_0x0015
            goto L_0x001a
        L_0x0015:
            int r7 = android.view.View.MeasureSpec.getSize(r7)     // Catch:{ all -> 0x00b2 }
            goto L_0x0041
        L_0x001a:
            r7 = 0
            r0 = 0
        L_0x001c:
            int r3 = r6.getChildCount()     // Catch:{ all -> 0x00b2 }
            if (r7 >= r3) goto L_0x0040
            android.view.View r3 = r6.getChildAt(r7)     // Catch:{ all -> 0x00b2 }
            int r4 = r3.getLeft()     // Catch:{ all -> 0x00b2 }
            int r5 = r3.getMeasuredWidth()     // Catch:{ all -> 0x00b2 }
            int r4 = r4 + r5
            int r5 = r3.getPaddingLeft()     // Catch:{ all -> 0x00b2 }
            int r4 = r4 + r5
            int r3 = r3.getPaddingRight()     // Catch:{ all -> 0x00b2 }
            int r4 = r4 + r3
            int r0 = java.lang.Math.max(r0, r4)     // Catch:{ all -> 0x00b2 }
            int r7 = r7 + 1
            goto L_0x001c
        L_0x0040:
            r7 = r0
        L_0x0041:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x00b2 }
            if (r0 == r1) goto L_0x004f
            if (r0 != 0) goto L_0x004a
            goto L_0x004f
        L_0x004a:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x00b2 }
            goto L_0x0074
        L_0x004f:
            r8 = 0
        L_0x0050:
            int r0 = r6.getChildCount()     // Catch:{ all -> 0x00b2 }
            if (r2 >= r0) goto L_0x0074
            android.view.View r0 = r6.getChildAt(r2)     // Catch:{ all -> 0x00b2 }
            int r1 = r0.getTop()     // Catch:{ all -> 0x00b2 }
            int r3 = r0.getMeasuredHeight()     // Catch:{ all -> 0x00b2 }
            int r1 = r1 + r3
            int r3 = r0.getPaddingTop()     // Catch:{ all -> 0x00b2 }
            int r1 = r1 + r3
            int r0 = r0.getPaddingBottom()     // Catch:{ all -> 0x00b2 }
            int r1 = r1 + r0
            int r8 = java.lang.Math.max(r8, r1)     // Catch:{ all -> 0x00b2 }
            int r2 = r2 + 1
            goto L_0x0050
        L_0x0074:
            r6.setMeasuredDimension(r7, r8)     // Catch:{ all -> 0x00b2 }
            r7 = 1
            r6.f451r3 = r7     // Catch:{ all -> 0x00b2 }
            com.facebook.react.ReactInstanceManager r7 = r6.f442b     // Catch:{ all -> 0x00b2 }
            if (r7 == 0) goto L_0x0089
            boolean r7 = r6.f449q     // Catch:{ all -> 0x00b2 }
            if (r7 != 0) goto L_0x0089
            r6.mo635c()     // Catch:{ all -> 0x00b2 }
            r6.mo636d()     // Catch:{ all -> 0x00b2 }
            goto L_0x00b4
        L_0x0089:
            r6.mo636d()     // Catch:{ all -> 0x00b2 }
            int r7 = r6.f452s3     // Catch:{ all -> 0x00b2 }
            int r8 = r6.f453t3     // Catch:{ all -> 0x00b2 }
            com.facebook.react.ReactInstanceManager r0 = r6.f442b     // Catch:{ all -> 0x00b2 }
            if (r0 != 0) goto L_0x009c
            java.lang.String r7 = "ReactNative"
            java.lang.String r8 = "Unable to update root layout specs for uninitialized ReactInstanceManager"
            pq.c(r7, r8)     // Catch:{ all -> 0x00b2 }
            goto L_0x00b4
        L_0x009c:
            com.facebook.react.bridge.ReactContext r0 = r0.mo615b()     // Catch:{ all -> 0x00b2 }
            if (r0 == 0) goto L_0x00b4
            int r1 = r6.mo646k()     // Catch:{ all -> 0x00b2 }
            com.facebook.react.bridge.UIManager r0 = dB.a(r0, r1)     // Catch:{ all -> 0x00b2 }
            int r1 = r6.mo645j()     // Catch:{ all -> 0x00b2 }
            r0.updateRootLayoutSpecs(r1, r7, r8)     // Catch:{ all -> 0x00b2 }
            goto L_0x00b4
        L_0x00b2:
            r7 = move-exception
            goto L_0x00b8
        L_0x00b4:
            android.os.Trace.endSection()
            return
        L_0x00b8:
            android.os.Trace.endSection()
            goto L_0x00bd
        L_0x00bc:
            throw r7
        L_0x00bd:
            goto L_0x00bc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactRootView.onMeasure(int, int):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mo634b(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.f455x) {
            this.f455x = false;
            String str = this.f443c;
            if (str != null) {
                ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, str, this.f448p);
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || !this.f449q || reactInstanceManager.mo615b() == null) {
            pq.c("ReactNative", "Unable to handle child focus changed event as the catalyst instance has not been attached");
            super.requestChildFocus(view, view2);
            return;
        }
        gx gxVar = this.f450q3;
        if (gxVar.a != view2.getId()) {
            int i = gxVar.a;
            if (i != -1) {
                gxVar.a("blur", i);
            }
            gxVar.a = view2.getId();
            gxVar.a("focus", view2.getId());
        }
        super.requestChildFocus(view, view2);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    public void setAppProperties(Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.f444d = bundle;
        if (mo645j() != 0) {
            mo648m();
        }
    }

    public void setEventListener(ReactRootViewEventListener reactRootViewEventListener) {
        this.f447n = reactRootViewEventListener;
    }

    public void setIsFabric(boolean z) {
        this.f454u3 = z ? 2 : 1;
    }

    public void setRootViewTag(int i) {
        this.f448p = i;
    }

    /* renamed from: b */
    public final void mo634b(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || !this.f449q || reactInstanceManager.mo615b() == null) {
            pq.c("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.f456y == null) {
            pq.c("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            this.f456y.b(motionEvent, ((UIManagerModule) this.f442b.mo615b().getNativeModule(UIManagerModule.class)).getEventDispatcher());
        }
    }

    public ReactRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setClipChildren(false);
    }

    /* renamed from: a */
    public int mo627a() {
        if (this.f451r3 || getLayoutParams() == null || getLayoutParams().width <= 0) {
            return this.f452s3;
        }
        return View.MeasureSpec.makeMeasureSpec(getLayoutParams().width, 1073741824);
    }

    public ReactRootView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setClipChildren(false);
    }

    /* renamed from: a */
    public void mo628a(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || !this.f449q || reactInstanceManager.mo615b() == null) {
            pq.c("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.f456y == null) {
            pq.c("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            qC eventDispatcher = ((UIManagerModule) this.f442b.mo615b().getNativeModule(UIManagerModule.class)).getEventDispatcher();
            uA uAVar = this.f456y;
            if (!uAVar.c) {
                uAVar.a(motionEvent, eventDispatcher);
                uAVar.c = true;
                uAVar.a = -1;
            }
        }
    }

    /* renamed from: a */
    public void mo629a(ReactInstanceManager reactInstanceManager, String str, Bundle bundle) {
        mo630a(reactInstanceManager, str, bundle, (String) null);
    }

    /* renamed from: a */
    public void mo632a(Throwable th) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager == null || reactInstanceManager.mo615b() == null) {
            throw new RuntimeException(th);
        }
        this.f442b.mo615b().handleException(new IllegalViewOperationException(th.getMessage(), this, th));
    }

    /* renamed from: a */
    public void mo631a(String str, WritableMap writableMap) {
        ReactInstanceManager reactInstanceManager = this.f442b;
        if (reactInstanceManager != null) {
            reactInstanceManager.mo615b().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(str, writableMap);
        }
    }
}
