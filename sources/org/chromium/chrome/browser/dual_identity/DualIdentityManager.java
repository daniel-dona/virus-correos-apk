package org.chromium.chrome.browser.dual_identity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.intune.mam.client.MAMIdentitySwitchResult;
import com.microsoft.intune.mam.client.app.AppIdentitySwitchReason;
import com.microsoft.intune.mam.client.app.AppIdentitySwitchResult;
import com.microsoft.intune.mam.client.app.AppIdentitySwitchResultCallback;
import com.microsoft.intune.mam.policy.MAMUserInfo;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.mmx.core.ui.WebPageShareActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.download.items.OfflineContentAggregatorFactory;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftOAuth2TokenServiceHelper;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.microsoft_signin.MsaNormalSignInActivity;
import org.chromium.chrome.browser.offlinepages.downloads.OfflinePageDownloadBridge;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.content_public.browser.BrowserStartupController;

/* compiled from: PG */
public class DualIdentityManager {

    /* renamed from: a */
    public static final String f1761a = "DualIdentityManager";

    /* renamed from: b */
    public static final Object f1762b = new Object();

    /* renamed from: c */
    public static AP0<Void> f1763c;

    /* renamed from: d */
    public static final HashMap<String, IProfileSwitchCallback> f1764d = new HashMap<>();

    /* renamed from: e */
    public static final HashMap<String, IGetProfileCallback> f1765e = new HashMap<>();

    /* renamed from: f */
    public static String f1766f;

    /* renamed from: g */
    public static boolean f1767g;

    /* renamed from: h */
    public static boolean f1768h;

    /* renamed from: i */
    public static boolean f1769i;

    /* renamed from: j */
    public static boolean f1770j;

    /* renamed from: k */
    public static boolean f1771k;

    /* renamed from: l */
    public static Intent f1772l;

    /* renamed from: m */
    public static boolean f1773m;

    /* renamed from: n */
    public static String f1774n;

    /* renamed from: o */
    public static boolean f1775o;

    /* renamed from: p */
    public static boolean f1776p = false;

    /* renamed from: q */
    public static AtomicBoolean f1777q = new AtomicBoolean(false);

    /* compiled from: PG */
    public interface IGetProfileCallback {
        void onProfileGot(Profile profile);
    }

    public /* synthetic */ DualIdentityManager(WS1 ws1) {
        MicrosoftSigninManager.C0424c.f2104a.mo8888a((MicrosoftSigninManager.SignInStateObserver) new WS1(this));
        DualIdentityUtils.m2248c(f1761a, "syncStateWithSignInManager");
        SharedPreferences sharedPreferences = QN0.a;
        String str = MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? "Default-AAD" : "Default";
        String string = sharedPreferences.getString("dual_identity_active_profile_dir", BuildConfig.FLAVOR);
        if (!string.endsWith(str)) {
            DualIdentityUtils.m2248c(f1761a, string + " does not match active mode: " + str + " need to do the correction");
            int lastIndexOf = string.lastIndexOf(47);
            if (lastIndexOf != -1) {
                string = string.substring(0, lastIndexOf) + str;
            }
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("dual_identity_active_profile_dir", string);
        edit.putString("dual_identity_active_profile_name", str);
        edit.apply();
    }

    /* renamed from: a */
    public static void m2206a(boolean z) {
        Context context = RN0.a;
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context.getPackageName(), WebPageShareActivity.class.getName());
        if (z) {
            Vc0.a(packageManager, componentName, 1, 1);
        } else {
            Vc0.a(packageManager, componentName, 2, 1);
        }
    }

    /* renamed from: b */
    public static /* synthetic */ String m2207b() {
        return f1761a;
    }

    /* renamed from: b */
    public static String m2208b(AuthenticationMode authenticationMode) {
        return authenticationMode == AuthenticationMode.AAD ? "Default-AAD" : "Default";
    }

    /* renamed from: c */
    public static /* synthetic */ void m2209c() {
        File file;
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "handleEnrollmentSucceededEvent after enroll and native load succeeded");
        File file2 = new File(m2194a(AuthenticationMode.AAD));
        File cacheDir = RN0.b().getCacheDir();
        try {
            file = RN0.b().getDatabasePath("BingSearchHistory.db");
        } catch (Exception unused) {
            VN0.a(f1761a, "error when get BingSearchHistory.db ", new Object[0]);
            file = null;
        }
        File[] fileArr = {file2, cacheDir, file};
        StringBuilder a = Eo.a("begin protectDirectoryWithIdentity ");
        a.append(MAMEdgeManager.m1305d());
        DualIdentityUtils.m2248c(f1761a, a.toString());
        m2196a(MAMEdgeManager.m1287a(fileArr, MAMEdgeManager.m1305d()));
        DualIdentityUtils.m2248c(f1761a, "finish protectDirectoryWithIdentity");
        MAMEdgeManager.m1312j();
        DualIdentityUtils.m2248c(f1761a, "handleEnrollmentSucceededEvent finished");
    }

    /* renamed from: d */
    public static /* synthetic */ void m2210d() {
        File dir = RN0.a.getDir("chrome", 0);
        File file = new File(dir, "Default");
        if (!file.exists() || !m2217k()) {
            VN0.c(f1761a, "No need to do AAD profile migration!", new Object[0]);
        } else {
            File file2 = new File(dir, "Default-AAD");
            if (file2.exists()) {
                VN0.c(f1761a, file2.getAbsolutePath() + " folder already exist!", new Object[0]);
            } else if (!file.renameTo(file2)) {
                VN0.a(f1761a, Eo.a("Failed to rename folder: ", file), new Object[0]);
            } else {
                SharedPreferences sharedPreferences = QN0.a;
                sharedPreferences.edit().putString("dual_identity_active_profile_dir", file2.getAbsolutePath()).apply();
                sharedPreferences.edit().putString("dual_identity_active_profile_name", "Default-AAD").apply();
                "PREF_DUAL_IDENTITY_ACTIVE_PROFILE_DIR_KEY is " + sharedPreferences.getString("dual_identity_active_profile_dir", "no-value");
                "PREF_DUAL_IDENTITY_ACTIVE_PROFILE_NAME_KEY is " + sharedPreferences.getString("dual_identity_active_profile_name", "no-value");
            }
        }
        Eo.b(QN0.a, "org.chromium.chrome.browser.dual_identity.DualIdentityManager.HAS_RUN_DUAL_IDENTITY_FILE_MIGRATION", true);
        VN0.c(f1761a, "Finished performing dual identity migration.", new Object[0]);
    }

    /* renamed from: e */
    public static /* synthetic */ void m2211e() {
        File file = new File(RN0.a.getApplicationInfo().dataDir);
        if (file.exists() && file.isDirectory()) {
            MAMEdgeManager.m1298b(file, BuildConfig.FLAVOR);
        }
        String string = QN0.a.getString("dual_identity_active_profile_dir", BuildConfig.FLAVOR);
        if (m2217k() && !TextUtils.isEmpty(string)) {
            File file2 = new File(string);
            if (file2.exists() && file2.isDirectory()) {
                MAMEdgeManager.m1298b(file2, MAMEdgeManager.m1305d());
            }
        }
        Eo.b(QN0.a, "org.chromium.chrome.browser.dual_identity.DualIdentityManager.HAS_RUN_DUAL_IDENTITY_DECRYPTION_MIGRATION", true);
        VN0.c(f1761a, "Finished performing dual identity decryption migration.", new Object[0]);
    }

    /* renamed from: f */
    public static void m2212f() {
        boolean z = QN0.a.getBoolean("org.chromium.chrome.browser.dual_identity.DualIdentityManager.HAS_RUN_DUAL_IDENTITY_FILE_MIGRATION", false);
        boolean z2 = QN0.a.getBoolean("org.chromium.chrome.browser.dual_identity.DualIdentityManager.HAS_RUN_DUAL_IDENTITY_DECRYPTION_MIGRATION", false);
        if (!z || !z2) {
            synchronized (f1762b) {
                if (f1763c == null) {
                    h hVar = new h(z, z2);
                    hVar.a(Us0.c);
                    f1763c = hVar;
                }
            }
        }
        DualIdentityUtils.m2248c(f1761a, "doMigrationIfNeeded started");
        m2196a((AP0) f1763c);
        DualIdentityUtils.m2248c(f1761a, "doMigrationIfNeeded finished");
    }

    /* renamed from: g */
    public static Intent m2213g() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "getCandidateIntentToProcess() return " + f1772l);
        return f1772l;
    }

    @CalledByNative
    public static String getInitDefaultProfile() {
        return MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? "Default-AAD" : "Default";
    }

    /* renamed from: h */
    public static String m2214h() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "getCandidateLaunchURL() return " + f1766f);
        return f1766f;
    }

    /* renamed from: i */
    public static DualIdentityManager m2215i() {
        return g.a;
    }

    /* renamed from: j */
    public static String m2216j() {
        String string = QN0.a.getString("dual_identity_active_profile_dir", BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(string)) {
            File file = new File(RN0.a.getDir("chrome", 0), MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? "Default-AAD" : "Default");
            if (!file.exists()) {
                file.mkdir();
            }
            string = file.getAbsolutePath();
        }
        DualIdentityUtils.m2248c(f1761a, "lastUsedProfileDir = " + string);
        return string;
    }

    /* renamed from: k */
    public static boolean m2217k() {
        String string = QN0.a.getString("microsoft_signin_manager_active_mode", BuildConfig.FLAVOR);
        AuthenticationMode authenticationMode = AuthenticationMode.AAD;
        return "AAD".equals(string);
    }

    /* renamed from: l */
    public static boolean m2218l() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "isDuringAccountSwitch() return " + f1770j);
        return f1770j;
    }

    /* renamed from: m */
    public static boolean m2219m() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "isIncognitoModeBeforeSwitch() return " + f1769i);
        return f1769i;
    }

    /* renamed from: n */
    public static boolean m2220n() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "isIncognitoModeRequired() return " + f1773m);
        return f1773m;
    }

    public static native String nativeGetLastUsedProfileDir();

    public static native void nativeGetProfileByName(String str, String str2);

    public static native String nativeGetProfileDirByName(String str);

    public static native void nativeSwitchToProfile(String str, String str2);

    /* renamed from: o */
    public static void m2221o() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "onEnrollmentSucceeded");
        m2202a((Runnable) new c());
    }

    @CalledByNative
    public static void onGetProfileCompleted(boolean z, String str, String str2, Profile profile) {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "onGetProfileCompleted for " + str);
        IGetProfileCallback iGetProfileCallback = f1765e.get(str2);
        if (!z) {
            DualIdentityUtils.m2248c(f1761a, "Get profile: " + str + " failed");
            if (iGetProfileCallback != null) {
                iGetProfileCallback.onProfileGot((Profile) null);
                f1764d.remove(str2);
                return;
            }
            return;
        }
        DualIdentityUtils.m2248c(f1761a, "Get profile: " + str + " succeeded");
        if (iGetProfileCallback != null) {
            iGetProfileCallback.onProfileGot(profile);
            f1764d.remove(str2);
        }
    }

    @CalledByNative
    public static void onSwitchProfileFinished(boolean z, String str, String str2) {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "onSwitchProfileFinished for " + str);
        IProfileSwitchCallback iProfileSwitchCallback = f1764d.get(str2);
        if (!z) {
            DualIdentityUtils.m2248c(f1761a, "switch to profile: " + str + "failed");
            if (iProfileSwitchCallback != null) {
                iProfileSwitchCallback.onProfileSwitchCompleted(false);
                f1764d.remove(str2);
                return;
            }
            return;
        }
        i iVar = new i(nativeGetLastUsedProfileDir(), str);
        iVar.a(Us0.d);
        m2196a((AP0) iVar);
        DualIdentityUtils.m2248c(f1761a, "Begin to do post Profile Switch stuffs on UI thread");
        if (pS1.a != null) {
            if (OfflinePageDownloadBridge.b == null) {
                OfflinePageDownloadBridge.b = new OfflinePageDownloadBridge();
            }
            OfflinePageDownloadBridge.b.a();
            OfflineContentAggregatorFactory.a = null;
            oS1 os1 = pS1.a;
            os1.a.a(os1);
            pS1.a = null;
        }
        if (TemplateUrlServiceFactory.f2200a != null) {
            TemplateUrlServiceFactory.f2200a = null;
        }
        PersonalDataManager.e().b();
        if (f1767g) {
            TemplateUrlServiceFactory.m2927a().mo9628d("bing.com");
            Mx1.a(RN0.a, "bing.com");
        }
        LW1.b().edit().remove("enhanced_bookmark_last_used_url").apply();
        if ("Default-AAD".equalsIgnoreCase(str)) {
            DualIdentityUtils.m2248c(f1761a, "switch to AAD profile, activateManagedFeatures");
            MAMEdgeManager.m1299b();
            cg0.a.a();
            SE2.a = tg0.h;
            tg0.f = true;
            m2206a(false);
        } else {
            DualIdentityUtils.m2248c(f1761a, "switch to non-AAD profile, deactivateManagedFeatures");
            MAMEdgeManager.f1289a.set(false);
            cg0.a.a.a();
            tg0.c();
            m2206a(true);
        }
        MicrosoftOAuth2TokenServiceHelper.m2573a(Profile.m2911j()).validateAccounts(str);
        m2222p();
        if (iProfileSwitchCallback != null) {
            iProfileSwitchCallback.onProfileSwitchCompleted(true);
            f1764d.remove(str2);
        }
    }

    /* renamed from: p */
    public static void m2222p() {
        ArrayList<Activity> arrayList = new ArrayList<>(2);
        List<Activity> b = ApplicationStatus.m1366b();
        for (int i = 0; i < b.size(); i++) {
            Activity activity = b.get(i);
            if (activity != null) {
                if (activity instanceof ChromeTabbedActivity) {
                    arrayList.add(activity);
                } else if (!(activity instanceof FirstRunActivity) && !(activity instanceof MicrosoftAccountSigninActivity) && !(activity instanceof MsaNormalSignInActivity)) {
                    activity.recreate();
                }
            }
        }
        for (Activity activity2 : arrayList) {
            ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activity2;
            chromeTabbedActivity.mo8036K1();
            chromeTabbedActivity.mo8236d2().a();
            f1776p = true;
            activity2.recreate();
        }
    }

    /* renamed from: q */
    public static boolean m2223q() {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "shouldNavigateToTabCenter() return " + f1768h);
        return f1768h;
    }

    /* renamed from: a */
    public static void m2204a(ProfileSwitchAccessPoint profileSwitchAccessPoint, AuthenticationMode authenticationMode) {
        m2205a(profileSwitchAccessPoint, authenticationMode, (IProfileSwitchCallback) new a(profileSwitchAccessPoint, authenticationMode));
    }

    /* renamed from: a */
    public static void m2205a(ProfileSwitchAccessPoint profileSwitchAccessPoint, AuthenticationMode authenticationMode, IProfileSwitchCallback iProfileSwitchCallback) {
        boolean z;
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, String.format(Locale.US, "Need to switch to profile %s from %s", new Object[]{authenticationMode.name(), profileSwitchAccessPoint.name()}));
        if (f1767g) {
            DualIdentityUtils.m2248c(f1761a, "sIsAADUserSignIn is not reset before, not expected!!!!");
            f1767g = false;
        }
        String b = m2208b(authenticationMode);
        String string = QN0.a.getString("dual_identity_active_profile_name", "Default");
        if (!b.equals(string)) {
            z = true;
        } else {
            VN0.c(f1761a, String.format(Locale.US, "Current profile name(%s) is already the same as target profile name(%s), ignore this switch", new Object[]{string, b}), new Object[0]);
            z = false;
        }
        if (z) {
            if (profileSwitchAccessPoint == ProfileSwitchAccessPoint.SIGN_IN && authenticationMode == AuthenticationMode.AAD) {
                DualIdentityUtils.m2248c(f1761a, "it is a new AAD user signed in, should ignore tab restore");
                f1767g = true;
            }
            if (profileSwitchAccessPoint == ProfileSwitchAccessPoint.ID_CONTROLLER_TOOLBAR_TABCENTER) {
                DualIdentityUtils.m2248c(f1761a, "it is a switch in tab center, should navigate to tab center after switch");
                f1768h = true;
            }
            f1769i = mx2.a;
            f1770j = true;
            String uuid = UUID.randomUUID().toString();
            f1764d.put(uuid, iProfileSwitchCallback);
            nativeSwitchToProfile(b, uuid);
            DualIdentityUtils.m2236a(profileSwitchAccessPoint, true, authenticationMode);
            return;
        }
        DualIdentityUtils.m2248c(f1761a, "Already in target profile, don't need to do the switch");
        DualIdentityUtils.m2236a(profileSwitchAccessPoint, false, authenticationMode);
        iProfileSwitchCallback.onProfileSwitchCompleted(false);
    }

    /* renamed from: a */
    public static void m2203a(String str) {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "setCandidateLaunchURL(" + str + ")");
        f1766f = str;
    }

    /* renamed from: a */
    public static void m2198a(Intent intent) {
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(f1761a, "setCandidateIntentToProcess(" + intent + ")");
        f1772l = intent;
    }

    /* renamed from: a */
    public static String m2194a(AuthenticationMode authenticationMode) {
        return nativeGetProfileDirByName(m2208b(authenticationMode));
    }

    /* renamed from: a */
    public static void m2200a(AuthenticationMode authenticationMode, IGetProfileCallback iGetProfileCallback) {
        ThreadUtils.m1462c();
        String uuid = UUID.randomUUID().toString();
        f1765e.put(uuid, iGetProfileCallback);
        nativeGetProfileByName(m2208b(authenticationMode), uuid);
    }

    /* renamed from: a */
    public static void m2201a(MAMIdentitySwitchResult mAMIdentitySwitchResult, Activity activity) {
        DualIdentityUtils.m2248c(f1761a, String.format(Locale.US, "source = %s, MAMIdentitySwitchResult = %s", new Object[]{activity.getLocalClassName(), mAMIdentitySwitchResult}));
        if (mAMIdentitySwitchResult == MAMIdentitySwitchResult.SUCCEEDED) {
            return;
        }
        if (MicrosoftSigninManager.m2574G().mo8932y()) {
            DualIdentityUtils.m2248c(f1761a, "MSA user signed in, switch to MSA");
            m2202a((Runnable) new b());
            return;
        }
        Sp2 a = Tp2.a(MicrosoftSigninManager.m2574G().mo8906f());
        if (!(activity instanceof ChromeTabbedActivity) || a == null || !a.a()) {
            DualIdentityUtils.m2248c(f1761a, "No MSA user signed in and inPrivate is disabled, back to home screen");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addFlags(268435456);
            RN0.d().startActivity(intent);
            return;
        }
        DualIdentityUtils.m2248c(f1761a, "No MSA user signed in, inPrivate is enabled, switch to inPrivate mode");
        ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activity;
        chromeTabbedActivity.mo8095d1().mo9443a(false).mo9432e();
        chromeTabbedActivity.mo8253m(true);
        chromeTabbedActivity.m1834b(true).b();
    }

    /* renamed from: a */
    public static void m2197a(Activity activity, String str, AppIdentitySwitchReason appIdentitySwitchReason, AppIdentitySwitchResultCallback appIdentitySwitchResultCallback) {
        int charAt;
        ThreadUtils.m1462c();
        boolean z = false;
        DualIdentityUtils.m2248c(f1761a, String.format(Locale.US, "source = %s, identity = %s, AppIdentitySwitchReason = %s", new Object[]{activity.getLocalClassName(), str, appIdentitySwitchReason}));
        if (appIdentitySwitchReason == AppIdentitySwitchReason.RESUME_CANCELLED) {
            DualIdentityUtils.m2248c(f1761a, "The reason is RESUME_CANCELLED, refuse the switch");
            if (appIdentitySwitchResultCallback != null) {
                appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.FAILURE);
                return;
            }
            return;
        }
        f1771k = true;
        boolean y = MicrosoftSigninManager.C0424c.f2104a.mo8932y();
        boolean w = MicrosoftSigninManager.C0424c.f2104a.mo8930w();
        boolean A = MicrosoftSigninManager.C0424c.f2104a.mo8866A();
        boolean b = MAMEdgeManager.m1301b(MicrosoftSigninManager.C0424c.f2104a.mo8899c());
        if (MAMEdgeManager.m1301b(str)) {
            DualIdentityUtils.m2248c(f1761a, "Implicit identity switch need managed identity");
            if (!w) {
                DualIdentityUtils.m2248c(f1761a, "No AAD account signed in but implicit identity switch need managed account, pop up sign in UX ");
                MicrosoftAccountSigninActivity.m2559a(activity, 31);
                f1774n = str;
                if (appIdentitySwitchResultCallback != null) {
                    appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.SUCCESS);
                    return;
                }
                return;
            } else if (!b) {
                DualIdentityUtils.m2248c(f1761a, "We don't have an managed identity");
                if (appIdentitySwitchResultCallback != null) {
                    appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.FAILURE);
                    return;
                }
                return;
            } else {
                DualIdentityUtils.m2248c(f1761a, "We have an managed identity");
                if (!A) {
                    DualIdentityUtils.m2248c(f1761a, "but it is not active, need to switch to AAD first");
                    m2199a(AuthenticationMode.AAD, appIdentitySwitchReason, appIdentitySwitchResultCallback);
                    return;
                }
            }
        } else {
            DualIdentityUtils.m2248c(f1761a, "Implicit identity switch need non-managed identity");
            String appPolicy = cd0.a(((MAMUserInfo) xb0.a(MAMUserInfo.class)).getPrimaryUser()).toString();
            int indexOf = appPolicy.indexOf("receiveSharingLevel = ") + 22;
            if (indexOf >= appPolicy.length() || !(appPolicy.charAt(indexOf) - '0' == 0 || charAt == 1)) {
                z = true;
            }
            if (A && b && !z) {
                DualIdentityUtils.m2248c(f1761a, "But we are now in managed identity");
                if (y) {
                    DualIdentityUtils.m2248c(f1761a, "MSA is signed in, switch to MSA profile");
                    m2199a(AuthenticationMode.MSA, appIdentitySwitchReason, appIdentitySwitchResultCallback);
                    return;
                }
                Sp2 a = Tp2.a(MicrosoftSigninManager.C0424c.f2104a.mo8906f());
                if (a == null || !a.a()) {
                    DualIdentityUtils.m2248c(f1761a, "only AAD is signed in and inPrivate mode is disabled, don't perform switch and refuse the identity switch");
                    if (appIdentitySwitchResultCallback != null) {
                        appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.FAILURE);
                        return;
                    }
                    return;
                }
                DualIdentityUtils.m2248c(f1761a, "AAD inPrivate mode is enabled, accept the switch");
                f1773m = true;
                if (appIdentitySwitchResultCallback != null) {
                    appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.SUCCESS);
                    return;
                }
                return;
            }
        }
        DualIdentityUtils.m2248c(f1761a, "No need to switch profile, directly return success");
        if (appIdentitySwitchResultCallback != null) {
            appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.SUCCESS);
        }
    }

    /* renamed from: a */
    public static void m2199a(AuthenticationMode authenticationMode, AppIdentitySwitchReason appIdentitySwitchReason, AppIdentitySwitchResultCallback appIdentitySwitchResultCallback) {
        Intent intent = new Intent("dual_identity_implicit_switch_action");
        intent.putExtra("extra_dual_identity_implicit_switch_reason", appIdentitySwitchReason.getCode());
        m2198a(intent);
        DualIdentityUtils.m2248c(f1761a, "Need to switch to profile: " + authenticationMode.name() + ", accept identity switch then do profile switch async");
        if (appIdentitySwitchResultCallback != null) {
            appIdentitySwitchResultCallback.reportIdentitySwitchResult(AppIdentitySwitchResult.SUCCESS);
        }
        m2202a((Runnable) new d(authenticationMode));
    }

    /* renamed from: a */
    public static void m2202a(Runnable runnable) {
        if (OP2.a(1).mo9665b()) {
            runnable.run();
        } else {
            OP2.a(1).mo9659a((BrowserStartupController.StartupCallback) new e(runnable));
        }
    }

    /* renamed from: a */
    public static void m2196a(AP0 ap0) {
        if (ap0 != null) {
            try {
                ap0.c();
            } catch (InterruptedException e) {
                StringBuilder a = Eo.a("waitForTaskFinish: ");
                a.append(e.getMessage());
                VN0.a(f1761a, a.toString(), new Object[0]);
            } catch (ExecutionException e2) {
                StringBuilder a2 = Eo.a("waitForTaskFinish: ");
                a2.append(e2.getMessage());
                VN0.a(f1761a, a2.toString(), new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m2195a() {
        if (!PersonalDataManager.g() || !PersonalDataManager.f()) {
            PersonalDataManager.nativeSetPref(10, true);
            PersonalDataManager.nativeSetPref(11, true);
        }
        if (!PrefServiceBridge.m2758o0().mo9062V() && !PrefServiceBridge.m2758o0().mo9063W()) {
            PrefServiceBridge.m2758o0().mo9142o(true);
        }
        if (!PrefServiceBridge.m2758o0().mo9058R() && !PrefServiceBridge.m2758o0().mo9059S()) {
            PrefServiceBridge.m2758o0().mo9136n(true);
        }
    }
}
