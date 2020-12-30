package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public abstract class ViewManagerPropertyUpdater {

    /* renamed from: a */
    public static final Map<Class<?>, ViewManagerSetter<?, ?>> f561a = new HashMap();

    /* renamed from: b */
    public static final Map<Class<?>, ShadowNodeSetter<?>> f562b = new HashMap();

    /* compiled from: PG */
    public interface Settable {
        void getProperties(Map<String, String> map);
    }

    /* compiled from: PG */
    public interface ShadowNodeSetter<T extends NA> extends Settable {
        void setProperty(T t, String str, PA pa);
    }

    /* compiled from: PG */
    public interface ViewManagerSetter<T extends ViewManager, V extends View> extends Settable {
        void setProperty(T t, V v, String str, PA pa);
    }

    /* renamed from: a */
    public static void m535a() {
        bC.a.clear();
        bC.b.clear();
        f561a.clear();
        f562b.clear();
    }

    /* renamed from: b */
    public static <T extends ViewManager, V extends View> ViewManagerSetter<T, V> m538b(Class<? extends ViewManager> cls) {
        NB nb = (ViewManagerSetter) f561a.get(cls);
        if (nb == null) {
            nb = (ViewManagerSetter) m533a(cls);
            if (nb == null) {
                nb = new NB(cls, (LB) null);
            }
            f561a.put(cls, nb);
        }
        return nb;
    }

    /* renamed from: c */
    public static <T extends NA> ShadowNodeSetter<T> m539c(Class<? extends NA> cls) {
        MB mb = (ShadowNodeSetter) f562b.get(cls);
        if (mb == null) {
            mb = (ShadowNodeSetter) m533a(cls);
            if (mb == null) {
                mb = new MB(cls, (LB) null);
            }
            f562b.put(cls, mb);
        }
        return mb;
    }

    /* renamed from: a */
    public static <T extends ViewManager, V extends View> void m537a(T t, V v, PA pa) {
        ViewManagerSetter<?, V> b = m538b(t.getClass());
        ReadableMapKeySetIterator keySetIterator = pa.a.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            b.setProperty(t, v, keySetIterator.nextKey(), pa);
        }
    }

    /* renamed from: a */
    public static <T extends NA> void m536a(T t, PA pa) {
        ShadowNodeSetter<?> c = m539c(t.getClass());
        ReadableMapKeySetIterator keySetIterator = pa.a.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            c.setProperty(t, keySetIterator.nextKey(), pa);
        }
    }

    /* renamed from: a */
    public static Map<String, String> m534a(Class<? extends ViewManager> cls, Class<? extends NA> cls2) {
        HashMap hashMap = new HashMap();
        m538b(cls).getProperties(hashMap);
        m539c(cls2).getProperties(hashMap);
        return hashMap;
    }

    /* renamed from: a */
    public static <T> T m533a(Class<?> cls) {
        String name = cls.getName();
        try {
            return Class.forName(name + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException unused) {
            pq.c("ViewManagerPropertyUpdater", "Could not find generated setter for " + cls);
            return null;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(Eo.a("Unable to instantiate methods getter for ", name), e);
        }
    }
}
