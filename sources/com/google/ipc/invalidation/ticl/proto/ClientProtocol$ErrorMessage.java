package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class ClientProtocol$ErrorMessage extends ProtoWrapper {

    /* renamed from: c */
    public final int f1138c;

    /* renamed from: d */
    public final String f1139d;

    /* compiled from: PG */
    public interface Code {
        public static final int AUTH_FAILURE = 1;
        public static final int UNKNOWN_FAILURE = 10000;
    }

    public ClientProtocol$ErrorMessage(Integer num, String str) throws ProtoWrapper.ValidationArgumentException {
        ProtoWrapper.m1050a("code", (Object) num);
        this.f1138c = num.intValue();
        ProtoWrapper.m1050a("description", (Object) str);
        this.f1139d = str;
    }

    /* renamed from: a */
    public void mo2367a(AN an) {
        an.a.append("<ErrorMessage:");
        an.a.append(" code=");
        an.a.append(this.f1138c);
        an.a.append(" description=");
        an.a.append(this.f1139d);
        an.a.append('>');
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientProtocol$ErrorMessage)) {
            return false;
        }
        ClientProtocol$ErrorMessage clientProtocol$ErrorMessage = (ClientProtocol$ErrorMessage) obj;
        if (this.f1138c != clientProtocol$ErrorMessage.f1138c || !ProtoWrapper.m1053a((Object) this.f1139d, (Object) clientProtocol$ErrorMessage.f1139d)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public int mo2356a() {
        return this.f1139d.hashCode() + ((this.f1138c + 31) * 31);
    }

    /* renamed from: a */
    public static ClientProtocol$ErrorMessage m1025a(wP wPVar) {
        if (wPVar == null) {
            return null;
        }
        return new ClientProtocol$ErrorMessage(wPVar.c, wPVar.d);
    }
}
