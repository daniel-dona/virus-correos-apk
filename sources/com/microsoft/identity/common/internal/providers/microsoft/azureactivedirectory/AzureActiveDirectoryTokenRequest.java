package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import com.microsoft.identity.common.internal.providers.oauth2.TokenRequest;

/* compiled from: PG */
public class AzureActiveDirectoryTokenRequest extends TokenRequest {
    @bK("resource")
    public String mResourceId;

    public String getResourceId() {
        return this.mResourceId;
    }

    public void setResourceId(String str) {
        this.mResourceId = str;
    }
}
