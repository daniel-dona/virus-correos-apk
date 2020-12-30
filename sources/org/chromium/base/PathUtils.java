package org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.system.Os;
import com.google.protobuf.ByteString;
import java.io.File;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public abstract class PathUtils {

    /* renamed from: a */
    public static final AtomicBoolean f1461a = new AtomicBoolean();

    /* renamed from: b */
    public static FutureTask<String[]> f1462b;

    /* renamed from: c */
    public static String f1463c;

    /* renamed from: d */
    public static String f1464d;

    /* renamed from: org.chromium.base.PathUtils$a */
    /* compiled from: PG */
    public static class C0369a {

        /* renamed from: a */
        public static final String[] f1465a = PathUtils.m1445a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        m1444a(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001d, code lost:
        throw r3;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.String[] m1445a() {
        /*
            r0 = 0
            java.util.concurrent.FutureTask<java.lang.String[]> r1 = f1462b     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            r2 = 0
            boolean r1 = r1.cancel(r2)     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            if (r1 == 0) goto L_0x001e
            pO0 r1 = pO0.b()     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            java.lang.String[] r2 = m1446b()     // Catch:{ all -> 0x0017 }
            m1444a(r0, r1)     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            r0 = r2
            goto L_0x0027
        L_0x0017:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r3 = move-exception
            m1444a(r2, r1)     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            throw r3     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
        L_0x001e:
            java.util.concurrent.FutureTask<java.lang.String[]> r1 = f1462b     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            java.lang.Object r1 = r1.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ InterruptedException | ExecutionException -> 0x0027 }
            r0 = r1
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.PathUtils.m1445a():java.lang.String[]");
    }

    /* renamed from: b */
    public static String[] m1446b() {
        String[] strArr = new String[3];
        Context context = RN0.a;
        strArr[0] = context.getDir(f1463c, 0).getPath();
        String str = strArr[0];
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.chmod(str, 448);
            } catch (Exception unused) {
                VN0.a("PathUtils", Eo.b("Failed to set permissions for path \"", str, "\""), new Object[0]);
            }
        }
        strArr[1] = context.getDir("textures", 0).getPath();
        if (context.getCacheDir() != null) {
            if (f1464d == null) {
                strArr[2] = context.getCacheDir().getPath();
            } else {
                strArr[2] = new File(context.getCacheDir(), f1464d).getPath();
            }
        }
        return strArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        m1444a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        throw r2;
     */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] getAllPrivateDownloadsDirectories() {
        /*
            pO0 r0 = pO0.b()
            r1 = 0
            android.content.Context r2 = RN0.a     // Catch:{ all -> 0x0043 }
            java.lang.String r3 = android.os.Environment.DIRECTORY_DOWNLOADS     // Catch:{ all -> 0x0043 }
            java.io.File[] r2 = r2.getExternalFilesDirs(r3)     // Catch:{ all -> 0x0043 }
            m1444a(r1, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
        L_0x0016:
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0036
            r3 = r2[r1]
            if (r3 == 0) goto L_0x0033
            r3 = r2[r1]
            java.lang.String r3 = r3.getAbsolutePath()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x002a
            goto L_0x0033
        L_0x002a:
            r3 = r2[r1]
            java.lang.String r3 = r3.getAbsolutePath()
            r0.add(r3)
        L_0x0033:
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0036:
            int r1 = r0.size()
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            return r0
        L_0x0043:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r2 = move-exception
            m1444a(r1, r0)
            goto L_0x004b
        L_0x004a:
            throw r2
        L_0x004b:
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.PathUtils.getAllPrivateDownloadsDirectories():java.lang.String[]");
    }

    @CalledByNative
    public static String getCacheDirectory() {
        return C0369a.f1465a[2];
    }

    @CalledByNative
    public static String getDataDirectory() {
        return C0369a.f1465a[0];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        m1444a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        throw r2;
     */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDownloadsDirectory() {
        /*
            pO0 r0 = pO0.a()
            boolean r1 = org.chromium.base.BuildInfo.m1372a()     // Catch:{ all -> 0x0024 }
            r2 = 0
            if (r1 == 0) goto L_0x0016
            java.lang.String[] r1 = getAllPrivateDownloadsDirectories()     // Catch:{ all -> 0x0024 }
            r3 = 0
            r1 = r1[r3]     // Catch:{ all -> 0x0024 }
            m1444a(r2, r0)
            return r1
        L_0x0016:
            java.lang.String r1 = android.os.Environment.DIRECTORY_DOWNLOADS     // Catch:{ all -> 0x0024 }
            java.io.File r1 = android.os.Environment.getExternalStoragePublicDirectory(r1)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = r1.getPath()     // Catch:{ all -> 0x0024 }
            m1444a(r2, r0)
            return r1
        L_0x0024:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r2 = move-exception
            m1444a(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.PathUtils.getDownloadsDirectory():java.lang.String");
    }

    @CalledByNative
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    @CalledByNative
    public static String getManagedDownloadsDirectory() {
        String cacheDirectory = getCacheDirectory();
        File file = new File(cacheDirectory, "managed-download");
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (SecurityException e) {
                StringBuilder c = Eo.c("Unable to create managed download directory in [", cacheDirectory, "]: ");
                c.append(e.getMessage());
                VN0.a("PathUtils", c.toString(), new Object[0]);
            }
        }
        return file.getPath();
    }

    @CalledByNative
    public static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = RN0.a.getApplicationInfo();
        int i = applicationInfo.flags;
        if ((i & ByteString.CONCATENATE_BY_COPY_SIZE) != 0 || (i & 1) == 0) {
            return applicationInfo.nativeLibraryDir;
        }
        return "/system/lib/";
    }

    @CalledByNative
    public static String getThumbnailCacheDirectory() {
        return C0369a.f1465a[1];
    }

    /* renamed from: a */
    public static /* synthetic */ void m1444a(Throwable th, pO0 po0) {
        if (th != null) {
            try {
                po0.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            po0.close();
        }
    }

    /* renamed from: a */
    public static void m1443a(String str) {
        if (!f1461a.getAndSet(true)) {
            f1463c = str;
            f1464d = null;
            f1462b = new FutureTask<>(dO0.a);
            AP0.f.execute(f1462b);
        }
    }
}
