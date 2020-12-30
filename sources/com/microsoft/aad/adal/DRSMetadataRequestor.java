package com.microsoft.aad.adal;

import com.google.gson.JsonSyntaxException;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

/* compiled from: PG */
public final class DRSMetadataRequestor extends AbstractMetadataRequestor<DRSMetadata, String> {
    public static final String CLOUD_RESOLVER_DOMAIN = "windows.net/";
    public static final String DRS_URL_PREFIX = "https://enterpriseregistration.";
    public static final String TAG = "DRSMetadataRequestor";

    /* compiled from: PG */
    public enum Type {
        ON_PREM,
        CLOUD
    }

    private DRSMetadata requestCloud(String str) throws AuthenticationException {
        Logger.m1250v(TAG, "Requesting DRS discovery (cloud)");
        try {
            return requestDrsDiscoveryInternal(Type.CLOUD, str);
        } catch (UnknownHostException unused) {
            throw new AuthenticationException(ADALError.DRS_DISCOVERY_FAILED_UNKNOWN_HOST);
        }
    }

    private DRSMetadata requestDrsDiscoveryInternal(Type type, String str) throws AuthenticationException, UnknownHostException {
        try {
            URL url = new URL(buildRequestUrlByType(type, str));
            HashMap e = Eo.e(WebRequestHandler.HEADER_ACCEPT, WebRequestHandler.HEADER_ACCEPT_JSON);
            if (getCorrelationId() != null) {
                e.put(MicrosoftTokenRequest.CORRELATION_ID, getCorrelationId().toString());
            }
            try {
                HttpWebResponse sendGet = getWebrequestHandler().sendGet(url, e);
                int statusCode = sendGet.getStatusCode();
                if (200 == statusCode) {
                    return parseMetadata(sendGet);
                }
                ADALError aDALError = ADALError.DRS_FAILED_SERVER_ERROR;
                throw new AuthenticationException(aDALError, "Unexpected error code: [" + statusCode + "]");
            } catch (UnknownHostException e2) {
                throw e2;
            } catch (IOException unused) {
                throw new AuthenticationException(ADALError.IO_EXCEPTION);
            }
        } catch (MalformedURLException unused2) {
            throw new AuthenticationException(ADALError.DRS_METADATA_URL_INVALID);
        }
    }

    private DRSMetadata requestOnPrem(String str) throws UnknownHostException, AuthenticationException {
        Logger.m1250v(TAG, "Requesting DRS discovery (on-prem)");
        return requestDrsDiscoveryInternal(Type.ON_PREM, str);
    }

    public String buildRequestUrlByType(Type type, String str) {
        StringBuilder sb = new StringBuilder(DRS_URL_PREFIX);
        if (Type.CLOUD == type) {
            sb.append(CLOUD_RESOLVER_DOMAIN);
            sb.append(str);
        } else if (Type.ON_PREM == type) {
            sb.append(str);
        }
        sb.append("/enrollmentserver/contract?api-version=1.0");
        String sb2 = sb.toString();
        String str2 = TAG;
        Logger.m1251v(str2, "Request will use DRS url. ", "URL: " + sb2, (ADALError) null);
        return sb2;
    }

    public DRSMetadata parseMetadata(HttpWebResponse httpWebResponse) throws AuthenticationException {
        Logger.m1250v(TAG, "Parsing DRS metadata response");
        try {
            return (DRSMetadata) parser().mo2097a(httpWebResponse.getBody(), DRSMetadata.class);
        } catch (JsonSyntaxException unused) {
            throw new AuthenticationException(ADALError.JSON_PARSE_ERROR);
        }
    }

    public DRSMetadata requestMetadata(String str) throws AuthenticationException {
        try {
            return requestOnPrem(str);
        } catch (UnknownHostException unused) {
            return requestCloud(str);
        }
    }
}
