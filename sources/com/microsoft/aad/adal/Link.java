package com.microsoft.aad.adal;

/* compiled from: PG */
public final class Link {
    @bK("href")
    public String mHref;
    @bK("rel")
    public String mRel;

    public String getHref() {
        return this.mHref;
    }

    public String getRel() {
        return this.mRel;
    }

    public void setHref(String str) {
        this.mHref = str;
    }

    public void setRel(String str) {
        this.mRel = str;
    }
}
