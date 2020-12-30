package com.microsoft.aad.adal;

/* compiled from: PG */
public interface AuthenticationCallback<T> {
    void onError(Exception exc);

    void onSuccess(T t);
}
