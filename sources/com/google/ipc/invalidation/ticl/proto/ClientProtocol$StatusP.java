package com.google.ipc.invalidation.ticl.proto;

import com.citrix.loggersdk.BuildConfig;
import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class ClientProtocol$StatusP extends ProtoWrapper {

    /* renamed from: c */
    public final long f1147c;

    /* renamed from: d */
    public final int f1148d;

    /* renamed from: e */
    public final String f1149e;

    /* compiled from: PG */
    public interface Code {
        public static final int PERMANENT_FAILURE = 3;
        public static final int SUCCESS = 1;
        public static final int TRANSIENT_FAILURE = 2;
    }

    public ClientProtocol$StatusP(Integer num, String str) throws ProtoWrapper.ValidationArgumentException {
        int i;
        ProtoWrapper.m1050a("code", (Object) num);
        this.f1148d = num.intValue();
        if (str != null) {
            i = 1;
            this.f1149e = str;
        } else {
            this.f1149e = BuildConfig.FLAVOR;
            i = 0;
        }
        this.f1147c = (long) i;
    }

    /* renamed from: a */
    public void mo2377a(AN an) {
        an.a.append("<StatusP:");
        an.a.append(" code=");
        an.a.append(this.f1148d);
        if (mo2379d()) {
            an.a.append(" description=");
            an.a.append(this.f1149e);
        }
        an.a.append('>');
    }

    /* renamed from: c */
    public int mo2378c() {
        return this.f1148d;
    }

    /* renamed from: d */
    public boolean mo2379d() {
        return (this.f1147c & 1) != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientProtocol$StatusP)) {
            return false;
        }
        ClientProtocol$StatusP clientProtocol$StatusP = (ClientProtocol$StatusP) obj;
        if (this.f1147c == clientProtocol$StatusP.f1147c && this.f1148d == clientProtocol$StatusP.f1148d && (!mo2379d() || ProtoWrapper.m1053a((Object) this.f1149e, (Object) clientProtocol$StatusP.f1149e))) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public int mo2356a() {
        int a = (ProtoWrapper.m1045a(this.f1147c) * 31) + this.f1148d;
        return mo2379d() ? (a * 31) + this.f1149e.hashCode() : a;
    }

    /* renamed from: a */
    public static ClientProtocol$StatusP m1040a(RP rp) {
        if (rp == null) {
            return null;
        }
        return new ClientProtocol$StatusP(rp.c, rp.d);
    }
}
