package com.facebook.drawee.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.react.uimanager.BaseViewManager;
import jq;
import ks;
import ms;

/* compiled from: PG */
public class DraweeView<DH extends ks> extends ImageView {

    /* renamed from: k */
    public static boolean f317k;

    /* renamed from: a */
    public final ms.a f318a = new ms.a();

    /* renamed from: b */
    public float f319b = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: c */
    public ns<DH> f320c;

    /* renamed from: d */
    public boolean f321d = false;

    /* renamed from: e */
    public boolean f322e = false;

    public DraweeView(Context context) {
        super(context);
        mo408a(context);
    }

    public static void setGlobalLegacyVisibilityHandlingEnabled(boolean z) {
        f317k = z;
    }

    /* renamed from: a */
    public final void mo408a(Context context) {
        if (!this.f321d) {
            boolean z = true;
            this.f321d = true;
            this.f320c = new ns<>((ks) null);
            if (Build.VERSION.SDK_INT >= 21) {
                ColorStateList imageTintList = getImageTintList();
                if (imageTintList != null) {
                    setColorFilter(imageTintList.getDefaultColor());
                } else {
                    return;
                }
            }
            if (!f317k || context.getApplicationInfo().targetSdkVersion < 24) {
                z = false;
            }
            this.f322e = z;
        }
    }

    /* renamed from: b */
    public void mo409b() {
        this.f320c.g();
    }

    /* renamed from: c */
    public js mo410c() {
        return this.f320c.e;
    }

    /* renamed from: d */
    public DH mo411d() {
        DH dh = this.f320c.d;
        kq.a(dh);
        return dh;
    }

    /* renamed from: e */
    public final void mo412e() {
        Drawable drawable;
        if (this.f322e && (drawable = getDrawable()) != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    /* renamed from: f */
    public void mo413f() {
        mo407a();
    }

    /* renamed from: g */
    public void mo414g() {
        mo409b();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mo412e();
        mo413f();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mo412e();
        mo414g();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mo412e();
        mo413f();
    }

    public void onMeasure(int i, int i2) {
        ms.a aVar = this.f318a;
        aVar.a = i;
        aVar.b = i2;
        float f = this.f319b;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (f > BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER && layoutParams != null) {
            if (ms.a(layoutParams.height)) {
                aVar.b = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (View.MeasureSpec.getSize(aVar.a) - paddingRight)) / f) + ((float) paddingBottom)), aVar.b), 1073741824);
            } else if (ms.a(layoutParams.width)) {
                aVar.a = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (View.MeasureSpec.getSize(aVar.b) - paddingBottom)) * f) + ((float) paddingRight)), aVar.a), 1073741824);
            }
        }
        ms.a aVar2 = this.f318a;
        super.onMeasure(aVar2.a, aVar2.b);
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mo412e();
        mo414g();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        ns<DH> nsVar = this.f320c;
        if (!nsVar.e()) {
            z = false;
        } else {
            z = nsVar.e.a(motionEvent);
        }
        if (z) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        mo412e();
    }

    public void setAspectRatio(float f) {
        if (f != this.f319b) {
            this.f319b = f;
            requestLayout();
        }
    }

    public void setController(js jsVar) {
        this.f320c.a(jsVar);
        super.setImageDrawable(this.f320c.d());
    }

    public void setHierarchy(DH dh) {
        this.f320c.a(dh);
        super.setImageDrawable(this.f320c.d());
    }

    @Deprecated
    public void setImageBitmap(Bitmap bitmap) {
        mo408a(getContext());
        this.f320c.a((js) null);
        super.setImageBitmap(bitmap);
    }

    @Deprecated
    public void setImageDrawable(Drawable drawable) {
        mo408a(getContext());
        this.f320c.a((js) null);
        super.setImageDrawable(drawable);
    }

    @Deprecated
    public void setImageResource(int i) {
        mo408a(getContext());
        this.f320c.a((js) null);
        super.setImageResource(i);
    }

    @Deprecated
    public void setImageURI(Uri uri) {
        mo408a(getContext());
        this.f320c.a((js) null);
        super.setImageURI(uri);
    }

    public void setLegacyVisibilityHandlingEnabled(boolean z) {
        this.f322e = z;
    }

    public String toString() {
        jq.a a = jq.a(this);
        ns<DH> nsVar = this.f320c;
        a.a("holder", nsVar != null ? nsVar.toString() : "<no holder set>");
        return a.toString();
    }

    public DraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo408a(context);
    }

    /* renamed from: a */
    public void mo407a() {
        this.f320c.f();
    }

    public DraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo408a(context);
    }
}
