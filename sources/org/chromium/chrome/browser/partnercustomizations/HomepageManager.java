package org.chromium.chrome.browser.partnercustomizations;

import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.mmx.experiment.FeatureManager$Feature;
import com.microsoft.ruby.serverconfig.ServerConfigManager;
import com.microsoft.ruby.util.RubyBuild;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ObserverList;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.suggestions.MostVisitedSitesManager;
import org.chromium.components.url_formatter.UrlFormatter;

/* compiled from: PG */
public class HomepageManager implements MicrosoftSigninManager.SignInStateObserver {

    /* renamed from: e */
    public static final LinkedHashMap<String, String> f2126e = new LinkedHashMap<>();

    /* renamed from: k */
    public static String f2127k = "https://www.msn.com/spartan/mmx?locale=%l&rt=%r";

    /* renamed from: n */
    public static String f2128n = "https://www.msn.com/spartan/mmx?locale=%l&rt=%r&pcsonly=true&item=flights:prg-mmxep-t";

    /* renamed from: p */
    public static String f2129p = "default";

    /* renamed from: q */
    public static String f2130q = "EN-US";

    /* renamed from: x */
    public static HomepageManager f2131x;

    /* renamed from: a */
    public final SharedPreferences f2132a = QN0.a;

    /* renamed from: b */
    public final ObserverList<HomepageStateListener> f2133b = new ObserverList<>();

    /* renamed from: c */
    public final ObserverList<HomepageUiChangeListener> f2134c = new ObserverList<>();

    /* renamed from: d */
    public String f2135d;

    /* compiled from: PG */
    public interface HomepageStateListener {
        void onHomepageStateUpdated();
    }

    /* compiled from: PG */
    public interface HomepageUiChangeListener {
        void onHomepageCustomizeLocaleUpdated(String str, String str2);

        void onHomepageSwitchChanged();
    }

    static {
        LinkedHashMap<String, String> linkedHashMap = f2126e;
        linkedHashMap.put("AR-AE", "الإمارات العربية المتحدة (العربية)");
        linkedHashMap.put("AR-EG", "مصر (العربية)");
        linkedHashMap.put("AR-SA", "المملكة العربية السعودية (العربية)");
        linkedHashMap.put("CS-CZ", "Česká republika (čeština)");
        linkedHashMap.put("DA-DK", "Danmark (dansk)");
        linkedHashMap.put("DE-AT", "Österreich (Deutsch)");
        linkedHashMap.put("DE-CH", "Schweiz (Deutsch)");
        linkedHashMap.put("DE-DE", "Deutschland (Deutsch)");
        linkedHashMap.put("EL-GR", "Ελλάδα (Ελληνικά)");
        linkedHashMap.put("EN-AE", "United Arab Emirates (English)");
        linkedHashMap.put("EN-AU", "Australia (English)");
        linkedHashMap.put("EN-CA", "Canada (English)");
        linkedHashMap.put("EN-GB", "United Kingdom (English)");
        linkedHashMap.put("EN-IE", "Ireland (English)");
        linkedHashMap.put("EN-IN", "India (English)");
        linkedHashMap.put("EN-MY", "Malaysia (English)");
        linkedHashMap.put("EN-NZ", "New Zealand (English)");
        linkedHashMap.put("EN-PH", "Philippines (English)");
        linkedHashMap.put("EN-SG", "Singapore (English)");
        linkedHashMap.put("EN-US", "United States (English)");
        linkedHashMap.put("EN-XL", "International Edition (English)");
        linkedHashMap.put("EN-ZA", "South Africa (English)");
        linkedHashMap.put("ES-AR", "Argentina (Español)");
        linkedHashMap.put("ES-CL", "Chile (Español)");
        linkedHashMap.put("ES-CO", "Colombia (Español)");
        linkedHashMap.put("ES-ES", "España (Español)");
        linkedHashMap.put("ES-MX", "México (Español)");
        linkedHashMap.put("ES-CR", "Costa Rica (Español)");
        linkedHashMap.put("ES-DO", "República Dominicana (Español)");
        linkedHashMap.put("ES-NI", "Nicaragua (Español)");
        linkedHashMap.put("ES-PA", "Panamá (Español)");
        linkedHashMap.put("ES-PE", "Perú (Español)");
        linkedHashMap.put("ES-VE", "Venezuela (Español)");
        linkedHashMap.put("ES-US", "Estados Unidos (Español)");
        linkedHashMap.put("ES-XL", "Latinoamérica (Español)");
        linkedHashMap.put("FI-FI", "Suomi (suomi)");
        linkedHashMap.put("FR-BE", "Belgique (Français)");
        linkedHashMap.put("FR-CA", "Canada (Français)");
        linkedHashMap.put("FR-CH", "Suisse (Français)");
        linkedHashMap.put("FR-FR", "France (Français)");
        linkedHashMap.put("HE-IL", "ישראל (עברית)");
        linkedHashMap.put("HI-IN", "भारत (हिंदी)");
        linkedHashMap.put("HU-HU", "Magyarország (magyar)");
        linkedHashMap.put("ID-ID", "Indonesia (Indonesia)");
        linkedHashMap.put("IT-IT", "Italia (Italiano)");
        linkedHashMap.put("JA-JP", "日本 (日本語)");
        linkedHashMap.put("KO-KR", "대한민국 (한국어)");
        linkedHashMap.put("NB-NO", "Norge (Norwegian Bokmål)");
        linkedHashMap.put("NL-BE", "België (Nederlands)");
        linkedHashMap.put("NL-NL", "Nederland (Nederlands)");
        linkedHashMap.put("PL-PL", "Poland (Polski)");
        linkedHashMap.put("PT-BR", "Brasil (Português)");
        linkedHashMap.put("PT-PT", "Portugal (Português)");
        linkedHashMap.put("RU-RU", "Россия  (русский)");
        linkedHashMap.put("SV-SE", "Sverige (Svenska)");
        linkedHashMap.put("TH-TH", "ไทย (ไทย)");
        linkedHashMap.put("TR-TR", "Türkiye (Türkçe)");
        linkedHashMap.put("VI-VN", "Vietnam (Việt)");
        linkedHashMap.put("ZH-CN", "中国 (简体中文)");
        linkedHashMap.put("ZH-HK", "香港 (繁體中文)");
        linkedHashMap.put("ZH-TW", "臺灣 (繁體中文)");
    }

    public HomepageManager() {
        String str;
        if (this.f2132a.getBoolean("is_locale_first_time_set", true)) {
            TelephonyManager telephonyManager = (TelephonyManager) RN0.a.getSystemService("phone");
            if (telephonyManager == null) {
                str = BuildConfig.FLAVOR;
            } else {
                str = telephonyManager.getNetworkCountryIso();
            }
            String str2 = null;
            char c = 65535;
            if (str.hashCode() == 3365 && str.equals("in")) {
                c = 0;
            }
            str2 = c == 0 ? "en-IN" : str2;
            if (str2 != null) {
                mo8965a(str2, true);
            }
            Eo.a(this.f2132a, "is_locale_first_time_set", false);
        }
        MicrosoftSigninManager.C0424c.f2104a.mo8888a((MicrosoftSigninManager.SignInStateObserver) this);
    }

    /* renamed from: m */
    public static String m2676m() {
        PartnerBrowserCustomizations.m2706c();
        PartnerBrowserCustomizations.m2704b();
        return null;
    }

    /* renamed from: n */
    public static String m2677n() {
        if (m2679p().mo8973f() == 1 && m2679p().mo8975h().equals(f2130q)) {
            return RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.DEVELOPMENT, RubyBuild.DAILY)) ? "https://www.microsoftnewskids.com/spartan/mmx?locale=%l&rt=%r&query=kids" : "https://www.msn.com/spartan/mmx?locale=%l&rt=%r&query=kids";
        }
        if (gh0.b(FeatureManager$Feature.NEWSFEEDURL_ROLLOUT)) {
            f2127k = "https://int.msn.com/spartan/mmx?locale=%l&rt=%r&pcsonly=true&item=flights:mmxpg1-t&enableConfigService=false";
        }
        if (Dn2.d()) {
            return "https://www.msn.cn/spartan/mmx?locale=%l&rt=%r";
        }
        if (RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.DAILY, RubyBuild.DEVELOPMENT))) {
            return f2128n;
        }
        return f2127k;
    }

    /* renamed from: o */
    public static String m2678o() {
        String str;
        HomepageManager p = m2679p();
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A() && !TextUtils.isEmpty(p.f2135d)) {
            return p.f2135d;
        }
        if (!m2681r()) {
            return null;
        }
        if (p.mo8974g()) {
            m2676m();
            str = null;
        } else {
            str = p.mo8971d();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    /* renamed from: p */
    public static HomepageManager m2679p() {
        if (f2131x == null) {
            f2131x = new HomepageManager();
        }
        return f2131x;
    }

    /* renamed from: q */
    public static boolean m2680q() {
        return !Dn2.d();
    }

    /* renamed from: r */
    public static boolean m2681r() {
        if (gh0.b(FeatureManager$Feature.SET_HOMEPAGE_ROLLOUT) || m2679p().mo8976i()) {
            return m2679p().f2132a.getBoolean("homepage", false);
        }
        return false;
    }

    /* renamed from: s */
    public static boolean m2682s() {
        PartnerBrowserCustomizations.m2706c();
        if (fv1.d()) {
            return m2680q();
        }
        return true;
    }

    /* renamed from: a */
    public void mo8964a(String str) {
        this.f2135d = UrlFormatter.m3350a(ft0.e(str));
    }

    /* renamed from: b */
    public void mo8968b(String str) {
        SharedPreferences.Editor edit = this.f2132a.edit();
        edit.putString("homepage_custom_uri", str).apply();
        edit.putBoolean("homepage_custom_uri_set_once", true).apply();
    }

    /* renamed from: c */
    public String mo8970c() {
        String str;
        String b = mo8967b();
        if (b.equalsIgnoreCase(f2129p)) {
            b = Dn2.b();
        }
        if (this.f2132a.getBoolean("news_feed_url_is_overridden", false)) {
            str = this.f2132a.getString("news_feed_url_overriding_value", BuildConfig.FLAVOR);
        } else {
            str = m2677n();
        }
        return str.replace("%l", b).replace("%r", FE2.a() ? "1" : "0");
    }

    /* renamed from: d */
    public String mo8971d() {
        return this.f2132a.getString("homepage_custom_uri", BuildConfig.FLAVOR);
    }

    /* renamed from: e */
    public boolean mo8972e() {
        if (!kg0.e().b() || !MAMEdgeManager.m1307e()) {
            return this.f2132a.getBoolean("homepage_news", true);
        }
        return false;
    }

    /* renamed from: f */
    public int mo8973f() {
        return this.f2132a.getInt("homepage_news_source", 0);
    }

    /* renamed from: g */
    public boolean mo8974g() {
        return this.f2132a.getBoolean("homepage_partner_enabled", true);
    }

    /* renamed from: h */
    public String mo8975h() {
        String b = mo8967b();
        if (b == null || b.equals(f2129p)) {
            return Dn2.b().toUpperCase(Locale.getDefault());
        }
        return b.toUpperCase(Locale.getDefault());
    }

    /* renamed from: i */
    public boolean mo8976i() {
        return QN0.a.getBoolean("homepage_custom_uri_set_once", false);
    }

    /* renamed from: j */
    public boolean mo8977j() {
        String b = m2679p().mo8967b();
        if (f2129p.equals(b)) {
            b = Dn2.b().toUpperCase(Locale.getDefault());
        }
        return "NL-NL".equals(b);
    }

    /* renamed from: k */
    public void mo8978k() {
        Iterator<HomepageStateListener> it = this.f2133b.iterator();
        while (it.hasNext()) {
            it.next().onHomepageStateUpdated();
        }
    }

    /* renamed from: l */
    public boolean mo8979l() {
        return m2675c(mo8967b());
    }

    public void onSignedIn(AuthenticationMode authenticationMode, String str) {
    }

    public void onSignedOut(AuthenticationMode authenticationMode) {
        if (authenticationMode == AuthenticationMode.AAD) {
            this.f2135d = null;
        }
    }

    /* renamed from: a */
    public void mo8966a(boolean z) {
        SharedPreferences.Editor edit = this.f2132a.edit();
        edit.putBoolean("homepage", z);
        edit.apply();
        RecordHistogram.m1543a("Settings.ShowHomeButtonPreferenceStateChanged", z);
        RecordHistogram.m1543a("Settings.ShowHomeButtonPreferenceState", z);
        mo8978k();
    }

    /* renamed from: b */
    public void mo8969b(boolean z) {
        RecordHistogram.m1543a("Settings.HomePageIsCustomized", !z);
        Eo.a(this.f2132a, "homepage_partner_enabled", z);
    }

    /* renamed from: b */
    public String mo8967b() {
        if (m2680q()) {
            return mo8963a();
        }
        return Dn2.b();
    }

    /* renamed from: c */
    public static boolean m2675c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List list = null;
        if (RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.PRODUCT, RubyBuild.ALPHA, RubyBuild.BETA, RubyBuild.UNKNOWN))) {
            list = ServerConfigManager.h().f();
        }
        if (str.equalsIgnoreCase(f2129p)) {
            str = Dn2.b();
        }
        if (list == null) {
            return f2126e.containsKey(str.toUpperCase(Locale.US));
        }
        if (list.indexOf(str.toUpperCase(Locale.US)) >= 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo8965a(String str, boolean z) {
        String a = mo8963a();
        if (!TextUtils.equals(a, str) && !TextUtils.isEmpty(a) && !TextUtils.isEmpty(str)) {
            SharedPreferences.Editor edit = this.f2132a.edit();
            edit.putString("homepage_contents_locale", str);
            edit.apply();
            String upperCase = Dn2.b().toUpperCase(Locale.getDefault());
            if (a.equals(f2129p)) {
                a = upperCase;
            }
            if (str.equals(f2129p)) {
                str = upperCase;
            }
            if (!str.equalsIgnoreCase(a)) {
                if (!z) {
                    MostVisitedSitesManager.m2930c().mo9217b();
                }
                Iterator<HomepageUiChangeListener> it = this.f2134c.iterator();
                while (it.hasNext()) {
                    it.next().onHomepageCustomizeLocaleUpdated(a, str);
                }
            }
        }
    }

    /* renamed from: a */
    public String mo8963a() {
        return this.f2132a.getString("homepage_contents_locale", f2129p);
    }
}
