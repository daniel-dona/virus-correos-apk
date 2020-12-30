package org.chromium.chrome.browser.webapps;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.browsing_data.UrlFilterBridge;

/* compiled from: PG */
public class WebappRegistry {

    /* renamed from: a */
    public HashMap<String, WebappDataStorage> f2353a;

    /* renamed from: b */
    public SharedPreferences f2354b;

    /* renamed from: c */
    public Xz1 f2355c;

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        qI.a.a(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WebappRegistry(org.chromium.chrome.browser.webapps.WebappRegistry.a r4) {
        /*
            r3 = this;
            r3.<init>()
            pO0 r4 = pO0.a()
            android.content.Context r0 = RN0.a     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "webapp_registry"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0024 }
            r4.close()
            r3.f2354b = r0
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            r3.f2353a = r4
            Xz1 r4 = new Xz1
            r4.<init>()
            r3.f2355c = r4
            return
        L_0x0024:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            r4.close()     // Catch:{ all -> 0x002b }
            goto L_0x0031
        L_0x002b:
            r4 = move-exception
            kI r2 = qI.a
            r2.a(r0, r4)
        L_0x0031:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.webapps.WebappRegistry.<init>(org.chromium.chrome.browser.webapps.WebappRegistry$a):void");
    }

    /* renamed from: c */
    public static WebappRegistry m3296c() {
        return b.a;
    }

    @CalledByNative
    public static void clearWebappHistoryForUrls(UrlFilterBridge urlFilterBridge) {
        b.a.mo9591a((qA1) urlFilterBridge);
        urlFilterBridge.a();
    }

    /* renamed from: d */
    public static void m3297d() {
        b.a.mo9590a((String) null, false);
    }

    @CalledByNative
    public static void unregisterWebappsForUrls(UrlFilterBridge urlFilterBridge) {
        b.a.mo9594b((qA1) urlFilterBridge);
        urlFilterBridge.a();
    }

    /* renamed from: b */
    public WebappDataStorage mo9593b(String str) {
        WebappDataStorage webappDataStorage = null;
        int i = 0;
        for (Map.Entry<String, WebappDataStorage> value : this.f2353a.entrySet()) {
            WebappDataStorage webappDataStorage2 = (WebappDataStorage) value.getValue();
            if (!webappDataStorage2.a.startsWith("webapk-")) {
                String string = webappDataStorage2.b.getString("scope", BuildConfig.FLAVOR);
                if (str.startsWith(string) && string.length() > i) {
                    i = string.length();
                    webappDataStorage = webappDataStorage2;
                }
            }
        }
        return webappDataStorage;
    }

    /* renamed from: a */
    public void mo9589a(String str, FetchWebappDataStorageCallback fetchWebappDataStorageCallback) {
        new a(this, str, fetchWebappDataStorageCallback).a(AP0.f);
    }

    /* renamed from: c */
    public boolean mo9595c(String str) {
        for (Map.Entry<String, WebappDataStorage> value : this.f2353a.entrySet()) {
            WebappDataStorage webappDataStorage = (WebappDataStorage) value.getValue();
            if (webappDataStorage.a.startsWith("webapk-") && str.startsWith(webappDataStorage.b.getString("scope", BuildConfig.FLAVOR))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public WebappDataStorage mo9587a(String str) {
        return this.f2353a.get(str);
    }

    /* renamed from: a */
    public List<String> mo9586a() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.f2353a.entrySet()) {
            WebappDataStorage webappDataStorage = (WebappDataStorage) next.getValue();
            if (!TextUtils.isEmpty(webappDataStorage.h()) && cO0.b(RN0.a, webappDataStorage.i())) {
                arrayList.add((String) next.getKey());
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public Xz1 mo9592b() {
        return this.f2355c;
    }

    /* renamed from: b */
    public void mo9594b(qA1 qa1) {
        Iterator<Map.Entry<String, WebappDataStorage>> it = this.f2353a.entrySet().iterator();
        while (it.hasNext()) {
            WebappDataStorage webappDataStorage = (WebappDataStorage) it.next().getValue();
            if (qa1.a(webappDataStorage.b.getString("url", BuildConfig.FLAVOR))) {
                webappDataStorage.b();
                webappDataStorage.b.edit().clear().apply();
                it.remove();
            }
        }
        if (this.f2353a.isEmpty()) {
            this.f2354b.edit().clear().apply();
        } else {
            this.f2354b.edit().putStringSet("webapp_set", this.f2353a.keySet()).apply();
        }
    }

    /* renamed from: a */
    public void mo9588a(long j) {
        if (j - this.f2354b.getLong("last_cleanup", 0) >= 2419200000L) {
            Iterator<Map.Entry<String, WebappDataStorage>> it = this.f2353a.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                WebappDataStorage webappDataStorage = (WebappDataStorage) next.getValue();
                String i = webappDataStorage.i();
                if (i != null) {
                    if (((String) next.getKey()).startsWith("webapk-") && cO0.b(RN0.a, i)) {
                    }
                } else if (j - webappDataStorage.f() < 7862400000L) {
                }
                webappDataStorage.b();
                webappDataStorage.b.edit().clear().apply();
                it.remove();
            }
            this.f2354b.edit().putLong("last_cleanup", j).putStringSet("webapp_set", this.f2353a.keySet()).apply();
        }
    }

    /* renamed from: a */
    public void mo9591a(qA1 qa1) {
        for (Map.Entry<String, WebappDataStorage> value : this.f2353a.entrySet()) {
            WebappDataStorage webappDataStorage = (WebappDataStorage) value.getValue();
            if (qa1.a(webappDataStorage.b.getString("url", BuildConfig.FLAVOR))) {
                webappDataStorage.b();
                SharedPreferences.Editor edit = webappDataStorage.b.edit();
                edit.remove("last_used");
                edit.remove("has_been_launched");
                edit.remove("url");
                edit.remove("scope");
                edit.remove("last_check_web_manifest_update_time");
                edit.remove("last_update_request_complete_time");
                edit.remove("did_last_update_request_succeed");
                edit.remove("relax_updates");
                edit.remove("show_disclosure");
                edit.apply();
            }
        }
    }

    /* renamed from: a */
    public final void mo9590a(String str, boolean z) {
        Set<String> stringSet = this.f2354b.getStringSet("webapp_set", Collections.emptySet());
        if (str == null || str.isEmpty()) {
            for (String next : stringSet) {
                if (z || !this.f2353a.containsKey(next)) {
                    this.f2353a.put(next, WebappDataStorage.b(next));
                }
            }
            this.f2355c.a();
        } else if (!stringSet.contains(str)) {
        } else {
            if (z || !this.f2353a.containsKey(str)) {
                this.f2353a.put(str, WebappDataStorage.b(str));
            }
        }
    }
}
