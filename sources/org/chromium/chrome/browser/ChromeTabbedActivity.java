package org.chromium.chrome.browser;

import BI3;
import FO0;
import J.N;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.citrix.MAM.Android.ManagedAppHelper.Interface.MAMAppInfo;
import com.citrix.loggersdk.BuildConfig;
import com.citrix.worx.sdk.AppLogArchiver;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.authentication.OAuthTokenProvider;
import com.microsoft.bing.visualsearch.camerasearchv2.content.model.OCRItem$OCRActionType;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.ruby.anaheim.AnaheimUtils;
import com.microsoft.ruby.collections.CollectionsBridge;
import com.microsoft.ruby.family.util.FamilyUtils;
import com.microsoft.ruby.share_usage_data.AadManagedShareHistoryDialog;
import com.microsoft.ruby.share_usage_data.AadManagedShareUsageDialog;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.CommandLine;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.bottombar.BottomBarHelper;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.DynamicMarginCompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome;
import org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior;
import org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior$OverviewModeObserver;
import org.chromium.chrome.browser.compositor.layouts.phone.TileLayout;
import org.chromium.chrome.browser.cookies.CookiesFetcher;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.edge_feedback.FeedbackSessionManager;
import org.chromium.chrome.browser.edge_ntp.NewTabPage;
import org.chromium.chrome.browser.edge_ntp.NewTabPageView;
import org.chromium.chrome.browser.favorites.BookmarkActivity;
import org.chromium.chrome.browser.feature_engagement.TrackerFactory;
import org.chromium.chrome.browser.hub.HubFragment;
import org.chromium.chrome.browser.hub.HubFragmentBackHelper;
import org.chromium.chrome.browser.hub.HubManager;
import org.chromium.chrome.browser.hub.HubManager$HubStateListener;
import org.chromium.chrome.browser.hub.HubUIManager;
import org.chromium.chrome.browser.hub.base.BaseFragment;
import org.chromium.chrome.browser.hub.collections.CollectionAnimationHelper;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.microsoft_signin.BingEnterpriseManager;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.microsoft_signin.NotifyResigninDialogFragment;
import org.chromium.chrome.browser.multiwindow.MultiInstanceChromeTabbedActivity;
import org.chromium.chrome.browser.p010vr.VrModuleProvider;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.preferences.PreferencesLauncher;
import org.chromium.chrome.browser.preferences.privacy.PrivacyPreferences;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.snackbar.SnackbarManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.tabmodel.TabWindowManager;
import org.chromium.chrome.browser.toolbar.ToolbarManager;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.util.FeatureUtilities;
import org.chromium.components.language.GeoLanguageProviderBridge;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.p012ui.base.DeviceFormFactor;
import org.chromium.ui.modaldialog.ModalDialogManager;
import org.chromium.ui.modaldialog.ModalDialogProperties;

/* compiled from: PG */
public class ChromeTabbedActivity extends ChromeActivity implements OverviewModeBehavior$OverviewModeObserver, RW1, HubManager$HubStateListener, re0, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback, Ly1 {

    /* renamed from: A5 */
    public static final FO0.c f1622A5 = new FO0.c("Android.MainActivity.UndispatchedExplicitMainViewIntentSource", 15);

    /* renamed from: B5 */
    public static boolean f1623B5;

    /* renamed from: y5 */
    public static final FO0.b f1624y5 = new FO0.b("Android.MainActivity.ExplicitMainViewIntentDispatched.OnCreate");

    /* renamed from: z5 */
    public static final FO0.b f1625z5 = new FO0.b("Android.MainActivity.ExplicitMainViewIntentDispatched.OnNewIntent");

    /* renamed from: N4 */
    public g f1626N4;

    /* renamed from: O4 */
    public final N72 f1627O4 = new N72();

    /* renamed from: P4 */
    public final W72 f1628P4 = new W72(this);

    /* renamed from: Q4 */
    public final eb2 f1629Q4;

    /* renamed from: R4 */
    public Bt2 f1630R4;

    /* renamed from: S4 */
    public LayoutManagerChrome f1631S4;

    /* renamed from: T4 */
    public ViewGroup f1632T4;

    /* renamed from: U4 */
    public String f1633U4;

    /* renamed from: V4 */
    public ToolbarControlContainer f1634V4;

    /* renamed from: W4 */
    public dx2 f1635W4;

    /* renamed from: X4 */
    public kx2 f1636X4;

    /* renamed from: Y4 */
    public hx2 f1637Y4;

    /* renamed from: Z4 */
    public CollectionsBridge.a f1638Z4;

    /* renamed from: a5 */
    public QW1 f1639a5;

    /* renamed from: b5 */
    public Xa2 f1640b5;

    /* renamed from: c5 */
    public boolean f1641c5;

    /* renamed from: d5 */
    public Boolean f1642d5;

    /* renamed from: e5 */
    public LocaleManager f1643e5;

    /* renamed from: f5 */
    public final i f1644f5 = new i(this, (a) null);

    /* renamed from: g5 */
    public zs1 f1645g5 = new zs1();

    /* renamed from: h5 */
    public Runnable f1646h5;

    /* renamed from: i5 */
    public Fu1 f1647i5;

    /* renamed from: j5 */
    public boolean f1648j5;

    /* renamed from: k5 */
    public tt1 f1649k5;

    /* renamed from: l5 */
    public boolean f1650l5;

    /* renamed from: m5 */
    public long f1651m5;

    /* renamed from: n5 */
    public boolean f1652n5;

    /* renamed from: o5 */
    public HubManager f1653o5;

    /* renamed from: p5 */
    public Ay1 f1654p5;

    /* renamed from: q5 */
    public rv2 f1655q5 = null;

    /* renamed from: r5 */
    public k f1656r5;

    /* renamed from: s5 */
    public Jo0 f1657s5;

    /* renamed from: t5 */
    public BottomBarHelper f1658t5 = new BottomBarHelper();

    /* renamed from: u5 */
    public boolean f1659u5 = false;

    /* renamed from: v5 */
    public final L02 f1660v5 = new a(this);

    /* renamed from: w5 */
    public final j f1661w5 = new j((a) null);

    /* renamed from: x5 */
    public l f1662x5;

    static {
        Class<ChromeTabbedActivity> cls = ChromeTabbedActivity.class;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ChromeTabbedActivity() {
        /*
            r9 = this;
            r9.<init>()
            r0 = 0
            r9.f1655q5 = r0
            r1 = 0
            r9.f1659u5 = r1
            org.chromium.chrome.browser.ChromeTabbedActivity$a r1 = new org.chromium.chrome.browser.ChromeTabbedActivity$a
            r1.<init>(r9)
            r9.f1660v5 = r1
            org.chromium.chrome.browser.ChromeTabbedActivity$j r1 = new org.chromium.chrome.browser.ChromeTabbedActivity$j
            r1.<init>(r0)
            r9.f1661w5 = r1
            N72 r1 = new N72
            r1.<init>()
            r9.f1627O4 = r1
            W72 r1 = new W72
            r1.<init>(r9)
            r9.f1628P4 = r1
            org.chromium.chrome.browser.ChromeTabbedActivity$i r1 = new org.chromium.chrome.browser.ChromeTabbedActivity$i
            r1.<init>(r9, r0)
            r9.f1644f5 = r1
            zs1 r1 = new zs1
            r1.<init>()
            r9.f1645g5 = r1
            org.chromium.chrome.browser.bottombar.BottomBarHelper r1 = new org.chromium.chrome.browser.bottombar.BottomBarHelper
            r1.<init>()
            r9.f1658t5 = r1
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L_0x0055
            eb2 r0 = new eb2
            aO0<org.chromium.chrome.browser.tabmodel.TabModelSelector> r5 = r9.f1563E3
            org.chromium.chrome.browser.multiwindow.MultiWindowModeStateDispatcher r6 = r9.mo8770P()
            Q52 r7 = r9.mo8769O()
            r3 = r0
            r4 = r9
            r8 = r9
            r3.<init>(r4, r5, r6, r7, r8)
            r9.f1629Q4 = r0
            goto L_0x0057
        L_0x0055:
            r9.f1629Q4 = r0
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.<init>():void");
    }

    /* renamed from: C2 */
    public static final /* synthetic */ void m1785C2() {
        String string = QN0.a.getString("com.microsoft.emmx.RERERRER_CAMPAIGN", BuildConfig.FLAVOR);
        if ("rewardsv2AMC".equals(string) || "v2test".equals(string)) {
            ThreadUtils.m1458a(Wt1.a, AppLogArchiver.ROLLOVER_TIME_DIFFERENCE);
        }
        Tl0 tl0 = Sl0.a;
        if (tl0.b.mo8932y()) {
            if (tl0.b() != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("rewardsType", OCRItem$OCRActionType.OCR_NONE);
                hashMap.put("userType", "rewardsUser");
                ss0.b("Rewards", hashMap, true, 0, BuildConfig.FLAVOR);
                return;
            }
            Integer num = (Integer) tl0.d.a(Integer.class, tl0.a);
            if (num == null) {
                return;
            }
            if (num.intValue() == 5 || num.intValue() == 6 || num.intValue() == 10) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("rewardsType", OCRItem$OCRActionType.OCR_NONE);
                hashMap2.put("userType", "nonRewardsUser");
                ss0.b("Rewards", hashMap2, true, 0, BuildConfig.FLAVOR);
            }
        }
    }

    /* renamed from: D2 */
    public static final /* synthetic */ void m1786D2() {
        if (q92.n()) {
            boolean l = q92.l();
            TelemetryConstants.Type type = TelemetryConstants.Type.Health;
            String[] strArr = new String[2];
            strArr[0] = "userType";
            strArr[1] = l ? "anaheim" : "ruby";
            AnaheimUtils.a("userTypeReport", type, strArr);
        }
    }

    /* renamed from: a */
    public static void m1790a(Intent intent, ComponentName componentName) {
        Context context = RN0.a;
        if (TextUtils.equals(componentName.getPackageName(), context.getPackageName())) {
            if (componentName.getClassName() == null || !TextUtils.equals(componentName.getClassName(), "com.google.android.apps.chrome.Main")) {
                intent.setComponent(componentName);
            } else if (FeatureUtilities.isNoTouchModeEnabled()) {
                intent.setClass(context, (Class) null);
            } else if (Build.VERSION.SDK_INT < 21) {
                intent.setClass(context, ChromeLauncherActivity.class);
            } else {
                intent.setClass(context, ChromeTabbedActivity.class);
            }
        }
    }

    /* renamed from: c */
    public static boolean m1792c(String str) {
        return TextUtils.equals(str, ChromeTabbedActivity.class.getName()) || TextUtils.equals(str, MultiInstanceChromeTabbedActivity.class.getName()) || TextUtils.equals(str, ChromeTabbedActivity2.class.getName()) || TextUtils.equals(str, "com.google.android.apps.chrome.Main");
    }

    /* renamed from: A2 */
    public final void mo8204A2() {
        if (mo8020D0() != null && this.f1631S4 != null) {
            CompositorViewHolder G0 = mo8026G0();
            if (G0 != null) {
                G0.setFocusable(!mo8154r1());
            }
            hH2 hh2 = this.f1631S4.K3;
            if (hh2 != null) {
                boolean z = !mo8154r1();
                if (hh2.p != null) {
                    int i = z ? 0 : 4;
                    if (hh2.p.getImportantForAccessibility() != i) {
                        hh2.p.setImportantForAccessibility(i);
                        hh2.p.sendAccessibilityEvent(2048);
                    }
                }
            }
        }
    }

    /* renamed from: B */
    public void mo8205B() {
        TrackerFactory.nativeGetTrackerForProfile(Profile.m2911j()).d("screenshot_taken_chrome_in_foreground");
        PostTask.m1566a(iQ2.a, new f(this), 0);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: B2 */
    public void mo8206B2() {
        int b = mo4510b(ThemeManager.f1300h.mo4505b());
        ON0.b(getWindow(), b);
        ON0.a(getWindow().getDecorView().getRootView(), !JE2.d(b));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, java.lang.Object] */
    /* renamed from: D1 */
    public void mo8021D1() {
        j jVar = this.f1661w5;
        if (jVar != null) {
            jVar.b.mo7869b(this);
        }
        kx2 kx2 = this.f1636X4;
        if (kx2 != null) {
            kx2.destroy();
            this.f1636X4 = null;
        }
        hx2 hx2 = this.f1637Y4;
        if (hx2 != null) {
            hx2.destroy();
        }
        Bt2 bt2 = this.f1630R4;
        if (bt2 != null) {
            TabModel a = bt2.a.mo9443a(false);
            if (a != null) {
                a.mo9421a(bt2.b);
            }
            this.f1630R4 = null;
        }
        M02 a2 = M02.a();
        a2.a.remove(this.f1660v5);
        bf0.d().a();
        if (this.f1654p5 != null) {
            BingEnterpriseManager.h().a(this.f1654p5);
            this.f1654p5 = null;
        }
        if (this.f1626N4 != null) {
            Y5.a(RN0.a).a(this.f1626N4);
        }
        if (this.f1662x5 != null) {
            Y5.a(RN0.a).a(this.f1662x5);
        }
        Jo0 jo0 = this.f1657s5;
        if (jo0 != null) {
            try {
                unregisterReceiver(jo0.c);
            } catch (Exception e) {
                Log.e(jo0.a, e.getMessage());
            }
            Fo0.e.d = null;
            Vg0 a3 = gh0.a();
            List list = a3.e;
            if (list != null && list.contains(jo0)) {
                a3.e.remove(jo0);
            }
            MicrosoftSigninManager.C0424c.f2104a.mo8895b((Oo0) jo0);
            this.f1657s5 = null;
        }
    }

    /* renamed from: H1 */
    public void mo8029H1() {
        super.mo8029H1();
        throw null;
    }

    /* renamed from: I1 */
    public boolean mo8031I1() {
        return super.mo8031I1() || FeatureUtilities.m() || N.MauDkM8b();
    }

    /* renamed from: J0 */
    public int mo8033J0() {
        return kx0.control_container_height;
    }

    /* renamed from: K0 */
    public int mo8035K0() {
        return rx0.control_container;
    }

    /* renamed from: L */
    public ModalDialogManager mo8037L() {
        ModalDialogManager L = super.mo8037L();
        this.f1640b5 = new Xa2(this, L);
        return L;
    }

    /* renamed from: R1 */
    public final void mo8207R1() {
        TraceEvent B = TraceEvent.m1469B("ChromeTabbedActivity.refreshSignIn");
        if (B != null) {
            m1791a((Throwable) null, B);
        }
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, RW1] */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x009f, code lost:
        if (r0 != null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a1, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a4, code lost:
        throw r2;
     */
    /* renamed from: S1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8208S1() {
        /*
            r15 = this;
            java.lang.String r0 = "ChromeTabbedActivity.initializeToolbarManager"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            Bt2 r1 = r15.f1630R4     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.tabmodel.TabModelSelector r2 = r1.a     // Catch:{ all -> 0x009c }
            r3 = 0
            org.chromium.chrome.browser.tabmodel.TabModel r2 = r2.mo9443a((boolean) r3)     // Catch:{ all -> 0x009c }
            Tw2 r1 = r1.b     // Catch:{ all -> 0x009c }
            r2.mo9428b((Tw2) r1)     // Catch:{ all -> 0x009c }
            yt1 r1 = new yt1     // Catch:{ all -> 0x009c }
            r1.<init>(r15)     // Catch:{ all -> 0x009c }
            Jt1 r10 = new Jt1     // Catch:{ all -> 0x009c }
            r10.<init>(r15)     // Catch:{ all -> 0x009c }
            Ut1 r13 = new Ut1     // Catch:{ all -> 0x009c }
            r13.<init>(r15)     // Catch:{ all -> 0x009c }
            Yt1 r14 = new Yt1     // Catch:{ all -> 0x009c }
            r14.<init>(r15)     // Catch:{ all -> 0x009c }
            Zt1 r11 = new Zt1     // Catch:{ all -> 0x009c }
            r11.<init>(r15)     // Catch:{ all -> 0x009c }
            boolean r2 = r15.mo8031I1()     // Catch:{ all -> 0x009c }
            if (r2 == 0) goto L_0x0036
            r15.mo8098e((boolean) r3)     // Catch:{ all -> 0x009c }
        L_0x0036:
            org.chromium.chrome.browser.toolbar.ToolbarManager r4 = r15.mo8103f1()     // Catch:{ all -> 0x009c }
            dx2 r5 = r15.f1635W4     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.fullscreen.ChromeFullscreenManager r2 = r15.mo8046P0()     // Catch:{ all -> 0x009c }
            SX1 r6 = r2.d     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.ChromeTabbedActivity$j r7 = r15.f1661w5     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r8 = r15.f1631S4     // Catch:{ all -> 0x009c }
            r12 = 0
            r9 = r1
            r4.mo9460a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r2 = r15.f1631S4     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.toolbar.ToolbarManager r4 = r15.mo8103f1()     // Catch:{ all -> 0x009c }
            r2.a(r4)     // Catch:{ all -> 0x009c }
            boolean r2 = org.chromium.p012ui.base.DeviceFormFactor.m3641c(r15)     // Catch:{ all -> 0x009c }
            if (r2 != 0) goto L_0x0069
            fz1 r2 = r15.mo8018C0()     // Catch:{ all -> 0x009c }
            dx2 r4 = r15.f1635W4     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.fullscreen.ChromeFullscreenManager r5 = r15.mo8046P0()     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r6 = r15.f1631S4     // Catch:{ all -> 0x009c }
            r2.a(r4, r5, r6, r1)     // Catch:{ all -> 0x009c }
        L_0x0069:
            org.chromium.chrome.browser.ChromeTabbedActivity$j r1 = r15.f1661w5     // Catch:{ all -> 0x009c }
            r1.hideOverview(r3)     // Catch:{ all -> 0x009c }
            QW1 r1 = new QW1     // Catch:{ all -> 0x009c }
            r1.<init>(r15)     // Catch:{ all -> 0x009c }
            r15.f1639a5 = r1     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.toolbar.ToolbarManager r1 = r15.mo8103f1()     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator r1 = r1.f2309e     // Catch:{ all -> 0x009c }
            boolean r1 = r1 instanceof org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator     // Catch:{ all -> 0x009c }
            if (r1 == 0) goto L_0x0095
            org.chromium.chrome.browser.toolbar.ToolbarManager r1 = r15.mo8103f1()     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator r1 = r1.f2309e     // Catch:{ all -> 0x009c }
            fu1 r2 = new fu1     // Catch:{ all -> 0x009c }
            r2.<init>(r15)     // Catch:{ all -> 0x009c }
            org.chromium.chrome.browser.toolbar.top.ToolbarLayout r1 = r1.a     // Catch:{ all -> 0x009c }
            boolean r3 = r1 instanceof org.chromium.chrome.browser.toolbar.top.ToolbarPhone     // Catch:{ all -> 0x009c }
            if (r3 == 0) goto L_0x0095
            org.chromium.chrome.browser.toolbar.top.ToolbarPhone r1 = (org.chromium.chrome.browser.toolbar.top.ToolbarPhone) r1     // Catch:{ all -> 0x009c }
            r1.setTabCenterBarListener(r2)     // Catch:{ all -> 0x009c }
        L_0x0095:
            if (r0 == 0) goto L_0x009b
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x009b:
            return
        L_0x009c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x009e }
        L_0x009e:
            r2 = move-exception
            if (r0 == 0) goto L_0x00a4
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x00a4:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8208S1():void");
    }

    /* renamed from: T1 */
    public final void mo8209T1() {
        if (mo8089b1() instanceof sq2) {
            mo8089b1().a(this.f1635W4, this.f1631S4, new au1(this));
            return;
        }
        mo8089b1().a(this.f1635W4, this.f1631S4);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (r0 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        throw r2;
     */
    /* renamed from: U1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8210U1() {
        /*
            r5 = this;
            java.lang.String r0 = "ChromeTabbedActivity.maybeCreateIncognitoTabSnapshotController"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.base.CommandLine r1 = org.chromium.base.CommandLine.m1384c()     // Catch:{ all -> 0x004e }
            java.lang.String r2 = "enable-incognito-snapshots-in-android-recents"
            boolean r1 = r1.mo7859c(r2)     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x001f
            android.view.Window r1 = r5.getWindow()     // Catch:{ all -> 0x004e }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r2 = r5.f1631S4     // Catch:{ all -> 0x004e }
            dx2 r3 = r5.f1635W4     // Catch:{ all -> 0x004e }
            O02 r4 = new O02     // Catch:{ all -> 0x004e }
            r4.<init>(r1, r2, r3)     // Catch:{ all -> 0x004e }
        L_0x001f:
            r1 = 1
            r5.f1641c5 = r1     // Catch:{ all -> 0x004e }
            r5.mo8211V1()     // Catch:{ all -> 0x004e }
            com.microsoft.managedbehavior.CitrixManager r1 = com.microsoft.managedbehavior.CitrixManager.e.a     // Catch:{ all -> 0x004e }
            r1.mo4434b((android.content.Context) r5)     // Catch:{ all -> 0x004e }
            dx2 r1 = r5.f1635W4     // Catch:{ all -> 0x004e }
            r1.m()     // Catch:{ all -> 0x004e }
            android.view.Window r1 = r5.getWindow()     // Catch:{ all -> 0x004e }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x004e }
            r3 = 21
            if (r2 >= r3) goto L_0x003e
            r2 = 5
            r3 = -2
            r1.setFeatureInt(r2, r3)     // Catch:{ all -> 0x004e }
        L_0x003e:
            boolean r1 = P02.c()     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x0047
            H02.a()     // Catch:{ all -> 0x004e }
        L_0x0047:
            if (r0 == 0) goto L_0x004d
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x004d:
            return
        L_0x004e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r2 = move-exception
            if (r0 == 0) goto L_0x0056
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0056:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8210U1():void");
    }

    /* renamed from: V0 */
    public OverviewModeBehavior mo8054V0() {
        return this.f1661w5;
    }

    /* renamed from: W0 */
    public YN0<OverviewModeBehavior> mo8055W0() {
        return this.f1661w5.c;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r0 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0061, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        throw r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* renamed from: W1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8212W1() {
        /*
            r7 = this;
            java.lang.String r0 = "ChromeTabbedActivity.createToolbarButtonInProductHelpController"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.chrome.browser.preferences.ChromePreferenceManager r1 = sl2.a     // Catch:{ all -> 0x005c }
            org.chromium.chrome.browser.locale.LocaleManager r2 = r7.f1643e5     // Catch:{ all -> 0x005c }
            boolean r2 = r2.mo8825f()     // Catch:{ all -> 0x005c }
            gb2 r3 = gb2.c     // Catch:{ all -> 0x005c }
            boolean r3 = r3.d(r7)     // Catch:{ all -> 0x005c }
            java.lang.String r4 = "promos_skipped_on_first_start"
            if (r2 != 0) goto L_0x0045
            boolean r5 = r7.f1650l5     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x0045
            boolean r5 = EX1.a()     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0045
            r5 = 0
            boolean r5 = r1.mo9025a((java.lang.String) r4, (boolean) r5)     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0045
            cF2 r5 = org.chromium.chrome.browser.p010vr.VrModuleProvider.m3290a()     // Catch:{ all -> 0x005c }
            r5.e()     // Catch:{ all -> 0x005c }
            gF2 r5 = org.chromium.chrome.browser.p010vr.VrModuleProvider.m3293c()     // Catch:{ all -> 0x005c }
            android.content.Intent r6 = r7.getIntent()     // Catch:{ all -> 0x005c }
            boolean r5 = r5.a(r7, r6)     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x0045
            if (r3 != 0) goto L_0x0045
            boolean r2 = r7.mo8278u2()     // Catch:{ all -> 0x005c }
            goto L_0x0052
        L_0x0045:
            r3 = 1
            android.content.SharedPreferences r1 = r1.f2175a     // Catch:{ all -> 0x005c }
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch:{ all -> 0x005c }
            r1.putBoolean(r4, r3)     // Catch:{ all -> 0x005c }
            r1.apply()     // Catch:{ all -> 0x005c }
        L_0x0052:
            yB2.a(r7, r2)     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x005b
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x005b:
            return
        L_0x005c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x005e }
        L_0x005e:
            r2 = move-exception
            if (r0 == 0) goto L_0x0064
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0064:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8212W1():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r0 != null) goto L_0x0017;
     */
    /* renamed from: X1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8213X1() {
        /*
            r3 = this;
            java.lang.String r0 = "ChromeTabbedActivity.maybeGetFeedAppLifecycleAndMaybeCreatePageViewObserver"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            java.lang.String r1 = "InterestFeedContentSuggestions"
            org.chromium.chrome.browser.ChromeFeatureList.m1784a(r1)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0011
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0011:
            return
        L_0x0012:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r2 = move-exception
            if (r0 == 0) goto L_0x001a
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8213X1():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        if (r0 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        throw r2;
     */
    /* renamed from: Y1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8214Y1() {
        /*
            r6 = this;
            java.lang.String r0 = "ChromeTabbedActivity.addOverviewModeObserver"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.chrome.browser.ChromeTabbedActivity$j r1 = r6.f1661w5     // Catch:{ all -> 0x0049 }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r2 = r6.f1631S4     // Catch:{ all -> 0x0049 }
            TE1 r3 = r1.a     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x0011
            r3.a(r1)     // Catch:{ all -> 0x0049 }
        L_0x0011:
            r1.a = r2     // Catch:{ all -> 0x0049 }
            TE1 r3 = r1.a     // Catch:{ all -> 0x0049 }
            r3.b(r1)     // Catch:{ all -> 0x0049 }
            aO0 r1 = r1.c     // Catch:{ all -> 0x0049 }
            r1.a(r2)     // Catch:{ all -> 0x0049 }
            org.chromium.chrome.browser.ChromeTabbedActivity$j r1 = r6.f1661w5     // Catch:{ all -> 0x0049 }
            org.chromium.base.ObserverList r1 = r1.b     // Catch:{ all -> 0x0049 }
            r1.mo7868a(r6)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = "TabEngagementReportingAndroid"
            boolean r1 = org.chromium.chrome.browser.ChromeFeatureList.m1784a(r1)     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0042
            Ux2 r1 = new Ux2     // Catch:{ all -> 0x0049 }
            org.chromium.chrome.browser.tabmodel.TabModelSelector r2 = r6.mo8095d1()     // Catch:{ all -> 0x0049 }
            Q52 r3 = r6.mo8769O()     // Catch:{ all -> 0x0049 }
            org.chromium.chrome.browser.compositor.layouts.OverviewModeBehavior r4 = r6.mo8054V0()     // Catch:{ all -> 0x0049 }
            Ox2 r5 = new Ox2     // Catch:{ all -> 0x0049 }
            r5.<init>()     // Catch:{ all -> 0x0049 }
            r1.<init>(r2, r3, r4, r5)     // Catch:{ all -> 0x0049 }
        L_0x0042:
            if (r0 == 0) goto L_0x0048
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0048:
            return
        L_0x0049:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x004b }
        L_0x004b:
            r2 = move-exception
            if (r0 == 0) goto L_0x0051
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0051:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8214Y1():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a9, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00aa, code lost:
        if (r0 != null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00af, code lost:
        throw r2;
     */
    /* renamed from: Z1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8215Z1() {
        /*
            r6 = this;
            java.lang.String r0 = "ChromeTabbedActivity.emmxInitialize"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            boolean r1 = my1.a()     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0062
            org.chromium.chrome.browser.tab.Tab r1 = r6.mo8172v0()     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0062
            dx2 r1 = r6.f1635W4     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0062
            dx2 r1 = r6.f1635W4     // Catch:{ all -> 0x00a7 }
            r2 = 0
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r1.b(r2)     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0062
            dx2 r1 = r6.f1635W4     // Catch:{ all -> 0x00a7 }
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r1.b(r2)     // Catch:{ all -> 0x00a7 }
            org.chromium.chrome.browser.profiles.Profile r1 = r1.mo9434g()     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x0062
            dx2 r1 = r6.f1635W4     // Catch:{ all -> 0x00a7 }
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r1.b(r2)     // Catch:{ all -> 0x00a7 }
            org.chromium.chrome.browser.profiles.Profile r1 = r1.mo9434g()     // Catch:{ all -> 0x00a7 }
            Ay1 r3 = new Ay1     // Catch:{ all -> 0x00a7 }
            r3.<init>(r1)     // Catch:{ all -> 0x00a7 }
            r6.f1654p5 = r3     // Catch:{ all -> 0x00a7 }
            org.chromium.chrome.browser.microsoft_signin.BingEnterpriseManager r1 = org.chromium.chrome.browser.microsoft_signin.BingEnterpriseManager.h()     // Catch:{ all -> 0x00a7 }
            Ay1 r3 = r6.f1654p5     // Catch:{ all -> 0x00a7 }
            java.util.List r4 = r1.d     // Catch:{ all -> 0x00a7 }
            if (r4 == 0) goto L_0x0062
            r4.add(r3)     // Catch:{ Exception -> 0x004a }
            goto L_0x0050
        L_0x004a:
            r4 = move-exception
            kI r5 = qI.a     // Catch:{ all -> 0x00a7 }
            r5.a(r4)     // Catch:{ all -> 0x00a7 }
        L_0x0050:
            r1.e()     // Catch:{ all -> 0x00a7 }
            java.util.List r4 = r1.a     // Catch:{ all -> 0x00a7 }
            if (r4 == 0) goto L_0x0062
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x00a7 }
            if (r4 != 0) goto L_0x0062
            java.util.List r1 = r1.a     // Catch:{ all -> 0x00a7 }
            r3.onCookiesChangedListener(r1, r2)     // Catch:{ all -> 0x00a7 }
        L_0x0062:
            org.chromium.chrome.browser.extensions.ExtensionsManager r1 = org.chromium.chrome.browser.extensions.ExtensionsManager.b()     // Catch:{ all -> 0x00a7 }
            r1.a()     // Catch:{ all -> 0x00a7 }
            android.content.SharedPreferences r1 = QN0.a     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = "honey_extension_id"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a7 }
            boolean r3 = r1.isEmpty()     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x007a
            goto L_0x0090
        L_0x007a:
            org.chromium.chrome.browser.extensions.ExtensionsManager r3 = org.chromium.chrome.browser.extensions.ExtensionsManager.b()     // Catch:{ all -> 0x00a7 }
            boolean r3 = r3.a(r1)     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0090
            org.chromium.chrome.browser.extensions.ExtensionsManager r3 = org.chromium.chrome.browser.extensions.ExtensionsManager.b()     // Catch:{ all -> 0x00a7 }
            gu1 r4 = new gu1     // Catch:{ all -> 0x00a7 }
            r4.<init>(r6, r2)     // Catch:{ all -> 0x00a7 }
            r3.a(r1, r4)     // Catch:{ all -> 0x00a7 }
        L_0x0090:
            Jo0 r1 = r6.f1657s5     // Catch:{ all -> 0x00a7 }
            if (r1 != 0) goto L_0x009b
            Jo0 r1 = new Jo0     // Catch:{ all -> 0x00a7 }
            r1.<init>()     // Catch:{ all -> 0x00a7 }
            r6.f1657s5 = r1     // Catch:{ all -> 0x00a7 }
        L_0x009b:
            Jo0 r1 = r6.f1657s5     // Catch:{ all -> 0x00a7 }
            r1.b()     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x00a6
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x00a6:
            return
        L_0x00a7:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            r2 = move-exception
            if (r0 == 0) goto L_0x00af
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x00af:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8215Z1():void");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: a0 */
    public void mo8078a0() {
        super.mo8078a0();
        if (!isFinishing()) {
            getWindow().setSoftInputMode(19);
            this.f1632T4 = (ViewGroup) findViewById(16908290);
            this.f1634V4 = findViewById(ox0.control_container);
            this.f1630R4 = new Bt2(this, this.f1635W4, new Lt1(this));
            if (this.f1626N4 == null) {
                this.f1626N4 = new g(this, (a) null);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("ACTION_TAB_MODEL_CHANGED");
            Y5 a = Y5.a(RN0.a);
            a.a(this.f1626N4);
            a.a(this.f1626N4, intentFilter);
            if (this.f1662x5 == null) {
                this.f1662x5 = new l(this, (a) null);
            }
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("SETTINGS_CHANGED");
            Y5 a2 = Y5.a(RN0.a);
            a2.a(this.f1662x5);
            a2.a(this.f1662x5, intentFilter2);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: a2 */
    public final void mo8227a2() {
        Tab v0 = mo8172v0();
        if (v0 != null && v0.mo9302K() != null && v0.isUserInteractable()) {
            this.f1647i5 = new Fu1(v0.mo9411z(), this, v0.mo9302K().mo9742F(), 0);
            this.f1647i5.q3 = new Rt1(this);
            this.f1647i5.a(findViewById(ox0.navigation_popup_anchor_stub));
        }
    }

    public void addViewObscuringAllTabs(View view) {
        super.addViewObscuringAllTabs(view);
        mo8204A2();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: b0 */
    public void mo8088b0() {
        int intExtra;
        super.mo8088b0();
        boolean z = true;
        if (!LibraryLoader.f1501h.mo7904a()) {
            R72 x0 = mo8176x0();
            x0.d = ".Tabbed";
            x0.g = true;
        }
        CommandLine c = CommandLine.m1384c();
        if (c.mo7859c("enable-test-intents") && getIntent() != null && getIntent().hasExtra("render_process_limit") && (intExtra = getIntent().getIntExtra("render_process_limit", -1)) != -1) {
            StringBuilder a = Eo.a("--renderer-process-limit=");
            a.append(Integer.toString(intExtra));
            c.mo7856a(new String[]{a.toString()});
        }
        supportRequestWindowFeature(10);
        if (!(Build.VERSION.SDK_INT >= 21 || getIntent().getData() == null || (getIntent().getFlags() & 1048576) == 0)) {
            if (Wd2.c().getLong("timestampOfInstall", -1) != -1) {
                z = false;
            }
            if (z) {
                getIntent().setData((Uri) null);
            }
        }
        M02 a2 = M02.a();
        a2.a.add(this.f1660v5);
    }

    /* renamed from: b2 */
    public CollectionsBridge.a mo8231b2() {
        if (this.f1638Z4 == null) {
            this.f1638Z4 = new CollectionsBridge.a(this.f1635W4);
        }
        return this.f1638Z4;
    }

    /* renamed from: c2 */
    public XA1 mo8234c2() {
        if (this.f1603n4 == null) {
            this.f1603n4 = new XA1(this);
        }
        return this.f1603n4;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v4.app.FragmentActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0175 A[SYNTHETIC, Splitter:B:54:0x0175] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01e8  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8093d() {
        /*
            r8 = this;
            super.mo8093d()
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r0 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r0 = r0.mo8866A()
            org.chromium.base.CommandLine r1 = org.chromium.base.CommandLine.m1384c()
            java.lang.String r2 = "proxy-pac-url"
            java.lang.String r1 = r1.mo7857b((java.lang.String) r2)
            java.lang.String r2 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2252e()
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x0021
            boolean r5 = java.util.Objects.equals(r2, r1)
            if (r5 == 0) goto L_0x0029
        L_0x0021:
            if (r0 != 0) goto L_0x0045
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0045
        L_0x0029:
            Sf0 r1 = new Sf0
            if (r0 == 0) goto L_0x0034
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0034
            r3 = 1
        L_0x0034:
            r1.<init>(r3)
            com.microsoft.managedbehavior.disablefeatures.DisableFeaturesRestartDialogFragment r0 = com.microsoft.managedbehavior.disablefeatures.DisableFeaturesRestartDialogFragment.b(r1)
            android.support.v4.app.FragmentManager r1 = r8.getSupportFragmentManager()
            java.lang.String r2 = "ProxyConfigurationDialogDataProvider"
            r0.show(r1, r2)
            return
        L_0x0045:
            org.chromium.chrome.browser.snackbar.SnackbarManager r0 = r8.getSnackbarManager()
            android.content.Intent r1 = r8.getIntent()
            android.content.Intent r2 = r8.getIntent()
            boolean r5 = Sa0.a
            java.lang.String r6 = "fromX"
            if (r5 != 0) goto L_0x0083
            if (r2 == 0) goto L_0x0083
            boolean r5 = r2.hasExtra(r6)
            if (r5 == 0) goto L_0x0083
            java.lang.String r5 = r2.getStringExtra(r6)
            java.lang.String r7 = ""
            boolean r5 = r5.equals(r7)
            if (r5 != 0) goto L_0x0083
            java.lang.String r5 = "pkgname"
            boolean r7 = r2.hasExtra(r5)
            if (r7 == 0) goto L_0x0081
            java.lang.String r2 = r2.getStringExtra(r5)
            java.lang.String r5 = r8.getPackageName()
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L_0x0083
        L_0x0081:
            r2 = 1
            goto L_0x0084
        L_0x0083:
            r2 = 0
        L_0x0084:
            if (r2 != 0) goto L_0x0087
            goto L_0x00de
        L_0x0087:
            java.lang.String r1 = r1.getStringExtra(r6)
            int r2 = wx0.default_browser_snackbar_message
            java.lang.String r2 = r8.getString(r2)
            Ra0 r5 = new Ra0
            r5.<init>(r8)
            r6 = -1
            rt2 r2 = rt2.a(r2, r5, r4, r6)
            int r5 = wx0.default_browser_snackbar_action
            java.lang.String r5 = r8.getString(r5)
            android.content.res.Resources r6 = r8.getResources()
            int r7 = tx0.app_icon
            android.graphics.drawable.Drawable r6 = ON0.b(r6, r7)
            r2.l = r6
            android.content.res.Resources r6 = r8.getResources()
            int r7 = jx0.default_browser_snackbar_background_color
            int r6 = ON0.a(r6, r7)
            r2.f = r6
            android.content.res.Resources r6 = r8.getResources()
            int r7 = jx0.default_browser_snackbar_message_color
            int r6 = ON0.a(r6, r7)
            r2.h = r6
            android.content.res.Resources r6 = r8.getResources()
            int r7 = jx0.edge_primary_color
            int r6 = ON0.a(r6, r7)
            r2.i = r6
            r2.d = r5
            r2.e = r1
            r1 = 5000(0x1388, float:7.006E-42)
            r2.k = r1
            r0.a(r2)
            Sa0.a = r4
        L_0x00de:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0166
            org.chromium.chrome.browser.profiles.Profile r0 = org.chromium.chrome.browser.profiles.Profile.m2911j()
            boolean r0 = r0.mo9207g()
            if (r0 != 0) goto L_0x00f0
            goto L_0x0166
        L_0x00f0:
            android.content.Context r0 = RN0.a
            java.lang.String r1 = "activity"
            java.lang.Object r1 = r0.getSystemService(r1)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.List r1 = r1.getAppTasks()
            java.util.Iterator r1 = r1.iterator()
        L_0x010b:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0132
            java.lang.Object r5 = r1.next()
            android.app.ActivityManager$AppTask r5 = (android.app.ActivityManager.AppTask) r5
            android.app.ActivityManager$RecentTaskInfo r6 = TM1.b(r5)
            if (r6 != 0) goto L_0x011e
            goto L_0x010b
        L_0x011e:
            java.lang.String r5 = TM1.a(r5, r0)
            boolean r5 = m1792c((java.lang.String) r5)
            if (r5 == 0) goto L_0x010b
            int r5 = r6.id
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r2.add(r5)
            goto L_0x010b
        L_0x0132:
            int r0 = r2.size()
            if (r0 != 0) goto L_0x013a
            r0 = 1
            goto L_0x0167
        L_0x013a:
            java.util.List r0 = org.chromium.base.ApplicationStatus.m1366b()
            java.util.Iterator r0 = r0.iterator()
        L_0x0142:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x015a
            java.lang.Object r1 = r0.next()
            android.app.Activity r1 = (android.app.Activity) r1
            int r1 = r1.getTaskId()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.remove(r1)
            goto L_0x0142
        L_0x015a:
            int r0 = r2.size()
            if (r0 != 0) goto L_0x0166
            boolean r0 = P02.c()
            r0 = r0 ^ r4
            goto L_0x0167
        L_0x0166:
            r0 = 0
        L_0x0167:
            if (r0 == 0) goto L_0x0175
            org.chromium.chrome.browser.profiles.Profile r0 = org.chromium.chrome.browser.profiles.Profile.m2911j()
            org.chromium.chrome.browser.profiles.Profile r0 = r0.mo9202b()
            r0.mo9198a()
            goto L_0x018d
        L_0x0175:
            boolean r0 = org.chromium.chrome.browser.cookies.CookiesFetcher.m1994b()     // Catch:{ RuntimeException -> 0x0187 }
            if (r0 == 0) goto L_0x017c
            goto L_0x018d
        L_0x017c:
            VI1 r0 = new VI1     // Catch:{ RuntimeException -> 0x0187 }
            r0.<init>()     // Catch:{ RuntimeException -> 0x0187 }
            java.util.concurrent.Executor r1 = AP0.g     // Catch:{ RuntimeException -> 0x0187 }
            r0.a(r1)     // Catch:{ RuntimeException -> 0x0187 }
            goto L_0x018d
        L_0x0187:
            r0 = move-exception
            kI r1 = qI.a
            r1.a(r0)
        L_0x018d:
            org.chromium.chrome.browser.locale.LocaleManager r0 = r8.f1643e5
            org.chromium.chrome.browser.snackbar.SnackbarManager r1 = r8.getSnackbarManager()
            r0.mo8818a((org.chromium.chrome.browser.snackbar.SnackbarManager) r1)
            org.chromium.chrome.browser.locale.LocaleManager r0 = r8.f1643e5
            r0.mo8838q()
            com.microsoft.managedbehavior.CitrixManager r0 = com.microsoft.managedbehavior.CitrixManager.e.a
            org.chromium.chrome.browser.snackbar.SnackbarManager r1 = r8.getSnackbarManager()
            r0.mo4432a((org.chromium.chrome.browser.snackbar.SnackbarManager) r1)
            android.content.SharedPreferences r0 = QN0.a
            java.lang.String r1 = "hasShareUsageDataAskCompleted"
            boolean r0 = r0.getBoolean(r1, r3)
            if (r0 != 0) goto L_0x01c7
            boolean r0 = com.microsoft.managedbehavior.MAMEdgeManager.m1307e()
            if (r0 == 0) goto L_0x01b5
            goto L_0x01c7
        L_0x01b5:
            com.microsoft.ruby.share_usage_data.ShareUsageDataDialog r0 = new com.microsoft.ruby.share_usage_data.ShareUsageDataDialog
            r0.<init>()
            android.support.v4.app.FragmentManager r1 = r8.getSupportFragmentManager()
            java.lang.Class<com.microsoft.ruby.share_usage_data.ShareUsageDataDialog> r2 = com.microsoft.ruby.share_usage_data.ShareUsageDataDialog.class
            java.lang.String r2 = r2.getSimpleName()
            r0.show(r1, r2)
        L_0x01c7:
            boolean r0 = HU1.g()
            if (r0 == 0) goto L_0x01e4
            org.chromium.chrome.browser.ChromeTabbedActivity$k r0 = r8.f1656r5
            if (r0 == 0) goto L_0x01d5
            int r0 = r0.c
            if (r0 == r4) goto L_0x01e4
        L_0x01d5:
            org.chromium.chrome.browser.ChromeTabbedActivity$k r0 = new org.chromium.chrome.browser.ChromeTabbedActivity$k
            r1 = 0
            r0.<init>(r1)
            r8.f1656r5 = r0
            org.chromium.chrome.browser.ChromeTabbedActivity$k r0 = r8.f1656r5
            java.util.concurrent.ExecutorService r1 = Us0.d
            r0.a(r1)
        L_0x01e4:
            Jo0 r0 = r8.f1657s5
            if (r0 != 0) goto L_0x01ef
            Jo0 r0 = new Jo0
            r0.<init>()
            r8.f1657s5 = r0
        L_0x01ef:
            Jo0 r0 = r8.f1657s5
            r0.c(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8093d():void");
    }

    /* renamed from: d2 */
    public HubManager mo8236d2() {
        return this.f1653o5;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean a = xu1.a(keyEvent, this, this.f1641c5);
        return a != null ? a.booleanValue() : super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: e1 */
    public int mo8099e1() {
        return ChromeApplication.m1775e().h().a(this);
    }

    /* renamed from: e2 */
    public void mo8237e2() {
        HubManager hubManager = this.f1653o5;
        HubFragment a = hubManager.a.a("HubFragment");
        if (a != null) {
            HubUIManager p = a.p();
            HashMap hashMap = hubManager.f;
            if (hashMap == null) {
                hubManager.f = new HashMap();
            } else {
                hashMap.clear();
            }
            BaseFragment currentFragment = p.d().getCurrentFragment();
            if (currentFragment != null) {
                currentFragment.a(hubManager.f);
                hubManager.f.put("hub_previous_page_type", a.getCurrentPageType());
            }
            hubManager.a();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8100f() {
        /*
            r7 = this;
            W72 r0 = r7.f1628P4
            r0.a()
            super.mo8100f()
            org.chromium.chrome.browser.ChromeTabbedActivity$j r0 = r7.f1661w5
            boolean r0 = r0.overviewVisible()
            tt1 r1 = r7.f1649k5
            long r1 = r1.e()
            r3 = 0
            r4 = -1
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x001c
            goto L_0x0034
        L_0x001c:
            r4 = -1
            java.lang.String r5 = "TabSwitcherOnReturn"
            java.lang.String r6 = "tab_switcher_on_return_time_ms"
            int r4 = org.chromium.chrome.browser.ChromeFeatureList.nativeGetFieldTrialParamByFeatureAsInt(r5, r6, r4)
            if (r4 >= 0) goto L_0x0028
            goto L_0x0034
        L_0x0028:
            long r4 = (long) r4
            long r1 = r1 + r4
            long r4 = java.lang.System.currentTimeMillis()
            int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x0034
            r1 = 1
            goto L_0x0035
        L_0x0034:
            r1 = 0
        L_0x0035:
            if (r1 == 0) goto L_0x005a
            android.content.Intent r1 = r7.getIntent()
            boolean r1 = r7.mo8240g((android.content.Intent) r1)
            if (r1 == 0) goto L_0x005a
            if (r0 != 0) goto L_0x005a
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r7.mo8041M0()
            if (r0 == 0) goto L_0x0056
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r7.mo8041M0()
            int r0 = r0.getCount()
            java.lang.String r1 = "Tabs.TabCountOnStartScreenShown"
            org.chromium.base.metrics.RecordHistogram.m1547c((java.lang.String) r1, (int) r0)
        L_0x0056:
            r7.mo8284z2()
            goto L_0x0077
        L_0x005a:
            org.chromium.chrome.browser.tab.Tab r1 = r7.mo8172v0()
            if (r1 != 0) goto L_0x0065
            if (r0 != 0) goto L_0x0065
            r7.mo8284z2()
        L_0x0065:
            boolean r0 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2223q()
            if (r0 == 0) goto L_0x0077
            r7.mo8284z2()
            java.lang.String r0 = "DualIdentityManager"
            java.lang.String r1 = "resetNavigateToTabCenter"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r0, r1)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1768h = r3
        L_0x0077:
            android.content.Intent r0 = r7.getIntent()
            boolean r0 = r7.mo8240g((android.content.Intent) r0)
            if (r0 == 0) goto L_0x0084
            r7.mo8151q1()
        L_0x0084:
            java.util.concurrent.ExecutorService r0 = Us0.d
            java.lang.Runnable r1 = Ht1.a
            r0.execute(r1)
            java.util.concurrent.ExecutorService r0 = Us0.f
            java.lang.Runnable r1 = It1.a
            r0.execute(r1)
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r0 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r1 = ve0.b()
            if (r1 != 0) goto L_0x00c0
            boolean r1 = r0.mo8932y()
            if (r1 == 0) goto L_0x00c0
            boolean r0 = r0.mo8867B()
            if (r0 != 0) goto L_0x00c0
            Wf0 r0 = new Wf0
            r0.<init>()
            com.microsoft.managedbehavior.disablefeatures.DisableFeaturesRestartDialogFragment r0 = com.microsoft.managedbehavior.disablefeatures.DisableFeaturesRestartDialogFragment.b(r0)
            android.app.Activity r1 = org.chromium.base.ApplicationStatus.f1396d
            boolean r2 = r1 instanceof android.support.v7.app.AppCompatActivity
            if (r2 == 0) goto L_0x00c0
            android.support.v7.app.AppCompatActivity r1 = (android.support.v7.app.AppCompatActivity) r1
            android.support.v4.app.FragmentManager r1 = r1.getSupportFragmentManager()
            java.lang.String r2 = "BlockedMsaDialogDataProvider"
            r0.show(r1, r2)
        L_0x00c0:
            r7.mo8782d0()
            boolean r0 = YA1.a()
            if (r0 == 0) goto L_0x00cc
            r7.mo8234c2()
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8100f():void");
    }

    /* renamed from: f2 */
    public void mo8238f2() {
        this.f1631S4.hideOverview(true);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: g */
    public final void mo8239g(int i) {
        View findViewById = findViewById(ox0.incognito_not_support_tips);
        if (i == 0) {
            mo8241g2();
            this.f1635W4.b(false);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            mo8089b1().b(true);
        } else if (i == 1) {
            if (PrefServiceBridge.m2758o0().mo9051K()) {
                this.f1635W4.b(true);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
                mo8089b1().b(true);
            } else {
                if (findViewById != null) {
                    findViewById.setVisibility(0);
                }
                TextView textView = (TextView) findViewById(ox0.inprivate_disable_text);
                AppCompatImageView findViewById2 = findViewById(ox0.inprivate_disabled_image);
                TextView textView2 = (TextView) findViewById(ox0.family_learn_more);
                if (Jo0.e()) {
                    textView.setText(wx0.family_inprivate_disable);
                    findViewById2.setImageDrawable(getResources().getDrawable(lx0.family_inprivate_disabled));
                    textView2.setVisibility(0);
                    FamilyUtils.a(this);
                } else {
                    textView.setText(wx0.inprivate_not_support);
                    findViewById2.setImageDrawable(getResources().getDrawable(lx0.inprivate_disabled));
                    textView2.setVisibility(8);
                }
                mo8089b1().b(false);
            }
            mo8241g2();
        } else if (i == 2) {
            this.f1635W4.b(false);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            rv2 rv2 = this.f1655q5;
            if (rv2 == null) {
                this.f1655q5 = new rv2(this, new nv2(this), true);
            } else {
                rv2.a(true);
            }
            mo8256n(true);
            mo8103f1().f2309e.a.mo9561p(true);
            AnaheimUtils.a("tabCenterSyncedTabsClick", TelemetryConstants.Type.Action, new String[0]);
            hH2 hh2 = this.f1631S4.K3;
            if (hh2 != null) {
                hh2.b(true);
            }
        } else if (i == 3) {
            mo8119k0();
        }
        ToolbarManager f1 = mo8103f1();
        if (f1 != null) {
            f1.mo9483n();
        }
    }

    /* renamed from: g2 */
    public void mo8241g2() {
        mo8256n(false);
        hH2 hh2 = this.f1631S4.K3;
        if (hh2 != null) {
            hh2.t = false;
            hh2.x();
        }
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* renamed from: h */
    public final void mo8243h(Intent intent) {
        W72 w72 = this.f1628P4;
        long e = this.f1649k5.e();
        long j = -1;
        if (e != -1) {
            j = System.currentTimeMillis() - e;
        }
        if (j < 86400000) {
            int i = (j > 43200000 ? 1 : (j == 43200000 ? 0 : -1));
        }
        if (!w72.e) {
            w72.k = j;
            ApplicationStatus.m1364a((ApplicationStatus.ActivityStateListener) w72, (Activity) w72.a);
            w72.e = true;
            w72.b.postDelayed(w72.c, 10000);
            w72.n = new V72(w72, w72.a.mo8095d1());
            w72.a(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0083 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0157  */
    /* renamed from: h1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8111h1() {
        /*
            r10 = this;
            boolean r0 = r10.f1641c5
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0019
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r0 = r10.mo8020D0()
            if (r0 == 0) goto L_0x0017
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r0 = r10.mo8020D0()
            boolean r0 = r0.s()
            if (r0 == 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            return r1
        L_0x0019:
            w32 r0 = r10.mo8050S0()
            B32 r0 = (B32) r0
            H32 r0 = r0.a
            boolean r3 = r0.q()
            r4 = 3
            if (r3 == 0) goto L_0x0041
            boolean r3 = r0.b(r1)
            if (r3 != 0) goto L_0x003c
            boolean r3 = r0.b(r4)
            if (r3 != 0) goto L_0x003c
            r3 = 11
            boolean r3 = r0.b(r3)
            if (r3 == 0) goto L_0x0041
        L_0x003c:
            r0.r()
            r0 = 1
            goto L_0x0042
        L_0x0041:
            r0 = 0
        L_0x0042:
            if (r0 == 0) goto L_0x0045
            return r1
        L_0x0045:
            boolean r0 = r10.mo8249k(r2)
            if (r0 == 0) goto L_0x004c
            return r1
        L_0x004c:
            XA1 r0 = r10.f1603n4
            if (r0 == 0) goto L_0x0080
            boolean r3 = r0.h
            if (r3 == 0) goto L_0x0062
            oC1 r3 = r0.y
            boolean r3 = r3.c()
            if (r3 == 0) goto L_0x0062
            oC1 r0 = r0.y
            r0.a(r2)
            goto L_0x007e
        L_0x0062:
            boolean r3 = r0.g()
            if (r3 == 0) goto L_0x0080
            HC1 r3 = r0.c
            boolean r5 = r3.d
            if (r5 == 0) goto L_0x0072
            r3.a(r2)
            goto L_0x007e
        L_0x0072:
            int r3 = r3.a
            if (r3 == r4) goto L_0x0077
            goto L_0x0080
        L_0x0077:
            aC1 r0 = r0.i
            android.widget.TextView r0 = r0.k
            r0.performClick()
        L_0x007e:
            r0 = 1
            goto L_0x0081
        L_0x0080:
            r0 = 0
        L_0x0081:
            if (r0 == 0) goto L_0x0084
            return r1
        L_0x0084:
            org.chromium.chrome.browser.tab.Tab r0 = r10.mo8172v0()
            boolean r3 = r10.mo8166t0()
            if (r3 == 0) goto L_0x0095
            r0 = 7
            java.lang.String r2 = "Exited fullscreen"
            r10.mo8221a((java.lang.String) r2, (int) r0)
            return r1
        L_0x0095:
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r3 = r10.mo8020D0()
            if (r3 == 0) goto L_0x00a6
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r3 = r10.mo8020D0()
            boolean r3 = r3.s()
            if (r3 == 0) goto L_0x00a6
            return r1
        L_0x00a6:
            Xa2 r3 = r10.f1640b5
            cb2 r3 = r3.d
            r5 = 5
            if (r3 == 0) goto L_0x00b7
            BI3 r6 = r3.b
            if (r6 != 0) goto L_0x00b2
            goto L_0x00b7
        L_0x00b2:
            r3.a(r5)
            r3 = 1
            goto L_0x00b8
        L_0x00b7:
            r3 = 0
        L_0x00b8:
            if (r3 == 0) goto L_0x00bb
            return r1
        L_0x00bb:
            if (r0 != 0) goto L_0x00c6
            java.lang.String r0 = "currentTab is null"
            r10.mo8221a((java.lang.String) r0, (int) r5)
            r10.moveTaskToBack(r1)
            return r1
        L_0x00c6:
            org.chromium.chrome.browser.ChromeTabbedActivity$j r3 = r10.f1661w5
            boolean r3 = r3.overviewVisible()
            r5 = 6
            if (r3 == 0) goto L_0x00e0
            boolean r3 = r10.mo8775W()
            if (r3 != 0) goto L_0x00e0
            java.lang.String r0 = "Hid overview"
            r10.mo8221a((java.lang.String) r0, (int) r5)
            org.chromium.chrome.browser.ChromeTabbedActivity$j r0 = r10.f1661w5
            r0.hideOverview(r1)
            return r1
        L_0x00e0:
            boolean r3 = Yx1.a
            if (r3 == 0) goto L_0x00ed
            com.microsoft.bing.instantsearchsdk.api.InstantSearchManager r3 = com.microsoft.bing.instantsearchsdk.api.InstantSearchManager.getInstance()
            boolean r3 = r3.handleBackKey()
            goto L_0x00ee
        L_0x00ed:
            r3 = 0
        L_0x00ee:
            if (r3 == 0) goto L_0x00f1
            return r1
        L_0x00f1:
            org.chromium.chrome.browser.toolbar.ToolbarManager r3 = r10.mo8103f1()
            boolean r3 = r3.mo9464a()
            if (r3 == 0) goto L_0x0103
            r0 = 8
            java.lang.String r2 = "Navigating backward"
            r10.mo8221a((java.lang.String) r2, (int) r0)
            return r1
        L_0x0103:
            int r3 = r0.mo9397s()
            java.lang.String r6 = r0.getUrl()
            java.lang.String r7 = "https://support.google.com/chrome/"
            boolean r6 = r6.startsWith(r7)
            r7 = 2
            if (r3 != r7) goto L_0x0123
            if (r6 == 0) goto L_0x0123
            org.chromium.chrome.browser.tabmodel.TabModel r2 = r10.mo8041M0()
            r2.mo9433e(r0)
            java.lang.String r0 = "Closed tab for help URL"
            r10.mo8221a((java.lang.String) r0, (int) r1)
            return r1
        L_0x0123:
            boolean r3 = r10.mo8087b((org.chromium.chrome.browser.tab.Tab) r0)
            if (r3 == 0) goto L_0x0154
            sO0 r6 = r0.mo9300I()
            java.lang.Class r8 = Ov2.c
            rO0 r6 = r6.a(r8)
            Ov2 r6 = (Ov2) r6
            if (r6 != 0) goto L_0x0138
            goto L_0x014e
        L_0x0138:
            android.content.Context r8 = RN0.a
            java.lang.String r8 = r8.getPackageName()
            int r9 = r0.mo9397s()
            if (r9 != r1) goto L_0x014e
            java.lang.String r6 = r6.b
            boolean r6 = android.text.TextUtils.equals(r6, r8)
            if (r6 != 0) goto L_0x014e
            r6 = 1
            goto L_0x014f
        L_0x014e:
            r6 = 0
        L_0x014f:
            if (r6 == 0) goto L_0x0152
            goto L_0x0154
        L_0x0152:
            r6 = 0
            goto L_0x0155
        L_0x0154:
            r6 = 1
        L_0x0155:
            if (r6 == 0) goto L_0x017e
            if (r3 == 0) goto L_0x016b
            java.lang.String r2 = "Minimized and closed tab"
            r10.mo8221a((java.lang.String) r2, (int) r4)
            N72 r2 = r10.f1627O4
            int r3 = r2.a
            if (r3 == r5) goto L_0x0165
            goto L_0x0167
        L_0x0165:
            r2.a = r1
        L_0x0167:
            r10.mo8235d(r0)
            return r1
        L_0x016b:
            java.lang.String r0 = "Minimized, kept tab"
            r10.mo8221a((java.lang.String) r0, (int) r7)
            N72 r0 = r10.f1627O4
            int r2 = r0.a
            if (r2 == r5) goto L_0x0177
            goto L_0x0179
        L_0x0177:
            r0.a = r1
        L_0x0179:
            r0 = 0
            r10.mo8235d(r0)
            return r1
        L_0x017e:
            if (r3 == 0) goto L_0x018e
            r3 = 4
            java.lang.String r4 = "Tab closed"
            r10.mo8221a((java.lang.String) r4, (int) r3)
            org.chromium.chrome.browser.tabmodel.TabModel r3 = r10.mo8041M0()
            r3.mo9425a(r0, r1, r2, r2)
            return r1
        L_0x018e:
            java.lang.String r0 = "Unhandled"
            r10.mo8221a((java.lang.String) r0, (int) r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8111h1():boolean");
    }

    /* renamed from: h2 */
    public boolean mo8244h2() {
        return this.f1603n4 != null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0050 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0051  */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo8245i(android.content.Intent r9) {
        /*
            r8 = this;
            org.chromium.chrome.browser.IntentHandler r9 = r8.f1578O3
            boolean r9 = r9.mo8287a()
            r0 = 0
            if (r9 != 0) goto L_0x000a
            return r0
        L_0x000a:
            tt1 r9 = r8.f1649k5
            boolean r1 = r9.a
            r2 = 1
            if (r1 != 0) goto L_0x0013
        L_0x0011:
            r9 = 0
            goto L_0x004e
        L_0x0013:
            long r3 = r9.e()
            r5 = -1
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x001e
            goto L_0x0011
        L_0x001e:
            long r3 = r9.g()
            r5 = 60000(0xea60, double:2.9644E-319)
            long r3 = r3 / r5
            int r1 = r9.c
            long r5 = (long) r1
            java.lang.String r1 = "InactivityTracker"
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0046
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r5[r0] = r3
            int r9 = r9.c
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r5[r2] = r9
            java.lang.String r9 = "Not launching NTP due to inactivity, background time: %d, launch delay: %d"
            VN0.b(r1, r9, r5)
            goto L_0x0011
        L_0x0046:
            java.lang.Object[] r9 = new java.lang.Object[r0]
            java.lang.String r3 = "Forcing NTP due to inactivity."
            VN0.b(r1, r3, r9)
            r9 = 1
        L_0x004e:
            if (r9 != 0) goto L_0x0051
            return r0
        L_0x0051:
            boolean r9 = r8.mo8151q1()
            if (r9 == 0) goto L_0x0062
            boolean r9 = r8.mo8775W()
            if (r9 != 0) goto L_0x0062
            org.chromium.chrome.browser.ChromeTabbedActivity$j r9 = r8.f1661w5
            r9.hideOverview(r0)
        L_0x0062:
            org.chromium.chrome.browser.tabmodel.TabModelSelector r9 = r8.mo8095d1()
            org.chromium.chrome.browser.tabmodel.TabModel r9 = r9.mo9443a((boolean) r0)
            r1 = 0
        L_0x006b:
            int r3 = r9.getCount()
            if (r1 >= r3) goto L_0x0096
            org.chromium.chrome.browser.tab.Tab r3 = r9.getTabAt(r1)
            boolean r4 = r3.mo9344b0()
            if (r4 == 0) goto L_0x0093
            boolean r4 = r3.mo9343b()
            if (r4 != 0) goto L_0x0093
            boolean r4 = r3.mo9348c()
            if (r4 != 0) goto L_0x0093
            org.chromium.chrome.browser.tab.Tab r1 = r8.mo8172v0()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0097
            r9 = 0
            goto L_0x00bb
        L_0x0093:
            int r1 = r1 + 1
            goto L_0x006b
        L_0x0096:
            r3 = 0
        L_0x0097:
            if (r3 == 0) goto L_0x00b1
            int r1 = r3.getId()
            int r4 = r9.getCount()
            r9.mo9427b(r1, r4)
            int r1 = r3.getId()
            int r1 = mx2.b(r9, r1)
            r3 = 3
            r9.mo9420a((int) r1, (int) r3)
            goto L_0x00ba
        L_0x00b1:
            Cw2 r9 = r8.m1834b((boolean) r0)
            java.lang.String r1 = SE2.a
            r9.a(r1, r2)
        L_0x00ba:
            r9 = 1
        L_0x00bb:
            if (r9 != 0) goto L_0x00be
            return r0
        L_0x00be:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8245i(android.content.Intent):boolean");
    }

    /* renamed from: i1 */
    public void mo8114i1() {
        super.mo8114i1();
        ou1.a.a(new Mt1(this));
    }

    /* renamed from: i2 */
    public boolean mo8246i2() {
        HubManager hubManager = this.f1653o5;
        if (hubManager != null) {
            return hubManager.b();
        }
        return false;
    }

    /* renamed from: j */
    public final void mo8247j(Intent intent) {
        int i = IntentHandler.m1924i(intent);
        if (i != -1) {
            this.f1653o5.a(HubManager.PageType.valueOfConstExpression(i));
            this.f1653o5.c();
            IntentHandler.m1914b(intent, -1);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: j2 */
    public final /* synthetic */ void mo8248j2() {
        RecordHistogram.m1552f("MemoryAndroid.DeviceMemoryClass", ((ActivityManager) getSystemService("activity")).getMemoryClass());
        try {
            LauncherShortcutActivity.m1943a(this);
            LauncherShortcutActivity.m1944b(this);
        } catch (Exception e) {
            VN0.a("ChromeTabbedActivity", "add shortcut error !", new Object[0]);
            qI.a.a(e);
        }
        new yu2(new zu2(), this.f1635W4).a(AP0.f);
        ve0.a(this);
    }

    /* renamed from: k */
    public final boolean mo8249k(boolean z) {
        if (!this.f1653o5.b()) {
            return false;
        }
        if (!z && HubFragmentBackHelper.a(this)) {
            return true;
        }
        this.f1653o5.a();
        return true;
    }

    /* renamed from: k0 */
    public void mo8119k0() {
        String str;
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        TabModelSelector d1 = mo8095d1();
        if (d1 == null || !d1.mo9452f()) {
            str = getResources().getString(wx0.ruby_tabs_clear_dialog_title);
            this.f1633U4 = "Tabs";
        } else {
            str = getResources().getString(wx0.ruby_tabs_clear_dialog_incognito_title);
            this.f1633U4 = "InPrivate";
        }
        alertDialogFragment.a(str, new St1(this, alertDialogFragment), new Tt1(this, alertDialogFragment));
        alertDialogFragment.show(getSupportFragmentManager(), (String) null);
        ss0.a("TabCenter", this.f1633U4, "CloseAllTabsAlert", new String[0]);
    }

    /* renamed from: k2 */
    public final /* synthetic */ void mo8250k2() {
        this.f1628P4.p = true;
        String str = SE2.a;
        m1834b(false).a(str, 2);
        ss0.b("codestart_homepage", "name", str);
        this.f1628P4.p = false;
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: l */
    public Lv1 mo8121l() {
        return new uw2(this, mo8174w0(), mo8770P(), mo8095d1(), mo8103f1(), getWindow().getDecorView(), this, this.f1661w5.c);
    }

    /* renamed from: l */
    public final void mo8251l(boolean z) {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: l0 */
    public Fs1 mo8122l0() {
        return new m(this, this);
    }

    /* renamed from: l2 */
    public final /* synthetic */ void mo8252l2() {
        ss0.a("MainFrame", "Toolbar", (String) null, TelemetryConstants.Actions.Click, "TabCenter", new String[0]);
        if (!mo8046P0().a.g) {
            if (!mo8103f1().f2306c4) {
                BE1 be1 = this.f1631S4.q3;
                if (!(be1 instanceof TileLayout) || be1.m) {
                    boolean q1 = mo8151q1();
                }
            }
            mo8094d(false);
            mo8284z2();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: m */
    public void mo8124m() {
        try {
            TraceEvent.m1472c("ChromeTabbedActivity.initializeCompositor", (String) null);
            super.mo8124m();
            this.f1643e5 = LocaleManager.getInstance();
            this.f1643e5.mo8821b(this, (Callback<Boolean>) null);
            this.f1635W4.a(mo8091c1());
            this.f1637Y4 = new b(this, this.f1635W4);
            this.f1653o5 = mo8024F0().g();
        } finally {
            TraceEvent.m1475z("ChromeTabbedActivity.initializeCompositor");
        }
    }

    /* renamed from: m1 */
    public void mo8126m1() {
        super.mo8126m1();
        if (mo8775W()) {
            return;
        }
        if (FeatureUtilities.d() || FeatureUtilities.m()) {
            mo8103f1().mo9465b();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: m2 */
    public final /* synthetic */ void mo8254m2() {
        mo8095d1().mo9443a(false).mo9432e();
        m1803L0().b();
        this.f1643e5.mo8821b(this, (Callback<Boolean>) null);
        boolean z = mo8103f1().f2306c4;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    public boolean moveTaskToBack(boolean z) {
        try {
            return ChromeTabbedActivity.super.moveTaskToBack(z);
        } catch (NullPointerException unused) {
            finish();
            return true;
        }
    }

    /* renamed from: n */
    public final void mo8256n(boolean z) {
        mo8103f1().f2309e.a.setInSyncedTabsView(z);
        mo8026G0().setCanInterceptTouchEvent(!z);
        FrameLayout frameLayout = (FrameLayout) findViewById(ox0.recent_tabs_page);
        int i = 0;
        if (frameLayout != null) {
            frameLayout.setVisibility(z ? 0 : 8);
        }
        if (z && mo8089b1() != null) {
            mo8089b1().b(false);
        }
        ToolbarPhone toolbarPhone = mo8103f1().f2309e.a;
        if (toolbarPhone instanceof ToolbarPhone) {
            ToolbarPhone toolbarPhone2 = toolbarPhone;
            if (toolbarPhone2.m0() != null) {
                View m0 = toolbarPhone2.m0();
                if (z || Og0.d()) {
                    i = 4;
                }
                m0.setVisibility(i);
            }
        }
    }

    /* renamed from: n0 */
    public IntentHandler.IntentHandlerDelegate mo8127n0() {
        return new h(this, (a) null);
    }

    /* renamed from: n2 */
    public final /* synthetic */ void mo8257n2() {
        this.f1653o5.c();
    }

    /* renamed from: o */
    public final void mo8258o(boolean z) {
        if (mo8103f1() != null) {
            ToolbarPhone toolbarPhone = mo8103f1().f2309e.a;
            if (toolbarPhone instanceof ToolbarPhone) {
                toolbarPhone.g0();
            }
        }
        this.f1631S4.b(z);
        pq2 pq2 = this.f1604o4;
        if (pq2 != null) {
            pq2.e.bringToFront();
        }
    }

    /* renamed from: o0 */
    public hD2 mo8129o0() {
        return new ww2(this);
    }

    /* renamed from: o2 */
    public final /* synthetic */ void mo8259o2() {
        mo8070a(mo8172v0());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        XA1 xa1 = this.f1603n4;
        if (xa1 != null && xa1.e()) {
            if (xa1.b(configuration.orientation)) {
                xa1.d();
                xa1.j();
            } else {
                xa1.c();
                xa1.k();
            }
        }
        LayoutManagerChrome layoutManagerChrome = this.f1631S4;
        if (layoutManagerChrome != null && layoutManagerChrome.overviewVisible()) {
            layoutManagerChrome.b(false);
        }
    }

    public void onHubClosed() {
        mo8206B2();
        FeedbackSessionManager.m2262a(false);
    }

    public void onHubShown() {
        FeedbackSessionManager.m2262a(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0111, code lost:
        r0 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r12, android.view.KeyEvent r13) {
        /*
            r11 = this;
            boolean r0 = r11.f1641c5
            if (r0 != 0) goto L_0x0009
            boolean r12 = org.chromium.chrome.browser.ChromeTabbedActivity.super.onKeyDown(r12, r13)
            return r12
        L_0x0009:
            r0 = 4
            r1 = 1
            r2 = 0
            if (r12 != r0) goto L_0x005b
            boolean r0 = r11.mo8775W()
            if (r0 != 0) goto L_0x005b
            CJ1 r0 = CJ1.c()
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x003f
            CJ1 r12 = CJ1.c()
            android.webkit.WebView r12 = r12.e
            if (r12 != 0) goto L_0x0028
            r12 = 0
            goto L_0x002c
        L_0x0028:
            boolean r12 = r12.isShown()
        L_0x002c:
            if (r12 == 0) goto L_0x0037
            aJ1 r12 = ZI1.a
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r2)
            r12.a(r13)
        L_0x0037:
            CJ1 r12 = CJ1.c()
            r12.a()
            return r1
        L_0x003f:
            java.lang.Runnable r0 = r11.f1646h5
            if (r0 != 0) goto L_0x004a
            Qt1 r0 = new Qt1
            r0.<init>(r11)
            r11.f1646h5 = r0
        L_0x004a:
            android.os.Handler r0 = r11.f2029d
            java.lang.Runnable r1 = r11.f1646h5
            int r2 = android.view.ViewConfiguration.getLongPressTimeout()
            long r2 = (long) r2
            r0.postDelayed(r1, r2)
            boolean r12 = org.chromium.chrome.browser.ChromeTabbedActivity.super.onKeyDown(r12, r13)
            return r12
        L_0x005b:
            org.chromium.chrome.browser.ChromeTabbedActivity$j r0 = r11.f1661w5
            boolean r0 = r0.overviewVisible()
            if (r0 != 0) goto L_0x0075
            boolean r0 = r11.mo8775W()
            if (r0 == 0) goto L_0x0073
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r11.mo8041M0()
            int r0 = r0.getCount()
            if (r0 == 0) goto L_0x0075
        L_0x0073:
            r0 = 1
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            int r3 = r13.getKeyCode()
            int r4 = r13.getRepeatCount()
            if (r4 != 0) goto L_0x024f
            boolean r4 = android.view.KeyEvent.isModifierKey(r3)
            if (r4 == 0) goto L_0x0088
            goto L_0x024f
        L_0x0088:
            boolean r4 = android.view.KeyEvent.isGamepadButton(r3)
            if (r4 == 0) goto L_0x0096
            org.chromium.device.gamepad.GamepadList r4 = org.chromium.device.gamepad.GamepadList.a.a
            boolean r4 = r4.e
            if (r4 == 0) goto L_0x00b4
            goto L_0x024f
        L_0x0096:
            boolean r4 = r13.isCtrlPressed()
            if (r4 != 0) goto L_0x00b4
            boolean r4 = r13.isAltPressed()
            if (r4 != 0) goto L_0x00b4
            r4 = 133(0x85, float:1.86E-43)
            if (r3 == r4) goto L_0x00b4
            r4 = 135(0x87, float:1.89E-43)
            if (r3 == r4) goto L_0x00b4
            r4 = 140(0x8c, float:1.96E-43)
            if (r3 == r4) goto L_0x00b4
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x00b4
            goto L_0x024f
        L_0x00b4:
            org.chromium.chrome.browser.tabmodel.TabModel r4 = r11.mo8041M0()
            int r5 = r4.getCount()
            boolean r6 = r13.isCtrlPressed()
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 == 0) goto L_0x00c7
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x00c8
        L_0x00c7:
            r6 = 0
        L_0x00c8:
            boolean r8 = r13.isAltPressed()
            r9 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L_0x00d3
            r8 = 1073741824(0x40000000, float:2.0)
            goto L_0x00d4
        L_0x00d3:
            r8 = 0
        L_0x00d4:
            r6 = r6 | r8
            boolean r8 = r13.isShiftPressed()
            r10 = 536870912(0x20000000, float:1.0842022E-19)
            if (r8 == 0) goto L_0x00e0
            r8 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x00e1
        L_0x00e0:
            r8 = 0
        L_0x00e1:
            r6 = r6 | r8
            r8 = r3 | r6
            switch(r8) {
                case -2147483606: goto L_0x010c;
                case -2147483600: goto L_0x00fd;
                case -1610612694: goto L_0x00f7;
                case -1610612688: goto L_0x00f1;
                case 100: goto L_0x00eb;
                case 140: goto L_0x00eb;
                case 1073741857: goto L_0x00eb;
                case 1073741858: goto L_0x00eb;
                default: goto L_0x00e7;
            }
        L_0x00e7:
            if (r0 == 0) goto L_0x024f
            r0 = 3
            goto L_0x0114
        L_0x00eb:
            int r0 = ox0.show_menu
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x00f1:
            int r0 = ox0.open_recently_closed_tab
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x00f7:
            int r0 = ox0.new_in_private_tab_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x00fd:
            boolean r0 = r4.isIncognito()
            if (r0 == 0) goto L_0x0106
            int r0 = ox0.new_in_private_tab_id
            goto L_0x0108
        L_0x0106:
            int r0 = ox0.new_tab_id
        L_0x0108:
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x010c:
            int r0 = ox0.new_tab_id
            r11.mo8077a((int) r0, (boolean) r2)
        L_0x0111:
            r0 = 1
            goto L_0x0250
        L_0x0114:
            if (r6 == r7) goto L_0x0118
            if (r6 != r9) goto L_0x0134
        L_0x0118:
            int r3 = r3 + -7
            if (r3 <= 0) goto L_0x0129
            r6 = 8
            int r6 = java.lang.Math.min(r5, r6)
            if (r3 > r6) goto L_0x0129
            int r3 = r3 - r1
            r4.mo9420a((int) r3, (int) r0)
            goto L_0x0111
        L_0x0129:
            r6 = 9
            if (r3 != r6) goto L_0x0134
            if (r5 == 0) goto L_0x0134
            int r5 = r5 - r1
            r4.mo9420a((int) r5, (int) r0)
            goto L_0x0111
        L_0x0134:
            switch(r8) {
                case -2147483641: goto L_0x0240;
                case -2147483616: goto L_0x0239;
                case -2147483614: goto L_0x0232;
                case -2147483613: goto L_0x0232;
                case -2147483612: goto L_0x022b;
                case -2147483608: goto L_0x0224;
                case -2147483604: goto L_0x021d;
                case -2147483602: goto L_0x01e6;
                case -2147483597: goto L_0x01d9;
                case -2147483587: goto L_0x01cc;
                case -2147483579: goto L_0x01bc;
                case -2147483578: goto L_0x01ad;
                case -2147483567: goto L_0x01ad;
                case -2147483556: goto L_0x019f;
                case -2147483555: goto L_0x01cc;
                case -2147483514: goto L_0x01d9;
                case -1610612706: goto L_0x0198;
                case -1610612701: goto L_0x0232;
                case -1610612690: goto L_0x015f;
                case -1610612675: goto L_0x019f;
                case -1610612666: goto L_0x01ad;
                case -1610612660: goto L_0x0159;
                case -1610612655: goto L_0x01ad;
                case 97: goto L_0x01d9;
                case 99: goto L_0x0224;
                case 102: goto L_0x019f;
                case 103: goto L_0x01cc;
                case 108: goto L_0x0149;
                case 125: goto L_0x0149;
                case 133: goto L_0x0232;
                case 135: goto L_0x01e6;
                case 168: goto L_0x01ad;
                case 169: goto L_0x01bc;
                case 174: goto L_0x0239;
                case 536871045: goto L_0x0232;
                case 536871047: goto L_0x01e6;
                case 1073741845: goto L_0x0139;
                case 1073741846: goto L_0x0149;
                case 1073741856: goto L_0x0224;
                default: goto L_0x0137;
            }
        L_0x0137:
            goto L_0x024f
        L_0x0139:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            if (r0 == 0) goto L_0x0111
            boolean r3 = r0.mo9343b()
            if (r3 == 0) goto L_0x0111
            r0.mo9305N()
            goto L_0x0111
        L_0x0149:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            if (r0 == 0) goto L_0x0111
            boolean r3 = r0.mo9348c()
            if (r3 == 0) goto L_0x0111
            r0.mo9306O()
            goto L_0x0111
        L_0x0159:
            int r0 = ox0.feedback_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x015f:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            if (r0 == 0) goto L_0x0111
            java.lang.String r3 = r0.getUrl()
            boolean r3 = pr2.e(r3)
            if (r3 == 0) goto L_0x0179
            boolean r3 = r0.mo9343b()
            if (r3 == 0) goto L_0x0111
            r0.mo9305N()
            goto L_0x0111
        L_0x0179:
            org.chromium.ui.KeyboardVisibilityDelegate r3 = org.chromium.p012ui.KeyboardVisibilityDelegate.f2588b
            android.view.View r4 = r0.mo9301J()
            r3.mo9950c(r4)
            android.view.View r3 = r0.mo9301J()
            r3.clearFocus()
            android.os.Handler r3 = new android.os.Handler
            r3.<init>()
            wu1 r4 = new wu1
            r4.<init>(r0)
            r3.post(r4)
            goto L_0x0111
        L_0x0198:
            int r0 = ox0.all_bookmarks_menu_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x019f:
            if (r5 <= r1) goto L_0x0111
            int r3 = r4.index()
            int r3 = r3 + r5
            int r3 = r3 - r1
            int r3 = r3 % r5
            r4.mo9420a((int) r3, (int) r0)
            goto L_0x0111
        L_0x01ad:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            org.chromium.content_public.browser.WebContents r0 = r0.mo9302K()
            r3 = 1067450368(0x3fa00000, float:1.25)
            Xu1.a(r0, r3)
            goto L_0x0111
        L_0x01bc:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            org.chromium.content_public.browser.WebContents r0 = r0.mo9302K()
            r3 = 1061997773(0x3f4ccccd, float:0.8)
            Xu1.a(r0, r3)
            goto L_0x0111
        L_0x01cc:
            if (r5 <= r1) goto L_0x0111
            int r3 = r4.index()
            int r3 = r3 + r1
            int r3 = r3 % r5
            r4.mo9420a((int) r3, (int) r0)
            goto L_0x0111
        L_0x01d9:
            org.chromium.chrome.browser.tab.Tab r0 = mx2.b(r4)
            if (r0 != 0) goto L_0x01e1
            goto L_0x0111
        L_0x01e1:
            r4.mo9433e(r0)
            goto L_0x0111
        L_0x01e6:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            if (r0 == 0) goto L_0x0111
            r3 = r8 & r10
            if (r3 != r10) goto L_0x01f4
            r0.mo9400t0()
            goto L_0x01f7
        L_0x01f4:
            r0.mo9398s0()
        L_0x01f7:
            org.chromium.chrome.browser.toolbar.ToolbarManager r3 = r11.mo8103f1()
            if (r3 == 0) goto L_0x0218
            org.chromium.content_public.browser.WebContents r3 = r0.mo9302K()
            if (r3 == 0) goto L_0x0218
            org.chromium.content_public.browser.WebContents r3 = r0.mo9302K()
            boolean r3 = r3.mo9787w0()
            if (r3 == 0) goto L_0x0218
            org.chromium.chrome.browser.toolbar.ToolbarManager r0 = r11.mo8103f1()
            Re2 r0 = r0.f2317t3
            r0.l()
            goto L_0x0111
        L_0x0218:
            r0.mo9402u0()
            goto L_0x0111
        L_0x021d:
            int r0 = ox0.print_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x0224:
            int r0 = ox0.focus_url_bar
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x022b:
            int r0 = ox0.open_history_menu_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x0232:
            int r0 = ox0.find_on_page_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x0239:
            int r0 = ox0.add_to_favorites_id
            r11.mo8077a((int) r0, (boolean) r2)
            goto L_0x0111
        L_0x0240:
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            org.chromium.content_public.browser.WebContents r0 = r0.mo9302K()
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            Xu1.a(r0, r3)
            goto L_0x0111
        L_0x024f:
            r0 = 0
        L_0x0250:
            if (r0 != 0) goto L_0x025a
            boolean r12 = org.chromium.chrome.browser.ChromeTabbedActivity.super.onKeyDown(r12, r13)
            if (r12 == 0) goto L_0x0259
            goto L_0x025a
        L_0x0259:
            r1 = 0
        L_0x025a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && !mo8775W()) {
            this.f2029d.removeCallbacks(this.f1646h5);
            this.f1646h5 = null;
        }
        return ChromeTabbedActivity.super.onKeyUp(i, keyEvent);
    }

    public void onMAMActivityResult(int i, int i2, Intent intent) {
        super.onMAMActivityResult(i, i2, intent);
        VN0.b("ChromeTabbedActivity", Eo.a("onMAMActivityResult: ", i, " ", i2), new Object[0]);
        Ie0.b().a(i, i2, intent);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity] */
    public void onMAMNewIntent(Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.addFlags(268435456);
        if (mo8216a(intent2, f1625z5) != 0) {
            moveTaskToBack(true);
            return;
        }
        this.f1651m5 = SystemClock.uptimeMillis();
        if (AnaheimUtils.a(intent.getDataString())) {
            AnaheimUtils.c();
            if (AnaheimUtils.d()) {
                AnaheimUtils.a("universalLinkReceived", TelemetryConstants.Type.Health, new String[]{"source", "external"});
                mo8220a(AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_NAVIGATION);
                return;
            }
            AnaheimUtils.a(this);
            return;
        }
        super.onMAMNewIntent(intent);
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeActivity, android.support.v4.app.FragmentActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    public void onMAMResume() {
        boolean z;
        super.onMAMResume();
        Us0.d.execute(new Ft1(this));
        if (mo8771Q() == null) {
            Us0.d.execute(new Gt1(this));
        }
        Sl0.a.e();
        Fu0.a(this, "edgeAndroidResumed");
        boolean booleanExtra = getIntent().getBooleanExtra("directToNtp", false);
        if (!CommandLine.m1384c().mo7859c("disable-default-browser-dialog-on-resume") && !booleanExtra) {
            Ua0.b(this, false);
        }
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            if (!PrivacyPreferences.m2900F() && (MicrosoftSigninManager.C0424c.f2104a.mo8917k() || AadManagedShareUsageDialog.s())) {
                new AadManagedShareUsageDialog().show(getSupportFragmentManager(), AadManagedShareUsageDialog.class.getSimpleName());
            }
            if (!PrivacyPreferences.m2899E() && (MicrosoftSigninManager.C0424c.f2104a.mo8917k() || AadManagedShareHistoryDialog.s())) {
                new AadManagedShareHistoryDialog().show(getSupportFragmentManager(), AadManagedShareHistoryDialog.class.getSimpleName());
            }
            MicrosoftSigninManager.C0424c.f2104a.mo8898b(false);
        }
        int i = 2;
        boolean z2 = true;
        if (is0.e()) {
            AnaheimUtils.a(this, 2);
            is0.a(MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? AuthenticationMode.AAD : AuthenticationMode.MSA, false);
        } else {
            if (!is0.d()) {
                z = false;
            } else {
                z = QN0.a.getBoolean(MicrosoftSigninManager.C0424c.f2104a.mo8866A() ? "AAD_SYNC_MIGRATION_POPUP_SHOULD_SHOW" : "MSA_SYNC_MIGRATION_POPUP_SHOULD_SHOW", false);
            }
            if (z) {
                if (!is0.e()) {
                    i = QN0.a.getInt("PROMPT_SOURCE", 0);
                }
                AnaheimUtils.a(this, i);
                is0.c(false);
            } else if (!ChromeActivity.f1554K4 || !Ua0.a) {
                if (MicrosoftSigninManager.C0424c.f2104a.mo8866A() && QN0.a.getBoolean("AADOnboardingAfterSignIn", false)) {
                    mo8220a(AnaheimUtils.SwitchToAnaheimSyncAccessPoint.FROM_NAVIGATION);
                    AnaheimUtils.a(false);
                }
            } else {
                mo8034J1();
            }
        }
        Xs0.a.c();
        if (Build.VERSION.SDK_INT >= 23) {
            String str = Build.MANUFACTURER;
            if (TextUtils.isEmpty(str) || !"oneplus".equalsIgnoreCase(str)) {
                z2 = false;
            }
            if (z2) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                Display display = ((DisplayManager) getSystemService("display")).getDisplay(0);
                Display.Mode[] supportedModes = display.getSupportedModes();
                int i2 = -1;
                for (Display.Mode mode : supportedModes) {
                    if (display.getMode().getPhysicalWidth() == mode.getPhysicalWidth()) {
                        if (((double) Math.abs(mode.getRefreshRate() - 90.0f)) < 0.1d && i2 == -1) {
                            i2 = mode.getModeId();
                        }
                        if (((double) Math.abs(mode.getRefreshRate() - 120.0f)) < 0.1d) {
                            i2 = mode.getModeId();
                        }
                    }
                }
                if (i2 != -1) {
                    attributes.preferredDisplayModeId = i2;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onMAMSaveInstanceState(Bundle bundle) {
        byte[] encoded;
        super.onMAMSaveInstanceState(bundle);
        PJ1 a = QJ1.a.a(false);
        if (!(a == null || (encoded = a.a.getEncoded()) == null || a.b == null)) {
            bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY", encoded);
            bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.IV", a.b);
        }
        bundle.putBoolean("is_incognito_selected", mo8041M0().isIncognito());
        bundle.putInt("window_index", TabWindowManager.a().a(this));
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        Fu1 fu1 = this.f1647i5;
        if (fu1 != null) {
            fu1.c.dismiss();
        }
    }

    public void onOverviewModeFinishedHiding() {
        HubManager hubManager;
        if (mo8014A0() != null) {
            mo8014A0().b();
        }
        if (mo8172v0() != null) {
            mo8206B2();
        }
        View findViewById = findViewById(ox0.incognito_not_support_tips);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        if (this.f1659u5 && (hubManager = this.f1653o5) != null) {
            this.f1659u5 = false;
            hubManager.c();
        }
        FeedbackSessionManager.m2264b(false);
    }

    public void onOverviewModeFinishedShowing() {
        FeedbackSessionManager.m2264b(true);
    }

    public void onOverviewModeStartedHiding(boolean z, boolean z2) {
        mo8089b1().b(false);
        mo8241g2();
    }

    public void onOverviewModeStartedShowing(boolean z) {
        if (mo8014A0() != null) {
            mo8014A0().b();
        }
        mo8206B2();
        HubManager hubManager = this.f1653o5;
        if (hubManager != null && hubManager.b()) {
            this.f1653o5.a();
            this.f1659u5 = true;
        }
    }

    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        if (!Og0.d()) {
            return false;
        }
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback parentFragment = preferenceFragmentCompat.getParentFragment();
        if (!(parentFragment instanceof PreferenceFragmentCompat.OnPreferenceStartFragmentCallback) || !parentFragment.onPreferenceStartFragment(preferenceFragmentCompat, preference)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity] */
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        ArrayList arrayList = new ArrayList();
        KeyboardShortcutGroup keyboardShortcutGroup = new KeyboardShortcutGroup(getString(wx0.keyboard_shortcut_tab_group_header));
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_open_new_tab, 42, 4096);
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_reopen_new_tab, 48, 4097);
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_new_incognito_tab, 42, 4097);
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_next_tab, 61, 4096);
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_prev_tab, 61, 4097);
        xu1.a(this, keyboardShortcutGroup, wx0.keyboard_shortcut_close_tab, 51, 4096);
        arrayList.add(keyboardShortcutGroup);
        KeyboardShortcutGroup keyboardShortcutGroup2 = new KeyboardShortcutGroup(getString(wx0.keyboard_shortcut_chrome_feature_group_header));
        xu1.a(this, keyboardShortcutGroup2, wx0.keyboard_shortcut_open_menu, 33, 2);
        xu1.a(this, keyboardShortcutGroup2, wx0.keyboard_shortcut_bookmark_manager, 30, 4097);
        xu1.a(this, keyboardShortcutGroup2, wx0.keyboard_shortcut_history_manager, 36, 4096);
        xu1.a(this, keyboardShortcutGroup2, wx0.keyboard_shortcut_find_bar, 34, 4096);
        xu1.a(this, keyboardShortcutGroup2, wx0.keyboard_shortcut_address_bar, 40, 4096);
        arrayList.add(keyboardShortcutGroup2);
        KeyboardShortcutGroup keyboardShortcutGroup3 = new KeyboardShortcutGroup(getString(wx0.keyboard_shortcut_webpage_group_header));
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_print_page, 44, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_reload_page, 46, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_reload_no_cache, 46, 4097);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_bookmark_page, 32, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_zoom_in, 70, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_zoom_out, 69, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_reset_zoom, 7, 4096);
        xu1.a(this, keyboardShortcutGroup3, wx0.keyboard_shortcut_help_center, 76, 4097);
        arrayList.add(keyboardShortcutGroup3);
        list.addAll(arrayList);
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (ChromeApplication.m1773b(i)) {
            mb2 mb2 = mb2.b;
            for (int i2 = 0; i2 < mb2.a.size(); i2++) {
                mb2.a((Tab) ((WeakReference) mb2.a.get(i2)).get());
            }
            mb2.a.clear();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.i("FeedbackSessionManager", "onChromeTabbedActivityFocusChanged() called with: focus = [" + z + "]");
        FeedbackSessionManager.f1782d = z;
        if (FeedbackSessionManager.f1782d) {
            FeedbackSessionManager.m2260a();
        }
    }

    /* renamed from: p */
    public final void mo8272p(boolean z) {
        Tab v0 = mo8172v0();
        WebContents K = v0 != null ? v0.mo9302K() : null;
        if (K != null) {
            WebContentsAccessibilityImpl.a(K).b(z);
        }
    }

    /* renamed from: p0 */
    public Pair<o, o> mo8147p0() {
        return Pair.create(new o(this, this, mo8772S(), false), new o(this, this, mo8772S(), true));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: p2 */
    public final /* synthetic */ void mo8273p2() {
        if (MAMEdgeManager.m1307e()) {
            MAMEdgeManager.m1306d(this);
            MAMEdgeManager.m1300b((Activity) this);
        }
    }

    /* renamed from: q */
    public boolean mo8149q() {
        if (!this.f1641c5) {
            return false;
        }
        Tab v0 = mo8172v0();
        if (v0 == null || !cb2.a(v0)) {
            return super.mo8149q();
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, Lw2, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, java.lang.Object, android.app.Activity] */
    /* renamed from: q0 */
    public TabModelSelector mo8150q0() {
        dx2 dx2;
        Bundle Q = mo8771Q();
        int i = 0;
        boolean z = Q != null && Q.getBoolean("is_incognito_selected", false);
        MicrosoftSigninManager microsoftSigninManager = MicrosoftSigninManager.C0424c.f2104a;
        Sp2 a = Tp2.a(microsoftSigninManager.mo8906f());
        if (z && microsoftSigninManager.mo8866A() && a != null) {
            z = a.a();
        }
        if (z && !Jo0.e() && a != null) {
            z = a.a();
        }
        int i2 = Q != null ? Q.getInt("window_index", 0) : 0;
        TabWindowManager a2 = TabWindowManager.a();
        if (a2.c.get(this) != null) {
            dx2 = (TabModelSelector) a2.c.get(this);
        } else {
            if (i2 < 0 || i2 >= a2.b.size()) {
                i2 = 0;
            }
            if (a2.b.get(i2) != null) {
                while (true) {
                    if (i >= a2.b.size()) {
                        break;
                    } else if (a2.b.get(i) == null) {
                        i2 = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (a2.b.get(i2) != null) {
                dx2 = null;
            } else {
                dx2 buildSelector = a2.a.buildSelector(this, this, i2);
                a2.b.set(i2, buildSelector);
                a2.c.put(this, buildSelector);
                dx2 = buildSelector;
            }
        }
        this.f1635W4 = dx2;
        dx2 dx22 = this.f1635W4;
        if (dx22 == null) {
            CK3.a(this, getString(wx0.unsupported_number_of_windows), 1).a();
            finish();
            return null;
        }
        dx22.a(new c(this));
        this.f1636X4 = new d(this, this.f1635W4);
        if (z) {
            this.f1635W4.b(true);
        }
        return this.f1635W4;
    }

    /* renamed from: q1 */
    public boolean mo8151q1() {
        j jVar = this.f1661w5;
        return jVar != null && jVar.overviewVisible();
    }

    /* renamed from: q2 */
    public final /* synthetic */ void mo8274q2() {
        ThreadUtils.m1457a((Runnable) new Xt1(this, MicrosoftSigninManager.C0424c.f2104a));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: r2 */
    public final /* synthetic */ void mo8275r2() {
        String a = LW1.a(this);
        if (DeviceFormFactor.isTablet()) {
            LW1.a(this, a, getComponentName());
            return;
        }
        Intent intent = new Intent(this, BookmarkActivity.class);
        intent.setData(Uri.parse(a));
        intent.putExtra("org.chromium.chrome.browser.parent_component", getComponentName());
        startActivity(intent);
    }

    public void removeViewObscuringAllTabs(View view) {
        super.removeViewObscuringAllTabs(view);
        mo8204A2();
    }

    /* renamed from: s2 */
    public final /* synthetic */ void mo8276s2() {
        this.f1647i5 = null;
    }

    /* renamed from: t2 */
    public final /* synthetic */ void mo8277t2() {
        mo8258o(true);
    }

    public void terminateIncognitoSession() {
        mo8095d1().mo9443a(true).mo9418a();
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: u2 */
    public final boolean mo8278u2() {
        if (XL1.a(this, this.f1635W4.c().isIncognito())) {
            return true;
        }
        if (!ChromeFeatureList.m1784a("ExplicitLanguageAsk") || PrefServiceBridge.m2758o0().mo9119i()) {
            return false;
        }
        P52 p52 = new P52();
        p52.a(0);
        List<String> s = PrefServiceBridge.m2758o0().mo9149s();
        p52.c = new HashSet();
        p52.c.addAll(s);
        p52.b = new HashSet(p52.c);
        View inflate = LayoutInflater.from(this).inflate(rx0.language_ask_prompt_content, (ViewGroup) null, false);
        RecyclerView findViewById = inflate.findViewById(ox0.recycler_view);
        M52 m52 = new M52(p52, p52.b);
        findViewById.setAdapter(m52);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.l(1);
        findViewById.setLayoutManager(linearLayoutManager);
        findViewById.setHasFixedSize(true);
        new N52(p52, findViewById, (ImageView) inflate.findViewById(ox0.top_shadow), (ImageView) inflate.findViewById(ox0.bottom_shadow));
        List<rn2> c = PrefServiceBridge.m2758o0().mo9087c();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        GeoLanguageProviderBridge.nativeGetCurrentGeoLanguages(linkedHashSet);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (rn2 next : c) {
            if (linkedHashSet.contains(next.a) || p52.c.contains(next.a)) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        Collections.sort(arrayList, new J52(p52, linkedHashSet));
        m52.a.clear();
        m52.a.addAll(arrayList);
        m52.b.clear();
        m52.b.addAll(arrayList2);
        m52.notifyDataSetChanged();
        Resources resources = getResources();
        BI3.a aVar = new BI3.a(ModalDialogProperties.n);
        aVar.a(ModalDialogProperties.a, p52);
        aVar.a(ModalDialogProperties.c, resources, wx0.languages_explicit_ask_title);
        aVar.a(ModalDialogProperties.f, inflate);
        aVar.a(ModalDialogProperties.g, resources, wx0.save);
        aVar.a(ModalDialogProperties.i, resources, wx0.cancel);
        aVar.a(ModalDialogProperties.k, true);
        BI3 a = aVar.a();
        p52.a = mo8766C();
        p52.a.a(a, 0, false);
        PrefServiceBridge.m2758o0().mo9110g(true);
        return true;
    }

    /* renamed from: v2 */
    public final void mo8211V1() {
        boolean z;
        Boolean bool;
        if (this.f1641c5) {
            boolean a = BM1.a();
            j jVar = this.f1661w5;
            if (jVar != null && jVar.overviewVisible() && ((bool = this.f1642d5) == null || bool.booleanValue() != BM1.a())) {
                this.f1661w5.hideOverview(true);
                if (mo8095d1().mo9448c().getCount() == 0) {
                    m1803L0().b();
                }
            }
            this.f1642d5 = Boolean.valueOf(a);
            if (EE2.a()) {
                RecordHistogram.m1543a("Accessibility.Android.TabSwitcherPreferenceEnabled", this.f1642d5.booleanValue());
            }
            XA1 xa1 = this.f1603n4;
            if (xa1 != null) {
                if (xa1.u != EE2.a()) {
                    xa1.u = !xa1.u;
                    z = xa1.e();
                } else {
                    z = false;
                }
                if (z) {
                    this.f1603n4.a(0);
                    this.f1603n4.a(3);
                }
            }
        }
    }

    /* renamed from: w2 */
    public final void mo8280w2() {
        List<Fragment> d = getSupportFragmentManager().d();
        if (d != null && d.size() > 0) {
            o5 a = getSupportFragmentManager().a();
            for (Fragment d2 : d) {
                a.d(d2);
            }
            a.b();
        }
    }

    /* renamed from: x2 */
    public void mo8281x2() {
        this.f1635W4.o();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0085, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0088, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0082, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0083, code lost:
        if (r0 != null) goto L_0x0085;
     */
    /* renamed from: y */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8282y() {
        /*
            r5 = this;
            java.lang.String r0 = "ChromeTabbedActivity.startNativeInitialization"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            df0.b()     // Catch:{ all -> 0x0080 }
            r5.mo8283y2()     // Catch:{ all -> 0x0080 }
            com.microsoft.authentication.adal.SovereignCloudManager r1 = com.microsoft.authentication.adal.SovereignCloudManager.m1262d()     // Catch:{ all -> 0x0080 }
            r1.mo3484c()     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            bu1 r2 = new bu1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            r3 = 0
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            cu1 r2 = new cu1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            du1 r2 = new du1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            eu1 r2 = new eu1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            zt1 r2 = new zt1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            At1 r2 = new At1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            Bt1 r2 = new Bt1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            Ct1 r2 = new Ct1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            Dt1 r2 = new Dt1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            VP0 r1 = iQ2.a     // Catch:{ all -> 0x0080 }
            Et1 r2 = new Et1     // Catch:{ all -> 0x0080 }
            r2.<init>(r5)     // Catch:{ all -> 0x0080 }
            org.chromium.base.task.PostTask.m1566a(r1, r2, r3)     // Catch:{ all -> 0x0080 }
            if (r0 == 0) goto L_0x007f
            r1 = 0
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x007f:
            return
        L_0x0080:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r2 = move-exception
            if (r0 == 0) goto L_0x0088
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x0088:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8282y():void");
    }

    /* renamed from: y0 */
    public int mo8178y0() {
        return 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        if (r0 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        m1791a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        r2 = nA2.a();
     */
    /* renamed from: y2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8283y2() {
        /*
            r6 = this;
            java.lang.String r0 = "ChromeTabbedActivity.setupCompositorContent"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.m1469B(r0)
            org.chromium.chrome.browser.compositor.CompositorViewHolder r1 = r6.mo8026G0()     // Catch:{ all -> 0x0055 }
            boolean r2 = r6.mo8775W()     // Catch:{ all -> 0x0055 }
            r3 = 0
            if (r2 == 0) goto L_0x0019
            OE1 r2 = new OE1     // Catch:{ all -> 0x0055 }
            r2.<init>(r1)     // Catch:{ all -> 0x0055 }
            r6.f1631S4 = r2     // Catch:{ all -> 0x0055 }
            goto L_0x0032
        L_0x0019:
            boolean r2 = org.chromium.chrome.browser.util.FeatureUtilities.f()     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x002a
            lA2 r2 = nA2.a()     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x002a
            org.chromium.chrome.features.start_surface.StartSurface r2 = r2.a(r6)     // Catch:{ all -> 0x0055 }
            goto L_0x002b
        L_0x002a:
            r2 = r3
        L_0x002b:
            LE1 r4 = new LE1     // Catch:{ all -> 0x0055 }
            r4.<init>(r1, r2)     // Catch:{ all -> 0x0055 }
            r6.f1631S4 = r4     // Catch:{ all -> 0x0055 }
        L_0x0032:
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r1 = r6.f1631S4     // Catch:{ all -> 0x0055 }
            boolean r2 = BM1.b()     // Catch:{ all -> 0x0055 }
            r1.Q3 = r2     // Catch:{ all -> 0x0055 }
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r1 = r6.f1631S4     // Catch:{ all -> 0x0055 }
            int r2 = ox0.url_bar     // Catch:{ all -> 0x0055 }
            android.view.View r2 = r6.findViewById(r2)     // Catch:{ all -> 0x0055 }
            android.view.ViewGroup r4 = r6.f1632T4     // Catch:{ all -> 0x0055 }
            org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer r5 = r6.f1634V4     // Catch:{ all -> 0x0055 }
            r6.mo8064a(r1, r2, r4, r5)     // Catch:{ all -> 0x0055 }
            dx2 r1 = r6.f1635W4     // Catch:{ all -> 0x0055 }
            org.chromium.chrome.browser.ChromeTabbedActivity$j r2 = r6.f1661w5     // Catch:{ all -> 0x0055 }
            r1.n = r2     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0054
            m1791a((java.lang.Throwable) r3, (org.chromium.base.TraceEvent) r0)
        L_0x0054:
            return
        L_0x0055:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r2 = move-exception
            if (r0 == 0) goto L_0x005d
            m1791a((java.lang.Throwable) r1, (org.chromium.base.TraceEvent) r0)
        L_0x005d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8283y2():void");
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0234 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0235 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x023e A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0240 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0254 A[SYNTHETIC, Splitter:B:110:0x0254] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0287 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0297 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0328 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005c A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0091 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ab A[SYNTHETIC, Splitter:B:37:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0132 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0154 A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0161 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0167 A[SYNTHETIC, Splitter:B:65:0x0167] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01ea A[Catch:{ all -> 0x0339 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ed A[Catch:{ all -> 0x0339 }] */
    /* renamed from: z */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8180z() {
        /*
            r15 = this;
            java.lang.String r0 = "ChromeTabbedActivity.initializeState"
            r1 = 0
            org.chromium.base.TraceEvent.m1472c(r0, r1)     // Catch:{ all -> 0x0339 }
            super.mo8180z()     // Catch:{ all -> 0x0339 }
            android.content.Intent r2 = r15.getIntent()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.crypto.CipherFactory r3 = QJ1.a     // Catch:{ all -> 0x0339 }
            android.os.Bundle r4 = r15.mo8771Q()     // Catch:{ all -> 0x0339 }
            boolean r3 = r3.a(r4)     // Catch:{ all -> 0x0339 }
            org.chromium.base.CommandLine r4 = org.chromium.base.CommandLine.m1384c()     // Catch:{ all -> 0x0339 }
            java.lang.String r5 = "no-restore-state"
            boolean r4 = r4.mo7859c(r5)     // Catch:{ all -> 0x0339 }
            r5 = 1
            r6 = 0
            if (r4 != 0) goto L_0x0036
            boolean r4 = f1623B5     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x0034
            android.content.SharedPreferences r4 = QN0.a     // Catch:{ all -> 0x0339 }
            java.lang.String r7 = "Startup Options"
            int r4 = r4.getInt(r7, r6)     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x0034
            goto L_0x0036
        L_0x0034:
            r4 = 0
            goto L_0x0037
        L_0x0036:
            r4 = 1
        L_0x0037:
            boolean r7 = r15.mo8240g((android.content.Intent) r2)     // Catch:{ all -> 0x0339 }
            if (r7 == 0) goto L_0x0053
            org.chromium.chrome.browser.preferences.PrefServiceBridge r7 = org.chromium.chrome.browser.preferences.PrefServiceBridge.m2758o0()     // Catch:{ all -> 0x0339 }
            boolean r7 = r7.mo9051K()     // Catch:{ all -> 0x0339 }
            if (r7 == 0) goto L_0x0053
            boolean r7 = org.chromium.chrome.browser.tabmodel.TabPersistentStore.k()     // Catch:{ all -> 0x0339 }
            if (r7 == 0) goto L_0x0053
            boolean r7 = f1623B5     // Catch:{ all -> 0x0339 }
            if (r7 != 0) goto L_0x0053
            r7 = 1
            goto L_0x0054
        L_0x0053:
            r7 = 0
        L_0x0054:
            f1623B5 = r5     // Catch:{ all -> 0x0339 }
            boolean r8 = Og0.d()     // Catch:{ all -> 0x0339 }
            if (r8 == 0) goto L_0x008b
            java.lang.String r8 = "android.intent.action.VIEW"
            java.lang.String r9 = r2.getAction()     // Catch:{ all -> 0x0339 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0339 }
            if (r8 == 0) goto L_0x0071
            java.lang.String r8 = "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB"
            boolean r8 = r2.getBooleanExtra(r8, r6)     // Catch:{ all -> 0x0339 }
            if (r8 == 0) goto L_0x0071
            r7 = 1
        L_0x0071:
            if (r7 == 0) goto L_0x0075
            r8 = 1
            goto L_0x0076
        L_0x0075:
            r8 = 0
        L_0x0076:
            android.content.Intent r9 = new android.content.Intent     // Catch:{ all -> 0x0339 }
            java.lang.String r10 = "ACTION_TAB_MODEL_CHANGED"
            r9.<init>(r10)     // Catch:{ all -> 0x0339 }
            java.lang.String r10 = "tabSelectorId"
            r9.putExtra(r10, r8)     // Catch:{ all -> 0x0339 }
            android.content.Context r8 = RN0.a     // Catch:{ all -> 0x0339 }
            Y5 r8 = Y5.a(r8)     // Catch:{ all -> 0x0339 }
            r8.a(r9)     // Catch:{ all -> 0x0339 }
        L_0x008b:
            boolean r8 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2218l()     // Catch:{ all -> 0x0339 }
            if (r8 == 0) goto L_0x00a7
            dx2 r8 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r8 = r8.i     // Catch:{ all -> 0x0339 }
            nx2 r9 = r8.a     // Catch:{ all -> 0x0339 }
            r9.g()     // Catch:{ all -> 0x0339 }
            LP0 r9 = r8.q     // Catch:{ all -> 0x0339 }
            qx2 r10 = new qx2     // Catch:{ all -> 0x0339 }
            r10.<init>(r8)     // Catch:{ all -> 0x0339 }
            r9.a(r10)     // Catch:{ all -> 0x0339 }
            r8.d()     // Catch:{ all -> 0x0339 }
        L_0x00a7:
            java.lang.String r8 = "DualIdentityManager"
            if (r4 != 0) goto L_0x00f2
            org.chromium.base.ThreadUtils.m1462c()     // Catch:{ all -> 0x0339 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "shouldIgnoreTabRestore() return "
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            boolean r9 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1767g     // Catch:{ all -> 0x0339 }
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r8, r4)     // Catch:{ all -> 0x0339 }
            boolean r4 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1767g     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x00c9
            goto L_0x00f2
        L_0x00c9:
            if (r3 != 0) goto L_0x00cd
            r4 = 1
            goto L_0x00ce
        L_0x00cd:
            r4 = 0
        L_0x00ce:
            org.chromium.chrome.browser.profiles.Profile r9 = org.chromium.chrome.browser.profiles.Profile.m2911j()     // Catch:{ all -> 0x0339 }
            Sp2 r10 = r9.mo9205e()     // Catch:{ all -> 0x0339 }
            if (r10 == 0) goto L_0x00ea
            Sp2 r9 = r9.mo9205e()     // Catch:{ all -> 0x0339 }
            boolean r9 = r9.a()     // Catch:{ all -> 0x0339 }
            if (r9 != 0) goto L_0x00ea
            dx2 r4 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r4 = r4.i     // Catch:{ all -> 0x0339 }
            r4.b(r5)     // Catch:{ all -> 0x0339 }
            goto L_0x010f
        L_0x00ea:
            dx2 r9 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r9 = r9.i     // Catch:{ all -> 0x0339 }
            r9.b(r4)     // Catch:{ all -> 0x0339 }
            goto L_0x010f
        L_0x00f2:
            dx2 r4 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r4 = r4.i     // Catch:{ all -> 0x0339 }
            nx2 r9 = r4.a     // Catch:{ all -> 0x0339 }
            r9.g()     // Catch:{ all -> 0x0339 }
            LP0 r9 = r4.q     // Catch:{ all -> 0x0339 }
            qx2 r10 = new qx2     // Catch:{ all -> 0x0339 }
            r10.<init>(r4)     // Catch:{ all -> 0x0339 }
            r9.a(r10)     // Catch:{ all -> 0x0339 }
            r4.d()     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = "resetIgnoreTabRestore"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r8, r4)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1767g = r6     // Catch:{ all -> 0x0339 }
        L_0x010f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "initializeState "
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            r4.append(r2)     // Catch:{ all -> 0x0339 }
            r4.toString()     // Catch:{ all -> 0x0339 }
            tt1 r4 = new tt1     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "ChromeTabbedActivity.BackgroundTimeMs"
            Q52 r10 = r15.mo8769O()     // Catch:{ all -> 0x0339 }
            r4.<init>(r9, r10)     // Catch:{ all -> 0x0339 }
            r15.f1649k5 = r4     // Catch:{ all -> 0x0339 }
            r15.f1650l5 = r6     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1774n     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x0154
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r9 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = r9.mo8899c()     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.equalsIgnoreCase(r9)     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x0150
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "Required user is not signed in; skip current intent: "
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            r4.append(r2)     // Catch:{ all -> 0x0339 }
            r4.toString()     // Catch:{ all -> 0x0339 }
            r4 = 1
            goto L_0x0151
        L_0x0150:
            r4 = 0
        L_0x0151:
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1774n = r1     // Catch:{ all -> 0x0339 }
            goto L_0x0155
        L_0x0154:
            r4 = 0
        L_0x0155:
            android.content.Intent r9 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2213g()     // Catch:{ all -> 0x0339 }
            android.os.Bundle r10 = r15.mo8771Q()     // Catch:{ all -> 0x0339 }
            java.lang.String r11 = "ChromeTabbedActivity"
            if (r10 == 0) goto L_0x0163
            if (r9 == 0) goto L_0x0213
        L_0x0163:
            if (r2 == 0) goto L_0x0213
            if (r9 == 0) goto L_0x01ea
            java.lang.String r4 = r9.getAction()     // Catch:{ all -> 0x0339 }
            java.lang.String r10 = "dual_identity_implicit_switch_action"
            boolean r4 = r10.equals(r4)     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x01ba
            com.microsoft.intune.mam.client.app.AppIdentitySwitchReason r4 = com.microsoft.intune.mam.client.app.AppIdentitySwitchReason.CREATE     // Catch:{ all -> 0x0339 }
            android.os.Bundle r9 = r9.getExtras()     // Catch:{ all -> 0x0339 }
            java.lang.String r10 = "extra_dual_identity_implicit_switch_reason"
            int r9 = r9.getInt(r10)     // Catch:{ all -> 0x0339 }
            com.microsoft.intune.mam.client.app.AppIdentitySwitchReason r9 = com.microsoft.intune.mam.client.app.AppIdentitySwitchReason.fromCode(r9)     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.equals(r9)     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x01a1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "DualIdentityManager had implicit switch intent for create, skip current intent and handle it later: "
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            r4.append(r2)     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r11, r4)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2198a((android.content.Intent) r2)     // Catch:{ all -> 0x0339 }
            goto L_0x01b8
        L_0x01a1:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r9 = "DualIdentityManager had implicit switch intent for new intent, current intent should be a stale intent, skip it: "
            r4.append(r9)     // Catch:{ all -> 0x0339 }
            r4.append(r2)     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r11, r4)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2198a((android.content.Intent) r1)     // Catch:{ all -> 0x0339 }
        L_0x01b8:
            r9 = r1
            goto L_0x01e8
        L_0x01ba:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r4.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r10 = "DualIdentityManager had valid intent, will use it, so skip current intent: "
            r4.append(r10)     // Catch:{ all -> 0x0339 }
            r4.append(r2)     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r11, r4)     // Catch:{ all -> 0x0339 }
            boolean r4 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2219m()     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x01e5
            org.chromium.chrome.browser.IntentHandler r4 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.mo8291d((android.content.Intent) r9)     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x01e4
            org.chromium.chrome.browser.IntentHandler r4 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.mo8290c((android.content.Intent) r9)     // Catch:{ all -> 0x0339 }
            r15.f1650l5 = r4     // Catch:{ all -> 0x0339 }
        L_0x01e4:
            r9 = r1
        L_0x01e5:
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2198a((android.content.Intent) r1)     // Catch:{ all -> 0x0339 }
        L_0x01e8:
            r4 = 1
            goto L_0x01eb
        L_0x01ea:
            r9 = r1
        L_0x01eb:
            if (r4 != 0) goto L_0x0214
            org.chromium.chrome.browser.IntentHandler r4 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.mo8291d((android.content.Intent) r2)     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x01fd
            org.chromium.chrome.browser.IntentHandler r4 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r4 = r4.mo8290c((android.content.Intent) r2)     // Catch:{ all -> 0x0339 }
            r15.f1650l5 = r4     // Catch:{ all -> 0x0339 }
        L_0x01fd:
            boolean r4 = r15.mo8240g((android.content.Intent) r2)     // Catch:{ all -> 0x0339 }
            if (r4 == 0) goto L_0x0214
            java.lang.String r4 = org.chromium.chrome.browser.IntentHandler.m1922g(r2)     // Catch:{ all -> 0x0339 }
            if (r4 != 0) goto L_0x020f
            boolean r4 = r15.mo8245i(r2)     // Catch:{ all -> 0x0339 }
            r15.f1650l5 = r4     // Catch:{ all -> 0x0339 }
        L_0x020f:
            r15.mo8243h(r2)     // Catch:{ all -> 0x0339 }
            goto L_0x0214
        L_0x0213:
            r9 = r1
        L_0x0214:
            boolean r4 = Bw2.a()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabModel r10 = r15.mo8041M0()     // Catch:{ all -> 0x0339 }
            int r10 = r10.getCount()     // Catch:{ all -> 0x0339 }
            if (r10 > 0) goto L_0x0237
            dx2 r10 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r10 = r10.i     // Catch:{ all -> 0x0339 }
            java.util.Deque r10 = r10.f     // Catch:{ all -> 0x0339 }
            int r10 = r10.size()     // Catch:{ all -> 0x0339 }
            if (r10 > 0) goto L_0x0237
            boolean r10 = r15.f1650l5     // Catch:{ all -> 0x0339 }
            if (r10 != 0) goto L_0x0237
            if (r4 == 0) goto L_0x0235
            goto L_0x0237
        L_0x0235:
            r10 = 0
            goto L_0x0238
        L_0x0237:
            r10 = 1
        L_0x0238:
            r15.f1648j5 = r10     // Catch:{ all -> 0x0339 }
            boolean r10 = r15.f1650l5     // Catch:{ all -> 0x0339 }
            if (r10 != 0) goto L_0x0240
            r10 = 1
            goto L_0x0241
        L_0x0240:
            r10 = 0
        L_0x0241:
            W72 r12 = r15.f1628P4     // Catch:{ all -> 0x0339 }
            r12.p = r5     // Catch:{ all -> 0x0339 }
            dx2 r12 = r15.f1635W4     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabPersistentStore r12 = r12.i     // Catch:{ all -> 0x0339 }
            r12.c(r10)     // Catch:{ all -> 0x0339 }
            W72 r12 = r15.f1628P4     // Catch:{ all -> 0x0339 }
            r12.p = r6     // Catch:{ all -> 0x0339 }
            java.lang.String r12 = "resetIsIncognitoModeBeforeSwitch"
            if (r9 == 0) goto L_0x0280
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0339 }
            r13.<init>()     // Catch:{ all -> 0x0339 }
            java.lang.String r14 = "had punt intent to do with: "
            r13.append(r14)     // Catch:{ all -> 0x0339 }
            r13.append(r9)     // Catch:{ all -> 0x0339 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r11, r13)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.IntentHandler r11 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r11 = r11.mo8291d((android.content.Intent) r9)     // Catch:{ all -> 0x0339 }
            if (r11 != 0) goto L_0x0278
            org.chromium.chrome.browser.IntentHandler r11 = r15.f1578O3     // Catch:{ all -> 0x0339 }
            boolean r9 = r11.mo8290c((android.content.Intent) r9)     // Catch:{ all -> 0x0339 }
            r15.f1650l5 = r9     // Catch:{ all -> 0x0339 }
        L_0x0278:
            org.chromium.base.ThreadUtils.m1462c()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r8, r12)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1769i = r6     // Catch:{ all -> 0x0339 }
        L_0x0280:
            java.lang.String r9 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2214h()     // Catch:{ all -> 0x0339 }
            r11 = 2
            if (r9 == 0) goto L_0x0297
            Cw2 r3 = r15.m1834b((boolean) r6)     // Catch:{ all -> 0x0339 }
            java.lang.String r4 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2214h()     // Catch:{ all -> 0x0339 }
            r3.a(r4, r11)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2203a((java.lang.String) r1)     // Catch:{ all -> 0x0339 }
            goto L_0x0315
        L_0x0297:
            org.chromium.chrome.browser.tabmodel.TabModelSelector r1 = r15.mo8095d1()     // Catch:{ all -> 0x0339 }
            boolean r9 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2219m()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r1.mo9443a((boolean) r9)     // Catch:{ all -> 0x0339 }
            int r1 = r1.getCount()     // Catch:{ all -> 0x0339 }
            if (r1 != 0) goto L_0x02d7
            boolean r1 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2218l()     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x02d7
            boolean r1 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2219m()     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x02cd
            org.chromium.chrome.browser.preferences.PrefServiceBridge r1 = org.chromium.chrome.browser.preferences.PrefServiceBridge.m2758o0()     // Catch:{ all -> 0x0339 }
            boolean r1 = r1.mo9051K()     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x02cd
            boolean r1 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2219m()     // Catch:{ all -> 0x0339 }
            Cw2 r1 = r15.m1834b((boolean) r1)     // Catch:{ all -> 0x0339 }
            java.lang.String r3 = SE2.a     // Catch:{ all -> 0x0339 }
            r1.a(r3, r11)     // Catch:{ all -> 0x0339 }
            goto L_0x0315
        L_0x02cd:
            Cw2 r1 = r15.m1834b((boolean) r6)     // Catch:{ all -> 0x0339 }
            java.lang.String r3 = SE2.a     // Catch:{ all -> 0x0339 }
            r1.a(r3, r11)     // Catch:{ all -> 0x0339 }
            goto L_0x0315
        L_0x02d7:
            if (r7 == 0) goto L_0x02e7
            if (r3 != 0) goto L_0x02e7
            Cw2 r1 = r15.m1834b((boolean) r5)     // Catch:{ all -> 0x0339 }
            java.lang.String r3 = SE2.a     // Catch:{ all -> 0x0339 }
            r1.a(r3, r11)     // Catch:{ all -> 0x0339 }
            r15.f1652n5 = r5     // Catch:{ all -> 0x0339 }
            goto L_0x0315
        L_0x02e7:
            boolean r1 = r15.f1648j5     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x02f9
            if (r4 != 0) goto L_0x0315
            if (r10 == 0) goto L_0x0315
            org.chromium.chrome.browser.tabmodel.TabModelSelector r1 = r15.mo8095d1()     // Catch:{ all -> 0x0339 }
            int r1 = r1.mo9446b()     // Catch:{ all -> 0x0339 }
            if (r1 != 0) goto L_0x0315
        L_0x02f9:
            Kt1 r1 = new Kt1     // Catch:{ all -> 0x0339 }
            r1.<init>(r15)     // Catch:{ all -> 0x0339 }
            r3 = 500(0x1f4, double:2.47E-321)
            java.util.List<java.lang.Runnable> r5 = org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations.f2140e     // Catch:{ all -> 0x0339 }
            r5.add(r1)     // Catch:{ all -> 0x0339 }
            VP0 r5 = iQ2.a     // Catch:{ all -> 0x0339 }
            Bi2 r7 = new Bi2     // Catch:{ all -> 0x0339 }
            r7.<init>(r1)     // Catch:{ all -> 0x0339 }
            boolean r1 = org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations.f2139d     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x0312
            r3 = 0
        L_0x0312:
            org.chromium.base.task.PostTask.m1566a(r5, r7, r3)     // Catch:{ all -> 0x0339 }
        L_0x0315:
            org.chromium.base.ThreadUtils.m1462c()     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r8, r12)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1769i = r6     // Catch:{ all -> 0x0339 }
            java.lang.String r1 = "resetIsDuringAccountSwitch"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r8, r1)     // Catch:{ all -> 0x0339 }
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1770j = r6     // Catch:{ all -> 0x0339 }
            boolean r1 = r15.f1650l5     // Catch:{ all -> 0x0339 }
            if (r1 == 0) goto L_0x032b
            r15.mo8280w2()     // Catch:{ all -> 0x0339 }
        L_0x032b:
            r15.mo8247j(r2)     // Catch:{ all -> 0x0339 }
            java.lang.String r1 = "MobileStartup.ColdStartupIntent"
            boolean r2 = r15.f1650l5     // Catch:{ all -> 0x0339 }
            org.chromium.base.metrics.RecordHistogram.m1543a((java.lang.String) r1, (boolean) r2)     // Catch:{ all -> 0x0339 }
            org.chromium.base.TraceEvent.m1475z(r0)
            return
        L_0x0339:
            r1 = move-exception
            org.chromium.base.TraceEvent.m1475z(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8180z():void");
    }

    /* renamed from: z2 */
    public final void mo8284z2() {
        if (mo8172v0() == null) {
            m1803L0().b();
        } else if (!this.f1661w5.overviewVisible()) {
            mo8026G0().a(new Pt1(this));
            mo8272p(false);
            TabModel M0 = mo8041M0();
            int count = M0.getCount();
            if (count != 0) {
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < count; i5++) {
                    Integer t = M0.getTabAt(i5).mo9399t();
                    if (t != null) {
                        if (t.intValue() == 2 || t.intValue() == 5 || t.intValue() == 7) {
                            i++;
                        } else {
                            if (t.intValue() == 4) {
                                i2++;
                            } else if (t.intValue() == 1 || t.intValue() == 10) {
                                i3++;
                            }
                        }
                    }
                    i4++;
                }
                RecordHistogram.m1547c("Tabs.Tasks.TabCreated.Count.FromManuallyCreated", i);
                RecordHistogram.m1547c("Tabs.Tasks.TabCreated.Count.FromTargetBlank", i2);
                RecordHistogram.m1547c("Tabs.Tasks.TabCreated.Count.FromExternalApp", i3);
                RecordHistogram.m1547c("Tabs.Tasks.TabCreated.Count.FromOthers", i4);
                RecordHistogram.m1551e("Tabs.Tasks.TabCreated.Percent.FromManuallyCreated", (i * 100) / count);
                RecordHistogram.m1551e("Tabs.Tasks.TabCreated.Percent.FromTargetBlank", (i2 * 100) / count);
                RecordHistogram.m1551e("Tabs.Tasks.TabCreated.Percent.FromExternalApp", (i3 * 100) / count);
                RecordHistogram.m1551e("Tabs.Tasks.TabCreated.Percent.FromOthers", (i4 * 100) / count);
            }
        } else {
            TileLayout tileLayout = this.f1631S4.q3;
            if (tileLayout instanceof TileLayout) {
                tileLayout.a(SystemClock.uptimeMillis());
            }
            if (mo8041M0().getCount() != 0) {
                this.f1661w5.hideOverview(true);
                rv2 rv2 = this.f1655q5;
                if (rv2 != null) {
                    rv2.destroy();
                    this.f1655q5 = null;
                }
                mo8272p(true);
            }
        }
    }

    /* renamed from: L0 */
    public Cw2 m1803L0() {
        return super.mo8038L0();
    }

    /* renamed from: b */
    public final /* synthetic */ void mo8228b(View view) {
        ViewStub viewStub = (ViewStub) findViewById(ox0.incognito_not_support_tips_stub);
        if (viewStub != null) {
            viewStub.inflate();
        }
        Object tag = view.getTag();
        if (tag instanceof Integer) {
            int intValue = ((Integer) tag).intValue();
            mo8239g(intValue);
            if (intValue == 0 || intValue == 1 || intValue == 2) {
                Intent intent = new Intent("ACTION_TAB_MODEL_CHANGED");
                intent.putExtra("tabSelectorId", intValue);
                Y5.a(RN0.a).a(intent);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, java.lang.Object, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: c */
    public int mo8232c(Intent intent) {
        if (getClass().equals(ChromeTabbedActivity.class) && "android.intent.action.MAIN".equals(intent.getAction())) {
            return new yu1(this, intent).a();
        }
        int a = mo8216a(intent, f1624y5);
        if (a != 0) {
            return a;
        }
        super.mo8232c(intent);
        return 0;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    @TargetApi(25)
    /* renamed from: m */
    public void mo8253m(boolean z) {
        if (Build.VERSION.SDK_INT >= 25) {
            ((ShortcutManager) getSystemService(ShortcutManager.class)).reportShortcutUsed(z ? "dynamic-new-incognito-tab-shortcut" : "new-tab-shortcut");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: a */
    public void mo8065a(Intent intent) {
        try {
            TraceEvent.m1472c("ChromeTabbedActivity.onNewIntentWithNative", (String) null);
            super.mo8065a(intent);
            if (mo8240g(intent)) {
                if (IntentHandler.m1922g(intent) == null) {
                    mo8245i(intent);
                }
                mo8243h(intent);
            } else {
                mo8280w2();
            }
            mo8247j(intent);
            if (CommandLine.m1384c().mo7859c("enable-test-intents")) {
                if ("com.google.android.apps.chrome.ACTION_CLOSE_TABS".equals(intent.getAction())) {
                    mo8095d1().mo9444a();
                } else {
                    MemoryPressureListener.m1435a(this, intent.getAction());
                }
            }
        } finally {
            TraceEvent.m1475z("ChromeTabbedActivity.onNewIntentWithNative");
        }
    }

    /* renamed from: c */
    public void mo8090c() {
        super.mo8090c();
        this.f1635W4.o();
        N72 n72 = this.f1627O4;
        if (n72.a == 6) {
            if (this == ApplicationStatus.f1396d || !ApplicationStatus.m1368d()) {
                n72.a = 0;
            } else {
                n72.a = 5;
            }
        }
        RecordHistogram.m1539a("Android.Activity.ChromeTabbedActivity.StopReason", n72.a, 6);
        n72.a = 6;
    }

    /* renamed from: h */
    public BottomBarHelper mo8242h() {
        return this.f1658t5;
    }

    /* renamed from: b */
    public void mo8082b() {
        this.f1635W4.e();
        try {
            CookiesFetcher.nativePersistCookies();
        } catch (RuntimeException e) {
            qI.a.a(e);
        }
        this.f1643e5.mo8818a((SnackbarManager) null);
        this.f1643e5.mo8839r();
        this.f1639a5.a();
        super.mo8082b();
        mo8094d(false);
    }

    /* renamed from: b */
    public Cw2 m1834b(boolean z) {
        return super.mo8081b(z);
    }

    /* renamed from: c */
    public final /* synthetic */ void mo8233c(Tab tab) {
        boolean z = mo8041M0().mo9426b(tab.getId()) != null;
        mo8041M0().mo9425a(tab, false, true, false);
        if (!z) {
            mo8258o(false);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f A[RETURN] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8230b(android.content.Intent r7) {
        /*
            r6 = this;
            eb2 r0 = r6.f1629Q4
            r1 = 1
            if (r0 == 0) goto L_0x0060
            int r2 = r6.getTaskId()
            r0.p = r2
            int r2 = eb2.x
            r3 = 0
            if (r2 == 0) goto L_0x0016
            int r4 = r0.p
            if (r2 == r4) goto L_0x0016
            r2 = 1
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            boolean r4 = org.chromium.chrome.browser.util.FeatureUtilities.o()
            if (r4 == 0) goto L_0x004f
            int r4 = eb2.x
            if (r4 != 0) goto L_0x0022
            goto L_0x004f
        L_0x0022:
            android.content.Context r0 = r0.c
            java.lang.String r4 = "activity"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.util.List r0 = r0.getAppTasks()
            java.util.Iterator r0 = r0.iterator()
        L_0x0034:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x004f
            java.lang.Object r4 = r0.next()
            android.app.ActivityManager$AppTask r4 = (android.app.ActivityManager.AppTask) r4
            android.app.ActivityManager$RecentTaskInfo r4 = TM1.b(r4)
            if (r4 != 0) goto L_0x0047
            goto L_0x0034
        L_0x0047:
            int r4 = r4.id
            int r5 = eb2.x
            if (r4 != r5) goto L_0x0034
            r0 = 1
            goto L_0x0050
        L_0x004f:
            r0 = 0
        L_0x0050:
            if (r2 == 0) goto L_0x0058
            if (r0 == 0) goto L_0x0058
            eb2.x = r3
            r0 = 0
            goto L_0x005d
        L_0x0058:
            if (r0 != 0) goto L_0x005c
            eb2.x = r3
        L_0x005c:
            r0 = 1
        L_0x005d:
            if (r0 != 0) goto L_0x0060
            return r3
        L_0x0060:
            super.mo8230b(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8230b(android.content.Intent):boolean");
    }

    /* renamed from: a */
    public void mo8223a(HubManager.PageType pageType) {
        HubManager hubManager = this.f1653o5;
        HashMap hashMap = hubManager.f;
        if (hashMap != null) {
            pageType = (HubManager.PageType) hashMap.get("hub_previous_page_type");
        }
        if (pageType == null) {
            pageType = HubManager.PageType.FAVORITES;
        }
        hubManager.a(pageType);
        HubFragment hubFragment = (HubFragment) hubManager.b.get();
        CollectionAnimationHelper collectionAnimationHelper = fZ1.a;
        if (collectionAnimationHelper.b != null) {
            collectionAnimationHelper.d = new SY1(hubManager, hubFragment);
            collectionAnimationHelper.d.putData(collectionAnimationHelper.f);
            collectionAnimationHelper.a();
            return;
        }
        hubManager.a(hubManager.f, hubFragment);
    }

    /* renamed from: f */
    public void mo8102f(boolean z) {
        super.mo8102f(z);
        LayoutManagerChrome layoutManagerChrome = this.f1631S4;
        if (layoutManagerChrome != null) {
            layoutManagerChrome.Q3 = BM1.b();
        }
        if (mo8775W() && mo8026G0() != null) {
            mo8026G0().a(z);
        }
        mo8211V1();
    }

    /* renamed from: b */
    public boolean mo8087b(Tab tab) {
        int s = tab.mo9397s();
        if (s == 0 || s == 1 || s == 4 || s == 5) {
            return true;
        }
        if (s != 3 || tab.mo9407x() == -1) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public void mo8083b(BE1 be1) {
        super.mo8083b(be1);
        if (!be1.u()) {
            lx2 lx2 = this.f1635W4.q;
            if (lx2.a != -1) {
                lx2.a(1);
                lx2.a = -1;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [org.chromium.chrome.browser.ChromeTabbedActivity, java.lang.Object, android.app.Activity] */
    /* renamed from: a */
    public final int mo8216a(Intent intent, FO0.b bVar) {
        boolean z;
        if (!getClass().equals(ChromeTabbedActivity.class) || !"android.intent.action.VIEW".equals(intent.getAction()) || intent.getComponent() == null || !"com.google.android.apps.chrome.Main".equals(intent.getComponent().getClassName())) {
            return 0;
        }
        yu1 yu1 = new yu1(this, intent);
        if (!yu1.c) {
            z = false;
        } else {
            yu1.b();
            z = true;
        }
        bVar.a(z);
        if (!z) {
            int f = IntentHandler.m1921f(intent);
            f1622A5.a(f);
            if (f == 5 && (getApplicationInfo().flags & 2) != 0 && !CommandLine.m1384c().mo7859c("dont-crash-on-view-main-intents")) {
                String intent2 = intent.toString();
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    StringBuilder c = Eo.c(intent2, ", extras.keySet = [");
                    c.append(TextUtils.join(", ", extras.keySet()));
                    c.append("]");
                    intent2 = c.toString();
                }
                throw new IllegalStateException(String.format((Locale) null, "VIEW intent sent to .Main activity alias was not dispatched. PLEASE report the following info to crbug.com/789732: \"%s\". Use --%s flag to disable this check.", new Object[]{intent2, "dont-crash-on-view-main-intents"}));
            }
        }
        return z ? 1 : 0;
    }

    /* renamed from: g */
    public final boolean mo8240g(Intent intent) {
        return intent != null && TextUtils.equals(intent.getAction(), "android.intent.action.MAIN") && intent.hasCategory("android.intent.category.LAUNCHER");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: b */
    public int mo4510b(Theme theme) {
        if (DeviceFormFactor.m3641c(this)) {
            return du0.a(getResources(), jx0.ruby_normal_tablet_status_bar_color);
        }
        boolean z = false;
        if (mo8172v0() != null && mo8172v0().mo9344b0() && !mo8172v0().mo9315X()) {
            z = true;
        }
        if (mo8151q1()) {
            return du0.a(getResources(), jx0.ruby_normal_tab_switcher_background);
        } else if (!z || mo8775W()) {
            return du0.a(getResources(), jx0.ruby_normal_status_bar_color);
        } else {
            return du0.a(getResources(), jx0.ntp_bg);
        }
    }

    /* renamed from: g */
    public void mo8104g(boolean z) {
        super.mo8104g(z);
        this.f1628P4.a(1);
        cb2 cb2 = this.f1640b5.d;
        if (!(cb2 == null || cb2.b == null)) {
            cb2.c(!z);
        }
        if (z) {
            mo8094d(true);
        }
    }

    /* renamed from: b */
    public final /* synthetic */ void mo8229b(AlertDialogFragment alertDialogFragment) {
        alertDialogFragment.getDialog().dismiss();
        ss0.a("TabCenter", this.f1633U4, "CloseAllTabsAlert", TelemetryConstants.Actions.Click, MAMAppInfo.VALUE_CL_CLEAR, new String[0]);
        ss0.b("TabCenter", this.f1633U4, "CloseAllTabsAlert", new String[0]);
    }

    /* renamed from: a */
    public static /* synthetic */ void m1791a(Throwable th, TraceEvent traceEvent) {
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
    public final /* synthetic */ void mo8219a(View view) {
        Object tag = view.getTag();
        if (tag.equals(3)) {
            mo8119k0();
            if (mo8103f1() != null) {
                mo8103f1().mo9483n();
            }
        } else if (tag.equals("tab_center_back_button")) {
            ss0.a("MainFrame", "Toolbar", (String) null, TelemetryConstants.Actions.Click, "TabCenter", new String[0]);
            if (!mo8046P0().a.g) {
                if (!mo8103f1().f2306c4) {
                    BE1 be1 = this.f1631S4.q3;
                    if (!(be1 instanceof TileLayout) || be1.m) {
                        boolean q1 = mo8151q1();
                    }
                }
                mo8284z2();
            }
        }
    }

    /* renamed from: d */
    public final void mo8235d(Tab tab) {
        VN0.b("ChromeTabbedActivity", "sendToBackground(): " + tab, new Object[0]);
        moveTaskToBack(true);
        if (tab != null) {
            this.f2029d.postDelayed(new Ot1(this, tab), 500);
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8224a(MicrosoftSigninManager microsoftSigninManager) {
        if (microsoftSigninManager.mo8932y() && microsoftSigninManager.mo8868C() && microsoftSigninManager.mo8869D()) {
            NotifyResigninDialogFragment.a(getSupportFragmentManager());
        }
    }

    /* renamed from: a */
    public void mo8062a(long j) {
        RecordHistogram.m1550d("MobileStartup.IntentToCreationTime", j);
        RecordHistogram.m1542a("MobileStartup.IntentToCreationTime.TabbedMode", j, 1, 30000, 50);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: a */
    public boolean mo8077a(int i, boolean z) {
        Tab v0 = mo8172v0();
        boolean z2 = false;
        boolean z3 = v0 != null && v0.mo9344b0();
        if (i == ox0.new_tab_id) {
            mo8095d1().mo9443a(false).mo9432e();
            ss0.b("new_tab_invoke", "CV", ss0.e());
            mo8253m(false);
            m1834b(false).b();
            this.f1643e5.mo8821b(this, (Callback<Boolean>) null);
        } else if (i == ox0.new_in_private_tab_id) {
            if (PrefServiceBridge.m2758o0().mo9051K()) {
                mo8095d1().mo9443a(false).mo9432e();
                ss0.b("new_tab_invoke", "CV", ss0.e());
                mo8253m(true);
                m1834b(true).b();
                XA1 xa1 = this.f1603n4;
                if (xa1 != null) {
                    xa1.a(0);
                }
            }
        } else if (i == ox0.add_to_reading_list_id) {
            if (v0 != null) {
                mo8026G0().a(new e(this, v0));
            }
        } else if (i == ox0.all_bookmarks_menu_id) {
            if (v0 != null) {
                mo8026G0().a(new Nt1(this));
            }
        } else if (i == ox0.recent_tabs_id) {
            if (v0 != null) {
                v0.mo9338b(new LoadUrlParams("chrome-native://recent-tabs/", 2));
            }
        } else if (i == ox0.focus_url_bar) {
            if (!this.f1661w5.overviewVisible() && (!mo8775W() || mo8041M0().getCount() != 0)) {
                z2 = true;
            }
            if (z2) {
                mo8103f1().mo9473e(true);
            }
        } else if (i == ox0.downloads_menu_id) {
            DownloadUtils.a(this, v0);
            if (z3) {
                WU1.a(7);
            }
        } else if (i == ox0.open_recently_closed_tab) {
            TabModel c = this.f1635W4.c();
            if (!c.isIncognito()) {
                c.mo9440p();
            }
        } else if (i == ox0.enter_vr_id) {
            VrModuleProvider.m3290a().c();
        } else if (i == ox0.user_community_id) {
            m1834b(false).a("https://plus.google.com/communities/110637548585404916138", 2);
        } else if (i == ox0.shopping_id) {
            if (v0 != null) {
                v0.mo9338b(new LoadUrlParams("https://www.bing.com/shop?&entrypoint=edgemmx", 0));
            }
        } else if (i == ox0.open_history_menu_id) {
            this.f1653o5.a(HubManager.PageType.HISTORY);
            this.f1653o5.c();
        } else if (i != ox0.read_aloud_id) {
            return super.mo8077a(i, z);
        } else {
            super.mo8077a(i, z);
            rU1 ru1 = this.f1566F4;
            if (ru1 != null) {
                ru1.c.a(this.f1631S4);
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void mo8221a(String str, int i) {
        VN0.b("ChromeTabbedActivity", Eo.a("Back pressed: ", str), new Object[0]);
        RecordHistogram.m1539a("Android.Activity.ChromeTabbedActivity.SystemBackAction", i, 9);
    }

    /* renamed from: a */
    public static /* synthetic */ LoadUrlParams m1789a(String str, String str2, boolean z, long j, Intent intent) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.mo9711b(j);
        loadUrlParams.mo9709a(z);
        loadUrlParams.mo9710b(IntentHandler.m1904a(intent, 134217728));
        if (str2 != null) {
            loadUrlParams.mo9707a(new oQ2(str2, IntentHandler.m1925j(intent)));
        }
        return loadUrlParams;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ org.chromium.chrome.browser.tab.Tab m1788a(org.chromium.chrome.browser.ChromeTabbedActivity r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, boolean r14, android.content.Intent r15) {
        /*
            boolean r0 = r9.f1641c5
            r1 = 0
            if (r0 == 0) goto L_0x0025
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r0 = r9.mo8020D0()
            if (r0 == 0) goto L_0x0011
            boolean r0 = org.chromium.chrome.browser.edge_ntp.NewTabPage.m2272b(r10)
            if (r0 != 0) goto L_0x0025
        L_0x0011:
            org.chromium.chrome.browser.ChromeTabbedActivity$j r0 = r9.f1661w5
            r0.hideOverview(r1)
            org.chromium.chrome.browser.toolbar.ToolbarManager r0 = r9.mo8103f1()
            boolean r2 = r0.f2299Y3
            if (r2 == 0) goto L_0x0025
            org.chromium.chrome.browser.toolbar.top.TopToolbarCoordinator r0 = r0.f2309e
            org.chromium.chrome.browser.toolbar.top.ToolbarLayout r0 = r0.a
            r0.mo9560p()
        L_0x0025:
            boolean r0 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2220n()
            org.chromium.base.ThreadUtils.m1462c()
            java.lang.String r2 = "DualIdentityManager"
            java.lang.String r3 = "resetIsIncognitoModeRequired"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2248c(r2, r3)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.f1773m = r1
            java.lang.String r2 = r9.getPackageName()
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x008c
            if (r0 != 0) goto L_0x004d
            java.lang.String r11 = "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB"
            boolean r11 = PE2.a(r15, r11, r1)
            if (r11 == 0) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r4 = 0
        L_0x004d:
            org.chromium.content_public.browser.LoadUrlParams r11 = new org.chromium.content_public.browser.LoadUrlParams
            r11.<init>(r10, r1)
            long r13 = r9.f1651m5
            r11.mo9711b((long) r13)
            r11.mo9722e(r12)
            java.lang.Integer r10 = org.chromium.chrome.browser.IntentHandler.m1928m(r15)
            if (r10 != 0) goto L_0x007f
            java.lang.String r10 = "com.android.chrome.invoked_from_shortcut"
            boolean r10 = PE2.a(r15, r10, r1)
            if (r10 == 0) goto L_0x006e
            r10 = 7
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            goto L_0x007f
        L_0x006e:
            boolean r10 = org.chromium.chrome.browser.incognito.IncognitoTabLauncher.a(r15)
            if (r10 == 0) goto L_0x007b
            r10 = 10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            goto L_0x007f
        L_0x007b:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
        L_0x007f:
            Cw2 r9 = r9.m1834b((boolean) r4)
            int r10 = r10.intValue()
            org.chromium.chrome.browser.tab.Tab r9 = r9.a(r11, r10, r3, r15)
            goto L_0x00d8
        L_0x008c:
            boolean r2 = ZM1.n()
            if (r2 == 0) goto L_0x00c8
            android.os.Bundle r2 = r15.getExtras()
            java.lang.String r5 = "org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT"
            r6 = -1
            int r2 = PE2.a(r2, r5, r6)
            if (r2 == r6) goto L_0x00a0
            goto L_0x00a1
        L_0x00a0:
            r4 = 0
        L_0x00a1:
            if (r4 == 0) goto L_0x00c8
            android.os.Bundle r2 = r15.getExtras()
            int r4 = PE2.a(r2, r5, r6)
            r2.remove(r5)
            if (r4 == r6) goto L_0x00c8
            dx2 r2 = r9.f1635W4
            if (r2 == 0) goto L_0x00c8
            Cw2 r11 = r9.m1803L0()
            org.chromium.content_public.browser.LoadUrlParams r12 = new org.chromium.content_public.browser.LoadUrlParams
            r12.<init>(r10, r1)
            dx2 r9 = r9.f1635W4
            org.chromium.chrome.browser.tab.Tab r9 = r9.a(r4)
            org.chromium.chrome.browser.tab.Tab r9 = r11.a(r12, r1, r9, r3)
            goto L_0x00d8
        L_0x00c8:
            Cw2 r0 = r9.m1834b((boolean) r0)
            long r7 = r9.f1651m5
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            org.chromium.chrome.browser.tab.Tab r9 = r0.a(r1, r2, r3, r4, r5, r6, r7)
        L_0x00d8:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.m1788a(org.chromium.chrome.browser.ChromeTabbedActivity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, android.content.Intent):org.chromium.chrome.browser.tab.Tab");
    }

    /* renamed from: a */
    public Bundle mo8217a(Bundle bundle) {
        if (bundle == null || OP2.a(1).mo9665b()) {
            super.mo8217a(bundle);
            return bundle;
        }
        Bundle bundle2 = new Bundle(bundle);
        bundle2.remove("android:support:fragments");
        return bundle2;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8222a(AlertDialogFragment alertDialogFragment) {
        alertDialogFragment.getDialog().dismiss();
        mo8041M0().mo9418a();
        mo8041M0().mo9432e();
        ss0.a("TabCenter", this.f1633U4, "CloseAllTabsAlert", TelemetryConstants.Actions.Click, "Confirm", new String[]{"TabCount", String.valueOf(mo8041M0().getCount())});
        ss0.b("TabCenter", this.f1633U4, "CloseAllTabsAlert", new String[0]);
    }

    /* renamed from: a */
    public void mo8220a(AnaheimUtils.SwitchToAnaheimSyncAccessPoint switchToAnaheimSyncAccessPoint) {
        VN0.b("Anaheim", "ChromeTabbedActivity checking if need onboarding", new Object[0]);
        if (AnaheimUtils.d()) {
            VN0.b("Anaheim", "start onboarding", new Object[0]);
            AnaheimUtils.a(this, switchToAnaheimSyncAccessPoint);
        }
    }

    /* renamed from: a */
    public void mo8225a(boolean z) {
        if (!z) {
            mo8095d1().mo9447b(false);
        }
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v4.app.FragmentActivity, org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a3, code lost:
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c9, code lost:
        ss0.a("MainFrame", "Overflow", (java.lang.String) null, com.microsoft.ruby.telemetry.TelemetryConstants.Actions.Click, r2, new java.lang.String[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01d6, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01d7, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8226a(Dh2 r12) {
        /*
            r11 = this;
            org.chromium.chrome.browser.tab.Tab r0 = r11.mo8172v0()
            int r1 = r12.a
            xh2 r2 = r11.f1602m4
            Bh2 r2 = r2.x3
            boolean r2 = r2.a(r1)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0022
            android.content.Context r12 = RN0.a
            int r0 = wx0.aad_item_disabled_text
            java.lang.String r12 = r12.getString(r0)
            CK3 r12 = CK3.a(r11, r12, r4)
            r12.a()
            return r3
        L_0x0022:
            boolean r12 = r12.g
            if (r12 == 0) goto L_0x0027
            return r3
        L_0x0027:
            r11.mo8094d(r3)
            android.os.Handler r12 = r11.f2029d
            Vt1 r2 = new Vt1
            r2.<init>(r11, r1, r0)
            r5 = 180(0xb4, double:8.9E-322)
            r12.postDelayed(r2, r5)
            r12 = 0
            switch(r1) {
                case 3: goto L_0x01a5;
                case 4: goto L_0x003a;
                case 5: goto L_0x019a;
                case 6: goto L_0x0190;
                case 7: goto L_0x0173;
                case 8: goto L_0x014a;
                case 9: goto L_0x0140;
                case 10: goto L_0x003a;
                case 11: goto L_0x012d;
                case 12: goto L_0x0107;
                case 13: goto L_0x00f6;
                case 14: goto L_0x00d1;
                case 15: goto L_0x00c5;
                case 16: goto L_0x00bc;
                case 17: goto L_0x006c;
                case 18: goto L_0x0061;
                case 19: goto L_0x005a;
                case 20: goto L_0x0107;
                case 21: goto L_0x004a;
                case 22: goto L_0x003c;
                case 23: goto L_0x003a;
                case 24: goto L_0x003a;
                case 25: goto L_0x00bc;
                default: goto L_0x003a;
            }
        L_0x003a:
            goto L_0x01d7
        L_0x003c:
            org.chromium.chrome.browser.hub.HubManager r12 = r11.f1653o5
            org.chromium.chrome.browser.hub.HubManager$PageType r0 = org.chromium.chrome.browser.hub.HubManager.PageType.READING_LIST
            r12.a(r0)
            org.chromium.chrome.browser.hub.HubManager r12 = r11.f1653o5
            r12.c()
            goto L_0x01d7
        L_0x004a:
            android.support.v4.app.FragmentManager r12 = r11.getSupportFragmentManager()
            com.microsoft.ruby.exit.EdgeExitDialogFragment r0 = new com.microsoft.ruby.exit.EdgeExitDialogFragment
            r0.<init>()
            r0.a(r12)
            java.lang.String r12 = "Exit"
            goto L_0x01a3
        L_0x005a:
            r11.mo8044N1()
            java.lang.String r12 = "Feedback"
            goto L_0x01a3
        L_0x0061:
            jq0 r12 = jq0.b()
            r12.a(r11)
            java.lang.String r12 = "ContinueOnPC"
            goto L_0x01a3
        L_0x006c:
            boolean r1 = r0.mo9337a0()
            if (r1 != 0) goto L_0x0073
            return r4
        L_0x0073:
            qU1 r1 = qU1.c
            java.util.HashMap r1 = r1.a
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0081
            java.lang.String r1 = "und"
        L_0x0081:
            boolean r1 = org.chromium.chrome.browser.edge_learning_tools.ReadAloudUtils.a(r1)
            if (r1 != 0) goto L_0x008d
            int r12 = wx0.read_aloud_not_supported
            org.chromium.chrome.browser.edge_learning_tools.ReadAloudUtils.a(r11, r12)
            return r4
        L_0x008d:
            boolean r1 = org.chromium.chrome.browser.edge_learning_tools.ReadAloudUtils.a(r11)
            if (r1 == 0) goto L_0x0099
            int r12 = wx0.read_aloud_no_tts
            org.chromium.chrome.browser.edge_learning_tools.ReadAloudUtils.a(r11, r12)
            return r4
        L_0x0099:
            rU1 r1 = r11.f1566F4
            if (r1 != 0) goto L_0x00b3
            rU1 r1 = new rU1
            org.chromium.chrome.browser.toolbar.ToolbarManager r2 = r11.mo8103f1()
            GI2 r2 = r2.f2318u3
            r1.<init>(r11, r12, r2)
            r11.f1566F4 = r1
            rU1 r12 = r11.f1566F4
            org.chromium.chrome.browser.compositor.layouts.LayoutManagerChrome r1 = r11.f1631S4
            wU1 r12 = r12.c
            r12.a(r1)
        L_0x00b3:
            rU1 r12 = r11.f1566F4
            r12.g(r0)
            java.lang.String r12 = "ReadAloud"
            goto L_0x01a3
        L_0x00bc:
            com.microsoft.bing.commonlib.model.search.SourceType r12 = com.microsoft.bing.commonlib.model.search.SourceType.FROM_FLOATING_MENU
            Mx1.a(r11, r12)
            java.lang.String r12 = "CameraSearch"
            goto L_0x01a3
        L_0x00c5:
            org.chromium.chrome.browser.webapps.AddToHomescreenManager r12 = new org.chromium.chrome.browser.webapps.AddToHomescreenManager
            r12.<init>(r11, r0)
            r12.b()
            java.lang.String r12 = "AddToHomePage"
            goto L_0x01a3
        L_0x00d1:
            ZD3 r12 = aE3.o
            if (r12 == 0) goto L_0x00f2
            aE3 r12 = (aE3) r12
            boolean r1 = r12.m
            if (r1 != 0) goto L_0x00f2
            org.chromium.chrome.browser.preferences.PrefServiceBridge r1 = org.chromium.chrome.browser.preferences.PrefServiceBridge.m2758o0()
            boolean r1 = r1.mo9061U()
            if (r1 == 0) goto L_0x00f2
            org.chromium.chrome.browser.printing.TabPrinter r1 = new org.chromium.chrome.browser.printing.TabPrinter
            r1.<init>(r0)
            YD3 r0 = new YD3
            r0.<init>(r11)
            r12.a(r1, r0)
        L_0x00f2:
            java.lang.String r12 = "Print"
            goto L_0x01a3
        L_0x00f6:
            boolean r12 = com.microsoft.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed()
            if (r12 != 0) goto L_0x0100
            com.microsoft.managedbehavior.MAMEdgeManager.m1303c((android.app.Activity) r11)
            return r3
        L_0x0100:
            org.chromium.chrome.browser.download.DownloadUtils.a(r11, r0)
            java.lang.String r12 = "DownloadPage"
            goto L_0x01a3
        L_0x0107:
            boolean r12 = r0.isNativePage()
            r12 = r12 ^ r4
            org.chromium.content_public.browser.WebContents r1 = r0.mo9302K()
            org.chromium.content_public.browser.NavigationController r1 = r1.mo9742F()
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x011d
            java.lang.String r2 = "RequestMobileSite"
            goto L_0x011f
        L_0x011d:
            java.lang.String r2 = "RequestDesktopSite"
        L_0x011f:
            org.chromium.content_public.browser.WebContents r0 = r0.mo9302K()
            org.chromium.content_public.browser.NavigationController r0 = r0.mo9742F()
            r1 = r1 ^ r4
            r0.a(r1, r12)
            goto L_0x01c9
        L_0x012d:
            if (r0 == 0) goto L_0x0137
            boolean r12 = r0.mo9315X()
            if (r12 == 0) goto L_0x0137
            r12 = 1
            goto L_0x0138
        L_0x0137:
            r12 = 0
        L_0x0138:
            com.microsoft.bing.commonlib.model.search.SourceType r0 = com.microsoft.bing.commonlib.model.search.SourceType.FROM_FLOATING_MENU
            Mx1.a(r11, r12, r0)
            java.lang.String r12 = "VoiceSearch"
            goto L_0x01a3
        L_0x0140:
            org.chromium.chrome.browser.tab.Tab r12 = r11.mo8172v0()
            r11.mo8070a((org.chromium.chrome.browser.tab.Tab) r12)
            java.lang.String r12 = "AddToFavorites"
            goto L_0x01a3
        L_0x014a:
            org.chromium.chrome.browser.preferences.PrefServiceBridge r12 = org.chromium.chrome.browser.preferences.PrefServiceBridge.m2758o0()
            boolean r12 = r12.mo9051K()
            if (r12 == 0) goto L_0x0170
            org.chromium.chrome.browser.tabmodel.TabModelSelector r12 = r11.mo8095d1()
            org.chromium.chrome.browser.tabmodel.TabModel r12 = r12.mo9443a((boolean) r3)
            r12.mo9432e()
            r11.mo8253m(r4)
            Cw2 r12 = r11.m1834b((boolean) r4)
            r12.b()
            XA1 r12 = r11.f1603n4
            if (r12 == 0) goto L_0x0170
            r12.a(r3)
        L_0x0170:
            java.lang.String r12 = "NewPrivateTab"
            goto L_0x01a3
        L_0x0173:
            org.chromium.chrome.browser.tabmodel.TabModelSelector r0 = r11.mo8095d1()
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r0.mo9443a((boolean) r3)
            r0.mo9432e()
            r11.mo8253m(r3)
            Cw2 r0 = r11.m1834b((boolean) r3)
            r0.b()
            org.chromium.chrome.browser.locale.LocaleManager r0 = r11.f1643e5
            r0.mo8821b(r11, r12)
            java.lang.String r12 = "NewTab"
            goto L_0x01a3
        L_0x0190:
            org.chromium.chrome.browser.toolbar.ToolbarManager r12 = r11.mo8103f1()
            r12.mo9478i()
            java.lang.String r12 = "HomePage"
            goto L_0x01a3
        L_0x019a:
            org.chromium.chrome.browser.toolbar.ToolbarManager r12 = r11.mo8103f1()
            r12.mo9473e(r4)
            java.lang.String r12 = "WebSearch"
        L_0x01a3:
            r2 = r12
            goto L_0x01c9
        L_0x01a5:
            if (r0 == 0) goto L_0x01c6
            boolean r1 = r0.mo9344b0()
            java.lang.String r2 = "https://www.bing.com/shop?&entrypoint=edgemmx"
            if (r1 == 0) goto L_0x01b8
            org.chromium.content_public.browser.LoadUrlParams r12 = new org.chromium.content_public.browser.LoadUrlParams
            r12.<init>(r2, r3)
            r0.mo9338b((org.chromium.content_public.browser.LoadUrlParams) r12)
            goto L_0x01c6
        L_0x01b8:
            Cw2 r1 = r11.m1803L0()
            org.chromium.content_public.browser.LoadUrlParams r5 = new org.chromium.content_public.browser.LoadUrlParams
            r5.<init>(r2, r3)
            r2 = 9
            r1.a(r5, r2, r0, r12)
        L_0x01c6:
            java.lang.String r12 = "Shopping"
            goto L_0x01a3
        L_0x01c9:
            r9 = r2
            r7 = 0
            com.microsoft.ruby.telemetry.TelemetryConstants$Actions r8 = com.microsoft.ruby.telemetry.TelemetryConstants.Actions.Click
            java.lang.String[] r10 = new java.lang.String[r3]
            java.lang.String r5 = "MainFrame"
            java.lang.String r6 = "Overflow"
            ss0.a(r5, r6, r7, r8, r9, r10)
            return r4
        L_0x01d7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ChromeTabbedActivity.mo8226a(Dh2):boolean");
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [android.content.Context, org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.ChromeTabbedActivity, android.app.Activity] */
    /* renamed from: a */
    public final /* synthetic */ void mo8218a(int i, Tab tab) {
        String str = null;
        if (i == 0) {
            this.f1653o5.a(HubManager.PageType.FAVORITES);
            this.f1653o5.c();
            str = "Favorites";
        } else if (i == 1) {
            this.f1653o5.a(HubManager.PageType.HISTORY);
            this.f1653o5.c();
            str = "History";
        } else if (i == 2) {
            DownloadUtils.a(this, tab);
            str = "Downloads";
        } else if (i == 4) {
            if (Og0.d()) {
                this.f1653o5.a(HubManager.PageType.SETTINGS);
                this.f1653o5.c();
            } else {
                MicrosoftSigninManager.C0424c.f2104a.mo8889a(MicrosoftSigninManager.TokenScopeType.RT_CHECK, (OAuthTokenProvider.AccessTokenCallback<String>) null);
                PreferencesLauncher.a(this, (Class) null, (Bundle) null);
            }
            str = "Settings";
        } else if (i == 10) {
            if (mo8103f1().f2318u3 != null) {
                mo8103f1().f2318u3.a();
                if (this.f1592c4 != null) {
                    mo8030I0().a(0);
                }
                if (mo8045O0() != null) {
                    mo8045O0().a(0, true);
                }
            }
            str = "FindInPage";
        } else if (i == 23) {
            this.f1653o5.a(HubManager.PageType.COLLECTIONS);
            this.f1653o5.c();
            str = "Collections";
        } else if (i == 24) {
            XA1 c2 = mo8234c2();
            ChromeTabbedActivity chromeTabbedActivity = c2.b;
            if (!c2.h) {
                c2.c(chromeTabbedActivity);
                c2.y = new oC1(chromeTabbedActivity);
                c2.h = true;
            }
            oC1 oc1 = c2.y;
            oc1.u3 = null;
            oc1.v3 = false;
            oc1.a.k();
            oc1.a.b.a().a();
            wC1 wc1 = oc1.q3;
            wc1.l.setImageDrawable(wc1.j);
            wc1.j.start();
            wc1.f = null;
            wc1.m.setText(BuildConfig.FLAVOR);
            wc1.n.setText(BuildConfig.FLAVOR);
            wc1.o.setVisibility(8);
            wc1.p.setVisibility(8);
            EC1 ec1 = oc1.a;
            ec1.b.a().a(new jC1(oc1));
            if (Og0.d()) {
                DynamicMarginCompositorViewHolder G0 = oc1.y.mo8026G0();
                if (G0 instanceof DynamicMarginCompositorViewHolder) {
                    G0.a(oc1);
                }
                Kg0 r = oc1.y.mo8152r();
                if (r != null) {
                    r.c.add(oc1);
                }
                oc1.b(r.d);
                oc1.b.post(new bC1(G0));
                if (oc1.y.mo8246i2()) {
                    oc1.w3 = true;
                    oc1.y.mo8237e2();
                }
            }
            oc1.b.setVisibility(0);
            EE2.b(oc1.e);
            str = "AddToCollection";
        }
        String str2 = str;
        if (str2 != null) {
            ss0.a("MainFrame", "Overflow", (String) null, TelemetryConstants.Actions.Click, str2, new String[0]);
        }
    }

    /* renamed from: a */
    public void mo4509a(Theme theme) {
        Tab v0;
        NewTabPageView newTabPageView;
        super.mo4509a(theme);
        if (Og0.d() && (v0 = mo8172v0()) != null && (v0.mo9403v() instanceof NewTabPage) && (newTabPageView = v0.mo9403v().f1787d) != null) {
            newTabPageView.c(false);
        }
    }
}
