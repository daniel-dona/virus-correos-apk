package com.microsoft.aad.adal;

import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.microsoft.aad.adal.Telemetry */
/* compiled from: PG */
public final class C0336Telemetry {
    public static final C0336Telemetry INSTANCE = new C0336Telemetry();
    public static final String TAG = "Telemetry";
    public static boolean sAllowPii;
    public DefaultDispatcher mDispatcher = null;
    public final Map<Pair<String, String>, String> mEventTracking = new ConcurrentHashMap();

    public static boolean getAllowPii() {
        return sAllowPii;
    }

    public static synchronized C0336Telemetry getInstance() {
        C0336Telemetry telemetry;
        synchronized (C0336Telemetry.class) {
            telemetry = INSTANCE;
        }
        return telemetry;
    }

    public static String registerNewRequest() {
        return UUID.randomUUID().toString();
    }

    public static void setAllowPii(boolean z) {
        sAllowPii = z;
    }

    public void flush(String str) {
        DefaultDispatcher defaultDispatcher = this.mDispatcher;
        if (defaultDispatcher != null) {
            defaultDispatcher.flush(str);
        }
    }

    public synchronized void registerDispatcher(IDispatcher iDispatcher, boolean z) {
        if (z) {
            this.mDispatcher = new AggregatedDispatcher(iDispatcher);
        } else {
            this.mDispatcher = new DefaultDispatcher(iDispatcher);
        }
    }

    public void startEvent(String str, String str2) {
        if (this.mDispatcher != null) {
            this.mEventTracking.put(new Pair(str, str2), Long.toString(System.currentTimeMillis()));
        }
    }

    public void stopEvent(String str, IEvents iEvents, String str2) {
        if (this.mDispatcher != null) {
            String remove = this.mEventTracking.remove(new Pair(str, str2));
            if (StringExtensions.isNullOrBlank(remove)) {
                Logger.m1253w(TAG, "Stop Event called without a corresponding start_event", BuildConfig.FLAVOR, (ADALError) null);
                return;
            }
            long parseLong = Long.parseLong(remove);
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - parseLong;
            String l = Long.toString(currentTimeMillis);
            iEvents.setProperty("Microsoft.ADAL.start_time", remove);
            iEvents.setProperty("Microsoft.ADAL.stop_time", l);
            iEvents.setProperty("Microsoft.ADAL.response_time", Long.toString(j));
            this.mDispatcher.receive(str, iEvents);
        }
    }
}
