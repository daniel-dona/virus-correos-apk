package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
public class ImagePipeline {

    /* renamed from: j */
    public static final CancellationException f334j = new CancellationException("Prefetching is not enabled");

    /* renamed from: a */
    public final Ft f335a;

    /* renamed from: b */
    public final du f336b;

    /* renamed from: c */
    public final mq<Boolean> f337c;

    /* renamed from: d */
    public final dt<qp, Vt> f338d;

    /* renamed from: e */
    public final dt<qp, PooledByteBuffer> f339e;

    /* renamed from: f */
    public final Qs f340f;

    /* renamed from: g */
    public final Qs f341g;

    /* renamed from: h */
    public final Rs f342h;

    /* renamed from: i */
    public AtomicLong f343i = new AtomicLong();

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$8 */
    /* compiled from: PG */
    public static /* synthetic */ class C00788 {

        /* renamed from: $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice */
        public static final /* synthetic */ int[] f344x9ce557aa = new int[ImageRequest.CacheChoice.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice[] r0 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f344x9ce557aa = r0
                int[] r0 = f344x9ce557aa     // Catch:{ NoSuchFieldError -> 0x0010 }
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                r1 = 1
                r0[r1] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                int[] r0 = f344x9ce557aa     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.SMALL     // Catch:{ NoSuchFieldError -> 0x0018 }
                r1 = 0
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.C00788.<clinit>():void");
        }
    }

    public ImagePipeline(Ft ft, Set<du> set, mq<Boolean> mqVar, dt<qp, Vt> dtVar, dt<qp, PooledByteBuffer> dtVar2, Qs qs, Qs qs2, Rs rs, qw qwVar, mq<Boolean> mqVar2, mq<Boolean> mqVar3) {
        this.f335a = ft;
        this.f336b = new cu(set);
        this.f337c = mqVar;
        this.f338d = dtVar;
        this.f339e = dtVar2;
        this.f340f = qs;
        this.f341g = qs2;
        this.f342h = rs;
    }

    /* renamed from: a */
    public final du mo469a(ImageRequest imageRequest, du duVar) {
        if (duVar == null) {
            du duVar2 = imageRequest.f398p;
            if (duVar2 == null) {
                return this.f336b;
            }
            return new cu(new du[]{this.f336b, duVar2});
        }
        du duVar3 = imageRequest.f398p;
        if (duVar3 == null) {
            return new cu(new du[]{this.f336b, duVar});
        }
        return new cu(new du[]{this.f336b, duVar, duVar3});
    }

    /* renamed from: b */
    public final String mo475b() {
        return String.valueOf(this.f343i.getAndIncrement());
    }

    /* renamed from: c */
    public Uq<Eq<Vt>> mo477c(ImageRequest imageRequest, Object obj) {
        return mo468a(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE, (du) null);
    }

    /* renamed from: d */
    public Uq<Void> mo478d(ImageRequest imageRequest, Object obj) {
        Priority priority = Priority.MEDIUM;
        if (!((Boolean) this.f337c.get()).booleanValue()) {
            return Wq.a(f334j);
        }
        try {
            Zv b = this.f335a.b(imageRequest);
            ImageRequest.RequestLevel requestLevel = ImageRequest.RequestLevel.FULL_FETCH;
            cw a = mo469a(imageRequest, (du) null);
            try {
                return new Kt(b, new jw(imageRequest, mo475b(), a, obj, ImageRequest.RequestLevel.getMax(imageRequest.f394l, requestLevel), true, false, priority), a);
            } catch (Exception e) {
                return Wq.a(e);
            }
        } catch (Exception e2) {
            return Wq.a(e2);
        }
    }

    /* renamed from: b */
    public Uq<Eq<PooledByteBuffer>> mo474b(ImageRequest imageRequest, Object obj) {
        kq.a(imageRequest.f384b);
        try {
            Zv c = this.f335a.c(imageRequest);
            if (imageRequest.f390h != null) {
                ImageRequestBuilder a = ImageRequestBuilder.m404a(imageRequest);
                a.f401c = null;
                imageRequest = a.mo554a();
            }
            return mo465a(c, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, (du) null);
        } catch (Exception e) {
            return Wq.a(e);
        }
    }

    /* renamed from: a */
    public Uq<Eq<Vt>> mo466a(ImageRequest imageRequest, Object obj) {
        return mo468a(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, (du) null);
    }

    /* renamed from: a */
    public Uq<Eq<Vt>> mo467a(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel) {
        return mo468a(imageRequest, obj, requestLevel, (du) null);
    }

    /* renamed from: a */
    public Uq<Eq<Vt>> mo468a(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, du duVar) {
        try {
            return mo465a(this.f335a.a(imageRequest), imageRequest, requestLevel, obj, duVar);
        } catch (Exception e) {
            return Wq.a(e);
        }
    }

    /* renamed from: b */
    public boolean mo476b(Uri uri) {
        return mo472a(uri, ImageRequest.CacheChoice.SMALL) || mo472a(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    /* renamed from: a */
    public void mo470a() {
        C00744 r0 = new lq<qp>() {
            public boolean apply(qp qpVar) {
                return true;
            }
        };
        this.f338d.a(r0);
        this.f339e.a(r0);
    }

    /* renamed from: a */
    public boolean mo473a(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        Eq eq = this.f338d.get(this.f342h.a(imageRequest, (Object) null));
        try {
            boolean c = Eq.c(eq);
            if (eq != null) {
                eq.close();
            }
            return c;
        } catch (Throwable th) {
            Eq.b(eq);
            throw th;
        }
    }

    /* renamed from: a */
    public boolean mo472a(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        boolean a;
        ImageRequestBuilder a2 = ImageRequestBuilder.m403a(uri);
        a2.f404f = cacheChoice;
        ImageRequest a3 = a2.mo554a();
        qp b = this.f342h.b(a3, (Object) null);
        int ordinal = a3.f383a.ordinal();
        if (ordinal == 0) {
            Qs qs = this.f341g;
            if (!qs.b(b)) {
                a = qs.a(b);
                return a;
            }
        } else if (ordinal != 1) {
            return false;
        } else {
            Qs qs2 = this.f340f;
            if (!qs2.b(b)) {
                a = qs2.a(b);
                return a;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final <T> Uq<Eq<T>> mo465a(Zv<Eq<T>> zv, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, du duVar) {
        boolean z;
        cw a = mo469a(imageRequest, duVar);
        try {
            ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.f394l, requestLevel);
            String valueOf = String.valueOf(this.f343i.getAndIncrement());
            if (!imageRequest.f387e) {
                if (Oq.g(imageRequest.f384b)) {
                    z = false;
                    return new Jt(zv, new jw(imageRequest, valueOf, a, obj, max, false, z, imageRequest.f393k), a);
                }
            }
            z = true;
            return new Jt(zv, new jw(imageRequest, valueOf, a, obj, max, false, z, imageRequest.f393k), a);
        } catch (Exception e) {
            return Wq.a(e);
        }
    }

    /* renamed from: a */
    public boolean mo471a(final Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.f338d.b(new lq<qp>() {
            public boolean apply(qp qpVar) {
                return qpVar.a(uri);
            }
        });
    }
}
