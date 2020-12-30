package com.microsoft.pal;

import com.microsoft.javahttp.JavaHttpRequest;
import com.microsoft.javahttp.JavaHttpResponse;

/* compiled from: PG */
public class PalClientProxy {
    public ul0 mClient;

    public PalClientProxy(ul0 ul0) {
        this.mClient = ul0;
    }

    public JavaHttpResponse sendHttpsRequest(JavaHttpRequest javaHttpRequest) {
        return this.mClient.a(javaHttpRequest);
    }
}
