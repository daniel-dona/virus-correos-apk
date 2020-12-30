package org.chromium.media;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class MediaCodecBridge {

    /* renamed from: n */
    public static HandlerThread f2448n;

    /* renamed from: o */
    public static Handler f2449o;

    /* renamed from: a */
    public MediaCodec f2450a;

    /* renamed from: b */
    public ByteBuffer[] f2451b;

    /* renamed from: c */
    public ByteBuffer[] f2452c;

    /* renamed from: d */
    public int f2453d;

    /* renamed from: e */
    public boolean f2454e;

    /* renamed from: f */
    public Queue<GetOutputFormatResult> f2455f;

    /* renamed from: g */
    public GetOutputFormatResult f2456g;

    /* renamed from: h */
    public boolean f2457h;

    /* renamed from: i */
    public boolean f2458i;

    /* renamed from: j */
    public long f2459j;

    /* renamed from: k */
    public int f2460k;

    /* renamed from: l */
    public Queue<DequeueInputResult> f2461l;

    /* renamed from: m */
    public Queue<DequeueOutputResult> f2462m;

    @MainDex
    /* compiled from: PG */
    public static class DequeueInputResult {

        /* renamed from: a */
        public final int f2463a;

        /* renamed from: b */
        public final int f2464b;

        public /* synthetic */ DequeueInputResult(int i, int i2, C0442a aVar) {
            this.f2463a = i;
            this.f2464b = i2;
        }

        private int index() {
            return this.f2464b;
        }

        private int status() {
            return this.f2463a;
        }
    }

    @MainDex
    /* compiled from: PG */
    public static class DequeueOutputResult {

        /* renamed from: a */
        public final int f2465a;

        /* renamed from: b */
        public final int f2466b;

        /* renamed from: c */
        public final int f2467c;

        /* renamed from: d */
        public final int f2468d;

        /* renamed from: e */
        public final long f2469e;

        /* renamed from: f */
        public final int f2470f;

        public /* synthetic */ DequeueOutputResult(int i, int i2, int i3, int i4, long j, int i5, C0442a aVar) {
            this.f2465a = i;
            this.f2466b = i2;
            this.f2467c = i3;
            this.f2468d = i4;
            this.f2469e = j;
            this.f2470f = i5;
        }

        private int flags() {
            return this.f2467c;
        }

        private int index() {
            return this.f2466b;
        }

        private int numBytes() {
            return this.f2470f;
        }

        private int offset() {
            return this.f2468d;
        }

        private long presentationTimeMicroseconds() {
            return this.f2469e;
        }

        /* access modifiers changed from: private */
        public int status() {
            return this.f2465a;
        }
    }

    @MainDex
    /* compiled from: PG */
    public static class GetOutputFormatResult {

        /* renamed from: a */
        public final int f2471a;

        /* renamed from: b */
        public final MediaFormat f2472b;

        public /* synthetic */ GetOutputFormatResult(int i, MediaFormat mediaFormat, C0442a aVar) {
            this.f2471a = i;
            this.f2472b = mediaFormat;
        }

        private int channelCount() {
            return this.f2472b.getInteger("channel-count");
        }

        private int height() {
            if (mo9810a()) {
                return (this.f2472b.getInteger("crop-bottom") - this.f2472b.getInteger("crop-top")) + 1;
            }
            return this.f2472b.getInteger("height");
        }

        private int sampleRate() {
            return this.f2472b.getInteger("sample-rate");
        }

        private int status() {
            return this.f2471a;
        }

        private int width() {
            if (mo9810a()) {
                return (this.f2472b.getInteger("crop-right") - this.f2472b.getInteger("crop-left")) + 1;
            }
            return this.f2472b.getInteger("width");
        }

        /* renamed from: a */
        public final boolean mo9810a() {
            return this.f2472b.containsKey("crop-right") && this.f2472b.containsKey("crop-left") && this.f2472b.containsKey("crop-bottom") && this.f2472b.containsKey("crop-top");
        }
    }

    /* renamed from: org.chromium.media.MediaCodecBridge$b */
    /* compiled from: PG */
    public class C0443b implements Runnable {

        /* renamed from: a */
        public int f2473a;

        public C0443b(int i) {
            this.f2473a = i;
        }

        public void run() {
            MediaCodecBridge.this.mo9803b(this.f2473a);
        }
    }

    @MainDex
    @TargetApi(23)
    /* renamed from: org.chromium.media.MediaCodecBridge$c */
    /* compiled from: PG */
    public class C0444c extends MediaCodec.Callback {

        /* renamed from: a */
        public MediaCodecBridge f2475a;

        public C0444c(MediaCodecBridge mediaCodecBridge, MediaCodecBridge mediaCodecBridge2) {
            this.f2475a = mediaCodecBridge2;
        }

        public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
            StringBuilder a = Eo.a("MediaCodec.onError: ");
            a.append(codecException.getDiagnosticInfo());
            VN0.a("cr_MediaCodecBridge", a.toString(), new Object[0]);
            this.f2475a.mo9802b();
        }

        public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
            this.f2475a.mo9797a(i);
        }

        public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
            this.f2475a.mo9798a(i, bufferInfo);
        }

        public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
            this.f2475a.mo9799a(mediaFormat);
        }
    }

    public MediaCodecBridge(MediaCodec mediaCodec, int i, boolean z) {
        this.f2450a = mediaCodec;
        this.f2453d = i;
        this.f2454e = z;
        if (this.f2454e) {
            this.f2457h = false;
            this.f2455f = new LinkedList();
            this.f2461l = new LinkedList();
            this.f2462m = new LinkedList();
            this.f2450a.setCallback(new C0444c(this, this), f2449o);
            mo9805c();
        }
    }

    @CalledByNative
    public static void createCallbackHandlerForTesting() {
        if (f2448n == null) {
            f2448n = new HandlerThread("TestCallbackThread");
            f2448n.start();
            f2449o = new Handler(f2448n.getLooper());
        }
    }

    @CalledByNative
    private DequeueInputResult dequeueInputBuffer(long j) {
        int i = 5;
        int i2 = -1;
        if (this.f2454e) {
            synchronized (this) {
                if (this.f2457h) {
                    DequeueInputResult dequeueInputResult = new DequeueInputResult(5, -1, (C0442a) null);
                    return dequeueInputResult;
                }
                if (!this.f2458i) {
                    if (!this.f2461l.isEmpty()) {
                        DequeueInputResult remove = this.f2461l.remove();
                        return remove;
                    }
                }
                DequeueInputResult dequeueInputResult2 = new DequeueInputResult(1, -1, (C0442a) null);
                return dequeueInputResult2;
            }
        }
        try {
            int dequeueInputBuffer = this.f2450a.dequeueInputBuffer(j);
            if (dequeueInputBuffer >= 0) {
                i2 = dequeueInputBuffer;
                i = 0;
            } else if (dequeueInputBuffer == -1) {
                i = 1;
            } else {
                VN0.a("cr_MediaCodecBridge", "Unexpected index_or_status: " + dequeueInputBuffer, new Object[0]);
            }
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to dequeue input buffer", new Object[]{e});
        }
        return new DequeueInputResult(i, i2, (C0442a) null);
    }

    @CalledByNative
    private DequeueOutputResult dequeueOutputBuffer(long j) {
        int i;
        int i2;
        if (this.f2454e) {
            synchronized (this) {
                if (this.f2457h) {
                    DequeueOutputResult dequeueOutputResult = new DequeueOutputResult(5, -1, 0, 0, 0, 0, (C0442a) null);
                    return dequeueOutputResult;
                } else if (this.f2462m.isEmpty()) {
                    DequeueOutputResult dequeueOutputResult2 = new DequeueOutputResult(1, -1, 0, 0, 0, 0, (C0442a) null);
                    return dequeueOutputResult2;
                } else {
                    if (this.f2462m.peek().status() == 3) {
                        this.f2456g = this.f2455f.remove();
                    }
                    DequeueOutputResult remove = this.f2462m.remove();
                    return remove;
                }
            }
        } else {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int i3 = -1;
            int i4 = 5;
            try {
                int a = mo9795a(bufferInfo, j);
                if (a >= 0) {
                    i3 = a;
                    i4 = 0;
                } else if (a == -3) {
                    this.f2452c = this.f2450a.getOutputBuffers();
                    i4 = 2;
                } else if (a == -2) {
                    MediaFormat outputFormat = this.f2450a.getOutputFormat();
                    VN0.b("cr_MediaCodecBridge", "dequeueOutputBuffer: out: INFO_OUTPUT_FORMAT_CHANGED: " + outputFormat.toString(), new Object[0]);
                    i4 = 3;
                } else if (a == -1) {
                    MediaFormat outputFormat2 = this.f2450a.getOutputFormat();
                    VN0.b("cr_MediaCodecBridge", "dequeueOutputBuffer: out: INFO_TRY_AGAIN_LATER: " + outputFormat2.toString(), new Object[0]);
                    i4 = 1;
                } else {
                    VN0.a("cr_MediaCodecBridge", "Unexpected index_or_status: " + a, new Object[0]);
                }
                i = i3;
                i2 = i4;
            } catch (IllegalStateException e) {
                VN0.a("cr_MediaCodecBridge", "Failed to dequeue output buffer", new Object[]{e});
                i2 = 5;
                i = -1;
            }
            return new DequeueOutputResult(i2, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, (C0442a) null);
        }
    }

    @CalledByNative
    private int flush() {
        try {
            this.f2450a.flush();
            if (this.f2454e) {
                mo9805c();
                if (!mo9806d()) {
                    return 5;
                }
            }
            return 0;
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to flush MediaCodec", new Object[]{e});
            return 5;
        }
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    private ByteBuffer getInputBuffer(int i) {
        if (Build.VERSION.SDK_INT <= 19) {
            return this.f2451b[i];
        }
        try {
            return this.f2450a.getInputBuffer(i);
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to get input buffer", new Object[]{e});
            return null;
        }
    }

    @TargetApi(19)
    @CalledByNative
    private String getName() {
        try {
            return this.f2450a.getName();
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot get codec name", new Object[]{e});
            return "unknown";
        }
    }

    @CalledByNative
    private GetOutputFormatResult getOutputFormat() {
        MediaFormat mediaFormat;
        GetOutputFormatResult getOutputFormatResult;
        if (this.f2454e && (getOutputFormatResult = this.f2456g) != null) {
            return getOutputFormatResult;
        }
        int i = 0;
        try {
            mediaFormat = this.f2450a.getOutputFormat();
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to get output format", new Object[]{e});
            i = 5;
            mediaFormat = null;
        }
        return new GetOutputFormatResult(i, mediaFormat, (C0442a) null);
    }

    private native void nativeOnBuffersAvailable(long j);

    @CalledByNative
    private int queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        try {
            this.f2450a.queueInputBuffer(i, i2, i3, j, i4);
            return 0;
        } catch (Exception e) {
            VN0.a("cr_MediaCodecBridge", "Failed to queue input buffer", new Object[]{e});
            return 5;
        }
    }

    @SuppressLint({"WrongConstant"})
    @CalledByNative
    private int queueSecureInputBuffer(int i, int i2, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, int i3, int i4, int i5, int i6, long j) {
        int i7 = i5;
        int i8 = i6;
        try {
            int c = mo9804c(i4);
            if (c == -1) {
                return 5;
            }
            boolean z = c == 2;
            if (!z || MediaCodecUtil.platformSupportsCbcsEncryption(Build.VERSION.SDK_INT)) {
                MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
                cryptoInfo.set(i3, iArr, iArr2, bArr2, bArr, c);
                if (!(i7 == 0 || i8 == 0)) {
                    if (z) {
                        MediaCodecUtil.m3501a(cryptoInfo, i7, i8);
                    } else {
                        VN0.a("cr_MediaCodecBridge", "Pattern encryption only supported for 'cbcs' scheme (CBC mode).", new Object[0]);
                        return 5;
                    }
                }
                this.f2450a.queueSecureInputBuffer(i, i2, cryptoInfo, j, 0);
                return 0;
            }
            VN0.a("cr_MediaCodecBridge", "Encryption scheme 'cbcs' not supported on this platform.", new Object[0]);
            return 5;
        } catch (MediaCodec.CryptoException e) {
            if (e.getErrorCode() == 1) {
                return 4;
            }
            StringBuilder a = Eo.a("Failed to queue secure input buffer, CryptoException with error code ");
            a.append(e.getErrorCode());
            VN0.a("cr_MediaCodecBridge", a.toString(), new Object[0]);
            return 5;
        } catch (IllegalArgumentException e2) {
            VN0.a("cr_MediaCodecBridge", "Failed to queue secure input buffer, IllegalArgumentException " + e2, new Object[0]);
            return 5;
        } catch (IllegalStateException e3) {
            VN0.a("cr_MediaCodecBridge", "Failed to queue secure input buffer, IllegalStateException " + e3, new Object[0]);
            return 5;
        }
    }

    @TargetApi(19)
    @CalledByNative
    private void requestKeyFrameSoon() {
        try {
            this.f2450a.setParameters(Eo.d("request-sync", 0));
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", new Object[]{e});
        }
    }

    @CalledByNative
    private synchronized void setBuffersAvailableListener(long j) {
        this.f2459j = j;
        if (!this.f2461l.isEmpty() || !this.f2462m.isEmpty() || this.f2457h) {
            mo9796a();
        }
    }

    @TargetApi(23)
    @CalledByNative
    private boolean setSurface(Surface surface) {
        try {
            this.f2450a.setOutputSurface(surface);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot set output surface", new Object[]{e});
            return false;
        }
    }

    @TargetApi(19)
    @CalledByNative
    private void setVideoBitrate(int i, int i2) {
        int i3;
        int i4 = this.f2453d;
        if (i4 != 0) {
            if (i4 != 1) {
                i3 = 0;
            } else if (i2 != 0) {
                i3 = (i * 30) / i2;
            }
            this.f2450a.setParameters(Eo.d("video-bitrate", i3));
            "setVideoBitrate: input " + i + "bps@" + i2 + ", targetBps " + i3;
        }
        i3 = i;
        try {
            this.f2450a.setParameters(Eo.d("video-bitrate", i3));
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", new Object[]{e});
        }
        "setVideoBitrate: input " + i + "bps@" + i2 + ", targetBps " + i3;
    }

    @CalledByNative
    private void stop() {
        try {
            this.f2450a.stop();
            if (this.f2454e) {
                mo9805c();
            }
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to stop MediaCodec", new Object[]{e});
        }
    }

    /* renamed from: a */
    public final synchronized void mo9796a() {
        if (this.f2459j != 0) {
            nativeOnBuffersAvailable(this.f2459j);
        }
    }

    /* renamed from: b */
    public synchronized void mo9802b() {
        this.f2457h = true;
        this.f2461l.clear();
        this.f2462m.clear();
        mo9796a();
    }

    /* renamed from: c */
    public final synchronized void mo9805c() {
        this.f2455f.clear();
        this.f2461l.clear();
        this.f2462m.clear();
        this.f2458i = true;
        this.f2456g = null;
        this.f2460k++;
    }

    /* renamed from: d */
    public boolean mo9806d() {
        Handler handler;
        try {
            if (this.f2454e) {
                synchronized (this) {
                    if (this.f2457h) {
                        return false;
                    }
                    if (f2449o == null) {
                        handler = new Handler(Looper.getMainLooper());
                    } else {
                        handler = f2449o;
                    }
                    handler.post(new C0443b(this.f2460k));
                }
            }
            this.f2450a.start();
            if (Build.VERSION.SDK_INT <= 19) {
                this.f2451b = this.f2450a.getInputBuffers();
                this.f2452c = this.f2450a.getOutputBuffers();
            }
            return true;
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot start the media codec", new Object[]{e});
            return false;
        } catch (IllegalArgumentException e2) {
            VN0.a("cr_MediaCodecBridge", "Cannot start the media codec", new Object[]{e2});
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    public ByteBuffer getOutputBuffer(int i) {
        if (Build.VERSION.SDK_INT <= 19) {
            return this.f2452c[i];
        }
        try {
            return this.f2450a.getOutputBuffer(i);
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to get output buffer", new Object[]{e});
            return null;
        }
    }

    @CalledByNative
    public void release() {
        if (this.f2454e) {
            synchronized (this) {
                this.f2459j = 0;
            }
        }
        try {
            String name = this.f2450a.getName();
            VN0.c("cr_MediaCodecBridge", "Releasing: " + name, new Object[0]);
            this.f2450a.release();
            VN0.c("cr_MediaCodecBridge", "Codec released", new Object[0]);
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot release media codec", new Object[]{e});
        }
        this.f2450a = null;
    }

    @CalledByNative
    public void releaseOutputBuffer(int i, boolean z) {
        try {
            this.f2450a.releaseOutputBuffer(i, z);
        } catch (IllegalStateException e) {
            VN0.a("cr_MediaCodecBridge", "Failed to release output buffer", new Object[]{e});
        }
    }

    /* renamed from: a */
    public synchronized void mo9797a(int i) {
        if (!this.f2458i) {
            this.f2461l.add(new DequeueInputResult(0, i, (C0442a) null));
            mo9796a();
        }
    }

    /* renamed from: b */
    public synchronized void mo9803b(int i) {
        if (this.f2460k == i) {
            this.f2458i = false;
        }
    }

    /* renamed from: a */
    public synchronized void mo9798a(int i, MediaCodec.BufferInfo bufferInfo) {
        if (!this.f2458i) {
            this.f2462m.add(new DequeueOutputResult(0, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, (C0442a) null));
            mo9796a();
        }
    }

    /* renamed from: c */
    public final int mo9804c(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                VN0.a("cr_MediaCodecBridge", Eo.b("Unsupported cipher mode: ", i), new Object[0]);
                return -1;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public synchronized void mo9799a(MediaFormat mediaFormat) {
        this.f2462m.add(new DequeueOutputResult(3, -1, 0, 0, 0, 0, (C0442a) null));
        this.f2455f.add(new GetOutputFormatResult(0, mediaFormat, (C0442a) null));
        mo9796a();
    }

    /* renamed from: a */
    public int mo9795a(MediaCodec.BufferInfo bufferInfo, long j) {
        return this.f2450a.dequeueOutputBuffer(bufferInfo, j);
    }

    /* renamed from: a */
    public boolean mo9801a(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i) {
        try {
            this.f2450a.configure(mediaFormat, surface, mediaCrypto, i);
            return true;
        } catch (IllegalArgumentException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the video codec, wrong format or surface", new Object[]{e});
            return false;
        } catch (IllegalStateException e2) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the video codec", new Object[]{e2});
            return false;
        } catch (MediaCodec.CryptoException e3) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the video codec: DRM error", new Object[]{e3});
            return false;
        } catch (Exception e4) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the video codec", new Object[]{e4});
            return false;
        }
    }

    /* renamed from: a */
    public boolean mo9800a(MediaFormat mediaFormat, MediaCrypto mediaCrypto, int i) {
        try {
            this.f2450a.configure(mediaFormat, (Surface) null, mediaCrypto, i);
            return true;
        } catch (IllegalArgumentException e) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{e});
            return false;
        } catch (IllegalStateException e2) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{e2});
            return false;
        } catch (MediaCodec.CryptoException e3) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the audio codec: DRM error", new Object[]{e3});
            return false;
        } catch (Exception e4) {
            VN0.a("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{e4});
            return false;
        }
    }
}
