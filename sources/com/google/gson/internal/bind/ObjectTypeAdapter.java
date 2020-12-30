package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: PG */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {

    /* renamed from: b */
    public static final YJ f1008b = new YJ() {
        /* renamed from: a */
        public <T> TypeAdapter<T> mo2233a(Gson gson, TK<T> tk) {
            if (tk.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };

    /* renamed from: a */
    public final Gson f1009a;

    public ObjectTypeAdapter(Gson gson) {
        this.f1009a = gson;
    }

    public Object read(VK vk) throws IOException {
        int ordinal = vk.j0().ordinal();
        if (ordinal == 0) {
            ArrayList arrayList = new ArrayList();
            vk.a();
            while (vk.F()) {
                arrayList.add(read(vk));
            }
            vk.e();
            return arrayList;
        } else if (ordinal == 2) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            vk.b();
            while (vk.F()) {
                linkedTreeMap.put(vk.M(), read(vk));
            }
            vk.f();
            return linkedTreeMap;
        } else if (ordinal == 5) {
            return vk.a0();
        } else {
            if (ordinal == 6) {
                return Double.valueOf(vk.I());
            }
            if (ordinal == 7) {
                return Boolean.valueOf(vk.H());
            }
            if (ordinal == 8) {
                vk.Y();
                return null;
            }
            throw new IllegalStateException();
        }
    }

    public void write(WK wk, Object obj) throws IOException {
        if (obj == null) {
            wk.E();
            return;
        }
        TypeAdapter<?> a = this.f1009a.mo2093a(obj.getClass());
        if (a instanceof ObjectTypeAdapter) {
            wk.c();
            wk.e();
            return;
        }
        a.write(wk, obj);
    }
}
