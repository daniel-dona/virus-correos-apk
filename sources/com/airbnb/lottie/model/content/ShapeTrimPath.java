package com.airbnb.lottie.model.content;

/* compiled from: PG */
public class ShapeTrimPath implements pj {

    /* renamed from: a */
    public final String f99a;

    /* renamed from: b */
    public final Type f100b;

    /* renamed from: c */
    public final bj f101c;

    /* renamed from: d */
    public final bj f102d;

    /* renamed from: e */
    public final bj f103e;

    /* renamed from: f */
    public final boolean f104f;

    /* compiled from: PG */
    public enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type forId(int i) {
            if (i == 1) {
                return SIMULTANEOUSLY;
            }
            if (i == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException(Eo.b("Unknown trim path type ", i));
        }
    }

    public ShapeTrimPath(String str, Type type, bj bjVar, bj bjVar2, bj bjVar3, boolean z) {
        this.f99a = str;
        this.f100b = type;
        this.f101c = bjVar;
        this.f102d = bjVar2;
        this.f103e = bjVar3;
        this.f104f = z;
    }

    /* renamed from: a */
    public ki mo116a(Uh uh, Aj aj) {
        return new Ai(aj, this);
    }

    public String toString() {
        StringBuilder a = Eo.a("Trim Path: {start: ");
        a.append(this.f101c);
        a.append(", end: ");
        a.append(this.f102d);
        a.append(", offset: ");
        a.append(this.f103e);
        a.append("}");
        return a.toString();
    }
}
