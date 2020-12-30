package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: PG */
public final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {

    /* renamed from: a */
    public final Gson f1037a;

    /* renamed from: b */
    public final TypeAdapter<T> f1038b;

    /* renamed from: c */
    public final Type f1039c;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.f1037a = gson;
        this.f1038b = typeAdapter;
        this.f1039c = type;
    }

    public T read(VK vk) throws IOException {
        return this.f1038b.read(vk);
    }

    public void write(WK wk, T t) throws IOException {
        TypeAdapter<T> typeAdapter = this.f1038b;
        Type type = this.f1039c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.f1039c) {
            typeAdapter = this.f1037a.mo2091a(new TK(type));
            if (typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter<T> typeAdapter2 = this.f1038b;
                if (!(typeAdapter2 instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    typeAdapter = typeAdapter2;
                }
            }
        }
        typeAdapter.write(wk, t);
    }
}
