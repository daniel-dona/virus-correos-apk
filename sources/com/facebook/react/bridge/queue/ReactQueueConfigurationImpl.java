package com.facebook.react.bridge.queue;

import android.os.Looper;
import java.util.HashMap;

/* compiled from: PG */
public class ReactQueueConfigurationImpl implements ReactQueueConfiguration {
    public final MessageQueueThreadImpl mJSQueueThread;
    public final MessageQueueThreadImpl mNativeModulesQueueThread;
    public final MessageQueueThreadImpl mUIQueueThread;

    public ReactQueueConfigurationImpl(MessageQueueThreadImpl messageQueueThreadImpl, MessageQueueThreadImpl messageQueueThreadImpl2, MessageQueueThreadImpl messageQueueThreadImpl3) {
        this.mUIQueueThread = messageQueueThreadImpl;
        this.mNativeModulesQueueThread = messageQueueThreadImpl2;
        this.mJSQueueThread = messageQueueThreadImpl3;
    }

    public static ReactQueueConfigurationImpl create(ReactQueueConfigurationSpec reactQueueConfigurationSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        HashMap hashMap = new HashMap();
        MessageQueueThreadSpec mainThreadSpec = MessageQueueThreadSpec.mainThreadSpec();
        MessageQueueThreadImpl create = MessageQueueThreadImpl.create(mainThreadSpec, queueThreadExceptionHandler);
        hashMap.put(mainThreadSpec, create);
        MessageQueueThreadImpl messageQueueThreadImpl = (MessageQueueThreadImpl) hashMap.get(reactQueueConfigurationSpec.getJSQueueThreadSpec());
        if (messageQueueThreadImpl == null) {
            messageQueueThreadImpl = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getJSQueueThreadSpec(), queueThreadExceptionHandler);
        }
        MessageQueueThreadImpl messageQueueThreadImpl2 = (MessageQueueThreadImpl) hashMap.get(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec());
        if (messageQueueThreadImpl2 == null) {
            messageQueueThreadImpl2 = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec(), queueThreadExceptionHandler);
        }
        return new ReactQueueConfigurationImpl(create, messageQueueThreadImpl2, messageQueueThreadImpl);
    }

    public void destroy() {
        if (this.mNativeModulesQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mNativeModulesQueueThread.quitSynchronous();
        }
        if (this.mJSQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mJSQueueThread.quitSynchronous();
        }
    }

    public MessageQueueThread getJSQueueThread() {
        return this.mJSQueueThread;
    }

    public MessageQueueThread getNativeModulesQueueThread() {
        return this.mNativeModulesQueueThread;
    }

    public MessageQueueThread getUIQueueThread() {
        return this.mUIQueueThread;
    }
}
