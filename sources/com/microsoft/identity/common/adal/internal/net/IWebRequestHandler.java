package com.microsoft.identity.common.adal.internal.net;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/* compiled from: PG */
public interface IWebRequestHandler {
    HttpWebResponse sendGet(URL url, Map<String, String> map) throws IOException;

    HttpWebResponse sendPost(URL url, Map<String, String> map, byte[] bArr, String str) throws IOException;

    void setClientVersion(String str);

    void setRequestCorrelationId(UUID uuid);
}
