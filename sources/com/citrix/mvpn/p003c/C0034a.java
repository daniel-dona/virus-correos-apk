package com.citrix.mvpn.p003c;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.sdk.config.exception.PolicyConfigException;

/* renamed from: com.citrix.mvpn.c.a */
/* compiled from: PG */
public class C0034a extends C0040d {
    public static final Parcelable.Creator<C0034a> CREATOR = new Parcelable.Creator<C0034a>() {
        /* renamed from: a */
        public C0034a createFromParcel(Parcel parcel) {
            return new C0034a(parcel);
        }

        /* renamed from: a */
        public C0034a[] newArray(int i) {
            return new C0034a[i];
        }
    };

    public C0034a() {
    }

    public C0034a(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: a */
    public static synchronized C0034a m76a() {
        C0034a aVar;
        synchronized (C0034a.class) {
            if (C0040d.f154D == null) {
                C0040d.f154D = new C0034a();
            }
            aVar = (C0034a) C0040d.f154D;
        }
        return aVar;
    }

    /* renamed from: a */
    public C0040d mo172a(Context context) throws TunnelConfigException, PolicyConfigException {
        Log.i("DirectTunnelConfig", "FIXME: getting DirectTunnelConfiguration");
        return this;
    }
}
