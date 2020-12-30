package com.nimbusds.jwt;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class JWTClaimsSet implements Serializable {
    public static final String AUDIENCE_CLAIM = "aud";
    public static final String EXPIRATION_TIME_CLAIM = "exp";
    public static final String ISSUED_AT_CLAIM = "iat";
    public static final String ISSUER_CLAIM = "iss";
    public static final String JWT_ID_CLAIM = "jti";
    public static final String NOT_BEFORE_CLAIM = "nbf";
    public static final Set<String> REGISTERED_CLAIM_NAMES;
    public static final String SUBJECT_CLAIM = "sub";
    public static final long serialVersionUID = 1;
    public final Map<String, Object> claims;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(ISSUER_CLAIM);
        hashSet.add("sub");
        hashSet.add(AUDIENCE_CLAIM);
        hashSet.add(EXPIRATION_TIME_CLAIM);
        hashSet.add(NOT_BEFORE_CLAIM);
        hashSet.add(ISSUED_AT_CLAIM);
        hashSet.add(JWT_ID_CLAIM);
        REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public JWTClaimsSet(Map<String, Object> map) {
        this.claims = new LinkedHashMap();
        this.claims.putAll(map);
    }

    public static Set<String> getRegisteredNames() {
        return REGISTERED_CLAIM_NAMES;
    }

    public static JWTClaimsSet parse(JSONObject jSONObject) throws ParseException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : jSONObject.keySet()) {
            if (str.equals(ISSUER_CLAIM)) {
                linkedHashMap.put(ISSUER_CLAIM, bv0.d(jSONObject, ISSUER_CLAIM));
            } else if (str.equals("sub")) {
                linkedHashMap.put("sub", bv0.d(jSONObject, "sub"));
            } else if (str.equals(AUDIENCE_CLAIM)) {
                Object obj = jSONObject.get(AUDIENCE_CLAIM);
                if (obj instanceof String) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(bv0.d(jSONObject, AUDIENCE_CLAIM));
                    linkedHashMap.put(AUDIENCE_CLAIM, arrayList);
                } else if (obj instanceof List) {
                    linkedHashMap.put(AUDIENCE_CLAIM, bv0.e(jSONObject, AUDIENCE_CLAIM));
                } else if (obj == null) {
                    linkedHashMap.put(AUDIENCE_CLAIM, (Object) null);
                }
            } else if (str.equals(EXPIRATION_TIME_CLAIM)) {
                linkedHashMap.put(EXPIRATION_TIME_CLAIM, new Date(bv0.c(jSONObject, EXPIRATION_TIME_CLAIM) * 1000));
            } else if (str.equals(NOT_BEFORE_CLAIM)) {
                linkedHashMap.put(NOT_BEFORE_CLAIM, new Date(bv0.c(jSONObject, NOT_BEFORE_CLAIM) * 1000));
            } else if (str.equals(ISSUED_AT_CLAIM)) {
                linkedHashMap.put(ISSUED_AT_CLAIM, new Date(bv0.c(jSONObject, ISSUED_AT_CLAIM) * 1000));
            } else if (str.equals(JWT_ID_CLAIM)) {
                linkedHashMap.put(JWT_ID_CLAIM, bv0.d(jSONObject, JWT_ID_CLAIM));
            } else {
                linkedHashMap.put(str, jSONObject.get(str));
            }
        }
        return new JWTClaimsSet(linkedHashMap, (JWTClaimsSet) null);
    }

    public List<String> getAudience() {
        Object claim = getClaim(AUDIENCE_CLAIM);
        if (claim instanceof String) {
            return Collections.singletonList((String) claim);
        }
        try {
            List<String> stringListClaim = getStringListClaim(AUDIENCE_CLAIM);
            return stringListClaim != null ? Collections.unmodifiableList(stringListClaim) : Collections.emptyList();
        } catch (ParseException unused) {
            return Collections.emptyList();
        }
    }

    public Boolean getBooleanClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof Boolean)) {
            return (Boolean) claim;
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a Boolean"), 0);
    }

    public Object getClaim(String str) {
        return this.claims.get(str);
    }

    public Map<String, Object> getClaims() {
        return Collections.unmodifiableMap(this.claims);
    }

    public Date getDateClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Date) {
            return (Date) claim;
        }
        if (claim instanceof Number) {
            return new Date(((Number) claim).longValue() * 1000);
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a Date"), 0);
    }

    public Double getDoubleClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Double.valueOf(((Number) claim).doubleValue());
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a Double"), 0);
    }

    public Date getExpirationTime() {
        try {
            return getDateClaim(EXPIRATION_TIME_CLAIM);
        } catch (ParseException unused) {
            return null;
        }
    }

    public Float getFloatClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Float.valueOf(((Number) claim).floatValue());
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a Float"), 0);
    }

    public Integer getIntegerClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Integer.valueOf(((Number) claim).intValue());
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not an Integer"), 0);
    }

    public Date getIssueTime() {
        try {
            return getDateClaim(ISSUED_AT_CLAIM);
        } catch (ParseException unused) {
            return null;
        }
    }

    public String getIssuer() {
        try {
            return getStringClaim(ISSUER_CLAIM);
        } catch (ParseException unused) {
            return null;
        }
    }

    public JSONObject getJSONObjectClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof JSONObject) {
            return (JSONObject) claim;
        }
        if (claim instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : ((Map) claim).entrySet()) {
                if (entry.getKey() instanceof String) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            return jSONObject;
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a JSON object or Map"), 0);
    }

    public String getJWTID() {
        try {
            return getStringClaim(JWT_ID_CLAIM);
        } catch (ParseException unused) {
            return null;
        }
    }

    public Long getLongClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Long.valueOf(((Number) claim).longValue());
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a Number"), 0);
    }

    public Date getNotBeforeTime() {
        try {
            return getDateClaim(NOT_BEFORE_CLAIM);
        } catch (ParseException unused) {
            return null;
        }
    }

    public String[] getStringArrayClaim(String str) throws ParseException {
        if (getClaim(str) == null) {
            return null;
        }
        try {
            List list = (List) getClaim(str);
            String[] strArr = new String[list.size()];
            int i = 0;
            while (i < strArr.length) {
                try {
                    strArr[i] = (String) list.get(i);
                    i++;
                } catch (ClassCastException unused) {
                    throw new ParseException(Eo.a("The \"", str, "\" claim is not a list / JSON array of strings"), 0);
                }
            }
            return strArr;
        } catch (ClassCastException unused2) {
            throw new ParseException(Eo.a("The \"", str, "\" claim is not a list / JSON array"), 0);
        }
    }

    public String getStringClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof String)) {
            return (String) claim;
        }
        throw new ParseException(Eo.a("The \"", str, "\" claim is not a String"), 0);
    }

    public List<String> getStringListClaim(String str) throws ParseException {
        String[] stringArrayClaim = getStringArrayClaim(str);
        if (stringArrayClaim == null) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList(stringArrayClaim));
    }

    public String getSubject() {
        try {
            return getStringClaim("sub");
        } catch (ParseException unused) {
            return null;
        }
    }

    public URI getURIClaim(String str) throws ParseException {
        String stringClaim = getStringClaim(str);
        if (stringClaim == null) {
            return null;
        }
        try {
            return new URI(stringClaim);
        } catch (URISyntaxException e) {
            throw new ParseException("The \"" + str + "\" claim is not a URI: " + e.getMessage(), 0);
        }
    }

    public JSONObject toJSONObject() {
        return toJSONObject(false);
    }

    public String toString() {
        return toJSONObject().toJSONString();
    }

    public <T> T toType(ev0<T> ev0) {
        return ev0.a(this);
    }

    public JSONObject toJSONObject(boolean z) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : this.claims.entrySet()) {
            if (next.getValue() instanceof Date) {
                jSONObject.put((String) next.getKey(), Long.valueOf(((Date) next.getValue()).getTime() / 1000));
            } else if (AUDIENCE_CLAIM.equals(next.getKey())) {
                List<String> audience = getAudience();
                if (audience == null || audience.isEmpty()) {
                    if (z) {
                        jSONObject.put(AUDIENCE_CLAIM, (Object) null);
                    }
                } else if (audience.size() == 1) {
                    jSONObject.put(AUDIENCE_CLAIM, audience.get(0));
                } else {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.addAll(audience);
                    jSONObject.put(AUDIENCE_CLAIM, jSONArray);
                }
            } else if (next.getValue() != null) {
                jSONObject.put((String) next.getKey(), next.getValue());
            } else if (z) {
                jSONObject.put((String) next.getKey(), (Object) null);
            }
        }
        return jSONObject;
    }

    public /* synthetic */ JWTClaimsSet(Map map, JWTClaimsSet jWTClaimsSet) {
        this(map);
    }

    public static JWTClaimsSet parse(String str) throws ParseException {
        return parse(bv0.a(str));
    }
}
