package com.microsoft.aad.adal;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public final class InstanceDiscoveryMetadata {
    public final List<String> mAliases = new ArrayList();
    public final boolean mIsValidated;
    public final String mPreferredCache;
    public final String mPreferredNetwork;

    public InstanceDiscoveryMetadata(boolean z) {
        this.mIsValidated = z;
        this.mPreferredNetwork = null;
        this.mPreferredCache = null;
    }

    public List<String> getAliases() {
        return this.mAliases;
    }

    public String getPreferredCache() {
        return this.mPreferredCache;
    }

    public String getPreferredNetwork() {
        return this.mPreferredNetwork;
    }

    public boolean isValidated() {
        return this.mIsValidated;
    }

    public InstanceDiscoveryMetadata(String str, String str2, List<String> list) {
        this.mPreferredNetwork = str;
        this.mPreferredCache = str2;
        this.mAliases.addAll(list);
        this.mIsValidated = true;
    }

    public InstanceDiscoveryMetadata(String str, String str2) {
        this.mPreferredNetwork = str;
        this.mPreferredCache = str2;
        this.mIsValidated = true;
    }
}
