package org.chromium.chrome.browser.microsoft_signin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.citrix.mvpn.api.MvpnIntuneConstants;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.authentication.OAuthTokenProvider;
import hy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.dual_identity.DualIdentityUtils;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.sync.AndroidSyncSettings;

/* compiled from: PG */
public class MicrosoftSigninManager {

    /* renamed from: a */
    public final Map<AuthenticationMode, I82> f2086a = CollectionUtil.m1378a(AuthenticationMode.MSA, new M92(), AuthenticationMode.AAD, new s82());

    /* renamed from: b */
    public final Map<AuthenticationMode, Map<TokenScopeType, String>> f2087b;

    /* renamed from: c */
    public long f2088c;

    /* renamed from: d */
    public final ObserverList<SignInStateObserver> f2089d;

    /* renamed from: e */
    public final Object f2090e;

    /* renamed from: f */
    public final ObserverList<Oo0> f2091f;

    /* renamed from: g */
    public final Object f2092g;

    /* renamed from: h */
    public AuthenticationMode f2093h;

    /* renamed from: i */
    public final Object f2094i;

    /* renamed from: j */
    public AtomicBoolean f2095j;

    /* compiled from: PG */
    public enum FinishSigninTrigger {
        ON_SIGNIN,
        ON_STARTUP
    }

    /* compiled from: PG */
    public interface SignInStateObserver {
        void onSignedIn(AuthenticationMode authenticationMode, String str);

        void onSignedOut(AuthenticationMode authenticationMode);
    }

    /* compiled from: PG */
    public enum TokenScopeType {
        BING,
        FAMILY,
        GRAPH,
        LOCATION_SERVICE,
        MSN_PRODUCTION,
        MSN_TEST,
        ONEDRIVE,
        MANAGE,
        TELEMETRY,
        TIMELINE,
        WNS,
        RT_CHECK,
        REWARDS,
        AFS_SYNC,
        MicroVPN,
        ANAHEIM_USERKEY,
        FCFD
    }

    /* renamed from: org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager$a */
    /* compiled from: PG */
    public class C0422a implements u82 {

        /* renamed from: a */
        public final /* synthetic */ AuthenticationMode f2096a;

        /* renamed from: b */
        public final /* synthetic */ u82 f2097b;

        /* renamed from: c */
        public final /* synthetic */ String f2098c;

        /* renamed from: d */
        public final /* synthetic */ Activity f2099d;

        public C0422a(AuthenticationMode authenticationMode, u82 u82, String str, Activity activity) {
            this.f2096a = authenticationMode;
            this.f2097b = u82;
            this.f2098c = str;
            this.f2099d = activity;
        }

        /* renamed from: a */
        public void mo8934a() {
            MicrosoftSigninManager microsoftSigninManager = MicrosoftSigninManager.this;
            if (this.f2096a == microsoftSigninManager.mo8908g()) {
                if (microsoftSigninManager.mo8911h(AuthenticationMode.AAD)) {
                    microsoftSigninManager.mo8916k(AuthenticationMode.AAD);
                } else {
                    microsoftSigninManager.mo8916k(AuthenticationMode.MSA);
                }
            }
            u82 u82 = this.f2097b;
            if (u82 != null) {
                u82.a();
            }
            DualIdentityManager.m2200a(this.f2096a, (DualIdentityManager.IGetProfileCallback) new x92(this));
            Activity activity = this.f2099d;
            if (activity != null) {
                ThreadUtils.m1461b(v92.a(CK3.a(this.f2099d, activity.getResources().getString(wx0.family_sign_out_successfully), 0)));
            }
            MicrosoftSigninManager.this.mo8915j(this.f2096a);
        }

        /* renamed from: b */
        public void mo8935b() {
            Activity activity = this.f2099d;
            if (activity != null) {
                ThreadUtils.m1461b(w92.a(CK3.a(this.f2099d, activity.getResources().getString(wx0.family_sign_out_fail), 0)));
            }
            u82 u82 = this.f2097b;
            if (u82 != null) {
                u82.b();
            }
        }
    }

    /* renamed from: org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager$b */
    /* compiled from: PG */
    public class C0423b implements t82 {

        /* renamed from: a */
        public final /* synthetic */ AuthenticationMode f2101a;

        /* renamed from: b */
        public final /* synthetic */ t82 f2102b;

        public C0423b(AuthenticationMode authenticationMode, t82 t82) {
            this.f2101a = authenticationMode;
            this.f2102b = t82;
        }

        /* renamed from: a */
        public void mo8936a(String str) {
            MicrosoftSigninManager.this.mo8916k(this.f2101a);
            t82 t82 = this.f2102b;
            if (t82 != null) {
                t82.a(str);
            }
            MicrosoftSigninManager.this.mo8884a(this.f2101a, str);
            MicrosoftSigninManager microsoftSigninManager = MicrosoftSigninManager.this;
            AuthenticationMode authenticationMode = this.f2101a;
            FinishSigninTrigger finishSigninTrigger = FinishSigninTrigger.ON_SIGNIN;
            microsoftSigninManager.mo8896b(authenticationMode);
            Hu0.a(RN0.a, C0424c.f2104a.mo8912i());
        }
    }

    /* renamed from: org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager$c */
    /* compiled from: PG */
    public static class C0424c {

        /* renamed from: a */
        public static MicrosoftSigninManager f2104a = new MicrosoftSigninManager((C0422a) null);
    }

    /* renamed from: org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager$d */
    /* compiled from: PG */
    public class C0425d {

        /* renamed from: a */
        public String f2105a;

        /* renamed from: b */
        public String f2106b;

        /* renamed from: c */
        public Bitmap f2107c;

        /* renamed from: d */
        public boolean f2108d;

        /* renamed from: e */
        public boolean f2109e;

        public C0425d(MicrosoftSigninManager microsoftSigninManager, String str, String str2, Bitmap bitmap, boolean z, boolean z2) {
            this.f2105a = str;
            this.f2106b = str2;
            this.f2107c = bitmap;
            this.f2108d = z;
            this.f2109e = z2;
        }
    }

    public /* synthetic */ MicrosoftSigninManager(C0422a aVar) {
        hy.a a = hy.a();
        AuthenticationMode authenticationMode = AuthenticationMode.MSA;
        hy.a a2 = hy.a();
        a2.a(TokenScopeType.BING, "service::bing.com::MBI_SSL");
        a2.a(TokenScopeType.FAMILY, "service::settings.family.microsoft.com::MBI_SSL");
        a2.a(TokenScopeType.GRAPH, BuildConfig.FLAVOR);
        a2.a(TokenScopeType.MANAGE, BuildConfig.FLAVOR);
        a2.a(TokenScopeType.LOCATION_SERVICE, BuildConfig.FLAVOR);
        a2.a(TokenScopeType.MSN_PRODUCTION, "service::www.msn.com::MBI_SSL");
        a2.a(TokenScopeType.MSN_TEST, "service::int1.msn.com::MBI_SSL");
        a2.a(TokenScopeType.ONEDRIVE, "service::ssl.live.com::MBI_SSL");
        a2.a(TokenScopeType.TELEMETRY, "service::vortex.data.microsoft.com::MBI_SSL");
        a2.a(TokenScopeType.TIMELINE, "https://activity.windows.com/UserActivity.ReadWrite.CreatedByApp");
        a2.a(TokenScopeType.WNS, "wns.connect");
        a2.a(TokenScopeType.RT_CHECK, "service::www.msn.com::MBI_SSL");
        a2.a(TokenScopeType.REWARDS, "service::Prod.rewardsplatform.microsoft.com::MBI_SSL");
        a2.a(TokenScopeType.AFS_SYNC, "service::activity.windows.com::MBI_SSL");
        a2.a(TokenScopeType.ANAHEIM_USERKEY, "service::http://Passport.NET/purpose::PURPOSE_GETKEYDATA_ANAHEIM");
        a2.a(TokenScopeType.FCFD, "service::family.microsoft.com::MBI_SSL");
        a.a(authenticationMode, a2.a());
        AuthenticationMode authenticationMode2 = AuthenticationMode.AAD;
        hy.a a3 = hy.a();
        a3.a(TokenScopeType.BING, "9ea1ad79-fdb6-4f9a-8bc3-2b70f96e34c7");
        a3.a(TokenScopeType.FAMILY, BuildConfig.FLAVOR);
        a3.a(TokenScopeType.GRAPH, "https://graph.microsoft.com/");
        a3.a(TokenScopeType.MSN_PRODUCTION, BuildConfig.FLAVOR);
        a3.a(TokenScopeType.MANAGE, "https://manage.microsoft.com/");
        a3.a(TokenScopeType.LOCATION_SERVICE, "https://graph.windows.net/");
        a3.a(TokenScopeType.MSN_TEST, BuildConfig.FLAVOR);
        a3.a(TokenScopeType.ONEDRIVE, BuildConfig.FLAVOR);
        a3.a(TokenScopeType.TELEMETRY, BuildConfig.FLAVOR);
        a3.a(TokenScopeType.TIMELINE, "https://activity.microsoft.com");
        a3.a(TokenScopeType.WNS, "https://wns.windows.com/");
        a3.a(TokenScopeType.RT_CHECK, "https://activity.microsoft.com");
        a3.a(TokenScopeType.MicroVPN, MvpnIntuneConstants.NSG_MULTI_TENANT_AAD_RESOURCE);
        a3.a(TokenScopeType.AFS_SYNC, "https://activity.microsoft.com");
        a3.a(TokenScopeType.ANAHEIM_USERKEY, "https://aadrm.com");
        a3.a(TokenScopeType.FCFD, BuildConfig.FLAVOR);
        a.a(authenticationMode2, a3.a());
        this.f2087b = a.a();
        this.f2089d = new ObserverList<>();
        this.f2090e = new Object();
        this.f2091f = new ObserverList<>();
        this.f2092g = new Object();
        this.f2094i = new Object();
        this.f2095j = new AtomicBoolean(false);
        SharedPreferences sharedPreferences = QN0.a;
        AuthenticationMode authenticationMode3 = AuthenticationMode.MSA;
        String string = sharedPreferences.getString("microsoft_signin_manager_active_mode", "MSA");
        AuthenticationMode authenticationMode4 = AuthenticationMode.AAD;
        if (!"AAD".equals(string) || !mo8930w()) {
            this.f2093h = AuthenticationMode.MSA;
        } else {
            this.f2093h = AuthenticationMode.AAD;
        }
        if (!QN0.a.getBoolean("microsoft_signin_manager_has_run_dual_identity_migration", false)) {
            if (mo8911h(AuthenticationMode.AAD)) {
                mo8916k(AuthenticationMode.AAD);
            } else if (mo8911h(AuthenticationMode.MSA)) {
                mo8916k(AuthenticationMode.MSA);
            }
        }
        Eo.b(QN0.a, "microsoft_signin_manager_has_run_dual_identity_migration", true);
    }

    /* renamed from: G */
    public static MicrosoftSigninManager m2574G() {
        return C0424c.f2104a;
    }

    private native long nativeInit();

    private native void nativeLogInSignedInUser(long j, Profile profile);

    private native void nativeOnSignInCompleted(long j, Profile profile, String str);

    /* access modifiers changed from: private */
    public native void nativeSignOut(long j, Profile profile);

    /* renamed from: A */
    public boolean mo8866A() {
        return mo8908g() == AuthenticationMode.AAD;
    }

    /* renamed from: B */
    public boolean mo8867B() {
        if (ve0.b()) {
            return false;
        }
        Sp2 a = Tp2.a(mo8906f());
        if (a == null) {
            return true;
        }
        return a.a.getBoolean("msa_accounts_enabled", true);
    }

    /* renamed from: C */
    public boolean mo8868C() {
        return mo8908g() == AuthenticationMode.MSA && mo8931x();
    }

    /* renamed from: D */
    public boolean mo8869D() {
        return this.f2086a.get(mo8908g()).l();
    }

    /* renamed from: E */
    public void mo8870E() {
        if (!this.f2095j.get()) {
            this.f2088c = nativeInit();
            this.f2095j.set(true);
        }
        if (C0424c.f2104a.mo8932y() || C0424c.f2104a.mo8930w()) {
            AuthenticationMode g = mo8908g();
            FinishSigninTrigger finishSigninTrigger = FinishSigninTrigger.ON_STARTUP;
            mo8896b(g);
            if ((mo8868C() && q92.l() && q92.n()) || (mo8866A() && q92.b())) {
                Zr0.a();
                ProfileSyncService.m2940M().mo9268m().mo9289a();
                ProfileSyncService.m2940M().mo9233G();
            }
            PrefServiceBridge.m2758o0().mo9131l0();
        }
    }

    /* renamed from: F */
    public void mo8871F() {
        if (!mo8911h(AuthenticationMode.MSA)) {
            AuthenticationMode authenticationMode = AuthenticationMode.MSA;
            this.f2086a.get(authenticationMode).a(RN0.a, authenticationMode);
        }
        if (!mo8911h(AuthenticationMode.AAD)) {
            AuthenticationMode authenticationMode2 = AuthenticationMode.AAD;
            this.f2086a.get(authenticationMode2).a(RN0.a, authenticationMode2);
        }
        if (mo8931x()) {
            mo8889a(TokenScopeType.RT_CHECK, (OAuthTokenProvider.AccessTokenCallback<String>) null);
        }
    }

    /* renamed from: a */
    public void mo8880a(Activity activity) {
        Context context = RN0.a;
        boolean z = false;
        boolean z2 = false;
        for (Account userData : AccountManager.get(context).getAccountsByType(context.getPackageName())) {
            String userData2 = AccountManager.get(context).getUserData(userData, "AuthenticationMode");
            if (TextUtils.isEmpty(userData2) || userData2.equals(AuthenticationMode.MSA.toString())) {
                z = true;
            } else if (userData2.equals(AuthenticationMode.AAD.toString())) {
                z2 = true;
            }
        }
        if (!z) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "MSA");
            hashMap.put("source", "DeleteSystemAccount");
            ss0.c("SignOut", hashMap);
            mo8882a(AuthenticationMode.MSA, activity, true, (u82) null);
        }
        if (!z2) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "AAD");
            hashMap2.put("source", "DeleteSystemAccount");
            ss0.c("SignOut", hashMap2);
            mo8882a(AuthenticationMode.AAD, activity, true, (u82) null);
        }
    }

    /* renamed from: b */
    public void mo8898b(boolean z) {
        Eo.b(QN0.a, "fre_signin_aad_managed", z);
    }

    /* renamed from: c */
    public void mo8901c(boolean z) {
        Eo.b(QN0.a, "is_first_run", z);
    }

    /* renamed from: d */
    public String mo8902d() {
        return this.f2086a.get(AuthenticationMode.AAD).a.h();
    }

    /* renamed from: e */
    public String mo8904e() {
        return this.f2086a.get(AuthenticationMode.AAD).a.m();
    }

    /* renamed from: f */
    public String mo8906f() {
        return mo8907f(AuthenticationMode.AAD);
    }

    /* renamed from: g */
    public final String mo8909g(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).j();
    }

    /* renamed from: h */
    public String mo8910h() {
        return this.f2086a.get(mo8908g()).a.d();
    }

    /* renamed from: i */
    public String mo8912i() {
        return mo8900c(mo8908g());
    }

    /* renamed from: j */
    public String mo8914j() {
        return mo8903d(mo8908g());
    }

    /* renamed from: k */
    public boolean mo8917k() {
        return QN0.a.getBoolean("fre_signin_aad_managed", false);
    }

    /* renamed from: l */
    public boolean mo8919l() {
        return QN0.a.getBoolean("is_first_run", false);
    }

    /* renamed from: m */
    public String mo8920m() {
        return mo8900c(AuthenticationMode.MSA);
    }

    /* renamed from: n */
    public boolean mo8921n() {
        return this.f2086a.get(AuthenticationMode.MSA).c();
    }

    /* renamed from: o */
    public String mo8922o() {
        return this.f2086a.get(AuthenticationMode.MSA).d();
    }

    /* renamed from: p */
    public String mo8923p() {
        return this.f2086a.get(AuthenticationMode.MSA).e();
    }

    /* renamed from: q */
    public String mo8924q() {
        return mo8907f(AuthenticationMode.MSA);
    }

    /* renamed from: r */
    public String mo8925r() {
        return this.f2086a.get(mo8908g()).a.h();
    }

    /* renamed from: s */
    public String mo8926s() {
        return this.f2086a.get(mo8908g()).g();
    }

    /* renamed from: t */
    public String mo8927t() {
        return this.f2086a.get(mo8908g()).a.m();
    }

    /* renamed from: u */
    public String mo8928u() {
        return mo8907f(mo8908g());
    }

    /* renamed from: v */
    public String mo8929v() {
        return mo8909g(mo8908g());
    }

    /* renamed from: w */
    public boolean mo8930w() {
        return mo8911h(AuthenticationMode.AAD);
    }

    /* renamed from: x */
    public boolean mo8931x() {
        return mo8911h(mo8908g());
    }

    /* renamed from: y */
    public boolean mo8932y() {
        return mo8911h(AuthenticationMode.MSA);
    }

    /* renamed from: z */
    public boolean mo8933z() {
        return mo8911h(AuthenticationMode.AAD) && mo8911h(AuthenticationMode.MSA);
    }

    /* renamed from: f */
    public String mo8907f(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).i();
    }

    /* renamed from: g */
    public final AuthenticationMode mo8908g() {
        AuthenticationMode authenticationMode;
        synchronized (this.f2094i) {
            authenticationMode = this.f2093h;
        }
        return authenticationMode;
    }

    /* renamed from: i */
    public final void mo8913i(AuthenticationMode authenticationMode) {
        ObserverList observerList = new ObserverList();
        synchronized (this.f2092g) {
            Iterator<Oo0> it = this.f2091f.iterator();
            while (it.hasNext()) {
                observerList.mo7868a(it.next());
            }
            Iterator it2 = observerList.iterator();
            while (it2.hasNext()) {
                ((Oo0) it2.next()).a(authenticationMode);
            }
        }
    }

    /* renamed from: j */
    public final void mo8915j(AuthenticationMode authenticationMode) {
        ObserverList observerList = new ObserverList();
        synchronized (this.f2090e) {
            Iterator<SignInStateObserver> it = this.f2089d.iterator();
            while (it.hasNext()) {
                observerList.mo7868a(it.next());
            }
        }
        Iterator it2 = observerList.iterator();
        while (it2.hasNext()) {
            ((SignInStateObserver) it2.next()).onSignedOut(authenticationMode);
        }
    }

    /* renamed from: b */
    public void mo8896b(AuthenticationMode authenticationMode) {
        if (C0424c.f2104a.mo8930w() && q92.b()) {
            js0.a();
        }
        SharedPreferences sharedPreferences = QN0.a;
        StringBuilder a = Eo.a("microsoft_signin_manager_finish_signin_prefix_");
        a.append(authenticationMode.name());
        if (sharedPreferences.getBoolean(a.toString(), false)) {
            A82.a(authenticationMode).a();
            "EdgeSync: Already finish signed in for " + authenticationMode.name();
        } else if (this.f2095j.get()) {
            DualIdentityManager.m2200a(authenticationMode, (DualIdentityManager.IGetProfileCallback) new y92(this, authenticationMode));
        }
    }

    /* renamed from: c */
    public String mo8899c() {
        return mo8900c(AuthenticationMode.AAD);
    }

    /* renamed from: k */
    public final void mo8916k(AuthenticationMode authenticationMode) {
        SharedPreferences.Editor edit = QN0.a.edit();
        edit.putString("microsoft_signin_manager_active_mode", authenticationMode.name());
        edit.apply();
        synchronized (this.f2094i) {
            this.f2093h = authenticationMode;
        }
        if (Jo0.h()) {
            RN0.a.sendBroadcast(new Intent("com.microsoft.emmx.action.SWITCH_TO_MSA"));
        }
        if (AuthenticationMode.MSA.equals(authenticationMode)) {
            if (!Ep0.a.b()) {
                Us0.c.execute(u92.a);
            }
        } else if (Ep0.a.b()) {
            Ep0.a.d();
        }
    }

    /* renamed from: l */
    public void mo8918l(AuthenticationMode authenticationMode) {
        mo8916k(authenticationMode);
    }

    /* renamed from: c */
    public final String mo8900c(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).a();
    }

    /* renamed from: d */
    public String mo8903d(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).b();
    }

    /* renamed from: e */
    public String mo8905e(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).h();
    }

    /* renamed from: h */
    public final boolean mo8911h(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).k();
    }

    /* renamed from: b */
    public String mo8894b(TokenScopeType tokenScopeType) {
        String str = (String) this.f2087b.get(mo8908g()).get(tokenScopeType);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f2086a.get(mo8908g()).a(str);
    }

    /* renamed from: b */
    public long mo8891b(String str) {
        return this.f2086a.get(mo8908g()).c(str);
    }

    /* renamed from: b */
    public void mo8897b(SignInStateObserver signInStateObserver) {
        synchronized (this.f2090e) {
            this.f2089d.mo7869b(signInStateObserver);
        }
    }

    /* renamed from: a */
    public void mo8882a(AuthenticationMode authenticationMode, Activity activity, boolean z, u82 u82) {
        this.f2086a.get(authenticationMode).a(activity, z, new C0422a(authenticationMode, u82, C0424c.f2104a.mo8912i(), activity));
    }

    /* renamed from: b */
    public void mo8895b(Oo0 oo0) {
        synchronized (this.f2091f) {
            this.f2091f.mo7869b(oo0);
        }
    }

    @SuppressLint({"ApplySharedPref"})
    /* renamed from: a */
    public void mo8878a() {
        ThreadUtils.m1460b();
        this.f2086a.get(AuthenticationMode.AAD).a(false);
        SharedPreferences.Editor edit = QN0.a.edit();
        AuthenticationMode authenticationMode = AuthenticationMode.MSA;
        edit.putString("microsoft_signin_manager_active_mode", "MSA");
        if (!edit.commit()) {
            DualIdentityUtils.m2248c("MicrosoftSigninManager", "Failed to change active mode to MSA when perform dirty signout");
            return;
        }
        synchronized (this.f2094i) {
            this.f2093h = AuthenticationMode.MSA;
        }
    }

    /* renamed from: b */
    public Bitmap mo8892b(Resources resources) {
        return this.f2086a.get(mo8908g()).a(resources);
    }

    /* renamed from: b */
    public String mo8893b() {
        return this.f2086a.get(mo8908g()).a.a();
    }

    /* renamed from: a */
    public void mo8890a(boolean z) {
        ThreadUtils.m1460b();
        this.f2086a.get(AuthenticationMode.MSA).a(z);
    }

    /* renamed from: a */
    public List<C0425d> mo8877a(Resources resources) {
        ArrayList arrayList = new ArrayList();
        if (mo8911h(AuthenticationMode.AAD)) {
            arrayList.add(new C0425d(this, mo8909g(AuthenticationMode.AAD), mo8900c(AuthenticationMode.AAD), this.f2086a.get(AuthenticationMode.AAD).a(resources), mo8866A(), false));
        }
        if (mo8911h(AuthenticationMode.MSA)) {
            arrayList.add(new C0425d(this, mo8909g(AuthenticationMode.MSA), mo8900c(AuthenticationMode.MSA), this.f2086a.get(AuthenticationMode.MSA).a(resources), mo8868C(), true));
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo8889a(TokenScopeType tokenScopeType, OAuthTokenProvider.AccessTokenCallback<String> accessTokenCallback) {
        mo8887a((String) this.f2087b.get(mo8908g()).get(tokenScopeType), tokenScopeType == TokenScopeType.RT_CHECK, accessTokenCallback);
    }

    /* renamed from: a */
    public void mo8887a(String str, boolean z, OAuthTokenProvider.AccessTokenCallback<String> accessTokenCallback) {
        if (!TextUtils.isEmpty(str)) {
            this.f2086a.get(mo8908g()).a(str, z, accessTokenCallback);
        }
    }

    /* renamed from: a */
    public int mo8872a(String str) {
        return this.f2086a.get(mo8908g()).b(str);
    }

    /* renamed from: a */
    public String mo8875a(TokenScopeType tokenScopeType) {
        return mo8876a(tokenScopeType, false);
    }

    /* renamed from: a */
    public String mo8876a(TokenScopeType tokenScopeType, boolean z) {
        ThreadUtils.m1460b();
        String str = (String) this.f2087b.get(mo8908g()).get(tokenScopeType);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (e20.b) {
            String str2 = "getAccessToken: type = [" + tokenScopeType + "], forceRefresh = [" + z + "]";
            jo0 jo0 = e20.a.a;
            if (jo0 != null) {
                jo0.a("MicrosoftSigninManager", str2);
            }
        }
        AuthenticationMode g = mo8908g();
        ThreadUtils.m1460b();
        return this.f2086a.get(g).a(str, z);
    }

    /* renamed from: a */
    public void mo8886a(i20 i20, boolean z, t82 t82) {
        mo8883a(i20.c(), i20, z, t82);
    }

    /* renamed from: a */
    public void mo8881a(Activity activity, boolean z, u82 u82) {
        mo8882a(mo8908g(), activity, z, u82);
    }

    /* renamed from: a */
    public void mo8888a(SignInStateObserver signInStateObserver) {
        synchronized (this.f2090e) {
            this.f2089d.mo7868a(signInStateObserver);
        }
    }

    /* renamed from: a */
    public void mo8879a(Oo0 oo0) {
        synchronized (this.f2092g) {
            this.f2091f.mo7868a(oo0);
        }
    }

    /* renamed from: a */
    public String mo8873a(AuthenticationMode authenticationMode) {
        return this.f2086a.get(authenticationMode).i();
    }

    /* renamed from: a */
    public String mo8874a(AuthenticationMode authenticationMode, TokenScopeType tokenScopeType) {
        ThreadUtils.m1460b();
        String str = (String) this.f2087b.get(authenticationMode).get(tokenScopeType);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ThreadUtils.m1460b();
        return this.f2086a.get(authenticationMode).a(str, false);
    }

    /* renamed from: a */
    public final void mo8883a(AuthenticationMode authenticationMode, i20 i20, boolean z, t82 t82) {
        mo8913i(authenticationMode);
        this.f2086a.get(authenticationMode).a(i20, z, new C0423b(authenticationMode, t82));
    }

    /* renamed from: a */
    public final void mo8885a(AuthenticationMode authenticationMode, Profile profile) {
        MicrosoftOAuth2TokenServiceHelper.m2573a(profile).validateAccounts(authenticationMode == AuthenticationMode.AAD ? "Default-AAD" : "Default");
        nativeOnSignInCompleted(this.f2088c, profile, this.f2086a.get(authenticationMode).a());
        A82.a(authenticationMode).a();
        if (q92.l()) {
            AndroidSyncSettings.m3337f().mo9634a();
        }
        nativeLogInSignedInUser(this.f2088c, profile);
        Fu0.b(RN0.a, "show_sign_in_callout");
        SharedPreferences.Editor edit = QN0.a.edit();
        StringBuilder a = Eo.a("microsoft_signin_manager_finish_signin_prefix_");
        a.append(authenticationMode.name());
        edit.putBoolean(a.toString(), true).apply();
    }

    /* renamed from: a */
    public final void mo8884a(AuthenticationMode authenticationMode, String str) {
        ObserverList observerList = new ObserverList();
        synchronized (this.f2090e) {
            Iterator<SignInStateObserver> it = this.f2089d.iterator();
            while (it.hasNext()) {
                observerList.mo7868a(it.next());
            }
        }
        Iterator it2 = observerList.iterator();
        while (it2.hasNext()) {
            ((SignInStateObserver) it2.next()).onSignedIn(authenticationMode, str);
        }
    }
}
