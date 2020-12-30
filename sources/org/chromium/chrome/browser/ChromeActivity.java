package org.chromium.chrome.browser;

import Lw2;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.assist.AssistContent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.citrix.loggersdk.BuildConfig;
import com.citrix.worx.sdk.AppLogArchiver;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.authentication.OAuthTokenProvider;
import com.microsoft.intune.mam.client.MAMIdentitySwitchResult;
import com.microsoft.intune.mam.client.app.AppIdentitySwitchReason;
import com.microsoft.intune.mam.client.app.AppIdentitySwitchResultCallback;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.ruby.anaheim.AnaheimUtils;
import com.microsoft.ruby.anaheim.SyncSwitchConfirmDialogFragment;
import com.microsoft.ruby.exit.EdgeExitDialogFragment;
import com.microsoft.ruby.serverconfig.ServerConfigManager;
import iM1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.CommandLine;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.MenuOrKeyboardActionController;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.bottombar.BottomBarHelper;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome;
import org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager$ContextualSearchTabPromotionDelegate;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.dependency_injection.ChromeActivityCommonsModule;
import org.chromium.chrome.browser.dom_distiller.DomDistillerUIUtils;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.dual_identity.DualIdentityUtils;
import org.chromium.chrome.browser.edge_learning_tools.ReadAloudUtils;
import org.chromium.chrome.browser.fullscreen.ChromeFullscreenManager;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.metrics.LaunchMetrics;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.microsoft_signin.AadAccountManagementFragment;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.microsoft_signin.MsaAccountManagementFragment;
import org.chromium.chrome.browser.omaha.UpdateNotificationController;
import org.chromium.chrome.browser.p009ui.system.StatusBarColorController$StatusBarColorProvider;
import org.chromium.chrome.browser.p010vr.VrModuleProvider;
import org.chromium.chrome.browser.page_info.PageInfoController;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.preferences.PreferencesLauncher;
import org.chromium.chrome.browser.printing.TabPrinter;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.chrome.browser.share.ShareHelper;
import org.chromium.chrome.browser.signin.ProfileDataCache;
import org.chromium.chrome.browser.signin.SigninManager;
import org.chromium.chrome.browser.snackbar.SnackbarManager;
import org.chromium.chrome.browser.snackbar.SnackbarManager$SnackbarManageable;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabBrowserControlsState;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.toolbar.IncognitoStateProvider;
import org.chromium.chrome.browser.toolbar.TabCountProvider;
import org.chromium.chrome.browser.toolbar.ToolbarManager;
import org.chromium.chrome.browser.toolbar.bottom.BottomControlsCoordinator;
import org.chromium.chrome.browser.toolbar.top.ActionModeController;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator;
import org.chromium.chrome.browser.ui.system.StatusBarColorController;
import org.chromium.chrome.browser.util.FeatureUtilities;
import org.chromium.chrome.browser.webapps.AddToHomescreenManager;
import org.chromium.chrome.browser.widget.ScrimView;
import org.chromium.chrome.browser.widget.bottomsheet.BottomSheet;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.p012ui.KeyboardVisibilityDelegate;
import org.chromium.p012ui.base.DeviceFormFactor;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.p012ui.display.DisplayAndroid;
import org.chromium.policy.CombinedPolicyProvider;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.modaldialog.ModalDialogManager;
import pe2;

/* compiled from: PG */
public abstract class ChromeActivity<C extends iM1> extends AsyncInitializationActivity implements Lw2, AccessibilityManager.AccessibilityStateChangeListener, CombinedPolicyProvider.PolicyChangeListener, ContextualSearchManager$ContextualSearchTabPromotionDelegate, SnackbarManager$SnackbarManageable, UE1, StatusBarColorController$StatusBarColorProvider, Dv1, xv1, MenuOrKeyboardActionController, Pg0 {

    /* renamed from: J4 */
    public static boolean f1553J4;

    /* renamed from: K4 */
    public static boolean f1554K4 = false;
    @Deprecated

    /* renamed from: L4 */
    public static WeakReference<ChromeActivity> f1555L4 = new WeakReference<>((Object) null);
    @Deprecated

    /* renamed from: M4 */
    public static WeakReference<ChromeActivity> f1556M4 = new WeakReference<>((Object) null);

    /* renamed from: A4 */
    public R72 f1557A4;

    /* renamed from: B4 */
    public ActivityTabProvider f1558B4 = new ActivityTabProvider();

    /* renamed from: C3 */
    public Kg0 f1559C3;

    /* renamed from: C4 */
    public Uu1 f1560C4;

    /* renamed from: D3 */
    public C f1561D3;

    /* renamed from: D4 */
    public boolean f1562D4;

    /* renamed from: E3 */
    public aO0<TabModelSelector> f1563E3 = new aO0<>();

    /* renamed from: E4 */
    public final Runnable f1564E4 = new Ks1(this);

    /* renamed from: F3 */
    public boolean f1565F3;

    /* renamed from: F4 */
    public rU1 f1566F4 = null;

    /* renamed from: G3 */
    public long f1567G3;

    /* renamed from: G4 */
    public boolean f1568G4;

    /* renamed from: H3 */
    public TabModelSelector f1569H3;

    /* renamed from: H4 */
    public ArrayList<WeakReference<ActivityPausedListener>> f1570H4 = new ArrayList<>();

    /* renamed from: I3 */
    public kx2 f1571I3;

    /* renamed from: I4 */
    public List<MenuOrKeyboardActionController.MenuOrKeyboardActionHandler> f1572I4 = new ArrayList();

    /* renamed from: J3 */
    public Lw2.a f1573J3;

    /* renamed from: K3 */
    public Lw2.a f1574K3;

    /* renamed from: L3 */
    public TabContentManager f1575L3;

    /* renamed from: M3 */
    public UmaSessionStats f1576M3;

    /* renamed from: N3 */
    public boolean f1577N3;

    /* renamed from: O3 */
    public IntentHandler f1578O3;

    /* renamed from: P3 */
    public boolean f1579P3;

    /* renamed from: Q3 */
    public boolean f1580Q3;

    /* renamed from: R3 */
    public boolean f1581R3;

    /* renamed from: S3 */
    public boolean f1582S3;

    /* renamed from: T3 */
    public boolean f1583T3;

    /* renamed from: U3 */
    public boolean f1584U3;
    @SuppressLint({"NewApi"})

    /* renamed from: V3 */
    public AccessibilityManager.TouchExplorationStateChangeListener f1585V3;

    /* renamed from: W3 */
    public ProfileSyncService.SyncStateChangedListener f1586W3;

    /* renamed from: X3 */
    public ChromeFullscreenManager f1587X3;

    /* renamed from: Y3 */
    public v62 f1588Y3;

    /* renamed from: Z3 */
    public CompositorViewHolder f1589Z3;

    /* renamed from: a4 */
    public aO0<IE1> f1590a4 = new aO0<>();

    /* renamed from: b4 */
    public InsetObserverView f1591b4;

    /* renamed from: c4 */
    public ContextualSearchManager f1592c4;

    /* renamed from: d4 */
    public ZM1 f1593d4;

    /* renamed from: e4 */
    public SnackbarManager f1594e4;

    /* renamed from: f4 */
    public ToolbarManager f1595f4;

    /* renamed from: g4 */
    public jI2 f1596g4;

    /* renamed from: h4 */
    public UpdateNotificationController f1597h4;

    /* renamed from: i4 */
    public BottomSheet f1598i4;

    /* renamed from: j4 */
    public ScrimView f1599j4;

    /* renamed from: k4 */
    public StatusBarColorController f1600k4;

    /* renamed from: l4 */
    public fz1 f1601l4;

    /* renamed from: m4 */
    public xh2 f1602m4;

    /* renamed from: n4 */
    public XA1 f1603n4;

    /* renamed from: o4 */
    public pq2 f1604o4;

    /* renamed from: p4 */
    public long f1605p4;

    /* renamed from: q4 */
    public long f1606q4;

    /* renamed from: r4 */
    public int f1607r4;

    /* renamed from: s4 */
    public int f1608s4;

    /* renamed from: t4 */
    public final TN0 f1609t4 = new TN0();

    /* renamed from: u4 */
    public final w32 f1610u4 = new B32();

    /* renamed from: v4 */
    public Fs1 f1611v4;

    /* renamed from: w4 */
    public QM1 f1612w4;

    /* renamed from: x4 */
    public Set<View> f1613x4 = new HashSet();

    /* renamed from: y4 */
    public boolean f1614y4;

    /* renamed from: z4 */
    public boolean f1615z4;

    static {
        Class<ChromeActivity> cls = ChromeActivity.class;
    }

    /* renamed from: O1 */
    public static int m1627O1() {
        if (SysUtils.isLowEndDevice() && Build.VERSION.SDK_INT >= 21) {
            return xx0.Theme_Chromium_WithWindowAnimation_LowEnd;
        }
        return xx0.Theme_Chromium_WithWindowAnimation;
    }

    /* renamed from: A0 */
    public Fs1 mo8014A0() {
        return this.f1611v4;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* renamed from: A1 */
    public final void mo8015A1() {
        if (this.f1576M3 == null) {
            this.f1576M3 = new UmaSessionStats(this);
        }
        UmaSessionStats.m2536c();
        this.f1576M3.mo8843a(mo8095d1());
    }

    /* renamed from: B0 */
    public Drawable mo8016B0() {
        return new ColorDrawable(ON0.a(getResources(), jx0.light_background_color));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, org.chromium.chrome.browser.ChromeActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: B1 */
    public final void mo8017B1() {
        if (!this.f1583T3 && this.f1582S3 && hasWindowFocus()) {
            if (Build.VERSION.SDK_INT >= 24) {
                getWindow().setBackgroundDrawable(new ColorDrawable(ON0.a(getResources(), jx0.resizing_background_color)));
                mo4508F();
            } else {
                new Handler().post(new Ls1(this));
            }
            this.f1583T3 = true;
        }
    }

    /* renamed from: C0 */
    public fz1 mo8018C0() {
        return this.f1601l4;
    }

    /* renamed from: C1 */
    public void mo8019C1() {
        mo8109h(true);
    }

    /* renamed from: D0 */
    public BottomSheet mo8020D0() {
        return this.f1598i4;
    }

    /* renamed from: D1 */
    public void mo8021D1() {
    }

    /* renamed from: E0 */
    public jI2 mo8022E0() {
        return this.f1596g4;
    }

    /* renamed from: E1 */
    public void mo8023E1() {
        ToolbarLayout toolbarLayout;
        Re2 v;
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null && (toolbarLayout = toolbarManager.f2309e.a) != null && (v = toolbarLayout.mo9581v()) != null) {
            v.a();
        }
    }

    /* renamed from: F0 */
    public final C mo8024F0() {
        return this.f1561D3;
    }

    /* renamed from: F1 */
    public void mo8025F1() {
        hC2 hc2;
        if (!mo8779a()) {
            if (ge2.c().d.b != null) {
                ToolbarManager toolbarManager = this.f1595f4;
                toolbarManager.f2309e.a.mo9551m(true);
                BottomControlsCoordinator bottomControlsCoordinator = toolbarManager.f2311n;
                if (!(bottomControlsCoordinator == null || (hc2 = bottomControlsCoordinator.b) == null)) {
                    hc2.a.f.c(true);
                }
                this.f1589Z3.a();
                return;
            }
            this.f1595f4.mo9471d(false);
        }
    }

    /* renamed from: G0 */
    public CompositorViewHolder mo8026G0() {
        return this.f1589Z3;
    }

    /* renamed from: G1 */
    public final void mo8027G1() {
        if (!this.f1582S3) {
            this.f1579P3 = true;
            return;
        }
        this.f1579P3 = false;
        if (!this.f1580Q3) {
            this.f1580Q3 = true;
            mo8114i1();
            V22.k().d();
            pu1 pu1 = ou1.a;
            pu1.c = 0;
            pu1.b = 0;
            Looper.myQueue().addIdleHandler(new nu1(pu1));
        }
    }

    /* renamed from: H0 */
    public sF1 mo8028H0() {
        return this.f1589Z3;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: H1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8029H1() {
        /*
            r8 = this;
            QM1 r0 = r8.f1612w4
            int r2 = r8.mo8178y0()
            Ms1 r4 = new Ms1
            r4.<init>(r8)
            org.chromium.chrome.browser.tabmodel.TabModelSelector r5 = r8.f1569H3
            jI2 r6 = r8.f1596g4
            org.chromium.chrome.browser.widget.ScrimView r7 = r8.f1599j4
            r1 = r8
            r3 = r8
            r0.a(r1, r2, r3, r4, r5, r6, r7)
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8029H1():void");
    }

    /* renamed from: I0 */
    public ContextualSearchManager mo8030I0() {
        return this.f1592c4;
    }

    /* renamed from: I1 */
    public boolean mo8031I1() {
        return mw1.a(mo8178y0());
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, java.lang.Object, android.app.Activity] */
    /* renamed from: J */
    public void mo8032J() {
        DualIdentityUtils.m2248c("ChromeActivity", "beforeCreateInternal called");
        String a = MAMEdgeManager.m1290a((Context) this);
        MAMEdgeManager.m1295a((Context) this, MAMEdgeManager.m1292a(DualIdentityManager.m2219m()));
        ThreadUtils.m1462c();
        DualIdentityUtils.m2248c(DualIdentityManager.f1761a, "isIntentIdentityAligned() return " + DualIdentityManager.f1771k);
        if (!DualIdentityManager.f1771k) {
            ThreadUtils.m1462c();
            DualIdentityUtils.m2248c(DualIdentityManager.f1761a, "setIsIntentIdentityAligned(" + true + ")");
            DualIdentityManager.f1771k = true;
            Intent intent = getIntent();
            DualIdentityUtils.m2248c("ChromeActivity", "input intent is: " + intent);
            if (!(intent == null || intent.getData() == null)) {
                DualIdentityUtils.m2248c("ChromeActivity", "input intent requires identity: '" + a + "'");
                DualIdentityManager.m2197a(this, a, AppIdentitySwitchReason.CREATE, (AppIdentitySwitchResultCallback) null);
            }
        }
        f1555L4 = new WeakReference<>(this);
        if (this instanceof ChromeTabbedActivity) {
            f1556M4 = new WeakReference<>(this);
        }
        if (Og0.d()) {
            this.f1559C3 = new Kg0(this);
            Kg0 kg0 = this.f1559C3;
            kg0.c.add(new Wp2());
        }
    }

    /* renamed from: J0 */
    public int mo8033J0() {
        return -1;
    }

    /* renamed from: J1 */
    public void mo8034J1() {
        AuthenticationMode authenticationMode = MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? AuthenticationMode.AAD : AuthenticationMode.MSA;
        if (AnaheimUtils.a(authenticationMode)) {
            String string = QN0.a.getString(authenticationMode == AuthenticationMode.AAD ? "AADSwitchToAnaheimSyncAccessPoint" : "SwitchToAnaheimSyncAccessPoint", (String) null);
            if (string != null) {
                if (string.equals(AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_CAMPAIGN.toString())) {
                    AnaheimUtils.a(this, AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_CAMPAIGN);
                } else if (string.equals(AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_NAVIGATION.toString())) {
                    AnaheimUtils.a(this, AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_NAVIGATION);
                } else if (string.equals(AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_SYNC_SWITCH.toString())) {
                    AnaheimUtils.a(this, SyncSwitchConfirmDialogFragment.SyncSwitchDirection.SWITCH_TO_ANAHEIM_SYNC, (SyncSwitchConfirmDialogFragment.OnSwitchListener) null);
                }
            }
        } else if (AnaheimUtils.b()) {
            AnaheimUtils.a(this, SyncSwitchConfirmDialogFragment.SyncSwitchDirection.SWITCH_TO_RUBY_SYNC, (SyncSwitchConfirmDialogFragment.OnSwitchListener) null);
        }
        f1554K4 = false;
    }

    /* renamed from: K0 */
    public int mo8035K0() {
        return -1;
    }

    /* renamed from: K1 */
    public void mo8036K1() {
        CompositorViewHolder compositorViewHolder = this.f1589Z3;
        if (compositorViewHolder != null) {
            compositorViewHolder.J();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: L */
    public ModalDialogManager mo8037L() {
        mo8106g1();
        return new ModalDialogManager(new Qa2(this), 0);
    }

    /* renamed from: L0 */
    public Lw2.a mo8038L0() {
        return mo8081b(mo8095d1().mo9452f());
    }

    /* renamed from: L1 */
    public boolean mo8039L1() {
        if (!FeatureUtilities.isNoTouchModeEnabled() && mo8099e1() != -1) {
            return true;
        }
        return false;
    }

    /* renamed from: M */
    public QH3 mo8040M() {
        return new ChromeWindow(this);
    }

    /* renamed from: M0 */
    public TabModel mo8041M0() {
        TabModelSelector d1 = mo8095d1();
        if (d1 == null) {
            return Ew2.a;
        }
        return d1.mo9448c();
    }

    /* renamed from: M1 */
    public boolean mo8042M1() {
        return true;
    }

    /* renamed from: N0 */
    public WebContents mo8043N0() {
        Tab b;
        if (this.f1581R3 && (b = mx2.b(mo8041M0())) != null) {
            return b.mo9302K();
        }
        return null;
    }

    /* renamed from: N1 */
    public void mo8044N1() {
        QH3 S = mo8772S();
        if (S.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            mo8116j(true);
        } else if (S.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE"};
            S.mo10001a(strArr, new b(this));
        } else {
            mo8116j(false);
        }
    }

    /* renamed from: O0 */
    public yE1 mo8045O0() {
        IE1 y = mo8026G0().y();
        if (y != null) {
            return y.y3;
        }
        return null;
    }

    /* renamed from: P0 */
    public ChromeFullscreenManager mo8046P0() {
        if (this.f1587X3 == null) {
            if (!mo8779a()) {
                this.f1587X3 = mo8125m0();
            } else {
                throw new IllegalStateException();
            }
        }
        return this.f1587X3;
    }

    /* renamed from: Q0 */
    public InsetObserverView mo8047Q0() {
        return this.f1591b4;
    }

    /* renamed from: R */
    public View mo8048R() {
        View findViewById = findViewById(ox0.control_container);
        return findViewById != null ? findViewById : super.mo8048R();
    }

    /* renamed from: R0 */
    public YN0<IE1> mo8049R0() {
        return this.f1590a4;
    }

    /* renamed from: S0 */
    public w32 mo8050S0() {
        return this.f1610u4;
    }

    /* renamed from: T0 */
    public MenuOrKeyboardActionController mo8051T0() {
        return this;
    }

    /* renamed from: U */
    public void mo8052U() {
        this.f1557A4 = new R72(this);
    }

    /* renamed from: U0 */
    public long mo8053U0() {
        return this.f2033p;
    }

    /* renamed from: V0 */
    public OverviewModeBehavior mo8054V0() {
        return null;
    }

    /* renamed from: W0 */
    public YN0<OverviewModeBehavior> mo8055W0() {
        return null;
    }

    /* renamed from: X0 */
    public ZM1 mo8056X0() {
        return this.f1593d4;
    }

    @Deprecated
    /* renamed from: Y0 */
    public TN0 mo8057Y0() {
        return this.f1609t4;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: Z */
    public void mo8058Z() {
        this.f1606q4 = SystemClock.elapsedRealtime();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();
        this.f1589Z3 = findViewById(ox0.compositor_view_holder);
        this.f1589Z3.setRootView(viewGroup);
        viewGroup.setFitsSystemWindows(false);
        this.f1591b4 = InsetObserverView.a(this);
        viewGroup.addView(this.f1591b4, 0);
        super.mo8058Z();
    }

    /* renamed from: Z0 */
    public ScrimView mo8059Z0() {
        return this.f1599j4;
    }

    /* renamed from: a */
    public void mo8061a(int i) {
    }

    /* renamed from: a */
    public void mo8063a(BE1 be1) {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: a */
    public void mo8074a(boolean z, boolean z2) {
        if (ms2.b == null) {
            ms2.b = new ms2(new ls2());
        }
        ms2.b.a(this, mo8172v0(), z, z2);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener, org.chromium.policy.CombinedPolicyProvider$PolicyChangeListener, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    @SuppressLint({"NewApi"})
    /* renamed from: a0 */
    public void mo8078a0() {
        TraceEvent B = TraceEvent.m1469B("ChromeActivity.performPostInflationStartup");
        try {
            super.mo8078a0();
            this.f1599j4 = new ScrimView(this, mo8079a1().e, (ViewGroup) findViewById(ox0.coordinator));
            getIntent();
            this.f1594e4 = new SnackbarManager(this, (ViewGroup) null);
            if (Og0.d()) {
                this.f1604o4 = new sq2(this);
            } else {
                this.f1604o4 = new pq2(this);
            }
            this.f1611v4 = mo8122l0();
            if (this.f1611v4 != null) {
                if (this.f1569H3 != null) {
                    this.f1611v4.a(this.f1569H3);
                }
                this.f1611v4.b();
            }
            AccessibilityManager accessibilityManager = (AccessibilityManager) getBaseContext().getSystemService("accessibility");
            accessibilityManager.addAccessibilityStateChangeListener(this);
            this.f1585V3 = new Ss1(this);
            accessibilityManager.addTouchExplorationStateChangeListener(this.f1585V3);
            CombinedPolicyProvider.m3619a().mo9938a((CombinedPolicyProvider.PolicyChangeListener) this);
            this.f1615z4 = true;
            mo8772S().mo9995a((View) this.f1589Z3.t());
            mo8123l1();
            if (!this.f1584U3) {
                this.f1584U3 = true;
                mo8126m1();
            }
            if (!isFinishing() && mo8046P0() != null) {
                mo8046P0().a(findViewById(ox0.control_container), mo8095d1(), mo8033J0());
            }
            findViewById(ox0.bottom_container).a(this.f1587X3, this.f1610u4.a.d);
            if (!DeviceFormFactor.m3641c(this) && !(this instanceof CustomTabActivity)) {
                mo8118j1();
                mo8120k1();
            }
            if (this.f1562D4) {
                this.f1589Z3.F();
            }
            if (B != null) {
                m1631a((Throwable) null, B);
            }
        } catch (Throwable th) {
            if (B != null) {
                m1631a(th, B);
            }
            throw th;
        }
    }

    /* renamed from: a1 */
    public final StatusBarColorController mo8079a1() {
        if (this.f1600k4 == null) {
            this.f1600k4 = new StatusBarColorController(this);
        }
        return this.f1600k4;
    }

    public void addViewObscuringAllTabs(View view) {
        this.f1613x4.add(view);
        Tab v0 = mo8172v0();
        if (v0 != null) {
            v0.mo9412z0();
        }
    }

    /* renamed from: b */
    public void mo8082b() {
        Tab v0 = mo8172v0();
        if (v0 != null && !DualIdentityManager.f1776p) {
            mo8091c1().b(v0);
        }
        mo8050S0().a.r();
        UmaSessionStats umaSessionStats = this.f1576M3;
        if (umaSessionStats != null) {
            umaSessionStats.mo8841a();
        }
        sJ1 sj1 = rJ1.a;
        kx2 kx2 = sj1.a;
        if (kx2 != null) {
            kx2.destroy();
            sj1.a = null;
            sj1.b = null;
        }
        super.mo8082b();
        this.f1568G4 = true;
        for (int i = 0; i < this.f1570H4.size(); i++) {
            ActivityPausedListener activityPausedListener = (ActivityPausedListener) this.f1570H4.get(i).get();
            if (activityPausedListener != null) {
                activityPausedListener.onPaused();
            }
        }
        rU1 ru1 = this.f1566F4;
        if (ru1 != null) {
            ru1.c(mo8172v0());
        }
    }

    /* renamed from: b */
    public void mo8083b(BE1 be1) {
    }

    /* renamed from: b */
    public boolean mo8087b(Tab tab) {
        return false;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: b0 */
    public void mo8088b0() {
        this.f1561D3 = mo8060a(new ChromeActivityCommonsModule(this, mo8769O()));
        super.mo8088b0();
        mo8129o0();
        mo8106g1();
        this.f1577N3 = !PartnerBrowserCustomizations.f2139d;
        CommandLine c = CommandLine.m1384c();
        if (!c.mo7859c("disable-fullscreen")) {
            TypedValue typedValue = new TypedValue();
            getResources().getValue(kx0.top_controls_show_threshold, typedValue, true);
            c.mo7855a("top-controls-show-threshold", typedValue.coerceToString().toString());
            getResources().getValue(kx0.top_controls_hide_threshold, typedValue, true);
            c.mo7855a("top-controls-hide-threshold", typedValue.coerceToString().toString());
        }
        getWindow().setBackgroundDrawable(mo8016B0());
    }

    /* renamed from: b1 */
    public pq2 mo8089b1() {
        return this.f1604o4;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: c */
    public void mo8090c() {
        boolean z;
        Tab v0 = mo8172v0();
        if (!hasWindowFocus() && v0 != null) {
            v0.mo9390o0();
        }
        MY1 a = MY1.a(this);
        Boolean bool = a.b;
        if (bool != null) {
            z = bool.booleanValue();
        } else {
            a.b = false;
            PackageManager packageManager = a.a.getPackageManager();
            Intent intent = new Intent("com.google.android.googlequicksearchbox.TEXT_ASSIST");
            intent.setPackage("com.google.android.googlequicksearchbox");
            if (Vc0.a(packageManager, intent, 0).size() == 0) {
                a.b = false;
            } else if (!a.a("com.google.android.googlequicksearchbox", 300401021) || !a.a("com.google.android.gms", 6577010)) {
                a.b = false;
            } else {
                a.b = true;
            }
            z = a.b.booleanValue();
        }
        if (z && !SysUtils.isLowEndDevice()) {
            if (FY1.d == null) {
                FY1.d = new FY1(RN0.a);
            }
            FY1 fy1 = FY1.d;
            fy1.a--;
            LY1 ly1 = fy1.b;
            if (ly1 != null && fy1.a == 0) {
                ly1.a();
            }
        }
        if (this.f1586W3 != null) {
            ProfileSyncService M = ProfileSyncService.m2940M();
            if (M != null) {
                M.mo9250b(this.f1586W3);
            }
            this.f1586W3 = null;
        }
        ThreadUtils.m1458a(Us1.a, AppLogArchiver.ROLLOVER_TIME_DIFFERENCE);
        super.mo8090c();
    }

    /* renamed from: c1 */
    public TabContentManager mo8091c1() {
        return this.f1575L3;
    }

    public void createContextualSearchTab(String str) {
        Lw2.a b;
        Tab v0 = mo8172v0();
        if (v0 != null && (b = mo8081b(v0.mo9315X())) != null) {
            b.a(new LoadUrlParams(str, 0), 0, mo8172v0());
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, java.lang.Object, android.app.Activity] */
    /* renamed from: d */
    public void mo8093d() {
        Kg0 kg0;
        super.mo8093d();
        mo8015A1();
        boolean z = this instanceof ChromeTabbedActivity;
        if (z && rJ1.a.e()) {
            rJ1.a.a(mo8095d1(), this);
            if (rJ1.a.a()) {
                CJ1.c().a(this);
            }
        }
        Tab v0 = mo8172v0();
        if (v0 != null) {
            WebContents K = v0.mo9302K();
            for (LaunchMetrics.a aVar : LaunchMetrics.a) {
                LaunchMetrics.nativeRecordLaunch(aVar.b, aVar.a, aVar.c, aVar.d, K);
            }
            LaunchMetrics.a.clear();
            FO0.a();
            if (K != null) {
                K.mo9754Y();
            }
        }
        FeatureUtilities.nativeSetCustomTabVisible(mo8148p1());
        FeatureUtilities.nativeSetIsInMultiWindowMode(gb2.c.c(this));
        v62 v62 = this.f1588Y3;
        if (v62 != null) {
            v62.a(this, 0);
        }
        H32 h32 = mo8050S0().a;
        if (h32.q()) {
            h32.r();
            h32.s();
        }
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            DualIdentityManager.m2206a(false);
        }
        if (MicrosoftSigninManager.C0424c.f2104a.mo8931x()) {
            Hu0.a(this, MicrosoftSigninManager.C0424c.f2104a.mo8912i());
        }
        qU1.c.a(this);
        rU1 ru1 = this.f1566F4;
        if (ru1 != null) {
            ru1.a = this;
        }
        this.f1568G4 = false;
        f1555L4 = new WeakReference<>(this);
        if (z) {
            f1556M4 = new WeakReference<>(this);
        }
        if (Og0.d() && (kg0 = this.f1559C3) != null) {
            ks0.a = kg0.d;
        }
    }

    /* renamed from: d1 */
    public TabModelSelector mo8095d1() {
        if (this.f1581R3) {
            return this.f1569H3;
        }
        throw new IllegalStateException("Attempting to access TabModelSelector before initialization");
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        mo8106g1();
        return keyEvent == null || ChromeActivity.super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: e */
    public void mo8098e(boolean z) {
        ViewGroup viewGroup = (ViewGroup) findViewById(ox0.coordinator);
        getLayoutInflater().inflate(rx0.bottom_sheet, viewGroup);
        this.f1598i4 = viewGroup.findViewById(ox0.bottom_sheet);
        this.f1598i4.a(viewGroup, this);
        findViewById(ox0.bottom_container).setBottomSheet(this.f1598i4);
        this.f1596g4 = new jI2(this, mo8769O(), this.f1558B4, this.f1599j4, this.f1598i4, mo8026G0().y().z3, z);
    }

    /* renamed from: e1 */
    public int mo8099e1() {
        return -1;
    }

    /* renamed from: f */
    public void mo8102f(boolean z) {
        TabSwitcherModeTTPhone tabSwitcherModeTTPhone;
        InfoBarContainerView.setIsAllowedToAutoHide(!z);
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null) {
            TopToolbarCoordinator topToolbarCoordinator = toolbarManager.f2309e;
            topToolbarCoordinator.a.mo9541f(z);
            FC2 fc2 = topToolbarCoordinator.b;
            if (!(fc2 == null || (tabSwitcherModeTTPhone = fc2.a) == null)) {
                tabSwitcherModeTTPhone.a(z);
            }
        }
        ContextualSearchManager contextualSearchManager = this.f1592c4;
        if (contextualSearchManager != null) {
            contextualSearchManager.L();
        }
        xh2 xh2 = this.f1602m4;
        if (xh2 != null) {
            xh2.a(true);
        }
    }

    /* renamed from: f1 */
    public ToolbarManager mo8103f1() {
        if (mo8774V() && !this.f1584U3) {
            this.f1584U3 = true;
            mo8126m1();
        }
        return this.f1595f4;
    }

    /* renamed from: g */
    public void mo8104g(boolean z) {
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0061, code lost:
        if (r0 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        m1631a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0066, code lost:
        throw r2;
     */
    /* renamed from: g0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8105g0() {
        /*
            r4 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            r4.f1605p4 = r0
            java.lang.String r0 = "ChromeActivity.triggerLayoutInflation"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            r1 = 1
            org.chromium.content.browser.selection.SelectionPopupControllerImpl.K3 = r1     // Catch:{ all -> 0x005e }
            boolean r2 = org.chromium.base.SysUtils.isLowEndDevice()     // Catch:{ all -> 0x005e }
            if (r2 != 0) goto L_0x0020
            android.view.Window r2 = r4.getWindow()     // Catch:{ all -> 0x005e }
            r3 = 16777216(0x1000000, float:2.3509887E-38)
            r2.addFlags(r3)     // Catch:{ all -> 0x005e }
            r4.f1614y4 = r1     // Catch:{ all -> 0x005e }
        L_0x0020:
            int r1 = m1627O1()     // Catch:{ all -> 0x005e }
            int r2 = xx0.Theme_Chromium_WithWindowAnimation_LowEnd     // Catch:{ all -> 0x005e }
            if (r1 != r2) goto L_0x002b
            r4.setTheme(r2)     // Catch:{ all -> 0x005e }
        L_0x002b:
            org.chromium.chrome.browser.WarmupManager r1 = org.chromium.chrome.browser.WarmupManager.m1950e()     // Catch:{ all -> 0x005e }
            int r2 = r4.mo8035K0()     // Catch:{ all -> 0x005e }
            boolean r2 = r1.mo8307a((int) r2)     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x0051
            android.view.View r2 = new android.view.View     // Catch:{ all -> 0x005e }
            r2.<init>(r4)     // Catch:{ all -> 0x005e }
            r4.setContentView(r2)     // Catch:{ all -> 0x005e }
            android.view.ViewParent r3 = r2.getParent()     // Catch:{ all -> 0x005e }
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3     // Catch:{ all -> 0x005e }
            r1.mo8303a((android.view.ViewGroup) r3)     // Catch:{ all -> 0x005e }
            r3.removeView(r2)     // Catch:{ all -> 0x005e }
            r4.mo8058Z()     // Catch:{ all -> 0x005e }
            goto L_0x0057
        L_0x0051:
            r1.mo8301a()     // Catch:{ all -> 0x005e }
            r4.mo8157s0()     // Catch:{ all -> 0x005e }
        L_0x0057:
            if (r0 == 0) goto L_0x005d
            r1 = 0
            m1631a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x005d:
            return
        L_0x005e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r2 = move-exception
            if (r0 == 0) goto L_0x0066
            m1631a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0066:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8105g0():void");
    }

    /* renamed from: g1 */
    public void mo8106g1() {
        if (FeatureUtilities.isNoTouchModeEnabled()) {
            AppHooks.get().mo7974a(this);
        }
    }

    public int getBaseStatusBarColor() {
        return 0;
    }

    public SnackbarManager getSnackbarManager() {
        mo8106g1();
        jI2 ji2 = this.f1596g4;
        if (ji2 != null && ji2.b.x() && !this.f1596g4.b.u()) {
            return this.f1596g4.c;
        }
        return this.f1594e4;
    }

    /* renamed from: h */
    public void mo8109h(boolean z) {
    }

    /* renamed from: h0 */
    public boolean mo8110h0() {
        return this.f1581R3;
    }

    /* renamed from: h1 */
    public abstract boolean mo8111h1();

    /* renamed from: i */
    public void mo8112i(boolean z) {
        CompositorViewHolder compositorViewHolder = this.f1589Z3;
        if (compositorViewHolder != null) {
            compositorViewHolder.setOverlayMode(z);
        }
    }

    /* renamed from: i0 */
    public boolean mo8113i0() {
        return false;
    }

    /* renamed from: i1 */
    public void mo8114i1() {
        ou1.a.a(new Vs1(this));
        ou1.a.a(new Ws1(this));
        ou1.a.a(new Xs1(this, getClass().getSimpleName()));
    }

    public boolean isStatusBarDefaultThemeColor() {
        return false;
    }

    /* renamed from: j */
    public final void mo8116j(boolean z) {
        String str;
        String str2;
        Tab v0 = mo8172v0();
        if (!MAMEdgeManager.m1310h()) {
            z = false;
        }
        if (v0 != null) {
            str = v0.getUrl();
            str2 = ss0.b(v0.getId());
        } else {
            str2 = BuildConfig.FLAVOR;
            str = str2;
        }
        HT1 ht1 = new HT1(this, str, str2);
        if (!z) {
            ht1.a(BuildConfig.FLAVOR);
            return;
        }
        ChromeActivity chromeActivity = ht1.a;
        FT1 ft1 = new FT1(ht1);
        if (chromeActivity == null) {
            ft1.onGotBitmap((Bitmap) null);
            return;
        }
        Tab v02 = chromeActivity.mo8172v0();
        if (v02 == null) {
            ft1.onGotBitmap((Bitmap) null);
        } else if (v02.isNativePage()) {
            TabContentManager c1 = chromeActivity.mo8091c1();
            if (c1 == null) {
                ft1.onGotBitmap((Bitmap) null);
            } else {
                c1.a(v02, 1.0f, new zV1(ft1, chromeActivity));
            }
        } else if (v02.mo9301J() == null) {
            ft1.onGotBitmap((Bitmap) null);
        } else {
            WebContents K = v02.mo9302K();
            if (K == null) {
                ft1.onGotBitmap((Bitmap) null);
            } else {
                ShareHelper.a(K, 0, 0, new yV1(chromeActivity, ft1));
            }
        }
    }

    /* renamed from: j0 */
    public final void mo8117j0() {
        mo8102f(EE2.a());
    }

    /* renamed from: j1 */
    public void mo8118j1() {
        Gv1 gv1 = mo8103f1().f2308d4;
        this.f1601l4 = new fz1(this, mo8772S());
    }

    /* renamed from: k0 */
    public void mo8119k0() {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* renamed from: k1 */
    public void mo8120k1() {
        this.f1602m4 = new xh2(this);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: l */
    public Lv1 mo8121l() {
        return new Nv1(this, mo8174w0(), mo8770P(), mo8095d1(), mo8103f1(), getWindow().getDecorView(), (YN0) null);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: l0 */
    public Fs1 mo8122l0() {
        return new Fs1(this);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, java.lang.Object, android.app.Activity] */
    /* renamed from: l1 */
    public final void mo8123l1() {
        if (!this.f1581R3) {
            this.f1569H3 = mo8150q0();
            this.f1563E3.a(this.f1569H3);
            this.f1560C4 = new Uu1(this);
            StatusBarColorController a1 = mo8079a1();
            a1.q3 = this.f1569H3;
            Yw2 yw2 = a1.q3;
            if (yw2 != null) {
                yw2.a(a1.n);
            }
            TabModelSelector tabModelSelector = this.f1569H3;
            if (tabModelSelector == null) {
                this.f1581R3 = true;
                return;
            }
            ActivityTabProvider activityTabProvider = this.f1558B4;
            activityTabProvider.f = tabModelSelector;
            activityTabProvider.g = new is1(activityTabProvider, activityTabProvider.f);
            this.f1560C4.a(this.f1558B4);
            Pair<? extends Lw2.a, ? extends Lw2.a> p0 = mo8147p0();
            this.f1573J3 = (Lw2.a) p0.first;
            this.f1574K3 = (Lw2.a) p0.second;
            zd2 zd2 = (zd2) Bd2.b.put(this, new zd2(this.f1569H3));
            if (zd2 != null) {
                zd2.destroy();
            } else {
                ApplicationStatus.m1364a((ApplicationStatus.ActivityStateListener) new vd2(), (Activity) this);
            }
            kx2 kx2 = this.f1571I3;
            if (kx2 != null) {
                kx2.destroy();
            }
            this.f1571I3 = new a(this, this.f1569H3);
            Fs1 fs1 = this.f1611v4;
            if (fs1 != null) {
                fs1.a(this.f1569H3);
            }
            this.f1581R3 = true;
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.contextualsearch.ContextualSearchManager$ContextualSearchTabPromotionDelegate, org.chromium.chrome.browser.init.AsyncInitializationActivity] */
    /* renamed from: m */
    public void mo8124m() {
        TraceEvent.m1472c("ChromeActivity:CompositorInitialization", (String) null);
        super.mo8124m();
        TabContentManager tabContentManager = new TabContentManager(this, mo8028H0(), BM1.f().a);
        this.f1575L3 = tabContentManager;
        new AF1(tabContentManager, mo8095d1());
        this.f1589Z3.a(mo8772S(), mo8091c1());
        if (mo8130o1() && WH1.a()) {
            Yx1.e();
            this.f1592c4 = new ContextualSearchManager(this, this);
        }
        this.f1593d4 = new ZM1(mo8095d1(), this);
        TraceEvent.m1475z("ChromeActivity:CompositorInitialization");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: m0 */
    public ChromeFullscreenManager mo8125m0() {
        return new ChromeFullscreenManager(this, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        m1631a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r0 != null) goto L_0x0037;
     */
    /* renamed from: m1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8126m1() {
        /*
            r9 = this;
            java.lang.String r0 = "ChromeActivity.initializeToolbar"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            int r1 = ox0.control_container     // Catch:{ all -> 0x0032 }
            android.view.View r1 = r9.findViewById(r1)     // Catch:{ all -> 0x0032 }
            r4 = r1
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer r4 = (org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer) r4     // Catch:{ all -> 0x0032 }
            Ts1 r6 = new Ts1     // Catch:{ all -> 0x0032 }
            r6.<init>(r9)     // Catch:{ all -> 0x0032 }
            org.chromium.chrome.browser.toolbar.ToolbarManager r1 = new org.chromium.chrome.browser.toolbar.ToolbarManager     // Catch:{ all -> 0x0032 }
            org.chromium.chrome.browser.compositor.CompositorViewHolder r2 = r9.mo8026G0()     // Catch:{ all -> 0x0032 }
            org.chromium.chrome.browser.compositor.Invalidator r5 = r2.x()     // Catch:{ all -> 0x0032 }
            Uu1 r7 = r9.f1560C4     // Catch:{ all -> 0x0032 }
            boolean r8 = r9.mo4507E()     // Catch:{ all -> 0x0032 }
            r2 = r1
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0032 }
            r9.f1595f4 = r1     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0031
            r1 = 0
            m1631a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0031:
            return
        L_0x0032:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            if (r0 == 0) goto L_0x003a
            m1631a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x003a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8126m1():void");
    }

    /* renamed from: n0 */
    public IntentHandler.IntentHandlerDelegate mo8127n0() {
        return new c(this);
    }

    /* renamed from: n1 */
    public boolean mo8128n1() {
        return this.f1568G4;
    }

    /* renamed from: o0 */
    public hD2 mo8129o0() {
        return new hD2(this);
    }

    /* renamed from: o1 */
    public boolean mo8130o1() {
        return true;
    }

    public final void onAccessibilityStateChanged(boolean z) {
        EE2.b();
        mo8117j0();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void onAttachedToWindow() {
        ChromeActivity.super.onAttachedToWindow();
        if (this.f1614y4) {
            this.f1614y4 = false;
            getWindow().setWindowManager(getWindow().getWindowManager(), getWindow().getAttributes().token, getComponentName().flattenToString(), true);
        }
    }

    public final void onBackPressed() {
        IE1 y;
        boolean z = this.f1582S3;
        tJ2.d();
        xh2 xh2 = this.f1602m4;
        boolean z2 = true;
        if (xh2 == null || !xh2.y) {
            CompositorViewHolder compositorViewHolder = this.f1589Z3;
            if (!(compositorViewHolder == null || (y = compositorViewHolder.y()) == null)) {
                BE1 be1 = y.q3;
                if (be1 == null || !be1.r()) {
                    z2 = false;
                }
                if (z2) {
                    return;
                }
            }
            WebContents N0 = mo8043N0();
            SelectionPopupControllerImpl a = N0 != null ? SelectionPopupControllerImpl.a(N0) : null;
            if (a != null && a.g()) {
                a.f();
            } else if (!mo8111h1()) {
                ChromeActivity.super.onBackPressed();
            }
        } else {
            xh2.a(true);
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConfigurationChanged(android.content.res.Configuration r6) {
        /*
            r5 = this;
            super.onConfigurationChanged(r6)
            int r0 = r6.uiMode
            r5.f1607r4 = r0
            int r1 = r5.f1607r4
            r2 = 1
            r3 = 0
            if (r1 != r0) goto L_0x000e
            goto L_0x0021
        L_0x000e:
            r1 = r1 & 15
            r4 = 7
            if (r1 != r4) goto L_0x0015
            r1 = 1
            goto L_0x0016
        L_0x0015:
            r1 = 0
        L_0x0016:
            r0 = r0 & 15
            if (r0 != r4) goto L_0x001c
            r0 = 1
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            if (r1 != r0) goto L_0x0021
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 == 0) goto L_0x0036
            int r0 = r5.f1607r4
            int r1 = r6.uiMode
            r0 = r0 & 48
            r1 = r1 & 48
            if (r0 == r1) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r2 = 0
        L_0x0030:
            if (r2 != 0) goto L_0x0036
            r5.recreate()
            return
        L_0x0036:
            int r0 = r6.densityDpi
            int r1 = r5.f1608s4
            if (r0 == r1) goto L_0x003e
            r5.f1608s4 = r0
        L_0x003e:
            org.chromium.chrome.browser.newsguard.NewsGuardManager r0 = vb2.a
            Rb2 r0 = r0.f2122g
            if (r0 == 0) goto L_0x0047
            r0.update()
        L_0x0047:
            xh2 r0 = r5.f1602m4
            if (r0 == 0) goto L_0x005b
            boolean r1 = r0.t3
            if (r1 != 0) goto L_0x0050
            goto L_0x005b
        L_0x0050:
            r0.a(r3)
            int r1 = r0.r3
            int r6 = r6.orientation
            if (r1 == r6) goto L_0x005b
            r0.r3 = r6
        L_0x005b:
            boolean r6 = Og0.d()
            if (r6 == 0) goto L_0x006e
            Kg0 r6 = r5.f1559C3
            if (r6 == 0) goto L_0x006e
            r6.b(r5)
            Kg0 r6 = r5.f1559C3
            int r6 = r6.d
            ks0.a = r6
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.onConfigurationChanged(android.content.res.Configuration):void");
    }

    @TargetApi(29)
    public void onGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.f1612w4 != null) {
            throw null;
        }
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [org.chromium.chrome.browser.ChromeActivity, android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener, org.chromium.policy.CombinedPolicyProvider$PolicyChangeListener, org.chromium.chrome.browser.init.AsyncInitializationActivity, java.lang.Object, android.app.Activity] */
    @SuppressLint({"NewApi"})
    public final void onMAMDestroy() {
        TabModelSelector d1;
        Tab g;
        DualIdentityUtils.m2248c("ChromeActivity", "OnDestroy");
        ZM1 zm1 = this.f1593d4;
        if (zm1 != null) {
            zm1.destroy();
            this.f1593d4 = null;
        }
        ContextualSearchManager contextualSearchManager = this.f1592c4;
        if (contextualSearchManager != null) {
            contextualSearchManager.v();
            this.f1592c4 = null;
        }
        kx2 kx2 = this.f1571I3;
        if (kx2 != null) {
            kx2.destroy();
            this.f1571I3 = null;
        }
        CompositorViewHolder compositorViewHolder = this.f1589Z3;
        if (compositorViewHolder != null) {
            if (compositorViewHolder.y() != null) {
                this.f1589Z3.y().y.mo7869b(this);
            }
            this.f1589Z3.J();
            this.f1589Z3 = null;
        }
        mo8021D1();
        fz1 fz1 = this.f1601l4;
        if (fz1 != null) {
            if (fz1.c != null && (fz1.a.getContext() instanceof Ly1)) {
                BottomBarHelper h = fz1.a.getContext().h();
                h.a.mo7869b(fz1.c);
                fz1.c = null;
            }
            Yw2 yw2 = fz1.d;
            if (!(yw2 == null || (g = yw2.g()) == null)) {
                g.mo9340b(fz1.g);
            }
            this.f1601l4 = null;
        }
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null) {
            if (toolbarManager.f2299Y3) {
                GI2 gi2 = toolbarManager.f2318u3;
                gi2.f.mo7869b(toolbarManager.f2276B3);
            }
            Yw2 yw22 = toolbarManager.f2312p;
            if (yw22 != null) {
                yw22.d.mo7869b(toolbarManager.f2313q);
                for (TabModel a : toolbarManager.f2312p.a) {
                    a.mo9421a(toolbarManager.f2321x);
                }
            }
            BookmarkBridge bookmarkBridge = toolbarManager.f2315r3;
            if (bookmarkBridge != null) {
                bookmarkBridge.a();
                toolbarManager.f2315r3 = null;
            }
            if (toolbarManager.f2316s3 != null) {
                TemplateUrlServiceFactory.m2927a().mo9624b(toolbarManager.f2316s3);
                toolbarManager.f2316s3 = null;
            }
            OverviewModeBehavior overviewModeBehavior = toolbarManager.f2320w3;
            if (overviewModeBehavior != null) {
                overviewModeBehavior.a(toolbarManager.f2277C3);
                toolbarManager.f2320w3 = null;
            }
            IE1 ie1 = toolbarManager.f2322x3;
            if (ie1 != null) {
                ie1.y.mo7869b(toolbarManager.f2278D3);
                toolbarManager.f2322x3 = null;
            }
            BottomControlsCoordinator bottomControlsCoordinator = toolbarManager.f2311n;
            if (bottomControlsCoordinator != null) {
                hC2 hc2 = bottomControlsCoordinator.b;
                if (hc2 != null) {
                    kC2 kc2 = hc2.a;
                    mC2 mc2 = kc2.a;
                    OverviewModeBehavior overviewModeBehavior2 = mc2.b;
                    if (overviewModeBehavior2 != null) {
                        overviewModeBehavior2.a(mc2);
                        mc2.b = null;
                    }
                    ThemeColorProvider themeColorProvider = mc2.c;
                    if (themeColorProvider != null) {
                        themeColorProvider.e.mo7869b(mc2);
                        mc2.c = null;
                    }
                    kc2.b.a();
                    kc2.c.a();
                    kc2.d.a();
                    nB2 nb2 = kc2.e;
                    ThemeColorProvider themeColorProvider2 = nb2.b;
                    if (themeColorProvider2 != null) {
                        themeColorProvider2.k.mo7869b(nb2.c);
                        nb2.b = null;
                    }
                    TabCountProvider tabCountProvider = nb2.d;
                    if (tabCountProvider != null) {
                        tabCountProvider.a.mo7869b(nb2.e);
                        nb2.d = null;
                    }
                    kc2.f.a();
                    pC2 pc2 = hc2.b;
                    if (pc2 != null) {
                        qC2 qc2 = pc2.a;
                        OverviewModeBehavior overviewModeBehavior3 = qc2.c;
                        if (overviewModeBehavior3 != null) {
                            overviewModeBehavior3.a(qc2);
                        }
                        ThemeColorProvider themeColorProvider3 = qc2.b;
                        if (themeColorProvider3 != null) {
                            themeColorProvider3.e.mo7869b(qc2);
                        }
                        pc2.b.a();
                        pc2.c.a();
                        pc2.d.a();
                        hc2.b = null;
                    }
                    hc2.d.a();
                }
                yz2 yz2 = bottomControlsCoordinator.c;
                if (yz2 != null) {
                    yz2 yz22 = yz2;
                    if (yz22.x != null) {
                        yz22.k.destroy();
                        cz2 cz2 = yz22.d;
                        if (cz2 != null) {
                            cz2.b.destroy();
                            kz2 kz2 = cz2.c;
                            Tw2 tw2 = kz2.f;
                            if (tw2 != null) {
                                kz2.e.b.b(tw2);
                            }
                            ThemeColorProvider themeColorProvider4 = kz2.h;
                            themeColorProvider4.e.mo7869b(kz2.i);
                            ThemeColorProvider themeColorProvider5 = kz2.h;
                            themeColorProvider5.k.mo7869b(kz2.j);
                            Zy2 zy2 = cz2.d;
                            nz2 nz2 = cz2.e;
                            if (nz2 != null) {
                                nz2.b.a();
                            }
                        }
                        ty2 ty2 = yz22.e;
                        if (ty2 != null) {
                            ty2.a();
                        }
                        Jz2 jz2 = yz22.n;
                        Yw2 yw23 = jz2.d;
                        if (yw23 != null) {
                            yw23.b.b(jz2.b);
                            jz2.d.b.a(false).d.mo7869b(jz2.p);
                            jz2.d.b.a(true).d.mo7869b(jz2.p);
                            Yw2 yw24 = jz2.d;
                            yw24.d.mo7869b(jz2.m);
                        }
                        jz2.f.a(jz2.g);
                        ThemeColorProvider themeColorProvider6 = jz2.i;
                        themeColorProvider6.e.mo7869b(jz2.j);
                        ThemeColorProvider themeColorProvider7 = jz2.i;
                        themeColorProvider7.k.mo7869b(jz2.k);
                        jz2.l.destroy();
                        yz22.q.b(yz22);
                    }
                }
                eC2 ec2 = bottomControlsCoordinator.a;
                ec2.b.D3.remove(ec2);
                WindowAndroid windowAndroid = ec2.k;
                if (windowAndroid != null) {
                    windowAndroid.mo10019f().mo9947b((KeyboardVisibilityDelegate.KeyboardVisibilityListener) ec2);
                    ec2.k = null;
                }
                if (ec2.a.a(fC2.f) != null) {
                    IE1 ie12 = (IE1) ec2.a.a(fC2.f);
                    ie12.z3.b.mo7869b(ec2);
                    ie12.y.mo7869b(ec2);
                }
                toolbarManager.f2311n = null;
            }
            b82 b82 = toolbarManager.f2304b4;
            if (b82 != null) {
                if (toolbarManager.f2299Y3) {
                    b82.a();
                }
                b82 b822 = toolbarManager.f2304b4;
                kx2 kx22 = b822.e;
                if (kx22 != null) {
                    kx22.destroy();
                    b822.e = null;
                }
                PageLoadMetrics.Observer observer = b822.f;
                if (observer != null) {
                    PageLoadMetrics.b(observer);
                    b822.f = null;
                }
                toolbarManager.f2304b4 = null;
            }
            Re2 re2 = toolbarManager.f2317t3;
            if (re2 != null) {
                re2.b(toolbarManager);
            }
            TopToolbarCoordinator topToolbarCoordinator = toolbarManager.f2309e;
            topToolbarCoordinator.a.mo9531b((TopToolbarCoordinator.UrlExpansionObserver) toolbarManager.f2285K3.mo8079a1());
            toolbarManager.f2309e.a();
            if (toolbarManager.f2325z3 != null) {
                Tab a2 = toolbarManager.f2323y.a();
                if (a2 != null) {
                    a2.mo9340b(toolbarManager.f2325z3);
                }
                toolbarManager.f2325z3 = null;
            }
            toolbarManager.f2296V3 = true;
            IncognitoStateProvider incognitoStateProvider = toolbarManager.f2301a;
            Yw2 yw25 = incognitoStateProvider.c;
            if (yw25 != null) {
                yw25.d.mo7869b(incognitoStateProvider.b);
                incognitoStateProvider.c = null;
            }
            incognitoStateProvider.a.clear();
            TabCountProvider tabCountProvider2 = toolbarManager.f2303b;
            Tw2 tw22 = tabCountProvider2.d;
            if (tw22 != null) {
                tabCountProvider2.b.b.b(tw22);
            }
            if (tabCountProvider2.e != null) {
                tabCountProvider2.b.b.a().d.mo7869b(tabCountProvider2.e);
            }
            Yw2 yw26 = tabCountProvider2.b;
            if (yw26 != null) {
                yw26.d.mo7869b(tabCountProvider2.c);
                tabCountProvider2.b = null;
            }
            tabCountProvider2.a.clear();
            eB2 eb2 = toolbarManager.f2324y3;
            I12 i12 = eb2.c;
            if (i12 != null) {
                i12.b(eb2);
                eb2.c = null;
            }
            ProfileDataCache profileDataCache = eb2.k;
            if (profileDataCache != null) {
                profileDataCache.b(eb2);
                eb2.k = null;
            }
            SigninManager signinManager = eb2.d;
            if (signinManager != null) {
                signinManager.b(eb2);
                eb2.d = null;
            }
            ProfileSyncService profileSyncService = eb2.e;
            if (profileSyncService != null) {
                profileSyncService.mo9250b((ProfileSyncService.SyncStateChangedListener) eb2);
                eb2.e = null;
            }
            toolbarManager.f2323y.s();
            toolbarManager.f2284J3.removeCallbacksAndMessages((Object) null);
            Re2 re22 = toolbarManager.f2317t3;
            if (re22 != null) {
                re22.b(toolbarManager.f2286L3);
                toolbarManager.f2286L3 = null;
            }
            ThemeColorProvider themeColorProvider8 = toolbarManager.f2305c;
            if (themeColorProvider8 != null) {
                themeColorProvider8.e.mo7869b(toolbarManager);
            }
            dB2 db2 = toolbarManager.f2307d;
            if (db2 != null) {
                db2.a();
            }
            this.f1595f4 = null;
        }
        pq2 pq2 = this.f1604o4;
        if (pq2 != null) {
            pq2.a();
            this.f1604o4 = null;
        }
        BottomSheet bottomSheet = this.f1598i4;
        if (bottomSheet != null) {
            bottomSheet.d();
            this.f1598i4 = null;
        }
        if (this.f1615z4) {
            CombinedPolicyProvider.m3619a().mo9939b(this);
            this.f1615z4 = false;
        }
        TabContentManager tabContentManager = this.f1575L3;
        if (tabContentManager != null) {
            tabContentManager.a();
            this.f1575L3 = null;
        }
        H32 h32 = this.f1610u4.a;
        if (h32.q()) {
            h32.r();
            h32.q.findViewById(16908290).removeOnLayoutChangeListener(h32);
            h32.x.destroy();
            Q32 q32 = h32.e;
            for (P32 a3 : q32.a.values()) {
                a3.a();
            }
            q32.a.clear();
            Iterator it = h32.k.iterator();
            while (it.hasNext()) {
                ((Tab) it.next()).mo9340b(h32.r3);
            }
            h32.k.clear();
            IE1 m = h32.m();
            if (m != null) {
                m.y.mo7869b(h32.q3);
            }
            h32.b = null;
            h32.q = null;
        }
        R72 r72 = this.f1557A4;
        if (r72 != null) {
            r72.a();
            this.f1557A4 = null;
        }
        ChromeFullscreenManager chromeFullscreenManager = this.f1587X3;
        if (chromeFullscreenManager != null) {
            chromeFullscreenManager.a((Tab) null);
            chromeFullscreenManager.d.b.removeCallbacksAndMessages((Object) null);
            kx2 kx23 = chromeFullscreenManager.p;
            if (kx23 != null) {
                kx23.destroy();
            }
            ContentView contentView = chromeFullscreenManager.C3;
            if (contentView != null) {
                contentView.b(chromeFullscreenManager);
                chromeFullscreenManager.C3.b(chromeFullscreenManager);
            }
            VrModuleProvider.f2350d.remove(chromeFullscreenManager);
            this.f1587X3 = null;
        }
        if (this.f1581R3 && (d1 = mo8095d1()) != null) {
            d1.destroy();
        }
        ge2 c = ge2.c();
        c.a.mo7869b(this.f1564E4);
        AccessibilityManager accessibilityManager = (AccessibilityManager) getBaseContext().getSystemService("accessibility");
        accessibilityManager.removeAccessibilityStateChangeListener(this);
        accessibilityManager.removeTouchExplorationStateChangeListener(this.f1585V3);
        Uu1 uu1 = this.f1560C4;
        if (uu1 != null) {
            uu1.a();
            this.f1560C4 = null;
        }
        ActivityTabProvider activityTabProvider = this.f1558B4;
        activityTabProvider.a.clear();
        IE1 ie13 = activityTabProvider.d;
        if (ie13 != null) {
            ie13.y.mo7869b(activityTabProvider.e);
        }
        activityTabProvider.d = null;
        hx2 hx2 = activityTabProvider.g;
        if (hx2 != null) {
            hx2.destroy();
        }
        activityTabProvider.f = null;
        this.f1561D3 = null;
        f1553J4 = false;
        super.onMAMDestroy();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void onMAMIdentitySwitchRequired(String str, AppIdentitySwitchReason appIdentitySwitchReason, AppIdentitySwitchResultCallback appIdentitySwitchResultCallback) {
        DualIdentityManager.m2197a(this, str, appIdentitySwitchReason, appIdentitySwitchResultCallback);
    }

    public void onMAMNewIntent(Intent intent) {
        DualIdentityUtils.m2248c("ChromeActivity", "onMAMNewIntent " + intent);
        super.onMAMNewIntent(intent);
    }

    @TargetApi(23)
    public void onMAMProvideAssistContent(AssistContent assistContent) {
        Tab v0;
        if (mo8014A0() != null && mo8014A0().a() && (v0 = mo8172v0()) != null && !mo8151q1()) {
            assistContent.setWebUri(Uri.parse(v0.getUrl()));
        }
    }

    public void onMAMUserLeaveHint() {
        ChromeActivity.super.onMAMUserLeaveHint();
        if (this.f1588Y3 == null) {
            this.f1588Y3 = new v62();
        }
        this.f1588Y3.a(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onMultiWindowModeChanged(boolean z) {
        if (this.f1582S3) {
            mo8109h(z);
            if (!z && ApplicationStatus.m1359a((Activity) this) == 3) {
                UmaSessionStats umaSessionStats = this.f1576M3;
                if (umaSessionStats != null) {
                    umaSessionStats.mo8841a();
                }
                mo8015A1();
                FeatureUtilities.nativeSetIsInMultiWindowMode(gb2.c.c(this));
            }
        }
        super.onMultiWindowModeChanged(z);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        mo8066a(menuItem);
        if (menuItem == null || !mo8076a(menuItem.getItemId(), (Bundle) null)) {
            return ChromeActivity.super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @TargetApi(29)
    public void onPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer<Bundle> consumer) {
        if (this.f1612w4 != null) {
            throw null;
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onStart() {
        if (Bw2.a()) {
            this.f1589Z3.I();
        }
        super.onStart();
        if (this.f1577N3) {
            this.f1577N3 = false;
            PartnerBrowserCustomizations.m2703a(getApplicationContext(), 10000);
            Ys1 ys1 = new Ys1(this);
            if (PartnerBrowserCustomizations.f2139d) {
                PostTask.m1566a(iQ2.a, ys1, 0);
            } else {
                PartnerBrowserCustomizations.f2140e.add(ys1);
            }
        }
        CompositorViewHolder compositorViewHolder = this.f1589Z3;
        if (compositorViewHolder != null) {
            compositorViewHolder.F();
        }
        mo8117j0();
        Configuration configuration = getResources().getConfiguration();
        this.f1607r4 = configuration.uiMode;
        this.f1608s4 = configuration.densityDpi;
        this.f1562D4 = true;
        if (!this.f1565F3) {
            this.f1565F3 = true;
            new g((a) null).a(Us0.d);
            gh0.a(false);
            ServerConfigManager.h().a();
            new i((a) null).a(Us0.d);
            Us0.c.execute(new Zs1(this));
        }
        if (this.f1567G3 == 0) {
            ao0.a(RN0.a);
            rZ.f().a(getApplication(), "1a733320-bd21-4f68-a547-3288fd5a1700", new Class[]{Crashes.class});
            rZ.f().b(ss0.d());
            this.f1567G3 = System.currentTimeMillis();
        }
    }

    public void onStop() {
        super.onStop();
        this.f1577N3 = true;
        CompositorViewHolder compositorViewHolder = this.f1589Z3;
        if (compositorViewHolder != null) {
            compositorViewHolder.G();
        }
        this.f1562D4 = false;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void onSwitchMAMIdentityComplete(MAMIdentitySwitchResult mAMIdentitySwitchResult) {
        DualIdentityManager.m2201a(mAMIdentitySwitchResult, (Activity) this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onTrimMemory(int i) {
        ChromeActivity.super.onTrimMemory(i);
        if (ChromeApplication.m1773b(i)) {
            this.f1609t4.a();
            NG2 findViewById = findViewById(ox0.control_container);
            if (findViewById != null) {
                findViewById.a().c = null;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        mo8017B1();
        if (!this.f1581R3) {
            Log.e("ChromeActivity", "TabModels still not initialized");
            return;
        }
        Tab v0 = mo8172v0();
        if (!z) {
            if ((ApplicationStatus.m1359a((Activity) this) == 5) && v0 != null) {
                v0.mo9390o0();
            }
        } else if (v0 != null) {
            v0.mo9392p0();
            TabBrowserControlsState.c(v0);
        }
        Clipboard.getInstance().a(z);
    }

    /* renamed from: p0 */
    public abstract Pair<? extends Lw2.a, ? extends Lw2.a> mo8147p0();

    /* renamed from: p1 */
    public boolean mo8148p1() {
        return mo8178y0() == 2;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: q */
    public boolean mo8149q() {
        if (mo8779a()) {
            return false;
        }
        int a = ApplicationStatus.m1359a((Activity) this);
        boolean c = gb2.c.c(this);
        if (a == 3) {
            return true;
        }
        if (!c || a != 4) {
            return false;
        }
        return true;
    }

    /* renamed from: q0 */
    public abstract TabModelSelector mo8150q0();

    /* renamed from: q1 */
    public boolean mo8151q1() {
        return false;
    }

    /* renamed from: r */
    public Kg0 mo8152r() {
        return this.f1559C3;
    }

    /* renamed from: r0 */
    public boolean mo8153r0() {
        return this.f1582S3;
    }

    /* renamed from: r1 */
    public boolean mo8154r1() {
        return !this.f1613x4.isEmpty();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void recreate() {
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null) {
            toolbarManager.f2296V3 = true;
        }
        ChromeActivity.super.recreate();
    }

    public void removeViewObscuringAllTabs(View view) {
        this.f1613x4.remove(view);
        Tab v0 = mo8172v0();
        if (v0 != null) {
            v0.mo9412z0();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v7.app.AppCompatActivity] */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        qI.a.a(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a4, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a7, code lost:
        if (r2 != null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a9, code lost:
        m1631a(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ac, code lost:
        throw r1;
     */
    /* renamed from: s0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8157s0() {
        /*
            r7 = this;
            java.lang.String r0 = "toolbarContainerStub.inflate"
            java.lang.String r1 = "setContentView(R.layout.main)"
            java.lang.String r2 = "ChromeActivity.doLayoutInflation"
            org.chromium.base.TraceEvent r2 = org.chromium.base.TraceEvent.m1469B(r2)
            pO0 r3 = pO0.b()     // Catch:{ all -> 0x0095 }
            r4 = 0
            org.chromium.base.TraceEvent.m1472c(r1, r4)     // Catch:{ all -> 0x0097 }
            jM1 r5 = org.chromium.chrome.browser.ChromeApplication.m1775e()     // Catch:{ all -> 0x0097 }
            rM1 r5 = (rM1) r5
            su1 r5 = r5.h()     // Catch:{ all -> 0x0097 }
            int r5 = r5.f()     // Catch:{ all -> 0x0097 }
            r7.setContentView(r5)     // Catch:{ all -> 0x0097 }
            org.chromium.base.TraceEvent.m1475z(r1)     // Catch:{ all -> 0x0097 }
            int r1 = r7.mo8035K0()     // Catch:{ all -> 0x0097 }
            r5 = -1
            if (r1 == r5) goto L_0x0045
            int r1 = ox0.control_container_stub     // Catch:{ all -> 0x0097 }
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ all -> 0x0097 }
            android.view.ViewStub r1 = (android.view.ViewStub) r1     // Catch:{ all -> 0x0097 }
            int r6 = r7.mo8035K0()     // Catch:{ all -> 0x0097 }
            r1.setLayoutResource(r6)     // Catch:{ all -> 0x0097 }
            org.chromium.base.TraceEvent.m1472c(r0, r4)     // Catch:{ all -> 0x0097 }
            r1.inflate()     // Catch:{ all -> 0x0097 }
            org.chromium.base.TraceEvent.m1475z(r0)     // Catch:{ all -> 0x0097 }
        L_0x0045:
            int r0 = ox0.control_container     // Catch:{ all -> 0x0097 }
            android.view.View r0 = r7.findViewById(r0)     // Catch:{ all -> 0x0097 }
            NG2 r0 = (NG2) r0     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x0058
            int r1 = ox0.omnibox_results_container_stub     // Catch:{ all -> 0x0097 }
            android.view.View r1 = r7.findViewById(r1)     // Catch:{ all -> 0x0097 }
            org.chromium.ui.UiUtils.a(r1)     // Catch:{ all -> 0x0097 }
        L_0x0058:
            int r1 = r7.mo8099e1()     // Catch:{ all -> 0x0097 }
            if (r1 == r5) goto L_0x0063
            if (r0 == 0) goto L_0x0063
            r0.a(r1)     // Catch:{ all -> 0x0097 }
        L_0x0063:
            boolean r0 = org.chromium.p012ui.base.DeviceFormFactor.m3641c(r7)     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x0089
            boolean r0 = r7 instanceof org.chromium.chrome.browser.customtabs.CustomTabActivity     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x0089
            int r0 = ox0.bottom_bar_container_stub     // Catch:{ all -> 0x0097 }
            android.view.View r0 = r7.findViewById(r0)     // Catch:{ all -> 0x0097 }
            android.view.ViewStub r0 = (android.view.ViewStub) r0     // Catch:{ all -> 0x0097 }
            jM1 r1 = org.chromium.chrome.browser.ChromeApplication.m1775e()     // Catch:{ all -> 0x0097 }
            rM1 r1 = (rM1) r1
            su1 r1 = r1.h()     // Catch:{ all -> 0x0097 }
            int r1 = r1.b()     // Catch:{ all -> 0x0097 }
            r0.setLayoutResource(r1)     // Catch:{ all -> 0x0097 }
            r0.inflate()     // Catch:{ all -> 0x0097 }
        L_0x0089:
            r3.close()     // Catch:{ all -> 0x0095 }
            r7.mo8058Z()     // Catch:{ all -> 0x0095 }
            if (r2 == 0) goto L_0x0094
            m1631a((java.lang.Throwable) r4, (org.chromium.base.TraceEvent) r2)
        L_0x0094:
            return
        L_0x0095:
            r0 = move-exception
            goto L_0x00a5
        L_0x0097:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x009e }
            goto L_0x00a4
        L_0x009e:
            r3 = move-exception
            kI r4 = qI.a     // Catch:{ all -> 0x0095 }
            r4.a(r0, r3)     // Catch:{ all -> 0x0095 }
        L_0x00a4:
            throw r1     // Catch:{ all -> 0x0095 }
        L_0x00a5:
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r1 = move-exception
            if (r2 == 0) goto L_0x00ac
            m1631a((java.lang.Throwable) r0, (org.chromium.base.TraceEvent) r2)
        L_0x00ac:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8157s0():void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: s1 */
    public final /* synthetic */ void mo8158s1() {
        if (!mo8779a()) {
            new de2(this);
            if (this.f1597h4 == null) {
                this.f1597h4 = new UpdateNotificationController(this);
            }
            this.f1597h4.a(getIntent());
            ge2.c().a(this.f1564E4);
        }
    }

    public void startActivity(Intent intent) {
        startActivity(intent, (Bundle) null);
    }

    public void startActivityForResult(Intent intent, int i) {
        startActivityForResult(intent, i, (Bundle) null);
    }

    public boolean startActivityIfNeeded(Intent intent, int i) {
        return startActivityIfNeeded(intent, i, (Bundle) null);
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v4.app.FragmentActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.support.v7.app.AppCompatActivity] */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0284, code lost:
        if (QN0.a.getBoolean(r1 == com.microsoft.authentication.AuthenticationMode.AAD ? "AAD_ANAHEIM_SYNC_UPSELLED" : "MSA_ANAHEIM_SYNC_UPSELLED", false) == false) goto L_0x0286;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x033a  */
    /* JADX WARNING: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* renamed from: t */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8165t() {
        /*
            r10 = this;
            r0 = 1
            r10.f1582S3 = r0
            pS1.a()
            r10.mo8017B1()
            org.chromium.chrome.browser.download.DownloadManagerService r1 = org.chromium.chrome.browser.download.DownloadManagerService.m()
            r1.i()
            boolean r1 = XE2.b
            if (r1 == 0) goto L_0x0017
            WE2 r1 = XE2.a
            goto L_0x002e
        L_0x0017:
            java.lang.String r1 = "org.chromium.chrome.browser.vr.ArDelegateImpl"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x002a, all -> 0x0026 }
            java.lang.Object r1 = r1.newInstance()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x002a, all -> 0x0026 }
            WE2 r1 = (WE2) r1     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x002a, all -> 0x0026 }
            XE2.a = r1     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x002a, all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r1 = move-exception
            XE2.b = r0
            throw r1
        L_0x002a:
            XE2.b = r0
            WE2 r1 = XE2.a
        L_0x002e:
            if (r1 == 0) goto L_0x0033
            r1.init()
        L_0x0033:
            org.chromium.chrome.browser.network_delegate.EMMXNetworkDelegate r1 = org.chromium.chrome.browser.network_delegate.EMMXNetworkDelegate.e
            r1.a()
            java.util.concurrent.atomic.AtomicBoolean r1 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1777q
            r1.set(r0)
            org.chromium.chrome.browser.preferences.privacy.BrowsingDataBridge r1 = org.chromium.chrome.browser.preferences.privacy.BrowsingDataBridge.m2891b()
            r1.mo9182a()
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            r1.mo8870E()
            boolean r1 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.a
            r2 = 0
            if (r1 == 0) goto L_0x0050
            goto L_0x00ca
        L_0x0050:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Map r4 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.b
            java.util.Set r4 = r4.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0064:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00b0
            java.lang.Object r5 = r4.next()
            com.microsoft.mmx.experiment.FeatureManager$Feature r5 = (com.microsoft.mmx.experiment.FeatureManager$Feature) r5
            boolean r6 = gh0.b(r5)
            if (r6 != 0) goto L_0x0095
            java.util.Set r6 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.c
            boolean r6 = r6.contains(r5)
            if (r6 == 0) goto L_0x0093
            com.microsoft.ruby.util.RubyBuild r6 = com.microsoft.ruby.util.RubyBuild.getForCurrentBuild()
            com.microsoft.ruby.util.RubyBuild r7 = com.microsoft.ruby.util.RubyBuild.DAILY
            com.microsoft.ruby.util.RubyBuild r8 = com.microsoft.ruby.util.RubyBuild.DEVELOPMENT
            com.microsoft.ruby.util.RubyBuild r9 = com.microsoft.ruby.util.RubyBuild.SELFHOST
            java.util.EnumSet r7 = java.util.EnumSet.of(r7, r8, r9)
            boolean r6 = r6.checkSupport(r7)
            if (r6 == 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r6 = 0
            goto L_0x0096
        L_0x0095:
            r6 = 1
        L_0x0096:
            if (r6 == 0) goto L_0x00a4
            java.util.Map r6 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.b
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r1.add(r5)
            goto L_0x0064
        L_0x00a4:
            java.util.Map r6 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.b
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r3.add(r5)
            goto L_0x0064
        L_0x00b0:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            r5 = 44
            java.lang.String r1 = Wi0.a(r1, r5, r4)
            com.microsoft.mmx.experiment.EdgeNativeFeatureManager.nativeSetEdgeNativeFeatures(r1, r2)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            java.lang.String r1 = Wi0.a(r3, r5, r1)
            com.microsoft.mmx.experiment.EdgeNativeFeatureManager.nativeSetEdgeNativeFeatures(r1, r0)
            com.microsoft.mmx.experiment.EdgeNativeFeatureManager.a = r0
        L_0x00ca:
            ds0 r1 = cs0.a
            r1.a = r0
            android.content.SharedPreferences r3 = QN0.a
            java.lang.String r4 = "ShouldDoAnaheimSyncFre"
            boolean r3 = r3.getBoolean(r4, r2)
            if (r3 != 0) goto L_0x0116
            android.content.SharedPreferences r1 = QN0.a
            java.lang.String r3 = "CompleteAnaheimMigrationForNoSigninClient"
            boolean r1 = r1.getBoolean(r3, r2)
            if (r1 != 0) goto L_0x0177
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8866A()
            if (r1 != 0) goto L_0x0110
            boolean r1 = q92.l()
            if (r1 != 0) goto L_0x0110
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8931x()
            if (r1 != 0) goto L_0x0110
            com.microsoft.ruby.anaheim.AnaheimUtils$SwitchToAnaheimSyncAccessPoint r1 = com.microsoft.ruby.anaheim.AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_NO_SIGNIN_RUBY
            is0.a(r1)
            com.microsoft.authentication.AuthenticationMode r1 = com.microsoft.authentication.AuthenticationMode.MSA
            is0.a(r1, r0)
            r6 = 0
            com.microsoft.ruby.telemetry.TelemetryConstants$Actions r7 = com.microsoft.ruby.telemetry.TelemetryConstants.Actions.Background
            java.lang.String[] r9 = new java.lang.String[r2]
            java.lang.String r4 = "Features"
            java.lang.String r5 = "AnaheimOnboarding"
            java.lang.String r8 = "MigrateAnaheim"
            ss0.a(r4, r5, r6, r7, r8, r9)
        L_0x0110:
            android.content.SharedPreferences r1 = QN0.a
            Eo.b(r1, r3, r0)
            goto L_0x0177
        L_0x0116:
            q92.d(r0)
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r3 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r3 = r3.mo8932y()
            if (r3 != 0) goto L_0x0126
            com.microsoft.authentication.AuthenticationMode r3 = com.microsoft.authentication.AuthenticationMode.MSA
            is0.a(r3, r0)
        L_0x0126:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r3 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r3 = r3.mo8930w()
            if (r3 != 0) goto L_0x0133
            com.microsoft.authentication.AuthenticationMode r3 = com.microsoft.authentication.AuthenticationMode.AAD
            is0.a(r3, r0)
        L_0x0133:
            r1.a(r2)
            com.microsoft.authentication.AuthenticationMode r3 = r1.g
            com.microsoft.ruby.anaheim.AnaheimUtils$SwitchToAnaheimSyncAccessPoint r4 = com.microsoft.ruby.anaheim.AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_FRE
            r1.a(r3, r4)
            boolean r3 = r1.f
            if (r3 != 0) goto L_0x014f
            org.chromium.chrome.browser.sync.ProfileSyncService r1 = org.chromium.chrome.browser.sync.ProfileSyncService.m2940M()
            r1.mo9234H()
            q92.e(r2)
            q92.a(r2)
            goto L_0x0177
        L_0x014f:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r3 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r3 = r3.mo8868C()
            if (r3 != 0) goto L_0x016b
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r3 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r3 = r3.mo8866A()
            if (r3 == 0) goto L_0x0177
            boolean r3 = q92.d()
            if (r3 == 0) goto L_0x0177
            boolean r3 = q92.f()
            if (r3 != 0) goto L_0x0177
        L_0x016b:
            com.microsoft.ruby.anaheim.AnaheimUtils$SwitchToAnaheimSyncAccessPoint r3 = com.microsoft.ruby.anaheim.AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_FRE
            com.microsoft.authentication.AuthenticationMode r4 = r1.g
            bs0 r5 = new bs0
            r5.<init>(r1)
            com.microsoft.ruby.anaheim.AnaheimUtils.a(r3, r4, r5)
        L_0x0177:
            boolean r1 = f1553J4
            r3 = 0
            if (r1 != 0) goto L_0x01dd
            f1553J4 = r0
            com.microsoft.authentication.AuthenticationMode r1 = com.microsoft.authentication.AuthenticationMode.MSA
            org.chromium.chrome.browser.ChromeActivity$f r4 = new org.chromium.chrome.browser.ChromeActivity$f
            r4.<init>(r10)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2200a((com.microsoft.authentication.AuthenticationMode) r1, (org.chromium.chrome.browser.dual_identity.DualIdentityManager.IGetProfileCallback) r4)
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8930w()
            java.lang.String r4 = "DualIdentityManager"
            if (r1 == 0) goto L_0x019a
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1775o = r0
            java.lang.String r5 = "Has AAD user signed in, just mark we tried, don't do anything"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r4, r5)
            goto L_0x01c0
        L_0x019a:
            boolean r5 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1775o
            if (r5 == 0) goto L_0x01a4
            java.lang.String r5 = "Already tried delete aad folder, don't do anything"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r4, r5)
            goto L_0x01c0
        L_0x01a4:
            com.microsoft.authentication.AuthenticationMode r5 = com.microsoft.authentication.AuthenticationMode.AAD
            java.lang.String r5 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2194a((com.microsoft.authentication.AuthenticationMode) r5)
            java.lang.String r6 = "deleteAADFolderIfNeeded"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r4, r6)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager$f r6 = new org.chromium.chrome.browser.dual_identity.DualIdentityManager$f
            r6.<init>(r5)
            java.util.concurrent.ExecutorService r5 = Us0.c
            r6.a(r5)
            java.lang.String r5 = "deleteAADFolderIfNeeded finished"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r4, r5)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1775o = r0
        L_0x01c0:
            org.chromium.chrome.browser.ChromeActivity$e r4 = new org.chromium.chrome.browser.ChromeActivity$e
            r4.<init>()
            java.util.concurrent.ExecutorService r5 = Us0.c
            r4.a(r5)
            if (r1 == 0) goto L_0x01d6
            com.microsoft.authentication.AuthenticationMode r1 = com.microsoft.authentication.AuthenticationMode.AAD
            org.chromium.chrome.browser.ChromeActivity$d r4 = new org.chromium.chrome.browser.ChromeActivity$d
            r4.<init>(r3)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2200a((com.microsoft.authentication.AuthenticationMode) r1, (org.chromium.chrome.browser.dual_identity.DualIdentityManager.IGetProfileCallback) r4)
        L_0x01d6:
            org.chromium.ui.base.Clipboard r1 = org.chromium.ui.base.Clipboard.getInstance()
            r1.a()
        L_0x01dd:
            org.chromium.chrome.browser.tabmodel.TabModelSelector r1 = r10.mo8095d1()
            ss0.a(r1)
            super.mo8165t()
            org.chromium.chrome.browser.widget.ScrimView r1 = r10.f1599j4
            r4 = 2
            r1.setImportantForAccessibility(r4)
            w32 r1 = r10.f1610u4
            QH3 r4 = r10.mo8772S()
            int r5 = ox0.keyboard_accessory_stub
            android.view.View r5 = r10.findViewById(r5)
            android.view.ViewStub r5 = (android.view.ViewStub) r5
            int r6 = ox0.keyboard_accessory_sheet_stub
            android.view.View r6 = r10.findViewById(r6)
            android.view.ViewStub r6 = (android.view.ViewStub) r6
            B32 r1 = (B32) r1
            r1.a(r4, r5, r6)
            org.chromium.chrome.browser.compositor.CompositorViewHolder r1 = r10.mo8026G0()
            w32 r4 = r10.f1610u4
            B32 r4 = (B32) r4
            H32 r4 = r4.a
            v32 r4 = r4.d
            r1.a(r4)
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r1 = r10.f1598i4
            if (r1 != 0) goto L_0x0224
            boolean r1 = r10.mo8031I1()
            if (r1 == 0) goto L_0x0224
            r10.mo8098e((boolean) r0)
        L_0x0224:
            org.chromium.chrome.browser.AppHooks r1 = org.chromium.chrome.browser.AppHooks.get()
            r1.mo7981g()
            r10.f1612w4 = r3
            QM1 r1 = r10.f1612w4
            if (r1 == 0) goto L_0x0234
            r10.mo8029H1()
        L_0x0234:
            org.chromium.chrome.browser.AppHooks r1 = org.chromium.chrome.browser.AppHooks.get()
            r1.mo7970K()
            Jr2 r1 = Ir2.a
            r1.a()
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8931x()
            if (r1 == 0) goto L_0x0251
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            java.lang.String r1 = r1.mo8912i()
            Hu0.a(r10, r1)
        L_0x0251:
            boolean r1 = r10.mo8148p1()
            if (r1 == 0) goto L_0x0258
            goto L_0x0259
        L_0x0258:
            r3 = r10
        L_0x0259:
            boolean r1 = is0.d()
            if (r1 != 0) goto L_0x0260
            goto L_0x028e
        L_0x0260:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8866A()
            if (r1 == 0) goto L_0x026b
            com.microsoft.authentication.AuthenticationMode r1 = com.microsoft.authentication.AuthenticationMode.AAD
            goto L_0x026d
        L_0x026b:
            com.microsoft.authentication.AuthenticationMode r1 = com.microsoft.authentication.AuthenticationMode.MSA
        L_0x026d:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r4 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r4 = r4.mo8868C()
            if (r4 == 0) goto L_0x0286
            android.content.SharedPreferences r4 = QN0.a
            com.microsoft.authentication.AuthenticationMode r5 = com.microsoft.authentication.AuthenticationMode.AAD
            if (r1 != r5) goto L_0x027e
            java.lang.String r1 = "AAD_ANAHEIM_SYNC_UPSELLED"
            goto L_0x0280
        L_0x027e:
            java.lang.String r1 = "MSA_ANAHEIM_SYNC_UPSELLED"
        L_0x0280:
            boolean r1 = r4.getBoolean(r1, r2)
            if (r1 != 0) goto L_0x028e
        L_0x0286:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = r1.mo8866A()
            if (r1 == 0) goto L_0x0290
        L_0x028e:
            r1 = 0
            goto L_0x0291
        L_0x0290:
            r1 = 1
        L_0x0291:
            com.microsoft.mmx.experiment.FeatureManager$Feature r4 = com.microsoft.mmx.experiment.FeatureManager$Feature.PROMPT_RUBY_TO_ANAHEIM
            boolean r4 = gh0.b(r4)
            if (r4 == 0) goto L_0x02d7
            android.content.SharedPreferences r4 = QN0.a
            java.lang.String r5 = "LAST_PROMPT_TIME_KEY"
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x02c0
            android.content.SharedPreferences r4 = QN0.a
            long r6 = java.lang.System.currentTimeMillis()
            long r4 = r4.getLong(r5, r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r4
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.DAYS
            r8 = 30
            long r4 = r4.toMillis(r8)
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 < 0) goto L_0x02c3
            r4 = 1
            goto L_0x02c4
        L_0x02c0:
            is0.c(r2)
        L_0x02c3:
            r4 = 0
        L_0x02c4:
            if (r4 != 0) goto L_0x02c7
            goto L_0x02d7
        L_0x02c7:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r4 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r4 = r4.mo8868C()
            if (r4 == 0) goto L_0x02d7
            boolean r4 = q92.l()
            if (r4 != 0) goto L_0x02d7
            r4 = 1
            goto L_0x02d8
        L_0x02d7:
            r4 = 0
        L_0x02d8:
            if (r1 != 0) goto L_0x02dd
            if (r4 != 0) goto L_0x02dd
            goto L_0x0321
        L_0x02dd:
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference
            r4.<init>(r3)
            is0.c = r4
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r3 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r3 = r3.mo8866A()
            if (r3 == 0) goto L_0x02ef
            com.microsoft.authentication.AuthenticationMode r3 = com.microsoft.authentication.AuthenticationMode.AAD
            goto L_0x02f1
        L_0x02ef:
            com.microsoft.authentication.AuthenticationMode r3 = com.microsoft.authentication.AuthenticationMode.MSA
        L_0x02f1:
            if (r1 == 0) goto L_0x0300
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r1 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager$TokenScopeType r2 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.TokenScopeType.AFS_SYNC
            fs0 r4 = new fs0
            r4.<init>(r3)
            r1.mo8889a((org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.TokenScopeType) r2, (com.microsoft.authentication.OAuthTokenProvider.AccessTokenCallback<java.lang.String>) r4)
            goto L_0x0321
        L_0x0300:
            is0.c(r0)
            r1 = 5
            android.content.SharedPreferences r3 = QN0.a
            java.lang.String r4 = "PROMPT_SOURCE"
            Eo.b(r3, r4, r1)
            java.lang.ref.WeakReference r3 = is0.c
            java.lang.Object r3 = r3.get()
            if (r3 == 0) goto L_0x0321
            java.lang.ref.WeakReference r3 = is0.c
            java.lang.Object r3 = r3.get()
            android.support.v4.app.FragmentActivity r3 = (android.support.v4.app.FragmentActivity) r3
            com.microsoft.ruby.anaheim.AnaheimUtils.a(r3, r1)
            is0.c(r2)
        L_0x0321:
            com.microsoft.mmx.experiment.FeatureManager$Feature r1 = com.microsoft.mmx.experiment.FeatureManager$Feature.HISTOGRAM_REPORTING_ROLLOUT
            boolean r1 = com.microsoft.mmx.experiment.EdgeNativeFeatureManager.a(r1)
            if (r1 == 0) goto L_0x0336
            so2 r1 = so2.l()
            boolean r1 = r1.f()
            if (r1 == 0) goto L_0x0336
            org.chromium.chrome.browser.metrics.UmaSessionStats.m2534a((boolean) r0)
        L_0x0336:
            boolean r1 = k82.a
            if (r1 != 0) goto L_0x0348
            java.lang.String r1 = "oneds"
            java.lang.System.loadLibrary(r1)
            com.microsoft.applications.events.httpClient r1 = new com.microsoft.applications.events.httpClient
            android.content.Context r2 = RN0.a
            r1.<init>(r2)
            k82.a = r0
        L_0x0348:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8165t():void");
    }

    /* renamed from: t0 */
    public boolean mo8166t0() {
        ChromeFullscreenManager P0 = mo8046P0();
        if (!P0.a.g) {
            return false;
        }
        P0.a();
        return true;
    }

    /* renamed from: t1 */
    public final /* synthetic */ String mo8167t1() {
        Tab v0 = mo8172v0();
        if (v0 != null && v0.isUserInteractable()) {
            return v0.getUrl();
        }
        return null;
    }

    public void terminateIncognitoSession() {
    }

    /* renamed from: u */
    public boolean mo8169u() {
        return true;
    }

    /* renamed from: u0 */
    public Tab mo8170u0() {
        Tab b = mx2.b(mo8095d1().mo9443a(false));
        return b == null ? mo8081b(false).a(SE2.a, 2) : b;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: u1 */
    public final /* synthetic */ void mo8171u1() {
        if (!mo8779a()) {
            Qs1 qs1 = new Qs1(this);
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
            if (defaultAdapter != null && ON0.a(this, "android.permission.NFC", Process.myPid(), Process.myUid()) != -1) {
                try {
                    Zb2 zb2 = new Zb2(this, qs1);
                    defaultAdapter.setNdefPushMessageCallback(zb2, this, new Activity[0]);
                    defaultAdapter.setOnNdefPushCompleteCallback(zb2, this, new Activity[0]);
                } catch (IllegalStateException unused) {
                    Log.w("BeamController", "NFC registration failure. Can't retry, giving up.");
                }
            }
        }
    }

    /* renamed from: v0 */
    public Tab mo8172v0() {
        if (!this.f1581R3) {
            return null;
        }
        return mx2.b(mo8041M0());
    }

    /* renamed from: v1 */
    public final /* synthetic */ void mo8173v1() {
        mo8094d(false);
    }

    /* renamed from: w0 */
    public ActivityTabProvider mo8174w0() {
        return this.f1558B4;
    }

    /* renamed from: w1 */
    public final /* synthetic */ void mo8175w1() {
        mo8780c0();
    }

    /* renamed from: x0 */
    public R72 mo8176x0() {
        return this.f1557A4;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: x1 */
    public final /* synthetic */ void mo8177x1() {
        Mo0.a();
        for (String a : Mo0.a) {
            Mo0.a(this, a);
        }
    }

    /* renamed from: y0 */
    public int mo8178y0() {
        return 0;
    }

    /* renamed from: y1 */
    public final /* synthetic */ void mo8179y1() {
        if (PartnerBrowserCustomizations.isIncognitoDisabled()) {
            terminateIncognitoSession();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: z */
    public void mo8180z() {
        IntentHandler.f1668i = CommandLine.m1384c().mo7859c("enable-test-intents");
        this.f1578O3 = new IntentHandler(mo8127n0(), getPackageName());
    }

    /* renamed from: z0 */
    public List<Tab> mo8181z0() {
        if (!this.f1581R3) {
            return null;
        }
        return mx2.a(mo8041M0());
    }

    /* renamed from: z1 */
    public final /* synthetic */ void mo8182z1() {
        EE2.b();
        mo8117j0();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    public void startActivity(Intent intent, Bundle bundle) {
        ChromeActivity.super.startActivity(intent, bundle);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        ChromeActivity.super.startActivityForResult(intent, i, bundle);
    }

    public boolean startActivityIfNeeded(Intent intent, int i, Bundle bundle) {
        return ChromeActivity.super.startActivityIfNeeded(intent, i, bundle);
    }

    /* renamed from: a */
    public void mo8068a(ActivityPausedListener activityPausedListener) {
        if (activityPausedListener != null) {
            int i = 0;
            boolean z = false;
            while (i < this.f1570H4.size()) {
                ActivityPausedListener activityPausedListener2 = (ActivityPausedListener) this.f1570H4.get(i).get();
                if (activityPausedListener2 == activityPausedListener) {
                    z = true;
                } else if (activityPausedListener2 == null) {
                    this.f1570H4.remove(i);
                    i--;
                }
                i++;
            }
            if (!z) {
                this.f1570H4.add(new WeakReference(activityPausedListener));
            }
        }
    }

    /* renamed from: e */
    public void mo8097e(int i) {
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null) {
            ActionModeController actionModeController = toolbarManager.f2280F3;
            if (actionModeController != null && actionModeController.c && actionModeController.b == null) {
                actionModeController.c();
            }
            if (toolbarManager.f2311n != null && FeatureUtilities.d() && FeatureUtilities.b()) {
                toolbarManager.f2306c4 = toolbarManager.f2285K3.getResources().getConfiguration().orientation != 2;
                toolbarManager.f2309e.b(toolbarManager.f2306c4);
                toolbarManager.f2311n.a(toolbarManager.f2306c4);
                zv1 zv1 = toolbarManager.f2297W3;
                if (zv1 != null) {
                    zv1.a(toolbarManager.f2306c4);
                }
            }
        }
    }

    /* renamed from: a */
    public C mo8060a(ChromeActivityCommonsModule chromeActivityCommonsModule) {
        rM1 e = ChromeApplication.m1775e();
        if (!Og0.d()) {
            return e.a(chromeActivityCommonsModule);
        }
        rM1 rm1 = e;
        return new xM1(rm1, chromeActivityCommonsModule, rm1.i(), (qM1) null);
    }

    /* renamed from: f */
    public void mo8100f() {
        super.mo8100f();
        ge2 c = ge2.c();
        if (c.c != null) {
            c.b();
        }
        ft1.c().b();
        if (ChromeFeatureList.m1784a("OfflineIndicator") && Ld2.q == null) {
            Ld2.q = new Ld2();
        }
        if (this.f1579P3 || mo8172v0() == null || !mo8172v0().mo9317Z()) {
            mo8027G1();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m1631a(Throwable th, TraceEvent traceEvent) {
        if (th != null) {
            try {
                traceEvent.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            traceEvent.close();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8067a(Boolean bool) {
        mo8104g(bool.booleanValue());
    }

    /* renamed from: a */
    public void mo8065a(Intent intent) {
        v62 v62 = this.f1588Y3;
        if (v62 != null) {
            v62.a(this, 0);
        }
        ft0.a(intent);
        if (!this.f1578O3.mo8291d(intent)) {
            DualIdentityUtils.m2248c("ChromeActivity", "onNewIntentWithNative " + intent);
            if (DualIdentityManager.m2213g() != null) {
                StringBuilder a = Eo.a("onNewIntentWithNative - DualIdentityManager had intent: ");
                a.append(DualIdentityManager.m2213g());
                DualIdentityUtils.m2248c("ChromeActivity", a.toString());
                DualIdentityManager.m2198a(intent);
                DualIdentityUtils.m2248c("ChromeActivity", "onNewIntentWithNative - this intent will be handled later: " + intent);
                return;
            }
            this.f1578O3.mo8290c(intent);
            if (this.f1597h4 == null) {
                this.f1597h4 = new UpdateNotificationController(this);
            }
            this.f1597h4.a(intent);
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: b */
    public final /* synthetic */ void mo8084b(String str) {
        if (!mo8779a()) {
            if (this.f1595f4 != null) {
                RecordHistogram.m1550d(Eo.a("MobileStartup.ToolbarInflationTime.", str), this.f1606q4 - this.f1605p4);
                this.f1595f4.mo9455a(mo8053U0(), str);
            }
            if (gb2.c.c(this)) {
                mo8019C1();
            }
            long n = IntentHandler.m1929n(getIntent());
            if (n != -1) {
                mo8062a(mo8053U0() - n);
            }
            DisplayAndroid a = DisplayAndroid.m3727a((Context) this);
            Point point = a.f2650c;
            float f = a.f2651d;
            int i = (int) ((((float) point.x) / f) + 0.5f);
            int i2 = (int) ((((float) point.y) / f) + 0.5f);
            int i3 = i > i2 ? i : i2;
            if (i < i2) {
                i2 = i;
            }
            RecordHistogram.m1552f("Android.DeviceSize.SmallestDisplaySize", RE2.a(i2, 0, 1000));
            RecordHistogram.m1552f("Android.DeviceSize.LargestDisplaySize", RE2.a(i3, 200, 1200));
        }
    }

    /* renamed from: f */
    public void mo8101f(int i) {
        ToolbarLayout toolbarLayout;
        Re2 v;
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null && (toolbarLayout = toolbarManager.f2309e.a) != null && (v = toolbarLayout.mo9581v()) != null) {
            v.a(i);
        }
    }

    /* renamed from: a */
    public void mo8062a(long j) {
        RecordHistogram.m1550d("MobileStartup.IntentToCreationTime", j);
    }

    /* renamed from: a */
    public boolean mo8076a(int i, Bundle bundle) {
        B32 b32 = this.f1610u4;
        if (b32 != null) {
            b32.a();
        }
        return mo8077a(i, true);
    }

    /* renamed from: a */
    public void mo8066a(MenuItem menuItem) {
        if (menuItem != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("itemId", Pp0.a(menuItem.getItemId()));
            ss0.b("ApplicationMenuClick", hashMap, true, 0, (String) null);
        }
    }

    /* renamed from: b */
    public void mo8086b(boolean z, int i) {
        ThreadUtils.m1461b((Runnable) new Ns1(this, z, i));
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8072a(boolean z, int i) {
        ToolbarLayout toolbarLayout;
        Re2 v;
        ToolbarManager toolbarManager = this.f1595f4;
        if (toolbarManager != null && (toolbarLayout = toolbarManager.f2309e.a) != null && (v = toolbarLayout.mo9581v()) != null) {
            v.a(z, i);
        }
    }

    /* renamed from: b */
    public Lw2.a mo8081b(boolean z) {
        if (this.f1581R3) {
            return z ? this.f1574K3 : this.f1573J3;
        }
        throw new IllegalStateException("Attempting to access TabCreator before initialization");
    }

    /* renamed from: b */
    public void mo8085b(MenuOrKeyboardActionController.MenuOrKeyboardActionHandler menuOrKeyboardActionHandler) {
        this.f1572I4.add(menuOrKeyboardActionHandler);
    }

    /* renamed from: a */
    public void mo8070a(Tab tab) {
        if (tab != null && !tab.mo9313V() && this.f1595f4.f2315r3.j()) {
            long H = tab.mo9299H();
            BookmarkModel bookmarkModel = new BookmarkModel();
            bookmarkModel.a(new Os1(this, tab, H, bookmarkModel));
        }
    }

    /* renamed from: d */
    public void mo8094d(boolean z) {
        xh2 xh2 = this.f1602m4;
        if (xh2 != null && xh2.y) {
            xh2.a(z);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.app.Activity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void mo8071a(org.chromium.chrome.browser.tab.Tab r9, long r10, org.chromium.chrome.browser.bookmarks.BookmarkModel r12) {
        /*
            r8 = this;
            boolean r0 = r9.mo9311T()
            if (r0 != 0) goto L_0x0023
            boolean r0 = r9.mo9316Y()
            if (r0 == 0) goto L_0x0023
            org.chromium.chrome.browser.snackbar.SnackbarManager r5 = r8.getSnackbarManager()
            boolean r0 = r8.mo8148p1()
            if (r0 == 0) goto L_0x0019
            r0 = 1
            r7 = 1
            goto L_0x001b
        L_0x0019:
            r0 = 2
            r7 = 2
        L_0x001b:
            r1 = r10
            r3 = r12
            r4 = r9
            r6 = r8
            LW1.a(r1, r3, r4, r5, r6, r7)
            goto L_0x0026
        L_0x0023:
            r12.a()
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8071a(org.chromium.chrome.browser.tab.Tab, long, org.chromium.chrome.browser.bookmarks.BookmarkModel):void");
    }

    /* JADX WARNING: type inference failed for: r10v3, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: a */
    public void mo8064a(IE1 ie1, View view, ViewGroup viewGroup, NG2 ng2) {
        ContextualSearchManager contextualSearchManager = this.f1592c4;
        if (contextualSearchManager != null) {
            contextualSearchManager.a(viewGroup);
            this.f1592c4.a(ie1);
        }
        ie1.y.mo7868a(this);
        this.f1589Z3.setLayoutManager(ie1);
        this.f1589Z3.setFocusable(false);
        this.f1589Z3.setControlContainer(ng2);
        this.f1589Z3.setFullscreenHandler(mo8046P0());
        this.f1589Z3.setUrlBar(view);
        this.f1589Z3.setInsetObserverView(mo8047Q0());
        this.f1589Z3.a(mo8095d1(), this, mo8091c1(), viewGroup, this.f1592c4);
        LayoutManagerChrome y = mo8026G0().y();
        if (!(ng2 == null || !BM1.e() || y.g() == null)) {
            if (y instanceof LayoutManagerChrome) {
                y.O3 = new Ps1(this);
            }
            ng2.setSwipeHandler(y.g());
        }
        ActivityTabProvider activityTabProvider = this.f1558B4;
        activityTabProvider.d = ie1;
        IE1 ie12 = activityTabProvider.d;
        ie12.y.mo7868a(activityTabProvider.e);
        yE1 ye1 = ie1.y3;
        if (ye1 != null) {
            ye1.Z3 = this;
            ? r10 = ye1.Z3;
            if (r10 != 0) {
                ApplicationStatus.m1364a((ApplicationStatus.ActivityStateListener) ye1, (Activity) r10);
            }
            ye1.w();
        }
        this.f1590a4.a(ie1);
    }

    /* renamed from: a */
    public void mo8069a(MenuOrKeyboardActionController.MenuOrKeyboardActionHandler menuOrKeyboardActionHandler) {
        this.f1572I4.remove(menuOrKeyboardActionHandler);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v4.app.FragmentActivity, android.app.Activity] */
    /* renamed from: a */
    public boolean mo8077a(int i, boolean z) {
        if (i == ox0.sign_in_id) {
            if (!MicrosoftSigninManager.C0424c.f2104a.mo8931x()) {
                MicrosoftAccountSigninActivity.m2560b(this, 3);
            } else {
                PreferencesLauncher.a(this, MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? AadAccountManagementFragment.class : MsaAccountManagementFragment.class, (Bundle) null);
            }
        }
        if (i == ox0.settings_id) {
            MicrosoftSigninManager.C0424c.f2104a.mo8889a(MicrosoftSigninManager.TokenScopeType.RT_CHECK, (OAuthTokenProvider.AccessTokenCallback<String>) null);
            PreferencesLauncher.a(this, (Class) null, (Bundle) null);
        }
        if (i == ox0.find_on_page_id) {
            if (mo8103f1().f2318u3 == null) {
                return false;
            }
            mo8103f1().f2318u3.a();
            if (this.f1592c4 != null) {
                mo8030I0().a(0);
            }
            if (mo8045O0() != null) {
                mo8045O0().a(0, true);
            }
            return true;
        } else if (i == ox0.update_menu_id) {
            ge2 c = ge2.c();
            pe2.a aVar = c.c;
            if (aVar != null) {
                int i2 = aVar.a;
                if (i2 != 1) {
                    if (i2 == 3) {
                        ne2.a.b(0, this);
                    } else if (i2 == 5) {
                        ne2.a.a(0);
                    } else if (i2 == 6) {
                        ne2.a.a(0, this);
                    }
                } else if (!TextUtils.isEmpty(aVar.b)) {
                    try {
                        ne2.a.a(this, 0, false);
                        c.a(1);
                        PrefServiceBridge.m2758o0().mo9096d(true);
                    } catch (ActivityNotFoundException unused) {
                        VN0.a("UpdateMenuItemHelper", "Failed to launch Activity for: %s", new Object[]{c.c.b});
                        c.a(2);
                    }
                }
                if (c.c.c != null) {
                    PrefServiceBridge.m2758o0().mo9105f(c.c.c);
                }
                c.a();
            }
            return true;
        } else {
            Tab v0 = mo8172v0();
            if (i == ox0.feedback_id) {
                mo8044N1();
                return true;
            } else if (i == ox0.exit_browser) {
                new EdgeExitDialogFragment().a(getSupportFragmentManager());
                return true;
            } else if (v0 == null) {
                return false;
            } else {
                if (i == ox0.forward_menu_id) {
                    if (v0.mo9348c()) {
                        v0.mo9306O();
                    }
                } else if (i == ox0.add_to_favorites_id) {
                    mo8070a(v0);
                    ss0.b("AddToFavoritesFromApplicationMenu", (HashMap) null, true, 0, (String) null);
                } else if (i == ox0.offline_page_id) {
                    if (!MAMEdgeManager.isSaveToLocalAllowed()) {
                        MAMEdgeManager.m1303c((Activity) this);
                        return false;
                    }
                    DownloadUtils.a(this, v0);
                } else if (i == ox0.reload_menu_id) {
                    if (v0.mo9317Z()) {
                        v0.mo9408x0();
                    } else {
                        v0.mo9398s0();
                    }
                } else if (i == ox0.info_menu_id) {
                    PageInfoController.a(this, v0, (String) null, 1);
                } else if (i != ox0.open_history_menu_id) {
                    if (i == ox0.share_id || i == ox0.share_menu_id || i == ox0.direct_share_id) {
                        mo8074a(i == ox0.direct_share_id, mo8041M0().isIncognito());
                        ss0.b("ShareFromApplicationMenu", (HashMap) null, true, 0, (String) null);
                    } else if (i == ox0.print_id) {
                        aE3 ae3 = aE3.o;
                        if (ae3 != null) {
                            aE3 ae32 = ae3;
                            if (!ae32.m && PrefServiceBridge.m2758o0().mo9061U()) {
                                ae32.a(new TabPrinter(v0), new YD3(this));
                            }
                        }
                    } else if (i == ox0.add_to_homescreen_id) {
                        new AddToHomescreenManager(this, v0).b();
                    } else if (i == ox0.open_webapk_id) {
                        Context context = RN0.a;
                        try {
                            context.startActivity(QM3.a(YM3.b(context, v0.getUrl()), v0.getUrl(), false));
                        } catch (ActivityNotFoundException unused2) {
                            CK3.a(context, wx0.open_webapk_failed, 0).a();
                        }
                    } else if (i == ox0.view_desktop_site_id || i == ox0.request_desktop_site_check_id) {
                        v0.mo9302K().mo9742F().a(!v0.mo9302K().mo9742F().e(), !v0.isNativePage());
                    } else if (i == ox0.reader_mode_prefs_id) {
                        DomDistillerUIUtils.openSettings(v0.mo9302K());
                    } else if (i == ox0.add_to_readinglist_id) {
                        pr2.a(this, v0);
                    } else if (i == ox0.reload_page_id) {
                        if (v0.mo9317Z()) {
                            v0.mo9408x0();
                        } else {
                            v0.mo9398s0();
                        }
                    } else if (i != ox0.read_aloud_id) {
                        return false;
                    } else {
                        ss0.a("Microsoft.ReadAloud", "ROL_INVOKED");
                        if (!v0.mo9337a0()) {
                            return true;
                        }
                        String str = (String) qU1.c.a.get(v0);
                        if (str == null) {
                            str = "und";
                        }
                        if (!ReadAloudUtils.a(str)) {
                            ReadAloudUtils.a(this, wx0.read_aloud_not_supported);
                            return true;
                        } else if (ReadAloudUtils.a(this)) {
                            ReadAloudUtils.a(this, wx0.read_aloud_no_tts);
                            return true;
                        } else {
                            if (this.f1566F4 == null) {
                                this.f1566F4 = new rU1(this, (OverviewModeBehavior) null, mo8103f1().f2318u3);
                            }
                            this.f1566F4.g(v0);
                        }
                    }
                }
                return true;
            }
        }
    }

    /* renamed from: a */
    public static ChromeActivity m1630a(WebContents webContents) {
        WindowAndroid o0;
        Activity activity;
        if (webContents == null || webContents.isDestroyed() || (o0 = webContents.mo9782o0()) == null || (activity = (Activity) o0.mo10006b().get()) == null || !(activity instanceof ChromeActivity)) {
            return null;
        }
        return (ChromeActivity) activity;
    }

    /* renamed from: a */
    public boolean mo8075a(int i, int i2, Intent intent) {
        return super.mo8075a(i, i2, intent);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard} */
    /* JADX WARNING: type inference failed for: r2v46, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8073a(boolean r17, android.view.View r18) {
        /*
            r16 = this;
            r0 = r16
            xh2 r1 = r0.f1602m4
            if (r1 == 0) goto L_0x03bc
            boolean r2 = r1.q3
            r3 = 0
            if (r2 != 0) goto L_0x0013
            android.view.ViewStub r2 = r1.f()
            if (r2 == 0) goto L_0x0013
            goto L_0x03ab
        L_0x0013:
            boolean r2 = r1.u3
            boolean r4 = EE2.a()
            r5 = 1
            if (r2 != r4) goto L_0x0029
            boolean r2 = r1.v3
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r4 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r4 = r4.mo8866A()
            if (r2 == r4) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r2 = 0
            goto L_0x002a
        L_0x0029:
            r2 = 1
        L_0x002a:
            boolean r4 = r1.t3
            r6 = 0
            if (r4 == 0) goto L_0x0033
            if (r2 != 0) goto L_0x0033
            goto L_0x01c7
        L_0x0033:
            r1.t3 = r5
            android.content.Context r2 = r1.a
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
            float r2 = r2.fontScale
            r4 = 1067030938(0x3f99999a, float:1.2)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x0051
            boolean r2 = EE2.a()
            if (r2 == 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r2 = 0
            goto L_0x0052
        L_0x0051:
            r2 = 1
        L_0x0052:
            r1.u3 = r2
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r2 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r2 = r2.mo8866A()
            r1.v3 = r2
            boolean r2 = r1.u3
            Bh2 r4 = new Bh2
            r4.<init>()
            r1.x3 = r4
            android.content.Context r4 = r1.a
            r7 = r4
            android.app.Activity r7 = (android.app.Activity) r7
            Lh2 r8 = new Lh2
            Bh2 r9 = r1.x3
            r8.<init>(r4, r2, r9)
            r1.n = r8
            Rh2 r4 = new Rh2
            r4.<init>(r7, r2)
            r1.e = r4
            Sh2 r4 = new Sh2
            r4.<init>(r7, r2)
            r1.k = r4
            Hh2 r2 = new Hh2
            r2.<init>(r7)
            r1.p = r2
            Nh2 r2 = new Nh2
            android.content.Context r4 = r1.a
            boolean r8 = r1.u3
            r2.<init>(r4, r8)
            r1.q = r2
            int r2 = ox0.coordinator
            android.view.View r2 = r7.findViewById(r2)
            sh2 r4 = new sh2
            r4.<init>(r1)
            r2.setAccessibilityDelegate(r4)
            Hh2 r2 = r1.p
            com.airbnb.lottie.LottieAnimationView r2 = r2.e
            r1.c = r2
            com.airbnb.lottie.LottieAnimationView r2 = r1.c
            rh2 r4 = new rh2
            r4.<init>(r1)
            r2.setOnClickListener(r4)
            com.airbnb.lottie.LottieAnimationView r2 = r1.c
            r1.c = r2
            android.content.Context r2 = r1.a
            android.app.Activity r2 = (android.app.Activity) r2
            int r4 = ox0.overflow_view_mask
            android.view.View r2 = r2.findViewById(r4)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            qh2 r4 = new qh2
            r4.<init>(r1)
            r2.setOnClickListener(r4)
            r1.b = r2
            boolean r2 = r1.u3
            if (r2 != 0) goto L_0x00e9
            Rh2 r2 = r1.e
            vh2 r4 = new vh2
            r4.<init>(r1)
            android.widget.FrameLayout r2 = r2.a
            boolean r7 = r2 instanceof org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard
            if (r7 == 0) goto L_0x00e2
            org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard r2 = (org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard) r2
            r2.setCardCallback(r4)
            goto L_0x00e9
        L_0x00e2:
            java.lang.String r2 = "PopupCardPresenter"
            java.lang.String r4 = "Large font card needs no callback"
            android.util.Log.w(r2, r4)
        L_0x00e9:
            Lh2 r2 = r1.n
            boolean r4 = r1.u3
            if (r4 == 0) goto L_0x00fa
            android.support.v7.widget.helper.ItemTouchHelper r4 = new android.support.v7.widget.helper.ItemTouchHelper
            uh2 r7 = new uh2
            r7.<init>(r1)
            r4.<init>(r7)
            goto L_0x0104
        L_0x00fa:
            android.support.v7.widget.helper.ItemTouchHelper r4 = new android.support.v7.widget.helper.ItemTouchHelper
            th2 r7 = new th2
            r7.<init>(r1)
            r4.<init>(r7)
        L_0x0104:
            android.support.v7.widget.RecyclerView r2 = r2.d
            r4.a(r2)
            java.lang.String r2 = "mListener"
            java.lang.String r7 = "OverflowHook"
            long r8 = android.os.SystemClock.elapsedRealtime()
            fh2 r10 = new fh2     // Catch:{ Exception -> 0x016f }
            r10.<init>()     // Catch:{ Exception -> 0x016f }
            java.lang.Class<android.support.v7.widget.helper.ItemTouchHelper> r11 = android.support.v7.widget.helper.ItemTouchHelper.class
            java.lang.String r12 = "z"
            java.lang.reflect.Field r11 = r11.getDeclaredField(r12)     // Catch:{ Exception -> 0x016f }
            r11.setAccessible(r5)     // Catch:{ Exception -> 0x016f }
            java.lang.Object r4 = r11.get(r4)     // Catch:{ Exception -> 0x016f }
            java.lang.Class<f9> r11 = f9.class
            java.lang.String r12 = "a"
            java.lang.reflect.Field r11 = r11.getDeclaredField(r12)     // Catch:{ Exception -> 0x016f }
            r11.setAccessible(r5)     // Catch:{ Exception -> 0x016f }
            java.lang.Object r4 = r11.get(r4)     // Catch:{ Exception -> 0x016f }
            java.lang.Class r11 = r4.getClass()     // Catch:{ Exception -> 0x013d }
            java.lang.reflect.Field r11 = r11.getDeclaredField(r2)     // Catch:{ Exception -> 0x013d }
            goto L_0x0143
        L_0x013d:
            java.lang.String r11 = "objGesDetector does not hold mListener directly"
            android.util.Log.e(r7, r11)     // Catch:{ Exception -> 0x016f }
            r11 = r6
        L_0x0143:
            if (r11 != 0) goto L_0x015f
            java.lang.Class r11 = r4.getClass()     // Catch:{ Exception -> 0x016f }
            java.lang.String r12 = "mDetector"
            java.lang.reflect.Field r11 = r11.getDeclaredField(r12)     // Catch:{ Exception -> 0x016f }
            r11.setAccessible(r5)     // Catch:{ Exception -> 0x016f }
            java.lang.Object r4 = r11.get(r4)     // Catch:{ Exception -> 0x016f }
            java.lang.Class r11 = r4.getClass()     // Catch:{ Exception -> 0x016f }
            java.lang.reflect.Field r11 = r11.getDeclaredField(r2)     // Catch:{ Exception -> 0x016f }
            goto L_0x0160
        L_0x015f:
            r4 = r6
        L_0x0160:
            r11.setAccessible(r5)     // Catch:{ Exception -> 0x016f }
            java.lang.Object r2 = r11.get(r4)     // Catch:{ Exception -> 0x016f }
            android.view.GestureDetector$OnGestureListener r2 = (android.view.GestureDetector.OnGestureListener) r2     // Catch:{ Exception -> 0x016f }
            r11.set(r4, r10)     // Catch:{ Exception -> 0x016f }
            r10.a = r2     // Catch:{ Exception -> 0x016f }
            goto L_0x0174
        L_0x016f:
            java.lang.String r2 = "hook hookItemTouchHelper failed, use default effect"
            android.util.Log.e(r7, r2)
        L_0x0174:
            java.lang.String r2 = "hook ItemTouchHelper success, time cost : "
            java.lang.StringBuilder r2 = Eo.a(r2)
            long r10 = android.os.SystemClock.elapsedRealtime()
            long r10 = r10 - r8
            r2.append(r10)
            r2.toString()
            Hh2 r2 = r1.p
            nh2 r4 = new nh2
            r4.<init>(r1)
            android.widget.TextView r2 = r2.d
            r2.setOnClickListener(r4)
            Hh2 r2 = r1.p
            oh2 r4 = new oh2
            r4.<init>(r1)
            android.widget.TextView r2 = r2.c
            r2.setOnClickListener(r4)
            com.microsoft.theme.ThemeManager r2 = com.microsoft.theme.ThemeManager.f1300h
            r2.mo4504a((zu0) r1)
            com.microsoft.theme.ThemeManager r2 = com.microsoft.theme.ThemeManager.f1300h
            com.microsoft.theme.Theme r2 = r2.mo4505b()
            r1.a(r2)
            android.content.Context r2 = r1.a
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
            int r2 = r2.orientation
            r1.r3 = r2
            android.content.Context r2 = r1.a
            org.chromium.ui.display.DisplayAndroid r2 = org.chromium.p012ui.display.DisplayAndroid.m3727a((android.content.Context) r2)
            int r4 = r1.s3
            int r2 = aI3.a(r2, r4)
            r1.s3 = r2
        L_0x01c7:
            com.microsoft.ruby.new_item_indicator.NewItemIndicatorManager r2 = Fq0.a
            java.lang.String r4 = "overflow_button"
            r2.a(r4)
            boolean r2 = r1.i()
            if (r2 == 0) goto L_0x01e3
            boolean r2 = Og0.d()
            if (r2 == 0) goto L_0x01db
            goto L_0x01e3
        L_0x01db:
            Lh2 r2 = r1.n
            android.widget.TextView r2 = r2.e
            r2.setVisibility(r3)
            goto L_0x01ec
        L_0x01e3:
            Lh2 r2 = r1.n
            android.widget.TextView r2 = r2.e
            r4 = 8
            r2.setVisibility(r4)
        L_0x01ec:
            Lh2 r2 = r1.n
            Gh2 r4 = r1.e()
            android.widget.FrameLayout r4 = r4.a
            int r4 = r4.getId()
            android.widget.LinearLayout r7 = r2.a
            android.view.ViewParent r7 = r7.getParent()
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
            android.widget.LinearLayout r8 = r2.a
            android.content.Context r8 = r8.getContext()
            android.app.Activity r8 = (android.app.Activity) r8
            android.view.View r4 = r8.findViewById(r4)
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            boolean r8 = r2.s3
            if (r8 == 0) goto L_0x0215
            if (r7 != r4) goto L_0x0215
            goto L_0x0244
        L_0x0215:
            if (r7 == 0) goto L_0x021c
            android.widget.LinearLayout r8 = r2.a
            r7.removeView(r8)
        L_0x021c:
            boolean r7 = r4 instanceof org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard
            if (r7 == 0) goto L_0x0227
            r7 = r4
            org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard r7 = (org.chromium.chrome.browser.overflow_menu.view.PopupMenuCard) r7
            r7.g()
            goto L_0x022a
        L_0x0227:
            r4.removeAllViews()
        L_0x022a:
            android.widget.LinearLayout r7 = r2.a
            android.widget.FrameLayout$LayoutParams r8 = new android.widget.FrameLayout$LayoutParams
            r9 = -1
            r10 = -2
            r8.<init>(r9, r10)
            r4.addView(r7, r8)
            r4.invalidate()
            r2.s3 = r5
            com.microsoft.theme.ThemeManager r4 = com.microsoft.theme.ThemeManager.f1300h
            com.microsoft.theme.Theme r4 = r4.mo4505b()
            r2.a(r4)
        L_0x0244:
            Lh2 r2 = r1.n
            boolean r4 = r2.s3
            if (r4 != 0) goto L_0x0253
            java.lang.String r2 = "MenuBlockPresenter"
            java.lang.String r4 = "menu block not attached yet, no necessary to updateItems"
            android.util.Log.w(r2, r4)
            goto L_0x0316
        L_0x0253:
            zh2 r4 = r2.q3
            java.util.List r4 = r4.a()
            yh2 r7 = r2.y
            java.util.List r7 = r7.a()
            android.widget.LinearLayout r8 = r2.a
            android.content.Context r8 = r8.getContext()
            org.chromium.chrome.browser.ChromeActivity r8 = (org.chromium.chrome.browser.ChromeActivity) r8
            org.chromium.chrome.browser.tab.Tab r8 = r8.mo8172v0()
            if (r8 != 0) goto L_0x026f
            goto L_0x0316
        L_0x026f:
            Bh2 r9 = r2.x
            r9.a(r8)
            java.util.Iterator r4 = r4.iterator()
        L_0x0278:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x0295
            java.lang.Object r9 = r4.next()
            Dh2 r9 = (Dh2) r9
            Eh2 r10 = r2.p
            r9.a(r10)
            Bh2 r10 = r2.x
            int r11 = r9.a
            boolean r10 = r10.a(r11, r8)
            r9.a(r10)
            goto L_0x0278
        L_0x0295:
            eh2 r4 = r2.k
            r4.notifyDataSetChanged()
            org.chromium.content_public.browser.WebContents r4 = r8.mo9302K()
            org.chromium.content_public.browser.NavigationController r4 = r4.mo9742F()
            boolean r4 = r4.e()
            boolean r9 = Mx1.f()
            r10 = 0
        L_0x02ab:
            int r11 = r7.size()
            if (r10 >= r11) goto L_0x0311
            java.lang.Object r11 = r7.get(r10)
            Dh2 r11 = (Dh2) r11
            java.lang.Object r12 = r7.get(r10)
            Dh2 r12 = (Dh2) r12
            int r13 = r12.a
            r14 = 12
            r15 = 20
            if (r13 == r15) goto L_0x02c7
            if (r13 != r14) goto L_0x02d7
        L_0x02c7:
            if (r4 == 0) goto L_0x02ce
            Dh2 r12 = Ah2.a(r15)
            goto L_0x02d2
        L_0x02ce:
            Dh2 r12 = Ah2.a(r14)
        L_0x02d2:
            Eh2 r13 = r2.q
            r12.a(r13)
        L_0x02d7:
            if (r12 == r11) goto L_0x02dc
            r7.set(r10, r12)
        L_0x02dc:
            int r12 = r11.a
            r13 = 25
            r14 = 16
            if (r12 == r14) goto L_0x02e9
            if (r12 != r13) goto L_0x02e7
            goto L_0x02e9
        L_0x02e7:
            r12 = r11
            goto L_0x02f9
        L_0x02e9:
            if (r9 == 0) goto L_0x02f0
            Dh2 r12 = Ah2.a(r14)
            goto L_0x02f4
        L_0x02f0:
            Dh2 r12 = Ah2.a(r13)
        L_0x02f4:
            Eh2 r13 = r2.q
            r12.a(r13)
        L_0x02f9:
            if (r12 == r11) goto L_0x02fe
            r7.set(r10, r12)
        L_0x02fe:
            Eh2 r12 = r2.q
            r11.a(r12)
            Bh2 r12 = r2.x
            int r13 = r11.a
            boolean r12 = r12.a(r13, r8)
            r11.a(r12)
            int r10 = r10 + 1
            goto L_0x02ab
        L_0x0311:
            eh2 r2 = r2.n
            r2.notifyDataSetChanged()
        L_0x0316:
            com.airbnb.lottie.LottieAnimationView r2 = r1.c
            r1.a(r2, r3)
            boolean r2 = r1.i()
            if (r2 != 0) goto L_0x0332
            boolean r2 = r1.u3
            if (r2 == 0) goto L_0x0326
            goto L_0x0332
        L_0x0326:
            android.widget.ImageView r2 = r1.b
            r4 = 0
            r2.setAlpha(r4)
            android.widget.ImageView r2 = r1.b
            r2.setVisibility(r3)
            goto L_0x033e
        L_0x0332:
            android.widget.ImageView r2 = r1.b
            r4 = 1065353216(0x3f800000, float:1.0)
            r2.setAlpha(r4)
            android.widget.ImageView r2 = r1.b
            r2.setVisibility(r3)
        L_0x033e:
            boolean r2 = r1.i()
            if (r2 == 0) goto L_0x0386
            Sh2 r2 = r1.k
            android.widget.FrameLayout r4 = r2.a
            r4.setVisibility(r3)
            android.widget.FrameLayout r4 = r2.a
            r4.setScrollY(r3)
            boolean r4 = Og0.d()
            if (r4 == 0) goto L_0x0382
            android.app.Activity r4 = r2.b
            Kg0 r4 = Kg0.a(r4)
            boolean r4 = r4.a()
            if (r4 == 0) goto L_0x0371
            android.widget.FrameLayout r4 = r2.a
            int r5 = lx0.bottom_corner_background
            r4.setBackgroundResource(r5)
            android.widget.FrameLayout r2 = r2.a
            int r4 = jx0.overflow_card_background
            Yt0.b(r2, r4)
            goto L_0x0382
        L_0x0371:
            android.widget.FrameLayout r4 = r2.a
            android.app.Activity r2 = r2.b
            android.content.res.Resources r2 = r2.getResources()
            int r5 = jx0.overflow_card_background
            int r2 = du0.a(r2, r5)
            r4.setBackgroundColor(r2)
        L_0x0382:
            r1.a()
            goto L_0x0392
        L_0x0386:
            Rh2 r2 = r1.e
            kh2 r4 = new kh2
            r4.<init>(r1)
            r7 = r17
            r2.a(r5, r7, r4)
        L_0x0392:
            Hh2 r2 = r1.p
            boolean r1 = r1.i()
            r2.f = r1
            android.widget.FrameLayout r1 = r2.a
            r1.setVisibility(r3)
            r2.c()
            java.lang.String[] r1 = new java.lang.String[r3]
            java.lang.String r2 = "MainFrame"
            java.lang.String r4 = "Overflow"
            ss0.a(r2, r4, r6, r1)
        L_0x03ab:
            xh2 r1 = r0.f1602m4
            r2 = r18
            r1.d = r2
            org.chromium.chrome.browser.contextualsearch.ContextualSearchManager r1 = r0.f1592c4
            if (r1 == 0) goto L_0x03bc
            org.chromium.chrome.browser.contextualsearch.ContextualSearchManager r1 = r16.mo8030I0()
            r1.a(r3)
        L_0x03bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeActivity.mo8073a(boolean, android.view.View):void");
    }
}
