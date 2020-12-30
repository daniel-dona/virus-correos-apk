package com.citrix.mvpn.p001a;

import android.content.Context;
import com.citrix.mvpn.MAM.Android.AuthSSO.b.f;
import com.citrix.mvpn.exception.ClientConfigurationException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;

/* renamed from: com.citrix.mvpn.a.b */
/* compiled from: PG */
public class C0023b {
    /* renamed from: a */
    public static URLConnection m59a(URL url, Context context) throws ClientConfigurationException {
        Proxy proxy;
        URLConnection uRLConnection;
        C0051c.f218b.debug5("MVPN-URLConnConfig", "Setting URLConnection proxy.");
        int b = f.b(context);
        Proxy proxy2 = Proxy.NO_PROXY;
        if (b != -1) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", b));
        } else {
            LoggingAPI loggingAPI = C0051c.f218b;
            loggingAPI.error("MVPN-URLConnConfig", "Invalid Port:" + b);
            proxy = proxy2;
        }
        try {
            uRLConnection = url.openConnection(proxy);
            if (uRLConnection instanceof HttpsURLConnection) {
                SSLContext instance = SSLContext.getInstance("TLSv1.2");
                instance.init((KeyManager[]) null, C0050b.m193j((Context) null), (SecureRandom) null);
                ((HttpsURLConnection) uRLConnection).setSSLSocketFactory(instance.getSocketFactory());
                ((HttpsURLConnection) uRLConnection).setHostnameVerifier(C0051c.f219c);
            }
            LoggingAPI loggingAPI2 = C0051c.f218b;
            loggingAPI2.debug("MVPN-URLConnConfig", "URLopenConnection opening Connection to " + url.getHost());
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            C0051c.f218b.error("MVPN-URLConnConfig", "Exception in setting URLConnection", e);
            try {
                uRLConnection = url.openConnection();
            } catch (IOException e2) {
                throw new ClientConfigurationException("Unable to create URLConnection", e2);
            }
        }
        m60a(uRLConnection, context);
        return uRLConnection;
    }

    /* renamed from: a */
    public static void m60a(URLConnection uRLConnection, Context context) {
        C0051c.f218b.debug5("MVPN-URLConnConfig", "Setting URLConnection user agent.");
        uRLConnection.setRequestProperty("User-Agent", f.a(context));
    }
}
