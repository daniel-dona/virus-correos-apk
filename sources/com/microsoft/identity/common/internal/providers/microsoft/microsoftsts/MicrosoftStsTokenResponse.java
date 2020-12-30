package com.microsoft.identity.common.internal.providers.microsoft.microsoftsts;

import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenResponse;

/* compiled from: PG */
public class MicrosoftStsTokenResponse extends MicrosoftTokenResponse {
    @bK("not_before")
    public String mExpiresNotBefore;

    public String getExpiresNotBefore() {
        return this.mExpiresNotBefore;
    }

    public void setExpiresNotBefore(String str) {
        this.mExpiresNotBefore = str;
    }
}
