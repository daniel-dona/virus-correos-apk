package com.microsoft.identity.common.internal.platform;

import android.os.Build;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public final class Device {
    public static Map<String, String> getPlatformIdParameters() {
        HashMap hashMap = new HashMap();
        hashMap.put("x-client-SKU", "MSAL.Android");
        hashMap.put("x-client-Ver", "0.3.1");
        if (Build.VERSION.SDK_INT < 21) {
            hashMap.put("x-client-CPU", Build.CPU_ABI);
        } else {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr != null && strArr.length > 0) {
                hashMap.put("x-client-CPU", strArr[0]);
            }
        }
        hashMap.put("x-client-OS", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("x-client-DM", Build.MODEL);
        return Collections.unmodifiableMap(hashMap);
    }
}
