package org.chromium.chrome.browser.installedapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.google.protobuf.ByteString;
import org.chromium.base.task.PostTask;
import org.chromium.installedapp.mojom.InstalledAppProvider;
import org.chromium.mojo.system.MojoException;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: PG */
public class InstalledAppProviderImpl implements InstalledAppProvider {

    /* renamed from: a */
    public final FrameUrlDelegate f2055a;

    /* renamed from: b */
    public final Context f2056b;

    /* renamed from: c */
    public final c32 f2057c;

    static {
        Class<InstalledAppProviderImpl> cls = InstalledAppProviderImpl.class;
    }

    public InstalledAppProviderImpl(FrameUrlDelegate frameUrlDelegate, Context context, c32 c32) {
        this.f2055a = frameUrlDelegate;
        this.f2056b = context;
        this.f2057c = c32;
    }

    /* renamed from: a */
    public void mo8810a(MojoException mojoException) {
    }

    public void close() {
    }

    /* renamed from: a */
    public void mo8811a(m53[] m53Arr, InstalledAppProvider.FilterInstalledAppsResponse filterInstalledAppsResponse) {
        if (this.f2055a.isIncognito()) {
            filterInstalledAppsResponse.call(new m53[0]);
        } else {
            new a32(this, m53Arr, this.f2055a.getUrl(), filterInstalledAppsResponse).a(AP0.f);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081 A[SYNTHETIC, Splitter:B:32:0x0081] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<m53[], java.lang.Integer> mo8808a(m53[] r17, java.net.URI r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            org.chromium.base.ThreadUtils.m1456a()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.Context r3 = r1.f2056b
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            int r4 = r0.length
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x0016:
            if (r6 >= r4) goto L_0x00d6
            r8 = r0[r6]
            java.lang.String r9 = r8.b
            java.lang.String r10 = "play"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00d0
            java.lang.String r9 = r8.d
            if (r9 == 0) goto L_0x00d0
            java.lang.String r10 = "instantapp"
            boolean r10 = r10.equals(r9)
            java.lang.String r11 = "instantapp:holdback"
            r12 = 1
            if (r10 != 0) goto L_0x003c
            boolean r9 = r11.equals(r9)
            if (r9 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            r9 = 0
            goto L_0x003d
        L_0x003c:
            r9 = 1
        L_0x003d:
            if (r9 == 0) goto L_0x004e
            c32 r9 = r1.f2057c
            r18.toString()
            java.lang.String r8 = r8.d
            r11.equals(r8)
            r9.b()
            goto L_0x00d0
        L_0x004e:
            java.lang.String r9 = r8.d
            java.lang.String r10 = "HmacSHA256"
            byte[] r11 = b32.a
            if (r11 != 0) goto L_0x008b
            r11 = 20
            r13 = 0
            java.io.FileInputStream r14 = new java.io.FileInputStream     // Catch:{ all -> 0x007e }
            java.lang.String r15 = "/dev/urandom"
            r14.<init>(r15)     // Catch:{ all -> 0x007e }
            byte[] r11 = new byte[r11]     // Catch:{ all -> 0x007b }
            int r13 = r11.length     // Catch:{ all -> 0x007b }
            int r15 = r14.read(r11)     // Catch:{ all -> 0x007b }
            if (r13 != r15) goto L_0x0073
            r14.close()     // Catch:{ IOException -> 0x0071, GeneralSecurityException -> 0x006f }
            b32.a = r11     // Catch:{ IOException -> 0x0071, GeneralSecurityException -> 0x006f }
            goto L_0x008b
        L_0x006f:
            r0 = move-exception
            goto L_0x0085
        L_0x0071:
            r0 = move-exception
            goto L_0x0085
        L_0x0073:
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x007b }
            java.lang.String r2 = "Not enough random data available"
            r0.<init>(r2)     // Catch:{ all -> 0x007b }
            throw r0     // Catch:{ all -> 0x007b }
        L_0x007b:
            r0 = move-exception
            r13 = r14
            goto L_0x007f
        L_0x007e:
            r0 = move-exception
        L_0x007f:
            if (r13 == 0) goto L_0x0084
            r13.close()     // Catch:{ IOException -> 0x0071, GeneralSecurityException -> 0x006f }
        L_0x0084:
            throw r0     // Catch:{ IOException -> 0x0071, GeneralSecurityException -> 0x006f }
        L_0x0085:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r0)
            throw r2
        L_0x008b:
            byte[] r11 = b32.a
            javax.crypto.Mac r13 = javax.crypto.Mac.getInstance(r10)     // Catch:{ NoSuchAlgorithmException -> 0x00c8 }
            byte[] r9 = ON0.a(r9)
            javax.crypto.spec.SecretKeySpec r14 = new javax.crypto.spec.SecretKeySpec
            r14.<init>(r11, r10)
            r13.init(r14)     // Catch:{ InvalidKeyException -> 0x00c0 }
            byte[] r9 = r13.doFinal(r9)
            byte r10 = r9[r5]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r10 = r10 << 8
            byte r9 = r9[r12]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r9 = r9 | r10
            short r9 = (short) r9
            r9 = r9 & 1023(0x3ff, float:1.434E-42)
            int r9 = r9 / 100
            int r7 = r7 + r9
            java.lang.String r9 = r8.d
            r10 = r18
            boolean r9 = m2504a((java.lang.String) r9, (java.net.URI) r10, (android.content.pm.PackageManager) r3)
            if (r9 == 0) goto L_0x00d2
            r2.add(r8)
            goto L_0x00d2
        L_0x00c0:
            r0 = move-exception
            r2 = r0
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x00c8:
            r0 = move-exception
            r2 = r0
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x00d0:
            r10 = r18
        L_0x00d2:
            int r6 = r6 + 1
            goto L_0x0016
        L_0x00d6:
            int r0 = r2.size()
            m53[] r0 = new m53[r0]
            r2.toArray(r0)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            android.util.Pair r0 = android.util.Pair.create(r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.installedapp.InstalledAppProviderImpl.mo8808a(m53[], java.net.URI):android.util.Pair");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m2504a(java.lang.String r5, java.net.URI r6, android.content.pm.PackageManager r7) {
        /*
            org.chromium.base.ThreadUtils.m1456a()
            r0 = 0
            if (r6 != 0) goto L_0x0007
            return r0
        L_0x0007:
            org.json.JSONArray r5 = m2503a((java.lang.String) r5, (android.content.pm.PackageManager) r7)     // Catch:{ NameNotFoundException -> 0x006f }
            r7 = 0
        L_0x000c:
            int r1 = r5.length()
            if (r7 >= r1) goto L_0x006f
            org.json.JSONObject r1 = r5.getJSONObject(r7)     // Catch:{ JSONException -> 0x006c }
            r2 = 0
            java.lang.String r3 = "target"
            org.json.JSONObject r1 = r1.getJSONObject(r3)     // Catch:{  }
            java.lang.String r3 = "namespace"
            java.lang.String r3 = r1.getString(r3)     // Catch:{ JSONException -> 0x002a }
            java.lang.String r4 = "web"
            boolean r3 = r3.equals(r4)
            goto L_0x002b
        L_0x002a:
            r3 = 0
        L_0x002b:
            if (r3 != 0) goto L_0x002e
            goto L_0x003a
        L_0x002e:
            java.net.URI r3 = new java.net.URI     // Catch:{ JSONException -> 0x003a }
            java.lang.String r4 = "site"
            java.lang.String r1 = r1.getString(r4)     // Catch:{ JSONException -> 0x003a }
            r3.<init>(r1)     // Catch:{ JSONException -> 0x003a }
            r2 = r3
        L_0x003a:
            if (r2 == 0) goto L_0x006c
            java.lang.String r1 = r2.getScheme()
            r3 = 1
            if (r1 == 0) goto L_0x0068
            java.lang.String r1 = r2.getAuthority()
            if (r1 != 0) goto L_0x004a
            goto L_0x0068
        L_0x004a:
            java.lang.String r1 = r2.getScheme()
            java.lang.String r4 = r6.getScheme()
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0068
            java.lang.String r1 = r2.getAuthority()
            java.lang.String r2 = r6.getAuthority()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = 1
            goto L_0x0069
        L_0x0068:
            r1 = 0
        L_0x0069:
            if (r1 == 0) goto L_0x006c
            return r3
        L_0x006c:
            int r7 = r7 + 1
            goto L_0x000c
        L_0x006f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.installedapp.InstalledAppProviderImpl.m2504a(java.lang.String, java.net.URI, android.content.pm.PackageManager):boolean");
    }

    /* renamed from: a */
    public static JSONArray m2503a(String str, PackageManager packageManager) throws PackageManager.NameNotFoundException {
        ApplicationInfo a = Vc0.a(packageManager, str, ByteString.CONCATENATE_BY_COPY_SIZE);
        if (a == null || a.metaData == null) {
            return new JSONArray();
        }
        int i = a.metaData.getInt("asset_statements");
        if (i == 0) {
            return new JSONArray();
        }
        try {
            try {
                return new JSONArray(Vc0.b(packageManager, a).getString(i));
            } catch (JSONException unused) {
                StringBuilder c = Eo.c("Android package ", str, " has JSON syntax error in asset statements resource (0x");
                c.append(Integer.toHexString(i));
                c.append(").");
                VN0.c("InstalledAppProvider", c.toString(), new Object[0]);
                return new JSONArray();
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder c2 = Eo.c("Android package ", str, " missing asset statements resource (0x");
            c2.append(Integer.toHexString(i));
            c2.append(").");
            VN0.c("InstalledAppProvider", c2.toString(), new Object[0]);
            return new JSONArray();
        }
    }

    /* renamed from: a */
    public void mo8809a(Runnable runnable, long j) {
        PostTask.m1566a(iQ2.a, runnable, j);
    }
}
