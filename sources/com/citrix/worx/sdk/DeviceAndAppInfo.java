package com.citrix.worx.sdk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.util.Arrays;
import java.util.Map;

/* compiled from: PG */
public class DeviceAndAppInfo {
    public static final String CTXLOG_DEVICE_AND_APP_INFO_PREF = "CTXLOG_DEVICE_AND_APP_INFO_PREF";
    public static final String DEVICE_AND_APP_INFO_FILE_NAME = "Device_And_AppInfo.txt";
    public static final String TAG = "com.citrix.worx.sdk.DeviceAndAppInfo";
    public static boolean bLargeMemory;
    public static boolean bMt;
    public static boolean bMtDistinct;
    public static int largeMemoryClass;
    public static SharedPreferences m_deviceAndAppInfoPrefs;
    public static int memoryClass;
    public static String versionName;

    public static void collectDeviceAndAppInfo() {
        Map<String, ?> all;
        StringBuilder sb = new StringBuilder();
        putDeviceInfoToFile(sb);
        SharedPreferences sharedPreferences = m_deviceAndAppInfoPrefs;
        if (!(sharedPreferences == null || (all = sharedPreferences.getAll()) == null || all.size() <= 0)) {
            for (String next : all.keySet()) {
                if (all.get(next) != null) {
                    StringBuilder c = Eo.c(next, "=");
                    c.append(all.get(next).toString());
                    c.append(AbstractAccountCredentialCache.NEW_LINE);
                    sb.append(c.toString());
                } else {
                    sb.append(next + "=\n");
                }
            }
        }
        CtxLog.addToSupportBundle(DEVICE_AND_APP_INFO_FILE_NAME, sb.toString().getBytes());
    }

    @SuppressLint({"InlinedApi"})
    public static void getCpuString(StringBuilder sb) {
        if (Build.VERSION.SDK_INT >= 21) {
            sb.append("\nBuild.SUPPORTED_ABIS=");
            sb.append(Arrays.asList(Build.SUPPORTED_ABIS).toString());
            return;
        }
        sb.append("\nBuild.CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append("\nBuild.CPU_ABI2=");
        sb.append(Build.CPU_ABI2);
    }

    public static void initialize(Context context) {
        m_deviceAndAppInfoPrefs = context.getSharedPreferences(CTXLOG_DEVICE_AND_APP_INFO_PREF, 0);
        PackageManager packageManager = context.getPackageManager();
        bMt = packageManager.hasSystemFeature("android.hardware.touchscreen.multitouch");
        bMtDistinct = packageManager.hasSystemFeature("android.hardware.touchscreen.multitouch.distinct");
        try {
            versionName = packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            versionName = "Package Not found";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        memoryClass = activityManager.getMemoryClass();
        bLargeMemory = true;
        largeMemoryClass = activityManager.getLargeMemoryClass();
    }

    public static void putDeviceInfoToFile(StringBuilder sb) {
        String str;
        try {
            sb.append("\nAbout my device\n");
            sb.append("\nBuild.BOARD=");
            sb.append(Build.BOARD);
            sb.append("\nBuild.BRAND=");
            sb.append(Build.BRAND);
            getCpuString(sb);
            sb.append("\nBuild.DEVICE=");
            sb.append(Build.DEVICE);
            sb.append("\nBuild.DISPLAY=");
            sb.append(Build.DISPLAY);
            sb.append("\nBuild.FINGERPRINT=");
            sb.append(Build.FINGERPRINT);
            sb.append("\nBuild.HARDWARE=");
            sb.append(Build.HARDWARE);
            sb.append("\nBuild.ID=");
            sb.append(Build.ID);
            sb.append("\nBuild.MANUFACTURER=");
            sb.append(Build.MANUFACTURER);
            sb.append("\nBuild.MODEL=");
            sb.append(Build.MODEL);
            sb.append("\nBuild.PRODUCT=");
            sb.append(Build.PRODUCT);
            sb.append("\nBuild.TYPE=");
            sb.append(Build.TYPE);
            sb.append("\n\nAndroid Operating System Version Information \n");
            sb.append("\nVersion.INCREMENTAL=");
            sb.append(Build.VERSION.INCREMENTAL);
            sb.append("\nVersion.RELEASE=");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\nVersion.SDK=");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("\n\nDevice Memory Information\n");
            sb.append("\nMemory class=");
            sb.append(memoryClass);
            sb.append("\nLarge memory requested=");
            String str2 = "true";
            sb.append(bLargeMemory ? str2 : "false");
            if (bLargeMemory) {
                sb.append("\nMemory class=");
                sb.append(largeMemoryClass);
            }
            sb.append("\n\nOther Android Features\n");
            sb.append("\nFEATURE_TOUCHSCREEN_MULTITOUCH=");
            if (bMt) {
                str = str2;
            } else {
                str = "false";
            }
            sb.append(str);
            sb.append("\nFEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT=");
            if (!bMtDistinct) {
                str2 = "false";
            }
            sb.append(str2);
            sb.append("\n\nApplication Info\n");
            sb.append(AbstractAccountCredentialCache.NEW_LINE + CtxLog.appName);
            sb.append(" Version=" + versionName);
            sb.append("\nLoggerSDK Version=");
            sb.append(CtxLog.getVersion());
            sb.append("\n\n");
        } catch (Exception e) {
            CtxLog.Error(CtxLog.TAG, "Error in writing device info to file", (Throwable) e);
        }
    }

    public static void setAttribute(String str, String str2) {
        if (m_deviceAndAppInfoPrefs == null) {
            return;
        }
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            CtxLog.Warning(TAG, "setAttribute: Null or empty key supplied");
        } else {
            m_deviceAndAppInfoPrefs.edit().putString(str, str2).commit();
        }
    }
}
