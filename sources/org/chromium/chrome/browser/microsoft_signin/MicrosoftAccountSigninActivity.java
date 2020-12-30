package org.chromium.chrome.browser.microsoft_signin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.theme.Theme;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.init.ChromeBrowserInitializer;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.microsoft_signin.fsm.FSM;
import org.chromium.chrome.browser.microsoft_signin.ui.SplashScreenFirstRunFragment;
import org.chromium.chrome.browser.suggestions.MostVisitedSitesManager;

/* compiled from: PG */
public class MicrosoftAccountSigninActivity extends AsyncInitializationActivity implements Pg0 {

    /* renamed from: C3 */
    public boolean f2082C3 = false;

    /* renamed from: D3 */
    public Kg0 f2083D3;

    /* renamed from: a */
    public static void m2559a(Context context, int i) {
        Intent intent = new Intent(context, MicrosoftAccountSigninActivity.class);
        intent.putExtra("MicrosoftAccountSigninActivity.SigninAccessPoint", m2561h(i));
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (Og0.d() && (context instanceof CustomTabActivity)) {
            intent.addFlags(268435456);
            Intent intent2 = new Intent(context, ChromeTabbedActivity.class);
            intent2.addFlags(402653184);
            intent.putExtra("Extra.FreChromeLaunchIntent", Bb0.a(context, 0, intent2, 1207959552));
            intent.putExtra("isFirstRun", !EX1.a());
        }
        context.startActivity(intent);
    }

    /* renamed from: b */
    public static boolean m2560b(Context context, int i) {
        if (MicrosoftSigninManager.C0424c.f2104a.mo8933z()) {
            return false;
        }
        m2559a(context, i);
        return true;
    }

    /* renamed from: h */
    public static int m2561h(int i) {
        if (!(i == 31 || i == 30)) {
            boolean y = MicrosoftSigninManager.C0424c.f2104a.mo8932y();
            boolean w = MicrosoftSigninManager.C0424c.f2104a.mo8930w();
            if (y && !w) {
                return 31;
            }
            if (!w || y) {
                return i;
            }
            return 30;
        }
        return i;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: J */
    public void mo8032J() {
        try {
            ChromeBrowserInitializer.m2488f().mo8798a(false);
        } catch (ProcessInitException e) {
            VN0.a("AccountSigninActivity", "Failed to start browser process.", new Object[]{e});
            System.exit(-1);
        }
        if (Og0.d()) {
            this.f2083D3 = new Kg0(this);
            MostVisitedSitesManager.m2930c().mo9215a((AppCompatActivity) this);
        }
    }

    /* renamed from: d */
    public boolean mo8394d(Intent intent) {
        return false;
    }

    /* renamed from: f */
    public final /* synthetic */ void mo8858f(int i) {
        wq0.b();
        String string = QN0.a.getString("com.microsoft.emmx.RERERRER_CAMPAIGN", BuildConfig.FLAVOR);
        if ("rewardsv2AMC".equals(string) || "v2test".equals(string) || Tl0.f()) {
            ia2 a = ia2.a();
            a.a(new ka2(this));
            ba2.a().a = a;
            aa2.a.a(new X92(FSM.Event.EV_REWARDS_SIGNIN, 32, (Object) null));
            Eo.b(QN0.a, "rewards_fre_showed", true);
            return;
        }
        mo8859g(i);
    }

    /* renamed from: g */
    public final void mo8859g(int i) {
        ba2.a().a = R92.a(this);
        FSM.Event event = FSM.Event.EV_BASIC_SIGNIN;
        if (Og0.d() && i == -1) {
            event = FSM.Event.EV_DUO_FRE_ENTRY;
        }
        aa2.a.a(new X92(event, Integer.valueOf(i), (Object) null));
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, android.support.v4.app.FragmentActivity, org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: g0 */
    public void mo8105g0() {
        if (mo8771Q() == null && MicrosoftSigninManager.C0424c.f2104a.mo8933z()) {
            rb0 rb0 = new rb0(this);
            rb0.setTitle(wx0.signin_error_title);
            rb0.setMessage(wx0.signin_second_account_error);
            rb0.setPositiveButton(wx0.ok, new a(this));
            rb0.setOnKeyListener(new b(this));
            AlertDialog create = rb0.create();
            if (Og0.d()) {
                Ng0.a(create.getWindow());
            }
            create.show();
        }
        setContentView(rx0.fre_container_activity);
        int a = du0.a(getResources(), jx0.bottom_system_nav_color);
        ON0.a(getWindow(), a);
        View rootView = getWindow().getDecorView().getRootView();
        boolean z = !JE2.d(a);
        if (Build.VERSION.SDK_INT >= 27) {
            int systemUiVisibility = rootView.getSystemUiVisibility();
            rootView.setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & -17);
        }
        int intExtra = getIntent().getIntExtra("MicrosoftAccountSigninActivity.SigninAccessPoint", -1);
        if (getIntent().getBooleanExtra("isFirstRun", false)) {
            MicrosoftSigninManager.C0424c.f2104a.mo8901c(true);
            SplashScreenFirstRunFragment splashScreenFirstRunFragment = new SplashScreenFirstRunFragment();
            R4 a2 = getSupportFragmentManager().a();
            a2.a(ox0.fragment_container, splashScreenFirstRunFragment, (String) null);
            a2.b();
            ThreadUtils.m1458a(new Z82(this, intExtra), 3000);
            return;
        }
        MicrosoftSigninManager.C0424c.f2104a.mo8901c(false);
        mo8859g(intExtra);
    }

    public void onBackPressed() {
        if (ve0.b()) {
            this.f2082C3 = true;
            ApplicationLifetime.terminate(false);
        }
        DualIdentityManager.m2203a((String) null);
        DualIdentityManager.m2198a((Intent) null);
        MicrosoftAccountSigninActivity.super.onBackPressed();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onConfigurationChanged(Configuration configuration) {
        Kg0 kg0;
        super.onConfigurationChanged(configuration);
        if (Og0.d() && (kg0 = this.f2083D3) != null) {
            kg0.b(this);
        }
    }

    public void onMAMDestroy() {
        ba2.a().a = null;
        if (!this.f2082C3) {
            ve0.a(RN0.a);
        }
        super.onMAMDestroy();
        this.f2082C3 = false;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity, android.app.Activity] */
    public void onRestart() {
        MicrosoftAccountSigninActivity.super.onRestart();
        if (ve0.b() && !MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            mo8859g(36);
        }
    }

    /* renamed from: r */
    public Kg0 mo8862r() {
        return this.f2083D3;
    }

    /* renamed from: t */
    public void mo8165t() {
        super.mo8165t();
    }

    /* renamed from: u */
    public boolean mo8863u() {
        return true;
    }

    /* renamed from: b */
    public int mo4510b(Theme theme) {
        return du0.a(getResources(), jx0.signin_body_background);
    }
}
