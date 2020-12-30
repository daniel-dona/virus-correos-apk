package org.chromium.chrome.browser.profiles;

import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.cookies.CookiesFetcher;

/* compiled from: PG */
public class Profile {

    /* renamed from: a */
    public final boolean f2196a = nativeIsOffTheRecord(this.f2197b);

    /* renamed from: b */
    public long f2197b;

    public Profile(long j) {
        this.f2197b = j;
    }

    @CalledByNative
    public static Profile create(long j) {
        return new Profile(j);
    }

    @CalledByNative
    private long getNativePointer() {
        return this.f2197b;
    }

    /* renamed from: j */
    public static Profile m2911j() {
        if (OP2.a(1).mo9662a()) {
            return (Profile) nativeGetLastUsedProfile();
        }
        throw new IllegalStateException("Browser hasn't finished initialization yet!");
    }

    private native void nativeAddCookie(long j, String str, String str2, String str3, String str4, long j2, long j3, long j4, boolean z, boolean z2, int i, int i2);

    private native void nativeClearCookiesForDomain(long j, String str);

    private native void nativeDestroyWhenAppropriate(long j);

    public static native Object nativeGetLastUsedProfile();

    private native Object nativeGetOffTheRecordProfile(long j);

    private native Object nativeGetOriginalProfile(long j);

    private native Object nativeGetProfileKey(long j);

    private native String nativeGetUserId(long j);

    private native boolean nativeHasOffTheRecordProfile(long j);

    private native boolean nativeIsChild(long j);

    private native boolean nativeIsOffTheRecord(long j);

    private native void nativeWipe(long j);

    @CalledByNative
    private void onNativeDestroyed() {
        this.f2197b = 0;
        if (this.f2196a) {
            CookiesFetcher.m1994b();
        }
    }

    /* renamed from: a */
    public void mo9198a() {
        nativeDestroyWhenAppropriate(this.f2197b);
    }

    /* renamed from: b */
    public Profile mo9202b() {
        return (Profile) nativeGetOffTheRecordProfile(this.f2197b);
    }

    /* renamed from: c */
    public Profile mo9203c() {
        return (Profile) nativeGetOriginalProfile(this.f2197b);
    }

    /* renamed from: d */
    public ProfileKey mo9204d() {
        return (ProfileKey) nativeGetProfileKey(this.f2197b);
    }

    /* renamed from: e */
    public Sp2 mo9205e() {
        String f = mo9206f();
        "getUserId: " + f;
        if (TextUtils.isEmpty(f)) {
            return null;
        }
        return Tp2.a(f);
    }

    /* renamed from: f */
    public String mo9206f() {
        return nativeGetUserId(this.f2197b);
    }

    /* renamed from: g */
    public boolean mo9207g() {
        return nativeHasOffTheRecordProfile(this.f2197b);
    }

    /* renamed from: h */
    public boolean mo9208h() {
        return nativeIsChild(this.f2197b);
    }

    /* renamed from: i */
    public boolean mo9209i() {
        return this.f2196a;
    }

    /* renamed from: a */
    public boolean mo9200a(String str, String str2, String str3) {
        long j = this.f2197b;
        if (j == 0) {
            return false;
        }
        nativeAddCookie(j, str, str2, str3, "/", 0, 0, 0, false, false, 0, 0);
        return true;
    }

    /* renamed from: a */
    public boolean mo9201a(String str, String str2, String str3, String str4, long j, boolean z, boolean z2) {
        long j2 = this.f2197b;
        if (j2 == 0) {
            return false;
        }
        nativeAddCookie(j2, str, str2, str3, str4, 0, j, 0, z, z2, 0, 0);
        return true;
    }

    /* renamed from: a */
    public boolean mo9199a(String str) {
        long j = this.f2197b;
        if (j == 0) {
            return false;
        }
        nativeClearCookiesForDomain(j, str);
        return true;
    }
}
