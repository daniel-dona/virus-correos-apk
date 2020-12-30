package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

/* compiled from: PG */
public final class CollectionTypeAdapterFactory implements YJ {

    /* renamed from: a */
    public final tK f996a;

    /* compiled from: PG */
    public static final class Adapter<E> extends TypeAdapter<Collection<E>> {

        /* renamed from: a */
        public final TypeAdapter<E> f997a;

        /* renamed from: b */
        public final zK<? extends Collection<E>> f998b;

        public Adapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, zK<? extends Collection<E>> zKVar) {
            this.f997a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f998b = zKVar;
        }

        /* renamed from: a */
        public void write(WK wk, Collection<E> collection) throws IOException {
            if (collection == null) {
                wk.E();
                return;
            }
            wk.b();
            for (E write : collection) {
                this.f997a.write(wk, write);
            }
            wk.d();
        }

        public Collection<E> read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            Collection<E> collection = (Collection) this.f998b.a();
            vk.a();
            while (vk.F()) {
                collection.add(this.f997a.read(vk));
            }
            vk.e();
            return collection;
        }
    }

    public CollectionTypeAdapterFactory(tK tKVar) {
        this.f996a = tKVar;
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2224a(Gson gson, TK<T> tk) {
        Type type;
        Type type2 = tk.getType();
        Class rawType = tk.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type b = C$Gson$Types.m883b(type2, rawType, Collection.class);
        if (b instanceof WildcardType) {
            b = ((WildcardType) b).getUpperBounds()[0];
        }
        if (b instanceof ParameterizedType) {
            type = ((ParameterizedType) b).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new Adapter(gson, type, gson.mo2091a(new TK(type)), this.f996a.a(tk));
    }
}
