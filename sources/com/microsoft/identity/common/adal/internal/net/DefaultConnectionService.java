package com.microsoft.identity.common.adal.internal.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.microsoft.identity.common.adal.internal.PowerManagerWrapper;
import com.microsoft.identity.common.adal.internal.UsageStatsManagerWrapper;

/* compiled from: PG */
public class DefaultConnectionService implements IConnectionService {
    public final Context mConnectionContext;

    public DefaultConnectionService(Context context) {
        this.mConnectionContext = context;
    }

    public boolean isConnectionAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mConnectionContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && !isNetworkDisabledFromOptimizations();
    }

    @TargetApi(23)
    public boolean isNetworkDisabledFromOptimizations() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        if (UsageStatsManagerWrapper.getInstance().isAppInactive(this.mConnectionContext)) {
            return true;
        }
        PowerManagerWrapper instance = PowerManagerWrapper.getInstance();
        if (!instance.isDeviceIdleMode(this.mConnectionContext) || instance.isIgnoringBatteryOptimizations(this.mConnectionContext)) {
            return false;
        }
        return true;
    }
}
