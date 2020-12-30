package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaJSExecutor;

@Qw
/* compiled from: PG */
public class ProxyJavaScriptExecutor extends JavaScriptExecutor {
    public JavaJSExecutor mJavaJSExecutor;

    /* compiled from: PG */
    public static class Factory implements JavaScriptExecutorFactory {
        public final JavaJSExecutor.Factory mJavaJSExecutorFactory;

        public Factory(JavaJSExecutor.Factory factory) {
            this.mJavaJSExecutorFactory = factory;
        }

        public JavaScriptExecutor create() throws Exception {
            return new ProxyJavaScriptExecutor(this.mJavaJSExecutorFactory.create());
        }
    }

    static {
        ReactBridge.staticInit();
    }

    public ProxyJavaScriptExecutor(JavaJSExecutor javaJSExecutor) {
        super(initHybrid(javaJSExecutor));
        this.mJavaJSExecutor = javaJSExecutor;
    }

    public static native HybridData initHybrid(JavaJSExecutor javaJSExecutor);

    public void close() {
        JavaJSExecutor javaJSExecutor = this.mJavaJSExecutor;
        if (javaJSExecutor != null) {
            javaJSExecutor.close();
            this.mJavaJSExecutor = null;
        }
    }

    public String getName() {
        return "ProxyJavaScriptExecutor";
    }
}
