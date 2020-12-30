package com.microsoft.aad.adal;

import java.util.List;

/* compiled from: PG */
public final class WebFingerMetadata {
    @bK("links")
    public List<Link> mLinks;
    @bK("subject")
    public String mSubject;

    public List<Link> getLinks() {
        return this.mLinks;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setLinks(List<Link> list) {
        this.mLinks = list;
    }

    public void setSubject(String str) {
        this.mSubject = str;
    }
}
