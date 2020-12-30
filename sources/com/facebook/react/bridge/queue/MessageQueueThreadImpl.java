package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Pair;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Qw
/* compiled from: PG */
public class MessageQueueThreadImpl implements MessageQueueThread {
    public final String mAssertionErrorMessage;
    public final MessageQueueThreadHandler mHandler;
    public volatile boolean mIsFinished;
    public final Looper mLooper;
    public final String mName;
    public MessageQueueThreadPerfStats mPerfStats;

    /* renamed from: com.facebook.react.bridge.queue.MessageQueueThreadImpl$5 */
    /* compiled from: PG */
    public static /* synthetic */ class C01385 {

        /* renamed from: $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType */
        public static final /* synthetic */ int[] f504x16463f8b = new int[MessageQueueThreadSpec.ThreadType.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        static {
            /*
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType[] r0 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f504x16463f8b = r0
                r0 = 1
                int[] r1 = f504x16463f8b     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType r2 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.MAIN_UI     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = f504x16463f8b     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType r2 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.NEW_BACKGROUND     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2 = 2
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.queue.MessageQueueThreadImpl.C01385.<clinit>():void");
        }
    }

    public MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this(str, looper, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) null);
    }

    public static void assignToPerfStats(MessageQueueThreadPerfStats messageQueueThreadPerfStats, long j, long j2) {
        messageQueueThreadPerfStats.wallTime = j;
        messageQueueThreadPerfStats.cpuTime = j2;
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec messageQueueThreadSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        int ordinal = messageQueueThreadSpec.getThreadType().ordinal();
        if (ordinal == 0) {
            return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
        }
        if (ordinal == 1) {
            return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
        }
        StringBuilder a = Eo.a("Unknown thread type: ");
        a.append(messageQueueThreadSpec.getThreadType());
        throw new RuntimeException(a.toString());
    }

    public static MessageQueueThreadImpl createForMainThread(String str, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(str, Looper.getMainLooper(), queueThreadExceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-4);
                }
            });
        }
        return messageQueueThreadImpl;
    }

    public static MessageQueueThreadImpl startNewBackgroundThread(String str, long j, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        new Thread((ThreadGroup) null, new Runnable() {
            public void run() {
                Process.setThreadPriority(-4);
                Looper.prepare();
                MessageQueueThreadPerfStats messageQueueThreadPerfStats = new MessageQueueThreadPerfStats();
                MessageQueueThreadImpl.assignToPerfStats(messageQueueThreadPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
                simpleSettableFuture.mo1006a(new Pair(Looper.myLooper(), messageQueueThreadPerfStats));
                Looper.loop();
            }
        }, Eo.a("mqt_", str), j).start();
        Pair pair = (Pair) simpleSettableFuture.mo1007b();
        return new MessageQueueThreadImpl(str, (Looper) pair.first, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) pair.second);
    }

    @Qw
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @Qw
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        runOnQueue(new Runnable() {
            public void run() {
                try {
                    simpleSettableFuture.mo1006a(callable.call());
                } catch (Exception e) {
                    simpleSettableFuture.mo1005a(e);
                }
            }
        });
        return simpleSettableFuture;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    @Qw
    public MessageQueueThreadPerfStats getPerfStats() {
        return this.mPerfStats;
    }

    @Qw
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @Qw
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException unused) {
                StringBuilder a = Eo.a("Got interrupted waiting to join thread ");
                a.append(this.mName);
                throw new RuntimeException(a.toString());
            }
        }
    }

    @Qw
    public void resetPerfStats() {
        assignToPerfStats(this.mPerfStats, -1, -1);
        runOnQueue(new Runnable() {
            public void run() {
                MessageQueueThreadImpl.assignToPerfStats(MessageQueueThreadImpl.this.mPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
            }
        });
    }

    @Qw
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            StringBuilder a = Eo.a("Tried to enqueue runnable on already finished thread: '");
            a.append(getName());
            a.append("... dropping Runnable.");
            pq.c("ReactNative", a.toString());
        }
        this.mHandler.post(runnable);
    }

    public MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler, MessageQueueThreadPerfStats messageQueueThreadPerfStats) {
        this.mIsFinished = false;
        this.mName = str;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, queueThreadExceptionHandler);
        this.mPerfStats = messageQueueThreadPerfStats;
        StringBuilder a = Eo.a("Expected to be called from the '");
        a.append(getName());
        a.append("' thread!");
        this.mAssertionErrorMessage = a.toString();
    }

    @Qw
    public void assertIsOnThread(String str) {
        boolean isOnThread = isOnThread();
        SoftAssertions.assertCondition(isOnThread, this.mAssertionErrorMessage + " " + str);
    }
}
