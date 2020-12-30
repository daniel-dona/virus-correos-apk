package com.airbnb.lottie.model.content;

/* compiled from: PG */
public class Mask {

    /* renamed from: a */
    public final MaskMode f72a;

    /* renamed from: b */
    public final hj f73b;

    /* renamed from: c */
    public final dj f74c;

    /* renamed from: d */
    public final boolean f75d;

    /* compiled from: PG */
    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT
    }

    public Mask(MaskMode maskMode, hj hjVar, dj djVar, boolean z) {
        this.f72a = maskMode;
        this.f73b = hjVar;
        this.f74c = djVar;
        this.f75d = z;
    }

    /* renamed from: a */
    public MaskMode mo109a() {
        return this.f72a;
    }
}
