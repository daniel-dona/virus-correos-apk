package com.citrix.worx.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: PG */
public class CtxPolicyChangeReceiver extends BroadcastReceiver {
    public static final String TAG = CtxPolicyChangeReceiver.class.getName();

    private void TakeActionOnIntent(Context context, Intent intent) {
        if (intent.hasExtra(SettingPreferences.KEY_LOG_CLEAR_TIME)) {
            CtxLog.clearLogs(intent.getLongExtra(SettingPreferences.KEY_LOG_CLEAR_TIME, 0));
        }
        if (intent.hasExtra(SettingPreferences.KEY_LOG_ENABLE)) {
            CtxLog.enable(intent.getIntExtra(SettingPreferences.KEY_LOG_ENABLE, 0) == 1, true);
        }
        if (intent.hasExtra(SettingPreferences.KEY_LOG_FILE_COUNT)) {
            CtxLog.setMaxFileCount(intent.getIntExtra(SettingPreferences.KEY_LOG_FILE_COUNT, 5), true);
        }
        if (intent.hasExtra(SettingPreferences.KEY_LOG_FILE_SIZE)) {
            CtxLog.setMaxFileSize(intent.getIntExtra(SettingPreferences.KEY_LOG_FILE_SIZE, 2), true);
        }
        if (intent.hasExtra(SettingPreferences.KEY_LOG_LEVEL)) {
            CtxLog.setLevel(intent.getIntExtra(SettingPreferences.KEY_LOG_LEVEL, 15), true);
        }
        if (intent.hasExtra(SettingPreferences.KEY_LOG_TARGET)) {
            CtxLog.setTargets(intent.getIntExtra(SettingPreferences.KEY_LOG_TARGET, 3), true);
        }
        if (intent.hasExtra(SettingPreferences.KEY_PERF_LEVEL)) {
            CtxLog.PerfLoggerInit(intent.getIntExtra(SettingPreferences.KEY_PERF_LEVEL, 0), true);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SettingPreferences.GetReciverFilterTag())) {
            TakeActionOnIntent(context, intent);
        }
    }
}
