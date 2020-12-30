package com.microsoft.aad.adal;

/* compiled from: PG */
public class AuthenticationCancelError extends AuthenticationException {
    public static final long serialVersionUID = 1;

    public AuthenticationCancelError() {
    }

    public AuthenticationCancelError(String str) {
        super(ADALError.AUTH_FAILED_CANCELLED, str);
    }
}
