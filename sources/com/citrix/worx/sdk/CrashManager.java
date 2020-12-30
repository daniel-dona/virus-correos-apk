package com.citrix.worx.sdk;

import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import java.io.File;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* compiled from: PG */
public class CrashManager {
    public static final String CRASH_DIR_TIMESTAMP_PATTERN = "yyyy-MM-dd-hh-mm-ssZ";
    public static final String CRASH_FILE_NAME = "Crash.txt";
    public static final String CRASH_REPORTS_DIR = "CrashReports";
    public static final int MAXIMUM_CRASH_FILE_COUNT = 10;
    public static String TAG = "CtxLog.CrashManager";
    public static Thread.UncaughtExceptionHandler m_oldHandler;

    public static void clearCrashLogs() {
        Utils.emptyFolder(new File(Eo.a(new StringBuilder(), CtxLog.procLoggingDir, "/", CRASH_REPORTS_DIR)));
    }

    public static synchronized void deregister() {
        synchronized (CrashManager.class) {
            if (m_oldHandler != null) {
                Thread.setDefaultUncaughtExceptionHandler(m_oldHandler);
                m_oldHandler = null;
            }
        }
    }

    public static void dumpCrashLogs(Thread thread, Throwable th) {
        try {
            rollOverCrashFiles();
            StringBuilder sb = new StringBuilder("UncaughtException stack trace:\n");
            sb.append("Timestamp: ");
            sb.append(new SimpleDateFormat("MM-dd hh:mm:ss.SSS Z", Locale.US).format(new Date()));
            sb.append("\nname=");
            sb.append(thread.getName());
            sb.append("; tid=");
            sb.append(thread.getId());
            sb.append("; priority=");
            sb.append(thread.getPriority());
            sb.append("; state=");
            sb.append(thread.getState());
            sb.append("; group=");
            sb.append(thread.getThreadGroup().getName());
            sb.append(10);
            sb.append(CtxLog.getMessage(BuildConfig.FLAVOR, th));
            sb.append("\n\n");
            for (Map.Entry next : Thread.getAllStackTraces().entrySet()) {
                Thread thread2 = (Thread) next.getKey();
                sb.append("\nname: ");
                sb.append(thread2.getName());
                sb.append("; tid=");
                sb.append(thread2.getId());
                sb.append("; priority=");
                sb.append(thread2.getPriority());
                sb.append("; state=");
                sb.append(thread2.getState());
                sb.append("; group=");
                sb.append(thread2.getThreadGroup().getName());
                sb.append(10);
                for (StackTraceElement stackTraceElement : (StackTraceElement[]) next.getValue()) {
                    sb.append(10);
                    sb.append(stackTraceElement.toString());
                }
                sb.append("\n\n");
            }
            CtxLog.addToSupportBundle(CtxLog.procLoggingDir + "/" + CRASH_REPORTS_DIR + "/" + getCrashDirName(), CRASH_FILE_NAME, sb.toString().getBytes());
            if (m_oldHandler != null) {
                m_oldHandler.uncaughtException(thread, th);
            }
        } catch (Throwable unused) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = m_oldHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    public static String getCrashDirName() {
        return new SimpleDateFormat(CRASH_DIR_TIMESTAMP_PATTERN, Locale.US).format(new Date());
    }

    public static synchronized void register() {
        synchronized (CrashManager.class) {
            new Thread(new AppLogArchiver()).start();
            if (m_oldHandler == null) {
                m_oldHandler = Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    public void uncaughtException(Thread thread, Throwable th) {
                        if (th == null) {
                            th = new Throwable("null throwable caught!");
                        }
                        CrashManager.dumpCrashLogs(thread, th);
                    }
                });
            }
        }
    }

    public static void rollOverCrashFiles() {
        if (CtxLog.procLoggingDir != null) {
            File file = new File(Eo.a(new StringBuilder(), CtxLog.procLoggingDir, "/", CRASH_REPORTS_DIR));
            if (!file.exists()) {
                String str = TAG;
                StringBuilder a = Eo.a("directory ");
                a.append(file.getAbsolutePath());
                a.append(" does not exist");
                CtxLog.Info(str, a.toString());
                if (!file.mkdirs()) {
                    String str2 = TAG;
                    StringBuilder a2 = Eo.a("directory ");
                    a2.append(file.getAbsolutePath());
                    a2.append(" creation failed");
                    CtxLog.Error(str2, a2.toString());
                    return;
                }
                String str3 = TAG;
                StringBuilder a3 = Eo.a("directory ");
                a3.append(file.getAbsolutePath());
                a3.append(" is created successfully");
                CtxLog.Info(str3, a3.toString());
            }
            File[] listFiles = file.listFiles();
            if (listFiles.length >= 10) {
                Arrays.sort(listFiles, new Comparator<File>() {
                    public int compare(File file, File file2) {
                        return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                    }
                });
                for (int i = 0; i <= listFiles.length - 10; i++) {
                    String str4 = TAG;
                    StringBuilder a4 = Eo.a("deleting crash ");
                    a4.append(listFiles[i].getAbsolutePath());
                    CtxLog.Info(str4, a4.toString());
                    Utils.removeFolder(listFiles[i]);
                }
                return;
            }
            return;
        }
        Log.w(TAG, "rollOverCrashFiles: logging directory is null");
    }
}
