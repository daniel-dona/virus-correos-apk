package com.citrix.mdx.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: PG */
public class MDXDictionary {
    public static final String CTX_MANAGED_APPLICATION_CLASS_NAME = "com.citrix.MAM.Android.ManagedApp.CtxManagedApplication";
    public static final int DICTIONARY_CHANGED = 65537;
    public static final int DICTIONARY_CREATED = 65536;
    public static final int DICTIONARY_DELETED = 65538;
    public static final long INVALID_SEQUENCE = -1;
    public static final String KEY_AAD_TID = "aadtid";
    public static final String KEY_AAD_UPN = "aadupn";
    public static final String KEY_CUSTOMER_ID = "customerId";
    public static final String KEY_DEVICE_ID = "deviceId";
    public static final String KEY_ENROLLMENT_USERNAME = "EnrollmentUserName";
    public static final String KEY_MDXCREDENTIALSDICTIONARY_GATEWAYCERT = "gatewayCertP12";
    public static final String KEY_MDXCREDENTIALSDICTIONARY_GATEWAYCERT_PASSWORD = "gatewayCertPassword";
    public static final String KEY_MDXCREDENTIALSDICTIONARY_PASSWORD = "password";
    public static final String KEY_MDXUSERDICTIONARY_DISPLAY_NAME = "displayname";
    public static final String KEY_MDXUSERDICTIONARY_MAIL = "mail";
    public static final String KEY_MDXUSERDICTIONARY_SAM_ACCOUNT_NAME = "samaccountname";
    public static final String KEY_MDXUSERDICTIONARY_USER_PRINCIPAL_NAME = "userprincipalname";
    public static final String KEY_NAME = "name";
    public static final String KEY_SHARED_DEVICE_ENABLED = "SharedDeviceEnabled";
    public static final String KEY_USERNAME = "UserName";
    public static final String KEY_USER_ID = "userId";
    public static final String MDX_CERT_STORE_DICTIONARY_NAME = "MDXCertStore";
    public static final String MDX_CREDENTIALS_DICTIONARY_NAME = "MDXCredentialsDictionary";
    public static final String MDX_USER_DICTIONARY_NAME = "MDXUserDictionary";
    public static final String METHOD_DELETE_DICTIONARY = "deleteDictionary";
    public static final String METHOD_EDIT_DICTIONARY = "editDictionary";
    public static final String METHOD_READ_DICTIONARY = "readDictionary";
    public static final String METHOD_SAVE_DICTIONARY = "saveDictionary";
    public static final String METHOD_START_NOTIFY_DICTIONARY = "startNotifyDictionary";
    public static final String METHOD_STOP_NOTIFY_DICTIONARY = "stopNotifyDictionary";
    public static final String TAG = "MDXDictionary";
    public static final long WHAT_DICTIONARY_UPDATED = 65536;
    public static Class<?> cCtxManagedApplication;
    public static Method mDeleteDictionary;
    public static Method mEditDictionary;
    public static boolean mIsInitialized;
    public static Method mReadDictionary;
    public static Method mSaveDictionary;
    public static Method mStartNotificationDictionary;
    public static Method mStopNotificationDictionary;
    public Bundle bundle;
    public String name;
    public long sequence;

    public MDXDictionary(MDXDictionary mDXDictionary) {
        this.name = new String(mDXDictionary.name);
        this.bundle = new Bundle(mDXDictionary.bundle);
        this.sequence = mDXDictionary.sequence;
    }

    public MDXDictionary(String str, Bundle bundle2) {
        this(str, bundle2, -1);
    }

    public MDXDictionary(String str, Bundle bundle2, long j) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        } else if (bundle2 == null) {
            throw new IllegalArgumentException("bundle cannot be null.");
        } else if (j >= -1) {
            this.name = str;
            this.bundle = bundle2;
            this.sequence = j;
        } else {
            throw new IllegalArgumentException("sequence number is bad.");
        }
    }

    public static boolean append(Context context, MDXDictionary mDXDictionary) {
        String str;
        initialize(context);
        Method method = mEditDictionary;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, mDXDictionary})).booleanValue();
            } catch (IllegalArgumentException e) {
                e = e;
                str = "Append dictionary: Illegal argument";
                Log.e(TAG, str, e);
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                str = "Append Dictionary: Illegal access";
                Log.e(TAG, str, e);
                return false;
            } catch (InvocationTargetException e3) {
                e = e3;
                str = "Append dictionary: Invalid target";
                Log.e(TAG, str, e);
                return false;
            } catch (Exception e4) {
                e = e4;
                str = "Exception caught when appending dictionary";
                Log.e(TAG, str, e);
                return false;
            }
        }
        return false;
    }

    public static MDXDictionary create(Context context, String str) {
        MDXDictionary find = find(context, str);
        if (find != null) {
            return find;
        }
        Log.i(TAG, "Existing dictionary was not found so creating a new dictionary.");
        return new MDXDictionary(str, new Bundle());
    }

    public static boolean delete(Context context, String str) {
        String str2;
        initialize(context);
        Method method = mDeleteDictionary;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, str})).booleanValue();
            } catch (IllegalArgumentException e) {
                e = e;
                str2 = "Delete dictionary: Illegal argument";
                Log.e(TAG, str2, e);
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                str2 = "Delete dictionary: Illegal access";
                Log.e(TAG, str2, e);
                return false;
            } catch (InvocationTargetException e3) {
                e = e3;
                str2 = "Delete dictionary: Invalid target";
                Log.e(TAG, str2, e);
                return false;
            }
        }
        return false;
    }

    public static MDXDictionary find(Context context, String str) {
        String str2;
        initialize(context);
        Method method = mReadDictionary;
        if (method != null) {
            try {
                return (MDXDictionary) method.invoke((Object) null, new Object[]{context, str});
            } catch (IllegalArgumentException e) {
                e = e;
                str2 = "Read dictionary: Illegal argument";
                Log.e(TAG, str2, e);
                return null;
            } catch (IllegalAccessException e2) {
                e = e2;
                str2 = "Read dictionary: Illegal access";
                Log.e(TAG, str2, e);
                return null;
            } catch (InvocationTargetException e3) {
                e = e3;
                str2 = "Read dictionary: Invalid target";
                Log.e(TAG, str2, e);
                return null;
            }
        }
        return null;
    }

    public static MDXDictionary getDictionary(Context context, Message message) {
        if (message != null) {
            String string = message.getData().getString("name");
            if (!TextUtils.isEmpty(string)) {
                return find(context, string);
            }
        }
        return null;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x008d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x00c2 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x00c2=Splitter:B:17:0x00c2, B:11:0x008d=Splitter:B:11:0x008d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void initialize(android.content.Context r9) {
        /*
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.Class<com.citrix.mdx.common.MDXDictionary> r1 = com.citrix.mdx.common.MDXDictionary.class
            monitor-enter(r1)
            boolean r2 = mIsInitialized     // Catch:{ all -> 0x00df }
            if (r2 != 0) goto L_0x00dd
            if (r9 == 0) goto L_0x00dd
            r2 = 1
            mIsInitialized = r2     // Catch:{ all -> 0x00df }
            java.lang.ClassLoader r3 = r9.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "com.citrix.MAM.Android.ManagedApp.CtxManagedApplication"
            java.lang.Class r3 = r3.loadClass(r4)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            cCtxManagedApplication = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "readDictionary"
            r5 = 2
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r8 = 0
            r6[r8] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r6[r2] = r0     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mReadDictionary = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<?> r3 = cCtxManagedApplication     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "saveDictionary"
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r8] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r6[r2] = r1     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mSaveDictionary = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<?> r3 = cCtxManagedApplication     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "editDictionary"
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r8] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r6[r2] = r1     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mEditDictionary = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<?> r3 = cCtxManagedApplication     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "deleteDictionary"
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r8] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r6[r2] = r0     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mDeleteDictionary = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<?> r3 = cCtxManagedApplication     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "startNotifyDictionary"
            r6 = 3
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r8] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r6[r2] = r0     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.os.Messenger> r7 = android.os.Messenger.class
            r6[r5] = r7     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mStartNotificationDictionary = r3     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<?> r3 = cCtxManagedApplication     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.String r4 = "stopNotifyDictionary"
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.Class<android.content.Context> r6 = android.content.Context.class
            r5[r8] = r6     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            r5[r2] = r0     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            java.lang.reflect.Method r0 = r3.getDeclaredMethod(r4, r5)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            mStopNotificationDictionary = r0     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00a7, IllegalArgumentException -> 0x008d }
            monitor-exit(r1)
            return
        L_0x008d:
            java.lang.String r0 = r1.getName()     // Catch:{ all -> 0x00df }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00df }
            java.lang.String r3 = "Corrupted Wrapped SDK Application = "
            r2.<init>(r3)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r9.getPackageName()     // Catch:{ all -> 0x00df }
            r2.append(r9)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x00df }
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x00df }
            goto L_0x00dd
        L_0x00a7:
            java.lang.String r0 = r1.getName()     // Catch:{ all -> 0x00df }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00df }
            java.lang.String r3 = "Corrupted Wrapped SDK Application = "
            r2.<init>(r3)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r9.getPackageName()     // Catch:{ all -> 0x00df }
            r2.append(r9)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x00df }
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x00df }
            monitor-exit(r1)
            return
        L_0x00c2:
            java.lang.String r0 = r1.getName()     // Catch:{ all -> 0x00df }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00df }
            java.lang.String r3 = "Unwrapped SDK Application = "
            r2.<init>(r3)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r9.getPackageName()     // Catch:{ all -> 0x00df }
            r2.append(r9)     // Catch:{ all -> 0x00df }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x00df }
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x00df }
            monitor-exit(r1)
            return
        L_0x00dd:
            monitor-exit(r1)
            return
        L_0x00df:
            r9 = move-exception
            monitor-exit(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mdx.common.MDXDictionary.initialize(android.content.Context):void");
    }

    public static boolean save(Context context, MDXDictionary mDXDictionary) {
        String str;
        initialize(context);
        Method method = mSaveDictionary;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, mDXDictionary})).booleanValue();
            } catch (IllegalArgumentException e) {
                e = e;
                str = "Save dictionary: Illegal argument";
                Log.e(TAG, str, e);
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                str = "Save dictionary: Illegal access";
                Log.e(TAG, str, e);
                return false;
            } catch (InvocationTargetException e3) {
                e = e3;
                str = "Save dictionary: Invalid target";
                Log.e(TAG, str, e);
                return false;
            }
        }
        return false;
    }

    public static boolean startNotification(Context context, String str, Messenger messenger) {
        String str2;
        initialize(context);
        Method method = mStartNotificationDictionary;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, str, messenger})).booleanValue();
            } catch (IllegalArgumentException e) {
                e = e;
                str2 = "Start dictionary notification: Illegal argument";
                Log.e(TAG, str2, e);
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                str2 = "Start dictionary notification: Illegal access";
                Log.e(TAG, str2, e);
                return false;
            } catch (InvocationTargetException e3) {
                e = e3;
                str2 = "Start dictionary notification: Invalid target";
                Log.e(TAG, str2, e);
                return false;
            }
        }
        return false;
    }

    public static boolean stopNotification(Context context, String str) {
        String str2;
        initialize(context);
        Method method = mStopNotificationDictionary;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{context, str})).booleanValue();
            } catch (IllegalArgumentException e) {
                e = e;
                str2 = "Stop dictionary notification: Illegal argument";
                Log.e(TAG, str2, e);
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                str2 = "Stop dictionary notification: Illegal access";
                Log.e(TAG, str2, e);
                return false;
            } catch (InvocationTargetException e3) {
                e = e3;
                str2 = "Stop dictionary notification: Invalid target";
                Log.e(TAG, str2, e);
                return false;
            }
        }
        return false;
    }

    public boolean append(Context context) {
        boolean z;
        if (this.name == null || this.bundle == null) {
            Log.e(TAG, "Bad argument to append().");
            z = false;
        } else {
            z = append(context, this);
        }
        if (z) {
            Log.i(TAG, "Dictionary appended successfully.");
        }
        return z;
    }

    public boolean delete(Context context) {
        boolean delete = delete(context, this.name);
        if (delete) {
            Log.i(TAG, "Dictionary was successfully deleted.");
            this.sequence = -1;
        }
        return delete;
    }

    public boolean isNew() {
        return this.sequence == -1;
    }

    public boolean save(Context context) {
        boolean z;
        if (this.name == null || this.bundle == null) {
            Log.e(TAG, "Bad argument to save().");
            z = false;
        } else {
            z = save(context, this);
        }
        if (z) {
            Log.i(TAG, "Dictionary saved successfully.");
        }
        return z;
    }
}
