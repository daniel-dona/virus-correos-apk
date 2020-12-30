package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.lang.reflect.Field;
import java.util.List;

/* compiled from: PG */
public class ReactScrollView extends ScrollView implements HA, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener {

    /* renamed from: C3 */
    public static Field f655C3;

    /* renamed from: D3 */
    public static boolean f656D3;

    /* renamed from: A3 */
    public View f657A3;

    /* renamed from: B3 */
    public qE f658B3 = new qE(this);

    /* renamed from: a */
    public final oD f659a = new oD();

    /* renamed from: b */
    public final OverScroller f660b;

    /* renamed from: c */
    public final rD f661c = new rD();

    /* renamed from: d */
    public final Rect f662d = new Rect();

    /* renamed from: e */
    public boolean f663e;

    /* renamed from: k */
    public Rect f664k;

    /* renamed from: n */
    public String f665n = "hidden";

    /* renamed from: p */
    public boolean f666p;

    /* renamed from: q */
    public boolean f667q = false;

    /* renamed from: q3 */
    public boolean f668q3 = true;

    /* renamed from: r3 */
    public boolean f669r3;

    /* renamed from: s3 */
    public String f670s3;

    /* renamed from: t3 */
    public Drawable f671t3;

    /* renamed from: u3 */
    public int f672u3 = 0;

    /* renamed from: v3 */
    public int f673v3 = 0;

    /* renamed from: w3 */
    public float f674w3 = 0.985f;

    /* renamed from: x */
    public Runnable f675x;

    /* renamed from: x3 */
    public List<Integer> f676x3;

    /* renamed from: y */
    public boolean f677y;

    /* renamed from: y3 */
    public boolean f678y3 = true;

    /* renamed from: z3 */
    public boolean f679z3 = true;

    /* renamed from: com.facebook.react.views.scroll.ReactScrollView$a */
    /* compiled from: PG */
    public class C0159a implements Runnable {

        /* renamed from: a */
        public boolean f680a = false;

        public C0159a() {
        }

        public void run() {
            ReactScrollView reactScrollView = ReactScrollView.this;
            if (reactScrollView.f663e) {
                reactScrollView.f663e = false;
                I9.a.a(reactScrollView, this, 20);
            } else if (!reactScrollView.f667q || this.f680a) {
                ReactScrollView reactScrollView2 = ReactScrollView.this;
                if (reactScrollView2.f669r3) {
                    pD.b(reactScrollView2);
                }
                ReactScrollView.this.f675x = null;
            } else {
                this.f680a = true;
                reactScrollView.mo1406a(0);
                I9.a.a(ReactScrollView.this, this, 20);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.widget.OverScroller} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReactScrollView(com.facebook.react.bridge.ReactContext r4) {
        /*
            r3 = this;
            r3.<init>(r4)
            oD r4 = new oD
            r4.<init>()
            r3.f659a = r4
            rD r4 = new rD
            r4.<init>()
            r3.f661c = r4
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r3.f662d = r4
            java.lang.String r4 = "hidden"
            r3.f665n = r4
            r4 = 0
            r3.f667q = r4
            r0 = 1
            r3.f668q3 = r0
            r3.f672u3 = r4
            r3.f673v3 = r4
            r4 = 1065101558(0x3f7c28f6, float:0.985)
            r3.f674w3 = r4
            r3.f678y3 = r0
            r3.f679z3 = r0
            qE r4 = new qE
            r4.<init>(r3)
            r3.f658B3 = r4
            boolean r4 = f656D3
            java.lang.String r1 = "ReactNative"
            if (r4 != 0) goto L_0x0053
            f656D3 = r0
            java.lang.Class<android.widget.ScrollView> r4 = android.widget.ScrollView.class
            java.lang.String r2 = "mScroller"
            java.lang.reflect.Field r4 = r4.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x004e }
            f655C3 = r4     // Catch:{ NoSuchFieldException -> 0x004e }
            java.lang.reflect.Field r4 = f655C3     // Catch:{ NoSuchFieldException -> 0x004e }
            r4.setAccessible(r0)     // Catch:{ NoSuchFieldException -> 0x004e }
            goto L_0x0053
        L_0x004e:
            java.lang.String r4 = "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :("
            android.util.Log.w(r1, r4)
        L_0x0053:
            java.lang.reflect.Field r4 = f655C3
            r0 = 0
            if (r4 == 0) goto L_0x0073
            java.lang.Object r4 = r4.get(r3)     // Catch:{ IllegalAccessException -> 0x006a }
            boolean r2 = r4 instanceof android.widget.OverScroller     // Catch:{ IllegalAccessException -> 0x006a }
            if (r2 == 0) goto L_0x0064
            r0 = r4
            android.widget.OverScroller r0 = (android.widget.OverScroller) r0     // Catch:{ IllegalAccessException -> 0x006a }
            goto L_0x0073
        L_0x0064:
            java.lang.String r4 = "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :("
            android.util.Log.w(r1, r4)     // Catch:{ IllegalAccessException -> 0x006a }
            goto L_0x0073
        L_0x006a:
            r4 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Failed to get mScroller from ScrollView!"
            r0.<init>(r1, r4)
            throw r0
        L_0x0073:
            r3.f660b = r0
            r3.setOnHierarchyChangeListener(r3)
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r3.setScrollBarStyle(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.<init>(com.facebook.react.bridge.ReactContext):void");
    }

    /* renamed from: a */
    public boolean mo1409a() {
        return this.f677y;
    }

    /* renamed from: b */
    public void mo1411b() {
        awakenScrollBars();
    }

    /* renamed from: c */
    public final int mo1412c() {
        return Math.max(0, this.f657A3.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public void draw(Canvas canvas) {
        char c = 0;
        if (this.f672u3 != 0) {
            View childAt = getChildAt(0);
            if (!(this.f671t3 == null || childAt == null || childAt.getBottom() >= getHeight())) {
                this.f671t3.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.f671t3.draw(canvas);
            }
        }
        getDrawingRect(this.f662d);
        String str = this.f665n;
        if (str.hashCode() != 466743410 || !str.equals("visible")) {
            c = 65535;
        }
        if (c != 0) {
            canvas.clipRect(this.f662d);
        }
        super.draw(canvas);
    }

    /* renamed from: e */
    public void mo1414e() {
        if (this.f677y) {
            Kw.a(this.f664k);
            IA.a(this, this.f664k);
            HA childAt = getChildAt(0);
            if (childAt instanceof HA) {
                childAt.e();
            }
        }
    }

    public void fling(int i) {
        int signum = (int) (Math.signum(this.f659a.d) * ((float) Math.abs(i)));
        if (this.f667q) {
            mo1406a(signum);
        } else if (this.f660b != null) {
            int i2 = signum;
            this.f660b.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, 0, Integer.MAX_VALUE, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            I9.a.z(this);
        } else {
            super.fling(signum);
        }
        mo1407a(0, signum);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f677y) {
            mo1414e();
        }
    }

    public void onChildViewAdded(View view, View view2) {
        this.f657A3 = view2;
        this.f657A3.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View view, View view2) {
        this.f657A3.removeOnLayoutChangeListener(this);
        this.f657A3 = null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f668q3) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                rC.a(this, motionEvent);
                pD.a(this);
                this.f666p = true;
                return true;
            }
        } catch (IllegalArgumentException e) {
            Log.w("ReactNative", "Error intercepting touch event.", e);
        }
        return false;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        scrollTo(getScrollX(), getScrollY());
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int c;
        if (this.f657A3 != null && getScrollY() > (c = mo1412c())) {
            scrollTo(getScrollX(), c);
        }
    }

    public void onMeasure(int i, int i2) {
        zA.a(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int c;
        OverScroller overScroller = this.f660b;
        if (overScroller != null && !overScroller.isFinished() && this.f660b.getCurrY() != this.f660b.getFinalY() && i2 >= (c = mo1412c())) {
            this.f660b.abortAnimation();
            i2 = c;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.f663e = true;
        if (this.f659a.a(i, i2)) {
            if (this.f677y) {
                mo1414e();
            }
            oD oDVar = this.f659a;
            pD.a(this, ScrollEventType.SCROLL, oDVar.c, oDVar.d);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f677y) {
            mo1414e();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f668q3) {
            return false;
        }
        this.f661c.a(motionEvent);
        if ((motionEvent.getAction() & 255) == 1 && this.f666p) {
            rD rDVar = this.f661c;
            float f = rDVar.b;
            float f2 = rDVar.c;
            pD.a(this, ScrollEventType.END_DRAG, f, f2);
            this.f666p = false;
            mo1407a(Math.round(f), Math.round(f2));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBackgroundColor(int i) {
        this.f658B3.a(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        this.f658B3.a().mo1703a(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.f658B3.a().mo1701a(f);
    }

    public void setBorderStyle(String str) {
        this.f658B3.a().mo1705a(str);
    }

    public void setBorderWidth(int i, float f) {
        this.f658B3.a().mo1702a(i, f);
    }

    public void setDecelerationRate(float f) {
        this.f674w3 = f;
        OverScroller overScroller = this.f660b;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - this.f674w3);
        }
    }

    public void setEndFillColor(int i) {
        if (i != this.f672u3) {
            this.f672u3 = i;
            this.f671t3 = new ColorDrawable(this.f672u3);
        }
    }

    public void setOverflow(String str) {
        this.f665n = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.f667q = z;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.f664k == null) {
            this.f664k = new Rect();
        }
        this.f677y = z;
        mo1414e();
    }

    public void setScrollEnabled(boolean z) {
        this.f668q3 = z;
    }

    public void setScrollPerfTag(String str) {
        this.f670s3 = str;
    }

    public void setSendMomentumEvents(boolean z) {
        this.f669r3 = z;
    }

    public void setSnapInterval(int i) {
        this.f673v3 = i;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.f676x3 = list;
    }

    public void setSnapToEnd(boolean z) {
        this.f679z3 = z;
    }

    public void setSnapToStart(boolean z) {
        this.f678y3 = z;
    }

    /* renamed from: a */
    public void mo1408a(Rect rect) {
        Rect rect2 = this.f664k;
        Kw.a(rect2);
        rect.set(rect2);
    }

    /* renamed from: b */
    public final int mo1410b(int i) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.f674w3);
        int c = mo1412c();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2;
        overScroller.fling(getScrollX(), getScrollY(), 0, i, 0, 0, 0, c, 0, height);
        return overScroller.getFinalY();
    }

    /* renamed from: a */
    public final void mo1407a(int i, int i2) {
        if ((this.f669r3 || this.f667q) && this.f675x == null) {
            if (this.f669r3) {
                pD.a(this, ScrollEventType.MOMENTUM_BEGIN, (float) i, (float) i2);
            }
            this.f663e = false;
            this.f675x = new C0159a();
            I9.a.a(this, this.f675x, 20);
        }
    }

    public void setBorderRadius(float f, int i) {
        this.f658B3.a().mo1707b(f, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01cc  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1406a(int r19) {
        /*
            r18 = this;
            r0 = r18
            int r1 = r18.getChildCount()
            if (r1 > 0) goto L_0x0009
            return
        L_0x0009:
            int r1 = r0.f673v3
            r2 = 1
            if (r1 != 0) goto L_0x00a0
            java.util.List<java.lang.Integer> r3 = r0.f676x3
            if (r3 != 0) goto L_0x00a0
            if (r1 == 0) goto L_0x0015
            goto L_0x0019
        L_0x0015:
            int r1 = r18.getHeight()
        L_0x0019:
            double r3 = (double) r1
            int r1 = r18.getScrollY()
            double r5 = (double) r1
            int r1 = r18.mo1410b(r19)
            double r7 = (double) r1
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r3)
            double r9 = r5 / r3
            double r11 = java.lang.Math.floor(r9)
            int r1 = (int) r11
            double r11 = java.lang.Math.ceil(r9)
            int r11 = (int) r11
            long r9 = java.lang.Math.round(r9)
            int r10 = (int) r9
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            double r7 = r7 / r3
            long r7 = java.lang.Math.round(r7)
            int r8 = (int) r7
            if (r19 <= 0) goto L_0x0066
            if (r11 != r1) goto L_0x0066
            int r11 = r11 + 1
            goto L_0x006c
        L_0x0066:
            if (r19 >= 0) goto L_0x006c
            if (r1 != r11) goto L_0x006c
            int r1 = r1 + -1
        L_0x006c:
            if (r19 <= 0) goto L_0x0074
            if (r10 >= r11) goto L_0x0074
            if (r8 <= r1) goto L_0x0074
            r1 = r11
            goto L_0x007c
        L_0x0074:
            if (r19 >= 0) goto L_0x007b
            if (r10 <= r1) goto L_0x007b
            if (r8 >= r11) goto L_0x007b
            goto L_0x007c
        L_0x007b:
            r1 = r10
        L_0x007c:
            double r7 = (double) r1
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r3)
            double r7 = r7 * r3
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x009f
            r0.f663e = r2
            int r1 = r18.getScrollX()
            int r2 = (int) r7
            r0.smoothScrollTo(r1, r2)
        L_0x009f:
            return
        L_0x00a0:
            int r1 = r18.mo1412c()
            int r3 = r18.mo1410b(r19)
            int r4 = r18.getHeight()
            int r5 = r18.getPaddingBottom()
            int r4 = r4 - r5
            int r5 = r18.getPaddingTop()
            int r4 = r4 - r5
            java.util.List<java.lang.Integer> r5 = r0.f676x3
            r6 = 0
            if (r5 == 0) goto L_0x0103
            java.lang.Object r5 = r5.get(r6)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.util.List<java.lang.Integer> r7 = r0.f676x3
            java.lang.Object r7 = Eo.a(r7, r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r8 = 0
            r9 = 0
            r9 = r1
            r10 = 0
        L_0x00d5:
            java.util.List<java.lang.Integer> r11 = r0.f676x3
            int r11 = r11.size()
            if (r8 >= r11) goto L_0x00fe
            java.util.List<java.lang.Integer> r11 = r0.f676x3
            java.lang.Object r11 = r11.get(r8)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r11 > r3) goto L_0x00f2
            int r12 = r3 - r11
            int r13 = r3 - r10
            if (r12 >= r13) goto L_0x00f2
            r10 = r11
        L_0x00f2:
            if (r11 < r3) goto L_0x00fb
            int r12 = r11 - r3
            int r13 = r9 - r3
            if (r12 >= r13) goto L_0x00fb
            r9 = r11
        L_0x00fb:
            int r8 = r8 + 1
            goto L_0x00d5
        L_0x00fe:
            r8 = r7
            r7 = r9
            r9 = r5
            r5 = r10
            goto L_0x013c
        L_0x0103:
            int r5 = r0.f673v3
            if (r5 == 0) goto L_0x0108
            goto L_0x010c
        L_0x0108:
            int r5 = r18.getHeight()
        L_0x010c:
            double r7 = (double) r5
            double r9 = (double) r3
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r7)
            double r9 = r9 / r7
            double r11 = java.lang.Math.floor(r9)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r7)
            double r11 = r11 * r7
            int r5 = (int) r11
            double r9 = java.lang.Math.ceil(r9)
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r7)
            double r9 = r9 * r7
            int r7 = (int) r9
            int r7 = java.lang.Math.min(r7, r1)
            r8 = 0
            r8 = r1
            r9 = 0
        L_0x013c:
            int r10 = r3 - r5
            int r11 = r7 - r3
            if (r10 >= r11) goto L_0x0144
            r12 = r5
            goto L_0x0145
        L_0x0144:
            r12 = r7
        L_0x0145:
            boolean r13 = r0.f679z3
            if (r13 != 0) goto L_0x0156
            if (r3 < r8) goto L_0x0156
            int r5 = r18.getScrollY()
            if (r5 < r8) goto L_0x0152
            goto L_0x0162
        L_0x0152:
            r5 = r19
            r3 = r8
            goto L_0x0193
        L_0x0156:
            boolean r8 = r0.f678y3
            if (r8 != 0) goto L_0x0169
            if (r3 > r9) goto L_0x0169
            int r5 = r18.getScrollY()
            if (r5 > r9) goto L_0x0165
        L_0x0162:
            r5 = r19
            goto L_0x0193
        L_0x0165:
            r5 = r19
            r3 = r9
            goto L_0x0193
        L_0x0169:
            r8 = 4621819117588971520(0x4024000000000000, double:10.0)
            if (r19 <= 0) goto L_0x017c
            double r10 = (double) r11
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r8
            int r3 = (int) r10
            int r3 = r19 + r3
            r5 = r3
            r3 = r7
            goto L_0x0193
        L_0x017c:
            if (r19 >= 0) goto L_0x0190
            double r10 = (double) r10
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r8
            int r3 = (int) r10
            int r3 = r19 - r3
            r17 = r5
            r5 = r3
            r3 = r17
            goto L_0x0193
        L_0x0190:
            r5 = r19
            r3 = r12
        L_0x0193:
            int r3 = java.lang.Math.max(r6, r3)
            int r14 = java.lang.Math.min(r3, r1)
            android.widget.OverScroller r6 = r0.f660b
            if (r6 == 0) goto L_0x01cc
            r0.f663e = r2
            int r7 = r18.getScrollX()
            int r8 = r18.getScrollY()
            r9 = 0
            if (r5 == 0) goto L_0x01ad
            goto L_0x01b3
        L_0x01ad:
            int r2 = r18.getScrollY()
            int r5 = r14 - r2
        L_0x01b3:
            r10 = r5
            r11 = 0
            r12 = 0
            r15 = 0
            if (r14 == 0) goto L_0x01c0
            if (r14 != r1) goto L_0x01bc
            goto L_0x01c0
        L_0x01bc:
            r1 = 0
            r16 = 0
            goto L_0x01c4
        L_0x01c0:
            int r4 = r4 / 2
            r16 = r4
        L_0x01c4:
            r13 = r14
            r6.fling(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r18.postInvalidateOnAnimation()
            goto L_0x01d3
        L_0x01cc:
            int r1 = r18.getScrollX()
            r0.smoothScrollTo(r1, r14)
        L_0x01d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.mo1406a(int):void");
    }
}
