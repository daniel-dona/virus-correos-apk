package com.google.ipc.invalidation.ticl;

import com.google.ipc.invalidation.external.client.SystemResources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class Statistics extends vN {

    /* renamed from: f */
    public static final Map<String, SentMessageType> f1121f = m1006a((Key[]) SentMessageType.values());

    /* renamed from: g */
    public static final Map<String, IncomingOperationType> f1122g = m1006a((Key[]) IncomingOperationType.values());

    /* renamed from: h */
    public static final Map<String, ReceivedMessageType> f1123h = m1006a((Key[]) ReceivedMessageType.values());

    /* renamed from: i */
    public static final Map<String, ListenerEventType> f1124i = m1006a((Key[]) ListenerEventType.values());

    /* renamed from: j */
    public static final Map<String, ClientErrorType> f1125j = m1006a((Key[]) ClientErrorType.values());

    /* renamed from: a */
    public final Map<SentMessageType, Integer> f1126a = new HashMap();

    /* renamed from: b */
    public final Map<ReceivedMessageType, Integer> f1127b = new HashMap();

    /* renamed from: c */
    public final Map<IncomingOperationType, Integer> f1128c = new HashMap();

    /* renamed from: d */
    public final Map<ListenerEventType, Integer> f1129d = new HashMap();

    /* renamed from: e */
    public final Map<ClientErrorType, Integer> f1130e = new HashMap();

    /* compiled from: PG */
    public enum ClientErrorType {
        ACKNOWLEDGE_HANDLE_FAILURE,
        INCOMING_MESSAGE_FAILURE,
        OUTGOING_MESSAGE_FAILURE,
        PERSISTENT_DESERIALIZATION_FAILURE,
        PERSISTENT_READ_FAILURE,
        PERSISTENT_WRITE_FAILURE,
        PROTOCOL_VERSION_FAILURE,
        REGISTRATION_DISCREPANCY,
        NONCE_MISMATCH,
        TOKEN_MISMATCH,
        TOKEN_MISSING_FAILURE,
        TOKEN_TRANSIENT_FAILURE
    }

    /* compiled from: PG */
    public enum IncomingOperationType {
        ACKNOWLEDGE,
        REGISTRATION,
        UNREGISTRATION
    }

    /* compiled from: PG */
    public enum ListenerEventType {
        INFORM_ERROR,
        INFORM_REGISTRATION_FAILURE,
        INFORM_REGISTRATION_STATUS,
        INVALIDATE,
        INVALIDATE_ALL,
        INVALIDATE_UNKNOWN,
        REISSUE_REGISTRATIONS
    }

    /* compiled from: PG */
    public enum ReceivedMessageType {
        INFO_REQUEST,
        INVALIDATION,
        REGISTRATION_STATUS,
        REGISTRATION_SYNC_REQUEST,
        TOKEN_CONTROL,
        ERROR,
        CONFIG_CHANGE,
        STALE_INVALIDATION,
        TOTAL
    }

    /* compiled from: PG */
    public enum SentMessageType {
        INFO,
        INITIALIZE,
        INVALIDATION_ACK,
        REGISTRATION,
        REGISTRATION_SYNC,
        TOTAL
    }

    public Statistics() {
        m1010a(this.f1126a, (Key[]) SentMessageType.values());
        m1010a(this.f1127b, (Key[]) ReceivedMessageType.values());
        m1010a(this.f1128c, (Key[]) IncomingOperationType.values());
        m1010a(this.f1129d, (Key[]) ListenerEventType.values());
        m1010a(this.f1130e, (Key[]) ClientErrorType.values());
    }

    /* renamed from: a */
    public static <Key extends Enum<Key>> void m1007a(SystemResources.Logger logger, Map<String, Key> map, Map<Key, Integer> map2, String str, int i) {
        Enum enumR = (Enum) map.get(str);
        if (enumR != null) {
            map2.put(enumR, Integer.valueOf(map2.get(enumR).intValue() + i));
            return;
        }
        logger.c("Skipping unknown enum value name %s", new Object[]{str});
    }

    /* renamed from: a */
    public static <Key> void m1008a(Map<Key, Integer> map, Key key) {
        map.put(key, Integer.valueOf(map.get(key).intValue() + 1));
    }

    /* renamed from: a */
    public void mo2355a(List<mL<String, Integer>> list) {
        m1009a(this.f1126a, list, "SentMessageType");
        m1009a(this.f1127b, list, "ReceivedMessageType");
        m1009a(this.f1128c, list, "IncomingOperationType");
        m1009a(this.f1129d, list, "ListenerEventType");
        m1009a(this.f1130e, list, "ClientErrorType");
    }

    /* renamed from: a */
    public static <Key extends Enum<Key>> void m1009a(Map<Key, Integer> map, List<mL<String, Integer>> list, String str) {
        String a = Eo.a(str, ".");
        for (Map.Entry next : map.entrySet()) {
            if (((Integer) next.getValue()).intValue() > 0) {
                StringBuilder a2 = Eo.a(a);
                a2.append(((Enum) next.getKey()).name());
                list.add(new mL(a2.toString(), (Integer) next.getValue()));
            }
        }
    }

    /* renamed from: a */
    public static <Key extends Enum<Key>> Map<String, Key> m1006a(Key[] keyArr) {
        HashMap hashMap = new HashMap();
        for (Key key : keyArr) {
            hashMap.put(key.name(), key);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static <Key> void m1010a(Map<Key, Integer> map, Key[] keyArr) {
        for (Key put : keyArr) {
            map.put(put, 0);
        }
    }

    /* renamed from: a */
    public void mo2354a(AN an) {
        ArrayList arrayList = new ArrayList();
        mo2355a((List<mL<String, Integer>>) arrayList);
        an.b.a.format("Client Statistics: %s\n", new Object[]{arrayList});
    }

    /* renamed from: a */
    public mN mo2353a() {
        ArrayList<mL> arrayList = new ArrayList<>();
        mo2355a((List<mL<String, Integer>>) arrayList);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (mL mLVar : arrayList) {
            arrayList2.add(new RM((String) mLVar.a, (Integer) mLVar.b));
        }
        return new mN(arrayList2);
    }
}
