package org.chromium.chrome.browser.sync;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.authentication.AuthenticationMode;
import com.microsoft.ruby.anaheim.AnaheimUtils;
import com.microsoft.ruby.telemetry.TelemetryConstants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.dual_identity.DualIdentityManager;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public class ProfileSyncService {

    /* renamed from: j */
    public static final int[] f2207j = {6, 2, 4, 3, 45, 10};

    /* renamed from: a */
    public final List<SyncStateChangedListener> f2208a = new CopyOnWriteArrayList();

    /* renamed from: b */
    public final List<SyncResetListener> f2209b = new CopyOnWriteArrayList();

    /* renamed from: c */
    public long f2210c;

    /* renamed from: d */
    public d f2211d;

    /* renamed from: e */
    public int f2212e;

    /* renamed from: f */
    public AnaheimSyncStatus f2213f = AnaheimSyncStatus.NONE;

    /* renamed from: g */
    public String f2214g;

    /* renamed from: h */
    public String f2215h;

    /* renamed from: i */
    public final AuthenticationMode f2216i;

    /* compiled from: PG */
    public interface SyncResetListener {
        void clearPendingReceived();

        void disableSyncOnClient();
    }

    /* compiled from: PG */
    public interface SyncStateChangedListener {
        void syncStateChanged();
    }

    /* renamed from: org.chromium.chrome.browser.sync.ProfileSyncService$f */
    /* compiled from: PG */
    public final class C0429f {

        /* renamed from: a */
        public boolean f2217a;

        public /* synthetic */ C0429f(a aVar) {
            ThreadUtils.m1462c();
            int i = ProfileSyncService.this.f2212e + 1;
            ProfileSyncService.this.f2212e = i;
            if (i == 1) {
                ProfileSyncService.this.nativeSetSetupInProgress(ProfileSyncService.this.f2210c, true);
            }
        }

        /* renamed from: a */
        public void mo9289a() {
            ThreadUtils.m1462c();
            if (!this.f2217a) {
                this.f2217a = true;
                ProfileSyncService profileSyncService = ProfileSyncService.this;
                int i = profileSyncService.f2212e - 1;
                profileSyncService.f2212e = i;
                if (i == 0) {
                    profileSyncService.nativeSetSetupInProgress(profileSyncService.f2210c, false);
                    ProfileSyncService.this.mo9236J();
                }
            }
        }
    }

    public ProfileSyncService(AuthenticationMode authenticationMode) {
        this.f2216i = authenticationMode;
        DualIdentityManager.m2200a(authenticationMode, (DualIdentityManager.IGetProfileCallback) new a(this));
    }

    /* renamed from: M */
    public static ProfileSyncService m2940M() {
        ThreadUtils.m1462c();
        if (MicrosoftSigninManager.C0424c.f2104a.mo8866A()) {
            return c.a;
        }
        return e.a;
    }

    @CalledByNative
    public static long getProfileSyncServiceAndroid() {
        return m2940M().f2210c;
    }

    private native boolean nativeCanSyncFeatureStart(long j);

    private native void nativeEnableEncryptEverything(long j);

    private native void nativeFlushDirectory(long j);

    private native int[] nativeGetActiveDataTypes(long j);

    private native void nativeGetAllNodes(long j, GetAllNodesCallback getAllNodesCallback);

    private native int nativeGetAuthError(long j);

    private native int[] nativeGetChosenDataTypes(long j);

    private native String nativeGetCurrentSignedInAccountText(long j);

    private native long nativeGetExplicitPassphraseTime(long j);

    private native long nativeGetLastSyncedTime(long j);

    private native int nativeGetNumberOfSyncedDevices(long j);

    private native int nativeGetPassphraseType(long j);

    private native int[] nativeGetPreferredDataTypes(long j);

    private native String nativeGetSyncAboutInfo(long j);

    private native String nativeGetSyncClientId(long j);

    private native String nativeGetSyncEnterCustomPassphraseBodyText(long j);

    private native String nativeGetSyncEnterCustomPassphraseBodyWithDateText(long j);

    private native String nativeGetSyncEnterGooglePassphraseBodyWithDateText(long j);

    private native boolean nativeHasExplicitPassphraseTime(long j);

    private native boolean nativeHasKeepEverythingSynced(long j);

    private native boolean nativeHasUnrecoverableError(long j);

    private native long nativeInit(Profile profile);

    private native boolean nativeIsEncryptEverythingAllowed(long j);

    private native boolean nativeIsEncryptEverythingEnabled(long j);

    private native boolean nativeIsEngineInitialized(long j);

    private native boolean nativeIsFirstSetupComplete(long j);

    private native boolean nativeIsPassphrasePrompted(long j);

    private native boolean nativeIsPassphraseRequiredForDecryption(long j);

    private native boolean nativeIsSyncActive(long j);

    private native boolean nativeIsSyncDisabledByEnterprisePolicy(long j);

    private native boolean nativeIsSyncRequested(long j);

    private native boolean nativeIsUsingSecondaryPassphrase(long j);

    private native void nativeOverrideNetworkResourcesForTest(long j, long j2);

    private native void nativeRequestStart(long j);

    private native void nativeRequestStop(long j);

    private native boolean nativeRequiresClientUpgrade(long j);

    private native void nativeSetChosenDataTypes(long j, boolean z, int[] iArr);

    private native boolean nativeSetDecryptionPassphrase(long j, String str);

    private native void nativeSetEncryptionPassphrase(long j, String str);

    private native void nativeSetFirstSetupComplete(long j);

    private native void nativeSetPassphrasePrompted(long j, boolean z);

    /* access modifiers changed from: private */
    public native void nativeSetSetupInProgress(long j, boolean z);

    private native void nativeSetSyncAllowedByPlatform(long j, boolean z);

    private native void nativeSetSyncSessionsId(long j, String str);

    private native void nativeTriggerRefresh(long j, int[] iArr);

    @CalledByNative
    public static void onGetAllNodesResult(GetAllNodesCallback getAllNodesCallback, String str) {
        getAllNodesCallback.a(str);
    }

    /* renamed from: A */
    public boolean mo9227A() {
        return this.f2210c != 0;
    }

    /* renamed from: B */
    public boolean mo9228B() {
        return nativeIsPassphrasePrompted(this.f2210c);
    }

    /* renamed from: C */
    public boolean mo9229C() {
        return nativeIsPassphraseRequiredForDecryption(this.f2210c);
    }

    /* renamed from: D */
    public boolean mo9230D() {
        return nativeIsSyncActive(this.f2210c);
    }

    /* renamed from: E */
    public boolean mo9231E() {
        return nativeIsSyncRequested(this.f2210c);
    }

    /* renamed from: F */
    public boolean mo9232F() {
        return nativeIsUsingSecondaryPassphrase(this.f2210c);
    }

    /* renamed from: G */
    public void mo9233G() {
        if (this.f2216i == AuthenticationMode.AAD) {
            js0.a();
        }
        nativeRequestStart(this.f2210c);
    }

    /* renamed from: H */
    public void mo9234H() {
        this.f2213f = AnaheimSyncStatus.NONE;
        nativeRequestStop(this.f2210c);
    }

    /* renamed from: I */
    public boolean mo9235I() {
        return nativeRequiresClientUpgrade(this.f2210c);
    }

    /* renamed from: J */
    public void mo9236J() {
        nativeSetFirstSetupComplete(this.f2210c);
    }

    /* renamed from: K */
    public void mo9237K() {
        ThreadUtils.m1462c();
        G82 g82 = (G82) q92.a.get(this.f2216i);
        for (Integer intValue : g82.a.keySet()) {
            int intValue2 = intValue.intValue();
            q92.b(g82.d, intValue2, g82.a(intValue2) && QN0.a.getStringSet(g82.d == AuthenticationMode.AAD ? "ANAHEIM_SYNC_USER_CHOOSE_MODEL_TYPES_AAD" : "ANAHEIM_SYNC_USER_CHOOSE_MODEL_TYPES", g82.b()).contains(g82.a.get(Integer.valueOf(intValue2))));
        }
    }

    /* renamed from: L */
    public void mo9238L() {
        if (mo9284y()) {
            this.f2213f = AnaheimSyncStatus.SYNCING;
        }
        mo9240a(mo9257e());
    }

    /* renamed from: b */
    public void mo9249b(String str) {
        nativeSetEncryptionPassphrase(this.f2210c, str);
    }

    /* renamed from: c */
    public void mo9253c(String str) {
        ThreadUtils.m1462c();
        nativeSetSyncSessionsId(this.f2210c, str);
    }

    @CalledByNative
    public void clearPendingReceived() {
        for (SyncResetListener clearPendingReceived : this.f2209b) {
            clearPendingReceived.clearPendingReceived();
        }
    }

    /* renamed from: d */
    public int mo9255d() {
        int nativeGetAuthError = nativeGetAuthError(this.f2210c);
        if (nativeGetAuthError >= 0 && nativeGetAuthError < 14) {
            return nativeGetAuthError;
        }
        throw new IllegalArgumentException(Eo.b("No state for code: ", nativeGetAuthError));
    }

    @CalledByNative
    public void disableSyncOnClient() {
        for (SyncResetListener disableSyncOnClient : this.f2209b) {
            disableSyncOnClient.disableSyncOnClient();
        }
    }

    /* renamed from: e */
    public Set<Integer> mo9257e() {
        return m2941a(nativeGetChosenDataTypes(this.f2210c));
    }

    /* renamed from: f */
    public String mo9258f() {
        return this.f2215h;
    }

    /* renamed from: g */
    public String mo9259g() {
        return nativeGetCurrentSignedInAccountText(this.f2210c);
    }

    @CalledByNative
    public int[] getBlockedModelTypes() {
        return m2946b((Set<Integer>) ((G82) q92.a.get(this.f2216i)).a());
    }

    /* renamed from: h */
    public long mo9261h() {
        return nativeGetExplicitPassphraseTime(this.f2210c);
    }

    /* renamed from: i */
    public String mo9262i() {
        return this.f2214g;
    }

    @CalledByNative
    public boolean isAnaheimSyncEnabled() {
        ThreadUtils.m1462c();
        if (this.f2216i == AuthenticationMode.MSA && q92.l()) {
            return true;
        }
        if (this.f2216i == AuthenticationMode.AAD) {
            if (q92.d() && !q92.f() && q92.e()) {
                return true;
            }
        }
        return false;
    }

    @CalledByNative
    public boolean isMasterSyncEnabled() {
        ThreadUtils.m1462c();
        Hu2 hu2 = this.f2211d;
        if (hu2 == null) {
            return true;
        }
        return hu2.a();
    }

    /* renamed from: j */
    public long mo9265j() {
        return nativeGetLastSyncedTime(this.f2210c);
    }

    /* renamed from: k */
    public int mo9266k() {
        int nativeGetPassphraseType = nativeGetPassphraseType(this.f2210c);
        if (nativeGetPassphraseType >= 0 && nativeGetPassphraseType < 4) {
            return nativeGetPassphraseType;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: l */
    public Set<Integer> mo9267l() {
        return m2941a(nativeGetPreferredDataTypes(this.f2210c));
    }

    /* renamed from: m */
    public C0429f mo9268m() {
        return new C0429f((a) null);
    }

    /* renamed from: n */
    public String mo9269n() {
        return nativeGetSyncAboutInfo(this.f2210c);
    }

    /* renamed from: o */
    public String mo9270o() {
        return nativeGetSyncClientId(this.f2210c);
    }

    /* renamed from: p */
    public String mo9271p() {
        return nativeGetSyncEnterCustomPassphraseBodyText(this.f2210c);
    }

    /* renamed from: q */
    public String mo9272q() {
        return nativeGetSyncEnterCustomPassphraseBodyWithDateText(this.f2210c);
    }

    /* renamed from: r */
    public String mo9273r() {
        return nativeGetSyncEnterGooglePassphraseBodyWithDateText(this.f2210c);
    }

    /* renamed from: s */
    public AnaheimSyncStatus mo9274s() {
        return this.f2213f;
    }

    @CalledByNative
    public void syncCycleFail(String str, String str2, String str3) {
        this.f2213f = AnaheimSyncStatus.SYNC_OTHER_ERROR;
        this.f2214g = str2;
        this.f2215h = str3;
        AnaheimUtils.a("syncOtherSyncError", TelemetryConstants.Type.Health, new String[]{"lastGetKeyResult", str, "lastDownloadUpdatesResult", str2, "commitResult", str3});
    }

    @CalledByNative
    public void syncCycleSuccess() {
        this.f2213f = AnaheimSyncStatus.SYNC_SUCCEED;
        AnaheimUtils.a("syncSucceed", TelemetryConstants.Type.Health, new String[0]);
    }

    @CalledByNative
    public void syncEngineInitialized() {
        Zr0.c(this.f2216i);
    }

    @CalledByNative
    public void syncStateChanged() {
        for (SyncStateChangedListener syncStateChanged : this.f2208a) {
            syncStateChanged.syncStateChanged();
        }
        mo9237K();
    }

    /* renamed from: t */
    public boolean mo9279t() {
        return nativeHasExplicitPassphraseTime(this.f2210c);
    }

    /* renamed from: u */
    public boolean mo9280u() {
        return nativeHasKeepEverythingSynced(this.f2210c);
    }

    /* renamed from: v */
    public boolean mo9281v() {
        return nativeHasUnrecoverableError(this.f2210c);
    }

    /* renamed from: w */
    public boolean mo9282w() {
        return nativeIsEncryptEverythingAllowed(this.f2210c);
    }

    /* renamed from: x */
    public boolean mo9283x() {
        return nativeIsEncryptEverythingEnabled(this.f2210c);
    }

    /* renamed from: y */
    public boolean mo9284y() {
        return nativeIsEngineInitialized(this.f2210c);
    }

    /* renamed from: z */
    public boolean mo9285z() {
        return nativeIsFirstSetupComplete(this.f2210c);
    }

    /* renamed from: a */
    public static ProfileSyncService m2942a(AuthenticationMode authenticationMode) {
        ThreadUtils.m1462c();
        if (authenticationMode == AuthenticationMode.AAD && !MicrosoftSigninManager.C0424c.f2104a.mo8930w()) {
            return null;
        }
        if (AuthenticationMode.AAD == authenticationMode) {
            return c.a;
        }
        return e.a;
    }

    /* renamed from: b */
    public static int[] m2946b(Set<Integer> set) {
        int[] iArr = new int[set.size()];
        int i = 0;
        for (Integer intValue : set) {
            iArr[i] = intValue.intValue();
            i++;
        }
        return iArr;
    }

    /* renamed from: c */
    public Set<Integer> mo9252c() {
        return m2941a(nativeGetActiveDataTypes(this.f2210c));
    }

    /* renamed from: b */
    public void mo9250b(SyncStateChangedListener syncStateChangedListener) {
        ThreadUtils.m1462c();
        this.f2208a.remove(syncStateChangedListener);
    }

    /* renamed from: b */
    public void mo9251b(boolean z) {
        nativeSetSyncAllowedByPlatform(this.f2210c, z);
    }

    /* renamed from: b */
    public void mo9248b() {
        nativeFlushDirectory(this.f2210c);
    }

    /* renamed from: b */
    public static String m2945b(AuthenticationMode authenticationMode) {
        ThreadUtils.m1460b();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
        ThreadUtils.m1461b((Runnable) new b(authenticationMode, arrayBlockingQueue));
        try {
            return (String) arrayBlockingQueue.take();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return BuildConfig.FLAVOR;
        }
    }

    /* renamed from: a */
    public final void mo9241a(Profile profile) {
        ThreadUtils.m1462c();
        this.f2210c = nativeInit(profile);
    }

    /* renamed from: a */
    public void mo9239a() {
        nativeEnableEncryptEverything(this.f2210c);
    }

    /* renamed from: a */
    public boolean mo9247a(String str) {
        return nativeSetDecryptionPassphrase(this.f2210c, str);
    }

    /* renamed from: a */
    public static Set<Integer> m2941a(int[] iArr) {
        HashSet hashSet = new HashSet();
        for (int valueOf : iArr) {
            hashSet.add(Integer.valueOf(valueOf));
        }
        return hashSet;
    }

    /* renamed from: a */
    public void mo9246a(boolean z, Set<Integer> set) {
        int[] b = z ? f2207j : m2946b(set);
        long j = this.f2210c;
        Set<Integer> a = m2941a(b);
        a.removeAll(m2941a(getBlockedModelTypes()));
        nativeSetChosenDataTypes(j, z, m2946b(a));
    }

    /* renamed from: a */
    public void mo9243a(SyncStateChangedListener syncStateChangedListener) {
        ThreadUtils.m1462c();
        this.f2208a.add(syncStateChangedListener);
    }

    /* renamed from: a */
    public void mo9242a(SyncResetListener syncResetListener) {
        ThreadUtils.m1462c();
        this.f2209b.add(syncResetListener);
    }

    /* renamed from: a */
    public void mo9240a(Set<Integer> set) {
        mo9237K();
        Set<Integer> e = mo9257e();
        HashSet hashSet = new HashSet();
        for (Integer next : set) {
            if (e.contains(next)) {
                if (next.intValue() == 45) {
                    next = 13;
                }
                hashSet.add(next);
            }
        }
        if (hashSet.size() > 0) {
            nativeTriggerRefresh(this.f2210c, m2946b((Set<Integer>) hashSet));
        }
    }

    /* renamed from: a */
    public void mo9245a(boolean z) {
        nativeSetPassphrasePrompted(this.f2210c, z);
    }

    /* renamed from: a */
    public void mo9244a(d dVar) {
        ThreadUtils.m1462c();
        this.f2211d = dVar;
    }
}
