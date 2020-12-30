package com.citrix.worx.sdk;

import android.content.Context;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/* compiled from: PG */
public class CtxLog {
    public static final int BUFFER_SIZE = 4096;
    public static final int CONSOLE = 2;
    public static final String CTXLOG_VERSION = "10.0";
    public static final String DEFAULT_LOGDIR = "CtxLogs";
    public static final int DEFAULT_LOGDIR_MODE = 0;
    public static final int FILE = 1;
    public static final String INVALID_FILE_NAME_CHARS = ".*[/\\\\?%*:|\"<>].*";
    public static final int LVL_DEBUG1 = 6;
    public static final int LVL_DEBUG10 = 15;
    public static final int LVL_DEBUG2 = 7;
    public static final int LVL_DEBUG3 = 8;
    public static final int LVL_DEBUG4 = 9;
    public static final int LVL_DEBUG5 = 10;
    public static final int LVL_DEBUG6 = 11;
    public static final int LVL_DEBUG7 = 12;
    public static final int LVL_DEBUG8 = 13;
    public static final int LVL_DEBUG9 = 14;
    public static final int MSG_CRITICAL = 1;
    public static final int MSG_DETAIL = 5;
    public static final int MSG_ERROR = 2;
    public static final int MSG_INFO = 4;
    public static final int MSG_NOTHING = 0;
    public static final int MSG_WARNING = 3;
    public static String PERF_EVENT_TYPE_START = "Start";
    public static String PERF_EVENT_TYPE_STOP = "Stop";
    public static String PERF_EVENT_TYPE_TIMESTAMP = "Timestamp";
    public static final int REMOTE_NETWORK = 4;
    public static final int REMOTE_SYSLOG = 8;
    public static final String TAG = "CtxLog";
    public static final String TEMP_DIR_NAME = "tmp";
    public static String appName = null;
    public static String compressedFileLocation = null;
    public static boolean debug = false;
    public static boolean enabled = true;
    public static boolean initialized = false;
    public static String procLoggingDir = null;

    static {
        try {
            System.loadLibrary("log4cpp");
            System.loadLibrary("ctxlog");
        } catch (Throwable th) {
            StringBuilder a = Eo.a("Failed to load logging libraries: ");
            a.append(th.getMessage());
            Log.e(TAG, a.toString());
        }
    }

    public static native void Critical(String str, String str2);

    public static void Critical(String str, String str2, Object... objArr) {
        Critical(str, String.format(str2, objArr));
    }

    public static native void Debug(String str, int i, String str2);

    public static void Debug(String str, int i, String str2, Object... objArr) {
        Debug(str, i, String.format(str2, objArr));
    }

    public static void Debug1(String str, String str2) {
        Debug(str, 6, str2);
    }

    public static void Debug10(String str, String str2) {
        Debug(str, 15, str2);
    }

    public static void Debug2(String str, String str2) {
        Debug(str, 7, str2);
    }

    public static void Debug3(String str, String str2) {
        Debug(str, 8, str2);
    }

    public static void Debug4(String str, String str2) {
        Debug(str, 9, str2);
    }

    public static void Debug5(String str, String str2) {
        Debug(str, 10, str2);
    }

    public static void Debug6(String str, String str2) {
        Debug(str, 11, str2);
    }

    public static void Debug7(String str, String str2) {
        Debug(str, 12, str2);
    }

    public static void Debug8(String str, String str2) {
        Debug(str, 13, str2);
    }

    public static void Debug9(String str, String str2) {
        Debug(str, 14, str2);
    }

    public static native void Detail(String str, String str2);

    public static void Detail(String str, String str2, Object... objArr) {
        Detail(str, String.format(str2, objArr));
    }

    public static native void Error(String str, String str2);

    public static void Error(String str, String str2, Object... objArr) {
        Error(str, String.format(str2, objArr));
    }

    public static String GetZipFileLocation() {
        String a = Eo.a(new StringBuilder(), procLoggingDir, "/", TEMP_DIR_NAME);
        new File(a).mkdirs();
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append("/");
        compressedFileLocation = Eo.a(sb, appName, ".zip");
        return compressedFileLocation;
    }

    public static native void Info(String str, String str2);

    public static void Info(String str, String str2, Object... objArr) {
        Info(str, String.format(str2, objArr));
    }

    public static native void NativePerfLoggerInit(int i);

    public static native void PerfEvent(String str, int i, String str2, String str3, int i2);

    public static native void PerfEventWithMsg(String str, int i, String str2, String str3, int i2, String str4);

    public static void PerfLoggerInit(int i) {
        if (initialized) {
            SettingPreferences.enablePerLogger(i);
        }
    }

    public static native void Secure(String str, int i, String str2);

    public static void Secure(String str, int i, String str2, Object... objArr) {
        if (debug) {
            Secure(str, i, String.format(str2, objArr));
        }
    }

    public static void Secure1(String str, String str2) {
        if (debug) {
            Secure(str, 6, str2);
        }
    }

    public static void Secure10(String str, String str2) {
        if (debug) {
            Secure(str, 15, str2);
        }
    }

    public static void Secure2(String str, String str2) {
        if (debug) {
            Secure(str, 7, str2);
        }
    }

    public static void Secure3(String str, String str2) {
        if (debug) {
            Secure(str, 8, str2);
        }
    }

    public static void Secure4(String str, String str2) {
        if (debug) {
            Secure(str, 9, str2);
        }
    }

    public static void Secure5(String str, String str2) {
        if (debug) {
            Secure(str, 10, str2);
        }
    }

    public static void Secure6(String str, String str2) {
        if (debug) {
            Secure(str, 11, str2);
        }
    }

    public static void Secure7(String str, String str2) {
        if (debug) {
            Secure(str, 12, str2);
        }
    }

    public static void Secure8(String str, String str2) {
        if (debug) {
            Secure(str, 13, str2);
        }
    }

    public static void Secure9(String str, String str2) {
        if (debug) {
            Secure(str, 14, str2);
        }
    }

    public static native void Warning(String str, String str2);

    public static void Warning(String str, String str2, Object... objArr) {
        Warning(str, String.format(str2, objArr));
    }

    public static void addToSupportBundle(String str, String str2, byte[] bArr) {
        String str3;
        FileOutputStream fileOutputStream;
        Exception e;
        if (str2 == null || str2.isEmpty() || str2.matches(INVALID_FILE_NAME_CHARS)) {
            str3 = Eo.a(" invalid fileName ", str2);
        } else {
            str3 = BuildConfig.FLAVOR;
        }
        if (bArr == null || bArr.length == 0) {
            str3 = Eo.a(str3, " argument data is null or empty");
        }
        if (str3.isEmpty()) {
            File file = new File(str);
            if (file.exists() || file.mkdirs()) {
                try {
                    fileOutputStream = new FileOutputStream(new File(file, str2));
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.flush();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            Error(TAG, "addToSupportBundle:Error in writing file " + str2, (Throwable) e);
                            Utils.closeStreamQuietly(fileOutputStream);
                            return;
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                } catch (Exception e3) {
                    fileOutputStream = null;
                    e = e3;
                    Error(TAG, "addToSupportBundle:Error in writing file " + str2, (Throwable) e);
                    Utils.closeStreamQuietly(fileOutputStream);
                    return;
                } catch (Throwable th2) {
                    fileOutputStream = null;
                    th = th2;
                    Utils.closeStreamQuietly(fileOutputStream);
                    throw th;
                }
                Utils.closeStreamQuietly(fileOutputStream);
                return;
            }
            StringBuilder a = Eo.a("addToSupportBundle: mkdirs failed for ");
            a.append(file.getAbsolutePath());
            Error(TAG, a.toString());
            return;
        }
        Warning(TAG, "addToSupportBundle: " + str3);
    }

    public static native void clearLogcatLogs();

    public static synchronized void clearLogs() {
        synchronized (CtxLog.class) {
            if (!initialized) {
                Log.e(TAG, "Logging not initialized");
                return;
            }
            SettingPreferences.SetClearTime(System.currentTimeMillis());
            clearZipFiles();
            clearLogsNative();
            SettingPreferences.SendUpdateNotifClear();
        }
    }

    public static native void clearLogsNative();

    public static void clearZipFiles() {
        if (compressedFileLocation == null) {
            GetZipFileLocation();
        }
        new File(compressedFileLocation).delete();
    }

    public static native void collectLogcatLogs();

    public static void disableProcessLog() {
        if (initialized) {
            nativeEnable(false);
        }
    }

    public static void enable(boolean z) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized");
        } else if (SettingPreferences.isLogEnabeld() != z) {
            SettingPreferences.SendUpdateNotifForEnable(z);
            nativeEnable(z);
            if (z) {
                CrashManager.register();
            } else {
                CrashManager.deregister();
            }
        }
    }

    public static void flushProcessStreams(Process process) {
        try {
            InputStream errorStream = process.getErrorStream();
            errorStream.skip((long) errorStream.available());
        } catch (IOException e) {
            qI.a.a(e);
        }
    }

    public static native int getLevel();

    public static native String getLoggingDir();

    public static native int getMaxFileCount();

    public static native int getMaxFileSize();

    public static String getMessage(String str, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        if (th != null) {
            qI.a.a(th, printWriter);
        } else {
            qI.a.a(new Throwable("null throwable"), printWriter);
        }
        StringBuilder c = Eo.c(str, AbstractAccountCredentialCache.NEW_LINE);
        c.append(stringWriter.toString());
        return c.toString();
    }

    public static native int getTargets();

    public static String getVersion() {
        return CTXLOG_VERSION;
    }

    public static File getlogDirectory() {
        File file = new File(procLoggingDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static synchronized void initialize(Context context, boolean z) {
        synchronized (CtxLog.class) {
            initialize(context, (String) null, z);
        }
    }

    public static native boolean isEnabled();

    public static void logStackTrace(int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        qI.a.a(new Exception(), printStream);
        Log.i(TAG, "stack trace: " + byteArrayOutputStream.toString());
        Debug(TAG, i, byteArrayOutputStream.toString());
    }

    public static native void nativeEnable(boolean z);

    public static native void nativeInitialize(String str, String str2);

    public static native void nativeSetLevel(int i);

    public static native void nativeSetMaxFileCount(int i);

    public static native void nativeSetMaxFileSize(int i);

    public static native void nativeSetTargets(int i);

    public static void refreshFileLogger() {
        new Thread(new RefreshAllFilesThread()).start();
    }

    public static void removeFromSupportBundle(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (str == null || str.isEmpty() || str.matches(INVALID_FILE_NAME_CHARS)) {
            str2 = Eo.b(str2, "invalid fileName ", str);
        }
        if (str2.isEmpty()) {
            File file = new File(procLoggingDir, str);
            if (!file.exists() || file.isDirectory() || !file.delete()) {
                Warning(TAG, "removeFromSupportBundle: couldn't remove file " + str + " from support bundle");
                return;
            }
            Info(TAG, "removeFromSupportBundle: successfully removed file " + str + " from support bundle");
            return;
        }
        Warning(TAG, "removeFromSupportBundle: " + str2);
    }

    public static void setAttribute(String str, String str2) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized. setAttribute failed for key=" + str + " value=" + str2 + " pair");
            return;
        }
        DeviceAndAppInfo.setAttribute(str, str2);
    }

    public static void setLevel(int i) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized");
        } else if (SettingPreferences.getLogLevel() != i) {
            nativeSetLevel(i);
            SettingPreferences.SendUpdateNotifForLevel(i);
            if (SettingPreferences.isPerfEnabeld()) {
                NativePerfLoggerInit(i);
            }
        }
    }

    public static void setMaxFileCount(int i) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized");
        } else if (SettingPreferences.getLogFileCount() != i) {
            SettingPreferences.SendUpdateNotifForMaxFileCount(i);
            nativeSetMaxFileCount(i);
        }
    }

    public static void setMaxFileSize(int i) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized");
        } else if (SettingPreferences.getLogFileSize() != i) {
            SettingPreferences.SendUpdateNotifForMaxFileSize(i);
            nativeSetMaxFileSize(i);
        }
    }

    public static void setTargets(int i) {
        if (!initialized) {
            Log.e(TAG, "Logging not initialized");
        } else if (SettingPreferences.getLogTarets() != i) {
            SettingPreferences.SendUpdateNotifForTarget(i);
            nativeSetTargets(i);
        }
    }

    public static synchronized File zipLogFiles() {
        File zipLogFiles;
        synchronized (CtxLog.class) {
            compressedFileLocation = GetZipFileLocation();
            zipLogFiles = zipLogFiles(compressedFileLocation);
        }
        return zipLogFiles;
    }

    public static void Critical(String str, String str2, Throwable th) {
        Critical(str, getMessage(str2, th));
    }

    public static void Debug(String str, int i, String str2, Throwable th) {
        Debug(str, i, getMessage(str2, th));
    }

    public static void Debug1(String str, String str2, Object... objArr) {
        Debug(str, 6, String.format(str2, objArr));
    }

    public static void Debug10(String str, String str2, Object... objArr) {
        Debug(str, 15, String.format(str2, objArr));
    }

    public static void Debug2(String str, String str2, Object... objArr) {
        Debug(str, 7, String.format(str2, objArr));
    }

    public static void Debug3(String str, String str2, Object... objArr) {
        Debug(str, 8, String.format(str2, objArr));
    }

    public static void Debug4(String str, String str2, Object... objArr) {
        Debug(str, 9, String.format(str2, objArr));
    }

    public static void Debug5(String str, String str2, Object... objArr) {
        Debug(str, 10, String.format(str2, objArr));
    }

    public static void Debug6(String str, String str2, Object... objArr) {
        Debug(str, 11, String.format(str2, objArr));
    }

    public static void Debug7(String str, String str2, Object... objArr) {
        Debug(str, 12, String.format(str2, objArr));
    }

    public static void Debug8(String str, String str2, Object... objArr) {
        Debug(str, 13, String.format(str2, objArr));
    }

    public static void Debug9(String str, String str2, Object... objArr) {
        Debug(str, 14, String.format(str2, objArr));
    }

    public static void Detail(String str, String str2, Throwable th) {
        Detail(str, getMessage(str2, th));
    }

    public static void Error(String str, String str2, Throwable th) {
        Error(str, getMessage(str2, th));
    }

    public static void Info(String str, String str2, Throwable th) {
        Info(str, getMessage(str2, th));
    }

    public static void Warning(String str, String str2, Throwable th) {
        Warning(str, getMessage(str2, th));
    }

    public static void Debug1(String str, String str2, Throwable th) {
        Debug(str, 6, getMessage(str2, th));
    }

    public static void Debug10(String str, String str2, Throwable th) {
        Debug(str, 15, getMessage(str2, th));
    }

    public static void Debug2(String str, String str2, Throwable th) {
        Debug(str, 7, getMessage(str2, th));
    }

    public static void Debug3(String str, String str2, Throwable th) {
        Debug(str, 8, getMessage(str2, th));
    }

    public static void Debug4(String str, String str2, Throwable th) {
        Debug(str, 9, getMessage(str2, th));
    }

    public static void Debug5(String str, String str2, Throwable th) {
        Debug(str, 10, getMessage(str2, th));
    }

    public static void Debug6(String str, String str2, Throwable th) {
        Debug(str, 11, getMessage(str2, th));
    }

    public static void Debug7(String str, String str2, Throwable th) {
        Debug(str, 12, getMessage(str2, th));
    }

    public static void Debug8(String str, String str2, Throwable th) {
        Debug(str, 13, getMessage(str2, th));
    }

    public static void Debug9(String str, String str2, Throwable th) {
        Debug(str, 14, getMessage(str2, th));
    }

    public static void PerfLoggerInit(int i, boolean z) {
        SettingPreferences.enablePerfLogger(i, z);
    }

    public static void Secure(String str, int i, String str2, Throwable th) {
        if (debug) {
            Secure(str, i, getMessage(str2, th));
        }
    }

    public static void Secure1(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 6, String.format(str2, objArr));
        }
    }

    public static void Secure10(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 15, String.format(str2, objArr));
        }
    }

    public static void Secure2(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 7, String.format(str2, objArr));
        }
    }

    public static void Secure3(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 8, String.format(str2, objArr));
        }
    }

    public static void Secure4(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 9, String.format(str2, objArr));
        }
    }

    public static void Secure5(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 10, String.format(str2, objArr));
        }
    }

    public static void Secure6(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 11, String.format(str2, objArr));
        }
    }

    public static void Secure7(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 12, String.format(str2, objArr));
        }
    }

    public static void Secure8(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 13, String.format(str2, objArr));
        }
    }

    public static void Secure9(String str, String str2, Object... objArr) {
        if (debug) {
            Secure(str, 14, String.format(str2, objArr));
        }
    }

    public static synchronized void initialize(Context context, String str, boolean z) {
        synchronized (CtxLog.class) {
            if (!initialized) {
                debug = z;
                if (context != null) {
                    procLoggingDir = null;
                    if (str != null) {
                        procLoggingDir = str;
                    }
                    if (procLoggingDir == null) {
                        procLoggingDir = context.getDir(DEFAULT_LOGDIR, 0).getAbsolutePath();
                    }
                    CrashManager.register();
                    clearZipFiles();
                    int i = context.getApplicationInfo().labelRes;
                    if (i == 0) {
                        appName = context.getPackageName();
                    } else {
                        appName = context.getString(i);
                    }
                    "CtxLog.initialize(), application name : " + appName + " using logdir: " + procLoggingDir;
                    try {
                        nativeInitialize(appName, procLoggingDir);
                        initialized = true;
                        Log.i(TAG, "Logging Framework initialized successfully.");
                    } catch (Throwable th) {
                        Log.e(TAG, "nativeInitialize exception: " + th.getMessage());
                    }
                    SettingPreferences.IntializeWithDefault(context);
                    DeviceAndAppInfo.initialize(context);
                    return;
                }
                throw new UnsupportedOperationException("Application context and logging directory both are null in CtxLog.initialize()");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d3, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d6, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f5, code lost:
        r3.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f8, code lost:
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d5 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f5 A[Catch:{ all -> 0x0100 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File zipLogFiles(java.lang.String r11) {
        /*
            boolean r0 = initialized
            java.lang.String r1 = "CtxLog"
            r2 = 0
            if (r0 != 0) goto L_0x000d
            java.lang.String r11 = "Logging not initialized. Log collection is failed"
            android.util.Log.e(r1, r11)
            return r2
        L_0x000d:
            boolean r0 = com.citrix.worx.sdk.SettingPreferences.isLogEnabeld()
            if (r0 != 0) goto L_0x0019
            java.lang.String r11 = "Logging not enabled. So log collection is not allowed"
            android.util.Log.e(r1, r11)
            return r2
        L_0x0019:
            collectLogcatLogs()     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            com.citrix.worx.sdk.DeviceAndAppInfo.collectDeviceAndAppInfo()     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            java.lang.String r3 = procLoggingDir     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            boolean r3 = r0.exists()     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            if (r3 != 0) goto L_0x0033
            com.citrix.worx.sdk.Utils.closeStreamQuietly(r2)
            clearLogcatLogs()
            return r2
        L_0x0033:
            com.citrix.worx.sdk.CtxLog$1 r3 = new com.citrix.worx.sdk.CtxLog$1     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            r3.<init>()     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            java.io.File[] r0 = r0.listFiles(r3)     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            int r3 = r0.length     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            if (r3 != 0) goto L_0x0046
            com.citrix.worx.sdk.Utils.closeStreamQuietly(r2)
            clearLogcatLogs()
            return r2
        L_0x0046:
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            r3.<init>(r11)     // Catch:{ IOException -> 0x00d8, all -> 0x00d5 }
            r3.delete()     // Catch:{ IOException -> 0x00d2, all -> 0x00d5 }
            r3.createNewFile()     // Catch:{ IOException -> 0x00d2, all -> 0x00d5 }
            r11 = 1
            r4 = 0
            r3.setReadable(r11, r4)     // Catch:{ IOException -> 0x00d2, all -> 0x00d5 }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00d2, all -> 0x00d5 }
            r11.<init>(r3)     // Catch:{ IOException -> 0x00d2, all -> 0x00d5 }
            java.util.zip.ZipOutputStream r5 = new java.util.zip.ZipOutputStream     // Catch:{ IOException -> 0x00d0 }
            r5.<init>(r11)     // Catch:{ IOException -> 0x00d0 }
            java.util.List r0 = com.citrix.worx.sdk.Utils.getRecursiveFileList(r0)     // Catch:{ IOException -> 0x00d0 }
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x00d0 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IOException -> 0x00d0 }
        L_0x006c:
            boolean r7 = r0.hasNext()     // Catch:{ IOException -> 0x00d0 }
            if (r7 == 0) goto L_0x00c9
            java.lang.Object r7 = r0.next()     // Catch:{ IOException -> 0x00d0 }
            java.io.File r7 = (java.io.File) r7     // Catch:{ IOException -> 0x00d0 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00d0 }
            r8.<init>(r7)     // Catch:{ IOException -> 0x00d0 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d0 }
            r9.<init>()     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r10 = appName     // Catch:{ IOException -> 0x00d0 }
            r9.append(r10)     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r7 = r7.getPath()     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r10 = procLoggingDir     // Catch:{ IOException -> 0x00d0 }
            int r10 = r10.length()     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r7 = r7.substring(r10)     // Catch:{ IOException -> 0x00d0 }
            r9.append(r7)     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r7 = r9.toString()     // Catch:{ IOException -> 0x00d0 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d0 }
            r9.<init>()     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r10 = "zipping file: "
            r9.append(r10)     // Catch:{ IOException -> 0x00d0 }
            r9.append(r7)     // Catch:{ IOException -> 0x00d0 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x00d0 }
            Info(r1, r9)     // Catch:{ IOException -> 0x00d0 }
            java.util.zip.ZipEntry r9 = new java.util.zip.ZipEntry     // Catch:{ IOException -> 0x00d0 }
            r9.<init>(r7)     // Catch:{ IOException -> 0x00d0 }
            r5.putNextEntry(r9)     // Catch:{ IOException -> 0x00d0 }
        L_0x00b8:
            int r7 = r8.read(r6)     // Catch:{ IOException -> 0x00d0 }
            if (r7 <= 0) goto L_0x00c2
            r5.write(r6, r4, r7)     // Catch:{ IOException -> 0x00d0 }
            goto L_0x00b8
        L_0x00c2:
            r5.closeEntry()     // Catch:{ IOException -> 0x00d0 }
            r8.close()     // Catch:{ IOException -> 0x00d0 }
            goto L_0x006c
        L_0x00c9:
            r5.finish()     // Catch:{ IOException -> 0x00d0 }
            r5.close()     // Catch:{ IOException -> 0x00d0 }
            goto L_0x00f9
        L_0x00d0:
            r0 = move-exception
            goto L_0x00db
        L_0x00d2:
            r0 = move-exception
            r11 = r2
            goto L_0x00db
        L_0x00d5:
            r0 = move-exception
            r11 = r2
            goto L_0x0101
        L_0x00d8:
            r0 = move-exception
            r11 = r2
            r3 = r11
        L_0x00db:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0100 }
            r4.<init>()     // Catch:{ all -> 0x0100 }
            java.lang.String r5 = "IOException while creating zip file: "
            r4.append(r5)     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0100 }
            r4.append(r0)     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0100 }
            Error(r1, r0)     // Catch:{ all -> 0x0100 }
            if (r3 == 0) goto L_0x00f9
            r3.delete()     // Catch:{ all -> 0x0100 }
            r3 = r2
        L_0x00f9:
            com.citrix.worx.sdk.Utils.closeStreamQuietly(r11)
            clearLogcatLogs()
            return r3
        L_0x0100:
            r0 = move-exception
        L_0x0101:
            com.citrix.worx.sdk.Utils.closeStreamQuietly(r11)
            clearLogcatLogs()
            goto L_0x0109
        L_0x0108:
            throw r0
        L_0x0109:
            goto L_0x0108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.worx.sdk.CtxLog.zipLogFiles(java.lang.String):java.io.File");
    }

    public static void Secure1(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 6, getMessage(str2, th));
        }
    }

    public static void Secure10(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 15, getMessage(str2, th));
        }
    }

    public static void Secure2(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 7, getMessage(str2, th));
        }
    }

    public static void Secure3(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 8, getMessage(str2, th));
        }
    }

    public static void Secure4(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 9, getMessage(str2, th));
        }
    }

    public static void Secure5(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 10, getMessage(str2, th));
        }
    }

    public static void Secure6(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 11, getMessage(str2, th));
        }
    }

    public static void Secure7(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 12, getMessage(str2, th));
        }
    }

    public static void Secure8(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 13, getMessage(str2, th));
        }
    }

    public static void Secure9(String str, String str2, Throwable th) {
        if (debug) {
            Secure(str, 14, getMessage(str2, th));
        }
    }

    public static void setMaxFileCount(int i, boolean z) {
        if (z && SettingPreferences.getLogFileCount() != i) {
            SettingPreferences.setLogFileCount(i);
            nativeSetMaxFileCount(i);
        }
    }

    public static void setMaxFileSize(int i, boolean z) {
        if (z && SettingPreferences.getLogFileSize() != i) {
            SettingPreferences.setLogFileSize(i);
            nativeSetMaxFileSize(i);
        }
    }

    public static void setTargets(int i, boolean z) {
        if (z && SettingPreferences.getLogTarets() != i) {
            SettingPreferences.setLogTarets(i);
            nativeSetTargets(i);
        }
    }

    public static void enable(boolean z, boolean z2) {
        if (z2 && SettingPreferences.isLogEnabeld() != z) {
            SettingPreferences.enableLog(z);
            nativeEnable(z);
        }
    }

    public static void setLevel(int i, boolean z) {
        if (z && SettingPreferences.getLogLevel() != i) {
            SettingPreferences.setLogLevel(i);
            nativeSetLevel(i);
            if (SettingPreferences.isPerfEnabeld()) {
                SettingPreferences.setPerfLogLevel(i);
                NativePerfLoggerInit(i);
            }
        }
    }

    public static void clearLogs(long j) {
        if (SettingPreferences.GetClearTime() != j) {
            clearZipFiles();
            clearLogsNative();
        }
    }

    public static void addToSupportBundle(File file) {
        FileOutputStream fileOutputStream;
        String str = (file == null || file.length() == 0 || file.isDirectory() || file.getName().matches(INVALID_FILE_NAME_CHARS)) ? " invalid file " : BuildConfig.FLAVOR;
        if (str.isEmpty()) {
            File file2 = new File(procLoggingDir, file.getName());
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    try {
                        Error(TAG, "addToSupportBundle:Error in writing file " + file.getName(), (Throwable) e);
                        Utils.closeStreamQuietly(fileOutputStream);
                        Utils.closeStreamQuietly(fileInputStream);
                    } catch (Throwable th) {
                        th = th;
                        Utils.closeStreamQuietly(fileOutputStream);
                        Utils.closeStreamQuietly(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    Utils.closeStreamQuietly(fileOutputStream);
                    Utils.closeStreamQuietly(fileInputStream);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            Utils.closeStreamQuietly(fileOutputStream);
                            Utils.closeStreamQuietly(fileInputStream2);
                            return;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    Error(TAG, "addToSupportBundle:Error in writing file " + file.getName(), (Throwable) e);
                    Utils.closeStreamQuietly(fileOutputStream);
                    Utils.closeStreamQuietly(fileInputStream);
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    Utils.closeStreamQuietly(fileOutputStream);
                    Utils.closeStreamQuietly(fileInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                Error(TAG, "addToSupportBundle:Error in writing file " + file.getName(), (Throwable) e);
                Utils.closeStreamQuietly(fileOutputStream);
                Utils.closeStreamQuietly(fileInputStream);
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                Utils.closeStreamQuietly(fileOutputStream);
                Utils.closeStreamQuietly(fileInputStream);
                throw th;
            }
        } else {
            Warning(TAG, "addToSupportBundle: " + str);
        }
    }

    public static void addToSupportBundle(String str, byte[] bArr) {
        addToSupportBundle(procLoggingDir, str, bArr);
    }
}
