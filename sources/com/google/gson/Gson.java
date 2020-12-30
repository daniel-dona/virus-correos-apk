package com.google.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: PG */
public final class Gson {

    /* renamed from: k */
    public static final TK<?> f931k = new TK<>(Object.class);

    /* renamed from: a */
    public final ThreadLocal<Map<TK<?>, FutureTypeAdapter<?>>> f932a;

    /* renamed from: b */
    public final Map<TK<?>, TypeAdapter<?>> f933b;

    /* renamed from: c */
    public final tK f934c;

    /* renamed from: d */
    public final JsonAdapterAnnotationTypeAdapterFactory f935d;

    /* renamed from: e */
    public final List<YJ> f936e;

    /* renamed from: f */
    public final boolean f937f;

    /* renamed from: g */
    public final boolean f938g;

    /* renamed from: h */
    public final boolean f939h;

    /* renamed from: i */
    public final boolean f940i;

    /* renamed from: j */
    public final boolean f941j;

    /* compiled from: PG */
    public static class FutureTypeAdapter<T> extends TypeAdapter<T> {

        /* renamed from: a */
        public TypeAdapter<T> f944a;

        /* renamed from: a */
        public void mo2114a(TypeAdapter<T> typeAdapter) {
            if (this.f944a == null) {
                this.f944a = typeAdapter;
                return;
            }
            throw new AssertionError();
        }

        public T read(VK vk) throws IOException {
            TypeAdapter<T> typeAdapter = this.f944a;
            if (typeAdapter != null) {
                return typeAdapter.read(vk);
            }
            throw new IllegalStateException();
        }

        public void write(WK wk, T t) throws IOException {
            TypeAdapter<T> typeAdapter = this.f944a;
            if (typeAdapter != null) {
                typeAdapter.write(wk, t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public Gson() {
        this(Excluder.f947n, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, (String) null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* renamed from: a */
    public void mo2103a(RJ rj, Appendable appendable) throws JsonIOException {
        try {
            mo2102a(rj, mo2090a(appendable instanceof Writer ? (Writer) appendable : new DK(appendable)));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    /* renamed from: b */
    public RJ mo2107b(Object obj) {
        if (obj == null) {
            return SJ.a;
        }
        Class<?> cls = obj.getClass();
        NK nk = new NK();
        mo2105a(obj, (Type) cls, (WK) nk);
        return nk.H();
    }

    public String toString() {
        return "{serializeNulls:" + this.f937f + ",factories:" + this.f936e + ",instanceCreators:" + this.f934c + "}";
    }

    /* renamed from: a */
    public void mo2106a(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            mo2105a(obj, type, mo2090a(appendable instanceof Writer ? (Writer) appendable : new DK(appendable)));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public Gson(Excluder excluder, LJ lj, Map<Type, NJ<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, String str, int i, int i2, List<YJ> list, List<YJ> list2, List<YJ> list3) {
        final TypeAdapter typeAdapter;
        TypeAdapter typeAdapter2;
        TypeAdapter typeAdapter3;
        Excluder excluder2 = excluder;
        this.f932a = new ThreadLocal<>();
        this.f933b = new ConcurrentHashMap();
        Map<Type, NJ<?>> map2 = map;
        this.f934c = new tK(map);
        this.f937f = z;
        this.f938g = z3;
        this.f939h = z4;
        this.f940i = z5;
        this.f941j = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.f1064Y);
        arrayList.add(ObjectTypeAdapter.f1008b);
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.f1043D);
        arrayList.add(TypeAdapters.f1078m);
        arrayList.add(TypeAdapters.f1072g);
        arrayList.add(TypeAdapters.f1074i);
        arrayList.add(TypeAdapters.f1076k);
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            typeAdapter = TypeAdapters.f1085t;
        } else {
            typeAdapter = new TypeAdapter<Number>() {
                /* renamed from: a */
                public void write(WK wk, Number number) throws IOException {
                    if (number == null) {
                        wk.E();
                    } else {
                        wk.B(number.toString());
                    }
                }

                public Number read(VK vk) throws IOException {
                    if (vk.j0() != JsonToken.NULL) {
                        return Long.valueOf(vk.K());
                    }
                    vk.Y();
                    return null;
                }
            };
        }
        arrayList.add(new YJ(Long.TYPE, Long.class, typeAdapter) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        Class cls = Double.TYPE;
        Class<Double> cls2 = Double.class;
        if (z7) {
            typeAdapter2 = TypeAdapters.f1087v;
        } else {
            typeAdapter2 = new TypeAdapter<Number>(this) {
                /* renamed from: a */
                public void write(WK wk, Number number) throws IOException {
                    if (number == null) {
                        wk.E();
                        return;
                    }
                    Gson.m851a(number.doubleValue());
                    wk.a(number);
                }

                public Double read(VK vk) throws IOException {
                    if (vk.j0() != JsonToken.NULL) {
                        return Double.valueOf(vk.I());
                    }
                    vk.Y();
                    return null;
                }
            };
        }
        arrayList.add(new YJ(cls, cls2, typeAdapter2) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        Class cls3 = Float.TYPE;
        Class<Float> cls4 = Float.class;
        if (z7) {
            typeAdapter3 = TypeAdapters.f1086u;
        } else {
            typeAdapter3 = new TypeAdapter<Number>(this) {
                /* renamed from: a */
                public void write(WK wk, Number number) throws IOException {
                    if (number == null) {
                        wk.E();
                        return;
                    }
                    Gson.m851a((double) number.floatValue());
                    wk.a(number);
                }

                public Float read(VK vk) throws IOException {
                    if (vk.j0() != JsonToken.NULL) {
                        return Float.valueOf((float) vk.I());
                    }
                    vk.Y();
                    return null;
                }
            };
        }
        arrayList.add(new YJ(cls3, cls4, typeAdapter3) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2270a(Gson gson, TK<T> tk) {
                Class rawType = tk.getRawType();
                if (rawType == r0 || rawType == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append("+");
                a.append(r0.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        arrayList.add(TypeAdapters.f1089x);
        arrayList.add(TypeAdapters.f1080o);
        arrayList.add(TypeAdapters.f1082q);
        arrayList.add(new YJ(AtomicLong.class, new TypeAdapter<AtomicLong>() {
            /* renamed from: a */
            public void write(WK wk, AtomicLong atomicLong) throws IOException {
                TypeAdapter.this.write(wk, Long.valueOf(atomicLong.get()));
            }

            public AtomicLong read(VK vk) throws IOException {
                return new AtomicLong(((Number) TypeAdapter.this.read(vk)).longValue());
            }
        }.nullSafe()) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        arrayList.add(new YJ(AtomicLongArray.class, new TypeAdapter<AtomicLongArray>() {
            /* renamed from: a */
            public void write(WK wk, AtomicLongArray atomicLongArray) throws IOException {
                wk.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    TypeAdapter.this.write(wk, Long.valueOf(atomicLongArray.get(i)));
                }
                wk.d();
            }

            public AtomicLongArray read(VK vk) throws IOException {
                ArrayList arrayList = new ArrayList();
                vk.a();
                while (vk.F()) {
                    arrayList.add(Long.valueOf(((Number) TypeAdapter.this.read(vk)).longValue()));
                }
                vk.e();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.nullSafe()) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        arrayList.add(TypeAdapters.f1084s);
        arrayList.add(TypeAdapters.f1091z);
        arrayList.add(TypeAdapters.f1045F);
        arrayList.add(TypeAdapters.f1047H);
        arrayList.add(new YJ(BigDecimal.class, TypeAdapters.f1041B) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        arrayList.add(new YJ(BigInteger.class, TypeAdapters.f1042C) {
            /* renamed from: a */
            public <T> TypeAdapter<T> mo2268a(Gson gson, TK<T> tk) {
                if (tk.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                StringBuilder a = Eo.a("Factory[type=");
                a.append(r1.getName());
                a.append(",adapter=");
                a.append(r2);
                a.append("]");
                return a.toString();
            }
        });
        arrayList.add(TypeAdapters.f1049J);
        arrayList.add(TypeAdapters.f1051L);
        arrayList.add(TypeAdapters.f1055P);
        arrayList.add(TypeAdapters.f1057R);
        arrayList.add(TypeAdapters.f1062W);
        arrayList.add(TypeAdapters.f1053N);
        arrayList.add(TypeAdapters.f1069d);
        arrayList.add(DateTypeAdapter.f999b);
        arrayList.add(TypeAdapters.f1060U);
        arrayList.add(TimeTypeAdapter.f1022b);
        arrayList.add(SqlDateTypeAdapter.f1020b);
        arrayList.add(TypeAdapters.f1058S);
        arrayList.add(ArrayTypeAdapter.f993c);
        arrayList.add(TypeAdapters.f1067b);
        arrayList.add(new CollectionTypeAdapterFactory(this.f934c));
        boolean z8 = z2;
        arrayList.add(new MapTypeAdapterFactory(this.f934c, z2));
        this.f935d = new JsonAdapterAnnotationTypeAdapterFactory(this.f934c);
        arrayList.add(this.f935d);
        arrayList.add(TypeAdapters.f1065Z);
        LJ lj2 = lj;
        arrayList.add(new ReflectiveTypeAdapterFactory(this.f934c, lj, excluder, this.f935d));
        this.f936e = Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    public void mo2102a(RJ rj, WK wk) throws JsonIOException {
        boolean z = wk.k;
        wk.k = true;
        boolean z2 = wk.n;
        wk.n = this.f939h;
        boolean z3 = wk.q;
        wk.q = this.f937f;
        try {
            TypeAdapters.f1063X.write(wk, rj);
            wk.k = z;
            wk.n = z2;
            wk.q = z3;
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        } catch (Throwable th) {
            wk.k = z;
            wk.n = z2;
            wk.q = z3;
            throw th;
        }
    }

    /* renamed from: a */
    public void mo2105a(Object obj, Type type, WK wk) throws JsonIOException {
        TypeAdapter a = mo2091a(new TK(type));
        boolean z = wk.k;
        wk.k = true;
        boolean z2 = wk.n;
        wk.n = this.f939h;
        boolean z3 = wk.q;
        wk.q = this.f937f;
        try {
            a.write(wk, obj);
            wk.k = z;
            wk.n = z2;
            wk.q = z3;
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        } catch (Throwable th) {
            wk.k = z;
            wk.n = z2;
            wk.q = z3;
            throw th;
        }
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2093a(Class<T> cls) {
        return mo2091a(new TK(cls));
    }

    /* renamed from: a */
    public <T> T mo2095a(VK vk, Type type) throws JsonIOException, JsonSyntaxException {
        boolean z = vk.b;
        vk.b = true;
        try {
            vk.j0();
            T read = mo2091a(new TK(type)).read(vk);
            vk.b = z;
            return read;
        } catch (EOFException e) {
            if (1 != 0) {
                vk.b = z;
                return null;
            }
            throw new JsonSyntaxException((Throwable) e);
        } catch (IllegalStateException e2) {
            throw new JsonSyntaxException((Throwable) e2);
        } catch (IOException e3) {
            throw new JsonSyntaxException((Throwable) e3);
        } catch (Throwable th) {
            vk.b = z;
            throw th;
        }
    }

    /* renamed from: a */
    public static void m851a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2091a(TK<T> tk) {
        TypeAdapter<T> typeAdapter = this.f933b.get(tk == null ? f931k : tk);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map map = this.f932a.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap();
            this.f932a.set(map);
            z = true;
        }
        FutureTypeAdapter futureTypeAdapter = (FutureTypeAdapter) map.get(tk);
        if (futureTypeAdapter != null) {
            return futureTypeAdapter;
        }
        try {
            FutureTypeAdapter futureTypeAdapter2 = new FutureTypeAdapter();
            map.put(tk, futureTypeAdapter2);
            for (YJ a : this.f936e) {
                TypeAdapter<T> a2 = a.a(this, tk);
                if (a2 != null) {
                    futureTypeAdapter2.mo2114a(a2);
                    this.f933b.put(tk, a2);
                    return a2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + tk);
        } finally {
            map.remove(tk);
            if (z) {
                this.f932a.remove();
            }
        }
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2092a(JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, TK<T> tk) {
        if (!this.f936e.contains(jsonAdapterAnnotationTypeAdapterFactory)) {
            jsonAdapterAnnotationTypeAdapterFactory = this.f935d;
        }
        boolean z = false;
        Iterator<YJ> it = this.f936e.iterator();
        while (it.hasNext()) {
            JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory2 = (YJ) it.next();
            if (z) {
                TypeAdapter<T> a = jsonAdapterAnnotationTypeAdapterFactory2.a(this, tk);
                if (a != null) {
                    return a;
                }
            } else if (jsonAdapterAnnotationTypeAdapterFactory2 == jsonAdapterAnnotationTypeAdapterFactory) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + tk);
    }

    /* renamed from: a */
    public String mo2100a(Object obj) {
        if (obj == null) {
            return mo2099a((RJ) SJ.a);
        }
        return mo2101a(obj, (Type) obj.getClass());
    }

    /* renamed from: a */
    public String mo2101a(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        mo2106a(obj, type, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public void mo2104a(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            mo2106a(obj, (Type) obj.getClass(), appendable);
        } else {
            mo2103a((RJ) SJ.a, appendable);
        }
    }

    /* renamed from: a */
    public String mo2099a(RJ rj) {
        StringWriter stringWriter = new StringWriter();
        mo2103a(rj, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public WK mo2090a(Writer writer) throws IOException {
        if (this.f938g) {
            writer.write(")]}'\n");
        }
        WK wk = new WK(writer);
        if (this.f940i) {
            wk.d = "  ";
            wk.e = ": ";
        }
        wk.q = this.f937f;
        return wk;
    }

    /* renamed from: a */
    public <T> T mo2096a(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        VK vk = new VK(reader);
        vk.b = this.f941j;
        T a = mo2095a(vk, (Type) cls);
        m852a((Object) a, vk);
        return BK.a(cls).cast(a);
    }

    /* renamed from: a */
    public VK mo2089a(Reader reader) {
        VK vk = new VK(reader);
        vk.b = this.f941j;
        return vk;
    }

    /* renamed from: a */
    public <T> T mo2097a(String str, Class<T> cls) throws JsonSyntaxException {
        return BK.a(cls).cast(mo2098a(str, (Type) cls));
    }

    /* renamed from: a */
    public <T> T mo2098a(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        VK a = mo2089a((Reader) new StringReader(str));
        T a2 = mo2095a(a, type);
        m852a((Object) a2, a);
        return a2;
    }

    /* renamed from: a */
    public static void m852a(Object obj, VK vk) {
        if (obj != null) {
            try {
                if (vk.j0() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IOException e2) {
                throw new JsonIOException((Throwable) e2);
            }
        }
    }

    /* renamed from: a */
    public <T> T mo2094a(RJ rj, Type type) throws JsonSyntaxException {
        if (rj == null) {
            return null;
        }
        return mo2095a((VK) new LK(rj), type);
    }
}
