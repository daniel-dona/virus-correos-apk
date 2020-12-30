package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* compiled from: PG */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {

    /* renamed from: c */
    public static final YJ f993c = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2223a(Gson gson, TK<T> tk) {
            Type type;
            Type type2 = tk.getType();
            boolean z = type2 instanceof GenericArrayType;
            if (!z && (!(type2 instanceof Class) || !((Class) type2).isArray())) {
                return null;
            }
            if (z) {
                type = ((GenericArrayType) type2).getGenericComponentType();
            } else {
                type = ((Class) type2).getComponentType();
            }
            return new ArrayTypeAdapter(gson, gson.mo2091a(new TK(type)), C$Gson$Types.m885c(type));
        }
    };

    /* renamed from: a */
    public final Class<E> f994a;

    /* renamed from: b */
    public final TypeAdapter<E> f995b;

    public ArrayTypeAdapter(Gson gson, TypeAdapter<E> typeAdapter, Class<E> cls) {
        this.f995b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, cls);
        this.f994a = cls;
    }

    public Object read(VK vk) throws IOException {
        if (vk.j0() == JsonToken.NULL) {
            vk.Y();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        vk.a();
        while (vk.F()) {
            arrayList.add(this.f995b.read(vk));
        }
        vk.e();
        int size = arrayList.size();
        Object newInstance = Array.newInstance(this.f994a, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void write(WK wk, Object obj) throws IOException {
        if (obj == null) {
            wk.E();
            return;
        }
        wk.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f995b.write(wk, Array.get(obj, i));
        }
        wk.d();
    }
}
