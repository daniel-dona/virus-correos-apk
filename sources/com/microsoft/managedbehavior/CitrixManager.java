package com.microsoft.managedbehavior;

import android.content.Context;
import android.os.Build;
import com.citrix.mvpn.api.MicroVPNSDK;
import com.microsoft.bing.visualsearch.answer.p006v2.CameraRankType;
import com.microsoft.intune.mam.policy.MAMUserInfo;
import com.microsoft.intune.mam.policy.appconfig.MAMAppConfigManager;
import com.microsoft.mmx.experiment.FeatureManager$Feature;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.snackbar.SnackbarManager;

/* compiled from: PG */
public class CitrixManager {

    /* renamed from: c */
    public static boolean f1286c;

    /* renamed from: a */
    public SnackbarManager f1287a;

    /* renamed from: b */
    public SnackbarManager.SnackbarController f1288b = new a(this);

    public /* synthetic */ CitrixManager(a aVar) {
    }

    /* renamed from: b */
    public static /* synthetic */ void m1276b(CitrixManager citrixManager) {
        SnackbarManager snackbarManager = citrixManager.f1287a;
        if (snackbarManager != null) {
            if (snackbarManager.a()) {
                citrixManager.mo4430a();
            }
            VN0.b("com.microsoft.managedbehavior.CitrixManager", "showFailedSnackbar", new Object[0]);
            Context context = RN0.a;
            String string = context.getString(wx0.citrix_failed_message);
            String string2 = context.getString(wx0.retry);
            SnackbarManager snackbarManager2 = citrixManager.f1287a;
            rt2 a = rt2.a(string, citrixManager.f1288b, 0, 34);
            a.k = CameraRankType.RANK_HIGHER_L2;
            a.d = string2;
            a.e = 1;
            snackbarManager2.a(a);
        }
    }

    /* renamed from: c */
    public static CitrixManager m1277c() {
        return e.a;
    }

    @CalledByNative
    public static boolean isNetworkTunnelRunning() {
        return m1278d() && MicroVPNSDK.isNetworkTunnelRunning(RN0.a);
    }

    /* renamed from: d */
    public void mo4437d(Context context) {
        if (m1278d()) {
            VN0.b("com.microsoft.managedbehavior.CitrixManager", "updateTunnelerState", new Object[0]);
            if (e.a.mo4435b()) {
                e.a.mo4434b(context);
            } else {
                e.a.mo4436c(context);
            }
        }
    }

    /* renamed from: a */
    public void mo4432a(SnackbarManager snackbarManager) {
        this.f1287a = snackbarManager;
    }

    /* renamed from: c */
    public void mo4436c(Context context) {
        if (m1278d()) {
            if (!MicroVPNSDK.isNetworkTunnelRunning(context)) {
                VN0.b("com.microsoft.managedbehavior.CitrixManager", "Tunnel is not running. ignore this stopTunnel", new Object[0]);
            } else {
                Us0.d.execute(new d(this, context));
            }
        }
    }

    /* renamed from: a */
    public final void mo4430a() {
        SnackbarManager snackbarManager = this.f1287a;
        if (snackbarManager != null && snackbarManager.a()) {
            this.f1287a.a(this.f1288b);
        }
    }

    /* renamed from: a */
    public void mo4431a(Context context) {
        if (!m1278d()) {
            VN0.b("com.microsoft.managedbehavior.CitrixManager", "[NotSupported] Citrix is not supported", new Object[0]);
            return;
        }
        MicrosoftSigninManager.C0424c.f2104a.mo8888a((MicrosoftSigninManager.SignInStateObserver) new b(this, context));
        mo4434b(context);
    }

    /* renamed from: d */
    public static boolean m1278d() {
        if (Build.VERSION.SDK_INT < 23 || gh0.b(FeatureManager$Feature.CITRIX_DISABLE_ROLLOUT) || QN0.a.getBoolean("disable_mvpn", false)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo4433a(boolean z) {
        if (MAMEdgeManager.m1307e()) {
            Eo.b(QN0.a, "disable_mvpn", z);
        }
    }

    /* renamed from: b */
    public void mo4434b(Context context) {
        if (m1278d()) {
            if (MicroVPNSDK.isNetworkTunnelRunning(context)) {
                VN0.b("com.microsoft.managedbehavior.CitrixManager", "Tunnel is already running. ignore this startTunnel", new Object[0]);
            } else if (!mo4435b()) {
                VN0.b("com.microsoft.managedbehavior.CitrixManager", "is not citrix account. ignore this startTunnel", new Object[0]);
            } else if (f1286c) {
                VN0.b("com.microsoft.managedbehavior.CitrixManager", "Tunnel is already initializing. ignore this startTunnel", new Object[0]);
            } else {
                f1286c = true;
                SnackbarManager snackbarManager = this.f1287a;
                if (snackbarManager != null) {
                    if (snackbarManager.a()) {
                        mo4430a();
                    }
                    VN0.b("com.microsoft.managedbehavior.CitrixManager", "showStartingSnackbar", new Object[0]);
                    String string = RN0.a.getString(wx0.citrix_starting_message);
                    SnackbarManager snackbarManager2 = this.f1287a;
                    rt2 a = rt2.a(string, this.f1288b, 0, 33);
                    a.k = 30000;
                    a.d = null;
                    a.e = null;
                    snackbarManager2.a(a);
                }
                Us0.d.execute(new c(this, context));
            }
        }
    }

    /* renamed from: b */
    public final boolean mo4435b() {
        List<Map> fullData;
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A() && !mx2.a && (fullData = ((MAMAppConfigManager) xb0.a(MAMAppConfigManager.class)).getAppConfig(((MAMUserInfo) xb0.a(MAMUserInfo.class)).getPrimaryUser()).getFullData()) != null) {
            for (Map map : fullData) {
                if (map.containsKey("MvpnGatewayAddress") && map.containsKey("MvpnNetworkAccess") && map.get("MvpnNetworkAccess").equals("MvpnNetworkAccessTunneledWebSSO")) {
                    return true;
                }
            }
        }
        return false;
    }
}
