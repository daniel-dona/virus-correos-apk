package com.airbnb.lottie.model.content;

import android.graphics.PointF;

/* compiled from: PG */
public class PolystarShape implements pj {

    /* renamed from: a */
    public final String f79a;

    /* renamed from: b */
    public final Type f80b;

    /* renamed from: c */
    public final bj f81c;

    /* renamed from: d */
    public final mj<PointF, PointF> f82d;

    /* renamed from: e */
    public final bj f83e;

    /* renamed from: f */
    public final bj f84f;

    /* renamed from: g */
    public final bj f85g;

    /* renamed from: h */
    public final bj f86h;

    /* renamed from: i */
    public final bj f87i;

    /* renamed from: j */
    public final boolean f88j;

    /* compiled from: PG */
    public enum Type {
        STAR(1),
        POLYGON(2);
        
        public final int value;

        /* access modifiers changed from: public */
        Type(int i) {
            this.value = i;
        }

        public static Type forValue(int i) {
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, bj bjVar, mj<PointF, PointF> mjVar, bj bjVar2, bj bjVar3, bj bjVar4, bj bjVar5, bj bjVar6, boolean z) {
        this.f79a = str;
        this.f80b = type;
        this.f81c = bjVar;
        this.f82d = mjVar;
        this.f83e = bjVar2;
        this.f84f = bjVar3;
        this.f85g = bjVar4;
        this.f86h = bjVar5;
        this.f87i = bjVar6;
        this.f88j = z;
    }

    /* renamed from: a */
    public ki mo112a(Uh uh, Aj aj) {
        return new vi(uh, aj, this);
    }
}
