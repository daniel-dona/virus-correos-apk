package com.microsoft.identity.common.internal.net.cache;

import android.net.http.HttpResponseCache;
import com.microsoft.identity.common.internal.logging.Logger;
import java.io.File;
import java.io.IOException;

/* compiled from: PG */
public class HttpCache {
    public static final long DEFAULT_HTTP_CACHE_CAPACITY_BYTES = 10485760;
    public static final String DEFAULT_HTTP_CACHE_NAME = "com.microsoft.identity.http-cache";
    public static final String TAG = "HttpCache";

    public static void flush() {
        HttpResponseCache installed = getInstalled();
        if (installed != null) {
            installed.flush();
            return;
        }
        Logger.warn(TAG + ":flush", "Unable to flush cache because none is installed.");
    }

    public static HttpResponseCache getInstalled() {
        return HttpResponseCache.getInstalled();
    }

    public static boolean initialize(File file, String str, long j) {
        try {
            HttpResponseCache.install(new File(file, str), j);
            return true;
        } catch (IOException e) {
            Logger.error(TAG + ":initialize (File, Filename, Capacity)", "HTTP Response cache installation failed.", e);
            return false;
        }
    }

    public static boolean initialize(File file) {
        return initialize(file, DEFAULT_HTTP_CACHE_NAME, DEFAULT_HTTP_CACHE_CAPACITY_BYTES);
    }
}
