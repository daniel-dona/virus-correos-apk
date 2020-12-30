package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JWSAlgorithm;
import java.io.Serializable;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public final class Curve implements Serializable {
    public static final Curve Ed25519 = new Curve("Ed25519", "Ed25519", (String) null);
    public static final Curve Ed448 = new Curve("Ed448", "Ed448", (String) null);
    public static final Curve P_256 = new Curve("P-256", "secp256r1", "1.2.840.10045.3.1.7");
    public static final Curve P_256K = new Curve("P-256K", "secp256k1", "1.3.132.0.10");
    public static final Curve P_384 = new Curve("P-384", "secp384r1", "1.3.132.0.34");
    public static final Curve P_521 = new Curve("P-521", "secp521r1", "1.3.132.0.35");
    public static final Curve X25519 = new Curve("X25519", "X25519", (String) null);
    public static final Curve X448 = new Curve("X448", "X448", (String) null);
    public static final long serialVersionUID = 1;
    public final String name;
    public final String oid;
    public final String stdName;

    public Curve(String str) {
        this(str, (String) null, (String) null);
    }

    public static Curve forECParameterSpec(ECParameterSpec eCParameterSpec) {
        return Tu0.a(eCParameterSpec);
    }

    public static Set<Curve> forJWSAlgorithm(JWSAlgorithm jWSAlgorithm) {
        if (JWSAlgorithm.ES256.equals(jWSAlgorithm)) {
            return Collections.singleton(P_256);
        }
        if (JWSAlgorithm.ES256K.equals(jWSAlgorithm)) {
            return Collections.singleton(P_256K);
        }
        if (JWSAlgorithm.ES384.equals(jWSAlgorithm)) {
            return Collections.singleton(P_384);
        }
        if (JWSAlgorithm.ES512.equals(jWSAlgorithm)) {
            return Collections.singleton(P_521);
        }
        if (!JWSAlgorithm.EdDSA.equals(jWSAlgorithm)) {
            return null;
        }
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(new Curve[]{Ed25519, Ed448})));
    }

    public static Curve forOID(String str) {
        if (P_256.getOID().equals(str)) {
            return P_256;
        }
        if (P_256K.getOID().equals(str)) {
            return P_256K;
        }
        if (P_384.getOID().equals(str)) {
            return P_384;
        }
        if (P_521.getOID().equals(str)) {
            return P_521;
        }
        return null;
    }

    public static Curve forStdName(String str) {
        if ("secp256r1".equals(str) || "prime256v1".equals(str)) {
            return P_256;
        }
        if ("secp256k1".equals(str)) {
            return P_256K;
        }
        if ("secp384r1".equals(str)) {
            return P_384;
        }
        if ("secp521r1".equals(str)) {
            return P_521;
        }
        if (Ed25519.getStdName().equals(str)) {
            return Ed25519;
        }
        if (Ed448.getStdName().equals(str)) {
            return Ed448;
        }
        if (X25519.getStdName().equals(str)) {
            return X25519;
        }
        if (X448.getStdName().equals(str)) {
            return X448;
        }
        return null;
    }

    public static Curve parse(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("The cryptographic curve string must not be null or empty");
        } else if (str.equals(P_256.getName())) {
            return P_256;
        } else {
            if (str.equals(P_256K.getName())) {
                return P_256K;
            }
            if (str.equals(P_384.getName())) {
                return P_384;
            }
            if (str.equals(P_521.getName())) {
                return P_521;
            }
            if (str.equals(Ed25519.getName())) {
                return Ed25519;
            }
            if (str.equals(Ed448.getName())) {
                return Ed448;
            }
            if (str.equals(X25519.getName())) {
                return X25519;
            }
            if (str.equals(X448.getName())) {
                return X448;
            }
            return new Curve(str);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof Curve) && toString().equals(obj.toString());
    }

    public String getName() {
        return this.name;
    }

    public String getOID() {
        return this.oid;
    }

    public String getStdName() {
        return this.stdName;
    }

    public ECParameterSpec toECParameterSpec() {
        return Tu0.a(this);
    }

    public String toString() {
        return getName();
    }

    public Curve(String str, String str2, String str3) {
        if (str != null) {
            this.name = str;
            this.stdName = str2;
            this.oid = str3;
            return;
        }
        throw new IllegalArgumentException("The JOSE cryptographic curve name must not be null");
    }
}
