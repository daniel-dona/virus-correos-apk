package com.microsoft.aad.adal;

/* compiled from: PG */
public class DeserializationAuthenticationException extends AuthenticationException {
    public static final long serialVersionUID = 1;

    public DeserializationAuthenticationException(String str) {
        super(ADALError.INCOMPATIBLE_BLOB_VERSION, str);
    }
}
