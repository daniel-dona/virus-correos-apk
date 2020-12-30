package org.chromium.chrome.browser.customtabs;

import BL1;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.customtabs.CustomTabsSessionToken;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import com.google.protobuf.ByteString;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.CommandLine;
import org.chromium.base.ThreadUtils;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.chrome.browser.ActivityTabProvider;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.ChromeApplication;
import org.chromium.chrome.browser.ChromeFeatureList;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.browserservices.OriginVerifier;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelManager;
import org.chromium.chrome.browser.customtabs.content.CustomTabActivityNavigationController;
import org.chromium.chrome.browser.dependency_injection.ChromeActivityCommonsModule;
import org.chromium.chrome.browser.fullscreen.ChromeFullscreenManager;
import org.chromium.chrome.browser.hub.HubFragmentBackHelper;
import org.chromium.chrome.browser.hub.HubManager;
import org.chromium.chrome.browser.hub.HubManager$HubStateListener;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.multiwindow.MultiWindowModeStateDispatcher;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.night_mode.NightModeStateProvider;
import org.chromium.chrome.browser.page_info.PageInfoController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.IncognitoTabModel;
import org.chromium.chrome.browser.tabmodel.TabModelSelector;
import org.chromium.chrome.browser.toolbar.ToolbarManager;
import org.chromium.chrome.browser.ui.system.StatusBarColorController;
import org.chromium.components.dom_distiller.core.DomDistillerUrlUtils;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.UiUtils;
import p1;

/* compiled from: PG */
public class CustomTabActivity extends ChromeActivity<DL1> implements HubManager$HubStateListener {

    /* renamed from: N4 */
    public wK1 f1718N4;

    /* renamed from: O4 */
    public CustomTabsSessionToken f1719O4;

    /* renamed from: P4 */
    public Iz1 f1720P4;

    /* renamed from: Q4 */
    public jK1 f1721Q4;

    /* renamed from: R4 */
    public yL1 f1722R4;

    /* renamed from: S4 */
    public BL1 f1723S4;

    /* renamed from: T4 */
    public AL1 f1724T4;

    /* renamed from: U4 */
    public CustomTabActivityNavigationController f1725U4;

    /* renamed from: V4 */
    public HK1 f1726V4;

    /* renamed from: W4 */
    public QL1 f1727W4;

    /* renamed from: X4 */
    public Hz1 f1728X4;

    /* renamed from: Y4 */
    public boolean f1729Y4;

    /* renamed from: Z4 */
    public boolean f1730Z4;

    /* renamed from: a5 */
    public final CustomTabsConnection f1731a5 = CustomTabsConnection.m2111l();

    /* renamed from: b5 */
    public pK1 f1732b5;

    /* renamed from: c5 */
    public AK1 f1733c5;

    /* renamed from: d5 */
    public boolean f1734d5;

    /* renamed from: e5 */
    public HubManager f1735e5;

    /* renamed from: f5 */
    public vK1 f1736f5 = new vK1(this);

    /* renamed from: g5 */
    public HL1 f1737g5;

    /* renamed from: h5 */
    public ps1 f1738h5;

    /* renamed from: i5 */
    public CK1 f1739i5;

    /* renamed from: j5 */
    public BL1.a f1740j5 = new a(this);

    /* renamed from: k5 */
    public L02 f1741k5;

    static {
        Class<CustomTabActivity> cls = CustomTabActivity.class;
    }

    /* renamed from: B0 */
    public Drawable mo8016B0() {
        wK1 wk1 = this.f1718N4;
        int i = wk1.n;
        if (!wk1.b || i == 0) {
            return super.mo8016B0();
        }
        return new ColorDrawable(i);
    }

    /* renamed from: D1 */
    public void mo8021D1() {
        if (this.f1741k5 != null) {
            M02 a = M02.a();
            a.a.remove(this.f1741k5);
        }
        ps1 ps1 = this.f1738h5;
        if (ps1 != null) {
            ps1.e.a();
            Tab tab = ps1.j;
            if (tab != null) {
                tab.mo9340b(ps1.h);
            }
            Yw2 yw2 = ps1.c;
            yw2.d.mo7869b(ps1.f);
            ps1.g.destroy();
        }
        ThemeManager.f1300h.f1303c = null;
    }

    /* renamed from: E */
    public boolean mo4507E() {
        return this.f1734d5;
    }

    /* renamed from: F1 */
    public void mo8025F1() {
    }

    /* renamed from: G */
    public NightModeStateProvider mo8196G() {
        this.f1739i5 = new CK1(mo8769O());
        return this.f1739i5;
    }

    /* renamed from: H1 */
    public void mo8029H1() {
        super.mo8029H1();
        throw null;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivity, android.app.Activity] */
    /* renamed from: I */
    public void mo8198I() {
        this.f1739i5.a(getDelegate(), getIntent());
    }

    /* renamed from: I1 */
    public boolean mo8031I1() {
        return super.mo8031I1() || mo8388U1();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    /* renamed from: J */
    public void mo8032J() {
        super.mo8032J();
        ThemeManager.f1300h.f1303c = new XJ1(this);
        this.f1734d5 = PE2.a(getIntent(), "support_theme", false);
        pK1 pk1 = this.f1732b5;
        if (pk1.a) {
            pk1.a(this);
        }
    }

    /* renamed from: J0 */
    public int mo8033J0() {
        return kx0.custom_tabs_control_container_height;
    }

    /* renamed from: K0 */
    public int mo8035K0() {
        return rx0.custom_tabs_control_container;
    }

    /* renamed from: L1 */
    public boolean mo8039L1() {
        if (!this.f1718N4.j() || !this.f1718N4.e().isEmpty()) {
            return super.mo8039L1();
        }
        return false;
    }

    /* renamed from: R1 */
    public AK1 mo8385R1() {
        return this.f1733c5;
    }

    /* renamed from: S1 */
    public wK1 mo8386S1() {
        return this.f1718N4;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    /* renamed from: T1 */
    public void mo8387T1() {
        if (mo8392Y1()) {
            ON0.a(this);
        } else {
            finish();
        }
    }

    /* renamed from: U1 */
    public final boolean mo8388U1() {
        return ChromeFeatureList.m1784a("AutofillAssistant") && mw1.a(mo8787i().getExtras(), "ENABLED");
    }

    /* renamed from: V1 */
    public boolean mo8389V1() {
        AK1 ak1 = this.f1733c5;
        return ak1 != null && ak1.a();
    }

    /* renamed from: W1 */
    public final /* synthetic */ Theme mo8390W1() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (Build.VERSION.SDK_INT < 21 || (runningTasks = ((ActivityManager) RN0.a.getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.size() <= 0 || !runningTasks.get(0).topActivity.getClassName().contains("CustomTabActivity") || this.f1734d5) {
            return Theme.Null;
        }
        return Theme.Default;
    }

    /* renamed from: X1 */
    public final void mo8391X1() {
        WebContents webContents;
        Cz1 cz1;
        Tab tab = this.f1723S4.b;
        if (tab == null) {
            webContents = null;
        } else {
            webContents = tab.mo9302K();
        }
        this.f1731a5.mo8420a(this.f1718N4.a, webContents);
        HL1 hl1 = this.f1737g5;
        if (hl1 != null && (cz1 = hl1.a) != null) {
            cz1.b((WebContents) null);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    /* renamed from: Y1 */
    public final boolean mo8392Y1() {
        return (getIntent().getFlags() & 268959744) != 0;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: a0 */
    public void mo8078a0() {
        super.mo8078a0();
        StatusBarColorController a1 = mo8079a1();
        JE2.a(getResources(), false, getBaseStatusBarColor());
        a1.a();
        int i = this.f1718N4.C;
        mo8103f1().f2309e.a.setCustomizedColor(i);
        mo8103f1().f2309e.a.setCustomTabIntentDataProvider(this.f1718N4);
        mo8103f1().f2323y.a(i);
        if (!this.f1718N4.P || this.f1733c5.a()) {
            mo8103f1().f2302a4 = false;
        }
        if (this.f1723S4.b != null) {
            InfoBarContainer.a(this.f1723S4.b).a((ViewGroup) findViewById(ox0.bottom_container));
        }
        ON0.a(this, (String) null, (Bitmap) null, this.f1718N4.C);
        this.f1721Q4 = mo8024F0().i();
        this.f1721Q4.e();
        HashMap hashMap = new HashMap();
        hashMap.put("hostapp", this.f1718N4.X);
        ss0.c("CustomTab", hashMap);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    /* renamed from: b0 */
    public void mo8088b0() {
        Integer num;
        this.f1733c5 = new AK1(getIntent());
        Intent intent = getIntent();
        CK1 ck1 = this.f1739i5;
        boolean z = true;
        this.f1718N4 = new wK1(intent, this, (ck1 == null || !ck1.e()) ? 1 : 2);
        super.mo8088b0();
        this.f1723S4.a.mo7868a(this.f1740j5);
        mo8391X1();
        wK1 wk1 = this.f1718N4;
        this.f1719O4 = wk1.a;
        if (wk1.u) {
            this.f1741k5 = new c(this);
            M02.a().a.add(this.f1741k5);
            if (!CommandLine.m1384c().mo7859c("enable-incognito-snapshots-in-android-recents")) {
                getWindow().addFlags(ByteString.MAX_READ_FROM_CHUNK_SIZE);
            }
        }
        wK1 wk12 = this.f1718N4;
        if (Build.VERSION.SDK_INT >= 21 && (num = wk12.s) != null) {
            Window window = getWindow();
            boolean z2 = !JE2.d(num.intValue());
            if (Build.VERSION.SDK_INT <= 26) {
                z = false;
            }
            if (z) {
                UiUtils.b(window.getDecorView().getRootView(), z2);
            } else if (z2) {
                num = Integer.valueOf(JE2.a(num.intValue()));
            }
            window.setNavigationBarColor(num.intValue());
            if (z2 && Build.VERSION.SDK_INT >= 28) {
                window.setNavigationBarDividerColor(ON0.a(getResources(), jx0.black_alpha_12));
            }
        }
        this.f1735e5 = mo8024F0().g();
    }

    /* renamed from: c */
    public void mo8393c(String str) {
        if (mo8172v0() != null) {
            mo8172v0().mo9338b(new LoadUrlParams(str, 0));
        }
    }

    public void createContextualSearchTab(String str) {
        if (mo8172v0() != null) {
            mo8172v0().mo9338b(new LoadUrlParams(str, 0));
        }
    }

    /* renamed from: d */
    public boolean mo8394d(Intent intent) {
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean a = xu1.a(keyEvent, this, this.f1727W4.t3);
        return a != null ? a.booleanValue() : super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: e0 */
    public boolean mo8395e0() {
        yL1 yl1 = this.f1722R4;
        boolean z = !TextUtils.isEmpty(yl1.c.mo8460f(yl1.t3));
        int i = yl1.s3.c;
        if (i == 3 || i == 4 || z || yl1.n.mo8311d()) {
            return false;
        }
        return true;
    }

    /* renamed from: e1 */
    public int mo8099e1() {
        return rx0.edge_custom_tabs_toolbar;
    }

    /* renamed from: f */
    public void mo8100f() {
        super.mo8100f();
        this.f1728X4.a(this.f1720P4);
        BL1 bl1 = this.f1723S4;
        int i = bl1.c;
        Tab tab = bl1.b;
        boolean z = false;
        if (tab != null && ((i == 4 || i == 3) && !tab.mo9317Z())) {
            z = true;
        }
        if (z) {
            mo8027G1();
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    public void finish() {
        CustomTabActivity.super.finish();
        wK1 wk1 = this.f1718N4;
        if (wk1 == null || !wk1.b()) {
            wK1 wk12 = this.f1718N4;
            if (wk12 != null && wk12.P) {
                overridePendingTransition(ex0.no_anim, ex0.activity_close_exit);
                return;
            }
            return;
        }
        this.f1729Y4 = true;
        wK1 wk13 = this.f1718N4;
        int i = wk13.b() ? wk13.d.getInt(qz1.g) : 0;
        wK1 wk14 = this.f1718N4;
        overridePendingTransition(i, wk14.b() ? wk14.d.getInt(qz1.h) : 0);
        this.f1729Y4 = false;
    }

    /* renamed from: g */
    public final /* synthetic */ void mo8397g(int i) {
        int i2;
        if (i == 0) {
            String b = mo8172v0() == null ? null : Ov2.b(mo8172v0());
            if (b != null) {
                if (b.equals(this.f1731a5.mo8453d(this.f1719O4))) {
                    i2 = this.f1730Z4 ? 3 : 2;
                } else {
                    i2 = this.f1730Z4 ? 1 : 0;
                }
                if (MY1.b(b)) {
                    RecordHistogram.m1539a("CustomTabs.ConnectionStatusOnReturn.GSA", i2, 4);
                } else {
                    RecordHistogram.m1539a("CustomTabs.ConnectionStatusOnReturn.NonGSA", i2, 4);
                }
            }
        }
        mo8387T1();
    }

    public int getBaseStatusBarColor() {
        HK1 hk1 = this.f1726V4;
        if (hk1.b.P) {
            return 0;
        }
        Tab tab = hk1.c.b;
        if (tab != null) {
            if (tab.mo9349c0()) {
                return JE2.a(hk1.a);
            }
            if (hk1.f && !hk1.e.a(tab)) {
                return 0;
            }
        }
        return hk1.b.C;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    public String getPackageName() {
        if (this.f1729Y4) {
            return this.f1718N4.a();
        }
        return CustomTabActivity.super.getPackageName();
    }

    /* renamed from: h1 */
    public boolean mo8111h1() {
        boolean z;
        if (this.f1735e5.b()) {
            if (!HubFragmentBackHelper.a(this)) {
                this.f1735e5.a();
            }
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        if (this.f1733c5.a()) {
            mo8387T1();
        }
        CustomTabActivityNavigationController customTabActivityNavigationController = this.f1725U4;
        if (!customTabActivityNavigationController.p.f2053e || customTabActivityNavigationController.b.b == null) {
            return false;
        }
        if (((ChromeFullscreenManager) customTabActivityNavigationController.x.get()).a.g) {
            ((ChromeFullscreenManager) customTabActivityNavigationController.x.get()).a();
            return true;
        }
        CustomTabActivityNavigationController.BackHandler backHandler = customTabActivityNavigationController.y;
        if (backHandler != null && backHandler.handleBackPressed(new rL1(customTabActivityNavigationController))) {
            return true;
        }
        customTabActivityNavigationController.a();
        return true;
    }

    /* renamed from: i0 */
    public boolean mo8113i0() {
        String c;
        if (!ChromeFeatureList.m1784a("ShowTrustedPublisherURL")) {
            return false;
        }
        Tab tab = this.f1723S4.b;
        if ((tab == null || !tab.mo9349c0()) && (c = this.f1731a5.mo8450c()) != null && c.equals(this.f1731a5.mo8453d(this.f1719O4))) {
            return true;
        }
        return false;
    }

    public boolean isStatusBarDefaultThemeColor() {
        HK1 hk1 = this.f1726V4;
        super.isStatusBarDefaultThemeColor();
        return hk1.a(false);
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: l */
    public Lv1 mo8121l() {
        ActivityTabProvider w0 = mo8174w0();
        MultiWindowModeStateDispatcher P = mo8770P();
        dx2 d1 = m2079d1();
        ToolbarManager f1 = mo8103f1();
        View decorView = getWindow().getDecorView();
        wK1 wk1 = this.f1718N4;
        int i = wk1.j;
        List e = wk1.e();
        wK1 wk12 = this.f1718N4;
        return new bK1(this, w0, P, d1, f1, decorView, i, e, wk12.P, wk12.J, !wk12.o, !wk12.p, wk12.u);
    }

    /* renamed from: m */
    public void mo8124m() {
        super.mo8124m();
        m2079d1().a(mo8091c1());
        jK1 jk1 = this.f1721Q4;
        if (jk1.a.mo8026G0().y() != null) {
            OverlayPanelManager overlayPanelManager = jk1.a.mo8026G0().y().z3;
            overlayPanelManager.b.mo7868a(new gK1(jk1));
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        pK1 pk1 = this.f1732b5;
        if (pk1 != null && pk1.a) {
            pk1.b(this);
        }
    }

    public void onHubClosed() {
    }

    public void onHubShown() {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    public void onMAMNewIntent(Intent intent) {
        Intent intent2 = getIntent();
        super.onMAMNewIntent(intent);
        setIntent(intent2);
    }

    public void onMAMPause() {
        super.onMAMPause();
        this.f1736f5.a(false);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, com.microsoft.intune.mam.client.support.v7.app.MAMAppCompatActivityBase, android.app.Activity] */
    public void onMAMPostCreate(Bundle bundle) {
        CustomTabActivity.super.onMAMPostCreate(bundle);
        pK1 pk1 = this.f1732b5;
        if (pk1 != null && pk1.a) {
            pk1.b(this);
        }
    }

    public void onMAMResume() {
        super.onMAMResume();
        this.f1736f5.a(true);
    }

    public void onStart() {
        super.onStart();
        CustomTabsConnection customTabsConnection = this.f1731a5;
        wK1 wk1 = this.f1718N4;
        this.f1730Z4 = customTabsConnection.mo8452c(wk1.a, wk1.c);
        this.f1736f5.a(true);
    }

    public void onStop() {
        super.onStop();
        this.f1731a5.mo8451c(this.f1718N4.a);
        this.f1730Z4 = false;
        this.f1736f5.a(false);
    }

    /* renamed from: p0 */
    public Pair<Cw2, Cw2> mo8147p0() {
        AL1 al1 = this.f1724T4;
        return Pair.create(al1.a(false), al1.a(true));
    }

    /* renamed from: q */
    public boolean mo8149q() {
        if (mo8172v0() == null || !this.f1727W4.t3) {
            return false;
        }
        return super.mo8149q();
    }

    /* renamed from: q0 */
    public TabModelSelector mo8150q0() {
        return this.f1724T4.a();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, java.lang.Object, android.app.Activity] */
    public void setTheme(int i) {
        this.f1732b5 = new pK1(getIntent());
        if (this.f1732b5.a) {
            super.setTheme(xx0.CustomTabTheme);
            return;
        }
        super.setTheme(xx0.Base_V17_Theme_Chromium);
        try {
            Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            VN0.a("CustomTabActivity", "callConvertFromTranslucent", new Object[]{e});
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: t */
    public void mo8165t() {
        TL1 tl1;
        this.f1718N4.i();
        boolean z = this.f1718N4.u;
        IncognitoTabModel b = m2079d1().b(z ? 1 : 0);
        if (z && (b instanceof IncognitoTabModel) && (tl1 = this.f1718N4.v) != null && tl1.a != null) {
            b.c();
            Profile g = b.mo9434g();
            for (VL1 vl1 : tl1.a) {
                String str = vl1.a;
                if (vl1.b != null && !TextUtils.isEmpty(str)) {
                    for (RL1 rl1 : vl1.b) {
                        g.mo9200a(rl1.a, rl1.b, str);
                    }
                }
            }
        }
        this.f1720P4 = new C0384b();
        CustomTabsConnection customTabsConnection = this.f1731a5;
        getIntent();
        customTabsConnection.mo8468i();
        if (Build.VERSION.SDK_INT >= 21 && mo8392Y1()) {
            this.f1738h5 = new ps1(this, ON0.a(getResources(), jx0.default_primary_color));
        }
        if (isTaskRoot() && tE2.d()) {
            tE2.c().a(m2079d1(), this);
        }
        super.mo8165t();
        if (mo8388U1()) {
            mw1.a(this);
        }
    }

    /* renamed from: v0 */
    public Tab mo8172v0() {
        return this.f1723S4.b;
    }

    /* renamed from: y0 */
    public int mo8178y0() {
        return 2;
    }

    /* renamed from: b */
    public int mo4510b(Theme theme) {
        return du0.a(getResources(), jx0.custom_tab_primary_color);
    }

    /* renamed from: d1 */
    public dx2 m2079d1() {
        return super.mo8095d1();
    }

    /* renamed from: a */
    public boolean mo8076a(int i, Bundle bundle) {
        int i2 = (bundle == null || !bundle.containsKey("CustomMenuItemId")) ? -1 : bundle.getInt("CustomMenuItemId");
        if (i2 < 0) {
            return super.mo8076a(i, bundle);
        }
        this.f1718N4.a(this, i2, mo8172v0().getUrl(), mo8172v0().getTitle());
        return true;
    }

    /* renamed from: c */
    public void mo8090c() {
        super.mo8090c();
        Hz1 hz1 = this.f1728X4;
        if (hz1.c == this.f1720P4) {
            hz1.c = null;
        }
    }

    /* renamed from: a */
    public void mo8065a(Intent intent) {
        super.mo8065a(intent);
        this.f1728X4.a(this.f1720P4);
        if (!this.f1728X4.b(intent)) {
            intent.setFlags(intent.getFlags() & -603979777);
            startActivity(intent);
        }
    }

    /* renamed from: a */
    public void mo8066a(MenuItem menuItem) {
        if (menuItem != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("itemId", Pp0.a(menuItem.getItemId()));
            ss0.b("CustomTabApplicationMenuClick", hashMap, true, 0, (String) null);
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r7v0, types: [org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
    /* renamed from: a */
    public boolean mo8077a(int i, boolean z) {
        if (i == ox0.focus_url_bar || i == ox0.all_bookmarks_menu_id || i == ox0.feedback_id || i == ox0.recent_tabs_id || i == ox0.new_in_private_tab_id || i == ox0.new_tab_id || i == ox0.open_history_menu_id) {
            return true;
        }
        if (i == ox0.add_to_favorites_id) {
            mo8070a(mo8172v0());
            return true;
        }
        boolean z2 = false;
        if (i == ox0.open_in_browser_id) {
            CustomTabActivityNavigationController customTabActivityNavigationController = this.f1725U4;
            Tab tab = customTabActivityNavigationController.b.b;
            if (tab != null) {
                String url = tab.getUrl();
                if (DomDistillerUrlUtils.b(url)) {
                    url = DomDistillerUrlUtils.a(url);
                }
                if (TextUtils.isEmpty(url)) {
                    url = customTabActivityNavigationController.c.h();
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                intent.setFlags(268435456);
                intent.setComponent(new ComponentName(RN0.a, ChromeTabbedActivity.class));
                p1.a(intent);
                wK1 wk1 = customTabActivityNavigationController.c;
                boolean z3 = wk1.P || wk1.u;
                StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                try {
                    boolean d = z3 | XV1.d(intent, true);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    Bundle a = Q4.a(customTabActivityNavigationController.q, ex0.abc_fade_in, ex0.abc_fade_out).a();
                    if (!d) {
                        StrictMode.allowThreadDiskWrites();
                        try {
                            if (customTabActivityNavigationController.c.j == 3) {
                                z2 = true;
                            }
                            if (z2) {
                                IntentHandler.m1934s(intent);
                            } else {
                                customTabActivityNavigationController.q.startActivity(intent, a);
                            }
                        } finally {
                            StrictMode.setThreadPolicy(allowThreadDiskWrites);
                        }
                    } else {
                        customTabActivityNavigationController.b.a.mo7869b(customTabActivityNavigationController.t3);
                        yL1 yl1 = customTabActivityNavigationController.a;
                        sL1 sl1 = new sL1(customTabActivityNavigationController);
                        Tab tab2 = yl1.s3.b;
                        if (tab2 != null) {
                            if (Og0.d() || ChromeFeatureList.m1784a("TabReparenting")) {
                                BL1 bl1 = yl1.s3;
                                if (bl1.b != null) {
                                    bl1.b = null;
                                    bl1.c = 0;
                                    Iterator it = bl1.a.iterator();
                                    while (it.hasNext()) {
                                        ((BL1.a) it.next()).a();
                                    }
                                }
                            }
                            tab2.mo9335a(intent, a, (Runnable) sl1);
                        }
                    }
                    z2 = true;
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    throw th;
                }
            }
            if (z2) {
                this.f1731a5.mo8472l(this.f1719O4);
            }
            return true;
        } else if (i != ox0.info_menu_id) {
            return super.mo8077a(i, z);
        } else {
            if (m2079d1().g() == null) {
                return false;
            }
            PageInfoController.a(this, m2079d1().g(), mo8103f1().f2309e.a.mo9563r(), 1);
            return true;
        }
    }

    /* renamed from: org.chromium.chrome.browser.customtabs.CustomTabActivity$b */
    /* compiled from: PG */
    public class C0384b implements Iz1 {
        public C0384b() {
        }

        /* JADX WARNING: type inference failed for: r7v4, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
        /* JADX WARNING: type inference failed for: r8v4, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
        /* JADX WARNING: type inference failed for: r7v8, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
        /* renamed from: a */
        public boolean mo8403a(int i, Bitmap bitmap, String str) {
            WJ1 wj1;
            Iterator it = CustomTabActivity.this.f1718N4.G.iterator();
            while (true) {
                if (!it.hasNext()) {
                    wj1 = null;
                    break;
                }
                wj1 = (WJ1) it.next();
                if (i == wj1.b) {
                    break;
                }
            }
            if (wj1 == null) {
                VN0.c("CustomTabActivity", "Custom toolbar button with ID %d not found", new Object[]{Integer.valueOf(i)});
                return false;
            }
            wj1.c = bitmap;
            wj1.d = str;
            if (wj1.f) {
                QL1 ql1 = CustomTabActivity.this.f1727W4;
                if (!WJ1.a(ql1.k, wj1.c)) {
                    return false;
                }
                wK1 wk1 = ql1.b;
                int i2 = wj1.b;
                int i3 = 0;
                while (true) {
                    if (i3 >= wk1.K.size()) {
                        i3 = -1;
                        break;
                    } else if (((WJ1) wk1.K.get(i3)).b == i2) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i3 == -1) {
                    return false;
                }
                ((ToolbarManager) ql1.a.get()).f2309e.a.mo9514a(i3, wj1.a(ql1.k), wj1.d);
                return true;
            }
            jK1 jk1 = CustomTabActivity.this.f1721Q4;
            ImageButton imageButton = (ImageButton) jk1.b().findViewById(wj1.b);
            imageButton.setContentDescription(wj1.d);
            imageButton.setImageDrawable(wj1.a(jk1.a));
            return true;
        }

        /* renamed from: b */
        public CustomTabsSessionToken mo8407b() {
            return CustomTabActivity.this.f1719O4;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.customtabs.CustomTabActivity, android.app.Activity] */
        /* renamed from: c */
        public int mo8408c() {
            return CustomTabActivity.this.getTaskId();
        }

        /* renamed from: a */
        public boolean mo8406a(RemoteViews remoteViews, int[] iArr, PendingIntent pendingIntent) {
            return CustomTabActivity.this.f1721Q4.a(remoteViews, iArr, pendingIntent);
        }

        /* renamed from: a */
        public Bundle mo8401a(String str, String str2, Bundle bundle) {
            ThreadUtils.m1462c();
            HashMap hashMap = new HashMap();
            hashMap.put("commandName", str);
            ss0.b("CustomTabExtraCommand", hashMap, true, 0, (String) null);
            if (!str.equals("update_remoteview")) {
                return CustomTabActivity.this.f1736f5.a(str, str2);
            }
            mo8406a((RemoteViews) PE2.b(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS"), PE2.a(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS"), (PendingIntent) PE2.b(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT"));
            return null;
        }

        /* renamed from: a */
        public Class<? extends Activity> mo8402a() {
            return CustomTabActivity.this.getClass();
        }

        /* renamed from: a */
        public boolean mo8404a(Intent intent) {
            if (CustomTabActivity.this.f1578O3.mo8291d(intent)) {
                VN0.c("CustomTabActivity", "Incoming intent to Custom Tab was ignored.", new Object[0]);
                return false;
            }
            String g = IntentHandler.m1922g(intent);
            if (TextUtils.isEmpty(g)) {
                return false;
            }
            LoadUrlParams loadUrlParams = new LoadUrlParams(g, 0);
            loadUrlParams.mo9719d(DataReductionProxySettings.m2648o().mo8941a(loadUrlParams.mo9735q()));
            CustomTabActivity.this.f1725U4.a(loadUrlParams, intent.getLongExtra("org.chromium.chrome.browser.timestamp", -1));
            return true;
        }

        /* renamed from: a */
        public boolean mo8405a(Uri uri) {
            CustomTabActivity customTabActivity = CustomTabActivity.this;
            String d = customTabActivity.f1731a5.mo8453d(customTabActivity.f1719O4);
            if (TextUtils.isEmpty(d)) {
                return false;
            }
            return OriginVerifier.m1981b(d, new yz1(uri), 1);
        }
    }

    /* renamed from: a */
    public static void m2042a(Context context, String str) {
        Activity activity;
        Tab v0;
        p1.a aVar = new p1.a();
        aVar.a(true);
        p1 a = aVar.a();
        a.a.setData(Uri.parse(str));
        Intent a2 = yu1.a(context, a.a);
        a2.setPackage(context.getPackageName());
        a2.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 3);
        a2.putExtra("com.android.browser.application_id", context.getPackageName());
        ChromeActivity chromeActivity = (ChromeActivity) ChromeActivity.f1556M4.get();
        if (!(chromeActivity == null || (v0 = chromeActivity.mo8172v0()) == null)) {
            if ("https://aka.ms/EdgeMMXfamilysafety".equals(str)) {
                a2.putExtra("com.microsoft.emmx.customtabs.OPEN_INCOGNITO", false);
            } else {
                a2.putExtra("com.microsoft.emmx.customtabs.OPEN_INCOGNITO", v0.mo9315X());
            }
        }
        boolean z = context instanceof Activity;
        if (!z) {
            a2.addFlags(268435456);
        }
        IntentHandler.m1920e(a2);
        a2.putExtra("support_theme", true);
        if (Og0.d()) {
            if (z) {
                activity = (Activity) context;
            } else {
                activity = ApplicationStatus.f1396d;
            }
            a2.addFlags(402653184);
            Og0.f.a(activity);
            context.startActivity(a2, (Bundle) null);
            return;
        }
        context.startActivity(a2);
    }

    /* renamed from: a */
    public DL1 m2066a(ChromeActivityCommonsModule chromeActivityCommonsModule) {
        zM1 zm1;
        EL1 el1 = new EL1(this.f1718N4, this.f1739i5);
        rM1 e = ChromeApplication.m1775e();
        if (Og0.d()) {
            rM1 rm1 = e;
            zm1 = new zM1(rm1, chromeActivityCommonsModule, el1, rm1.i(), (qM1) null);
        } else {
            zm1 = e.a(chromeActivityCommonsModule, el1);
        }
        this.f1726V4 = zm1.c();
        this.f1722R4 = zm1.e();
        this.f1723S4 = zm1.h();
        this.f1724T4 = zm1.l();
        this.f1727W4 = zm1.j();
        zm1.f();
        this.f1725U4 = zm1.d();
        this.f1725U4.q3 = new YJ1(this);
        zm1.b();
        this.f1728X4 = zm1.getParent().j();
        if (this.f1718N4.r) {
            zm1.k();
        }
        if (this.f1731a5.mo8474n(this.f1718N4.a)) {
            zm1.a();
        }
        return zm1;
    }

    /* renamed from: a */
    public void mo8062a(long j) {
        RecordHistogram.m1550d("MobileStartup.IntentToCreationTime", j);
        RecordHistogram.m1550d("MobileStartup.IntentToCreationTime.CustomTabs", j);
    }
}
