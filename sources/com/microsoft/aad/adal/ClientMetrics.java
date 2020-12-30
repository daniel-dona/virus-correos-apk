package com.microsoft.aad.adal;

import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/* compiled from: PG */
public enum ClientMetrics {
    INSTANCE;
    
    public static final String CLIENT_METRICS_HEADER_LAST_ENDPOINT = "x-client-last-endpoint";
    public static final String CLIENT_METRICS_HEADER_LAST_ERROR = "x-client-last-error";
    public static final String CLIENT_METRICS_HEADER_LAST_REQUEST = "x-client-last-request";
    public static final String CLIENT_METRICS_HEADER_LAST_RESPONSE_TIME = "x-client-last-response-time";
    public boolean mIsPending;
    public UUID mLastCorrelationId;
    public String mLastEndpoint;
    public String mLastError;
    public long mLastResponseTime;
    public URL mQueryUrl;
    public long mStartTimeMillis;

    private void addClientMetricsHeadersToRequest(Map<String, String> map) {
        String str = this.mLastError;
        if (str != null) {
            map.put(CLIENT_METRICS_HEADER_LAST_ERROR, str);
        }
        UUID uuid = this.mLastCorrelationId;
        if (uuid != null) {
            map.put(CLIENT_METRICS_HEADER_LAST_REQUEST, uuid.toString());
        }
        map.put(CLIENT_METRICS_HEADER_LAST_RESPONSE_TIME, Long.toString(this.mLastResponseTime));
        map.put(CLIENT_METRICS_HEADER_LAST_ENDPOINT, this.mLastEndpoint);
    }

    public void beginClientMetricsRecord(URL url, UUID uuid, Map<String, String> map) {
        if (UrlExtensions.isADFSAuthority(url)) {
            this.mLastCorrelationId = null;
            return;
        }
        if (this.mIsPending) {
            addClientMetricsHeadersToRequest(map);
        }
        this.mStartTimeMillis = System.currentTimeMillis();
        this.mQueryUrl = url;
        this.mLastCorrelationId = uuid;
        this.mLastError = BuildConfig.FLAVOR;
        this.mIsPending = false;
    }

    public void endClientMetricsRecord(String str, UUID uuid) {
        if (!UrlExtensions.isADFSAuthority(this.mQueryUrl)) {
            this.mLastEndpoint = str;
            if (this.mStartTimeMillis != 0) {
                this.mLastResponseTime = System.currentTimeMillis() - this.mStartTimeMillis;
                this.mLastCorrelationId = uuid;
            }
            this.mIsPending = true;
        }
    }

    public void setLastError(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (str != null) {
            str2 = str.replaceAll("[\\[\\]]", str2);
        }
        this.mLastError = str2;
    }

    public void setLastErrorCodes(String[] strArr) {
        this.mLastError = strArr == null ? null : TextUtils.join(",", strArr);
    }
}
