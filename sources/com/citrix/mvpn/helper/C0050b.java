package com.citrix.mvpn.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.citrix.loggersdk.BuildConfig;
import com.citrix.mvpn.MAM.Android.AuthSSO.b.f;
import com.citrix.mvpn.MAM.Android.AuthSSO.proxy.Helper;
import com.citrix.mvpn.api.MicroVPNSDK;
import com.citrix.mvpn.exception.NetworkTunnelStartFailedException;
import com.citrix.mvpn.p001a.C0025d;
import com.citrix.mvpn.p003c.C0038c;
import com.citrix.mvpn.p003c.C0040d;
import com.citrix.mvpn.p003c.C0042e;
import com.citrix.sdk.config.api.PolicyAPI;
import com.citrix.sdk.config.model.Policies;
import com.citrix.sdk.crypto.api.CryptoAPI;
import com.citrix.sdk.crypto.exception.CryptoException;
import com.citrix.sdk.googleanalytics.api.GoogleAnalyticsAPI;
import com.citrix.sdk.googleanalytics.api.GoogleAnalyticsCustomDimension;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/* renamed from: com.citrix.mvpn.helper.b */
/* compiled from: PG */
public class C0050b {

    /* renamed from: a */
    public static boolean f208a;

    /* renamed from: b */
    public static Boolean f209b;

    /* renamed from: c */
    public static C0042e f210c;

    /* renamed from: d */
    public static Policies f211d;

    /* renamed from: e */
    public static Boolean f212e;

    /* renamed from: f */
    public static Boolean f213f;

    /* renamed from: g */
    public static ExecutorService f214g = Executors.newFixedThreadPool(1);

    /* renamed from: h */
    public static MvpnProxy f215h;

    /* renamed from: i */
    public static final Map<String, List<WeakReference<Messenger>>> f216i = new HashMap();

    /* renamed from: a */
    public static int m172a(Context context, C0042e eVar) {
        f210c = eVar;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", String.valueOf(eVar.mo233a()));
            contentValues.put("PROXY_ID", eVar.mo238b());
            contentValues.put("PROXY_PORT", String.valueOf(eVar.mo240c()));
            contentValues.put("MITM_SOCKET_LISTENING", Boolean.valueOf(eVar.mo241d()));
            contentValues.put("NSG_COOKIE_EXPIRED", Boolean.valueOf(eVar.mo242e()));
            Uri.Builder builder = new Uri.Builder();
            builder.path("tunnelState").authority(context.getPackageName() + ".com.citrix.mvpn.tunnelStateProvider").scheme("content");
            return context.getContentResolver().update(builder.build(), contentValues, (String) null, (String[]) null);
        } catch (Exception e) {
            C0051c.f218b.error("MVPN-TunnelHelper", "Unable to save tunnel state", e);
            return -1;
        }
    }

    /* renamed from: a */
    public static String m173a() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            sb.append("0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".charAt(secureRandom.nextInt(62)));
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static void m174a(Context context) {
        if (!f208a && context != null) {
            try {
                C0051c.f218b.initialize(context);
                f208a = true;
            } catch (Exception | UnsatisfiedLinkError e) {
                C0051c.f218b.error("MVPN-TunnelHelper", e.getMessage());
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m175a(Context context, Messenger messenger) {
        synchronized (C0050b.class) {
            C0051c.f218b.debug5("MVPN-TunnelHelper", "Within startTunnel()");
            new C0046a(context, messenger).executeOnExecutor(f214g, new Void[0]);
        }
    }

    /* renamed from: a */
    public static synchronized void m176a(Context context, Messenger messenger, List<Map<String, String>> list, String str) {
        synchronized (C0050b.class) {
            new C0046a(context, messenger, list, str).executeOnExecutor(f214g, new Void[0]);
        }
    }

    /* renamed from: a */
    public static void m177a(Bundle bundle, Context context) throws NetworkTunnelStartFailedException {
        try {
            C0051c.f218b.debug5("MVPN-TunnelHelper", "Before Initialize Proxy.");
            if (Helper.a(context, m173a(), (C0040d) bundle.getParcelable("Args"))) {
                C0051c.f218b.debug5("MVPN-TunnelHelper", "Initialize Proxy Success.");
                m198o(context);
                return;
            }
            throw new NetworkTunnelStartFailedException("Failed to start proxy!");
        } catch (Exception e) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Initialize Proxy Failed. Exception =");
            a.append(e.getMessage());
            loggingAPI.error("MVPN-TunnelHelper", a.toString());
            throw new NetworkTunnelStartFailedException((Throwable) e);
        }
    }

    /* renamed from: a */
    public static void m178a(Message message) {
        C0051c.f218b.debug("MVPN-TunnelHelper", "Sending Message back to handler.");
        for (String next : f216i.keySet()) {
            List list = f216i.get(next);
            if (list == null || list.isEmpty()) {
                C0051c.f218b.debug("MVPN-TunnelHelper", "sendMessage with empty messenger list.");
                f216i.remove(next);
            } else {
                Iterator it = f216i.get(next).iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (weakReference == null || weakReference.get() == null) {
                        LoggingAPI loggingAPI = C0051c.f218b;
                        StringBuilder a = Eo.a("Didn't sendMessage a message to: ");
                        a.append(weakReference == null ? "null" : weakReference.get());
                        loggingAPI.debug("MVPN-TunnelHelper", a.toString());
                        it.remove();
                    } else {
                        try {
                            ((Messenger) weakReference.get()).send(Message.obtain(message));
                            LoggingAPI loggingAPI2 = C0051c.f218b;
                            loggingAPI2.debug("MVPN-TunnelHelper", "sendMessage a message to: " + weakReference.get());
                        } catch (RemoteException e) {
                            C0051c.f218b.error("MVPN-TunnelHelper", "Unable to send message back to handler ", e);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m179a(C0042e eVar) {
        f210c = eVar;
    }

    /* renamed from: a */
    public static void m180a(MvpnProxy mvpnProxy) {
        f215h = mvpnProxy;
    }

    /* renamed from: a */
    public static void m181a(Policies policies) {
        f211d = policies;
    }

    /* renamed from: a */
    public static void m182a(Boolean bool) {
        f212e = bool;
    }

    /* renamed from: a */
    public static void m183a(String str, Messenger messenger) {
        if (!f216i.containsKey(str)) {
            f216i.put(str, new ArrayList());
        }
        Iterator it = f216i.get(str).iterator();
        boolean z = false;
        while (true) {
            if (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                if (weakReference != null && weakReference.get() == messenger) {
                    z = true;
                    LoggingAPI loggingAPI = C0051c.f218b;
                    loggingAPI.debug("MVPN-TunnelHelper", "registerMessenger with the same messenger: " + messenger);
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            f216i.get(str).add(new WeakReference(messenger));
            LoggingAPI loggingAPI2 = C0051c.f218b;
            loggingAPI2.debug("MVPN-TunnelHelper", "registerMessenger with a new messenger: " + messenger);
        }
    }

    /* renamed from: b */
    public static void m184b(Boolean bool) {
        f213f = bool;
    }

    /* renamed from: b */
    public static boolean m185b(Context context) {
        C0042e d = m187d(context);
        boolean z = d != null && d.mo241d() && !d.mo242e();
        LoggingAPI loggingAPI = C0051c.f218b;
        loggingAPI.debug10("MVPN-TunnelHelper", "isNetworkTunnelRunning() = " + z);
        return z;
    }

    /* renamed from: c */
    public static C0042e m186c(Context context) {
        if (f210c == null) {
            f210c = m187d(context);
        }
        return f210c;
    }

    /* renamed from: d */
    public static C0042e m187d(Context context) {
        C0051c.f218b.debug5("MVPN-TunnelHelper", "Getting Tunnel State.");
        C0042e b = C0051c.m201b();
        Uri.Builder builder = new Uri.Builder();
        builder.path("tunnelState").authority(context.getPackageName() + ".com.citrix.mvpn.tunnelStateProvider").scheme("content");
        Cursor query = context.getContentResolver().query(builder.build(), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            if (query.moveToFirst()) {
                b = new C0042e(query.getLong(query.getColumnIndex("ID")), query.getString(query.getColumnIndex("PROXY_ID")), query.getInt(query.getColumnIndex("PROXY_PORT")), Boolean.parseBoolean(query.getString(query.getColumnIndex("MITM_SOCKET_LISTENING"))), Boolean.parseBoolean(query.getString(query.getColumnIndex("NSG_COOKIE_EXPIRED"))));
            }
            query.close();
        }
        return b;
    }

    /* renamed from: e */
    public static boolean m188e(Context context) {
        LoggingAPI loggingAPI;
        StringBuilder sb;
        boolean z;
        if (f212e != null) {
            loggingAPI = C0051c.f218b;
            sb = Eo.a("isSDKMode() = ");
            sb.append(f212e);
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z2 = true;
            f212e = true;
            if (m191h(context)) {
                Policies f = m189f(context);
                if (f != null) {
                    if (f.getManagementMode() != Policies.ManagementMode.SDKApp) {
                        z2 = false;
                    }
                    z = Boolean.valueOf(z2);
                } else {
                    z = false;
                }
                f212e = z;
            }
            loggingAPI = C0051c.f218b;
            sb = Eo.a("isSDKMode() = ");
            sb.append(f212e);
            sb.append(", Time taken = ");
            sb.append(System.currentTimeMillis() - currentTimeMillis);
            sb.append(" ms");
        }
        loggingAPI.debug10("MVPN-TunnelHelper", sb.toString());
        return f212e.booleanValue();
    }

    /* renamed from: f */
    public static synchronized Policies m189f(Context context) {
        Policies policies;
        synchronized (C0050b.class) {
            C0051c.f218b.debug10("MVPN-TunnelHelper", "Fetching Policies...");
            try {
                if (f211d == null) {
                    f211d = PolicyAPI.getInstance().getPolicies(context);
                    C0051c.f218b.debug5("MVPN-TunnelHelper", "Caching Policies...");
                } else {
                    C0051c.f218b.debug10("MVPN-TunnelHelper", "Returning cached policies.");
                }
            } catch (Exception e) {
                C0051c.f218b.error("MVPN-TunnelHelper", e.getMessage());
            }
            policies = f211d;
        }
        return policies;
    }

    /* renamed from: g */
    public static boolean m190g(Context context) {
        LoggingAPI loggingAPI;
        StringBuilder sb;
        if (f213f != null) {
            loggingAPI = C0051c.f218b;
            sb = Eo.a("isWebSSO() = ");
            sb.append(f213f);
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = true;
            f213f = true;
            Policies f = m189f(context);
            if (f != null) {
                if (f.getMvpnNetworkAccess() != Policies.MamSdkMvpnNetworkAccess.MvpnNetworkAccessTunneledWebSSO) {
                    z = false;
                }
                f213f = Boolean.valueOf(z);
            } else {
                f213f = false;
                C0051c.f218b.warning("MVPN-TunnelHelper", "Unable to retrieve policies.");
            }
            loggingAPI = C0051c.f218b;
            sb = Eo.a("isWebSSO() = ");
            sb.append(f213f);
            sb.append(", Time taken = ");
            sb.append(System.currentTimeMillis() - currentTimeMillis);
            sb.append(" ms");
        }
        loggingAPI.debug10("MVPN-TunnelHelper", sb.toString());
        return f213f.booleanValue();
    }

    /* renamed from: h */
    public static boolean m191h(Context context) {
        if (f209b == null) {
            try {
                context.getClassLoader().loadClass("com.citrix.MAM.Android.ManagedApp.CtxManagedApplication");
                f209b = true;
            } catch (ClassNotFoundException unused) {
                f209b = false;
            }
        }
        return f209b.booleanValue();
    }

    /* renamed from: i */
    public static boolean m192i(Context context) {
        try {
            if (MicroVPNSDK.isNetworkTunnelRunning(context)) {
                C0025d.m66b(context.getApplicationContext());
                Helper.b();
                m172a(context, new C0042e(1, (String) null, -1, false, false));
                C0040d.m98c();
                f216i.clear();
                return true;
            }
            C0051c.f218b.warning("MVPN-TunnelHelper", "Network tunnel is not running");
            return true;
        } catch (Exception unused) {
            C0051c.f218b.error("MVPN-TunnelHelper", "Error occurred while stopping tunnel");
            return false;
        }
    }

    /* renamed from: j */
    public static TrustManager[] m193j(Context context) {
        return m196m(context);
    }

    /* renamed from: k */
    public static X509Certificate m194k(Context context) {
        try {
            return CryptoAPI.getInstance().getTunnelCertificate();
        } catch (CryptoException e) {
            C0051c.f218b.error("MVPN-TunnelHelper", "Unable to retrieve Tunnel Certificate.", e);
            return null;
        }
    }

    /* renamed from: l */
    public static MvpnProxy m195l(Context context) {
        if (!MicroVPNSDK.isNetworkTunnelRunning(context)) {
            f215h = null;
        } else if (f215h == null) {
            f215h = new MvpnProxy(f.a(context), new InetSocketAddress("127.0.0.1", f.b(context)));
        }
        return f215h;
    }

    /* renamed from: m */
    public static TrustManager[] m196m(Context context) {
        try {
            KeyStore n = m197n(context);
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(n);
            return instance.getTrustManagers();
        } catch (KeyStoreException | NoSuchAlgorithmException e) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Exception occurred while creating custom trust manager: ");
            a.append(e.getMessage());
            loggingAPI.error("MVPN-TunnelHelper", a.toString());
            return null;
        }
    }

    /* renamed from: n */
    public static KeyStore m197n(Context context) {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                keyStore.load((InputStream) null, (char[]) null);
                keyStore.setCertificateEntry("ca", m194k(context));
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                e = e;
            }
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
            e = e2;
            keyStore = null;
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Exception occurred while creating keystore: ");
            a.append(e.getMessage());
            loggingAPI.error("MVPN-TunnelHelper", a.toString());
            return keyStore;
        }
        return keyStore;
    }

    /* renamed from: o */
    public static void m198o(Context context) {
        if (C0040d.m99d() instanceof C0038c) {
            try {
                GoogleAnalyticsAPI initialize = GoogleAnalyticsAPI.initialize(context, GoogleAnalyticsAPI.getClientIdFromSecureHubBundle(PolicyAPI.getInstance().getAppInfo(context)), GoogleAnalyticsAPI.TrackingIdType.PRODUCTION);
                initialize.saveEventHitWithCustomDimension("MvpnSdkEvent", "MvpnProxyInitialized", 0, BuildConfig.FLAVOR, GoogleAnalyticsCustomDimension.POLICY_MAMSDK_MVPN_NETWORK_ACCESS, "success", false);
                if (m188e(context)) {
                    initialize.saveEventHitWithCustomDimension("MvpnSdkEvent", "SDKModeControlPolicy", 0, BuildConfig.FLAVOR, GoogleAnalyticsCustomDimension.POLICY_SDK_MODE_CONTROL, "Sdk", false);
                }
            } catch (Exception e) {
                LoggingAPI loggingAPI = C0051c.f218b;
                StringBuilder a = Eo.a("An exception occurred when reporting event to Google Analytics: ");
                a.append(e.getLocalizedMessage());
                loggingAPI.error("MVPN-TunnelHelper", a.toString());
            }
        }
    }
}
