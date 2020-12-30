package com.microsoft.aad.adal;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
public final class CallbackExecutor<T> {
    public static final String TAG = "CallbackExecutor";
    public final AtomicReference<Callback<T>> mCallbackReference = new AtomicReference<>((Object) null);
    public final Handler mHandler;

    public CallbackExecutor(Callback<T> callback) {
        Handler handler = null;
        this.mHandler = Looper.myLooper() != null ? new Handler() : handler;
        this.mCallbackReference.set(callback);
    }

    public void onError(final Throwable th) {
        final Callback andSet = this.mCallbackReference.getAndSet((Object) null);
        if (andSet == null) {
            Logger.m1250v(TAG, "Callback does not exist.");
            return;
        }
        Handler handler = this.mHandler;
        if (handler == null) {
            andSet.onError(th);
        } else {
            handler.post(new Runnable() {
                public void run() {
                    andSet.onError(th);
                }
            });
        }
    }

    public void onSuccess(final T t) {
        final Callback andSet = this.mCallbackReference.getAndSet((Object) null);
        if (andSet == null) {
            Logger.m1250v(TAG, "Callback does not exist.");
            return;
        }
        Handler handler = this.mHandler;
        if (handler == null) {
            andSet.onSuccess(t);
        } else {
            handler.post(new Runnable() {
                public void run() {
                    andSet.onSuccess(t);
                }
            });
        }
    }
}
