package org.chromium.chrome.browser.microsoft_signin;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.components.signin.MicrosoftAccountTrackerService;

/* compiled from: PG */
public class MicrosoftOAuth2TokenService {

    /* renamed from: a */
    public final long f2084a;

    /* renamed from: b */
    public final MicrosoftAccountTrackerService f2085b;

    public MicrosoftOAuth2TokenService(long j, MicrosoftAccountTrackerService microsoftAccountTrackerService) {
        this.f2084a = j;
        this.f2085b = microsoftAccountTrackerService;
    }

    @CalledByNative
    public static MicrosoftOAuth2TokenService create(long j, MicrosoftAccountTrackerService microsoftAccountTrackerService) {
        ThreadUtils.m1462c();
        return new MicrosoftOAuth2TokenService(j, microsoftAccountTrackerService);
    }

    @CalledByNative
    public static void getOAuth2AuthToken(String str, String str2, String str3, long j) {
        new a(str2, str3, j).a(Us0.c);
    }

    private native void nativeFireRefreshTokenAvailableFromJava(long j, String str);

    public static native void nativeOAuth2TokenFetched(String str, boolean z, long j);

    @CalledByNative
    public void validateAccounts(String str) {
        ThreadUtils.m1462c();
        if ("Default".equals(str) && MicrosoftSigninManager.C0424c.f2104a.mo8932y()) {
            this.f2085b.a(MicrosoftSigninManager.C0424c.f2104a.mo8924q(), MicrosoftSigninManager.C0424c.f2104a.mo8920m(), new MicrosoftAccountTrackerService.EdgeSignInInfo(1));
            nativeFireRefreshTokenAvailableFromJava(this.f2084a, MicrosoftSigninManager.C0424c.f2104a.mo8920m());
        } else if ("Default-AAD".equals(str) && MicrosoftSigninManager.C0424c.f2104a.mo8930w()) {
            this.f2085b.a(MicrosoftSigninManager.C0424c.f2104a.mo8906f(), MicrosoftSigninManager.C0424c.f2104a.mo8899c(), new MicrosoftAccountTrackerService.EdgeSignInInfo(2, MicrosoftSigninManager.C0424c.f2104a.mo8902d(), MicrosoftSigninManager.C0424c.f2104a.mo8904e()));
            nativeFireRefreshTokenAvailableFromJava(this.f2084a, MicrosoftSigninManager.C0424c.f2104a.mo8899c());
        }
    }

    /* renamed from: a */
    public void mo8864a(String str) {
        this.f2085b.a(str);
    }
}
