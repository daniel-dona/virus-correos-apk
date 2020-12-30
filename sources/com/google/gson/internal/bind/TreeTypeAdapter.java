package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;

/* compiled from: PG */
public final class TreeTypeAdapter<T> extends TypeAdapter<T> {

    /* renamed from: a */
    public final XJ<T> f1024a;

    /* renamed from: b */
    public final QJ<T> f1025b;

    /* renamed from: c */
    public final Gson f1026c;

    /* renamed from: d */
    public final TK<T> f1027d;

    /* renamed from: e */
    public final YJ f1028e;

    /* renamed from: f */
    public final TreeTypeAdapter<T>.b f1029f = new C0236b((C0235a) null);

    /* renamed from: g */
    public TypeAdapter<T> f1030g;

    /* compiled from: PG */
    public static final class SingleTypeFactory implements YJ {

        /* renamed from: a */
        public final TK<?> f1031a;

        /* renamed from: b */
        public final boolean f1032b;

        /* renamed from: c */
        public final Class<?> f1033c;

        /* renamed from: d */
        public final XJ<?> f1034d;

        /* renamed from: e */
        public final QJ<?> f1035e;

        public SingleTypeFactory(Object obj, TK<?> tk, boolean z, Class<?> cls) {
            QJ<?> qj = null;
            this.f1034d = obj instanceof XJ ? (XJ) obj : null;
            this.f1035e = obj instanceof QJ ? (QJ) obj : qj;
            eK.a((this.f1034d == null && this.f1035e == null) ? false : true);
            this.f1031a = tk;
            this.f1032b = z;
            this.f1033c = cls;
        }

        /* renamed from: a */
        public <T> TypeAdapter<T> mo2240a(Gson gson, TK<T> tk) {
            boolean z;
            TK<?> tk2 = this.f1031a;
            if (tk2 != null) {
                z = tk2.equals(tk) || (this.f1032b && this.f1031a.getType() == tk.getRawType());
            } else {
                z = this.f1033c.isAssignableFrom(tk.getRawType());
            }
            if (z) {
                return new TreeTypeAdapter(this.f1034d, this.f1035e, gson, tk, this);
            }
            return null;
        }
    }

    /* renamed from: com.google.gson.internal.bind.TreeTypeAdapter$b */
    /* compiled from: PG */
    public final class C0236b implements WJ, PJ {
        public /* synthetic */ C0236b(C0235a aVar) {
        }
    }

    public TreeTypeAdapter(XJ<T> xj, QJ<T> qj, Gson gson, TK<T> tk, YJ yj) {
        this.f1024a = xj;
        this.f1025b = qj;
        this.f1026c = gson;
        this.f1027d = tk;
        this.f1028e = yj;
    }

    public T read(VK vk) throws IOException {
        if (this.f1025b == null) {
            TypeAdapter<T> typeAdapter = this.f1030g;
            if (typeAdapter == null) {
                typeAdapter = this.f1026c.mo2092a(this.f1028e, this.f1027d);
                this.f1030g = typeAdapter;
            }
            return typeAdapter.read(vk);
        }
        RJ a = EK.a(vk);
        if (a.f()) {
            return null;
        }
        return this.f1025b.deserialize(a, this.f1027d.getType(), this.f1029f);
    }

    public void write(WK wk, T t) throws IOException {
        XJ<T> xj = this.f1024a;
        if (xj == null) {
            TypeAdapter<T> typeAdapter = this.f1030g;
            if (typeAdapter == null) {
                typeAdapter = this.f1026c.mo2092a(this.f1028e, this.f1027d);
                this.f1030g = typeAdapter;
            }
            typeAdapter.write(wk, t);
        } else if (t == null) {
            wk.E();
        } else {
            TypeAdapters.f1063X.write(wk, xj.serialize(t, this.f1027d.getType(), this.f1029f));
        }
    }
}
