package com.microsoft.identity.common.internal.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public enum CredentialType {
    RefreshToken,
    AccessToken,
    IdToken,
    V1IdToken,
    Password,
    Cookie,
    Certificate;

    public static CredentialType fromString(String str) {
        for (CredentialType credentialType : values()) {
            if (credentialType.name().equalsIgnoreCase(str)) {
                return credentialType;
            }
        }
        return null;
    }

    public static Set<String> valueSet() {
        HashSet hashSet = new HashSet();
        for (CredentialType name : values()) {
            hashSet.add(name.name());
        }
        return Collections.unmodifiableSet(hashSet);
    }
}
