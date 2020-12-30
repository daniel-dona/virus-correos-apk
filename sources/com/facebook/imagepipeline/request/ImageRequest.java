package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.imagepipeline.common.Priority;
import java.io.File;
import java.util.Arrays;
import jq;

/* compiled from: PG */
public class ImageRequest {

    /* renamed from: a */
    public final CacheChoice f383a;

    /* renamed from: b */
    public final Uri f384b;

    /* renamed from: c */
    public final int f385c;

    /* renamed from: d */
    public File f386d;

    /* renamed from: e */
    public final boolean f387e;

    /* renamed from: f */
    public final boolean f388f;

    /* renamed from: g */
    public final lt f389g;

    /* renamed from: h */
    public final nt f390h;

    /* renamed from: i */
    public final ot f391i;

    /* renamed from: j */
    public final kt f392j;

    /* renamed from: k */
    public final Priority f393k;

    /* renamed from: l */
    public final RequestLevel f394l;

    /* renamed from: m */
    public final boolean f395m;

    /* renamed from: n */
    public final boolean f396n;

    /* renamed from: o */
    public final Aw f397o;

    /* renamed from: p */
    public final du f398p;

    /* compiled from: PG */
    public enum CacheChoice {
        SMALL,
        DEFAULT
    }

    /* compiled from: PG */
    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        public int mValue;

        /* access modifiers changed from: public */
        RequestLevel(int i) {
            this.mValue = i;
        }

        public static RequestLevel getMax(RequestLevel requestLevel, RequestLevel requestLevel2) {
            return requestLevel.getValue() > requestLevel2.getValue() ? requestLevel : requestLevel2;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ImageRequest(com.facebook.imagepipeline.request.ImageRequestBuilder r5) {
        /*
            r4 = this;
            r4.<init>()
            com.facebook.imagepipeline.request.ImageRequest$CacheChoice r0 = r5.f404f
            r4.f383a = r0
            android.net.Uri r0 = r5.f399a
            r4.f384b = r0
            android.net.Uri r0 = r4.f384b
            r1 = 0
            if (r0 != 0) goto L_0x0011
            goto L_0x0072
        L_0x0011:
            boolean r2 = Oq.g(r0)
            if (r2 == 0) goto L_0x0019
            r0 = 0
            goto L_0x0073
        L_0x0019:
            boolean r2 = Oq.e(r0)
            if (r2 == 0) goto L_0x0031
            java.lang.String r0 = r0.getPath()
            java.lang.String r0 = sq.a(r0)
            boolean r0 = sq.b(r0)
            if (r0 == 0) goto L_0x002f
            r0 = 2
            goto L_0x0073
        L_0x002f:
            r0 = 3
            goto L_0x0073
        L_0x0031:
            boolean r2 = Oq.d(r0)
            if (r2 == 0) goto L_0x0039
            r0 = 4
            goto L_0x0073
        L_0x0039:
            java.lang.String r2 = Oq.a(r0)
            java.lang.String r3 = "asset"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
            r0 = 5
            goto L_0x0073
        L_0x0047:
            java.lang.String r2 = Oq.a(r0)
            java.lang.String r3 = "res"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0055
            r0 = 6
            goto L_0x0073
        L_0x0055:
            java.lang.String r2 = Oq.a(r0)
            java.lang.String r3 = "data"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0063
            r0 = 7
            goto L_0x0073
        L_0x0063:
            java.lang.String r0 = Oq.a(r0)
            java.lang.String r2 = "android.resource"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0072
            r0 = 8
            goto L_0x0073
        L_0x0072:
            r0 = -1
        L_0x0073:
            r4.f385c = r0
            boolean r0 = r5.f405g
            r4.f387e = r0
            boolean r0 = r5.f406h
            r4.f388f = r0
            lt r0 = r5.f403e
            r4.f389g = r0
            nt r0 = r5.f401c
            r4.f390h = r0
            ot r0 = r5.f402d
            if (r0 != 0) goto L_0x008b
            ot r0 = ot.c
        L_0x008b:
            r4.f391i = r0
            kt r0 = r5.f412n
            r4.f392j = r0
            com.facebook.imagepipeline.common.Priority r0 = r5.f407i
            r4.f393k = r0
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r0 = r5.f400b
            r4.f394l = r0
            boolean r0 = r5.f409k
            if (r0 == 0) goto L_0x00a6
            android.net.Uri r0 = r5.f399a
            boolean r0 = Oq.g(r0)
            if (r0 == 0) goto L_0x00a6
            r1 = 1
        L_0x00a6:
            r4.f395m = r1
            boolean r0 = r5.f410l
            r4.f396n = r0
            Aw r0 = r5.f408j
            r4.f397o = r0
            du r5 = r5.f411m
            r4.f398p = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.request.ImageRequest.<init>(com.facebook.imagepipeline.request.ImageRequestBuilder):void");
    }

    /* renamed from: a */
    public boolean mo548a() {
        return this.f388f;
    }

    /* renamed from: b */
    public synchronized File mo549b() {
        if (this.f386d == null) {
            this.f386d = new File(this.f384b.getPath());
        }
        return this.f386d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageRequest)) {
            return false;
        }
        ImageRequest imageRequest = (ImageRequest) obj;
        if (!jq.a(this.f384b, imageRequest.f384b) || !jq.a(this.f383a, imageRequest.f383a) || !jq.a(this.f386d, imageRequest.f386d) || !jq.a(this.f392j, imageRequest.f392j) || !jq.a(this.f389g, imageRequest.f389g) || !jq.a(this.f390h, imageRequest.f390h) || !jq.a(this.f391i, imageRequest.f391i)) {
            return false;
        }
        Aw aw = this.f397o;
        qp qpVar = null;
        qp a = aw != null ? aw.a() : null;
        Aw aw2 = imageRequest.f397o;
        if (aw2 != null) {
            qpVar = aw2.a();
        }
        return jq.a(a, qpVar);
    }

    public int hashCode() {
        Aw aw = this.f397o;
        return Arrays.hashCode(new Object[]{this.f383a, this.f384b, this.f386d, this.f392j, this.f389g, this.f390h, this.f391i, aw != null ? aw.a() : null});
    }

    public String toString() {
        jq.a a = jq.a(this);
        a.a("uri", this.f384b);
        a.a("cacheChoice", this.f383a);
        a.a("decodeOptions", this.f389g);
        a.a("postprocessor", this.f397o);
        a.a("priority", this.f393k);
        a.a("resizeOptions", this.f390h);
        a.a("rotationOptions", this.f391i);
        a.a("bytesRange", this.f392j);
        return a.toString();
    }
}
