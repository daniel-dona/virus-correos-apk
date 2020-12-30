package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public class DeferredReleaser {

    /* renamed from: d */
    public static DeferredReleaser f255d;

    /* renamed from: a */
    public final Set<Releasable> f256a = new HashSet();

    /* renamed from: b */
    public final Handler f257b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public final Runnable f258c = new xr(this);

    /* compiled from: PG */
    public interface Releasable {
        void release();
    }

    /* renamed from: b */
    public static synchronized DeferredReleaser m270b() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            if (f255d == null) {
                f255d = new DeferredReleaser();
            }
            deferredReleaser = f255d;
        }
        return deferredReleaser;
    }

    /* renamed from: a */
    public void mo356a(Releasable releasable) {
        m269a();
        this.f256a.remove(releasable);
    }

    /* renamed from: a */
    public static void m269a() {
        kq.b(Looper.getMainLooper().getThread() == Thread.currentThread());
    }

    /* renamed from: b */
    public void mo357b(Releasable releasable) {
        m269a();
        if (this.f256a.add(releasable) && this.f256a.size() == 1) {
            this.f257b.post(this.f258c);
        }
    }
}
