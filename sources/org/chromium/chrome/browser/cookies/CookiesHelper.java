package org.chromium.chrome.browser.cookies;

import java.util.List;
import org.chromium.base.ThreadUtils;

/* compiled from: PG */
public class CookiesHelper {

    /* renamed from: a */
    public static List<String> f1708a;

    /* renamed from: a */
    public static void m1997a(String str, boolean z) {
        if (!str.isEmpty()) {
            str = Eo.a("type:MSA,token:", str);
        }
        nativeAddCookie("lt", str, z ? ".int1.msn.com" : ".msn.com", "/", 0, 0, 0, true, true, 0, 1);
    }

    public static native void nativeAddCookie(String str, String str2, String str3, String str4, long j, long j2, long j3, boolean z, boolean z2, int i, int i2);

    public static native void nativeDeleteCookie(String str, String str2);

    /* renamed from: a */
    public static void m1998a(List<String> list) {
        ThreadUtils.m1461b((Runnable) new a(list));
    }
}
