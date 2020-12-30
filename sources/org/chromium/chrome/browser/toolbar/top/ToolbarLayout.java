package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.ruby.new_item_indicator.BadgeFontIconView;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import fv1;
import org.chromium.base.ObserverList;
import org.chromium.chrome.browser.ThemeColorProvider;
import org.chromium.chrome.browser.ThemeColorProvider$ThemeColorObserver;
import org.chromium.chrome.browser.ThemeColorProvider$TintObserver;
import org.chromium.chrome.browser.compositor.Invalidator;
import org.chromium.chrome.browser.edge_ntp.NewTabPage;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.partnercustomizations.HomepageManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.MenuButton;
import org.chromium.chrome.browser.toolbar.TabCountProvider;
import org.chromium.chrome.browser.toolbar.ToolbarManager;
import org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator;
import org.chromium.chrome.browser.widget.ToolbarProgressBar;
import org.chromium.p012ui.base.DeviceFormFactor;
import org.chromium.ui.UiUtils;

/* compiled from: PG */
public abstract class ToolbarLayout extends FrameLayout implements ThemeColorProvider$TintObserver, ThemeColorProvider$ThemeColorObserver, MicrosoftSigninManager.SignInStateObserver, No0 {

    /* renamed from: A3 */
    public fv1.a f2326A3;

    /* renamed from: a */
    public Invalidator f2327a;

    /* renamed from: b */
    public final ObserverList<TopToolbarCoordinator.UrlExpansionObserver> f2328b = new ObserverList<>();

    /* renamed from: c */
    public final int[] f2329c = new int[2];

    /* renamed from: d */
    public MenuButton f2330d;

    /* renamed from: e */
    public AppCompatImageButton f2331e;

    /* renamed from: k */
    public ImageView f2332k;

    /* renamed from: n */
    public final ColorStateList f2333n = Gc.b(getContext(), jx0.dark_mode_tint);

    /* renamed from: p */
    public final ColorStateList f2334p = Gc.b(getContext(), jx0.light_mode_tint);

    /* renamed from: q */
    public BB2 f2335q;

    /* renamed from: q3 */
    public BadgeFontIconView f2336q3;

    /* renamed from: r3 */
    public AppCompatImageButton f2337r3;

    /* renamed from: s3 */
    public boolean f2338s3;

    /* renamed from: t3 */
    public boolean f2339t3;

    /* renamed from: u3 */
    public long f2340u3;

    /* renamed from: v3 */
    public boolean f2341v3;

    /* renamed from: w3 */
    public ThemeColorProvider f2342w3;

    /* renamed from: x */
    public bC2 f2343x;

    /* renamed from: x3 */
    public boolean f2344x3;

    /* renamed from: y */
    public ToolbarProgressBar f2345y;

    /* renamed from: y3 */
    public Drawable f2346y3;

    /* renamed from: z3 */
    public Drawable f2347z3;

    static {
        Class<ToolbarLayout> cls = ToolbarLayout.class;
    }

    public ToolbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        JE2.b(getContext(), false);
        this.f2345y = mo9543g();
        addOnLayoutChangeListener(new a(this));
    }

    /* renamed from: A */
    public ToolbarProgressBar mo9486A() {
        return this.f2345y;
    }

    /* renamed from: B */
    public int mo9487B() {
        return getResources().getDimensionPixelSize(kx0.toolbar_progress_bar_height);
    }

    /* renamed from: C */
    public int mo9488C() {
        return getResources().getDimensionPixelSize(kx0.tab_strip_height);
    }

    /* renamed from: D */
    public BB2 mo9489D() {
        return this.f2335q;
    }

    /* renamed from: E */
    public boolean mo9490E() {
        return this.f2344x3;
    }

    /* renamed from: F */
    public boolean mo9491F() {
        return this.f2335q.isIncognito();
    }

    /* renamed from: G */
    public boolean mo9492G() {
        Tab a = this.f2335q.a();
        return a != null && (!a.mo9317Z() || a.mo9290A() >= 100);
    }

    /* renamed from: H */
    public boolean mo9493H() {
        return this.f2338s3;
    }

    /* renamed from: I */
    public boolean mo9494I() {
        return this.f2345y.e();
    }

    /* renamed from: J */
    public boolean mo9495J() {
        return true;
    }

    /* renamed from: K */
    public boolean mo9496K() {
        MenuButton menuButton = this.f2330d;
        if (menuButton == null) {
            return false;
        }
        return menuButton.e();
    }

    /* renamed from: L */
    public void mo9497L() {
    }

    /* renamed from: M */
    public void mo9498M() {
    }

    /* renamed from: N */
    public void mo9499N() {
    }

    /* renamed from: O */
    public void mo9500O() {
    }

    /* renamed from: P */
    public void mo9501P() {
        NewTabPage q = mo9489D().q();
        if (q != null) {
            mo9581v().a(q);
        }
    }

    /* renamed from: Q */
    public void mo9502Q() {
        NewTabPage q = mo9489D().q();
        if (q != null) {
            mo9581v().a(q);
        }
        mo9581v().m();
    }

    /* renamed from: R */
    public void mo9503R() {
    }

    /* renamed from: S */
    public void mo9504S() {
        if (mo9581v() != null) {
            mo9581v().setUrlBarFocus(false);
        }
        ToolbarManager toolbarManager = this.f2343x;
        if (toolbarManager != null) {
            toolbarManager.mo9478i();
            ss0.b("edge_click_homepage", "visit_customized_homepage", HomepageManager.m2681r() ? "yes" : "no");
        }
    }

    /* renamed from: T */
    public void mo9505T() {
    }

    /* renamed from: U */
    public void mo9506U() {
    }

    /* renamed from: V */
    public boolean mo9507V() {
        zv1 b;
        if (this.f2339t3 || this.f2341v3) {
            return true;
        }
        MenuButton menuButton = this.f2330d;
        if (!(menuButton == null || (b = menuButton.b()) == null)) {
            zv1 zv1 = b;
            if (zv1.c || zv1.a.m()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: W */
    public void mo9508W() {
        this.f2345y.h();
        mo9535c0();
        mo9581v().m();
    }

    /* renamed from: X */
    public void mo9509X() {
        if (mo9581v() != null) {
            mo9581v().setUrlBarFocus(false);
        }
        ToolbarManager toolbarManager = this.f2343x;
        if (toolbarManager != null) {
            ToolbarManager toolbarManager2 = toolbarManager;
            Tab a = toolbarManager2.f2323y.a();
            if (a != null) {
                if (a.mo9317Z()) {
                    a.mo9408x0();
                } else {
                    a.mo9398s0();
                }
            }
            toolbarManager2.mo9483n();
        }
    }

    /* renamed from: Y */
    public void mo9510Y() {
        boolean z = true;
        boolean z2 = !DeviceFormFactor.m3641c(getContext());
        boolean x = MicrosoftSigninManager.C0424c.f2104a.mo8931x();
        FrameLayout frameLayout = (FrameLayout) findViewById(ox0.account_image_layout);
        if (frameLayout != null) {
            this.f2332k = bB2.a(frameLayout);
            if (z2) {
                frameLayout.setVisibility(0);
            } else if (MicrosoftSigninManager.C0424c.f2104a.mo8933z()) {
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
                return;
            }
            if (this.f2332k == null) {
                return;
            }
            if (!z2 || x) {
                Da.a.a(this.f2332k, (ColorStateList) null);
                return;
            }
            if (!(ThemeManager.f1300h.mo4505b() == Theme.Dark) && (this.f2326A3 == null || this.f2337r3.getVisibility() != 0 || !this.f2326A3.b)) {
                z = false;
            }
            Da.a.a(this.f2332k, z ? this.f2334p : this.f2333n);
        }
    }

    /* renamed from: Z */
    public void mo9511Z() {
    }

    /* renamed from: a */
    public void mo9514a(int i, Drawable drawable, String str) {
    }

    /* renamed from: a */
    public void mo9517a(Drawable drawable) {
    }

    /* renamed from: a */
    public void mo9518a(Drawable drawable, String str, View.OnClickListener onClickListener) {
    }

    /* renamed from: a */
    public void mo9519a(View.OnClickListener onClickListener) {
    }

    /* renamed from: a */
    public final void mo9521a(ImageView imageView, ColorStateList colorStateList) {
        if (imageView != null) {
            Da.a.a(imageView, colorStateList);
        }
    }

    /* renamed from: a0 */
    public void mo9529a0() {
        if (ThemeManager.f1300h.mo4505b() == Theme.Dark) {
            mo9521a((ImageView) this.f2337r3, Gc.b(getContext(), jx0.grey100));
            return;
        }
        fv1.a aVar = this.f2326A3;
        if (aVar == null) {
            return;
        }
        if (aVar.b) {
            mo9521a((ImageView) this.f2337r3, Gc.b(getContext(), jx0.grey100));
        } else {
            mo9521a((ImageView) this.f2337r3, Gc.b(getContext(), jx0.grey900));
        }
    }

    /* renamed from: b */
    public void mo9531b(TopToolbarCoordinator.UrlExpansionObserver urlExpansionObserver) {
        this.f2328b.mo7869b(urlExpansionObserver);
    }

    /* renamed from: b */
    public void mo9532b(boolean z) {
    }

    /* renamed from: b0 */
    public void mo9533b0() {
        mo9529a0();
        mo9510Y();
    }

    /* renamed from: c */
    public boolean mo9534c() {
        if (mo9581v() != null) {
            mo9581v().setUrlBarFocus(false);
        }
        ToolbarManager toolbarManager = this.f2343x;
        if (toolbarManager != null) {
            return toolbarManager.mo9464a();
        }
        return false;
    }

    /* renamed from: c0 */
    public void mo9535c0() {
        BadgeFontIconView badgeFontIconView = this.f2336q3;
        if (badgeFontIconView != null && badgeFontIconView.getVisibility() == 0) {
            if (mo9492G() && this.f2347z3 != null) {
                this.f2336q3.setText(wx0.font_icon_reload);
                this.f2336q3.setContentDescription(getContext().getString(wx0.accessibility_btn_refresh));
            } else if (this.f2346y3 != null) {
                this.f2336q3.setText(wx0.font_icon_cancel);
                this.f2336q3.setContentDescription(getContext().getString(wx0.accessibility_btn_stop_loading));
            }
        }
    }

    /* renamed from: d */
    public void mo9536d(boolean z) {
        this.f2345y.a(z);
        mo9535c0();
        mo9581v().m();
    }

    /* renamed from: d0 */
    public void mo9537d0() {
        if (this.f2331e != null) {
            boolean A = MicrosoftSigninManager.C0424c.f2104a.mo8866A();
            this.f2331e.setVisibility(A ? 8 : 0);
            this.f2331e.setClickable(!A);
        }
    }

    public void destroy() {
        ThemeColorProvider themeColorProvider = this.f2342w3;
        if (themeColorProvider != null) {
            themeColorProvider.k.mo7869b(this);
            this.f2342w3.e.mo7869b(this);
            this.f2342w3 = null;
        }
        mo9581v().destroy();
    }

    /* renamed from: e */
    public void mo9539e(boolean z) {
        this.f2341v3 = z;
    }

    /* renamed from: e0 */
    public boolean mo9540e0() {
        return this.f2339t3;
    }

    /* renamed from: f */
    public void mo9541f(boolean z) {
    }

    /* renamed from: f0 */
    public boolean mo9542f0() {
        ThemeColorProvider themeColorProvider = this.f2342w3;
        if (themeColorProvider == null) {
            return false;
        }
        Boolean bool = themeColorProvider.d;
        if (bool != null ? bool.booleanValue() : false) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    public ToolbarProgressBar mo9543g() {
        return new ToolbarProgressBar(getContext(), mo9487B(), this, false);
    }

    /* renamed from: g */
    public void mo9544g(boolean z) {
    }

    /* renamed from: h */
    public void mo9545h(boolean z) {
    }

    /* renamed from: i */
    public void mo9546i(boolean z) {
        this.f2339t3 = z;
    }

    /* renamed from: j */
    public void mo9547j() {
        this.f2338s3 = true;
        if (this.f2345y.getParent() != null) {
            this.f2345y.d();
        }
        mo9581v().e();
    }

    /* renamed from: k */
    public void mo9549k(boolean z) {
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.setAppMenuUpdateBadgeSuppressed(z);
        }
    }

    /* renamed from: l */
    public boolean mo9550l(boolean z) {
        return false;
    }

    /* renamed from: m */
    public void mo9551m(boolean z) {
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.c(z);
        }
    }

    /* renamed from: n */
    public void mo9552n() {
    }

    /* renamed from: n */
    public void mo9553n(boolean z) {
    }

    /* renamed from: o */
    public void mo9554o() {
        UiUtils.a(mo9585z());
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.a();
            this.f2330d = null;
        }
    }

    /* renamed from: o */
    public void mo9555o(boolean z) {
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mo9530b();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f2340u3 == 0) {
            this.f2340u3 = SystemClock.elapsedRealtime();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        if (DeviceFormFactor.m3641c(getContext())) {
            this.f2330d = findViewById(ox0.menu_button_wrapper);
        }
        mo9510Y();
        this.f2335q = new b(this);
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.setMenuButtonHighlightDrawable();
        }
        this.f2346y3 = ON0.b(getResources(), lx0.btn_close_white);
        this.f2347z3 = ON0.b(getResources(), lx0.btn_toolbar_reload);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked;
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onSignedIn(AuthenticationMode authenticationMode, String str) {
        mo9537d0();
        mo9510Y();
    }

    public void onSignedOut(AuthenticationMode authenticationMode) {
        mo9537d0();
        mo9510Y();
    }

    public void onThemeColorChanged(int i, boolean z) {
        mo9561p(true);
    }

    public void onTintChanged(ColorStateList colorStateList, boolean z) {
    }

    /* renamed from: p */
    public void mo9560p() {
    }

    /* renamed from: p */
    public void mo9561p(boolean z) {
    }

    /* renamed from: q */
    public boolean mo9562q() {
        if (mo9581v() != null) {
            mo9581v().setUrlBarFocus(false);
        }
        ToolbarManager toolbarManager = this.f2343x;
        if (toolbarManager == null) {
            return false;
        }
        ToolbarManager toolbarManager2 = toolbarManager;
        Tab a = toolbarManager2.f2323y.a();
        if (a == null || !a.mo9348c()) {
            return false;
        }
        ss0.b("go_forward", "CV", ss0.c(a.getId()));
        a.mo9306O();
        toolbarManager2.mo9483n();
        return true;
    }

    /* renamed from: r */
    public String mo9563r() {
        return null;
    }

    /* renamed from: s */
    public View mo9564s() {
        Tab a = this.f2335q.a();
        if (a != null) {
            return a.mo9301J();
        }
        return null;
    }

    public void setCloseButtonImageResource(Drawable drawable) {
    }

    public void setContentAttached(boolean z) {
    }

    public void setCustomTabCloseClickHandler(View.OnClickListener onClickListener) {
    }

    public void setCustomTabIntentDataProvider(wK1 wk1) {
    }

    public void setCustomizedColor(int i) {
    }

    public void setInSyncedTabsView(boolean z) {
        this.f2344x3 = z;
    }

    public void setLayoutUpdateHost(SE1 se1) {
    }

    public void setOnHubClickHandler(View.OnClickListener onClickListener) {
    }

    public void setOnRubyTabCenterClickHandler(View.OnClickListener onClickListener) {
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
    }

    public void setTabCountProvider(TabCountProvider tabCountProvider) {
    }

    public void setTabSwitcherMode(boolean z, boolean z2, boolean z3) {
    }

    public void setTextureCaptureMode(boolean z) {
    }

    public void setUrlBarHidden(boolean z) {
    }

    /* renamed from: t */
    public long mo9579t() {
        return this.f2340u3;
    }

    /* renamed from: u */
    public View mo9580u() {
        return this.f2330d;
    }

    /* renamed from: v */
    public abstract Re2 mo9581v();

    /* renamed from: w */
    public View mo9582w() {
        MenuButton menuButton = this.f2330d;
        if (menuButton == null) {
            return null;
        }
        return menuButton.d();
    }

    /* renamed from: x */
    public ImageButton mo9583x() {
        MenuButton menuButton = this.f2330d;
        if (menuButton == null) {
            return null;
        }
        return menuButton.c();
    }

    /* renamed from: y */
    public yv1 mo9584y() {
        MenuButton menuButton = this.f2330d;
        if (menuButton == null) {
            return null;
        }
        return menuButton.b();
    }

    /* renamed from: z */
    public View mo9585z() {
        return this.f2330d;
    }

    /* renamed from: a */
    public void mo9515a(BB2 bb2, bC2 bc2) {
        this.f2335q = bb2;
        this.f2343x = bc2;
    }

    /* renamed from: b */
    public void mo9530b() {
        ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(ox0.control_container);
        UiUtils.a(viewGroup, this.f2345y, (View) getParent());
        this.f2345y.setProgressBarContainer(viewGroup);
    }

    /* renamed from: a */
    public void mo9527a(yv1 yv1) {
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.setAppMenuButtonHelper(yv1);
            return;
        }
        ImageButton x = mo9583x();
        if (x != null) {
            x.setOnTouchListener(yv1);
            zv1 zv1 = (zv1) yv1;
            zv1.a();
            x.setAccessibilityDelegate(zv1);
        }
    }

    /* renamed from: j */
    public void mo9548j(boolean z) {
        MenuButton menuButton = this.f2330d;
        if (menuButton != null) {
            menuButton.a(z);
        }
    }

    /* renamed from: a */
    public void mo9526a(TopToolbarCoordinator.UrlExpansionObserver urlExpansionObserver) {
        this.f2328b.mo7868a(urlExpansionObserver);
    }

    /* renamed from: a */
    public void mo9523a(ThemeColorProvider themeColorProvider) {
        this.f2342w3 = themeColorProvider;
        this.f2342w3.k.mo7868a(this);
        this.f2342w3.e.mo7868a(this);
    }

    /* renamed from: a */
    public FrameLayout.LayoutParams mo9512a(View view) {
        return (FrameLayout.LayoutParams) view.getLayoutParams();
    }

    /* renamed from: a */
    public void mo9520a(View view, int[] iArr) {
        TE2.a(view, this, iArr);
    }

    /* renamed from: a */
    public void mo9525a(Invalidator invalidator) {
        this.f2327a = invalidator;
    }

    /* renamed from: a */
    public void mo9524a(Invalidator.Client client) {
        Invalidator invalidator = this.f2327a;
        if (invalidator == null) {
            client.doInvalidate();
            return;
        }
        Invalidator.Host host = invalidator.a;
        if (host != null) {
            host.deferInvalidate(client);
        } else {
            client.doInvalidate();
        }
    }

    /* renamed from: a */
    public void mo9516a(Rect rect) {
        View k = mo9581v().k();
        rect.set(k.getPaddingLeft(), k.getPaddingTop(), k.getWidth() - k.getPaddingRight(), k.getHeight() - k.getPaddingBottom());
        TE2.a(this, mo9581v().k(), this.f2329c);
        int[] iArr = this.f2329c;
        rect.offset(iArr[0], iArr[1]);
    }

    /* renamed from: a */
    public void mo9513a(float f) {
        this.f2345y.setProgress(f);
    }

    /* renamed from: a */
    public void mo9528a(boolean z) {
        mo9510Y();
    }

    /* renamed from: a */
    public void mo9522a(fv1.a aVar) {
        this.f2326A3 = aVar;
    }
}
