package com.citrix.mvpn.p002b;

import com.citrix.mvpn.helper.C0051c;
import com.citrix.sdk.logging.api.LoggingAPI;

/* renamed from: com.citrix.mvpn.b.d */
/* compiled from: PG */
public class C0030d {

    /* renamed from: com.citrix.mvpn.b.d$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C00311 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f148a = new int[C0028b.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        static {
            /*
                com.citrix.mvpn.b.b[] r0 = com.citrix.mvpn.p002b.C0028b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f148a = r0
                r0 = 1
                int[] r1 = f148a     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.citrix.mvpn.b.b r2 = com.citrix.mvpn.p002b.C0028b.START_TUNNEL     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = f148a     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.citrix.mvpn.b.b r2 = com.citrix.mvpn.p002b.C0028b.SESSION_EXPIRY     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2 = 2
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.p002b.C0030d.C00311.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static void m70a(C0028b bVar, C0027a aVar) {
        LoggingAPI loggingAPI = C0051c.f218b;
        StringBuilder a = Eo.a("Within dispatchEvent(): ");
        a.append(bVar.name());
        loggingAPI.debug5("MVPN-EventDispatcher", a.toString());
        int i = C00311.f148a[bVar.ordinal()];
        if (i == 1) {
            C0033f.m73a().mo171a(aVar);
        } else if (i == 2) {
            C0032e.m71a().mo170a(aVar);
        }
    }
}
