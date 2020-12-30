package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
public class JavaOnlyArray implements ReadableArray, WritableArray {
    public final List mBackingList;

    /* renamed from: com.facebook.react.bridge.JavaOnlyArray$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C01261 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.JavaOnlyArray.C01261.<clinit>():void");
        }
    }

    public JavaOnlyArray(Object... objArr) {
        this.mBackingList = Arrays.asList(objArr);
    }

    public static JavaOnlyArray deepClone(ReadableArray readableArray) {
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            int ordinal = readableArray.getType(i).ordinal();
            if (ordinal == 0) {
                javaOnlyArray.pushNull();
            } else if (ordinal == 1) {
                javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
            } else if (ordinal == 2) {
                javaOnlyArray.pushDouble(readableArray.getDouble(i));
            } else if (ordinal == 3) {
                javaOnlyArray.pushString(readableArray.getString(i));
            } else if (ordinal == 4) {
                javaOnlyArray.pushMap(JavaOnlyMap.deepClone(readableArray.getMap(i)));
            } else if (ordinal == 5) {
                javaOnlyArray.pushArray(deepClone(readableArray.getArray(i)));
            }
        }
        return javaOnlyArray;
    }

    public static JavaOnlyArray from(List list) {
        return new JavaOnlyArray(list);
    }

    /* renamed from: of */
    public static JavaOnlyArray m490of(Object... objArr) {
        return new JavaOnlyArray(objArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || JavaOnlyArray.class != obj.getClass()) {
            return false;
        }
        List list = this.mBackingList;
        List list2 = ((JavaOnlyArray) obj).mBackingList;
        return list == null ? list2 == null : list.equals(list2);
    }

    public boolean getBoolean(int i) {
        return ((Boolean) this.mBackingList.get(i)).booleanValue();
    }

    public double getDouble(int i) {
        return ((Number) this.mBackingList.get(i)).doubleValue();
    }

    public Dynamic getDynamic(int i) {
        return DynamicFromArray.create(this, i);
    }

    public int getInt(int i) {
        return ((Number) this.mBackingList.get(i)).intValue();
    }

    public String getString(int i) {
        return (String) this.mBackingList.get(i);
    }

    public ReadableType getType(int i) {
        Object obj = this.mBackingList.get(i);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer)) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        return null;
    }

    public int hashCode() {
        List list = this.mBackingList;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public boolean isNull(int i) {
        return this.mBackingList.get(i) == null;
    }

    public void pushArray(WritableArray writableArray) {
        this.mBackingList.add(writableArray);
    }

    public void pushBoolean(boolean z) {
        this.mBackingList.add(Boolean.valueOf(z));
    }

    public void pushDouble(double d) {
        this.mBackingList.add(Double.valueOf(d));
    }

    public void pushInt(int i) {
        this.mBackingList.add(Integer.valueOf(i));
    }

    public void pushMap(WritableMap writableMap) {
        this.mBackingList.add(writableMap);
    }

    public void pushNull() {
        this.mBackingList.add((Object) null);
    }

    public void pushString(String str) {
        this.mBackingList.add(str);
    }

    public int size() {
        return this.mBackingList.size();
    }

    public ArrayList<Object> toArrayList() {
        return new ArrayList<>(this.mBackingList);
    }

    public String toString() {
        return this.mBackingList.toString();
    }

    public JavaOnlyArray getArray(int i) {
        return (JavaOnlyArray) this.mBackingList.get(i);
    }

    public JavaOnlyMap getMap(int i) {
        return (JavaOnlyMap) this.mBackingList.get(i);
    }

    public JavaOnlyArray(List list) {
        this.mBackingList = new ArrayList(list);
    }

    public JavaOnlyArray() {
        this.mBackingList = new ArrayList();
    }
}
