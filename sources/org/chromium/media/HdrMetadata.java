package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class HdrMetadata {

    /* renamed from: a */
    public long f2446a;

    /* renamed from: b */
    public final Object f2447b = new Object();

    public HdrMetadata(long j) {
        this.f2446a = j;
    }

    @CalledByNative
    private void close() {
        synchronized (this.f2447b) {
            this.f2446a = 0;
        }
    }

    @CalledByNative
    public static HdrMetadata create(long j) {
        return new HdrMetadata(j);
    }

    private native int nativeColorTransfer(long j);

    private native int nativeMaxContentLuminance(long j);

    private native int nativeMaxFrameAverageLuminance(long j);

    private native float nativeMaxMasteringLuminance(long j);

    private native float nativeMinMasteringLuminance(long j);

    private native int nativePrimaries(long j);

    private native float nativePrimaryBChromaticityX(long j);

    private native float nativePrimaryBChromaticityY(long j);

    private native float nativePrimaryGChromaticityX(long j);

    private native float nativePrimaryGChromaticityY(long j);

    private native float nativePrimaryRChromaticityX(long j);

    private native float nativePrimaryRChromaticityY(long j);

    private native int nativeRange(long j);

    private native float nativeWhitePointChromaticityX(long j);

    private native float nativeWhitePointChromaticityY(long j);

    /* JADX WARNING: Removed duplicated region for block: B:38:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0071  */
    @android.annotation.TargetApi(24)
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo9794a(android.media.MediaFormat r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.f2447b
            monitor-enter(r0)
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0133 }
            r2 = 24
            r3 = 0
            if (r1 >= r2) goto L_0x0015
            java.lang.String r10 = "HdrMetadata"
            java.lang.String r1 = "HDR not supported before Android N"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x0133 }
            VN0.a(r10, r1, r2)     // Catch:{ all -> 0x0133 }
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            return
        L_0x0015:
            long r1 = r9.f2446a     // Catch:{ all -> 0x0133 }
            int r1 = r9.nativePrimaries(r1)     // Catch:{ all -> 0x0133 }
            r2 = 7
            r4 = 6
            r5 = 1
            r6 = -1
            if (r1 == r5) goto L_0x0033
            r7 = 9
            if (r1 == r7) goto L_0x0031
            r7 = 4
            if (r1 == r7) goto L_0x0034
            r8 = 5
            if (r1 == r8) goto L_0x0034
            if (r1 == r4) goto L_0x0034
            if (r1 == r2) goto L_0x0034
            r7 = -1
            goto L_0x0034
        L_0x0031:
            r7 = 6
            goto L_0x0034
        L_0x0033:
            r7 = 1
        L_0x0034:
            if (r7 == r6) goto L_0x003b
            java.lang.String r1 = "color-standard"
            r10.setInteger(r1, r7)     // Catch:{ all -> 0x0133 }
        L_0x003b:
            long r7 = r9.f2446a     // Catch:{ all -> 0x0133 }
            int r1 = r9.nativeColorTransfer(r7)     // Catch:{ all -> 0x0133 }
            if (r1 == r5) goto L_0x0059
            r7 = 16
            if (r1 == r7) goto L_0x0057
            r7 = 18
            if (r1 == r7) goto L_0x005a
            if (r1 == r4) goto L_0x0059
            if (r1 == r2) goto L_0x0059
            r2 = 8
            if (r1 == r2) goto L_0x0055
            r2 = -1
            goto L_0x005a
        L_0x0055:
            r2 = 1
            goto L_0x005a
        L_0x0057:
            r2 = 6
            goto L_0x005a
        L_0x0059:
            r2 = 3
        L_0x005a:
            if (r2 == r6) goto L_0x0061
            java.lang.String r1 = "color-transfer"
            r10.setInteger(r1, r2)     // Catch:{ all -> 0x0133 }
        L_0x0061:
            long r1 = r9.f2446a     // Catch:{ all -> 0x0133 }
            int r1 = r9.nativeRange(r1)     // Catch:{ all -> 0x0133 }
            r2 = 2
            if (r1 == r5) goto L_0x006f
            if (r1 == r2) goto L_0x006e
            r2 = -1
            goto L_0x006f
        L_0x006e:
            r2 = 1
        L_0x006f:
            if (r2 == r6) goto L_0x0076
            java.lang.String r1 = "color-range"
            r10.setInteger(r1, r2)     // Catch:{ all -> 0x0133 }
        L_0x0076:
            r1 = 25
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0133 }
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r1)     // Catch:{ all -> 0x0133 }
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0133 }
            r1.order(r2)     // Catch:{ all -> 0x0133 }
            r1.put(r3)     // Catch:{ all -> 0x0133 }
            long r2 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryRChromaticityX(r2)     // Catch:{ all -> 0x0133 }
            r3 = 1195593728(0x47435000, float:50000.0)
            float r2 = r2 * r3
            r4 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryRChromaticityY(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryGChromaticityX(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryGChromaticityY(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryBChromaticityX(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativePrimaryBChromaticityY(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativeWhitePointChromaticityX(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r5 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativeWhitePointChromaticityY(r5)     // Catch:{ all -> 0x0133 }
            float r2 = r2 * r3
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r2 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativeMaxMasteringLuminance(r2)     // Catch:{ all -> 0x0133 }
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r2 = r9.f2446a     // Catch:{ all -> 0x0133 }
            float r2 = r9.nativeMinMasteringLuminance(r2)     // Catch:{ all -> 0x0133 }
            float r2 = r2 + r4
            int r2 = (int) r2     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r2 = r9.f2446a     // Catch:{ all -> 0x0133 }
            int r2 = r9.nativeMaxContentLuminance(r2)     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            long r2 = r9.f2446a     // Catch:{ all -> 0x0133 }
            int r2 = r9.nativeMaxFrameAverageLuminance(r2)     // Catch:{ all -> 0x0133 }
            short r2 = (short) r2     // Catch:{ all -> 0x0133 }
            r1.putShort(r2)     // Catch:{ all -> 0x0133 }
            r1.rewind()     // Catch:{ all -> 0x0133 }
            java.lang.String r2 = "hdr-static-info"
            r10.setByteBuffer(r2, r1)     // Catch:{ all -> 0x0133 }
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            return
        L_0x0131:
            monitor-exit(r0)     // Catch:{ all -> 0x0133 }
            throw r10
        L_0x0133:
            r10 = move-exception
            goto L_0x0131
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.HdrMetadata.mo9794a(android.media.MediaFormat):void");
    }
}
