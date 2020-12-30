package org.chromium.chrome.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import com.microsoft.aad.adal.Oauth2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;

/* compiled from: PG */
public class WarmupManager {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: i */
    public static WarmupManager f1673i;

    /* renamed from: a */
    public final Set<String> f1674a = new HashSet();

    /* renamed from: b */
    public final Map<String, Profile> f1675b = new HashMap();

    /* renamed from: c */
    public int f1676c;

    /* renamed from: d */
    public ViewGroup f1677d;

    /* renamed from: e */
    public WebContents f1678e;

    /* renamed from: f */
    public long f1679f;

    /* renamed from: g */
    public a f1680g;

    /* renamed from: h */
    public boolean f1681h;

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        qI.a.a(r4, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005c, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005f, code lost:
        if (r0 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006b, code lost:
        throw r5;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.view.ViewGroup m1949b(android.content.Context r4, int r5, int r6) {
        /*
            java.lang.String r0 = "WarmupManager.inflateViewHierarchy"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)     // Catch:{ InflateException -> 0x006c }
            pO0 r1 = pO0.a()     // Catch:{ all -> 0x004d }
            android.view.ContextThemeWrapper r2 = new android.view.ContextThemeWrapper     // Catch:{ all -> 0x004f }
            int r3 = org.chromium.chrome.browser.ChromeActivity.m1627O1()     // Catch:{ all -> 0x004f }
            r2.<init>(r4, r3)     // Catch:{ all -> 0x004f }
            android.widget.FrameLayout r4 = new android.widget.FrameLayout     // Catch:{ all -> 0x004f }
            r4.<init>(r2)     // Catch:{ all -> 0x004f }
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)     // Catch:{ all -> 0x004f }
            int r3 = rx0.main     // Catch:{ all -> 0x004f }
            android.view.View r4 = r2.inflate(r3, r4)     // Catch:{ all -> 0x004f }
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch:{ all -> 0x004f }
            r2 = -1
            if (r5 == r2) goto L_0x0035
            int r3 = ox0.control_container_stub     // Catch:{ all -> 0x004f }
            android.view.View r3 = r4.findViewById(r3)     // Catch:{ all -> 0x004f }
            android.view.ViewStub r3 = (android.view.ViewStub) r3     // Catch:{ all -> 0x004f }
            r3.setLayoutResource(r5)     // Catch:{ all -> 0x004f }
            r3.inflate()     // Catch:{ all -> 0x004f }
        L_0x0035:
            int r5 = ox0.control_container     // Catch:{ all -> 0x004f }
            android.view.View r5 = r4.findViewById(r5)     // Catch:{ all -> 0x004f }
            NG2 r5 = (NG2) r5     // Catch:{ all -> 0x004f }
            if (r6 == r2) goto L_0x0044
            if (r5 == 0) goto L_0x0044
            r5.a(r6)     // Catch:{ all -> 0x004f }
        L_0x0044:
            r1.close()     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ InflateException -> 0x006c }
        L_0x004c:
            return r4
        L_0x004d:
            r4 = move-exception
            goto L_0x005d
        L_0x004f:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x0056 }
            goto L_0x005c
        L_0x0056:
            r6 = move-exception
            kI r1 = qI.a     // Catch:{ all -> 0x004d }
            r1.a(r4, r6)     // Catch:{ all -> 0x004d }
        L_0x005c:
            throw r5     // Catch:{ all -> 0x004d }
        L_0x005d:
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005e:
            r5 = move-exception
            if (r0 == 0) goto L_0x006b
            r0.close()     // Catch:{ all -> 0x0065 }
            goto L_0x006b
        L_0x0065:
            r6 = move-exception
            kI r0 = qI.a     // Catch:{ InflateException -> 0x006c }
            r0.a(r4, r6)     // Catch:{ InflateException -> 0x006c }
        L_0x006b:
            throw r5     // Catch:{ InflateException -> 0x006c }
        L_0x006c:
            r4 = move-exception
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r4
            java.lang.String r4 = "WarmupManager"
            java.lang.String r6 = "Inflation exception."
            VN0.a(r4, r6, r5)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.WarmupManager.m1949b(android.content.Context, int, int):android.view.ViewGroup");
    }

    /* renamed from: e */
    public static WarmupManager m1950e() {
        ThreadUtils.m1462c();
        if (f1673i == null) {
            f1673i = new WarmupManager();
        }
        return f1673i;
    }

    public static native void nativePreconnectUrlAndSubresources(Profile profile, String str);

    public static native void nativeStartPreconnectPredictorInitialization(Profile profile);

    public static native void nativeWarmupSpareRenderer(Profile profile);

    /* renamed from: a */
    public void mo8302a(Context context, int i, int i2) {
        ThreadUtils.m1462c();
        if (this.f1677d == null || this.f1676c != i) {
            this.f1677d = m1949b(context, i, i2);
            this.f1676c = i;
        }
    }

    /* renamed from: c */
    public final void mo8310c() {
        this.f1678e.mo9763a((lQ2) this.f1680g);
        this.f1678e.destroy();
        this.f1678e = null;
        this.f1680g = null;
    }

    /* renamed from: d */
    public boolean mo8311d() {
        return this.f1678e != null;
    }

    /* renamed from: a */
    public void mo8303a(ViewGroup viewGroup) {
        ThreadUtils.m1462c();
        ViewGroup viewGroup2 = this.f1677d;
        this.f1677d = null;
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeView(childAt);
                viewGroup.addView(childAt);
            }
        }
    }

    /* renamed from: a */
    public static void m1947a(ViewGroup viewGroup, ViewGroup viewGroup2) {
        while (viewGroup.getChildCount() > 0) {
            View childAt = viewGroup.getChildAt(0);
            viewGroup.removeView(childAt);
            viewGroup2.addView(childAt);
        }
    }

    /* renamed from: a */
    public boolean mo8307a(int i) {
        ThreadUtils.m1462c();
        return this.f1677d != null && this.f1676c == i;
    }

    /* renamed from: a */
    public void mo8301a() {
        ThreadUtils.m1462c();
        this.f1677d = null;
    }

    /* renamed from: a */
    public void mo8304a(String str) {
        ThreadUtils.m1462c();
        this.f1674a.add(str);
        new Vu1(this, str).a(AP0.f);
    }

    /* renamed from: a */
    public static void m1948a(Profile profile) {
        ThreadUtils.m1462c();
        nativeStartPreconnectPredictorInitialization(profile);
    }

    /* renamed from: b */
    public void mo8308b() {
        ThreadUtils.m1462c();
        if (this.f1678e != null) {
            mo8309b(3);
            this.f1678e.mo9763a((lQ2) this.f1680g);
            this.f1678e.destroy();
            this.f1678e = null;
            this.f1680g = null;
        }
    }

    /* renamed from: a */
    public void mo8305a(Profile profile, String str) {
        ThreadUtils.m1462c();
        Uri parse = Uri.parse(str);
        if (parse != null) {
            String scheme = parse.normalizeScheme().getScheme();
            if (!"http".equals(scheme) && !Oauth2.HTTPS_PROTOCOL_STRING.equals(scheme)) {
                return;
            }
            if (this.f1674a.contains(str)) {
                this.f1675b.put(str, profile);
            } else {
                nativePreconnectUrlAndSubresources(profile, str);
            }
        }
    }

    /* renamed from: b */
    public final void mo8309b(int i) {
        if (this.f1681h) {
            RecordHistogram.m1539a("CustomTabs.SpareWebContents.Status2", i, 5);
        }
    }

    /* renamed from: a */
    public void mo8306a(boolean z) {
        ThreadUtils.m1462c();
        if (LibraryLoader.f1501h.mo7904a() && this.f1678e == null) {
            this.f1681h = z;
            this.f1678e = WebContentsFactory.nativeCreateWebContents(Profile.m2911j(), false, true, true);
            this.f1680g = new a(this, (Vu1) null);
            this.f1678e.mo9769b((lQ2) this.f1680g);
            this.f1679f = SystemClock.elapsedRealtime();
            mo8309b(0);
        }
    }

    /* renamed from: a */
    public WebContents mo8300a(boolean z, boolean z2, boolean z3) {
        WebContents webContents;
        ThreadUtils.m1462c();
        if (z || (webContents = this.f1678e) == null) {
            return null;
        }
        this.f1678e = null;
        webContents.mo9763a((lQ2) this.f1680g);
        this.f1680g = null;
        if (!z2) {
            webContents.mo9740D();
        }
        mo8309b(this.f1681h == z3 ? 1 : 4);
        return webContents;
    }
}
