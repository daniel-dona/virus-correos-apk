package org.chromium.base;

import android.support.v4.util.ArrayMap;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public abstract class CollectionUtil {

    /* compiled from: PG */
    public interface Callable<T, V> {
        T call(V v);
    }

    @SafeVarargs
    /* renamed from: a */
    public static <E> ArrayList<E> m1375a(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>(eArr.length);
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    @SafeVarargs
    /* renamed from: b */
    public static <E> HashSet<E> m1382b(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }

    @SafeVarargs
    /* renamed from: a */
    public static <K, V> HashMap<K, V> m1377a(Pair<? extends K, ? extends V>... pairArr) {
        HashMap<K, V> hashMap = new HashMap<>();
        for (Pair<? extends K, ? extends V> pair : pairArr) {
            hashMap.put(pair.first, pair.second);
        }
        return hashMap;
    }

    /* renamed from: b */
    public static <T> T m1381b(Collection<? extends T> collection, Callable<Boolean, T> callable) {
        if (collection == null) {
            return null;
        }
        for (T next : collection) {
            if (callable.call(next).booleanValue()) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static <K, V> Map<K, V> m1378a(K k, V v, K k2, V v2) {
        ArrayMap arrayMap = new ArrayMap(2);
        arrayMap.put(k, v);
        arrayMap.put(k2, v2);
        return Collections.unmodifiableMap(arrayMap);
    }

    /* renamed from: a */
    public static int[] m1380a(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    /* renamed from: a */
    public static <T> void m1379a(Collection<? extends T> collection, Callback<T> callback) {
        for (Object onResult : collection) {
            callback.onResult(onResult);
        }
    }

    /* renamed from: a */
    public static <T> Collection<T> m1376a(Collection<? extends T> collection, Callable<Boolean, T> callable) {
        if (collection == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : collection) {
            if (callable.call(next).booleanValue()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
