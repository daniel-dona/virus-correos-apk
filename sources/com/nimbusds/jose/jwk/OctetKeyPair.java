package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import java.net.URI;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public class OctetKeyPair extends JWK {
    public static final Set<Curve> SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Curve[]{Curve.Ed25519, Curve.Ed448, Curve.X25519, Curve.X448})));
    public static final long serialVersionUID = 1;
    public final Curve crv;

    /* renamed from: d */
    public final Base64URL f1352d;

    /* renamed from: x */
    public final Base64URL f1353x;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetKeyPair(Curve curve, Base64URL base64URL, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL4 = base64URL;
        if (curve2 == null) {
            throw new IllegalArgumentException("The curve must not be null");
        } else if (SUPPORTED_CURVES.contains(curve)) {
            this.crv = curve2;
            if (base64URL4 != null) {
                this.f1353x = base64URL4;
                this.f1352d = null;
                return;
            }
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        } else {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        }
    }

    public Curve getCurve() {
        return this.crv;
    }

    public Base64URL getD() {
        return this.f1352d;
    }

    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("crv", this.crv.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("x", this.f1353x.toString());
        return linkedHashMap;
    }

    public Base64URL getX() {
        return this.f1353x;
    }

    public boolean isPrivate() {
        return this.f1352d != null;
    }

    public boolean matches(X509Certificate x509Certificate) {
        return false;
    }

    public int size() {
        byte[] decode = this.f1353x.decode();
        if (decode == null) {
            return 0;
        }
        return decode.length * 8;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.f1353x.toString());
        Base64URL base64URL = this.f1352d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    public KeyPair toKeyPair() throws JOSEException {
        throw new JOSEException("Export to java.security.KeyPair not supported");
    }

    public PrivateKey toPrivateKey() throws JOSEException {
        throw new JOSEException("Export to java.security.PrivateKey not supported");
    }

    public PublicKey toPublicKey() throws JOSEException {
        throw new JOSEException("Export to java.security.PublicKey not supported");
    }

    public OctetKeyPair toPublicJWK() {
        return new OctetKeyPair(getCurve(), getX(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    public static OctetKeyPair parse(String str) throws ParseException {
        return parse(bv0.a(str));
    }

    public static OctetKeyPair parse(JSONObject jSONObject) throws ParseException {
        Curve parse = Curve.parse(bv0.d(jSONObject, "crv"));
        Base64URL base64URL = new Base64URL(bv0.d(jSONObject, "x"));
        if (Uu0.d(jSONObject) == KeyType.OKP) {
            Base64URL base64URL2 = null;
            if (jSONObject.get("d") != null) {
                base64URL2 = new Base64URL(bv0.d(jSONObject, "d"));
            }
            Base64URL base64URL3 = base64URL2;
            if (base64URL3 != null) {
                return new OctetKeyPair(parse, base64URL, base64URL3, Uu0.e(jSONObject), Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), Uu0.f(jSONObject), (KeyStore) null);
            }
            try {
                return new OctetKeyPair(parse, base64URL, Uu0.e(jSONObject), Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), Uu0.f(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be OKP", 0);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetKeyPair(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        if (curve2 == null) {
            throw new IllegalArgumentException("The curve must not be null");
        } else if (SUPPORTED_CURVES.contains(curve2)) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.f1353x = base64URL5;
                if (base64URL6 != null) {
                    this.f1352d = base64URL6;
                    return;
                }
                throw new IllegalArgumentException("The 'd' parameter must not be null");
            }
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        } else {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve2);
        }
    }
}
