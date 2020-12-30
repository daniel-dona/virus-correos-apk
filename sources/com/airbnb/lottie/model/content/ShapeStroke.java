package com.airbnb.lottie.model.content;

import android.graphics.Paint;
import java.util.List;

/* compiled from: PG */
public class ShapeStroke implements pj {

    /* renamed from: a */
    public final String f89a;

    /* renamed from: b */
    public final bj f90b;

    /* renamed from: c */
    public final List<bj> f91c;

    /* renamed from: d */
    public final aj f92d;

    /* renamed from: e */
    public final dj f93e;

    /* renamed from: f */
    public final bj f94f;

    /* renamed from: g */
    public final LineCapType f95g;

    /* renamed from: h */
    public final LineJoinType f96h;

    /* renamed from: i */
    public final float f97i;

    /* renamed from: j */
    public final boolean f98j;

    /* compiled from: PG */
    public enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap toPaintCap() {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return Paint.Cap.BUTT;
            }
            if (ordinal != 1) {
                return Paint.Cap.SQUARE;
            }
            return Paint.Cap.ROUND;
        }
    }

    /* compiled from: PG */
    public enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join toPaintJoin() {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return Paint.Join.MITER;
            }
            if (ordinal == 1) {
                return Paint.Join.ROUND;
            }
            if (ordinal != 2) {
                return null;
            }
            return Paint.Join.BEVEL;
        }
    }

    public ShapeStroke(String str, bj bjVar, List<bj> list, aj ajVar, dj djVar, bj bjVar2, LineCapType lineCapType, LineJoinType lineJoinType, float f, boolean z) {
        this.f89a = str;
        this.f90b = bjVar;
        this.f91c = list;
        this.f92d = ajVar;
        this.f93e = djVar;
        this.f94f = bjVar2;
        this.f95g = lineCapType;
        this.f96h = lineJoinType;
        this.f97i = f;
        this.f98j = z;
    }

    /* renamed from: a */
    public ki mo113a(Uh uh, Aj aj) {
        return new zi(uh, aj, this);
    }
}
