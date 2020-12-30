package com.citrix.mvpn.p003c;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.citrix.mvpn.MAM.Android.AuthSSO.a.ac;
import com.citrix.mvpn.d.a.a.a;
import com.citrix.mvpn.d.a.d;
import com.citrix.mvpn.d.a.e;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.sdk.config.api.PolicyAPI;
import com.citrix.sdk.config.exception.PolicyConfigException;
import com.citrix.sdk.config.model.Policies;
import com.citrix.sdk.logging.exception.LoggingException;
import java.util.List;
import java.util.Map;

/* renamed from: com.citrix.mvpn.c.b */
/* compiled from: PG */
public class C0036b extends C0040d {
    public static final Parcelable.Creator<C0036b> CREATOR = new Parcelable.Creator<C0036b>() {
        /* renamed from: a */
        public C0036b createFromParcel(Parcel parcel) {
            return new C0036b(parcel);
        }

        /* renamed from: a */
        public C0036b[] newArray(int i) {
            return new C0036b[i];
        }
    };

    /* renamed from: E */
    public List<Map<String, String>> f151E;

    public C0036b() {
        this.f166j = true;
    }

    public C0036b(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: a */
    public static synchronized C0036b m80a() {
        C0036b bVar;
        synchronized (C0036b.class) {
            if (C0040d.f154D == null) {
                C0040d.f154D = new C0036b();
            }
            bVar = (C0036b) C0040d.f154D;
        }
        return bVar;
    }

    /* renamed from: a */
    public C0040d mo172a(Context context) throws TunnelConfigException, PolicyConfigException {
        if (!C0040d.f152B) {
            Policies policies = PolicyAPI.getInstance().getPolicies(this.f151E);
            if (policies != null) {
                mo205d(policies.getMvpnGatewayAddress());
                mo206d((List<String>) policies.getMvpnExcludeDomains());
                mo208e(policies.getBackgroundServicesList());
                mo194a(policies.getManagementMode());
                mo193a(policies.getMvpnNetworkAccess());
                mo206d((List<String>) policies.getMvpnExcludeDomains());
                C0050b.m181a(policies);
                try {
                    C0040d.f153C.initialize(context);
                } catch (LoggingException e) {
                    C0040d.f153C.error("MVPN-IntuneTunnelConfig", e.getMessage());
                }
                C0040d.f152B = true;
            } else {
                throw new PolicyConfigException("Unable to retrieve XMS Policies from Intune AppConfigData.");
            }
        }
        return this;
    }

    /* renamed from: a */
    public void mo177a(d dVar) throws TunnelConfigException {
        a a = dVar.a();
        if (a != null) {
            String b = dVar.b();
            if (!TextUtils.isEmpty(b)) {
                mo195a(b);
                mo202c(e.a());
                mo203c((List<String>) a.c());
                mo200b((List<String>) a.d());
                mo191a(a.b() ? ac.a.c : a.a() ? ac.a.a : ac.a.b);
                mo192a(new ac(this));
                return;
            }
            throw new TunnelConfigException("Failed to retrieve NSC AAAC Cookie in Intune mode.");
        }
        throw new TunnelConfigException("Failed to retrieve mVPN Configuration in Intune mode.");
    }

    /* renamed from: a */
    public void mo178a(List<Map<String, String>> list) {
        this.f151E = list;
    }
}
