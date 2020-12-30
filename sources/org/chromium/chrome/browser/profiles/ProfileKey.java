package org.chromium.chrome.browser.profiles;

import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class ProfileKey {

    /* renamed from: a */
    public long f2198a;

    public ProfileKey(long j) {
        this.f2198a = j;
        nativeIsOffTheRecord(this.f2198a);
    }

    /* renamed from: b */
    public static ProfileKey m2924b() {
        return (ProfileKey) nativeGetLastUsedProfileKey();
    }

    @CalledByNative
    public static ProfileKey create(long j) {
        return new ProfileKey(j);
    }

    @CalledByNative
    private long getNativePointer() {
        return this.f2198a;
    }

    public static native Object nativeGetLastUsedProfileKey();

    private native Object nativeGetOriginalKey(long j);

    private native boolean nativeIsOffTheRecord(long j);

    @CalledByNative
    private void onNativeDestroyed() {
        this.f2198a = 0;
    }

    /* renamed from: a */
    public ProfileKey mo9210a() {
        return (ProfileKey) nativeGetOriginalKey(this.f2198a);
    }
}
