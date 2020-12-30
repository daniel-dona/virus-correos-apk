package com.facebook.react.bridge;

/* compiled from: PG */
public class JSCJavaScriptExecutorFactory implements JavaScriptExecutorFactory {
    public final String mAppName;
    public final String mDeviceName;

    public JSCJavaScriptExecutorFactory(String str, String str2) {
        this.mAppName = str;
        this.mDeviceName = str2;
    }

    public JavaScriptExecutor create() throws Exception {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", "ReactNative");
        writableNativeMap.putString("AppIdentity", this.mAppName);
        writableNativeMap.putString("DeviceIdentity", this.mDeviceName);
        return new JSCJavaScriptExecutor(writableNativeMap);
    }

    public String toString() {
        return "JSCExecutor";
    }
}
