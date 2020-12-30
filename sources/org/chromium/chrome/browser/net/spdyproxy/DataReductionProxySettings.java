package org.chromium.chrome.browser.net.spdyproxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class DataReductionProxySettings {

    /* renamed from: c */
    public static DataReductionProxySettings f2113c;

    /* renamed from: a */
    public Callback<List<fn2>> f2114a;

    /* renamed from: b */
    public final long f2115b = nativeInit();

    @CalledByNative
    public static void createDataUseItemAndAddToList(List<fn2> list, String str, long j, long j2) {
        list.add(new fn2(str, j, j2));
    }

    private native void nativeClearDataSavingStatistics(long j, int i);

    private native ContentLengths nativeGetContentLengths(long j);

    private native long[] nativeGetDailyOriginalContentLengths(long j);

    private native long[] nativeGetDailyReceivedContentLengths(long j);

    private native long nativeGetDataReductionLastUpdateTime(long j);

    private native String nativeGetDataReductionProxyPassThroughHeader(long j);

    private native long nativeGetTotalHttpContentLengthSaved(long j);

    private native long nativeInit();

    private native boolean nativeIsDataReductionProxyEnabled(long j);

    private native boolean nativeIsDataReductionProxyFREPromoAllowed(long j);

    private native boolean nativeIsDataReductionProxyManaged(long j);

    private native boolean nativeIsDataReductionProxyPromoAllowed(long j);

    private native boolean nativeIsDataReductionProxyUnreachable(long j);

    private native String nativeMaybeRewriteWebliteUrl(long j, String str);

    private native void nativeQueryDataUsage(long j, List<fn2> list, int i);

    private native void nativeSetDataReductionProxyEnabled(long j, boolean z);

    /* renamed from: o */
    public static DataReductionProxySettings m2648o() {
        ThreadUtils.m1462c();
        if (f2113c == null) {
            f2113c = new DataReductionProxySettings();
        }
        return f2113c;
    }

    /* renamed from: a */
    public void mo8944a(boolean z) {
        if (z && QN0.a.getLong("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", 0) == 0) {
            QN0.a.edit().putLong("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", System.currentTimeMillis()).apply();
        }
        Eo.b(QN0.a, "BANDWIDTH_REDUCTION_PROXY_ENABLED", z);
        nativeSetDataReductionProxyEnabled(this.f2115b, z);
    }

    /* renamed from: b */
    public ContentLengths mo8945b() {
        return nativeGetContentLengths(this.f2115b);
    }

    /* renamed from: c */
    public long mo8946c() {
        return nativeGetDataReductionLastUpdateTime(this.f2115b);
    }

    /* renamed from: d */
    public long mo8947d() {
        return QN0.a.getLong("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", 0);
    }

    /* renamed from: e */
    public String mo8948e() {
        return nativeGetDataReductionProxyPassThroughHeader(this.f2115b);
    }

    /* renamed from: f */
    public long[] mo8949f() {
        return nativeGetDailyOriginalContentLengths(this.f2115b);
    }

    /* renamed from: g */
    public long[] mo8950g() {
        return nativeGetDailyReceivedContentLengths(this.f2115b);
    }

    /* renamed from: h */
    public long mo8951h() {
        return nativeGetTotalHttpContentLengthSaved(this.f2115b);
    }

    /* renamed from: i */
    public boolean mo8952i() {
        return nativeIsDataReductionProxyEnabled(this.f2115b);
    }

    /* renamed from: j */
    public boolean mo8953j() {
        return nativeIsDataReductionProxyManaged(this.f2115b);
    }

    /* renamed from: k */
    public boolean mo8954k() {
        return nativeIsDataReductionProxyPromoAllowed(this.f2115b);
    }

    /* renamed from: l */
    public boolean mo8955l() {
        return nativeIsDataReductionProxyUnreachable(this.f2115b);
    }

    /* renamed from: m */
    public boolean mo8956m() {
        if (mo8952i() && mo8940a() / 1024 >= 100) {
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public Map<String, String> mo8957n() {
        HashMap hashMap = new HashMap();
        hashMap.put("Data Reduction Proxy Enabled", String.valueOf(mo8952i()));
        return hashMap;
    }

    @CalledByNative
    public void onQueryDataUsageComplete(List<fn2> list) {
        Callback<List<fn2>> callback = this.f2114a;
        if (callback != null) {
            callback.onResult(list);
        }
        this.f2114a = null;
    }

    /* renamed from: a */
    public void mo8942a(int i) {
        YL1.a(0);
        QN0.a.edit().putLong("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", System.currentTimeMillis()).apply();
        nativeClearDataSavingStatistics(this.f2115b, i);
    }

    /* renamed from: a */
    public long mo8940a() {
        ContentLengths b = mo8945b();
        return Math.max(b.a() - b.b(), 0);
    }

    /* renamed from: a */
    public String mo8941a(String str) {
        return nativeMaybeRewriteWebliteUrl(this.f2115b, str);
    }

    /* renamed from: a */
    public void mo8943a(int i, Callback<List<fn2>> callback) {
        this.f2114a = callback;
        nativeQueryDataUsage(this.f2115b, new ArrayList(), i);
    }
}
