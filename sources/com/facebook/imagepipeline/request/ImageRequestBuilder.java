package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;

/* compiled from: PG */
public class ImageRequestBuilder {

    /* renamed from: a */
    public Uri f399a = null;

    /* renamed from: b */
    public ImageRequest.RequestLevel f400b = ImageRequest.RequestLevel.FULL_FETCH;

    /* renamed from: c */
    public nt f401c = null;

    /* renamed from: d */
    public ot f402d = null;

    /* renamed from: e */
    public lt f403e = lt.h;

    /* renamed from: f */
    public ImageRequest.CacheChoice f404f = ImageRequest.CacheChoice.DEFAULT;

    /* renamed from: g */
    public boolean f405g = wt.x.a;

    /* renamed from: h */
    public boolean f406h = false;

    /* renamed from: i */
    public Priority f407i = Priority.HIGH;

    /* renamed from: j */
    public Aw f408j = null;

    /* renamed from: k */
    public boolean f409k = true;

    /* renamed from: l */
    public boolean f410l = true;

    /* renamed from: m */
    public du f411m;

    /* renamed from: n */
    public kt f412n = null;

    /* compiled from: PG */
    public static class BuilderException extends RuntimeException {
        public BuilderException(String str) {
            super(Eo.a("Invalid request builder: ", str));
        }
    }

    /* renamed from: a */
    public static ImageRequestBuilder m403a(Uri uri) {
        ImageRequestBuilder imageRequestBuilder = new ImageRequestBuilder();
        if (uri != null) {
            imageRequestBuilder.f399a = uri;
            return imageRequestBuilder;
        }
        throw new NullPointerException();
    }

    @Deprecated
    /* renamed from: a */
    public ImageRequestBuilder mo556a(boolean z) {
        if (z) {
            this.f402d = ot.c;
            return this;
        }
        this.f402d = ot.d;
        return this;
    }

    /* renamed from: a */
    public static ImageRequestBuilder m404a(ImageRequest imageRequest) {
        ImageRequestBuilder a = m403a(imageRequest.f384b);
        a.f403e = imageRequest.f389g;
        a.f412n = imageRequest.f392j;
        a.f404f = imageRequest.f383a;
        a.f406h = imageRequest.f388f;
        a.f400b = imageRequest.f394l;
        a.f408j = imageRequest.f397o;
        a.f405g = imageRequest.f387e;
        a.f407i = imageRequest.f393k;
        a.f401c = imageRequest.f390h;
        a.f411m = imageRequest.f398p;
        a.f402d = imageRequest.f391i;
        return a;
    }

    /* renamed from: a */
    public ImageRequestBuilder mo555a(kt ktVar) {
        this.f412n = ktVar;
        return this;
    }

    /* renamed from: a */
    public ImageRequest mo554a() {
        Uri uri = this.f399a;
        if (uri != null) {
            if (Oq.f(uri)) {
                if (!this.f399a.isAbsolute()) {
                    throw new BuilderException("Resource URI path must be absolute.");
                } else if (!this.f399a.getPath().isEmpty()) {
                    try {
                        Integer.parseInt(this.f399a.getPath().substring(1));
                    } catch (NumberFormatException unused) {
                        throw new BuilderException("Resource URI path must be a resource id.");
                    }
                } else {
                    throw new BuilderException("Resource URI must not be empty");
                }
            }
            if (!Oq.b(this.f399a) || this.f399a.isAbsolute()) {
                return new ImageRequest(this);
            }
            throw new BuilderException("Asset URI path must be absolute.");
        }
        throw new BuilderException("Source must be set!");
    }
}
