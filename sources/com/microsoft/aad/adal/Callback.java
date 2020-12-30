package com.microsoft.aad.adal;

/* compiled from: PG */
public interface Callback<T> {
    void onError(Throwable th);

    void onSuccess(T t);
}
