package com.microsoft.identity.common.internal.net;

import java.util.List;
import java.util.Map;

/* compiled from: PG */
public final class HttpResponse {
    public final String mResponseBody;
    public final Map<String, List<String>> mResponseHeaders;
    public final int mStatusCode;

    public HttpResponse(int i, String str, Map<String, List<String>> map) {
        this.mStatusCode = i;
        this.mResponseBody = str;
        this.mResponseHeaders = map;
    }

    public String getBody() {
        return this.mResponseBody;
    }

    public Map<String, List<String>> getHeaders() {
        return this.mResponseHeaders;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String toString() {
        StringBuilder a = Eo.a("HttpResponse{mStatusCode=");
        a.append(this.mStatusCode);
        a.append(", mResponseBody='");
        Eo.a(a, this.mResponseBody, '\'', ", mResponseHeaders=");
        a.append(this.mResponseHeaders);
        a.append('}');
        return a.toString();
    }
}
