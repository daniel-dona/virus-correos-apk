package com.nimbusds.jose;

import com.microsoft.aad.adal.JWSBuilder;
import java.util.Collection;

/* compiled from: PG */
public final class JWSAlgorithm extends Algorithm {
    public static final JWSAlgorithm ES256 = new JWSAlgorithm("ES256", Requirement.RECOMMENDED);
    public static final JWSAlgorithm ES256K = new JWSAlgorithm("ES256K", Requirement.OPTIONAL);
    public static final JWSAlgorithm ES384 = new JWSAlgorithm("ES384", Requirement.OPTIONAL);
    public static final JWSAlgorithm ES512 = new JWSAlgorithm("ES512", Requirement.OPTIONAL);
    public static final JWSAlgorithm EdDSA = new JWSAlgorithm("EdDSA", Requirement.OPTIONAL);
    public static final JWSAlgorithm HS256 = new JWSAlgorithm("HS256", Requirement.REQUIRED);
    public static final JWSAlgorithm HS384 = new JWSAlgorithm("HS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm HS512 = new JWSAlgorithm("HS512", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS256 = new JWSAlgorithm("PS256", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS384 = new JWSAlgorithm("PS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS512 = new JWSAlgorithm("PS512", Requirement.OPTIONAL);
    public static final JWSAlgorithm RS256 = new JWSAlgorithm(JWSBuilder.JWS_HEADER_ALG, Requirement.RECOMMENDED);
    public static final JWSAlgorithm RS384 = new JWSAlgorithm("RS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm RS512 = new JWSAlgorithm("RS512", Requirement.OPTIONAL);
    public static final long serialVersionUID = 1;

    /* compiled from: PG */
    public static final class Family extends AlgorithmFamily<JWSAlgorithm> {

        /* renamed from: EC */
        public static final Family f1332EC = new Family(JWSAlgorithm.ES256, JWSAlgorithm.ES256K, JWSAlgorithm.ES384, JWSAlgorithm.ES512);

        /* renamed from: ED */
        public static final Family f1333ED = new Family(JWSAlgorithm.EdDSA);
        public static final Family HMAC_SHA = new Family(JWSAlgorithm.HS256, JWSAlgorithm.HS384, JWSAlgorithm.HS512);
        public static final Family RSA = new Family(JWSAlgorithm.RS256, JWSAlgorithm.RS384, JWSAlgorithm.RS512, JWSAlgorithm.PS256, JWSAlgorithm.PS384, JWSAlgorithm.PS512);
        public static final Family SIGNATURE = new Family((JWSAlgorithm[]) Xu0.a((JWSAlgorithm[]) RSA.toArray(new JWSAlgorithm[0]), new JWSAlgorithm[][]{(JWSAlgorithm[]) f1332EC.toArray(new JWSAlgorithm[0]), (JWSAlgorithm[]) f1333ED.toArray(new JWSAlgorithm[0])}));
        public static final long serialVersionUID = 1;

        public Family(JWSAlgorithm... jWSAlgorithmArr) {
            super(jWSAlgorithmArr);
        }

        public /* bridge */ /* synthetic */ boolean add(Algorithm algorithm) {
            return super.add(algorithm);
        }

        public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
            return super.addAll(collection);
        }

        public /* bridge */ /* synthetic */ boolean remove(Object obj) {
            return super.remove(obj);
        }

        public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
            return super.removeAll(collection);
        }

        public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
            return super.retainAll(collection);
        }
    }

    public JWSAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public static JWSAlgorithm parse(String str) {
        if (str.equals(HS256.getName())) {
            return HS256;
        }
        if (str.equals(HS384.getName())) {
            return HS384;
        }
        if (str.equals(HS512.getName())) {
            return HS512;
        }
        if (str.equals(RS256.getName())) {
            return RS256;
        }
        if (str.equals(RS384.getName())) {
            return RS384;
        }
        if (str.equals(RS512.getName())) {
            return RS512;
        }
        if (str.equals(ES256.getName())) {
            return ES256;
        }
        if (str.equals(ES256K.getName())) {
            return ES256K;
        }
        if (str.equals(ES384.getName())) {
            return ES384;
        }
        if (str.equals(ES512.getName())) {
            return ES512;
        }
        if (str.equals(PS256.getName())) {
            return PS256;
        }
        if (str.equals(PS384.getName())) {
            return PS384;
        }
        if (str.equals(PS512.getName())) {
            return PS512;
        }
        if (str.equals(EdDSA.getName())) {
            return EdDSA;
        }
        return new JWSAlgorithm(str);
    }

    public JWSAlgorithm(String str) {
        super(str, (Requirement) null);
    }
}
