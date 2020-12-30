package com.microsoft.identity.common.adal.internal.net;

import android.os.Build;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/* compiled from: PG */
public class WebRequestHandler implements IWebRequestHandler {
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_JSON = "application/json";
    public String mCurrentClientVersion = BuildConfig.FLAVOR;
    public UUID mRequestCorrelationId = null;

    private Map<String, String> updateHeaders(Map<String, String> map) {
        UUID uuid = this.mRequestCorrelationId;
        if (uuid != null) {
            map.put(MicrosoftTokenRequest.CORRELATION_ID, uuid.toString());
        }
        map.put("x-client-SKU", "Android");
        map.put("x-client-Ver", this.mCurrentClientVersion);
        map.put("x-client-OS", BuildConfig.FLAVOR + Build.VERSION.SDK_INT);
        map.put("x-client-DM", Build.MODEL);
        return map;
    }

    public HttpWebResponse sendGet(URL url, Map<String, String> map) throws IOException {
        return new HttpWebRequest(url, "GET", updateHeaders(map)).send();
    }

    public HttpWebResponse sendPost(URL url, Map<String, String> map, byte[] bArr, String str) throws IOException {
        return new HttpWebRequest(url, "POST", updateHeaders(map), bArr, str).send();
    }

    public void setClientVersion(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            this.mCurrentClientVersion = str;
        }
    }

    public void setRequestCorrelationId(UUID uuid) {
        this.mRequestCorrelationId = uuid;
    }
}
