package org.chromium.p012ui.resources;

/* renamed from: org.chromium.ui.resources.ResourceLoader */
/* compiled from: PG */
public abstract class ResourceLoader {

    /* renamed from: a */
    public final int f2670a;

    /* renamed from: b */
    public final ResourceLoaderCallback f2671b;

    /* renamed from: org.chromium.ui.resources.ResourceLoader$ResourceLoaderCallback */
    /* compiled from: PG */
    public interface ResourceLoaderCallback {
        void onResourceLoaded(int i, int i2, aK3 ak3);

        void onResourceUnregistered(int i, int i2);
    }

    public ResourceLoader(int i, ResourceLoaderCallback resourceLoaderCallback) {
        this.f2670a = i;
        this.f2671b = resourceLoaderCallback;
    }

    /* renamed from: a */
    public abstract void mo10066a(int i);

    /* renamed from: b */
    public abstract void mo10067b(int i);
}
