package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.StrictMode;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.google.protobuf.ByteString;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
public abstract class SoLoader {

    /* renamed from: a */
    public static final boolean f841a = true;

    /* renamed from: b */
    public static PE f842b;

    /* renamed from: c */
    public static final ReentrantReadWriteLock f843c = new ReentrantReadWriteLock();

    /* renamed from: d */
    public static RE[] f844d = null;

    /* renamed from: e */
    public static int f845e = 0;

    /* renamed from: f */
    public static aF f846f;

    /* renamed from: g */
    public static BE f847g;

    /* renamed from: h */
    public static final HashSet<String> f848h = new HashSet<>();

    /* renamed from: i */
    public static final Map<String, Object> f849i = new HashMap();

    /* renamed from: j */
    public static final Set<String> f850j = Collections.newSetFromMap(new ConcurrentHashMap());

    /* renamed from: k */
    public static int f851k;

    @DE
    @TargetApi(14)
    /* compiled from: PG */
    public static class Api14Utils {
        /* renamed from: a */
        public static String m697a() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            } else {
                StringBuilder a = Eo.a("ClassLoader ");
                a.append(classLoader.getClass().getName());
                a.append(" should be of type BaseDexClassLoader");
                throw new IllegalStateException(a.toString());
            }
        }
    }

    /* compiled from: PG */
    public static final class WrongAbiError extends UnsatisfiedLinkError {
        public WrongAbiError(Throwable th) {
            super("APK was built for a different platform");
            initCause(th);
        }
    }

    /* renamed from: a */
    public static void m687a(Context context, int i) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            m685a((PE) null);
            m695b(context, i);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    /* renamed from: b */
    public static void m695b(Context context, int i) throws IOException {
        int i2;
        f843c.writeLock().lock();
        try {
            if (f844d == null) {
                f851k = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (int i3 = 0; i3 < split.length; i3++) {
                    "adding system library source: " + split[i3];
                    arrayList.add(new CE(new File(split[i3]), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        f846f = null;
                        arrayList.add(0, new IE(context, "lib-main"));
                    } else {
                        ApplicationInfo applicationInfo = context.getApplicationInfo();
                        if ((applicationInfo.flags & 1) != 0 && (applicationInfo.flags & ByteString.CONCATENATE_BY_COPY_SIZE) == 0) {
                            i2 = 0;
                        } else {
                            f847g = new BE(context, 0);
                            "adding application source: " + f847g.toString();
                            arrayList.add(0, f847g);
                            i2 = 1;
                        }
                        f846f = new AE(context, "lib-main", i2);
                        "adding backup  source: " + f846f.toString();
                        arrayList.add(0, f846f);
                    }
                }
                RE[] reArr = (RE[]) arrayList.toArray(new RE[arrayList.size()]);
                int c = m696c();
                int length = reArr.length;
                while (true) {
                    int i4 = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    "Preparing SO source: " + reArr[i4];
                    reArr[i4].a(c);
                    length = i4;
                }
                f844d = reArr;
                f845e++;
                "init finish: " + f844d.length + " SO sources prepared";
            }
        } finally {
            f843c.writeLock().unlock();
        }
    }

    /* renamed from: c */
    public static int m696c() {
        f843c.writeLock().lock();
        try {
            return (f851k & 2) != 0 ? 1 : 0;
        } finally {
            f843c.writeLock().unlock();
        }
    }

    /* renamed from: a */
    public static void m688a(Context context, boolean z) {
        try {
            m687a(context, z ? 1 : 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static synchronized void m685a(PE pe) {
        String str;
        synchronized (SoLoader.class) {
            if (pe != null) {
                f842b = pe;
                return;
            }
            Runtime runtime = Runtime.getRuntime();
            Method b = m694b();
            boolean z = b != null;
            String a = z ? Api14Utils.m697a() : null;
            if (a == null) {
                str = null;
            } else {
                String[] split = a.split(":");
                ArrayList arrayList = new ArrayList(split.length);
                for (String str2 : split) {
                    if (!str2.contains("!")) {
                        arrayList.add(str2);
                    }
                }
                str = TextUtils.join(":", arrayList);
            }
            f842b = new QE(z, a, str, runtime, b);
        }
    }

    /* renamed from: a */
    public static boolean m690a(String str) {
        return m691a(str, 0);
    }

    /* renamed from: a */
    public static boolean m691a(String str, int i) throws UnsatisfiedLinkError {
        boolean z;
        f843c.readLock().lock();
        try {
            if (f844d == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    m684a();
                } else {
                    synchronized (SoLoader.class) {
                        z = !f848h.contains(str);
                        if (z) {
                            System.loadLibrary(str);
                        }
                    }
                    f843c.readLock().unlock();
                    return z;
                }
            }
            f843c.readLock().unlock();
            return m692a(System.mapLibraryName(str), str, (String) null, i, (StrictMode.ThreadPolicy) null);
        } catch (Throwable th) {
            f843c.readLock().unlock();
            throw th;
        }
    }

    /* renamed from: b */
    public static Method m694b() {
        Method method;
        Class<String> cls = String.class;
        int i = Build.VERSION.SDK_INT;
        if (i < 23) {
            return null;
        }
        if (i <= 27) {
            try {
                method = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{cls, ClassLoader.class, cls});
            } catch (NoSuchMethodException | SecurityException e) {
                Log.w("SoLoader", "Cannot get nativeLoad method", e);
                return null;
            }
        } else {
            method = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{cls, ClassLoader.class});
        }
        method.setAccessible(true);
        return method;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r1 != false) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        if (f848h.contains(r7) == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r9 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004d, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004e, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0050, code lost:
        if (r1 != false) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        "About to load: " + r7;
        m689a(r7, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        "Loaded: " + r7;
        f848h.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007b, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0080, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0081, code lost:
        r8 = r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0085, code lost:
        if (r8 == null) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0094, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0095, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0096, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009c, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a4, code lost:
        if (android.text.TextUtils.isEmpty(r8) != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00ac, code lost:
        if (f850j.contains(r8) == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ae, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00af, code lost:
        if (r9 == null) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00b1, code lost:
        if (r2 != false) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00b5, code lost:
        if (f841a == false) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00b7, code lost:
        android.os.Trace.beginSection("MergedSoMapping.invokeJniOnload[" + r8 + "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        "About to merge: " + r8 + " / " + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00fe, code lost:
        throw new java.lang.IllegalArgumentException("Unknown library: " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00ff, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0102, code lost:
        if (f841a != false) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0104, code lost:
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0107, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0108, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x010b, code lost:
        return !r1;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m692a(java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, android.os.StrictMode.ThreadPolicy r11) {
        /*
            java.lang.Class<com.facebook.soloader.SoLoader> r0 = com.facebook.soloader.SoLoader.class
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            r2 = 0
            if (r1 != 0) goto L_0x0012
            java.util.Set<java.lang.String> r1 = f850j
            boolean r1 = r1.contains(r8)
            if (r1 == 0) goto L_0x0012
            return r2
        L_0x0012:
            monitor-enter(r0)
            java.util.HashSet<java.lang.String> r1 = f848h     // Catch:{ all -> 0x010f }
            boolean r1 = r1.contains(r7)     // Catch:{ all -> 0x010f }
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r9 != 0) goto L_0x0020
            monitor-exit(r0)     // Catch:{ all -> 0x010f }
            return r2
        L_0x0020:
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            java.util.Map<java.lang.String, java.lang.Object> r4 = f849i     // Catch:{ all -> 0x010f }
            boolean r4 = r4.containsKey(r7)     // Catch:{ all -> 0x010f }
            if (r4 == 0) goto L_0x0032
            java.util.Map<java.lang.String, java.lang.Object> r4 = f849i     // Catch:{ all -> 0x010f }
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x010f }
            goto L_0x003c
        L_0x0032:
            java.lang.Object r4 = new java.lang.Object     // Catch:{ all -> 0x010f }
            r4.<init>()     // Catch:{ all -> 0x010f }
            java.util.Map<java.lang.String, java.lang.Object> r5 = f849i     // Catch:{ all -> 0x010f }
            r5.put(r7, r4)     // Catch:{ all -> 0x010f }
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x010f }
            monitor-enter(r4)
            if (r1 != 0) goto L_0x00a0
            monitor-enter(r0)     // Catch:{ all -> 0x010c }
            java.util.HashSet<java.lang.String> r5 = f848h     // Catch:{ all -> 0x009d }
            boolean r5 = r5.contains(r7)     // Catch:{ all -> 0x009d }
            if (r5 == 0) goto L_0x004f
            if (r9 != 0) goto L_0x004e
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            monitor-exit(r4)     // Catch:{ all -> 0x010c }
            return r2
        L_0x004e:
            r1 = 1
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            if (r1 != 0) goto L_0x00a0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            r5.<init>()     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            java.lang.String r6 = "About to load: "
            r5.append(r6)     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            r5.append(r7)     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            r5.toString()     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            m689a(r7, r10, r11)     // Catch:{ IOException -> 0x0096, UnsatisfiedLinkError -> 0x0080 }
            monitor-enter(r0)     // Catch:{ all -> 0x010c }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r10.<init>()     // Catch:{ all -> 0x007d }
            java.lang.String r11 = "Loaded: "
            r10.append(r11)     // Catch:{ all -> 0x007d }
            r10.append(r7)     // Catch:{ all -> 0x007d }
            r10.toString()     // Catch:{ all -> 0x007d }
            java.util.HashSet<java.lang.String> r10 = f848h     // Catch:{ all -> 0x007d }
            r10.add(r7)     // Catch:{ all -> 0x007d }
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            goto L_0x00a0
        L_0x007d:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            throw r7     // Catch:{ all -> 0x010c }
        L_0x0080:
            r7 = move-exception
            java.lang.String r8 = r7.getMessage()     // Catch:{ all -> 0x010c }
            if (r8 == 0) goto L_0x0095
            java.lang.String r9 = "unexpected e_machine:"
            boolean r8 = r8.contains(r9)     // Catch:{ all -> 0x010c }
            if (r8 == 0) goto L_0x0095
            com.facebook.soloader.SoLoader$WrongAbiError r8 = new com.facebook.soloader.SoLoader$WrongAbiError     // Catch:{ all -> 0x010c }
            r8.<init>(r7)     // Catch:{ all -> 0x010c }
            throw r8     // Catch:{ all -> 0x010c }
        L_0x0095:
            throw r7     // Catch:{ all -> 0x010c }
        L_0x0096:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException     // Catch:{ all -> 0x010c }
            r8.<init>(r7)     // Catch:{ all -> 0x010c }
            throw r8     // Catch:{ all -> 0x010c }
        L_0x009d:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009d }
            throw r7     // Catch:{ all -> 0x010c }
        L_0x00a0:
            boolean r10 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x010c }
            if (r10 != 0) goto L_0x00af
            java.util.Set<java.lang.String> r10 = f850j     // Catch:{ all -> 0x010c }
            boolean r10 = r10.contains(r8)     // Catch:{ all -> 0x010c }
            if (r10 == 0) goto L_0x00af
            r2 = 1
        L_0x00af:
            if (r9 == 0) goto L_0x0108
            if (r2 != 0) goto L_0x0108
            boolean r9 = f841a     // Catch:{ all -> 0x010c }
            if (r9 == 0) goto L_0x00d0
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x010c }
            r9.<init>()     // Catch:{ all -> 0x010c }
            java.lang.String r10 = "MergedSoMapping.invokeJniOnload["
            r9.append(r10)     // Catch:{ all -> 0x010c }
            r9.append(r8)     // Catch:{ all -> 0x010c }
            java.lang.String r10 = "]"
            r9.append(r10)     // Catch:{ all -> 0x010c }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x010c }
            android.os.Trace.beginSection(r9)     // Catch:{ all -> 0x010c }
        L_0x00d0:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ff }
            r9.<init>()     // Catch:{ all -> 0x00ff }
            java.lang.String r10 = "About to merge: "
            r9.append(r10)     // Catch:{ all -> 0x00ff }
            r9.append(r8)     // Catch:{ all -> 0x00ff }
            java.lang.String r10 = " / "
            r9.append(r10)     // Catch:{ all -> 0x00ff }
            r9.append(r7)     // Catch:{ all -> 0x00ff }
            r9.toString()     // Catch:{ all -> 0x00ff }
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ff }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ff }
            r9.<init>()     // Catch:{ all -> 0x00ff }
            java.lang.String r10 = "Unknown library: "
            r9.append(r10)     // Catch:{ all -> 0x00ff }
            r9.append(r8)     // Catch:{ all -> 0x00ff }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x00ff }
            r7.<init>(r8)     // Catch:{ all -> 0x00ff }
            throw r7     // Catch:{ all -> 0x00ff }
        L_0x00ff:
            r7 = move-exception
            boolean r8 = f841a     // Catch:{ all -> 0x010c }
            if (r8 == 0) goto L_0x0107
            android.os.Trace.endSection()     // Catch:{ all -> 0x010c }
        L_0x0107:
            throw r7     // Catch:{ all -> 0x010c }
        L_0x0108:
            monitor-exit(r4)     // Catch:{ all -> 0x010c }
            r7 = r1 ^ 1
            return r7
        L_0x010c:
            r7 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x010c }
            throw r7
        L_0x010f:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x010f }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.m692a(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    /* renamed from: b */
    public static File m693b(String str) throws IOException {
        f843c.readLock().lock();
        int i = 0;
        while (i < f844d.length) {
            try {
                File a = f844d[i].a(str);
                if (a != null) {
                    return a;
                }
                i++;
            } finally {
                f843c.readLock().unlock();
            }
        }
        f843c.readLock().unlock();
        throw new FileNotFoundException(str);
    }

    /* renamed from: a */
    public static void m689a(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        boolean z;
        boolean z2;
        f843c.readLock().lock();
        try {
            if (f844d != null) {
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (f841a) {
                    Trace.beginSection("SoLoader.loadLibrary[" + str + "]");
                }
                int i2 = 0;
                do {
                    try {
                        f843c.readLock().lock();
                        int i3 = f845e;
                        int i4 = 0;
                        while (true) {
                            if (i2 == 0) {
                                if (i4 < f844d.length) {
                                    i2 = f844d[i4].a(str, i, threadPolicy);
                                    if (i2 == 3 && f846f != null) {
                                        "Trying backup SoSource for " + str;
                                        f846f.c(str);
                                        i2 = f846f.a(str, i, threadPolicy);
                                        break;
                                    }
                                    i4++;
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        f843c.readLock().unlock();
                        if (i2 == 0) {
                            f843c.writeLock().lock();
                            if (f847g != null && f847g.a()) {
                                f845e++;
                            }
                            z2 = f845e != i3;
                            f843c.writeLock().unlock();
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                    } catch (Throwable th) {
                        if (f841a) {
                            Trace.endSection();
                        }
                        if (z) {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                        if (i2 == 0 || i2 == 3) {
                            String a = Eo.a("couldn't find DSO to load: ", str);
                            String message = th.getMessage();
                            if (message == null) {
                                message = th.toString();
                            }
                            String str2 = a + " caused by: " + message;
                            Log.e("SoLoader", str2);
                            throw new UnsatisfiedLinkError(str2);
                        }
                        return;
                    }
                } while (z2);
                if (f841a) {
                    Trace.endSection();
                }
                if (z) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                if (i2 == 0 || i2 == 3) {
                    String str3 = "couldn't find DSO to load: " + str;
                    Log.e("SoLoader", str3);
                    throw new UnsatisfiedLinkError(str3);
                }
                return;
            }
            Log.e("SoLoader", "Could not load: " + str + " because no SO source exists");
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
        } finally {
            f843c.readLock().unlock();
        }
    }

    /* renamed from: a */
    public static void m684a() {
        f843c.readLock().lock();
        try {
            if (f844d == null) {
                throw new RuntimeException("SoLoader.init() not yet called");
            }
        } finally {
            f843c.readLock().unlock();
        }
    }

    /* renamed from: a */
    public static void m686a(RE re) throws IOException {
        f843c.writeLock().lock();
        try {
            "Prepending to SO sources: " + re;
            m684a();
            re.a(m696c());
            RE[] reArr = new RE[(f844d.length + 1)];
            reArr[0] = re;
            System.arraycopy(f844d, 0, reArr, 1, f844d.length);
            f844d = reArr;
            f845e++;
            "Prepended to SO sources: " + re;
        } finally {
            f843c.writeLock().unlock();
        }
    }
}
