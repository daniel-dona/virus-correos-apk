package com.citrix.mvpn.api;

import android.app.Activity;
import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.citrix.mvpn.exception.ClientConfigurationException;
import com.citrix.mvpn.exception.NetworkTunnelNotStartedException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.helper.C0053d;
import com.citrix.mvpn.p001a.C0021a;
import com.citrix.mvpn.p001a.C0023b;
import com.citrix.mvpn.p001a.C0025d;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class MicroVPNSDK {
    public static final String TAG = "MVPN-MicroVPNSDK";

    public MicroVPNSDK() {
        C0051c.f218b.debug(TAG, "Not to be instantiated externally.");
    }

    public static URLConnection createURLConnection(Context context, URL url) throws NetworkTunnelNotStartedException, ClientConfigurationException {
        C0051c.f218b.debug10(TAG, "Within createURLConnection()");
        if (!C0050b.m188e(context) || !C0050b.m190g(context)) {
            try {
                return url.openConnection();
            } catch (IOException e) {
                LoggingAPI loggingAPI = C0051c.f218b;
                StringBuilder a = Eo.a("Unable to open URLConnection:");
                a.append(e.getMessage());
                loggingAPI.error(TAG, a.toString());
                return null;
            }
        } else {
            C0051c.f218b.debug(TAG, "Enabling URL object for network tunnel");
            if (C0050b.m185b(context)) {
                return C0023b.m59a(url, context);
            }
            C0051c.f218b.error(TAG, C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
            throw new NetworkTunnelNotStartedException(C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
        }
    }

    public static Object enableOkHttpClientObjectForNetworkTunnel(Context context, Object obj) throws NetworkTunnelNotStartedException, ClientConfigurationException {
        if (!C0050b.m188e(context) || !C0050b.m190g(context)) {
            return obj;
        }
        C0051c.f218b.debug(TAG, "Enabling OkHttpClient object for network tunnel");
        if (C0050b.m185b(context)) {
            return C0021a.m51a(obj, context);
        }
        C0051c.f218b.error(TAG, C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
        throw new NetworkTunnelNotStartedException(C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
    }

    public static WebView enableWebViewObjectForNetworkTunnel(Context context, WebView webView) throws NetworkTunnelNotStartedException, ClientConfigurationException {
        return enableWebViewObjectForNetworkTunnel(context, webView, (WebViewClient) null);
    }

    public static WebView enableWebViewObjectForNetworkTunnel(Context context, WebView webView, WebViewClient webViewClient) throws NetworkTunnelNotStartedException, ClientConfigurationException {
        C0051c.f218b.debug10(TAG, "Enabling WebView object for network tunnel");
        if (!C0050b.m188e(context) || !C0050b.m190g(context)) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Returning original webview, isSDKAppMode = ");
            a.append(C0050b.m188e(context));
            a.append(" isWebSSO = ");
            a.append(C0050b.m190g(context));
            loggingAPI.debug10(TAG, a.toString());
            return webView;
        } else if (C0050b.m185b(context)) {
            return C0025d.m61a(webView, webViewClient, context);
        } else {
            C0051c.f218b.error(TAG, C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
            throw new NetworkTunnelNotStartedException(C0053d.m203a(context, wx0.TUNNEL_NOT_STARTED));
        }
    }

    public static X509Certificate getTunnelCertificate(Context context) {
        if (context != null) {
            C0051c.f218b.debug5(TAG, "Within getTunnelCertificate()");
            return C0050b.m194k(context);
        }
        throw new IllegalArgumentException("Context cannot be null.");
    }

    public static void initialize(Context context) {
        C0050b.m174a(context);
    }

    public static boolean isNetworkTunnelRunning(Context context) {
        C0051c.f218b.debug10(TAG, "Within isNetworkTunnelRunning()");
        return C0050b.m185b(context);
    }

    public static void startTunnel(Activity activity, Messenger messenger) {
        if (activity == null || messenger == null) {
            throw new IllegalArgumentException(C0053d.m203a(activity, wx0.MVPN_MISSING_ARGUMENTS));
        }
        C0050b.m175a((Context) activity, messenger);
    }

    public static void startTunnel(Context context, Messenger messenger) {
        if (context == null || messenger == null) {
            throw new IllegalArgumentException(C0053d.m203a(context, wx0.MVPN_MISSING_ARGUMENTS));
        }
        C0050b.m175a(context, messenger);
    }

    public static void startTunnel(Context context, Messenger messenger, List<Map<String, String>> list, String str) {
        if (context == null || messenger == null || list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0053d.m203a(context, wx0.MVPN_MISSING_ARGUMENTS));
        }
        C0050b.m176a(context, messenger, list, str);
    }

    public static boolean stopTunnel(Context context) {
        C0051c.f218b.debug5(TAG, "Within stopTunnel()");
        if (context != null) {
            return C0050b.m192i(context);
        }
        throw new IllegalArgumentException("Context cannot be null.");
    }
}
