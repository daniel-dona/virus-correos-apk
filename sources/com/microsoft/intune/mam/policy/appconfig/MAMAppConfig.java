package com.microsoft.intune.mam.policy.appconfig;

/* compiled from: PG */
public interface MAMAppConfig extends me0 {
    Boolean getBooleanForKey(String str, BooleanQueryType booleanQueryType);

    String getStringForKey(String str, StringQueryType stringQueryType);
}
