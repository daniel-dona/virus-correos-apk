package com.citrix.worx.sdk;

import android.content.Context;
import android.os.Messenger;
import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: PG */
public class MDXPolicies {
    public static final String TAG = "MDXPolicies";

    public static String getPoliciesXML(Context context) {
        return query(context, MDXConstants.URI_PATH_GET_POLICIES_XML, MDXConstants.COLUMN_POLICIES_XML);
    }

    public static String getPolicyValue(Context context, String str) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mGetPolicyValue;
        if (method != null) {
            try {
                return (String) method.invoke((Object) null, new Object[]{context, str});
            } catch (Exception e) {
                Log.e(TAG, "Failed to get policy value", e);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: android.content.ContentProviderClient} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: android.content.ContentProviderClient} */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
        if (r9 == 0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006e, code lost:
        if (r9 == 0) goto L_0x0071;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String query(android.content.Context r9, java.lang.String r10, java.lang.String r11) {
        /*
            com.citrix.worx.sdk.MDXConstants.initialize((android.content.Context) r9)
            boolean r0 = com.citrix.worx.sdk.MDXConstants.bIsWrapped
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0071
            r0 = 0
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ NullPointerException -> 0x0068, RemoteException -> 0x005f, all -> 0x0052 }
            java.lang.String r2 = "com.citrix.work.MDXProvider"
            android.content.ContentProviderClient r9 = r9.acquireContentProviderClient(r2)     // Catch:{ NullPointerException -> 0x0068, RemoteException -> 0x005f, all -> 0x0052 }
            android.net.Uri$Builder r2 = new android.net.Uri$Builder     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            r2.<init>()     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            java.lang.String r3 = "mdxmdm"
            android.net.Uri$Builder r2 = r2.authority(r3)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            java.lang.String r3 = "citrix"
            android.net.Uri$Builder r2 = r2.scheme(r3)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            android.net.Uri$Builder r10 = r2.path(r10)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            android.net.Uri r4 = r10.build()     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            boolean r10 = r0.moveToFirst()     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            if (r10 == 0) goto L_0x0045
            int r10 = r0.getColumnIndex(r11)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            java.lang.String r10 = r0.getString(r10)     // Catch:{ NullPointerException -> 0x004e, RemoteException -> 0x004c, all -> 0x0050 }
            r1 = r10
        L_0x0045:
            r0.close()
        L_0x0048:
            r9.release()
            goto L_0x0071
        L_0x004c:
            goto L_0x0060
        L_0x004e:
            goto L_0x0069
        L_0x0050:
            r10 = move-exception
            goto L_0x0054
        L_0x0052:
            r10 = move-exception
            r9 = r0
        L_0x0054:
            if (r0 == 0) goto L_0x0059
            r0.close()
        L_0x0059:
            if (r9 == 0) goto L_0x005e
            r9.release()
        L_0x005e:
            throw r10
        L_0x005f:
            r9 = r0
        L_0x0060:
            if (r0 == 0) goto L_0x0065
            r0.close()
        L_0x0065:
            if (r9 == 0) goto L_0x0071
            goto L_0x0048
        L_0x0068:
            r9 = r0
        L_0x0069:
            if (r0 == 0) goto L_0x006e
            r0.close()
        L_0x006e:
            if (r9 == 0) goto L_0x0071
            goto L_0x0048
        L_0x0071:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.worx.sdk.MDXPolicies.query(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String setPolicyChangeMessenger(Context context, String str, Messenger messenger) {
        MDXConstants.initialize(context);
        Method method = MDXConstants.mSetPolicyChangeMessenger;
        if (method != null) {
            try {
                method.invoke((Object) null, new Object[]{str, messenger});
            } catch (Exception e) {
                Log.e(TAG, "Failed to set policy change messenger", e);
            }
        }
        return null;
    }
}
