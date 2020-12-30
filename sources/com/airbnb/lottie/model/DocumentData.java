package com.airbnb.lottie.model;

/* compiled from: PG */
public class DocumentData {

    /* renamed from: a */
    public final String f61a;

    /* renamed from: b */
    public final String f62b;

    /* renamed from: c */
    public final double f63c;

    /* renamed from: d */
    public final Justification f64d;

    /* renamed from: e */
    public final int f65e;

    /* renamed from: f */
    public final double f66f;

    /* renamed from: g */
    public final double f67g;

    /* renamed from: h */
    public final int f68h;

    /* renamed from: i */
    public final int f69i;

    /* renamed from: j */
    public final double f70j;

    /* renamed from: k */
    public final boolean f71k;

    /* compiled from: PG */
    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, double d, Justification justification, int i, double d2, double d3, int i2, int i3, double d4, boolean z) {
        this.f61a = str;
        this.f62b = str2;
        this.f63c = d;
        this.f64d = justification;
        this.f65e = i;
        this.f66f = d2;
        this.f67g = d3;
        this.f68h = i2;
        this.f69i = i3;
        this.f70j = d4;
        this.f71k = z;
    }

    public int hashCode() {
        double b = (double) Eo.b(this.f62b, this.f61a.hashCode() * 31, 31);
        double d = this.f63c;
        Double.isNaN(b);
        int ordinal = this.f64d.ordinal();
        long doubleToLongBits = Double.doubleToLongBits(this.f66f);
        return ((((((ordinal + (((int) (b + d)) * 31)) * 31) + this.f65e) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.f68h;
    }
}
