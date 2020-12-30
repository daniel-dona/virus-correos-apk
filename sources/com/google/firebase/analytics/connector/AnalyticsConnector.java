package com.google.firebase.analytics.connector;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: PG */
public interface AnalyticsConnector {

    @KeepForSdk
    /* compiled from: PG */
    public interface AnalyticsConnectorHandle {
        @KeepForSdk
        void unregister();
    }

    @KeepForSdk
    /* compiled from: PG */
    public interface AnalyticsConnectorListener {
        @KeepForSdk
        void onMessageTriggered(int i, Bundle bundle);
    }

    @KeepForSdk
    /* renamed from: a */
    void mo2005a(String str, String str2, Bundle bundle);

    @KeepForSdk
    /* renamed from: a */
    void mo2006a(String str, String str2, Object obj);
}
