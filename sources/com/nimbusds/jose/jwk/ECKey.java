package com.nimbusds.jose.jwk;

import com.microsoft.identity.common.internal.providers.oauth2.PkceChallenge;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import java.math.BigInteger;
import java.net.URI;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class ECKey extends JWK {
    public static final Set<Curve> SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Curve[]{Curve.P_256, Curve.P_384, Curve.P_521})));
    public static final long serialVersionUID = 1;
    public final Curve crv;

    /* renamed from: d */
    public final Base64URL f1334d;
    public final PrivateKey privateKey;

    /* renamed from: x */
    public final Base64URL f1335x;

    /* renamed from: y */
    public final Base64URL f1336y;

    /* renamed from: com.nimbusds.jose.jwk.ECKey$a */
    /* compiled from: PG */
    public static class C0355a {

        /* renamed from: a */
        public final Curve f1337a;

        /* renamed from: b */
        public final Base64URL f1338b;

        /* renamed from: c */
        public final Base64URL f1339c;

        /* renamed from: d */
        public Base64URL f1340d;

        /* renamed from: e */
        public PrivateKey f1341e;

        /* renamed from: f */
        public KeyUse f1342f;

        /* renamed from: g */
        public Set<KeyOperation> f1343g;

        /* renamed from: h */
        public Algorithm f1344h;

        /* renamed from: i */
        public String f1345i;

        /* renamed from: j */
        public URI f1346j;
        @Deprecated

        /* renamed from: k */
        public Base64URL f1347k;

        /* renamed from: l */
        public Base64URL f1348l;

        /* renamed from: m */
        public List<Base64> f1349m;

        /* renamed from: n */
        public KeyStore f1350n;

        public C0355a(Curve curve, ECPublicKey eCPublicKey) {
            Base64URL encodeCoordinate = ECKey.encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX());
            Base64URL encodeCoordinate2 = ECKey.encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY());
            if (curve != null) {
                this.f1337a = curve;
                if (encodeCoordinate != null) {
                    this.f1338b = encodeCoordinate;
                    if (encodeCoordinate2 != null) {
                        this.f1339c = encodeCoordinate2;
                        return;
                    }
                    throw new IllegalArgumentException("The 'y' coordinate must not be null");
                }
                throw new IllegalArgumentException("The 'x' coordinate must not be null");
            }
            throw new IllegalArgumentException("The curve must not be null");
        }

        /* renamed from: a */
        public C0355a mo4649a(ECPrivateKey eCPrivateKey) {
            if (eCPrivateKey != null) {
                this.f1340d = ECKey.encodeCoordinate(eCPrivateKey.getParams().getCurve().getField().getFieldSize(), eCPrivateKey.getS());
            }
            return this;
        }

        /* renamed from: a */
        public C0355a mo4648a(PrivateKey privateKey) {
            if ("EC".equalsIgnoreCase(privateKey.getAlgorithm())) {
                this.f1341e = privateKey;
                return this;
            }
            throw new IllegalArgumentException("The private key algorithm must be EC");
        }

        /* renamed from: a */
        public C0355a mo4644a(KeyUse keyUse) {
            this.f1342f = keyUse;
            return this;
        }

        /* renamed from: a */
        public C0355a mo4646a(String str) {
            this.f1345i = str;
            return this;
        }

        /* renamed from: a */
        public C0355a mo4645a(Base64URL base64URL) {
            this.f1348l = base64URL;
            return this;
        }

        /* renamed from: a */
        public C0355a mo4650a(List<Base64> list) {
            this.f1349m = list;
            return this;
        }

        /* renamed from: a */
        public C0355a mo4647a(KeyStore keyStore) {
            this.f1350n = keyStore;
            return this;
        }

        public C0355a(ECKey eCKey) {
            this.f1337a = eCKey.crv;
            this.f1338b = eCKey.f1335x;
            this.f1339c = eCKey.f1336y;
            this.f1340d = eCKey.f1334d;
            this.f1341e = eCKey.privateKey;
            this.f1342f = eCKey.getKeyUse();
            this.f1343g = eCKey.getKeyOperations();
            this.f1344h = eCKey.getAlgorithm();
            this.f1345i = eCKey.getKeyID();
            this.f1346j = eCKey.getX509CertURL();
            this.f1347k = eCKey.getX509CertThumbprint();
            this.f1348l = eCKey.getX509CertSHA256Thumbprint();
            this.f1349m = eCKey.getX509CertChain();
            this.f1350n = eCKey.getKeyStore();
        }

        /* renamed from: a */
        public ECKey mo4651a() {
            try {
                if (this.f1340d == null && this.f1341e == null) {
                    return new ECKey(this.f1337a, this.f1338b, this.f1339c, this.f1342f, this.f1343g, this.f1344h, this.f1345i, this.f1346j, this.f1347k, this.f1348l, this.f1349m, this.f1350n);
                }
                if (this.f1341e != null) {
                    return new ECKey(this.f1337a, this.f1338b, this.f1339c, this.f1341e, this.f1342f, this.f1343g, this.f1344h, this.f1345i, this.f1346j, this.f1347k, this.f1348l, this.f1349m, this.f1350n);
                }
                return new ECKey(this.f1337a, this.f1338b, this.f1339c, this.f1340d, this.f1342f, this.f1343g, this.f1344h, this.f1345i, this.f1346j, this.f1347k, this.f1348l, this.f1349m, this.f1350n);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f1351EC, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.f1335x = base64URL5;
                if (base64URL6 != null) {
                    this.f1336y = base64URL6;
                    ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
                    ensureMatches(getParsedX509CertChain());
                    this.f1334d = null;
                    this.privateKey = null;
                    return;
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    public static Base64URL encodeCoordinate(int i, BigInteger bigInteger) {
        byte[] a = Zu0.a(bigInteger);
        int i2 = (i + 7) / 8;
        if (a.length >= i2) {
            return Base64URL.encode(a);
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(a, 0, bArr, i2 - a.length, a.length);
        return Base64URL.encode(bArr);
    }

    private void ensureMatches(List<X509Certificate> list) {
        if (list != null && !matches(list.get(0))) {
            throw new IllegalArgumentException("The public subject key info of the first X.509 certificate in the chain must match the JWK type and public parameters");
        }
    }

    public static void ensurePublicCoordinatesOnCurve(Curve curve, Base64URL base64URL, Base64URL base64URL2) {
        if (SUPPORTED_CURVES.contains(curve)) {
            BigInteger decodeToBigInteger = base64URL.decodeToBigInteger();
            BigInteger decodeToBigInteger2 = base64URL2.decodeToBigInteger();
            EllipticCurve curve2 = curve.toECParameterSpec().getCurve();
            BigInteger a = curve2.getA();
            BigInteger b = curve2.getB();
            BigInteger p = ((ECFieldFp) curve2.getField()).getP();
            if (!decodeToBigInteger2.pow(2).mod(p).equals(decodeToBigInteger.pow(3).add(a.multiply(decodeToBigInteger)).add(b).mod(p))) {
                throw new IllegalArgumentException("Invalid EC JWK: The 'x' and 'y' public coordinates are not on the " + curve + " curve");
            }
            return;
        }
        throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
    }

    public Curve getCurve() {
        return this.crv;
    }

    public Base64URL getD() {
        return this.f1334d;
    }

    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("crv", this.crv.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("x", this.f1335x.toString());
        linkedHashMap.put("y", this.f1336y.toString());
        return linkedHashMap;
    }

    public Base64URL getX() {
        return this.f1335x;
    }

    public Base64URL getY() {
        return this.f1336y;
    }

    public boolean isPrivate() {
        return (this.f1334d == null && this.privateKey == null) ? false : true;
    }

    public boolean matches(X509Certificate x509Certificate) {
        try {
            ECPublicKey eCPublicKey = (ECPublicKey) getParsedX509CertChain().get(0).getPublicKey();
            if (getX().decodeToBigInteger().equals(eCPublicKey.getW().getAffineX()) && getY().decodeToBigInteger().equals(eCPublicKey.getW().getAffineY())) {
                return true;
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int size() {
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec != null) {
            return eCParameterSpec.getCurve().getField().getFieldSize();
        }
        throw new UnsupportedOperationException("Couldn't determine field size for curve " + this.crv.getName());
    }

    public ECPrivateKey toECPrivateKey() throws JOSEException {
        return toECPrivateKey((Provider) null);
    }

    public ECPublicKey toECPublicKey() throws JOSEException {
        return toECPublicKey((Provider) null);
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.f1335x.toString());
        jSONObject.put("y", this.f1336y.toString());
        Base64URL base64URL = this.f1334d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    public KeyPair toKeyPair() throws JOSEException {
        return toKeyPair((Provider) null);
    }

    public PrivateKey toPrivateKey() throws JOSEException {
        ECPrivateKey eCPrivateKey = toECPrivateKey();
        if (eCPrivateKey != null) {
            return eCPrivateKey;
        }
        return this.privateKey;
    }

    public PublicKey toPublicKey() throws JOSEException {
        return toECPublicKey();
    }

    public static ECKey load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null || !(certificate instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (x509Certificate.getPublicKey() instanceof ECPublicKey) {
            C0355a aVar = new C0355a(parse(x509Certificate));
            aVar.mo4646a(str);
            aVar.mo4647a(keyStore);
            ECKey a = aVar.mo4651a();
            try {
                Key key = keyStore.getKey(str, cArr);
                if (key instanceof ECPrivateKey) {
                    C0355a aVar2 = new C0355a(a);
                    aVar2.mo4649a((ECPrivateKey) key);
                    return aVar2.mo4651a();
                } else if (!(key instanceof PrivateKey) || !"EC".equalsIgnoreCase(key.getAlgorithm())) {
                    return a;
                } else {
                    C0355a aVar3 = new C0355a(a);
                    aVar3.mo4648a((PrivateKey) key);
                    return aVar3.mo4651a();
                }
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
                throw new JOSEException("Couldn't retrieve private EC key (bad pin?): " + e.getMessage(), e);
            }
        } else {
            throw new JOSEException("Couldn't load EC JWK: The key algorithm is not EC");
        }
    }

    public ECPrivateKey toECPrivateKey(Provider provider) throws JOSEException {
        KeyFactory keyFactory;
        if (this.f1334d == null) {
            return null;
        }
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec != null) {
            ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(this.f1334d.decodeToBigInteger(), eCParameterSpec);
            if (provider == null) {
                try {
                    keyFactory = KeyFactory.getInstance("EC");
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new JOSEException(e.getMessage(), e);
                }
            } else {
                keyFactory = KeyFactory.getInstance("EC", provider);
            }
            return (ECPrivateKey) keyFactory.generatePrivate(eCPrivateKeySpec);
        }
        throw new JOSEException("Couldn't get EC parameter spec for curve " + this.crv);
    }

    public ECPublicKey toECPublicKey(Provider provider) throws JOSEException {
        KeyFactory keyFactory;
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec != null) {
            ECPublicKeySpec eCPublicKeySpec = new ECPublicKeySpec(new ECPoint(this.f1335x.decodeToBigInteger(), this.f1336y.decodeToBigInteger()), eCParameterSpec);
            if (provider == null) {
                try {
                    keyFactory = KeyFactory.getInstance("EC");
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new JOSEException(e.getMessage(), e);
                }
            } else {
                keyFactory = KeyFactory.getInstance("EC", provider);
            }
            return (ECPublicKey) keyFactory.generatePublic(eCPublicKeySpec);
        }
        throw new JOSEException("Couldn't get EC parameter spec for curve " + this.crv);
    }

    public KeyPair toKeyPair(Provider provider) throws JOSEException {
        if (this.privateKey != null) {
            return new KeyPair(toECPublicKey(provider), this.privateKey);
        }
        return new KeyPair(toECPublicKey(provider), toECPrivateKey(provider));
    }

    public ECKey toPublicJWK() {
        return new ECKey(getCurve(), getX(), getY(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    public static ECKey parse(String str) throws ParseException {
        return parse(bv0.a(str));
    }

    public static ECKey parse(JSONObject jSONObject) throws ParseException {
        JSONObject jSONObject2 = jSONObject;
        Curve parse = Curve.parse(bv0.d(jSONObject2, "crv"));
        Base64URL base64URL = new Base64URL(bv0.d(jSONObject2, "x"));
        Base64URL base64URL2 = new Base64URL(bv0.d(jSONObject2, "y"));
        if (Uu0.d(jSONObject) == KeyType.f1351EC) {
            Base64URL base64URL3 = null;
            if (jSONObject2.get("d") != null) {
                base64URL3 = new Base64URL(bv0.d(jSONObject2, "d"));
            }
            Base64URL base64URL4 = base64URL3;
            if (base64URL4 != null) {
                return new ECKey(parse, base64URL, base64URL2, base64URL4, Uu0.e(jSONObject), (Set<KeyOperation>) Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), (List<Base64>) Uu0.f(jSONObject), (KeyStore) null);
            }
            try {
                return new ECKey(parse, base64URL, base64URL2, Uu0.e(jSONObject), (Set<KeyOperation>) Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), (List<Base64>) Uu0.f(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be EC", 0);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f1351EC, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL6 = base64URL;
        Base64URL base64URL7 = base64URL2;
        Base64URL base64URL8 = base64URL3;
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL6 != null) {
                this.f1335x = base64URL6;
                if (base64URL7 != null) {
                    this.f1336y = base64URL7;
                    ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
                    ensureMatches(getParsedX509CertChain());
                    if (base64URL8 != null) {
                        this.f1334d = base64URL8;
                        this.privateKey = null;
                        return;
                    }
                    throw new IllegalArgumentException("The 'd' coordinate must not be null");
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, PrivateKey privateKey2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f1351EC, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.f1335x = base64URL5;
                if (base64URL6 != null) {
                    this.f1336y = base64URL6;
                    ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
                    ensureMatches(getParsedX509CertChain());
                    this.f1334d = null;
                    this.privateKey = privateKey2;
                    return;
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    public static ECKey parse(X509Certificate x509Certificate) throws JOSEException {
        if (x509Certificate.getPublicKey() instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) x509Certificate.getPublicKey();
            try {
                String obj = new DH0(x509Certificate).a().f().f().toString();
                Curve forOID = Curve.forOID(obj);
                if (forOID != null) {
                    MessageDigest instance = MessageDigest.getInstance(PkceChallenge.DIGEST_ALGORITHM);
                    C0355a aVar = new C0355a(forOID, eCPublicKey);
                    aVar.mo4644a(KeyUse.from(x509Certificate));
                    aVar.mo4646a(x509Certificate.getSerialNumber().toString(10));
                    aVar.mo4650a((List<Base64>) Collections.singletonList(Base64.encode(x509Certificate.getEncoded())));
                    aVar.mo4645a(Base64URL.encode(instance.digest(x509Certificate.getEncoded())));
                    return aVar.mo4651a();
                }
                throw new JOSEException("Couldn't determine EC JWK curve for OID " + obj);
            } catch (NoSuchAlgorithmException e) {
                throw new JOSEException("Couldn't encode x5t parameter: " + e.getMessage(), e);
            } catch (CertificateEncodingException e2) {
                throw new JOSEException("Couldn't encode x5c parameter: " + e2.getMessage(), e2);
            }
        } else {
            throw new JOSEException("The public key of the X.509 certificate is not EC");
        }
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), encodeCoordinate(eCPrivateKey.getParams().getCurve().getField().getFieldSize(), eCPrivateKey.getS()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, PrivateKey privateKey2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), privateKey2, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }
}
