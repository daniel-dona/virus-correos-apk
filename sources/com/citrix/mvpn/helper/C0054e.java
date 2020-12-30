package com.citrix.mvpn.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Messenger;
import android.util.Log;
import com.citrix.mvpn.api.ResponseStatusCode;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.mvpn.p002b.C0027a;
import com.citrix.mvpn.p002b.C0028b;
import com.citrix.mvpn.p002b.C0030d;
import com.citrix.mvpn.p003c.C0040d;
import com.citrix.sdk.config.exception.PolicyConfigException;

/* renamed from: com.citrix.mvpn.helper.e */
/* compiled from: PG */
public class C0054e {
    /* renamed from: a */
    public static void m204a(Context context, Messenger messenger, ResponseStatusCode responseStatusCode) {
        C0050b.m183a(C0053d.m202a(context), messenger);
        C0030d.m70a(C0028b.START_TUNNEL, new C0027a(context, C0050b.m187d(context), responseStatusCode));
    }

    /* renamed from: a */
    public static boolean m205a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = context != null ? (ConnectivityManager) context.getSystemService("connectivity") : null;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    /* renamed from: a */
    public static boolean m206a(Context context, Messenger messenger) {
        ResponseStatusCode responseStatusCode;
        try {
            C0040d a = C0040d.m99d().mo172a(context);
            if (!m205a(context)) {
                C0051c.f218b.error("MVPN-Validator", C0053d.m203a(context, wx0.MVPN_NO_NETWORK_CONNECTION));
                responseStatusCode = ResponseStatusCode.NO_NETWORK_CONNECTION;
            } else if (!C0050b.m188e(context)) {
                Log.w("MVPN-Validator", C0053d.m203a(context, wx0.TUNNEL_START_IS_NOT_REQUIRED));
                responseStatusCode = ResponseStatusCode.FOUND_LEGACY_MODE;
            } else if (!a.mo210f()) {
                responseStatusCode = ResponseStatusCode.FOUND_NON_WEBSSO_MODE;
            } else if (!C0050b.m185b(context)) {
                return true;
            } else {
                C0051c.f218b.warning("MVPN-Validator", C0053d.m203a(context, wx0.TUNNEL_ALREADY_RUNNING));
                responseStatusCode = ResponseStatusCode.TUNNEL_ALREADY_RUNNING;
            }
            m204a(context, messenger, responseStatusCode);
            return false;
        } catch (TunnelConfigException | PolicyConfigException unused) {
            m204a(context, messenger, ResponseStatusCode.FOUND_NON_MANAGED_APP);
            return false;
        }
    }
}
