package org.chromium.chrome.browser.preferences.privacy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.text.SpannableString;
import com.microsoft.edge_trust.tracking_prevention.TrackingPreventionBridge;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.preferences.ChromeBaseCheckBoxPreferenceCompat;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.preferences.TextMessageWithLinkPreference;
import org.chromium.p012ui.base.LocalizationUtils;
import uK3;

/* compiled from: PG */
public class PrivacyPreferences extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    /* renamed from: E */
    public static boolean m2899E() {
        return QN0.a.getBoolean("privacyDisableShareHistory", false);
    }

    /* renamed from: F */
    public static boolean m2900F() {
        return QN0.a.getBoolean("privacyDisableShareUsageData", false);
    }

    /* renamed from: a */
    public static void m2901a(boolean z) {
        Eo.b(QN0.a, "privacyDisableShareHistory", z);
    }

    /* renamed from: A */
    public final /* synthetic */ void mo9183A() {
        CustomTabActivity.m2042a((Context) getActivity(), LocalizationUtils.m3658a(getString(wx0.chrome_privacy_notice_url)));
    }

    /* renamed from: B */
    public final /* synthetic */ boolean mo9184B() {
        iE2.a(getActivity(), true, new ro2(this)).a();
        return true;
    }

    /* renamed from: C */
    public final SpannableString mo9185C() {
        tK3 tk3 = new tK3(getActivity().getResources(), new po2(this));
        uK3.a aVar = new uK3.a("<link1>", "</link1>", new tK3(getActivity().getResources(), new qo2(this)));
        uK3.a aVar2 = new uK3.a("<link2>", "</link2>", tk3);
        return uK3.a(getString(wx0.privacy_share_history_link), new uK3.a[]{aVar, aVar2});
    }

    /* renamed from: D */
    public void mo9186D() {
        PrefServiceBridge o0 = PrefServiceBridge.m2758o0();
        CharSequence text = getActivity().getResources().getText(wx0.text_on);
        CharSequence text2 = getActivity().getResources().getText(wx0.text_off);
        CheckBoxPreference findPreference = findPreference("search_suggestions");
        if (findPreference != null) {
            findPreference.l(o0.mo9098d0());
        }
        CheckBoxPreference findPreference2 = findPreference("can_make_payment");
        if (findPreference2 != null) {
            findPreference2.l(o0.mo9073a(7));
        }
        Preference findPreference3 = findPreference("do_not_track");
        if (findPreference3 != null) {
            findPreference3.a(o0.mo9049I() ? text : text2);
        }
        Preference findPreference4 = findPreference("usage_stats_reporting");
        if (findPreference4 != null) {
            if (!BuildInfo.m1372a() || !o0.mo9073a(12)) {
                s().e(findPreference4);
            } else {
                findPreference4.a(new oo2(this));
            }
        }
        so2 l = so2.l();
        CheckBoxPreference findPreference5 = findPreference("full_browsing_track");
        if (findPreference5 != null) {
            findPreference5.l(l.e());
        }
        CheckBoxPreference findPreference6 = findPreference("share_usage_data");
        if (findPreference6 != null) {
            findPreference6.l(l.f());
        }
        Preference findPreference7 = findPreference("edge_tracking_prevention");
        if (findPreference7 != null) {
            if (!TrackingPreventionBridge.m1268a()) {
                text = text2;
            }
            findPreference7.a(text);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String j = preference.j();
        if ("can_make_payment".equals(j)) {
            PrefServiceBridge.m2758o0().mo9068a(7, ((Boolean) obj).booleanValue());
            return true;
        } else if ("search_suggestions".equals(j)) {
            PrefServiceBridge.m2758o0().mo9150s(((Boolean) obj).booleanValue());
            return true;
        } else if ("share_usage_data".equals(j)) {
            so2.l().b(((Boolean) obj).booleanValue());
            return true;
        } else if (!"full_browsing_track".equals(j)) {
            return true;
        } else {
            so2.l().a(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public void onResume() {
        PrivacyPreferences.super.onResume();
        mo9186D();
        a((Drawable) null);
    }

    /* renamed from: y */
    public final /* synthetic */ void mo9191y() {
        CustomTabActivity.m2042a((Context) getActivity(), LocalizationUtils.m3658a(getString(wx0.chrome_privacy_notice_url)));
    }

    /* renamed from: z */
    public final /* synthetic */ void mo9192z() {
        CustomTabActivity.m2042a((Context) getActivity(), LocalizationUtils.m3658a(getString(wx0.chrome_privacy_learn_more)));
    }

    /* renamed from: a */
    public void mo9187a(Bundle bundle, String str) {
        so2 l = so2.l();
        l.h();
        qm2.a(this, Ax0.privacy_preferences);
        getActivity().setTitle(wx0.prefs_privacy);
        setHasOptionsMenu(true);
        PreferenceScreen s = s();
        Fu0.a("privacySectionVisited", true);
        Fu0.b(getActivity(), "show_privacy_callout");
        findPreference("can_make_payment").a(this);
        findPreference("search_suggestions").a(this);
        mo9186D();
        ChromeBaseCheckBoxPreferenceCompat findPreference = findPreference("full_browsing_track");
        TextMessageWithLinkPreference findPreference2 = findPreference("share_history_link");
        if (!MicrosoftSigninManager.C0424c.f2104a.mo8866A() || !m2899E()) {
            findPreference.a(this);
            findPreference.l(l.e());
            findPreference.f(true);
            findPreference.mo9009n(false);
            findPreference2.mo9175a((CharSequence) mo9185C());
        } else {
            findPreference.l(false);
            findPreference.f(false);
            findPreference.mo9009n(true);
            findPreference2.mo9175a((CharSequence) getResources().getString(wx0.privacy_setting_managed_by_organization));
        }
        ChromeBaseCheckBoxPreferenceCompat findPreference3 = findPreference("share_usage_data");
        TextMessageWithLinkPreference findPreference4 = findPreference("share_usage_data_link");
        if (!MicrosoftSigninManager.C0424c.f2104a.mo8866A() || !m2900F()) {
            findPreference3.a(this);
            findPreference3.l(l.f());
            findPreference3.f(true);
            findPreference3.mo9009n(false);
            findPreference4.mo9176a((Runnable) new no2(this));
        } else {
            findPreference3.l(false);
            findPreference3.f(false);
            findPreference3.mo9009n(true);
            findPreference4.mo9175a((CharSequence) getResources().getString(wx0.privacy_setting_managed_by_organization));
        }
        Preference findPreference5 = findPreference("edge_tracking_prevention");
        if (!TrackingPreventionBridge.m1269b()) {
            s.e(findPreference5);
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9188a(Boolean bool) {
        if (bool.booleanValue()) {
            mo9186D();
        }
    }
}
