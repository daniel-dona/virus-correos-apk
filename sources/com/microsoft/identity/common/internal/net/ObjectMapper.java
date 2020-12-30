package com.microsoft.identity.common.internal.net;

import com.google.gson.Gson;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/* compiled from: PG */
public final class ObjectMapper {
    public static final String ENCODING_SCHEME = "UTF-8";
    public static final String TAG = "ObjectMapper";

    public static <T> T deserializeJsonStringToObject(String str, Class<T> cls) {
        return BK.a(cls).cast(new Gson().mo2098a(str, (Type) cls));
    }

    public static Map<String, String> deserializeQueryStringToMap(String str) {
        HashMap hashMap = new HashMap();
        if (StringUtil.isEmpty(str)) {
            return hashMap;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String[] split = stringTokenizer.nextToken().split("=");
            if (split.length == 2) {
                try {
                    String decode = URLDecoder.decode(split[0], "UTF-8");
                    String decode2 = URLDecoder.decode(split[1], "UTF-8");
                    if (!StringUtil.isEmpty(decode) && !StringUtil.isEmpty(decode2)) {
                        hashMap.put(decode, decode2);
                    }
                } catch (UnsupportedEncodingException e) {
                    Logger.error(TAG, (String) null, "Decode failed.", e);
                }
            }
        }
        return hashMap;
    }

    public static String serializeExposedFieldsOfObjectToJsonString(Object obj) {
        MJ mj = new MJ();
        mj.a = mj.a.mo2139a();
        return mj.a().mo2100a(obj);
    }

    public static Map<String, Object> serializeObjectHashMap(Object obj) {
        return (Map) new Gson().mo2097a(serializeObjectToJsonString(obj), Map.class);
    }

    public static String serializeObjectToFormUrlEncoded(Object obj) throws UnsupportedEncodingException {
        String serializeObjectToJsonString = serializeObjectToJsonString(obj);
        Type type = new 1().getType();
        StringBuilder sb = new StringBuilder();
        Iterator it = ((TreeMap) new Gson().mo2098a(serializeObjectToJsonString, type)).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            sb.append('=');
            sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            if (it.hasNext()) {
                sb.append('&');
            }
        }
        return sb.toString();
    }

    public static String serializeObjectToJsonString(Object obj) {
        return new Gson().mo2100a(obj);
    }
}
