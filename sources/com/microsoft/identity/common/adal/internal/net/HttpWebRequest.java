package com.microsoft.identity.common.adal.internal.net;

import android.content.Context;
import android.os.Debug;
import com.microsoft.aad.adal.Oauth2;
import com.microsoft.identity.common.adal.internal.AuthenticationSettings;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.net.HttpRequest;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class HttpWebRequest {
    public static final int CONNECT_TIME_OUT = AuthenticationSettings.INSTANCE.getConnectTimeOut();
    public static final int DEBUG_SIMULATE_DELAY = 0;
    public static final int READ_TIME_OUT = AuthenticationSettings.INSTANCE.getReadTimeOut();
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public final byte[] mRequestContent;
    public final String mRequestContentType;
    public final Map<String, String> mRequestHeaders;
    public final String mRequestMethod;
    public final URL mUrl;

    public HttpWebRequest(URL url, String str, Map<String, String> map) {
        this(url, str, map, (byte[]) null, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String convertStreamToString(java.io.InputStream r3) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x002f }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x002f }
            r2.<init>(r3)     // Catch:{ all -> 0x002f }
            r1.<init>(r2)     // Catch:{ all -> 0x002f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002d }
            r3.<init>()     // Catch:{ all -> 0x002d }
        L_0x0010:
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0025
            int r2 = r3.length()     // Catch:{ all -> 0x002d }
            if (r2 <= 0) goto L_0x0021
            r2 = 10
            r3.append(r2)     // Catch:{ all -> 0x002d }
        L_0x0021:
            r3.append(r0)     // Catch:{ all -> 0x002d }
            goto L_0x0010
        L_0x0025:
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x002d }
            r1.close()
            return r3
        L_0x002d:
            r3 = move-exception
            goto L_0x0031
        L_0x002f:
            r3 = move-exception
            r1 = r0
        L_0x0031:
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            goto L_0x0038
        L_0x0037:
            throw r3
        L_0x0038:
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.adal.internal.net.HttpWebRequest.convertStreamToString(java.io.InputStream):java.lang.String");
    }

    public static void safeCloseStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void setRequestBody(HttpURLConnection httpURLConnection, byte[] bArr, String str) throws IOException {
        if (bArr != null) {
            httpURLConnection.setDoOutput(true);
            if (str != null && !str.isEmpty()) {
                httpURLConnection.setRequestProperty("Content-Type", str);
            }
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(bArr.length));
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            OutputStream outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
            } finally {
                safeCloseStream(outputStream);
            }
        }
    }

    private HttpURLConnection setupConnection() throws IOException {
        URL url = this.mUrl;
        if (url == null) {
            throw new IllegalArgumentException("requestURL");
        } else if (url.getProtocol().equalsIgnoreCase("http") || this.mUrl.getProtocol().equalsIgnoreCase(Oauth2.HTTPS_PROTOCOL_STRING)) {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection createHttpUrlConnection = HttpUrlConnectionFactory.createHttpUrlConnection(this.mUrl);
            createHttpUrlConnection.setConnectTimeout(CONNECT_TIME_OUT);
            for (Map.Entry next : this.mRequestHeaders.entrySet()) {
                createHttpUrlConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            createHttpUrlConnection.setReadTimeout(READ_TIME_OUT);
            createHttpUrlConnection.setInstanceFollowRedirects(true);
            createHttpUrlConnection.setUseCaches(false);
            createHttpUrlConnection.setRequestMethod(this.mRequestMethod);
            createHttpUrlConnection.setDoInput(true);
            setRequestBody(createHttpUrlConnection, this.mRequestContent, this.mRequestContentType);
            return createHttpUrlConnection;
        } else {
            throw new IllegalArgumentException("requestURL");
        }
    }

    public static void throwIfNetworkNotAvailable(Context context) throws ClientException {
        DefaultConnectionService defaultConnectionService = new DefaultConnectionService(context);
        if (defaultConnectionService.isConnectionAvailable()) {
            return;
        }
        if (defaultConnectionService.isNetworkDisabledFromOptimizations()) {
            throw new ClientException("device_network_not_available_doze_mode", "Connection is not available to refresh token because power optimization is enabled. And the device is in doze mode or the app is standby");
        }
        throw new ClientException(ClientException.DEVICE_NETWORK_NOT_AVAILABLE, "Connection is not available to refresh token");
    }

    public HttpWebResponse send() throws IOException {
        HttpURLConnection httpURLConnection = setupConnection();
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
            if (inputStream == null) {
                throw e;
            }
        } catch (Throwable th) {
            safeCloseStream(inputStream);
            throw th;
        }
        int responseCode = httpURLConnection.getResponseCode();
        String convertStreamToString = convertStreamToString(inputStream);
        Debug.isDebuggerConnected();
        HttpWebResponse httpWebResponse = new HttpWebResponse(responseCode, convertStreamToString, httpURLConnection.getHeaderFields());
        safeCloseStream(inputStream);
        return httpWebResponse;
    }

    public HttpWebRequest(URL url, String str, Map<String, String> map, byte[] bArr, String str2) {
        this.mUrl = url;
        this.mRequestMethod = str;
        this.mRequestHeaders = new HashMap();
        URL url2 = this.mUrl;
        if (url2 != null) {
            this.mRequestHeaders.put(HttpRequest.HOST, url2.getAuthority());
        }
        this.mRequestHeaders.putAll(map);
        this.mRequestContent = bArr;
        this.mRequestContentType = str2;
    }
}
