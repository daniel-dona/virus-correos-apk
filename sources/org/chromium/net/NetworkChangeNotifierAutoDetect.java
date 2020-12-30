package org.chromium.net;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.citrix.loggersdk.BuildConfig;
import java.util.Arrays;
import org.chromium.base.ApplicationStatus;

@SuppressLint({"NewApi"})
/* compiled from: PG */
public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {

    /* renamed from: a */
    public final Looper f2549a = Looper.myLooper();

    /* renamed from: b */
    public final Handler f2550b = new Handler(this.f2549a);

    /* renamed from: c */
    public final NetworkConnectivityIntentFilter f2551c;

    /* renamed from: d */
    public final Observer f2552d;

    /* renamed from: e */
    public final C0469f f2553e;

    /* renamed from: f */
    public c f2554f;

    /* renamed from: g */
    public b f2555g;

    /* renamed from: h */
    public g f2556h;

    /* renamed from: i */
    public d f2557i;

    /* renamed from: j */
    public NetworkRequest f2558j;

    /* renamed from: k */
    public boolean f2559k;

    /* renamed from: l */
    public C0468e f2560l;

    /* renamed from: m */
    public boolean f2561m;

    /* renamed from: n */
    public boolean f2562n;

    /* renamed from: o */
    public boolean f2563o;

    /* compiled from: PG */
    public interface Observer {
        void onConnectionSubtypeChanged(int i);

        void onConnectionTypeChanged(int i);

        void onNetworkConnect(long j, int i);

        void onNetworkDisconnect(long j);

        void onNetworkSoonToDisconnect(long j);

        void purgeActiveNetworkList(long[] jArr);
    }

    /* renamed from: org.chromium.net.NetworkChangeNotifierAutoDetect$e */
    /* compiled from: PG */
    public static class C0468e {

        /* renamed from: a */
        public final boolean f2564a;

        /* renamed from: b */
        public final int f2565b;

        /* renamed from: c */
        public final int f2566c;

        /* renamed from: d */
        public final String f2567d;

        /* renamed from: e */
        public final boolean f2568e;

        public C0468e(boolean z, int i, int i2, String str, boolean z2) {
            this.f2564a = z;
            this.f2565b = i;
            this.f2566c = i2;
            this.f2567d = str == null ? BuildConfig.FLAVOR : str;
            this.f2568e = z2;
        }

        /* renamed from: a */
        public int mo9930a() {
            if (!this.f2564a) {
                return 1;
            }
            if (this.f2565b != 0) {
                return 0;
            }
            switch (this.f2566c) {
                case 1:
                    return 7;
                case 2:
                    return 8;
                case 3:
                    return 9;
                case 4:
                    return 5;
                case 5:
                    return 10;
                case 6:
                    return 11;
                case 7:
                    return 6;
                case 8:
                    return 14;
                case 9:
                    return 15;
                case 10:
                    return 12;
                case 11:
                    return 4;
                case 12:
                    return 13;
                case 13:
                    return 18;
                case 14:
                    return 16;
                case 15:
                    return 17;
                default:
                    return 0;
            }
        }

        /* renamed from: b */
        public int mo9931b() {
            if (!this.f2564a) {
                return 6;
            }
            return NetworkChangeNotifierAutoDetect.m3590a(this.f2565b, this.f2566c);
        }
    }

    /* renamed from: org.chromium.net.NetworkChangeNotifierAutoDetect$f */
    /* compiled from: PG */
    public static abstract class C0469f {

        /* renamed from: a */
        public NetworkChangeNotifierAutoDetect f2569a;

        static {
            Class<NetworkChangeNotifierAutoDetect> cls = NetworkChangeNotifierAutoDetect.class;
        }

        /* renamed from: a */
        public abstract void mo9932a();

        /* renamed from: a */
        public abstract void mo9933a(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect);
    }

    static {
        Class<NetworkChangeNotifierAutoDetect> cls = NetworkChangeNotifierAutoDetect.class;
    }

    @TargetApi(21)
    public NetworkChangeNotifierAutoDetect(Observer observer, C0469f fVar) {
        this.f2552d = observer;
        this.f2555g = new b(RN0.a);
        if (Build.VERSION.SDK_INT < 23) {
            this.f2556h = new g(RN0.a);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2557i = new d(this, (a) null);
            this.f2558j = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        } else {
            this.f2557i = null;
            this.f2558j = null;
        }
        this.f2554f = Build.VERSION.SDK_INT >= 28 ? new c(this, (a) null) : null;
        this.f2560l = mo9923c();
        this.f2551c = new NetworkConnectivityIntentFilter();
        this.f2561m = false;
        this.f2562n = false;
        this.f2553e = fVar;
        this.f2553e.mo9933a(this);
        this.f2562n = true;
    }

    /* renamed from: a */
    public static /* synthetic */ int m3590a(int i, int i2) {
        if (i == 0) {
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 3;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 4;
                case 13:
                    return 5;
            }
        } else if (i == 1) {
            return 2;
        } else {
            if (i == 6) {
                return 5;
            }
            if (i != 7) {
                return i != 9 ? 0 : 1;
            }
            return 7;
        }
    }

    /* renamed from: b */
    public void mo9922b() {
        this.f2553e.mo9932a();
        mo9928h();
    }

    /* renamed from: c */
    public C0468e mo9923c() {
        Network network;
        NetworkInfo networkInfo;
        b bVar = this.f2555g;
        g gVar = this.f2556h;
        if (Build.VERSION.SDK_INT >= 23) {
            Network a = bVar.a();
            network = a;
            networkInfo = bVar.a.getNetworkInfo(a);
        } else {
            networkInfo = bVar.a.getActiveNetworkInfo();
            network = null;
        }
        if (networkInfo == null || (!networkInfo.isConnected() && !(Build.VERSION.SDK_INT >= 21 && networkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED && ApplicationStatus.getStateForApplication() == 1))) {
            networkInfo = null;
        }
        if (networkInfo == null) {
            return new C0468e(false, -1, -1, (String) null, false);
        }
        if (network != null) {
            return new C0468e(true, networkInfo.getType(), networkInfo.getSubtype(), String.valueOf(m3591a(network)), Build.VERSION.SDK_INT >= 28 && AndroidNetworkLibrary.m3571a(bVar.a.getLinkProperties(network)));
        } else if (networkInfo.getType() != 1) {
            return new C0468e(true, networkInfo.getType(), networkInfo.getSubtype(), (String) null, false);
        } else if (networkInfo.getExtraInfo() == null || BuildConfig.FLAVOR.equals(networkInfo.getExtraInfo())) {
            return new C0468e(true, networkInfo.getType(), networkInfo.getSubtype(), gVar.a(), false);
        } else {
            return new C0468e(true, networkInfo.getType(), networkInfo.getSubtype(), networkInfo.getExtraInfo(), false);
        }
    }

    /* renamed from: d */
    public long mo9924d() {
        Network a;
        if (Build.VERSION.SDK_INT >= 21 && (a = this.f2555g.a()) != null) {
            return m3591a(a);
        }
        return -1;
    }

    /* renamed from: e */
    public long[] mo9925e() {
        if (Build.VERSION.SDK_INT < 21) {
            return new long[0];
        }
        Network[] a = m3593a(this.f2555g, (Network) null);
        long[] jArr = new long[(a.length * 2)];
        int i = 0;
        for (Network network : a) {
            int i2 = i + 1;
            jArr[i] = m3591a(network);
            i = i2 + 1;
            jArr[i2] = (long) this.f2555g.a(network);
        }
        return jArr;
    }

    /* renamed from: f */
    public void mo9926f() {
        NetworkCapabilities b;
        if (!this.f2559k) {
            if (this.f2562n) {
                mo9920a();
            }
            c cVar = this.f2554f;
            if (cVar != null) {
                try {
                    this.f2555g.a.registerDefaultNetworkCallback(cVar, this.f2550b);
                } catch (RuntimeException unused) {
                    this.f2554f = null;
                }
            }
            if (this.f2554f == null) {
                this.f2561m = RN0.a.registerReceiver(this, this.f2551c) != null;
            }
            this.f2559k = true;
            d dVar = this.f2557i;
            if (dVar != null) {
                Network[] a = m3593a(dVar.b.f2555g, (Network) null);
                dVar.a = null;
                if (a.length == 1 && (b = dVar.b.f2555g.b(a[0])) != null && b.hasTransport(4)) {
                    dVar.a = a[0];
                }
                try {
                    b bVar = this.f2555g;
                    NetworkRequest networkRequest = this.f2558j;
                    d dVar2 = this.f2557i;
                    Handler handler = this.f2550b;
                    if (Build.VERSION.SDK_INT >= 26) {
                        bVar.a.registerNetworkCallback(networkRequest, dVar2, handler);
                    } else {
                        bVar.a.registerNetworkCallback(networkRequest, dVar2);
                    }
                } catch (RuntimeException unused2) {
                    this.f2563o = true;
                    this.f2557i = null;
                }
                if (!this.f2563o && this.f2562n) {
                    Network[] a2 = m3593a(this.f2555g, (Network) null);
                    long[] jArr = new long[a2.length];
                    for (int i = 0; i < a2.length; i++) {
                        jArr[i] = m3591a(a2[i]);
                    }
                    this.f2552d.purgeActiveNetworkList(jArr);
                }
            }
        }
    }

    /* renamed from: g */
    public boolean mo9927g() {
        return this.f2563o;
    }

    /* renamed from: h */
    public void mo9928h() {
        if (this.f2559k) {
            this.f2559k = false;
            d dVar = this.f2557i;
            if (dVar != null) {
                this.f2555g.a.unregisterNetworkCallback(dVar);
            }
            c cVar = this.f2554f;
            if (cVar != null) {
                this.f2555g.a.unregisterNetworkCallback(cVar);
            } else {
                RN0.a.unregisterReceiver(this);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        mo9921a((Runnable) new a(this));
    }

    @TargetApi(21)
    /* renamed from: a */
    public static Network[] m3593a(b bVar, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworks = bVar.a.getAllNetworks();
        if (allNetworks == null) {
            allNetworks = new Network[0];
        }
        int i = 0;
        for (Network network2 : allNetworks) {
            if (!network2.equals(network) && (networkCapabilities = bVar.a.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (!networkCapabilities.hasTransport(4)) {
                    allNetworks[i] = network2;
                    i++;
                } else if (bVar.c(network2)) {
                    return new Network[]{network2};
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworks, i);
    }

    /* renamed from: a */
    public final void mo9921a(Runnable runnable) {
        if (this.f2549a == Looper.myLooper()) {
            runnable.run();
        } else {
            this.f2550b.post(runnable);
        }
    }

    /* renamed from: a */
    public final void mo9920a() {
        C0468e c = mo9923c();
        if (!(c.mo9931b() == this.f2560l.mo9931b() && c.f2567d.equals(this.f2560l.f2567d) && c.f2568e == this.f2560l.f2568e)) {
            this.f2552d.onConnectionTypeChanged(c.mo9931b());
        }
        if (!(c.mo9931b() == this.f2560l.mo9931b() && c.mo9930a() == this.f2560l.mo9930a())) {
            this.f2552d.onConnectionSubtypeChanged(c.mo9930a());
        }
        this.f2560l = c;
    }

    @TargetApi(21)
    /* renamed from: a */
    public static long m3591a(Network network) {
        if (Build.VERSION.SDK_INT >= 23) {
            return network.getNetworkHandle();
        }
        return (long) Integer.parseInt(network.toString());
    }
}
