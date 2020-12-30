package org.chromium.media;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.os.Build;
import com.citrix.loggersdk.BuildConfig;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.chromium.base.BuildInfo;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.media.CodecProfileLevelList;

@MainDex
/* compiled from: PG */
public class MediaCodecUtil {

    /* renamed from: a */
    public static final String[] f2476a = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};

    /* renamed from: org.chromium.media.MediaCodecUtil$b */
    /* compiled from: PG */
    public static class C0446b {

        /* renamed from: a */
        public MediaCodec f2477a;

        /* renamed from: b */
        public boolean f2478b;

        /* renamed from: c */
        public int f2479c = 0;
    }

    @TargetApi(21)
    /* renamed from: a */
    public static void m3502a(CodecProfileLevelList codecProfileLevelList, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        for (int[] iArr : new int[][]{new int[]{200, 10}, new int[]{800, 11}, new int[]{1800, 20}, new int[]{3600, 21}, new int[]{7200, 30}, new int[]{12000, 31}, new int[]{18000, 40}, new int[]{30000, 41}, new int[]{60000, 50}, new int[]{120000, 51}, new int[]{180000, 52}}) {
            int i = iArr[0];
            int i2 = iArr[1];
            if (videoCapabilities.getBitrateRange().contains(Integer.valueOf(i))) {
                codecProfileLevelList.f2442a.add(new CodecProfileLevelList.CodecProfileLevelAdapter(7, 12, i2));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d0 A[SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Integer m3504b(java.lang.String r11) {
        /*
            org.chromium.media.MediaCodecUtil$c r0 = new org.chromium.media.MediaCodecUtil$c
            r0.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0009:
            boolean r1 = r0.hasNext()
            r2 = 0
            java.lang.String r3 = "cr_MediaCodecUtil"
            r4 = 0
            if (r1 == 0) goto L_0x00d4
            java.lang.Object r1 = r0.next()
            android.media.MediaCodecInfo r1 = (android.media.MediaCodecInfo) r1
            boolean r5 = r1.isEncoder()
            if (r5 == 0) goto L_0x0009
            java.lang.String r5 = r1.getName()
            boolean r5 = m3506d(r5)
            if (r5 == 0) goto L_0x002a
            goto L_0x0009
        L_0x002a:
            java.lang.String[] r5 = r1.getSupportedTypes()
            int r6 = r5.length
            r7 = 0
        L_0x0030:
            if (r7 >= r6) goto L_0x0042
            r8 = r5[r7]
            boolean r8 = r8.equalsIgnoreCase(r11)
            if (r8 == 0) goto L_0x003f
            java.lang.String r2 = r1.getName()
            goto L_0x0042
        L_0x003f:
            int r7 = r7 + 1
            goto L_0x0030
        L_0x0042:
            if (r2 != 0) goto L_0x0045
            goto L_0x0009
        L_0x0045:
            r1 = 0
        L_0x0046:
            r5 = 5
            if (r1 >= r5) goto L_0x0009
            java.lang.String r5 = ""
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r1 == 0) goto L_0x005e
            if (r1 == r9) goto L_0x005b
            if (r1 == r8) goto L_0x005e
            if (r1 == r7) goto L_0x005b
            if (r1 == r6) goto L_0x005b
            r10 = r5
            goto L_0x0060
        L_0x005b:
            java.lang.String r10 = "video/avc"
            goto L_0x0060
        L_0x005e:
            java.lang.String r10 = "video/x-vnd.on2.vp8"
        L_0x0060:
            boolean r10 = r11.equalsIgnoreCase(r10)
            if (r10 != 0) goto L_0x0068
            goto L_0x00d0
        L_0x0068:
            if (r1 == 0) goto L_0x0079
            if (r1 == r9) goto L_0x0079
            if (r1 == r8) goto L_0x0076
            if (r1 == r7) goto L_0x0076
            if (r1 == r6) goto L_0x0073
            goto L_0x007b
        L_0x0073:
            java.lang.String r5 = "OMX.MTK."
            goto L_0x007b
        L_0x0076:
            java.lang.String r5 = "OMX.Exynos."
            goto L_0x007b
        L_0x0079:
            java.lang.String r5 = "OMX.qcom."
        L_0x007b:
            boolean r5 = r2.startsWith(r5)
            if (r5 == 0) goto L_0x00d0
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r1 == 0) goto L_0x0098
            if (r1 == r9) goto L_0x0098
            if (r1 == r8) goto L_0x0095
            if (r1 == r7) goto L_0x0092
            if (r1 == r6) goto L_0x008f
            r6 = -1
            goto L_0x009a
        L_0x008f:
            r6 = 27
            goto L_0x009a
        L_0x0092:
            r6 = 21
            goto L_0x009a
        L_0x0095:
            r6 = 23
            goto L_0x009a
        L_0x0098:
            r6 = 19
        L_0x009a:
            if (r5 >= r6) goto L_0x00b3
            java.lang.String r5 = "Codec "
            java.lang.String r6 = " is disabled due to SDK version "
            java.lang.StringBuilder r5 = Eo.c(r5, r2, r6)
            int r6 = android.os.Build.VERSION.SDK_INT
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.Object[] r6 = new java.lang.Object[r4]
            VN0.c(r3, r5, r6)
            goto L_0x00d0
        L_0x00b3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "Found target encoder for mime "
            r0.append(r3)
            r0.append(r11)
            java.lang.String r11 = " : "
            r0.append(r11)
            r0.append(r2)
            r0.toString()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r1)
            return r11
        L_0x00d0:
            int r1 = r1 + 1
            goto L_0x0046
        L_0x00d4:
            java.lang.String r0 = "HW encoder for "
            java.lang.String r1 = " is not available on this device."
            java.lang.String r11 = Eo.b(r0, r11, r1)
            java.lang.Object[] r0 = new java.lang.Object[r4]
            VN0.c(r3, r11, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecUtil.m3504b(java.lang.String):java.lang.Integer");
    }

    /* renamed from: c */
    public static boolean m3505c(String str) {
        if ((!str.equals("video/avc") && !str.equals("video/avc1")) || !Build.VERSION.RELEASE.equals("4.4.2") || !Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
            return false;
        }
        if (Build.MODEL.startsWith("GT-I9300") || Build.MODEL.startsWith("SCH-I535")) {
            return true;
        }
        return false;
    }

    @CalledByNative
    public static boolean canDecode(String str, boolean z) {
        MediaCodec mediaCodec;
        if (!isDecoderSupportedForDevice(str)) {
            VN0.a("cr_MediaCodecUtil", "Decoder for type %s is not supported on this device", new Object[]{str});
            return false;
        }
        C0447c cVar = new C0447c();
        if (cVar.mo9816a()) {
            Iterator<MediaCodecInfo> it = cVar.iterator();
            while (it.hasNext()) {
                MediaCodecInfo next = it.next();
                if (!next.isEncoder()) {
                    try {
                        MediaCodecInfo.CodecCapabilities capabilitiesForType = next.getCapabilitiesForType(str);
                        if (capabilitiesForType == null) {
                            continue;
                        } else {
                            if (z) {
                                if (capabilitiesForType.isFeatureSupported("secure-playback")) {
                                    return true;
                                }
                            }
                            if (!z && !capabilitiesForType.isFeatureRequired("secure-playback")) {
                                return true;
                            }
                        }
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
            return false;
        }
        C0446b a = m3500a(str, z ? 1 : 0, (MediaCrypto) null);
        if (a == null || (mediaCodec = a.f2477a) == null) {
            return false;
        }
        try {
            mediaCodec.release();
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecUtil", "Cannot release media codec", new Object[]{e});
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m3506d(String str) {
        if (!str.startsWith("OMX.google.") && str.startsWith("OMX.")) {
            return false;
        }
        return true;
    }

    @CalledByNative
    public static String getDefaultCodecName(String str, int i, boolean z) {
        Iterator<MediaCodecInfo> it = new C0447c().iterator();
        while (true) {
            if (it.hasNext()) {
                MediaCodecInfo next = it.next();
                if (next.isEncoder() == i && (!z || m3506d(next.getName()))) {
                    for (String equalsIgnoreCase : next.getSupportedTypes()) {
                        if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                            return next.getName();
                        }
                    }
                    continue;
                }
            } else {
                VN0.a("cr_MediaCodecUtil", "Decoder for type %s is not supported on this device", new Object[]{str});
                return BuildConfig.FLAVOR;
            }
        }
    }

    @CalledByNative
    public static int[] getEncoderColorFormatsForMime(String str) {
        Iterator<MediaCodecInfo> it = new C0447c().iterator();
        while (it.hasNext()) {
            MediaCodecInfo next = it.next();
            if (next.isEncoder()) {
                String[] supportedTypes = next.getSupportedTypes();
                int length = supportedTypes.length;
                int i = 0;
                while (i < length) {
                    String str2 = supportedTypes[i];
                    if (str2.equalsIgnoreCase(str)) {
                        try {
                            return next.getCapabilitiesForType(str2).colorFormats;
                        } catch (IllegalArgumentException unused) {
                            continue;
                        }
                    } else {
                        i++;
                    }
                }
                continue;
            }
        }
        return null;
    }

    @CalledByNative
    public static Object[] getSupportedCodecProfileLevels() {
        CodecProfileLevelList codecProfileLevelList = new CodecProfileLevelList();
        Iterator<MediaCodecInfo> it = new C0447c().iterator();
        while (it.hasNext()) {
            MediaCodecInfo next = it.next();
            for (String str : next.getSupportedTypes()) {
                if (!isDecoderSupportedForDevice(str)) {
                    VN0.c("cr_MediaCodecUtil", "Decoder for type %s disabled on this device", new Object[]{str});
                } else {
                    try {
                        MediaCodecInfo.CodecCapabilities capabilitiesForType = next.getCapabilitiesForType(str);
                        if (!str.endsWith("vp9") || 21 > Build.VERSION.SDK_INT || Build.VERSION.SDK_INT > 23) {
                            for (MediaCodecInfo.CodecProfileLevel a : capabilitiesForType.profileLevels) {
                                codecProfileLevelList.mo9790a(str, a);
                            }
                        } else {
                            m3502a(codecProfileLevelList, capabilitiesForType);
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
        }
        return codecProfileLevelList.f2442a.toArray();
    }

    @CalledByNative
    public static boolean isDecoderSupportedForDevice(String str) {
        if (str.equals("video/x-vnd.on2.vp8")) {
            if ((Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung") && ((Build.VERSION.SDK_INT < 21 && (Build.MODEL.startsWith("GT-I9505") || Build.MODEL.startsWith("GT-I9500"))) || Build.MODEL.startsWith("GT-I9190") || Build.MODEL.startsWith("GT-I9195") || (Build.VERSION.SDK_INT <= 19 && (Build.MODEL.startsWith("GT-") || Build.MODEL.startsWith("SCH-") || Build.MODEL.startsWith("SM-T") || Build.MODEL.startsWith("SM-G"))))) || Build.HARDWARE.startsWith("mt")) {
                return false;
            }
            if (Build.VERSION.SDK_INT > 19 || !Build.MODEL.startsWith("Lenovo A6000")) {
                return true;
            }
            return false;
        } else if (str.equals("video/x-vnd.on2.vp9")) {
            if ((Build.VERSION.SDK_INT >= 21 || !Build.HARDWARE.startsWith("mt")) && !Build.MODEL.equals("Nexus Player")) {
                return true;
            }
            return false;
        } else if (str.equals("video/av01")) {
            if (!BuildInfo.m1372a()) {
                return false;
            }
            return true;
        } else if (str.equals("audio/opus") && Build.VERSION.SDK_INT < 21) {
            return false;
        } else {
            if (!str.equals("video/hevc") || Build.VERSION.SDK_INT >= 21) {
                return true;
            }
            return false;
        }
    }

    @CalledByNative
    public static boolean isEncoderSupportedByDevice(String str) {
        if (str.equals("video/avc") && Arrays.asList(f2476a).contains(Build.MODEL)) {
            VN0.c("cr_MediaCodecUtil", Eo.a(Eo.a("Model: "), Build.MODEL, " has blacklisted H.264 encoder."), new Object[0]);
            return false;
        } else if (m3504b(str) != null) {
            return true;
        } else {
            return false;
        }
    }

    @CalledByNative
    public static boolean isSetOutputSurfaceSupported() {
        return Build.VERSION.SDK_INT >= 23 && !Build.HARDWARE.equalsIgnoreCase("hi6210sft") && !Build.HARDWARE.equalsIgnoreCase("hi6250");
    }

    @CalledByNative
    public static boolean platformSupportsCbcsEncryption(int i) {
        return i >= 25;
    }

    /* renamed from: org.chromium.media.MediaCodecUtil$c */
    /* compiled from: PG */
    public static class C0447c implements Iterable<MediaCodecInfo> {

        /* renamed from: a */
        public MediaCodecInfo[] f2480a;

        /* renamed from: org.chromium.media.MediaCodecUtil$c$a */
        /* compiled from: PG */
        public class C0448a implements Iterator<MediaCodecInfo> {

            /* renamed from: a */
            public int f2481a;

            public /* synthetic */ C0448a(C0445a aVar) {
            }

            public boolean hasNext() {
                return this.f2481a < C0447c.m3507a(C0447c.this);
            }

            public Object next() {
                if (this.f2481a != C0447c.m3507a(C0447c.this)) {
                    C0447c cVar = C0447c.this;
                    int i = this.f2481a;
                    this.f2481a = i + 1;
                    if (cVar.mo9816a()) {
                        return cVar.f2480a[i];
                    }
                    return MediaCodecList.getCodecInfoAt(i);
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @TargetApi(21)
        public C0447c() {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    this.f2480a = new MediaCodecList(1).getCodecInfos();
                } catch (RuntimeException unused) {
                }
            }
        }

        /* renamed from: a */
        public static /* synthetic */ int m3507a(C0447c cVar) {
            if (cVar.mo9816a()) {
                return cVar.f2480a.length;
            }
            try {
                return MediaCodecList.getCodecCount();
            } catch (RuntimeException unused) {
                return 0;
            }
        }

        public Iterator<MediaCodecInfo> iterator() {
            return new C0448a((C0445a) null);
        }

        /* renamed from: a */
        public final boolean mo9816a() {
            if (!(Build.VERSION.SDK_INT >= 21) || this.f2480a == null) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: a */
    public static C0446b m3500a(String str, int i, MediaCrypto mediaCrypto) {
        C0446b bVar = new C0446b();
        if (!isDecoderSupportedForDevice(str)) {
            VN0.a("cr_MediaCodecUtil", "Decoder for type %s is not supported on this device", new Object[]{str});
            return bVar;
        }
        try {
            if ((!str.startsWith("video") || i != 1) && (!str.startsWith("audio") || mediaCrypto == null || !mediaCrypto.requiresSecureDecoderComponent(str))) {
                if (i == 2) {
                    bVar.f2477a = MediaCodec.createByCodecName(getDefaultCodecName(str, 0, true));
                } else if (str.equals("audio/raw")) {
                    bVar.f2477a = MediaCodec.createByCodecName("OMX.google.raw.decoder");
                } else {
                    bVar.f2477a = MediaCodec.createDecoderByType(str);
                }
                bVar.f2478b = m3503a(bVar.f2477a, str);
                return bVar;
            }
            String defaultCodecName = getDefaultCodecName(str, 0, false);
            if (defaultCodecName.equals(BuildConfig.FLAVOR)) {
                return bVar;
            }
            MediaCodec createByCodecName = MediaCodec.createByCodecName(defaultCodecName);
            bVar.f2478b = m3503a(createByCodecName, str);
            createByCodecName.release();
            bVar.f2477a = MediaCodec.createByCodecName(defaultCodecName + ".secure");
            return bVar;
        } catch (Exception e) {
            VN0.a("cr_MediaCodecUtil", "Failed to create MediaCodec: %s, codecType: %d", new Object[]{str, Integer.valueOf(i), e});
            bVar.f2477a = null;
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    public static boolean m3503a(MediaCodec mediaCodec, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        if (mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
            if (!codecInfo.isEncoder() && !m3505c(str) && (capabilitiesForType = codecInfo.getCapabilitiesForType(str)) != null && capabilitiesForType.isFeatureSupported("adaptive-playback")) {
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            VN0.a("cr_MediaCodecUtil", "Cannot retrieve codec information", new Object[]{e});
            return false;
        }
    }

    /* renamed from: a */
    public static C0446b m3499a(String str) {
        C0446b bVar = new C0446b();
        Integer b = m3504b(str);
        if (b == null) {
            return bVar;
        }
        try {
            bVar.f2477a = MediaCodec.createEncoderByType(str);
            bVar.f2478b = false;
            int intValue = b.intValue();
            bVar.f2479c = (intValue == 0 || intValue == 1 || intValue == 2) ? 0 : (intValue == 3 || intValue == 4) ? 1 : -1;
        } catch (Exception e) {
            VN0.a("cr_MediaCodecUtil", "Failed to create MediaCodec: %s", new Object[]{str, e});
        }
        return bVar;
    }

    /* renamed from: a */
    public static void m3501a(MediaCodec.CryptoInfo cryptoInfo, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i, i2));
        }
    }
}
