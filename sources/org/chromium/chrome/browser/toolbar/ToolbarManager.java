package org.chromium.chrome.browser.toolbar;

import FO0;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.mmx.experiment.FeatureManager$Feature;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.ThemeColorProvider;
import org.chromium.chrome.browser.ThemeColorProvider$ThemeColorObserver;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.compositor.Invalidator;
import org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior;
import org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior$OverviewModeObserver;
import org.chromium.chrome.browser.compositor.layouts.phone.TileLayout;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.edge_ntp.NativePageFactory;
import org.chromium.chrome.browser.edge_ntp.NewTabPage;
import org.chromium.chrome.browser.feature_engagement.TrackerFactory;
import org.chromium.chrome.browser.newsguard.NewsGuardManager;
import org.chromium.chrome.browser.newsguard.NewsGuardResponseModel;
import org.chromium.chrome.browser.partnercustomizations.HomepageManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.tasks.tab_groups.TabGroupModelFilter;
import org.chromium.chrome.browser.toolbar.bottom.BottomControlsCoordinator;
import org.chromium.chrome.browser.toolbar.top.ActionModeController;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator;
import org.chromium.chrome.browser.translate.TranslateBridge;
import org.chromium.chrome.browser.util.FeatureUtilities;
import org.chromium.chrome.browser.widget.ScrimView$ScrimObserver;
import org.chromium.components.feature_engagement.Tracker;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.p012ui.KeyboardVisibilityDelegate;
import org.chromium.p012ui.resources.ResourceManager;

/* compiled from: PG */
public class ToolbarManager implements ScrimView$ScrimObserver, bC2, sf2, ThemeColorProvider$ThemeColorObserver, Ov1, zu0, NewsGuardManager.OnGetDataCallback {

    /* renamed from: e4 */
    public static final FO0.c f2273e4 = new FO0.c("Android.OmniboxFocusReason", 6);

    /* renamed from: f4 */
    public static final FO0.a f2274f4 = new FO0.a("MobileToolbarOmniboxAcceleratorTap");

    /* renamed from: A3 */
    public BookmarkBridge.b f2275A3;

    /* renamed from: B3 */
    public HI2 f2276B3;

    /* renamed from: C3 */
    public OverviewModeBehavior$OverviewModeObserver f2277C3;

    /* renamed from: D3 */
    public UE1 f2278D3;

    /* renamed from: E3 */
    public final ActionModeController.ActionBarDelegate f2279E3;

    /* renamed from: F3 */
    public ActionModeController f2280F3;

    /* renamed from: G3 */
    public final HC2 f2281G3;

    /* renamed from: H3 */
    public aC2 f2282H3;

    /* renamed from: I3 */
    public final Callback<Boolean> f2283I3;

    /* renamed from: J3 */
    public final Handler f2284J3 = new Handler();

    /* renamed from: K3 */
    public final ChromeActivity f2285K3;

    /* renamed from: L3 */
    public sf2 f2286L3;

    /* renamed from: M3 */
    public SX1 f2287M3;

    /* renamed from: N3 */
    public int f2288N3 = -1;

    /* renamed from: O3 */
    public int f2289O3 = -1;

    /* renamed from: P3 */
    public int f2290P3 = -1;

    /* renamed from: Q3 */
    public int f2291Q3 = -1;

    /* renamed from: R3 */
    public boolean f2292R3;

    /* renamed from: S3 */
    public int f2293S3 = -1;

    /* renamed from: T3 */
    public boolean f2294T3;

    /* renamed from: U3 */
    public boolean f2295U3;

    /* renamed from: V3 */
    public boolean f2296V3;

    /* renamed from: W3 */
    public yv1 f2297W3;

    /* renamed from: X3 */
    public tJ2 f2298X3;

    /* renamed from: Y3 */
    public boolean f2299Y3;

    /* renamed from: Z3 */
    public Runnable f2300Z3;

    /* renamed from: a */
    public final IncognitoStateProvider f2301a;

    /* renamed from: a4 */
    public boolean f2302a4 = true;

    /* renamed from: b */
    public final TabCountProvider f2303b;

    /* renamed from: b4 */
    public b82 f2304b4;

    /* renamed from: c */
    public final ThemeColorProvider f2305c;

    /* renamed from: c4 */
    public boolean f2306c4;

    /* renamed from: d */
    public final dB2 f2307d;

    /* renamed from: d4 */
    public Gv1 f2308d4;

    /* renamed from: e */
    public final TopToolbarCoordinator f2309e;

    /* renamed from: k */
    public final ToolbarControlContainer f2310k;

    /* renamed from: n */
    public BottomControlsCoordinator f2311n;

    /* renamed from: p */
    public TabModelSelector f2312p;

    /* renamed from: q */
    public ex2 f2313q;

    /* renamed from: q3 */
    public Profile f2314q3;

    /* renamed from: r3 */
    public BookmarkBridge f2315r3;

    /* renamed from: s3 */
    public TemplateUrlService.TemplateUrlServiceObserver f2316s3;

    /* renamed from: t3 */
    public Re2 f2317t3;

    /* renamed from: u3 */
    public GI2 f2318u3;

    /* renamed from: v3 */
    public Lv1 f2319v3;

    /* renamed from: w3 */
    public OverviewModeBehavior f2320w3;

    /* renamed from: x */
    public Tw2 f2321x;

    /* renamed from: x3 */
    public IE1 f2322x3;

    /* renamed from: y */
    public final LocationBarModel f2323y;

    /* renamed from: y3 */
    public eB2 f2324y3;

    /* renamed from: z3 */
    public Yv2 f2325z3;

    /* JADX WARNING: type inference failed for: r6v1, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r5v9, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r5v12, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public ToolbarManager(ChromeActivity chromeActivity, ToolbarControlContainer toolbarControlContainer, Invalidator invalidator, Callback<Boolean> callback, ThemeColorProvider themeColorProvider, boolean z) {
        TabSwitcherModeTTPhone tabSwitcherModeTTPhone;
        TabSwitcherModeTTPhone tabSwitcherModeTTPhone2;
        this.f2285K3 = chromeActivity;
        this.f2279E3 = new PC2(chromeActivity, toolbarControlContainer);
        this.f2323y = new LocationBarModel(chromeActivity);
        this.f2310k = toolbarControlContainer;
        this.f2283I3 = callback;
        this.f2281G3 = new HC2();
        this.f2292R3 = z;
        this.f2286L3 = new RB2(this);
        this.f2301a = new IncognitoStateProvider();
        this.f2303b = new TabCountProvider();
        this.f2305c = themeColorProvider;
        this.f2305c.e.mo7868a(this);
        this.f2307d = new dB2(this.f2285K3);
        MAMEdgeManager.m1290a((Context) chromeActivity);
        this.f2309e = new TopToolbarCoordinator(toolbarControlContainer, (ToolbarLayout) this.f2285K3.findViewById(ox0.toolbar));
        this.f2280F3 = new ActionModeController(this.f2285K3, this.f2279E3);
        ActionModeController actionModeController = this.f2280F3;
        HC2 hc2 = this.f2281G3;
        if (!hc2.equals(actionModeController.a)) {
            actionModeController.a = hc2;
            actionModeController.a.a = actionModeController;
        }
        this.f2324y3 = new eB2(chromeActivity, this, chromeActivity.mo8769O());
        this.f2309e.a.mo9525a(invalidator);
        this.f2280F3.d = (float) this.f2309e.a.mo9488C();
        this.f2317t3 = this.f2309e.c();
        this.f2317t3.setToolbarDataProvider(this.f2323y);
        this.f2317t3.a(this);
        this.f2317t3.setDefaultTextEditActionModeCallback(this.f2280F3.a);
        this.f2317t3.a(new Wu1(this.f2285K3.getWindow()), this.f2285K3.mo8772S(), this.f2285K3.mo8174w0());
        this.f2317t3.a(this.f2286L3);
        this.f2309e.a.mo9515a((BB2) this.f2323y, (bC2) this);
        this.f2309e.a.mo9526a((TopToolbarCoordinator.UrlExpansionObserver) chromeActivity.mo8079a1());
        this.f2304b4 = new b82(chromeActivity);
        this.f2313q = new SB2(this);
        this.f2321x = new TB2(this);
        this.f2325z3 = new UB2(this);
        this.f2275A3 = new VB2(this);
        this.f2276B3 = new WB2(this);
        this.f2277C3 = new XB2(this);
        this.f2278D3 = new YB2(this);
        this.f2282H3 = new aC2(this);
        if (this.f2292R3) {
            ThemeManager.f1300h.mo4504a((zu0) this);
            TopToolbarCoordinator topToolbarCoordinator = this.f2309e;
            TabCountProvider tabCountProvider = this.f2303b;
            topToolbarCoordinator.a.setTabCountProvider(tabCountProvider);
            FC2 fc2 = topToolbarCoordinator.b;
            if (!(fc2 == null || (tabSwitcherModeTTPhone2 = fc2.a) == null)) {
                tabSwitcherModeTTPhone2.a(tabCountProvider);
            }
            TopToolbarCoordinator topToolbarCoordinator2 = this.f2309e;
            IncognitoStateProvider incognitoStateProvider = this.f2301a;
            FC2 fc22 = topToolbarCoordinator2.b;
            if (!(fc22 == null || (tabSwitcherModeTTPhone = fc22.a) == null)) {
                tabSwitcherModeTTPhone.a(incognitoStateProvider);
            }
            TopToolbarCoordinator topToolbarCoordinator3 = this.f2309e;
            dB2 db2 = this.f2285K3.mo8775W() ? this.f2307d : this.f2305c;
            MenuButton d = topToolbarCoordinator3.d();
            if (d != null) {
                d.setThemeColorProvider(db2);
            }
            topToolbarCoordinator3.a.mo9523a((ThemeColorProvider) db2);
        }
    }

    /* renamed from: b */
    public static void m3163b(int i) {
        if (i == 0) {
            if (Vx2.a() != null) {
                i = 5;
            }
        }
        f2273e4.a(i);
    }

    /* renamed from: b */
    public void mo9467b(boolean z) {
    }

    /* renamed from: e */
    public void mo9473e(boolean z) {
        if (this.f2299Y3) {
            boolean isUrlBarFocused = this.f2317t3.isUrlBarFocused();
            this.f2317t3.setUrlBarFocus(z);
            if (isUrlBarFocused && z) {
                this.f2317t3.selectAll();
            }
        }
    }

    /* renamed from: f */
    public int mo9474f() {
        return this.f2323y.e();
    }

    /* renamed from: g */
    public ToolbarLayout mo9476g() {
        return this.f2309e.a;
    }

    /* renamed from: h */
    public final void mo9477h() {
        if (this.f2295U3 && this.f2294T3) {
            this.f2309e.a.mo9500O();
        }
    }

    /* renamed from: i */
    public void mo9478i() {
        boolean z = false;
        ss0.b("OpenHomePage", (HashMap) null, true, 0, (String) null);
        boolean z2 = this.f2306c4;
        Tab a = this.f2323y.a();
        if (a != null) {
            String o = HomepageManager.m2678o();
            boolean j = FeatureUtilities.j();
            if (TextUtils.isEmpty(o) || j) {
                o = SE2.a;
            }
            if (o.startsWith("about:") || o.startsWith("chrome:") || o.startsWith("chrome-native:")) {
                z = true;
            }
            RecordHistogram.m1543a("Navigation.Home.IsChromeInternal", z);
            if (j) {
                mo9458a("clear_tab_button_clicked");
            } else {
                mo9458a("homepage_button_clicked");
            }
            a.mo9338b(new LoadUrlParams(o, 67108864));
            gh0.a(FeatureManager$Feature.EXPERIMENT_TEST1);
        }
    }

    /* renamed from: j */
    public final void mo9479j() {
        int i = this.f2293S3;
        Tab a = i != -1 ? this.f2312p.mo9441a(i) : null;
        if (a == null) {
            a = this.f2312p.g();
        }
        boolean isIncognito = this.f2323y.isIncognito();
        Tab a2 = this.f2323y.a();
        boolean X = a != null ? a.mo9315X() : this.f2312p.mo9452f();
        this.f2323y.a(a, X);
        Tab a3 = this.f2323y.a();
        this.f2317t3.setUrlToPageUrl();
        this.f2317t3.c(true);
        mo9483n();
        if (a3 == null) {
            mo9469c(false);
        } else {
            this.f2282H3.a();
            if (!a3.mo9317Z()) {
                mo9469c(false);
            } else if (NativePageFactory.a(a3.getUrl(), a3.mo9315X())) {
                mo9469c(false);
            } else {
                mo9482m();
                mo9454a(a3.mo9290A());
            }
        }
        if (!(a2 == null || isIncognito == X || !this.f2285K3.mo8775W())) {
            this.f2280F3.b();
        }
        if (!(a2 == a && isIncognito == X)) {
            if (a2 != a) {
                if (a2 != null) {
                    a2.mo9340b(this.f2325z3);
                }
                if (a != null) {
                    a.mo9319a(this.f2325z3);
                }
            }
            JE2.a(this.f2285K3.getResources());
            if (a != null) {
                fw2.q(a);
            }
            this.f2309e.a.mo9502Q();
            if (!(a == null || a.mo9302K() == null || !a.mo9302K().mo9749M())) {
                this.f2309e.a.mo9499N();
            }
            mo9473e(false);
            if (mo9481l()) {
                this.f2317t3.f();
            }
            if (X) {
                this.f2309e.c().h();
            }
        }
        Profile g = this.f2312p.mo9443a(X).mo9434g();
        if (this.f2314q3 != g) {
            BookmarkBridge bookmarkBridge = this.f2315r3;
            if (bookmarkBridge != null) {
                bookmarkBridge.a();
                this.f2315r3 = null;
            }
            if (g != null) {
                this.f2315r3 = new BookmarkBridge(g.mo9203c());
                this.f2315r3.a(this.f2275A3);
                Nv1 nv1 = this.f2319v3;
                if (nv1 != null) {
                    nv1.k = this.f2315r3;
                }
                this.f2317t3.setAutocompleteProfile(g);
            }
            this.f2314q3 = g;
        }
        mo9483n();
    }

    /* renamed from: k */
    public final void mo9480k() {
        TemplateUrlService a = TemplateUrlServiceFactory.m2927a();
        this.f2316s3 = new OB2(this, a);
        a.mo9620a(this.f2316s3);
    }

    /* renamed from: l */
    public final boolean mo9481l() {
        Tab a = this.f2323y.a();
        if (a == null) {
            return false;
        }
        lb2 v = a.mo9403v();
        if (((v instanceof NewTabPage) || (v instanceof JU1)) && this.f2285K3.mo8775W() && this.f2285K3.getResources().getConfiguration().keyboard == 2) {
            return true;
        }
        return false;
    }

    /* renamed from: m */
    public final void mo9482m() {
        if (!this.f2309e.a.mo9494I()) {
            this.f2309e.a.mo9508W();
        }
    }

    /* renamed from: n */
    public void mo9483n() {
        TileLayout tileLayout = this.f2322x3.q3;
        if (!(tileLayout instanceof TileLayout) || !tileLayout.U) {
            Tab a = this.f2323y.a();
            this.f2309e.a.mo9511Z();
            boolean z = true;
            this.f2309e.a.mo9553n(a != null && a.mo9343b());
            this.f2309e.a.mo9555o(a != null && a.mo9348c());
            if (this.f2309e.d() != null && !this.f2306c4) {
                this.f2309e.d().setVisibility(0);
            }
            eB2 eb2 = this.f2324y3;
            if (this.f2323y.q() == null) {
                z = false;
            }
            eb2.a(z);
            this.f2309e.a.mo9510Y();
        }
    }

    public void onGetNewsGuardData(NewsGuardResponseModel newsGuardResponseModel) {
        this.f2317t3.a(newsGuardResponseModel);
    }

    public void onScrimClick() {
        mo9473e(false);
    }

    public void onScrimVisibilityChanged(boolean z) {
        if (z) {
            ChromeActivity chromeActivity = this.f2285K3;
            chromeActivity.addViewObscuringAllTabs(chromeActivity.mo8059Z0());
            return;
        }
        ChromeActivity chromeActivity2 = this.f2285K3;
        chromeActivity2.removeViewObscuringAllTabs(chromeActivity2.mo8059Z0());
    }

    public void onThemeColorChanged(int i, boolean z) {
    }

    /* renamed from: c */
    public final void mo9469c(boolean z) {
        this.f2282H3.a();
        this.f2309e.a.mo9536d(z);
    }

    /* renamed from: d */
    public void mo9471d(boolean z) {
        hC2 hc2;
        this.f2309e.a.mo9548j(z);
        BottomControlsCoordinator bottomControlsCoordinator = this.f2311n;
        if (bottomControlsCoordinator != null && (hc2 = bottomControlsCoordinator.b) != null) {
            hc2.a.f.a(true);
        }
    }

    /* renamed from: f */
    public final void mo9475f(boolean z) {
        this.f2317t3.c(z);
        if (z) {
            mo9483n();
        }
    }

    /* renamed from: a */
    public final void mo9458a(String str) {
        Yw2 yw2 = this.f2312p;
        if (yw2 != null && yw2.g() != null) {
            TrackerFactory.nativeGetTrackerForProfile(this.f2312p.g().mo9411z()).d(str);
        }
    }

    /* renamed from: b */
    public void mo9465b() {
        int i;
        this.f2311n = new BottomControlsCoordinator(this.f2285K3.mo8046P0(), (ViewStub) this.f2285K3.findViewById(ox0.bottom_controls_stub), this.f2285K3.mo8174w0(), new CB2(this), new EB2(this), new FB2(this), this.f2307d);
        this.f2306c4 = FeatureUtilities.d() && (!FeatureUtilities.b() || this.f2285K3.getResources().getConfiguration().orientation != 2);
        this.f2311n.a(this.f2306c4);
        this.f2309e.a.mo9544g(this.f2306c4);
        Resources resources = this.f2285K3.getResources();
        if (FeatureUtilities.h()) {
            i = kx0.labeled_bottom_toolbar_height;
        } else {
            i = kx0.bottom_toolbar_height;
        }
        CK3.c = resources.getDimensionPixelSize(i);
    }

    /* renamed from: c */
    public View mo9468c() {
        return this.f2309e.a.mo9580u();
    }

    /* renamed from: e */
    public final MenuButton mo9472e() {
        BottomControlsCoordinator bottomControlsCoordinator = this.f2311n;
        if (bottomControlsCoordinator == null) {
            return this.f2309e.d();
        }
        hC2 hc2 = bottomControlsCoordinator.b;
        if (hc2 != null) {
            return hc2.a.f;
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* renamed from: a */
    public final void mo9462a(Tracker tracker, String str, int i, int i2, Integer num, boolean z) {
        EK3 ek3 = new EK3(mo9470d());
        ek3.a(0, 0, 0, this.f2285K3.getResources().getDimensionPixelOffset(kx0.text_bubble_menu_anchor_y_inset));
        this.f2298X3 = new tJ2(this.f2285K3, mo9470d(), i, i2, ek3);
        this.f2298X3.d.a(true);
        tJ2 tj2 = this.f2298X3;
        tj2.d.y.mo7868a(new HB2(this, tracker, str));
        this.f2308d4.a(num, z);
        this.f2298X3.c();
    }

    /* renamed from: d */
    public View mo9470d() {
        BottomControlsCoordinator bottomControlsCoordinator = this.f2311n;
        if (bottomControlsCoordinator == null || !this.f2306c4) {
            return this.f2309e.a.mo9583x();
        }
        hC2 hc2 = bottomControlsCoordinator.b;
        return (hc2 != null ? hc2.a.f : null).c();
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9461a(Tracker tracker, String str) {
        tracker.c(str);
        this.f2308d4.a();
    }

    /* renamed from: a */
    public void mo9459a(Tab tab, String str) {
        if (tab != null) {
            ChromeActivity j = tab.mo9379j();
            if ((j instanceof ChromeTabbedActivity) && !j.mo8775W() && !j.mo8151q1() && DownloadUtils.a(tab)) {
                Tracker nativeGetTrackerForProfile = TrackerFactory.nativeGetTrackerForProfile(tab.mo9411z());
                if (nativeGetTrackerForProfile.a(str)) {
                    mo9462a(nativeGetTrackerForProfile, str, wx0.iph_download_page_for_offline_usage_text, wx0.iph_download_page_for_offline_usage_accessibility_text, Integer.valueOf(ox0.offline_page_id), true);
                    SW1 o = SW1.o(((ChromeTabbedActivity) j).mo8172v0());
                    if (o != null) {
                        o.a(2);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public void mo9466b(Tab tab, String str) {
        if (tab != null) {
            tab.mo9379j();
            Nv1 nv1 = this.f2319v3;
            if (nv1 != null && nv1.a(tab) && TranslateBridge.nativeShouldShowManualTranslateIPH(tab.mo9302K())) {
                Tracker nativeGetTrackerForProfile = TrackerFactory.nativeGetTrackerForProfile(tab.mo9411z());
                if (nativeGetTrackerForProfile.a(str)) {
                    mo9462a(nativeGetTrackerForProfile, str, wx0.iph_translate_menu_button_text, wx0.iph_translate_menu_button_accessibility_text, Integer.valueOf(ox0.translate_id), false);
                }
            }
        }
    }

    /* renamed from: b */
    public static /* synthetic */ void m3165b(ToolbarManager toolbarManager, boolean z) {
        toolbarManager.f2282H3.a();
        toolbarManager.f2309e.a(z);
    }

    /* renamed from: a */
    public void mo9460a(TabModelSelector tabModelSelector, SX1 sx1, OverviewModeBehavior overviewModeBehavior, IE1 ie1, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3, View.OnClickListener onClickListener4, View.OnClickListener onClickListener5, View.OnClickListener onClickListener6) {
        ToolbarManager toolbarManager = this;
        TabModelSelector tabModelSelector2 = tabModelSelector;
        OverviewModeBehavior overviewModeBehavior2 = overviewModeBehavior;
        IE1 ie12 = ie1;
        View.OnClickListener onClickListener7 = onClickListener;
        View.OnClickListener onClickListener8 = onClickListener2;
        toolbarManager.f2312p = tabModelSelector2;
        TopToolbarCoordinator topToolbarCoordinator = toolbarManager.f2309e;
        FC2 fc2 = topToolbarCoordinator.b;
        if (fc2 != null) {
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone = fc2.a;
            if (tabSwitcherModeTTPhone != null) {
                tabSwitcherModeTTPhone.b(onClickListener7);
            }
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone2 = topToolbarCoordinator.b.a;
            if (tabSwitcherModeTTPhone2 != null) {
                tabSwitcherModeTTPhone2.a(onClickListener8);
            }
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone3 = topToolbarCoordinator.b.a;
            if (tabSwitcherModeTTPhone3 != null) {
                tabSwitcherModeTTPhone3.a(tabModelSelector2);
            }
        }
        topToolbarCoordinator.a.mo9506U();
        topToolbarCoordinator.c().e();
        topToolbarCoordinator.c().setUrlToPageUrl();
        topToolbarCoordinator.a.mo9505T();
        topToolbarCoordinator.a.setOnTabSwitcherClickHandler(onClickListener7);
        topToolbarCoordinator.a.mo9519a(onClickListener3);
        topToolbarCoordinator.a.setCustomTabCloseClickHandler(onClickListener4);
        topToolbarCoordinator.a.setLayoutUpdateHost(ie12);
        topToolbarCoordinator.a.setOnRubyTabCenterClickHandler(onClickListener5);
        topToolbarCoordinator.a.setOnHubClickHandler(onClickListener6);
        topToolbarCoordinator.a.mo9547j();
        TopToolbarCoordinator topToolbarCoordinator2 = toolbarManager.f2309e;
        topToolbarCoordinator2.a.addOnAttachStateChangeListener(new NB2(toolbarManager));
        toolbarManager.f2323y.v();
        toolbarManager.f2323y.c(Vx2.b());
        toolbarManager.f2287M3 = sx1;
        toolbarManager.f2294T3 = false;
        if (overviewModeBehavior2 != null) {
            toolbarManager.f2320w3 = overviewModeBehavior2;
            toolbarManager.f2320w3.b(toolbarManager.f2277C3);
            dB2 db2 = toolbarManager.f2307d;
            db2.x = toolbarManager.f2320w3;
            db2.x.b(db2.y);
            toolbarManager.f2323y.a(toolbarManager.f2320w3);
        }
        if (ie12 != null) {
            toolbarManager.f2322x3 = ie12;
            IE1 ie13 = toolbarManager.f2322x3;
            ie13.y.mo7868a(toolbarManager.f2278D3);
        }
        if (toolbarManager.f2311n != null) {
            View.OnClickListener ib2 = new IB2(toolbarManager);
            zv1 zv1 = toolbarManager.f2297W3;
            if (zv1 != null) {
                zv1.e = new JB2(toolbarManager);
            }
            BottomControlsCoordinator bottomControlsCoordinator = toolbarManager.f2311n;
            ChromeActivity chromeActivity = toolbarManager.f2285K3;
            ResourceManager g = chromeActivity.mo8026G0().g();
            IE1 y = toolbarManager.f2285K3.mo8026G0().y();
            GB2 gb2 = new GB2(toolbarManager, onClickListener7);
            View.OnClickListener gb22 = new GB2(toolbarManager, onClickListener8);
            yv1 yv1 = toolbarManager.f2297W3;
            OverviewModeBehavior overviewModeBehavior3 = toolbarManager.f2320w3;
            QH3 S = toolbarManager.f2285K3.mo8772S();
            TabCountProvider tabCountProvider = toolbarManager.f2303b;
            IncognitoStateProvider incognitoStateProvider = toolbarManager.f2301a;
            ViewGroup viewGroup = (ViewGroup) toolbarManager.f2285K3.findViewById(ox0.control_container);
            eC2 ec2 = bottomControlsCoordinator.a;
            ec2.a.a(fC2.f, y);
            y.y.mo7868a(ec2);
            y.z3.b.mo7868a(ec2);
            bottomControlsCoordinator.a.a.a(fC2.h, g);
            eC2 ec22 = bottomControlsCoordinator.a;
            ec22.k = S;
            ec22.k.mo10019f().mo9944a((KeyboardVisibilityDelegate.KeyboardVisibilityListener) ec22);
            hC2 hc2 = bottomControlsCoordinator.b;
            if (hc2 != null) {
                kC2 kc2 = hc2.a;
                ThemeColorProvider themeColorProvider = hc2.d;
                mC2 mc2 = kc2.a;
                OverviewModeBehavior overviewModeBehavior4 = mc2.b;
                if (overviewModeBehavior4 != null) {
                    overviewModeBehavior4.a(mc2);
                }
                mc2.b = overviewModeBehavior3;
                mc2.b.b(mc2);
                mC2 mc22 = kc2.a;
                mc22.c = themeColorProvider;
                mc22.c.e.mo7868a(mc22);
                kc2.b.setThemeColorProvider(themeColorProvider);
                kc2.c.setThemeColorProvider(themeColorProvider);
                kc2.d.setThemeColorProvider(themeColorProvider);
                kc2.d.setIncognitoStateProvider(incognitoStateProvider);
                kc2.e.a.a(oB2.b, gb2);
                nB2 nb2 = kc2.e;
                nb2.b = themeColorProvider;
                nb2.c = new lB2(nb2);
                ThemeColorProvider themeColorProvider2 = nb2.b;
                themeColorProvider2.k.mo7868a(nb2.c);
                nB2 nb22 = kc2.e;
                nb22.d = tabCountProvider;
                nb22.e = new mB2(nb22);
                TabCountProvider tabCountProvider2 = nb22.d;
                tabCountProvider2.a.mo7868a(nb22.e);
                kc2.f.setAppMenuButtonHelper(yv1);
                kc2.f.setThemeColorProvider(themeColorProvider);
                hc2.b = new pC2(hc2.c, viewGroup, incognitoStateProvider, hc2.d, gb22, ib2, yv1, overviewModeBehavior3, tabCountProvider);
                eC2 ec23 = bottomControlsCoordinator.a;
                ec23.a.a(fC2.i, y.g());
            }
            yz2 yz2 = bottomControlsCoordinator.c;
            if (yz2 != null) {
                eC2 ec24 = bottomControlsCoordinator.a;
                ec24.getClass();
                yz2.a(chromeActivity, new dC2(ec24));
            }
            toolbarManager = this;
            ON0.a(toolbarManager.f2317t3.k(), ox0.bottom_toolbar);
        }
        toolbarManager.f2294T3 = true;
        TemplateUrlService a = TemplateUrlServiceFactory.m2927a();
        PB2 pb2 = new PB2(toolbarManager, a);
        a.mo9623b((TemplateUrlService.LoadListener) pb2);
        if (a.mo9630e()) {
            pb2.onTemplateUrlServiceLoaded();
        } else {
            a.mo9632g();
        }
        toolbarManager.f2312p.a(toolbarManager.f2313q);
        for (TabModel b : toolbarManager.f2312p.a) {
            b.mo9428b(toolbarManager.f2321x);
        }
        mo9479j();
        if (toolbarManager.f2312p.e) {
            toolbarManager.f2295U3 = true;
        }
        if (toolbarManager.f2295U3 && toolbarManager.f2294T3) {
            toolbarManager.f2309e.a.mo9500O();
        }
        TabCountProvider tabCountProvider3 = toolbarManager.f2303b;
        tabCountProvider3.b = toolbarManager.f2312p;
        tabCountProvider3.c = new hB2(tabCountProvider3);
        tabCountProvider3.b.a(tabCountProvider3.c);
        tabCountProvider3.d = new iB2(tabCountProvider3);
        tabCountProvider3.b.b.a(tabCountProvider3.d);
        if (tabCountProvider3.b.b.a() instanceof TabGroupModelFilter) {
            tabCountProvider3.e = new jB2(tabCountProvider3);
            tabCountProvider3.b.b.a().d.mo7868a(tabCountProvider3.e);
        }
        tabCountProvider3.a();
        IncognitoStateProvider incognitoStateProvider2 = toolbarManager.f2301a;
        incognitoStateProvider2.c = toolbarManager.f2312p;
        incognitoStateProvider2.c.a(incognitoStateProvider2.b);
        incognitoStateProvider2.a(incognitoStateProvider2.c.mo9452f());
        dB2 db22 = toolbarManager.f2307d;
        db22.q = toolbarManager.f2301a;
        IncognitoStateProvider incognitoStateProvider3 = db22.q;
        incognitoStateProvider3.a.mo7868a(db22);
        db22.onIncognitoStateChanged(incognitoStateProvider3.a());
        toolbarManager.f2299Y3 = true;
        Runnable runnable = toolbarManager.f2300Z3;
        if (runnable != null) {
            runnable.run();
            toolbarManager.f2300Z3 = null;
        }
    }

    /* renamed from: a */
    public boolean mo9464a() {
        Tab a = this.f2323y.a();
        if (a == null || !a.mo9343b()) {
            return false;
        }
        ss0.b("go_back", "CV", ss0.c(a.getId()));
        a.mo9305N();
        mo9483n();
        return true;
    }

    /* renamed from: a */
    public void mo9463a(boolean z) {
        FC2 fc2;
        TopToolbarCoordinator topToolbarCoordinator = this.f2309e;
        topToolbarCoordinator.a.mo9546i(z);
        if (!(topToolbarCoordinator.a.mo9489D() == null || !topToolbarCoordinator.a.mo9489D().c() || (fc2 = topToolbarCoordinator.b) == null)) {
            boolean z2 = !z;
            fc2.a.animate().alpha(z2 ? 1.0f : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER).setDuration(200).setListener(new EC2(fc2, z2));
        }
        if (z) {
            b82 b82 = this.f2304b4;
            if (b82.d == -1) {
                b82.c = b82.b;
                b82.d = SystemClock.elapsedRealtime();
            }
        }
        GI2 gi2 = this.f2318u3;
        if (gi2 != null && z) {
            gi2.a(true);
        }
        SX1 sx1 = this.f2287M3;
        if (sx1 != null) {
            if (z) {
                this.f2288N3 = sx1.b(this.f2288N3);
            } else {
                sx1.a(this.f2288N3);
            }
            this.f2283I3.onResult(Boolean.valueOf(z));
        }
    }

    /* renamed from: a */
    public void mo9456a(Drawable drawable) {
        this.f2309e.a.setCloseButtonImageResource(drawable);
    }

    /* renamed from: a */
    public final void mo9455a(long j, String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (elapsedRealtime < 30000) {
            this.f2284J3.postDelayed(new MB2(this, j, str), 30000 - elapsedRealtime);
            return;
        }
        RecordHistogram.m1550d(Eo.a("MobileStartup.ToolbarFirstDrawTime2.", str), this.f2309e.a.mo9579t() - j);
        this.f2304b4.a();
    }

    /* renamed from: a */
    public static /* synthetic */ void m3162a(ToolbarManager toolbarManager, boolean z) {
        toolbarManager.f2317t3.c(z);
        if (z) {
            toolbarManager.mo9483n();
        }
    }

    /* renamed from: a */
    public final void mo9454a(int i) {
        Tab a = this.f2323y.a();
        if (a != null && !NativePageFactory.a(a.getUrl(), a.mo9315X())) {
            int max = Math.max(i, 5);
            this.f2309e.a.mo9513a(((float) max) / 100.0f);
            if (max == 100) {
                mo9469c(true);
            }
        }
    }

    /* renamed from: a */
    public void mo9457a(Theme theme) {
        if (!this.f2296V3) {
            this.f2323y.x();
            this.f2309e.a.mo9561p(true);
        }
    }
}
