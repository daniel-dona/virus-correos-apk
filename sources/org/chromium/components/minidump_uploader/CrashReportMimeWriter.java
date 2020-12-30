package org.chromium.components.minidump_uploader;

import java.io.File;

/* compiled from: PG */
public class CrashReportMimeWriter {
    /* renamed from: a */
    public static void m3309a(File file, File file2) {
        nativeRewriteMinidumpsAsMIMEs(file.getAbsolutePath(), file2.getAbsolutePath());
    }

    public static native void nativeRewriteMinidumpsAsMIMEs(String str, String str2);

    public static native String[] nativeRewriteMinidumpsAsMIMEsAndGetCrashKeys(String str, String str2);
}
