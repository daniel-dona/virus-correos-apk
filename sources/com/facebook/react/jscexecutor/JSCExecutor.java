package com.facebook.react.jscexecutor;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.soloader.SoLoader;

@Qw
/* compiled from: PG */
public class JSCExecutor extends JavaScriptExecutor {
    static {
        SoLoader.m690a("jscexecutor");
    }

    public JSCExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }

    public static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    public String getName() {
        return "JSCExecutor";
    }
}
