package com.citrix.mvpn.api;

import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p001a.C0021a;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/* compiled from: PG */
public class ResponseValidator {
    public static final String TAG = "MVPN-ResponseValidator";

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r3 = r3.getResponseHeaders();
     */
    @android.annotation.TargetApi(21)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isNetScalerCookieExpired(android.webkit.WebResourceResponse r3) {
        /*
            int r0 = r3.getStatusCode()
            r1 = 403(0x193, float:5.65E-43)
            if (r0 == r1) goto L_0x0010
            int r0 = r3.getStatusCode()
            r1 = 401(0x191, float:5.62E-43)
            if (r0 != r1) goto L_0x0026
        L_0x0010:
            java.util.Map r3 = r3.getResponseHeaders()
            if (r3 == 0) goto L_0x0026
            java.lang.String r0 = "X-Citrix-Session-Expired"
            java.lang.Object r3 = r3.get(r0)
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            if (r3 == 0) goto L_0x0026
            r3 = 1
            goto L_0x0027
        L_0x0026:
            r3 = 0
        L_0x0027:
            com.citrix.sdk.logging.api.LoggingAPI r0 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "WebView Cookie Expired = "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MVPN-ResponseValidator"
            r0.debug5(r2, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.api.ResponseValidator.isNetScalerCookieExpired(android.webkit.WebResourceResponse):boolean");
    }

    public static boolean isNetScalerCookieExpired(Object obj) {
        boolean a = C0021a.m55a(obj);
        LoggingAPI loggingAPI = C0051c.f218b;
        loggingAPI.debug5(TAG, "OkHttp Cookie Expired = " + a);
        return a;
    }

    public static boolean isNetScalerCookieExpired(URLConnection uRLConnection) {
        boolean z = false;
        try {
            if (uRLConnection instanceof HttpURLConnection) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
                if ((httpURLConnection.getResponseCode() == 403 || httpURLConnection.getResponseCode() == 401) && Boolean.parseBoolean(httpURLConnection.getHeaderField("X-Citrix-Session-Expired"))) {
                    z = true;
                }
            }
        } catch (IOException e) {
            C0051c.f218b.error(TAG, e.getMessage(), e);
        }
        LoggingAPI loggingAPI = C0051c.f218b;
        loggingAPI.debug5(TAG, "URLConnection Cookie Expired = " + z);
        return z;
    }
}
