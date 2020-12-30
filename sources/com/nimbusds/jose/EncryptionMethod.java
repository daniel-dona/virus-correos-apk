package com.nimbusds.jose;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public final class EncryptionMethod extends Algorithm {
    public static final EncryptionMethod A128CBC_HS256 = new EncryptionMethod("A128CBC-HS256", Requirement.REQUIRED, 256);
    public static final EncryptionMethod A128CBC_HS256_DEPRECATED = new EncryptionMethod("A128CBC+HS256", Requirement.OPTIONAL, 256);
    public static final EncryptionMethod A128GCM = new EncryptionMethod("A128GCM", Requirement.RECOMMENDED, ByteString.CONCATENATE_BY_COPY_SIZE);
    public static final EncryptionMethod A192CBC_HS384 = new EncryptionMethod("A192CBC-HS384", Requirement.OPTIONAL, 384);
    public static final EncryptionMethod A192GCM = new EncryptionMethod("A192GCM", Requirement.OPTIONAL, 192);
    public static final EncryptionMethod A256CBC_HS512 = new EncryptionMethod("A256CBC-HS512", Requirement.REQUIRED, 512);
    public static final EncryptionMethod A256CBC_HS512_DEPRECATED = new EncryptionMethod("A256CBC+HS512", Requirement.OPTIONAL, 512);
    public static final EncryptionMethod A256GCM = new EncryptionMethod("A256GCM", Requirement.RECOMMENDED, 256);
    public static final long serialVersionUID = 1;
    public final int cekBitLength;

    public EncryptionMethod(String str, Requirement requirement, int i) {
        super(str, requirement);
        this.cekBitLength = i;
    }

    public static EncryptionMethod parse(String str) {
        if (str.equals(A128CBC_HS256.getName())) {
            return A128CBC_HS256;
        }
        if (str.equals(A192CBC_HS384.getName())) {
            return A192CBC_HS384;
        }
        if (str.equals(A256CBC_HS512.getName())) {
            return A256CBC_HS512;
        }
        if (str.equals(A128GCM.getName())) {
            return A128GCM;
        }
        if (str.equals(A192GCM.getName())) {
            return A192GCM;
        }
        if (str.equals(A256GCM.getName())) {
            return A256GCM;
        }
        if (str.equals(A128CBC_HS256_DEPRECATED.getName())) {
            return A128CBC_HS256_DEPRECATED;
        }
        if (str.equals(A256CBC_HS512_DEPRECATED.getName())) {
            return A256CBC_HS512_DEPRECATED;
        }
        return new EncryptionMethod(str);
    }

    public int cekBitLength() {
        return this.cekBitLength;
    }

    public EncryptionMethod(String str, Requirement requirement) {
        this(str, requirement, 0);
    }

    public EncryptionMethod(String str) {
        this(str, (Requirement) null, 0);
    }
}
