package org.chromium.net;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.CalledByNativeUnchecked;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class AndroidNetworkLibrary {

    /* renamed from: a */
    public static Boolean f2536a;

    /* renamed from: b */
    public static Boolean f2537b;

    /* renamed from: c */
    public static final Set<InetAddress> f2538c = new HashSet();

    /* renamed from: d */
    public static final Set<String> f2539d = new HashSet();

    /* renamed from: org.chromium.net.AndroidNetworkLibrary$b */
    /* compiled from: PG */
    public static class C0465b {

        /* renamed from: a */
        public static final Method f2541a;

        static {
            Class<FileDescriptor> cls = FileDescriptor.class;
            try {
                f2541a = cls.getMethod("setInt$", new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException("Unable to get FileDescriptor.setInt$", e);
            }
        }

        /* renamed from: a */
        public static FileDescriptor m3574a(int i) {
            try {
                FileDescriptor fileDescriptor = new FileDescriptor();
                f2541a.invoke(fileDescriptor, new Object[]{Integer.valueOf(i)});
                return fileDescriptor;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", e2);
            }
        }
    }

    /* renamed from: org.chromium.net.AndroidNetworkLibrary$c */
    /* compiled from: PG */
    public static class C0466c extends Socket {
        public C0466c(FileDescriptor fileDescriptor) throws IOException {
            super(new Vk3(fileDescriptor));
        }
    }

    static {
        try {
            f2538c.add(InetAddress.getByName("8.8.8.8"));
            f2538c.add(InetAddress.getByName("8.8.4.4"));
            f2538c.add(InetAddress.getByName("2001:4860:4860::8888"));
            f2538c.add(InetAddress.getByName("2001:4860:4860::8844"));
            f2538c.add(InetAddress.getByName("1.1.1.1"));
            f2538c.add(InetAddress.getByName("1.0.0.1"));
            f2538c.add(InetAddress.getByName("2606:4700:4700::1111"));
            f2538c.add(InetAddress.getByName("2606:4700:4700::1001"));
            f2538c.add(InetAddress.getByName("9.9.9.9"));
            f2538c.add(InetAddress.getByName("149.112.112.112"));
            f2538c.add(InetAddress.getByName("2620:fe::fe"));
            f2538c.add(InetAddress.getByName("2620:fe::9"));
            f2539d.add("dns.google");
            f2539d.add("1dot1dot1dot1.cloudflare-dns.com");
            f2539d.add("cloudflare-dns.com");
            f2539d.add("dns.quad9.net");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to parse IP addresses", e);
        }
    }

    /* renamed from: a */
    public static boolean m3571a(LinkProperties linkProperties) {
        if (Build.VERSION.SDK_INT < 28 || linkProperties == null) {
            return false;
        }
        return linkProperties.isPrivateDnsActive();
    }

    @CalledByNativeUnchecked
    public static void addTestRootCertificate(byte[] bArr) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509Util.m3610a(bArr);
    }

    @CalledByNativeUnchecked
    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.m3609a();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [int] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.annotation.TargetApi(23)
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[][] getDnsServers() {
        /*
            java.lang.Class<byte> r0 = byte.class
            java.lang.Boolean r1 = f2536a
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0023
            android.content.Context r1 = RN0.a
            int r4 = android.os.Process.myPid()
            int r5 = android.os.Process.myUid()
            java.lang.String r6 = "android.permission.ACCESS_NETWORK_STATE"
            int r1 = ON0.a(r1, r6, r4, r5)
            if (r1 != 0) goto L_0x001c
            r1 = 1
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            f2536a = r1
        L_0x0023:
            java.lang.Boolean r1 = f2536a
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0036
            int[] r1 = new int[]{r3, r3}
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            byte[][] r0 = (byte[][]) r0
            return r0
        L_0x0036:
            android.content.Context r1 = RN0.a
            java.lang.String r4 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r4)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            if (r1 != 0) goto L_0x004d
            int[] r1 = new int[]{r3, r3}
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            byte[][] r0 = (byte[][]) r0
            return r0
        L_0x004d:
            android.net.Network r4 = r1.getActiveNetwork()
            if (r4 != 0) goto L_0x005e
            int[] r1 = new int[]{r3, r3}
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            byte[][] r0 = (byte[][]) r0
            return r0
        L_0x005e:
            android.net.LinkProperties r1 = r1.getLinkProperties(r4)
            if (r1 != 0) goto L_0x006f
            int[] r1 = new int[]{r3, r3}
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            byte[][] r0 = (byte[][]) r0
            return r0
        L_0x006f:
            java.util.List r4 = r1.getDnsServers()
            java.util.Iterator r5 = r4.iterator()
        L_0x0077:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x008d
            java.lang.Object r6 = r5.next()
            java.net.InetAddress r6 = (java.net.InetAddress) r6
            java.util.Set<java.net.InetAddress> r7 = f2538c
            boolean r6 = r7.contains(r6)
            if (r6 == 0) goto L_0x0077
            r5 = 1
            goto L_0x008e
        L_0x008d:
            r5 = 0
        L_0x008e:
            boolean r6 = m3571a(r1)
            if (r6 == 0) goto L_0x00c6
            int r4 = android.os.Build.VERSION.SDK_INT
            r6 = 28
            if (r4 < r6) goto L_0x009f
            java.lang.String r1 = r1.getPrivateDnsServerName()
            goto L_0x00a0
        L_0x009f:
            r1 = 0
        L_0x00a0:
            if (r1 == 0) goto L_0x00ae
            java.util.Set<java.lang.String> r4 = f2539d
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r5 = r1.toLowerCase(r5)
            boolean r5 = r4.contains(r5)
        L_0x00ae:
            if (r1 == 0) goto L_0x00b1
            r3 = 1
        L_0x00b1:
            java.lang.String r1 = "Net.DNS.Android.DotExplicit"
            org.chromium.base.metrics.RecordHistogram.m1543a((java.lang.String) r1, (boolean) r3)
            java.lang.String r1 = "Net.DNS.Android.AutoDohPrivate"
            org.chromium.base.metrics.RecordHistogram.m1543a((java.lang.String) r1, (boolean) r5)
            int[] r1 = new int[]{r2, r2}
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            byte[][] r0 = (byte[][]) r0
            return r0
        L_0x00c6:
            java.lang.String r0 = "Net.DNS.Android.AutoDohPublic"
            org.chromium.base.metrics.RecordHistogram.m1543a((java.lang.String) r0, (boolean) r5)
            int r0 = r4.size()
            byte[][] r0 = new byte[r0][]
        L_0x00d1:
            int r1 = r4.size()
            if (r3 >= r1) goto L_0x00e6
            java.lang.Object r1 = r4.get(r3)
            java.net.InetAddress r1 = (java.net.InetAddress) r1
            byte[] r1 = r1.getAddress()
            r0[r3] = r1
            int r3 = r3 + 1
            goto L_0x00d1
        L_0x00e6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidNetworkLibrary.getDnsServers():byte[][]");
    }

    @TargetApi(23)
    @CalledByNative
    public static boolean getIsCaptivePortal() {
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        if (Build.VERSION.SDK_INT < 23 || (connectivityManager = (ConnectivityManager) RN0.a.getSystemService("connectivity")) == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(17)) {
            return false;
        }
        return true;
    }

    @CalledByNative
    public static boolean getIsRoaming() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) RN0.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }

    @CalledByNative
    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    @CalledByNative
    public static String getNetworkCountryIso() {
        Zk3 a = Zk3.a();
        if (a.a == null) {
            TelephonyManager b = Zk3.b();
            if (b == null) {
                return BuildConfig.FLAVOR;
            }
            a.a = b.getNetworkCountryIso();
        }
        return a.a;
    }

    @CalledByNative
    public static String getNetworkOperator() {
        Zk3 a = Zk3.a();
        if (a.b == null) {
            TelephonyManager b = Zk3.b();
            if (b == null) {
                return BuildConfig.FLAVOR;
            }
            a.b = b.getNetworkOperator();
        }
        return a.b;
    }

    @CalledByNative
    public static String getSimOperator() {
        Zk3 a = Zk3.a();
        if (a.c == null) {
            TelephonyManager b = Zk3.b();
            if (b == null) {
                return BuildConfig.FLAVOR;
            }
            a.c = b.getSimOperator();
        }
        return a.c;
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getWifiSSID() {
        /*
            java.lang.Boolean r0 = f2537b
            if (r0 != 0) goto L_0x001f
            android.content.Context r0 = RN0.a
            int r1 = android.os.Process.myPid()
            int r2 = android.os.Process.myUid()
            java.lang.String r3 = "android.permission.ACCESS_WIFI_STATE"
            int r0 = ON0.a(r0, r3, r1, r2)
            if (r0 != 0) goto L_0x0018
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            f2537b = r0
        L_0x001f:
            java.lang.Boolean r0 = f2537b
            boolean r0 = r0.booleanValue()
            r1 = 0
            if (r0 == 0) goto L_0x0037
            android.content.Context r0 = RN0.a
            java.lang.String r1 = "wifi"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0
            android.net.wifi.WifiInfo r1 = r0.getConnectionInfo()
            goto L_0x004f
        L_0x0037:
            android.content.Context r0 = RN0.a
            android.content.IntentFilter r2 = new android.content.IntentFilter
            java.lang.String r3 = "android.net.wifi.STATE_CHANGE"
            r2.<init>(r3)
            android.content.Intent r0 = r0.registerReceiver(r1, r2)
            if (r0 == 0) goto L_0x004f
            java.lang.String r1 = "wifiInfo"
            android.os.Parcelable r0 = r0.getParcelableExtra(r1)
            r1 = r0
            android.net.wifi.WifiInfo r1 = (android.net.wifi.WifiInfo) r1
        L_0x004f:
            if (r1 == 0) goto L_0x0060
            java.lang.String r0 = r1.getSSID()
            if (r0 == 0) goto L_0x0060
            java.lang.String r1 = "<unknown ssid>"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L_0x0060
            return r0
        L_0x0060:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidNetworkLibrary.getWifiSSID():java.lang.String");
    }

    @CalledByNative
    public static int getWifiSignalLevel(int i) {
        int intExtra;
        int calculateSignalLevel;
        try {
            Intent registerReceiver = RN0.a.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
            if (registerReceiver != null && (intExtra = registerReceiver.getIntExtra("newRssi", Integer.MIN_VALUE)) != Integer.MIN_VALUE && (calculateSignalLevel = WifiManager.calculateSignalLevel(intExtra, i)) >= 0 && calculateSignalLevel < i) {
                return calculateSignalLevel;
            }
            return -1;
        } catch (IllegalArgumentException unused) {
        }
    }

    @CalledByNative
    public static boolean haveOnlyLoopbackAddresses() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    if (nextElement.isUp() && !nextElement.isLoopback()) {
                        return false;
                    }
                } catch (SocketException unused) {
                }
            }
            return true;
        } catch (Exception e) {
            Log.w("AndroidNetworkLibrary", "could not get network interfaces: " + e);
            return false;
        }
    }

    @CalledByNative
    public static boolean isCleartextPermitted(String str) {
        try {
            return C0464a.f2540a.mo9895a(str);
        } catch (IllegalArgumentException unused) {
            return C0464a.f2540a.mo9894a();
        }
    }

    @CalledByNative
    public static void tagSocket(int i, int i2, int i3) throws IOException {
        FileDescriptor fileDescriptor;
        ParcelFileDescriptor parcelFileDescriptor;
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i3);
        }
        if (i2 != -1) {
            nl3.a(i2);
        }
        if (Build.VERSION.SDK_INT < 23) {
            parcelFileDescriptor = null;
            fileDescriptor = C0465b.m3574a(i);
        } else {
            parcelFileDescriptor = ParcelFileDescriptor.adoptFd(i);
            fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        }
        C0466c cVar = new C0466c(fileDescriptor);
        TrafficStats.tagSocket(cVar);
        cVar.close();
        if (parcelFileDescriptor != null) {
            parcelFileDescriptor.detachFd();
        }
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i2 != -1) {
            nl3.a();
        }
    }

    @CalledByNative
    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return X509Util.m3607a(bArr, str, str2);
        } catch (KeyStoreException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (IllegalArgumentException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    /* renamed from: org.chromium.net.AndroidNetworkLibrary$a */
    /* compiled from: PG */
    public static class C0464a {

        /* renamed from: a */
        public static C0464a f2540a = new C0464a();

        @TargetApi(24)
        /* renamed from: a */
        public boolean mo9895a(String str) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
            }
            if (i < 23) {
                return true;
            }
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }

        @TargetApi(23)
        /* renamed from: a */
        public boolean mo9894a() {
            if (Build.VERSION.SDK_INT < 23) {
                return true;
            }
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }
    }
}
