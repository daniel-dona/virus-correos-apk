package net.minidev.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class JSONObject extends HashMap<String, Object> implements mB0, nB0, pB0 {
    public static final long serialVersionUID = -503443796854799292L;

    public JSONObject() {
    }

    public static String escape(String str) {
        qB0 qb0 = tB0.a;
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        qb0.a(str, sb);
        return sb.toString();
    }

    public static String toJSONString(Map<String, ? extends Object> map) {
        return toJSONString(map, tB0.a);
    }

    public static void writeJSON(Map<String, ? extends Object> map, Appendable appendable) throws IOException {
        writeJSON(map, appendable, tB0.a);
    }

    public static void writeJSONKV(String str, Object obj, Appendable appendable, qB0 qb0) throws IOException {
        if (str == null) {
            appendable.append("null");
        } else if (!qb0.e.mustBeProtect(str)) {
            appendable.append(str);
        } else {
            appendable.append('\"');
            tB0.a(str, appendable, qb0);
            appendable.append('\"');
        }
        appendable.append(':');
        if (obj instanceof String) {
            qb0.a(appendable, (String) obj);
        } else {
            tB0.a(obj, appendable, qb0);
        }
    }

    public JSONObject appendField(String str, Object obj) {
        put(str, obj);
        return this;
    }

    public Number getAsNumber(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return (Number) obj;
        }
        return Long.valueOf(obj.toString());
    }

    public String getAsString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public void merge(Object obj) {
        merge(this, obj);
    }

    public String toString(qB0 qb0) {
        return toJSONString(this, qb0);
    }

    public void writeJSONString(Appendable appendable) throws IOException {
        writeJSON(this, appendable, tB0.a);
    }

    public JSONObject(Map<String, ?> map) {
        super(map);
    }

    public static JSONObject merge(JSONObject jSONObject, Object obj) {
        if (obj == null) {
            return jSONObject;
        }
        if (obj instanceof JSONObject) {
            return merge(jSONObject, (JSONObject) obj);
        }
        throw new RuntimeException("JSON megre can not merge JSONObject with " + obj.getClass());
    }

    public static String toJSONString(Map<String, ? extends Object> map, qB0 qb0) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSON(map, sb, qb0);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public static void writeJSON(Map<String, ? extends Object> map, Appendable appendable, qB0 qb0) throws IOException {
        if (map == null) {
            appendable.append("null");
        } else {
            aC0.i.a(map, appendable, qb0);
        }
    }

    public String toString() {
        return toJSONString(this, tB0.a);
    }

    public void writeJSONString(Appendable appendable, qB0 qb0) throws IOException {
        writeJSON(this, appendable, qb0);
    }

    public static JSONObject merge(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 == null) {
            return jSONObject;
        }
        for (String str : jSONObject.keySet()) {
            Object obj = jSONObject.get(str);
            Object obj2 = jSONObject2.get(str);
            if (obj2 != null) {
                if (obj instanceof JSONArray) {
                    jSONObject.put(str, merge((JSONArray) obj, obj2));
                } else if (obj instanceof JSONObject) {
                    jSONObject.put(str, merge((JSONObject) obj, obj2));
                } else if (!obj.equals(obj2)) {
                    if (obj.getClass().equals(obj2.getClass())) {
                        throw new RuntimeException("JSON merge can not merge two " + obj.getClass().getName() + " Object together");
                    }
                    throw new RuntimeException("JSON merge can not merge " + obj.getClass().getName() + " with " + obj2.getClass().getName());
                }
            }
        }
        for (String str2 : jSONObject2.keySet()) {
            if (!jSONObject.containsKey(str2)) {
                jSONObject.put(str2, jSONObject2.get(str2));
            }
        }
        return jSONObject;
    }

    public String toJSONString() {
        return toJSONString(this, tB0.a);
    }

    public String toJSONString(qB0 qb0) {
        return toJSONString(this, qb0);
    }

    public static JSONArray merge(JSONArray jSONArray, Object obj) {
        if (obj == null) {
            return jSONArray;
        }
        if (jSONArray instanceof JSONArray) {
            return merge(jSONArray, (JSONArray) obj);
        }
        jSONArray.add(obj);
        return jSONArray;
    }

    public static JSONArray merge(JSONArray jSONArray, JSONArray jSONArray2) {
        jSONArray.addAll(jSONArray2);
        return jSONArray;
    }
}
