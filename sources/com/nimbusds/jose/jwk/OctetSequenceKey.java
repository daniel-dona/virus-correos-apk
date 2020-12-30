package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.IntegerOverflowException;
import java.net.URI;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class OctetSequenceKey extends JWK {
    public static final long serialVersionUID = 1;

    /* renamed from: k */
    public final Base64URL f1354k;

    /* renamed from: com.nimbusds.jose.jwk.OctetSequenceKey$a */
    /* compiled from: PG */
    public static class C0356a {

        /* renamed from: a */
        public final Base64URL f1355a;

        /* renamed from: b */
        public String f1356b;

        /* renamed from: c */
        public KeyStore f1357c;

        public C0356a(SecretKey secretKey) {
            byte[] encoded = secretKey.getEncoded();
            Base64URL encode = Base64URL.encode(encoded);
            if (encode != null) {
                this.f1355a = encode;
                if (encoded.length == 0) {
                    throw new IllegalArgumentException("The key must have a positive length");
                }
                return;
            }
            throw new IllegalArgumentException("The key value must not be null");
        }

        /* renamed from: a */
        public C0356a mo4688a(String str) {
            this.f1356b = str;
            return this;
        }

        /* renamed from: a */
        public C0356a mo4689a(KeyStore keyStore) {
            this.f1357c = keyStore;
            return this;
        }

        /* renamed from: a */
        public OctetSequenceKey mo4690a() {
            try {
                return new OctetSequenceKey(this.f1355a, (KeyUse) null, (Set<KeyOperation>) null, (Algorithm) null, this.f1356b, (URI) null, (Base64URL) null, (Base64URL) null, (List<Base64>) null, this.f1357c);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetSequenceKey(Base64URL base64URL, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OCT, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        Base64URL base64URL4 = base64URL;
        if (base64URL4 != null) {
            this.f1354k = base64URL4;
        } else {
            throw new IllegalArgumentException("The key value must not be null");
        }
    }

    public Base64URL getKeyValue() {
        return this.f1354k;
    }

    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("k", this.f1354k.toString());
        linkedHashMap.put("kty", getKeyType().toString());
        return linkedHashMap;
    }

    public boolean isPrivate() {
        return true;
    }

    public int size() {
        try {
            return av0.a(this.f1354k.decode());
        } catch (IntegerOverflowException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }

    public byte[] toByteArray() {
        return getKeyValue().decode();
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("k", this.f1354k.toString());
        return jSONObject;
    }

    public OctetSequenceKey toPublicJWK() {
        return null;
    }

    public SecretKey toSecretKey() {
        return toSecretKey("NONE");
    }

    public static OctetSequenceKey load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        try {
            Key key = keyStore.getKey(str, cArr);
            if (!(key instanceof SecretKey)) {
                return null;
            }
            C0356a aVar = new C0356a((SecretKey) key);
            aVar.mo4688a(str);
            aVar.mo4689a(keyStore);
            return aVar.mo4690a();
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new JOSEException("Couldn't retrieve secret key (bad pin?): " + e.getMessage(), e);
        }
    }

    public SecretKey toSecretKey(String str) {
        return new SecretKeySpec(toByteArray(), str);
    }

    public static OctetSequenceKey parse(String str) throws ParseException {
        return parse(bv0.a(str));
    }

    public static OctetSequenceKey parse(JSONObject jSONObject) throws ParseException {
        Base64URL base64URL = new Base64URL(bv0.d(jSONObject, "k"));
        if (Uu0.d(jSONObject) == KeyType.OCT) {
            return new OctetSequenceKey(base64URL, Uu0.e(jSONObject), Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), Uu0.f(jSONObject), (KeyStore) null);
        }
        throw new ParseException("The key type \"kty\" must be oct", 0);
    }
}
