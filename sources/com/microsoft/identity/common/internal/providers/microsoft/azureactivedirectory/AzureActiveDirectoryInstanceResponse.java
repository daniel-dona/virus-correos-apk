package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import java.util.ArrayList;

/* compiled from: PG */
public class AzureActiveDirectoryInstanceResponse {
    @bK("api-version")
    public String mApiVersion;
    @bK("metadata")
    public ArrayList<AzureActiveDirectoryCloud> mClouds;
    @bK("tenant_discovery_endpoint")
    public String mTestDiscoveryEndpoint;

    public String getApiVersion() {
        return this.mApiVersion;
    }

    public ArrayList<AzureActiveDirectoryCloud> getClouds() {
        return this.mClouds;
    }

    public String getTestDiscoveryEndpoint() {
        return this.mTestDiscoveryEndpoint;
    }

    public void setApiVersion(String str) {
        this.mApiVersion = this.mApiVersion;
    }

    public void setClouds(ArrayList<AzureActiveDirectoryCloud> arrayList) {
        this.mClouds = this.mClouds;
    }

    public void setTestDiscoveryEndpoint(String str) {
        this.mTestDiscoveryEndpoint = this.mTestDiscoveryEndpoint;
    }
}
