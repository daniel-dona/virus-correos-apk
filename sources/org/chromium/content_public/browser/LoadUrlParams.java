package org.chromium.content_public.browser;

import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.util.Locale;
import java.util.Map;
import org.chromium.content_public.common.ResourceRequestBody;

/* compiled from: PG */
public class LoadUrlParams {

    /* renamed from: a */
    public String f2423a;

    /* renamed from: b */
    public String f2424b;

    /* renamed from: c */
    public int f2425c;

    /* renamed from: d */
    public int f2426d;

    /* renamed from: e */
    public oQ2 f2427e;

    /* renamed from: f */
    public Map<String, String> f2428f;

    /* renamed from: g */
    public String f2429g;

    /* renamed from: h */
    public int f2430h;

    /* renamed from: i */
    public ResourceRequestBody f2431i;

    /* renamed from: j */
    public String f2432j;

    /* renamed from: k */
    public String f2433k;

    /* renamed from: l */
    public String f2434l;

    /* renamed from: m */
    public boolean f2435m;

    /* renamed from: n */
    public boolean f2436n;

    /* renamed from: o */
    public long f2437o;

    /* renamed from: p */
    public long f2438p;

    /* renamed from: q */
    public boolean f2439q;

    /* renamed from: r */
    public boolean f2440r;

    public LoadUrlParams(String str) {
        this(str, 0);
    }

    /* renamed from: a */
    public static String m3398a(String str, String str2, boolean z, String str3) {
        StringBuilder b = Eo.b("data:", str2);
        if (str3 != null && !str3.isEmpty()) {
            b.append(";charset=" + str3);
        }
        if (z) {
            b.append(";base64");
        }
        return Eo.a(b, ",", str);
    }

    /* renamed from: b */
    public static LoadUrlParams m3399b(String str, String str2, boolean z, String str3) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(m3398a(str, str2, z, str3), 0);
        loadUrlParams.mo9703a(2);
        loadUrlParams.mo9710b(1);
        return loadUrlParams;
    }

    public static native boolean nativeIsDataScheme(String str);

    /* renamed from: b */
    public boolean mo9714b() {
        return false;
    }

    /* renamed from: c */
    public void mo9716c(String str) {
        this.f2424b = str;
    }

    /* renamed from: d */
    public void mo9719d(String str) {
        this.f2423a = str;
    }

    /* renamed from: e */
    public String mo9721e() {
        if (this.f2428f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : this.f2428f.entrySet()) {
            if (sb.length() > 0) {
                sb.append(AbstractAccountCredentialCache.NEW_LINE);
            }
            sb.append(((String) next.getKey()).toLowerCase(Locale.US));
            sb.append(":");
            sb.append((String) next.getValue());
        }
        return sb.toString();
    }

    /* renamed from: f */
    public void mo9723f(String str) {
        this.f2433k = str;
    }

    /* renamed from: g */
    public String mo9725g() {
        return this.f2424b;
    }

    /* renamed from: h */
    public long mo9726h() {
        return this.f2438p;
    }

    /* renamed from: i */
    public long mo9727i() {
        return this.f2437o;
    }

    /* renamed from: j */
    public boolean mo9728j() {
        return this.f2435m;
    }

    /* renamed from: k */
    public int mo9729k() {
        return this.f2425c;
    }

    /* renamed from: l */
    public ResourceRequestBody mo9730l() {
        return this.f2431i;
    }

    /* renamed from: m */
    public oQ2 mo9731m() {
        return this.f2427e;
    }

    /* renamed from: n */
    public boolean mo9732n() {
        return this.f2440r;
    }

    /* renamed from: o */
    public boolean mo9733o() {
        return this.f2436n;
    }

    /* renamed from: p */
    public int mo9734p() {
        return this.f2426d;
    }

    /* renamed from: q */
    public String mo9735q() {
        return this.f2423a;
    }

    /* renamed from: r */
    public int mo9736r() {
        return this.f2430h;
    }

    /* renamed from: s */
    public String mo9737s() {
        return this.f2429g;
    }

    /* renamed from: t */
    public String mo9738t() {
        return this.f2433k;
    }

    public LoadUrlParams(String str, int i) {
        this.f2423a = str;
        this.f2426d = i;
        this.f2425c = 0;
        this.f2430h = 0;
        this.f2431i = null;
        this.f2432j = null;
        this.f2433k = null;
        this.f2434l = null;
    }

    /* renamed from: c */
    public String mo9715c() {
        return this.f2434l;
    }

    /* renamed from: d */
    public Map<String, String> mo9718d() {
        return this.f2428f;
    }

    /* renamed from: f */
    public boolean mo9724f() {
        return this.f2439q;
    }

    /* renamed from: c */
    public void mo9717c(boolean z) {
        this.f2440r = z;
    }

    /* renamed from: d */
    public void mo9720d(boolean z) {
        this.f2436n = z;
    }

    /* renamed from: a */
    public String mo9702a() {
        return this.f2432j;
    }

    /* renamed from: b */
    public void mo9710b(int i) {
        this.f2426d = i;
    }

    /* renamed from: a */
    public void mo9703a(int i) {
        this.f2425c = i;
    }

    /* renamed from: b */
    public void mo9712b(String str) {
        this.f2434l = str;
    }

    /* renamed from: a */
    public void mo9707a(oQ2 oq2) {
        this.f2427e = oq2;
    }

    /* renamed from: b */
    public void mo9713b(boolean z) {
        this.f2435m = z;
    }

    /* renamed from: a */
    public void mo9706a(Map<String, String> map) {
        this.f2428f = map;
    }

    /* renamed from: b */
    public void mo9711b(long j) {
        this.f2437o = j;
    }

    /* renamed from: e */
    public void mo9722e(String str) {
        this.f2429g = str;
    }

    /* renamed from: a */
    public void mo9708a(ResourceRequestBody resourceRequestBody) {
        this.f2431i = resourceRequestBody;
    }

    /* renamed from: a */
    public void mo9705a(String str) {
        this.f2432j = str;
    }

    /* renamed from: a */
    public void mo9704a(long j) {
        this.f2438p = j;
    }

    /* renamed from: a */
    public void mo9709a(boolean z) {
        this.f2439q = z;
    }
}
