package com.facebook.react.bridge;

import android.os.Bundle;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class Arguments {

    /* renamed from: com.facebook.react.bridge.Arguments$2 */
    /* compiled from: PG */
    public static /* synthetic */ class C01032 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.Arguments.C01032.<clinit>():void");
        }
    }

    public static void addEntry(WritableNativeMap writableNativeMap, String str, Object obj) {
        Object makeNativeObject = makeNativeObject(obj);
        if (makeNativeObject == null) {
            writableNativeMap.putNull(str);
        } else if (makeNativeObject instanceof Boolean) {
            writableNativeMap.putBoolean(str, ((Boolean) makeNativeObject).booleanValue());
        } else if (makeNativeObject instanceof Integer) {
            writableNativeMap.putInt(str, ((Integer) makeNativeObject).intValue());
        } else if (makeNativeObject instanceof Number) {
            writableNativeMap.putDouble(str, ((Number) makeNativeObject).doubleValue());
        } else if (makeNativeObject instanceof String) {
            writableNativeMap.putString(str, (String) makeNativeObject);
        } else if (makeNativeObject instanceof WritableNativeArray) {
            writableNativeMap.putArray(str, (WritableNativeArray) makeNativeObject);
        } else if (makeNativeObject instanceof WritableNativeMap) {
            writableNativeMap.putMap(str, (WritableNativeMap) makeNativeObject);
        } else {
            StringBuilder a = Eo.a("Could not convert ");
            a.append(makeNativeObject.getClass());
            throw new IllegalArgumentException(a.toString());
        }
    }

    public static WritableArray createArray() {
        return new WritableNativeArray();
    }

    public static WritableMap createMap() {
        return new WritableNativeMap();
    }

    public static WritableArray fromArray(Object obj) {
        WritableArray createArray = createArray();
        int i = 0;
        if (obj instanceof String[]) {
            String[] strArr = (String[]) obj;
            int length = strArr.length;
            while (i < length) {
                createArray.pushString(strArr[i]);
                i++;
            }
        } else if (obj instanceof Bundle[]) {
            Bundle[] bundleArr = (Bundle[]) obj;
            int length2 = bundleArr.length;
            while (i < length2) {
                createArray.pushMap(fromBundle(bundleArr[i]));
                i++;
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length;
            while (i < length3) {
                createArray.pushInt(iArr[i]);
                i++;
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length4 = fArr.length;
            while (i < length4) {
                createArray.pushDouble((double) fArr[i]);
                i++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length5 = dArr.length;
            while (i < length5) {
                createArray.pushDouble(dArr[i]);
                i++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length6 = zArr.length;
            while (i < length6) {
                createArray.pushBoolean(zArr[i]);
                i++;
            }
        } else {
            StringBuilder a = Eo.a("Unknown array type ");
            a.append(obj.getClass());
            throw new IllegalArgumentException(a.toString());
        }
        return createArray;
    }

    public static WritableMap fromBundle(Bundle bundle) {
        WritableMap createMap = createMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                createMap.putNull(str);
            } else if (obj.getClass().isArray()) {
                createMap.putArray(str, fromArray(obj));
            } else if (obj instanceof String) {
                createMap.putString(str, (String) obj);
            } else if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    createMap.putInt(str, ((Integer) obj).intValue());
                } else {
                    createMap.putDouble(str, ((Number) obj).doubleValue());
                }
            } else if (obj instanceof Boolean) {
                createMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Bundle) {
                createMap.putMap(str, fromBundle((Bundle) obj));
            } else if (obj instanceof List) {
                createMap.putArray(str, fromList((List) obj));
            } else {
                StringBuilder a = Eo.a("Could not convert ");
                a.append(obj.getClass());
                throw new IllegalArgumentException(a.toString());
            }
        }
        return createMap;
    }

    public static WritableNativeArray fromJavaArgs(Object[] objArr) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (Boolean bool : objArr) {
            if (bool == null) {
                writableNativeArray.pushNull();
            } else {
                Class<?> cls = bool.getClass();
                if (cls == Boolean.class) {
                    writableNativeArray.pushBoolean(bool.booleanValue());
                } else if (cls == Integer.class) {
                    writableNativeArray.pushDouble(((Integer) bool).doubleValue());
                } else if (cls == Double.class) {
                    writableNativeArray.pushDouble(((Double) bool).doubleValue());
                } else if (cls == Float.class) {
                    writableNativeArray.pushDouble(((Float) bool).doubleValue());
                } else if (cls == String.class) {
                    writableNativeArray.pushString(bool.toString());
                } else if (cls == WritableNativeMap.class) {
                    writableNativeArray.pushMap((WritableNativeMap) bool);
                } else if (cls == WritableNativeArray.class) {
                    writableNativeArray.pushArray((WritableNativeArray) bool);
                } else {
                    throw new RuntimeException("Cannot convert argument of type " + cls);
                }
            }
        }
        return writableNativeArray;
    }

    public static WritableArray fromList(List list) {
        WritableArray createArray = createArray();
        for (Object next : list) {
            if (next == null) {
                createArray.pushNull();
            } else if (next.getClass().isArray()) {
                createArray.pushArray(fromArray(next));
            } else if (next instanceof Bundle) {
                createArray.pushMap(fromBundle((Bundle) next));
            } else if (next instanceof List) {
                createArray.pushArray(fromList((List) next));
            } else if (next instanceof String) {
                createArray.pushString((String) next);
            } else if (next instanceof Integer) {
                createArray.pushInt(((Integer) next).intValue());
            } else if (next instanceof Number) {
                createArray.pushDouble(((Number) next).doubleValue());
            } else if (next instanceof Boolean) {
                createArray.pushBoolean(((Boolean) next).booleanValue());
            } else {
                StringBuilder a = Eo.a("Unknown value type ");
                a.append(next.getClass());
                throw new IllegalArgumentException(a.toString());
            }
        }
        return createArray;
    }

    public static WritableNativeArray makeNativeArray(List list) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (list == null) {
            return writableNativeArray;
        }
        for (Object makeNativeObject : list) {
            Object makeNativeObject2 = makeNativeObject(makeNativeObject);
            if (makeNativeObject2 == null) {
                writableNativeArray.pushNull();
            } else if (makeNativeObject2 instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) makeNativeObject2).booleanValue());
            } else if (makeNativeObject2 instanceof Integer) {
                writableNativeArray.pushInt(((Integer) makeNativeObject2).intValue());
            } else if (makeNativeObject2 instanceof Double) {
                writableNativeArray.pushDouble(((Double) makeNativeObject2).doubleValue());
            } else if (makeNativeObject2 instanceof String) {
                writableNativeArray.pushString((String) makeNativeObject2);
            } else if (makeNativeObject2 instanceof WritableNativeArray) {
                writableNativeArray.pushArray((WritableNativeArray) makeNativeObject2);
            } else if (makeNativeObject2 instanceof WritableNativeMap) {
                writableNativeArray.pushMap((WritableNativeMap) makeNativeObject2);
            } else {
                StringBuilder a = Eo.a("Could not convert ");
                a.append(makeNativeObject2.getClass());
                throw new IllegalArgumentException(a.toString());
            }
        }
        return writableNativeArray;
    }

    public static WritableNativeMap makeNativeMap(Map<String, Object> map) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (map == null) {
            return writableNativeMap;
        }
        for (Map.Entry next : map.entrySet()) {
            addEntry(writableNativeMap, (String) next.getKey(), next.getValue());
        }
        return writableNativeMap;
    }

    public static Object makeNativeObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Float) || (obj instanceof Long) || (obj instanceof Byte) || (obj instanceof Short)) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj.getClass().isArray()) {
            return makeNativeArray(obj);
        }
        if (obj instanceof List) {
            return makeNativeArray((List) obj);
        }
        if (obj instanceof Map) {
            return makeNativeMap((Map<String, Object>) (Map) obj);
        }
        return obj instanceof Bundle ? makeNativeMap((Bundle) obj) : obj;
    }

    public static Bundle toBundle(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        Bundle bundle = new Bundle();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int ordinal = readableMap.getType(nextKey).ordinal();
            if (ordinal == 0) {
                bundle.putString(nextKey, (String) null);
            } else if (ordinal == 1) {
                bundle.putBoolean(nextKey, readableMap.getBoolean(nextKey));
            } else if (ordinal == 2) {
                bundle.putDouble(nextKey, readableMap.getDouble(nextKey));
            } else if (ordinal == 3) {
                bundle.putString(nextKey, readableMap.getString(nextKey));
            } else if (ordinal == 4) {
                bundle.putBundle(nextKey, toBundle(readableMap.getMap(nextKey)));
            } else if (ordinal == 5) {
                bundle.putSerializable(nextKey, toList(readableMap.getArray(nextKey)));
            } else {
                throw new IllegalArgumentException(Eo.b("Could not convert object with key: ", nextKey, "."));
            }
        }
        return bundle;
    }

    public static ArrayList toList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            int ordinal = readableArray.getType(i).ordinal();
            if (ordinal == 0) {
                arrayList.add((Object) null);
            } else if (ordinal == 1) {
                arrayList.add(Boolean.valueOf(readableArray.getBoolean(i)));
            } else if (ordinal == 2) {
                double d = readableArray.getDouble(i);
                if (d == Math.rint(d)) {
                    arrayList.add(Integer.valueOf((int) d));
                } else {
                    arrayList.add(Double.valueOf(d));
                }
            } else if (ordinal == 3) {
                arrayList.add(readableArray.getString(i));
            } else if (ordinal == 4) {
                arrayList.add(toBundle(readableArray.getMap(i)));
            } else if (ordinal == 5) {
                arrayList.add(toList(readableArray.getArray(i)));
            } else {
                throw new IllegalArgumentException("Could not convert object in array.");
            }
        }
        return arrayList;
    }

    public static WritableNativeMap makeNativeMap(Bundle bundle) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (bundle == null) {
            return writableNativeMap;
        }
        for (String str : bundle.keySet()) {
            addEntry(writableNativeMap, str, bundle.get(str));
        }
        return writableNativeMap;
    }

    public static <T> WritableNativeArray makeNativeArray(final Object obj) {
        if (obj == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray((List) new AbstractList() {
            public Object get(int i) {
                return Array.get(obj, i);
            }

            public int size() {
                return Array.getLength(obj);
            }
        });
    }
}
