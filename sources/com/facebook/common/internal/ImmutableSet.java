package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public class ImmutableSet<E> extends HashSet<E> {
    public ImmutableSet(Set<E> set) {
        super(set);
    }

    public static <E> ImmutableSet<E> copyOf(Set<E> set) {
        return new ImmutableSet<>(set);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m238of(E... eArr) {
        HashSet hashSet = new HashSet(eArr.length);
        Collections.addAll(hashSet, eArr);
        return new ImmutableSet<>(hashSet);
    }
}
