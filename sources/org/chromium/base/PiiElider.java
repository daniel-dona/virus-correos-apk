package org.chromium.base;

import android.text.TextUtils;
import android.util.Patterns;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: PG */
public class PiiElider {

    /* renamed from: a */
    public static final Pattern f1466a = Pattern.compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))");

    /* renamed from: b */
    public static final Pattern f1467b;

    /* renamed from: c */
    public static final Pattern f1468c = Pattern.compile("\\sat\\s(org\\.chromium|com\\.microsoft)\\.[^ ]+.");

    /* renamed from: d */
    public static final Pattern f1469d;

    /* renamed from: e */
    public static final Pattern f1470e = Pattern.compile("([0-9a-fA-F]{2}[-:]+){5}[0-9a-fA-F]{2}");

    /* renamed from: f */
    public static final Pattern f1471f = Pattern.compile("\\[\\w*:CONSOLE.*\\].*");

    /* renamed from: g */
    public static final String[] f1472g = {"org.chromium.", "com.google."};

    /* renamed from: h */
    public static final String[] f1473h = {"android.accessibilityservice", "android.accounts", "android.animation", "android.annotation", "android.app", "android.appwidget", "android.bluetooth", "android.content", "android.database", "android.databinding", "android.drm", "android.gesture", "android.graphics", "android.hardware", "android.inputmethodservice", "android.location", "android.media", "android.mtp", "android.net", "android.nfc", "android.opengl", "android.os", "android.preference", "android.print", "android.printservice", "android.provider", "android.renderscript", "android.sax", "android.security", "android.service", "android.speech", "android.support", "android.system", "android.telecom", "android.telephony", "android.test", "android.text", "android.transition", "android.util", "android.view", "android.webkit", "android.widget", "com.android.", "dalvik.", "java.", "javax.", "org.apache.", "org.json.", "org.w3c.dom.", "org.xml.", "org.xmlpull."};

    static {
        StringBuilder a = Eo.a("(([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯\\-]{0,61}[a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]){0,1}\\.)+[a-zA-Z -퟿豈-﷏ﷰ-￯]{2,63}|");
        a.append(f1466a);
        a.append(")");
        f1467b = Pattern.compile(a.toString());
        StringBuilder a2 = Eo.a("(?:\\b|^)((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:");
        a2.append(f1467b);
        a2.append(")(?:\\:\\d{1,5})?)(\\/(?:(?:[");
        a2.append("a-zA-Z0-9 -퟿豈-﷏ﷰ-￯");
        a2.append("\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
        f1469d = Pattern.compile(a2.toString());
    }

    /* renamed from: a */
    public static String m1447a(String str) {
        return f1471f.matcher(str).replaceAll("[ELIDED:CONSOLE(0)] ELIDED CONSOLE MESSAGE");
    }

    /* renamed from: b */
    public static String m1448b(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).replaceAll("XXX@EMAIL.ELIDED");
    }

    /* renamed from: c */
    public static String m1449c(String str) {
        return Patterns.IP_ADDRESS.matcher(str).replaceAll("1.2.3.4");
    }

    /* renamed from: d */
    public static String m1450d(String str) {
        return f1470e.matcher(str).replaceAll("01:23:45:67:89:AB");
    }

    /* renamed from: e */
    public static String m1451e(String str) {
        boolean z;
        boolean z2;
        if (f1468c.matcher(str).find()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Matcher matcher = f1469d.matcher(sb);
        int i = 0;
        while (matcher.find(i)) {
            int start = matcher.start();
            int end = matcher.end();
            String substring = sb.substring(start, end);
            String[] strArr = f1472g;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= length) {
                    z2 = false;
                    break;
                } else if (substring.startsWith(strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z2) {
                String[] strArr2 = f1473h;
                int length2 = strArr2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z = false;
                        break;
                    } else if (substring.startsWith(strArr2[i3])) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z) {
                    sb.replace(start, end, "XXXX://WEBADDRESS.ELIDED");
                    i = start + 24;
                    matcher = f1469d.matcher(sb);
                }
            }
            i = end;
        }
        return sb.toString();
    }

    public static String sanitizeStacktrace(String str) {
        String[] split = str.split("\\n");
        split[0] = m1451e(split[0]);
        for (int i = 1; i < split.length; i++) {
            if (split[i].startsWith("Caused by:")) {
                split[i] = m1451e(split[i]);
            }
        }
        return TextUtils.join(AbstractAccountCredentialCache.NEW_LINE, split);
    }
}
