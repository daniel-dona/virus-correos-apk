package com.citrix.mvpn.p002b;

import android.os.Handler;
import android.os.Message;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.sdk.logging.api.LoggingAPI;

/* renamed from: com.citrix.mvpn.b.f */
/* compiled from: PG */
public class C0033f extends C0029c {

    /* renamed from: a */
    public static C0033f f150a;

    /* renamed from: a */
    public static synchronized C0033f m73a() {
        C0033f fVar;
        synchronized (C0033f.class) {
            if (f150a == null) {
                f150a = new C0033f();
            }
            fVar = f150a;
        }
        return fVar;
    }

    /* renamed from: b */
    public static Message m74b(C0027a aVar) {
        LoggingAPI loggingAPI = C0051c.f218b;
        StringBuilder a = Eo.a("Start Tunnel Status = ");
        a.append(aVar.mo166c().getValue());
        loggingAPI.info("MVPN-StartTunnelEvent", a.toString());
        return Message.obtain((Handler) null, aVar.mo166c().getValue());
    }

    /* renamed from: a */
    public void mo171a(C0027a aVar) {
        C0050b.m172a(aVar.mo164a(), aVar.mo165b());
        C0050b.m178a(m74b(aVar));
    }
}
