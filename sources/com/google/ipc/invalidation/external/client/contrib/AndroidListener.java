package com.google.ipc.invalidation.external.client.contrib;

import KL;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.citrix.loggersdk.BuildConfig;
import com.google.ipc.invalidation.external.client.InvalidationListener;
import com.google.ipc.invalidation.external.client.SystemResources;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public abstract class AndroidListener extends IntentService {

    /* renamed from: e */
    public static final SystemResources.Logger f1109e = cL.a(BuildConfig.FLAVOR);

    /* renamed from: k */
    public static int f1110k = ((int) TimeUnit.SECONDS.toMillis(60));

    /* renamed from: n */
    public static int f1111n = 360;

    /* renamed from: p */
    public static sN f1112p;

    /* renamed from: a */
    public final InvalidationListener f1113a = new C0274a();

    /* renamed from: b */
    public fL f1114b;

    /* renamed from: c */
    public final KL f1115c = new KL.a();

    /* renamed from: d */
    public NL f1116d;

    /* compiled from: PG */
    public static final class AlarmReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (context == null) {
                throw new NullPointerException();
            } else if (intent == null) {
                throw new NullPointerException();
            } else if (intent.hasExtra("com.google.ipc.invalidation.android_listener.REGISTRATION") || intent.hasExtra("com.google.ipc.invalidation.android_listener.SCHEDULED_TASK")) {
                dL.a(context, intent);
            }
        }
    }

    public AndroidListener() {
        super(BuildConfig.FLAVOR);
        setIntentRedelivery(true);
    }

    /* renamed from: a */
    public void mo2304a() {
    }

    /* renamed from: a */
    public abstract void mo2305a(PendingIntent pendingIntent, String str);

    /* renamed from: a */
    public abstract void mo2306a(jL jLVar);

    /* renamed from: a */
    public abstract void mo2308a(kL kLVar, byte[] bArr);

    /* renamed from: a */
    public abstract void mo2309a(lL lLVar, byte[] bArr);

    /* renamed from: a */
    public void mo2311a(byte[] bArr, Iterable<lL> iterable) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (iterable != null) {
            Context applicationContext = getApplicationContext();
            if (applicationContext != null) {
                try {
                    applicationContext.startService(dL.a(applicationContext, sN.a(bArr), iterable, true));
                } catch (IllegalStateException e) {
                    f1109e.d("Unable to deliver `register` intent: %s", new Object[]{e});
                }
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public abstract void mo2312a(byte[] bArr, lL lLVar, InvalidationListener.RegistrationState registrationState);

    /* renamed from: a */
    public abstract void mo2313a(byte[] bArr, lL lLVar, boolean z, String str);

    /* renamed from: b */
    public abstract void mo2314b(byte[] bArr);

    /* renamed from: b */
    public void mo2315b(byte[] bArr, Iterable<lL> iterable) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (iterable != null) {
            Context applicationContext = getApplicationContext();
            if (applicationContext != null) {
                try {
                    applicationContext.startService(dL.a(applicationContext, sN.a(bArr), iterable, false));
                } catch (IllegalStateException e) {
                    f1109e.d("Unable to deliver `unregister` intent: %s", new Object[]{e});
                }
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: b */
    public abstract byte[] mo2316b();

    /* renamed from: c */
    public abstract void mo2317c(byte[] bArr);

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* renamed from: d */
    public abstract void mo2319d(byte[] bArr);

    /* renamed from: e */
    public abstract void mo2320e(byte[] bArr);

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public void onCreate() {
        super.onCreate();
        this.f1116d = new NL(this.f1113a, this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r24) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            if (r2 != 0) goto L_0x0007
            return
        L_0x0007:
            fL r0 = r1.f1114b
            r3 = 0
            r4 = 0
            r5 = 1
            if (r0 != 0) goto L_0x0059
            byte[] r0 = r23.mo2316b()
            if (r0 == 0) goto L_0x003f
            iM r0 = iM.a(r0)     // Catch:{ ValidationException -> 0x0033 }
            boolean r6 = r0.c()     // Catch:{ ValidationException -> 0x0033 }
            if (r6 == 0) goto L_0x0026
            boolean r6 = r0.d()     // Catch:{ ValidationException -> 0x0033 }
            if (r6 == 0) goto L_0x0026
            r6 = 1
            goto L_0x0027
        L_0x0026:
            r6 = 0
        L_0x0027:
            if (r6 != 0) goto L_0x0040
            com.google.ipc.invalidation.external.client.SystemResources$Logger r0 = f1109e     // Catch:{ ValidationException -> 0x0033 }
            java.lang.String r6 = "Invalid listener state."
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ ValidationException -> 0x0033 }
            r0.c(r6, r7)     // Catch:{ ValidationException -> 0x0033 }
            goto L_0x003f
        L_0x0033:
            r0 = move-exception
            com.google.ipc.invalidation.external.client.SystemResources$Logger r6 = f1109e
            java.lang.Object[] r7 = new java.lang.Object[r5]
            r7[r4] = r0
            java.lang.String r0 = "Failed to parse listener state: %s"
            r6.c(r0, r7)
        L_0x003f:
            r0 = r3
        L_0x0040:
            if (r0 == 0) goto L_0x004e
            fL r6 = new fL
            int r7 = f1110k
            int r8 = f1111n
            r6.<init>(r7, r8, r0)
            r1.f1114b = r6
            goto L_0x0059
        L_0x004e:
            fL r0 = new fL
            int r6 = f1110k
            int r7 = f1111n
            r0.<init>(r6, r7)
            r1.f1114b = r0
        L_0x0059:
            fL r0 = r1.f1114b
            KL r6 = r1.f1115c
            KL$a r6 = (KL.a) r6
            long r6 = r6.a()
            java.util.List r0 = r0.a(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x006b:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x007b
            java.lang.Object r6 = r0.next()
            jM r6 = (jM) r6
            r1.mo2307a((jM) r6)
            goto L_0x006b
        L_0x007b:
            boolean r0 = dL.c(r24)
            if (r0 != 0) goto L_0x02b1
            java.lang.String r0 = r24.getAction()
            java.lang.String r6 = "com.google.ipc.invalidation.AUTH_TOKEN_REQUEST"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x008f
            r0 = 0
            goto L_0x00ad
        L_0x008f:
            java.lang.String r0 = "com.google.ipc.invalidaton.AUTH_TOKEN_INVALIDATE"
            java.lang.String r0 = r2.getStringExtra(r0)
            java.lang.String r6 = "com.google.ipc.invalidation.AUTH_TOKEN_PENDING_INTENT"
            android.os.Parcelable r6 = r2.getParcelableExtra(r6)
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            if (r6 != 0) goto L_0x00a9
            com.google.ipc.invalidation.external.client.SystemResources$Logger r0 = f1109e
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.String r7 = "Authorization request without pending intent extra."
            r0.c(r7, r6)
            goto L_0x00ac
        L_0x00a9:
            r1.mo2305a((android.app.PendingIntent) r6, (java.lang.String) r0)
        L_0x00ac:
            r0 = 1
        L_0x00ad:
            if (r0 != 0) goto L_0x02b1
            jM r0 = dL.a(r24)
            if (r0 == 0) goto L_0x00d2
            boolean r6 = r0.e()
            if (r6 == 0) goto L_0x00c9
            boolean r6 = r0.c()
            if (r6 == 0) goto L_0x00c9
            boolean r6 = r0.d()
            if (r6 == 0) goto L_0x00c9
            r6 = 1
            goto L_0x00ca
        L_0x00c9:
            r6 = 0
        L_0x00ca:
            if (r6 != 0) goto L_0x00cd
            goto L_0x00d2
        L_0x00cd:
            r1.mo2307a((jM) r0)
            r0 = 1
            goto L_0x00d3
        L_0x00d2:
            r0 = 0
        L_0x00d3:
            if (r0 != 0) goto L_0x02b1
            kM r0 = dL.b(r24)
            if (r0 == 0) goto L_0x023c
            boolean r6 = r0.e()
            if (r6 == 0) goto L_0x00e9
            boolean r6 = r0.d()
            if (r6 == 0) goto L_0x00e9
            r6 = 1
            goto L_0x00ea
        L_0x00e9:
            r6 = 0
        L_0x00ea:
            if (r6 != 0) goto L_0x00ee
            goto L_0x023c
        L_0x00ee:
            fL r6 = new fL
            int r7 = f1110k
            int r8 = f1111n
            r6.<init>(r7, r8)
            r1.f1114b = r6
            r6 = 3
            r7 = 2
            fN r9 = fN.a(r6, r7)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            UM r8 = new UM
            r10 = 5000(0x1388, float:7.006E-42)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.<init>(r10, r6)
            r7.add(r8)
            SM r6 = new SM
            r6.<init>(r3, r7)
            r21 = 0
            r20 = 0
            r19 = 0
            r17 = 0
            r16 = 0
            r15 = 0
            r14 = 0
            r13 = 0
            r12 = 0
            r11 = 0
            r10 = 0
            IM r7 = new IM
            r8 = r7
            r18 = r6
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            boolean r6 = r0.f
            boolean r8 = r7.p
            if (r6 == r8) goto L_0x01ef
            fN r10 = r7.d
            SM r6 = r7.m
            boolean r8 = r7.k()
            if (r8 == 0) goto L_0x014b
            int r8 = r7.e
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r11 = r8
            goto L_0x014c
        L_0x014b:
            r11 = r3
        L_0x014c:
            boolean r8 = r7.o()
            if (r8 == 0) goto L_0x015a
            int r8 = r7.f
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r12 = r8
            goto L_0x015b
        L_0x015a:
            r12 = r3
        L_0x015b:
            boolean r8 = r7.g()
            if (r8 == 0) goto L_0x0169
            int r8 = r7.g
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r13 = r8
            goto L_0x016a
        L_0x0169:
            r13 = r3
        L_0x016a:
            boolean r8 = r7.m()
            if (r8 == 0) goto L_0x0178
            int r8 = r7.h
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r14 = r8
            goto L_0x0179
        L_0x0178:
            r14 = r3
        L_0x0179:
            boolean r8 = r7.j()
            if (r8 == 0) goto L_0x0187
            int r8 = r7.i
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r15 = r8
            goto L_0x0188
        L_0x0187:
            r15 = r3
        L_0x0188:
            boolean r8 = r7.n()
            if (r8 == 0) goto L_0x0197
            int r8 = r7.j
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r16 = r8
            goto L_0x0199
        L_0x0197:
            r16 = r3
        L_0x0199:
            boolean r8 = r7.i()
            if (r8 == 0) goto L_0x01a8
            boolean r8 = r7.k
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r17 = r8
            goto L_0x01aa
        L_0x01a8:
            r17 = r3
        L_0x01aa:
            boolean r8 = r7.h()
            if (r8 == 0) goto L_0x01b9
            int r8 = r7.l
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r18 = r8
            goto L_0x01bb
        L_0x01b9:
            r18 = r3
        L_0x01bb:
            boolean r8 = r7.f()
            if (r8 == 0) goto L_0x01ca
            boolean r8 = r7.n
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r20 = r8
            goto L_0x01cc
        L_0x01ca:
            r20 = r3
        L_0x01cc:
            boolean r8 = r7.l()
            if (r8 == 0) goto L_0x01db
            int r8 = r7.o
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r21 = r8
            goto L_0x01dd
        L_0x01db:
            r21 = r3
        L_0x01dd:
            boolean r7 = r7.e()
            boolean r7 = r0.f
            java.lang.Boolean r22 = java.lang.Boolean.valueOf(r7)
            IM r7 = new IM
            r9 = r7
            r19 = r6
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
        L_0x01ef:
            int r6 = r0.d
            sN r0 = r0.e
            tM$a r13 = new tM$a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            r13.<init>(r6, r0, r7, r8)
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            fN r9 = RL.a
            tM r6 = new tM
            r10 = 0
            r11 = 0
            r12 = 0
            r8 = r6
            r8.<init>(r9, r10, r11, r12, r13)
            byte[] r6 = r6.d()
            java.lang.String r7 = "ipcinv-internal-downcall"
            r0.putExtra(r7, r6)
            android.content.Context r6 = r23.getApplicationContext()
            QL r7 = new QL     // Catch:{ IllegalStateException -> 0x022e }
            r7.<init>(r6)     // Catch:{ IllegalStateException -> 0x022e }
            PL r7 = r7.a     // Catch:{ IllegalStateException -> 0x022e }
            java.lang.String r7 = r7.a     // Catch:{ IllegalStateException -> 0x022e }
            android.content.Intent r0 = r0.setClassName(r6, r7)     // Catch:{ IllegalStateException -> 0x022e }
            r6.startService(r0)     // Catch:{ IllegalStateException -> 0x022e }
            goto L_0x023a
        L_0x022e:
            r0 = move-exception
            com.google.ipc.invalidation.external.client.SystemResources$Logger r6 = dL.a
            java.lang.Object[] r7 = new java.lang.Object[r5]
            r7[r4] = r0
            java.lang.String r0 = "Unable to deliver ticl intent: %s"
            r6.d(r0, r7)
        L_0x023a:
            r0 = 1
            goto L_0x023d
        L_0x023c:
            r0 = 0
        L_0x023d:
            if (r0 != 0) goto L_0x02b1
            java.lang.String r0 = "com.google.ipc.invalidation.android_listener.STOP"
            boolean r0 = r2.hasExtra(r0)
            if (r0 != 0) goto L_0x0249
            r0 = 0
            goto L_0x0251
        L_0x0249:
            NL r0 = r1.f1116d
            bL r0 = r0.b
            r0.stop()
            r0 = 1
        L_0x0251:
            if (r0 != 0) goto L_0x02b1
            java.lang.String r0 = "com.google.ipc.invalidation.android_listener.ACK"
            byte[] r0 = r2.getByteArrayExtra(r0)
            if (r0 != 0) goto L_0x025d
            r0 = 0
            goto L_0x026a
        L_0x025d:
            NL r6 = r1.f1116d
            bL r6 = r6.b
            gL r7 = new gL
            r7.<init>(r0)
            r6.a(r7)
            r0 = 1
        L_0x026a:
            if (r0 != 0) goto L_0x02b1
            java.lang.String r0 = "ipcinv-background-inv"
            byte[] r0 = r2.getByteArrayExtra(r0)
            if (r0 != 0) goto L_0x0275
            goto L_0x02ac
        L_0x0275:
            OM r0 = OM.a(r0)     // Catch:{ ValidationException -> 0x029c }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ ValidationException -> 0x029c }
            r6.<init>()     // Catch:{ ValidationException -> 0x029c }
            java.util.List r0 = r0.c     // Catch:{ ValidationException -> 0x029c }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ ValidationException -> 0x029c }
        L_0x0284:
            boolean r7 = r0.hasNext()     // Catch:{ ValidationException -> 0x029c }
            if (r7 == 0) goto L_0x0298
            java.lang.Object r7 = r0.next()     // Catch:{ ValidationException -> 0x029c }
            PM r7 = (PM) r7     // Catch:{ ValidationException -> 0x029c }
            kL r7 = wL.a(r7)     // Catch:{ ValidationException -> 0x029c }
            r6.add(r7)     // Catch:{ ValidationException -> 0x029c }
            goto L_0x0284
        L_0x0298:
            r23.mo2304a()     // Catch:{ ValidationException -> 0x029c }
            goto L_0x02ac
        L_0x029c:
            r0 = move-exception
            com.google.ipc.invalidation.external.client.SystemResources$Logger r6 = f1109e
            java.lang.Object[] r7 = new java.lang.Object[r5]
            java.lang.String r0 = r0.getMessage()
            r7[r4] = r0
            java.lang.String r0 = "Failed to parse background invalidation intent payload: %s"
            r6.d(r0, r7)
        L_0x02ac:
            NL r0 = r1.f1116d
            r0.a(r2)
        L_0x02b1:
            fL r0 = r1.f1114b
            java.util.TreeMap r2 = r0.c
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x02bd
            r0 = r3
            goto L_0x02c9
        L_0x02bd:
            java.util.TreeMap r0 = r0.c
            java.util.Map$Entry r0 = r0.firstEntry()
            java.lang.Object r0 = r0.getKey()
            java.lang.Long r0 = (java.lang.Long) r0
        L_0x02c9:
            if (r0 == 0) goto L_0x030e
            com.google.ipc.invalidation.external.client.SystemResources$Logger r2 = f1109e
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r6[r4] = r0
            java.lang.String r7 = "scheduling alarm at %s"
            r2.b(r7, r6)
            android.content.Context r2 = r23.getApplicationContext()
            long r6 = r0.longValue()
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r8 = "com.google.ipc.invalidation.android_listener.SCHEDULED_TASK"
            android.content.Intent r0 = r0.putExtra(r8, r5)
            java.lang.Class<com.google.ipc.invalidation.external.client.contrib.AndroidListener$AlarmReceiver> r8 = com.google.ipc.invalidation.external.client.contrib.AndroidListener.AlarmReceiver.class
            android.content.Intent r0 = r0.setClass(r2, r8)
            r8 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r0 = android.app.PendingIntent.getBroadcast(r2, r4, r0, r8)
            java.lang.String r8 = "alarm"
            java.lang.Object r2 = r2.getSystemService(r8)
            android.app.AlarmManager r2 = (android.app.AlarmManager) r2
            r2.set(r5, r6, r0)     // Catch:{ SecurityException -> 0x0301 }
            goto L_0x030e
        L_0x0301:
            r0 = move-exception
            r2 = r0
            com.google.ipc.invalidation.external.client.SystemResources$Logger r0 = dL.a
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r2
            java.lang.String r2 = "Unable to schedule task: %s"
            r0.c(r2, r5)
        L_0x030e:
            fL r0 = r1.f1114b
            boolean r2 = r0.h
            if (r2 == 0) goto L_0x03a7
            iM r0 = r0.a()
            FO r2 = new FO
            r2.<init>()
            java.util.List r5 = r0.d
            int r5 = r5.size()
            CP[] r5 = new CP[r5]
            r2.c = r5
            r5 = 0
        L_0x0328:
            CP[] r6 = r2.c
            int r7 = r6.length
            if (r5 >= r7) goto L_0x033e
            java.util.List r7 = r0.d
            java.lang.Object r7 = r7.get(r5)
            QM r7 = (QM) r7
            CP r7 = r7.c()
            r6[r5] = r7
            int r5 = r5 + 1
            goto L_0x0328
        L_0x033e:
            java.util.List r5 = r0.e
            int r5 = r5.size()
            DO[] r5 = new DO[r5]
            r2.d = r5
            r5 = 0
        L_0x0349:
            DO[] r6 = r2.d
            int r7 = r6.length
            if (r5 >= r7) goto L_0x035f
            java.util.List r7 = r0.e
            java.lang.Object r7 = r7.get(r5)
            gM r7 = (gM) r7
            DO r7 = r7.d()
            r6[r5] = r7
            int r5 = r5 + 1
            goto L_0x0349
        L_0x035f:
            boolean r5 = r0.c()
            if (r5 == 0) goto L_0x036a
            sN r5 = r0.f
            byte[] r5 = r5.a
            goto L_0x036b
        L_0x036a:
            r5 = r3
        L_0x036b:
            r2.e = r5
            boolean r5 = r0.d()
            if (r5 == 0) goto L_0x0379
            int r3 = r0.g
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0379:
            r2.k = r3
            java.util.List r3 = r0.h
            int r3 = r3.size()
            EO[] r3 = new EO[r3]
            r2.n = r3
            r3 = 0
        L_0x0386:
            EO[] r5 = r2.n
            int r6 = r5.length
            if (r3 >= r6) goto L_0x039c
            java.util.List r6 = r0.h
            java.lang.Object r6 = r6.get(r3)
            hM r6 = (hM) r6
            EO r6 = r6.e()
            r5[r3] = r6
            int r3 = r3 + 1
            goto L_0x0386
        L_0x039c:
            byte[] r0 = xO.a(r2)
            r1.mo2320e(r0)
            fL r0 = r1.f1114b
            r0.h = r4
        L_0x03a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ipc.invalidation.external.client.contrib.AndroidListener.onHandleIntent(android.content.Intent):void");
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: com.google.ipc.invalidation.external.client.contrib.AndroidListener$a */
    /* compiled from: PG */
    public class C0274a implements InvalidationListener {
        public C0274a() {
        }

        /* renamed from: a */
        public final void mo2283a(bL bLVar) {
            AndroidListener androidListener = AndroidListener.this;
            sN sNVar = androidListener.f1114b.i;
            AndroidListener.f1112p = sNVar;
            androidListener.mo2317c(sNVar.a);
        }

        /* renamed from: a */
        public final void mo2290a(bL bLVar, byte[] bArr, int i) {
            AndroidListener androidListener = AndroidListener.this;
            androidListener.mo2319d(androidListener.f1114b.i.a);
        }

        /* renamed from: a */
        public final void mo2287a(bL bLVar, lL lLVar, InvalidationListener.RegistrationState registrationState) {
            AndroidListener.this.f1114b.b(lLVar);
            AndroidListener androidListener = AndroidListener.this;
            androidListener.mo2312a(androidListener.f1114b.i.a, lLVar, registrationState);
        }

        /* renamed from: a */
        public final void mo2289a(bL bLVar, lL lLVar, boolean z, String str) {
            fL fLVar = AndroidListener.this.f1114b;
            fLVar.a(lLVar);
            if (!z) {
                fLVar.b(lLVar);
            }
            AndroidListener androidListener = AndroidListener.this;
            androidListener.mo2313a(androidListener.f1114b.i.a, lLVar, z, str);
        }

        /* renamed from: a */
        public void mo2286a(bL bLVar, kL kLVar, gL gLVar) {
            AndroidListener.this.mo2308a(kLVar, gLVar.a);
        }

        /* renamed from: a */
        public void mo2288a(bL bLVar, lL lLVar, gL gLVar) {
            AndroidListener.this.mo2309a(lLVar, gLVar.a);
        }

        /* renamed from: a */
        public void mo2284a(bL bLVar, gL gLVar) {
            AndroidListener.this.mo2314b(gLVar.a);
        }

        /* renamed from: a */
        public void mo2285a(bL bLVar, jL jLVar) {
            AndroidListener.this.mo2306a(jLVar);
        }
    }

    /* renamed from: a */
    public void mo2310a(byte[] bArr) {
        if (bArr != null) {
            Context applicationContext = getApplicationContext();
            if (applicationContext != null) {
                try {
                    applicationContext.startService(dL.a(applicationContext, bArr));
                } catch (IllegalStateException e) {
                    f1109e.d("Unable to deliver `acknowledge` intent: %s", new Object[]{e});
                }
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public final void mo2307a(jM jMVar) {
        int i;
        jM jMVar2;
        boolean z;
        if (!jMVar.f.equals(this.f1114b.i)) {
            f1109e.c("Ignoring registration request for old client. Old ID = %s, New ID = %s", new Object[]{jMVar.f, this.f1114b.i});
            return;
        }
        boolean z2 = jMVar.d;
        for (QM a : jMVar.e) {
            lL a2 = wL.a(a);
            if (!jMVar.g) {
                fL fLVar = this.f1114b;
                JL jl = (JL) fLVar.a.get(a2);
                if (jl == null) {
                    jl = new JL(fLVar.d, fLVar.e, fLVar.f);
                    fLVar.a.put(a2, jl);
                }
                fLVar.h = true;
                i = jl.a();
            } else {
                i = 0;
            }
            if (i != 0) {
                long a3 = this.f1115c.a() + ((long) i);
                fL fLVar2 = this.f1114b;
                if (z2) {
                    jMVar2 = eL.a(fLVar2.i, a2, true);
                } else {
                    jMVar2 = eL.a(fLVar2.i, a2, false);
                }
                while (fLVar2.c.containsKey(Long.valueOf(a3))) {
                    a3++;
                }
                fLVar2.c.put(Long.valueOf(a3), jMVar2);
                fLVar2.h = true;
            } else if (z2) {
                fL fLVar3 = this.f1114b;
                if (fLVar3.b.add(a2)) {
                    fLVar3.h = true;
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.f1116d.b.b(a2);
                }
            } else {
                fL fLVar4 = this.f1114b;
                if (fLVar4.b.remove(a2)) {
                    fLVar4.h = true;
                }
                this.f1116d.b.a(a2);
            }
        }
    }
}
