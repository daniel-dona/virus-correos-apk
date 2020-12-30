package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public abstract class Header implements Serializable {
    public static final Map<String, Object> EMPTY_CUSTOM_PARAMS = Collections.unmodifiableMap(new HashMap());
    public static final long serialVersionUID = 1;
    public final Algorithm alg;
    public final Set<String> crit;
    public final String cty;
    public final Map<String, Object> customParams;
    public final Base64URL parsedBase64URL;
    public final JOSEObjectType typ;

    public Header(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        if (algorithm != null) {
            this.alg = algorithm;
            this.typ = jOSEObjectType;
            this.cty = str;
            if (set != null) {
                this.crit = Collections.unmodifiableSet(new HashSet(set));
            } else {
                this.crit = null;
            }
            if (map != null) {
                this.customParams = Collections.unmodifiableMap(new HashMap(map));
            } else {
                this.customParams = EMPTY_CUSTOM_PARAMS;
            }
            this.parsedBase64URL = base64URL;
            return;
        }
        throw new IllegalArgumentException("The algorithm \"alg\" header parameter must not be null");
    }

    public static Header parse(JSONObject jSONObject) throws ParseException {
        return parse(jSONObject, (Base64URL) null);
    }

    public static Algorithm parseAlgorithm(JSONObject jSONObject) throws ParseException {
        String d = bv0.d(jSONObject, "alg");
        if (d.equals(Algorithm.NONE.getName())) {
            return Algorithm.NONE;
        }
        if (jSONObject.containsKey("enc")) {
            return JWEAlgorithm.parse(d);
        }
        return JWSAlgorithm.parse(d);
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public String getContentType() {
        return this.cty;
    }

    public Set<String> getCriticalParams() {
        return this.crit;
    }

    public Object getCustomParam(String str) {
        return this.customParams.get(str);
    }

    public Map<String, Object> getCustomParams() {
        return this.customParams;
    }

    public Set<String> getIncludedParams() {
        HashSet hashSet = new HashSet(getCustomParams().keySet());
        hashSet.add("alg");
        if (getType() != null) {
            hashSet.add("typ");
        }
        if (getContentType() != null) {
            hashSet.add("cty");
        }
        if (getCriticalParams() != null && !getCriticalParams().isEmpty()) {
            hashSet.add("crit");
        }
        return hashSet;
    }

    public Base64URL getParsedBase64URL() {
        return this.parsedBase64URL;
    }

    public JOSEObjectType getType() {
        return this.typ;
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL = this.parsedBase64URL;
        return base64URL == null ? Base64URL.encode(toString()) : base64URL;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject(this.customParams);
        jSONObject.put("alg", this.alg.toString());
        JOSEObjectType jOSEObjectType = this.typ;
        if (jOSEObjectType != null) {
            jSONObject.put("typ", jOSEObjectType.toString());
        }
        String str = this.cty;
        if (str != null) {
            jSONObject.put("cty", str);
        }
        Set<String> set = this.crit;
        if (set != null && !set.isEmpty()) {
            jSONObject.put("crit", new ArrayList(this.crit));
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public static Header parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        Algorithm parseAlgorithm = parseAlgorithm(jSONObject);
        if (parseAlgorithm.equals(Algorithm.NONE)) {
            return PlainHeader.parse(jSONObject, base64URL);
        }
        if (parseAlgorithm instanceof JWSAlgorithm) {
            return JWSHeader.parse(jSONObject, base64URL);
        }
        if (parseAlgorithm instanceof JWEAlgorithm) {
            return JWEHeader.parse(jSONObject, base64URL);
        }
        throw new AssertionError("Unexpected algorithm type: " + parseAlgorithm);
    }

    public static Header parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public Header(Header header) {
        this(header.getAlgorithm(), header.getType(), header.getContentType(), header.getCriticalParams(), header.getCustomParams(), header.getParsedBase64URL());
    }

    public static Header parse(String str, Base64URL base64URL) throws ParseException {
        return parse(bv0.a(str), base64URL);
    }

    public static Header parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
