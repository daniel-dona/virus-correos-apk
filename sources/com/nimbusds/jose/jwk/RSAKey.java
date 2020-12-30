package com.nimbusds.jose.jwk;

import com.microsoft.identity.common.internal.providers.oauth2.PkceChallenge;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.IntegerOverflowException;
import java.io.Serializable;
import java.net.URI;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAOtherPrimeInfo;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class RSAKey extends JWK {
    public static final long serialVersionUID = 1;

    /* renamed from: d */
    public final Base64URL f1358d;

    /* renamed from: dp */
    public final Base64URL f1359dp;

    /* renamed from: dq */
    public final Base64URL f1360dq;

    /* renamed from: e */
    public final Base64URL f1361e;

    /* renamed from: n */
    public final Base64URL f1362n;
    public final List<OtherPrimesInfo> oth;

    /* renamed from: p */
    public final Base64URL f1363p;
    public final PrivateKey privateKey;

    /* renamed from: q */
    public final Base64URL f1364q;

    /* renamed from: qi */
    public final Base64URL f1365qi;

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (List<OtherPrimesInfo>) null, (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
    }

    public Base64URL getFirstCRTCoefficient() {
        return this.f1365qi;
    }

    public Base64URL getFirstFactorCRTExponent() {
        return this.f1359dp;
    }

    public Base64URL getFirstPrimeFactor() {
        return this.f1363p;
    }

    public Base64URL getModulus() {
        return this.f1362n;
    }

    public List<OtherPrimesInfo> getOtherPrimes() {
        return this.oth;
    }

    public Base64URL getPrivateExponent() {
        return this.f1358d;
    }

    public Base64URL getPublicExponent() {
        return this.f1361e;
    }

    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("e", this.f1361e.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("n", this.f1362n.toString());
        return linkedHashMap;
    }

    public Base64URL getSecondFactorCRTExponent() {
        return this.f1360dq;
    }

    public Base64URL getSecondPrimeFactor() {
        return this.f1364q;
    }

    public boolean isPrivate() {
        return (this.f1358d == null && this.f1363p == null && this.privateKey == null) ? false : true;
    }

    public boolean matches(X509Certificate x509Certificate) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) getParsedX509CertChain().get(0).getPublicKey();
            if (this.f1361e.decodeToBigInteger().equals(rSAPublicKey.getPublicExponent()) && this.f1362n.decodeToBigInteger().equals(rSAPublicKey.getModulus())) {
                return true;
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int size() {
        try {
            return av0.a(this.f1362n.decode());
        } catch (IntegerOverflowException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("n", this.f1362n.toString());
        jSONObject.put("e", this.f1361e.toString());
        Base64URL base64URL = this.f1358d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        Base64URL base64URL2 = this.f1363p;
        if (base64URL2 != null) {
            jSONObject.put("p", base64URL2.toString());
        }
        Base64URL base64URL3 = this.f1364q;
        if (base64URL3 != null) {
            jSONObject.put("q", base64URL3.toString());
        }
        Base64URL base64URL4 = this.f1359dp;
        if (base64URL4 != null) {
            jSONObject.put("dp", base64URL4.toString());
        }
        Base64URL base64URL5 = this.f1360dq;
        if (base64URL5 != null) {
            jSONObject.put("dq", base64URL5.toString());
        }
        Base64URL base64URL6 = this.f1365qi;
        if (base64URL6 != null) {
            jSONObject.put("qi", base64URL6.toString());
        }
        List<OtherPrimesInfo> list = this.oth;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (OtherPrimesInfo next : this.oth) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("r", next.f1367r.toString());
                jSONObject2.put("d", next.f1366d.toString());
                jSONObject2.put("t", next.f1368t.toString());
                jSONArray.add(jSONObject2);
            }
            jSONObject.put("oth", jSONArray);
        }
        return jSONObject;
    }

    public KeyPair toKeyPair() throws JOSEException {
        return new KeyPair(toRSAPublicKey(), toPrivateKey());
    }

    public PrivateKey toPrivateKey() throws JOSEException {
        RSAPrivateKey rSAPrivateKey = toRSAPrivateKey();
        if (rSAPrivateKey != null) {
            return rSAPrivateKey;
        }
        return this.privateKey;
    }

    public PublicKey toPublicKey() throws JOSEException {
        return toRSAPublicKey();
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.security.spec.KeySpec] */
    /* JADX WARNING: type inference failed for: r1v12, types: [java.security.spec.RSAPrivateCrtKeySpec] */
    /* JADX WARNING: type inference failed for: r1v13, types: [java.security.spec.RSAMultiPrimePrivateCrtKeySpec] */
    /* JADX WARNING: type inference failed for: r0v24 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.interfaces.RSAPrivateKey toRSAPrivateKey() throws com.nimbusds.jose.JOSEException {
        /*
            r14 = this;
            com.nimbusds.jose.util.Base64URL r0 = r14.f1358d
            if (r0 != 0) goto L_0x0006
            r0 = 0
            return r0
        L_0x0006:
            com.nimbusds.jose.util.Base64URL r0 = r14.f1362n
            java.math.BigInteger r2 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1358d
            java.math.BigInteger r4 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1363p
            if (r0 != 0) goto L_0x001d
            java.security.spec.RSAPrivateKeySpec r0 = new java.security.spec.RSAPrivateKeySpec
            r0.<init>(r2, r4)
            goto L_0x0093
        L_0x001d:
            com.nimbusds.jose.util.Base64URL r0 = r14.f1361e
            java.math.BigInteger r3 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1363p
            java.math.BigInteger r5 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1364q
            java.math.BigInteger r6 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1359dp
            java.math.BigInteger r7 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1360dq
            java.math.BigInteger r8 = r0.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r0 = r14.f1365qi
            java.math.BigInteger r9 = r0.decodeToBigInteger()
            java.util.List<com.nimbusds.jose.jwk.RSAKey$OtherPrimesInfo> r0 = r14.oth
            if (r0 == 0) goto L_0x008d
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x008d
            java.util.List<com.nimbusds.jose.jwk.RSAKey$OtherPrimesInfo> r0 = r14.oth
            int r0 = r0.size()
            java.security.spec.RSAOtherPrimeInfo[] r10 = new java.security.spec.RSAOtherPrimeInfo[r0]
            r0 = 0
        L_0x0054:
            java.util.List<com.nimbusds.jose.jwk.RSAKey$OtherPrimesInfo> r1 = r14.oth
            int r1 = r1.size()
            if (r0 < r1) goto L_0x0063
            java.security.spec.RSAMultiPrimePrivateCrtKeySpec r0 = new java.security.spec.RSAMultiPrimePrivateCrtKeySpec
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x0093
        L_0x0063:
            java.util.List<com.nimbusds.jose.jwk.RSAKey$OtherPrimesInfo> r1 = r14.oth
            java.lang.Object r1 = r1.get(r0)
            com.nimbusds.jose.jwk.RSAKey$OtherPrimesInfo r1 = (com.nimbusds.jose.jwk.RSAKey.OtherPrimesInfo) r1
            com.nimbusds.jose.util.Base64URL r11 = r1.getPrimeFactor()
            java.math.BigInteger r11 = r11.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r12 = r1.getFactorCRTExponent()
            java.math.BigInteger r12 = r12.decodeToBigInteger()
            com.nimbusds.jose.util.Base64URL r1 = r1.getFactorCRTCoefficient()
            java.math.BigInteger r1 = r1.decodeToBigInteger()
            java.security.spec.RSAOtherPrimeInfo r13 = new java.security.spec.RSAOtherPrimeInfo
            r13.<init>(r11, r12, r1)
            r10[r0] = r13
            int r0 = r0 + 1
            goto L_0x0054
        L_0x008d:
            java.security.spec.RSAPrivateCrtKeySpec r0 = new java.security.spec.RSAPrivateCrtKeySpec
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x0093:
            java.lang.String r1 = "RSA"
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1)     // Catch:{ InvalidKeySpecException -> 0x00a2, NoSuchAlgorithmException -> 0x00a0 }
            java.security.PrivateKey r0 = r1.generatePrivate(r0)     // Catch:{ InvalidKeySpecException -> 0x00a2, NoSuchAlgorithmException -> 0x00a0 }
            java.security.interfaces.RSAPrivateKey r0 = (java.security.interfaces.RSAPrivateKey) r0     // Catch:{ InvalidKeySpecException -> 0x00a2, NoSuchAlgorithmException -> 0x00a0 }
            return r0
        L_0x00a0:
            r0 = move-exception
            goto L_0x00a3
        L_0x00a2:
            r0 = move-exception
        L_0x00a3:
            com.nimbusds.jose.JOSEException r1 = new com.nimbusds.jose.JOSEException
            java.lang.String r2 = r0.getMessage()
            r1.<init>(r2, r0)
            goto L_0x00ae
        L_0x00ad:
            throw r1
        L_0x00ae:
            goto L_0x00ad
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.jwk.RSAKey.toRSAPrivateKey():java.security.interfaces.RSAPrivateKey");
    }

    public RSAPublicKey toRSAPublicKey() throws JOSEException {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(this.f1362n.decodeToBigInteger(), this.f1361e.decodeToBigInteger()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, base64URL3, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (List<OtherPrimesInfo>) null, (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The private exponent must not be null");
        }
    }

    public static RSAKey load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null || !(certificate instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (x509Certificate.getPublicKey() instanceof RSAPublicKey) {
            C0357a aVar = new C0357a(parse(x509Certificate));
            aVar.mo4711a(str);
            aVar.mo4712a(keyStore);
            RSAKey a = aVar.mo4716a();
            try {
                Key key = keyStore.getKey(str, cArr);
                if (key instanceof RSAPrivateKey) {
                    C0357a aVar2 = new C0357a(a);
                    aVar2.mo4714a((RSAPrivateKey) key);
                    return aVar2.mo4716a();
                } else if (!(key instanceof PrivateKey) || !"RSA".equalsIgnoreCase(key.getAlgorithm())) {
                    return a;
                } else {
                    C0357a aVar3 = new C0357a(a);
                    aVar3.mo4713a((PrivateKey) key);
                    return aVar3.mo4716a();
                }
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
                throw new JOSEException("Couldn't retrieve private RSA key (bad pin?): " + e.getMessage(), e);
            }
        } else {
            throw new JOSEException("Couldn't load RSA JWK: The key algorithm is not RSA");
        }
    }

    public RSAKey toPublicJWK() {
        return new RSAKey(getModulus(), getPublicExponent(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    /* renamed from: com.nimbusds.jose.jwk.RSAKey$a */
    /* compiled from: PG */
    public static class C0357a {

        /* renamed from: a */
        public final Base64URL f1369a;

        /* renamed from: b */
        public final Base64URL f1370b;

        /* renamed from: c */
        public Base64URL f1371c;

        /* renamed from: d */
        public Base64URL f1372d;

        /* renamed from: e */
        public Base64URL f1373e;

        /* renamed from: f */
        public Base64URL f1374f;

        /* renamed from: g */
        public Base64URL f1375g;

        /* renamed from: h */
        public Base64URL f1376h;

        /* renamed from: i */
        public List<OtherPrimesInfo> f1377i;

        /* renamed from: j */
        public PrivateKey f1378j;

        /* renamed from: k */
        public KeyUse f1379k;

        /* renamed from: l */
        public Set<KeyOperation> f1380l;

        /* renamed from: m */
        public Algorithm f1381m;

        /* renamed from: n */
        public String f1382n;

        /* renamed from: o */
        public URI f1383o;
        @Deprecated

        /* renamed from: p */
        public Base64URL f1384p;

        /* renamed from: q */
        public Base64URL f1385q;

        /* renamed from: r */
        public List<Base64> f1386r;

        /* renamed from: s */
        public KeyStore f1387s;

        public C0357a(RSAPublicKey rSAPublicKey) {
            this.f1369a = Base64URL.encode(rSAPublicKey.getModulus());
            this.f1370b = Base64URL.encode(rSAPublicKey.getPublicExponent());
        }

        /* renamed from: a */
        public C0357a mo4714a(RSAPrivateKey rSAPrivateKey) {
            if (rSAPrivateKey instanceof RSAPrivateCrtKey) {
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) rSAPrivateKey;
                this.f1371c = Base64URL.encode(rSAPrivateCrtKey.getPrivateExponent());
                this.f1372d = Base64URL.encode(rSAPrivateCrtKey.getPrimeP());
                this.f1373e = Base64URL.encode(rSAPrivateCrtKey.getPrimeQ());
                this.f1374f = Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentP());
                this.f1375g = Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentQ());
                this.f1376h = Base64URL.encode(rSAPrivateCrtKey.getCrtCoefficient());
                return this;
            } else if (rSAPrivateKey instanceof RSAMultiPrimePrivateCrtKey) {
                RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey = (RSAMultiPrimePrivateCrtKey) rSAPrivateKey;
                this.f1371c = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent());
                this.f1372d = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeP());
                this.f1373e = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeQ());
                this.f1374f = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP());
                this.f1375g = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ());
                this.f1376h = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient());
                this.f1377i = OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo());
                return this;
            } else {
                this.f1371c = Base64URL.encode(rSAPrivateKey.getPrivateExponent());
                return this;
            }
        }

        public C0357a(RSAKey rSAKey) {
            this.f1369a = rSAKey.f1362n;
            this.f1370b = rSAKey.f1361e;
            this.f1371c = rSAKey.f1358d;
            this.f1372d = rSAKey.f1363p;
            this.f1373e = rSAKey.f1364q;
            this.f1374f = rSAKey.f1359dp;
            this.f1375g = rSAKey.f1360dq;
            this.f1376h = rSAKey.f1365qi;
            this.f1377i = rSAKey.oth;
            this.f1378j = rSAKey.privateKey;
            this.f1379k = rSAKey.getKeyUse();
            this.f1380l = rSAKey.getKeyOperations();
            this.f1381m = rSAKey.getAlgorithm();
            this.f1382n = rSAKey.getKeyID();
            this.f1383o = rSAKey.getX509CertURL();
            this.f1384p = rSAKey.getX509CertThumbprint();
            this.f1385q = rSAKey.getX509CertSHA256Thumbprint();
            this.f1386r = rSAKey.getX509CertChain();
            this.f1387s = rSAKey.getKeyStore();
        }

        /* renamed from: a */
        public C0357a mo4713a(PrivateKey privateKey) {
            if ("RSA".equalsIgnoreCase(privateKey.getAlgorithm())) {
                this.f1378j = privateKey;
                return this;
            }
            throw new IllegalArgumentException("The private key algorithm must be RSA");
        }

        /* renamed from: a */
        public C0357a mo4709a(KeyUse keyUse) {
            this.f1379k = keyUse;
            return this;
        }

        /* renamed from: a */
        public C0357a mo4711a(String str) {
            this.f1382n = str;
            return this;
        }

        /* renamed from: a */
        public C0357a mo4710a(Base64URL base64URL) {
            this.f1385q = base64URL;
            return this;
        }

        /* renamed from: a */
        public C0357a mo4715a(List<Base64> list) {
            this.f1386r = list;
            return this;
        }

        /* renamed from: a */
        public C0357a mo4712a(KeyStore keyStore) {
            this.f1387s = keyStore;
            return this;
        }

        /* renamed from: a */
        public RSAKey mo4716a() {
            try {
                Base64URL base64URL = this.f1369a;
                Base64URL base64URL2 = this.f1370b;
                Base64URL base64URL3 = this.f1371c;
                Base64URL base64URL4 = this.f1372d;
                Base64URL base64URL5 = this.f1373e;
                Base64URL base64URL6 = this.f1374f;
                Base64URL base64URL7 = this.f1375g;
                Base64URL base64URL8 = this.f1376h;
                List<OtherPrimesInfo> list = this.f1377i;
                PrivateKey privateKey = this.f1378j;
                KeyUse keyUse = this.f1379k;
                Set<KeyOperation> set = this.f1380l;
                Algorithm algorithm = this.f1381m;
                return new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, privateKey, keyUse, set, algorithm, this.f1382n, this.f1383o, this.f1384p, this.f1385q, this.f1386r, this.f1387s);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL8, Base64URL base64URL9, List<Base64> list2, KeyStore keyStore) {
        this(base64URL, base64URL2, (Base64URL) null, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, list, (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL8, base64URL9, list2, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The first prime factor must not be null");
        } else if (base64URL4 == null) {
            throw new IllegalArgumentException("The second prime factor must not be null");
        } else if (base64URL5 == null) {
            throw new IllegalArgumentException("The first factor CRT exponent must not be null");
        } else if (base64URL6 == null) {
            throw new IllegalArgumentException("The second factor CRT exponent must not be null");
        } else if (base64URL7 == null) {
            throw new IllegalArgumentException("The first CRT coefficient must not be null");
        }
    }

    public static RSAKey parse(String str) throws ParseException {
        return parse(bv0.a(str));
    }

    public static RSAKey parse(JSONObject jSONObject) throws ParseException {
        ArrayList arrayList;
        JSONObject jSONObject2 = jSONObject;
        Base64URL base64URL = new Base64URL(bv0.d(jSONObject2, "n"));
        Base64URL base64URL2 = new Base64URL(bv0.d(jSONObject2, "e"));
        if (KeyType.parse(bv0.d(jSONObject2, "kty")) == KeyType.RSA) {
            Base64URL base64URL3 = jSONObject2.containsKey("d") ? new Base64URL(bv0.d(jSONObject2, "d")) : null;
            Base64URL base64URL4 = jSONObject2.containsKey("p") ? new Base64URL(bv0.d(jSONObject2, "p")) : null;
            Base64URL base64URL5 = jSONObject2.containsKey("q") ? new Base64URL(bv0.d(jSONObject2, "q")) : null;
            Base64URL base64URL6 = jSONObject2.containsKey("dp") ? new Base64URL(bv0.d(jSONObject2, "dp")) : null;
            Base64URL base64URL7 = jSONObject2.containsKey("dq") ? new Base64URL(bv0.d(jSONObject2, "dq")) : null;
            Base64URL base64URL8 = jSONObject2.containsKey("qi") ? new Base64URL(bv0.d(jSONObject2, "qi")) : null;
            if (jSONObject2.containsKey("oth")) {
                JSONArray a = bv0.a(jSONObject2, "oth");
                arrayList = new ArrayList(a.size());
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof JSONObject) {
                        JSONObject jSONObject3 = (JSONObject) next;
                        arrayList.add(new OtherPrimesInfo(new Base64URL(bv0.d(jSONObject3, "r")), new Base64URL(bv0.d(jSONObject3, "dq")), new Base64URL(bv0.d(jSONObject3, "t"))));
                    }
                    JSONObject jSONObject4 = jSONObject;
                }
            } else {
                arrayList = null;
            }
            try {
                return new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, arrayList, (PrivateKey) null, Uu0.e(jSONObject), Uu0.c(jSONObject), Uu0.a(jSONObject), Uu0.b(jSONObject), Uu0.i(jSONObject), Uu0.h(jSONObject), Uu0.g(jSONObject), Uu0.f(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be RSA", 0);
        }
    }

    /* compiled from: PG */
    public static class OtherPrimesInfo implements Serializable {
        public static final long serialVersionUID = 1;

        /* renamed from: d */
        public final Base64URL f1366d;

        /* renamed from: r */
        public final Base64URL f1367r;

        /* renamed from: t */
        public final Base64URL f1368t;

        public OtherPrimesInfo(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
            if (base64URL != null) {
                this.f1367r = base64URL;
                if (base64URL2 != null) {
                    this.f1366d = base64URL2;
                    if (base64URL3 != null) {
                        this.f1368t = base64URL3;
                        return;
                    }
                    throw new IllegalArgumentException("The factor CRT coefficient must not be null");
                }
                throw new IllegalArgumentException("The factor CRT exponent must not be null");
            }
            throw new IllegalArgumentException("The prime factor must not be null");
        }

        public static List<OtherPrimesInfo> toList(RSAOtherPrimeInfo[] rSAOtherPrimeInfoArr) {
            ArrayList arrayList = new ArrayList();
            if (rSAOtherPrimeInfoArr == null) {
                return arrayList;
            }
            for (RSAOtherPrimeInfo otherPrimesInfo : rSAOtherPrimeInfoArr) {
                arrayList.add(new OtherPrimesInfo(otherPrimesInfo));
            }
            return arrayList;
        }

        public Base64URL getFactorCRTCoefficient() {
            return this.f1368t;
        }

        public Base64URL getFactorCRTExponent() {
            return this.f1366d;
        }

        public Base64URL getPrimeFactor() {
            return this.f1367r;
        }

        public OtherPrimesInfo(RSAOtherPrimeInfo rSAOtherPrimeInfo) {
            this.f1367r = Base64URL.encode(rSAOtherPrimeInfo.getPrime());
            this.f1366d = Base64URL.encode(rSAOtherPrimeInfo.getExponent());
            this.f1368t = Base64URL.encode(rSAOtherPrimeInfo.getCrtCoefficient());
        }
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, Base64URL base64URL10, List<Base64> list2) {
        this(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL9, base64URL10, list2, (KeyStore) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RSAKey(com.nimbusds.jose.util.Base64URL r17, com.nimbusds.jose.util.Base64URL r18, com.nimbusds.jose.util.Base64URL r19, com.nimbusds.jose.util.Base64URL r20, com.nimbusds.jose.util.Base64URL r21, com.nimbusds.jose.util.Base64URL r22, com.nimbusds.jose.util.Base64URL r23, com.nimbusds.jose.util.Base64URL r24, java.util.List<com.nimbusds.jose.jwk.RSAKey.OtherPrimesInfo> r25, java.security.PrivateKey r26, com.nimbusds.jose.jwk.KeyUse r27, java.util.Set<com.nimbusds.jose.jwk.KeyOperation> r28, com.nimbusds.jose.Algorithm r29, java.lang.String r30, java.net.URI r31, com.nimbusds.jose.util.Base64URL r32, com.nimbusds.jose.util.Base64URL r33, java.util.List<com.nimbusds.jose.util.Base64> r34, java.security.KeyStore r35) {
        /*
            r16 = this;
            r11 = r16
            r12 = r17
            r13 = r18
            r14 = r20
            r15 = r21
            r10 = r22
            r9 = r23
            r8 = r24
            com.nimbusds.jose.jwk.KeyType r1 = com.nimbusds.jose.jwk.KeyType.RSA
            r0 = r16
            r2 = r27
            r3 = r28
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            r8 = r33
            r9 = r34
            r10 = r35
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r12 == 0) goto L_0x00fc
            r11.f1362n = r12
            if (r13 == 0) goto L_0x00f4
            r11.f1361e = r13
            java.util.List r0 = r16.getParsedX509CertChain()
            if (r0 == 0) goto L_0x0051
            java.util.List r0 = r16.getParsedX509CertChain()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            boolean r0 = r11.matches(r0)
            if (r0 == 0) goto L_0x0049
            goto L_0x0051
        L_0x0049:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The public subject key info of the first X.509 certificate in the chain must match the JWK type and public parameters"
            r0.<init>(r1)
            throw r0
        L_0x0051:
            r0 = r19
            r11.f1358d = r0
            if (r14 == 0) goto L_0x0081
            if (r15 == 0) goto L_0x0081
            r0 = r22
            r1 = r23
            if (r0 == 0) goto L_0x0085
            r2 = r24
            if (r1 == 0) goto L_0x0087
            if (r2 == 0) goto L_0x0087
            r11.f1363p = r14
            r11.f1364q = r15
            r11.f1359dp = r0
            r11.f1360dq = r1
            r11.f1365qi = r2
            if (r25 == 0) goto L_0x0078
            java.util.List r0 = java.util.Collections.unmodifiableList(r25)
            r11.oth = r0
            goto L_0x007e
        L_0x0078:
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
        L_0x007e:
            r0 = r26
            goto L_0x00c1
        L_0x0081:
            r0 = r22
            r1 = r23
        L_0x0085:
            r2 = r24
        L_0x0087:
            r3 = 0
            if (r14 != 0) goto L_0x00a5
            if (r15 != 0) goto L_0x00a5
            if (r0 != 0) goto L_0x00a5
            if (r1 != 0) goto L_0x00a5
            if (r2 != 0) goto L_0x00a5
            if (r25 != 0) goto L_0x00a5
            r11.f1363p = r3
            r11.f1364q = r3
            r11.f1359dp = r3
            r11.f1360dq = r3
            r11.f1365qi = r3
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
            goto L_0x007e
        L_0x00a5:
            if (r14 != 0) goto L_0x00c4
            if (r15 != 0) goto L_0x00c4
            if (r0 != 0) goto L_0x00c4
            if (r1 != 0) goto L_0x00c4
            if (r2 == 0) goto L_0x00b0
            goto L_0x00c4
        L_0x00b0:
            r11.f1363p = r3
            r11.f1364q = r3
            r11.f1359dp = r3
            r11.f1360dq = r3
            r11.f1365qi = r3
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
            goto L_0x007e
        L_0x00c1:
            r11.privateKey = r0
            return
        L_0x00c4:
            if (r14 == 0) goto L_0x00ec
            if (r15 == 0) goto L_0x00e4
            if (r0 == 0) goto L_0x00dc
            if (r1 != 0) goto L_0x00d4
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The second factor CRT exponent must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00d4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first CRT coefficient must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00dc:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first factor CRT exponent must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00e4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The second prime factor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00ec:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first prime factor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00f4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The public exponent value must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00fc:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The modulus value must not be null"
            r0.<init>(r1)
            goto L_0x0105
        L_0x0104:
            throw r0
        L_0x0105:
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.jwk.RSAKey.<init>(com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.security.PrivateKey, com.nimbusds.jose.jwk.KeyUse, java.util.Set, com.nimbusds.jose.Algorithm, java.lang.String, java.net.URI, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.security.KeyStore):void");
    }

    public static RSAKey parse(X509Certificate x509Certificate) throws JOSEException {
        if (x509Certificate.getPublicKey() instanceof RSAPublicKey) {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) x509Certificate.getPublicKey();
            try {
                MessageDigest instance = MessageDigest.getInstance(PkceChallenge.DIGEST_ALGORITHM);
                C0357a aVar = new C0357a(rSAPublicKey);
                aVar.mo4709a(KeyUse.from(x509Certificate));
                aVar.mo4711a(x509Certificate.getSerialNumber().toString(10));
                aVar.mo4715a((List<Base64>) Collections.singletonList(Base64.encode(x509Certificate.getEncoded())));
                aVar.mo4710a(Base64URL.encode(instance.digest(x509Certificate.getEncoded())));
                return aVar.mo4716a();
            } catch (NoSuchAlgorithmException e) {
                throw new JOSEException("Couldn't encode x5t parameter: " + e.getMessage(), e);
            } catch (CertificateEncodingException e2) {
                throw new JOSEException("Couldn't encode x5c parameter: " + e2.getMessage(), e2);
            }
        } else {
            throw new JOSEException("The public key of the X.509 certificate is not RSA");
        }
    }

    public RSAKey(RSAPublicKey rSAPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateKey rSAPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAPrivateKey.getPrivateExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateCrtKey rSAPrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAPrivateCrtKey.getPrivateExponent()), Base64URL.encode(rSAPrivateCrtKey.getPrimeP()), Base64URL.encode(rSAPrivateCrtKey.getPrimeQ()), Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentP()), Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentQ()), Base64URL.encode(rSAPrivateCrtKey.getCrtCoefficient()), (List<OtherPrimesInfo>) null, (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeP()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeQ()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient()), OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo()), (PrivateKey) null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, PrivateKey privateKey2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (Base64URL) null, (List<OtherPrimesInfo>) null, privateKey2, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }
}
