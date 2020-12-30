package com.microsoft.aad.adal;

import android.util.Pair;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public final class BrokerEvent extends DefaultEvent {
    public BrokerEvent(String str) {
        setProperty("Microsoft.ADAL.event_name", str);
    }

    public void processEvent(Map<String, String> map) {
        List<Pair<String, String>> eventList = getEventList();
        map.put("Microsoft.ADAL.broker_app_used", Boolean.toString(true));
        for (Pair next : eventList) {
            if (!((String) next.first).equals("Microsoft.ADAL.event_name")) {
                map.put(next.first, next.second);
            }
        }
    }

    public void setBrokerAccountServerStartsBinding() {
        setProperty("Microsoft.ADAL.broker_account_service_starts_binding", Boolean.toString(true));
    }

    public void setBrokerAccountServiceBindingSucceed(boolean z) {
        setProperty("Microsoft.ADAL.broker_account_service_binding_succeed", Boolean.toString(z));
    }

    public void setBrokerAccountServiceConnected() {
        setProperty("Microsoft.ADAL.broker_account_service_connected", Boolean.toString(true));
    }

    public void setBrokerAppName(String str) {
        setProperty("Microsoft.ADAL.broker_app", str);
    }

    public void setBrokerAppVersion(String str) {
        setProperty("Microsoft.ADAL.broker_version", str);
    }

    public void setRefreshTokenAge(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.rt_age", str.trim());
        }
    }

    public void setServerErrorCode(String str) {
        if (!StringExtensions.isNullOrBlank(str) && !str.equals("0")) {
            setProperty("Microsoft.ADAL.server_error_code", str.trim());
        }
    }

    public void setServerSubErrorCode(String str) {
        if (!StringExtensions.isNullOrBlank(str) && !str.equals("0")) {
            setProperty("Microsoft.ADAL.server_sub_error_code", str.trim());
        }
    }

    public void setSpeRing(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.spe_info", str.trim());
        }
    }
}
