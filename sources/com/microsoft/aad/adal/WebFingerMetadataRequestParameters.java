package com.microsoft.aad.adal;

import java.net.URL;

/* compiled from: PG */
public final class WebFingerMetadataRequestParameters {
    public final URL mDomain;
    public final DRSMetadata mMetadata;

    public WebFingerMetadataRequestParameters(URL url, DRSMetadata dRSMetadata) {
        this.mDomain = url;
        this.mMetadata = dRSMetadata;
    }

    public URL getDomain() {
        return this.mDomain;
    }

    public DRSMetadata getDrsMetadata() {
        return this.mMetadata;
    }
}
