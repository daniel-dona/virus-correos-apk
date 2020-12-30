package com.facebook.react.common;

import android.support.v4.util.Pools;

/* compiled from: PG */
public class ClearableSynchronizedPool<T> implements Pools.Pool<T> {

    /* renamed from: a */
    public final Object[] f505a;

    /* renamed from: b */
    public int f506b = 0;

    public ClearableSynchronizedPool(int i) {
        this.f505a = new Object[i];
    }

    /* renamed from: a */
    public synchronized void mo1000a() {
        for (int i = 0; i < this.f506b; i++) {
            this.f505a[i] = null;
        }
        this.f506b = 0;
    }

    public synchronized T acquire() {
        if (this.f506b == 0) {
            return null;
        }
        this.f506b--;
        int i = this.f506b;
        T t = this.f505a[i];
        this.f505a[i] = null;
        return t;
    }

    public synchronized boolean release(T t) {
        if (this.f506b == this.f505a.length) {
            return false;
        }
        this.f505a[this.f506b] = t;
        this.f506b++;
        return true;
    }
}
