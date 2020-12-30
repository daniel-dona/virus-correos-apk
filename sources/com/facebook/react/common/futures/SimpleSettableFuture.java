package com.facebook.react.common.futures;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
public class SimpleSettableFuture<T> implements Future<T> {

    /* renamed from: a */
    public final CountDownLatch f507a = new CountDownLatch(1);

    /* renamed from: b */
    public T f508b;

    /* renamed from: c */
    public Exception f509c;

    /* renamed from: a */
    public void mo1006a(T t) {
        mo1004a();
        this.f508b = t;
        this.f507a.countDown();
    }

    /* renamed from: b */
    public T mo1007b() {
        try {
            return get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    public T get() throws InterruptedException, ExecutionException {
        this.f507a.await();
        Exception exc = this.f509c;
        if (exc == null) {
            return this.f508b;
        }
        throw new ExecutionException(exc);
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return this.f507a.getCount() == 0;
    }

    /* renamed from: a */
    public void mo1005a(Exception exc) {
        mo1004a();
        this.f509c = exc;
        this.f507a.countDown();
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.f507a.await(j, timeUnit)) {
            Exception exc = this.f509c;
            if (exc == null) {
                return this.f508b;
            }
            throw new ExecutionException(exc);
        }
        throw new TimeoutException("Timed out waiting for result");
    }

    /* renamed from: a */
    public final void mo1004a() {
        if (this.f507a.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
