package com.citrix.worx.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import java.lang.reflect.Method;

/* compiled from: PG */
public class MDXApplication {
    public static final String TAG = "WorxSDK-MDXApplication";
    public static ManagingAgent[] enumValues;

    /* compiled from: PG */
    public enum ManagingAgent {
        NONE,
        PREMIUM,
        XM_MAM,
        INTUNE_MAM
    }

    public static boolean clearCertCache(Context context, boolean z, boolean z2) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mClearCertCache;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, Boolean.valueOf(z), Boolean.valueOf(z2)})).booleanValue();
            } catch (Exception e) {
                Log.e(TAG, "Clearing cert cache failed", e);
            }
        }
        return false;
    }

    public static String getGeneratedCustomerId(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mGetGeneratedCustomerId;
        if (method == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return (String) method.invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            Log.e(TAG, "Getting customer ID failed", e);
            return BuildConfig.FLAVOR;
        }
    }

    public static String getGeneratedDeviceId(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mGetGeneratedDeviceID;
        if (method == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return (String) method.invoke((Object) null, new Object[]{context});
        } catch (Exception e) {
            Log.e(TAG, "Getting device ID failed", e);
            return BuildConfig.FLAVOR;
        }
    }

    public static ManagingAgent getManagingAgent(Context context) {
        MDXConstants.initialize(context);
        ManagingAgent managingAgent = ManagingAgent.NONE;
        Method method = MDXConstants.mGetManagingAgent;
        if (method == null) {
            return managingAgent;
        }
        try {
            Integer num = (Integer) method.invoke((Object) null, new Object[0]);
            if (enumValues == null) {
                enumValues = ManagingAgent.values();
            }
            return enumValues[num.intValue()];
        } catch (Exception e) {
            Log.e(TAG, "Getting managing agent failed", e);
            return managingAgent;
        }
    }

    public static Bundle getShareFileConnectorBackground(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mGetShareFileConnectorBackground;
        if (method != null) {
            try {
                return (Bundle) method.invoke((Object) null, new Object[]{context});
            } catch (Exception e) {
                Log.e(TAG, "getShareFileConnector (bg) failed", e);
            }
        }
        return null;
    }

    public static boolean getShareFileConnectorForeground(Activity activity, int i) {
        MDXConstants.initialize((Context) activity);
        Method method = MDXConstants.mGetShareFileConnectorForeground;
        if (method == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke((Object) null, new Object[]{activity, Integer.valueOf(i)})).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, "getShareFileConnector (fg) failed", e);
            return false;
        }
    }

    public static String getUserName(Context context) {
        return MDXPolicies.query(context, MDXConstants.URI_PATH_GET_USERNAME, MDXConstants.COLUMN_USERNAME);
    }

    public static boolean isAppCertAvailable(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mIsAppCertAvailable;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context})).booleanValue();
            } catch (Exception e) {
                Log.e(TAG, "Checking if app cert was available failed", e);
            }
        }
        return false;
    }

    public static boolean isManaged(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mIsManaged;
        if (method == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke((Object) null, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, "Checking if app is managed failed", e);
            return false;
        }
    }

    public static boolean isSessionValid(Context context) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mIsSessionValid;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context})).booleanValue();
            } catch (Exception e) {
                Log.e(TAG, "Checking if session was valid failed", e);
            }
        }
        return false;
    }

    public static boolean isWrapped(Context context) {
        MDXConstants.initialize(context);
        return MDXConstants.bIsWrapped;
    }

    public static boolean isXMMDMOnly(Context context) {
        MDXConstants.initialize(context);
        Boolean bool = Boolean.FALSE;
        Method method = MDXConstants.mIsMDMOnly;
        if (method != null) {
            try {
                bool = (Boolean) method.invoke((Object) null, new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, "Getting XM server type failed", e);
            }
        }
        return bool.booleanValue();
    }

    public static boolean startOnlineActivity(Activity activity) {
        MDXConstants.initialize((Context) activity);
        Method method = MDXConstants.mStartOnlineActivity;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{activity})).booleanValue();
            } catch (Exception e) {
                Log.e(TAG, "Starting online activity failed", e);
            }
        }
        return false;
    }
}
