package org.chromium.chrome.browser.preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.intune.mam.client.MAMIdentitySwitchResult;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import com.microsoft.theme.Theme;
import com.microsoft.theme.entity.ThemeCompatActivity;
import java.util.HashMap;
import java.util.Stack;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.init.ChromeBrowserInitializer;
import org.chromium.chrome.browser.profiles.ProfileManagerUtils;

/* compiled from: PG */
public class Preferences extends ThemeCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    /* renamed from: c */
    public static Preferences f2185c;

    /* renamed from: d */
    public static Stack<CharSequence> f2186d = new Stack<>();

    /* renamed from: e */
    public static Stack<CharSequence> f2187e = new Stack<>();

    /* renamed from: k */
    public static boolean f2188k;

    /* renamed from: b */
    public boolean f2189b;

    /* renamed from: I */
    public static Stack m2876I() {
        return f2187e;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    /* renamed from: G */
    public String mo9161G() {
        return getIntent().getStringExtra("current_tab_url");
    }

    /* renamed from: H */
    public Fragment mo9162H() {
        return getSupportFragmentManager().a(16908290);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    /* renamed from: a */
    public void mo9163a(String str, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(this, Preferences.class);
        intent.putExtra("show_fragment", str);
        intent.putExtra("show_fragment_args", bundle);
        intent.putExtra("current_tab_url", mo9161G());
        startActivity(intent);
    }

    /* renamed from: b */
    public int mo4510b(Theme theme) {
        return ON0.a(getResources(), jx0.pref_toolbar_background_color);
    }

    /* renamed from: c */
    public int mo4511c(Theme theme) {
        int ordinal = theme.ordinal();
        if (ordinal == 1) {
            return xx0.Theme_Chromium_NormalPreferences;
        }
        if (ordinal != 2) {
            return 0;
        }
        return xx0.DarkThemeStyle;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    public void onAttachedToWindow() {
        View findViewById;
        Preferences.super.onAttachedToWindow();
        Fragment H = mo9162H();
        if (H != null && H.getView() != null && H.getView().findViewById(ox0.list) != null && (findViewById = H.getActivity().findViewById(16908290)) != null && (findViewById instanceof FrameLayout)) {
            View inflate = View.inflate(getApplicationContext(), rx0.preferences_action_bar_shadow, (ViewGroup) findViewById);
            RecyclerView findViewById2 = H.getView().findViewById(ox0.list);
            findViewById2.getViewTreeObserver().addOnScrollChangedListener(new pm2(findViewById2, inflate.findViewById(ox0.shadow)));
        }
    }

    public void onBackPressed() {
        OnBackPressedListener H = mo9162H();
        if (!(H instanceof OnBackPressedListener)) {
            Preferences.super.onBackPressed();
        } else if (!H.onBackPressed()) {
            Preferences.super.onBackPressed();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, android.content.Context, android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v7.app.AppCompatActivity, org.chromium.chrome.browser.preferences.Preferences] */
    @SuppressLint({"InlinedApi"})
    public void onMAMCreate(Bundle bundle) {
        NfcAdapter defaultAdapter;
        TextView textView;
        if (!f2188k) {
            f2188k = true;
            try {
                if (Vc0.a(getPackageManager(), getComponentName(), 0).exported) {
                    throw new IllegalStateException("Preferences must not be exported.");
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            ChromeBrowserInitializer.m2488f().mo8798a(false);
            MAMEdgeManager.m1295a((Context) this, MAMEdgeManager.m1292a(false));
            super.onMAMCreate(bundle);
            this.f2189b = bundle == null;
            String stringExtra = getIntent().getStringExtra("show_fragment");
            Bundle bundleExtra = getIntent().getBundleExtra("show_fragment_args");
            getSupportActionBar().c(true);
            getSupportActionBar().a(U5.c(RN0.a, du0.d(getResources(), lx0.btn_left_dark)));
            getSupportActionBar().a(BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            ViewGroup a = TE2.a((ViewGroup) getWindow().getDecorView());
            if (!(a == null || (textView = (TextView) a.getChildAt(0)) == null)) {
                EE2.b(textView);
            }
            if (bundle == null) {
                if (stringExtra == null) {
                    stringExtra = MainPreferences.class.getName();
                }
                Fragment a2 = Fragment.a(this, stringExtra, bundleExtra);
                R4 a3 = getSupportFragmentManager().a();
                a3.a(16908290, a2, (String) null);
                a3.a();
            }
            if (ON0.a(this, "android.permission.NFC", Process.myPid(), Process.myUid()) == 0 && (defaultAdapter = NfcAdapter.getDefaultAdapter(this)) != null) {
                defaultAdapter.setNdefPushMessage((NdefMessage) null, this, new Activity[0]);
            }
        } catch (ProcessInitException e2) {
            Log.e("Preferences", "Failed to start browser process.", e2);
            System.exit(-1);
        }
    }

    public void onMAMPause() {
        Preferences.super.onMAMPause();
        ProfileManagerUtils.a();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    public void onMAMResume() {
        super.onMAMResume();
        ? r0 = f2185c;
        if (r0 == 0 || r0.getTaskId() == getTaskId() || this.f2189b) {
            ? r02 = f2185c;
            if (!(r02 == 0 || r02.getTaskId() == getTaskId())) {
                f2185c.finish();
            }
            f2185c = this;
            this.f2189b = false;
            return;
        }
        finish();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return Preferences.super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        m2878a(preference, TelemetryConstants.ActivityStatus.START, (HashMap<String, String>) null);
        mo9163a(preference.f(), preference.d());
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.app.Activity, android.support.v7.app.AppCompatActivity, org.chromium.chrome.browser.preferences.Preferences] */
    public void onStop() {
        if (!f2186d.isEmpty() && f2186d.peek().toString().equals(getTitle().toString())) {
            f2186d.pop();
            m2878a((Preference) null, TelemetryConstants.ActivityStatus.STOP, (HashMap<String, String>) null);
        }
        Preferences.super.onStop();
        if (f2185c == this) {
            f2185c = null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.app.Activity, org.chromium.chrome.browser.preferences.Preferences] */
    public void onSwitchMAMIdentityComplete(MAMIdentitySwitchResult mAMIdentitySwitchResult) {
        DualIdentityManager.m2201a(mAMIdentitySwitchResult, (Activity) this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity, android.support.v7.app.AppCompatActivity, org.chromium.chrome.browser.preferences.Preferences] */
    public void onTitleChanged(CharSequence charSequence, int i) {
        if (f2186d.isEmpty() || f2186d.peek() != getTitle()) {
            if (getTitle() == "Settings") {
                f2186d.empty();
            }
            f2186d.push(getTitle());
        }
        Preferences.super.onTitleChanged(charSequence, i);
        getWindow().setTitle(BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public static void m2878a(Preference preference, TelemetryConstants.ActivityStatus activityStatus, HashMap<String, String> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        if (TelemetryConstants.ActivityStatus.START == activityStatus && preference != null) {
            String b = Pp0.b(preference.j());
            if (f2187e.isEmpty() || !f2187e.peek().toString().equals(b)) {
                ss0.a("Settings", b, (String) null, hashMap);
                if (!f2187e.isEmpty()) {
                    ss0.a("Settings", f2187e.peek().toString(), (String) null, TelemetryConstants.ActivityStatus.STOP, hashMap);
                }
                f2187e.push(b);
            }
        } else if (TelemetryConstants.ActivityStatus.STOP == activityStatus && !f2187e.isEmpty()) {
            ss0.b("Settings", f2187e.peek().toString(), (String) null, hashMap);
            f2187e.pop();
            if (!f2187e.isEmpty()) {
                ss0.a("Settings", f2187e.peek().toString(), (String) null, hashMap);
            }
        }
    }

    /* renamed from: a */
    public static String m2877a(PreferenceFragmentCompat preferenceFragmentCompat) {
        Preferences activity = preferenceFragmentCompat.getActivity();
        if (!(activity instanceof Preferences)) {
            return null;
        }
        String G = activity.mo9161G();
        if (!URLUtil.isNetworkUrl(G)) {
            return null;
        }
        return G;
    }
}
