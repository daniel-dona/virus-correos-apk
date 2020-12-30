package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;

/* compiled from: PG */
public final class Client$RunStateP extends ProtoWrapper {

    /* renamed from: e */
    public static final Client$RunStateP f1135e = new Client$RunStateP((Integer) null);

    /* renamed from: c */
    public final long f1136c;

    /* renamed from: d */
    public final int f1137d;

    /* compiled from: PG */
    public interface State {
        public static final int NOT_STARTED = 1;
        public static final int STARTED = 2;
        public static final int STOPPED = 3;
    }

    public Client$RunStateP(Integer num) {
        int i = 1;
        if (num != null) {
            this.f1137d = num.intValue();
        } else {
            this.f1137d = 1;
            i = 0;
        }
        this.f1136c = (long) i;
    }

    /* renamed from: a */
    public void mo2363a(AN an) {
        an.a.append("<RunStateP:");
        if (mo2364c()) {
            an.a.append(" state=");
            an.a.append(this.f1137d);
        }
        an.a.append('>');
    }

    /* renamed from: c */
    public boolean mo2364c() {
        return (this.f1136c & 1) != 0;
    }

    /* renamed from: d */
    public pP mo2365d() {
        pP pPVar = new pP();
        pPVar.c = mo2364c() ? Integer.valueOf(this.f1137d) : null;
        return pPVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Client$RunStateP)) {
            return false;
        }
        Client$RunStateP client$RunStateP = (Client$RunStateP) obj;
        if (this.f1136c != client$RunStateP.f1136c || (mo2364c() && this.f1137d != client$RunStateP.f1137d)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public int mo2356a() {
        int a = ProtoWrapper.m1045a(this.f1136c);
        return mo2364c() ? (a * 31) + this.f1137d : a;
    }

    /* renamed from: a */
    public static Client$RunStateP m1020a(pP pPVar) {
        if (pPVar == null) {
            return null;
        }
        return new Client$RunStateP(pPVar.c);
    }
}
