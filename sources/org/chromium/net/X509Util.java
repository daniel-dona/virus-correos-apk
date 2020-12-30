package org.chromium.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class X509Util {

    /* renamed from: a */
    public static CertificateFactory f2570a;

    /* renamed from: b */
    public static C0472c f2571b;

    /* renamed from: c */
    public static C0471b f2572c;

    /* renamed from: d */
    public static C0472c f2573d;

    /* renamed from: e */
    public static KeyStore f2574e;

    /* renamed from: f */
    public static KeyStore f2575f;

    /* renamed from: g */
    public static File f2576g;

    /* renamed from: h */
    public static Set<Pair<X500Principal, PublicKey>> f2577h;

    /* renamed from: i */
    public static boolean f2578i;

    /* renamed from: j */
    public static final Object f2579j = new Object();

    /* renamed from: k */
    public static final char[] f2580k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: org.chromium.net.X509Util$b */
    /* compiled from: PG */
    public static final class C0471b extends BroadcastReceiver {
        public /* synthetic */ C0471b(C0470a aVar) {
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (Build.VERSION.SDK_INT < 26) {
                z = "android.security.STORAGE_CHANGED".equals(intent.getAction());
            } else if (!"android.security.action.KEYCHAIN_CHANGED".equals(intent.getAction()) && !"android.security.action.TRUST_STORE_CHANGED".equals(intent.getAction()) && (!"android.security.action.KEY_ACCESS_CHANGED".equals(intent.getAction()) || intent.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false))) {
                z = false;
            }
            if (z) {
                try {
                    X509Util.m3616d();
                } catch (CertificateException e) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", e);
                } catch (KeyStoreException e2) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", e2);
                } catch (NoSuchAlgorithmException e3) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", e3);
                }
            }
        }
    }

    /* renamed from: org.chromium.net.X509Util$c */
    /* compiled from: PG */
    public interface C0472c {
        /* renamed from: a */
        List<X509Certificate> mo9935a(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException;
    }

    /* renamed from: org.chromium.net.X509Util$d */
    /* compiled from: PG */
    public static final class C0473d implements C0472c {

        /* renamed from: a */
        public final X509TrustManagerExtensions f2581a;

        @SuppressLint({"NewApi"})
        public C0473d(X509TrustManager x509TrustManager) {
            this.f2581a = new X509TrustManagerExtensions(x509TrustManager);
        }

        @SuppressLint({"NewApi"})
        /* renamed from: a */
        public List<X509Certificate> mo9935a(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
            return this.f2581a.checkServerTrusted(x509CertificateArr, str, str2);
        }
    }

    /* renamed from: a */
    public static C0472c m3608a(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        int length = trustManagers.length;
        int i = 0;
        while (i < length) {
            TrustManager trustManager = trustManagers[i];
            if (trustManager instanceof X509TrustManager) {
                try {
                    return new C0473d((X509TrustManager) trustManager);
                } catch (IllegalArgumentException e) {
                    String name = trustManager.getClass().getName();
                    Log.e("X509Util", "Error creating trust manager (" + name + "): " + e);
                }
            } else {
                i++;
            }
        }
        Log.e("X509Util", "Could not find suitable trust manager");
        return null;
    }

    /* renamed from: b */
    public static void m3613b() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        synchronized (f2579j) {
            m3615c();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:8|9|10|11|12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0028 */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m3615c() throws java.security.cert.CertificateException, java.security.KeyStoreException, java.security.NoSuchAlgorithmException {
        /*
            java.security.cert.CertificateFactory r0 = f2570a
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)
            f2570a = r0
        L_0x000c:
            org.chromium.net.X509Util$c r0 = f2571b
            r1 = 0
            if (r0 != 0) goto L_0x0017
            org.chromium.net.X509Util$c r0 = m3608a((java.security.KeyStore) r1)
            f2571b = r0
        L_0x0017:
            boolean r0 = f2578i
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "AndroidCAStore"
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)     // Catch:{ KeyStoreException -> 0x0046 }
            f2575f = r0     // Catch:{ KeyStoreException -> 0x0046 }
            java.security.KeyStore r0 = f2575f     // Catch:{ IOException -> 0x0028 }
            r0.load(r1)     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            java.io.File r0 = new java.io.File     // Catch:{ KeyStoreException -> 0x0046 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ KeyStoreException -> 0x0046 }
            r2.<init>()     // Catch:{ KeyStoreException -> 0x0046 }
            java.lang.String r3 = "ANDROID_ROOT"
            java.lang.String r3 = java.lang.System.getenv(r3)     // Catch:{ KeyStoreException -> 0x0046 }
            r2.append(r3)     // Catch:{ KeyStoreException -> 0x0046 }
            java.lang.String r3 = "/etc/security/cacerts"
            r2.append(r3)     // Catch:{ KeyStoreException -> 0x0046 }
            java.lang.String r2 = r2.toString()     // Catch:{ KeyStoreException -> 0x0046 }
            r0.<init>(r2)     // Catch:{ KeyStoreException -> 0x0046 }
            f2576g = r0     // Catch:{ KeyStoreException -> 0x0046 }
        L_0x0046:
            r0 = 1
            f2578i = r0
        L_0x0049:
            java.util.Set<android.util.Pair<javax.security.auth.x500.X500Principal, java.security.PublicKey>> r0 = f2577h
            if (r0 != 0) goto L_0x0054
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            f2577h = r0
        L_0x0054:
            java.security.KeyStore r0 = f2574e
            if (r0 != 0) goto L_0x0069
            java.lang.String r0 = java.security.KeyStore.getDefaultType()
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)
            f2574e = r0
            java.security.KeyStore r0 = f2574e     // Catch:{ IOException -> 0x0068 }
            r0.load(r1)     // Catch:{ IOException -> 0x0068 }
            goto L_0x0069
        L_0x0068:
        L_0x0069:
            org.chromium.net.X509Util$c r0 = f2573d
            if (r0 != 0) goto L_0x0075
            java.security.KeyStore r0 = f2574e
            org.chromium.net.X509Util$c r0 = m3608a((java.security.KeyStore) r0)
            f2573d = r0
        L_0x0075:
            org.chromium.net.X509Util$b r0 = f2572c
            if (r0 != 0) goto L_0x00a7
            org.chromium.net.X509Util$b r0 = new org.chromium.net.X509Util$b
            r0.<init>(r1)
            f2572c = r0
            android.content.IntentFilter r0 = new android.content.IntentFilter
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L_0x009b
            java.lang.String r1 = "android.security.action.KEYCHAIN_CHANGED"
            r0.addAction(r1)
            java.lang.String r1 = "android.security.action.KEY_ACCESS_CHANGED"
            r0.addAction(r1)
            java.lang.String r1 = "android.security.action.TRUST_STORE_CHANGED"
            r0.addAction(r1)
            goto L_0x00a0
        L_0x009b:
            java.lang.String r1 = "android.security.STORAGE_CHANGED"
            r0.addAction(r1)
        L_0x00a0:
            android.content.Context r1 = RN0.a
            org.chromium.net.X509Util$b r2 = f2572c
            r1.registerReceiver(r2, r0)
        L_0x00a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.X509Util.m3615c():void");
    }

    /* renamed from: d */
    public static void m3616d() throws KeyStoreException, NoSuchAlgorithmException, CertificateException {
        synchronized (f2579j) {
            f2571b = null;
            f2577h = null;
            m3615c();
        }
        nativeNotifyKeyChainChanged();
    }

    public static native void nativeNotifyKeyChainChanged();

    /* renamed from: b */
    public static X509Certificate m3612b(byte[] bArr) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        m3613b();
        return (X509Certificate) f2570a.generateCertificate(new ByteArrayInputStream(bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0013  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m3614b(java.security.cert.X509Certificate r4) throws java.security.cert.CertificateException {
        /*
            r0 = 0
            java.util.List r4 = r4.getExtendedKeyUsage()     // Catch:{ NullPointerException -> 0x003a }
            r1 = 1
            if (r4 != 0) goto L_0x0009
            return r1
        L_0x0009:
            java.util.Iterator r4 = r4.iterator()
        L_0x000d:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x003a
            java.lang.Object r2 = r4.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "1.3.6.1.5.5.7.3.1"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "2.5.29.37.0"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "2.16.840.1.113730.4.1"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "1.3.6.1.4.1.311.10.3.3"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x000d
        L_0x0039:
            return r1
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.X509Util.m3614b(java.security.cert.X509Certificate):boolean");
    }

    /* renamed from: a */
    public static void m3610a(byte[] bArr) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        m3613b();
        X509Certificate b = m3612b(bArr);
        synchronized (f2579j) {
            KeyStore keyStore = f2574e;
            keyStore.setCertificateEntry("root_cert_" + Integer.toString(f2574e.size()), b);
            f2573d = m3608a(f2574e);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0017 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m3609a() throws java.security.NoSuchAlgorithmException, java.security.cert.CertificateException, java.security.KeyStoreException {
        /*
            m3613b()
            java.lang.Object r0 = f2579j
            monitor-enter(r0)
            java.security.KeyStore r1 = f2574e     // Catch:{ IOException -> 0x0017 }
            r2 = 0
            r1.load(r2)     // Catch:{ IOException -> 0x0017 }
            java.security.KeyStore r1 = f2574e     // Catch:{ IOException -> 0x0017 }
            org.chromium.net.X509Util$c r1 = m3608a((java.security.KeyStore) r1)     // Catch:{ IOException -> 0x0017 }
            f2573d = r1     // Catch:{ IOException -> 0x0017 }
            goto L_0x0017
        L_0x0015:
            r1 = move-exception
            goto L_0x0019
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.X509Util.m3609a():void");
    }

    /* renamed from: a */
    public static boolean m3611a(X509Certificate x509Certificate) throws NoSuchAlgorithmException, KeyStoreException {
        if (f2575f == null) {
            return false;
        }
        Pair pair = new Pair(x509Certificate.getSubjectX500Principal(), x509Certificate.getPublicKey());
        if (f2577h.contains(pair)) {
            return true;
        }
        byte[] digest = MessageDigest.getInstance("MD5").digest(x509Certificate.getSubjectX500Principal().getEncoded());
        char[] cArr = new char[8];
        for (int i = 0; i < 4; i++) {
            int i2 = i * 2;
            char[] cArr2 = f2580k;
            int i3 = 3 - i;
            cArr[i2] = cArr2[(digest[i3] >> 4) & 15];
            cArr[i2 + 1] = cArr2[digest[i3] & 15];
        }
        String str = new String(cArr);
        int i4 = 0;
        while (true) {
            String str2 = str + '.' + i4;
            if (!new File(f2576g, str2).exists()) {
                return false;
            }
            Certificate certificate = f2575f.getCertificate("system:" + str2);
            if (certificate != null) {
                if (!(certificate instanceof X509Certificate)) {
                    Log.e("X509Util", "Anchor " + str2 + " not an X509Certificate: " + certificate.getClass().getName());
                } else {
                    X509Certificate x509Certificate2 = (X509Certificate) certificate;
                    if (x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getSubjectX500Principal()) && x509Certificate.getPublicKey().equals(x509Certificate2.getPublicKey())) {
                        f2577h.add(pair);
                        return true;
                    }
                }
            }
            i4++;
        }
    }

    /* renamed from: a */
    public static AndroidCertVerifyResult m3607a(byte[][] bArr, String str, String str2) throws KeyStoreException, NoSuchAlgorithmException {
        List<X509Certificate> list;
        if (bArr == null || bArr.length == 0 || bArr[0] == null) {
            StringBuilder a = Eo.a("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=");
            a.append(Arrays.deepToString(bArr));
            throw new IllegalArgumentException(a.toString());
        }
        try {
            m3613b();
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(m3612b(bArr[0]));
                for (int i = 1; i < bArr.length; i++) {
                    try {
                        arrayList.add(m3612b(bArr[i]));
                    } catch (CertificateException unused) {
                        Log.w("X509Util", "intermediate " + i + " failed parsing");
                    }
                }
                X509Certificate[] x509CertificateArr = (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                try {
                    x509CertificateArr[0].checkValidity();
                    if (!m3614b(x509CertificateArr[0])) {
                        return new AndroidCertVerifyResult(-6);
                    }
                    synchronized (f2579j) {
                        if (f2571b == null) {
                            AndroidCertVerifyResult androidCertVerifyResult = new AndroidCertVerifyResult(-1);
                            return androidCertVerifyResult;
                        }
                        try {
                            list = f2571b.mo9935a(x509CertificateArr, str, str2);
                        } catch (CertificateException e) {
                            try {
                                list = f2573d.mo9935a(x509CertificateArr, str, str2);
                            } catch (CertificateException unused2) {
                                Log.i("X509Util", "Failed to validate the certificate chain, error: " + e.getMessage());
                                return new AndroidCertVerifyResult(-2);
                            }
                        }
                        AndroidCertVerifyResult androidCertVerifyResult2 = new AndroidCertVerifyResult(0, list.size() > 0 ? m3611a(list.get(list.size() - 1)) : false, list);
                        return androidCertVerifyResult2;
                    }
                } catch (CertificateExpiredException unused3) {
                    return new AndroidCertVerifyResult(-3);
                } catch (CertificateNotYetValidException unused4) {
                    return new AndroidCertVerifyResult(-4);
                } catch (CertificateException unused5) {
                    return new AndroidCertVerifyResult(-1);
                }
            } catch (CertificateException unused6) {
                return new AndroidCertVerifyResult(-5);
            }
        } catch (CertificateException unused7) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
