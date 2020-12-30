package org.chromium.chrome.browser.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.bing.commonlib.model.search.SearchAction;
import com.microsoft.bing.partnercodelib.api.PartnerCodeManager;
import com.microsoft.bing.usbsdk.api.BingClientManager;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import com.microsoft.managedbehavior.CitrixManager;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.theme.Theme;
import com.microsoft.theme.ThemeManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.base.ObserverList;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.ChromeFeatureList;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.WebContentsFactory;
import org.chromium.chrome.browser.bing_search_sdk.BingSearchWidgetManager;
import org.chromium.chrome.browser.complex_tasks.TaskTabHelper;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.content.ContentUtils;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulator;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchTabHelper;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.dom_distiller.TabDistillabilityProvider;
import org.chromium.chrome.browser.edge_feedback.FeedbackSessionManager;
import org.chromium.chrome.browser.edge_ntp.NativePageFactory;
import org.chromium.chrome.browser.edge_ntp.NewTabPage;
import org.chromium.chrome.browser.edge_ntp.NewTabPageView;
import org.chromium.chrome.browser.fullscreen.FullscreenOptions;
import org.chromium.chrome.browser.prerender.ExternalPrerenderHandler;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory;
import org.chromium.chrome.browser.ssl.SecurityStateModel;
import org.chromium.chrome.browser.tab.TabState;
import org.chromium.components.dom_distiller.core.DomDistillerUrlUtils;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.url_formatter.UrlFormatter;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.ViewEventSink;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
public class Tab implements zu0 {

    /* renamed from: Y3 */
    public static final Pattern f2219Y3 = Pattern.compile("^http[s]?://[a-zA-Z0-9]+\\.bing\\..*");

    /* renamed from: A3 */
    public boolean f2220A3;

    /* renamed from: B3 */
    public boolean f2221B3;

    /* renamed from: C3 */
    public final Integer f2222C3;

    /* renamed from: D3 */
    public Integer f2223D3;

    /* renamed from: E3 */
    public TabState.a f2224E3;

    /* renamed from: F3 */
    public LoadUrlParams f2225F3;

    /* renamed from: G3 */
    public String f2226G3;

    /* renamed from: H3 */
    public boolean f2227H3;

    /* renamed from: I3 */
    public boolean f2228I3;

    /* renamed from: J3 */
    public boolean f2229J3;

    /* renamed from: K3 */
    public boolean f2230K3;

    /* renamed from: L3 */
    public int f2231L3;

    /* renamed from: M3 */
    public boolean f2232M3;

    /* renamed from: N3 */
    public long f2233N3;

    /* renamed from: O3 */
    public String f2234O3;

    /* renamed from: P3 */
    public boolean f2235P3;

    /* renamed from: Q3 */
    public yg0 f2236Q3;

    /* renamed from: R3 */
    public Uv2 f2237R3;

    /* renamed from: S3 */
    public View.OnAttachStateChangeListener f2238S3;

    /* renamed from: T3 */
    public boolean f2239T3;

    /* renamed from: U3 */
    public boolean f2240U3;

    /* renamed from: V3 */
    public final sO0 f2241V3;

    /* renamed from: W3 */
    public boolean f2242W3;

    /* renamed from: X3 */
    public boolean f2243X3;

    /* renamed from: a */
    public long f2244a;

    /* renamed from: b */
    public final int f2245b;

    /* renamed from: c */
    public final boolean f2246c;

    /* renamed from: d */
    public boolean f2247d = true;

    /* renamed from: e */
    public Date f2248e;

    /* renamed from: k */
    public final Context f2249k;

    /* renamed from: n */
    public WindowAndroid f2250n;

    /* renamed from: p */
    public boolean f2251p;

    /* renamed from: q */
    public lb2 f2252q;

    /* renamed from: q3 */
    public WebContents f2253q3;

    /* renamed from: r3 */
    public ViewGroup f2254r3;

    /* renamed from: s3 */
    public final ObserverList<Yv2> f2255s3 = new ObserverList<>();

    /* renamed from: t3 */
    public TabWebContentsDelegateAndroid f2256t3;

    /* renamed from: u3 */
    public int f2257u3;

    /* renamed from: v3 */
    public final int f2258v3;

    /* renamed from: w3 */
    public final int f2259w3;

    /* renamed from: x */
    public NewTabPage f2260x;

    /* renamed from: x3 */
    public int f2261x3;

    /* renamed from: y */
    public boolean f2262y;

    /* renamed from: y3 */
    public Is0 f2263y3;

    /* renamed from: z3 */
    public boolean f2264z3;

    @SuppressLint({"HandlerLeak"})
    public Tab(int i, Tab tab, boolean z, WindowAndroid windowAndroid, Integer num, Integer num2, LoadUrlParams loadUrlParams) {
        int i2 = -1;
        this.f2257u3 = -1;
        this.f2221B3 = true;
        this.f2230K3 = true;
        this.f2231L3 = 0;
        this.f2233N3 = -1;
        this.f2241V3 = new sO0();
        this.f2242W3 = false;
        this.f2243X3 = false;
        this.f2245b = Wv2.a().a(i);
        this.f2246c = z;
        if (tab == null) {
            this.f2258v3 = -1;
            this.f2259w3 = -1;
        } else {
            this.f2258v3 = tab.getId();
            this.f2259w3 = tab.mo9315X() == z ? this.f2258v3 : i2;
        }
        this.f2261x3 = this.f2245b;
        this.f2249k = ic2.a(RN0.a, ChromeActivity.m1627O1(), false);
        Context context = this.f2249k;
        if (context != null) {
            MAMEdgeManager.m1295a(context, MAMEdgeManager.m1292a(z));
        }
        this.f2250n = windowAndroid;
        this.f2222C3 = num;
        this.f2223D3 = num;
        this.f2225F3 = loadUrlParams;
        if (loadUrlParams != null) {
            this.f2226G3 = loadUrlParams.mo9735q();
        }
        if (num2 != null) {
            iw2.i(this, num2.intValue());
        }
        mo9300I().a(TabDistillabilityProvider.n, new TabDistillabilityProvider(this));
        mo9300I().a(fw2.k, new fw2(this));
        mo9300I().a(InterceptNavigationDelegateImpl.p, new InterceptNavigationDelegateImpl(this));
        new ContextualSearchTabHelper(this);
        if (ChromeFeatureList.m1783a() && ChromeFeatureList.m1784a("ShoppingAssist") && Wx2.c == null) {
            Wx2.c = new Wx2(this);
        }
        new M72(this);
        if (tab != null) {
            Pv2 a = Pv2.a(this);
            Object valueOf = Long.valueOf(TaskTabHelper.nativeGetTaskId(tab.mo9302K()));
            a.a.put("ParentTaskId", valueOf == null ? Pv2.c : valueOf);
            Pv2 a2 = Pv2.a(this);
            Object valueOf2 = Long.valueOf(TaskTabHelper.nativeGetRootTaskId(tab.mo9302K()));
            a2.a.put("ParentRootTaskId", valueOf2 == null ? Pv2.c : valueOf2);
        }
        mo9300I().a(TabBrowserControlsState.x, new TabBrowserControlsState(this));
        if (mo9315X()) {
            QJ1.a.a();
        }
        this.f2238S3 = new a(this);
        mo9319a((Yv2) new ff0());
        ThemeManager.f1300h.mo4504a((zu0) this);
    }

    @CalledByNative
    private void clearNativePtr() {
        this.f2244a = 0;
    }

    @CalledByNative
    private void deleteNavigationEntriesFromFrozenState(long j) {
        TabState.a aVar;
        TabState.a aVar2 = this.f2224E3;
        if (aVar2 != null) {
            ByteBuffer nativeDeleteNavigationEntries = TabState.nativeDeleteNavigationEntries(aVar2.a, aVar2.b, j);
            if (nativeDeleteNavigationEntries == null) {
                aVar = null;
            } else {
                TabState.a aVar3 = new TabState.a(nativeDeleteNavigationEntries);
                aVar3.b = 2;
                aVar = aVar3;
            }
            if (aVar != null) {
                this.f2224E3 = aVar;
                mo9386m0();
            }
        }
    }

    @CalledByNative
    private long getNativePtr() {
        return this.f2244a;
    }

    private native boolean nativeAreRendererInputEventsIgnored(long j);

    private native void nativeAttachDetachedTab(long j);

    private native void nativeCreateHistoricalTab(long j);

    private native void nativeDestroy(long j);

    private native void nativeDestroyWebContents(long j);

    private native long nativeGetBookmarkId(long j, boolean z);

    private native long nativeGetBookmarkIdByUrl(long j, boolean z, String str);

    private native Profile nativeGetProfileAndroid(long j);

    private native boolean nativeHasPrerenderedUrl(long j, String str);

    private native void nativeInit();

    private native void nativeInitWebContents(long j, boolean z, boolean z2, WebContents webContents, int i, TabWebContentsDelegateAndroid tabWebContentsDelegateAndroid, ContextMenuPopulator contextMenuPopulator);

    private native void nativeLoadOriginalImage(long j);

    private native int nativeLoadUrl(long j, String str, String str2, String str3, ResourceRequestBody resourceRequestBody, int i, String str4, int i2, boolean z, boolean z2, boolean z3, boolean z4, long j2, long j3);

    private native void nativeOnPhysicalBackingSizeChanged(long j, WebContents webContents, int i, int i2);

    private native void nativeReleaseWebContents(long j);

    private native void nativeSetActiveNavigationEntryTitleForUrl(long j, String str, String str2);

    private native void nativeUpdateDelegates(long j, TabWebContentsDelegateAndroid tabWebContentsDelegateAndroid, ContextMenuPopulator contextMenuPopulator);

    @CalledByNative
    private void setNativePtr(long j) {
        this.f2244a = j;
    }

    /* renamed from: A */
    public int mo9290A() {
        if (!mo9317Z()) {
            return 100;
        }
        return this.f2253q3.mo9777h0();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e  */
    /* renamed from: A0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo9291A0() {
        /*
            r3 = this;
            boolean r0 = r3.f2230K3
            if (r0 != 0) goto L_0x0018
            boolean r0 = r3.mo9313V()
            if (r0 != 0) goto L_0x0018
            boolean r0 = r3.f2240U3
            if (r0 != 0) goto L_0x0016
            cF2 r0 = org.chromium.chrome.browser.p010vr.VrModuleProvider.m3290a()
            r0.e()
            goto L_0x0018
        L_0x0016:
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            boolean r1 = r3.f2239T3
            if (r0 != r1) goto L_0x001e
            return
        L_0x001e:
            r3.f2239T3 = r0
            org.chromium.base.ObserverList<Yv2> r1 = r3.f2255s3
            java.util.Iterator r1 = r1.iterator()
        L_0x0026:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r1.next()
            Yv2 r2 = (Yv2) r2
            r2.a(r0)
            goto L_0x0026
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.Tab.mo9291A0():void");
    }

    /* renamed from: B */
    public int mo9292B() {
        return this.f2261x3;
    }

    /* renamed from: B0 */
    public void mo9293B0() {
        String str;
        if (!mo9313V()) {
            if (isNativePage()) {
                str = this.f2252q.getTitle();
            } else {
                str = mo9302K() != null ? mo9302K().getTitle() : BuildConfig.FLAVOR;
            }
            mo9346c(str);
        }
    }

    /* renamed from: C */
    public int mo9294C() {
        return SecurityStateModel.a(mo9302K());
    }

    /* renamed from: D */
    public ObserverList.RewindableIterator<Yv2> mo9295D() {
        return this.f2255s3.mo7867a();
    }

    /* renamed from: E */
    public TabWebContentsDelegateAndroid mo9296E() {
        return this.f2256t3;
    }

    /* renamed from: F */
    public Context mo9297F() {
        return this.f2249k;
    }

    /* renamed from: G */
    public long mo9298G() {
        return this.f2233N3;
    }

    /* renamed from: H */
    public long mo9299H() {
        if (pr2.e(getUrl())) {
            if (mo9313V()) {
                return -1;
            }
            return nativeGetBookmarkIdByUrl(this.f2244a, false, pr2.a(getUrl()));
        } else if (mo9313V()) {
            return -1;
        } else {
            return nativeGetBookmarkId(this.f2244a, false);
        }
    }

    /* renamed from: I */
    public sO0 mo9300I() {
        return this.f2241V3;
    }

    /* renamed from: J */
    public View mo9301J() {
        lb2 lb2 = this.f2252q;
        return lb2 != null ? lb2.getView() : this.f2254r3;
    }

    /* renamed from: K */
    public WebContents mo9302K() {
        return this.f2253q3;
    }

    /* renamed from: L */
    public int mo9303L() {
        View J = mo9301J();
        if (J != null) {
            return J.getWidth();
        }
        return 0;
    }

    /* renamed from: M */
    public WindowAndroid mo9304M() {
        return this.f2250n;
    }

    /* renamed from: N */
    public void mo9305N() {
        if (mo9302K() != null) {
            ss0.a("tab_window_back", "CV", ss0.b(getId()), "hrValue", "0");
            mo9302K().mo9742F().b();
            Yx1.a();
        }
    }

    /* renamed from: O */
    public void mo9306O() {
        if (mo9302K() != null) {
            ss0.a("tab_window_forward", "CV", ss0.b(getId()), "hrValue", "0");
            mo9302K().mo9742F().m();
        }
    }

    /* renamed from: P */
    public void mo9307P() {
        this.f2228I3 = false;
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().f(this);
        }
        this.f2229J3 = false;
    }

    /* renamed from: Q */
    public boolean mo9308Q() {
        return this.f2225F3 != null;
    }

    /* renamed from: R */
    public void mo9309R() {
        if (this.f2244a == 0) {
            nativeInit();
        }
        this.f2251p = true;
    }

    /* renamed from: S */
    public boolean mo9310S() {
        return this.f2229J3;
    }

    /* renamed from: T */
    public boolean mo9311T() {
        return this.f2264z3;
    }

    /* renamed from: U */
    public boolean mo9312U() {
        WindowAndroid o0;
        if (mo9302K() == null || (o0 = mo9302K().mo9782o0()) == null) {
            return true;
        }
        return !(WindowAndroid.m3684a((Context) o0.mo10017d().get()) instanceof ChromeActivity);
    }

    /* renamed from: V */
    public boolean mo9313V() {
        return !isNativePage() && mo9302K() == null;
    }

    /* renamed from: W */
    public boolean mo9314W() {
        return this.f2230K3;
    }

    /* renamed from: X */
    public boolean mo9315X() {
        return this.f2246c;
    }

    /* renamed from: Y */
    public boolean mo9316Y() {
        return this.f2251p;
    }

    /* renamed from: Z */
    public boolean mo9317Z() {
        return this.f2228I3 && !mo9365g0();
    }

    /* renamed from: a */
    public void mo9329a(WebContents webContents) {
        try {
            TraceEvent.m1472c("ChromeTab.initWebContents", (String) null);
            WebContents webContents2 = this.f2253q3;
            this.f2253q3 = webContents;
            ContentView a = ContentView.a(this.f2249k, webContents);
            a.setContentDescription(this.f2249k.getResources().getString(wx0.accessibility_content_view));
            this.f2254r3 = a;
            webContents.mo9762a("77.0.3865.116", (ViewAndroidDelegate) new jw2(this, a), (ViewEventSink.InternalAccessDelegate) a, mo9304M(), (WebContents.InternalsHolder) new WebContents.a());
            lb2 lb2 = this.f2252q;
            this.f2252q = null;
            mo9323a(lb2);
            if (webContents2 != null) {
                webContents2.mo9756a(0);
                WebContentsAccessibilityImpl.a(webContents2).a(false);
            }
            this.f2253q3.mo9756a(this.f2231L3);
            ContentUtils.nativeSetUserAgentOverride(this.f2253q3);
            this.f2254r3.addOnAttachStateChangeListener(this.f2238S3);
            mo9291A0();
            this.f2256t3 = this.f2237R3.a(this);
            nativeInitWebContents(this.f2244a, this.f2246c, mo9312U(), webContents, this.f2259w3, this.f2256t3, new Tv2(this.f2237R3.d(this), this));
            this.f2253q3.mo9754Y();
            Vv2.a(this);
            mo9384l0();
        } finally {
            TraceEvent.m1475z("ChromeTab.initWebContents");
        }
    }

    /* renamed from: a0 */
    public boolean mo9337a0() {
        return mo9353d0() && mo9290A() >= 100;
    }

    /* renamed from: b */
    public final void mo9339b(int i) {
        try {
            TraceEvent.m1472c("Tab.hide", (String) null);
            if (!mo9314W()) {
                this.f2230K3 = true;
                mo9291A0();
                if (mo9302K() != null) {
                    mo9302K().mo9745I();
                }
                mb2 mb2 = mb2.b;
                mb2.a.add(new WeakReference(this));
                if (mb2.a.size() > 3) {
                    mb2.a((Tab) ((WeakReference) mb2.a.remove(0)).get());
                }
                Iterator<Yv2> it = this.f2255s3.iterator();
                while (it.hasNext()) {
                    it.next().c(this, i);
                }
                Ns0.c().a(this);
                this.f2263y3 = null;
                Yx1.a();
                TraceEvent.m1475z("Tab.hide");
            }
        } finally {
            TraceEvent.m1475z("Tab.hide");
        }
    }

    /* renamed from: b0 */
    public boolean mo9344b0() {
        return NewTabPage.m2272b(getUrl());
    }

    /* renamed from: c */
    public boolean mo9348c() {
        return mo9302K() != null && mo9302K().mo9742F().n();
    }

    /* renamed from: c0 */
    public boolean mo9349c0() {
        return mo9302K() != null && !isNativePage() && !mo9365g0() && mo9294C() != 5 && PreviewsAndroidBridge.a().d(mo9302K());
    }

    /* renamed from: d */
    public void mo9351d(int i) {
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().a(this, i);
        }
    }

    /* renamed from: d0 */
    public boolean mo9353d0() {
        cQ2 G;
        if (this.f2252q != null) {
            return true;
        }
        WebContents K = mo9302K();
        if (K == null || (G = K.mo9743G()) == null || !G.a()) {
            return false;
        }
        return true;
    }

    /* renamed from: e */
    public void mo9356e(boolean z) {
        this.f2264z3 = z;
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().e(this, z);
        }
    }

    /* renamed from: e0 */
    public boolean mo9357e0() {
        return this.f2232M3;
    }

    /* renamed from: f */
    public final void mo9359f(int i) {
        if (this.f2231L3 != i) {
            this.f2231L3 = i;
            WebContents K = mo9302K();
            if (K != null) {
                K.mo9756a(this.f2231L3);
            }
        }
    }

    /* renamed from: f */
    public void mo9360f(boolean z) {
    }

    /* renamed from: f0 */
    public boolean mo9361f0() {
        return this.f2220A3;
    }

    /* renamed from: g */
    public void mo9364g(boolean z) {
        this.f2220A3 = z;
    }

    /* renamed from: g0 */
    public boolean mo9365g0() {
        return mo9302K() != null && mo9302K().mo9773e1();
    }

    @CalledByNative
    public int getId() {
        return this.f2245b;
    }

    @CalledByNative
    public String getTitle() {
        if (this.f2234O3 == null) {
            mo9293B0();
        }
        return this.f2234O3;
    }

    @CalledByNative
    public String getUrl() {
        String H = mo9302K() != null ? mo9302K().mo9744H() : BuildConfig.FLAVOR;
        if (mo9302K() != null || isNativePage() || !TextUtils.isEmpty(H)) {
            this.f2226G3 = H;
        }
        String str = this.f2226G3;
        return str != null ? str : BuildConfig.FLAVOR;
    }

    /* renamed from: h */
    public final void mo9370h(int i) {
        try {
            TraceEvent.m1472c("Tab.show", (String) null);
            if (mo9314W()) {
                this.f2230K3 = false;
                mo9291A0();
                CitrixManager.e.a.mo4437d(RN0.a);
                mo9380j0();
                if (mo9302K() != null) {
                    mo9302K().mo9740D();
                }
                lb2 v = mo9403v();
                if (v instanceof kb2) {
                    mo9336a(v.getUrl(), true);
                }
                mb2 mb2 = mb2.b;
                for (int i2 = 0; i2 < mb2.a.size(); i2++) {
                    if (((Tab) ((WeakReference) mb2.a.get(i2)).get()) == this) {
                        mb2.a.remove(i2);
                    }
                }
                Xv2.b(this);
                if (mo9290A() < 100 && !mo9365g0()) {
                    mo9345c(mo9290A());
                }
                Iterator<Yv2> it = this.f2255s3.iterator();
                while (it.hasNext()) {
                    it.next().f(this, i);
                }
                this.f2233N3 = System.currentTimeMillis();
                this.f2263y3 = Ns0.c().b(this);
                TraceEvent.m1475z("Tab.show");
            }
        } finally {
            TraceEvent.m1475z("Tab.show");
        }
    }

    /* renamed from: h0 */
    public boolean mo9372h0() {
        return this.f2221B3;
    }

    /* renamed from: i */
    public void mo9373i() {
        lb2 lb2 = this.f2252q;
        if (lb2 != null && !(lb2 instanceof kb2)) {
            kb2 kb2 = new kb2(lb2);
            lb2.destroy();
            this.f2252q = kb2;
            mo9291A0();
            mo9350d();
        }
    }

    /* renamed from: i0 */
    public boolean mo9375i0() {
        return this.f2242W3;
    }

    @CalledByNative
    public boolean isCurrentlyACustomTab() {
        ChromeActivity j = mo9379j();
        return j != null && j.mo8148p1();
    }

    @CalledByNative
    public boolean isNativePage() {
        return this.f2252q != null;
    }

    @CalledByNative
    public boolean isUserInteractable() {
        return this.f2239T3;
    }

    /* renamed from: j */
    public ChromeActivity mo9379j() {
        if (mo9304M() == null) {
            return null;
        }
        Activity a = WindowAndroid.m3684a((Context) mo9304M().mo10017d().get());
        if (a instanceof ChromeActivity) {
            return (ChromeActivity) a;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005b, code lost:
        if (mo9382k0() == false) goto L_0x005d;
     */
    /* renamed from: j0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo9380j0() {
        /*
            r6 = this;
            org.chromium.chrome.browser.ChromeActivity r0 = r6.mo9379j()
            if (r0 != 0) goto L_0x0011
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "Tab"
            java.lang.String r3 = "Tab couldn't be loaded because Context was null."
            VN0.a(r2, r3, r1)
            return r0
        L_0x0011:
            org.chromium.content_public.browser.LoadUrlParams r0 = r6.f2225F3
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0044
            org.chromium.chrome.browser.WarmupManager r0 = org.chromium.chrome.browser.WarmupManager.m1950e()
            boolean r3 = r6.mo9315X()
            boolean r4 = r6.mo9314W()
            boolean r5 = r6.isCurrentlyACustomTab()
            org.chromium.content_public.browser.WebContents r0 = r0.mo8300a((boolean) r3, (boolean) r4, (boolean) r5)
            if (r0 != 0) goto L_0x0039
            boolean r0 = r6.mo9315X()
            boolean r3 = r6.mo9314W()
            org.chromium.content_public.browser.WebContents r0 = org.chromium.chrome.browser.WebContentsFactory.b(r0, r3)
        L_0x0039:
            r6.mo9329a((org.chromium.content_public.browser.WebContents) r0)
            org.chromium.content_public.browser.LoadUrlParams r0 = r6.f2225F3
            r6.mo9338b((org.chromium.content_public.browser.LoadUrlParams) r0)
            r6.f2225F3 = r1
            return r2
        L_0x0044:
            java.lang.String r0 = "Tab.restoreIfNeeded"
            org.chromium.base.TraceEvent.m1472c(r0, r1)     // Catch:{ all -> 0x0087 }
            boolean r1 = r6.mo9313V()     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0057
            org.chromium.chrome.browser.tab.TabState$a r1 = r6.f2224E3     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0057
            r6.mo9410y0()     // Catch:{ all -> 0x0087 }
            goto L_0x0061
        L_0x0057:
            boolean r1 = r6.mo9382k0()     // Catch:{ all -> 0x0087 }
            if (r1 != 0) goto L_0x0061
        L_0x005d:
            org.chromium.base.TraceEvent.m1475z(r0)
            goto L_0x0086
        L_0x0061:
            org.chromium.content_public.browser.WebContents r1 = r6.f2253q3     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x006e
            org.chromium.content_public.browser.WebContents r1 = r6.f2253q3     // Catch:{ all -> 0x0087 }
            org.chromium.content_public.browser.NavigationController r1 = r1.mo9742F()     // Catch:{ all -> 0x0087 }
            r1.k()     // Catch:{ all -> 0x0087 }
        L_0x006e:
            r6.f2229J3 = r2     // Catch:{ all -> 0x0087 }
            org.chromium.base.ObserverList<Yv2> r1 = r6.f2255s3     // Catch:{ all -> 0x0087 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0087 }
        L_0x0076:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0087 }
            if (r3 == 0) goto L_0x005d
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0087 }
            Yv2 r3 = (Yv2) r3     // Catch:{ all -> 0x0087 }
            r3.d(r6)     // Catch:{ all -> 0x0087 }
            goto L_0x0076
        L_0x0086:
            return r2
        L_0x0087:
            r1 = move-exception
            org.chromium.base.TraceEvent.m1475z(r0)
            goto L_0x008d
        L_0x008c:
            throw r1
        L_0x008d:
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.Tab.mo9380j0():boolean");
    }

    /* renamed from: k */
    public Context mo9381k() {
        return this.f2249k.getApplicationContext();
    }

    /* renamed from: k0 */
    public boolean mo9382k0() {
        return mo9302K() != null && mo9302K().mo9742F().c();
    }

    /* renamed from: l */
    public long mo9383l() {
        if (pr2.e(getUrl())) {
            if (mo9313V()) {
                return -1;
            }
            return nativeGetBookmarkIdByUrl(this.f2244a, false, pr2.a(getUrl()));
        } else if (mo9313V()) {
            return -1;
        } else {
            return nativeGetBookmarkId(this.f2244a, false);
        }
    }

    /* renamed from: l0 */
    public void mo9384l0() {
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().g(this);
        }
        mo9412z0();
    }

    /* renamed from: m */
    public ViewGroup mo9385m() {
        return this.f2254r3;
    }

    /* renamed from: m0 */
    public void mo9386m0() {
        this.f2221B3 = true;
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().k(this);
        }
    }

    /* renamed from: n */
    public Context mo9387n() {
        if (mo9304M() == null) {
            return mo9297F();
        }
        Context context = (Context) mo9304M().mo10017d().get();
        return context == context.getApplicationContext() ? mo9297F() : context;
    }

    /* renamed from: n0 */
    public final void mo9388n0() {
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().n(this);
        }
    }

    /* renamed from: o */
    public Is0 mo9389o() {
        return this.f2263y3;
    }

    /* renamed from: o0 */
    public void mo9390o0() {
        mo9339b(1);
    }

    /* renamed from: p */
    public Uv2 mo9391p() {
        return this.f2237R3;
    }

    /* renamed from: p0 */
    public void mo9392p0() {
        if (mo9314W()) {
            mo9370h(3);
        } else {
            mo9380j0();
        }
    }

    /* renamed from: q */
    public TabState.a mo9393q() {
        return this.f2224E3;
    }

    /* renamed from: q0 */
    public void mo9394q0() {
        if (!(mo9379j() == null || mo9379j().mo8026G0() == null || mo9379j().mo8026G0().t() == null)) {
            mo9379j().mo8026G0().t().setTranslationX(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
        if (mo9379j().findViewById(ox0.control_container) != null) {
            mo9379j().findViewById(ox0.control_container).setTranslationX(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
        boolean z = this.f2228I3;
        this.f2228I3 = false;
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().c(this, z);
        }
        this.f2263y3 = Ns0.c().b(this);
        if (QN0.a.contains("FeedbackSessionManager.activation_key")) {
            FeedbackSessionManager.ActivationPoint valueOf = FeedbackSessionManager.ActivationPoint.valueOf(QN0.a.getInt("FeedbackSessionManager.activation_key", -1));
            if (!FeedbackSessionManager.m2265b()) {
                return;
            }
            if (valueOf == FeedbackSessionManager.ActivationPoint.WIDGET_SEARCH || valueOf == FeedbackSessionManager.ActivationPoint.VOICE_SEARCH) {
                FeedbackSessionManager.m2271g();
                Eo.a(QN0.a, "FeedbackSessionManager.activation_key");
                new OT1().b((ViewGroup) ((ChromeActivity) ChromeActivity.f1556M4.get()).findViewById(16908290));
            }
        }
    }

    /* renamed from: r */
    public int mo9395r() {
        View J = mo9301J();
        if (J != null) {
            return J.getHeight();
        }
        return 0;
    }

    /* renamed from: r0 */
    public void mo9396r0() {
        nativeSetActiveNavigationEntryTitleForUrl(this.f2244a, mo9403v().getUrl(), mo9403v().getTitle());
    }

    /* renamed from: s */
    public int mo9397s() {
        return this.f2222C3.intValue();
    }

    /* renamed from: s0 */
    public void mo9398s0() {
        if (Bd2.b(this)) {
            Bd2.d(this);
        } else if (mo9302K() != null) {
            mo9302K().mo9742F().b(true);
        }
    }

    /* renamed from: t */
    public Integer mo9399t() {
        return this.f2223D3;
    }

    /* renamed from: t0 */
    public void mo9400t0() {
        if (mo9302K() != null) {
            mo9302K().mo9742F().a(true);
        }
    }

    /* renamed from: u */
    public yg0 mo9401u() {
        return this.f2236Q3;
    }

    /* renamed from: u0 */
    public void mo9402u0() {
        View J = mo9301J();
        if (J != null) {
            J.requestFocus();
        }
    }

    /* renamed from: v */
    public lb2 mo9403v() {
        return this.f2252q;
    }

    /* renamed from: v0 */
    public void mo9404v0() {
        if (mo9302K() != null) {
            mo9302K().mo9742F().a();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tabCV", ss0.b(getId()));
        ss0.b("TabNeedReload", hashMap, true, 0, (String) null);
    }

    /* renamed from: w */
    public String mo9405w() {
        return DomDistillerUrlUtils.a(getUrl());
    }

    /* renamed from: w0 */
    public final boolean mo9406w0() {
        return so2.l().e() && !mo9315X();
    }

    /* renamed from: x */
    public int mo9407x() {
        return this.f2258v3;
    }

    /* renamed from: x0 */
    public void mo9408x0() {
        if (mo9317Z()) {
            ObserverList.RewindableIterator<Yv2> D = mo9295D();
            while (D.hasNext()) {
                D.next().b(this, getUrl());
            }
        }
        if (mo9302K() != null) {
            mo9302K().stop();
        }
    }

    /* renamed from: y */
    public LoadUrlParams mo9409y() {
        return this.f2225F3;
    }

    /* renamed from: y0 */
    public final void mo9410y0() {
        try {
            TraceEvent.m1472c("Tab.unfreezeContents", (String) null);
            TabState.a aVar = this.f2224E3;
            WebContents nativeRestoreContentsFromByteBuffer = TabState.nativeRestoreContentsFromByteBuffer(aVar.a, aVar.b, mo9314W());
            boolean z = false;
            if (nativeRestoreContentsFromByteBuffer == null) {
                nativeRestoreContentsFromByteBuffer = WebContentsFactory.b(mo9315X(), mo9314W());
                iw2.i(this, 4);
                z = true;
            }
            CompositorViewHolder G0 = mo9379j().mo8026G0();
            nativeRestoreContentsFromByteBuffer.mo9766b(G0.getWidth(), G0.getHeight());
            this.f2224E3 = null;
            mo9329a(nativeRestoreContentsFromByteBuffer);
            if (z) {
                mo9338b(new LoadUrlParams(TextUtils.isEmpty(this.f2226G3) ? SE2.a : this.f2226G3, 5));
            }
        } finally {
            TraceEvent.m1475z("Tab.unfreezeContents");
        }
    }

    /* renamed from: z */
    public Profile mo9411z() {
        long j = this.f2244a;
        if (j == 0) {
            return null;
        }
        return nativeGetProfileAndroid(j);
    }

    /* renamed from: z0 */
    public void mo9412z0() {
        View J = mo9301J();
        boolean z = false;
        if (J != null) {
            ChromeActivity j = mo9379j();
            int i = j != null && j.mo8154r1() ? 4 : 1;
            if (J.getImportantForAccessibility() != i) {
                J.setImportantForAccessibility(i);
                J.sendAccessibilityEvent(2048);
            }
        }
        WebContents K = mo9302K();
        WebContentsAccessibilityImpl a = K != null ? WebContentsAccessibilityImpl.a(K) : null;
        if (a != null) {
            ChromeActivity j2 = mo9379j();
            if ((j2 != null && j2.mo8154r1()) || Kv2.p(this)) {
                z = true;
            }
            a.a(z);
        }
    }

    /* renamed from: g */
    public void mo9363g(int i) {
        if (i != this.f2261x3) {
            this.f2261x3 = i;
            this.f2221B3 = true;
            Iterator<Yv2> it = this.f2255s3.iterator();
            while (it.hasNext()) {
                it.next().g(this, i);
            }
        }
    }

    /* renamed from: c */
    public static final /* synthetic */ void m2998c(LoadUrlParams loadUrlParams) {
        HashMap hashMap = new HashMap();
        String partnerCode = PartnerCodeManager.getInstance().getPartnerCode(RN0.a);
        if (TextUtils.isEmpty(partnerCode)) {
            partnerCode = BuildConfig.FLAVOR;
        }
        hashMap.put("partnerCode", partnerCode);
        hashMap.put("isBingSearch", ft0.c(loadUrlParams.mo9735q()) ? "yes" : "no");
        ss0.b("tab_partner_code", hashMap, true, 0, (String) null);
    }

    /* renamed from: e */
    public void mo9355e(int i) {
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().e(this, i);
        }
    }

    /* renamed from: d */
    public final void mo9350d() {
        NewTabPage newTabPage = this.f2260x;
        if (newTabPage != null) {
            if (!newTabPage.f1795y) {
                newTabPage.destroy();
            }
            this.f2260x = null;
        }
    }

    /* renamed from: e */
    public void mo9354e() {
        if (!mo9313V()) {
            nativeCreateHistoricalTab(this.f2244a);
            return;
        }
        TabState.a aVar = this.f2224E3;
        if (aVar != null) {
            TabState.nativeCreateHistoricalTab(aVar.a, aVar.b);
        }
    }

    /* renamed from: f */
    public void mo9358f() {
        this.f2251p = false;
        mo9293B0();
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().c(this);
        }
        this.f2255s3.clear();
        sO0 so0 = this.f2241V3;
        HashMap hashMap = so0.b;
        so0.b = null;
        for (rO0 destroy : hashMap.values()) {
            destroy.destroy();
        }
        lb2 lb2 = this.f2252q;
        this.f2252q = null;
        mo9323a(lb2);
        mo9333a(true);
        Xv2.a(this);
        nativeDestroy(this.f2244a);
        this.f2227H3 = true;
        Yx1.a();
    }

    /* renamed from: g */
    public void mo9362g() {
        WebContents K = mo9302K();
        if (K != null) {
            K.mo9770b((WindowAndroid) null);
        }
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().b(this, false);
        }
        Yx1.a();
    }

    /* renamed from: i */
    public void mo9374i(boolean z) {
        if (z && so2.l().e() && !mo9315X()) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", getUrl());
            hashMap.put("CV", ss0.b(getId()));
            hashMap.put("tab_mode", "normal");
            ss0.a("navigate_redirect_url_sensitive", hashMap);
        }
        mo9293B0();
        NewTabPage newTabPage = this.f2252q;
        if (newTabPage != null) {
            if (!(newTabPage instanceof kb2)) {
                newTabPage.getView().removeOnAttachStateChangeListener(this.f2238S3);
            }
            this.f2252q = null;
            mo9384l0();
            if (!(newTabPage instanceof NewTabPage) || !this.f2262y) {
                mo9323a((lb2) newTabPage);
                mo9350d();
                return;
            }
            NewTabPage newTabPage2 = this.f2260x;
            if (newTabPage2 != newTabPage || newTabPage2.f1795y) {
                mo9350d();
                this.f2260x = newTabPage;
                NewTabPageView newTabPageView = this.f2260x.f1787d;
                if (newTabPageView != null) {
                    newTabPageView.setSnapshotChanged(true);
                }
            }
        } else if (!z) {
            mo9350d();
        }
    }

    /* renamed from: d */
    public void mo9352d(boolean z) {
        if (z) {
            this.f2228I3 = true;
        }
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().f(this, z);
        }
        Ns0.c().a(this);
        this.f2263y3 = null;
    }

    /* renamed from: c */
    public void mo9347c(boolean z) {
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().d(this, z);
        }
    }

    /* renamed from: c */
    public void mo9346c(String str) {
        if (!TextUtils.isEmpty(str) || !str.equals(this.f2226G3)) {
            Ns0.c().b(this.f2226G3, str);
        }
        if (!TextUtils.equals(this.f2234O3, str)) {
            this.f2221B3 = true;
            this.f2234O3 = str;
            mo9388n0();
        }
    }

    /* renamed from: b */
    public int mo9338b(LoadUrlParams loadUrlParams) {
        String str;
        int nativeLoadUrl;
        boolean z;
        Matcher matcher;
        String a;
        LoadUrlParams loadUrlParams2 = loadUrlParams;
        try {
            TraceEvent.m1472c("Tab.loadUrl", (String) null);
            if (!this.f2235P3) {
                this.f2235P3 = mo9336a(loadUrlParams.mo9735q(), false);
            }
            if (!"chrome://java-crash/".equals(loadUrlParams.mo9735q())) {
                String q = loadUrlParams.mo9735q();
                if (!TextUtils.isEmpty(q) && (a = ft0.a(q, "microsoft-edge:", true)) != null) {
                    if (!a.startsWith("https://")) {
                        if (!a.startsWith("http://")) {
                            a = a.startsWith("www.") ? UrlFormatter.m3350a(a) : TemplateUrlServiceFactory.m2927a().mo9621b(a);
                        }
                    }
                    loadUrlParams2.mo9719d(a);
                }
                Us0.d.execute(new Lv2(loadUrlParams2));
                String q2 = loadUrlParams.mo9735q();
                if (pr2.f(q2)) {
                    q2 = DomDistillerUrlUtils.a("chrome-distiller", pr2.d(q2));
                }
                loadUrlParams2.mo9719d(q2);
                if (!ft0.d(loadUrlParams.mo9735q()) && (loadUrlParams.mo9734p() & 33554432) == 33554432) {
                    ss0.a("tab_window_to_url", "CV", ss0.b(getId()), "hResult", "0", "openInNewTab", String.valueOf(this.f2247d), "relatedCV", us0.b(getId()));
                    this.f2247d = false;
                    String a2 = ss0.a(getId());
                    if (!a2.equals(BuildConfig.FLAVOR) && loadUrlParams.mo9735q() != null && !mo9315X() && (matcher = f2219Y3.matcher(loadUrlParams.mo9735q())) != null && matcher.matches()) {
                        Uri.Builder buildUpon = Uri.parse(loadUrlParams.mo9735q()).buildUpon();
                        if (!buildUpon.build().getQueryParameterNames().contains("refcv")) {
                            buildUpon.appendQueryParameter("refcv", a2);
                            loadUrlParams2.mo9719d(buildUpon.toString());
                        }
                    }
                }
                if (this.f2244a != 0) {
                    if (!this.f2242W3) {
                        String q3 = loadUrlParams.mo9735q();
                        if (Mx1.a(q3)) {
                            Uri parse = Uri.parse(q3);
                            if (parse != null) {
                                z = SearchAction.isVoiceFormCode(parse.getQueryParameter("form"));
                                this.f2242W3 = z;
                            }
                        }
                        z = false;
                        this.f2242W3 = z;
                    }
                    str = "Tab.loadUrl";
                    try {
                        nativeLoadUrl = nativeLoadUrl(this.f2244a, loadUrlParams.mo9735q(), loadUrlParams.mo9725g(), loadUrlParams.mo9737s(), loadUrlParams.mo9730l(), loadUrlParams.mo9734p(), loadUrlParams.mo9731m() != null ? loadUrlParams.mo9731m().a : null, loadUrlParams.mo9731m() != null ? loadUrlParams.mo9731m().b : 0, loadUrlParams.mo9728j(), loadUrlParams.mo9733o(), loadUrlParams.mo9724f(), loadUrlParams.mo9732n(), loadUrlParams.mo9726h(), loadUrlParams.mo9727i());
                    } catch (Throwable th) {
                        th = th;
                        TraceEvent.m1475z(str);
                        throw th;
                    }
                    try {
                        Iterator<Yv2> it = this.f2255s3.iterator();
                        while (it.hasNext()) {
                            it.next().a(this, loadUrlParams2, nativeLoadUrl);
                        }
                        this.f2262y = false;
                        Iterator<Yv2> it2 = this.f2255s3.iterator();
                        while (it2.hasNext()) {
                            it2.next().a();
                        }
                        TraceEvent.m1475z(str);
                        return nativeLoadUrl;
                    } catch (Throwable th2) {
                        th = th2;
                        TraceEvent.m1475z(str);
                        throw th;
                    }
                } else {
                    str = "Tab.loadUrl";
                    throw new RuntimeException("Tab.loadUrl called when no native side exists");
                }
            } else {
                Object obj = "Tab.loadUrl";
                throw new RuntimeException("Intentional Java Crash");
            }
        } catch (Throwable th3) {
            th = th3;
            str = "Tab.loadUrl";
            TraceEvent.m1475z(str);
            throw th;
        }
    }

    /* renamed from: c */
    public void mo9345c(int i) {
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().h(this, i);
        }
    }

    /* renamed from: h */
    public void mo9371h(boolean z) {
        this.f2221B3 = z;
    }

    /* renamed from: h */
    public void mo9369h() {
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().a(this);
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* renamed from: a */
    public void mo9330a(org.chromium.content_public.browser.WebContents r5, Uv2 r6, boolean r7, org.chromium.chrome.browser.tab.TabState r8, boolean r9) {
        /*
            r4 = this;
            java.lang.String r0 = "Tab.initialize"
            r1 = -1
            r3 = 0
            org.chromium.base.TraceEvent.m1472c(r0, r3)     // Catch:{ all -> 0x00c8 }
            if (r8 == 0) goto L_0x000d
            r4.mo9327a((org.chromium.chrome.browser.tab.TabState) r8)     // Catch:{ all -> 0x00c8 }
        L_0x000d:
            r4.mo9309R()     // Catch:{ all -> 0x00c8 }
            r4.f2237R3 = r6     // Catch:{ all -> 0x00c8 }
            org.chromium.chrome.browser.rlz.RevenueStats r6 = org.chromium.chrome.browser.rlz.RevenueStats.f2199a     // Catch:{ all -> 0x00c8 }
            if (r6 != 0) goto L_0x0020
            org.chromium.chrome.browser.AppHooks r6 = org.chromium.chrome.browser.AppHooks.get()     // Catch:{ all -> 0x00c8 }
            org.chromium.chrome.browser.rlz.RevenueStats r6 = r6.mo7998w()     // Catch:{ all -> 0x00c8 }
            org.chromium.chrome.browser.rlz.RevenueStats.f2199a = r6     // Catch:{ all -> 0x00c8 }
        L_0x0020:
            org.chromium.chrome.browser.rlz.RevenueStats r6 = org.chromium.chrome.browser.rlz.RevenueStats.f2199a     // Catch:{ all -> 0x00c8 }
            r6.mo9211a()     // Catch:{ all -> 0x00c8 }
            org.chromium.chrome.browser.tab.TabState$a r6 = r4.mo9393q()     // Catch:{ all -> 0x00c8 }
            if (r6 != 0) goto L_0x009d
            org.chromium.content_public.browser.LoadUrlParams r6 = r4.mo9409y()     // Catch:{ all -> 0x00c8 }
            if (r6 == 0) goto L_0x0032
            goto L_0x009d
        L_0x0032:
            if (r5 != 0) goto L_0x0036
            r6 = 1
            goto L_0x0037
        L_0x0036:
            r6 = 0
        L_0x0037:
            if (r6 == 0) goto L_0x0053
            org.chromium.chrome.browser.WarmupManager r5 = org.chromium.chrome.browser.WarmupManager.m1950e()     // Catch:{ all -> 0x00c8 }
            boolean r9 = r4.mo9315X()     // Catch:{ all -> 0x00c8 }
            boolean r3 = r4.isCurrentlyACustomTab()     // Catch:{ all -> 0x00c8 }
            org.chromium.content_public.browser.WebContents r5 = r5.mo8300a((boolean) r9, (boolean) r7, (boolean) r3)     // Catch:{ all -> 0x00c8 }
            if (r5 != 0) goto L_0x0053
            boolean r5 = r4.mo9315X()     // Catch:{ all -> 0x00c8 }
            org.chromium.content_public.browser.WebContents r5 = org.chromium.chrome.browser.WebContentsFactory.b(r5, r7)     // Catch:{ all -> 0x00c8 }
        L_0x0053:
            r4.mo9329a((org.chromium.content_public.browser.WebContents) r5)     // Catch:{ all -> 0x00c8 }
            if (r6 != 0) goto L_0x0065
            boolean r6 = r5.mo9749M()     // Catch:{ all -> 0x00c8 }
            if (r6 == 0) goto L_0x0065
            java.lang.String r5 = r5.mo9744H()     // Catch:{ all -> 0x00c8 }
            r4.mo9341b((java.lang.String) r5)     // Catch:{ all -> 0x00c8 }
        L_0x0065:
            yg0 r5 = r4.f2236Q3     // Catch:{ all -> 0x00c8 }
            if (r5 != 0) goto L_0x0077
            org.chromium.content_public.browser.WebContents r5 = r4.mo9302K()     // Catch:{ all -> 0x00c8 }
            if (r5 != 0) goto L_0x0070
            goto L_0x0077
        L_0x0070:
            yg0 r5 = new yg0     // Catch:{ all -> 0x00c8 }
            r5.<init>(r4)     // Catch:{ all -> 0x00c8 }
            r4.f2236Q3 = r5     // Catch:{ all -> 0x00c8 }
        L_0x0077:
            long r5 = r4.f2233N3
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x0083
            long r5 = java.lang.System.currentTimeMillis()
            r4.f2233N3 = r5
        L_0x0083:
            org.chromium.base.ObserverList<Yv2> r5 = r4.f2255s3
            java.util.Iterator r5 = r5.iterator()
        L_0x0089:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0099
            java.lang.Object r6 = r5.next()
            Yv2 r6 = (Yv2) r6
            r6.a(r4, r8)
            goto L_0x0089
        L_0x0099:
            org.chromium.base.TraceEvent.m1475z(r0)
            return
        L_0x009d:
            if (r9 == 0) goto L_0x00a2
            r4.mo9410y0()     // Catch:{ all -> 0x00c8 }
        L_0x00a2:
            long r5 = r4.f2233N3
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x00ae
            long r5 = java.lang.System.currentTimeMillis()
            r4.f2233N3 = r5
        L_0x00ae:
            org.chromium.base.ObserverList<Yv2> r5 = r4.f2255s3
            java.util.Iterator r5 = r5.iterator()
        L_0x00b4:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00c4
            java.lang.Object r6 = r5.next()
            Yv2 r6 = (Yv2) r6
            r6.a(r4, r8)
            goto L_0x00b4
        L_0x00c4:
            org.chromium.base.TraceEvent.m1475z(r0)
            return
        L_0x00c8:
            r5 = move-exception
            long r6 = r4.f2233N3
            int r9 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r9 != 0) goto L_0x00d5
            long r6 = java.lang.System.currentTimeMillis()
            r4.f2233N3 = r6
        L_0x00d5:
            org.chromium.base.ObserverList<Yv2> r6 = r4.f2255s3
            java.util.Iterator r6 = r6.iterator()
        L_0x00db:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00eb
            java.lang.Object r7 = r6.next()
            Yv2 r7 = (Yv2) r7
            r7.a(r4, r8)
            goto L_0x00db
        L_0x00eb:
            org.chromium.base.TraceEvent.m1475z(r0)
            goto L_0x00f0
        L_0x00ef:
            throw r5
        L_0x00f0:
            goto L_0x00ef
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.Tab.mo9330a(org.chromium.content_public.browser.WebContents, Uv2, boolean, org.chromium.chrome.browser.tab.TabState, boolean):void");
    }

    /* renamed from: a */
    public void mo9327a(TabState tabState) {
        this.f2224E3 = tabState.f2265a;
        this.f2233N3 = tabState.f2268d;
        this.f2226G3 = tabState.mo9415c();
        this.f2234O3 = tabState.mo9413a();
        this.f2223D3 = tabState.f2271g;
        int i = tabState.f2267c;
        if (i == -1) {
            i = this.f2245b;
        }
        this.f2261x3 = i;
    }

    /* renamed from: a */
    public void mo9319a(Yv2 yv2) {
        this.f2255s3.mo7868a(yv2);
    }

    /* renamed from: a */
    public void mo9328a(LoadUrlParams loadUrlParams) {
        mo9338b(loadUrlParams);
        this.f2262y = true;
    }

    /* renamed from: a */
    public boolean mo9335a(Intent intent, Bundle bundle, Runnable runnable) {
        ChromeActivity j = mo9379j();
        if (j == null) {
            return false;
        }
        if (intent == null) {
            intent = new Intent();
        }
        if (intent.getComponent() == null) {
            intent.setClass(this.f2249k, ChromeLauncherActivity.class);
        }
        intent.setAction("android.intent.action.VIEW");
        if (TextUtils.isEmpty(intent.getDataString())) {
            intent.setData(Uri.parse(getUrl()));
        }
        if (mo9315X()) {
            intent.putExtra("com.android.browser.application_id", RN0.a.getPackageName());
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
        }
        IntentHandler.m1920e(intent);
        if (Og0.d() || ChromeFeatureList.m1784a("TabReparenting")) {
            intent.putExtra("com.android.chrome.tab_id", this.f2245b);
            Bw2.a(this.f2245b, new Gx2(this, intent, runnable));
            mo9362g();
        }
        j.startActivity(intent, bundle);
        return true;
    }

    /* renamed from: b */
    public void mo9340b(Yv2 yv2) {
        this.f2255s3.mo7869b(yv2);
    }

    /* renamed from: b */
    public boolean mo9343b() {
        return mo9302K() != null && mo9302K().mo9742F().d();
    }

    /* renamed from: b */
    public void mo9341b(String str) {
        this.f2248e = Calendar.getInstance().getTime();
        mo9293B0();
        if (this.f2232M3) {
            mo9342b(true);
        }
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().c(this, str);
        }
        if (mo9406w0()) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", getUrl());
            hashMap.put("CV", ss0.b(getId()));
            hashMap.put("tab_mode", "normal");
            ss0.a("before_navigate_url_sensitive", hashMap);
        }
        this.f2257u3 = Pp0.a();
        if (ss0.c() == 0) {
            ss0.b("unknown_navigate_start", "CV", us0.c(getId()));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("CV", us0.b(getId()));
        ss0.a("navigate_start", hashMap2);
    }

    /* renamed from: a */
    public void mo9325a(ChromeActivity chromeActivity, Uv2 uv2, Runnable runnable) {
        chromeActivity.mo8026G0().I();
        mo9324a(chromeActivity, uv2);
        this.f2221B3 = true;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* renamed from: a */
    public void mo9324a(ChromeActivity chromeActivity, Uv2 uv2) {
        mo9332a((WindowAndroid) chromeActivity.mo8772S());
        this.f2237R3 = uv2;
        this.f2256t3 = this.f2237R3.a(this);
        mo9336a(getUrl(), true);
        nativeAttachDetachedTab(this.f2244a);
        if (mo9302K() != null) {
            nativeUpdateDelegates(this.f2244a, this.f2256t3, new Tv2(this.f2237R3.d(this), this));
        }
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().b(this, true);
        }
    }

    /* renamed from: b */
    public void mo9342b(boolean z) {
        this.f2232M3 = !z;
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().a(this, z);
        }
    }

    /* renamed from: a */
    public void mo9332a(WindowAndroid windowAndroid) {
        this.f2250n = windowAndroid;
        WebContents K = mo9302K();
        if (K != null) {
            K.mo9770b(this.f2250n);
            K.mo9754Y();
        }
    }

    /* JADX WARNING: type inference failed for: r0v36, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r0v37, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* renamed from: a */
    public void mo9321a(String str) {
        boolean z;
        String str2 = str;
        this.f2221B3 = true;
        mo9293B0();
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().b(this, str2);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("CV", ss0.b(getId()));
        ss0.a("navigate_finish", hashMap);
        if (mo9406w0()) {
            boolean z2 = this.f2257u3 != Pp0.a();
            Date time = Calendar.getInstance().getTime();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("url", getUrl());
            hashMap2.put("title", getTitle());
            hashMap2.put("pageLoadStartTime", String.valueOf(this.f2248e.getTime()));
            hashMap2.put("pageLoadFinishTime", String.valueOf(time.getTime()));
            hashMap2.put("pageLoadTime", String.valueOf(time.getTime() - this.f2248e.getTime()));
            hashMap2.put("navigationType", BuildConfig.FLAVOR);
            hashMap2.put("referrer", BuildConfig.FLAVOR);
            hashMap2.put("CV", ss0.b(getId()));
            hashMap2.put("isInterrupted", String.valueOf(z2));
            hashMap2.put("tab_mode", "normal");
            ss0.a("full_browser_tracking_sensitive", hashMap2);
        }
        this.f2229J3 = false;
        BingSearchWidgetManager c = BingSearchWidgetManager.c();
        if (BingSearchWidgetManager.d() && c.a(str2)) {
            ChromeActivity chromeActivity = (ChromeActivity) ChromeActivity.f1556M4.get();
            if (chromeActivity == null) {
                z = false;
            } else {
                z = Qx1.e().a((ViewGroup) chromeActivity.findViewById(16908290));
            }
            if (z) {
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferences.Editor edit = QN0.a.edit();
                edit.putLong("promote_widget_last_show_time", currentTimeMillis);
                edit.apply();
                "setPromoteWidgetLastShowTime-->" + currentTimeMillis;
                SharedPreferences sharedPreferences = QN0.a;
                int i = sharedPreferences.getInt("promote_widget_show_counter", 0) + 1;
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                edit2.putInt("promote_widget_show_counter", i);
                edit2.apply();
                "increasePromoteSearchWidgetCounter-->" + i;
                HashMap hashMap3 = new HashMap();
                hashMap3.put("Action", "Show");
                BingClientManager.getInstance().getTelemetryMgr().addEvent("EVENT_EDGE_PROMOTE_SEARCH_WIDGET", hashMap3);
                "sendPromoteSearchWidgetMetrics-->" + hashMap3.toString();
            }
        }
        if (mo9302K() != null) {
            Al0.a().a(this);
        }
        yg0 yg0 = this.f2236Q3;
        if (yg0 != null) {
            yg0.b(getUrl());
        }
        if (mo9344b0()) {
            Fu0.a(mo9379j(), "edgeAndroidNtpIsShown");
        } else {
            Fu0.a(mo9379j(), "edgeAndroidWebNavigation");
        }
        if (df0.a()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RN0.a.getResources().openRawResource(vx0.app_proxy)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append(10);
                } catch (IOException e) {
                    VN0.a("Tab", e.getMessage(), new Object[0]);
                }
            }
            mo9302K().mo9761a(sb.toString(), (JavaScriptCallback) null);
            HashMap b = bf0.e().b((String) null);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(RN0.a);
            int i2 = defaultSharedPreferences.getInt("LAST_SAVED_MAPPING_SIZE", 0);
            String string = defaultSharedPreferences.getString("LAST_SAVED_MAPPING_STRING", (String) null);
            if (i2 != b.size()) {
                JSONArray jSONArray = new JSONArray();
                for (Map.Entry entry : b.entrySet()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", entry.getKey().toString());
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("url", entry.getValue().toString());
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("internal", jSONObject);
                        jSONObject3.put("external", jSONObject2);
                        jSONArray.put(jSONObject3);
                    } catch (Exception e2) {
                        VN0.a("Tab", e2.getMessage(), new Object[0]);
                    }
                }
                string = jSONArray.toString();
                defaultSharedPreferences.edit().putString("LAST_SAVED_MAPPING_STRING", string).apply();
                defaultSharedPreferences.edit().putInt("LAST_SAVED_MAPPING_SIZE", b.size()).apply();
            }
            String replace = Base64.encodeToString(string.getBytes(), 0).replace(AbstractAccountCredentialCache.NEW_LINE, BuildConfig.FLAVOR);
            mo9302K().mo9761a("window.__emmxReverseProxy__.proxyTableBase64 =\"" + replace + "\";window.__emmxReverseProxy__.proxyTableDirty = true;", (JavaScriptCallback) null);
        }
        if (!mo9315X() && !mo9314W()) {
            Yx1.a(mo9379j(), getUrl());
        }
    }

    /* renamed from: a */
    public void mo9318a(int i) {
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().d(this, i);
        }
        this.f2229J3 = false;
        ss0.a("navigate_fail", "errorCode", String.valueOf(i), "CV", ss0.b(getId()));
    }

    /* renamed from: a */
    public boolean mo9336a(String str, boolean z) {
        lb2 lb2;
        if (mo9312U()) {
            return false;
        }
        if (z) {
            lb2 = null;
        } else {
            lb2 = mo9403v();
        }
        if (lb2 == null && this.f2262y) {
            lb2 = this.f2260x;
        }
        lb2 a = NativePageFactory.a(str, lb2, this, mo9379j());
        if (a == lb2 && lb2 == this.f2260x) {
            this.f2260x = null;
        }
        if (a == null) {
            return false;
        }
        lb2 lb22 = this.f2252q;
        if (lb22 != a) {
            if (lb22 != null && !(lb22 instanceof kb2)) {
                lb22.getView().removeOnAttachStateChangeListener(this.f2238S3);
            }
            this.f2252q = a;
            lb2 lb23 = this.f2252q;
            if (lb23 != null && !(lb23 instanceof kb2)) {
                lb23.getView().addOnAttachStateChangeListener(this.f2238S3);
            }
            mo9396r0();
            mo9384l0();
            mo9323a(lb22);
        }
        mo9388n0();
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().a(this, (Bitmap) null);
        }
        return true;
    }

    /* renamed from: a */
    public void mo9322a(String str, Integer num) {
        boolean z = false;
        this.f2235P3 = false;
        if (!mo9336a(str, num != null && (num.intValue() & 255) == 8)) {
            if (!(num == null || (num.intValue() & -1073741824) == 0)) {
                z = true;
            }
            mo9374i(z);
        }
        if (this.f2236Q3 == null && mo9302K() != null) {
            this.f2236Q3 = new yg0(this);
        }
        yg0 yg0 = this.f2236Q3;
        if (yg0 != null) {
            yg0.a(str);
        }
    }

    /* renamed from: a */
    public final void mo9323a(lb2 lb2) {
        if (lb2 != null) {
            lb2.destroy();
        }
    }

    /* renamed from: a */
    public final void mo9333a(boolean z) {
        if (this.f2253q3 != null) {
            this.f2254r3.removeOnAttachStateChangeListener(this.f2238S3);
            this.f2254r3 = null;
            mo9291A0();
            WebContents webContents = this.f2253q3;
            this.f2253q3 = null;
            this.f2256t3 = null;
            if (z) {
                nativeDestroyWebContents(this.f2244a);
                return;
            }
            nativeReleaseWebContents(this.f2244a);
            webContents.mo9752T1();
        }
    }

    /* renamed from: a */
    public void mo9331a(WebContents webContents, boolean z, boolean z2) {
        int i;
        int i2;
        ViewGroup viewGroup = this.f2254r3;
        if (viewGroup == null || this.f2253q3 == null) {
            i2 = 0;
            i = 0;
        } else {
            i2 = viewGroup.getWidth();
            i = this.f2254r3.getHeight();
            this.f2253q3.mo9745I();
        }
        Rect rect = new Rect();
        if (i2 == 0 && i == 0) {
            rect = ExternalPrerenderHandler.m2910a(mo9381k(), false);
            i2 = rect.right - rect.left;
            i = rect.bottom - rect.top;
        }
        mo9333a(false);
        lb2 lb2 = this.f2252q;
        this.f2252q = null;
        webContents.mo9766b(i2, i);
        if (!rect.isEmpty()) {
            nativeOnPhysicalBackingSizeChanged(this.f2244a, webContents, rect.right, rect.bottom);
        }
        webContents.mo9740D();
        mo9329a(webContents);
        mo9323a(lb2);
        String url = getUrl();
        if (z) {
            mo9341b(url);
            if (z2) {
                mo9321a(url);
            }
        }
        Iterator<Yv2> it = this.f2255s3.iterator();
        while (it.hasNext()) {
            it.next().a(this, z, z2);
        }
    }

    /* renamed from: a */
    public void mo9326a(FullscreenOptions fullscreenOptions) {
        ObserverList.RewindableIterator<Yv2> D = mo9295D();
        while (D.hasNext()) {
            D.next().a(this, fullscreenOptions);
        }
    }

    /* renamed from: a */
    public boolean mo9334a() {
        return nativeAreRendererInputEventsIgnored(this.f2244a);
    }

    /* renamed from: a */
    public void mo9320a(Theme theme) {
        if (this.f2227H3) {
        }
    }
}
