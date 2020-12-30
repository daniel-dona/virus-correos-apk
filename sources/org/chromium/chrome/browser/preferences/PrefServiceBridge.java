package org.chromium.chrome.browser.preferences;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.website.ContentSettingException;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;

/* compiled from: PG */
public class PrefServiceBridge implements Rp2 {

    /* renamed from: a */
    public static final String[] f2178a = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    /* renamed from: b */
    public static final String[] f2179b = {"android.permission.CAMERA"};

    /* renamed from: c */
    public static final String[] f2180c = {"android.permission.RECORD_AUDIO"};

    /* renamed from: d */
    public static final String[] f2181d = new String[0];

    /* renamed from: e */
    public static PrefServiceBridge f2182e;

    /* compiled from: PG */
    public static class AboutVersionStrings {

        /* renamed from: a */
        public final String f2183a;

        /* renamed from: b */
        public final String f2184b;

        public /* synthetic */ AboutVersionStrings(String str, String str2, C0428a aVar) {
            this.f2183a = str;
            this.f2184b = str2;
        }

        /* renamed from: a */
        public String mo9159a() {
            return this.f2183a;
        }

        /* renamed from: b */
        public String mo9160b() {
            return this.f2184b;
        }
    }

    @CalledByNative
    public static void addContentSettingExceptionToList(ArrayList<ContentSettingException> arrayList, int i, String str, int i2, String str2) {
        arrayList.add(new ContentSettingException(i, str, Integer.valueOf(i2), str2));
    }

    @CalledByNative
    public static void addNewLanguageItemToList(List<rn2> list, String str, String str2, String str3, boolean z) {
        list.add(new rn2(str, str2, str3, z));
    }

    @CalledByNative
    public static void copyStringArrayToList(List<String> list, String[] strArr) {
        list.addAll(Arrays.asList(strArr));
    }

    @CalledByNative
    public static AboutVersionStrings createAboutVersionStrings(String str, String str2) {
        return new AboutVersionStrings(str, str2, (C0428a) null);
    }

    @CalledByNative
    public static String[] getAndroidPermissionsForContentSetting(int i) {
        if (i == 5) {
            String[] strArr = f2178a;
            return (String[]) Arrays.copyOf(strArr, strArr.length);
        } else if (i == 9) {
            String[] strArr2 = f2180c;
            return (String[]) Arrays.copyOf(strArr2, strArr2.length);
        } else if (i != 10) {
            return f2181d;
        } else {
            String[] strArr3 = f2179b;
            return (String[]) Arrays.copyOf(strArr3, strArr3.length);
        }
    }

    private native boolean nativeCanPrefetchAndPrerender();

    private native AboutVersionStrings nativeGetAboutVersionStrings();

    private native boolean nativeGetAcceptCookiesEnabled();

    private native boolean nativeGetAcceptCookiesManagedByCustodian();

    private native boolean nativeGetAcceptCookiesUserModifiable();

    private native boolean nativeGetAllowLocationEnabled();

    private native boolean nativeGetAllowLocationManagedByCustodian();

    private native boolean nativeGetAllowLocationUserModifiable();

    private native boolean nativeGetAutoTranslateEnabled();

    private native boolean nativeGetAutomaticDownloadsEnabled();

    private native boolean nativeGetAutoplayEnabled();

    private native boolean nativeGetBackgroundSyncEnabled();

    private native boolean nativeGetBlockThirdPartyCookiesEnabled();

    private native boolean nativeGetBlockThirdPartyCookiesManaged();

    private native boolean nativeGetBoolean(int i);

    private native boolean nativeGetBrowsingDataDeletionPreference(int i, int i2);

    private native int nativeGetBrowsingDataDeletionTimePeriod(int i);

    private native boolean nativeGetCameraEnabled();

    private native boolean nativeGetCameraManagedByCustodian();

    private native boolean nativeGetCameraUserModifiable();

    private native void nativeGetChromeAcceptLanguages(List<rn2> list);

    private native boolean nativeGetClickedUpdateMenuItem();

    private native void nativeGetContentSettingsExceptions(int i, List<ContentSettingException> list);

    private native String nativeGetContextualSearchPreference();

    private native boolean nativeGetContextualSearchPreferenceIsManaged();

    private native int nativeGetDefaultSupervisedUserFilteringBehavior();

    private native boolean nativeGetDoNotTrackEnabled();

    private native String nativeGetDownloadDefaultDirectory();

    private native boolean nativeGetEdgeBrowsingDataDeletionPreference(int i, int i2);

    private native int nativeGetEnhancedTrackingPreventionUserPref();

    private native boolean nativeGetExplicitLanguageAskPromptShown();

    private native boolean nativeGetFamilySafetyBlockEmbedVideoEnabled();

    private native boolean nativeGetFirstRunEulaAccepted();

    private native boolean nativeGetIncognitoModeEnabled();

    private native boolean nativeGetIncognitoModeManaged();

    private native String nativeGetKeyBoardLanguage();

    private native int nativeGetLastClearBrowsingDataTab();

    private native String nativeGetLatestVersionWhenClickedUpdateMenuItem();

    private native boolean nativeGetLocationAllowedByPolicy();

    private native boolean nativeGetMicEnabled();

    private native boolean nativeGetMicManagedByCustodian();

    private native boolean nativeGetMicUserModifiable();

    private native boolean nativeGetNetworkPredictionEnabled();

    private native boolean nativeGetNetworkPredictionManaged();

    private native boolean nativeGetNotificationsEnabled();

    private native boolean nativeGetNotificationsVibrateEnabled();

    private native boolean nativeGetPasswordEchoEnabled();

    private native boolean nativeGetPasswordManagerAutoSigninEnabled();

    private native boolean nativeGetPasswordManagerAutoSigninManaged();

    private native boolean nativeGetPrintingEnabled();

    private native boolean nativeGetPrintingManaged();

    private native int nativeGetPromptForDownloadAndroid();

    private native boolean nativeGetRememberPasswordsEnabled();

    private native boolean nativeGetRememberPasswordsManaged();

    private native boolean nativeGetResolveNavigationErrorEnabled();

    private native boolean nativeGetResolveNavigationErrorManaged();

    private native boolean nativeGetSafeBrowsingEnabled();

    private native boolean nativeGetSafeBrowsingExtendedReportingEnabled();

    private native boolean nativeGetSafeBrowsingExtendedReportingManaged();

    private native boolean nativeGetSafeBrowsingManaged();

    private native boolean nativeGetSearchSuggestEnabled();

    private native boolean nativeGetSearchSuggestManaged();

    private native boolean nativeGetSensorsEnabled();

    private native boolean nativeGetSoundEnabled();

    private native String nativeGetSupervisedUserCustodianEmail();

    private native String nativeGetSupervisedUserCustodianName();

    private native String nativeGetSupervisedUserCustodianProfileImageURL();

    private native boolean nativeGetSupervisedUserSafeSitesEnabled();

    private native String nativeGetSupervisedUserSecondCustodianEmail();

    private native String nativeGetSupervisedUserSecondCustodianName();

    private native String nativeGetSupervisedUserSecondCustodianProfileImageURL();

    private native String nativeGetSyncLastAccountId();

    private native String nativeGetSyncLastAccountName();

    private native boolean nativeGetTranslateEnabled();

    private native boolean nativeGetTranslateManaged();

    private native void nativeGetUserAcceptLanguages(List<String> list);

    private native boolean nativeIsBlockedLanguage(String str);

    private native boolean nativeIsContentSettingEnabled(int i);

    private native boolean nativeIsContentSettingManaged(int i);

    private native boolean nativeIsMetricsReportingEnabled();

    private native boolean nativeIsMetricsReportingManaged();

    private native void nativeMigrateJavascriptPreference();

    private native void nativeMoveAcceptLanguage(String str, int i);

    private native boolean nativeObsoleteNetworkPredictionOptionsHasUserSetting();

    private native void nativeResetAcceptLanguages(String str);

    private native void nativeSetAllowCookiesEnabled(boolean z);

    private native void nativeSetAllowLocationEnabled(boolean z);

    private native void nativeSetAutoTranslateEnabled(boolean z);

    private native void nativeSetAutomaticDownloadsEnabled(boolean z);

    private native void nativeSetAutoplayEnabled(boolean z);

    private native void nativeSetBackgroundSyncEnabled(boolean z);

    private native void nativeSetBlockThirdPartyCookiesEnabled(boolean z);

    private native void nativeSetBoolean(int i, boolean z);

    private native void nativeSetBrowsingDataDeletionPreference(int i, int i2, boolean z);

    private native void nativeSetBrowsingDataDeletionTimePeriod(int i, int i2);

    private native void nativeSetCameraEnabled(boolean z);

    private native void nativeSetClickedUpdateMenuItem(boolean z);

    private native void nativeSetClipboardEnabled(boolean z);

    private native void nativeSetContentSettingEnabled(int i, boolean z);

    private native void nativeSetContextualSearchPreference(String str);

    private native void nativeSetDoNotTrackEnabled(boolean z);

    private native void nativeSetDownloadAndSaveFileDefaultDirectory(String str);

    private native void nativeSetEdgeBrowsingDataDeletionPreference(int i, int i2, boolean z);

    private native void nativeSetEnhancedTrackingPreventionUserPref(int i);

    private native void nativeSetEulaAccepted();

    private native void nativeSetExplicitLanguageAskPromptShown(boolean z);

    private native void nativeSetFamilySafetyBlockEmbedVideoEnabled(boolean z);

    private native void nativeSetIncognitoModeEnabled(boolean z);

    private native void nativeSetKeyBoardLanguage(String str);

    private native void nativeSetLanguageBlockedState(String str, boolean z);

    private native void nativeSetLastClearBrowsingDataTab(int i);

    private native void nativeSetLatestVersionWhenClickedUpdateMenuItem(String str);

    private native void nativeSetMetricsReportingEnabled(boolean z);

    private native void nativeSetMicEnabled(boolean z);

    private native void nativeSetNetworkPredictionEnabled(boolean z);

    private native void nativeSetNotificationsEnabled(boolean z);

    private native void nativeSetNotificationsVibrateEnabled(boolean z);

    private native void nativeSetPasswordEchoEnabled(boolean z);

    private native void nativeSetPasswordManagerAutoSigninEnabled(boolean z);

    private native void nativeSetPromptForDownloadAndroid(int i);

    private native void nativeSetRememberPasswordsEnabled(boolean z);

    private native void nativeSetResolveNavigationErrorEnabled(boolean z);

    private native void nativeSetSafeBrowsingEnabled(boolean z);

    private native void nativeSetSafeBrowsingExtendedReportingEnabled(boolean z);

    private native void nativeSetSearchSuggestEnabled(boolean z);

    private native void nativeSetSensorsEnabled(boolean z);

    private native void nativeSetSoundEnabled(boolean z);

    private native void nativeSetSupervisedUserId(String str);

    private native void nativeSetTranslateEnabled(boolean z);

    private native void nativeUpdateUserAcceptLanguages(String str, boolean z);

    /* renamed from: o0 */
    public static PrefServiceBridge m2758o0() {
        ThreadUtils.m1462c();
        if (f2182e == null) {
            f2182e = new PrefServiceBridge();
            f2182e.mo9131l0();
            TemplateUrlServiceFactory.m2927a().mo9632g();
            Sp2.b.add(f2182e);
        }
        return f2182e;
    }

    /* renamed from: A */
    public boolean mo9041A() {
        return mo9111g(22);
    }

    /* renamed from: B */
    public boolean mo9042B() {
        return nativeGetBlockThirdPartyCookiesEnabled();
    }

    /* renamed from: C */
    public boolean mo9043C() {
        return nativeGetBlockThirdPartyCookiesManaged();
    }

    /* renamed from: D */
    public boolean mo9044D() {
        return nativeGetCameraManagedByCustodian();
    }

    /* renamed from: E */
    public boolean mo9045E() {
        return nativeGetCameraUserModifiable();
    }

    /* renamed from: F */
    public boolean mo9046F() {
        return mo9099e().equals("false");
    }

    /* renamed from: G */
    public boolean mo9047G() {
        return nativeGetContextualSearchPreferenceIsManaged() && mo9046F();
    }

    /* renamed from: H */
    public boolean mo9048H() {
        return mo9099e().isEmpty();
    }

    /* renamed from: I */
    public boolean mo9049I() {
        return nativeGetDoNotTrackEnabled();
    }

    /* renamed from: J */
    public boolean mo9050J() {
        return nativeGetFirstRunEulaAccepted();
    }

    /* renamed from: K */
    public boolean mo9051K() {
        if (ve0.b()) {
            return false;
        }
        return nativeGetIncognitoModeEnabled();
    }

    /* renamed from: L */
    public boolean mo9052L() {
        return nativeGetIncognitoModeManaged();
    }

    /* renamed from: M */
    public boolean mo9053M() {
        return nativeGetLocationAllowedByPolicy();
    }

    /* renamed from: N */
    public boolean mo9054N() {
        return nativeIsMetricsReportingManaged();
    }

    /* renamed from: O */
    public boolean mo9055O() {
        return nativeGetMicManagedByCustodian();
    }

    /* renamed from: P */
    public boolean mo9056P() {
        return nativeGetMicUserModifiable();
    }

    /* renamed from: Q */
    public boolean mo9057Q() {
        return nativeGetNotificationsVibrateEnabled();
    }

    /* renamed from: R */
    public boolean mo9058R() {
        return nativeGetPasswordManagerAutoSigninEnabled();
    }

    /* renamed from: S */
    public boolean mo9059S() {
        return nativeGetPasswordManagerAutoSigninManaged();
    }

    /* renamed from: T */
    public boolean mo9060T() {
        return mo9111g(4);
    }

    /* renamed from: U */
    public boolean mo9061U() {
        return nativeGetPrintingEnabled();
    }

    /* renamed from: V */
    public boolean mo9062V() {
        return nativeGetRememberPasswordsEnabled();
    }

    /* renamed from: W */
    public boolean mo9063W() {
        return nativeGetRememberPasswordsManaged();
    }

    /* renamed from: X */
    public boolean mo9064X() {
        return nativeGetResolveNavigationErrorEnabled();
    }

    /* renamed from: Y */
    public boolean mo9065Y() {
        return nativeGetResolveNavigationErrorManaged();
    }

    /* renamed from: Z */
    public boolean mo9066Z() {
        return nativeGetSafeBrowsingEnabled();
    }

    /* renamed from: a */
    public void mo9071a(boolean z) {
        mo9131l0();
    }

    /* renamed from: a0 */
    public boolean mo9076a0() {
        return nativeGetSafeBrowsingExtendedReportingEnabled();
    }

    /* renamed from: b */
    public boolean mo9084b(int i, int i2) {
        return nativeGetEdgeBrowsingDataDeletionPreference(i, i2);
    }

    /* renamed from: b0 */
    public boolean mo9085b0() {
        return nativeGetSafeBrowsingExtendedReportingManaged();
    }

    /* renamed from: c */
    public void mo9089c(int i, boolean z) {
        nativeSetContentSettingEnabled(i, z);
    }

    /* renamed from: c0 */
    public boolean mo9092c0() {
        return nativeGetSafeBrowsingManaged();
    }

    /* renamed from: d */
    public List<ContentSettingException> mo9093d(int i) {
        ArrayList arrayList = new ArrayList();
        nativeGetContentSettingsExceptions(i, arrayList);
        return arrayList;
    }

    /* renamed from: d0 */
    public boolean mo9098d0() {
        return nativeGetSearchSuggestEnabled();
    }

    /* renamed from: e */
    public String mo9099e() {
        return nativeGetContextualSearchPreference();
    }

    /* renamed from: e0 */
    public boolean mo9103e0() {
        return nativeGetSearchSuggestManaged();
    }

    /* renamed from: f */
    public boolean mo9107f(int i) {
        return nativeIsContentSettingEnabled(i);
    }

    /* renamed from: f0 */
    public boolean mo9108f0() {
        return nativeGetSupervisedUserSafeSitesEnabled();
    }

    /* renamed from: g */
    public boolean mo9111g(int i) {
        return nativeIsContentSettingManaged(i);
    }

    /* renamed from: g0 */
    public boolean mo9112g0() {
        return nativeGetTranslateEnabled();
    }

    /* renamed from: h */
    public void mo9114h(boolean z) {
        nativeSetFamilySafetyBlockEmbedVideoEnabled(z);
    }

    /* renamed from: h */
    public boolean mo9115h(int i) {
        return i == 16;
    }

    /* renamed from: h0 */
    public boolean mo9116h0() {
        return nativeGetTranslateManaged();
    }

    /* renamed from: i */
    public void mo9118i(boolean z) {
        nativeSetIncognitoModeEnabled(z);
    }

    /* renamed from: i0 */
    public boolean mo9120i0() {
        return mo9111g(2);
    }

    /* renamed from: j */
    public int mo9121j() {
        return nativeGetLastClearBrowsingDataTab();
    }

    /* renamed from: j0 */
    public void mo9124j0() {
        SharedPreferences sharedPreferences = QN0.a;
        int i = sharedPreferences.getInt("PrefMigrationVersion", 0);
        if (i != 4) {
            if (i > 4) {
                Log.e("PrefServiceBridge", "Saved preferences version is newer than supported.  Attempting to run an older version of Chrome without clearing data is unsupported and the results may be unpredictable.");
            }
            if (i < 1) {
                nativeMigrateJavascriptPreference();
            }
            Eo.b(sharedPreferences, "PrefMigrationVersion", 4);
        }
    }

    /* renamed from: k */
    public void mo9127k(boolean z) {
        nativeSetNetworkPredictionEnabled(z);
    }

    /* renamed from: k0 */
    public boolean mo9128k0() {
        return nativeObsoleteNetworkPredictionOptionsHasUserSetting();
    }

    /* renamed from: l */
    public boolean mo9130l() {
        return nativeGetNetworkPredictionEnabled();
    }

    /* renamed from: l0 */
    public void mo9131l0() {
        boolean a;
        Sp2 e = Profile.m2911j().mo9205e();
        if (e != null && (a = e.a()) != mo9051K()) {
            mo9118i(a);
        }
    }

    /* renamed from: m */
    public boolean mo9133m() {
        return nativeGetPasswordEchoEnabled();
    }

    /* renamed from: m0 */
    public void mo9134m0() {
    }

    /* renamed from: n */
    public void mo9136n(boolean z) {
        nativeSetPasswordManagerAutoSigninEnabled(z);
    }

    /* renamed from: n0 */
    public void mo9137n0() {
        nativeSetEulaAccepted();
    }

    public native int nativeGetContentSetting(int i);

    public native void nativeSetContentSetting(int i, int i2);

    public native void nativeSetContentSettingForPattern(int i, String str, int i2);

    /* renamed from: o */
    public void mo9142o(boolean z) {
        nativeSetRememberPasswordsEnabled(z);
    }

    /* renamed from: p */
    public void mo9144p(boolean z) {
        nativeSetResolveNavigationErrorEnabled(z);
    }

    /* renamed from: q */
    public void mo9146q(boolean z) {
        nativeSetSafeBrowsingEnabled(z);
    }

    /* renamed from: r */
    public String mo9147r() {
        return nativeGetSyncLastAccountName();
    }

    /* renamed from: s */
    public void mo9150s(boolean z) {
        nativeSetSearchSuggestEnabled(z);
    }

    /* renamed from: t */
    public boolean mo9152t() {
        return nativeGetAcceptCookiesManagedByCustodian();
    }

    /* renamed from: u */
    public boolean mo9153u() {
        return nativeGetAcceptCookiesUserModifiable();
    }

    /* renamed from: v */
    public boolean mo9154v() {
        return nativeGetAllowLocationEnabled();
    }

    /* renamed from: w */
    public boolean mo9155w() {
        return nativeGetAllowLocationManagedByCustodian();
    }

    /* renamed from: x */
    public boolean mo9156x() {
        return nativeGetAllowLocationUserModifiable();
    }

    /* renamed from: y */
    public boolean mo9157y() {
        return nativeGetAutoTranslateEnabled();
    }

    /* renamed from: z */
    public boolean mo9158z() {
        return mo9111g(13);
    }

    /* renamed from: a */
    public boolean mo9073a(int i) {
        return nativeGetBoolean(i);
    }

    /* renamed from: b */
    public void mo9079b(int i, int i2, boolean z) {
        nativeSetEdgeBrowsingDataDeletionPreference(i, i2, z);
    }

    /* renamed from: c */
    public void mo9090c(String str) {
        nativeSetContextualSearchPreference(str);
    }

    /* renamed from: e */
    public void mo9101e(boolean z) {
        mo9090c(z ? "true" : "false");
    }

    /* renamed from: f */
    public void mo9106f(boolean z) {
        nativeSetDoNotTrackEnabled(z);
    }

    /* renamed from: g */
    public String mo9109g() {
        return nativeGetDownloadDefaultDirectory();
    }

    /* renamed from: h */
    public int mo9113h() {
        return nativeGetEnhancedTrackingPreventionUserPref();
    }

    /* renamed from: i */
    public boolean mo9119i() {
        return nativeGetExplicitLanguageAskPromptShown();
    }

    /* renamed from: j */
    public void mo9122j(int i) {
        nativeSetLastClearBrowsingDataTab(i);
    }

    /* renamed from: k */
    public String mo9125k() {
        return nativeGetLatestVersionWhenClickedUpdateMenuItem();
    }

    /* renamed from: l */
    public void mo9129l(boolean z) {
        nativeSetNotificationsVibrateEnabled(z);
    }

    /* renamed from: m */
    public void mo9132m(boolean z) {
        nativeSetPasswordEchoEnabled(z);
    }

    /* renamed from: n */
    public int mo9135n() {
        return nativeGetPromptForDownloadAndroid();
    }

    /* renamed from: o */
    public String mo9141o() {
        return nativeGetSupervisedUserCustodianEmail();
    }

    /* renamed from: p */
    public String mo9143p() {
        return nativeGetSupervisedUserSecondCustodianEmail();
    }

    /* renamed from: q */
    public String mo9145q() {
        return nativeGetSupervisedUserSecondCustodianName();
    }

    /* renamed from: r */
    public void mo9148r(boolean z) {
        nativeSetSafeBrowsingExtendedReportingEnabled(z);
    }

    /* renamed from: s */
    public List<String> mo9149s() {
        ArrayList arrayList = new ArrayList();
        nativeGetUserAcceptLanguages(arrayList);
        return arrayList;
    }

    /* renamed from: t */
    public void mo9151t(boolean z) {
        nativeSetTranslateEnabled(z);
    }

    /* renamed from: a */
    public void mo9068a(int i, boolean z) {
        nativeSetBoolean(i, z);
    }

    /* renamed from: b */
    public int mo9077b(int i) {
        return nativeGetBrowsingDataDeletionTimePeriod(i);
    }

    /* renamed from: c */
    public void mo9088c(int i, int i2) {
        nativeSetBrowsingDataDeletionTimePeriod(i, i2);
    }

    /* renamed from: d */
    public void mo9094d(int i, int i2) {
        nativeSetContentSetting(i, i2);
    }

    /* renamed from: e */
    public boolean mo9102e(int i) {
        switch (i) {
            case 0:
                return nativeGetAcceptCookiesEnabled();
            case 2:
            case 4:
            case 26:
            case 35:
            case 39:
            case 46:
                return mo9107f(i);
            case 6:
                return nativeGetNotificationsEnabled();
            case 9:
                return nativeGetMicEnabled();
            case 10:
                return nativeGetCameraEnabled();
            case 13:
                return nativeGetAutomaticDownloadsEnabled();
            case 22:
                return nativeGetBackgroundSyncEnabled();
            case 23:
                return nativeGetAutoplayEnabled();
            case 31:
                return nativeGetSoundEnabled();
            case 33:
                return nativeGetSensorsEnabled();
            default:
                return false;
        }
    }

    /* renamed from: f */
    public int mo9104f() {
        return nativeGetDefaultSupervisedUserFilteringBehavior();
    }

    /* renamed from: g */
    public void mo9110g(boolean z) {
        nativeSetExplicitLanguageAskPromptShown(z);
    }

    /* renamed from: i */
    public void mo9117i(int i) {
        nativeSetEnhancedTrackingPreventionUserPref(i);
    }

    /* renamed from: j */
    public void mo9123j(boolean z) {
        nativeSetMetricsReportingEnabled(z);
    }

    /* renamed from: k */
    public void mo9126k(int i) {
        nativeSetPromptForDownloadAndroid(i);
    }

    /* renamed from: a */
    public boolean mo9072a() {
        return nativeCanPrefetchAndPrerender();
    }

    /* renamed from: b */
    public void mo9080b(int i, boolean z) {
        if (i != 0) {
            if (i != 2) {
                if (i == 13) {
                    nativeSetAutomaticDownloadsEnabled(z);
                    return;
                } else if (i != 26) {
                    if (i == 31) {
                        nativeSetSoundEnabled(z);
                        return;
                    } else if (i == 33) {
                        nativeSetSensorsEnabled(z);
                        return;
                    } else if (i == 35) {
                        nativeSetClipboardEnabled(z);
                        return;
                    } else if (!(i == 39 || i == 46 || i == 4)) {
                        if (i == 5) {
                            nativeSetAllowLocationEnabled(z);
                            return;
                        } else if (i == 6) {
                            nativeSetNotificationsEnabled(z);
                            return;
                        } else if (i == 9) {
                            nativeSetMicEnabled(z);
                            return;
                        } else if (i == 10) {
                            nativeSetCameraEnabled(z);
                            return;
                        } else if (i == 22) {
                            nativeSetBackgroundSyncEnabled(z);
                            return;
                        } else if (i == 23) {
                            nativeSetAutoplayEnabled(z);
                            return;
                        } else {
                            return;
                        }
                    }
                }
            }
            mo9089c(i, z);
            return;
        }
        nativeSetAllowCookiesEnabled(z);
    }

    /* renamed from: c */
    public void mo9091c(boolean z) {
        nativeSetBlockThirdPartyCookiesEnabled(z);
    }

    /* renamed from: d */
    public void mo9096d(boolean z) {
        nativeSetClickedUpdateMenuItem(z);
    }

    /* renamed from: f */
    public void mo9105f(String str) {
        nativeSetLatestVersionWhenClickedUpdateMenuItem(str);
    }

    /* renamed from: a */
    public boolean mo9074a(int i, int i2) {
        return nativeGetBrowsingDataDeletionPreference(i, i2);
    }

    /* renamed from: c */
    public int mo9086c(int i) {
        return nativeGetContentSetting(i);
    }

    /* renamed from: d */
    public boolean mo9097d() {
        return nativeGetClickedUpdateMenuItem();
    }

    /* renamed from: a */
    public void mo9067a(int i, int i2, boolean z) {
        nativeSetBrowsingDataDeletionPreference(i, i2, z);
    }

    /* renamed from: c */
    public List<rn2> mo9087c() {
        ArrayList arrayList = new ArrayList();
        nativeGetChromeAcceptLanguages(arrayList);
        return arrayList;
    }

    /* renamed from: d */
    public void mo9095d(String str) {
        nativeSetDownloadAndSaveFileDefaultDirectory(str);
    }

    /* renamed from: a */
    public void mo9069a(String str, int i) {
        nativeMoveAcceptLanguage(str, i);
    }

    /* renamed from: a */
    public boolean mo9075a(String str) {
        return nativeIsBlockedLanguage(str);
    }

    /* renamed from: a */
    public void mo9070a(String str, boolean z) {
        nativeSetLanguageBlockedState(str, z);
    }

    /* renamed from: e */
    public void mo9100e(String str) {
        nativeSetKeyBoardLanguage(str);
    }

    /* renamed from: b */
    public AboutVersionStrings mo9078b() {
        return nativeGetAboutVersionStrings();
    }

    /* renamed from: b */
    public void mo9081b(String str) {
        nativeResetAcceptLanguages(str);
    }

    /* renamed from: b */
    public void mo9082b(String str, boolean z) {
        nativeUpdateUserAcceptLanguages(str, z);
    }

    /* renamed from: b */
    public void mo9083b(boolean z) {
        nativeSetAutoTranslateEnabled(z);
    }
}
