package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ViewGroup;

/* compiled from: PG */
public class ReactTextView extends AppCompatTextView implements JA {

    /* renamed from: p */
    public static final ViewGroup.LayoutParams f705p = new ViewGroup.LayoutParams(0, 0);

    /* renamed from: a */
    public boolean f706a;

    /* renamed from: b */
    public int f707b = (getGravity() & 8388615);

    /* renamed from: c */
    public int f708c = (getGravity() & 112);

    /* renamed from: d */
    public int f709d = 0;

    /* renamed from: e */
    public int f710e = Integer.MAX_VALUE;

    /* renamed from: k */
    public TextUtils.TruncateAt f711k = TextUtils.TruncateAt.END;

    /* renamed from: n */
    public qE f712n = new qE(this);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView, android.view.View] */
    public ReactTextView(Context context) {
        super(context);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* renamed from: a */
    public int mo1525a(float f, float f2) {
        int i;
        CharSequence text = getText();
        int id = getId();
        int i2 = (int) f;
        int i3 = (int) f2;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i3);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i2 >= lineLeft && i2 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, (float) i2);
                ID[] idArr = (ID[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, ID.class);
                if (idArr != null) {
                    int length = text.length();
                    for (int i4 = 0; i4 < idArr.length; i4++) {
                        int spanStart = spanned.getSpanStart(idArr[i4]);
                        int spanEnd = spanned.getSpanEnd(idArr[i4]);
                        if (spanEnd > offsetForHorizontal && (i = spanEnd - spanStart) <= length) {
                            id = idArr[i4].a;
                            length = i;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                StringBuilder a = Eo.a("Crash in HorizontalMeasurementProvider: ");
                a.append(e.getMessage());
                pq.b("ReactNative", a.toString());
            }
        }
        return id;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* renamed from: b */
    public void mo1528b(int i) {
        if (i == 0) {
            i = this.f708c;
        }
        setGravity(i | (getGravity() & -113));
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void invalidateDrawable(Drawable drawable) {
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                if (vd.a == drawable) {
                    invalidate();
                }
            }
        }
        ReactTextView.super.invalidateDrawable(drawable);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void onAttachedToWindow() {
        ReactTextView.super.onAttachedToWindow();
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                vd.c.f();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void onDetachedFromWindow() {
        ReactTextView.super.onDetachedFromWindow();
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                vd.c.g();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void onFinishTemporaryDetach() {
        ReactTextView.super.onFinishTemporaryDetach();
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                vd.c.f();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void onStartTemporaryDetach() {
        ReactTextView.super.onStartTemporaryDetach();
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                vd.c.g();
            }
        }
    }

    public void setBackgroundColor(int i) {
        this.f712n.a(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        this.f712n.a().mo1703a(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.f712n.a().mo1701a(f);
    }

    public void setBorderStyle(String str) {
        this.f712n.a().mo1705a(str);
    }

    public void setBorderWidth(int i, float f) {
        this.f712n.a().mo1702a(i, f);
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.f711k = truncateAt;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = Integer.MAX_VALUE;
        }
        this.f710e = i;
        boolean z = true;
        if (this.f710e != 1) {
            z = false;
        }
        setSingleLine(z);
        setMaxLines(this.f710e);
    }

    public void setSpanned(Spannable spannable) {
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public void setText(MD md) {
        int i;
        int i2;
        this.f706a = md.c;
        if (getLayoutParams() == null) {
            setLayoutParams(f705p);
        }
        setText(md.a);
        setPadding((int) Math.floor((double) md.d), (int) Math.floor((double) md.e), (int) Math.floor((double) md.f), (int) Math.floor((double) md.g));
        int i3 = md.h;
        if (this.f709d != i3) {
            this.f709d = i3;
        }
        mo1527a(this.f709d);
        if (Build.VERSION.SDK_INT >= 23 && getBreakStrategy() != (i2 = md.i)) {
            setBreakStrategy(i2);
        }
        if (Build.VERSION.SDK_INT >= 26 && getJustificationMode() != (i = md.j)) {
            setJustificationMode(i);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.f706a && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (VD vd : (RD[]) spanned.getSpans(0, spanned.length(), RD.class)) {
                if (vd.a == drawable) {
                    return true;
                }
            }
        }
        return ReactTextView.super.verifyDrawable(drawable);
    }

    public void setBorderRadius(float f, int i) {
        this.f712n.a().mo1707b(f, i);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* renamed from: a */
    public void mo1527a(int i) {
        if (i == 0) {
            i = this.f707b;
        }
        setGravity(i | (getGravity() & -8 & -8388616));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* renamed from: a */
    public void mo1526a() {
        setEllipsize(this.f710e == Integer.MAX_VALUE ? null : this.f711k);
    }
}
