package com.nimbusds.jose;

import com.nimbusds.jose.Algorithm;
import java.util.Collection;
import java.util.LinkedHashSet;

/* compiled from: PG */
public class AlgorithmFamily<T extends Algorithm> extends LinkedHashSet<T> {
    public static final long serialVersionUID = 1;

    public AlgorithmFamily(T... tArr) {
        for (T add : tArr) {
            super.add(add);
        }
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }
}
