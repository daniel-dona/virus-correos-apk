package com.citrix.mvpn.helper;

import android.content.UriMatcher;
import com.citrix.mvpn.p003c.C0042e;
import com.citrix.sdk.appcore.api.MamSdk;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.citrix.mvpn.helper.c */
/* compiled from: PG */
public class C0051c {

    /* renamed from: a */
    public static final UriMatcher f217a = new UriMatcher(-1);

    /* renamed from: b */
    public static final LoggingAPI f218b = MamSdk.getLogger();

    /* renamed from: c */
    public static final HostnameVerifier f219c = c$$Lambda$0.$instance;

    /* renamed from: d */
    public static final TrustManager[] f220d = {new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            C0051c.f218b.debug("MVPN-Constants", "Citrix checkClientTrusted");
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            C0051c.f218b.debug("MVPN-Constants", "Citrix checkServerTrusted");
        }

        public X509Certificate[] getAcceptedIssuers() {
            C0051c.f218b.debug("MVPN-Constants", "Citrix getAcceptedIssuers");
            return null;
        }
    }};

    /* renamed from: e */
    public static final C0042e f221e = new C0042e(0, (String) null, -1, false, true);

    /* renamed from: a */
    public static TrustManager[] m200a() {
        return f220d;
    }

    /* renamed from: b */
    public static C0042e m201b() {
        return f221e;
    }
}
