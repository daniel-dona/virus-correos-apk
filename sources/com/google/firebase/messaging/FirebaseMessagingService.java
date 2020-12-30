package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.zzb;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/* compiled from: PG */
public class FirebaseMessagingService extends zzb {

    /* renamed from: k */
    public static final Queue<String> f926k = new ArrayDeque(10);

    /* renamed from: a */
    public static void m838a(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* renamed from: b */
    public static boolean m839b(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString("google.c.a.e"));
    }

    /* renamed from: a */
    public void mo2063a() {
    }

    /* renamed from: a */
    public void mo2064a(RemoteMessage remoteMessage) {
    }

    /* renamed from: a */
    public void mo2065a(String str) {
    }

    /* renamed from: a */
    public void mo2066a(String str, Exception exc) {
    }

    /* renamed from: b */
    public final Intent mo2036b(Intent intent) {
        return (Intent) bJ.a().d.poll();
    }

    /* renamed from: c */
    public final boolean mo2048c(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (!m839b(intent.getExtras())) {
            return true;
        }
        if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
            FirebaseApp d = FirebaseApp.m799d();
            d.mo1997b();
            AnalyticsConnector analyticsConnector = (AnalyticsConnector) d.f899d.get(AnalyticsConnector.class);
            if (analyticsConnector != null) {
                String stringExtra = intent.getStringExtra("google.c.a.c_id");
                analyticsConnector.mo2006a("fcm", "_ln", (Object) stringExtra);
                Bundle bundle = new Bundle();
                bundle.putString("source", "Firebase");
                bundle.putString("medium", "notification");
                bundle.putString("campaign", stringExtra);
                analyticsConnector.mo2005a("fcm", "_cmp", bundle);
            } else {
                Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
            }
        }
        HJ.a("_no", intent);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ce  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo2037d(android.content.Intent r12) {
        /*
            r11 = this;
            java.lang.String r0 = r12.getAction()
            if (r0 != 0) goto L_0x0008
            java.lang.String r0 = ""
        L_0x0008:
            int r1 = r0.hashCode()
            r2 = 75300319(0x47cfddf, float:2.973903E-36)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L_0x0024
            r2 = 366519424(0x15d8a480, float:8.750124E-26)
            if (r1 == r2) goto L_0x001a
            goto L_0x002e
        L_0x001a:
            java.lang.String r1 = "com.google.android.c2dm.intent.RECEIVE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002e
            r0 = 0
            goto L_0x002f
        L_0x0024:
            java.lang.String r1 = "com.google.firebase.messaging.NOTIFICATION_DISMISS"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002e
            r0 = 1
            goto L_0x002f
        L_0x002e:
            r0 = -1
        L_0x002f:
            if (r0 == 0) goto L_0x005d
            if (r0 == r5) goto L_0x004d
            java.lang.String r0 = "Unknown intent action: "
            java.lang.String r12 = r12.getAction()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            int r1 = r12.length()
            if (r1 == 0) goto L_0x0047
            r0.concat(r12)
            goto L_0x005c
        L_0x0047:
            java.lang.String r12 = new java.lang.String
            r12.<init>(r0)
            goto L_0x005c
        L_0x004d:
            android.os.Bundle r0 = r12.getExtras()
            boolean r0 = m839b((android.os.Bundle) r0)
            if (r0 == 0) goto L_0x005c
            java.lang.String r0 = "_nd"
            HJ.a(r0, r12)
        L_0x005c:
            return
        L_0x005d:
            java.lang.String r0 = "google.message_id"
            java.lang.String r1 = r12.getStringExtra(r0)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x006f
            r2 = 0
            com.google.android.gms.tasks.Task r2 = com.google.android.gms.tasks.Tasks.forResult(r2)
            goto L_0x0088
        L_0x006f:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            r2.putString(r0, r1)
            yJ r6 = yJ.a(r11)
            QI r7 = new QI
            int r8 = r6.a()
            r7.<init>(r8, r2)
            com.google.android.gms.tasks.Task r2 = r6.a(r7)
        L_0x0088:
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r7 = "FirebaseMessaging"
            r8 = 3
            if (r6 == 0) goto L_0x0092
            goto L_0x00cb
        L_0x0092:
            java.util.Queue<java.lang.String> r6 = f926k
            boolean r6 = r6.contains(r1)
            if (r6 == 0) goto L_0x00b7
            boolean r6 = android.util.Log.isLoggable(r7, r8)
            if (r6 == 0) goto L_0x00b5
            java.lang.String r6 = "Received duplicate message: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r9 = r1.length()
            if (r9 == 0) goto L_0x00b0
            r6.concat(r1)
            goto L_0x00b5
        L_0x00b0:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r6)
        L_0x00b5:
            r1 = 1
            goto L_0x00cc
        L_0x00b7:
            java.util.Queue<java.lang.String> r6 = f926k
            int r6 = r6.size()
            r9 = 10
            if (r6 < r9) goto L_0x00c6
            java.util.Queue<java.lang.String> r6 = f926k
            r6.remove()
        L_0x00c6:
            java.util.Queue<java.lang.String> r6 = f926k
            r6.add(r1)
        L_0x00cb:
            r1 = 0
        L_0x00cc:
            if (r1 != 0) goto L_0x01a3
            java.lang.String r1 = "message_type"
            java.lang.String r1 = r12.getStringExtra(r1)
            java.lang.String r6 = "gcm"
            if (r1 != 0) goto L_0x00d9
            r1 = r6
        L_0x00d9:
            int r9 = r1.hashCode()
            r10 = 2
            switch(r9) {
                case -2062414158: goto L_0x00fe;
                case 102161: goto L_0x00f6;
                case 814694033: goto L_0x00ec;
                case 814800675: goto L_0x00e2;
                default: goto L_0x00e1;
            }
        L_0x00e1:
            goto L_0x0107
        L_0x00e2:
            java.lang.String r6 = "send_event"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0107
            r3 = 2
            goto L_0x0107
        L_0x00ec:
            java.lang.String r6 = "send_error"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0107
            r3 = 3
            goto L_0x0107
        L_0x00f6:
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0107
            r3 = 0
            goto L_0x0107
        L_0x00fe:
            java.lang.String r6 = "deleted_messages"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0107
            r3 = 1
        L_0x0107:
            if (r3 == 0) goto L_0x014e
            if (r3 == r5) goto L_0x014a
            if (r3 == r10) goto L_0x0142
            if (r3 == r8) goto L_0x0127
            java.lang.String r12 = "Received message with unknown type: "
            int r0 = r1.length()
            if (r0 == 0) goto L_0x011c
            java.lang.String r12 = r12.concat(r1)
            goto L_0x0122
        L_0x011c:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r12)
            r12 = r0
        L_0x0122:
            android.util.Log.w(r7, r12)
            goto L_0x01a3
        L_0x0127:
            java.lang.String r0 = r12.getStringExtra(r0)
            if (r0 != 0) goto L_0x0133
            java.lang.String r0 = "message_id"
            java.lang.String r0 = r12.getStringExtra(r0)
        L_0x0133:
            com.google.firebase.messaging.SendException r1 = new com.google.firebase.messaging.SendException
            java.lang.String r3 = "error"
            java.lang.String r12 = r12.getStringExtra(r3)
            r1.<init>(r12)
            r11.mo2066a(r0, r1)
            goto L_0x01a3
        L_0x0142:
            java.lang.String r12 = r12.getStringExtra(r0)
            r11.mo2065a((java.lang.String) r12)
            goto L_0x01a3
        L_0x014a:
            r11.mo2063a()
            goto L_0x01a3
        L_0x014e:
            android.os.Bundle r0 = r12.getExtras()
            boolean r0 = m839b((android.os.Bundle) r0)
            if (r0 == 0) goto L_0x015d
            java.lang.String r0 = "_nr"
            HJ.a(r0, r12)
        L_0x015d:
            android.os.Bundle r0 = r12.getExtras()
            if (r0 != 0) goto L_0x0168
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
        L_0x0168:
            java.lang.String r1 = "android.support.content.wakelockid"
            r0.remove(r1)
            java.lang.String r1 = "gcm.n.e"
            java.lang.String r1 = GJ.b(r0, r1)
            java.lang.String r3 = "1"
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L_0x0183
            java.lang.String r1 = "gcm.n.icon"
            java.lang.String r1 = GJ.b(r0, r1)
            if (r1 == 0) goto L_0x0184
        L_0x0183:
            r4 = 1
        L_0x0184:
            if (r4 == 0) goto L_0x019b
            GJ r1 = GJ.a(r11)
            boolean r1 = r1.a(r0)
            if (r1 != 0) goto L_0x01a3
            boolean r1 = m839b((android.os.Bundle) r0)
            if (r1 == 0) goto L_0x019b
            java.lang.String r1 = "_nf"
            HJ.a(r1, r12)
        L_0x019b:
            com.google.firebase.messaging.RemoteMessage r12 = new com.google.firebase.messaging.RemoteMessage
            r12.<init>(r0)
            r11.mo2064a((com.google.firebase.messaging.RemoteMessage) r12)
        L_0x01a3:
            r0 = 1
            java.util.concurrent.TimeUnit r12 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ ExecutionException -> 0x01af, InterruptedException -> 0x01ad, TimeoutException -> 0x01ab }
            com.google.android.gms.tasks.Tasks.await(r2, r0, r12)     // Catch:{ ExecutionException -> 0x01af, InterruptedException -> 0x01ad, TimeoutException -> 0x01ab }
            return
        L_0x01ab:
            r12 = move-exception
            goto L_0x01b0
        L_0x01ad:
            r12 = move-exception
            goto L_0x01b0
        L_0x01af:
            r12 = move-exception
        L_0x01b0:
            java.lang.String r12 = java.lang.String.valueOf(r12)
            int r0 = r12.length()
            int r0 = r0 + 20
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Message ack failed: "
            r1.append(r0)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            android.util.Log.w(r7, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.mo2037d(android.content.Intent):void");
    }
}
