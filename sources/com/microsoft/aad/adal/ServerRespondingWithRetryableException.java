package com.microsoft.aad.adal;

import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;

/* compiled from: PG */
public class ServerRespondingWithRetryableException extends AuthenticationException {
    public static final long serialVersionUID = 1;

    public ServerRespondingWithRetryableException(String str) {
        super((ADALError) null, str);
    }

    public ServerRespondingWithRetryableException(String str, Throwable th) {
        super((ADALError) null, str, th);
    }

    public ServerRespondingWithRetryableException(String str, HttpWebResponse httpWebResponse) {
        super((ADALError) null, str, httpWebResponse);
    }
}
