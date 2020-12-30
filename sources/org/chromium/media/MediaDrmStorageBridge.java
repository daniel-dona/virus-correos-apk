package org.chromium.media;

import android.annotation.TargetApi;
import org.chromium.base.Callback;
import org.chromium.base.annotations.MainDex;

@MainDex
@TargetApi(23)
/* compiled from: PG */
public class MediaDrmStorageBridge {

    /* renamed from: a */
    public long f2521a;

    @MainDex
    /* compiled from: PG */
    public static class PersistentInfo {

        /* renamed from: a */
        public final byte[] f2522a;

        /* renamed from: b */
        public final byte[] f2523b;

        /* renamed from: c */
        public final String f2524c;

        /* renamed from: d */
        public final int f2525d;

        public PersistentInfo(byte[] bArr, byte[] bArr2, String str, int i) {
            this.f2522a = bArr;
            this.f2523b = bArr2;
            this.f2524c = str;
            this.f2525d = i;
        }

        public static PersistentInfo create(byte[] bArr, byte[] bArr2, String str, int i) {
            return new PersistentInfo(bArr, bArr2, str, i);
        }

        public byte[] emeId() {
            return this.f2522a;
        }

        public byte[] keySetId() {
            return this.f2523b;
        }

        public int keyType() {
            return this.f2525d;
        }

        public String mimeType() {
            return this.f2524c;
        }
    }

    public MediaDrmStorageBridge(long j) {
        this.f2521a = j;
    }

    private native void nativeOnClearInfo(long j, byte[] bArr, Callback<Boolean> callback);

    private native void nativeOnLoadInfo(long j, byte[] bArr, Callback<PersistentInfo> callback);

    private native void nativeOnProvisioned(long j, Callback<Boolean> callback);

    private native void nativeOnSaveInfo(long j, PersistentInfo persistentInfo, Callback<Boolean> callback);

    /* renamed from: a */
    public void mo9847a(Callback<Boolean> callback) {
        if (mo9850a()) {
            nativeOnProvisioned(this.f2521a, callback);
        } else {
            callback.onResult(true);
        }
    }

    /* renamed from: b */
    public void mo9851b(byte[] bArr, Callback<PersistentInfo> callback) {
        if (mo9850a()) {
            nativeOnLoadInfo(this.f2521a, bArr, callback);
        } else {
            callback.onResult(null);
        }
    }

    /* renamed from: a */
    public void mo9848a(PersistentInfo persistentInfo, Callback<Boolean> callback) {
        if (mo9850a()) {
            nativeOnSaveInfo(this.f2521a, persistentInfo, callback);
        } else {
            callback.onResult(false);
        }
    }

    /* renamed from: a */
    public void mo9849a(byte[] bArr, Callback<Boolean> callback) {
        if (mo9850a()) {
            nativeOnClearInfo(this.f2521a, bArr, callback);
        } else {
            callback.onResult(true);
        }
    }

    /* renamed from: a */
    public final boolean mo9850a() {
        return this.f2521a != -1;
    }
}
