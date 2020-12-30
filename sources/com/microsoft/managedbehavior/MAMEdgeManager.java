package com.microsoft.managedbehavior;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.ADALError;
import com.microsoft.aad.adal.AuthenticationException;
import com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.intune.mam.client.MAMIdentitySwitchResult;
import com.microsoft.intune.mam.client.app.IdentitySwitchOption;
import com.microsoft.intune.mam.client.identity.MAMFileProtectionInfo;
import com.microsoft.intune.mam.client.identity.MAMPolicyManagerBehavior;
import com.microsoft.intune.mam.client.identity.MAMSetUIIdentityCallback;
import com.microsoft.intune.mam.client.notification.MAMNotificationReceiver;
import com.microsoft.intune.mam.client.notification.MAMNotificationReceiverRegistry;
import com.microsoft.intune.mam.policy.MAMComplianceManager;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager;
import com.microsoft.intune.mam.policy.MAMUserInfo;
import com.microsoft.intune.mam.policy.SaveLocation;
import com.microsoft.intune.mam.policy.notification.MAMComplianceNotification;
import com.microsoft.intune.mam.policy.notification.MAMNotification;
import com.microsoft.intune.mam.policy.notification.MAMNotificationType;
import com.microsoft.managedbehavior.urllist.ManagedUrlAction;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.dual_identity.DualIdentityUtils;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.p012ui.base.SelectFileDialog;
import ve0;

/* compiled from: PG */
public class MAMEdgeManager {

    /* renamed from: a */
    public static AtomicBoolean f1289a = new AtomicBoolean(false);

    /* renamed from: b */
    public static SharedPreferences f1290b;

    /* compiled from: PG */
    public interface MAMComplianceNotificationCallback {
        void handleComplianceNotification(MAMComplianceNotification mAMComplianceNotification);
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$a */
    /* compiled from: PG */
    public class C0348a implements Callable<File> {
        public Object call() throws Exception {
            return new File(DualIdentityManager.m2194a(AuthenticationMode.AAD));
        }
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$b */
    /* compiled from: PG */
    public class C0349b implements MAMSetUIIdentityCallback {

        /* renamed from: a */
        public final /* synthetic */ String f1291a;

        public C0349b(String str) {
            this.f1291a = str;
        }

        public void notifyIdentityResult(MAMIdentitySwitchResult mAMIdentitySwitchResult) {
            if (mAMIdentitySwitchResult == MAMIdentitySwitchResult.SUCCEEDED) {
                MAMEdgeManager.m1289a();
                DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "setUIPolicyIdentity succeeded for " + this.f1291a);
                return;
            }
            MAMEdgeManager.m1289a();
            DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "setUIPolicyIdentity failed for " + this.f1291a);
        }
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$c */
    /* compiled from: PG */
    public class C0350c implements Runnable {
        public void run() {
            new e((C0348a) null).a(Us0.c);
        }
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$d */
    /* compiled from: PG */
    public class C0351d implements MAMNotificationReceiver {

        /* renamed from: a */
        public final /* synthetic */ MAMComplianceNotificationCallback f1292a;

        /* renamed from: b */
        public final /* synthetic */ MAMNotificationReceiverRegistry f1293b;

        public C0351d(MAMComplianceNotificationCallback mAMComplianceNotificationCallback, MAMNotificationReceiverRegistry mAMNotificationReceiverRegistry) {
            this.f1292a = mAMComplianceNotificationCallback;
            this.f1293b = mAMNotificationReceiverRegistry;
        }

        public boolean onReceive(MAMNotification mAMNotification) {
            if (mAMNotification.getType() == MAMNotificationType.COMPLIANCE_STATUS) {
                try {
                    this.f1292a.handleComplianceNotification((MAMComplianceNotification) mAMNotification);
                } catch (Exception e) {
                    MAMEdgeManager.m1289a();
                    VN0.c("com.microsoft.managedbehavior.MAMEdgeManager", "callback for Intune compliance notification threw an exception", new Object[]{e});
                }
                this.f1293b.unregisterReceiver(this, MAMNotificationType.COMPLIANCE_STATUS);
            }
            return true;
        }
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$f */
    /* compiled from: PG */
    public static class C0352f extends CP0<Void> {

        /* renamed from: i */
        public File[] f1294i;

        /* renamed from: j */
        public String f1295j;

        public C0352f(File[] fileArr, String str) {
            this.f1294i = fileArr;
            this.f1295j = str;
        }

        /* renamed from: a */
        public Object mo4443a() {
            for (File file : this.f1294i) {
                if (file != null) {
                    StringBuilder a = Eo.a(" protect file: ");
                    a.append(file.getAbsolutePath());
                    a.toString();
                    MAMEdgeManager.m1296a(file, this.f1295j);
                }
            }
            return null;
        }
    }

    /* renamed from: com.microsoft.managedbehavior.MAMEdgeManager$g */
    /* compiled from: PG */
    public static class C0353g extends CP0<Void> {
        public /* synthetic */ C0353g(C0348a aVar) {
        }

        /* renamed from: a */
        public Object mo4444a() {
            if (!MAMEdgeManager.m1309g() || !MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
                return null;
            }
            MAMEdgeManager.m1299b();
            return null;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ String m1289a() {
        return "com.microsoft.managedbehavior.MAMEdgeManager";
    }

    /* renamed from: a */
    public static String m1291a(Uri uri) {
        try {
            MAMFileProtectionInfo a = Yc0.a(Tc0.c(RN0.a.getContentResolver(), uri, "r"));
            if (a != null) {
                return a.getIdentity();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static void m1299b() {
        if (!f1289a.get()) {
            f1289a.set(true);
        }
    }

    /* renamed from: c */
    public static void m1304c(String str) {
        Eo.f("unenroll ", str);
        if (str != null) {
            m1296a((File) ThreadUtils.m1459b(new C0348a()), BuildConfig.FLAVOR);
            ((MAMEnrollmentManager) xb0.a(MAMEnrollmentManager.class)).unregisterAccountForMAM(str);
            m1302c().edit().clear().apply();
        }
    }

    /* renamed from: d */
    public static String m1305d() {
        return ((MAMUserInfo) xb0.a(MAMUserInfo.class)).getPrimaryUser();
    }

    /* renamed from: e */
    public static boolean m1307e() {
        return MicrosoftSigninManager.C0424c.f2104a.mo8866A() && m1301b(MicrosoftSigninManager.C0424c.f2104a.mo8912i());
    }

    /* renamed from: f */
    public static boolean m1308f() {
        return f1289a.get();
    }

    /* renamed from: g */
    public static boolean m1309g() {
        String c = MicrosoftSigninManager.C0424c.f2104a.mo8899c();
        if (c == null || ((MAMEnrollmentManager) xb0.a(MAMEnrollmentManager.class)).getRegisteredAccountStatus(c) == MAMEnrollmentManager.Result.NOT_LICENSED) {
            return false;
        }
        return true;
    }

    /* renamed from: h */
    public static boolean m1310h() {
        if (!MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            return true;
        }
        return cd0.a(MicrosoftSigninManager.C0424c.f2104a.mo8899c()).getIsScreenCaptureAllowed();
    }

    /* renamed from: i */
    public static void m1311i() {
        Class<MAMPolicyManagerBehavior> cls = MAMPolicyManagerBehavior.class;
        ((MAMEnrollmentManager) xb0.a(MAMEnrollmentManager.class)).registerAuthenticationCallback(new Re0());
        m1313k();
        "UIIdentity " + ((MAMPolicyManagerBehavior) xb0.a(cls)).getUIPolicyIdentity(RN0.a);
        "ProcessIdentity " + ((MAMPolicyManagerBehavior) xb0.a(cls)).getProcessIdentity();
        "ThreadIdentity " + ((MAMPolicyManagerBehavior) xb0.a(cls)).getCurrentThreadIdentity();
        new C0353g((C0348a) null).a(Us0.d);
        SelectFileDialog.m3661a(we0.a);
    }

    @CalledByNative
    public static boolean isSaveToLocalAllowed() {
        if (!MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            return true;
        }
        return cd0.a(MicrosoftSigninManager.C0424c.f2104a.mo8899c()).getIsSaveToLocationAllowed(SaveLocation.LOCAL, MicrosoftSigninManager.C0424c.f2104a.mo8912i());
    }

    /* renamed from: j */
    public static void m1312j() {
        DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "refreshAppConfigAsync");
        ThreadUtils.m1462c();
        DualIdentityManager.m2202a((Runnable) new C0350c());
    }

    /* renamed from: k */
    public static void m1313k() {
        MAMNotificationReceiverRegistry mAMNotificationReceiverRegistry = (MAMNotificationReceiverRegistry) xb0.a(MAMNotificationReceiverRegistry.class);
        De0 de0 = new De0();
        mAMNotificationReceiverRegistry.registerReceiver(de0, MAMNotificationType.REFRESH_POLICY);
        mAMNotificationReceiverRegistry.registerReceiver(de0, MAMNotificationType.REFRESH_APP_CONFIG);
        mAMNotificationReceiverRegistry.registerReceiver(de0, MAMNotificationType.MAM_ENROLLMENT_RESULT);
        mAMNotificationReceiverRegistry.registerReceiver(de0, MAMNotificationType.WIPE_USER_DATA);
        db0.a(new ve0.a());
    }

    @CalledByNative
    public static void onDownloadBlockedByMAM() {
        m1303c((Activity) null);
    }

    /* renamed from: d */
    public static void m1306d(Activity activity) {
        Ie0.b().a(activity);
    }

    /* renamed from: b */
    public static boolean m1301b(String str) {
        if (str == null) {
            return false;
        }
        return ((MAMPolicyManagerBehavior) xb0.a(MAMPolicyManagerBehavior.class)).getIsIdentityManaged(str);
    }

    /* renamed from: b */
    public static void m1300b(Activity activity) {
        if (!m1309g()) {
            VN0.b("com.microsoft.managedbehavior.MAMEdgeManager", "Not refreshing app proxy because managed user is not signed in.", new Object[0]);
        } else {
            bf0.d().a(activity);
        }
    }

    /* renamed from: a */
    public static String m1290a(Context context) {
        String uIPolicyIdentity = ((MAMPolicyManagerBehavior) xb0.a(MAMPolicyManagerBehavior.class)).getUIPolicyIdentity(context);
        DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "UI Identity for " + context + " is " + uIPolicyIdentity);
        return uIPolicyIdentity;
    }

    /* renamed from: c */
    public static void m1303c(Activity activity) {
        if (activity == null) {
            activity = (Activity) ChromeActivity.f1556M4.get();
        }
        if (activity == null) {
            VN0.a("com.microsoft.managedbehavior.MAMEdgeManager", "Could not show download blocked dialog because we could not get an Activity.", new Object[0]);
        } else {
            activity.runOnUiThread(new xe0(activity));
        }
    }

    /* renamed from: b */
    public static AP0 m1298b(File file, String str) {
        return m1287a(new File[]{file}, str);
    }

    /* renamed from: a */
    public static final /* synthetic */ void m1293a(Activity activity) {
        AlertDialog create = new rb0(activity).create();
        create.setMessage(activity.getString(wx0.download_blocked_by_mam));
        create.setButton(-2, activity.getString(wx0.ok), new ye0(create));
        create.setCancelable(false);
        if (Og0.d()) {
            Ng0.a(create.getWindow());
        }
        create.show();
    }

    /* renamed from: c */
    public static SharedPreferences m1302c() {
        if (f1290b == null) {
            f1290b = RN0.a.getSharedPreferences("mam_edge_prefs_file", 0);
        }
        return f1290b;
    }

    /* renamed from: a */
    public static String m1292a(boolean z) {
        String str;
        if (z) {
            str = MicrosoftSigninManager.C0424c.f2104a.mo8920m();
        } else {
            str = MicrosoftSigninManager.C0424c.f2104a.mo8912i();
        }
        return str == null ? BuildConfig.FLAVOR : str;
    }

    /* renamed from: a */
    public static void m1296a(File file, String str) {
        try {
            Yc0.a(file, str == null ? BuildConfig.FLAVOR : str);
            DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "protect " + file.getPath() + " with identity '" + str + "' succeeded");
        } catch (Exception e) {
            StringBuilder a = Eo.a("protect ");
            a.append(file.getPath());
            a.append(" with identity '");
            a.append(str);
            a.append("' failed.");
            a.append(e);
            DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", a.toString());
        }
    }

    /* renamed from: a */
    public static AP0 m1287a(File[] fileArr, String str) {
        C0352f fVar = new C0352f(fileArr, str);
        fVar.a(Us0.c);
        return fVar;
    }

    /* renamed from: a */
    public static void m1295a(Context context, String str) {
        String a = m1290a(context);
        DualIdentityUtils.m2248c("com.microsoft.managedbehavior.MAMEdgeManager", "setUIPolicyIdentity(" + str + ") for " + context + ", its previous identity is " + a);
        cd0.a(context, str, new C0349b(context.toString()), EnumSet.of(IdentitySwitchOption.IGNORE_INTENT));
    }

    /* renamed from: a */
    public static ManagedUrlAction m1288a(String str) {
        if (str == null || !f1289a.get()) {
            return ManagedUrlAction.ALLOW;
        }
        if (!Dg0.a(str)) {
            return ManagedUrlAction.ALLOW;
        }
        return Dg0.b() ? ManagedUrlAction.ALLOW_TRANSITION : ManagedUrlAction.BLOCK;
    }

    /* renamed from: a */
    public static boolean m1297a(AuthenticationException authenticationException, boolean z, MAMComplianceNotificationCallback mAMComplianceNotificationCallback) {
        if (authenticationException.getCode() != ADALError.AUTH_FAILED_INTUNE_POLICY_REQUIRED || !(authenticationException instanceof IntuneAppProtectionPolicyRequiredException)) {
            return false;
        }
        IntuneAppProtectionPolicyRequiredException intuneAppProtectionPolicyRequiredException = (IntuneAppProtectionPolicyRequiredException) authenticationException;
        String accountUpn = intuneAppProtectionPolicyRequiredException.getAccountUpn();
        String accountUserId = intuneAppProtectionPolicyRequiredException.getAccountUserId();
        String tenantId = intuneAppProtectionPolicyRequiredException.getTenantId();
        String authorityURL = intuneAppProtectionPolicyRequiredException.getAuthorityURL();
        if (mAMComplianceNotificationCallback != null) {
            MAMNotificationReceiverRegistry mAMNotificationReceiverRegistry = (MAMNotificationReceiverRegistry) xb0.a(MAMNotificationReceiverRegistry.class);
            mAMNotificationReceiverRegistry.registerReceiver(new C0351d(mAMComplianceNotificationCallback, mAMNotificationReceiverRegistry), MAMNotificationType.COMPLIANCE_STATUS);
        }
        ((MAMComplianceManager) xb0.a(MAMComplianceManager.class)).remediateCompliance(accountUpn, accountUserId, tenantId, authorityURL, z);
        return true;
    }
}
