package com.facebook.react.bridge;

@Qw
/* compiled from: PG */
public interface ReactCallback {
    @Qw
    void decrementPendingJSCalls();

    @Qw
    void incrementPendingJSCalls();

    @Qw
    void onBatchComplete();
}
