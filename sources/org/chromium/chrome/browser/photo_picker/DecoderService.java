package org.chromium.chrome.browser.photo_picker;

import Zk2;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.microsoft.intune.mam.client.app.MAMService;
import org.chromium.base.CommandLine;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.task.PostTask;

@MainDex
/* compiled from: PG */
public class DecoderService extends MAMService {

    /* renamed from: c */
    public boolean f2142c;

    /* renamed from: d */
    public final Zk2.a f2143d = new C0427a();

    public static native void nativeInitializePhotoPickerSandbox();

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = DecoderService.super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMService, org.chromium.chrome.browser.photo_picker.DecoderService] */
    public AssetManager getAssets() {
        return !gs1.d() ? DecoderService.super.getAssets() : gs1.g(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMService, org.chromium.chrome.browser.photo_picker.DecoderService] */
    public Resources getResources() {
        return !gs1.d() ? DecoderService.super.getResources() : gs1.h(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMService, org.chromium.chrome.browser.photo_picker.DecoderService] */
    public Resources.Theme getTheme() {
        return !gs1.d() ? DecoderService.super.getTheme() : gs1.i(this);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.app.Service, org.chromium.chrome.browser.photo_picker.DecoderService] */
    public void onCreate() {
        if (!CommandLine.m1386d()) {
            CommandLine.m1383b((String[]) null);
        }
        try {
            PostTask.m1568b(iQ2.a, Uk2.a);
            LibraryLoader.f1501h.mo7901a(2);
            nativeInitializePhotoPickerSandbox();
            this.f2142c = true;
        } catch (ProcessInitException e) {
            VN0.a("ImageDecoder", "Unable to initialize the native library and sandbox", new Object[]{e});
        }
        DecoderService.super.onCreate();
    }

    public IBinder onMAMBind(Intent intent) {
        return this.f2143d;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.microsoft.intune.mam.client.app.MAMService, org.chromium.chrome.browser.photo_picker.DecoderService] */
    public void setTheme(int i) {
        if (!gs1.d()) {
            DecoderService.super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: org.chromium.chrome.browser.photo_picker.DecoderService$a */
    /* compiled from: PG */
    public class C0427a extends Zk2.a {
        public C0427a() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x00ee  */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo8995a(android.os.Bundle r19, cl2 r20) {
            /*
                r18 = this;
                r1 = r18
                r0 = r19
                r2 = r20
                java.lang.String r3 = "success"
                java.lang.String r4 = ") "
                java.lang.String r5 = "file_path"
                java.lang.String r6 = " (size: "
                java.lang.String r7 = "ImageDecoder"
                java.lang.String r8 = ""
                r9 = 0
                r10 = 0
                java.lang.String r8 = r0.getString(r5)     // Catch:{ Exception -> 0x00c7 }
                java.lang.String r11 = "file_descriptor"
                android.os.Parcelable r11 = r0.getParcelable(r11)     // Catch:{ Exception -> 0x00c7 }
                android.os.ParcelFileDescriptor r11 = (android.os.ParcelFileDescriptor) r11     // Catch:{ Exception -> 0x00c7 }
                java.lang.String r12 = "size"
                int r12 = r0.getInt(r12)     // Catch:{ Exception -> 0x00c7 }
                android.os.Bundle r13 = new android.os.Bundle     // Catch:{ Exception -> 0x00c4 }
                r13.<init>()     // Catch:{ Exception -> 0x00c4 }
                r13.putString(r5, r8)     // Catch:{ Exception -> 0x00c2 }
                r13.putBoolean(r3, r9)     // Catch:{ Exception -> 0x00c2 }
                org.chromium.chrome.browser.photo_picker.DecoderService r0 = org.chromium.chrome.browser.photo_picker.DecoderService.this     // Catch:{ Exception -> 0x00c2 }
                boolean r0 = r0.f2142c     // Catch:{ Exception -> 0x00c2 }
                r5 = 1
                if (r0 != 0) goto L_0x004c
                java.lang.String r0 = "Decode failed %s (size: %d): no sandbox"
                r3 = 2
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x00c2 }
                r3[r9] = r8     // Catch:{ Exception -> 0x00c2 }
                java.lang.Integer r10 = java.lang.Integer.valueOf(r12)     // Catch:{ Exception -> 0x00c2 }
                r3[r5] = r10     // Catch:{ Exception -> 0x00c2 }
                VN0.a(r7, r0, r3)     // Catch:{ Exception -> 0x00c2 }
                r1.mo8996a((cl2) r2, (android.os.Bundle) r13)     // Catch:{ Exception -> 0x00c2 }
                return
            L_0x004c:
                java.io.FileDescriptor r0 = r11.getFileDescriptor()     // Catch:{ Exception -> 0x00c2 }
                long r14 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x00c2 }
                android.graphics.Bitmap r10 = Tk2.a(r0, r12)     // Catch:{ Exception -> 0x00c2 }
                long r16 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x00c2 }
                long r14 = r16 - r14
                r11.close()     // Catch:{ IOException -> 0x0062 }
                goto L_0x0086
            L_0x0062:
                r0 = move-exception
                r11 = r0
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
                r0.<init>()     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r5 = "Closing failed "
                r0.append(r5)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r8)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r6)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r12)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r4)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r11)     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00c2 }
                java.lang.Object[] r5 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00c2 }
                VN0.a(r7, r0, r5)     // Catch:{ Exception -> 0x00c2 }
            L_0x0086:
                if (r10 != 0) goto L_0x00ad
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
                r0.<init>()     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r3 = "Decode failed "
                r0.append(r3)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r8)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r6)     // Catch:{ Exception -> 0x00c2 }
                r0.append(r12)     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r3 = ")"
                r0.append(r3)     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00c2 }
                java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00c2 }
                VN0.a(r7, r0, r3)     // Catch:{ Exception -> 0x00c2 }
                r1.mo8996a((cl2) r2, (android.os.Bundle) r13)     // Catch:{ Exception -> 0x00c2 }
                return
            L_0x00ad:
                java.lang.String r0 = "image_bitmap"
                r13.putParcelable(r0, r10)     // Catch:{ Exception -> 0x00c2 }
                r5 = 1
                r13.putBoolean(r3, r5)     // Catch:{ Exception -> 0x00c2 }
                java.lang.String r0 = "decode_time"
                r13.putLong(r0, r14)     // Catch:{ Exception -> 0x00c2 }
                r1.mo8996a((cl2) r2, (android.os.Bundle) r13)     // Catch:{ Exception -> 0x00c2 }
                r10.recycle()     // Catch:{ Exception -> 0x00c2 }
                goto L_0x00f1
            L_0x00c2:
                r0 = move-exception
                goto L_0x00ca
            L_0x00c4:
                r0 = move-exception
                r13 = r10
                goto L_0x00ca
            L_0x00c7:
                r0 = move-exception
                r13 = r10
                r12 = 0
            L_0x00ca:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r5 = "Unexpected error during decoding "
                r3.append(r5)
                r3.append(r8)
                r3.append(r6)
                r3.append(r12)
                r3.append(r4)
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                java.lang.Object[] r3 = new java.lang.Object[r9]
                VN0.a(r7, r0, r3)
                if (r13 == 0) goto L_0x00f1
                r1.mo8996a((cl2) r2, (android.os.Bundle) r13)
            L_0x00f1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.photo_picker.DecoderService.C0427a.mo8995a(android.os.Bundle, cl2):void");
        }

        /* renamed from: a */
        public final void mo8996a(cl2 cl2, Bundle bundle) {
            try {
                cl2.e(bundle);
            } catch (RemoteException e) {
                VN0.a("ImageDecoder", "Remote error while replying: " + e, new Object[0]);
            }
        }
    }
}
