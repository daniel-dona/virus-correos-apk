package org.chromium.chrome.browser.feedback;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Pair;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.CollectionUtil;
import org.chromium.base.LocaleUtils;

/* compiled from: PG */
public class SystemInfoFeedbackSource extends UW1<StatFs> {
    public static native int nativeGetAvailableMemoryMB();

    public static native String nativeGetCpuArchitecture();

    public static native String nativeGetGpuModel();

    public static native String nativeGetGpuVendor();

    public static native int nativeGetTotalMemoryMB();

    /* renamed from: a */
    public /* bridge */ /* synthetic */ Object mo8755a(Context context) {
        return mo8757d();
    }

    /* renamed from: b */
    public Map<String, String> mo8756b() {
        HashMap a = CollectionUtil.m1377a((Pair<? extends K, ? extends V>[]) new Pair[]{Pair.create("CPU Architecture", nativeGetCpuArchitecture()), Pair.create("Available Memory (MB)", Integer.toString(nativeGetAvailableMemoryMB())), Pair.create("Total Memory (MB)", Integer.toString(nativeGetTotalMemoryMB())), Pair.create("GPU Vendor", nativeGetGpuVendor()), Pair.create("GPU Model", nativeGetGpuModel()), Pair.create("UI Locale", LocaleUtils.getDefaultLocaleString())});
        StatFs statFs = (StatFs) c();
        if (statFs != null) {
            long blockSizeLong = statFs.getBlockSizeLong();
            a.put("Available Storage (MB)", Long.toString((statFs.getAvailableBlocksLong() * blockSizeLong) / 1048576));
            a.put("Total Storage (MB)", Long.toString((statFs.getBlockCountLong() * blockSizeLong) / 1048576));
        }
        return a;
    }

    /* renamed from: d */
    public StatFs mo8757d() {
        File dataDirectory = Environment.getDataDirectory();
        if (!dataDirectory.exists()) {
            return null;
        }
        return new StatFs(dataDirectory.getPath());
    }
}
