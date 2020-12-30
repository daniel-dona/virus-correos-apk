package org.chromium.net;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
public class NetworkChangeNotifier {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: f */
    public static NetworkChangeNotifier f2542f;

    /* renamed from: a */
    public final ArrayList<Long> f2543a = new ArrayList<>();

    /* renamed from: b */
    public final ObserverList<ConnectionTypeObserver> f2544b = new ObserverList<>();

    /* renamed from: c */
    public final ConnectivityManager f2545c = ((ConnectivityManager) RN0.a.getSystemService("connectivity"));

    /* renamed from: d */
    public NetworkChangeNotifierAutoDetect f2546d;

    /* renamed from: e */
    public int f2547e = 0;

    /* compiled from: PG */
    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int i);
    }

    /* renamed from: org.chromium.net.NetworkChangeNotifier$a */
    /* compiled from: PG */
    public class C0467a implements NetworkChangeNotifierAutoDetect.Observer {
        public C0467a() {
        }

        public void onConnectionSubtypeChanged(int i) {
            NetworkChangeNotifier.this.mo9897a(i);
        }

        public void onConnectionTypeChanged(int i) {
            NetworkChangeNotifier networkChangeNotifier = NetworkChangeNotifier.this;
            networkChangeNotifier.f2547e = i;
            networkChangeNotifier.mo9904b(i);
        }

        public void onNetworkConnect(long j, int i) {
            NetworkChangeNotifier.this.mo9900a(j, i);
        }

        public void onNetworkDisconnect(long j) {
            NetworkChangeNotifier.this.mo9899a(j);
        }

        public void onNetworkSoonToDisconnect(long j) {
            NetworkChangeNotifier.this.mo9905b(j);
        }

        public void purgeActiveNetworkList(long[] jArr) {
            NetworkChangeNotifier.this.mo9902a(jArr);
        }
    }

    /* renamed from: a */
    public static void m3575a(ConnectionTypeObserver connectionTypeObserver) {
        f2542f.f2544b.mo7868a(connectionTypeObserver);
    }

    /* renamed from: b */
    public static void m3577b(ConnectionTypeObserver connectionTypeObserver) {
        f2542f.f2544b.mo7869b(connectionTypeObserver);
    }

    /* renamed from: c */
    public static boolean m3578c() {
        return f2542f != null;
    }

    /* renamed from: d */
    public static boolean m3579d() {
        return f2542f.getCurrentConnectionType() != 6;
    }

    @CalledByNative
    public static void fakeConnectionSubtypeChanged(int i) {
        m3576a(false);
        f2542f.mo9897a(i);
    }

    @CalledByNative
    public static void fakeDefaultNetwork(long j, int i) {
        m3576a(false);
        f2542f.mo9898a(i, j);
    }

    @CalledByNative
    public static void fakeNetworkConnected(long j, int i) {
        m3576a(false);
        f2542f.mo9900a(j, i);
    }

    @CalledByNative
    public static void fakeNetworkDisconnected(long j) {
        m3576a(false);
        f2542f.mo9899a(j);
    }

    @CalledByNative
    public static void fakeNetworkSoonToBeDisconnected(long j) {
        m3576a(false);
        f2542f.mo9905b(j);
    }

    @CalledByNative
    public static void fakePurgeActiveNetworkList(long[] jArr) {
        m3576a(false);
        f2542f.mo9902a(jArr);
    }

    @CalledByNative
    public static void forceConnectivityState(boolean z) {
        int i = 0;
        m3576a(false);
        NetworkChangeNotifier networkChangeNotifier = f2542f;
        if ((networkChangeNotifier.f2547e != 6) != z) {
            if (!z) {
                i = 6;
            }
            networkChangeNotifier.f2547e = i;
            networkChangeNotifier.mo9904b(i);
            networkChangeNotifier.mo9897a(z ^ true ? 1 : 0);
        }
    }

    @CalledByNative
    public static NetworkChangeNotifier init() {
        if (f2542f == null) {
            f2542f = new NetworkChangeNotifier();
        }
        return f2542f;
    }

    @CalledByNative
    public static boolean isProcessBoundToNetwork() {
        return f2542f.mo9906b();
    }

    private native void nativeNotifyConnectionTypeChanged(long j, int i, long j2);

    private native void nativeNotifyMaxBandwidthChanged(long j, int i);

    private native void nativeNotifyOfNetworkConnect(long j, long j2, int i);

    private native void nativeNotifyOfNetworkDisconnect(long j, long j2);

    private native void nativeNotifyOfNetworkSoonToDisconnect(long j, long j2);

    private native void nativeNotifyPurgeActiveNetworkList(long j, long[] jArr);

    @CalledByNative
    public void addNativeObserver(long j) {
        this.f2543a.add(Long.valueOf(j));
    }

    @CalledByNative
    public int getCurrentConnectionSubtype() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.f2546d;
        if (networkChangeNotifierAutoDetect == null) {
            return 0;
        }
        return networkChangeNotifierAutoDetect.mo9923c().mo9930a();
    }

    @CalledByNative
    public int getCurrentConnectionType() {
        return this.f2547e;
    }

    @CalledByNative
    public long getCurrentDefaultNetId() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.f2546d;
        if (networkChangeNotifierAutoDetect == null) {
            return -1;
        }
        return networkChangeNotifierAutoDetect.mo9924d();
    }

    @CalledByNative
    public long[] getCurrentNetworksAndTypes() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.f2546d;
        return networkChangeNotifierAutoDetect == null ? new long[0] : networkChangeNotifierAutoDetect.mo9925e();
    }

    @CalledByNative
    public boolean registerNetworkCallbackFailed() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.f2546d;
        if (networkChangeNotifierAutoDetect == null) {
            return false;
        }
        return networkChangeNotifierAutoDetect.mo9927g();
    }

    @CalledByNative
    public void removeNativeObserver(long j) {
        this.f2543a.remove(Long.valueOf(j));
    }

    /* renamed from: a */
    public static void m3576a(boolean z) {
        f2542f.mo9901a(z, (NetworkChangeNotifierAutoDetect.C0469f) new ml3());
    }

    /* renamed from: b */
    public void mo9904b(int i) {
        mo9898a(i, getCurrentDefaultNetId());
    }

    /* renamed from: b */
    public void mo9905b(long j) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkSoonToDisconnect(it.next().longValue(), j);
        }
    }

    /* renamed from: a */
    public final void mo9896a() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.f2546d;
        if (networkChangeNotifierAutoDetect != null) {
            networkChangeNotifierAutoDetect.mo9922b();
            this.f2546d = null;
        }
    }

    /* renamed from: b */
    public final boolean mo9906b() {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            return false;
        }
        if (i < 23) {
            if (ConnectivityManager.getProcessDefaultNetwork() != null) {
                return true;
            }
            return false;
        } else if (this.f2545c.getBoundNetworkForProcess() != null) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo9901a(boolean z, NetworkChangeNotifierAutoDetect.C0469f fVar) {
        if (!z) {
            mo9896a();
        } else if (this.f2546d == null) {
            this.f2546d = new NetworkChangeNotifierAutoDetect(new C0467a(), fVar);
            NetworkChangeNotifierAutoDetect.C0468e c = this.f2546d.mo9923c();
            int b = c.mo9931b();
            this.f2547e = b;
            mo9904b(b);
            mo9897a(c.mo9930a());
        }
    }

    /* renamed from: a */
    public final void mo9898a(int i, long j) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue(), i, j);
        }
        Iterator<ConnectionTypeObserver> it2 = this.f2544b.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(i);
        }
    }

    /* renamed from: a */
    public void mo9897a(int i) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyMaxBandwidthChanged(it.next().longValue(), i);
        }
    }

    /* renamed from: a */
    public void mo9900a(long j, int i) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(it.next().longValue(), j, i);
        }
    }

    /* renamed from: a */
    public void mo9899a(long j) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it.next().longValue(), j);
        }
    }

    /* renamed from: a */
    public void mo9902a(long[] jArr) {
        Iterator<Long> it = this.f2543a.iterator();
        while (it.hasNext()) {
            nativeNotifyPurgeActiveNetworkList(it.next().longValue(), jArr);
        }
    }
}
