package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public class AzureActiveDirectoryCloud {
    @bK("aliases")
    public List<String> mCloudHostAliases;
    public boolean mIsValidated;
    @bK("preferred_cache")
    public final String mPreferredCacheHostName;
    @bK("preferred_network")
    public final String mPreferredNetworkHostName;

    public AzureActiveDirectoryCloud(boolean z) {
        this.mIsValidated = z;
        this.mPreferredNetworkHostName = null;
        this.mPreferredCacheHostName = null;
    }

    public List<String> getHostAliases() {
        return this.mCloudHostAliases;
    }

    public String getPreferredCacheHostName() {
        return this.mPreferredCacheHostName;
    }

    public String getPreferredNetworkHostName() {
        return this.mPreferredNetworkHostName;
    }

    public boolean isValidated() {
        return this.mIsValidated;
    }

    public void setIsValidated(boolean z) {
        this.mIsValidated = z;
    }

    public AzureActiveDirectoryCloud(String str, String str2, List<String> list) {
        this.mPreferredNetworkHostName = str;
        this.mPreferredCacheHostName = str2;
        this.mCloudHostAliases = new ArrayList();
        this.mCloudHostAliases.addAll(list);
        this.mIsValidated = true;
    }

    public AzureActiveDirectoryCloud(String str, String str2) {
        this.mPreferredNetworkHostName = str;
        this.mPreferredCacheHostName = str2;
        this.mIsValidated = true;
    }
}
