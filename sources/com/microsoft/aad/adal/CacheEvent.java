package com.microsoft.aad.adal;

import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public final class CacheEvent extends DefaultEvent {
    public final String mEventName;

    public CacheEvent(String str) {
        this.mEventName = str;
        setProperty("Microsoft.ADAL.event_name", str);
    }

    public void processEvent(Map<String, String> map) {
        if (this.mEventName == "Microsoft.ADAL.token_cache_lookup") {
            List<Pair<String, String>> eventList = getEventList();
            String str = map.get("Microsoft.ADAL.cache_event_count");
            if (str == null) {
                map.put("Microsoft.ADAL.cache_event_count", "1");
            } else {
                map.put("Microsoft.ADAL.cache_event_count", Integer.toString(Integer.parseInt(str) + 1));
            }
            map.put("Microsoft.ADAL.is_frt", BuildConfig.FLAVOR);
            map.put("Microsoft.ADAL.is_mrrt", BuildConfig.FLAVOR);
            map.put("Microsoft.ADAL.is_rt", BuildConfig.FLAVOR);
            if (map.containsKey("Microsoft.ADAL.spe_info")) {
                map.remove("Microsoft.ADAL.spe_info");
            }
            for (Pair next : eventList) {
                String str2 = (String) next.first;
                if (str2.equals("Microsoft.ADAL.is_frt") || str2.equals("Microsoft.ADAL.is_rt") || str2.equals("Microsoft.ADAL.is_mrrt") || str2.equals("Microsoft.ADAL.spe_info")) {
                    map.put(str2, next.second);
                }
            }
        }
    }

    public void setSpeRing(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            setProperty("Microsoft.ADAL.spe_info", str.trim());
        }
    }

    public void setTokenType(String str) {
        getEventList().add(Pair.create("Microsoft.ADAL.token_type", str));
    }

    public void setTokenTypeFRT(boolean z) {
        setProperty("Microsoft.ADAL.is_frt", String.valueOf(z));
    }

    public void setTokenTypeMRRT(boolean z) {
        setProperty("Microsoft.ADAL.is_mrrt", String.valueOf(z));
    }

    public void setTokenTypeRT(boolean z) {
        setProperty("Microsoft.ADAL.is_rt", String.valueOf(z));
    }
}
