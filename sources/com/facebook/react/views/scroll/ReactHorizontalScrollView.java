package com.facebook.react.views.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import java.lang.reflect.Field;
import java.util.List;

/* compiled from: PG */
public class ReactHorizontalScrollView extends HorizontalScrollView implements HA {

    /* renamed from: B3 */
    public static Field f629B3;

    /* renamed from: C3 */
    public static boolean f630C3;

    /* renamed from: A3 */
    public qE f631A3;

    /* renamed from: a */
    public final oD f632a;

    /* renamed from: b */
    public final OverScroller f633b;

    /* renamed from: c */
    public final rD f634c;

    /* renamed from: d */
    public final Rect f635d;

    /* renamed from: e */
    public boolean f636e;

    /* renamed from: k */
    public Rect f637k;

    /* renamed from: n */
    public String f638n;

    /* renamed from: p */
    public boolean f639p;

    /* renamed from: q */
    public boolean f640q;

    /* renamed from: q3 */
    public boolean f641q3;

    /* renamed from: r3 */
    public boolean f642r3;

    /* renamed from: s3 */
    public String f643s3;

    /* renamed from: t3 */
    public Drawable f644t3;

    /* renamed from: u3 */
    public int f645u3;

    /* renamed from: v3 */
    public int f646v3;

    /* renamed from: w3 */
    public float f647w3;

    /* renamed from: x */
    public Runnable f648x;

    /* renamed from: x3 */
    public List<Integer> f649x3;

    /* renamed from: y */
    public boolean f650y;

    /* renamed from: y3 */
    public boolean f651y3;

    /* renamed from: z3 */
    public boolean f652z3;

    /* renamed from: com.facebook.react.views.scroll.ReactHorizontalScrollView$a */
    /* compiled from: PG */
    public class C0158a implements Runnable {

        /* renamed from: a */
        public boolean f653a = false;

        public C0158a() {
        }

        public void run() {
            ReactHorizontalScrollView reactHorizontalScrollView = ReactHorizontalScrollView.this;
            if (reactHorizontalScrollView.f636e) {
                reactHorizontalScrollView.f636e = false;
                I9.a.a(reactHorizontalScrollView, this, 20);
            } else if (!reactHorizontalScrollView.f640q || this.f653a) {
                ReactHorizontalScrollView reactHorizontalScrollView2 = ReactHorizontalScrollView.this;
                if (reactHorizontalScrollView2.f642r3) {
                    pD.b(reactHorizontalScrollView2);
                }
                ReactHorizontalScrollView.this.f648x = null;
            } else {
                this.f653a = true;
                reactHorizontalScrollView.mo1342a(0);
                I9.a.a(ReactHorizontalScrollView.this, this, 20);
            }
        }
    }

    public ReactHorizontalScrollView(Context context) {
        this(context, (nD) null);
    }

    /* renamed from: a */
    public boolean mo1345a() {
        return this.f650y;
    }

    /* renamed from: b */
    public void mo1347b() {
        awakenScrollBars();
    }

    public void draw(Canvas canvas) {
        if (this.f645u3 != 0) {
            View childAt = getChildAt(0);
            if (!(this.f644t3 == null || childAt == null || childAt.getRight() >= getWidth())) {
                this.f644t3.setBounds(childAt.getRight(), 0, getWidth(), getHeight());
                this.f644t3.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    /* renamed from: e */
    public void mo1349e() {
        if (this.f650y) {
            Kw.a(this.f637k);
            IA.a(this, this.f637k);
            HA childAt = getChildAt(0);
            if (childAt instanceof HA) {
                childAt.e();
            }
        }
    }

    public void fling(int i) {
        int signum = (int) (Math.signum(this.f632a.c) * ((float) Math.abs(i)));
        if (this.f640q) {
            mo1342a(signum);
        } else if (this.f633b != null) {
            int i2 = signum;
            this.f633b.fling(getScrollX(), getScrollY(), i2, 0, 0, Integer.MAX_VALUE, 0, 0, ((getWidth() - I9.i(this)) - I9.h(this)) / 2, 0);
            I9.a.z(this);
        } else {
            super.fling(signum);
        }
        mo1343a(signum, 0);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f650y) {
            mo1349e();
        }
    }

    public void onDraw(Canvas canvas) {
        getDrawingRect(this.f635d);
        String str = this.f638n;
        if (((str.hashCode() == 466743410 && str.equals("visible")) ? (char) 0 : 65535) != 0) {
            canvas.clipRect(this.f635d);
        }
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f641q3) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                rC.a(this, motionEvent);
                pD.a(this);
                this.f639p = true;
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

    public void onMeasure(int i, int i2) {
        zA.a(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int computeHorizontalScrollRange;
        OverScroller overScroller = this.f633b;
        if (overScroller != null && !overScroller.isFinished() && this.f633b.getCurrX() != this.f633b.getFinalX() && i >= (computeHorizontalScrollRange = computeHorizontalScrollRange() - getWidth())) {
            this.f633b.abortAnimation();
            i = computeHorizontalScrollRange;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.f636e = true;
        if (this.f632a.a(i, i2)) {
            if (this.f650y) {
                mo1349e();
            }
            oD oDVar = this.f632a;
            pD.a(this, ScrollEventType.SCROLL, oDVar.c, oDVar.d);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f650y) {
            mo1349e();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f641q3) {
            return false;
        }
        this.f634c.a(motionEvent);
        if ((motionEvent.getAction() & 255) == 1 && this.f639p) {
            rD rDVar = this.f634c;
            float f = rDVar.b;
            float f2 = rDVar.c;
            pD.a(this, ScrollEventType.END_DRAG, f, f2);
            this.f639p = false;
            mo1343a(Math.round(f), Math.round(f2));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBackgroundColor(int i) {
        this.f631A3.a(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        this.f631A3.a().mo1703a(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.f631A3.a().mo1701a(f);
    }

    public void setBorderStyle(String str) {
        this.f631A3.a().mo1705a(str);
    }

    public void setBorderWidth(int i, float f) {
        this.f631A3.a().mo1702a(i, f);
    }

    public void setDecelerationRate(float f) {
        this.f647w3 = f;
        OverScroller overScroller = this.f633b;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - this.f647w3);
        }
    }

    public void setEndFillColor(int i) {
        if (i != this.f645u3) {
            this.f645u3 = i;
            this.f644t3 = new ColorDrawable(this.f645u3);
        }
    }

    public void setOverflow(String str) {
        this.f638n = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.f640q = z;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.f637k == null) {
            this.f637k = new Rect();
        }
        this.f650y = z;
        mo1349e();
    }

    public void setScrollEnabled(boolean z) {
        this.f641q3 = z;
    }

    public void setScrollPerfTag(String str) {
        this.f643s3 = str;
    }

    public void setSendMomentumEvents(boolean z) {
        this.f642r3 = z;
    }

    public void setSnapInterval(int i) {
        this.f646v3 = i;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.f649x3 = list;
    }

    public void setSnapToEnd(boolean z) {
        this.f652z3 = z;
    }

    public void setSnapToStart(boolean z) {
        this.f651y3 = z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: android.widget.OverScroller} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReactHorizontalScrollView(android.content.Context r3, nD r4) {
        /*
            r2 = this;
            r2.<init>(r3)
            oD r3 = new oD
            r3.<init>()
            r2.f632a = r3
            rD r3 = new rD
            r3.<init>()
            r2.f634c = r3
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            r2.f635d = r3
            java.lang.String r3 = "hidden"
            r2.f638n = r3
            r3 = 0
            r2.f640q = r3
            r4 = 1
            r2.f641q3 = r4
            r2.f645u3 = r3
            r2.f646v3 = r3
            r3 = 1065101558(0x3f7c28f6, float:0.985)
            r2.f647w3 = r3
            r2.f651y3 = r4
            r2.f652z3 = r4
            qE r3 = new qE
            r3.<init>(r2)
            r2.f631A3 = r3
            boolean r3 = f630C3
            java.lang.String r0 = "ReactNative"
            if (r3 != 0) goto L_0x0053
            f630C3 = r4
            java.lang.Class<android.widget.HorizontalScrollView> r3 = android.widget.HorizontalScrollView.class
            java.lang.String r1 = "mScroller"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r1)     // Catch:{ NoSuchFieldException -> 0x004e }
            f629B3 = r3     // Catch:{ NoSuchFieldException -> 0x004e }
            java.lang.reflect.Field r3 = f629B3     // Catch:{ NoSuchFieldException -> 0x004e }
            r3.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x004e }
            goto L_0x0053
        L_0x004e:
            java.lang.String r3 = "Failed to get mScroller field for HorizontalScrollView! This app will exhibit the bounce-back scrolling bug :("
            android.util.Log.w(r0, r3)
        L_0x0053:
            java.lang.reflect.Field r3 = f629B3
            r4 = 0
            if (r3 == 0) goto L_0x0073
            java.lang.Object r3 = r3.get(r2)     // Catch:{ IllegalAccessException -> 0x006a }
            boolean r1 = r3 instanceof android.widget.OverScroller     // Catch:{ IllegalAccessException -> 0x006a }
            if (r1 == 0) goto L_0x0064
            r4 = r3
            android.widget.OverScroller r4 = (android.widget.OverScroller) r4     // Catch:{ IllegalAccessException -> 0x006a }
            goto L_0x0073
        L_0x0064:
            java.lang.String r3 = "Failed to cast mScroller field in HorizontalScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :("
            android.util.Log.w(r0, r3)     // Catch:{ IllegalAccessException -> 0x006a }
            goto L_0x0073
        L_0x006a:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r0 = "Failed to get mScroller from HorizontalScrollView!"
            r4.<init>(r0, r3)
            throw r4
        L_0x0073:
            r2.f633b = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactHorizontalScrollView.<init>(android.content.Context, nD):void");
    }

    /* renamed from: a */
    public void mo1344a(Rect rect) {
        Rect rect2 = this.f637k;
        Kw.a(rect2);
        rect.set(rect2);
    }

    /* renamed from: b */
    public final int mo1346b(int i) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.f647w3);
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        int width = ((getWidth() - I9.i(this)) - I9.h(this)) / 2;
        overScroller.fling(getScrollX(), getScrollY(), i, 0, 0, max, 0, 0, width, 0);
        return overScroller.getFinalX();
    }

    /* renamed from: a */
    public final void mo1343a(int i, int i2) {
        if ((this.f642r3 || this.f640q) && this.f648x == null) {
            if (this.f642r3) {
                pD.a(this, ScrollEventType.MOMENTUM_BEGIN, (float) i, (float) i2);
            }
            this.f636e = false;
            this.f648x = new C0158a();
            I9.a.a(this, this.f648x, 20);
        }
    }

    public void setBorderRadius(float f, int i) {
        this.f631A3.a().mo1707b(f, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01e5  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1342a(int r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            int r2 = r19.getChildCount()
            if (r2 > 0) goto L_0x000b
            return
        L_0x000b:
            int r2 = r0.f646v3
            r3 = 1
            if (r2 != 0) goto L_0x00a2
            java.util.List<java.lang.Integer> r4 = r0.f649x3
            if (r4 != 0) goto L_0x00a2
            if (r2 == 0) goto L_0x0017
            goto L_0x001b
        L_0x0017:
            int r2 = r19.getWidth()
        L_0x001b:
            double r4 = (double) r2
            int r2 = r19.getScrollX()
            double r6 = (double) r2
            int r2 = r19.mo1346b(r20)
            double r8 = (double) r2
            java.lang.Double.isNaN(r6)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r6)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r6)
            java.lang.Double.isNaN(r4)
            double r10 = r6 / r4
            double r12 = java.lang.Math.floor(r10)
            int r2 = (int) r12
            double r12 = java.lang.Math.ceil(r10)
            int r12 = (int) r12
            long r10 = java.lang.Math.round(r10)
            int r11 = (int) r10
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r4)
            double r8 = r8 / r4
            long r8 = java.lang.Math.round(r8)
            int r9 = (int) r8
            if (r1 <= 0) goto L_0x0068
            if (r12 != r2) goto L_0x0068
            int r12 = r12 + 1
            goto L_0x006e
        L_0x0068:
            if (r1 >= 0) goto L_0x006e
            if (r2 != r12) goto L_0x006e
            int r2 = r2 + -1
        L_0x006e:
            if (r1 <= 0) goto L_0x0076
            if (r11 >= r12) goto L_0x0076
            if (r9 <= r2) goto L_0x0076
            r2 = r12
            goto L_0x007e
        L_0x0076:
            if (r1 >= 0) goto L_0x007d
            if (r11 <= r2) goto L_0x007d
            if (r9 >= r12) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            r2 = r11
        L_0x007e:
            double r1 = (double) r2
            java.lang.Double.isNaN(r1)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r1)
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r1)
            java.lang.Double.isNaN(r4)
            double r1 = r1 * r4
            int r4 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x00a1
            r0.f636e = r3
            int r1 = (int) r1
            int r2 = r19.getScrollY()
            r0.smoothScrollTo(r1, r2)
        L_0x00a1:
            return
        L_0x00a2:
            int r2 = r19.computeHorizontalScrollRange()
            int r4 = r19.getWidth()
            int r2 = r2 - r4
            r4 = 0
            int r2 = java.lang.Math.max(r4, r2)
            int r5 = r19.mo1346b(r20)
            int r6 = r19.getWidth()
            int r7 = I9.i(r19)
            int r6 = r6 - r7
            int r7 = I9.h(r19)
            int r6 = r6 - r7
            java.util.Locale r7 = java.util.Locale.getDefault()
            int r7 = H8.a(r7)
            if (r7 != r3) goto L_0x00ce
            r7 = 1
            goto L_0x00cf
        L_0x00ce:
            r7 = 0
        L_0x00cf:
            if (r7 == 0) goto L_0x00d4
            int r5 = r2 - r5
            int r1 = -r1
        L_0x00d4:
            java.util.List<java.lang.Integer> r8 = r0.f649x3
            if (r8 == 0) goto L_0x0123
            java.lang.Object r8 = r8.get(r4)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.util.List<java.lang.Integer> r9 = r0.f649x3
            java.lang.Object r3 = Eo.a(r9, r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r9 = 0
            r10 = 0
            r10 = r2
            r11 = 0
        L_0x00f2:
            java.util.List<java.lang.Integer> r12 = r0.f649x3
            int r12 = r12.size()
            if (r9 >= r12) goto L_0x011b
            java.util.List<java.lang.Integer> r12 = r0.f649x3
            java.lang.Object r12 = r12.get(r9)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            if (r12 > r5) goto L_0x010f
            int r13 = r5 - r12
            int r14 = r5 - r11
            if (r13 >= r14) goto L_0x010f
            r11 = r12
        L_0x010f:
            if (r12 < r5) goto L_0x0118
            int r13 = r12 - r5
            int r14 = r10 - r5
            if (r13 >= r14) goto L_0x0118
            r10 = r12
        L_0x0118:
            int r9 = r9 + 1
            goto L_0x00f2
        L_0x011b:
            r9 = r3
            r3 = r11
            r18 = r10
            r10 = r8
            r8 = r18
            goto L_0x015c
        L_0x0123:
            int r3 = r0.f646v3
            if (r3 == 0) goto L_0x0128
            goto L_0x012c
        L_0x0128:
            int r3 = r19.getWidth()
        L_0x012c:
            double r8 = (double) r3
            double r10 = (double) r5
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r8)
            double r10 = r10 / r8
            double r12 = java.lang.Math.floor(r10)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r8)
            double r12 = r12 * r8
            int r3 = (int) r12
            double r10 = java.lang.Math.ceil(r10)
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r8)
            double r10 = r10 * r8
            int r8 = (int) r10
            int r8 = java.lang.Math.min(r8, r2)
            r9 = 0
            r9 = r2
            r10 = 0
        L_0x015c:
            int r11 = r5 - r3
            int r12 = r8 - r5
            if (r11 >= r12) goto L_0x0164
            r13 = r3
            goto L_0x0165
        L_0x0164:
            r13 = r8
        L_0x0165:
            int r14 = r19.getScrollX()
            if (r7 == 0) goto L_0x016d
            int r14 = r2 - r14
        L_0x016d:
            boolean r15 = r0.f652z3
            if (r15 != 0) goto L_0x0178
            if (r5 < r9) goto L_0x0178
            if (r14 < r9) goto L_0x0176
            goto L_0x0180
        L_0x0176:
            r3 = r9
            goto L_0x01a4
        L_0x0178:
            boolean r9 = r0.f651y3
            if (r9 != 0) goto L_0x0184
            if (r5 > r10) goto L_0x0184
            if (r14 > r10) goto L_0x0182
        L_0x0180:
            r3 = r5
            goto L_0x01a4
        L_0x0182:
            r3 = r10
            goto L_0x01a4
        L_0x0184:
            r9 = 4621819117588971520(0x4024000000000000, double:10.0)
            if (r1 <= 0) goto L_0x0195
            double r11 = (double) r12
            java.lang.Double.isNaN(r11)
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r9
            int r3 = (int) r11
            int r1 = r1 + r3
            r3 = r8
            goto L_0x01a4
        L_0x0195:
            if (r1 >= 0) goto L_0x01a3
            double r11 = (double) r11
            java.lang.Double.isNaN(r11)
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r9
            int r5 = (int) r11
            int r1 = r1 - r5
            goto L_0x01a4
        L_0x01a3:
            r3 = r13
        L_0x01a4:
            int r3 = java.lang.Math.max(r4, r3)
            int r3 = java.lang.Math.min(r3, r2)
            if (r7 == 0) goto L_0x01b1
            int r3 = r2 - r3
            int r1 = -r1
        L_0x01b1:
            r13 = r3
            android.widget.OverScroller r7 = r0.f633b
            if (r7 == 0) goto L_0x01e5
            r3 = 1
            r0.f636e = r3
            int r8 = r19.getScrollX()
            int r9 = r19.getScrollY()
            if (r1 == 0) goto L_0x01c4
            goto L_0x01ca
        L_0x01c4:
            int r1 = r19.getScrollX()
            int r1 = r13 - r1
        L_0x01ca:
            r10 = r1
            r11 = 0
            r14 = 0
            r15 = 0
            if (r13 == 0) goto L_0x01d7
            if (r13 != r2) goto L_0x01d3
            goto L_0x01d7
        L_0x01d3:
            r1 = 0
            r16 = 0
            goto L_0x01db
        L_0x01d7:
            int r6 = r6 / 2
            r16 = r6
        L_0x01db:
            r17 = 0
            r12 = r13
            r7.fling(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r19.postInvalidateOnAnimation()
            goto L_0x01ec
        L_0x01e5:
            int r1 = r19.getScrollY()
            r0.smoothScrollTo(r13, r1)
        L_0x01ec:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactHorizontalScrollView.mo1342a(int):void");
    }
}
