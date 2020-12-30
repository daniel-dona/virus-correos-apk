package com.microsoft.identity.common.internal.net;

import com.microsoft.identity.common.internal.util.StringUtil;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public final class HttpRequest {
    public static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    public static final int DEFAULT_READ_TIMEOUT = 30000;
    public static final String HOST = "Host";
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final int RETRY_TIME_WAITING_PERIOD_MSEC = 1000;
    public static final int STREAM_BUFFER_SIZE = 1024;
    public final byte[] mRequestContent;
    public final String mRequestContentType;
    public final Map<String, String> mRequestHeaders;
    public final String mRequestMethod;
    public final URL mRequestUrl;

    public HttpRequest(URL url, Map<String, String> map, String str) {
        this(url, map, str, (byte[]) null, (String) null);
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] cArr = new char[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read <= -1) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } finally {
            safeCloseStream(inputStream);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microsoft.identity.common.internal.net.HttpResponse executeHttpSend() throws java.io.IOException {
        /*
            r5 = this;
            java.net.HttpURLConnection r0 = r5.setupConnection()
            java.lang.String r1 = r5.mRequestMethod
            r0.setRequestMethod(r1)
            r1 = 1
            r0.setUseCaches(r1)
            byte[] r1 = r5.mRequestContent
            java.lang.String r2 = r5.mRequestContentType
            setRequestBody(r0, r1, r2)
            r1 = 0
            java.io.InputStream r1 = r0.getInputStream()     // Catch:{ SocketTimeoutException -> 0x003a, IOException -> 0x001c }
            goto L_0x0020
        L_0x001a:
            r0 = move-exception
            goto L_0x003c
        L_0x001c:
            java.io.InputStream r1 = r0.getErrorStream()     // Catch:{ all -> 0x001a }
        L_0x0020:
            int r2 = r0.getResponseCode()     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x0029
            java.lang.String r3 = ""
            goto L_0x002d
        L_0x0029:
            java.lang.String r3 = convertStreamToString(r1)     // Catch:{ all -> 0x001a }
        L_0x002d:
            com.microsoft.identity.common.internal.net.HttpResponse r4 = new com.microsoft.identity.common.internal.net.HttpResponse     // Catch:{ all -> 0x001a }
            java.util.Map r0 = r0.getHeaderFields()     // Catch:{ all -> 0x001a }
            r4.<init>(r2, r3, r0)     // Catch:{ all -> 0x001a }
            safeCloseStream(r1)
            return r4
        L_0x003a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001a }
        L_0x003c:
            safeCloseStream(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.net.HttpRequest.executeHttpSend():com.microsoft.identity.common.internal.net.HttpResponse");
    }

    public static boolean isRetryableError(int i) {
        return i == 500 || i == 504 || i == 503;
    }

    public static void safeCloseStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private HttpResponse send() throws IOException, SocketTimeoutException, UnknownServiceException {
        try {
            HttpResponse sendWithRetry = sendWithRetry();
            if (sendWithRetry == null || !isRetryableError(sendWithRetry.getStatusCode())) {
                return sendWithRetry;
            }
            throw new UnknownServiceException("Retry failed again with 500/503/504");
        } catch (SocketTimeoutException e) {
            throw e;
        }
    }

    public static HttpResponse sendGet(URL url, Map<String, String> map) throws IOException {
        return new HttpRequest(url, map, "GET").send();
    }

    public static HttpResponse sendPost(URL url, Map<String, String> map, byte[] bArr, String str) throws IOException {
        return new HttpRequest(url, map, "POST", bArr, str).send();
    }

    private HttpResponse sendWithRetry() throws IOException {
        try {
            HttpResponse executeHttpSend = executeHttpSend();
            if (!isRetryableError(executeHttpSend.getStatusCode())) {
                return executeHttpSend;
            }
            waitBeforeRetry();
            return executeHttpSend();
        } catch (SocketTimeoutException unused) {
            waitBeforeRetry();
            return executeHttpSend();
        }
    }

    public static void setRequestBody(HttpURLConnection httpURLConnection, byte[] bArr, String str) throws IOException {
        if (bArr != null) {
            httpURLConnection.setDoOutput(true);
            if (!StringUtil.isEmpty(str)) {
                httpURLConnection.setRequestProperty("Content-Type", str);
            }
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
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
        HttpURLConnection createHttpURLConnection = HttpUrlConnectionFactory.createHttpURLConnection(this.mRequestUrl);
        for (Map.Entry next : this.mRequestHeaders.entrySet()) {
            createHttpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
        }
        createHttpURLConnection.setConnectTimeout(30000);
        createHttpURLConnection.setReadTimeout(30000);
        createHttpURLConnection.setInstanceFollowRedirects(true);
        createHttpURLConnection.setUseCaches(false);
        createHttpURLConnection.setDoInput(true);
        return createHttpURLConnection;
    }

    private void waitBeforeRetry() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException unused) {
        }
    }

    public HttpRequest(URL url, Map<String, String> map, String str, byte[] bArr, String str2) {
        this.mRequestHeaders = new HashMap();
        this.mRequestUrl = url;
        this.mRequestHeaders.put(HOST, url.getAuthority());
        this.mRequestHeaders.putAll(map);
        this.mRequestMethod = str;
        this.mRequestContent = bArr;
        this.mRequestContentType = str2;
    }
}
