package com.facebook.react.views.slider;

import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import com.google.protobuf.ByteString;

/* compiled from: PG */
public class ReactSlider extends AppCompatSeekBar {

    /* renamed from: b */
    public double f686b = 0.0d;

    /* renamed from: c */
    public double f687c = 0.0d;

    /* renamed from: d */
    public double f688d = 0.0d;

    /* renamed from: e */
    public double f689e = 0.0d;

    /* renamed from: k */
    public double f690k = 0.0d;

    /* JADX WARNING: type inference failed for: r0v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    public ReactSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23 && i2 < 26) {
            ReactSlider.super.setStateListAnimator((StateListAnimator) null);
        }
    }

    /* renamed from: a */
    public void mo1472a(double d) {
        this.f687c = d;
        mo1473b();
    }

    /* renamed from: b */
    public void mo1474b(double d) {
        this.f686b = d;
        mo1473b();
    }

    /* renamed from: c */
    public void mo1476c(double d) {
        this.f689e = d;
        mo1473b();
    }

    /* renamed from: d */
    public void mo1477d(double d) {
        this.f688d = d;
        mo1475c();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* renamed from: a */
    public double mo1470a(int i) {
        if (i == getMax()) {
            return this.f687c;
        }
        double d = (double) i;
        double d2 = this.f689e;
        if (d2 <= 0.0d) {
            d2 = this.f690k;
        }
        Double.isNaN(d);
        Double.isNaN(d);
        return (d2 * d) + this.f686b;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* renamed from: b */
    public final void mo1473b() {
        if (this.f689e == 0.0d) {
            double d = this.f687c - this.f686b;
            double d2 = (double) ByteString.CONCATENATE_BY_COPY_SIZE;
            Double.isNaN(d2);
            Double.isNaN(d2);
            this.f690k = d / d2;
        }
        setMax(mo1471a());
        mo1475c();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* renamed from: c */
    public final void mo1475c() {
        double d = this.f688d;
        double d2 = this.f686b;
        double a = (double) mo1471a();
        Double.isNaN(a);
        Double.isNaN(a);
        setProgress((int) Math.round(((d - d2) / (this.f687c - d2)) * a));
    }

    /* renamed from: a */
    public final int mo1471a() {
        double d = this.f687c - this.f686b;
        double d2 = this.f689e;
        if (d2 <= 0.0d) {
            d2 = this.f690k;
        }
        return (int) Math.ceil(d / d2);
    }
}
