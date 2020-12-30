package com.citrix.worx.sdk;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/* compiled from: PG */
public class RefreshAllFilesThread implements Runnable {
    public static final String TAG = "CtxLog.RefreshAllFilesThread";

    public void run() {
        if (!CtxLog.initialized) {
            Log.e(TAG, "Logger not initialized");
            return;
        }
        for (File next : Utils.getRecursiveFileList(new File(CtxLog.procLoggingDir).listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.equals(CtxLog.TEMP_DIR_NAME);
            }
        }))) {
            try {
                Log.i(TAG, "opening and closing file :" + next.getName());
                new FileOutputStream(next, true).close();
            } catch (IOException unused) {
                StringBuilder a = Eo.a("IOException in opening and closing of file :");
                a.append(next.getName());
                Log.e(TAG, a.toString());
            }
        }
    }
}
