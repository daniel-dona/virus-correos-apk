package com.citrix.mvpn.helper;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: PG */
public final /* synthetic */ class c$$Lambda$0 implements HostnameVerifier {
    public static final HostnameVerifier $instance = new c$$Lambda$0();

    public boolean verify(String str, SSLSession sSLSession) {
        return C0051c.f218b.debug("MVPN-Constants", "Verify using Citrix Hostname Verifier");
    }
}
