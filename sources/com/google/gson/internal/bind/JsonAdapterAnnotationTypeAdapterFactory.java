package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/* compiled from: PG */
public final class JsonAdapterAnnotationTypeAdapterFactory implements YJ {

    /* renamed from: a */
    public final tK f1001a;

    public JsonAdapterAnnotationTypeAdapterFactory(tK tKVar) {
        this.f1001a = tKVar;
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> mo2229a(Gson gson, TK<T> tk) {
        aK annotation = tk.getRawType().getAnnotation(aK.class);
        if (annotation == null) {
            return null;
        }
        return mo2230a(this.f1001a, gson, tk, annotation);
    }

    /* JADX WARNING: type inference failed for: r9v13, types: [com.google.gson.TypeAdapter] */
    /* JADX WARNING: type inference failed for: r9v14, types: [com.google.gson.TypeAdapter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> mo2230a(tK r9, com.google.gson.Gson r10, TK<?> r11, aK r12) {
        /*
            r8 = this;
            java.lang.Class r0 = r12.value()
            TK r1 = new TK
            r1.<init>(r0)
            zK r9 = r9.a(r1)
            java.lang.Object r9 = r9.a()
            boolean r0 = r9 instanceof com.google.gson.TypeAdapter
            if (r0 == 0) goto L_0x0018
            com.google.gson.TypeAdapter r9 = (com.google.gson.TypeAdapter) r9
            goto L_0x0072
        L_0x0018:
            boolean r0 = r9 instanceof YJ
            if (r0 == 0) goto L_0x0023
            YJ r9 = (YJ) r9
            com.google.gson.TypeAdapter r9 = r9.a(r10, r11)
            goto L_0x0072
        L_0x0023:
            boolean r0 = r9 instanceof XJ
            if (r0 != 0) goto L_0x0058
            boolean r1 = r9 instanceof QJ
            if (r1 == 0) goto L_0x002c
            goto L_0x0058
        L_0x002c:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Invalid attempt to bind an instance of "
            java.lang.StringBuilder r12 = Eo.a(r12)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getName()
            r12.append(r9)
            java.lang.String r9 = " as a @JsonAdapter for "
            r12.append(r9)
            java.lang.String r9 = r11.toString()
            r12.append(r9)
            java.lang.String r9 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            r10.<init>(r9)
            throw r10
        L_0x0058:
            r1 = 0
            if (r0 == 0) goto L_0x0060
            r0 = r9
            XJ r0 = (XJ) r0
            r3 = r0
            goto L_0x0061
        L_0x0060:
            r3 = r1
        L_0x0061:
            boolean r0 = r9 instanceof QJ
            if (r0 == 0) goto L_0x0068
            r1 = r9
            QJ r1 = (QJ) r1
        L_0x0068:
            r4 = r1
            com.google.gson.internal.bind.TreeTypeAdapter r9 = new com.google.gson.internal.bind.TreeTypeAdapter
            r7 = 0
            r2 = r9
            r5 = r10
            r6 = r11
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x0072:
            if (r9 == 0) goto L_0x007e
            boolean r10 = r12.nullSafe()
            if (r10 == 0) goto L_0x007e
            com.google.gson.TypeAdapter r9 = r9.nullSafe()
        L_0x007e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.mo2230a(tK, com.google.gson.Gson, TK, aK):com.google.gson.TypeAdapter");
    }
}
