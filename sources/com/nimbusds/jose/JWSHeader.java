package com.nimbusds.jose;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class JWSHeader extends CommonSEHeader {
    public static final Set<String> REGISTERED_PARAMETER_NAMES;
    public static final long serialVersionUID = 1;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("jku");
        hashSet.add("jwk");
        hashSet.add("x5u");
        hashSet.add("x5t");
        hashSet.add("x5t#S256");
        hashSet.add("x5c");
        hashSet.add("kid");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public JWSHeader(JWSAlgorithm jWSAlgorithm) {
        this(jWSAlgorithm, (JOSEObjectType) null, (String) null, (Set<String>) null, (URI) null, (JWK) null, (URI) null, (Base64URL) null, (Base64URL) null, (List<Base64>) null, (String) null, (Map<String, Object>) null, (Base64URL) null);
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    public /* bridge */ /* synthetic */ Set getIncludedParams() {
        return super.getIncludedParams();
    }

    public /* bridge */ /* synthetic */ JWK getJWK() {
        return super.getJWK();
    }

    public /* bridge */ /* synthetic */ URI getJWKURL() {
        return super.getJWKURL();
    }

    public /* bridge */ /* synthetic */ String getKeyID() {
        return super.getKeyID();
    }

    public /* bridge */ /* synthetic */ List getX509CertChain() {
        return super.getX509CertChain();
    }

    public /* bridge */ /* synthetic */ Base64URL getX509CertSHA256Thumbprint() {
        return super.getX509CertSHA256Thumbprint();
    }

    @Deprecated
    public /* bridge */ /* synthetic */ Base64URL getX509CertThumbprint() {
        return super.getX509CertThumbprint();
    }

    public /* bridge */ /* synthetic */ URI getX509CertURL() {
        return super.getX509CertURL();
    }

    public /* bridge */ /* synthetic */ JSONObject toJSONObject() {
        return super.toJSONObject();
    }

    public JWSHeader(JWSAlgorithm jWSAlgorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, Map<String, Object> map, Base64URL base64URL3) {
        super(jWSAlgorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, map, base64URL3);
        if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
            throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
        }
    }

    public JWSAlgorithm getAlgorithm() {
        return (JWSAlgorithm) super.getAlgorithm();
    }

    public JWSHeader(JWSHeader jWSHeader) {
        this(jWSHeader.getAlgorithm(), jWSHeader.getType(), jWSHeader.getContentType(), jWSHeader.getCriticalParams(), jWSHeader.getJWKURL(), jWSHeader.getJWK(), jWSHeader.getX509CertURL(), jWSHeader.getX509CertThumbprint(), jWSHeader.getX509CertSHA256Thumbprint(), jWSHeader.getX509CertChain(), jWSHeader.getKeyID(), jWSHeader.getCustomParams(), jWSHeader.getParsedBase64URL());
    }

    public static JWSHeader parse(JSONObject jSONObject) throws ParseException {
        return parse(jSONObject, (Base64URL) null);
    }

    public static JWSHeader parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        JSONObject jSONObject2 = jSONObject;
        Algorithm parseAlgorithm = Header.parseAlgorithm(jSONObject);
        if (parseAlgorithm instanceof JWSAlgorithm) {
            JWSAlgorithm jWSAlgorithm = (JWSAlgorithm) parseAlgorithm;
            if (!jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
                JOSEObjectType jOSEObjectType = null;
                String str = null;
                HashSet hashSet = null;
                URI uri = null;
                JWK jwk = null;
                URI uri2 = null;
                Base64URL base64URL2 = null;
                Base64URL base64URL3 = null;
                List list = null;
                String str2 = null;
                HashMap hashMap = null;
                for (String str3 : jSONObject.keySet()) {
                    if (!"alg".equals(str3)) {
                        if ("typ".equals(str3)) {
                            jOSEObjectType = new JOSEObjectType(bv0.d(jSONObject2, str3));
                        } else if ("cty".equals(str3)) {
                            str = bv0.d(jSONObject2, str3);
                        } else if ("crit".equals(str3)) {
                            hashSet = new HashSet(bv0.e(jSONObject2, str3));
                        } else if ("jku".equals(str3)) {
                            uri = bv0.f(jSONObject2, str3);
                        } else if ("jwk".equals(str3)) {
                            jwk = JWK.parse(bv0.b(jSONObject2, str3));
                        } else if ("x5u".equals(str3)) {
                            uri2 = bv0.f(jSONObject2, str3);
                        } else if ("x5t".equals(str3)) {
                            base64URL2 = new Base64URL(bv0.d(jSONObject2, str3));
                        } else if ("x5t#S256".equals(str3)) {
                            base64URL3 = new Base64URL(bv0.d(jSONObject2, str3));
                        } else if ("x5c".equals(str3)) {
                            list = dv0.a(bv0.a(jSONObject2, str3));
                        } else if ("kid".equals(str3)) {
                            str2 = bv0.d(jSONObject2, str3);
                        } else {
                            Object obj = jSONObject2.get(str3);
                            if (!getRegisteredParameterNames().contains(str3)) {
                                if (hashMap == null) {
                                    hashMap = new HashMap();
                                }
                                hashMap.put(str3, obj);
                            } else {
                                throw new IllegalArgumentException(Eo.a("The parameter name \"", str3, "\" matches a registered name"));
                            }
                        }
                    }
                    jSONObject2 = jSONObject;
                }
                return new JWSHeader(jWSAlgorithm, jOSEObjectType, str, hashSet, uri, jwk, uri2, base64URL2, base64URL3, list, str2, hashMap, base64URL);
            }
            throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
        }
        throw new ParseException("The algorithm \"alg\" header parameter must be for signatures", 0);
    }

    public static JWSHeader parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public static JWSHeader parse(String str, Base64URL base64URL) throws ParseException {
        return parse(bv0.a(str), base64URL);
    }

    public static JWSHeader parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
