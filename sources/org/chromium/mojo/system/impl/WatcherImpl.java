package org.chromium.mojo.system.impl;

import fk3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.mojo.system.Watcher;

/* compiled from: PG */
public class WatcherImpl implements Watcher {

    /* renamed from: a */
    public long f2531a = nativeCreateWatcher();

    /* renamed from: b */
    public Watcher.Callback f2532b;

    private native void nativeCancel(long j);

    private native long nativeCreateWatcher();

    private native void nativeDelete(long j);

    private native int nativeStart(long j, int i, int i2);

    @CalledByNative
    private void onHandleReady(int i) {
        this.f2532b.onResult(i);
    }

    /* renamed from: a */
    public int mo9869a(lk3 lk3, fk3.a aVar, Watcher.Callback callback) {
        long j = this.f2531a;
        if (j == 0 || !(lk3 instanceof uk3)) {
            return 3;
        }
        int nativeStart = nativeStart(j, ((uk3) lk3).a, aVar.a);
        if (nativeStart == 0) {
            this.f2532b = callback;
        }
        return nativeStart;
    }

    public void cancel() {
        long j = this.f2531a;
        if (j != 0) {
            this.f2532b = null;
            nativeCancel(j);
        }
    }

    public void destroy() {
        long j = this.f2531a;
        if (j != 0) {
            nativeDelete(j);
            this.f2531a = 0;
        }
    }
}
