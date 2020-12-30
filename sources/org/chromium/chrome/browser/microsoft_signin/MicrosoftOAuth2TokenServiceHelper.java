package org.chromium.chrome.browser.microsoft_signin;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public class MicrosoftOAuth2TokenServiceHelper {
    /* renamed from: a */
    public static MicrosoftOAuth2TokenService m2573a(Profile profile) {
        ThreadUtils.m1462c();
        return (MicrosoftOAuth2TokenService) nativeGetForProfile(profile);
    }

    public static native Object nativeGetForProfile(Profile profile);
}
