package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.library_loader.Linker;

/* compiled from: PG */
public class LegacyLinker extends Linker {

    /* renamed from: d */
    public boolean f1493d;

    /* renamed from: e */
    public boolean f1494e = true;

    /* renamed from: f */
    public boolean f1495f;

    /* renamed from: g */
    public Bundle f1496g;

    /* renamed from: h */
    public long f1497h = -1;

    /* renamed from: i */
    public long f1498i = -1;

    /* renamed from: j */
    public HashMap<String, Linker.LibInfo> f1499j;

    public static native boolean nativeAddZipArchivePath(String str);

    public static native boolean nativeCreateSharedRelro(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeUseSharedRelro(String str, Linker.LibInfo libInfo);

    /* renamed from: a */
    public void mo7890a() {
        synchronized (Linker.f1512b) {
            mo7899i();
            this.f1494e = false;
            this.f1495f = false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:13|14|15|16|29|26|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0020, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002a */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7893b() {
        /*
            r2 = this;
            java.lang.Object r0 = org.chromium.base.library_loader.Linker.f1512b
            monitor-enter(r0)
            r2.mo7899i()     // Catch:{ all -> 0x004a }
            java.util.HashMap<java.lang.String, org.chromium.base.library_loader.Linker$LibInfo> r1 = r2.f1499j     // Catch:{ all -> 0x004a }
            if (r1 != 0) goto L_0x000b
            goto L_0x003f
        L_0x000b:
            boolean r1 = r2.f1494e     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x001c
            java.util.HashMap<java.lang.String, org.chromium.base.library_loader.Linker$LibInfo> r1 = r2.f1499j     // Catch:{ all -> 0x004a }
            android.os.Bundle r1 = r2.mo7916b((java.util.HashMap<java.lang.String, org.chromium.base.library_loader.Linker.LibInfo>) r1)     // Catch:{ all -> 0x004a }
            r2.f1496g = r1     // Catch:{ all -> 0x004a }
            android.os.Bundle r1 = r2.f1496g     // Catch:{ all -> 0x004a }
            r2.mo7896c(r1)     // Catch:{ all -> 0x004a }
        L_0x001c:
            boolean r1 = r2.f1495f     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x003f
        L_0x0020:
            android.os.Bundle r1 = r2.f1496g     // Catch:{ all -> 0x004a }
            if (r1 != 0) goto L_0x0032
            java.lang.Object r1 = org.chromium.base.library_loader.Linker.f1512b     // Catch:{ InterruptedException -> 0x002a }
            r1.wait()     // Catch:{ InterruptedException -> 0x002a }
            goto L_0x0020
        L_0x002a:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x004a }
            r1.interrupt()     // Catch:{ all -> 0x004a }
            goto L_0x0020
        L_0x0032:
            android.os.Bundle r1 = r2.f1496g     // Catch:{ all -> 0x004a }
            r2.mo7896c(r1)     // Catch:{ all -> 0x004a }
            android.os.Bundle r1 = r2.f1496g     // Catch:{ all -> 0x004a }
            r1.clear()     // Catch:{ all -> 0x004a }
            r1 = 0
            r2.f1496g = r1     // Catch:{ all -> 0x004a }
        L_0x003f:
            boolean r1 = xO0.c     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0048
            boolean r1 = r2.f1494e     // Catch:{ all -> 0x004a }
            r2.mo7914a((boolean) r1)     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004d:
            throw r1
        L_0x004e:
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.library_loader.LegacyLinker.mo7893b():void");
    }

    /* renamed from: c */
    public long mo7895c() {
        synchronized (Linker.f1512b) {
            mo7899i();
            if (!this.f1494e) {
                VN0.c("LegacyLinker", "Shared RELRO sections are disabled in this process!", new Object[0]);
                return 0;
            }
            mo7900j();
            long j = this.f1497h;
            return j;
        }
    }

    /* renamed from: d */
    public void mo7897d(String str) {
        synchronized (Linker.f1512b) {
            mo7899i();
            if (str != null) {
                nativeAddZipArchivePath(str);
            }
            if (this.f1494e) {
                mo7900j();
            }
        }
    }

    /* renamed from: e */
    public Bundle mo7898e() {
        synchronized (Linker.f1512b) {
            if (!this.f1494e) {
                return null;
            }
            Bundle bundle = this.f1496g;
            return bundle;
        }
    }

    /* renamed from: i */
    public final void mo7899i() {
        if (!this.f1493d) {
            LibraryLoader.m1499g();
            try {
                System.loadLibrary("chromium_android_linker");
            } catch (UnsatisfiedLinkError unused) {
                if (LibraryLoader.f1500g) {
                    System.load(LibraryLoader.m1492a(RN0.a.getApplicationInfo(), "chromium_android_linker"));
                }
            }
            this.f1493d = true;
        }
    }

    /* renamed from: j */
    public final void mo7900j() {
        if (this.f1497h == -1) {
            this.f1497h = mo7919d();
            long j = this.f1497h;
            this.f1498i = j;
            if (j == 0) {
                VN0.c("LegacyLinker", "Disabling shared RELROs due address space pressure", new Object[0]);
                this.f1495f = false;
            }
        }
    }

    /* renamed from: a */
    public void mo7891a(long j) {
        synchronized (Linker.f1512b) {
            mo7899i();
            this.f1494e = false;
            this.f1495f = true;
            this.f1497h = j;
            this.f1498i = j;
        }
    }

    /* renamed from: c */
    public final void mo7896c(Bundle bundle) {
        if (bundle != null && this.f1499j != null) {
            HashMap<String, Linker.LibInfo> a = mo7912a(bundle);
            for (Map.Entry next : a.entrySet()) {
                String str = (String) next.getKey();
                if (!nativeUseSharedRelro(str, (Linker.LibInfo) next.getValue())) {
                    VN0.c("LegacyLinker", "Could not use shared RELRO section for %s", new Object[]{str});
                }
            }
            if (!this.f1494e) {
                mo7913a(a);
            }
        }
    }

    /* renamed from: a */
    public void mo7892a(String str, boolean z) {
        long j;
        String str2 = str;
        synchronized (Linker.f1512b) {
            mo7899i();
            if (this.f1499j == null) {
                this.f1499j = new HashMap<>();
            }
            if (!this.f1499j.containsKey(str2)) {
                Linker.LibInfo libInfo = new Linker.LibInfo();
                if (!z || (!this.f1494e && !this.f1495f)) {
                    j = 0;
                } else {
                    j = this.f1498i;
                    if (j > this.f1497h + 201326592) {
                        String str3 = "Load address outside reservation, for: " + str2;
                        VN0.a("LegacyLinker", str3, new Object[0]);
                        throw new UnsatisfiedLinkError(str3);
                    }
                }
                if (nativeLoadLibrary(str2, j, libInfo)) {
                    if (xO0.c) {
                        VN0.b("LegacyLinker", String.format(Locale.US, "%s: %s %x", new Object[]{this.f1494e ? "BROWSER_LIBRARY_ADDRESS" : "RENDERER_LIBRARY_ADDRESS", str2, Long.valueOf(libInfo.mLoadAddress)}), new Object[0]);
                    }
                    if (this.f1494e && !nativeCreateSharedRelro(str2, this.f1498i, libInfo)) {
                        VN0.c("LegacyLinker", String.format(Locale.US, "Could not create shared RELRO for %s at %x", new Object[]{str2, Long.valueOf(this.f1498i)}), new Object[0]);
                    }
                    if (!(j == 0 || this.f1498i == 0)) {
                        this.f1498i = libInfo.mLoadAddress + libInfo.mLoadSize;
                    }
                    this.f1499j.put(str2, libInfo);
                    return;
                }
                String str4 = "Unable to load library: " + str2;
                VN0.a("LegacyLinker", str4, new Object[0]);
                throw new UnsatisfiedLinkError(str4);
            }
        }
    }

    /* renamed from: b */
    public void mo7894b(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null) {
            bundle.setClassLoader(Linker.LibInfo.class.getClassLoader());
            bundle2 = new Bundle(Linker.LibInfo.class.getClassLoader());
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            bundle2.readFromParcel(obtain);
            obtain.recycle();
        } else {
            bundle2 = null;
        }
        synchronized (Linker.f1512b) {
            this.f1496g = bundle2;
            Linker.f1512b.notifyAll();
        }
    }
}
