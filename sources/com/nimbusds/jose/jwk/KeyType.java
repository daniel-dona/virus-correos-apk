package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.Requirement;
import java.io.Serializable;
import net.minidev.json.JSONObject;

/* compiled from: PG */
public final class KeyType implements mB0, Serializable {

    /* renamed from: EC */
    public static final KeyType f1351EC = new KeyType("EC", Requirement.RECOMMENDED);
    public static final KeyType OCT = new KeyType("oct", Requirement.OPTIONAL);
    public static final KeyType OKP = new KeyType("OKP", Requirement.OPTIONAL);
    public static final KeyType RSA = new KeyType("RSA", Requirement.REQUIRED);
    public static final long serialVersionUID = 1;
    public final Requirement requirement;
    public final String value;

    public KeyType(String str, Requirement requirement2) {
        if (str != null) {
            this.value = str;
            this.requirement = requirement2;
            return;
        }
        throw new IllegalArgumentException("The key type value must not be null");
    }

    public static KeyType forAlgorithm(Algorithm algorithm) {
        if (algorithm == null) {
            return null;
        }
        if (JWSAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWSAlgorithm.Family.f1332EC.contains(algorithm)) {
            return f1351EC;
        }
        if (JWSAlgorithm.Family.HMAC_SHA.contains(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWEAlgorithm.Family.ECDH_ES.contains(algorithm)) {
            return f1351EC;
        }
        if (JWEAlgorithm.DIR.equals(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.AES_GCM_KW.contains(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.AES_KW.contains(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.PBES2.contains(algorithm)) {
            return OCT;
        }
        if (JWSAlgorithm.Family.f1333ED.contains(algorithm)) {
            return OKP;
        }
        return null;
    }

    public static KeyType parse(String str) {
        if (str.equals(f1351EC.getValue())) {
            return f1351EC;
        }
        if (str.equals(RSA.getValue())) {
            return RSA;
        }
        if (str.equals(OCT.getValue())) {
            return OCT;
        }
        if (str.equals(OKP.getValue())) {
            return OKP;
        }
        return new KeyType(str, (Requirement) null);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof KeyType) && toString().equals(obj.toString());
    }

    public Requirement getRequirement() {
        return this.requirement;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.value) + '\"';
    }

    public String toString() {
        return this.value;
    }
}
