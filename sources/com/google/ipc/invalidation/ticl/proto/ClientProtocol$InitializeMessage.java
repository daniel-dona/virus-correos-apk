package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class ClientProtocol$InitializeMessage extends ProtoWrapper {

    /* renamed from: c */
    public final int f1141c;

    /* renamed from: d */
    public final sN f1142d;

    /* renamed from: e */
    public final HM f1143e;

    /* renamed from: f */
    public final int f1144f;

    /* compiled from: PG */
    public interface DigestSerializationType {
        public static final int BYTE_BASED = 1;
        public static final int NUMBER_BASED = 2;
    }

    public ClientProtocol$InitializeMessage(Integer num, sN sNVar, HM hm, Integer num2) throws ProtoWrapper.ValidationArgumentException {
        ProtoWrapper.m1050a("client_type", (Object) num);
        ProtoWrapper.m1048a("client_type", num.intValue());
        this.f1141c = num.intValue();
        ProtoWrapper.m1050a("nonce", (Object) sNVar);
        this.f1142d = sNVar;
        ProtoWrapper.m1050a("application_client_id", (Object) hm);
        this.f1143e = hm;
        ProtoWrapper.m1050a("digest_serialization_type", (Object) num2);
        this.f1144f = num2.intValue();
    }

    /* renamed from: a */
    public void mo2371a(AN an) {
        an.a.append("<InitializeMessage:");
        an.a.append(" client_type=");
        an.a.append(this.f1141c);
        an.a.append(" nonce=");
        an.a(this.f1142d);
        an.a.append(" application_client_id=");
        an.a(this.f1143e);
        an.a.append(" digest_serialization_type=");
        an.a.append(this.f1144f);
        an.a.append('>');
    }

    /* renamed from: c */
    public zP mo2372c() {
        zP zPVar = new zP();
        zPVar.c = Integer.valueOf(this.f1141c);
        zPVar.d = this.f1142d.a;
        zPVar.e = this.f1143e.d();
        zPVar.k = Integer.valueOf(this.f1144f);
        return zPVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientProtocol$InitializeMessage)) {
            return false;
        }
        ClientProtocol$InitializeMessage clientProtocol$InitializeMessage = (ClientProtocol$InitializeMessage) obj;
        if (this.f1141c != clientProtocol$InitializeMessage.f1141c || !ProtoWrapper.m1053a((Object) this.f1142d, (Object) clientProtocol$InitializeMessage.f1142d) || !ProtoWrapper.m1053a((Object) this.f1143e, (Object) clientProtocol$InitializeMessage.f1143e) || this.f1144f != clientProtocol$InitializeMessage.f1144f) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public int mo2356a() {
        int hashCode = this.f1142d.hashCode();
        return ((this.f1143e.hashCode() + ((hashCode + ((this.f1141c + 31) * 31)) * 31)) * 31) + this.f1144f;
    }

    /* renamed from: a */
    public static ClientProtocol$InitializeMessage m1031a(zP zPVar) {
        if (zPVar == null) {
            return null;
        }
        return new ClientProtocol$InitializeMessage(zPVar.c, sN.a(zPVar.d), HM.a(zPVar.e), zPVar.k);
    }
}
