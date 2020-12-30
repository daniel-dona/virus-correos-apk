package org.chromium.chrome.browser.favorites;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/* compiled from: PG */
public class BookmarkSwipeLayout extends ViewGroup {

    /* renamed from: x3 */
    public static boolean f1974x3;

    /* renamed from: y3 */
    public static BookmarkSwipeLayout f1975y3;

    /* renamed from: a */
    public int f1976a;

    /* renamed from: b */
    public int f1977b;

    /* renamed from: c */
    public boolean f1978c;

    /* renamed from: d */
    public VelocityTracker f1979d;

    /* renamed from: e */
    public boolean f1980e;

    /* renamed from: k */
    public boolean f1981k;

    /* renamed from: n */
    public int f1982n;

    /* renamed from: p */
    public View f1983p;

    /* renamed from: q */
    public int f1984q;

    /* renamed from: q3 */
    public boolean f1985q3;

    /* renamed from: r3 */
    public PointF f1986r3;

    /* renamed from: s3 */
    public PointF f1987s3;

    /* renamed from: t3 */
    public int f1988t3;

    /* renamed from: u3 */
    public ValueAnimator f1989u3;

    /* renamed from: v3 */
    public ValueAnimator f1990v3;

    /* renamed from: w3 */
    public boolean f1991w3;

    /* renamed from: x */
    public boolean f1992x;

    /* renamed from: y */
    public boolean f1993y;

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSwipeLayout$a */
    /* compiled from: PG */
    public class C0414a implements ValueAnimator.AnimatorUpdateListener {
        public C0414a() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BookmarkSwipeLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSwipeLayout$b */
    /* compiled from: PG */
    public class C0415b extends AnimatorListenerAdapter {
        public C0415b() {
        }

        public void onAnimationEnd(Animator animator) {
            BookmarkSwipeLayout.this.f1991w3 = true;
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSwipeLayout$c */
    /* compiled from: PG */
    public class C0416c implements ValueAnimator.AnimatorUpdateListener {
        public C0416c() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BookmarkSwipeLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    /* renamed from: org.chromium.chrome.browser.favorites.BookmarkSwipeLayout$d */
    /* compiled from: PG */
    public class C0417d extends AnimatorListenerAdapter {
        public C0417d() {
        }

        public void onAnimationEnd(Animator animator) {
            BookmarkSwipeLayout.this.f1991w3 = false;
        }
    }

    public BookmarkSwipeLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: a */
    public final void mo8716a(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                if (marginLayoutParams.height == -1) {
                    int i4 = marginLayoutParams.width;
                    marginLayoutParams.width = childAt.getMeasuredWidth();
                    measureChildWithMargins(childAt, i2, 0, makeMeasureSpec, 0);
                    marginLayoutParams.width = i4;
                }
            }
        }
    }

    /* renamed from: b */
    public void mo8717b() {
        f1975y3 = null;
        View view = this.f1983p;
        if (view != null) {
            view.setLongClickable(true);
        }
        mo8715a();
        this.f1990v3 = ValueAnimator.ofInt(new int[]{getScrollX(), 0});
        this.f1990v3.addUpdateListener(new C0416c());
        this.f1990v3.setInterpolator(new LinearInterpolator());
        this.f1990v3.addListener(new C0417d());
        this.f1990v3.setDuration(200).start();
    }

    /* renamed from: c */
    public void mo8718c() {
        f1975y3 = this;
        View view = this.f1983p;
        if (view != null) {
            view.setLongClickable(false);
        }
        mo8715a();
        int[] iArr = new int[2];
        iArr[0] = getScrollX();
        iArr[1] = this.f1981k ? this.f1982n : -this.f1982n;
        this.f1989u3 = ValueAnimator.ofInt(iArr);
        this.f1989u3.addUpdateListener(new C0414a());
        this.f1989u3.setInterpolator(new OvershootInterpolator());
        this.f1989u3.addListener(new C0415b());
        this.f1989u3.setDuration(300).start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (r1 != 3) goto L_0x018b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.f1978c
            if (r0 == 0) goto L_0x018b
            android.view.VelocityTracker r0 = r5.f1979d
            if (r0 != 0) goto L_0x000e
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r5.f1979d = r0
        L_0x000e:
            android.view.VelocityTracker r0 = r5.f1979d
            r0.addMovement(r6)
            android.view.VelocityTracker r0 = r5.f1979d
            int r1 = r6.getAction()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x014a
            if (r1 == r2) goto L_0x00cb
            r4 = 2
            if (r1 == r4) goto L_0x0027
            r4 = 3
            if (r1 == r4) goto L_0x00cb
            goto L_0x018b
        L_0x0027:
            boolean r0 = r5.f1985q3
            if (r0 == 0) goto L_0x002d
            goto L_0x018b
        L_0x002d:
            android.graphics.PointF r0 = r5.f1986r3
            float r0 = r0.x
            float r1 = r6.getRawX()
            float r0 = r0 - r1
            android.graphics.PointF r1 = r5.f1986r3
            float r1 = r1.y
            float r4 = r6.getRawY()
            float r1 = r1 - r4
            float r4 = java.lang.Math.abs(r0)
            float r1 = java.lang.Math.abs(r1)
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x00bc
            float r1 = r6.getRawX()
            android.graphics.PointF r4 = r5.f1987s3
            float r4 = r4.x
            float r1 = r1 - r4
            float r1 = java.lang.Math.abs(r1)
            int r4 = r5.f1976a
            float r4 = (float) r4
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x00bc
            float r1 = java.lang.Math.abs(r0)
            r4 = 1092616192(0x41200000, float:10.0)
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 > 0) goto L_0x0075
            int r1 = r5.getScrollX()
            int r1 = java.lang.Math.abs(r1)
            r4 = 10
            if (r1 <= r4) goto L_0x007c
        L_0x0075:
            android.view.ViewParent r1 = r5.getParent()
            r1.requestDisallowInterceptTouchEvent(r2)
        L_0x007c:
            float r1 = java.lang.Math.abs(r0)
            int r2 = r5.f1976a
            float r2 = (float) r2
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0089
            r5.f1993y = r3
        L_0x0089:
            int r0 = (int) r0
            r5.scrollBy(r0, r3)
            boolean r0 = r5.f1981k
            if (r0 == 0) goto L_0x00a6
            int r0 = r5.getScrollX()
            if (r0 >= 0) goto L_0x009a
            r5.scrollTo(r3, r3)
        L_0x009a:
            int r0 = r5.getScrollX()
            int r1 = r5.f1982n
            if (r0 <= r1) goto L_0x00bc
            r5.scrollTo(r1, r3)
            goto L_0x00bc
        L_0x00a6:
            int r0 = r5.getScrollX()
            int r1 = r5.f1982n
            int r2 = -r1
            if (r0 >= r2) goto L_0x00b3
            int r0 = -r1
            r5.scrollTo(r0, r3)
        L_0x00b3:
            int r0 = r5.getScrollX()
            if (r0 <= 0) goto L_0x00bc
            r5.scrollTo(r3, r3)
        L_0x00bc:
            android.graphics.PointF r0 = r5.f1986r3
            float r1 = r6.getRawX()
            float r2 = r6.getRawY()
            r0.set(r1, r2)
            goto L_0x018b
        L_0x00cb:
            float r1 = r6.getRawX()
            android.graphics.PointF r4 = r5.f1987s3
            float r4 = r4.x
            float r1 = r1 - r4
            float r1 = java.lang.Math.abs(r1)
            int r4 = r5.f1976a
            float r4 = (float) r4
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x00e1
            r5.f1992x = r2
        L_0x00e1:
            int r1 = r5.getScrollX()
            int r1 = java.lang.Math.abs(r1)
            if (r1 <= 0) goto L_0x0138
            boolean r1 = r5.f1985q3
            if (r1 != 0) goto L_0x0138
            r1 = 1000(0x3e8, float:1.401E-42)
            int r2 = r5.f1977b
            float r2 = (float) r2
            r0.computeCurrentVelocity(r1, r2)
            int r1 = r5.f1988t3
            float r0 = r0.getXVelocity(r1)
            float r1 = java.lang.Math.abs(r0)
            r2 = 1148846080(0x447a0000, float:1000.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0125
            r1 = -998637568(0xffffffffc47a0000, float:-1000.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0119
            boolean r0 = r5.f1981k
            if (r0 == 0) goto L_0x0115
            r5.mo8718c()
            goto L_0x0138
        L_0x0115:
            r5.mo8717b()
            goto L_0x0138
        L_0x0119:
            boolean r0 = r5.f1981k
            if (r0 == 0) goto L_0x0121
            r5.mo8717b()
            goto L_0x0138
        L_0x0121:
            r5.mo8718c()
            goto L_0x0138
        L_0x0125:
            int r0 = r5.getScrollX()
            int r0 = java.lang.Math.abs(r0)
            int r1 = r5.f1984q
            if (r0 <= r1) goto L_0x0135
            r5.mo8718c()
            goto L_0x0138
        L_0x0135:
            r5.mo8717b()
        L_0x0138:
            android.view.VelocityTracker r0 = r5.f1979d
            if (r0 == 0) goto L_0x0147
            r0.clear()
            android.view.VelocityTracker r0 = r5.f1979d
            r0.recycle()
            r0 = 0
            r5.f1979d = r0
        L_0x0147:
            f1974x3 = r3
            goto L_0x018b
        L_0x014a:
            r5.f1992x = r3
            r5.f1993y = r2
            r5.f1985q3 = r3
            boolean r0 = f1974x3
            if (r0 == 0) goto L_0x0155
            return r3
        L_0x0155:
            f1974x3 = r2
            android.graphics.PointF r0 = r5.f1986r3
            float r1 = r6.getRawX()
            float r4 = r6.getRawY()
            r0.set(r1, r4)
            android.graphics.PointF r0 = r5.f1987s3
            float r1 = r6.getRawX()
            float r4 = r6.getRawY()
            r0.set(r1, r4)
            org.chromium.chrome.browser.favorites.BookmarkSwipeLayout r0 = f1975y3
            if (r0 == 0) goto L_0x0185
            if (r0 == r5) goto L_0x017e
            r0.mo8717b()
            boolean r0 = r5.f1980e
            r5.f1985q3 = r0
        L_0x017e:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
        L_0x0185:
            int r0 = r6.getPointerId(r3)
            r5.f1988t3 = r0
        L_0x018b:
            boolean r6 = super.dispatchTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.favorites.BookmarkSwipeLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public void onDetachedFromWindow() {
        BookmarkSwipeLayout bookmarkSwipeLayout = f1975y3;
        if (this == bookmarkSwipeLayout) {
            bookmarkSwipeLayout.mo8717b();
            f1975y3 = null;
        }
        super.onDetachedFromWindow();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f1978c) {
            int action = motionEvent.getAction();
            if (action == 1) {
                if (this.f1981k) {
                    if (getScrollX() > this.f1976a && motionEvent.getX() < ((float) (getWidth() - getScrollX()))) {
                        if (this.f1993y) {
                            mo8717b();
                        }
                        return true;
                    }
                } else if ((-getScrollX()) > this.f1976a && motionEvent.getX() > ((float) (-getScrollX()))) {
                    if (this.f1993y) {
                        mo8717b();
                    }
                    return true;
                }
                if (this.f1992x) {
                    return true;
                }
            } else if (action == 2 && Math.abs(motionEvent.getRawX() - this.f1987s3.x) > ((float) this.f1976a)) {
                return true;
            }
            if (this.f1985q3) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        int paddingLeft = getPaddingLeft();
        int paddingLeft2 = getPaddingLeft();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                if (i5 == 0) {
                    childAt.layout(paddingLeft, getPaddingTop(), childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + getPaddingTop());
                    measuredWidth = childAt.getMeasuredWidth();
                } else if (this.f1981k) {
                    childAt.layout(paddingLeft, getPaddingTop(), childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + getPaddingTop());
                    measuredWidth = childAt.getMeasuredWidth();
                } else {
                    childAt.layout(paddingLeft2 - childAt.getMeasuredWidth(), getPaddingTop(), paddingLeft2, childAt.getMeasuredHeight() + getPaddingTop());
                    paddingLeft2 -= childAt.getMeasuredWidth();
                }
                paddingLeft = measuredWidth + paddingLeft;
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setClickable(true);
        this.f1982n = 0;
        int childCount = getChildCount();
        boolean z = View.MeasureSpec.getMode(i2) != 1073741824;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            childAt.setClickable(true);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                i4 = Math.max(i4, childAt.getMeasuredHeight());
                if (z && marginLayoutParams.height == -1) {
                    z2 = true;
                }
                if (i5 > 0) {
                    this.f1982n = childAt.getMeasuredWidth() + this.f1982n;
                } else {
                    this.f1983p = childAt;
                    i3 = childAt.getMeasuredWidth();
                }
            }
        }
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + i3, getPaddingBottom() + getPaddingTop() + i4);
        this.f1984q = this.f1982n / 2;
        if (z2) {
            mo8716a(childCount, i);
        }
    }

    public boolean performLongClick() {
        if (Math.abs(getScrollX()) > this.f1976a) {
            return false;
        }
        return super.performLongClick();
    }

    public void setSwipeEnable(boolean z) {
        this.f1978c = z;
    }

    public BookmarkSwipeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BookmarkSwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1993y = true;
        this.f1986r3 = new PointF();
        this.f1987s3 = new PointF();
        this.f1976a = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f1977b = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.f1978c = true;
        this.f1980e = true;
        this.f1981k = true;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, yx0.BookmarkSwipeLayout, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == yx0.BookmarkSwipeLayout_swipe_enable) {
                this.f1978c = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == yx0.BookmarkSwipeLayout_iOS) {
                this.f1980e = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == yx0.BookmarkSwipeLayout_left_swipe) {
                this.f1981k = obtainStyledAttributes.getBoolean(index, true);
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void mo8715a() {
        ValueAnimator valueAnimator = this.f1990v3;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f1990v3.cancel();
        }
        ValueAnimator valueAnimator2 = this.f1989u3;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.f1989u3.cancel();
        }
    }
}
