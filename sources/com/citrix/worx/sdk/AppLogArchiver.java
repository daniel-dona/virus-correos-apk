package com.citrix.worx.sdk;

import android.util.Log;
import java.io.File;

/* compiled from: PG */
public class AppLogArchiver implements Runnable {
    public static final String ARCHIVE_FILE_NAME = "AppLogs.zip";
    public static final long ROLLOVER_TIME_DIFFERENCE = 5000;
    public static final String TAG = "CtxLog.AppLogArchiver";

    private boolean archiveExists(File file) {
        return file.exists() && file.isDirectory() && new File(file, ARCHIVE_FILE_NAME).exists();
    }

    private File[] getFilesToArchive(long j) {
        File[] listFiles = new File(Eo.a(new StringBuilder(), CtxLog.procLoggingDir, "/diagnostics")).listFiles();
        if (listFiles.length < 2) {
            StringBuilder a = Eo.a("only single log file ");
            a.append(listFiles[0]);
            a.append(" to archive ");
            Log.i(TAG, a.toString());
            return listFiles;
        }
        Utils.sortFilesByModifiedTimeFirst(listFiles);
        File[] fileArr = {listFiles[0]};
        long lastModified = listFiles[1].lastModified();
        if (j > lastModified) {
            Log.i(TAG, "app log roll over happened before crash");
            if (j - lastModified >= ROLLOVER_TIME_DIFFERENCE) {
                return fileArr;
            }
            Log.i(TAG, "latest two log files to archive");
            return new File[]{listFiles[0], listFiles[1]};
        }
        Log.i(TAG, "app log roll over happened before crash so latest two log files to archive");
        return new File[]{listFiles[0], listFiles[1]};
    }

    private File getLatestCrashDir(File file) {
        if (!file.exists() || file.isFile()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length == 0) {
            return null;
        }
        Utils.sortFilesByModifiedTimeFirst(listFiles);
        return listFiles[0];
    }

    public void run() {
        File latestCrashDir = getLatestCrashDir(new File(Eo.a(new StringBuilder(), CtxLog.procLoggingDir, "/", CrashManager.CRASH_REPORTS_DIR)));
        if (latestCrashDir != null) {
            if (archiveExists(latestCrashDir)) {
                StringBuilder a = Eo.a("logs already archived for crash file ");
                a.append(latestCrashDir.getName());
                Log.i(TAG, a.toString());
                return;
            }
            File[] filesToArchive = getFilesToArchive(latestCrashDir.lastModified());
            if (filesToArchive.length != 0) {
                Utils.archive(filesToArchive, new File(latestCrashDir, ARCHIVE_FILE_NAME));
            }
        }
    }
}
