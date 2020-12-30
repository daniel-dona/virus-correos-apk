package com.nimbusds.jose;

import com.nimbusds.jose.jwk.ECKey;
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
public final class JWEHeader extends CommonSEHeader {
    public static final Set<String> REGISTERED_PARAMETER_NAMES;
    public static final long serialVersionUID = 1;
    public final Base64URL apu;
    public final Base64URL apv;
    public final EncryptionMethod enc;
    public final ECKey epk;

    /* renamed from: iv */
    public final Base64URL f1308iv;
    public final int p2c;
    public final Base64URL p2s;
    public final Base64URL tag;
    public final CompressionAlgorithm zip;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("enc");
        hashSet.add("epk");
        hashSet.add("zip");
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
        hashSet.add("apu");
        hashSet.add("apv");
        hashSet.add("p2s");
        hashSet.add("p2c");
        hashSet.add("iv");
        hashSet.add("authTag");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public JWEHeader(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) {
        this(jWEAlgorithm, encryptionMethod, (JOSEObjectType) null, (String) null, (Set<String>) null, (URI) null, (JWK) null, (URI) null, (Base64URL) null, (Base64URL) null, (List<Base64>) null, (String) null, (ECKey) null, (CompressionAlgorithm) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, 0, (Base64URL) null, (Base64URL) null, (Map<String, Object>) null, (Base64URL) null);
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    public static EncryptionMethod parseEncryptionMethod(JSONObject jSONObject) throws ParseException {
        return EncryptionMethod.parse(bv0.d(jSONObject, "enc"));
    }

    public Base64URL getAgreementPartyUInfo() {
        return this.apu;
    }

    public Base64URL getAgreementPartyVInfo() {
        return this.apv;
    }

    public Base64URL getAuthTag() {
        return this.tag;
    }

    public CompressionAlgorithm getCompressionAlgorithm() {
        return this.zip;
    }

    public EncryptionMethod getEncryptionMethod() {
        return this.enc;
    }

    public ECKey getEphemeralPublicKey() {
        return this.epk;
    }

    public Base64URL getIV() {
        return this.f1308iv;
    }

    public Set<String> getIncludedParams() {
        Set<String> includedParams = super.getIncludedParams();
        if (this.enc != null) {
            includedParams.add("enc");
        }
        if (this.epk != null) {
            includedParams.add("epk");
        }
        if (this.zip != null) {
            includedParams.add("zip");
        }
        if (this.apu != null) {
            includedParams.add("apu");
        }
        if (this.apv != null) {
            includedParams.add("apv");
        }
        if (this.p2s != null) {
            includedParams.add("p2s");
        }
        if (this.p2c > 0) {
            includedParams.add("p2c");
        }
        if (this.f1308iv != null) {
            includedParams.add("iv");
        }
        if (this.tag != null) {
            includedParams.add("tag");
        }
        return includedParams;
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

    public int getPBES2Count() {
        return this.p2c;
    }

    public Base64URL getPBES2Salt() {
        return this.p2s;
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

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        EncryptionMethod encryptionMethod = this.enc;
        if (encryptionMethod != null) {
            jSONObject.put("enc", encryptionMethod.toString());
        }
        ECKey eCKey = this.epk;
        if (eCKey != null) {
            jSONObject.put("epk", eCKey.toJSONObject());
        }
        CompressionAlgorithm compressionAlgorithm = this.zip;
        if (compressionAlgorithm != null) {
            jSONObject.put("zip", compressionAlgorithm.toString());
        }
        Base64URL base64URL = this.apu;
        if (base64URL != null) {
            jSONObject.put("apu", base64URL.toString());
        }
        Base64URL base64URL2 = this.apv;
        if (base64URL2 != null) {
            jSONObject.put("apv", base64URL2.toString());
        }
        Base64URL base64URL3 = this.p2s;
        if (base64URL3 != null) {
            jSONObject.put("p2s", base64URL3.toString());
        }
        int i = this.p2c;
        if (i > 0) {
            jSONObject.put("p2c", Integer.valueOf(i));
        }
        Base64URL base64URL4 = this.f1308iv;
        if (base64URL4 != null) {
            jSONObject.put("iv", base64URL4.toString());
        }
        Base64URL base64URL5 = this.tag;
        if (base64URL5 != null) {
            jSONObject.put("tag", base64URL5.toString());
        }
        return jSONObject;
    }

    /* renamed from: com.nimbusds.jose.JWEHeader$a */
    /* compiled from: PG */
    public static class C0354a {

        /* renamed from: a */
        public final JWEAlgorithm f1309a;

        /* renamed from: b */
        public final EncryptionMethod f1310b;

        /* renamed from: c */
        public JOSEObjectType f1311c;

        /* renamed from: d */
        public String f1312d;

        /* renamed from: e */
        public Set<String> f1313e;

        /* renamed from: f */
        public URI f1314f;

        /* renamed from: g */
        public JWK f1315g;

        /* renamed from: h */
        public URI f1316h;
        @Deprecated

        /* renamed from: i */
        public Base64URL f1317i;

        /* renamed from: j */
        public Base64URL f1318j;

        /* renamed from: k */
        public List<Base64> f1319k;

        /* renamed from: l */
        public String f1320l;

        /* renamed from: m */
        public ECKey f1321m;

        /* renamed from: n */
        public CompressionAlgorithm f1322n;

        /* renamed from: o */
        public Base64URL f1323o;

        /* renamed from: p */
        public Base64URL f1324p;

        /* renamed from: q */
        public Base64URL f1325q;

        /* renamed from: r */
        public int f1326r;

        /* renamed from: s */
        public Base64URL f1327s;

        /* renamed from: t */
        public Base64URL f1328t;

        /* renamed from: u */
        public Map<String, Object> f1329u;

        /* renamed from: v */
        public Base64URL f1330v;

        public C0354a(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) {
            if (!jWEAlgorithm.getName().equals(Algorithm.NONE.getName())) {
                this.f1309a = jWEAlgorithm;
                if (encryptionMethod != null) {
                    this.f1310b = encryptionMethod;
                    return;
                }
                throw new IllegalArgumentException("The encryption method \"enc\" parameter must not be null");
            }
            throw new IllegalArgumentException("The JWE algorithm \"alg\" cannot be \"none\"");
        }

        /* renamed from: a */
        public C0354a mo4597a(int i) {
            if (i >= 0) {
                this.f1326r = i;
                return this;
            }
            throw new IllegalArgumentException("The PBES2 count parameter must not be negative");
        }

        /* renamed from: a */
        public C0354a mo4598a(String str, Object obj) {
            if (!JWEHeader.getRegisteredParameterNames().contains(str)) {
                if (this.f1329u == null) {
                    this.f1329u = new HashMap();
                }
                this.f1329u.put(str, obj);
                return this;
            }
            throw new IllegalArgumentException(Eo.a("The parameter name \"", str, "\" matches a registered name"));
        }

        /* renamed from: a */
        public JWEHeader mo4599a() {
            return new JWEHeader(this.f1309a, this.f1310b, this.f1311c, this.f1312d, this.f1313e, this.f1314f, this.f1315g, this.f1316h, this.f1317i, this.f1318j, this.f1319k, this.f1320l, this.f1321m, this.f1322n, this.f1323o, this.f1324p, this.f1325q, this.f1326r, this.f1327s, this.f1328t, this.f1329u, this.f1330v);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JWEHeader(Algorithm algorithm, EncryptionMethod encryptionMethod, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, ECKey eCKey, CompressionAlgorithm compressionAlgorithm, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, int i, Base64URL base64URL6, Base64URL base64URL7, Map<String, Object> map, Base64URL base64URL8) {
        super(algorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, map, base64URL8);
        EncryptionMethod encryptionMethod2 = encryptionMethod;
        if (algorithm.getName().equals(Algorithm.NONE.getName())) {
            throw new IllegalArgumentException("The JWE algorithm cannot be \"none\"");
        } else if (encryptionMethod2 != null) {
            this.enc = encryptionMethod2;
            this.epk = eCKey;
            this.zip = compressionAlgorithm;
            this.apu = base64URL3;
            this.apv = base64URL4;
            this.p2s = base64URL5;
            this.p2c = i;
            this.f1308iv = base64URL6;
            this.tag = base64URL7;
        } else {
            throw new IllegalArgumentException("The encryption method \"enc\" parameter must not be null");
        }
    }

    public JWEAlgorithm getAlgorithm() {
        return (JWEAlgorithm) super.getAlgorithm();
    }

    public static JWEHeader parse(JSONObject jSONObject) throws ParseException {
        return parse(jSONObject, (Base64URL) null);
    }

    public static JWEHeader parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        Algorithm parseAlgorithm = Header.parseAlgorithm(jSONObject);
        if (parseAlgorithm instanceof JWEAlgorithm) {
            C0354a aVar = new C0354a((JWEAlgorithm) parseAlgorithm, parseEncryptionMethod(jSONObject));
            aVar.f1330v = base64URL;
            for (String str : jSONObject.keySet()) {
                if (!"alg".equals(str) && !"enc".equals(str)) {
                    if ("typ".equals(str)) {
                        aVar.f1311c = new JOSEObjectType(bv0.d(jSONObject, str));
                    } else if ("cty".equals(str)) {
                        aVar.f1312d = bv0.d(jSONObject, str);
                    } else if ("crit".equals(str)) {
                        aVar.f1313e = new HashSet(bv0.e(jSONObject, str));
                    } else if ("jku".equals(str)) {
                        aVar.f1314f = bv0.f(jSONObject, str);
                    } else if ("jwk".equals(str)) {
                        aVar.f1315g = JWK.parse(bv0.b(jSONObject, str));
                    } else if ("x5u".equals(str)) {
                        aVar.f1316h = bv0.f(jSONObject, str);
                    } else if ("x5t".equals(str)) {
                        aVar.f1317i = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("x5t#S256".equals(str)) {
                        aVar.f1318j = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("x5c".equals(str)) {
                        aVar.f1319k = dv0.a(bv0.a(jSONObject, str));
                    } else if ("kid".equals(str)) {
                        aVar.f1320l = bv0.d(jSONObject, str);
                    } else if ("epk".equals(str)) {
                        aVar.f1321m = ECKey.parse(bv0.b(jSONObject, str));
                    } else if ("zip".equals(str)) {
                        aVar.f1322n = new CompressionAlgorithm(bv0.d(jSONObject, str));
                    } else if ("apu".equals(str)) {
                        aVar.f1323o = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("apv".equals(str)) {
                        aVar.f1324p = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("p2s".equals(str)) {
                        aVar.f1325q = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("p2c".equals(str)) {
                        aVar.mo4597a(((Number) bv0.a(jSONObject, str, Number.class)).intValue());
                    } else if ("iv".equals(str)) {
                        aVar.f1327s = new Base64URL(bv0.d(jSONObject, str));
                    } else if ("tag".equals(str)) {
                        aVar.f1328t = new Base64URL(bv0.d(jSONObject, str));
                    } else {
                        aVar.mo4598a(str, jSONObject.get(str));
                    }
                }
            }
            return aVar.mo4599a();
        }
        throw new ParseException("The algorithm \"alg\" header parameter must be for encryption", 0);
    }

    public JWEHeader(JWEHeader jWEHeader) {
        this(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod(), jWEHeader.getType(), jWEHeader.getContentType(), jWEHeader.getCriticalParams(), jWEHeader.getJWKURL(), jWEHeader.getJWK(), jWEHeader.getX509CertURL(), jWEHeader.getX509CertThumbprint(), jWEHeader.getX509CertSHA256Thumbprint(), jWEHeader.getX509CertChain(), jWEHeader.getKeyID(), jWEHeader.getEphemeralPublicKey(), jWEHeader.getCompressionAlgorithm(), jWEHeader.getAgreementPartyUInfo(), jWEHeader.getAgreementPartyVInfo(), jWEHeader.getPBES2Salt(), jWEHeader.getPBES2Count(), jWEHeader.getIV(), jWEHeader.getAuthTag(), jWEHeader.getCustomParams(), jWEHeader.getParsedBase64URL());
    }

    public static JWEHeader parse(String str) throws ParseException {
        return parse(bv0.a(str), (Base64URL) null);
    }

    public static JWEHeader parse(String str, Base64URL base64URL) throws ParseException {
        return parse(bv0.a(str), base64URL);
    }

    public static JWEHeader parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
