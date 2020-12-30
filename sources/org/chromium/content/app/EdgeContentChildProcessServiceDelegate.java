package org.chromium.content.app;

import LP2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.Surface;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.task.PostTask;
import org.chromium.content.common.SurfaceWrapper;

@MainDex
/* compiled from: PG */
public class EdgeContentChildProcessServiceDelegate implements rP0 {

    /* renamed from: a */
    public jM2 f2384a;

    /* renamed from: b */
    public int f2385b;

    /* renamed from: c */
    public LP2 f2386c;

    /* renamed from: d */
    public int f2387d;

    /* renamed from: e */
    public long f2388e;

    /* renamed from: f */
    public SparseArray<String> f2389f;

    public EdgeContentChildProcessServiceDelegate() {
        if (!BuildInfo.m1373b()) {
            Thread.setDefaultUncaughtExceptionHandler(new lM2());
        }
    }

    @CalledByNative
    private void forwardSurfaceForSurfaceRequest(UnguessableToken unguessableToken, Surface surface) {
        LP2 lp2 = this.f2386c;
        if (lp2 == null) {
            VN0.a("EdgeContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return;
        }
        try {
            lp2.a(unguessableToken, surface);
        } catch (RemoteException e) {
            VN0.a("EdgeContentCPSDelegate", "Unable to call forwardSurfaceForSurfaceRequest: %s", new Object[]{e});
        } finally {
            surface.release();
        }
    }

    @CalledByNative
    private SurfaceWrapper getViewSurface(int i) {
        LP2 lp2 = this.f2386c;
        if (lp2 == null) {
            VN0.a("EdgeContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return null;
        }
        try {
            return lp2.g(i);
        } catch (RemoteException e) {
            VN0.a("EdgeContentCPSDelegate", "Unable to call getViewSurface: %s", new Object[]{e});
            return null;
        }
    }

    private native void nativeInitChildProcess(int i, long j);

    private native void nativeRetrieveFileDescriptorsIdsToKeys();

    @CalledByNative
    private void setFileDescriptorsIdsToKeys(int[] iArr, String[] strArr) {
        this.f2389f = new SparseArray<>();
        for (int i = 0; i < iArr.length; i++) {
            this.f2389f.put(iArr[i], strArr[i]);
        }
    }

    /* renamed from: a */
    public void mo9649a(Intent intent) {
        Bundle extras = intent.getExtras();
        this.f2384a = (!extras.containsKey("org.chromium.content.common.linker_params.base_load_address") || !extras.containsKey("org.chromium.content.common.linker_params.wait_for_shared_relro") || !extras.containsKey("org.chromium.content.common.linker_params.test_runner_class_name")) ? null : new jM2(extras);
        this.f2385b = intent.getExtras().getInt("org.chromium.content.common.child_service_params.library_process_type", 2);
    }

    /* renamed from: b */
    public void mo9652b() {
        nQ2.a = true;
    }

    /* renamed from: c */
    public SparseArray<String> mo9654c() {
        return this.f2389f;
    }

    /* renamed from: d */
    public void mo9655d() {
        ContentMain.nativeStart(false);
    }

    /* renamed from: e */
    public final boolean mo9656e() {
        try {
            LibraryLoader.f1501h.mo7905b(this.f2385b);
            nativeRetrieveFileDescriptorsIdsToKeys();
            return true;
        } catch (ProcessInitException e) {
            VN0.c("EdgeContentCPSDelegate", "startup failed: %s", new Object[]{e});
            return false;
        }
    }

    /* renamed from: b */
    public void mo9653b(Context context) {
        LibraryLoader.f1501h.mo7906b(context);
    }

    /* renamed from: a */
    public void mo9650a(Bundle bundle, List<IBinder> list) {
        Bundle bundle2;
        this.f2386c = (list == null || list.isEmpty()) ? null : LP2.a.a(list.get(0));
        this.f2387d = bundle.getInt("com.google.android.apps.chrome.extra.cpu_count");
        this.f2388e = bundle.getLong("com.google.android.apps.chrome.extra.cpu_features");
        if (LibraryLoader.m1500h() && !LibraryLoader.f1501h.mo7907b() && (bundle2 = bundle.getBundle("org.chromium.base.android.linker.shared_relros")) != null) {
            if (Linker.m1515g()) {
                Linker.m1514e(this.f2384a.c);
            }
            Linker.m1516h().mo7894b(bundle2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0078 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo9651a(android.content.Context r8) {
        /*
            r7 = this;
            java.lang.String r0 = "EdgeContentCPSDelegate"
            org.chromium.base.library_loader.LibraryLoader r1 = org.chromium.base.library_loader.LibraryLoader.f1501h
            boolean r1 = r1.mo7907b()
            if (r1 == 0) goto L_0x000f
            boolean r8 = r7.mo9656e()
            return r8
        L_0x000f:
            r1 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            org.chromium.base.JNIUtils.f1440a = r2
            r2 = 0
            boolean r3 = org.chromium.base.library_loader.LibraryLoader.m1500h()
            r4 = 0
            if (r3 == 0) goto L_0x0040
            boolean r2 = org.chromium.base.library_loader.Linker.m1515g()
            if (r2 == 0) goto L_0x002b
            jM2 r2 = r7.f2384a
            java.lang.String r2 = r2.c
            org.chromium.base.library_loader.Linker.m1514e(r2)
        L_0x002b:
            org.chromium.base.library_loader.Linker r2 = org.chromium.base.library_loader.Linker.m1516h()
            jM2 r3 = r7.f2384a
            boolean r5 = r3.b
            if (r5 == 0) goto L_0x003d
            long r5 = r3.a
            r2.mo7891a((long) r5)
            r3 = r2
            r2 = 1
            goto L_0x0042
        L_0x003d:
            r2.mo7890a()
        L_0x0040:
            r3 = r2
            r2 = 0
        L_0x0042:
            org.chromium.base.library_loader.LibraryLoader r5 = org.chromium.base.library_loader.LibraryLoader.f1501h     // Catch:{ ProcessInitException -> 0x0049 }
            r5.mo7902a((android.content.Context) r8)     // Catch:{ ProcessInitException -> 0x0049 }
            r5 = 1
            goto L_0x005e
        L_0x0049:
            r5 = move-exception
            if (r2 == 0) goto L_0x0054
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.String r6 = "Failed to load native library with shared RELRO, retrying without"
            VN0.c(r0, r6, r5)
            goto L_0x005d
        L_0x0054:
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r4] = r5
            java.lang.String r5 = "Failed to load native library"
            VN0.a(r0, r5, r6)
        L_0x005d:
            r5 = 0
        L_0x005e:
            if (r5 != 0) goto L_0x0075
            if (r2 == 0) goto L_0x0075
            r3.mo7890a()
            org.chromium.base.library_loader.LibraryLoader r2 = org.chromium.base.library_loader.LibraryLoader.f1501h     // Catch:{ ProcessInitException -> 0x006b }
            r2.mo7902a((android.content.Context) r8)     // Catch:{ ProcessInitException -> 0x006b }
            goto L_0x0076
        L_0x006b:
            r8 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r8
            java.lang.String r8 = "Failed to load native library on retry"
            VN0.a(r0, r8, r1)
        L_0x0075:
            r1 = r5
        L_0x0076:
            if (r1 != 0) goto L_0x0079
            return r4
        L_0x0079:
            org.chromium.base.library_loader.LibraryLoader r8 = org.chromium.base.library_loader.LibraryLoader.f1501h
            r8.mo7910d()
            boolean r8 = r7.mo9656e()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.app.EdgeContentChildProcessServiceDelegate.mo9651a(android.content.Context):boolean");
    }

    /* renamed from: a */
    public void mo9648a() {
        nativeInitChildProcess(this.f2387d, this.f2388e);
        PostTask.m1566a(iQ2.a, kM2.a, 0);
    }
}
