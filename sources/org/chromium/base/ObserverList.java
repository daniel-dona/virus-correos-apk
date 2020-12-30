package org.chromium.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: PG */
public class ObserverList<E> implements Iterable<E> {

    /* renamed from: a */
    public final List<E> f1453a = new ArrayList();

    /* renamed from: b */
    public int f1454b;

    /* renamed from: c */
    public int f1455c;

    /* renamed from: d */
    public boolean f1456d;

    /* compiled from: PG */
    public interface RewindableIterator<E> extends Iterator<E> {
        void rewind();
    }

    /* renamed from: org.chromium.base.ObserverList$a */
    /* compiled from: PG */
    public class C0368a implements RewindableIterator<E> {

        /* renamed from: a */
        public int f1457a;

        /* renamed from: b */
        public int f1458b;

        /* renamed from: c */
        public boolean f1459c;

        public /* synthetic */ C0368a(bO0 bo0) {
            ObserverList.m1437a((ObserverList) ObserverList.this);
            this.f1457a = ObserverList.this.f1453a.size();
        }

        /* renamed from: a */
        public final void mo7874a() {
            if (!this.f1459c) {
                this.f1459c = true;
                ObserverList observerList = ObserverList.this;
                observerList.f1454b--;
                if (observerList.f1454b <= 0 && observerList.f1456d) {
                    observerList.f1456d = false;
                    int size = observerList.f1453a.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            return;
                        }
                        if (observerList.f1453a.get(size) == null) {
                            observerList.f1453a.remove(size);
                        }
                    }
                }
            }
        }

        public boolean hasNext() {
            int i = this.f1458b;
            while (i < this.f1457a && ObserverList.this.f1453a.get(i) == null) {
                i++;
            }
            if (i < this.f1457a) {
                return true;
            }
            mo7874a();
            return false;
        }

        public E next() {
            while (true) {
                int i = this.f1458b;
                if (i >= this.f1457a || ObserverList.this.f1453a.get(i) != null) {
                    int i2 = this.f1458b;
                } else {
                    this.f1458b++;
                }
            }
            int i22 = this.f1458b;
            if (i22 < this.f1457a) {
                ObserverList observerList = ObserverList.this;
                this.f1458b = i22 + 1;
                return observerList.f1453a.get(i22);
            }
            mo7874a();
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void rewind() {
            mo7874a();
            ObserverList observerList = ObserverList.this;
            observerList.f1454b++;
            this.f1457a = observerList.f1453a.size();
            this.f1459c = false;
            this.f1458b = 0;
        }
    }

    static {
        Class<ObserverList> cls = ObserverList.class;
    }

    /* renamed from: a */
    public boolean mo7868a(E e) {
        if (e == null || this.f1453a.contains(e)) {
            return false;
        }
        this.f1453a.add(e);
        this.f1455c++;
        return true;
    }

    /* renamed from: b */
    public boolean mo7869b(E e) {
        int indexOf;
        if (e == null || (indexOf = this.f1453a.indexOf(e)) == -1) {
            return false;
        }
        if (this.f1454b == 0) {
            this.f1453a.remove(indexOf);
        } else {
            this.f1456d = true;
            this.f1453a.set(indexOf, (Object) null);
        }
        this.f1455c--;
        return true;
    }

    public void clear() {
        this.f1455c = 0;
        if (this.f1454b == 0) {
            this.f1453a.clear();
            return;
        }
        int size = this.f1453a.size();
        this.f1456d |= size != 0;
        for (int i = 0; i < size; i++) {
            this.f1453a.set(i, (Object) null);
        }
    }

    public boolean isEmpty() {
        return this.f1455c == 0;
    }

    public Iterator<E> iterator() {
        return new C0368a((bO0) null);
    }

    /* renamed from: a */
    public RewindableIterator<E> mo7867a() {
        return new C0368a((bO0) null);
    }

    /* renamed from: a */
    public static /* synthetic */ void m1437a(ObserverList observerList) {
        observerList.f1454b++;
    }
}
