package com.facebook.drawee.controller;

import android.content.Context;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> {

    /* renamed from: m */
    public static final Fr<Object> f262m = new Cr();

    /* renamed from: n */
    public static final NullPointerException f263n = new NullPointerException("No image request was specified!");

    /* renamed from: o */
    public static final AtomicLong f264o = new AtomicLong();

    /* renamed from: a */
    public final Context f265a;

    /* renamed from: b */
    public final Set<Fr> f266b;

    /* renamed from: c */
    public Object f267c;

    /* renamed from: d */
    public REQUEST f268d;

    /* renamed from: e */
    public REQUEST f269e;

    /* renamed from: f */
    public REQUEST[] f270f;

    /* renamed from: g */
    public boolean f271g;

    /* renamed from: h */
    public Fr<? super INFO> f272h;

    /* renamed from: i */
    public boolean f273i;

    /* renamed from: j */
    public boolean f274j;

    /* renamed from: k */
    public String f275k;

    /* renamed from: l */
    public js f276l;

    /* compiled from: PG */
    public enum CacheLevel {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    public AbstractDraweeControllerBuilder(Context context, Set<Fr> set) {
        this.f265a = context;
        this.f266b = set;
        mo366b();
    }

    /* renamed from: a */
    public abstract Uq<IMAGE> mo362a(js jsVar, String str, REQUEST request, Object obj, CacheLevel cacheLevel);

    /* renamed from: a */
    public mq<Uq<IMAGE>> mo365a(js jsVar, String str, REQUEST request, CacheLevel cacheLevel) {
        return new Dr(this, jsVar, str, request, this.f267c, cacheLevel);
    }

    /* renamed from: b */
    public final void mo366b() {
        this.f267c = null;
        this.f268d = null;
        this.f269e = null;
        this.f270f = null;
        this.f271g = true;
        this.f272h = null;
        this.f273i = false;
        this.f274j = false;
        this.f276l = null;
        this.f275k = null;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public Br mo361a() {
        jr jrVar;
        qp qpVar;
        REQUEST request;
        kq.b(this.f270f == null || this.f268d == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        kq.b(true, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
        if (this.f268d == null && this.f270f == null && (request = this.f269e) != null) {
            this.f268d = request;
            this.f269e = null;
        }
        kr krVar = (kr) this;
        FrescoSystrace.m409b().beginSection("obtainController");
        try {
            jr jrVar2 = krVar.f276l;
            String valueOf = String.valueOf(f264o.getAndIncrement());
            if (jrVar2 instanceof jr) {
                jrVar = jrVar2;
            } else {
                mr mrVar = krVar.q;
                jr jrVar3 = new jr(mrVar.a, mrVar.b, mrVar.c, mrVar.d, mrVar.e, mrVar.f);
                mq mqVar = mrVar.g;
                if (mqVar != null) {
                    jrVar3.z = ((Boolean) mqVar.get()).booleanValue();
                }
                jrVar = jrVar3;
            }
            mq a = krVar.mo363a(jrVar, valueOf);
            ImageRequest imageRequest = (ImageRequest) krVar.f268d;
            Xs xs = krVar.p.f342h;
            if (xs == null || imageRequest == null) {
                qpVar = null;
            } else {
                qpVar = imageRequest.f397o != null ? xs.c(imageRequest, krVar.f267c) : xs.a(imageRequest, krVar.f267c);
            }
            jrVar.a(valueOf, krVar.f267c);
            jrVar.s = false;
            jrVar.y = a;
            jrVar.a((Vt) null);
            jrVar.x = qpVar;
            jrVar.A = null;
            jrVar.g();
            jrVar.a((or) null);
            jrVar.a((rr) null);
            FrescoSystrace.m408a();
            jrVar.n = false;
            jrVar.o = this.f275k;
            if (this.f273i) {
                if (jrVar.d == null) {
                    jrVar.d = new yr();
                }
                jrVar.d.a = this.f273i;
                if (jrVar.e == null) {
                    jrVar.e = new GestureDetector(this.f265a);
                    GestureDetector gestureDetector = jrVar.e;
                    if (gestureDetector != null) {
                        gestureDetector.mo404a((GestureDetector.ClickListener) jrVar);
                    }
                }
            }
            Set<Fr> set = this.f266b;
            if (set != null) {
                for (Fr a2 : set) {
                    jrVar.a(a2);
                }
            }
            Fr<? super INFO> fr = this.f272h;
            if (fr != null) {
                jrVar.a(fr);
            }
            if (this.f274j) {
                jrVar.a(f262m);
            }
            return jrVar;
        } catch (Throwable th) {
            FrescoSystrace.m408a();
            throw th;
        }
    }

    /* renamed from: a */
    public mq<Uq<IMAGE>> mo363a(js jsVar, String str) {
        mq<Uq<IMAGE>> mqVar = null;
        REQUEST request = this.f268d;
        if (request != null) {
            mqVar = mo364a(jsVar, str, request);
        } else {
            REQUEST[] requestArr = this.f270f;
            if (requestArr != null) {
                boolean z = this.f271g;
                ArrayList arrayList = new ArrayList(requestArr.length * 2);
                if (z) {
                    for (REQUEST a : requestArr) {
                        arrayList.add(mo365a(jsVar, str, a, CacheLevel.BITMAP_MEMORY_CACHE));
                    }
                }
                for (REQUEST a2 : requestArr) {
                    arrayList.add(mo364a(jsVar, str, a2));
                }
                mqVar = new Zq<>(arrayList);
            }
        }
        if (!(mqVar == null || this.f269e == null)) {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(mqVar);
            arrayList2.add(mo364a(jsVar, str, this.f269e));
            mqVar = new ar<>(arrayList2, false);
        }
        return mqVar == null ? new Vq(f263n) : mqVar;
    }

    /* renamed from: a */
    public mq<Uq<IMAGE>> mo364a(js jsVar, String str, REQUEST request) {
        return new Dr(this, jsVar, str, request, this.f267c, CacheLevel.FULL_FETCH);
    }
}
