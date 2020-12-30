package org.chromium.chrome.browser.util;

import android.net.Uri;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.Oauth2;
import java.net.URI;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.chromium.base.CollectionUtil;

/* compiled from: PG */
public class UrlUtilities {

    /* renamed from: a */
    public static final HashSet<String> f2348a = CollectionUtil.m1382b("chrome", "chrome-native", "about", "edge", "edge-native");

    static {
        Pattern.compile("^[\\w\\.-]*$");
        Pattern.compile("^[\\w\\.-]*$");
        Pattern.compile("^[\\w\\./-]*$");
        Pattern.compile("^[a-zA-Z]+$");
    }

    /* renamed from: a */
    public static boolean m3282a(URI uri) {
        return f2348a.contains(uri.getScheme());
    }

    /* renamed from: b */
    public static String m3283b(String str) {
        if (str == null || !str.contains(":")) {
            return BuildConfig.FLAVOR;
        }
        String[] split = str.split(":");
        if (split.length <= 1) {
            return BuildConfig.FLAVOR;
        }
        return split[1];
    }

    /* renamed from: c */
    public static boolean m3285c(String str) {
        String scheme = Uri.parse(str).getScheme();
        return "http".equals(scheme) || Oauth2.HTTPS_PROTOCOL_STRING.equals(scheme);
    }

    /* renamed from: d */
    public static boolean m3286d(String str) {
        if (str == null || !str.startsWith("tel:") || str.split(":").length <= 1) {
            return false;
        }
        return true;
    }

    /* renamed from: e */
    public static String m3287e(String str) {
        Uri parse = Uri.parse(str);
        StringBuilder sb = new StringBuilder();
        sb.append(parse.getScheme());
        sb.append("://");
        String host = parse.getHost();
        String str2 = BuildConfig.FLAVOR;
        sb.append(host != null ? parse.getHost() : str2);
        if (parse.getPort() != -1) {
            StringBuilder a = Eo.a(":");
            a.append(parse.getPort());
            str2 = a.toString();
        }
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: f */
    public static String m3288f(String str) {
        Uri parse;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
            return null;
        }
        String queryParameter = parse.getQueryParameter("q");
        if (!TextUtils.isEmpty(queryParameter)) {
            return queryParameter;
        }
        String queryParameter2 = parse.getQueryParameter("query");
        if (!TextUtils.isEmpty(queryParameter2)) {
            return queryParameter2;
        }
        return null;
    }

    /* renamed from: g */
    public static String m3289g(String str) {
        String trim = str.trim();
        if (trim.startsWith("https://")) {
            return trim.substring(8);
        }
        return trim.startsWith("http://") ? trim.substring(7) : trim;
    }

    public static native String nativeGetDomainAndRegistry(String str, boolean z);

    public static native boolean nativeIsAcceptedScheme(String str);

    public static native boolean nativeIsDownloadable(String str);

    public static native boolean nativeIsGoogleDomainUrl(String str, boolean z);

    public static native boolean nativeIsGoogleHomePageUrl(String str);

    public static native boolean nativeIsGoogleSearchUrl(String str);

    public static native boolean nativeIsGoogleSubDomainUrl(String str);

    public static native boolean nativeIsUrlWithinScope(String str, String str2);

    public static native boolean nativeIsValidForIntentFallbackNavigation(String str);

    public static native boolean nativeSameDomainOrHost(String str, String str2, boolean z);

    public static native boolean nativeUrlsFragmentsDiffer(String str, String str2);

    public static native boolean nativeUrlsMatchIgnoringFragments(String str, String str2);

    /* renamed from: a */
    public static String m3280a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return nativeGetDomainAndRegistry(str, z);
    }

    /* renamed from: a */
    public static boolean m3281a(String str, String str2) {
        if (TextUtils.equals(str, str2)) {
            return false;
        }
        return nativeUrlsFragmentsDiffer(str, str2);
    }

    /* renamed from: b */
    public static boolean m3284b(String str, String str2) {
        if (TextUtils.equals(str, str2)) {
            return true;
        }
        return nativeUrlsMatchIgnoringFragments(str, str2);
    }

    /* renamed from: a */
    public static String m3279a(String str) {
        String scheme = Uri.parse(str).getScheme();
        if ("chrome-native".equals(scheme)) {
            return str.replaceFirst(scheme, "edge-native");
        }
        return "chrome".equals(scheme) ? str.replaceFirst(scheme, "edge") : str;
    }
}
