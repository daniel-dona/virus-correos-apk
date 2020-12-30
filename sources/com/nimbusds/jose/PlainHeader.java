package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class PlainHeader extends Header {
    public static final Set<String> REGISTERED_PARAMETER_NAMES;
    public static final long serialVersionUID = 1;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public PlainHeader() {
        this((JOSEObjectType) null, (String) null, (Set<String>) null, (Map<String, Object>) null, (Base64URL) null);
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    public Algorithm getAlgorithm() {
        return Algorithm.NONE;
    }

    public PlainHeader(JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        super(Algorithm.NONE, jOSEObjectType, str, set, map, base64URL);
    }

    public PlainHeader(PlainHeader plainHeader) {
        this(plainHeader.getType(), plainHeader.getContentType(), plainHeader.getCriticalParams(), plainHeader.getCustomParams(), plainHeader.getParsedBase64URL());
    }

    public static PlainHeader parse(JSONObject jSONObject) throws ParseException {
        return parse(jSONObject, (Base64URL) null);
    }

    public static PlainHeader parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        if (Header.parseAlgorithm(jSONObject) == Algorithm.NONE) {
            JOSEObjectType jOSEObjectType = null;
            String str = null;
            HashSet hashSet = null;
            HashMap hashMap = null;
            for (String str2 : jSONObject.keySet()) {
                if (!"alg".equals(str2)) {
                    if ("typ".equals(str2)) {
                        jOSEObjectType = new JOSEObjectType(bv0.d(jSONObject, str2));
                    } else if ("cty".equals(str2)) {
                        str = bv0.d(jSONObject, str2);
                    } else if ("crit".equals(str2)) {
                        hashSet = new HashSet(bv0.e(jSONObject, str2));
                    } else {
                        Object obj = jSONObject.get(str2);
                        if (!getRegisteredParameterNames().contains(str2)) {
                            if (hashMap == null) {
                                hashMap = new HashMap();
                            }
                            hashMap.put(str2, obj);
                        } else {
                            throw new IllegalArgumentException(Eo.a("The parameter name \"", str2, "\" matches a registered name"));
                        }
                    }
                }
            }
            return new PlainHeader(jOSEObjectType, str, hashSet, hashMap, base64URL);
        }
        throw new ParseException("The algorithm \"alg\" header parameter must be \"none\"", 0);
    }

    public static PlainHeader parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public static PlainHeader parse(String str, Base64URL base64URL) throws ParseException {
        return parse(bv0.a(str), base64URL);
    }

    public static PlainHeader parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
