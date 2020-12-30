package com.citrix.worx.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import java.util.Map;

/* compiled from: PG */
public class SettingPreferences {
    public static final int DEFAULT_LOG_ENABLE = 1;
    public static final int DEFAULT_LOG_LEVEL = 15;
    public static final int DEFAULT_LOG_TARGET = 3;
    public static final int DEFAULT_MAX_FILE_COUNT = 5;
    public static final int DEFAULT_MAX_FILE_SIZE = 2;
    public static final String KEY_LOG_CLEAR_TIME = "CtxLogClearingTime";
    public static final String KEY_LOG_ENABLE = "CtxLogEnable";
    public static final String KEY_LOG_FILE_COUNT = "CtxLogMaxFileCount";
    public static final String KEY_LOG_FILE_SIZE = "CtxLogMaxFileSize";
    public static final String KEY_LOG_LEVEL = "CtxLogLevel";
    public static final String KEY_LOG_TARGET = "CtxLogTarget";
    public static final String KEY_PERF_LEVEL = "CtxPerfLoggerLevel";
    public static String SHARED_PREF_FOR_SETTINGS = "CtxLogPreference";
    public static String mBroadCastFilterTag = null;
    public static CtxPolicyChangeReceiver mChangeReceiver = null;
    public static long mClearingTime = 0;
    public static Context mContext = null;
    public static int mIsLogEnabled = 1;
    public static int mLogLevel = 15;
    public static int mLogTargets = 3;
    public static int mMaxLogFileCount = 5;
    public static int mMaxLogFileSize = 2;
    public static boolean mPerfLoggerInit;
    public static int mPerfLoggerLevel;
    public static SharedPreferences mSharedPref;

    public static synchronized long GetClearTime() {
        long j;
        synchronized (SettingPreferences.class) {
            j = mClearingTime;
        }
        return j;
    }

    public static String GetReciverFilterTag() {
        return mBroadCastFilterTag;
    }

    public static synchronized void IntializeWithDefault(Context context) {
        synchronized (SettingPreferences.class) {
            mBroadCastFilterTag = context.getPackageName() + ".SettingBroadCast";
            mContext = context;
            RegisterReceiver();
            if (mSharedPref == null) {
                mSharedPref = mContext.getSharedPreferences(SHARED_PREF_FOR_SETTINGS, 4);
            }
            Map<String, ?> all = mSharedPref.getAll();
            if (all.size() > 0) {
                for (Map.Entry next : all.entrySet()) {
                    if (((String) next.getKey()).equals(KEY_LOG_TARGET)) {
                        CtxLog.setTargets(((Integer) next.getValue()).intValue());
                    }
                    if (((String) next.getKey()).equals(KEY_LOG_FILE_COUNT)) {
                        CtxLog.setMaxFileCount(((Integer) next.getValue()).intValue());
                    }
                    if (((String) next.getKey()).equals(KEY_LOG_FILE_SIZE)) {
                        CtxLog.setMaxFileSize(((Integer) next.getValue()).intValue());
                    }
                    if (((String) next.getKey()).equals(KEY_LOG_LEVEL)) {
                        CtxLog.setLevel(((Integer) next.getValue()).intValue());
                    }
                }
            } else {
                SharedPreferences.Editor edit = mSharedPref.edit();
                edit.putInt(KEY_LOG_ENABLE, mIsLogEnabled);
                edit.putInt(KEY_LOG_TARGET, mLogTargets);
                edit.putInt(KEY_LOG_LEVEL, mLogLevel);
                edit.putInt(KEY_LOG_FILE_COUNT, mMaxLogFileCount);
                edit.putInt(KEY_LOG_FILE_SIZE, mMaxLogFileSize);
                if (mPerfLoggerInit) {
                    edit.putInt(KEY_PERF_LEVEL, mPerfLoggerLevel);
                }
                edit.commit();
            }
        }
    }

    public static void RegisterReceiver() {
        IntentFilter intentFilter = new IntentFilter(GetReciverFilterTag());
        mChangeReceiver = new CtxPolicyChangeReceiver();
        mContext.registerReceiver(mChangeReceiver, intentFilter);
    }

    public static void SendUpdateNotifClear() {
        sendUpdateNotif(KEY_LOG_CLEAR_TIME, 1);
    }

    public static synchronized void SendUpdateNotifForEnable(boolean z) {
        synchronized (SettingPreferences.class) {
            int i = 1;
            mIsLogEnabled = z ? 1 : 0;
            if (!z) {
                i = 0;
            }
            UpdateWith(KEY_LOG_ENABLE, i);
        }
    }

    public static synchronized void SendUpdateNotifForLevel(int i) {
        synchronized (SettingPreferences.class) {
            mLogLevel = i;
            UpdateWith(KEY_LOG_LEVEL, i);
        }
    }

    public static synchronized void SendUpdateNotifForMaxFileCount(int i) {
        synchronized (SettingPreferences.class) {
            mMaxLogFileCount = i;
            UpdateWith(KEY_LOG_FILE_COUNT, i);
        }
    }

    public static synchronized void SendUpdateNotifForMaxFileSize(int i) {
        synchronized (SettingPreferences.class) {
            mMaxLogFileSize = i;
            UpdateWith(KEY_LOG_FILE_SIZE, i);
        }
    }

    public static synchronized void SendUpdateNotifForTarget(int i) {
        synchronized (SettingPreferences.class) {
            mLogTargets = i;
            UpdateWith(KEY_LOG_TARGET, i);
        }
    }

    public static synchronized void SetClearTime(long j) {
        synchronized (SettingPreferences.class) {
            mClearingTime = j;
        }
    }

    public static synchronized void UpdateWith(String str, int i) {
        synchronized (SettingPreferences.class) {
            SharedPreferences.Editor edit = mSharedPref.edit();
            edit.putInt(str, i);
            edit.commit();
            sendUpdateNotif(str, i);
        }
    }

    public static synchronized void enableLog(boolean z) {
        synchronized (SettingPreferences.class) {
            mIsLogEnabled = z ? 1 : 0;
        }
    }

    public static synchronized void enablePerLogger(int i) {
        synchronized (SettingPreferences.class) {
            boolean z = true;
            if (!mPerfLoggerInit) {
                mPerfLoggerInit = true;
            } else if (mPerfLoggerLevel == i) {
                z = false;
            }
            if (z) {
                mPerfLoggerLevel = i;
                SharedPreferences.Editor edit = mSharedPref.edit();
                edit.putInt(KEY_PERF_LEVEL, i);
                edit.commit();
                sendUpdateNotif(KEY_PERF_LEVEL, i);
                CtxLog.NativePerfLoggerInit(i);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void enablePerfLogger(int r1, boolean r2) {
        /*
            java.lang.Class<com.citrix.worx.sdk.SettingPreferences> r0 = com.citrix.worx.sdk.SettingPreferences.class
            monitor-enter(r0)
            if (r2 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            boolean r2 = mPerfLoggerInit     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0015
            int r2 = mPerfLoggerLevel     // Catch:{ all -> 0x001f }
            if (r2 == r1) goto L_0x001d
            mPerfLoggerLevel = r1     // Catch:{ all -> 0x001f }
            com.citrix.worx.sdk.CtxLog.NativePerfLoggerInit(r1)     // Catch:{ all -> 0x001f }
            goto L_0x001d
        L_0x0015:
            r2 = 1
            mPerfLoggerInit = r2     // Catch:{ all -> 0x001f }
            mPerfLoggerLevel = r1     // Catch:{ all -> 0x001f }
            com.citrix.worx.sdk.CtxLog.NativePerfLoggerInit(r1)     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r0)
            return
        L_0x001f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.worx.sdk.SettingPreferences.enablePerfLogger(int, boolean):void");
    }

    public static int getLogFileCount() {
        return mMaxLogFileCount;
    }

    public static int getLogFileSize() {
        return mMaxLogFileSize;
    }

    public static int getLogLevel() {
        return mLogLevel;
    }

    public static int getLogTarets() {
        return mLogTargets;
    }

    public static int getPerfLogLevel() {
        return mPerfLoggerLevel;
    }

    public static boolean isLogEnabeld() {
        return mIsLogEnabled != 0;
    }

    public static boolean isPerfEnabeld() {
        return mPerfLoggerInit;
    }

    public static void sendUpdateNotif(String str, int i) {
        Intent intent = new Intent(GetReciverFilterTag());
        intent.setAction(GetReciverFilterTag());
        Intent intent2 = intent.setPackage(mContext.getPackageName());
        if (str.equals(KEY_LOG_CLEAR_TIME)) {
            intent2.putExtra(KEY_LOG_CLEAR_TIME, mClearingTime);
        } else {
            intent2.putExtra(str, i);
        }
        mContext.sendBroadcast(intent2);
    }

    public static synchronized void setBroadCastFileterName(String str) {
        synchronized (SettingPreferences.class) {
            mBroadCastFilterTag = str;
        }
    }

    public static void setLogFileCount(int i) {
        mMaxLogFileCount = i;
    }

    public static void setLogFileSize(int i) {
        mMaxLogFileSize = i;
    }

    public static void setLogLevel(int i) {
        mLogLevel = i;
    }

    public static void setLogTarets(int i) {
        mLogTargets = i;
    }

    public static void setPerfLogLevel(int i) {
        mPerfLoggerLevel = i;
    }
}
