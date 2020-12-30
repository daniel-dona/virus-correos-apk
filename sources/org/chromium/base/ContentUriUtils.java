package org.chromium.base;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public abstract class ContentUriUtils {

    /* renamed from: a */
    public static FileProviderUtil f1421a;

    /* renamed from: b */
    public static final Object f1422b = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        android.util.Log.w("ContentUriUtils", "Unknown content uri: " + r11, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        android.util.Log.w("ContentUriUtils", "Cannot open content uri: " + r11, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0082, code lost:
        android.util.Log.w("ContentUriUtils", "Cannot find content uri: " + r11, r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055 A[ExcHandler: Exception (r1v4 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x000d] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b A[ExcHandler: SecurityException (r1v3 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:1:0x000d] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.AssetFileDescriptor m1408a(java.lang.String r11) {
        /*
            java.lang.String r0 = "ContentUriUtils"
            android.content.Context r1 = RN0.a
            android.content.ContentResolver r1 = r1.getContentResolver()
            android.net.Uri r2 = android.net.Uri.parse(r11)
            r3 = 0
            boolean r4 = m1414a((android.net.Uri) r2)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            if (r4 == 0) goto L_0x0042
            java.lang.String r4 = "*/*"
            java.lang.String[] r4 = Tc0.a(r1, r2, r4)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            if (r4 == 0) goto L_0x0096
            int r5 = r4.length     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            if (r5 <= 0) goto L_0x0096
            r5 = 0
            r4 = r4[r5]     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            com.microsoft.intune.mam.client.content.ContentResolverManagementBehavior r5 = Tc0.a()     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            android.content.res.AssetFileDescriptor r1 = r5.openTypedAssetFileDescriptor(r1, r2, r4, r3)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            if (r1 == 0) goto L_0x0041
            long r4 = r1.getStartOffset()     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x0036
            goto L_0x0041
        L_0x0036:
            r1.close()     // Catch:{ IOException -> 0x0039, SecurityException -> 0x006b, Exception -> 0x0055 }
        L_0x0039:
            java.lang.SecurityException r1 = new java.lang.SecurityException     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            java.lang.String r2 = "Cannot open files with non-zero offset type."
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            throw r1     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
        L_0x0041:
            return r1
        L_0x0042:
            java.lang.String r4 = "r"
            android.os.ParcelFileDescriptor r6 = Tc0.c(r1, r2, r4)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            if (r6 == 0) goto L_0x0096
            android.content.res.AssetFileDescriptor r1 = new android.content.res.AssetFileDescriptor     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            r7 = 0
            r9 = -1
            r5 = r1
            r5.<init>(r6, r7, r9)     // Catch:{ FileNotFoundException -> 0x0081, SecurityException -> 0x006b, Exception -> 0x0055 }
            return r1
        L_0x0055:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Unknown content uri: "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.w(r0, r11, r1)
            goto L_0x0096
        L_0x006b:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Cannot open content uri: "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.w(r0, r11, r1)
            goto L_0x0096
        L_0x0081:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Cannot find content uri: "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.w(r0, r11, r1)
        L_0x0096:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ContentUriUtils.m1408a(java.lang.String):android.content.res.AssetFileDescriptor");
    }

    /* renamed from: b */
    public static boolean m1415b(String str) {
        Uri parse;
        if (str == null || (parse = Uri.parse(str)) == null || !"content".equals(parse.getScheme())) {
            return false;
        }
        return true;
    }

    @CalledByNative
    public static boolean contentUriExists(String str) {
        AssetFileDescriptor a = m1408a(str);
        boolean z = a != null;
        if (a != null) {
            try {
                a.close();
            } catch (IOException unused) {
            }
        }
        return z;
    }

    @CalledByNative
    public static boolean delete(String str) {
        return Tc0.a().delete(RN0.a.getContentResolver(), Uri.parse(str), (String) null, (String[]) null) > 0;
    }

    @CalledByNative
    public static String getMimeType(String str) {
        ContentResolver contentResolver = RN0.a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (!m1414a(parse)) {
            return Tc0.a(contentResolver, parse);
        }
        String[] a = Tc0.a(contentResolver, parse, "*/*");
        if (a == null || a.length <= 0) {
            return null;
        }
        return a[0];
    }

    @CalledByNative
    public static String maybeGetDisplayName(String str) {
        try {
            String a = m1410a(Uri.parse(str), RN0.a, "_display_name");
            if (TextUtils.isEmpty(a)) {
                return null;
            }
            return a;
        } catch (Exception e) {
            Log.w("ContentUriUtils", "Cannot open content uri: " + str, e);
            return null;
        }
    }

    @CalledByNative
    public static int openContentUriForRead(String str) {
        AssetFileDescriptor a = m1408a(str);
        if (a != null) {
            return a.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        m1411a(r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0037, code lost:
        throw r2;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1414a(android.net.Uri r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.content.Context r1 = RN0.a
            boolean r1 = android.provider.DocumentsContract.isDocumentUri(r1, r8)
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            android.content.Context r1 = RN0.a
            android.content.ContentResolver r2 = r1.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r8
            android.database.Cursor r8 = Tc0.a(r2, r3, r4, r5, r6, r7)     // Catch:{ NullPointerException -> 0x003d }
            r1 = 0
            if (r8 == 0) goto L_0x0038
            int r2 = r8.getCount()     // Catch:{ all -> 0x0031 }
            r3 = 1
            if (r2 < r3) goto L_0x0038
            r8.moveToFirst()     // Catch:{ all -> 0x0031 }
            boolean r2 = m1413a((android.database.Cursor) r8)     // Catch:{ all -> 0x0031 }
            m1411a(r1, r8)     // Catch:{ NullPointerException -> 0x003d }
            return r2
        L_0x0031:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r2 = move-exception
            m1411a(r1, r8)     // Catch:{ NullPointerException -> 0x003d }
            throw r2     // Catch:{ NullPointerException -> 0x003d }
        L_0x0038:
            if (r8 == 0) goto L_0x003d
            m1411a(r1, r8)     // Catch:{ NullPointerException -> 0x003d }
        L_0x003d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ContentUriUtils.m1414a(android.net.Uri):boolean");
    }

    /* renamed from: a */
    public static void m1412a(FileProviderUtil fileProviderUtil) {
        synchronized (f1422b) {
            f1421a = fileProviderUtil;
        }
    }

    /* renamed from: a */
    public static Uri m1409a(File file) {
        synchronized (f1422b) {
            if (f1421a == null) {
                return null;
            }
            Uri contentUriFromFile = f1421a.getContentUriFromFile(file);
            return contentUriFromFile;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        m1411a(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006b, code lost:
        throw r8;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1410a(android.net.Uri r7, android.content.Context r8, java.lang.String r9) {
        /*
            java.lang.String r0 = ""
            if (r7 != 0) goto L_0x0005
            return r0
        L_0x0005:
            android.content.ContentResolver r8 = r8.getContentResolver()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r8
            r2 = r7
            android.database.Cursor r1 = Tc0.a(r1, r2, r3, r4, r5, r6)     // Catch:{ NullPointerException -> 0x0071 }
            r2 = 0
            if (r1 == 0) goto L_0x006c
            int r3 = r1.getCount()     // Catch:{ all -> 0x0065 }
            r4 = 1
            if (r3 < r4) goto L_0x006c
            r1.moveToFirst()     // Catch:{ all -> 0x0065 }
            int r9 = r1.getColumnIndex(r9)     // Catch:{ all -> 0x0065 }
            r3 = -1
            if (r9 != r3) goto L_0x002b
            m1411a(r2, r1)     // Catch:{ NullPointerException -> 0x0071 }
            return r0
        L_0x002b:
            java.lang.String r9 = r1.getString(r9)     // Catch:{ all -> 0x0065 }
            boolean r3 = m1413a((android.database.Cursor) r1)     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x0061
            java.lang.String r3 = "*/*"
            java.lang.String[] r7 = Tc0.a(r8, r7, r3)     // Catch:{ all -> 0x0065 }
            if (r7 == 0) goto L_0x0061
            int r8 = r7.length     // Catch:{ all -> 0x0065 }
            if (r8 <= 0) goto L_0x0061
            android.webkit.MimeTypeMap r8 = android.webkit.MimeTypeMap.getSingleton()     // Catch:{ all -> 0x0065 }
            r3 = 0
            r7 = r7[r3]     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = r8.getExtensionFromMimeType(r7)     // Catch:{ all -> 0x0065 }
            if (r7 == 0) goto L_0x0061
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r8.<init>()     // Catch:{ all -> 0x0065 }
            r8.append(r9)     // Catch:{ all -> 0x0065 }
            java.lang.String r9 = "."
            r8.append(r9)     // Catch:{ all -> 0x0065 }
            r8.append(r7)     // Catch:{ all -> 0x0065 }
            java.lang.String r9 = r8.toString()     // Catch:{ all -> 0x0065 }
        L_0x0061:
            m1411a(r2, r1)     // Catch:{ NullPointerException -> 0x0071 }
            return r9
        L_0x0065:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r8 = move-exception
            m1411a(r7, r1)     // Catch:{ NullPointerException -> 0x0071 }
            throw r8     // Catch:{ NullPointerException -> 0x0071 }
        L_0x006c:
            if (r1 == 0) goto L_0x0071
            m1411a(r2, r1)     // Catch:{ NullPointerException -> 0x0071 }
        L_0x0071:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ContentUriUtils.m1410a(android.net.Uri, android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static /* synthetic */ void m1411a(Throwable th, Cursor cursor) {
        if (th != null) {
            try {
                cursor.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            cursor.close();
        }
    }

    /* renamed from: a */
    public static boolean m1413a(Cursor cursor) {
        int columnIndex;
        if (Build.VERSION.SDK_INT >= 24 && (columnIndex = cursor.getColumnIndex("flags")) > -1 && (cursor.getLong(columnIndex) & 512) != 0) {
            return true;
        }
        return false;
    }
}
