package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

@Qw
/* compiled from: PG */
public class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    static {
        ReactBridge.staticInit();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    public static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    public void pushArray(WritableArray writableArray) {
        Kw.a(writableArray == null || (writableArray instanceof WritableNativeArray), "Illegal type provided");
        pushNativeArray((WritableNativeArray) writableArray);
    }

    public native void pushBoolean(boolean z);

    public native void pushDouble(double d);

    public native void pushInt(int i);

    public void pushMap(WritableMap writableMap) {
        Kw.a(writableMap == null || (writableMap instanceof WritableNativeMap), "Illegal type provided");
        pushNativeMap((WritableNativeMap) writableMap);
    }

    public native void pushNull();

    public native void pushString(String str);
}
