package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Excluder;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
public final class ReflectiveTypeAdapterFactory implements YJ {

    /* renamed from: a */
    public final tK f1010a;

    /* renamed from: b */
    public final LJ f1011b;

    /* renamed from: c */
    public final Excluder f1012c;

    /* renamed from: d */
    public final JsonAdapterAnnotationTypeAdapterFactory f1013d;

    /* renamed from: e */
    public final RK f1014e = RK.a;

    /* compiled from: PG */
    public static final class Adapter<T> extends TypeAdapter<T> {

        /* renamed from: a */
        public final zK<T> f1015a;

        /* renamed from: b */
        public final Map<String, C0232a> f1016b;

        public Adapter(zK<T> zKVar, Map<String, C0232a> map) {
            this.f1015a = zKVar;
            this.f1016b = map;
        }

        public T read(VK vk) throws IOException {
            if (vk.j0() == JsonToken.NULL) {
                vk.Y();
                return null;
            }
            T a = this.f1015a.a();
            try {
                vk.b();
                while (vk.F()) {
                    OK ok = (C0232a) this.f1016b.get(vk.M());
                    if (ok != null) {
                        if (ok.f1019c) {
                            OK ok2 = ok;
                            Object read = ok2.f.read(vk);
                            if (read != null || !ok2.i) {
                                ok2.d.set(a, read);
                            }
                        }
                    }
                    vk.w0();
                }
                vk.f();
                return a;
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(WK wk, T t) throws IOException {
            TypeAdapter typeAdapter;
            if (t == null) {
                wk.E();
                return;
            }
            wk.c();
            try {
                Iterator<C0232a> it = this.f1016b.values().iterator();
                while (it.hasNext()) {
                    OK ok = (C0232a) it.next();
                    OK ok2 = ok;
                    boolean z = false;
                    if (ok2.f1018b) {
                        if (ok2.d.get(t) != t) {
                            z = true;
                        }
                    }
                    if (z) {
                        wk.z(ok.f1017a);
                        OK ok3 = ok;
                        Object obj = ok3.d.get(t);
                        if (ok3.e) {
                            typeAdapter = ok3.f;
                        } else {
                            typeAdapter = new TypeAdapterRuntimeTypeWrapper(ok3.g, ok3.f, ok3.h.getType());
                        }
                        typeAdapter.write(wk, obj);
                    }
                }
                wk.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* renamed from: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$a */
    /* compiled from: PG */
    public static abstract class C0232a {

        /* renamed from: a */
        public final String f1017a;

        /* renamed from: b */
        public final boolean f1018b;

        /* renamed from: c */
        public final boolean f1019c;

        public C0232a(String str, boolean z, boolean z2) {
            this.f1017a = str;
            this.f1018b = z;
            this.f1019c = z2;
        }
    }

    public ReflectiveTypeAdapterFactory(tK tKVar, LJ lj, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.f1010a = tKVar;
        this.f1011b = lj;
        this.f1012c = excluder;
        this.f1013d = jsonAdapterAnnotationTypeAdapterFactory;
    }

    /* renamed from: a */
    public boolean mo2235a(Field field, boolean z) {
        Excluder excluder = this.f1012c;
        return !excluder.mo2142a(field.getType(), z) && !excluder.mo2143a(field, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x019e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0186 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.google.gson.TypeAdapter<T> mo2234a(com.google.gson.Gson r35, TK<T> r36) {
        /*
            r34 = this;
            r11 = r34
            r12 = r35
            java.lang.Class<java.lang.Object> r13 = java.lang.Object.class
            java.lang.Class r0 = r36.getRawType()
            boolean r1 = r13.isAssignableFrom(r0)
            r14 = 0
            if (r1 != 0) goto L_0x0012
            return r14
        L_0x0012:
            tK r1 = r11.f1010a
            r2 = r36
            zK r15 = r1.a(r2)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter r10 = new com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
            boolean r1 = r0.isInterface()
            if (r1 == 0) goto L_0x002e
        L_0x0027:
            r33 = r10
            r28 = r15
            r15 = r9
            goto L_0x01e6
        L_0x002e:
            java.lang.reflect.Type r8 = r36.getType()
            r7 = r0
            r16 = r2
        L_0x0035:
            if (r7 == r13) goto L_0x0027
            java.lang.reflect.Field[] r6 = r7.getDeclaredFields()
            int r5 = r6.length
            r4 = 0
            r3 = 0
        L_0x003e:
            if (r3 >= r5) goto L_0x01ba
            r2 = r6[r3]
            r1 = 1
            boolean r0 = r11.mo2235a((java.lang.reflect.Field) r2, (boolean) r1)
            boolean r17 = r11.mo2235a((java.lang.reflect.Field) r2, (boolean) r4)
            if (r0 != 0) goto L_0x0063
            if (r17 != 0) goto L_0x0063
            r20 = r3
            r31 = r5
            r32 = r6
            r36 = r7
            r14 = r8
            r33 = r10
            r22 = r13
            r28 = r15
            r30 = 0
            r15 = r9
            goto L_0x0186
        L_0x0063:
            RK r4 = r11.f1014e
            r4.a(r2)
            java.lang.reflect.Type r4 = r16.getType()
            java.lang.reflect.Type r14 = r2.getGenericType()
            java.lang.reflect.Type r14 = com.google.gson.internal.C$Gson$Types.m880a((java.lang.reflect.Type) r4, (java.lang.Class<?>) r7, (java.lang.reflect.Type) r14)
            java.lang.Class<bK> r4 = bK.class
            java.lang.annotation.Annotation r4 = r2.getAnnotation(r4)
            bK r4 = (bK) r4
            if (r4 != 0) goto L_0x008b
            LJ r4 = r11.f1011b
            java.lang.String r4 = r4.translateName(r2)
            java.util.List r4 = java.util.Collections.singletonList(r4)
            r19 = r0
            goto L_0x009c
        L_0x008b:
            java.lang.String r1 = r4.value()
            java.lang.String[] r4 = r4.alternate()
            r19 = r0
            int r0 = r4.length
            if (r0 != 0) goto L_0x00a1
            java.util.List r4 = java.util.Collections.singletonList(r1)
        L_0x009c:
            r20 = r3
            r18 = 1
            goto L_0x00c1
        L_0x00a1:
            java.util.ArrayList r0 = new java.util.ArrayList
            r20 = r3
            int r3 = r4.length
            r18 = 1
            int r3 = r3 + 1
            r0.<init>(r3)
            r0.add(r1)
            int r1 = r4.length
            r3 = 0
        L_0x00b2:
            if (r3 >= r1) goto L_0x00c0
            r21 = r1
            r1 = r4[r3]
            r0.add(r1)
            int r3 = r3 + 1
            r1 = r21
            goto L_0x00b2
        L_0x00c0:
            r4 = r0
        L_0x00c1:
            int r3 = r4.size()
            r0 = 0
            r1 = 0
        L_0x00c7:
            if (r1 >= r3) goto L_0x0173
            java.lang.Object r21 = r4.get(r1)
            r22 = r13
            r13 = r21
            java.lang.String r13 = (java.lang.String) r13
            r21 = r9
            if (r1 == 0) goto L_0x00d9
            r19 = 0
        L_0x00d9:
            TK r9 = new TK
            r9.<init>(r14)
            r23 = r0
            java.lang.Class r0 = r9.getRawType()
            r24 = r1
            java.util.Map r1 = BK.a
            boolean r25 = r1.containsKey(r0)
            java.lang.Class<aK> r0 = aK.class
            java.lang.annotation.Annotation r0 = r2.getAnnotation(r0)
            aK r0 = (aK) r0
            if (r0 == 0) goto L_0x0101
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r1 = r11.f1013d
            r26 = r2
            tK r2 = r11.f1010a
            com.google.gson.TypeAdapter r0 = r1.mo2230a(r2, r12, r9, r0)
            goto L_0x0104
        L_0x0101:
            r26 = r2
            r0 = 0
        L_0x0104:
            if (r0 == 0) goto L_0x0109
            r27 = 1
            goto L_0x010b
        L_0x0109:
            r27 = 0
        L_0x010b:
            if (r0 != 0) goto L_0x0111
            com.google.gson.TypeAdapter r0 = r12.mo2091a(r9)
        L_0x0111:
            r28 = r0
            OK r2 = new OK
            r1 = r23
            r0 = r2
            r11 = r1
            r18 = r24
            r23 = 1
            r1 = r34
            r12 = r2
            r24 = r26
            r2 = r13
            r26 = r3
            r3 = r19
            r29 = r4
            r30 = 0
            r4 = r17
            r31 = r5
            r5 = r24
            r32 = r6
            r6 = r27
            r36 = r7
            r7 = r28
            r27 = r14
            r14 = r8
            r8 = r35
            r28 = r15
            r15 = r21
            r33 = r10
            r10 = r25
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.Object r0 = r15.put(r13, r12)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$a r0 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.C0232a) r0
            if (r11 != 0) goto L_0x0152
            goto L_0x0153
        L_0x0152:
            r0 = r11
        L_0x0153:
            int r1 = r18 + 1
            r11 = r34
            r12 = r35
            r7 = r36
            r8 = r14
            r9 = r15
            r13 = r22
            r2 = r24
            r3 = r26
            r14 = r27
            r15 = r28
            r4 = r29
            r5 = r31
            r6 = r32
            r10 = r33
            r18 = 1
            goto L_0x00c7
        L_0x0173:
            r11 = r0
            r31 = r5
            r32 = r6
            r36 = r7
            r14 = r8
            r33 = r10
            r22 = r13
            r28 = r15
            r30 = 0
            r15 = r9
            if (r11 != 0) goto L_0x019e
        L_0x0186:
            int r3 = r20 + 1
            r11 = r34
            r12 = r35
            r7 = r36
            r8 = r14
            r9 = r15
            r13 = r22
            r15 = r28
            r5 = r31
            r6 = r32
            r10 = r33
            r4 = 0
            r14 = 0
            goto L_0x003e
        L_0x019e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r14)
            java.lang.String r2 = " declares multiple JSON fields named "
            r1.append(r2)
            java.lang.String r2 = r11.f1017a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01ba:
            r36 = r7
            r14 = r8
            r33 = r10
            r22 = r13
            r28 = r15
            r15 = r9
            java.lang.reflect.Type r0 = r16.getType()
            java.lang.reflect.Type r1 = r36.getGenericSuperclass()
            r2 = r36
            java.lang.reflect.Type r0 = com.google.gson.internal.C$Gson$Types.m880a((java.lang.reflect.Type) r0, (java.lang.Class<?>) r2, (java.lang.reflect.Type) r1)
            TK r1 = new TK
            r1.<init>(r0)
            java.lang.Class r7 = r1.getRawType()
            r11 = r34
            r12 = r35
            r16 = r1
            r15 = r28
            r14 = 0
            goto L_0x0035
        L_0x01e6:
            r0 = r28
            r1 = r33
            r1.<init>(r0, r15)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.mo2234a(com.google.gson.Gson, TK):com.google.gson.TypeAdapter");
    }
}
