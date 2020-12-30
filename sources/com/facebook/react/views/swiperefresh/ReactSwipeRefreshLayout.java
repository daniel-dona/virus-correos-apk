package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewConfiguration;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.BaseViewManager;

/* compiled from: PG */
public class ReactSwipeRefreshLayout extends SwipeRefreshLayout {

    /* renamed from: U3 */
    public boolean f694U3 = false;

    /* renamed from: V3 */
    public boolean f695V3 = false;

    /* renamed from: W3 */
    public float f696W3 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: X3 */
    public int f697X3;

    /* renamed from: Y3 */
    public float f698Y3;

    /* renamed from: Z3 */
    public boolean f699Z3;

    public ReactSwipeRefreshLayout(ReactContext reactContext) {
        super(reactContext);
        this.f697X3 = ViewConfiguration.get(reactContext).getScaledTouchSlop();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout, android.view.View, android.view.ViewGroup, android.support.v4.widget.SwipeRefreshLayout] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0026
            r3 = 2
            if (r0 == r3) goto L_0x000c
            goto L_0x002e
        L_0x000c:
            float r0 = r5.getX()
            float r3 = r4.f698Y3
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            boolean r3 = r4.f699Z3
            if (r3 != 0) goto L_0x0022
            int r3 = r4.f697X3
            float r3 = (float) r3
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x002e
        L_0x0022:
            r4.f699Z3 = r2
            r0 = 0
            goto L_0x002f
        L_0x0026:
            float r0 = r5.getX()
            r4.f698Y3 = r0
            r4.f699Z3 = r1
        L_0x002e:
            r0 = 1
        L_0x002f:
            if (r0 == 0) goto L_0x0048
            boolean r0 = com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout.super.onInterceptTouchEvent(r5)
            if (r0 == 0) goto L_0x0048
            rC.a(r4, r5)
            android.view.ViewParent r5 = r4.getParent()
            if (r5 == 0) goto L_0x0047
            android.view.ViewParent r5 = r4.getParent()
            r5.requestDisallowInterceptTouchEvent(r2)
        L_0x0047:
            return r2
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ReactSwipeRefreshLayout.super.onLayout(z, i, i2, i3, i4);
        if (!this.f694U3) {
            this.f694U3 = true;
            setProgressViewOffset(this.f696W3);
            setRefreshing(this.f695V3);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout, android.view.ViewGroup] */
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    public void setProgressViewOffset(float f) {
        this.f696W3 = f;
        if (this.f694U3) {
            int c = c();
            setProgressViewOffset(false, Math.round(GA.b(f)) - c, Math.round(GA.b(f + 64.0f) - ((float) c)));
        }
    }

    public void setRefreshing(boolean z) {
        this.f695V3 = z;
        if (this.f694U3) {
            ReactSwipeRefreshLayout.super.setRefreshing(z);
        }
    }
}
