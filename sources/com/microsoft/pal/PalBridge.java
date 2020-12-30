package com.microsoft.pal;

/* compiled from: PG */
public class PalBridge {

    /* renamed from: a */
    public long f1296a = nativeCreatePalBridge();

    /* renamed from: b */
    public PalClientProxy f1297b;

    static {
        System.loadLibrary("rubysync");
    }

    public static native long nativeCreatePalBridge();

    public static native void nativeInitialize(long j, PalClientProxy palClientProxy);

    /* renamed from: a */
    public void mo4466a(ul0 ul0) {
        this.f1297b = new PalClientProxy(ul0);
        nativeInitialize(this.f1296a, this.f1297b);
    }
}
