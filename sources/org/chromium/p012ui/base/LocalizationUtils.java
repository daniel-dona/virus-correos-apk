package org.chromium.p012ui.base;

import java.util.Locale;
import org.chromium.base.LocaleUtils;
import org.chromium.base.annotations.CalledByNative;

/* renamed from: org.chromium.ui.base.LocalizationUtils */
/* compiled from: PG */
public class LocalizationUtils {

    /* renamed from: a */
    public static Boolean f2605a;

    /* renamed from: a */
    public static String m3658a(String str) {
        return str.replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_'));
    }

    @CalledByNative
    public static String getDisplayNameForLocale(Locale locale, Locale locale2) {
        return locale.getDisplayName(locale2);
    }

    @CalledByNative
    public static Locale getJavaLocale(String str, String str2, String str3) {
        return new Locale(str, str2, str3);
    }

    @CalledByNative
    public static boolean isLayoutRtl() {
        if (f2605a == null) {
            boolean z = true;
            if (RN0.a.getResources().getConfiguration().getLayoutDirection() != 1) {
                z = false;
            }
            f2605a = Boolean.valueOf(z);
        }
        return f2605a.booleanValue();
    }

    public static native int nativeGetFirstStrongCharacterDirection(String str);
}
