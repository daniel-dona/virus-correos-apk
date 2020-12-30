package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: PG */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Comparator<Comparable> NATURAL_ORDER = new C0224a();
    public Comparator<? super K> comparator;
    public LinkedTreeMap<K, V>.b entrySet;
    public final C0228e<K, V> header;
    public LinkedTreeMap<K, V>.c keySet;
    public int modCount;
    public C0228e<K, V> root;
    public int size;

    /* renamed from: com.google.gson.internal.LinkedTreeMap$a */
    /* compiled from: PG */
    public static class C0224a implements Comparator<Comparable> {
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }

    /* renamed from: com.google.gson.internal.LinkedTreeMap$b */
    /* compiled from: PG */
    public class C0225b extends AbstractSet<Map.Entry<K, V>> {
        public C0225b() {
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.findByEntry((Map.Entry) obj) != null;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new xK(this);
        }

        public boolean remove(Object obj) {
            C0228e findByEntry;
            if (!(obj instanceof Map.Entry) || (findByEntry = LinkedTreeMap.this.findByEntry((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    /* renamed from: com.google.gson.internal.LinkedTreeMap$c */
    /* compiled from: PG */
    public final class C0226c extends AbstractSet<K> {
        public C0226c() {
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new yK(this);
        }

        public boolean remove(Object obj) {
            return LinkedTreeMap.this.removeInternalByKey(obj) != null;
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    /* renamed from: com.google.gson.internal.LinkedTreeMap$d */
    /* compiled from: PG */
    public abstract class C0227d<T> implements Iterator<T> {

        /* renamed from: a */
        public C0228e<K, V> f981a;

        /* renamed from: b */
        public C0228e<K, V> f982b = null;

        /* renamed from: c */
        public int f983c;

        public C0227d() {
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            this.f981a = linkedTreeMap.header.f988d;
            this.f983c = linkedTreeMap.modCount;
        }

        /* renamed from: a */
        public final C0228e<K, V> mo2214a() {
            C0228e<K, V> eVar = this.f981a;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            } else if (linkedTreeMap.modCount == this.f983c) {
                this.f981a = eVar.f988d;
                this.f982b = eVar;
                return eVar;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean hasNext() {
            return this.f981a != LinkedTreeMap.this.header;
        }

        public final void remove() {
            C0228e<K, V> eVar = this.f982b;
            if (eVar != null) {
                LinkedTreeMap.this.removeInternal(eVar, true);
                this.f982b = null;
                this.f983c = LinkedTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    static {
        Class<LinkedTreeMap> cls = LinkedTreeMap.class;
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void rebalance(C0228e<K, V> eVar, boolean z) {
        while (eVar != null) {
            C0228e<K, V> eVar2 = eVar.f986b;
            C0228e<K, V> eVar3 = eVar.f987c;
            int i = 0;
            int i2 = eVar2 != null ? eVar2.f992p : 0;
            int i3 = eVar3 != null ? eVar3.f992p : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                C0228e<K, V> eVar4 = eVar3.f986b;
                C0228e<K, V> eVar5 = eVar3.f987c;
                int i5 = eVar5 != null ? eVar5.f992p : 0;
                if (eVar4 != null) {
                    i = eVar4.f992p;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    rotateLeft(eVar);
                } else {
                    rotateRight(eVar3);
                    rotateLeft(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                C0228e<K, V> eVar6 = eVar2.f986b;
                C0228e<K, V> eVar7 = eVar2.f987c;
                int i7 = eVar7 != null ? eVar7.f992p : 0;
                if (eVar6 != null) {
                    i = eVar6.f992p;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    rotateRight(eVar);
                } else {
                    rotateLeft(eVar2);
                    rotateRight(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.f992p = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.f992p = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f985a;
        }
    }

    private void replaceInParent(C0228e<K, V> eVar, C0228e<K, V> eVar2) {
        C0228e<K, V> eVar3 = eVar.f985a;
        eVar.f985a = null;
        if (eVar2 != null) {
            eVar2.f985a = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f986b == eVar) {
            eVar3.f986b = eVar2;
        } else {
            eVar3.f987c = eVar2;
        }
    }

    private void rotateLeft(C0228e<K, V> eVar) {
        C0228e<K, V> eVar2 = eVar.f986b;
        C0228e<K, V> eVar3 = eVar.f987c;
        C0228e<K, V> eVar4 = eVar3.f986b;
        C0228e<K, V> eVar5 = eVar3.f987c;
        eVar.f987c = eVar4;
        if (eVar4 != null) {
            eVar4.f985a = eVar;
        }
        replaceInParent(eVar, eVar3);
        eVar3.f986b = eVar;
        eVar.f985a = eVar3;
        int i = 0;
        eVar.f992p = Math.max(eVar2 != null ? eVar2.f992p : 0, eVar4 != null ? eVar4.f992p : 0) + 1;
        int i2 = eVar.f992p;
        if (eVar5 != null) {
            i = eVar5.f992p;
        }
        eVar3.f992p = Math.max(i2, i) + 1;
    }

    private void rotateRight(C0228e<K, V> eVar) {
        C0228e<K, V> eVar2 = eVar.f986b;
        C0228e<K, V> eVar3 = eVar.f987c;
        C0228e<K, V> eVar4 = eVar2.f986b;
        C0228e<K, V> eVar5 = eVar2.f987c;
        eVar.f986b = eVar5;
        if (eVar5 != null) {
            eVar5.f985a = eVar;
        }
        replaceInParent(eVar, eVar2);
        eVar2.f987c = eVar;
        eVar.f985a = eVar2;
        int i = 0;
        eVar.f992p = Math.max(eVar3 != null ? eVar3.f992p : 0, eVar5 != null ? eVar5.f992p : 0) + 1;
        int i2 = eVar.f992p;
        if (eVar4 != null) {
            i = eVar4.f992p;
        }
        eVar2.f992p = Math.max(i2, i) + 1;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        C0228e<K, V> eVar = this.header;
        eVar.f989e = eVar;
        eVar.f988d = eVar;
    }

    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.b bVar = this.entrySet;
        if (bVar != null) {
            return bVar;
        }
        LinkedTreeMap<K, V>.b bVar2 = new C0225b();
        this.entrySet = bVar2;
        return bVar2;
    }

    public C0228e<K, V> find(K k, boolean z) {
        int i;
        C0228e<K, V> eVar;
        Comparator<? super K> comparator2 = this.comparator;
        C0228e<K, V> eVar2 = this.root;
        if (eVar2 != null) {
            Comparable comparable = comparator2 == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(eVar2.f990k);
                } else {
                    i = comparator2.compare(k, eVar2.f990k);
                }
                if (i == 0) {
                    return eVar2;
                }
                C0228e<K, V> eVar3 = i < 0 ? eVar2.f986b : eVar2.f987c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        C0228e<K, V> eVar4 = this.header;
        if (eVar2 != null) {
            eVar = new C0228e<>(eVar2, k, eVar4, eVar4.f989e);
            if (i < 0) {
                eVar2.f986b = eVar;
            } else {
                eVar2.f987c = eVar;
            }
            rebalance(eVar2, true);
        } else if (comparator2 != NATURAL_ORDER || (k instanceof Comparable)) {
            eVar = new C0228e<>(eVar2, k, eVar4, eVar4.f989e);
            this.root = eVar;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    public C0228e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        C0228e<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && equal(findByObject.f991n, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    public C0228e<K, V> findByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return find(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public V get(Object obj) {
        C0228e findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.f991n;
        }
        return null;
    }

    public Set<K> keySet() {
        LinkedTreeMap<K, V>.c cVar = this.keySet;
        if (cVar != null) {
            return cVar;
        }
        LinkedTreeMap<K, V>.c cVar2 = new C0226c();
        this.keySet = cVar2;
        return cVar2;
    }

    public V put(K k, V v) {
        if (k != null) {
            C0228e find = find(k, true);
            V v2 = find.f991n;
            find.f991n = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public V remove(Object obj) {
        C0228e removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.f991n;
        }
        return null;
    }

    public void removeInternal(C0228e<K, V> eVar, boolean z) {
        C0228e<K, V> eVar2;
        int i;
        C0228e<K, V> eVar3;
        if (z) {
            C0228e<K, V> eVar4 = eVar.f989e;
            eVar4.f988d = eVar.f988d;
            eVar.f988d.f989e = eVar4;
        }
        C0228e<K, V> eVar5 = eVar.f986b;
        C0228e<K, V> eVar6 = eVar.f987c;
        C0228e<K, V> eVar7 = eVar.f985a;
        int i2 = 0;
        if (eVar5 == null || eVar6 == null) {
            if (eVar5 != null) {
                replaceInParent(eVar, eVar5);
                eVar.f986b = null;
            } else if (eVar6 != null) {
                replaceInParent(eVar, eVar6);
                eVar.f987c = null;
            } else {
                replaceInParent(eVar, (C0228e<K, V>) null);
            }
            rebalance(eVar7, false);
            this.size--;
            this.modCount++;
            return;
        }
        if (eVar5.f992p <= eVar6.f992p) {
            C0228e<K, V> eVar8 = eVar6.f986b;
            while (true) {
                C0228e<K, V> eVar9 = eVar6;
                eVar6 = eVar8;
                eVar2 = eVar9;
                if (eVar6 == null) {
                    break;
                }
                eVar8 = eVar6.f986b;
            }
        } else {
            C0228e<K, V> eVar10 = eVar5.f987c;
            while (true) {
                C0228e<K, V> eVar11 = eVar10;
                eVar3 = eVar5;
                eVar5 = eVar11;
                if (eVar5 == null) {
                    break;
                }
                eVar10 = eVar5.f987c;
            }
            eVar2 = eVar3;
        }
        removeInternal(eVar2, false);
        C0228e<K, V> eVar12 = eVar.f986b;
        if (eVar12 != null) {
            i = eVar12.f992p;
            eVar2.f986b = eVar12;
            eVar12.f985a = eVar2;
            eVar.f986b = null;
        } else {
            i = 0;
        }
        C0228e<K, V> eVar13 = eVar.f987c;
        if (eVar13 != null) {
            i2 = eVar13.f992p;
            eVar2.f987c = eVar13;
            eVar13.f985a = eVar2;
            eVar.f987c = null;
        }
        eVar2.f992p = Math.max(i, i2) + 1;
        replaceInParent(eVar, eVar2);
    }

    public C0228e<K, V> removeInternalByKey(Object obj) {
        C0228e<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    public int size() {
        return this.size;
    }

    public LinkedTreeMap(Comparator<? super K> comparator2) {
        this.size = 0;
        this.modCount = 0;
        this.header = new C0228e<>();
        this.comparator = comparator2 == null ? NATURAL_ORDER : comparator2;
    }

    /* renamed from: com.google.gson.internal.LinkedTreeMap$e */
    /* compiled from: PG */
    public static final class C0228e<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        public C0228e<K, V> f985a;

        /* renamed from: b */
        public C0228e<K, V> f986b;

        /* renamed from: c */
        public C0228e<K, V> f987c;

        /* renamed from: d */
        public C0228e<K, V> f988d;

        /* renamed from: e */
        public C0228e<K, V> f989e;

        /* renamed from: k */
        public final K f990k;

        /* renamed from: n */
        public V f991n;

        /* renamed from: p */
        public int f992p;

        public C0228e() {
            this.f990k = null;
            this.f989e = this;
            this.f988d = this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 == 0) goto L_0x0032
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                K r0 = r3.f990k
                if (r0 != 0) goto L_0x0012
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x0032
                goto L_0x001c
            L_0x0012:
                java.lang.Object r2 = r4.getKey()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0032
            L_0x001c:
                V r0 = r3.f991n
                if (r0 != 0) goto L_0x0027
                java.lang.Object r4 = r4.getValue()
                if (r4 != 0) goto L_0x0032
                goto L_0x0031
            L_0x0027:
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r0.equals(r4)
                if (r4 == 0) goto L_0x0032
            L_0x0031:
                r1 = 1
            L_0x0032:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.C0228e.equals(java.lang.Object):boolean");
        }

        public K getKey() {
            return this.f990k;
        }

        public V getValue() {
            return this.f991n;
        }

        public int hashCode() {
            K k = this.f990k;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f991n;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            V v2 = this.f991n;
            this.f991n = v;
            return v2;
        }

        public String toString() {
            return this.f990k + "=" + this.f991n;
        }

        public C0228e(C0228e<K, V> eVar, K k, C0228e<K, V> eVar2, C0228e<K, V> eVar3) {
            this.f985a = eVar;
            this.f990k = k;
            this.f992p = 1;
            this.f988d = eVar2;
            this.f989e = eVar3;
            eVar3.f988d = this;
            eVar2.f989e = this;
        }
    }
}
