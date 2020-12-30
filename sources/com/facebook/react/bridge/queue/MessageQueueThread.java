package com.facebook.react.bridge.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Qw
/* compiled from: PG */
public interface MessageQueueThread {
    @Qw
    void assertIsOnThread();

    @Qw
    void assertIsOnThread(String str);

    @Qw
    <T> Future<T> callOnQueue(Callable<T> callable);

    @Qw
    MessageQueueThreadPerfStats getPerfStats();

    @Qw
    boolean isOnThread();

    @Qw
    void quitSynchronous();

    @Qw
    void resetPerfStats();

    @Qw
    void runOnQueue(Runnable runnable);
}
