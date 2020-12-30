package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.animation.Animation;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;

/* compiled from: PG */
public class ReactViewGroup extends ViewGroup implements kA, HA, LA, jA, RA {

    /* renamed from: v3 */
    public static final ViewGroup.LayoutParams f806v3 = new ViewGroup.LayoutParams(0, 0);

    /* renamed from: w3 */
    public static final Rect f807w3 = new Rect();

    /* renamed from: a */
    public boolean f808a = false;

    /* renamed from: b */
    public View[] f809b = null;

    /* renamed from: c */
    public int f810c;

    /* renamed from: d */
    public Rect f811d;

    /* renamed from: e */
    public Rect f812e;

    /* renamed from: k */
    public String f813k;

    /* renamed from: n */
    public PointerEvents f814n = PointerEvents.AUTO;

    /* renamed from: p */
    public C0187b f815p;

    /* renamed from: q */
    public ReactViewBackgroundDrawable f816q;

    /* renamed from: q3 */
    public final KB f817q3;

    /* renamed from: r3 */
    public Path f818r3;

    /* renamed from: s3 */
    public int f819s3;

    /* renamed from: t3 */
    public float f820t3 = 1.0f;

    /* renamed from: u3 */
    public String f821u3 = "visible";

    /* renamed from: x */
    public iA f822x;

    /* renamed from: y */
    public boolean f823y = false;

    /* renamed from: com.facebook.react.views.view.ReactViewGroup$b */
    /* compiled from: PG */
    public static final class C0187b implements View.OnLayoutChangeListener {

        /* renamed from: a */
        public final ReactViewGroup f824a;

        public /* synthetic */ C0187b(ReactViewGroup reactViewGroup, C0186a aVar) {
            this.f824a = reactViewGroup;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.f824a.mo1725a()) {
                ReactViewGroup reactViewGroup = this.f824a;
                if (reactViewGroup.f808a && reactViewGroup.getParent() != null) {
                    Kw.a(reactViewGroup.f811d);
                    Kw.a(reactViewGroup.f809b);
                    ReactViewGroup.f807w3.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    Rect rect = reactViewGroup.f811d;
                    Rect rect2 = ReactViewGroup.f807w3;
                    if (rect.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom) != (view.getParent() != null)) {
                        int i9 = 0;
                        for (int i10 = 0; i10 < reactViewGroup.f810c; i10++) {
                            View[] viewArr = reactViewGroup.f809b;
                            if (viewArr[i10] == view) {
                                reactViewGroup.mo1721a(reactViewGroup.f811d, i10, i9);
                                return;
                            }
                            if (viewArr[i10].getParent() == null) {
                                i9++;
                            }
                        }
                    }
                }
            }
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        setClipChildren(false);
        this.f817q3 = new KB(this);
    }

    /* renamed from: a */
    public boolean mo1725a() {
        return this.f808a;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        this.f817q3.a(view);
        setChildrenDrawingOrderEnabled(this.f817q3.a());
        super.addView(view, i, layoutParams);
    }

    /* renamed from: b */
    public final void mo1729b(Rect rect) {
        Kw.a(this.f809b);
        int i = 0;
        for (int i2 = 0; i2 < this.f810c; i2++) {
            mo1721a(rect, i2, i);
            if (this.f809b[i2].getParent() == null) {
                i++;
            }
        }
    }

    /* renamed from: c */
    public void mo1731c() {
        KB kb = this.f817q3;
        kb.b = 0;
        for (int i = 0; i < kb.a.getChildCount(); i++) {
            if (ViewGroupManager.getViewZIndex(kb.a.getChildAt(i)) != null) {
                kb.b++;
            }
        }
        kb.c = null;
        setChildrenDrawingOrderEnabled(this.f817q3.a());
        invalidate();
    }

    /* renamed from: d */
    public PointerEvents mo1732d() {
        return this.f814n;
    }

    public void dispatchDraw(Canvas canvas) {
        try {
            mo1719a(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException e) {
            pq.a("ReactNative", "NullPointerException when executing ViewGroup.dispatchDraw method", e);
        } catch (StackOverflowError e2) {
            SA a = TA.a(this);
            if (a != null) {
                a.a(e2);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new IllegalViewOperationException("StackOverflowException", this, e2));
            } else {
                throw e2;
            }
        }
    }

    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e) {
            pq.a("ReactNative", "NullPointerException when executing dispatchProvideStructure", e);
        }
    }

    public void dispatchSetPressed(boolean z) {
    }

    /* renamed from: e */
    public void mo1736e() {
        if (this.f808a) {
            Kw.a(this.f811d);
            Kw.a(this.f809b);
            IA.a(this, this.f811d);
            mo1729b(this.f811d);
        }
    }

    /* renamed from: f */
    public int mo1737f() {
        return this.f810c;
    }

    /* renamed from: g */
    public final ReactViewBackgroundDrawable mo1738g() {
        if (this.f816q == null) {
            this.f816q = new ReactViewBackgroundDrawable(getContext());
            Drawable background = getBackground();
            super.setBackground((Drawable) null);
            if (background == null) {
                super.setBackground(this.f816q);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.f816q, background}));
            }
            this.f819s3 = Ty.a().b(getContext()) ? 1 : 0;
            ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.f816q;
            int i = this.f819s3;
            if (reactViewBackgroundDrawable.f805z != i) {
                reactViewBackgroundDrawable.f805z = i;
            }
        }
        return this.f816q;
    }

    public int getChildDrawingOrder(int i, int i2) {
        return this.f817q3.a(i, i2);
    }

    /* renamed from: h */
    public void mo1740h() {
        Kw.a(this.f808a);
        Kw.a(this.f809b);
        for (int i = 0; i < this.f810c; i++) {
            this.f809b[i].removeOnLayoutChangeListener(this.f815p);
        }
        removeAllViewsInLayout();
        this.f810c = 0;
    }

    public boolean hasOverlappingRendering() {
        return this.f823y;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f808a) {
            mo1736e();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        hA hAVar = this.f822x;
        if (hAVar != null) {
            int i = hAVar.a;
            boolean z = false;
            if (!(i == -1 || motionEvent.getAction() == 1 || getId() != i)) {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        PointerEvents pointerEvents = this.f814n;
        if (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public void onMeasure(int i, int i2) {
        zA.a(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    public void onRtlPropertiesChanged(int i) {
        int i2;
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.f816q;
        if (reactViewBackgroundDrawable != null && reactViewBackgroundDrawable.f805z != (i2 = this.f819s3)) {
            reactViewBackgroundDrawable.f805z = i2;
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f808a) {
            mo1736e();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents = this.f814n;
        return (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_NONE) ? false : true;
    }

    public void removeView(View view) {
        this.f817q3.b(view);
        setChildrenDrawingOrderEnabled(this.f817q3.a());
        super.removeView(view);
    }

    public void removeViewAt(int i) {
        this.f817q3.b(getChildAt(i));
        setChildrenDrawingOrderEnabled(this.f817q3.a());
        super.removeViewAt(i);
    }

    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    public void setBackfaceVisibility(String str) {
        this.f821u3 = str;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.f821u3.equals("visible")) {
            setAlpha(this.f820t3);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.f820t3);
        } else {
            setAlpha(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    public void setBackgroundColor(int i) {
        if (i != 0 || this.f816q != null) {
            ReactViewBackgroundDrawable g = mo1738g();
            g.f801v = i;
            g.invalidateSelf();
        }
    }

    public void setBorderColor(int i, float f, float f2) {
        mo1738g().mo1703a(i, f, f2);
    }

    public void setBorderRadius(float f) {
        ReactViewBackgroundDrawable g = mo1738g();
        if (!sA.a(g.f799t, f)) {
            g.f799t = f;
            g.f798s = true;
            g.invalidateSelf();
        }
    }

    public void setBorderStyle(String str) {
        mo1738g().mo1705a(str);
    }

    public void setBorderWidth(int i, float f) {
        mo1738g().mo1702a(i, f);
    }

    public void setHitSlopRect(Rect rect) {
        this.f812e = rect;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.f823y = z;
    }

    public void setOnInterceptTouchEventListener(iA iAVar) {
        this.f822x = iAVar;
    }

    public void setOpacityIfPossible(float f) {
        this.f820t3 = f;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setOverflow(String str) {
        this.f813k = str;
        invalidate();
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z != this.f808a) {
            this.f808a = z;
            if (z) {
                this.f811d = new Rect();
                IA.a(this, this.f811d);
                this.f810c = getChildCount();
                this.f809b = new View[Math.max(12, this.f810c)];
                this.f815p = new C0187b(this, (C0186a) null);
                for (int i = 0; i < this.f810c; i++) {
                    View childAt = getChildAt(i);
                    this.f809b[i] = childAt;
                    childAt.addOnLayoutChangeListener(this.f815p);
                }
                mo1736e();
                return;
            }
            Kw.a(this.f811d);
            Kw.a(this.f809b);
            Kw.a(this.f815p);
            for (int i2 = 0; i2 < this.f810c; i2++) {
                this.f809b[i2].removeOnLayoutChangeListener(this.f815p);
            }
            getDrawingRect(this.f811d);
            mo1729b(this.f811d);
            this.f809b = null;
            this.f811d = null;
            this.f810c = 0;
            this.f815p = null;
        }
    }

    public void setTranslucentBackgroundDrawable(Drawable drawable) {
        super.setBackground((Drawable) null);
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.f816q;
        if (reactViewBackgroundDrawable != null && drawable != null) {
            super.setBackground(new LayerDrawable(new Drawable[]{reactViewBackgroundDrawable, drawable}));
        } else if (drawable != null) {
            super.setBackground(drawable);
        }
    }

    /* renamed from: a */
    public void mo1720a(Rect rect) {
        rect.set(this.f811d);
    }

    /* renamed from: a */
    public final void mo1721a(Rect rect, int i, int i2) {
        HA[] haArr = this.f809b;
        Kw.a(haArr);
        HA ha = ((View[]) haArr)[i];
        f807w3.set(ha.getLeft(), ha.getTop(), ha.getRight(), ha.getBottom());
        Rect rect2 = f807w3;
        boolean intersects = rect.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom);
        Animation animation = ha.getAnimation();
        boolean z = true;
        boolean z2 = animation != null && !animation.hasEnded();
        if (!intersects && ha.getParent() != null && !z2) {
            super.removeViewsInLayout(i - i2, 1);
        } else if (intersects && ha.getParent() == null) {
            super.addViewInLayout(ha, i - i2, f806v3, true);
            invalidate();
        } else if (!intersects) {
            z = false;
        }
        if (z && (ha instanceof HA)) {
            HA ha2 = ha;
            if (ha2.a()) {
                ha2.e();
            }
        }
    }

    /* renamed from: b */
    public View mo1728b(int i) {
        View[] viewArr = this.f809b;
        Kw.a(viewArr);
        return viewArr[i];
    }

    /* renamed from: b */
    public void mo1730b(View view, int i) {
        Kw.a(this.f808a);
        Kw.a(this.f811d);
        Kw.a(this.f809b);
        View[] viewArr = this.f809b;
        Kw.a(viewArr);
        int i2 = this.f810c;
        int length = viewArr.length;
        if (i == i2) {
            if (length == i2) {
                this.f809b = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.f809b, 0, length);
                viewArr = this.f809b;
            }
            int i3 = this.f810c;
            this.f810c = i3 + 1;
            viewArr[i3] = view;
        } else if (i < i2) {
            if (length == i2) {
                this.f809b = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.f809b, 0, i);
                System.arraycopy(viewArr, i, this.f809b, i + 1, i2 - i);
                viewArr = this.f809b;
            } else {
                System.arraycopy(viewArr, i, viewArr, i + 1, i2 - i);
            }
            viewArr[i] = view;
            this.f810c++;
        } else {
            throw new IndexOutOfBoundsException(Eo.a("index=", i, " count=", i2));
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            if (this.f809b[i5].getParent() == null) {
                i4++;
            }
        }
        mo1721a(this.f811d, i, i4);
        view.addOnLayoutChangeListener(this.f815p);
    }

    public void setBorderRadius(float f, int i) {
        mo1738g().mo1707b(f, i);
    }

    /* renamed from: a */
    public int mo1718a(int i) {
        return this.f817q3.a() ? this.f817q3.a(getChildCount(), i) : i;
    }

    /* renamed from: a */
    public void mo1724a(PointerEvents pointerEvents) {
        this.f814n = pointerEvents;
    }

    /* renamed from: a */
    public void mo1723a(View view, int i) {
        mo1730b(view, i);
    }

    /* renamed from: a */
    public void mo1722a(View view) {
        Kw.a(this.f808a);
        Kw.a(this.f811d);
        Kw.a(this.f809b);
        view.removeOnLayoutChangeListener(this.f815p);
        int i = this.f810c;
        View[] viewArr = this.f809b;
        Kw.a(viewArr);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                i2 = -1;
                break;
            } else if (viewArr[i2] == view) {
                break;
            } else {
                i2++;
            }
        }
        if (this.f809b[i2].getParent() != null) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.f809b[i4].getParent() == null) {
                    i3++;
                }
            }
            super.removeViewsInLayout(i2 - i3, 1);
        }
        View[] viewArr2 = this.f809b;
        Kw.a(viewArr2);
        int i5 = this.f810c;
        int i6 = i5 - 1;
        if (i2 == i6) {
            this.f810c = i6;
            viewArr2[i6] = null;
        } else if (i2 < 0 || i2 >= i5) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(viewArr2, i2 + 1, viewArr2, i2, (i5 - i2) - 1);
            int i7 = this.f810c - 1;
            this.f810c = i7;
            viewArr2[i7] = null;
        }
    }

    /* renamed from: b */
    public Rect mo1727b() {
        return this.f812e;
    }

    /* renamed from: a */
    public final void mo1719a(Canvas canvas) {
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        Canvas canvas2 = canvas;
        String str = this.f813k;
        if (str != null) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1217487446) {
                if (hashCode == 466743410 && str.equals("visible")) {
                    c = 0;
                }
            } else if (str.equals("hidden")) {
                c = 1;
            }
            if (c == 0) {
                Path path = this.f818r3;
                if (path != null) {
                    path.rewind();
                }
            } else if (c == 1) {
                float width = (float) getWidth();
                float height = (float) getHeight();
                ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.f816q;
                if (reactViewBackgroundDrawable != null) {
                    RectF a = reactViewBackgroundDrawable.mo1700a();
                    if (a.top > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a.left > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a.bottom > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER || a.right > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                        f2 = a.left + BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                        f = a.top + BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                        width -= a.right;
                        height -= a.bottom;
                    } else {
                        f2 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                        f = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                    }
                    ReactViewBackgroundDrawable reactViewBackgroundDrawable2 = this.f816q;
                    float f5 = eF.a(reactViewBackgroundDrawable2.f799t) ? BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER : reactViewBackgroundDrawable2.f799t;
                    float a2 = this.f816q.mo1697a(f5, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_LEFT);
                    float a3 = this.f816q.mo1697a(f5, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_RIGHT);
                    float a4 = this.f816q.mo1697a(f5, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_LEFT);
                    float a5 = this.f816q.mo1697a(f5, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_RIGHT);
                    boolean z2 = this.f819s3 == 1;
                    float a6 = this.f816q.mo1698a(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_START);
                    float a7 = this.f816q.mo1698a(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_END);
                    float a8 = this.f816q.mo1698a(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_START);
                    float f6 = a5;
                    float a9 = this.f816q.mo1698a(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_END);
                    float f7 = a2;
                    if (Ty.a().a(getContext())) {
                        if (!eF.a(a6)) {
                            f7 = a6;
                        }
                        if (eF.a(a7)) {
                            a7 = a3;
                        }
                        if (eF.a(a8)) {
                            a8 = a4;
                        }
                        if (!eF.a(a9)) {
                            f6 = a9;
                        }
                        float f8 = z2 ? a7 : f7;
                        if (!z2) {
                            f7 = a7;
                        }
                        float f9 = z2 ? f6 : a8;
                        if (z2) {
                            f6 = a8;
                        }
                        f4 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                        float f10 = f8;
                        f3 = f9;
                        a7 = f7;
                        f7 = f10;
                    } else {
                        float f11 = z2 ? a7 : a6;
                        if (z2) {
                            a7 = a6;
                        }
                        f3 = z2 ? a9 : a8;
                        if (z2) {
                            a9 = a8;
                        }
                        if (!eF.a(f11)) {
                            f7 = f11;
                        }
                        if (eF.a(a7)) {
                            a7 = a3;
                        }
                        if (eF.a(f3)) {
                            f3 = a4;
                        }
                        if (!eF.a(a9)) {
                            f6 = a9;
                        }
                        f4 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                    }
                    if (f7 > f4 || a7 > f4 || f6 > f4 || f3 > f4) {
                        if (this.f818r3 == null) {
                            this.f818r3 = new Path();
                        }
                        this.f818r3.rewind();
                        this.f818r3.addRoundRect(new RectF(f2, f, width, height), new float[]{Math.max(f7 - a.left, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(f7 - a.top, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(a7 - a.right, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(a7 - a.top, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(f6 - a.right, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(f6 - a.bottom, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(f3 - a.left, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER), Math.max(f3 - a.bottom, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER)}, Path.Direction.CW);
                        canvas2.clipPath(this.f818r3);
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                    f2 = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                    f = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                }
                if (!z) {
                    canvas2.clipRect(new RectF(f2, f, width, height));
                }
            }
        }
    }
}
