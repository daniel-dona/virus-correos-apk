package com.microsoft.intune.mam.policy.appconfig;

import android.content.Context;
import android.content.RestrictionsManager;
import android.os.Build;
import android.os.Bundle;
import com.microsoft.intune.mam.policy.appconfig.MAMAppConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public class AndroidEnterpriseAppConfig implements MAMAppConfig {
    public static Bundle mRestrictions;

    public AndroidEnterpriseAppConfig(Bundle bundle) {
        mRestrictions = bundle;
    }

    public static AndroidEnterpriseAppConfig create(Context context) {
        return create(context, new HashSet());
    }

    public List<Boolean> getAllBooleansForKey(String str) {
        ArrayList arrayList = new ArrayList();
        if (mRestrictions.containsKey(str) && (mRestrictions.get(str) instanceof Boolean)) {
            arrayList.add(Boolean.valueOf(mRestrictions.getBoolean(str)));
        }
        return arrayList;
    }

    public List<Double> getAllDoublesForKey(String str) {
        ArrayList arrayList = new ArrayList();
        if (mRestrictions.containsKey(str) && (mRestrictions.get(str) instanceof Double)) {
            arrayList.add(Double.valueOf(mRestrictions.getDouble(str)));
        }
        return arrayList;
    }

    public List<Long> getAllIntegersForKey(String str) {
        ArrayList arrayList = new ArrayList();
        if (mRestrictions.containsKey(str) && (mRestrictions.get(str) instanceof Long)) {
            arrayList.add(Long.valueOf(mRestrictions.getLong(str)));
        }
        return arrayList;
    }

    public List<String> getAllStringsForKey(String str) {
        ArrayList arrayList = new ArrayList();
        if (mRestrictions.containsKey(str) && (mRestrictions.get(str) instanceof String)) {
            arrayList.add(mRestrictions.getString(str));
        }
        return arrayList;
    }

    public Boolean getBooleanForKey(String str, MAMAppConfig.BooleanQueryType booleanQueryType) {
        List<Boolean> allBooleansForKey = getAllBooleansForKey(str);
        if (allBooleansForKey.size() == 0) {
            return null;
        }
        return allBooleansForKey.get(0);
    }

    public Double getDoubleForKey(String str, MAMAppConfig.NumberQueryType numberQueryType) {
        List<Double> allDoublesForKey = getAllDoublesForKey(str);
        if (allDoublesForKey.size() == 0) {
            return null;
        }
        return allDoublesForKey.get(0);
    }

    public List<Map<String, String>> getFullData() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (String str : mRestrictions.keySet()) {
            hashMap.put(str, mRestrictions.get(str).toString());
        }
        arrayList.add(hashMap);
        return arrayList;
    }

    public Long getIntegerForKey(String str, MAMAppConfig.NumberQueryType numberQueryType) {
        List<Long> allIntegersForKey = getAllIntegersForKey(str);
        if (allIntegersForKey.size() == 0) {
            return null;
        }
        return allIntegersForKey.get(0);
    }

    public String getStringForKey(String str, MAMAppConfig.StringQueryType stringQueryType) {
        List<String> allStringsForKey = getAllStringsForKey(str);
        if (allStringsForKey.size() == 0) {
            return null;
        }
        return allStringsForKey.get(0);
    }

    public boolean hasConflict(String str) {
        return false;
    }

    public static AndroidEnterpriseAppConfig create(Context context, Set<String> set) {
        if (Build.VERSION.SDK_INT < 21) {
            return new AndroidEnterpriseAppConfig(new Bundle());
        }
        return new AndroidEnterpriseAppConfig(AndroidEnterpriseAppConfigUtil.a(((RestrictionsManager) context.getSystemService("restrictions")).getApplicationRestrictions(), context, set));
    }
}
