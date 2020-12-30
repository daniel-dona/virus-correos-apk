package com.microsoft.intune.mam.policy;

import com.microsoft.intune.mam.client.app.startup.ADALConnectionDetails;
import com.microsoft.intune.mam.client.identity.MAMIdentity;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public class MAMServiceLookupThread extends Thread {

    /* renamed from: n */
    public static final Ld0 f1267n = Md0.a(MAMServiceLookupThread.class);

    /* renamed from: a */
    public final C0347a f1268a;

    /* renamed from: b */
    public final Ud0 f1269b;

    /* renamed from: c */
    public final Callback f1270c;

    /* renamed from: d */
    public final Operations f1271d;

    /* renamed from: e */
    public boolean f1272e = false;

    /* renamed from: k */
    public boolean f1273k = false;

    /* compiled from: PG */
    public interface Callback {
        void onFailure(MAMEnrollmentManager.Result result, MAMWEError mAMWEError);

        void onSuccess(String str, Map<String, String> map, String str2, String str3);
    }

    /* compiled from: PG */
    public interface Operations {
        public static final long DEFAULT_REQUERY_INTERVAL_MS = TimeUnit.HOURS.toMillis(12);
        public static final long MAX_REQUERY_INTERVAL_MS = TimeUnit.DAYS.toMillis(7);
        public static final long MIN_REQUERY_INTERVAL_MS = TimeUnit.MINUTES.toMillis(30);

        void acquireToken(C0347a aVar);

        void getIsTargeted(C0347a aVar);

        HttpURLConnection getLastConnection();

        String getLastRequestId();

        void getLookupServiceUrl(C0347a aVar);

        void queryLookupService(C0347a aVar);
    }

    /* renamed from: com.microsoft.intune.mam.policy.MAMServiceLookupThread$a */
    /* compiled from: PG */
    public static class C0347a {

        /* renamed from: a */
        public final MAMIdentity f1274a;

        /* renamed from: b */
        public final String f1275b;

        /* renamed from: c */
        public final ADALConnectionDetails f1276c;

        /* renamed from: d */
        public String f1277d;

        /* renamed from: e */
        public String f1278e;

        /* renamed from: f */
        public String f1279f;

        /* renamed from: g */
        public Map<String, String> f1280g;

        /* renamed from: h */
        public Boolean f1281h;

        /* renamed from: i */
        public MAMWEError f1282i = MAMWEError.NONE_KNOWN;

        /* renamed from: j */
        public long f1283j;

        public C0347a(MAMIdentity mAMIdentity, String str, ADALConnectionDetails aDALConnectionDetails) {
            this.f1274a = mAMIdentity;
            this.f1275b = str;
            this.f1276c = aDALConnectionDetails;
            this.f1283j = Operations.DEFAULT_REQUERY_INTERVAL_MS;
        }

        /* renamed from: a */
        public String mo4385a() {
            Map<String, String> map = this.f1280g;
            if (map == null) {
                return null;
            }
            return map.get("mam.api.application");
        }
    }

    public MAMServiceLookupThread(MAMIdentity mAMIdentity, String str, ADALConnectionDetails aDALConnectionDetails, Ud0 ud0, Callback callback, Operations operations) {
        super("MAMServiceLookupThread");
        this.f1268a = new C0347a(mAMIdentity, str, aDALConnectionDetails);
        this.f1269b = ud0;
        this.f1270c = callback;
        this.f1271d = operations;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0175  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r11 = this;
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            com.microsoft.intune.mam.client.identity.MAMIdentity r0 = r0.f1274a
            java.lang.String r0 = r0.authority()
            boolean r0 = com.microsoft.intune.mam.http.KnownClouds.isSupported(r0)
            if (r0 != 0) goto L_0x0018
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Callback r0 = r11.f1270c
            com.microsoft.intune.mam.policy.MAMEnrollmentManager$Result r1 = com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result.NOT_LICENSED
            com.microsoft.intune.mam.policy.MAMWEError r2 = com.microsoft.intune.mam.policy.MAMWEError.NONE_KNOWN
            r0.onFailure(r1, r2)
            return
        L_0x0018:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r1 = r0.f1278e
            if (r1 != 0) goto L_0x0023
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Operations r1 = r11.f1271d
            r1.acquireToken(r0)
        L_0x0023:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r0 = r0.f1278e
            r1 = 1
            if (r0 == 0) goto L_0x002c
            r0 = 1
            goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            if (r0 != 0) goto L_0x003b
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Callback r0 = r11.f1270c
            com.microsoft.intune.mam.policy.MAMEnrollmentManager$Result r1 = com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result.AUTHORIZATION_NEEDED
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            com.microsoft.intune.mam.policy.MAMWEError r2 = r2.f1282i
            r0.onFailure(r1, r2)
            return
        L_0x003b:
            boolean r0 = r11.f1273k
            if (r0 != 0) goto L_0x019c
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r0 = r0.mo4385a()
            if (r0 == 0) goto L_0x0049
            goto L_0x0164
        L_0x0049:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            Ud0 r2 = r11.f1269b
            com.microsoft.intune.mam.client.identity.MAMIdentity r3 = r0.f1274a
            java.lang.String r3 = r3.canonicalUPN()
            Gc0 r2 = (Gc0) r2
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r4 = r2.a
            java.util.Map r4 = r4.getMAMServiceUrls()
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r5 = r2.a
            java.lang.String r5 = r5.getMAMServiceUrlIdentity()
            r6 = 0
            if (r4 == 0) goto L_0x0090
            boolean r7 = r4.isEmpty()
            if (r7 != 0) goto L_0x0090
            boolean r5 = r3.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x0090
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r2 = r2.a
            long r2 = r2.getMAMServiceUrlTimestamp()
            r7 = 0
            int r5 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0088
            long r7 = java.lang.System.currentTimeMillis()
            r9 = 1209600000(0x48190800, double:5.97621805E-315)
            long r2 = r2 + r9
            int r5 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r5 <= 0) goto L_0x009e
        L_0x0088:
            Ld0 r2 = Gc0.c
            java.lang.String r3 = "MAM Service URL found in cache, but data is stale; discarding."
            r2.c(r3)
            goto L_0x009d
        L_0x0090:
            Ld0 r4 = Gc0.c
            com.microsoft.intune.mam.log.MAMLogPIIFactory r2 = r2.b
            Qd0 r2 = r2.getPIIUPN((java.lang.String) r3)
            java.lang.String r3 = "No MAM Service URL found in the cache for user {0}"
            r4.a(r3, r2)
        L_0x009d:
            r4 = r6
        L_0x009e:
            r0.f1280g = r4
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r0 = r0.mo4385a()
            if (r0 == 0) goto L_0x00c2
            Ld0 r0 = f1267n
            java.lang.String r2 = "MAM Service URL retrieved from cache: "
            java.lang.StringBuilder r2 = Eo.a(r2)
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r3 = r11.f1268a
            java.lang.String r3 = r3.mo4385a()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.c(r2)
            goto L_0x015c
        L_0x00c2:
            Ud0 r0 = r11.f1269b
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            com.microsoft.intune.mam.client.identity.MAMIdentity r2 = r2.f1274a
            java.lang.String r2 = r2.canonicalUPN()
            Gc0 r0 = (Gc0) r0
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r3 = r0.a
            java.lang.String r3 = r3.getMAMServiceUrlIdentity()
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r4 = r0.a
            long r4 = r4.getMAMServiceUrlRequeryInterval()
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x00f1
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r0 = r0.a
            long r2 = r0.getMAMServiceUrlTimestamp()
            long r6 = java.lang.System.currentTimeMillis()
            long r2 = r2 + r4
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f1
            r0 = 0
            goto L_0x00f2
        L_0x00f1:
            r0 = 1
        L_0x00f2:
            if (r0 != 0) goto L_0x00fc
            Ld0 r0 = f1267n
            java.lang.String r2 = "Skipping lookup service query since insufficient time has passed since the last attempt."
            r0.e(r2)
            goto L_0x015c
        L_0x00fc:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Operations r0 = r11.f1271d
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            r0.getLookupServiceUrl(r2)
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r2 = r0.f1279f
            if (r2 != 0) goto L_0x010a
            goto L_0x015c
        L_0x010a:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Operations r2 = r11.f1271d
            r2.queryLookupService(r0)
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            com.microsoft.intune.mam.policy.MAMWEError r2 = r0.f1282i
            com.microsoft.intune.mam.policy.MAMWEError r3 = com.microsoft.intune.mam.policy.MAMWEError.NETWORK_ERROR
            if (r2 == r3) goto L_0x012d
            Ud0 r2 = r11.f1269b
            com.microsoft.intune.mam.client.identity.MAMIdentity r0 = r0.f1274a
            java.lang.String r0 = r0.canonicalUPN()
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r3 = r11.f1268a
            java.util.Map<java.lang.String, java.lang.String> r4 = r3.f1280g
            long r5 = r3.f1283j
            Gc0 r2 = (Gc0) r2
            com.microsoft.intune.mam.policy.MAMEnrollmentStatusCache r2 = r2.a
            r2.setMAMServiceUrls(r0, r4, r5)
            goto L_0x0134
        L_0x012d:
            Ld0 r0 = f1267n
            java.lang.String r2 = "Not updating MAMServiceURL time after network error"
            r0.c(r2)
        L_0x0134:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r0 = r0.mo4385a()
            if (r0 != 0) goto L_0x0144
            Ld0 r0 = f1267n
            java.lang.String r2 = "failed to get a MAM Service URL"
            r0.e(r2)
            goto L_0x015c
        L_0x0144:
            Ld0 r0 = f1267n
            java.lang.String r2 = "MAM Service URL: "
            java.lang.StringBuilder r2 = Eo.a(r2)
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r3 = r11.f1268a
            java.lang.String r3 = r3.mo4385a()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.c(r2)
        L_0x015c:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.String r0 = r0.mo4385a()
            if (r0 == 0) goto L_0x0166
        L_0x0164:
            r0 = 1
            goto L_0x0167
        L_0x0166:
            r0 = 0
        L_0x0167:
            if (r0 != 0) goto L_0x0175
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Callback r0 = r11.f1270c
            com.microsoft.intune.mam.policy.MAMEnrollmentManager$Result r1 = com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result.NOT_LICENSED
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            com.microsoft.intune.mam.policy.MAMWEError r2 = r2.f1282i
            r0.onFailure(r1, r2)
            return
        L_0x0175:
            boolean r0 = r11.f1272e
            if (r0 == 0) goto L_0x018e
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Operations r0 = r11.f1271d
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            r0.getIsTargeted(r2)
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r0 = r11.f1268a
            java.lang.Boolean r0 = r0.f1281h
            if (r0 == 0) goto L_0x018d
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x018d
            goto L_0x018e
        L_0x018d:
            r1 = 0
        L_0x018e:
            if (r1 != 0) goto L_0x019c
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Callback r0 = r11.f1270c
            com.microsoft.intune.mam.policy.MAMEnrollmentManager$Result r1 = com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result.NOT_LICENSED
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            com.microsoft.intune.mam.policy.MAMWEError r2 = r2.f1282i
            r0.onFailure(r1, r2)
            return
        L_0x019c:
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$Callback r0 = r11.f1270c
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r1 = r11.f1268a
            com.microsoft.intune.mam.client.identity.MAMIdentity r1 = r1.f1274a
            java.lang.String r1 = r1.canonicalUPN()
            com.microsoft.intune.mam.policy.MAMServiceLookupThread$a r2 = r11.f1268a
            java.util.Map<java.lang.String, java.lang.String> r3 = r2.f1280g
            java.lang.String r4 = r2.f1277d
            java.lang.String r2 = r2.f1278e
            r0.onSuccess(r1, r3, r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.intune.mam.policy.MAMServiceLookupThread.run():void");
    }
}
