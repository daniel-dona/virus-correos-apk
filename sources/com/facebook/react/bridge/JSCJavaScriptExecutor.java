package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public class JSCJavaScriptExecutor extends JavaScriptExecutor {
    static {
        ReactBridge.staticInit();
    }

    public JSCJavaScriptExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }

    public static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    public String getName() {
        return "JSCJavaScriptExecutor";
    }
}
