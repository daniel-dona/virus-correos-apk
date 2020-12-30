package org.chromium.base.library_loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.citrix.loggersdk.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.chromium.base.BuildInfo;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.metrics.RecordHistogram;

@MainDex
/* compiled from: PG */
public class LibraryLoader {

    /* renamed from: g */
    public static final boolean f1500g = (Build.VERSION.SDK_INT <= 19);

    /* renamed from: h */
    public static LibraryLoader f1501h = new LibraryLoader();

    /* renamed from: a */
    public volatile boolean f1502a;

    /* renamed from: b */
    public final Object f1503b = new Object();

    /* renamed from: c */
    public boolean f1504c;

    /* renamed from: d */
    public boolean f1505d;

    /* renamed from: e */
    public int f1506e;

    /* renamed from: f */
    public long f1507f;

    /* renamed from: a */
    public static void m1496a(boolean z) {
        Eo.b(QN0.a, "reached_code_profiler_enabled", z);
    }

    /* renamed from: e */
    public static File m1497e() {
        return new File(U5.b(RN0.a), "native_libraries");
    }

    /* renamed from: f */
    public static final /* synthetic */ void m1498f() {
        String str = BuildInfo.C0359b.f1415a.f1412j;
        File[] listFiles = new File(U5.b(RN0.a), "native_libraries").listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!file.getName().contains(str)) {
                    String name = file.getName();
                    if (!file.delete()) {
                        VN0.c("LibraryLoader", "Unable to remove %s", new Object[]{name});
                    } else {
                        VN0.b("LibraryLoader", "Removed obsolete file %s", new Object[]{name});
                    }
                }
            }
        }
    }

    /* renamed from: g */
    public static void m1499g() {
    }

    /* renamed from: h */
    public static boolean m1500h() {
        if (Build.VERSION.SDK_INT >= 24) {
            return false;
        }
        return xO0.a;
    }

    private native String nativeGetVersionNumber();

    private native boolean nativeLibraryLoaded(int i);

    private native void nativeRecordRendererLibraryLoadTime(long j);

    @CalledByNative
    public static void onUmaRecordingReadyInRenderer() {
        FO0.a();
    }

    /* renamed from: b */
    public void mo7906b(Context context) {
        synchronized (this.f1503b) {
            if (!m1500h()) {
                context.getApplicationInfo();
                TraceEvent B = TraceEvent.m1469B("LibraryLoader.preloadAlreadyLocked");
                if (B != null) {
                    m1494a((Throwable) null, B);
                }
            }
        }
    }

    /* renamed from: b */
    public boolean mo7907b() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        m1495a(r8, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        throw r1;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7909c(int r8) throws org.chromium.base.library_loader.ProcessInitException {
        /*
            r7 = this;
            boolean r0 = r7.f1502a
            r1 = 2
            if (r0 == 0) goto L_0x0010
            int r0 = r7.f1506e
            if (r0 != r8) goto L_0x000a
            return
        L_0x000a:
            org.chromium.base.library_loader.ProcessInitException r8 = new org.chromium.base.library_loader.ProcessInitException
            r8.<init>(r1)
            throw r8
        L_0x0010:
            r7.f1506e = r8
            int r0 = r7.f1506e
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L_0x003b
            pO0 r0 = pO0.a()
            android.content.SharedPreferences r5 = QN0.a     // Catch:{ all -> 0x0034 }
            java.lang.String r6 = "reached_code_profiler_enabled"
            boolean r5 = r5.getBoolean(r6, r3)     // Catch:{ all -> 0x0034 }
            m1495a((java.lang.Throwable) r2, (pO0) r0)
            if (r5 == 0) goto L_0x003b
            org.chromium.base.CommandLine r0 = org.chromium.base.CommandLine.m1384c()
            java.lang.String r5 = "enable-reached-code-profiler"
            r0.mo7854a((java.lang.String) r5)
            goto L_0x003b
        L_0x0034:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r1 = move-exception
            m1495a((java.lang.Throwable) r8, (pO0) r0)
            throw r1
        L_0x003b:
            boolean r0 = r7.f1505d
            if (r0 == 0) goto L_0x0040
            goto L_0x0058
        L_0x0040:
            java.util.concurrent.atomic.AtomicReference<org.chromium.base.CommandLine> r0 = org.chromium.base.CommandLine.f1417a
            org.chromium.base.CommandLine$c r5 = new org.chromium.base.CommandLine$c
            java.lang.Object r6 = r0.get()
            org.chromium.base.CommandLine r6 = (org.chromium.base.CommandLine) r6
            if (r6 == 0) goto L_0x0050
            java.lang.String[] r2 = r6.mo7858b()
        L_0x0050:
            r5.<init>(r2)
            r0.set(r5)
            r7.f1505d = r4
        L_0x0058:
            int r0 = r7.f1506e
            boolean r0 = r7.nativeLibraryLoaded(r0)
            java.lang.String r2 = "LibraryLoader"
            if (r0 == 0) goto L_0x00a2
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r1 = xO0.e
            r0[r3] = r1
            java.lang.String r1 = r7.nativeGetVersionNumber()
            r0[r4] = r1
            java.lang.String r1 = "Expected native library version number \"%s\", actual native library version number \"%s\""
            java.lang.String r0 = java.lang.String.format(r1, r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            VN0.b(r2, r0, r1)
            java.lang.String r0 = xO0.e
            java.lang.String r1 = r7.nativeGetVersionNumber()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x009b
            org.chromium.base.TraceEvent.nativeRegisterEnabledObserver()
            if (r8 != r4) goto L_0x0098
            boolean r8 = f1500g
            if (r8 == 0) goto L_0x0098
            java.lang.Thread r8 = new java.lang.Thread
            java.lang.Runnable r0 = uO0.a
            r8.<init>(r0)
            r8.start()
        L_0x0098:
            r7.f1502a = r4
            return
        L_0x009b:
            org.chromium.base.library_loader.ProcessInitException r8 = new org.chromium.base.library_loader.ProcessInitException
            r0 = 3
            r8.<init>(r0)
            throw r8
        L_0x00a2:
            java.lang.Object[] r8 = new java.lang.Object[r3]
            java.lang.String r0 = "error calling nativeLibraryLoaded"
            VN0.a(r2, r0, r8)
            org.chromium.base.library_loader.ProcessInitException r8 = new org.chromium.base.library_loader.ProcessInitException
            r8.<init>(r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.library_loader.LibraryLoader.mo7909c(int):void");
    }

    /* renamed from: d */
    public void mo7910d() {
        synchronized (this.f1503b) {
            if (m1500h()) {
                nativeRecordRendererLibraryLoadTime(this.f1507f);
            }
        }
    }

    /* renamed from: a */
    public void mo7901a(int i) throws ProcessInitException {
        synchronized (this.f1503b) {
            if (!this.f1502a) {
                mo7903a(RN0.a.getApplicationInfo(), false);
                mo7909c(i);
            }
        }
    }

    /* renamed from: b */
    public void mo7905b(int i) throws ProcessInitException {
        synchronized (this.f1503b) {
            mo7909c(i);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m1495a(Throwable th, pO0 po0) {
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
    public static /* synthetic */ void m1494a(Throwable th, TraceEvent traceEvent) {
        if (th != null) {
            try {
                traceEvent.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            traceEvent.close();
        }
    }

    /* renamed from: a */
    public boolean mo7904a() {
        return this.f1502a;
    }

    /* renamed from: a */
    public void mo7902a(Context context) throws ProcessInitException {
        synchronized (this.f1503b) {
            if (this.f1504c) {
                if (context != RN0.a) {
                    throw new IllegalStateException("Attempt to load again from alternate context.");
                }
            }
            mo7903a(context.getApplicationInfo(), false);
        }
    }

    /* renamed from: a */
    public static String m1492a(ApplicationInfo applicationInfo, String str) {
        VN0.c("LibraryLoader", "Failed to load libName %s, attempting fallback extraction then trying again", new Object[]{str});
        String a = m1493a(str, false, false);
        if (!RN0.f()) {
            File b = U5.b(RN0.a);
            File file = new File(b, "native_libraries");
            b.mkdir();
            b.setExecutable(true, false);
            file.mkdir();
            file.setExecutable(true, false);
        }
        File e = m1497e();
        String str2 = applicationInfo.sourceDir;
        File file2 = new File(e, new File(a).getName() + BuildInfo.C0359b.f1415a.f1412j);
        if (!file2.exists()) {
            ZipFile zipFile = null;
            try {
                ZipFile zipFile2 = new ZipFile(str2);
                try {
                    ZipEntry entry = zipFile2.getEntry(a);
                    if (entry != null) {
                        UN0.a(zipFile2.getInputStream(entry), file2);
                        file2.setReadable(true, false);
                        file2.setExecutable(true, false);
                        oO0.a(zipFile2);
                    } else {
                        throw new RuntimeException("Cannot find ZipEntry" + a);
                    }
                } catch (IOException e2) {
                    e = e2;
                    zipFile = zipFile2;
                    try {
                        throw new RuntimeException(e);
                    } catch (Throwable th) {
                        th = th;
                        zipFile2 = zipFile;
                        oO0.a(zipFile2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    oO0.a(zipFile2);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                throw new RuntimeException(e);
            }
        }
        return file2.getAbsolutePath();
    }

    /* renamed from: c */
    public void mo7908c() {
        synchronized (this.f1503b) {
            if (m1500h()) {
                RecordHistogram.m1550d("ChromiumAndroidLinker.BrowserLoadTime", this.f1507f);
            }
        }
    }

    @SuppressLint({"DefaultLocale", "UnsafeDynamicallyLoadedCode"})
    /* renamed from: a */
    public final void mo7903a(ApplicationInfo applicationInfo, boolean z) throws ProcessInitException {
        String str;
        Linker h;
        String str2;
        int i;
        String str3;
        ApplicationInfo applicationInfo2 = applicationInfo;
        try {
            TraceEvent B = TraceEvent.m1469B("LibraryLoader.loadAlreadyLocked");
            try {
                if (!this.f1504c) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    if (!m1500h() || z) {
                        TraceEvent B2 = TraceEvent.m1469B("LibraryLoader.preloadAlreadyLocked");
                        if (B2 != null) {
                            m1494a((Throwable) null, B2);
                        }
                        String[] strArr = xO0.d;
                        int length = strArr.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            str = strArr[i2];
                            if (!xO0.b) {
                                VN0.b("LibraryLoader", "libraryName: " + str, new Object[0]);
                                System.loadLibrary(str);
                            } else {
                                boolean is64Bit = Process.is64Bit();
                                String str4 = applicationInfo2.sourceDir + "!/" + m1493a(str, true, is64Bit);
                                VN0.b("LibraryLoader", "libraryName: " + str4, new Object[0]);
                                System.load(str4);
                            }
                        }
                    } else {
                        h = Linker.m1516h();
                        String str5 = xO0.b ? applicationInfo2.sourceDir : null;
                        h.mo7897d(str5);
                        String[] strArr2 = xO0.d;
                        int length2 = strArr2.length;
                        int i3 = 0;
                        while (i3 < length2) {
                            str2 = strArr2[i3];
                            if (h.mo7915a(str2)) {
                                i = length2;
                                str3 = str5;
                            } else {
                                String mapLibraryName = System.mapLibraryName(str2);
                                if (str5 != null) {
                                    StringBuilder sb = new StringBuilder();
                                    i = length2;
                                    sb.append(" Loading ");
                                    sb.append(str2);
                                    sb.append(" from within ");
                                    sb.append(str5);
                                    str3 = str5;
                                    VN0.b("LibraryLoader", sb.toString(), new Object[0]);
                                } else {
                                    i = length2;
                                    str3 = str5;
                                    VN0.b("LibraryLoader", "Loading " + str2, new Object[0]);
                                }
                                try {
                                    h.mo7917b(mapLibraryName);
                                } catch (UnsatisfiedLinkError unused) {
                                    VN0.c("LibraryLoader", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
                                    h.mo7918c(mapLibraryName);
                                }
                            }
                            i3++;
                            length2 = i;
                            str5 = str3;
                        }
                        h.mo7893b();
                    }
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    this.f1507f = uptimeMillis2 - uptimeMillis;
                    VN0.b("LibraryLoader", String.format("Time to load native libraries: %d ms (timestamps %d-%d)", new Object[]{Long.valueOf(this.f1507f), Long.valueOf(uptimeMillis % 10000), Long.valueOf(uptimeMillis2 % 10000)}), new Object[0]);
                    this.f1504c = true;
                }
                if (B != null) {
                    m1494a((Throwable) null, B);
                }
            } catch (UnsatisfiedLinkError e) {
                VN0.a("LibraryLoader", "Unable to load library: " + str, new Object[0]);
                throw e;
            } catch (UnsatisfiedLinkError e2) {
                if (xO0.b || !f1500g) {
                    VN0.a("LibraryLoader", "Unable to load library: " + str2, new Object[0]);
                    throw e2;
                }
                String a = m1492a(applicationInfo2, str2);
                try {
                    h.mo7917b(a);
                } catch (UnsatisfiedLinkError unused2) {
                    VN0.c("LibraryLoader", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
                    h.mo7918c(a);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                try {
                    throw th2;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    if (B != null) {
                        m1494a(th2, B);
                    }
                    throw th4;
                }
            }
        } catch (UnsatisfiedLinkError e3) {
            throw new ProcessInitException(2, e3);
        }
    }

    /* renamed from: a */
    public static String m1493a(String str, boolean z, boolean z2) {
        String str2;
        int i = xO0.f;
        if (i == 1) {
            str2 = z2 ? "arm64-v8a" : "armeabi-v7a";
        } else if (i == 2) {
            str2 = z2 ? "mips64" : "mips";
        } else if (i == 3) {
            str2 = z2 ? "x86_64" : "x86";
        } else {
            throw new RuntimeException("Unknown CPU ABI for native libraries");
        }
        return String.format("lib/%s/%s%s", new Object[]{str2, z ? "crazy." : BuildConfig.FLAVOR, System.mapLibraryName(str)});
    }
}
