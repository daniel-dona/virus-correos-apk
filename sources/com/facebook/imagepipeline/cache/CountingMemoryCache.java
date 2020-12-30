package com.facebook.imagepipeline.cache;

import android.os.SystemClock;
import com.facebook.common.memory.MemoryTrimType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class CountingMemoryCache<K, V> implements dt<K, V>, uq {

    /* renamed from: g */
    public static final long f327g = TimeUnit.MINUTES.toMillis(5);

    /* renamed from: a */
    public final Ss<K, Vs<K, V>> f328a;

    /* renamed from: b */
    public final Ss<K, Vs<K, V>> f329b;

    /* renamed from: c */
    public final jt<V> f330c;

    /* renamed from: d */
    public final mq<et> f331d;

    /* renamed from: e */
    public et f332e = ((et) this.f331d.get());

    /* renamed from: f */
    public long f333f = SystemClock.uptimeMillis();

    /* compiled from: PG */
    public interface CacheTrimStrategy {
        double getTrimRatio(MemoryTrimType memoryTrimType);
    }

    /* compiled from: PG */
    public interface EntryStateObserver<K> {
        void onExclusivityChanged(K k, boolean z);
    }

    public CountingMemoryCache(jt<V> jtVar, CacheTrimStrategy cacheTrimStrategy, mq<et> mqVar) {
        new WeakHashMap();
        this.f330c = jtVar;
        this.f328a = new Ss<>(new Ts(this, jtVar));
        this.f329b = new Ss<>(new Ts(this, jtVar));
        this.f331d = mqVar;
    }

    /* renamed from: h */
    public static <K, V> void m317h(Vs<K, V> vs) {
        EntryStateObserver entryStateObserver;
        if (vs != null && (entryStateObserver = vs.e) != null) {
            entryStateObserver.onExclusivityChanged(vs.a, false);
        }
    }

    /* renamed from: b */
    public synchronized boolean mo453b(lq<K> lqVar) {
        return !this.f329b.a(lqVar).isEmpty();
    }

    /* renamed from: c */
    public final void mo454c() {
        ArrayList a;
        synchronized (this) {
            a = mo446a(Math.min(this.f332e.d, this.f332e.b - mo442a()), Math.min(this.f332e.c, this.f332e.a - mo450b()));
            mo448a(a);
        }
        mo452b(a);
        mo456c(a);
    }

    /* renamed from: d */
    public final synchronized boolean mo458d(Vs<K, V> vs) {
        if (vs.d || vs.c != 0) {
            return false;
        }
        this.f328a.a(vs.a, vs);
        return true;
    }

    /* renamed from: e */
    public final synchronized Eq<V> mo459e(Vs<K, V> vs) {
        mo451b(vs);
        return Eq.a(vs.b.b(), new Us(this, vs));
    }

    /* renamed from: f */
    public final synchronized Eq<V> mo460f(Vs<K, V> vs) {
        if (vs == null) {
            throw new NullPointerException();
        }
        return (!vs.d || vs.c != 0) ? null : vs.b;
    }

    /* renamed from: g */
    public final void mo461g(Vs<K, V> vs) {
        boolean d;
        Eq f;
        EntryStateObserver entryStateObserver;
        if (vs != null) {
            synchronized (this) {
                mo447a(vs);
                d = mo458d(vs);
                f = mo460f(vs);
            }
            Eq.b(f);
            if (!d) {
                vs = null;
            }
            if (!(vs == null || (entryStateObserver = vs.e) == null)) {
                entryStateObserver.onExclusivityChanged(vs.a, true);
            }
            mo457d();
            mo454c();
            return;
        }
        throw new NullPointerException();
    }

    public Eq<V> get(K k) {
        Vs vs;
        if (k != null) {
            Eq<V> eq = null;
            synchronized (this) {
                vs = (Vs) this.f328a.c(k);
                Vs vs2 = (Vs) this.f329b.a(k);
                if (vs2 != null) {
                    eq = mo459e(vs2);
                }
            }
            m317h(vs);
            mo457d();
            mo454c();
            return eq;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public Eq<V> mo444a(K k, Eq<V> eq) {
        return mo445a(k, eq, (EntryStateObserver) null);
    }

    /* renamed from: b */
    public final void mo452b(ArrayList<Vs<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Vs<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                Eq.b(mo460f((Vs) it.next()));
            }
        }
    }

    /* renamed from: a */
    public Eq<V> mo445a(K k, Eq<V> eq, EntryStateObserver<K> entryStateObserver) {
        Vs vs;
        Eq<V> eq2;
        Eq eq3;
        if (k == null) {
            throw new NullPointerException();
        } else if (eq != null) {
            mo457d();
            synchronized (this) {
                vs = (Vs) this.f328a.c(k);
                Vs vs2 = (Vs) this.f329b.c(k);
                eq2 = null;
                if (vs2 != null) {
                    mo455c(vs2);
                    eq3 = mo460f(vs2);
                } else {
                    eq3 = null;
                }
                if (mo449a(eq.b())) {
                    Vs vs3 = new Vs(k, eq, entryStateObserver);
                    this.f329b.a(k, vs3);
                    eq2 = mo459e(vs3);
                }
            }
            if (eq3 != null) {
                eq3.close();
            }
            m317h(vs);
            mo454c();
            return eq2;
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: b */
    public final synchronized void mo451b(Vs<K, V> vs) {
        if (vs != null) {
            kq.b(!vs.d);
            vs.c++;
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: d */
    public final synchronized void mo457d() {
        if (this.f333f + f327g <= SystemClock.uptimeMillis()) {
            this.f333f = SystemClock.uptimeMillis();
            this.f332e = (et) this.f331d.get();
        }
    }

    /* renamed from: b */
    public synchronized int mo450b() {
        return this.f329b.c() - this.f328a.c();
    }

    /* renamed from: c */
    public final void mo456c(ArrayList<Vs<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Vs<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                m317h((Vs) it.next());
            }
        }
    }

    /* renamed from: c */
    public final synchronized void mo455c(Vs<K, V> vs) {
        if (vs != null) {
            kq.b(!vs.d);
            vs.d = true;
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public final synchronized boolean mo449a(V v) {
        boolean z;
        int a = this.f330c.a(v);
        z = true;
        if (a > this.f332e.e || mo442a() > this.f332e.b - 1 || mo450b() > this.f332e.a - a) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    public int mo443a(lq<K> lqVar) {
        ArrayList b;
        ArrayList b2;
        synchronized (this) {
            b = this.f328a.b(lqVar);
            b2 = this.f329b.b(lqVar);
            mo448a(b2);
        }
        mo452b(b2);
        mo456c(b);
        mo457d();
        mo454c();
        return b2.size();
    }

    /* renamed from: a */
    public final synchronized ArrayList<Vs<K, V>> mo446a(int i, int i2) {
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (this.f328a.a() <= max && this.f328a.c() <= max2) {
            return null;
        }
        ArrayList<Vs<K, V>> arrayList = new ArrayList<>();
        while (true) {
            if (this.f328a.a() <= max && this.f328a.c() <= max2) {
                return arrayList;
            }
            Object b = this.f328a.b();
            this.f328a.c(b);
            arrayList.add(this.f329b.c(b));
        }
    }

    /* renamed from: a */
    public final synchronized void mo448a(ArrayList<Vs<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Vs<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                mo455c((Vs) it.next());
            }
        }
    }

    /* renamed from: a */
    public final synchronized void mo447a(Vs<K, V> vs) {
        if (vs != null) {
            kq.b(vs.c > 0);
            vs.c--;
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public synchronized int mo442a() {
        return this.f329b.a() - this.f328a.a();
    }
}
