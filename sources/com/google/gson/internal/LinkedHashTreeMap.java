package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: PG */
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Comparator<Comparable> NATURAL_ORDER = new C0218a();
    public Comparator<? super K> comparator;
    public LinkedHashTreeMap<K, V>.c entrySet;
    public final C0223f<K, V> header;
    public LinkedHashTreeMap<K, V>.d keySet;
    public int modCount;
    public int size;
    public C0223f<K, V>[] table;
    public int threshold;

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$a */
    /* compiled from: PG */
    public static class C0218a implements Comparator<Comparable> {
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$c */
    /* compiled from: PG */
    public final class C0220c extends AbstractSet<Map.Entry<K, V>> {
        public C0220c() {
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.findByEntry((Map.Entry) obj) != null;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new vK(this);
        }

        public boolean remove(Object obj) {
            C0223f findByEntry;
            if (!(obj instanceof Map.Entry) || (findByEntry = LinkedHashTreeMap.this.findByEntry((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$d */
    /* compiled from: PG */
    public final class C0221d extends AbstractSet<K> {
        public C0221d() {
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new wK(this);
        }

        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.removeInternalByKey(obj) != null;
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$e */
    /* compiled from: PG */
    public abstract class C0222e<T> implements Iterator<T> {

        /* renamed from: a */
        public C0223f<K, V> f966a;

        /* renamed from: b */
        public C0223f<K, V> f967b = null;

        /* renamed from: c */
        public int f968c;

        public C0222e() {
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            this.f966a = linkedHashTreeMap.header.f973d;
            this.f968c = linkedHashTreeMap.modCount;
        }

        /* renamed from: a */
        public final C0223f<K, V> mo2181a() {
            C0223f<K, V> fVar = this.f966a;
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            if (fVar == linkedHashTreeMap.header) {
                throw new NoSuchElementException();
            } else if (linkedHashTreeMap.modCount == this.f968c) {
                this.f966a = fVar.f973d;
                this.f967b = fVar;
                return fVar;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean hasNext() {
            return this.f966a != LinkedHashTreeMap.this.header;
        }

        public final void remove() {
            C0223f<K, V> fVar = this.f967b;
            if (fVar != null) {
                LinkedHashTreeMap.this.removeInternal(fVar, true);
                this.f967b = null;
                this.f968c = LinkedHashTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    static {
        Class<LinkedHashTreeMap> cls = LinkedHashTreeMap.class;
    }

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    private void doubleCapacity() {
        this.table = doubleCapacity(this.table);
        C0223f<K, V>[] fVarArr = this.table;
        this.threshold = (fVarArr.length / 4) + (fVarArr.length / 2);
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void rebalance(C0223f<K, V> fVar, boolean z) {
        while (fVar != null) {
            C0223f<K, V> fVar2 = fVar.f971b;
            C0223f<K, V> fVar3 = fVar.f972c;
            int i = 0;
            int i2 = fVar2 != null ? fVar2.f978q : 0;
            int i3 = fVar3 != null ? fVar3.f978q : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                C0223f<K, V> fVar4 = fVar3.f971b;
                C0223f<K, V> fVar5 = fVar3.f972c;
                int i5 = fVar5 != null ? fVar5.f978q : 0;
                if (fVar4 != null) {
                    i = fVar4.f978q;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    rotateLeft(fVar);
                } else {
                    rotateRight(fVar3);
                    rotateLeft(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                C0223f<K, V> fVar6 = fVar2.f971b;
                C0223f<K, V> fVar7 = fVar2.f972c;
                int i7 = fVar7 != null ? fVar7.f978q : 0;
                if (fVar6 != null) {
                    i = fVar6.f978q;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    rotateRight(fVar);
                } else {
                    rotateLeft(fVar2);
                    rotateRight(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                fVar.f978q = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                fVar.f978q = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            fVar = fVar.f970a;
        }
    }

    private void replaceInParent(C0223f<K, V> fVar, C0223f<K, V> fVar2) {
        C0223f<K, V> fVar3 = fVar.f970a;
        fVar.f970a = null;
        if (fVar2 != null) {
            fVar2.f970a = fVar3;
        }
        if (fVar3 == null) {
            int i = fVar.f976n;
            C0223f<K, V>[] fVarArr = this.table;
            fVarArr[i & (fVarArr.length - 1)] = fVar2;
        } else if (fVar3.f971b == fVar) {
            fVar3.f971b = fVar2;
        } else {
            fVar3.f972c = fVar2;
        }
    }

    private void rotateLeft(C0223f<K, V> fVar) {
        C0223f<K, V> fVar2 = fVar.f971b;
        C0223f<K, V> fVar3 = fVar.f972c;
        C0223f<K, V> fVar4 = fVar3.f971b;
        C0223f<K, V> fVar5 = fVar3.f972c;
        fVar.f972c = fVar4;
        if (fVar4 != null) {
            fVar4.f970a = fVar;
        }
        replaceInParent(fVar, fVar3);
        fVar3.f971b = fVar;
        fVar.f970a = fVar3;
        int i = 0;
        fVar.f978q = Math.max(fVar2 != null ? fVar2.f978q : 0, fVar4 != null ? fVar4.f978q : 0) + 1;
        int i2 = fVar.f978q;
        if (fVar5 != null) {
            i = fVar5.f978q;
        }
        fVar3.f978q = Math.max(i2, i) + 1;
    }

    private void rotateRight(C0223f<K, V> fVar) {
        C0223f<K, V> fVar2 = fVar.f971b;
        C0223f<K, V> fVar3 = fVar.f972c;
        C0223f<K, V> fVar4 = fVar2.f971b;
        C0223f<K, V> fVar5 = fVar2.f972c;
        fVar.f971b = fVar5;
        if (fVar5 != null) {
            fVar5.f970a = fVar;
        }
        replaceInParent(fVar, fVar2);
        fVar2.f972c = fVar;
        fVar.f970a = fVar2;
        int i = 0;
        fVar.f978q = Math.max(fVar3 != null ? fVar3.f978q : 0, fVar5 != null ? fVar5.f978q : 0) + 1;
        int i2 = fVar.f978q;
        if (fVar4 != null) {
            i = fVar4.f978q;
        }
        fVar2.f978q = Math.max(i2, i) + 1;
    }

    public static int secondaryHash(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public void clear() {
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        this.modCount++;
        C0223f<K, V> fVar = this.header;
        C0223f<K, V> fVar2 = fVar.f973d;
        while (fVar2 != fVar) {
            C0223f<K, V> fVar3 = fVar2.f973d;
            fVar2.f974e = null;
            fVar2.f973d = null;
            fVar2 = fVar3;
        }
        fVar.f974e = fVar;
        fVar.f973d = fVar;
    }

    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.c cVar = this.entrySet;
        if (cVar != null) {
            return cVar;
        }
        LinkedHashTreeMap<K, V>.c cVar2 = new C0220c();
        this.entrySet = cVar2;
        return cVar2;
    }

    public C0223f<K, V> find(K k, boolean z) {
        int i;
        C0223f<K, V> fVar;
        C0223f<K, V> fVar2;
        int i2;
        Comparator<? super K> comparator2 = this.comparator;
        C0223f<K, V>[] fVarArr = this.table;
        int secondaryHash = secondaryHash(k.hashCode());
        int length = (fVarArr.length - 1) & secondaryHash;
        C0223f<K, V> fVar3 = fVarArr[length];
        if (fVar3 != null) {
            Comparable comparable = comparator2 == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i2 = comparable.compareTo(fVar3.f975k);
                } else {
                    i2 = comparator2.compare(k, fVar3.f975k);
                }
                if (i2 == 0) {
                    return fVar3;
                }
                C0223f<K, V> fVar4 = i2 < 0 ? fVar3.f971b : fVar3.f972c;
                if (fVar4 == null) {
                    fVar = fVar3;
                    i = i2;
                    break;
                }
                fVar3 = fVar4;
            }
        } else {
            fVar = fVar3;
            i = 0;
        }
        if (!z) {
            return null;
        }
        C0223f<K, V> fVar5 = this.header;
        if (fVar != null) {
            fVar2 = new C0223f<>(fVar, k, secondaryHash, fVar5, fVar5.f974e);
            if (i < 0) {
                fVar.f971b = fVar2;
            } else {
                fVar.f972c = fVar2;
            }
            rebalance(fVar, true);
        } else if (comparator2 != NATURAL_ORDER || (k instanceof Comparable)) {
            fVar2 = new C0223f<>(fVar, k, secondaryHash, fVar5, fVar5.f974e);
            fVarArr[length] = fVar2;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        int i3 = this.size;
        this.size = i3 + 1;
        if (i3 > this.threshold) {
            doubleCapacity();
        }
        this.modCount++;
        return fVar2;
    }

    public C0223f<K, V> findByEntry(Map.Entry<?, ?> entry) {
        C0223f<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && equal(findByObject.f977p, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    public C0223f<K, V> findByObject(Object obj) {
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
        C0223f findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.f977p;
        }
        return null;
    }

    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.d dVar = this.keySet;
        if (dVar != null) {
            return dVar;
        }
        LinkedHashTreeMap<K, V>.d dVar2 = new C0221d();
        this.keySet = dVar2;
        return dVar2;
    }

    public V put(K k, V v) {
        if (k != null) {
            C0223f find = find(k, true);
            V v2 = find.f977p;
            find.f977p = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public V remove(Object obj) {
        C0223f removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.f977p;
        }
        return null;
    }

    public void removeInternal(C0223f<K, V> fVar, boolean z) {
        C0223f<K, V> fVar2;
        int i;
        C0223f<K, V> fVar3;
        if (z) {
            C0223f<K, V> fVar4 = fVar.f974e;
            fVar4.f973d = fVar.f973d;
            fVar.f973d.f974e = fVar4;
            fVar.f974e = null;
            fVar.f973d = null;
        }
        C0223f<K, V> fVar5 = fVar.f971b;
        C0223f<K, V> fVar6 = fVar.f972c;
        C0223f<K, V> fVar7 = fVar.f970a;
        int i2 = 0;
        if (fVar5 == null || fVar6 == null) {
            if (fVar5 != null) {
                replaceInParent(fVar, fVar5);
                fVar.f971b = null;
            } else if (fVar6 != null) {
                replaceInParent(fVar, fVar6);
                fVar.f972c = null;
            } else {
                replaceInParent(fVar, (C0223f<K, V>) null);
            }
            rebalance(fVar7, false);
            this.size--;
            this.modCount++;
            return;
        }
        if (fVar5.f978q <= fVar6.f978q) {
            C0223f<K, V> fVar8 = fVar6.f971b;
            while (true) {
                C0223f<K, V> fVar9 = fVar6;
                fVar6 = fVar8;
                fVar2 = fVar9;
                if (fVar6 == null) {
                    break;
                }
                fVar8 = fVar6.f971b;
            }
        } else {
            C0223f<K, V> fVar10 = fVar5.f972c;
            while (true) {
                C0223f<K, V> fVar11 = fVar10;
                fVar3 = fVar5;
                fVar5 = fVar11;
                if (fVar5 == null) {
                    break;
                }
                fVar10 = fVar5.f972c;
            }
            fVar2 = fVar3;
        }
        removeInternal(fVar2, false);
        C0223f<K, V> fVar12 = fVar.f971b;
        if (fVar12 != null) {
            i = fVar12.f978q;
            fVar2.f971b = fVar12;
            fVar12.f970a = fVar2;
            fVar.f971b = null;
        } else {
            i = 0;
        }
        C0223f<K, V> fVar13 = fVar.f972c;
        if (fVar13 != null) {
            i2 = fVar13.f978q;
            fVar2.f972c = fVar13;
            fVar13.f970a = fVar2;
            fVar.f972c = null;
        }
        fVar2.f978q = Math.max(i, i2) + 1;
        replaceInParent(fVar, fVar2);
    }

    public C0223f<K, V> removeInternalByKey(Object obj) {
        C0223f<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    public int size() {
        return this.size;
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator2) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator2 == null ? NATURAL_ORDER : comparator2;
        this.header = new C0223f<>();
        this.table = new C0223f[16];
        C0223f<K, V>[] fVarArr = this.table;
        this.threshold = (fVarArr.length / 4) + (fVarArr.length / 2);
    }

    public static <K, V> C0223f<K, V>[] doubleCapacity(C0223f<K, V>[] fVarArr) {
        C0223f<K, V> fVar;
        C0223f<K, V> fVar2;
        C0223f<K, V> fVar3;
        int length = fVarArr.length;
        C0223f<K, V>[] fVarArr2 = new C0223f[(length * 2)];
        C0219b bVar = new C0219b();
        C0219b bVar2 = new C0219b();
        for (int i = 0; i < length; i++) {
            C0223f<K, V> fVar4 = fVarArr[i];
            if (fVar4 != null) {
                C0223f<K, V> fVar5 = null;
                C0223f<K, V> fVar6 = fVar4;
                C0223f<K, V> fVar7 = null;
                while (fVar6 != null) {
                    fVar6.f970a = fVar7;
                    C0223f<K, V> fVar8 = fVar6;
                    fVar6 = fVar6.f971b;
                    fVar7 = fVar8;
                }
                C0223f<K, V> fVar9 = fVar7;
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    if (fVar9 == null) {
                        fVar = fVar9;
                        fVar9 = null;
                    } else {
                        fVar = fVar9.f970a;
                        fVar9.f970a = null;
                        C0223f<K, V> fVar10 = fVar9.f972c;
                        while (fVar10 != null) {
                            fVar10.f970a = fVar;
                            C0223f<K, V> fVar11 = fVar10;
                            fVar10 = fVar10.f971b;
                            fVar = fVar11;
                        }
                    }
                    if (fVar9 == null) {
                        break;
                    }
                    if ((fVar9.f976n & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                    fVar9 = fVar;
                }
                bVar.mo2169a(i2);
                bVar2.mo2169a(i3);
                C0223f<K, V> fVar12 = fVar4;
                C0223f<K, V> fVar13 = null;
                while (fVar12 != null) {
                    fVar12.f970a = fVar13;
                    C0223f<K, V> fVar14 = fVar12;
                    fVar12 = fVar12.f971b;
                    fVar13 = fVar14;
                }
                while (true) {
                    if (fVar13 == null) {
                        fVar2 = fVar13;
                        fVar13 = null;
                    } else {
                        fVar2 = fVar13.f970a;
                        fVar13.f970a = null;
                        C0223f<K, V> fVar15 = fVar13.f972c;
                        while (fVar15 != null) {
                            fVar15.f970a = fVar2;
                            C0223f<K, V> fVar16 = fVar15;
                            fVar15 = fVar15.f971b;
                            fVar2 = fVar16;
                        }
                    }
                    if (fVar13 == null) {
                        break;
                    }
                    if ((fVar13.f976n & length) == 0) {
                        bVar.mo2170a(fVar13);
                    } else {
                        bVar2.mo2170a(fVar13);
                    }
                    fVar13 = fVar2;
                }
                if (i2 > 0) {
                    fVar3 = bVar.f960a;
                    if (fVar3.f970a != null) {
                        throw new IllegalStateException();
                    }
                } else {
                    fVar3 = null;
                }
                fVarArr2[i] = fVar3;
                int i4 = i + length;
                if (i3 > 0) {
                    fVar5 = bVar2.f960a;
                    if (fVar5.f970a != null) {
                        throw new IllegalStateException();
                    }
                }
                fVarArr2[i4] = fVar5;
            }
        }
        return fVarArr2;
    }

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$f */
    /* compiled from: PG */
    public static final class C0223f<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        public C0223f<K, V> f970a;

        /* renamed from: b */
        public C0223f<K, V> f971b;

        /* renamed from: c */
        public C0223f<K, V> f972c;

        /* renamed from: d */
        public C0223f<K, V> f973d;

        /* renamed from: e */
        public C0223f<K, V> f974e;

        /* renamed from: k */
        public final K f975k;

        /* renamed from: n */
        public final int f976n;

        /* renamed from: p */
        public V f977p;

        /* renamed from: q */
        public int f978q;

        public C0223f() {
            this.f975k = null;
            this.f976n = -1;
            this.f974e = this;
            this.f973d = this;
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
                K r0 = r3.f975k
                if (r0 != 0) goto L_0x0012
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x0032
                goto L_0x001c
            L_0x0012:
                java.lang.Object r2 = r4.getKey()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0032
            L_0x001c:
                V r0 = r3.f977p
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedHashTreeMap.C0223f.equals(java.lang.Object):boolean");
        }

        public K getKey() {
            return this.f975k;
        }

        public V getValue() {
            return this.f977p;
        }

        public int hashCode() {
            K k = this.f975k;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f977p;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            V v2 = this.f977p;
            this.f977p = v;
            return v2;
        }

        public String toString() {
            return this.f975k + "=" + this.f977p;
        }

        public C0223f(C0223f<K, V> fVar, K k, int i, C0223f<K, V> fVar2, C0223f<K, V> fVar3) {
            this.f970a = fVar;
            this.f975k = k;
            this.f976n = i;
            this.f978q = 1;
            this.f973d = fVar2;
            this.f974e = fVar3;
            fVar3.f973d = this;
            fVar2.f974e = this;
        }
    }

    /* renamed from: com.google.gson.internal.LinkedHashTreeMap$b */
    /* compiled from: PG */
    public static final class C0219b<K, V> {

        /* renamed from: a */
        public C0223f<K, V> f960a;

        /* renamed from: b */
        public int f961b;

        /* renamed from: c */
        public int f962c;

        /* renamed from: d */
        public int f963d;

        /* renamed from: a */
        public void mo2169a(int i) {
            this.f961b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.f963d = 0;
            this.f962c = 0;
            this.f960a = null;
        }

        /* renamed from: a */
        public void mo2170a(C0223f<K, V> fVar) {
            fVar.f972c = null;
            fVar.f970a = null;
            fVar.f971b = null;
            fVar.f978q = 1;
            int i = this.f961b;
            if (i > 0) {
                int i2 = this.f963d;
                if ((i2 & 1) == 0) {
                    this.f963d = i2 + 1;
                    this.f961b = i - 1;
                    this.f962c++;
                }
            }
            fVar.f970a = this.f960a;
            this.f960a = fVar;
            this.f963d++;
            int i3 = this.f961b;
            if (i3 > 0) {
                int i4 = this.f963d;
                if ((i4 & 1) == 0) {
                    this.f963d = i4 + 1;
                    this.f961b = i3 - 1;
                    this.f962c++;
                }
            }
            int i5 = 4;
            while (true) {
                int i6 = i5 - 1;
                if ((this.f963d & i6) == i6) {
                    int i7 = this.f962c;
                    if (i7 == 0) {
                        C0223f<K, V> fVar2 = this.f960a;
                        C0223f<K, V> fVar3 = fVar2.f970a;
                        C0223f<K, V> fVar4 = fVar3.f970a;
                        fVar3.f970a = fVar4.f970a;
                        this.f960a = fVar3;
                        fVar3.f971b = fVar4;
                        fVar3.f972c = fVar2;
                        fVar3.f978q = fVar2.f978q + 1;
                        fVar4.f970a = fVar3;
                        fVar2.f970a = fVar3;
                    } else if (i7 == 1) {
                        C0223f<K, V> fVar5 = this.f960a;
                        C0223f<K, V> fVar6 = fVar5.f970a;
                        this.f960a = fVar6;
                        fVar6.f972c = fVar5;
                        fVar6.f978q = fVar5.f978q + 1;
                        fVar5.f970a = fVar6;
                        this.f962c = 0;
                    } else if (i7 == 2) {
                        this.f962c = 0;
                    }
                    i5 *= 2;
                } else {
                    return;
                }
            }
        }
    }
}
