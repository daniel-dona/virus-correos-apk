package org.chromium.mojo.system;

import fk3;

/* compiled from: PG */
public interface Watcher {

    /* compiled from: PG */
    public interface Callback {
        void onResult(int i);
    }

    /* renamed from: a */
    int mo9869a(lk3 lk3, fk3.a aVar, Callback callback);

    void cancel();

    void destroy();
}
