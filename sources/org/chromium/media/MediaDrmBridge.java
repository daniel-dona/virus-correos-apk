package org.chromium.media;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Build;
import android.os.Handler;
import com.citrix.loggersdk.BuildConfig;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import w53;

@MainDex
@SuppressLint({"WrongConstant"})
@TargetApi(19)
/* compiled from: PG */
public class MediaDrmBridge {

    /* renamed from: l */
    public static final UUID f2483l = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");

    /* renamed from: m */
    public static final byte[] f2484m = {0};

    /* renamed from: n */
    public static final byte[] f2485n = ON0.a("unprovision");

    /* renamed from: o */
    public static final C0458j f2486o = new C0458j();

    /* renamed from: a */
    public MediaDrm f2487a;

    /* renamed from: b */
    public MediaCrypto f2488b;

    /* renamed from: c */
    public long f2489c;

    /* renamed from: d */
    public UUID f2490d;

    /* renamed from: e */
    public final boolean f2491e;

    /* renamed from: f */
    public w53.a f2492f;

    /* renamed from: g */
    public w53 f2493g = new w53(this.f2494h);

    /* renamed from: h */
    public MediaDrmStorageBridge f2494h;

    /* renamed from: i */
    public boolean f2495i = false;

    /* renamed from: j */
    public boolean f2496j;

    /* renamed from: k */
    public C0459k f2497k;

    @MainDex
    /* compiled from: PG */
    public static class KeyStatus {

        /* renamed from: a */
        public final byte[] f2498a;

        /* renamed from: b */
        public final int f2499b;

        public /* synthetic */ KeyStatus(byte[] bArr, int i, C0449a aVar) {
            this.f2498a = bArr;
            this.f2499b = i;
        }

        private byte[] getKeyId() {
            return this.f2498a;
        }

        private int getStatusCode() {
            return this.f2499b;
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$a */
    /* compiled from: PG */
    public class C0449a implements Runnable {
        public C0449a() {
        }

        public void run() {
            boolean unused = MediaDrmBridge.this.mo9832a();
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$b */
    /* compiled from: PG */
    public class C0450b implements Callback<w53.a> {

        /* renamed from: a */
        public final /* synthetic */ long f2501a;

        public C0450b(long j) {
            this.f2501a = j;
        }

        public void onResult(Object obj) {
            w53.a aVar = (w53.a) obj;
            if (aVar == null) {
                MediaDrmBridge.this.mo9823a(this.f2501a);
            } else {
                MediaDrmBridge.this.mo9827a(aVar, this.f2501a);
            }
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$c */
    /* compiled from: PG */
    public class C0451c implements Callback<Boolean> {

        /* renamed from: a */
        public final /* synthetic */ long f2503a;

        public C0451c(long j) {
            this.f2503a = j;
        }

        public void onResult(Object obj) {
            if (!((Boolean) obj).booleanValue()) {
                VN0.c("cr_media", "Failed to clear persistent storage for non-exist license", new Object[0]);
            }
            MediaDrmBridge.this.mo9823a(this.f2503a);
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$d */
    /* compiled from: PG */
    public class C0452d implements Callback<Boolean> {

        /* renamed from: a */
        public final /* synthetic */ long f2505a;

        /* renamed from: b */
        public final /* synthetic */ w53.a f2506b;

        /* renamed from: c */
        public final /* synthetic */ w53.b f2507c;

        public C0452d(long j, w53.a aVar, w53.b bVar) {
            this.f2505a = j;
            this.f2506b = aVar;
            this.f2507c = bVar;
        }

        public void onResult(Object obj) {
            if (!((Boolean) obj).booleanValue()) {
                MediaDrmBridge.this.mo9824a(this.f2505a, "Fail to update persistent storage");
            } else {
                MediaDrmBridge.this.mo9829a(this.f2506b, this.f2507c.a(), this.f2505a);
            }
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$e */
    /* compiled from: PG */
    public class C0453e implements Callback<Boolean> {
        public C0453e() {
        }

        public void onResult(Object obj) {
            if (!((Boolean) obj).booleanValue()) {
                VN0.a("cr_media", "Failed to initialize storage for origin", new Object[0]);
                MediaDrmBridge.this.mo9838e();
                return;
            }
            boolean unused = MediaDrmBridge.this.mo9832a();
        }
    }

    @MainDex
    /* renamed from: org.chromium.media.MediaDrmBridge$f */
    /* compiled from: PG */
    public class C0454f implements MediaDrm.OnEventListener {
        public /* synthetic */ C0454f(C0449a aVar) {
        }

        public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            boolean z = true;
            if (bArr == null) {
                VN0.a("cr_media", "EventListener: No session for event %d.", new Object[]{Integer.valueOf(i)});
                return;
            }
            w53.a a = MediaDrmBridge.m3510a(MediaDrmBridge.this, bArr);
            if (a == null) {
                VN0.a("cr_media", "EventListener: Invalid session %s", new Object[]{w53.a.c(bArr)});
                return;
            }
            w53.b a2 = MediaDrmBridge.this.f2493g.a(a);
            if (i == 2) {
                try {
                    MediaDrm.KeyRequest a3 = MediaDrmBridge.this.mo9821a(a, bArr2, a2.b, a2.c, (HashMap<String, String>) null);
                    if (a3 != null) {
                        MediaDrmBridge.this.mo9828a(a, a3);
                        return;
                    }
                    if (Build.VERSION.SDK_INT < 23) {
                        MediaDrmBridge.this.mo9830a(a, MediaDrmBridge.m3509a(4).toArray(), false, false);
                    }
                    VN0.a("cr_media", "EventListener: getKeyRequest failed.", new Object[0]);
                } catch (NotProvisionedException e) {
                    VN0.a("cr_media", "Device not provisioned", new Object[]{e});
                }
            } else if (i != 3) {
                if (i != 4) {
                    VN0.a("cr_media", Eo.b("Invalid DRM event ", i), new Object[0]);
                }
            } else if (Build.VERSION.SDK_INT < 23) {
                MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
                Object[] array = MediaDrmBridge.m3509a(1).toArray();
                if (a2.c != 3) {
                    z = false;
                }
                mediaDrmBridge.mo9830a(a, array, false, z);
            }
        }
    }

    @MainDex
    @TargetApi(23)
    /* renamed from: org.chromium.media.MediaDrmBridge$g */
    /* compiled from: PG */
    public class C0455g implements MediaDrm.OnExpirationUpdateListener {
        public /* synthetic */ C0455g(C0449a aVar) {
        }

        public void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
            w53.a a = MediaDrmBridge.m3510a(MediaDrmBridge.this, bArr);
            MediaDrmBridge.m3515a(MediaDrmBridge.this, a, (Runnable) new t53(this, a, j));
        }
    }

    @MainDex
    @TargetApi(23)
    /* renamed from: org.chromium.media.MediaDrmBridge$h */
    /* compiled from: PG */
    public class C0456h implements MediaDrm.OnKeyStatusChangeListener {
        public /* synthetic */ C0456h(C0449a aVar) {
        }

        /* renamed from: a */
        public final List<KeyStatus> mo9843a(List<MediaDrm.KeyStatus> list) {
            ArrayList arrayList = new ArrayList();
            for (MediaDrm.KeyStatus next : list) {
                arrayList.add(new KeyStatus(next.getKeyId(), next.getStatusCode(), (C0449a) null));
            }
            return arrayList;
        }

        public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List<MediaDrm.KeyStatus> list, boolean z) {
            w53.a a = MediaDrmBridge.m3510a(MediaDrmBridge.this, bArr);
            MediaDrmBridge.m3515a(MediaDrmBridge.this, a, (Runnable) new u53(this, a, z, list, MediaDrmBridge.this.f2493g.a(a).c == 3));
        }
    }

    @MainDex
    /* renamed from: org.chromium.media.MediaDrmBridge$i */
    /* compiled from: PG */
    public class C0457i implements Callback<Boolean> {

        /* renamed from: a */
        public final w53.a f2513a;

        /* renamed from: b */
        public final long f2514b;

        /* renamed from: c */
        public final boolean f2515c;

        public C0457i(w53.a aVar, long j, boolean z) {
            this.f2513a = aVar;
            this.f2514b = j;
            this.f2515c = z;
        }

        /* renamed from: a */
        public void onResult(Boolean bool) {
            if (!bool.booleanValue()) {
                MediaDrmBridge.this.mo9824a(this.f2514b, "failed to update key after response accepted");
                return;
            }
            boolean z = this.f2515c;
            this.f2513a.a();
            MediaDrmBridge.m3512a(MediaDrmBridge.this, this.f2514b);
            if (!this.f2515c && Build.VERSION.SDK_INT < 23) {
                MediaDrmBridge.this.mo9830a(this.f2513a, MediaDrmBridge.m3509a(0).toArray(), true, this.f2515c);
            }
        }
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$j */
    /* compiled from: PG */
    public static class C0458j {

        /* renamed from: a */
        public boolean f2517a = false;

        /* renamed from: b */
        public final Queue<Runnable> f2518b = new ArrayDeque();
    }

    /* renamed from: org.chromium.media.MediaDrmBridge$k */
    /* compiled from: PG */
    public static class C0459k {

        /* renamed from: a */
        public final w53.a f2519a;

        /* renamed from: b */
        public final ArrayList<Runnable> f2520b = new ArrayList<>();

        public C0459k(w53.a aVar) {
            this.f2519a = aVar;
        }

        /* renamed from: a */
        public void mo9846a() {
            Iterator<Runnable> it = this.f2520b.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            this.f2520b.clear();
        }
    }

    @TargetApi(23)
    public MediaDrmBridge(UUID uuid, boolean z, long j, long j2) throws UnsupportedSchemeException {
        this.f2490d = uuid;
        this.f2487a = new MediaDrm(uuid);
        this.f2491e = z;
        this.f2489c = j;
        this.f2494h = new MediaDrmStorageBridge(j2);
        this.f2487a.setOnEventListener(new C0454f((C0449a) null));
        if (Build.VERSION.SDK_INT >= 23) {
            this.f2487a.setOnExpirationUpdateListener(new C0455g((C0449a) null), (Handler) null);
            this.f2487a.setOnKeyStatusChangeListener(new C0456h((C0449a) null), (Handler) null);
        }
        if (mo9836c()) {
            this.f2487a.setPropertyString("privacyMode", "enable");
            this.f2487a.setPropertyString("sessionSharing", "enable");
        }
    }

    /* renamed from: c */
    public static UUID m3521c(byte[] bArr) {
        if (bArr.length != 16) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 << 8) | ((long) (bArr[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            j = (j << 8) | ((long) (bArr[i2] & 255));
        }
        return new UUID(j2, j);
    }

    @CalledByNative
    private void closeSession(byte[] bArr, long j) {
        if (this.f2487a == null) {
            mo9824a(j, "closeSession() called when MediaDrm is null.");
            return;
        }
        w53.a a = mo9822a(bArr);
        if (a == null) {
            StringBuilder a2 = Eo.a("Invalid sessionId in closeSession(): ");
            a2.append(w53.a.c(bArr));
            mo9824a(j, a2.toString());
            return;
        }
        try {
            this.f2487a.removeKeys(a.b);
        } catch (Exception e) {
            VN0.a("cr_media", "removeKeys failed: ", new Object[]{e});
        }
        mo9826a(a);
        w53 w53 = this.f2493g;
        w53.a(a);
        w53.a.remove(ByteBuffer.wrap(a.a));
        byte[] bArr2 = a.b;
        if (bArr2 != null) {
            w53.b.remove(ByteBuffer.wrap(bArr2));
        }
        if (mo9834b()) {
            nativeOnPromiseResolved(this.f2489c, j);
        }
        if (mo9834b()) {
            nativeOnSessionClosed(this.f2489c, a.a);
        }
        a.a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ed A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ee A[RETURN] */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.media.MediaDrmBridge create(byte[] r16, java.lang.String r17, java.lang.String r18, boolean r19, long r20, long r22) {
        /*
            r1 = r17
            r2 = r18
            java.lang.String r3 = "Failed to create MediaDrmBridge"
            r4 = 2
            java.lang.Object[] r0 = new java.lang.Object[r4]
            r5 = 0
            r0[r5] = r2
            r6 = 1
            r0[r6] = r1
            java.lang.String r7 = "cr_media"
            java.lang.String r8 = "Create MediaDrmBridge with level %s and origin %s"
            VN0.b(r7, r8, r0)
            boolean r0 = Og0.d()
            r8 = 0
            if (r0 == 0) goto L_0x001e
            return r8
        L_0x001e:
            java.util.UUID r10 = m3521c(r16)
            if (r10 == 0) goto L_0x010b
            boolean r0 = android.media.MediaDrm.isCryptoSchemeSupported(r10)
            if (r0 != 0) goto L_0x002c
            goto L_0x010b
        L_0x002c:
            org.chromium.media.MediaDrmBridge r14 = new org.chromium.media.MediaDrmBridge     // Catch:{ UnsupportedSchemeException -> 0x0101, IllegalArgumentException -> 0x00f8, IllegalStateException -> 0x00ef }
            r9 = r14
            r11 = r19
            r12 = r20
            r16 = r14
            r14 = r22
            r9.<init>(r10, r11, r12, r14)     // Catch:{ UnsupportedSchemeException -> 0x0101, IllegalArgumentException -> 0x00f8, IllegalStateException -> 0x00ef }
            boolean r0 = r18.isEmpty()
            if (r0 != 0) goto L_0x009c
            java.lang.String r3 = "Failed to set security level %s"
            boolean r0 = r16.mo9836c()
            if (r0 != 0) goto L_0x004c
        L_0x0048:
            r0 = 1
        L_0x0049:
            r9 = r16
            goto L_0x0096
        L_0x004c:
            java.lang.String r0 = r16.getSecurityLevel()
            java.lang.String r9 = ""
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L_0x005a
            r0 = 0
            goto L_0x0049
        L_0x005a:
            java.lang.Object[] r9 = new java.lang.Object[r4]
            r9[r5] = r0
            r9[r6] = r2
            java.lang.String r10 = "Security level: current %s, new %s"
            VN0.b(r7, r10, r9)
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x006c
            goto L_0x0048
        L_0x006c:
            r9 = r16
            android.media.MediaDrm r0 = r9.f2487a     // Catch:{ IllegalArgumentException -> 0x0082, IllegalStateException -> 0x0077 }
            java.lang.String r10 = "securityLevel"
            r0.setPropertyString(r10, r2)     // Catch:{ IllegalArgumentException -> 0x0082, IllegalStateException -> 0x0077 }
            r0 = 1
            goto L_0x0096
        L_0x0077:
            r0 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r4]
            r10[r5] = r2
            r10[r6] = r0
            VN0.a(r7, r3, r10)
            goto L_0x008c
        L_0x0082:
            r0 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r4]
            r10[r5] = r2
            r10[r6] = r0
            VN0.a(r7, r3, r10)
        L_0x008c:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r5] = r2
            java.lang.String r2 = "Security level %s not supported!"
            VN0.a(r7, r2, r0)
            r0 = 0
        L_0x0096:
            if (r0 != 0) goto L_0x009e
            r9.mo9838e()
            return r8
        L_0x009c:
            r9 = r16
        L_0x009e:
            boolean r0 = r17.isEmpty()
            if (r0 != 0) goto L_0x00e5
            java.lang.String r2 = "Failed to set security origin %s"
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r5] = r1
            java.lang.String r3 = "Set origin: %s"
            VN0.b(r7, r3, r0)
            boolean r0 = r9.mo9836c()
            if (r0 != 0) goto L_0x00b6
            goto L_0x00df
        L_0x00b6:
            android.media.MediaDrm r0 = r9.f2487a     // Catch:{ IllegalArgumentException -> 0x00cb, IllegalStateException -> 0x00c0 }
            java.lang.String r3 = "origin"
            r0.setPropertyString(r3, r1)     // Catch:{ IllegalArgumentException -> 0x00cb, IllegalStateException -> 0x00c0 }
            r9.f2496j = r6     // Catch:{ IllegalArgumentException -> 0x00cb, IllegalStateException -> 0x00c0 }
            goto L_0x00df
        L_0x00c0:
            r0 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r4]
            r3[r5] = r1
            r3[r6] = r0
            VN0.a(r7, r2, r3)
            goto L_0x00d5
        L_0x00cb:
            r0 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r4]
            r3[r5] = r1
            r3[r6] = r0
            VN0.a(r7, r2, r3)
        L_0x00d5:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r5] = r1
            java.lang.String r1 = "Security origin %s not supported!"
            VN0.a(r7, r1, r0)
            r6 = 0
        L_0x00df:
            if (r6 != 0) goto L_0x00e5
            r9.mo9838e()
            return r8
        L_0x00e5:
            if (r19 == 0) goto L_0x00ee
            boolean r0 = r9.mo9832a()
            if (r0 != 0) goto L_0x00ee
            return r8
        L_0x00ee:
            return r9
        L_0x00ef:
            r0 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r6]
            r1[r5] = r0
            VN0.a(r7, r3, r1)
            return r8
        L_0x00f8:
            r0 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r6]
            r1[r5] = r0
            VN0.a(r7, r3, r1)
            return r8
        L_0x0101:
            r0 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r6]
            r1[r5] = r0
            java.lang.String r0 = "Unsupported DRM scheme"
            VN0.a(r7, r0, r1)
        L_0x010b:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.create(byte[], java.lang.String, java.lang.String, boolean, long, long):org.chromium.media.MediaDrmBridge");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0084  */
    @org.chromium.base.annotations.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createSessionFromNative(byte[] r10, java.lang.String r11, int r12, java.lang.String[] r13, long r14) {
        /*
            r9 = this;
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r0 = 2
            r6 = 0
            if (r13 == 0) goto L_0x0025
            int r1 = r13.length
            int r1 = r1 % r0
            if (r1 != 0) goto L_0x001d
            r1 = 0
        L_0x000e:
            int r2 = r13.length
            if (r1 >= r2) goto L_0x0025
            r2 = r13[r1]
            int r3 = r1 + 1
            r3 = r13[r3]
            r5.put(r2, r3)
            int r1 = r1 + 2
            goto L_0x000e
        L_0x001d:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Additional data array doesn't have equal keys/values"
            r10.<init>(r11)
            throw r10
        L_0x0025:
            android.media.MediaDrm r13 = r9.f2487a
            java.lang.String r7 = "cr_media"
            if (r13 != 0) goto L_0x0038
            java.lang.Object[] r10 = new java.lang.Object[r6]
            java.lang.String r11 = "createSession() called when MediaDrm is null."
            VN0.a(r7, r11, r10)
            java.lang.String r10 = "MediaDrm released previously."
            r9.mo9824a((long) r14, (java.lang.String) r10)
            goto L_0x008c
        L_0x0038:
            r13 = 0
            r8 = 1
            byte[] r1 = r9.mo9837d()     // Catch:{ NotProvisionedException -> 0x0077 }
            if (r1 != 0) goto L_0x0046
            java.lang.String r10 = "Open session failed."
            r9.mo9824a((long) r14, (java.lang.String) r10)     // Catch:{ NotProvisionedException -> 0x0077 }
            goto L_0x008c
        L_0x0046:
            if (r12 != r0) goto L_0x004d
            w53$a r13 = w53.a.a(r1)     // Catch:{ NotProvisionedException -> 0x0074 }
            goto L_0x0051
        L_0x004d:
            w53$a r13 = w53.a.b(r1)     // Catch:{ NotProvisionedException -> 0x0074 }
        L_0x0051:
            r0 = r9
            r1 = r13
            r2 = r10
            r3 = r11
            r4 = r12
            android.media.MediaDrm$KeyRequest r10 = r0.mo9821a((w53.a) r1, (byte[]) r2, (java.lang.String) r3, (int) r4, (java.util.HashMap<java.lang.String, java.lang.String>) r5)     // Catch:{ NotProvisionedException -> 0x0074 }
            if (r10 != 0) goto L_0x0065
            r9.mo9826a((w53.a) r13)     // Catch:{ NotProvisionedException -> 0x0074 }
            java.lang.String r10 = "Generate request failed."
            r9.mo9824a((long) r14, (java.lang.String) r10)     // Catch:{ NotProvisionedException -> 0x0074 }
            goto L_0x008c
        L_0x0065:
            r13.a()     // Catch:{ NotProvisionedException -> 0x0074 }
            r9.mo9825a((long) r14, (w53.a) r13)     // Catch:{ NotProvisionedException -> 0x0074 }
            r9.mo9828a((w53.a) r13, (android.media.MediaDrm.KeyRequest) r10)     // Catch:{ NotProvisionedException -> 0x0074 }
            w53 r10 = r9.f2493g     // Catch:{ NotProvisionedException -> 0x0074 }
            r10.a(r13, r11, r12)     // Catch:{ NotProvisionedException -> 0x0074 }
            goto L_0x008c
        L_0x0074:
            r10 = move-exception
            r11 = 1
            goto L_0x0079
        L_0x0077:
            r10 = move-exception
            r11 = 0
        L_0x0079:
            java.lang.Object[] r12 = new java.lang.Object[r8]
            r12[r6] = r10
            java.lang.String r10 = "Device not provisioned"
            VN0.a(r7, r10, r12)
            if (r11 == 0) goto L_0x0087
            r9.mo9826a((w53.a) r13)
        L_0x0087:
            java.lang.String r10 = "Device not provisioned during createSession()."
            r9.mo9824a((long) r14, (java.lang.String) r10)
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.createSessionFromNative(byte[], java.lang.String, int, java.lang.String[], long):void");
    }

    @CalledByNative
    private void destroy() {
        this.f2489c = 0;
        if (this.f2487a != null) {
            mo9838e();
        }
    }

    @CalledByNative
    public static int getFirstApiLevel() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke((Object) null, new Object[]{"ro.product.first_api_level", 0})).intValue();
        } catch (Exception e) {
            VN0.a("Exception while getting system property %s. Using default.", "ro.product.first_api_level", new Object[]{e});
            return 0;
        }
    }

    @CalledByNative
    private String getSecurityLevel() {
        if (this.f2487a == null || !mo9836c()) {
            VN0.a("cr_media", "getSecurityLevel(): MediaDrm is null or security level is not supported.", new Object[0]);
            return BuildConfig.FLAVOR;
        }
        try {
            return this.f2487a.getPropertyString("securityLevel");
        } catch (IllegalStateException e) {
            VN0.a("cr_media", "Failed to get current security level", new Object[]{e});
            return BuildConfig.FLAVOR;
        } catch (Exception e2) {
            VN0.a("cr_media", "Failed to get current security level", new Object[]{e2});
            return BuildConfig.FLAVOR;
        }
    }

    @CalledByNative
    public static boolean isCryptoSchemeSupported(byte[] bArr, String str) {
        UUID c = m3521c(bArr);
        if (str.isEmpty()) {
            return MediaDrm.isCryptoSchemeSupported(c);
        }
        return MediaDrm.isCryptoSchemeSupported(c, str);
    }

    @TargetApi(23)
    @CalledByNative
    private void loadSession(byte[] bArr, long j) {
        w53 w53 = this.f2493g;
        w53.c.mo9851b(bArr, new v53(w53, new C0450b(j)));
    }

    private native void nativeOnMediaCryptoReady(long j, MediaCrypto mediaCrypto);

    private native void nativeOnPromiseRejected(long j, long j2, String str);

    private native void nativeOnPromiseResolved(long j, long j2);

    private native void nativeOnPromiseResolvedWithSession(long j, long j2, byte[] bArr);

    private native void nativeOnProvisionRequest(long j, String str, byte[] bArr);

    private native void nativeOnProvisioningComplete(long j, boolean z);

    private native void nativeOnSessionClosed(long j, byte[] bArr);

    private native void nativeOnSessionExpirationUpdate(long j, byte[] bArr, long j2);

    private native void nativeOnSessionKeysChange(long j, byte[] bArr, Object[] objArr, boolean z, boolean z2);

    private native void nativeOnSessionMessage(long j, byte[] bArr, int i, byte[] bArr2);

    @CalledByNative
    private void processProvisionResponse(boolean z, byte[] bArr) {
        this.f2495i = false;
        mo9831a((this.f2487a == null || !z) ? false : mo9835b(bArr));
        if (this.f2491e) {
            C0458j jVar = f2486o;
            jVar.f2517a = false;
            while (!jVar.f2518b.isEmpty()) {
                jVar.f2518b.remove();
                jVar.f2518b.element().run();
                if (jVar.f2517a) {
                    return;
                }
            }
        }
    }

    @CalledByNative
    private void provision() {
        if (!this.f2496j) {
            VN0.a("cr_media", "Calling provision() without an origin.", new Object[0]);
            nativeOnProvisioningComplete(this.f2489c, false);
            return;
        }
        try {
            byte[] d = mo9837d();
            if (d != null) {
                mo9826a(w53.a.b(d));
            }
            nativeOnProvisioningComplete(this.f2489c, true);
        } catch (NotProvisionedException unused) {
            if (!mo9839f()) {
                nativeOnProvisioningComplete(this.f2489c, false);
            }
        }
    }

    @CalledByNative
    private void removeSession(byte[] bArr, long j) {
        w53.a a = mo9822a(bArr);
        if (a == null) {
            mo9824a(j, "Session doesn't exist");
            return;
        }
        w53.b a2 = this.f2493g.a(a);
        if (a2.c == 1) {
            mo9824a(j, "Removing temporary session isn't implemented");
            return;
        }
        w53 w53 = this.f2493g;
        C0452d dVar = new C0452d(j, a, a2);
        w53.b a3 = w53.a(a);
        a3.c = 3;
        w53.c.mo9848a(w53.b.a(a3), (Callback<Boolean>) dVar);
    }

    @CalledByNative
    private boolean setServerCertificate(byte[] bArr) {
        if (!mo9836c()) {
            return true;
        }
        try {
            this.f2487a.setPropertyByteArray("serviceCertificate", bArr);
            return true;
        } catch (IllegalArgumentException e) {
            VN0.a("cr_media", "Failed to set server certificate", new Object[]{e});
            return false;
        } catch (IllegalStateException e2) {
            VN0.a("cr_media", "Failed to set server certificate", new Object[]{e2});
            return false;
        }
    }

    @CalledByNative
    private void unprovision() {
        if (this.f2487a != null && this.f2496j) {
            mo9835b(f2485n);
        }
    }

    @CalledByNative
    private void updateSession(byte[] bArr, byte[] bArr2, long j) {
        int i;
        byte[] bArr3 = bArr2;
        long j2 = j;
        if (this.f2487a == null) {
            mo9824a(j2, "updateSession() called when MediaDrm is null.");
            return;
        }
        w53.a a = mo9822a(bArr);
        if (a == null) {
            StringBuilder a2 = Eo.a("Invalid session in updateSession: ");
            a2.append(w53.a.c(bArr));
            mo9824a(j2, a2.toString());
            return;
        }
        try {
            w53.b a3 = this.f2493g.a(a);
            boolean z = a3.c == 3;
            byte[] bArr4 = null;
            if (z) {
                this.f2487a.provideKeyResponse(a.c, bArr3);
            } else {
                bArr4 = this.f2487a.provideKeyResponse(a.b, bArr3);
            }
            byte[] bArr5 = bArr4;
            C0457i iVar = r1;
            C0457i iVar2 = new C0457i(a, j, z);
            if (z) {
                this.f2493g.a(a, iVar);
            } else if (a3.c != 2 || bArr5 == null || bArr5.length <= 0) {
                i = 1;
                try {
                    iVar.onResult(true);
                } catch (NotProvisionedException e) {
                    e = e;
                    Object[] objArr = new Object[i];
                    objArr[0] = e;
                    VN0.a("cr_media", "failed to provide key response", objArr);
                    mo9824a(j2, "Update session failed.");
                    mo9838e();
                } catch (DeniedByServerException e2) {
                    e = e2;
                    Object[] objArr2 = new Object[i];
                    objArr2[0] = e;
                    VN0.a("cr_media", "failed to provide key response", objArr2);
                    mo9824a(j2, "Update session failed.");
                    mo9838e();
                } catch (IllegalStateException e3) {
                    e = e3;
                    Object[] objArr3 = new Object[i];
                    objArr3[0] = e;
                    VN0.a("cr_media", "failed to provide key response", objArr3);
                    mo9824a(j2, "Update session failed.");
                    mo9838e();
                }
            } else {
                this.f2493g.a(a, bArr5, iVar);
            }
        } catch (NotProvisionedException e4) {
            e = e4;
            i = 1;
        } catch (DeniedByServerException e5) {
            e = e5;
            i = 1;
            Object[] objArr22 = new Object[i];
            objArr22[0] = e;
            VN0.a("cr_media", "failed to provide key response", objArr22);
            mo9824a(j2, "Update session failed.");
            mo9838e();
        } catch (IllegalStateException e6) {
            e = e6;
            i = 1;
            Object[] objArr32 = new Object[i];
            objArr32[0] = e;
            VN0.a("cr_media", "failed to provide key response", objArr32);
            mo9824a(j2, "Update session failed.");
            mo9838e();
        }
    }

    /* renamed from: d */
    public final byte[] mo9837d() throws NotProvisionedException {
        try {
            return (byte[]) this.f2487a.openSession().clone();
        } catch (RuntimeException e) {
            VN0.a("cr_media", "Cannot open a new session", new Object[]{e});
            mo9838e();
            return null;
        } catch (NotProvisionedException e2) {
            throw e2;
        } catch (MediaDrmException e3) {
            VN0.a("cr_media", "Cannot open a new session", new Object[]{e3});
            mo9838e();
            return null;
        }
    }

    /* renamed from: e */
    public final void mo9838e() {
        for (w53.a aVar : this.f2493g.a()) {
            try {
                this.f2487a.removeKeys(aVar.b);
            } catch (Exception e) {
                VN0.a("cr_media", "removeKeys failed: ", new Object[]{e});
            }
            mo9826a(aVar);
            if (mo9834b()) {
                nativeOnSessionClosed(this.f2489c, aVar.a);
            }
        }
        this.f2493g = new w53(this.f2494h);
        w53.a aVar2 = this.f2492f;
        if (aVar2 != null) {
            mo9826a(aVar2);
            this.f2492f = null;
        }
        MediaDrm mediaDrm = this.f2487a;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.f2487a = null;
        }
        MediaCrypto mediaCrypto = this.f2488b;
        if (mediaCrypto != null) {
            mediaCrypto.release();
            this.f2488b = null;
        } else if (mo9834b()) {
            nativeOnMediaCryptoReady(this.f2489c, (MediaCrypto) null);
        }
    }

    /* renamed from: f */
    public final boolean mo9839f() {
        this.f2495i = true;
        if (!mo9834b()) {
            return false;
        }
        if (this.f2491e) {
            f2486o.f2517a = true;
        }
        try {
            MediaDrm.ProvisionRequest provisionRequest = this.f2487a.getProvisionRequest();
            nativeOnProvisionRequest(this.f2489c, provisionRequest.getDefaultUrl(), provisionRequest.getData());
            return true;
        } catch (IllegalStateException e) {
            VN0.a("cr_media", "Failed to get provisioning request", new Object[]{e});
            return false;
        }
    }

    /* renamed from: b */
    public final boolean mo9834b() {
        return this.f2489c != 0;
    }

    /* renamed from: a */
    public final void mo9823a(long j) {
        mo9825a(j, w53.a.b(new byte[0]));
    }

    /* renamed from: b */
    public final void mo9833b(w53.a aVar, long j) {
        mo9826a(aVar);
        this.f2493g.a(aVar, new C0451c(j));
    }

    /* renamed from: c */
    public final boolean mo9836c() {
        return this.f2490d.equals(f2483l);
    }

    /* renamed from: a */
    public static List<KeyStatus> m3509a(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyStatus(f2484m, i, (C0449a) null));
        return arrayList;
    }

    /* renamed from: b */
    public boolean mo9835b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            VN0.a("cr_media", "Invalid provision response.", new Object[0]);
            return false;
        }
        try {
            this.f2487a.provideProvisionResponse(bArr);
            return true;
        } catch (DeniedByServerException e) {
            VN0.a("cr_media", "failed to provide provision response", new Object[]{e});
            return false;
        } catch (IllegalStateException e2) {
            VN0.a("cr_media", "failed to provide provision response", new Object[]{e2});
            return false;
        }
    }

    /* renamed from: a */
    public final boolean mo9832a() {
        try {
            byte[] d = mo9837d();
            if (d == null) {
                VN0.a("cr_media", "Cannot create MediaCrypto Session.", new Object[0]);
                return false;
            }
            this.f2492f = w53.a.b(d);
            this.f2492f.a();
            try {
                if (MediaCrypto.isCryptoSchemeSupported(this.f2490d)) {
                    this.f2488b = new MediaCrypto(this.f2490d, this.f2492f.b);
                    MediaCrypto mediaCrypto = this.f2488b;
                    if (mo9834b()) {
                        nativeOnMediaCryptoReady(this.f2489c, mediaCrypto);
                    }
                    return true;
                }
                VN0.a("cr_media", "Cannot create MediaCrypto for unsupported scheme.", new Object[0]);
                mo9838e();
                return false;
            } catch (MediaCryptoException e) {
                VN0.a("cr_media", "Cannot create MediaCrypto", new Object[]{e});
            }
        } catch (NotProvisionedException unused) {
            C0458j jVar = f2486o;
            if (!jVar.f2517a) {
                return mo9839f();
            }
            jVar.f2518b.add(new C0449a());
            return true;
        }
    }

    /* renamed from: a */
    public final MediaDrm.KeyRequest mo9821a(w53.a aVar, byte[] bArr, String str, int i, HashMap<String, String> hashMap) throws NotProvisionedException {
        byte[] bArr2;
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        HashMap<String, String> hashMap2 = hashMap;
        if (i == 3) {
            try {
                bArr2 = aVar.c;
            } catch (IllegalStateException e) {
                if (Build.VERSION.SDK_INT < 21 || !(e instanceof MediaDrm.MediaDrmStateException)) {
                    return null;
                }
                VN0.a("cr_media", "MediaDrmStateException fired during getKeyRequest().", new Object[]{e});
                return null;
            }
        } else {
            bArr2 = aVar.b;
        }
        return this.f2487a.getKeyRequest(bArr2, bArr, str, i, hashMap2);
    }

    /* renamed from: a */
    public final w53.a mo9822a(byte[] bArr) {
        if (this.f2492f == null) {
            VN0.a("cr_media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        w53.a a = this.f2493g.a(bArr);
        if (a == null) {
            return null;
        }
        return a;
    }

    /* renamed from: a */
    public static /* synthetic */ w53.a m3510a(MediaDrmBridge mediaDrmBridge, byte[] bArr) {
        if (mediaDrmBridge.f2492f == null) {
            VN0.a("cr_media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        w53 w53 = mediaDrmBridge.f2493g;
        w53.a a = w53.a(w53.b, bArr);
        if (a == null) {
            return null;
        }
        return a;
    }

    /* renamed from: a */
    public final void mo9826a(w53.a aVar) {
        try {
            this.f2487a.closeSession(aVar.b);
        } catch (Exception e) {
            VN0.a("cr_media", "closeSession failed: ", new Object[]{e});
        }
    }

    @TargetApi(23)
    /* renamed from: a */
    public final void mo9827a(w53.a aVar, long j) {
        try {
            byte[] d = mo9837d();
            if (d == null) {
                mo9824a(j, "Failed to open session to load license.");
                return;
            }
            w53 w53 = this.f2493g;
            w53.b a = w53.a(aVar);
            aVar.b = d;
            w53.b.put(ByteBuffer.wrap(d), a);
            if (this.f2493g.a(aVar).c == 3) {
                VN0.c("cr_media", "Persistent license is waiting for release ack.", new Object[0]);
                mo9825a(j, aVar);
                mo9830a(aVar, m3509a(1).toArray(), false, true);
                return;
            }
            this.f2497k = new C0459k(aVar);
            this.f2487a.restoreKeys(aVar.b, aVar.c);
            mo9825a(j, aVar);
            this.f2497k.mo9846a();
            this.f2497k = null;
        } catch (NotProvisionedException unused) {
            VN0.c("cr_media", "Persistent license load fail because origin isn't provisioned.", new Object[0]);
            mo9833b(aVar, j);
        } catch (IllegalStateException unused2) {
            mo9833b(aVar, j);
        }
    }

    /* renamed from: a */
    public final void mo9829a(w53.a aVar, String str, long j) {
        try {
            MediaDrm.KeyRequest a = mo9821a(aVar, (byte[]) null, str, 3, (HashMap<String, String>) null);
            if (a == null) {
                mo9824a(j, "Fail to generate key release request");
                return;
            }
            if (mo9834b()) {
                nativeOnPromiseResolved(this.f2489c, j);
            }
            mo9828a(aVar, a);
        } catch (NotProvisionedException unused) {
            VN0.a("cr_media", "removeSession called on unprovisioned device", new Object[0]);
            mo9824a(j, "Unknown failure");
        }
    }

    /* renamed from: a */
    public void mo9831a(boolean z) {
        if (!this.f2491e) {
            nativeOnProvisioningComplete(this.f2489c, z);
            if (!z) {
                mo9838e();
            }
        } else if (!z) {
            mo9838e();
        } else if (!this.f2496j) {
            mo9832a();
        } else {
            this.f2494h.mo9847a(new C0453e());
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m3515a(MediaDrmBridge mediaDrmBridge, w53.a aVar, Runnable runnable) {
        C0459k kVar = mediaDrmBridge.f2497k;
        if (kVar == null || !Arrays.equals(kVar.f2519a.a, aVar.a)) {
            runnable.run();
        } else {
            mediaDrmBridge.f2497k.f2520b.add(runnable);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m3512a(MediaDrmBridge mediaDrmBridge, long j) {
        if (mediaDrmBridge.mo9834b()) {
            mediaDrmBridge.nativeOnPromiseResolved(mediaDrmBridge.f2489c, j);
        }
    }

    /* renamed from: a */
    public final void mo9825a(long j, w53.a aVar) {
        if (mo9834b()) {
            nativeOnPromiseResolvedWithSession(this.f2489c, j, aVar.a);
        }
    }

    /* renamed from: a */
    public final void mo9824a(long j, String str) {
        VN0.a("cr_media", "onPromiseRejected: %s", new Object[]{str});
        if (mo9834b()) {
            nativeOnPromiseRejected(this.f2489c, j, str);
        }
    }

    @TargetApi(23)
    /* renamed from: a */
    public final void mo9828a(w53.a aVar, MediaDrm.KeyRequest keyRequest) {
        int i;
        if (mo9834b()) {
            if (Build.VERSION.SDK_INT >= 23) {
                i = keyRequest.getRequestType();
            } else {
                i = !keyRequest.getDefaultUrl().isEmpty();
            }
            nativeOnSessionMessage(this.f2489c, aVar.a, i, keyRequest.getData());
        }
    }

    /* renamed from: a */
    public final void mo9830a(w53.a aVar, Object[] objArr, boolean z, boolean z2) {
        if (mo9834b()) {
            nativeOnSessionKeysChange(this.f2489c, aVar.a, objArr, z, z2);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m3514a(MediaDrmBridge mediaDrmBridge, w53.a aVar, long j) {
        if (mediaDrmBridge.mo9834b()) {
            mediaDrmBridge.nativeOnSessionExpirationUpdate(mediaDrmBridge.f2489c, aVar.a, j);
        }
    }
}
