package org.chromium.chrome.browser.edge_ntp;

import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.ruby.branding.BrandInfoManager;
import com.microsoft.ruby.serverconfig.ServerConfigManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.anaheim_ntp_mode.view.NewTabPageLayout;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.ntp.snippets.SuggestionsSource;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.chrome.browser.suggestions.MostVisitedSitesManager;
import org.chromium.chrome.browser.suggestions.SuggestionsEventReporterBridge;
import org.chromium.chrome.browser.suggestions.tile.TileGroup;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.util.UrlUtilities;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.p012ui.base.DeviceFormFactor;
import org.chromium.ui.UiUtils;

/* compiled from: PG */
public class NewTabPage implements lb2, tF1, TemplateUrlService.TemplateUrlServiceObserver, MostVisitedSitesManager.MostVisitedSitesStringChangedObserver, BrandInfoManager.BrandInfoChangedObserver, MicrosoftSigninManager.SignInStateObserver {

    /* renamed from: a */
    public final hv1 f1784a;

    /* renamed from: b */
    public final Tab f1785b;

    /* renamed from: c */
    public final String f1786c;

    /* renamed from: d */
    public final NewTabPageView f1787d;

    /* renamed from: e */
    public final SU1 f1788e;

    /* renamed from: k */
    public final TileGroup.Delegate f1789k;

    /* renamed from: n */
    public Yv2 f1790n = new RU1(this);

    /* renamed from: p */
    public boolean f1791p;

    /* renamed from: q */
    public FakeboxDelegate f1792q;

    /* renamed from: q3 */
    public fv1 f1793q3;

    /* renamed from: x */
    public boolean f1794x;

    /* renamed from: y */
    public boolean f1795y;

    /* JADX WARNING: type inference failed for: r11v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public NewTabPage(ChromeActivity chromeActivity, ob2 ob2, TabModelSelector tabModelSelector) {
        TileGroup.Delegate delegate;
        ? r11 = chromeActivity;
        TraceEvent.m1472c("NewTabPage", (String) null);
        this.f1785b = ob2.getActiveTab();
        Profile z = this.f1785b.mo9411z();
        SuggestionsSource c = Mt2.b().c(z);
        Nt2 suggestionsEventReporterBridge = new SuggestionsEventReporterBridge();
        ob2 ob22 = ob2;
        Pt2 pt2 = new Pt2(r11, z, ob22, tabModelSelector);
        SU1 su1 = r1;
        SU1 su12 = new SU1(this, c, suggestionsEventReporterBridge, pt2, z, ob22, chromeActivity.mo8057Y0(), chromeActivity.getSnackbarManager());
        this.f1788e = su1;
        this.f1789k = new TU1(this, chromeActivity, z, pt2, (RU1) null);
        this.f1786c = chromeActivity.getResources().getString(wx0.button_new_tab);
        TemplateUrlServiceFactory.m2927a().mo9620a((TemplateUrlService.TemplateUrlServiceObserver) this);
        this.f1785b.mo9319a(this.f1790n);
        this.f1787d = LayoutInflater.from(chromeActivity).inflate(rx0.edge_new_tab_page_view, (ViewGroup) null);
        Sn0.a.a(this);
        NewTabPageView newTabPageView = this.f1787d;
        SU1 su13 = this.f1788e;
        Tab tab = this.f1785b;
        TileGroup.Delegate delegate2 = this.f1789k;
        boolean z2 = this.f1791p;
        TemplateUrlServiceFactory.m2927a().mo9627c();
        if (this.f1785b.mo9302K() != null) {
            NavigationController F = this.f1785b.mo9302K().mo9742F();
            String a = F.a(F.j(), "NewTabPageScrollPosition");
            if (!TextUtils.isEmpty(a)) {
                try {
                    Integer.parseInt(a);
                } catch (NumberFormatException e) {
                    VN0.c("NewTabPage", "Bad data found for scroll position: %s", new Object[]{a, e});
                }
            }
        }
        newTabPageView.a(su13, tab, delegate2, z2);
        this.f1784a = new hv1();
        this.f1784a.a(this.f1787d.findViewById(ox0.ntp_content));
        mo8510h();
        suggestionsEventReporterBridge.a();
        String str = MostVisitedSitesManager.m2930c().f2204b;
        if (!(str == null || (delegate = this.f1789k) == null)) {
            delegate.fetchPopularSites(str);
        }
        this.f1787d.c().a(false);
        Ht2.a.mo9216a((MostVisitedSitesManager.MostVisitedSitesStringChangedObserver) this);
        MicrosoftSigninManager.C0424c.f2104a.mo8888a((MicrosoftSigninManager.SignInStateObserver) this);
        ServerConfigManager.h().b();
        if (fv1.d()) {
            this.f1793q3 = new fv1(r11);
            hv1 hv1 = this.f1784a;
            fv1 fv1 = this.f1793q3;
            NewTabPageLayout newTabPageLayout = hv1.a;
            if (newTabPageLayout != null) {
                newTabPageLayout.setAnaheimNtpManager(fv1);
            }
            NewTabPageLayout newTabPageLayout2 = this.f1784a.a;
            if (newTabPageLayout2 != null) {
                newTabPageLayout2.f();
            }
            this.f1793q3.c = new NU1(this, r11);
            Us0.c.submit(new OU1(this));
        }
    }

    /* renamed from: b */
    public static boolean m2272b(String str) {
        if (str == null) {
            return false;
        }
        try {
            URI uri = new URI(str);
            if (!UrlUtilities.f2348a.contains(uri.getScheme())) {
                return false;
            }
            String host = uri.getHost();
            if (host == null) {
                host = new URI(uri.getScheme() + "://" + uri.getSchemeSpecificPart()).getHost();
            }
            return "newtab".equals(host);
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public void mo8496a(float f) {
        this.f1787d.a(f);
    }

    /* renamed from: a */
    public void mo8498a(String str) {
    }

    /* renamed from: c */
    public boolean mo8501c() {
        hv1 hv1 = this.f1784a;
        if (hv1 == null || hv1.a == null) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    public final boolean mo8502d() {
        if (DeviceFormFactor.isTablet()) {
            return false;
        }
        mo8510h();
        return this.f1791p;
    }

    public void destroy() {
        UiUtils.a(this.f1787d);
        SU1 su1 = this.f1788e;
        su1.e.a();
        for (Ct2 onDestroy : su1.a) {
            onDestroy.onDestroy();
        }
        su1.b.destroy();
        su1.b = new bd2();
        su1.f = true;
        this.f1789k.destroy();
        TemplateUrlServiceFactory.m2927a().mo9624b((TemplateUrlService.TemplateUrlServiceObserver) this);
        this.f1785b.mo9340b(this.f1790n);
        this.f1790n = null;
        this.f1787d.b();
        MostVisitedSitesManager.m2930c().f2205c.remove(this);
        Sn0.a.p.remove(this);
        MicrosoftSigninManager.C0424c.f2104a.mo8897b((MicrosoftSigninManager.SignInStateObserver) this);
        this.f1795y = true;
    }

    /* renamed from: e */
    public int mo8504e() {
        return du0.a(RN0.a.getResources(), jx0.ntp_bg);
    }

    /* renamed from: f */
    public boolean mo8505f() {
        return this.f1788e.isLocationBarShownInNTP();
    }

    /* renamed from: g */
    public String mo8506g() {
        return "newtab";
    }

    public String getTitle() {
        return this.f1786c;
    }

    public String getUrl() {
        return SE2.a;
    }

    public View getView() {
        return this.f1787d;
    }

    /* renamed from: h */
    public final void mo8510h() {
        if (this.f1787d.getResources().getConfiguration().orientation == 1) {
            this.f1791p = true;
        } else {
            this.f1791p = false;
        }
    }

    public void onBrandColorChanged() {
        this.f1787d.m();
    }

    public void onBrandLogoChanged() {
        this.f1787d.o();
    }

    public void onMostVisitedSitesStringChanged(String str) {
        TileGroup.Delegate delegate;
        if (!this.f1795y && str != null && (delegate = this.f1789k) != null) {
            delegate.fetchPopularSites(str);
        }
    }

    public void onShowEnterpriseNewsPolicyChanged() {
        this.f1787d.r();
    }

    public void onSignedIn(AuthenticationMode authenticationMode, String str) {
        this.f1787d.c(true);
    }

    public void onSignedOut(AuthenticationMode authenticationMode) {
        this.f1787d.c(true);
    }

    public void onTemplateURLServiceChanged() {
    }

    /* renamed from: a */
    public boolean mo8499a() {
        return this.f1787d.l();
    }

    /* renamed from: a */
    public void mo8497a(Canvas canvas) {
        this.f1787d.a(canvas, (Runnable) null);
    }

    /* renamed from: b */
    public final String mo8500b() {
        return new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date());
    }
}
