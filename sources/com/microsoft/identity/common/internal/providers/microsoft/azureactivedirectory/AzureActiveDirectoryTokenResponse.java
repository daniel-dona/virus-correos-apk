package com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory;

import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenResponse;
import java.util.Date;

/* compiled from: PG */
public class AzureActiveDirectoryTokenResponse extends MicrosoftTokenResponse {
    public Date mExpiresOn;
    public String mNotBefore;
    public String mResource;
    public String mSpeRing;

    public Date getExpiresOn() {
        return this.mExpiresOn;
    }

    public String getNotBefore() {
        return this.mNotBefore;
    }

    public String getResource() {
        return this.mResource;
    }

    public String getSpeRing() {
        return this.mSpeRing;
    }

    public void setExpiresOn(Date date) {
        this.mExpiresOn = date;
    }

    public void setNotBefore(String str) {
        this.mNotBefore = str;
    }

    public void setResource(String str) {
        this.mResource = str;
    }

    public void setSpeRing(String str) {
        this.mSpeRing = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("AzureActiveDirectoryTokenResponse{mExpiresOn=");
        a.append(this.mExpiresOn);
        a.append(", mResource='");
        Eo.a(a, this.mResource, '\'', ", mNotBefore='");
        Eo.a(a, this.mNotBefore, '\'', ", mSpeRing='");
        Eo.a(a, this.mSpeRing, '\'', "} ");
        a.append(super.toString());
        return a.toString();
    }
}
