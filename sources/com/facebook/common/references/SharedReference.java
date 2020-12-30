package com.facebook.common.references;

import java.util.IdentityHashMap;
import java.util.Map;

/* compiled from: PG */
public class SharedReference<T> {

    /* renamed from: d */
    public static final Map<Object, Integer> f234d = new IdentityHashMap();

    /* renamed from: a */
    public T f235a;

    /* renamed from: b */
    public int f236b;

    /* renamed from: c */
    public final Gq<T> f237c;

    /* compiled from: PG */
    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T t, Gq<T> gq) {
        if (t != null) {
            this.f235a = t;
            if (gq != null) {
                this.f237c = gq;
                this.f236b = 1;
                m239a(t);
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public static void m239a(Object obj) {
        synchronized (f234d) {
            Integer num = f234d.get(obj);
            if (num == null) {
                f234d.put(obj, 1);
            } else {
                f234d.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    /* renamed from: b */
    public static void m240b(Object obj) {
        synchronized (f234d) {
            Integer num = f234d.get(obj);
            if (num == null) {
                pq.b("SharedReference", "No entry in sLiveObjects for value of type %s", new Object[]{obj.getClass()});
            } else if (num.intValue() == 1) {
                f234d.remove(obj);
            } else {
                f234d.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    /* renamed from: c */
    public void mo320c() {
        T t;
        if (mo319b() == 0) {
            synchronized (this) {
                t = this.f235a;
                this.f235a = null;
            }
            this.f237c.release(t);
            m240b(t);
        }
    }

    /* renamed from: d */
    public final void mo321d() {
        if (!(mo323f())) {
            throw new NullReferenceException();
        }
    }

    /* renamed from: e */
    public synchronized T mo322e() {
        return this.f235a;
    }

    /* renamed from: f */
    public synchronized boolean mo323f() {
        return this.f236b > 0;
    }

    /* renamed from: a */
    public synchronized void mo318a() {
        mo321d();
        this.f236b++;
    }

    /* renamed from: b */
    public final synchronized int mo319b() {
        mo321d();
        kq.a(this.f236b > 0);
        this.f236b--;
        return this.f236b;
    }
}
