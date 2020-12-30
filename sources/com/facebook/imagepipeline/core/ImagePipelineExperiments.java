package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.webp.WebpBitmapFactory;

/* compiled from: PG */
public class ImagePipelineExperiments {

    /* renamed from: a */
    public final boolean f345a;

    /* renamed from: b */
    public final WebpBitmapFactory.WebpErrorLogger f346b;

    /* renamed from: c */
    public final boolean f347c;

    /* renamed from: d */
    public final WebpBitmapFactory f348d;

    /* renamed from: e */
    public final boolean f349e;

    /* renamed from: f */
    public final boolean f350f;

    /* renamed from: g */
    public final int f351g;

    /* renamed from: h */
    public final int f352h;

    /* renamed from: i */
    public boolean f353i;

    /* renamed from: j */
    public final boolean f354j;

    /* renamed from: k */
    public final ProducerFactoryMethod f355k;

    /* renamed from: l */
    public final mq<Boolean> f356l;

    /* compiled from: PG */
    public interface ProducerFactoryMethod {
        Et createProducerFactory(Context context, tq tqVar, Nt nt, Pt pt, boolean z, boolean z2, boolean z3, tt ttVar, zq zqVar, dt<qp, Vt> dtVar, dt<qp, PooledByteBuffer> dtVar2, Qs qs, Qs qs2, Rs rs, Es es, int i, int i2, boolean z4);
    }

    public /* synthetic */ ImagePipelineExperiments(zt ztVar, yt ytVar) {
        this.f345a = ztVar.a;
        this.f346b = ztVar.b;
        this.f347c = ztVar.c;
        this.f348d = ztVar.d;
        this.f349e = ztVar.e;
        this.f350f = ztVar.f;
        this.f351g = ztVar.g;
        this.f352h = ztVar.h;
        this.f353i = ztVar.i;
        this.f354j = ztVar.j;
        if (zt.a(ztVar) == null) {
            this.f355k = new At();
        } else {
            this.f355k = zt.a(ztVar);
        }
        this.f356l = ztVar.l;
    }
}
