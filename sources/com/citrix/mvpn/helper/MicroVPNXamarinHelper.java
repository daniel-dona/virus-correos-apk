package com.citrix.mvpn.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebViewClient;
import com.citrix.mvpn.exception.ClientConfigurationException;
import com.citrix.mvpn.p001a.C0025d;
import java.lang.ref.WeakReference;

/* compiled from: PG */
public class MicroVPNXamarinHelper {
    public static WeakReference<Activity> currentActivity;
    public static boolean isInitialized;

    public static WebViewClient enableWebViewClientObjectForNetworkTunnel(Context context, WebViewClient webViewClient) throws ClientConfigurationException {
        C0025d.m63a(context);
        return C0025d.m62a(context, webViewClient);
    }

    public static Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = currentActivity;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    public static MvpnProxy getProxyInfo(Context context) {
        if (context != null) {
            return C0050b.m195l(context);
        }
        throw new IllegalArgumentException("Context cannot be null.");
    }

    public static void init(Context context) {
        if (!isInitialized) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    MicroVPNXamarinHelper.setCurrentActivity(activity);
                }

                public void onActivityDestroyed(Activity activity) {
                    if (MicroVPNXamarinHelper.getCurrentActivity() == activity) {
                        MicroVPNXamarinHelper.setCurrentActivity((Activity) null);
                    }
                }

                public void onActivityPaused(Activity activity) {
                    if (MicroVPNXamarinHelper.getCurrentActivity() == activity) {
                        MicroVPNXamarinHelper.setCurrentActivity((Activity) null);
                    }
                }

                public void onActivityResumed(Activity activity) {
                    MicroVPNXamarinHelper.setCurrentActivity(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityStarted(Activity activity) {
                    MicroVPNXamarinHelper.setCurrentActivity(activity);
                }

                public void onActivityStopped(Activity activity) {
                    if (MicroVPNXamarinHelper.getCurrentActivity() == activity) {
                        MicroVPNXamarinHelper.setCurrentActivity((Activity) null);
                    }
                }
            });
            isInitialized = true;
        }
    }

    public static void setCurrentActivity(Activity activity) {
        if (activity == null) {
            currentActivity = null;
            return;
        }
        WeakReference<Activity> weakReference = currentActivity;
        if (weakReference == null || weakReference.get() != activity) {
            currentActivity = new WeakReference<>(activity);
        }
    }
}
