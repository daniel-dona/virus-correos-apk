package com.facebook.react.bridge;

/* compiled from: PG */
public interface WritableMap extends ReadableMap {
    void merge(ReadableMap readableMap);

    void putArray(String str, WritableArray writableArray);

    void putBoolean(String str, boolean z);

    void putDouble(String str, double d);

    void putInt(String str, int i);

    void putMap(String str, WritableMap writableMap);

    void putNull(String str);

    void putString(String str, String str2);
}
