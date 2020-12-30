package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import java.util.ArrayList;
import java.util.Arrays;

@Qw
/* compiled from: PG */
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    public static int jniPassCounter = 0;
    public Object[] mLocalArray;
    public ReadableType[] mLocalTypeArray;

    /* renamed from: com.facebook.react.bridge.ReadableNativeArray$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C01321 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ReadableNativeArray.C01321.<clinit>():void");
        }
    }

    static {
        ReactBridge.staticInit();
    }

    public ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    private native ReadableNativeArray getArrayNative(int i);

    private native boolean getBooleanNative(int i);

    private native double getDoubleNative(int i);

    private native int getIntNative(int i);

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        Object[] objArr = this.mLocalArray;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            if (this.mLocalArray == null) {
                jniPassCounter++;
                Object[] importArray = importArray();
                Kw.a(importArray);
                this.mLocalArray = importArray;
            }
        }
        return this.mLocalArray;
    }

    private ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr = this.mLocalTypeArray;
        if (readableTypeArr != null) {
            return readableTypeArr;
        }
        synchronized (this) {
            if (this.mLocalTypeArray == null) {
                jniPassCounter++;
                Object[] importTypeArray = importTypeArray();
                Kw.a(importTypeArray);
                Object[] objArr = importTypeArray;
                this.mLocalTypeArray = (ReadableType[]) Arrays.copyOf(objArr, objArr.length, ReadableType[].class);
            }
        }
        return this.mLocalTypeArray;
    }

    private native ReadableNativeMap getMapNative(int i);

    private native String getStringNative(int i);

    private native ReadableType getTypeNative(int i);

    private native Object[] importArray();

    private native Object[] importTypeArray();

    private native boolean isNullNative(int i);

    public static void setUseNativeAccessor(boolean z) {
        ly.a = z;
    }

    private native int sizeNative();

    public boolean getBoolean(int i) {
        if (!ly.a) {
            return ((Boolean) getLocalArray()[i]).booleanValue();
        }
        jniPassCounter++;
        return getBooleanNative(i);
    }

    public double getDouble(int i) {
        if (!ly.a) {
            return ((Double) getLocalArray()[i]).doubleValue();
        }
        jniPassCounter++;
        return getDoubleNative(i);
    }

    public Dynamic getDynamic(int i) {
        return DynamicFromArray.create(this, i);
    }

    public int getInt(int i) {
        if (!ly.a) {
            return ((Double) getLocalArray()[i]).intValue();
        }
        jniPassCounter++;
        return getIntNative(i);
    }

    public String getString(int i) {
        if (!ly.a) {
            return (String) getLocalArray()[i];
        }
        jniPassCounter++;
        return getStringNative(i);
    }

    public ReadableType getType(int i) {
        if (!ly.a) {
            return getLocalTypeArray()[i];
        }
        jniPassCounter++;
        return getTypeNative(i);
    }

    public boolean isNull(int i) {
        if (ly.a) {
            jniPassCounter++;
            return isNullNative(i);
        } else if (getLocalArray()[i] == null) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        if (!ly.a) {
            return getLocalArray().length;
        }
        jniPassCounter++;
        return sizeNative();
    }

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            int ordinal = getType(i).ordinal();
            if (ordinal == 0) {
                arrayList.add((Object) null);
            } else if (ordinal == 1) {
                arrayList.add(Boolean.valueOf(getBoolean(i)));
            } else if (ordinal == 2) {
                arrayList.add(Double.valueOf(getDouble(i)));
            } else if (ordinal == 3) {
                arrayList.add(getString(i));
            } else if (ordinal == 4) {
                arrayList.add(getMap(i).toHashMap());
            } else if (ordinal == 5) {
                arrayList.add(getArray(i).toArrayList());
            } else {
                throw new IllegalArgumentException(Eo.a("Could not convert object at index: ", i, "."));
            }
        }
        return arrayList;
    }

    public ReadableNativeArray getArray(int i) {
        if (!ly.a) {
            return (ReadableNativeArray) getLocalArray()[i];
        }
        jniPassCounter++;
        return getArrayNative(i);
    }

    public ReadableNativeMap getMap(int i) {
        if (!ly.a) {
            return (ReadableNativeMap) getLocalArray()[i];
        }
        jniPassCounter++;
        return getMapNative(i);
    }
}
