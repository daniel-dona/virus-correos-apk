package com.facebook.react.bridge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
public class JavaOnlyMap implements ReadableMap, WritableMap {
    public final Map mBackingMap;

    /* renamed from: com.facebook.react.bridge.JavaOnlyMap$2 */
    /* compiled from: PG */
    public static /* synthetic */ class C01282 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.JavaOnlyMap.C01282.<clinit>():void");
        }
    }

    public JavaOnlyMap(Object... objArr) {
        if (objArr.length % 2 == 0) {
            this.mBackingMap = new HashMap();
            for (int i = 0; i < objArr.length; i += 2) {
                this.mBackingMap.put(objArr[i], objArr[i + 1]);
            }
            return;
        }
        throw new IllegalArgumentException("You must provide the same number of keys and values");
    }

    public static JavaOnlyMap deepClone(ReadableMap readableMap) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int ordinal = readableMap.getType(nextKey).ordinal();
            if (ordinal == 0) {
                javaOnlyMap.putNull(nextKey);
            } else if (ordinal == 1) {
                javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
            } else if (ordinal == 2) {
                javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
            } else if (ordinal == 3) {
                javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
            } else if (ordinal == 4) {
                javaOnlyMap.putMap(nextKey, deepClone(readableMap.getMap(nextKey)));
            } else if (ordinal == 5) {
                javaOnlyMap.putArray(nextKey, JavaOnlyArray.deepClone(readableMap.getArray(nextKey)));
            }
        }
        return javaOnlyMap;
    }

    /* renamed from: of */
    public static JavaOnlyMap m491of(Object... objArr) {
        return new JavaOnlyMap(objArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || JavaOnlyMap.class != obj.getClass()) {
            return false;
        }
        Map map = this.mBackingMap;
        Map map2 = ((JavaOnlyMap) obj).mBackingMap;
        return map == null ? map2 == null : map.equals(map2);
    }

    public boolean getBoolean(String str) {
        return ((Boolean) this.mBackingMap.get(str)).booleanValue();
    }

    public double getDouble(String str) {
        return ((Number) this.mBackingMap.get(str)).doubleValue();
    }

    public Dynamic getDynamic(String str) {
        return DynamicFromMap.create(this, str);
    }

    public int getInt(String str) {
        return ((Number) this.mBackingMap.get(str)).intValue();
    }

    public ReadableMap getMap(String str) {
        return (ReadableMap) this.mBackingMap.get(str);
    }

    public String getString(String str) {
        return (String) this.mBackingMap.get(str);
    }

    public ReadableType getType(String str) {
        Object obj = this.mBackingMap.get(str);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Number) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof Dynamic) {
            return ((Dynamic) obj).getType();
        }
        StringBuilder a = Eo.a("Invalid value ");
        a.append(obj.toString());
        a.append(" for key ");
        a.append(str);
        a.append("contained in JavaOnlyMap");
        throw new IllegalArgumentException(a.toString());
    }

    public boolean hasKey(String str) {
        return this.mBackingMap.containsKey(str);
    }

    public int hashCode() {
        Map map = this.mBackingMap;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }

    public boolean isNull(String str) {
        return this.mBackingMap.get(str) == null;
    }

    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableMapKeySetIterator() {
            public Iterator<String> mIterator = JavaOnlyMap.this.mBackingMap.keySet().iterator();

            public boolean hasNextKey() {
                return this.mIterator.hasNext();
            }

            public String nextKey() {
                return this.mIterator.next();
            }
        };
    }

    public void merge(ReadableMap readableMap) {
        this.mBackingMap.putAll(((JavaOnlyMap) readableMap).mBackingMap);
    }

    public void putArray(String str, WritableArray writableArray) {
        this.mBackingMap.put(str, writableArray);
    }

    public void putBoolean(String str, boolean z) {
        this.mBackingMap.put(str, Boolean.valueOf(z));
    }

    public void putDouble(String str, double d) {
        this.mBackingMap.put(str, Double.valueOf(d));
    }

    public void putInt(String str, int i) {
        this.mBackingMap.put(str, Integer.valueOf(i));
    }

    public void putMap(String str, WritableMap writableMap) {
        this.mBackingMap.put(str, writableMap);
    }

    public void putNull(String str) {
        this.mBackingMap.put(str, (Object) null);
    }

    public void putString(String str, String str2) {
        this.mBackingMap.put(str, str2);
    }

    public HashMap<String, Object> toHashMap() {
        return new HashMap<>(this.mBackingMap);
    }

    public String toString() {
        return this.mBackingMap.toString();
    }

    public JavaOnlyArray getArray(String str) {
        return (JavaOnlyArray) this.mBackingMap.get(str);
    }

    public JavaOnlyMap() {
        this.mBackingMap = new HashMap();
    }
}
