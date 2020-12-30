package com.microsoft.aad.adal;

import java.net.URI;
import java.net.URISyntaxException;

/* compiled from: PG */
public final class ADFSWebFingerValidator {
    public static final String TAG = "ADFSWebFingerValidator";
    public static final URI TRUSTED_REALM_REL;

    static {
        try {
            TRUSTED_REALM_REL = new URI("http://schemas.microsoft.com/rel/trusted-realm");
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean realmIsTrusted(URI uri, WebFingerMetadata webFingerMetadata) {
        if (uri == null) {
            throw new IllegalArgumentException("Authority cannot be null");
        } else if (webFingerMetadata != null) {
            String a = Eo.a(new StringBuilder(), TAG, ":realmIsTrusted");
            Logger.m1251v(a, "Verifying trust authority. ", uri.toString() + webFingerMetadata.toString(), (ADALError) null);
            if (webFingerMetadata.getLinks() == null) {
                return false;
            }
            for (Link next : webFingerMetadata.getLinks()) {
                try {
                    URI uri2 = new URI(next.getHref());
                    URI uri3 = new URI(next.getRel());
                    if (uri2.getScheme().equalsIgnoreCase(uri.getScheme()) && uri2.getAuthority().equalsIgnoreCase(uri.getAuthority()) && uri3.equals(TRUSTED_REALM_REL)) {
                        return true;
                    }
                } catch (URISyntaxException unused) {
                }
            }
            return false;
        } else {
            throw new IllegalArgumentException("WebFingerMetadata cannot be null");
        }
    }
}
