package org.chromium.chrome.browser.cookies;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public class CookiesFetcher {
    /* renamed from: b */
    public static boolean m1994b() {
        try {
            if (Profile.m2911j().mo9207g()) {
                return false;
            }
            new a().a(AP0.g);
            return true;
        } catch (RuntimeException e) {
            qI.a.a(e);
            return false;
        }
    }

    /* renamed from: c */
    public static String m1995c() {
        return RN0.a.getFileStreamPath("COOKIES.DAT").getAbsolutePath();
    }

    @CalledByNative
    public static CanonicalCookie createCookie(String str, String str2, String str3, String str4, long j, long j2, long j3, boolean z, boolean z2, int i, int i2) {
        return new CanonicalCookie(str, str2, str3, str4, j, j2, j3, z, z2, i, i2);
    }

    @CalledByNative
    public static CanonicalCookie[] createCookiesArray(int i) {
        return new CanonicalCookie[i];
    }

    /* renamed from: d */
    public static void m1996d() {
        new a().a(AP0.g);
    }

    public static native void nativePersistCookies();

    public static native void nativeRestoreCookies(String str, String str2, String str3, String str4, long j, long j2, long j3, boolean z, boolean z2, int i, int i2);

    @CalledByNative
    public static void onCookieFetchFinished(CanonicalCookie[] canonicalCookieArr) {
        new b(canonicalCookieArr).a(AP0.g);
    }
}
