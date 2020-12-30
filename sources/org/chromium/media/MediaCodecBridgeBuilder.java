package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.media.MediaCodecUtil;

@MainDex
/* compiled from: PG */
public class MediaCodecBridgeBuilder {
    @CalledByNative
    public static MediaCodecBridge createAudioDecoder(String str, MediaCrypto mediaCrypto, int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z, boolean z2) {
        MediaCodecUtil.C0446b bVar = new MediaCodecUtil.C0446b();
        try {
            VN0.b("cr_MediaCodecBridge", "create MediaCodec audio decoder, mime %s", new Object[]{str});
            bVar = MediaCodecUtil.m3500a(str, 0, mediaCrypto);
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to create MediaCodec audio decoder: %s", new Object[]{str, e});
        }
        MediaCodec mediaCodec = bVar.f2477a;
        if (mediaCodec == null) {
            return null;
        }
        MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, bVar.f2479c, z2);
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(str, i, i2);
        x53.a(createAudioFormat, new byte[][]{bArr, bArr2, bArr3});
        if (z) {
            createAudioFormat.setInteger("is-adts", 1);
        }
        if (!mediaCodecBridge.mo9800a(createAudioFormat, mediaCrypto, 0)) {
            return null;
        }
        if (mediaCodecBridge.mo9806d()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        return null;
    }

    @CalledByNative
    public static MediaCodecBridge createVideoDecoder(String str, int i, MediaCrypto mediaCrypto, int i2, int i3, Surface surface, byte[] bArr, byte[] bArr2, HdrMetadata hdrMetadata, boolean z, boolean z2) {
        String str2 = str;
        HdrMetadata hdrMetadata2 = hdrMetadata;
        MediaCodecUtil.C0446b bVar = new MediaCodecUtil.C0446b();
        boolean z3 = true;
        try {
            VN0.b("cr_MediaCodecBridge", "create MediaCodec video decoder, mime %s, codecType %d", new Object[]{str2, Integer.valueOf(i)});
            bVar = MediaCodecUtil.m3500a(str, i, mediaCrypto);
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to create MediaCodec video decoder: %s, codecType: %d", new Object[]{str2, Integer.valueOf(i), e});
        }
        MediaCodec mediaCodec = bVar.f2477a;
        if (mediaCodec == null) {
            return null;
        }
        MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, bVar.f2479c, z2);
        byte[][] bArr3 = {bArr, bArr2};
        if (!bVar.f2478b || !z) {
            z3 = false;
        }
        int i4 = i2;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i2, i3);
        if (createVideoFormat == null) {
            createVideoFormat = null;
        } else {
            x53.a(createVideoFormat, bArr3);
            if (hdrMetadata2 != null) {
                hdrMetadata2.mo9794a(createVideoFormat);
            }
            x53.a(createVideoFormat, z3);
        }
        MediaCrypto mediaCrypto2 = mediaCrypto;
        if (!mediaCodecBridge.mo9801a(createVideoFormat, surface, mediaCrypto, 0)) {
            return null;
        }
        if (mediaCodecBridge.mo9806d()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        if (i == 0) {
            return createVideoDecoder(str, 2, mediaCrypto, i2, i3, surface, bArr, bArr2, hdrMetadata, z, z2);
        }
        return null;
    }

    @CalledByNative
    public static MediaCodecBridge createVideoEncoder(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        s53 s53;
        MediaCodecUtil.C0446b bVar = new MediaCodecUtil.C0446b();
        int i7 = 0;
        try {
            VN0.b("cr_MediaCodecBridge", "create MediaCodec video encoder, mime %s", new Object[]{str});
            bVar = MediaCodecUtil.m3499a(str);
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to create MediaCodec video encoder: %s", new Object[]{str, e});
        }
        if (bVar.f2477a == null) {
            return null;
        }
        if (str.equals("video/avc")) {
            s53 = new s53(bVar.f2477a, bVar.f2479c);
        } else {
            s53 = new MediaCodecBridge(bVar.f2477a, bVar.f2479c, false);
        }
        int i8 = bVar.f2479c;
        if (i8 == 0) {
            i7 = Math.min(i4, 30);
        } else if (i8 == 1) {
            i7 = 30;
        }
        boolean z = bVar.f2478b;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
        createVideoFormat.setInteger("bitrate", i3);
        createVideoFormat.setInteger("frame-rate", i7);
        createVideoFormat.setInteger("i-frame-interval", i5);
        createVideoFormat.setInteger("color-format", i6);
        x53.a(createVideoFormat, z);
        if (!s53.mo9801a(createVideoFormat, (Surface) null, (MediaCrypto) null, 1)) {
            return null;
        }
        if (s53.mo9806d()) {
            return s53;
        }
        s53.release();
        return null;
    }
}
