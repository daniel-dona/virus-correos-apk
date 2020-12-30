package org.chromium.base;

import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class LocaleUtils {
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1432a(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 3365(0xd25, float:4.715E-42)
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x0036
            r1 = 3374(0xd2e, float:4.728E-42)
            if (r0 == r1) goto L_0x002c
            r1 = 3391(0xd3f, float:4.752E-42)
            if (r0 == r1) goto L_0x0022
            r1 = 3704(0xe78, float:5.19E-42)
            if (r0 == r1) goto L_0x0018
            goto L_0x0040
        L_0x0018:
            java.lang.String r0 = "tl"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 3
            goto L_0x0041
        L_0x0022:
            java.lang.String r0 = "ji"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x002c:
            java.lang.String r0 = "iw"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 0
            goto L_0x0041
        L_0x0036:
            java.lang.String r0 = "in"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 2
            goto L_0x0041
        L_0x0040:
            r0 = -1
        L_0x0041:
            if (r0 == 0) goto L_0x0053
            if (r0 == r4) goto L_0x0050
            if (r0 == r3) goto L_0x004d
            if (r0 == r2) goto L_0x004a
            return r5
        L_0x004a:
            java.lang.String r5 = "fil"
            return r5
        L_0x004d:
            java.lang.String r5 = "id"
            return r5
        L_0x0050:
            java.lang.String r5 = "yi"
            return r5
        L_0x0053:
            java.lang.String r5 = "he"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.LocaleUtils.m1432a(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public static String m1434b(String str) {
        int indexOf = str.indexOf(45);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(0, indexOf);
    }

    @CalledByNative
    public static String getDefaultCountryCode() {
        CommandLine c = CommandLine.m1384c();
        if (c.mo7859c("default-country-code")) {
            return c.mo7857b("default-country-code");
        }
        return Locale.getDefault().getCountry();
    }

    @CalledByNative
    public static String getDefaultLocaleString() {
        return m1433a(Locale.getDefault());
    }

    /* renamed from: a */
    public static String m1433a(Locale locale) {
        String a = m1432a(locale.getLanguage());
        String country = locale.getCountry();
        if (!a.equals("no") || !country.equals("NO") || !locale.getVariant().equals("NY")) {
            return country.isEmpty() ? a : Eo.b(a, CacheKeyValueDelegate.CACHE_VALUE_SEPARATOR, country);
        }
        return "nn-NO";
    }
}
