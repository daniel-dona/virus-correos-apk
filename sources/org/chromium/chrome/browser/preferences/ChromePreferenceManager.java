package org.chromium.chrome.browser.preferences;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public class ChromePreferenceManager {

    /* renamed from: a */
    public final SharedPreferences f2175a = QN0.a;

    /* renamed from: b */
    public final Map<Observer, SharedPreferences.OnSharedPreferenceChangeListener> f2176b = new HashMap();

    public /* synthetic */ ChromePreferenceManager(rl2 rl2) {
    }

    /* renamed from: c */
    public static ChromePreferenceManager m2734c() {
        return sl2.a;
    }

    /* renamed from: a */
    public void mo9024a(Observer observer) {
        ql2 ql2 = new ql2(observer);
        this.f2176b.put(observer, ql2);
        this.f2175a.registerOnSharedPreferenceChangeListener(ql2);
    }

    /* renamed from: b */
    public void mo9028b(String str, int i) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.putInt(mo9038k(str), i);
        edit.apply();
    }

    /* renamed from: d */
    public void mo9031d(String str) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.putInt(mo9020a(str), this.f2175a.getInt(mo9020a(str), 0) + 1);
        edit.apply();
    }

    /* renamed from: e */
    public void mo9032e(String str) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.putInt(mo9038k(str), this.f2175a.getInt(mo9038k(str), 0) + 1);
        edit.apply();
    }

    /* renamed from: f */
    public int mo9033f(String str) {
        int i = this.f2175a.getInt(str, 0) + 1;
        Eo.a(this.f2175a, str, i);
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        m2733a(r4, r0);
     */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo9034g(java.lang.String r4) {
        /*
            r3 = this;
            pO0 r0 = pO0.a()
            android.content.SharedPreferences r1 = r3.f2175a     // Catch:{ all -> 0x0010 }
            r2 = 0
            int r4 = r1.getInt(r4, r2)     // Catch:{ all -> 0x0010 }
            r1 = 0
            m2733a((java.lang.Throwable) r1, (pO0) r0)
            return r4
        L_0x0010:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r1 = move-exception
            m2733a((java.lang.Throwable) r4, (pO0) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.preferences.ChromePreferenceManager.mo9034g(java.lang.String):int");
    }

    /* renamed from: h */
    public void mo9035h(String str) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.remove(str);
        edit.apply();
    }

    /* renamed from: i */
    public void mo9036i(String str) {
        HashSet hashSet = new HashSet(mo9022a());
        if (hashSet.remove(str)) {
            this.f2175a.edit().putStringSet("trusted_web_activity_disclosure_accepted_packages", hashSet).apply();
        }
    }

    /* renamed from: j */
    public void mo9037j(String str) {
        HashSet hashSet = new HashSet(mo9022a());
        hashSet.add(str);
        this.f2175a.edit().putStringSet("trusted_web_activity_disclosure_accepted_packages", hashSet).apply();
    }

    /* renamed from: k */
    public final String mo9038k(String str) {
        return str.toLowerCase(Locale.US) + "_crash_success_upload";
    }

    /* renamed from: c */
    public int mo9030c(String str) {
        return this.f2175a.getInt(mo9038k(str), 0);
    }

    /* renamed from: a */
    public void mo9023a(String str, int i) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.putInt(mo9020a(str), i);
        edit.apply();
    }

    /* renamed from: b */
    public int mo9026b(String str) {
        return this.f2175a.getInt(mo9020a(str), 0);
    }

    /* renamed from: b */
    public Set<String> mo9027b() {
        return new HashSet(this.f2175a.getStringSet("verified_digital_asset_links", Collections.emptySet()));
    }

    /* renamed from: a */
    public final String mo9020a(String str) {
        return str.toLowerCase(Locale.US) + "_crash_failure_upload";
    }

    /* renamed from: a */
    public final Set<String> mo9022a() {
        return this.f2175a.getStringSet("trusted_web_activity_disclosure_accepted_packages", Collections.emptySet());
    }

    /* renamed from: b */
    public void mo9029b(String str, String str2) {
        SharedPreferences.Editor edit = this.f2175a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    /* renamed from: a */
    public static /* synthetic */ void m2733a(Throwable th, pO0 po0) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        m2733a(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r4 = move-exception;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long mo9019a(java.lang.String r3, long r4) {
        /*
            r2 = this;
            pO0 r0 = pO0.a()
            android.content.SharedPreferences r1 = r2.f2175a     // Catch:{ all -> 0x0011 }
            long r3 = r1.getLong(r3, r4)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r5 = 0
            m2733a((java.lang.Throwable) r5, (pO0) r0)
        L_0x0010:
            return r3
        L_0x0011:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r4 = move-exception
            if (r0 == 0) goto L_0x0019
            m2733a((java.lang.Throwable) r3, (pO0) r0)
        L_0x0019:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.preferences.ChromePreferenceManager.mo9019a(java.lang.String, long):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        m2733a(r3, r0);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo9025a(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            pO0 r0 = pO0.a()
            android.content.SharedPreferences r1 = r2.f2175a     // Catch:{ all -> 0x000f }
            boolean r3 = r1.getBoolean(r3, r4)     // Catch:{ all -> 0x000f }
            r4 = 0
            m2733a((java.lang.Throwable) r4, (pO0) r0)
            return r3
        L_0x000f:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r4 = move-exception
            m2733a((java.lang.Throwable) r3, (pO0) r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.preferences.ChromePreferenceManager.mo9025a(java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        m2733a(r3, r0);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String mo9021a(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            pO0 r0 = pO0.a()
            android.content.SharedPreferences r1 = r2.f2175a     // Catch:{ all -> 0x000f }
            java.lang.String r3 = r1.getString(r3, r4)     // Catch:{ all -> 0x000f }
            r4 = 0
            m2733a((java.lang.Throwable) r4, (pO0) r0)
            return r3
        L_0x000f:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r4 = move-exception
            m2733a((java.lang.Throwable) r3, (pO0) r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.preferences.ChromePreferenceManager.mo9021a(java.lang.String, java.lang.String):java.lang.String");
    }
}
