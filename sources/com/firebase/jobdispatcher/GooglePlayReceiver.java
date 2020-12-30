package com.firebase.jobdispatcher;

import CF;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.Pair;
import com.firebase.jobdispatcher.ValidationEnforcer;
import java.util.List;
import lF;
import yF;

/* compiled from: PG */
public class GooglePlayReceiver extends Service implements lF.a {

    /* renamed from: n */
    public static final AF f860n = new AF("com.firebase.jobdispatcher.");

    /* renamed from: p */
    public static final T8<String, T8<String, zF>> f861p = new T8<>(1);

    /* renamed from: a */
    public final mF f862a = new mF();

    /* renamed from: b */
    public Messenger f863b;

    /* renamed from: c */
    public jF f864c;

    /* renamed from: d */
    public ValidationEnforcer f865d;

    /* renamed from: e */
    public lF f866e;

    /* renamed from: k */
    public int f867k;

    /* renamed from: a */
    public static void m760a(zF zFVar, int i) {
        try {
            zFVar.a(i);
        } catch (Throwable th) {
            StringBuilder a = Eo.a("Encountered error running callback: ");
            a.append(th.getMessage());
            Log.e("FJD.GooglePlayReceiver", a.toString());
        }
    }

    /* renamed from: b */
    public final synchronized jF mo1953b() {
        if (this.f864c == null) {
            this.f864c = new nF(getApplicationContext());
        }
        return this.f864c;
    }

    /* renamed from: c */
    public final synchronized Messenger mo1954c() {
        if (this.f863b == null) {
            this.f863b = new Messenger(new qF(Looper.getMainLooper(), this));
        }
        return this.f863b;
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* renamed from: d */
    public final synchronized ValidationEnforcer mo1956d() {
        if (this.f865d == null) {
            this.f865d = new ValidationEnforcer(mo1953b().a);
        }
        return this.f865d;
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || Build.VERSION.SDK_INT < 21 || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return mo1954c().getBinder();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            super.onStartCommand(intent, i, i2);
            if (intent == null) {
                Log.w("FJD.GooglePlayReceiver", "Null Intent passed, terminating");
                synchronized (f861p) {
                    this.f867k = i2;
                    if (f861p.isEmpty()) {
                        stopSelf(this.f867k);
                    }
                }
                return 2;
            }
            String action = intent.getAction();
            if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(action)) {
                mo1950a().a(mo1948a(intent));
                synchronized (f861p) {
                    this.f867k = i2;
                    if (f861p.isEmpty()) {
                        stopSelf(this.f867k);
                    }
                }
                return 2;
            } else if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(action)) {
                synchronized (f861p) {
                    this.f867k = i2;
                    if (f861p.isEmpty()) {
                        stopSelf(this.f867k);
                    }
                }
                return 2;
            } else {
                Log.e("FJD.GooglePlayReceiver", "Unknown action received, terminating");
                synchronized (f861p) {
                    this.f867k = i2;
                    if (f861p.isEmpty()) {
                        stopSelf(this.f867k);
                    }
                }
                return 2;
            }
        } catch (Throwable th) {
            synchronized (f861p) {
                this.f867k = i2;
                if (f861p.isEmpty()) {
                    stopSelf(this.f867k);
                }
                throw th;
            }
        }
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: a */
    public synchronized lF mo1950a() {
        if (this.f866e == null) {
            this.f866e = new lF(this, this, new hF(getApplicationContext()));
        }
        return this.f866e;
    }

    /* renamed from: a */
    public CF mo1948a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.e("FJD.GooglePlayReceiver", "No data provided, terminating");
            return null;
        }
        Pair a = this.f862a.a(extras);
        if (a != null) {
            return mo1949a((zF) a.first, (Bundle) a.second);
        }
        Log.i("FJD.GooglePlayReceiver", "no callback found");
        return null;
    }

    /* renamed from: a */
    public CF mo1949a(zF zFVar, Bundle bundle) {
        CF b = f860n.b(bundle);
        if (b == null) {
            Log.e("FJD.GooglePlayReceiver", "unable to decode job");
            m760a(zFVar, 2);
            return null;
        }
        synchronized (f861p) {
            T8 t8 = (T8) f861p.get(b.b);
            if (t8 == null) {
                t8 = new T8(1);
                f861p.put(b.b, t8);
            }
            t8.put(b.a, zFVar);
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (f861p.isEmpty() == false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        stopSelf(r4.f867k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        r5 = f861p;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0039, code lost:
        if (f861p.isEmpty() == false) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003b, code lost:
        stopSelf(r4.f867k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0058, code lost:
        if (r5.f() == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0060, code lost:
        if ((r5.a() instanceof FF.a) == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0062, code lost:
        if (r6 == 1) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0065, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0066, code lost:
        if (r1 == false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0068, code lost:
        mo1951a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0073, code lost:
        if (android.util.Log.isLoggable("FJD.GooglePlayReceiver", 2) == false) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0075, code lost:
        "sending jobFinished for " + r5.a + " = " + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008f, code lost:
        m760a(r2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0092, code lost:
        r5 = f861p;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0094, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009b, code lost:
        if (f861p.isEmpty() == false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009d, code lost:
        stopSelf(r4.f867k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a2, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        r5 = f861p;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        monitor-enter(r5);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1952a(CF r5, int r6) {
        /*
            r4 = this;
            T8<java.lang.String, T8<java.lang.String, zF>> r0 = f861p     // Catch:{ all -> 0x00aa }
            monitor-enter(r0)     // Catch:{ all -> 0x00aa }
            T8<java.lang.String, T8<java.lang.String, zF>> r1 = f861p     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = r5.b     // Catch:{ all -> 0x00a7 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x00a7 }
            T8 r1 = (T8) r1     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x0025
            monitor-exit(r0)     // Catch:{ all -> 0x00a7 }
            T8<java.lang.String, T8<java.lang.String, zF>> r5 = f861p
            monitor-enter(r5)
            T8<java.lang.String, T8<java.lang.String, zF>> r6 = f861p     // Catch:{ all -> 0x0022 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x0022 }
            if (r6 == 0) goto L_0x0020
            int r6 = r4.f867k     // Catch:{ all -> 0x0022 }
            r4.stopSelf(r6)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r5)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0022 }
            throw r6
        L_0x0025:
            java.lang.String r2 = r5.a     // Catch:{ all -> 0x00a7 }
            java.lang.Object r2 = r1.remove(r2)     // Catch:{ all -> 0x00a7 }
            zF r2 = (zF) r2     // Catch:{ all -> 0x00a7 }
            if (r2 != 0) goto L_0x0045
            monitor-exit(r0)     // Catch:{ all -> 0x00a7 }
            T8<java.lang.String, T8<java.lang.String, zF>> r5 = f861p
            monitor-enter(r5)
            T8<java.lang.String, T8<java.lang.String, zF>> r6 = f861p     // Catch:{ all -> 0x0042 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r6 == 0) goto L_0x0040
            int r6 = r4.f867k     // Catch:{ all -> 0x0042 }
            r4.stopSelf(r6)     // Catch:{ all -> 0x0042 }
        L_0x0040:
            monitor-exit(r5)     // Catch:{ all -> 0x0042 }
            return
        L_0x0042:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0042 }
            throw r6
        L_0x0045:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0052
            T8<java.lang.String, T8<java.lang.String, zF>> r1 = f861p     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = r5.b     // Catch:{ all -> 0x00a7 }
            r1.remove(r3)     // Catch:{ all -> 0x00a7 }
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x00a7 }
            boolean r0 = r5.f()     // Catch:{ all -> 0x00aa }
            r1 = 1
            if (r0 == 0) goto L_0x0065
            FF r0 = r5.a()     // Catch:{ all -> 0x00aa }
            boolean r0 = r0 instanceof FF.a     // Catch:{ all -> 0x00aa }
            if (r0 == 0) goto L_0x0065
            if (r6 == r1) goto L_0x0065
            goto L_0x0066
        L_0x0065:
            r1 = 0
        L_0x0066:
            if (r1 == 0) goto L_0x006c
            r4.mo1951a((CF) r5)     // Catch:{ all -> 0x00aa }
            goto L_0x0092
        L_0x006c:
            java.lang.String r0 = "FJD.GooglePlayReceiver"
            r1 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00aa }
            if (r0 == 0) goto L_0x008f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00aa }
            r0.<init>()     // Catch:{ all -> 0x00aa }
            java.lang.String r1 = "sending jobFinished for "
            r0.append(r1)     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = r5.a     // Catch:{ all -> 0x00aa }
            r0.append(r5)     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = " = "
            r0.append(r5)     // Catch:{ all -> 0x00aa }
            r0.append(r6)     // Catch:{ all -> 0x00aa }
            r0.toString()     // Catch:{ all -> 0x00aa }
        L_0x008f:
            m760a((zF) r2, (int) r6)     // Catch:{ all -> 0x00aa }
        L_0x0092:
            T8<java.lang.String, T8<java.lang.String, zF>> r5 = f861p
            monitor-enter(r5)
            T8<java.lang.String, T8<java.lang.String, zF>> r6 = f861p     // Catch:{ all -> 0x00a4 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00a4 }
            if (r6 == 0) goto L_0x00a2
            int r6 = r4.f867k     // Catch:{ all -> 0x00a4 }
            r4.stopSelf(r6)     // Catch:{ all -> 0x00a4 }
        L_0x00a2:
            monitor-exit(r5)     // Catch:{ all -> 0x00a4 }
            return
        L_0x00a4:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00a4 }
            throw r6
        L_0x00a7:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a7 }
            throw r5     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r5 = move-exception
            T8<java.lang.String, T8<java.lang.String, zF>> r6 = f861p
            monitor-enter(r6)
            T8<java.lang.String, T8<java.lang.String, zF>> r0 = f861p     // Catch:{ all -> 0x00bd }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x00bb
            int r0 = r4.f867k     // Catch:{ all -> 0x00bd }
            r4.stopSelf(r0)     // Catch:{ all -> 0x00bd }
        L_0x00bb:
            monitor-exit(r6)     // Catch:{ all -> 0x00bd }
            throw r5
        L_0x00bd:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00bd }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.mo1952a(CF, int):void");
    }

    /* renamed from: a */
    public final void mo1951a(CF cf) {
        yF.a aVar = new yF.a(mo1956d(), cf);
        aVar.i = true;
        List<String> a = aVar.a.mo1993a(aVar);
        if (a == null) {
            mo1953b().a(new yF(aVar, (xF) null));
            return;
        }
        throw new ValidationEnforcer.ValidationException("JobParameters is invalid", a);
    }

    /* renamed from: a */
    public static void m759a(yF yFVar) {
        synchronized (f861p) {
            T8 t8 = (T8) f861p.get(yFVar.a);
            if (t8 != null) {
                if (((zF) t8.get(yFVar.b)) != null) {
                    CF.a aVar = new CF.a();
                    aVar.a = yFVar.b;
                    aVar.b = yFVar.a;
                    aVar.c = yFVar.c;
                    lF.a(aVar.a(), false);
                }
            }
        }
    }
}
