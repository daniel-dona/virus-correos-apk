package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import java.util.HashMap;

@Qw
/* compiled from: PG */
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    public static int mJniCallCounter;
    public String[] mKeys;
    public HashMap<String, Object> mLocalMap;
    public HashMap<String, ReadableType> mLocalTypeMap;

    /* renamed from: com.facebook.react.bridge.ReadableNativeMap$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C01331 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x002d */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.facebook.react.bridge.ReadableType r2 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                r1 = 2
                int[] r2 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.facebook.react.bridge.ReadableType r3 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                r0 = 3
                int[] r2 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.react.bridge.ReadableType r3 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r1 = 4
                int[] r2 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0026 }
                com.facebook.react.bridge.ReadableType r3 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0026 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0026 }
            L_0x0026:
                r0 = 5
                int[] r2 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x002d }
                com.facebook.react.bridge.ReadableType r3 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x002d }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                int[] r1 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0034 }
                com.facebook.react.bridge.ReadableType r2 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 6
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeMap.C01331.<clinit>():void");
        }
    }

    @Qw
    /* compiled from: PG */
    public static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        @Qw
        public final HybridData mHybridData;
        @Qw
        public final ReadableNativeMap mMap;

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mMap = readableNativeMap;
            this.mHybridData = initHybrid(readableNativeMap);
        }

        public static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

        public native boolean hasNextKey();

        public native String nextKey();
    }

    static {
        ReactBridge.staticInit();
    }

    public ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    private void checkInstance(String str, Object obj, Class cls) {
        if (obj != null && !cls.isInstance(obj)) {
            StringBuilder c = Eo.c("Value for ", str, " cannot be cast from ");
            c.append(obj.getClass().getSimpleName());
            c.append(" to ");
            c.append(cls.getSimpleName());
            throw new ClassCastException(c.toString());
        }
    }

    private native ReadableNativeArray getArrayNative(String str);

    private native boolean getBooleanNative(String str);

    private native double getDoubleNative(String str);

    private native int getIntNative(String str);

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    private HashMap<String, Object> getLocalMap() {
        HashMap<String, Object> hashMap = this.mLocalMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                String[] importKeys = importKeys();
                Kw.a(importKeys);
                this.mKeys = importKeys;
                mJniCallCounter++;
            }
            if (this.mLocalMap == null) {
                Object[] importValues = importValues();
                Kw.a(importValues);
                Object[] objArr = importValues;
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalMap.put(this.mKeys[i], objArr[i]);
                }
            }
        }
        return this.mLocalMap;
    }

    private HashMap<String, ReadableType> getLocalTypeMap() {
        HashMap<String, ReadableType> hashMap = this.mLocalTypeMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                String[] importKeys = importKeys();
                Kw.a(importKeys);
                this.mKeys = importKeys;
                mJniCallCounter++;
            }
            if (this.mLocalTypeMap == null) {
                Object[] importTypes = importTypes();
                Kw.a(importTypes);
                Object[] objArr = importTypes;
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalTypeMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalTypeMap.put(this.mKeys[i], (ReadableType) objArr[i]);
                }
            }
        }
        return this.mLocalTypeMap;
    }

    private native ReadableNativeMap getMapNative(String str);

    private Object getNullableValue(String str) {
        if (hasKey(str)) {
            return getLocalMap().get(str);
        }
        throw new NoSuchKeyException(str);
    }

    private native String getStringNative(String str);

    private native ReadableType getTypeNative(String str);

    private Object getValue(String str) {
        if (!hasKey(str) || isNull(str)) {
            throw new NoSuchKeyException(str);
        }
        Object obj = getLocalMap().get(str);
        Kw.a(obj);
        return obj;
    }

    private native boolean hasKeyNative(String str);

    private native String[] importKeys();

    private native Object[] importTypes();

    private native Object[] importValues();

    private native boolean isNullNative(String str);

    public static void setUseNativeAccessor(boolean z) {
        ly.b = z;
    }

    public ReadableArray getArray(String str) {
        if (!ly.b) {
            return (ReadableArray) getNullableValue(str, ReadableArray.class);
        }
        mJniCallCounter++;
        return getArrayNative(str);
    }

    public boolean getBoolean(String str) {
        if (!ly.b) {
            return ((Boolean) getValue(str, Boolean.class)).booleanValue();
        }
        mJniCallCounter++;
        return getBooleanNative(str);
    }

    public double getDouble(String str) {
        if (!ly.b) {
            return ((Double) getValue(str, Double.class)).doubleValue();
        }
        mJniCallCounter++;
        return getDoubleNative(str);
    }

    public Dynamic getDynamic(String str) {
        return DynamicFromMap.create(this, str);
    }

    public int getInt(String str) {
        if (!ly.b) {
            return ((Double) getValue(str, Double.class)).intValue();
        }
        mJniCallCounter++;
        return getIntNative(str);
    }

    public String getString(String str) {
        if (!ly.b) {
            return (String) getNullableValue(str, String.class);
        }
        mJniCallCounter++;
        return getStringNative(str);
    }

    public ReadableType getType(String str) {
        if (ly.b) {
            mJniCallCounter++;
            return getTypeNative(str);
        } else if (getLocalTypeMap().containsKey(str)) {
            ReadableType readableType = getLocalTypeMap().get(str);
            Kw.a(readableType);
            return readableType;
        } else {
            throw new NoSuchKeyException(str);
        }
    }

    public boolean hasKey(String str) {
        if (!ly.b) {
            return getLocalMap().containsKey(str);
        }
        mJniCallCounter++;
        return hasKeyNative(str);
    }

    public boolean isNull(String str) {
        if (ly.b) {
            mJniCallCounter++;
            return isNullNative(str);
        } else if (!getLocalMap().containsKey(str)) {
            throw new NoSuchKeyException(str);
        } else if (getLocalMap().get(str) == null) {
            return true;
        } else {
            return false;
        }
    }

    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMapKeySetIterator(this);
    }

    public HashMap<String, Object> toHashMap() {
        if (ly.b) {
            ReadableMapKeySetIterator keySetIterator = keySetIterator();
            HashMap<String, Object> hashMap = new HashMap<>();
            while (keySetIterator.hasNextKey()) {
                mJniCallCounter++;
                String nextKey = keySetIterator.nextKey();
                mJniCallCounter++;
                int ordinal = getType(nextKey).ordinal();
                if (ordinal == 0) {
                    hashMap.put(nextKey, (Object) null);
                } else if (ordinal == 1) {
                    hashMap.put(nextKey, Boolean.valueOf(getBoolean(nextKey)));
                } else if (ordinal == 2) {
                    hashMap.put(nextKey, Double.valueOf(getDouble(nextKey)));
                } else if (ordinal == 3) {
                    hashMap.put(nextKey, getString(nextKey));
                } else if (ordinal == 4) {
                    ReadableNativeMap map = getMap(nextKey);
                    Kw.a(map);
                    hashMap.put(nextKey, map.toHashMap());
                } else if (ordinal == 5) {
                    ReadableArray array = getArray(nextKey);
                    Kw.a(array);
                    hashMap.put(nextKey, array.toArrayList());
                } else {
                    throw new IllegalArgumentException(Eo.b("Could not convert object with key: ", nextKey, "."));
                }
            }
            return hashMap;
        }
        HashMap<String, Object> hashMap2 = new HashMap<>(getLocalMap());
        for (String next : hashMap2.keySet()) {
            int ordinal2 = getType(next).ordinal();
            if (!(ordinal2 == 0 || ordinal2 == 1 || ordinal2 == 2 || ordinal2 == 3)) {
                if (ordinal2 == 4) {
                    ReadableNativeMap map2 = getMap(next);
                    Kw.a(map2);
                    hashMap2.put(next, map2.toHashMap());
                } else if (ordinal2 == 5) {
                    ReadableArray array2 = getArray(next);
                    Kw.a(array2);
                    hashMap2.put(next, array2.toArrayList());
                } else {
                    throw new IllegalArgumentException(Eo.b("Could not convert object with key: ", next, "."));
                }
            }
        }
        return hashMap2;
    }

    public ReadableNativeMap getMap(String str) {
        if (!ly.b) {
            return (ReadableNativeMap) getNullableValue(str, ReadableNativeMap.class);
        }
        mJniCallCounter++;
        return getMapNative(str);
    }

    private <T> T getNullableValue(String str, Class<T> cls) {
        T nullableValue = getNullableValue(str);
        checkInstance(str, nullableValue, cls);
        return nullableValue;
    }

    private <T> T getValue(String str, Class<T> cls) {
        T value = getValue(str);
        checkInstance(str, value, cls);
        return value;
    }
}
