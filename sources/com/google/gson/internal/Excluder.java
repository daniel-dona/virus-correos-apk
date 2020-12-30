package com.google.gson.internal;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
public final class Excluder implements YJ, Cloneable {

    /* renamed from: n */
    public static final Excluder f947n = new Excluder();

    /* renamed from: a */
    public double f948a = -1.0d;

    /* renamed from: b */
    public int f949b = 136;

    /* renamed from: c */
    public boolean f950c = true;

    /* renamed from: d */
    public boolean f951d;

    /* renamed from: e */
    public List<JJ> f952e = Collections.emptyList();

    /* renamed from: k */
    public List<JJ> f953k = Collections.emptyList();

    /* renamed from: a */
    public Excluder mo2139a() {
        Excluder clone = clone();
        clone.f951d = true;
        return clone;
    }

    /* renamed from: b */
    public final boolean mo2145b(Class<?> cls, boolean z) {
        for (JJ a : z ? this.f952e : this.f953k) {
            if (a.a(cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public final boolean mo2146c(Class<?> cls) {
        if (cls.isMemberClass()) {
            if (!((cls.getModifiers() & 8) != 0)) {
                return true;
            }
        }
        return false;
    }

    public Excluder clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2138a(Gson gson, TK<T> tk) {
        Class rawType = tk.getRawType();
        boolean a = mo2141a(rawType);
        final boolean z = a || mo2145b(rawType, true);
        final boolean z2 = a || mo2145b(rawType, false);
        if (!z && !z2) {
            return null;
        }
        final Gson gson2 = gson;
        final TK<T> tk2 = tk;
        return new TypeAdapter<T>() {

            /* renamed from: a */
            public TypeAdapter<T> f954a;

            public T read(VK vk) throws IOException {
                if (z2) {
                    vk.w0();
                    return null;
                }
                TypeAdapter<T> typeAdapter = this.f954a;
                if (typeAdapter == null) {
                    typeAdapter = gson2.mo2092a((YJ) Excluder.this, tk2);
                    this.f954a = typeAdapter;
                }
                return typeAdapter.read(vk);
            }

            public void write(WK wk, T t) throws IOException {
                if (z) {
                    wk.E();
                    return;
                }
                TypeAdapter<T> typeAdapter = this.f954a;
                if (typeAdapter == null) {
                    typeAdapter = gson2.mo2092a((YJ) Excluder.this, tk2);
                    this.f954a = typeAdapter;
                }
                typeAdapter.write(wk, t);
            }
        };
    }

    /* renamed from: b */
    public final boolean mo2144b(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    /* renamed from: a */
    public boolean mo2143a(Field field, boolean z) {
        ZJ annotation;
        if ((this.f949b & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f948a != -1.0d && !mo2140a(field.getAnnotation(cK.class), field.getAnnotation(dK.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f951d && ((annotation = field.getAnnotation(ZJ.class)) == null || (!z ? !annotation.deserialize() : !annotation.serialize()))) {
            return true;
        }
        if ((!this.f950c && mo2146c(field.getType())) || mo2144b(field.getType())) {
            return true;
        }
        List<JJ> list = z ? this.f952e : this.f953k;
        if (list.isEmpty()) {
            return false;
        }
        KJ kj = new KJ(field);
        for (JJ a : list) {
            if (a.a(kj)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo2141a(Class<?> cls) {
        if (this.f948a != -1.0d && !mo2140a(cls.getAnnotation(cK.class), cls.getAnnotation(dK.class))) {
            return true;
        }
        if ((this.f950c || !mo2146c(cls)) && !mo2144b(cls)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo2142a(Class<?> cls, boolean z) {
        return mo2141a(cls) || mo2145b(cls, z);
    }

    /* renamed from: a */
    public final boolean mo2140a(cK cKVar, dK dKVar) {
        if (!(cKVar == null || cKVar.value() <= this.f948a)) {
            return false;
        }
        if (dKVar == null || dKVar.value() > this.f948a) {
            return true;
        }
        return false;
    }
}
