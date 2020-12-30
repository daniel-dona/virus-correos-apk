package org.chromium.chrome.browser;

import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.offlinepages.CCTRequestStatus;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.policy.PolicyAuditor;
import org.chromium.chrome.browser.preferences.LocationSettings;
import org.chromium.chrome.browser.rlz.RevenueStats;
import org.chromium.policy.CombinedPolicyProvider;

/* compiled from: PG */
public abstract class AppHooks {

    /* renamed from: a */
    public static ts1 f1551a;

    /* renamed from: org.chromium.chrome.browser.AppHooks$a */
    /* compiled from: PG */
    public class C0379a implements jX1 {
        public C0379a(AppHooks appHooks) {
        }

        /* renamed from: a */
        public void mo8002a(hX1 hx1) {
        }
    }

    /* renamed from: org.chromium.chrome.browser.AppHooks$b */
    /* compiled from: PG */
    public class C0380b extends LocationSettings {
        public C0380b(AppHooks appHooks) {
        }
    }

    /* renamed from: org.chromium.chrome.browser.AppHooks$c */
    /* compiled from: PG */
    public class C0381c extends PolicyAuditor {
        public C0381c(AppHooks appHooks) {
        }
    }

    /* renamed from: org.chromium.chrome.browser.AppHooks$d */
    /* compiled from: PG */
    public class C0382d implements lX1 {
        public C0382d(AppHooks appHooks) {
        }

        /* renamed from: a */
        public Collection mo8003a() {
            return new ArrayList();
        }

        /* renamed from: b */
        public Collection mo8004b() {
            return new ArrayList();
        }
    }

    @CalledByNative
    public static AppHooks get() {
        if (f1551a == null) {
            f1551a = new ts1();
        }
        return f1551a;
    }

    /* renamed from: A */
    public void mo7960A() {
    }

    /* renamed from: B */
    public List<String> mo7961B() {
        return Collections.emptyList();
    }

    /* renamed from: C */
    public List<String> mo7962C() {
        return Collections.emptyList();
    }

    /* renamed from: D */
    public PolicyAuditor mo7963D() {
        return new C0381c(this);
    }

    /* renamed from: E */
    public void mo7964E() {
    }

    /* renamed from: F */
    public boolean mo7965F() {
        return false;
    }

    /* renamed from: G */
    public void mo7966G() {
    }

    /* renamed from: H */
    public void mo7967H() {
    }

    /* renamed from: I */
    public void mo7968I() {
    }

    /* renamed from: J */
    public void mo7969J() {
    }

    /* renamed from: K */
    public void mo7970K() {
    }

    /* renamed from: a */
    public int mo7971a(int i) {
        try {
            if (Vc0.b(RN0.a.getPackageManager(), "com.google.android.gms", 0).versionCode >= i) {
                return 0;
            }
            return 2;
        } catch (PackageManager.NameNotFoundException unused) {
            return 1;
        }
    }

    /* renamed from: a */
    public void mo7974a(ChromeActivity chromeActivity) {
    }

    /* renamed from: b */
    public void mo7976b() {
    }

    /* renamed from: c */
    public PY1 mo7977c() {
        return new PY1();
    }

    /* renamed from: d */
    public void mo7978d() {
    }

    /* renamed from: e */
    public CustomTabsConnection mo7979e() {
        return new CustomTabsConnection();
    }

    /* renamed from: f */
    public nD2 mo7980f() {
        return new nD2();
    }

    /* renamed from: g */
    public void mo7981g() {
    }

    @CalledByNative
    public Callback<CCTRequestStatus> getOfflinePagesCCTRequestDoneCallback() {
        return null;
    }

    /* renamed from: h */
    public LV1 mo7983h() {
        return new LV1();
    }

    /* renamed from: i */
    public aH2 mo7984i() {
        return new aH2();
    }

    /* renamed from: j */
    public jX1 mo7985j() {
        return new C0379a(this);
    }

    /* renamed from: k */
    public zG2 mo7986k() {
        return new zG2();
    }

    /* renamed from: l */
    public HX1 mo7987l() {
        return new HX1();
    }

    /* renamed from: m */
    public HY1 mo7988m() {
        return new HY1();
    }

    /* renamed from: n */
    public OY1 mo7989n() {
        return new OY1();
    }

    /* renamed from: o */
    public void mo7990o() {
    }

    /* renamed from: p */
    public c32 mo7991p() {
        return new c32();
    }

    /* renamed from: q */
    public LocaleManager mo7992q() {
        return new LocaleManager();
    }

    /* renamed from: r */
    public LocationSettings mo7993r() {
        return new C0380b(this);
    }

    /* renamed from: s */
    public gb2 mo7994s() {
        if (Og0.d()) {
            return new Jg0();
        }
        return new gb2();
    }

    /* renamed from: t */
    public Runnable mo7995t() {
        return ss1.a;
    }

    /* renamed from: u */
    public void mo7996u() {
    }

    /* renamed from: v */
    public V22 mo7997v() {
        return new V22();
    }

    /* renamed from: w */
    public RevenueStats mo7998w() {
        return new RevenueStats();
    }

    /* renamed from: x */
    public Au2 mo7999x() {
        return new Au2();
    }

    /* renamed from: y */
    public lX1 mo8000y() {
        return new C0382d(this);
    }

    /* renamed from: z */
    public PartnerBrowserCustomizations.Provider mo8001z() {
        return new PartnerBrowserCustomizations.C0426b();
    }

    /* renamed from: a */
    public void mo7973a(Br2 br2) {
        PostTask.m1566a(iQ2.a, new rs1(br2), 0);
    }

    /* renamed from: a */
    public uL2 mo7972a() {
        return new XL2();
    }

    /* renamed from: a */
    public void mo7975a(CombinedPolicyProvider combinedPolicyProvider) {
        combinedPolicyProvider.mo9937a((UD3) new TD3(RN0.a));
    }
}
