package org.chromium.components.url_formatter;

import android.text.TextUtils;

/* compiled from: PG */
public final class UrlFormatter {
    /* renamed from: a */
    public static String m3350a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return nativeFixupUrl(str);
    }

    /* renamed from: b */
    public static String m3351b(String str) {
        return nativeFormatUrlForDisplayOmitScheme(str);
    }

    public static native String nativeFixupUrl(String str);

    public static native String nativeFormatUrlForCopy(String str);

    public static native String nativeFormatUrlForDisplayOmitHTTPScheme(String str);

    public static native String nativeFormatUrlForDisplayOmitScheme(String str);

    public static native String nativeFormatUrlForSecurityDisplay(String str);

    public static native String nativeFormatUrlForSecurityDisplayOmitScheme(String str);

    public static native void nativeUpdateDefaultSchemeString(String str);
}
