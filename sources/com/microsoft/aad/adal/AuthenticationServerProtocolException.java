package com.microsoft.aad.adal;

/* compiled from: PG */
public class AuthenticationServerProtocolException extends AuthenticationException {
    public static final long serialVersionUID = 1;

    public AuthenticationServerProtocolException(String str) {
        super(ADALError.DEVICE_CHALLENGE_FAILURE, str);
    }
}
