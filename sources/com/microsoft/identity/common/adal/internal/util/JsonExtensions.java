package com.microsoft.identity.common.adal.internal.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public final class JsonExtensions {
    public static Map<String, String> extractJsonObjectIntoMap(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }
}
