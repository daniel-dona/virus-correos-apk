package org.chromium.chrome.browser.preferences.privacy;

import com.microsoft.bing.commonlib.history.JournalStore;
import com.microsoft.bing.usbsdk.api.BingClientManager;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.PrefServiceBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public final class BrowsingDataBridge {

    /* renamed from: c */
    public static BrowsingDataBridge f2193c;

    /* renamed from: a */
    public OnClearBrowsingDataListener f2194a;

    /* renamed from: b */
    public boolean f2195b;

    /* renamed from: b */
    public static BrowsingDataBridge m2891b() {
        if (f2193c == null) {
            f2193c = new BrowsingDataBridge();
        }
        return f2193c;
    }

    @CalledByNative
    private void browsingDataCleared() {
        OnClearBrowsingDataListener onClearBrowsingDataListener = this.f2194a;
        if (onClearBrowsingDataListener != null) {
            onClearBrowsingDataListener.onBrowsingDataCleared();
            this.f2194a = null;
        }
    }

    /* renamed from: c */
    public static Profile m2892c() {
        return Profile.m2911j().mo9203c();
    }

    private native void nativeClearBrowsingData(Profile profile, int[] iArr, int i, String[] strArr, int[] iArr2, String[] strArr2, int[] iArr3);

    public static native void nativeFetchImportantSites(Profile profile, ImportantSitesCallback importantSitesCallback);

    public static native int nativeGetMaxImportantSites();

    public static native void nativeMarkOriginAsImportantForTesting(Profile profile, String str);

    private native void nativeRequestInfoAboutOtherFormsOfBrowsingHistory(Profile profile, OtherFormsOfBrowsingHistoryListener otherFormsOfBrowsingHistoryListener);

    /* renamed from: a */
    public void mo9178a(OnClearBrowsingDataListener onClearBrowsingDataListener, int[] iArr, int i, String[] strArr, int[] iArr2, String[] strArr2, int[] iArr3) {
        int[] iArr4 = iArr;
        int i2 = i;
        if (!(iArr4 == null || iArr4.length == 0)) {
            int length = iArr4.length;
            boolean z = false;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if (iArr4[i3] == 0) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                long j = -1;
                if (i2 == 0) {
                    j = 3600000;
                } else if (i2 == 1) {
                    j = 86400000;
                } else if (i2 == 2) {
                    j = 604800000;
                } else if (i2 == 3) {
                    j = 2419200000L;
                }
                JournalStore historyManager = BingClientManager.getInstance().getHistoryManager();
                long currentTimeMillis = System.currentTimeMillis();
                if (j > 0) {
                    long j2 = currentTimeMillis - j;
                    if (historyManager != null && j2 < currentTimeMillis) {
                        historyManager.remove(j2, currentTimeMillis);
                    }
                } else if (historyManager != null) {
                    historyManager.removeAll();
                }
            }
        }
        ThreadUtils.m1462c();
        this.f2194a = onClearBrowsingDataListener;
        nativeClearBrowsingData(m2892c(), iArr, i, strArr, iArr2, strArr2, iArr3);
    }

    /* renamed from: a */
    public boolean mo9182a() {
        if (ThreadUtils.m1467g()) {
            this.f2195b = PrefServiceBridge.m2758o0().mo9073a(8);
        }
        return this.f2195b;
    }

    /* renamed from: a */
    public void mo9181a(boolean z) {
        ThreadUtils.m1462c();
        PrefServiceBridge.m2758o0().mo9068a(8, z);
        this.f2195b = z;
    }

    /* renamed from: a */
    public void mo9177a(OnClearBrowsingDataListener onClearBrowsingDataListener, int[] iArr, int i) {
        ThreadUtils.m1462c();
        mo9178a(onClearBrowsingDataListener, iArr, i, new String[0], new int[0], new String[0], new int[0]);
    }

    /* renamed from: a */
    public void mo9180a(Profile profile, OnClearBrowsingDataListener onClearBrowsingDataListener, int[] iArr, int i) {
        ThreadUtils.m1462c();
        this.f2194a = onClearBrowsingDataListener;
        nativeClearBrowsingData(profile, iArr, i, new String[0], new int[0], new String[0], new int[0]);
    }

    /* renamed from: a */
    public void mo9179a(OtherFormsOfBrowsingHistoryListener otherFormsOfBrowsingHistoryListener) {
        ThreadUtils.m1462c();
        nativeRequestInfoAboutOtherFormsOfBrowsingHistory(m2892c(), otherFormsOfBrowsingHistoryListener);
    }
}
