package com.microsoft.identity.common.adal.internal.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: PG */
public final class HttpUrlConnectionFactory {
    public static HttpURLConnection sMockedConnection;
    public static URL sMockedConnectionOpenUrl;

    public static HttpURLConnection createHttpUrlConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = sMockedConnection;
        if (httpURLConnection == null) {
            return (HttpURLConnection) url.openConnection();
        }
        sMockedConnectionOpenUrl = url;
        return httpURLConnection;
    }

    public static URL getMockedConnectionOpenUrl() {
        return sMockedConnectionOpenUrl;
    }

    public static void setMockedHttpUrlConnection(HttpURLConnection httpURLConnection) {
        sMockedConnection = httpURLConnection;
        if (httpURLConnection == null) {
            sMockedConnectionOpenUrl = null;
        }
    }
}
