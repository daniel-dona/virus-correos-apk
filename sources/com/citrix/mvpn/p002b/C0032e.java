package com.citrix.mvpn.p002b;

import android.os.Handler;
import android.os.Message;
import com.citrix.mvpn.api.ResponseStatusCode;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p003c.C0040d;

/* renamed from: com.citrix.mvpn.b.e */
/* compiled from: PG */
public class C0032e extends C0029c {

    /* renamed from: a */
    public static C0032e f149a;

    /* renamed from: a */
    public static synchronized C0032e m71a() {
        C0032e eVar;
        synchronized (C0032e.class) {
            if (f149a == null) {
                f149a = new C0032e();
            }
            eVar = f149a;
        }
        return eVar;
    }

    /* renamed from: a */
    public void mo170a(C0027a aVar) {
        C0051c.f218b.info("MVPN-StartTunnelEvent", "Session Expired!!!");
        C0050b.m172a(aVar.mo164a(), aVar.mo165b());
        Message obtain = Message.obtain((Handler) null, ResponseStatusCode.SESSION_EXPIRED.getValue());
        C0040d.m98c();
        C0050b.m178a(obtain);
    }
}
