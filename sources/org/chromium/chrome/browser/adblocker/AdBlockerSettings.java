package org.chromium.chrome.browser.adblocker;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.microsoft.ruby.util.RubyBuild;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.CollectionUtil;

/* compiled from: PG */
public class AdBlockerSettings {

    /* renamed from: g */
    public static final HashSet<String> f1687g = CollectionUtil.m1382b(new String[0]);

    /* renamed from: a */
    public AdBlockerProviderType f1688a;

    /* renamed from: b */
    public boolean f1689b;

    /* renamed from: c */
    public boolean f1690c;

    /* renamed from: d */
    public String f1691d;

    /* renamed from: e */
    public Set<String> f1692e;

    /* renamed from: f */
    public long f1693f;

    /* compiled from: PG */
    public enum AdBlockerProviderType {
        ABP(0);
        
        public int value;

        /* access modifiers changed from: public */
        AdBlockerProviderType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static AdBlockerProviderType valueOf(int i) {
            if (i != 0) {
                return ABP;
            }
            return ABP;
        }
    }

    /* compiled from: PG */
    public enum WhitelistDomainOptResult {
        SUCCESS,
        FAIL_NO_VALID_DOMAIN,
        FAIL_DUPLICATE_DOMAIN,
        FAIL_DOMAIN_NOT_EXIST
    }

    static {
        AdBlockerSettings.class.toString();
    }

    public AdBlockerSettings() {
        SharedPreferences sharedPreferences = QN0.a;
        this.f1688a = AdBlockerProviderType.valueOf(sharedPreferences.getInt("adblock_provider_type", AdBlockerProviderType.ABP.getValue()));
        this.f1689b = sharedPreferences.getBoolean("adblock_enabled", !RubyBuild.getForCurrentBuild().checkSupport(EnumSet.of(RubyBuild.PRODUCT, RubyBuild.BETA, RubyBuild.ALPHA)));
        this.f1690c = sharedPreferences.getBoolean("adblock_enable_aa", true);
        this.f1691d = sharedPreferences.getString("adblock_locale", Dn2.a());
        this.f1693f = sharedPreferences.getLong("adblock_blocked_count", 0);
        this.f1692e = sharedPreferences.getStringSet("adblock_whitelist_domains", f1687g);
    }

    /* renamed from: a */
    public WhitelistDomainOptResult mo8323a(String str) {
        if (TextUtils.isEmpty(str)) {
            return WhitelistDomainOptResult.FAIL_NO_VALID_DOMAIN;
        }
        if (this.f1692e.contains(str)) {
            return WhitelistDomainOptResult.FAIL_DUPLICATE_DOMAIN;
        }
        this.f1692e.add(str);
        SharedPreferences.Editor edit = QN0.a.edit();
        edit.putStringSet("adblock_whitelist_domains", this.f1692e);
        edit.apply();
        return WhitelistDomainOptResult.SUCCESS;
    }

    /* renamed from: b */
    public WhitelistDomainOptResult mo8324b(String str) {
        if (TextUtils.isEmpty(str)) {
            return WhitelistDomainOptResult.FAIL_NO_VALID_DOMAIN;
        }
        if (!this.f1692e.contains(str)) {
            return WhitelistDomainOptResult.FAIL_DOMAIN_NOT_EXIST;
        }
        this.f1692e.remove(str);
        SharedPreferences.Editor edit = QN0.a.edit();
        edit.putStringSet("adblock_whitelist_domains", this.f1692e);
        edit.apply();
        return WhitelistDomainOptResult.SUCCESS;
    }
}
