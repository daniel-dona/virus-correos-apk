package org.chromium.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.google.protobuf.ByteString;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class BuildInfo {

    /* renamed from: m */
    public static PackageInfo f1401m = null;

    /* renamed from: n */
    public static String f1402n = "";

    /* renamed from: a */
    public final String f1403a;

    /* renamed from: b */
    public final long f1404b;

    /* renamed from: c */
    public final String f1405c;

    /* renamed from: d */
    public final long f1406d;

    /* renamed from: e */
    public final String f1407e;

    /* renamed from: f */
    public final String f1408f;

    /* renamed from: g */
    public final String f1409g;

    /* renamed from: h */
    public final String f1410h;

    /* renamed from: i */
    public final String f1411i;

    /* renamed from: j */
    public final String f1412j;

    /* renamed from: k */
    public final String f1413k;

    /* renamed from: l */
    public final String f1414l;

    /* renamed from: org.chromium.base.BuildInfo$b */
    /* compiled from: PG */
    public static class C0359b {

        /* renamed from: a */
        public static BuildInfo f1415a = new BuildInfo((C0358a) null);
    }

    public /* synthetic */ BuildInfo(C0358a aVar) {
        String str;
        Context context = RN0.a;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo b = Vc0.b(packageManager, packageName, 0);
        this.f1404b = m1370a(b);
        PackageInfo packageInfo = null;
        if (f1401m != null) {
            this.f1405c = f1401m.packageName;
            this.f1406d = m1370a(f1401m);
            this.f1407e = m1371a((CharSequence) f1401m.versionName);
            f1401m = null;
        } else {
            this.f1405c = packageName;
            this.f1406d = this.f1404b;
            this.f1407e = m1371a((CharSequence) b.versionName);
        }
        this.f1403a = m1371a(Vc0.a(packageManager, b.applicationInfo));
        this.f1408f = m1371a((CharSequence) Vc0.a().getInstallerPackageName(packageManager, this.f1405c));
        try {
            packageInfo = Vc0.b(packageManager, "com.google.android.gms", 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.f1409g = packageInfo != null ? String.valueOf(m1370a(packageInfo)) : "gms versionCode not available.";
        String str2 = "true";
        try {
            Vc0.b(packageManager, "projekt.substratum", 0);
        } catch (PackageManager.NameNotFoundException unused2) {
            str2 = "false";
        }
        try {
            this.f1413k = str2;
            try {
                str = RN0.a.getString(2131954132);
            } catch (Exception unused3) {
                str = "Not found";
            }
            this.f1414l = str;
            if (Build.VERSION.SDK_INT >= 21) {
                this.f1410h = TextUtils.join(", ", Build.SUPPORTED_ABIS);
            } else {
                this.f1410h = String.format("ABI1: %s, ABI2: %s", new Object[]{Build.CPU_ABI, Build.CPU_ABI2});
            }
            this.f1412j = String.format("@%x_%x", new Object[]{Long.valueOf(this.f1406d), Long.valueOf(b.lastUpdateTime)});
            this.f1411i = Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), ByteString.CONCATENATE_BY_COPY_SIZE));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static String m1371a(CharSequence charSequence) {
        return charSequence == null ? BuildConfig.FLAVOR : charSequence.toString();
    }

    /* renamed from: b */
    public static boolean m1373b() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    @CalledByNative
    public static String[] getAll() {
        BuildInfo buildInfo = C0359b.f1415a;
        String packageName = RN0.a.getPackageName();
        String[] strArr = new String[24];
        strArr[0] = Build.BRAND;
        strArr[1] = Build.DEVICE;
        strArr[2] = Build.ID;
        strArr[3] = Build.MANUFACTURER;
        strArr[4] = Build.MODEL;
        strArr[5] = String.valueOf(Build.VERSION.SDK_INT);
        strArr[6] = Build.TYPE;
        strArr[7] = Build.BOARD;
        strArr[8] = packageName;
        strArr[9] = String.valueOf(buildInfo.f1404b);
        strArr[10] = buildInfo.f1403a;
        strArr[11] = buildInfo.f1405c;
        strArr[12] = String.valueOf(buildInfo.f1406d);
        strArr[13] = buildInfo.f1407e;
        strArr[14] = buildInfo.f1411i;
        strArr[15] = buildInfo.f1409g;
        strArr[16] = buildInfo.f1408f;
        strArr[17] = buildInfo.f1410h;
        strArr[18] = f1402n;
        strArr[19] = buildInfo.f1413k;
        strArr[20] = buildInfo.f1414l;
        strArr[21] = buildInfo.f1412j;
        String str = "1";
        strArr[22] = m1372a() ? str : "0";
        if (!m1373b()) {
            str = "0";
        }
        strArr[23] = str;
        return strArr;
    }

    @SuppressLint({"HardwareIds"})
    @CalledByNative
    public static String getDeviceId() {
        try {
            Context context = RN0.a;
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                return "m:" + connectionInfo.getMacAddress().replace(":", BuildConfig.FLAVOR);
            }
            return "a:" + string;
        } catch (SecurityException unused) {
            return BuildConfig.FLAVOR;
        }
    }

    @CalledByNative
    public static String getDeviceName() {
        String string = Settings.Global.getString(RN0.a.getContentResolver(), "device_name");
        return string == null ? Build.MANUFACTURER : string;
    }

    /* renamed from: a */
    public static long m1370a(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return (long) packageInfo.versionCode;
    }

    /* renamed from: a */
    public static boolean m1372a() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
