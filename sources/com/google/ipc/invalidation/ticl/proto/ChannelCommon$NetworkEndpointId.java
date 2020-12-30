package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class ChannelCommon$NetworkEndpointId extends ProtoWrapper {

    /* renamed from: c */
    public final long f1131c;

    /* renamed from: d */
    public final int f1132d;

    /* renamed from: e */
    public final sN f1133e;

    /* renamed from: f */
    public final boolean f1134f;

    /* compiled from: PG */
    public interface NetworkAddress {
        public static final int ANDROID = 113;
        public static final int LCS = 114;
        public static final int TEST = 1;
    }

    static {
        new ChannelCommon$NetworkEndpointId((Integer) null, (sN) null, (Boolean) null);
    }

    public ChannelCommon$NetworkEndpointId(Integer num, sN sNVar, Boolean bool) {
        int i = 1;
        if (num != null) {
            this.f1132d = num.intValue();
        } else {
            this.f1132d = 1;
            i = 0;
        }
        if (sNVar != null) {
            i |= 2;
            this.f1133e = sNVar;
        } else {
            this.f1133e = sN.c;
        }
        if (bool != null) {
            i |= 4;
            this.f1134f = bool.booleanValue();
        } else {
            this.f1134f = false;
        }
        this.f1131c = (long) i;
    }

    /* renamed from: a */
    public int mo2356a() {
        int a = ProtoWrapper.m1045a(this.f1131c);
        if (mo2360e()) {
            a = (a * 31) + this.f1132d;
        }
        if (mo2358c()) {
            a = (a * 31) + this.f1133e.hashCode();
        }
        return mo2359d() ? (a * 31) + ProtoWrapper.m1046a(this.f1134f) : a;
    }

    /* renamed from: c */
    public boolean mo2358c() {
        return (this.f1131c & 2) != 0;
    }

    /* renamed from: d */
    public boolean mo2359d() {
        return (this.f1131c & 4) != 0;
    }

    /* renamed from: e */
    public boolean mo2360e() {
        return (this.f1131c & 1) != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChannelCommon$NetworkEndpointId)) {
            return false;
        }
        ChannelCommon$NetworkEndpointId channelCommon$NetworkEndpointId = (ChannelCommon$NetworkEndpointId) obj;
        if (this.f1131c != channelCommon$NetworkEndpointId.f1131c || ((mo2360e() && this.f1132d != channelCommon$NetworkEndpointId.f1132d) || ((mo2358c() && !ProtoWrapper.m1053a((Object) this.f1133e, (Object) channelCommon$NetworkEndpointId.f1133e)) || (mo2359d() && this.f1134f != channelCommon$NetworkEndpointId.f1134f)))) {
            return false;
        }
        return true;
    }

    /* renamed from: f */
    public byte[] mo2362f() {
        kP kPVar = new kP();
        Boolean bool = null;
        kPVar.c = mo2360e() ? Integer.valueOf(this.f1132d) : null;
        kPVar.d = mo2358c() ? this.f1133e.a : null;
        if (mo2359d()) {
            bool = Boolean.valueOf(this.f1134f);
        }
        kPVar.e = bool;
        return xO.a(kPVar);
    }

    /* renamed from: a */
    public void mo2357a(AN an) {
        an.a.append("<NetworkEndpointId:");
        if (mo2360e()) {
            an.a.append(" network_address=");
            an.a.append(this.f1132d);
        }
        if (mo2358c()) {
            an.a.append(" client_address=");
            an.a(this.f1133e);
        }
        if (mo2359d()) {
            an.a.append(" is_offline=");
            an.a.append(this.f1134f);
        }
        an.a.append('>');
    }
}
