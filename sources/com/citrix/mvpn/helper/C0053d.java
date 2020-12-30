package com.citrix.mvpn.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.citrix.loggersdk.BuildConfig;

/* renamed from: com.citrix.mvpn.helper.d */
/* compiled from: PG */
public class C0053d {
    /* renamed from: a */
    public static String m202a(Context context) {
        int myPid = Process.myPid();
        String str = BuildConfig.FLAVOR;
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.pid == myPid) {
                str = next.processName;
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m203a(Context context, int i) {
        return (context == null || context.getResources() == null) ? BuildConfig.FLAVOR : context.getResources().getString(i);
    }
}
