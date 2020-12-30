package com.google.ipc.invalidation.ticl.proto;

import com.google.ipc.invalidation.util.ProtoWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
public final class ClientProtocol$InfoRequestMessage extends ProtoWrapper {

    /* renamed from: c */
    public final List<Integer> f1140c;

    /* compiled from: PG */
    public interface InfoType {
        public static final int GET_PERFORMANCE_COUNTERS = 1;
    }

    public ClientProtocol$InfoRequestMessage(Collection<Integer> collection) throws ProtoWrapper.ValidationArgumentException {
        this.f1140c = ProtoWrapper.m1054b("info_type", collection);
    }

    /* renamed from: a */
    public void mo2369a(AN an) {
        an.a.append("<InfoRequestMessage:");
        an.a.append(" info_type=[");
        an.a(this.f1140c);
        an.a.append(']');
        an.a.append('>');
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientProtocol$InfoRequestMessage)) {
            return false;
        }
        return ProtoWrapper.m1053a((Object) this.f1140c, (Object) ((ClientProtocol$InfoRequestMessage) obj).f1140c);
    }

    /* renamed from: a */
    public int mo2356a() {
        return this.f1140c.hashCode() + 31;
    }

    /* renamed from: a */
    public static ClientProtocol$InfoRequestMessage m1028a(yP yPVar) {
        if (yPVar == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(yPVar.c.length);
        int i = 0;
        while (true) {
            int[] iArr = yPVar.c;
            if (i >= iArr.length) {
                return new ClientProtocol$InfoRequestMessage(arrayList);
            }
            arrayList.add(Integer.valueOf(iArr[i]));
            i++;
        }
    }
}
