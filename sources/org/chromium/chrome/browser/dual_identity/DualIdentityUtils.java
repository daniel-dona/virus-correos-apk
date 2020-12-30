package org.chromium.chrome.browser.dual_identity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.managedbehavior.MAMEdgeManager;
import com.microsoft.managedbehavior.disablefeatures.DisableFeaturesRestartDialogFragment;
import com.microsoft.managedbehavior.urllist.ManagedUrlAction;
import java.util.HashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;

/* compiled from: PG */
public abstract class DualIdentityUtils {

    /* renamed from: a */
    public static StringBuilder f1778a = new StringBuilder();

    /* renamed from: a */
    public static /* synthetic */ String m2224a() {
        return "DualIdentityUtils";
    }

    /* renamed from: a */
    public static void m2227a(String str, String str2, DialogActionType dialogActionType) {
        QN0.a.edit().putInt(Eo.b(str, str2, "_action"), dialogActionType.ordinal()).apply();
    }

    /* renamed from: b */
    public static DialogActionType m2241b(String str, String str2) {
        SharedPreferences sharedPreferences = QN0.a;
        String b = Eo.b(str, str2, "_action");
        DialogActionType dialogActionType = DialogActionType.ShowDialog;
        return DialogActionType.values()[sharedPreferences.getInt(b, 0)];
    }

    /* renamed from: c */
    public static BrowsingPolicy m2247c() {
        boolean B = MicrosoftSigninManager.C0424c.f2104a.mo8867B();
        boolean K = PrefServiceBridge.m2758o0().mo9051K();
        if (B && K) {
            return BrowsingPolicy.NORMAL;
        }
        if (!B && !K) {
            return BrowsingPolicy.BLOCK;
        }
        if (!B) {
            return BrowsingPolicy.DISABLE_MSA;
        }
        return BrowsingPolicy.DISABLE_INPRIVATE;
    }

    /* renamed from: d */
    public static void m2250d() {
        Us0.g.execute(new vT1());
    }

    /* renamed from: f */
    public static boolean m2255f() {
        return QN0.a.getBoolean("openInPrivateIfBlocked", false);
    }

    /* renamed from: e */
    public static String m2252e() {
        String b = Fg0.b("proxyPacUrlUuid");
        if (b != null) {
            return Fg0.a("proxyPacUrl", b);
        }
        return null;
    }

    /* renamed from: a */
    public static UrlActionType m2225a(String str, boolean z, boolean z2, boolean z3) {
        if (z3) {
            return UrlActionType.None;
        }
        if (!MicrosoftSigninManager.C0424c.f2104a.mo8866A() && Fe0.a(str, z2)) {
            if (MicrosoftSigninManager.C0424c.f2104a.mo8930w()) {
                if (z) {
                    return UrlActionType.SwitchToManaged;
                }
            } else if (z) {
                return UrlActionType.SignInToManaged;
            }
        }
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            if (MAMEdgeManager.m1288a(str) == ManagedUrlAction.ALLOW_TRANSITION) {
                if (MicrosoftSigninManager.C0424c.f2104a.mo8932y()) {
                    if (!z || !z2) {
                        return UrlActionType.SilentlyBlock;
                    }
                    return UrlActionType.SwitchToPersonal;
                } else if (!z || !z2) {
                    return UrlActionType.SilentlyBlock;
                } else {
                    return UrlActionType.SignInToPersonal;
                }
            } else if (MAMEdgeManager.m1288a(str) == ManagedUrlAction.BLOCK) {
                if (!z || !z2) {
                    return UrlActionType.SilentlyBlock;
                }
                return UrlActionType.BlockedSite;
            }
        }
        return UrlActionType.None;
    }

    /* renamed from: b */
    public static final /* synthetic */ void m2246b(ModalDialogCallback modalDialogCallback, boolean z) {
        if (!z) {
            m2227a(MicrosoftSigninManager.C0424c.f2104a.mo8928u(), "_switch_to_managed_account", DialogActionType.Cancel);
        }
        modalDialogCallback.onCompleted(false);
    }

    /* renamed from: b */
    public static /* synthetic */ void m2242b() {
        if (f1778a.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("data", GJ1.a(f1778a.toString()));
            ss0.b("DualIdentityDiagnostic", hashMap, true, 0, BuildConfig.FLAVOR);
            f1778a.setLength(0);
        }
    }

    /* renamed from: c */
    public static void m2248c(String str, String str2) {
        Us0.g.execute(new uT1(str, str2));
    }

    /* renamed from: b */
    public static void m2244b(ChromeActivity chromeActivity, String str, ModalDialogCallback modalDialogCallback) {
        DualIdentityModalDialogFragment.d(chromeActivity, new dT1(str, modalDialogCallback), new eT1(modalDialogCallback), new fT1(modalDialogCallback));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context, android.support.v4.app.FragmentActivity] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2235a(org.chromium.chrome.browser.ChromeActivity r3, org.chromium.chrome.browser.dual_identity.DualIdentityUtils.UrlActionType r4, java.lang.String r5, org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback r6) {
        /*
            java.lang.String r0 = "DualIdentityUtils"
            java.lang.String r1 = "urlActionType = "
            java.lang.StringBuilder r1 = Eo.a(r1)
            java.lang.String r2 = r4.name()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            m2248c(r0, r1)
            int r4 = r4.ordinal()
            r0 = 0
            r1 = 1
            if (r4 == r1) goto L_0x010c
            r2 = 2
            if (r4 == r2) goto L_0x00bf
            r0 = 3
            if (r4 == r0) goto L_0x0071
            r0 = 4
            if (r4 == r0) goto L_0x0037
            r5 = 5
            if (r4 != r5) goto L_0x002f
            m2234a((org.chromium.chrome.browser.ChromeActivity) r3, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x002f:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException
            java.lang.String r4 = "not expected"
            r3.<init>(r4)
            throw r3
        L_0x0037:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r4 = m2247c()
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.NORMAL
            if (r4 != r0) goto L_0x004f
            boolean r4 = m2255f()
            if (r4 == 0) goto L_0x004a
            m2232a((org.chromium.chrome.browser.ChromeActivity) r3, (java.lang.String) r5)
            goto L_0x014b
        L_0x004a:
            m2233a((org.chromium.chrome.browser.ChromeActivity) r3, (java.lang.String) r5, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x004f:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.DISABLE_MSA
            if (r4 != r0) goto L_0x0063
            boolean r4 = m2255f()
            if (r4 == 0) goto L_0x005e
            m2232a((org.chromium.chrome.browser.ChromeActivity) r3, (java.lang.String) r5)
            goto L_0x014b
        L_0x005e:
            m2234a((org.chromium.chrome.browser.ChromeActivity) r3, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x0063:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.DISABLE_INPRIVATE
            if (r4 != r0) goto L_0x006c
            m2233a((org.chromium.chrome.browser.ChromeActivity) r3, (java.lang.String) r5, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x006c:
            m2234a((org.chromium.chrome.browser.ChromeActivity) r3, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x0071:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r4 = m2247c()
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.NORMAL
            if (r4 != r0) goto L_0x009d
            boolean r4 = m2255f()
            if (r4 == 0) goto L_0x0098
            nT1 r4 = new nT1
            r4.<init>(r5, r6)
            oT1 r0 = new oT1
            r0.<init>(r6, r3, r5)
            pT1 r5 = new pT1
            r5.<init>(r6)
            qT1 r1 = new qT1
            r1.<init>(r6)
            org.chromium.chrome.browser.dual_identity.DualIdentityModalDialogFragment.a(r3, r4, r0, r5, r1)
            goto L_0x014b
        L_0x0098:
            m2244b(r3, r5, r6)
            goto L_0x014b
        L_0x009d:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.DISABLE_MSA
            if (r4 != r0) goto L_0x00b1
            boolean r4 = m2255f()
            if (r4 == 0) goto L_0x00ac
            m2232a((org.chromium.chrome.browser.ChromeActivity) r3, (java.lang.String) r5)
            goto L_0x014b
        L_0x00ac:
            m2234a((org.chromium.chrome.browser.ChromeActivity) r3, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x00b1:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$BrowsingPolicy r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.BrowsingPolicy.DISABLE_INPRIVATE
            if (r4 != r0) goto L_0x00ba
            m2244b(r3, r5, r6)
            goto L_0x014b
        L_0x00ba:
            m2234a((org.chromium.chrome.browser.ChromeActivity) r3, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback) r6)
            goto L_0x014b
        L_0x00bf:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r4 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            java.lang.String r4 = r4.mo8928u()
            java.lang.String r2 = "_sign_in_to_managed_account"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r4 = m2241b((java.lang.String) r4, (java.lang.String) r2)
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r2 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.Cancel
            if (r4 == r2) goto L_0x00d9
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r2 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            boolean r2 = r2.mo8930w()
            if (r2 != 0) goto L_0x00d9
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r4 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.ShowDialog
        L_0x00d9:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r2 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.ShowDialog
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x00f4
            kT1 r4 = new kT1
            r4.<init>(r5, r3, r6)
            lT1 r5 = new lT1
            r5.<init>(r6)
            mT1 r0 = new mT1
            r0.<init>(r6)
            org.chromium.chrome.browser.dual_identity.DualIdentityModalDialogFragment.a(r3, r4, r5, r0)
            goto L_0x014b
        L_0x00f4:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r2 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.SignInToManaged
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x0108
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2203a((java.lang.String) r5)
            r4 = 31
            org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity.m2559a(r3, r4)
            r6.onCompleted(r1)
            goto L_0x014b
        L_0x0108:
            r6.onCompleted(r0)
            goto L_0x014b
        L_0x010c:
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r4 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            java.lang.String r4 = r4.mo8928u()
            java.lang.String r1 = "_switch_to_managed_account"
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r4 = m2241b((java.lang.String) r4, (java.lang.String) r1)
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r1 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.ShowDialog
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0133
            YS1 r4 = new YS1
            r4.<init>(r5, r6)
            cT1 r5 = new cT1
            r5.<init>(r6)
            jT1 r0 = new jT1
            r0.<init>(r6)
            org.chromium.chrome.browser.dual_identity.DualIdentityModalDialogFragment.c(r3, r4, r5, r0)
            goto L_0x014b
        L_0x0133:
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r3 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.SwitchToManaged
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0148
            org.chromium.chrome.browser.dual_identity.DualIdentityManager$ProfileSwitchAccessPoint r3 = org.chromium.chrome.browser.dual_identity.DualIdentityManager.ProfileSwitchAccessPoint.SOFT_TRANSITION
            com.microsoft.authentication.AuthenticationMode r4 = com.microsoft.authentication.AuthenticationMode.AAD
            sT1 r0 = new sT1
            r0.<init>(r5, r6)
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2205a((org.chromium.chrome.browser.dual_identity.DualIdentityManager.ProfileSwitchAccessPoint) r3, (com.microsoft.authentication.AuthenticationMode) r4, (org.chromium.chrome.browser.dual_identity.DualIdentityManager.IProfileSwitchCallback) r0)
            goto L_0x014b
        L_0x0148:
            r6.onCompleted(r0)
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2235a(org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.dual_identity.DualIdentityUtils$UrlActionType, java.lang.String, org.chromium.chrome.browser.dual_identity.DualIdentityUtils$ModalDialogCallback):void");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void m2229a(java.lang.String r2, org.chromium.chrome.browser.ChromeActivity r3, org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback r4, boolean r5) {
        /*
            if (r5 != 0) goto L_0x000f
            org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager r5 = org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager.C0424c.f2104a
            java.lang.String r5 = r5.mo8928u()
            org.chromium.chrome.browser.dual_identity.DualIdentityUtils$DialogActionType r0 = org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType.SignInToManaged
            java.lang.String r1 = "_sign_in_to_managed_account"
            m2227a((java.lang.String) r5, (java.lang.String) r1, (org.chromium.chrome.browser.dual_identity.DualIdentityUtils.DialogActionType) r0)
        L_0x000f:
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2203a((java.lang.String) r2)
            r2 = 31
            org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity.m2559a(r3, r2)
            r2 = 1
            r4.onCompleted(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2229a(java.lang.String, org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.dual_identity.DualIdentityUtils$ModalDialogCallback, boolean):void");
    }

    /* renamed from: a */
    public static final /* synthetic */ void m2239a(ModalDialogCallback modalDialogCallback, boolean z) {
        if (!z) {
            m2227a(MicrosoftSigninManager.C0424c.f2104a.mo8928u(), "_sign_in_to_managed_account", DialogActionType.Cancel);
        }
        modalDialogCallback.onCompleted(false);
    }

    /* renamed from: a */
    public static final /* synthetic */ void m2231a(String str, ModalDialogCallback modalDialogCallback, boolean z) {
        if (!z) {
            m2227a(MicrosoftSigninManager.C0424c.f2104a.mo8928u(), "_switch_to_managed_account", DialogActionType.SwitchToManaged);
        }
        DualIdentityManager.m2205a(DualIdentityManager.ProfileSwitchAccessPoint.SOFT_TRANSITION, AuthenticationMode.AAD, (DualIdentityManager.IProfileSwitchCallback) new rT1(str, modalDialogCallback));
    }

    /* renamed from: a */
    public static final /* synthetic */ void m2238a(ModalDialogCallback modalDialogCallback, ChromeActivity chromeActivity, String str) {
        modalDialogCallback.onCompleted(true);
        chromeActivity.mo8081b(true).a(str, 2);
    }

    /* renamed from: a */
    public static void m2236a(DualIdentityManager.ProfileSwitchAccessPoint profileSwitchAccessPoint, boolean z, AuthenticationMode authenticationMode) {
        HashMap d = Eo.d("source", BuildConfig.FLAVOR);
        if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.SIGN_IN) {
            d.put("type", "signIn");
        } else if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.SIGN_OUT) {
            d.put("type", "signOut");
        } else if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.SOFT_TRANSITION) {
            d.put("type", "softTransition");
        } else if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.IMPLICIT_IDENTITY_SWITCH) {
            d.put("type", "implicitIdentitySwitch");
        } else if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.SET_UI_POLICY_IDENTITY_FAILED) {
            d.put("type", "setUIPolicyIdentityFailed");
        } else {
            d.put("type", "accountSwitcher");
            if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.ID_CONTROLLER_TOOLBAR_NTP) {
                d.put("source", "mainFrame");
            } else if (profileSwitchAccessPoint == DualIdentityManager.ProfileSwitchAccessPoint.ID_CONTROLLER_TOOLBAR_TABCENTER) {
                d.put("source", "tabCenter");
            } else {
                d.put("source", "hub");
            }
        }
        String str = "AAD";
        String str2 = "MSA";
        if (authenticationMode != AuthenticationMode.MSA) {
            String str3 = str2;
            str2 = str;
            str = str3;
        }
        d.put("preActiveAccountType", str);
        d.put("nextActiveAccountType", str2);
        ss0.b("SwitchAccount", d, z, 0, BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public static void m2226a(String str, String str2) {
        if (m2241b(str, str2) != DialogActionType.Cancel) {
            SharedPreferences.Editor edit = QN0.a.edit();
            edit.remove(str + str2 + "_action").apply();
        }
    }

    /* renamed from: a */
    public static void m2233a(ChromeActivity chromeActivity, String str, ModalDialogCallback modalDialogCallback) {
        DualIdentityModalDialogFragment.b(chromeActivity, new ZS1(str, chromeActivity, modalDialogCallback), new aT1(modalDialogCallback), new bT1(modalDialogCallback));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void m2228a(java.lang.String r0, org.chromium.chrome.browser.ChromeActivity r1, org.chromium.chrome.browser.dual_identity.DualIdentityUtils.ModalDialogCallback r2) {
        /*
            org.chromium.chrome.browser.dual_identity.DualIdentityManager.m2203a((java.lang.String) r0)
            r0 = 30
            org.chromium.chrome.browser.microsoft_signin.MicrosoftAccountSigninActivity.m2559a(r1, r0)
            r0 = 1
            r2.onCompleted(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2228a(java.lang.String, org.chromium.chrome.browser.ChromeActivity, org.chromium.chrome.browser.dual_identity.DualIdentityUtils$ModalDialogCallback):void");
    }

    /* renamed from: a */
    public static void m2234a(ChromeActivity chromeActivity, ModalDialogCallback modalDialogCallback) {
        DualIdentityModalDialogFragment.a(chromeActivity, new gT1(modalDialogCallback), new hT1(modalDialogCallback));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.ChromeActivity, android.app.Activity] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2232a(org.chromium.chrome.browser.ChromeActivity r4, java.lang.String r5) {
        /*
            r0 = 1
            Lw2$a r1 = r4.mo8081b((boolean) r0)
            r2 = 2
            r1.a(r5, r2)
            xT1 r5 = new xT1
            r5.<init>()
            int r1 = wx0.open_inprivate_if_blocked
            java.lang.CharSequence r1 = r4.getText(r1)
            r2 = 109(0x6d, float:1.53E-43)
            rt2 r5 = rt2.a(r1, r5, r0, r2)
            r0 = 0
            android.content.SharedPreferences r1 = QN0.a     // Catch:{ NumberFormatException -> 0x002a }
            java.lang.String r2 = "durationOfOpenInPrivateSnackBar"
            java.lang.String r3 = "7"
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ NumberFormatException -> 0x002a }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x002a }
            goto L_0x0034
        L_0x002a:
            java.lang.String r1 = "DualIdentityUtils"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            java.lang.String r3 = "Wrong format for the key durationOfOpenInPrivateSnackBar, set as default value"
            VN0.a(r1, r3, r2)
            r1 = 7
        L_0x0034:
            int r1 = r1 * 1000
            r5.k = r1
            r5.j = r0
            org.chromium.chrome.browser.snackbar.SnackbarManager r4 = r4.getSnackbarManager()
            r4.a(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.dual_identity.DualIdentityUtils.m2232a(org.chromium.chrome.browser.ChromeActivity, java.lang.String):void");
    }

    /* renamed from: a */
    public static final /* synthetic */ void m2240a(boolean z, String str) {
        DisableFeaturesRestartDialogFragment b = DisableFeaturesRestartDialogFragment.b(new Sf0(z && !TextUtils.isEmpty(str)));
        Activity activity = ApplicationStatus.f1396d;
        if (activity instanceof ChromeTabbedActivity) {
            b.show(((ChromeTabbedActivity) activity).getSupportFragmentManager(), "ProxyConfigurationDialogDataProvider");
        }
    }
}
