package com.microsoft.aad.adal;

import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public final class UIEvent extends DefaultEvent {
    public UIEvent(String str) {
        getEventList().add(Pair.create("Microsoft.ADAL.event_name", str));
    }

    public void processEvent(Map<String, String> map) {
        List<Pair<String, String>> eventList = getEventList();
        String str = map.get("Microsoft.ADAL.ui_event_count");
        if (str == null) {
            map.put("Microsoft.ADAL.ui_event_count", "1");
        } else {
            map.put("Microsoft.ADAL.ui_event_count", Integer.toString(Integer.parseInt(str) + 1));
        }
        if (map.containsKey("Microsoft.ADAL.user_cancel")) {
            map.put("Microsoft.ADAL.user_cancel", BuildConfig.FLAVOR);
        }
        if (map.containsKey("Microsoft.ADAL.ntlm")) {
            map.put("Microsoft.ADAL.ntlm", BuildConfig.FLAVOR);
        }
        for (Pair next : eventList) {
            String str2 = (String) next.first;
            if (str2.equals("Microsoft.ADAL.user_cancel") || str2.equals("Microsoft.ADAL.ntlm")) {
                map.put(str2, next.second);
            }
        }
    }

    public void setNTLM(boolean z) {
        setProperty("Microsoft.ADAL.ntlm", String.valueOf(z));
    }

    public void setRedirectCount(Integer num) {
        setProperty("Microsoft.ADAL.redirect_count", num.toString());
    }

    public void setUserCancel() {
        setProperty("Microsoft.ADAL.user_cancel", "true");
    }
}
