package org.chromium.p012ui.resources.async;

import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;
import org.chromium.p012ui.resources.ResourceLoader;

/* renamed from: org.chromium.ui.resources.async.AsyncPreloadResourceLoader */
/* compiled from: PG */
public abstract class AsyncPreloadResourceLoader extends ResourceLoader {

    /* renamed from: c */
    public final SparseArray<eK3> f2676c = new SparseArray<>();

    /* renamed from: d */
    public final ResourceCreator f2677d;

    /* renamed from: org.chromium.ui.resources.async.AsyncPreloadResourceLoader$ResourceCreator */
    /* compiled from: PG */
    public interface ResourceCreator {
        aK3 create(int i);
    }

    public AsyncPreloadResourceLoader(int i, ResourceLoader.ResourceLoaderCallback resourceLoaderCallback, ResourceCreator resourceCreator) {
        super(i, resourceLoaderCallback);
        this.f2677d = resourceCreator;
    }

    /* renamed from: a */
    public void mo10066a(int i) {
        eK3 ek3 = this.f2676c.get(i);
        if (ek3 == null || ek3.a(false)) {
            mo10075a(mo10076c(i), i);
            return;
        }
        try {
            mo10075a((aK3) ek3.c(), i);
        } catch (InterruptedException unused) {
            ResourceLoader.ResourceLoaderCallback resourceLoaderCallback = this.f2671b;
            if (resourceLoaderCallback != null) {
                resourceLoaderCallback.onResourceLoaded(this.f2670a, i, (aK3) null);
            }
        } catch (ExecutionException unused2) {
            ResourceLoader.ResourceLoaderCallback resourceLoaderCallback2 = this.f2671b;
            if (resourceLoaderCallback2 != null) {
                resourceLoaderCallback2.onResourceLoaded(this.f2670a, i, (aK3) null);
            }
        }
    }

    /* renamed from: b */
    public void mo10067b(int i) {
        if (this.f2676c.get(i) == null) {
            eK3 ek3 = new eK3(this, i);
            ek3.a(AP0.g);
            this.f2676c.put(i, ek3);
        }
    }

    /* renamed from: c */
    public final aK3 mo10076c(int i) {
        try {
            TraceEvent.m1472c("AsyncPreloadResourceLoader.createResource", (String) null);
            return this.f2677d.create(i);
        } finally {
            TraceEvent.m1475z("AsyncPreloadResourceLoader.createResource");
        }
    }

    /* renamed from: a */
    public final void mo10075a(aK3 ak3, int i) {
        ResourceLoader.ResourceLoaderCallback resourceLoaderCallback = this.f2671b;
        if (resourceLoaderCallback != null) {
            resourceLoaderCallback.onResourceLoaded(this.f2670a, i, ak3);
        }
        this.f2676c.remove(i);
    }
}
