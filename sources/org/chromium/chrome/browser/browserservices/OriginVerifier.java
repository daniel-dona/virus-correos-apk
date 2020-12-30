package org.chromium.chrome.browser.browserservices;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.Oauth2;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.CommandLine;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public class OriginVerifier {

    /* renamed from: h */
    public static final char[] f1694h = "0123456789ABCDEF".toCharArray();

    /* renamed from: i */
    public static final AtomicReference<Set<String>> f1695i = new AtomicReference<>();

    /* renamed from: a */
    public final String f1696a;

    /* renamed from: b */
    public final String f1697b = m1976a(this.f1696a);

    /* renamed from: c */
    public final int f1698c;

    /* renamed from: d */
    public long f1699d;

    /* renamed from: e */
    public OriginVerificationListener f1700e;

    /* renamed from: f */
    public yz1 f1701f;

    /* renamed from: g */
    public long f1702g;

    /* compiled from: PG */
    public interface OriginVerificationListener {
        void onOriginVerified(String str, yz1 yz1, boolean z, Boolean bool);
    }

    /* renamed from: org.chromium.chrome.browser.browserservices.OriginVerifier$b */
    /* compiled from: PG */
    public class C0383b implements Runnable {

        /* renamed from: a */
        public final boolean f1703a;

        /* renamed from: b */
        public final Boolean f1704b;

        public C0383b(boolean z, Boolean bool) {
            this.f1703a = z;
            this.f1704b = bool;
        }

        public void run() {
            OriginVerifier.this.mo8330a(this.f1703a, this.f1704b);
        }
    }

    public OriginVerifier(String str, int i) {
        this.f1696a = str;
        this.f1698c = i;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    /* renamed from: a */
    public static String m1976a(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = Vc0.b(RN0.a.getPackageManager(), str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        try {
            return m1977a(MessageDigest.getInstance("SHA256").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getEncoded()));
        } catch (CertificateEncodingException unused2) {
            VN0.c("OriginVerifier", "Certificate type X509 encoding failed", new Object[0]);
            return null;
        } catch (NoSuchAlgorithmException | CertificateException unused3) {
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m1981b(String str, yz1 yz1, int i) {
        return m1980a(str, yz1, i) || Pz1.b(new Dz1(str, m1976a(str), yz1, i));
    }

    @CalledByNative
    public static void clearBrowsingData() {
        Pz1.a();
    }

    private native void nativeDestroy(long j);

    private native long nativeInit(Profile profile);

    private native boolean nativeVerifyOrigin(long j, String str, String str2, String str3, String str4);

    @CalledByNative
    private void onOriginVerificationResult(int i) {
        if (i == 0) {
            pz1.a(0);
            mo8330a(true, (Boolean) true);
        } else if (i == 1) {
            pz1.a(1);
            mo8330a(false, (Boolean) true);
        } else if (i == 2) {
            VN0.b("OriginVerifier", "Device is offline, checking saved verification result.", new Object[0]);
            mo8327a();
        }
    }

    /* renamed from: c */
    public void mo8333c() {
        this.f1700e = null;
    }

    /* renamed from: b */
    public void mo8332b() {
        long j = this.f1699d;
        if (j != 0) {
            nativeDestroy(j);
            this.f1699d = 0;
        }
    }

    /* renamed from: a */
    public static Uri m1975a(String str, yz1 yz1) {
        StringBuilder a = Eo.a("android-app://");
        a.append(yz1.a().getHost());
        a.append("/");
        a.append(str);
        return Uri.parse(a.toString());
    }

    /* renamed from: a */
    public boolean mo8331a(yz1 yz1) {
        return m1979a(this.f1696a, this.f1697b, yz1, this.f1698c);
    }

    /* renamed from: a */
    public static boolean m1979a(String str, String str2, yz1 yz1, int i) {
        return Pz1.b(new Dz1(str, str2, yz1, i));
    }

    /* renamed from: a */
    public void mo8328a(OriginVerificationListener originVerificationListener, yz1 yz1) {
        ThreadUtils.m1462c();
        this.f1701f = yz1;
        this.f1700e = originVerificationListener;
        String b = CommandLine.m1384c().mo7857b("disable-digital-asset-link-verification-for-url");
        String str = null;
        if (TextUtils.isEmpty(b) || !this.f1701f.equals(new yz1(b))) {
            String scheme = this.f1701f.a().getScheme();
            if (TextUtils.isEmpty(scheme) || !Oauth2.HTTPS_PROTOCOL_STRING.equals(scheme.toLowerCase(Locale.US))) {
                VN0.b("OriginVerifier", "Verification failed for %s as not https.", new Object[]{yz1});
                pz1.a(4);
                PostTask.m1565a(iQ2.a, (Runnable) new C0383b(false, (Boolean) null));
            } else if (m1980a(this.f1696a, this.f1701f, this.f1698c)) {
                VN0.b("OriginVerifier", "Verification succeeded for %s, it was overridden.", new Object[]{yz1});
                PostTask.m1565a(iQ2.a, (Runnable) new C0383b(true, (Boolean) null));
            } else {
                if (this.f1699d != 0) {
                    mo8332b();
                }
                if (OP2.a(1).mo9662a()) {
                    this.f1699d = nativeInit(Profile.m2911j().mo9203c());
                    int i = this.f1698c;
                    if (i == 1) {
                        str = "delegate_permission/common.use_as_origin";
                    } else if (i == 2) {
                        str = "delegate_permission/common.handle_all_urls";
                    }
                    String str2 = str;
                    this.f1702g = SystemClock.uptimeMillis();
                    if (!nativeVerifyOrigin(this.f1699d, this.f1696a, this.f1697b, this.f1701f.toString(), str2)) {
                        pz1.a(5);
                        PostTask.m1565a(iQ2.a, (Runnable) new C0383b(false, false));
                    }
                }
            }
        } else {
            VN0.b("OriginVerifier", "Verification skipped for %s due to command line flag.", new Object[]{yz1});
            PostTask.m1565a(iQ2.a, (Runnable) new C0383b(true, (Boolean) null));
        }
    }

    /* renamed from: a */
    public static boolean m1980a(String str, yz1 yz1, int i) {
        if (f1695i.get() == null) {
            return false;
        }
        return f1695i.get().contains(new Dz1(str, BuildConfig.FLAVOR, yz1, i).toString());
    }

    /* renamed from: a */
    public static String m1977a(byte[] bArr) {
        StringBuilder sb = new StringBuilder((bArr.length * 3) - 1);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(f1694h[(bArr[i] & 240) >>> 4]);
            sb.append(f1694h[bArr[i] & 15]);
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo8330a(boolean z, Boolean bool) {
        Object[] objArr = new Object[1];
        objArr[0] = z ? "succeeded" : "failed";
        VN0.b("OriginVerifier", "Verification %s.", objArr);
        if (z) {
            Pz1.a(new Dz1(this.f1696a, this.f1697b, this.f1701f, this.f1698c));
        }
        mo8329a(z);
        OriginVerificationListener originVerificationListener = this.f1700e;
        if (originVerificationListener != null) {
            originVerificationListener.onOriginVerified(this.f1696a, this.f1701f, z, bool);
        }
        if (bool != null) {
            RecordHistogram.m1550d(bool.booleanValue() ? "BrowserServices.VerificationTime.Online" : "BrowserServices.VerificationTime.Offline", SystemClock.uptimeMillis() - this.f1702g);
        }
        mo8332b();
    }

    /* renamed from: a */
    public final void mo8329a(boolean z) {
        Dz1 dz1 = new Dz1(this.f1696a, this.f1697b, this.f1701f, this.f1698c);
        if (z) {
            Pz1.a(dz1);
        } else {
            Pz1.c(dz1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        qI.a.a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        throw r2;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8327a() {
        /*
            r6 = this;
            pO0 r0 = pO0.a()
            Dz1 r1 = new Dz1     // Catch:{ all -> 0x0029 }
            java.lang.String r2 = r6.f1696a     // Catch:{ all -> 0x0029 }
            java.lang.String r3 = r6.f1697b     // Catch:{ all -> 0x0029 }
            yz1 r4 = r6.f1701f     // Catch:{ all -> 0x0029 }
            int r5 = r6.f1698c     // Catch:{ all -> 0x0029 }
            r1.<init>(r2, r3, r4, r5)     // Catch:{ all -> 0x0029 }
            boolean r1 = Pz1.b(r1)     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0019
            r2 = 2
            goto L_0x001a
        L_0x0019:
            r2 = 3
        L_0x001a:
            pz1.a(r2)     // Catch:{ all -> 0x0029 }
            r2 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0029 }
            r6.mo8330a((boolean) r1, (java.lang.Boolean) r2)     // Catch:{ all -> 0x0029 }
            r0.close()
            return
        L_0x0029:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002b }
        L_0x002b:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0030 }
            goto L_0x0036
        L_0x0030:
            r0 = move-exception
            kI r3 = qI.a
            r3.a(r1, r0)
        L_0x0036:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.browserservices.OriginVerifier.mo8327a():void");
    }
}
