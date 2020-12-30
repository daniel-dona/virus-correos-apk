package org.chromium.base;

import FO0;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class SysUtils {

    /* renamed from: a */
    public static Boolean f1474a;

    /* renamed from: b */
    public static Integer f1475b;

    /* renamed from: c */
    public static Boolean f1476c;

    /* renamed from: d */
    public static FO0.b f1477d = new FO0.b("Android.SysUtilsLowEndMatches");

    /* renamed from: a */
    public static int m1452a() {
        if (f1475b == null) {
            f1475b = Integer.valueOf(m1453b());
        }
        return f1475b.intValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r6 = java.lang.Integer.parseInt(r5.group(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r6 > 1024) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        android.util.Log.w("SysUtils", "Invalid /proc/meminfo total size in kB: " + r5.group(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        android.os.StrictMode.setThreadPolicy(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        return r6;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m1453b() {
        /*
            java.lang.String r0 = "SysUtils"
            java.lang.String r1 = "^MemTotal:\\s+([0-9]+) kB$"
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)
            android.os.StrictMode$ThreadPolicy r2 = android.os.StrictMode.allowThreadDiskReads()
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x0071 }
            java.lang.String r4 = "/proc/meminfo"
            r3.<init>(r4)     // Catch:{ Exception -> 0x0071 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x006a }
            r4.<init>(r3)     // Catch:{ all -> 0x006a }
        L_0x0018:
            java.lang.String r5 = r4.readLine()     // Catch:{ all -> 0x0065 }
            if (r5 != 0) goto L_0x0024
            java.lang.String r1 = "/proc/meminfo lacks a MemTotal entry?"
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x0065 }
            goto L_0x0054
        L_0x0024:
            java.util.regex.Matcher r5 = r1.matcher(r5)     // Catch:{ all -> 0x0065 }
            boolean r6 = r5.find()     // Catch:{ all -> 0x0065 }
            if (r6 != 0) goto L_0x002f
            goto L_0x0018
        L_0x002f:
            r1 = 1
            java.lang.String r6 = r5.group(r1)     // Catch:{ all -> 0x0065 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ all -> 0x0065 }
            r7 = 1024(0x400, float:1.435E-42)
            if (r6 > r7) goto L_0x005b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r6.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Invalid /proc/meminfo total size in kB: "
            r6.append(r7)     // Catch:{ all -> 0x0065 }
            java.lang.String r1 = r5.group(r1)     // Catch:{ all -> 0x0065 }
            r6.append(r1)     // Catch:{ all -> 0x0065 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x0065 }
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x0065 }
        L_0x0054:
            r4.close()     // Catch:{ all -> 0x006a }
            r3.close()     // Catch:{ Exception -> 0x0071 }
            goto L_0x0077
        L_0x005b:
            r4.close()     // Catch:{ all -> 0x006a }
            r3.close()     // Catch:{ Exception -> 0x0071 }
            android.os.StrictMode.setThreadPolicy(r2)
            return r6
        L_0x0065:
            r1 = move-exception
            r4.close()     // Catch:{ all -> 0x006a }
            throw r1     // Catch:{ all -> 0x006a }
        L_0x006a:
            r1 = move-exception
            r3.close()     // Catch:{ Exception -> 0x0071 }
            throw r1     // Catch:{ Exception -> 0x0071 }
        L_0x006f:
            r0 = move-exception
            goto L_0x007c
        L_0x0071:
            r1 = move-exception
            java.lang.String r3 = "Cannot get total physical size from /proc/meminfo"
            android.util.Log.w(r0, r3, r1)     // Catch:{ all -> 0x006f }
        L_0x0077:
            android.os.StrictMode.setThreadPolicy(r2)
            r0 = 0
            return r0
        L_0x007c:
            android.os.StrictMode.setThreadPolicy(r2)
            goto L_0x0081
        L_0x0080:
            throw r0
        L_0x0081:
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.SysUtils.m1453b():int");
    }

    @CalledByNative
    public static boolean isCurrentlyLowMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) RN0.a.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    @CalledByNative
    public static boolean isLowEndDevice() {
        boolean z;
        if (f1474a == null) {
            boolean z2 = true;
            if (CommandLine.m1384c().mo7859c("enable-low-end-device-mode")) {
                z = true;
            } else if (CommandLine.m1384c().mo7859c("disable-low-end-device-mode")) {
                z = false;
            } else {
                f1475b = Integer.valueOf(m1453b());
                z = f1475b.intValue() > 0 && (Build.VERSION.SDK_INT < 26 ? f1475b.intValue() / 1024 <= 512 : f1475b.intValue() / 1024 <= 1024);
                Context context = RN0.a;
                boolean isLowRamDevice = context != null ? ((ActivityManager) context.getSystemService("activity")).isLowRamDevice() : false;
                FO0.b bVar = f1477d;
                if (z != isLowRamDevice) {
                    z2 = false;
                }
                bVar.a(z2);
            }
            f1474a = Boolean.valueOf(z);
        }
        return f1474a.booleanValue();
    }

    public static native void nativeLogPageFaultCountToTracing();
}
