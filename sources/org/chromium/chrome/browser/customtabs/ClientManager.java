package org.chromium.chrome.browser.customtabs;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.support.customtabs.CustomTabsSessionToken;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.browserservices.OriginVerifier;
import org.chromium.chrome.browser.installedapp.InstalledAppProviderImpl;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class ClientManager {

    /* renamed from: a */
    public final Map<CustomTabsSessionToken, UJ1> f1715a = new HashMap();

    /* renamed from: b */
    public final SparseBooleanArray f1716b = new SparseBooleanArray();

    /* renamed from: c */
    public boolean f1717c;

    /* compiled from: PG */
    public interface DisconnectCallback {
        void run(CustomTabsSessionToken customTabsSessionToken);
    }

    public ClientManager() {
        oL1.c();
    }

    /* renamed from: a */
    public synchronized boolean mo8360a(CustomTabsSessionToken customTabsSessionToken, int i, DisconnectCallback disconnectCallback, Cz1 cz1, B1 b1) {
        if (customTabsSessionToken != null) {
            if (customTabsSessionToken.a() != null) {
                if (this.f1715a.containsKey(customTabsSessionToken)) {
                    this.f1715a.get(customTabsSessionToken).b = customTabsSessionToken.a();
                } else {
                    this.f1715a.put(customTabsSessionToken, new UJ1(RN0.a, i, customTabsSessionToken.a(), disconnectCallback, cz1, b1));
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public synchronized int mo8365b(CustomTabsSessionToken customTabsSessionToken, String str) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 == null) {
            return -3;
        }
        return uj1.d.a(str);
    }

    /* renamed from: c */
    public synchronized void mo8369c(CustomTabsSessionToken customTabsSessionToken, String str) {
        synchronized (this) {
            int a = mo8351a(customTabsSessionToken, str);
            RecordHistogram.m1539a("CustomTabs.PredictionStatus", a, 3);
            UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
            int i = 1;
            if (a == 1) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - uj1.q;
                oL1.a(uj1.a).a(uj1.p);
                RecordHistogram.m1542a("CustomTabs.PredictionToLaunch", elapsedRealtime, 1, 180000, 100);
            }
            RecordHistogram.m1539a("CustomTabs.WarmupStateOnLaunch", mo8378l(customTabsSessionToken), 5);
            if (uj1 != null) {
                if (!uj1.i) {
                    i = 0;
                }
                RecordHistogram.m1539a("CustomTabs.MayLaunchUrlType", i + (uj1.j ? 2 : 0), 4);
                uj1.p = null;
                uj1.q = 0;
                uj1.j = false;
                uj1.i = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        return;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo8370d(android.support.customtabs.CustomTabsSessionToken r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<android.support.customtabs.CustomTabsSessionToken, UJ1> r0 = r1.f1715a     // Catch:{ all -> 0x001d }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x001d }
            UJ1 r2 = (UJ1) r2     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x001b
            TJ1 r0 = r2.a()     // Catch:{ all -> 0x001d }
            if (r0 != 0) goto L_0x0012
            goto L_0x001b
        L_0x0012:
            TJ1 r2 = r2.a()     // Catch:{ all -> 0x001d }
            r2.b()     // Catch:{ all -> 0x001d }
            monitor-exit(r1)
            return
        L_0x001b:
            monitor-exit(r1)
            return
        L_0x001d:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.ClientManager.mo8370d(android.support.customtabs.CustomTabsSessionToken):void");
    }

    /* renamed from: e */
    public synchronized boolean mo8371e(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.s : false;
    }

    /* renamed from: f */
    public synchronized boolean mo8372f(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.t : false;
    }

    /* renamed from: g */
    public synchronized g1 mo8373g(CustomTabsSessionToken customTabsSessionToken) {
        if (customTabsSessionToken != null) {
            if (this.f1715a.containsKey(customTabsSessionToken)) {
                return this.f1715a.get(customTabsSessionToken).b;
            }
        }
        return null;
    }

    /* renamed from: h */
    public synchronized boolean mo8374h(CustomTabsSessionToken customTabsSessionToken) {
        boolean z;
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 == null) {
            z = false;
        } else {
            z = uj1.r;
        }
        return z;
    }

    /* renamed from: i */
    public synchronized String mo8375i(CustomTabsSessionToken customTabsSessionToken) {
        String str;
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 == null) {
            str = null;
        } else {
            str = uj1.k;
        }
        return str;
    }

    /* renamed from: j */
    public synchronized boolean mo8376j(CustomTabsSessionToken customTabsSessionToken) {
        boolean z;
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 == null) {
            z = false;
        } else {
            z = uj1.h;
        }
        return z;
    }

    /* renamed from: k */
    public synchronized oQ2 mo8377k(CustomTabsSessionToken customTabsSessionToken) {
        return IntentHandler.m1907a(mo8375i(customTabsSessionToken));
    }

    /* renamed from: l */
    public synchronized int mo8378l(CustomTabsSessionToken customTabsSessionToken) {
        int i;
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        boolean z = true;
        boolean z2 = uj1 != null;
        if (!z2 || !this.f1716b.get(uj1.a)) {
            z = false;
        }
        i = this.f1717c;
        if (z2) {
            i = z ? 4 : this.f1717c ? 2 : 3;
        }
        return i;
    }

    /* renamed from: m */
    public synchronized boolean mo8379m(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.u : false;
    }

    /* renamed from: n */
    public synchronized boolean mo8380n(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.l : false;
    }

    /* renamed from: o */
    public synchronized boolean mo8381o(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null && uj1.v;
    }

    /* renamed from: p */
    public synchronized boolean mo8382p(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.n : false;
    }

    /* renamed from: q */
    public synchronized boolean mo8383q(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1;
        uj1 = this.f1715a.get(customTabsSessionToken);
        return uj1 != null ? uj1.m : false;
    }

    /* renamed from: b */
    public synchronized List<CustomTabsSessionToken> mo8366b(int i) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Map.Entry next : this.f1715a.entrySet()) {
            if (((UJ1) next.getValue()).a == i) {
                arrayList.add((CustomTabsSessionToken) next.getKey());
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public synchronized void mo8367b(CustomTabsSessionToken customTabsSessionToken) {
        if (customTabsSessionToken.d()) {
            this.f1715a.get(customTabsSessionToken).b = null;
        } else {
            mo8368c(customTabsSessionToken);
        }
    }

    /* renamed from: a */
    public synchronized void mo8353a(int i) {
        this.f1717c = true;
        this.f1716b.put(i, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo8359a(android.support.customtabs.CustomTabsSessionToken r6, int r7, java.lang.String r8, boolean r9) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Map<android.support.customtabs.CustomTabsSessionToken, UJ1> r0 = r5.f1715a     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0046 }
            UJ1 r6 = (UJ1) r6     // Catch:{ all -> 0x0046 }
            r0 = 0
            if (r6 == 0) goto L_0x0044
            int r1 = r6.a     // Catch:{ all -> 0x0046 }
            if (r1 == r7) goto L_0x0011
            goto L_0x0044
        L_0x0011:
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0046 }
            r2 = 1
            if (r1 == 0) goto L_0x001f
            if (r9 == 0) goto L_0x001f
            boolean r1 = r6.i     // Catch:{ all -> 0x0046 }
            if (r1 != 0) goto L_0x001f
            r0 = 1
        L_0x001f:
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0046 }
            r6.p = r8     // Catch:{ all -> 0x0046 }
            r6.q = r3     // Catch:{ all -> 0x0046 }
            boolean r1 = r6.j     // Catch:{ all -> 0x0046 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0046 }
            r8 = r8 ^ r2
            r8 = r8 | r1
            r6.j = r8     // Catch:{ all -> 0x0046 }
            boolean r8 = r6.i     // Catch:{ all -> 0x0046 }
            r8 = r8 | r9
            r6.i = r8     // Catch:{ all -> 0x0046 }
            if (r0 == 0) goto L_0x003a
            monitor-exit(r5)
            return r2
        L_0x003a:
            oL1 r6 = oL1.a(r7)     // Catch:{ all -> 0x0046 }
            boolean r6 = r6.a()     // Catch:{ all -> 0x0046 }
            monitor-exit(r5)
            return r6
        L_0x0044:
            monitor-exit(r5)
            return r0
        L_0x0046:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.ClientManager.mo8359a(android.support.customtabs.CustomTabsSessionToken, int, java.lang.String, boolean):boolean");
    }

    /* renamed from: c */
    public final synchronized void mo8368c(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 != null) {
            this.f1715a.remove(customTabsSessionToken);
            B1 b1 = uj1.e;
            if (b1 != null) {
                Context context = RN0.a;
                if (b1.a()) {
                    b1.b(context);
                }
            }
            OriginVerifier originVerifier = uj1.g;
            if (originVerifier != null) {
                originVerifier.mo8332b();
            }
            DisconnectCallback disconnectCallback = uj1.c;
            if (disconnectCallback != null) {
                disconnectCallback.run(customTabsSessionToken);
            }
            this.f1716b.delete(uj1.a);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
        return r3;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int mo8351a(android.support.customtabs.CustomTabsSessionToken r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map<android.support.customtabs.CustomTabsSessionToken, UJ1> r0 = r4.f1715a     // Catch:{ all -> 0x002c }
            java.lang.Object r5 = r0.get(r5)     // Catch:{ all -> 0x002c }
            UJ1 r5 = (UJ1) r5     // Catch:{ all -> 0x002c }
            r0 = 0
            if (r5 != 0) goto L_0x000e
            monitor-exit(r4)
            return r0
        L_0x000e:
            java.lang.String r1 = r5.p     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0014
            monitor-exit(r4)
            return r0
        L_0x0014:
            boolean r2 = android.text.TextUtils.equals(r1, r6)     // Catch:{ all -> 0x002c }
            r3 = 1
            if (r2 != 0) goto L_0x0025
            boolean r5 = r5.h     // Catch:{ all -> 0x002c }
            if (r5 == 0) goto L_0x0026
            boolean r5 = org.chromium.chrome.browser.util.UrlUtilities.m3284b(r1, r6)     // Catch:{ all -> 0x002c }
            if (r5 == 0) goto L_0x0026
        L_0x0025:
            r0 = 1
        L_0x0026:
            if (r0 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r3 = 2
        L_0x002a:
            monitor-exit(r4)
            return r3
        L_0x002c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.ClientManager.mo8351a(android.support.customtabs.CustomTabsSessionToken, java.lang.String):int");
    }

    /* renamed from: a */
    public synchronized boolean mo8358a(CustomTabsSessionToken customTabsSessionToken) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 == null) {
            return false;
        }
        B1 b1 = uj1.e;
        return b1.a(RN0.a, b1.d);
    }

    /* renamed from: a */
    public synchronized void mo8354a(CustomTabsSessionToken customTabsSessionToken, Uri uri) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 != null) {
            uj1.d.a(uri);
        }
    }

    /* renamed from: a */
    public synchronized boolean mo8361a(CustomTabsSessionToken customTabsSessionToken, int i, yz1 yz1) {
        return mo8362a(customTabsSessionToken, i, yz1, false);
    }

    /* renamed from: a */
    public synchronized void mo8356a(CustomTabsSessionToken customTabsSessionToken, yz1 yz1, int i) {
        mo8362a(customTabsSessionToken, i, yz1, true);
    }

    /* renamed from: a */
    public final synchronized boolean mo8362a(CustomTabsSessionToken customTabsSessionToken, int i, yz1 yz1, boolean z) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 != null) {
            if (!TextUtils.isEmpty(uj1.k)) {
                RJ1 rj1 = new RJ1(this, yz1, customTabsSessionToken, i, z, uj1);
                uj1.g = new OriginVerifier(uj1.k, i);
                PostTask.m1565a(iQ2.a, (Runnable) new SJ1(uj1, rj1, yz1));
                if (i == 2 && InstalledAppProviderImpl.m2504a(uj1.k, URI.create(yz1.toString()), RN0.a.getPackageManager())) {
                    uj1.f.add(yz1);
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public synchronized void mo8355a(CustomTabsSessionToken customTabsSessionToken, WebContents webContents) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 != null) {
            uj1.d.b(webContents);
        }
    }

    /* renamed from: a */
    public synchronized void mo8357a(CustomTabsSessionToken customTabsSessionToken, boolean z) {
        UJ1 uj1 = this.f1715a.get(customTabsSessionToken);
        if (uj1 != null) {
            uj1.n = z;
        }
    }

    /* renamed from: a */
    public synchronized boolean mo8364a(CustomTabsSessionToken customTabsSessionToken, yz1 yz1) {
        return OriginVerifier.m1981b(mo8375i(customTabsSessionToken), yz1, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo8363a(android.support.customtabs.CustomTabsSessionToken r5, android.content.Intent r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r6 == 0) goto L_0x005f
            android.content.ComponentName r1 = r6.getComponent()     // Catch:{ all -> 0x005c }
            if (r1 != 0) goto L_0x000b
            goto L_0x005f
        L_0x000b:
            java.util.Map<android.support.customtabs.CustomTabsSessionToken, UJ1> r1 = r4.f1715a     // Catch:{ all -> 0x005c }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ all -> 0x005c }
            UJ1 r5 = (UJ1) r5     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x0017
            monitor-exit(r4)
            return r0
        L_0x0017:
            TJ1 r1 = r5.a()     // Catch:{ all -> 0x005c }
            if (r1 != 0) goto L_0x0051
            android.content.ComponentName r1 = r6.getComponent()     // Catch:{ all -> 0x005c }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x005c }
            android.content.Context r2 = RN0.a     // Catch:{ all -> 0x005c }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ all -> 0x005c }
            int r3 = r5.a     // Catch:{ all -> 0x005c }
            java.lang.String[] r2 = Vc0.a(r2, r3)     // Catch:{ all -> 0x005c }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ all -> 0x005c }
            boolean r1 = r2.contains(r1)     // Catch:{ all -> 0x005c }
            if (r1 != 0) goto L_0x003d
            monitor-exit(r4)
            return r0
        L_0x003d:
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x005c }
            r0.<init>()     // Catch:{ all -> 0x005c }
            android.content.ComponentName r6 = r6.getComponent()     // Catch:{ all -> 0x005c }
            android.content.Intent r6 = r0.setComponent(r6)     // Catch:{ all -> 0x005c }
            TJ1 r1 = new TJ1     // Catch:{ all -> 0x005c }
            android.content.Context r0 = RN0.a     // Catch:{ all -> 0x005c }
            r1.<init>(r0, r6)     // Catch:{ all -> 0x005c }
        L_0x0051:
            boolean r6 = r1.a()     // Catch:{ all -> 0x005c }
            if (r6 == 0) goto L_0x005a
            r5.a(r1)     // Catch:{ all -> 0x005c }
        L_0x005a:
            monitor-exit(r4)
            return r6
        L_0x005c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x005f:
            monitor-exit(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.ClientManager.mo8363a(android.support.customtabs.CustomTabsSessionToken, android.content.Intent):boolean");
    }

    /* renamed from: a */
    public synchronized void mo8352a() {
        for (CustomTabsSessionToken customTabsSessionToken : new ArrayList(this.f1715a.keySet())) {
            if (this.f1715a.get(customTabsSessionToken).b == null) {
                mo8368c(customTabsSessionToken);
            }
        }
    }
}
