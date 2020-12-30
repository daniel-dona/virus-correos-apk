package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAuthorizationRequest;

/* compiled from: PG */
public class AzureActiveDirectoryAuthorizationRequest extends MicrosoftAuthorizationRequest {
    @bK("claims")
    public String mClaimsChallenge;
    @bK("prompt")
    public String mPrompt;
    @bK("resource")
    public String mResource;

    /* compiled from: PG */
    public static class Builder extends MicrosoftAuthorizationRequest.Builder<Builder> {
        public String mClaimsChallenge;
        public String mPrompt;
        public String mResource;

        public Builder self() {
            return this;
        }

        public Builder setClaimsChallenge(String str) {
            this.mClaimsChallenge = str;
            return this;
        }

        public Builder setResource(String str) {
            this.mResource = str;
            return this;
        }

        public AzureActiveDirectoryAuthorizationRequest build() {
            setLibraryName("ADAL.Android");
            setLibraryVersion("1.15.2");
            return new AzureActiveDirectoryAuthorizationRequest(this);
        }

        public Builder setPrompt(String str) {
            this.mPrompt = str;
            return this;
        }
    }

    public AzureActiveDirectoryAuthorizationRequest(Builder builder) {
        super(builder);
        this.mResource = builder.mResource;
        this.mPrompt = builder.mPrompt;
        this.mClaimsChallenge = builder.mClaimsChallenge;
    }

    public String getAuthorizationEndpoint() {
        return null;
    }

    public String getClaimsChallenge() {
        return this.mClaimsChallenge;
    }

    public String getPrompt() {
        return this.mPrompt;
    }

    public String getResource() {
        return this.mResource;
    }
}
