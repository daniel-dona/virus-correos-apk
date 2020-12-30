package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/* compiled from: PG */
public final class MapTypeAdapterFactory implements YJ {

    /* renamed from: a */
    public final tK f1002a;

    /* renamed from: b */
    public final boolean f1003b;

    /* compiled from: PG */
    public final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {

        /* renamed from: a */
        public final TypeAdapter<K> f1004a;

        /* renamed from: b */
        public final TypeAdapter<V> f1005b;

        /* renamed from: c */
        public final zK<? extends Map<K, V>> f1006c;

        public Adapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, zK<? extends Map<K, V>> zKVar) {
            this.f1004a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f1005b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.f1006c = zKVar;
        }

        /* renamed from: a */
        public void write(WK wk, Map<K, V> map) throws IOException {
            String str;
            if (map == null) {
                wk.E();
            } else if (!MapTypeAdapterFactory.this.f1003b) {
                wk.c();
                for (Map.Entry next : map.entrySet()) {
                    wk.z(String.valueOf(next.getKey()));
                    this.f1005b.write(wk, next.getValue());
                }
                wk.e();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry next2 : map.entrySet()) {
                    RJ jsonTree = this.f1004a.toJsonTree(next2.getKey());
                    arrayList.add(jsonTree);
                    arrayList2.add(next2.getValue());
                    z |= jsonTree.e() || (jsonTree instanceof TJ);
                }
                if (z) {
                    wk.b();
                    int size = arrayList.size();
                    while (i < size) {
                        wk.b();
                        TypeAdapters.f1063X.write(wk, (RJ) arrayList.get(i));
                        this.f1005b.write(wk, arrayList2.get(i));
                        wk.d();
                        i++;
                    }
                    wk.d();
                    return;
                }
                wk.c();
                int size2 = arrayList.size();
                while (i < size2) {
                    RJ rj = (RJ) arrayList.get(i);
                    if (rj.g()) {
                        VJ c = rj.c();
                        Object obj = c.a;
                        if (obj instanceof Number) {
                            str = String.valueOf(c.i());
                        } else if (obj instanceof Boolean) {
                            str = Boolean.toString(c.h());
                        } else if (c.j()) {
                            str = c.d();
                        } else {
                            throw new AssertionError();
                        }
                    } else if (rj instanceof SJ) {
                        str = "null";
                    } else {
                        throw new AssertionError();
                    }
                    wk.z(str);
                    this.f1005b.write(wk, arrayList2.get(i));
                    i++;
                }
                wk.e();
            }
        }

        public Map<K, V> read(VK vk) throws IOException {
            JsonToken j0 = vk.j0();
            if (j0 == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            Map<K, V> map = (Map) this.f1006c.a();
            if (j0 == JsonToken.BEGIN_ARRAY) {
                vk.a();
                while (vk.F()) {
                    vk.a();
                    K read = this.f1004a.read(vk);
                    if (map.put(read, this.f1005b.read(vk)) == null) {
                        vk.e();
                    } else {
                        throw new JsonSyntaxException("duplicate key: " + read);
                    }
                }
                vk.e();
            } else {
                vk.b();
                while (vk.F()) {
                    uK.a.a(vk);
                    K read2 = this.f1004a.read(vk);
                    if (map.put(read2, this.f1005b.read(vk)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + read2);
                    }
                }
                vk.f();
            }
            return map;
        }
    }

    public MapTypeAdapterFactory(tK tKVar, boolean z) {
        this.f1002a = tKVar;
        this.f1003b = z;
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2231a(Gson gson, TK<T> tk) {
        Type[] typeArr;
        TypeAdapter<Boolean> typeAdapter;
        Type type = tk.getType();
        if (!Map.class.isAssignableFrom(tk.getRawType())) {
            return null;
        }
        Class<?> c = C$Gson$Types.m885c(type);
        Class<Object> cls = Object.class;
        Class<String> cls2 = String.class;
        if (type == Properties.class) {
            typeArr = new Type[]{cls2, cls2};
        } else {
            Type b = C$Gson$Types.m883b(type, c, Map.class);
            typeArr = b instanceof ParameterizedType ? ((ParameterizedType) b).getActualTypeArguments() : new Type[]{cls, cls};
        }
        Type type2 = typeArr[0];
        if (type2 == Boolean.TYPE || type2 == Boolean.class) {
            typeAdapter = TypeAdapters.f1071f;
        } else {
            typeAdapter = gson.mo2091a(new TK(type2));
        }
        return new Adapter(gson, typeArr[0], typeAdapter, typeArr[1], gson.mo2091a(new TK(typeArr[1])), this.f1002a.a(tk));
    }
}
