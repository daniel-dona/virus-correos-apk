package org.chromium.chrome.browser.adblocker;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.microsoft.rewards.mission.Mission;
import com.microsoft.rewards.mission.MissionCompleteType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.adblocker.AdBlockerSettings;

/* compiled from: PG */
public class AdBlockerBridge {

    /* renamed from: d */
    public static final String f1682d = AdBlockerBridge.class.toString();

    /* renamed from: e */
    public static AdBlockerBridge f1683e = new AdBlockerBridge();

    /* renamed from: a */
    public AdBlockerSettings f1684a = new AdBlockerSettings();

    /* renamed from: b */
    public long f1685b = nativeInit();

    /* renamed from: c */
    public boolean f1686c = false;

    private native void nativeAddAdblockerWhitelistDomain(long j, String str);

    private native void nativeDestroy(long j);

    private native long nativeInit();

    private native void nativeInitializeAdBlocker(long j, String str, int i, boolean z, String[] strArr);

    private native void nativeRemoveAdblockerWhitelistDomain(long j, String str);

    private native void nativeSetAdblockerAAEnabled(long j, boolean z);

    private native void nativeSetAdblockerEnabled(long j, boolean z);

    /* renamed from: a */
    public void mo8314a(boolean z) {
        this.f1684a.f1690c = z;
        Eo.a(QN0.a, "adblock_enable_aa", z);
        nativeSetAdblockerAAEnabled(this.f1685b, z);
    }

    /* renamed from: b */
    public void mo8316b(boolean z) {
        this.f1684a.f1689b = z;
        Eo.a(QN0.a, "adblock_enabled", z);
        if (z && !this.f1686c) {
            mo8319c();
        }
        if (z) {
            Tl0.g().a(Mission.MISSION_TURN_ON_ADD_BLOCKER, MissionCompleteType.MANUAL);
        }
        nativeSetAdblockerEnabled(this.f1685b, z);
    }

    /* renamed from: c */
    public void mo8319c() {
        if (!this.f1686c) {
            AdBlockerSettings adBlockerSettings = this.f1684a;
            if (adBlockerSettings.f1689b) {
                long j = this.f1685b;
                String str = adBlockerSettings.f1691d;
                int value = adBlockerSettings.f1688a.getValue();
                AdBlockerSettings adBlockerSettings2 = this.f1684a;
                boolean z = adBlockerSettings2.f1690c;
                Set<String> set = adBlockerSettings2.f1692e;
                nativeInitializeAdBlocker(j, str, value, z, (String[]) set.toArray(new String[set.size()]));
                this.f1686c = true;
            }
        }
    }

    /* renamed from: d */
    public boolean mo8320d() {
        return this.f1684a.f1690c;
    }

    /* renamed from: e */
    public boolean mo8321e() {
        return this.f1684a.f1689b;
    }

    @CalledByNative
    public void onURLBlocked(String str) {
        AdBlockerSettings adBlockerSettings = this.f1684a;
        adBlockerSettings.f1693f++;
        SharedPreferences.Editor edit = QN0.a.edit();
        edit.putLong("adblock_blocked_count", adBlockerSettings.f1693f);
        edit.apply();
    }

    /* renamed from: a */
    public AdBlockerSettings.WhitelistDomainOptResult mo8313a(String str) {
        String b = mo8315b(str);
        AdBlockerSettings.WhitelistDomainOptResult a = this.f1684a.mo8323a(b);
        if (a == AdBlockerSettings.WhitelistDomainOptResult.SUCCESS) {
            nativeAddAdblockerWhitelistDomain(this.f1685b, b);
        }
        return a;
    }

    /* renamed from: b */
    public final String mo8315b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.toLowerCase(Locale.US).trim();
        if (!trim.contains("://")) {
            trim = Eo.a("http://", trim);
        }
        try {
            return new URL(trim).getHost();
        } catch (MalformedURLException e) {
            VN0.a(f1682d, e.getMessage(), new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public long mo8312a() {
        return this.f1684a.f1693f;
    }

    /* renamed from: c */
    public AdBlockerSettings.WhitelistDomainOptResult mo8318c(String str) {
        String b = mo8315b(str);
        AdBlockerSettings.WhitelistDomainOptResult b2 = this.f1684a.mo8324b(b);
        if (b2 == AdBlockerSettings.WhitelistDomainOptResult.SUCCESS) {
            nativeRemoveAdblockerWhitelistDomain(this.f1685b, b);
        }
        return b2;
    }

    /* renamed from: b */
    public String[] mo8317b() {
        Set<String> set = this.f1684a.f1692e;
        return (String[]) set.toArray(new String[set.size()]);
    }
}
