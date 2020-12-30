package com.citrix.worx.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Messenger;
import java.lang.reflect.Method;

/* compiled from: PG */
public class MDXConstants {
    public static final String COLUMN_POLICIES_XML = "PoliciesXML";
    public static final String COLUMN_USERNAME = "userName";
    public static final String CONTENT_PROVIDER = "com.citrix.work.MDXProvider";
    public static final String CTX_MANAGED_APPLICATION_CLASS_NAME = "com.citrix.MAM.Android.ManagedApp.CtxManagedApplication";
    public static final String KEY_NEW_VALUE = "newValue";
    public static final String KEY_OLD_VALUE = "oldValue";
    public static final String KEY_POLICY_NAME = "policyName";
    public static final String METHOD_CLEAR_CERT_CACHE = "clearCertCache";
    public static final String METHOD_GET_GENERATED_CUSTOMER_ID = "getGeneratedCustomerId";
    public static final String METHOD_GET_GENERATED_DEVICE_ID = "getGeneratedDeviceId";
    public static final String METHOD_GET_MANAGING_AGENT = "getManagingAgent";
    public static final String METHOD_GET_POLICY_VALUE = "getPolicyValue";
    public static final String METHOD_GET_SFCONNECT_BG = "getShareFileConnectorBackground";
    public static final String METHOD_GET_SFCONNECT_FG = "getShareFileConnectorForeground";
    public static final String METHOD_IS_APP_CERT_AVAILABLE = "isAppCertAvailable";
    public static final String METHOD_IS_MANAGED = "isManaged";
    public static final String METHOD_IS_MDM_ONLY = "isXMMDMOnly";
    public static final String METHOD_IS_SESSION_VALID = "isSessionValid";
    public static final String METHOD_SET_POLICY_CHANGE_MESSENGER = "setPolicyChangeMessenger";
    public static final String METHOD_START_ONLINE_ACTIVITY = "startOnlineActivity";
    public static final String URI_AUTHORITY = "mdxmdm";
    public static final String URI_PATH_GET_POLICIES_XML = "policiesXML";
    public static final String URI_PATH_GET_USERNAME = "getUsername";
    public static final String URI_QUERY_PKGNAME = "pkgName";
    public static final String URI_SCHEME = "citrix";
    public static boolean bInitialized;
    public static boolean bIsWrapped;
    public static Class<?> cCtxManagedApplication;
    public static Method mClearCertCache;
    public static Method mGetGeneratedCustomerId;
    public static Method mGetGeneratedDeviceID;
    public static Method mGetManagingAgent;
    public static Method mGetPolicyValue;
    public static Method mGetShareFileConnectorBackground;
    public static Method mGetShareFileConnectorForeground;
    public static Method mIsAppCertAvailable;
    public static Method mIsMDMOnly;
    public static Method mIsManaged;
    public static Method mIsSessionValid;
    public static Method mSetPolicyChangeMessenger;
    public static Method mStartOnlineActivity;

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void initialize(android.content.Context r4) {
        /*
            java.lang.Class<com.citrix.worx.sdk.MDXConstants> r0 = com.citrix.worx.sdk.MDXConstants.class
            monitor-enter(r0)
            boolean r1 = bInitialized     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x004a
            if (r4 == 0) goto L_0x004a
            r1 = 1
            bInitialized = r1     // Catch:{ all -> 0x004c }
            java.lang.ClassLoader r1 = r4.getClassLoader()     // Catch:{ NoSuchMethodException -> 0x002f, ClassNotFoundException -> 0x0015 }
            initialize((java.lang.ClassLoader) r1)     // Catch:{ NoSuchMethodException -> 0x002f, ClassNotFoundException -> 0x0015 }
            monitor-exit(r0)
            return
        L_0x0015:
            java.lang.String r1 = r0.getName()     // Catch:{ all -> 0x004c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004c }
            java.lang.String r3 = "Unwrapped SDK Application = "
            r2.<init>(r3)     // Catch:{ all -> 0x004c }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x004c }
            r2.append(r4)     // Catch:{ all -> 0x004c }
            java.lang.String r4 = r2.toString()     // Catch:{ all -> 0x004c }
            android.util.Log.e(r1, r4)     // Catch:{ all -> 0x004c }
            goto L_0x004a
        L_0x002f:
            java.lang.String r1 = r0.getName()     // Catch:{ all -> 0x004c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004c }
            java.lang.String r3 = "Corrupted Wrapped SDK Application = "
            r2.<init>(r3)     // Catch:{ all -> 0x004c }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x004c }
            r2.append(r4)     // Catch:{ all -> 0x004c }
            java.lang.String r4 = r2.toString()     // Catch:{ all -> 0x004c }
            android.util.Log.e(r1, r4)     // Catch:{ all -> 0x004c }
            monitor-exit(r0)
            return
        L_0x004a:
            monitor-exit(r0)
            return
        L_0x004c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.worx.sdk.MDXConstants.initialize(android.content.Context):void");
    }

    public static synchronized void initialize(ClassLoader classLoader) {
        Class<String> cls = String.class;
        synchronized (MDXConstants.class) {
            cCtxManagedApplication = classLoader.loadClass("com.citrix.MAM.Android.ManagedApp.CtxManagedApplication");
            bIsWrapped = true;
            mGetPolicyValue = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_POLICY_VALUE, new Class[]{Context.class, cls});
            mIsManaged = cCtxManagedApplication.getDeclaredMethod(METHOD_IS_MANAGED, new Class[0]);
            mSetPolicyChangeMessenger = cCtxManagedApplication.getDeclaredMethod(METHOD_SET_POLICY_CHANGE_MESSENGER, new Class[]{cls, Messenger.class});
            mClearCertCache = cCtxManagedApplication.getDeclaredMethod(METHOD_CLEAR_CERT_CACHE, new Class[]{Context.class, Boolean.TYPE, Boolean.TYPE});
            mIsAppCertAvailable = cCtxManagedApplication.getDeclaredMethod(METHOD_IS_APP_CERT_AVAILABLE, new Class[]{Context.class});
            mIsSessionValid = cCtxManagedApplication.getDeclaredMethod(METHOD_IS_SESSION_VALID, new Class[]{Context.class});
            mStartOnlineActivity = cCtxManagedApplication.getDeclaredMethod(METHOD_START_ONLINE_ACTIVITY, new Class[]{Activity.class});
            mGetGeneratedDeviceID = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_GENERATED_DEVICE_ID, new Class[]{Context.class});
            mGetGeneratedCustomerId = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_GENERATED_CUSTOMER_ID, new Class[0]);
            mGetManagingAgent = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_MANAGING_AGENT, new Class[0]);
            mGetShareFileConnectorBackground = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_SFCONNECT_BG, new Class[]{Context.class});
            mGetShareFileConnectorForeground = cCtxManagedApplication.getDeclaredMethod(METHOD_GET_SFCONNECT_FG, new Class[]{Activity.class, Integer.TYPE});
            mIsMDMOnly = cCtxManagedApplication.getDeclaredMethod(METHOD_IS_MDM_ONLY, new Class[0]);
        }
    }
}
