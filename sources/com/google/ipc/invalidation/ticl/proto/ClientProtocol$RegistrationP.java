package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class ClientProtocol$RegistrationP extends ProtoWrapper {

    /* renamed from: c */
    public final QM f1145c;

    /* renamed from: d */
    public final int f1146d;

    /* compiled from: PG */
    public interface OpType {
        public static final int REGISTER = 1;
        public static final int UNREGISTER = 2;
    }

    public ClientProtocol$RegistrationP(QM qm, Integer num) throws ProtoWrapper.ValidationArgumentException {
        ProtoWrapper.m1050a("object_id", (Object) qm);
        this.f1145c = qm;
        ProtoWrapper.m1050a("op_type", (Object) num);
        this.f1146d = num.intValue();
    }

    /* renamed from: a */
    public void mo2374a(AN an) {
        an.a.append("<RegistrationP:");
        an.a.append(" object_id=");
        an.a(this.f1145c);
        an.a.append(" op_type=");
        an.a.append(this.f1146d);
        an.a.append('>');
    }

    /* renamed from: c */
    public IP mo2375c() {
        IP ip = new IP();
        ip.c = this.f1145c.c();
        ip.d = Integer.valueOf(this.f1146d);
        return ip;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientProtocol$RegistrationP)) {
            return false;
        }
        ClientProtocol$RegistrationP clientProtocol$RegistrationP = (ClientProtocol$RegistrationP) obj;
        if (!ProtoWrapper.m1053a((Object) this.f1145c, (Object) clientProtocol$RegistrationP.f1145c) || this.f1146d != clientProtocol$RegistrationP.f1146d) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static ClientProtocol$RegistrationP m1036a(QM qm, int i) {
        return new ClientProtocol$RegistrationP(qm, Integer.valueOf(i));
    }

    /* renamed from: a */
    public int mo2356a() {
        return ((this.f1145c.hashCode() + 31) * 31) + this.f1146d;
    }

    /* renamed from: a */
    public static ClientProtocol$RegistrationP m1035a(IP ip) {
        if (ip == null) {
            return null;
        }
        return new ClientProtocol$RegistrationP(QM.a(ip.c), ip.d);
    }
}
