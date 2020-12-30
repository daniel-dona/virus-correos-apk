package org.chromium.chrome.browser.crash;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.microsoft.intune.mam.client.app.MAMIntentService;
import com.microsoft.ruby.crash.NativeCrashManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.ChromePreferenceManager;

/* compiled from: PG */
public class MinidumpUploadService extends MAMIntentService {

    /* renamed from: c */
    public static AtomicBoolean f1709c = new AtomicBoolean();

    /* renamed from: d */
    public static AtomicBoolean f1710d = new AtomicBoolean();

    /* renamed from: e */
    public static final String[] f1711e = {"Browser", "Renderer", "GPU", "Other"};

    static {
        Class<MinidumpUploadService> cls = MinidumpUploadService.class;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.app.IntentService] */
    public MinidumpUploadService() {
        super("MinidmpUploadService");
        setIntentRedelivery(true);
    }

    /* renamed from: b */
    public static void m2002b(String str) {
        String a = m2000a(str);
        if ("Browser".equals(a)) {
            f1710d.set(true);
        }
        ChromePreferenceManager.m2734c().mo9031d(a);
    }

    /* renamed from: b */
    public static boolean m2003b() {
        return false;
    }

    @CalledByNative
    public static boolean browserCrashMetricsInitialized() {
        return f1709c.get();
    }

    /* renamed from: c */
    public static void m2005c(String str) {
        String a = m2000a(str.replace("dmp", "up").replace("forced", "up"));
        if ("Browser".equals(a)) {
            f1710d.set(true);
        }
        ChromePreferenceManager.m2734c().mo9032e(a);
    }

    @CalledByNative
    public static boolean didBrowserCrashRecently() {
        return f1710d.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c4, code lost:
        if (r9.renameTo(r12) != false) goto L_0x00c8;
     */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void tryUploadCrashDumpWithLocalId(java.lang.String r12) {
        /*
            java.lang.String r0 = "MinidmpUploadService"
            r1 = 0
            if (r12 == 0) goto L_0x00ea
            boolean r2 = r12.isEmpty()
            if (r2 == 0) goto L_0x000d
            goto L_0x00ea
        L_0x000d:
            YK2 r2 = new YK2
            android.content.Context r3 = RN0.a
            java.io.File r3 = r3.getCacheDir()
            r2.<init>(r3)
            r3 = 0
            java.io.File[] r2 = r2.a(r3)
            int r4 = r2.length
            r5 = 0
        L_0x001f:
            java.lang.String r6 = ".forced"
            java.lang.String r7 = ".skipped"
            java.lang.String r8 = ".dmp"
            if (r5 >= r4) goto L_0x005e
            r9 = r2[r5]
            java.lang.String r10 = r9.getName()
            boolean r10 = r10.contains(r8)
            if (r10 != 0) goto L_0x0048
            java.lang.String r10 = r9.getName()
            boolean r10 = r10.contains(r7)
            if (r10 != 0) goto L_0x0048
            java.lang.String r10 = r9.getName()
            boolean r10 = r10.contains(r6)
            if (r10 != 0) goto L_0x0048
            goto L_0x005b
        L_0x0048:
            java.lang.String r10 = r9.getName()
            java.lang.String r11 = "\\."
            java.lang.String[] r10 = r10.split(r11)
            r10 = r10[r1]
            boolean r10 = r10.endsWith(r12)
            if (r10 == 0) goto L_0x005b
            goto L_0x005f
        L_0x005b:
            int r5 = r5 + 1
            goto L_0x001f
        L_0x005e:
            r9 = r3
        L_0x005f:
            if (r9 != 0) goto L_0x006d
            java.lang.String r2 = "Could not find a crash dump with local ID "
            java.lang.String r12 = Eo.a(r2, r12)
            java.lang.Object[] r1 = new java.lang.Object[r1]
            VN0.c(r0, r12, r1)
            return
        L_0x006d:
            java.lang.String r12 = r9.getName()
            java.lang.String r2 = ".up"
            boolean r12 = r12.contains(r2)
            if (r12 == 0) goto L_0x0092
            java.lang.String r12 = "Refusing to reset upload attempt state for a file that has already been successfully uploaded: "
            java.lang.StringBuilder r12 = Eo.a(r12)
            java.lang.String r2 = r9.getName()
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r4 = "CrashFileManager"
            VN0.c(r4, r12, r2)
            goto L_0x00c7
        L_0x0092:
            java.io.File r12 = new java.io.File
            java.lang.String r2 = r9.getPath()
            int r4 = YK2.c(r2)
            if (r4 <= 0) goto L_0x00b5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = ".try"
            r5.append(r10)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = ".try0"
            java.lang.String r2 = r2.replace(r4, r5)
        L_0x00b5:
            java.lang.String r2 = r2.replace(r7, r6)
            java.lang.String r2 = r2.replace(r8, r6)
            r12.<init>(r2)
            boolean r2 = r9.renameTo(r12)
            if (r2 == 0) goto L_0x00c7
            goto L_0x00c8
        L_0x00c7:
            r12 = r3
        L_0x00c8:
            if (r12 != 0) goto L_0x00e6
            java.lang.String r12 = "Could not rename the file "
            java.lang.StringBuilder r12 = Eo.a(r12)
            java.lang.String r2 = r9.getName()
            r12.append(r2)
            java.lang.String r2 = " for re-upload"
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            VN0.c(r0, r12, r1)
            return
        L_0x00e6:
            m2001b((java.io.File) r12)
            return
        L_0x00ea:
            java.lang.Object[] r12 = new java.lang.Object[r1]
            java.lang.String r1 = "Cannot force crash upload since local crash id is absent."
            VN0.c(r0, r1, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.crash.MinidumpUploadService.tryUploadCrashDumpWithLocalId(java.lang.String):void");
    }

    /* renamed from: a */
    public Integer mo8343a(File file) {
        eL2 a = mo8342a();
        if (!a.a()) {
            VN0.b("MinidmpUploadService", "Minidump upload is not permitted", new Object[0]);
            return 2;
        } else if (!a.b()) {
            VN0.b("MinidmpUploadService", "Minidump cannot currently be uploaded due to network not available.", new Object[0]);
            return 1;
        } else if (!NativeCrashManager.a(file.getAbsolutePath())) {
            return 1;
        } else {
            if (!YK2.d(file)) {
                VN0.c("MinidmpUploadService", "Unable to mark " + file + " as uploaded.", new Object[0]);
                if (!file.delete()) {
                    VN0.c("MinidmpUploadService", Eo.a("Cannot delete ", file), new Object[0]);
                }
            }
            return 0;
        }
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = MinidumpUploadService.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.content.Context, com.microsoft.intune.mam.client.app.MAMIntentService] */
    public AssetManager getAssets() {
        return !gs1.d() ? MinidumpUploadService.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.content.Context, com.microsoft.intune.mam.client.app.MAMIntentService] */
    public Resources getResources() {
        return !gs1.d() ? MinidumpUploadService.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.content.Context, com.microsoft.intune.mam.client.app.MAMIntentService] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? MinidumpUploadService.super.getTheme() : gs1.i(this);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.app.IntentService] */
    public void onHandleIntent(Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(ox0.minidumpuploadservice_notification_id, uc2.a(true, "browser").build());
        }
        if (intent != null) {
            if (!"com.google.android.apps.chrome.crash.ACTION_UPLOAD".equals(intent.getAction())) {
                StringBuilder a = Eo.a("Got unknown action from intent: ");
                a.append(intent.getAction());
                VN0.c("MinidmpUploadService", a.toString(), new Object[0]);
                return;
            }
            String stringExtra = intent.getStringExtra("minidump_file");
            if (stringExtra == null || stringExtra.isEmpty()) {
                VN0.c("MinidmpUploadService", "Cannot upload crash data since minidump is absent.", new Object[0]);
                return;
            }
            File file = new File(stringExtra);
            if (!file.isFile()) {
                VN0.c("MinidmpUploadService", Eo.b("Cannot upload crash data since specified minidump ", stringExtra, " is not present."), new Object[0]);
                return;
            }
            int c = YK2.c(stringExtra);
            if (c >= 3 || c < 0) {
                VN0.a("MinidmpUploadService", Eo.b("Giving up on trying to upload ", stringExtra, " after failing to read a valid attempt number."), new Object[0]);
                return;
            }
            mo8341a(file, new File(intent.getStringExtra("upload_log")));
            int intValue = mo8343a(file).intValue();
            if (intValue == 0) {
                m2005c(stringExtra);
            } else if (intValue == 1) {
                int i = c + 1;
                if (i == 3) {
                    m2002b(stringExtra);
                }
                if (YK2.c(file) == null) {
                    VN0.c("MinidmpUploadService", Eo.a("Failed to rename minidump ", stringExtra), new Object[0]);
                } else if (i < 3) {
                    KJ1.a(getApplicationContext(), mo8342a());
                } else {
                    "Giving up on trying to upload " + stringExtra + "after " + i + " number of tries.";
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.crash.MinidumpUploadService, android.content.Context, com.microsoft.intune.mam.client.app.MAMIntentService] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            MinidumpUploadService.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: b */
    public static void m2001b(File file) throws SecurityException {
        try {
            YK2 yk2 = new YK2(RN0.a.getCacheDir());
            Intent intent = new Intent(RN0.a, MinidumpUploadService.class);
            intent.setAction("com.google.android.apps.chrome.crash.ACTION_UPLOAD");
            intent.putExtra("minidump_file", file.getAbsolutePath());
            intent.putExtra("upload_log", new File(yk2.b(), "uploads.log").getAbsolutePath());
            RN0.a.startService(intent);
        } catch (IllegalStateException e) {
            qI.a.a(e);
        }
    }

    /* renamed from: c */
    public static void m2004c() {
        File[] a = new YK2(RN0.a.getCacheDir()).a(3);
        VN0.b("MinidmpUploadService", "Attempting to upload accumulated crash dumps.", new Object[0]);
        for (File b : a) {
            m2001b(b);
        }
    }

    /* renamed from: a */
    public eL2 mo8342a() {
        return so2.l();
    }

    /* renamed from: a */
    public static String m2000a(String str) {
        BufferedReader bufferedReader;
        IOException e;
        String readLine;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        oO0.a(bufferedReader);
                        return "Other";
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        VN0.c("MinidmpUploadService", "Error while reading crash file %s: %s", new Object[]{str, e.toString()});
                        oO0.a(bufferedReader);
                        return "Other";
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            } while (!readLine.equals("Content-Disposition: form-data; name=\"ptype\""));
            bufferedReader.readLine();
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null) {
                oO0.a(bufferedReader);
                return "Other";
            } else if (readLine2.equals("browser")) {
                oO0.a(bufferedReader);
                return "Browser";
            } else if (readLine2.equals("renderer")) {
                oO0.a(bufferedReader);
                return "Renderer";
            } else if (readLine2.equals("gpu-process")) {
                oO0.a(bufferedReader);
                return "GPU";
            } else {
                oO0.a(bufferedReader);
                return "Other";
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            bufferedReader = null;
            e = iOException;
            VN0.c("MinidmpUploadService", "Error while reading crash file %s: %s", new Object[]{str, e.toString()});
            oO0.a(bufferedReader);
            return "Other";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            oO0.a(bufferedReader);
            throw th;
        }
    }

    /* renamed from: a */
    public ZK2 mo8341a(File file, File file2) {
        return new ZK2(file, file2, mo8342a());
    }
}
