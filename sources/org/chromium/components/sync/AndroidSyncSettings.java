package org.chromium.components.sync;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.ObserverList;

/* compiled from: PG */
public class AndroidSyncSettings {

    /* renamed from: i */
    public static final Object f2369i = new Object();
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: j */
    public static AndroidSyncSettings f2370j;

    /* renamed from: a */
    public final Object f2371a = new Object();

    /* renamed from: b */
    public final String f2372b = RN0.a.getPackageName();

    /* renamed from: c */
    public final dM2 f2373c;

    /* renamed from: d */
    public Account f2374d;

    /* renamed from: e */
    public boolean f2375e;

    /* renamed from: f */
    public boolean f2376f;

    /* renamed from: g */
    public boolean f2377g;

    /* renamed from: h */
    public final ObserverList<AndroidSyncSettingsObserver> f2378h = new ObserverList<>();

    public AndroidSyncSettings(dM2 dm2) {
        this.f2373c = dm2;
        NL2.d().b();
        this.f2374d = null;
        mo9641b((Callback<Boolean>) null);
        mo9645e();
        this.f2373c.a(1, new bM2(this, (aM2) null));
    }

    /* renamed from: f */
    public static AndroidSyncSettings m3337f() {
        AndroidSyncSettings androidSyncSettings;
        synchronized (f2369i) {
            if (f2370j == null) {
                f2370j = new AndroidSyncSettings(new eM2());
            }
            androidSyncSettings = f2370j;
        }
        return androidSyncSettings;
    }

    /* renamed from: b */
    public String mo9640b() {
        return this.f2372b;
    }

    /* renamed from: c */
    public boolean mo9643c() {
        return this.f2377g && this.f2376f;
    }

    /* renamed from: d */
    public final void mo9644d() {
        Iterator<AndroidSyncSettingsObserver> it = this.f2378h.iterator();
        while (it.hasNext()) {
            it.next().androidSyncSettingsChanged();
        }
    }

    /* renamed from: e */
    public final boolean mo9645e() {
        boolean z;
        synchronized (this.f2371a) {
            boolean z2 = this.f2376f;
            boolean z3 = this.f2377g;
            StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            z = true;
            if (this.f2374d != null) {
                this.f2375e = this.f2373c.a(this.f2374d, this.f2372b) == 1;
                this.f2376f = this.f2373c.b(this.f2374d, this.f2372b);
            } else {
                this.f2375e = false;
                this.f2376f = false;
            }
            this.f2377g = this.f2373c.a();
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            if (z2 == this.f2376f) {
                if (z3 == this.f2377g) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public void mo9634a() {
        mo9639a(true);
    }

    /* renamed from: b */
    public void mo9642b(AndroidSyncSettingsObserver androidSyncSettingsObserver) {
        synchronized (this.f2371a) {
            this.f2378h.mo7869b(androidSyncSettingsObserver);
        }
    }

    /* renamed from: a */
    public void mo9635a(Account account, Callback<Boolean> callback) {
        synchronized (this.f2371a) {
            this.f2374d = account;
            mo9641b(callback);
        }
        if (mo9645e()) {
            mo9644d();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        m3335a(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
        throw r0;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo9641b(org.chromium.base.Callback<java.lang.Boolean> r7) {
        /*
            r6 = this;
            android.accounts.Account r0 = r6.f2374d
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            boolean r3 = r6.f2375e
            if (r3 != r0) goto L_0x0017
            if (r7 == 0) goto L_0x0016
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r7.onResult(r0)
        L_0x0016:
            return
        L_0x0017:
            r6.f2375e = r0
            pO0 r1 = pO0.b()
            r3 = 0
            if (r0 == 0) goto L_0x0040
            dM2 r0 = r6.f2373c     // Catch:{ all -> 0x0039 }
            android.accounts.Account r4 = r6.f2374d     // Catch:{ all -> 0x0039 }
            java.lang.String r5 = r6.f2372b     // Catch:{ all -> 0x0039 }
            eM2 r0 = (eM2) r0
            r0.a(r4, r5, r2)     // Catch:{ all -> 0x0039 }
            dM2 r0 = r6.f2373c     // Catch:{ all -> 0x0039 }
            android.accounts.Account r2 = r6.f2374d     // Catch:{ all -> 0x0039 }
            java.lang.String r4 = r6.f2372b     // Catch:{ all -> 0x0039 }
            android.os.Bundle r5 = android.os.Bundle.EMPTY     // Catch:{ all -> 0x0039 }
            eM2 r0 = (eM2) r0
            r0.a(r2, r4, r5)     // Catch:{ all -> 0x0039 }
            goto L_0x0040
        L_0x0039:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x003b }
        L_0x003b:
            r0 = move-exception
            m3335a((java.lang.Throwable) r7, (pO0) r1)
            throw r0
        L_0x0040:
            m3335a((java.lang.Throwable) r3, (pO0) r1)
            YL2 r0 = new YL2
            r0.<init>(r6, r7)
            org.chromium.base.ThreadUtils.m1457a((java.lang.Runnable) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.sync.AndroidSyncSettings.mo9641b(org.chromium.base.Callback):void");
    }

    /* renamed from: a */
    public void mo9638a(AndroidSyncSettingsObserver androidSyncSettingsObserver) {
        synchronized (this.f2371a) {
            this.f2378h.mo7868a(androidSyncSettingsObserver);
        }
    }

    /* renamed from: a */
    public final void mo9639a(boolean z) {
        synchronized (this.f2371a) {
            mo9641b((Callback<Boolean>) null);
            if (z != this.f2376f) {
                if (this.f2374d != null) {
                    this.f2376f = z;
                    StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                    this.f2373c.a(this.f2374d, this.f2372b, z);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    mo9644d();
                }
            }
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m3335a(Throwable th, pO0 po0) {
        if (th != null) {
            try {
                po0.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            po0.close();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9636a(Callback callback) {
        FL2.l().b(new ZL2(this, callback));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        m3335a(r9, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004b, code lost:
        throw r10;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void mo9637a(org.chromium.base.Callback r9, java.util.List r10) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.f2371a
            monitor-enter(r0)
            pO0 r1 = pO0.b()     // Catch:{ all -> 0x004c }
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000a:
            int r5 = r10.size()     // Catch:{ all -> 0x0045 }
            if (r4 >= r5) goto L_0x0036
            java.lang.Object r5 = r10.get(r4)     // Catch:{ all -> 0x0045 }
            android.accounts.Account r5 = (android.accounts.Account) r5     // Catch:{ all -> 0x0045 }
            android.accounts.Account r6 = r8.f2374d     // Catch:{ all -> 0x0045 }
            boolean r6 = r5.equals(r6)     // Catch:{ all -> 0x0045 }
            if (r6 != 0) goto L_0x0033
            dM2 r6 = r8.f2373c     // Catch:{ all -> 0x0045 }
            java.lang.String r7 = r8.f2372b     // Catch:{ all -> 0x0045 }
            eM2 r6 = (eM2) r6
            int r6 = r6.a(r5, r7)     // Catch:{ all -> 0x0045 }
            if (r6 <= 0) goto L_0x0033
            dM2 r6 = r8.f2373c     // Catch:{ all -> 0x0045 }
            java.lang.String r7 = r8.f2372b     // Catch:{ all -> 0x0045 }
            eM2 r6 = (eM2) r6
            r6.a(r5, r7, r3)     // Catch:{ all -> 0x0045 }
        L_0x0033:
            int r4 = r4 + 1
            goto L_0x000a
        L_0x0036:
            m3335a((java.lang.Throwable) r2, (pO0) r1)     // Catch:{ all -> 0x004c }
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            if (r9 == 0) goto L_0x0044
            r10 = 1
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9.onResult(r10)
        L_0x0044:
            return
        L_0x0045:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r10 = move-exception
            m3335a((java.lang.Throwable) r9, (pO0) r1)     // Catch:{ all -> 0x004c }
            throw r10     // Catch:{ all -> 0x004c }
        L_0x004c:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            goto L_0x0050
        L_0x004f:
            throw r9
        L_0x0050:
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.sync.AndroidSyncSettings.mo9637a(org.chromium.base.Callback, java.util.List):void");
    }
}
