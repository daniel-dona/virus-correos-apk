package com.facebook.react.bridge;

import android.support.v4.util.Pools;

/* compiled from: PG */
public class DynamicFromMap implements Dynamic {
    public static final ThreadLocal<Pools.SimplePool<DynamicFromMap>> sPool = new ThreadLocal<Pools.SimplePool<DynamicFromMap>>() {
        public Pools.SimplePool<DynamicFromMap> initialValue() {
            return new Pools.SimplePool<>(10);
        }
    };
    public ReadableMap mMap;
    public String mName;

    public static DynamicFromMap create(ReadableMap readableMap, String str) {
        DynamicFromMap dynamicFromMap = (DynamicFromMap) ((Pools.SimplePool) sPool.get()).acquire();
        if (dynamicFromMap == null) {
            dynamicFromMap = new DynamicFromMap();
        }
        dynamicFromMap.mMap = readableMap;
        dynamicFromMap.mName = str;
        return dynamicFromMap;
    }

    public ReadableArray asArray() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getArray(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public boolean asBoolean() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getBoolean(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public double asDouble() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getDouble(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public int asInt() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getInt(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableMap asMap() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getMap(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public String asString() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getString(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableType getType() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getType(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public boolean isNull() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.isNull(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public void recycle() {
        this.mMap = null;
        this.mName = null;
        ((Pools.SimplePool) sPool.get()).release(this);
    }
}
