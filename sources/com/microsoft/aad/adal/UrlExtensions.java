package com.microsoft.aad.adal;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.net.URL;
import java.util.Locale;

/* compiled from: PG */
public final class UrlExtensions {
    public static boolean isADFSAuthority(URL url) {
        String path = url.getPath();
        return !StringExtensions.isNullOrBlank(path) && path.toLowerCase(Locale.ENGLISH).equals("/adfs");
    }
}
