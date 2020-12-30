package com.facebook.common.time;

import android.os.SystemClock;

@gq
/* compiled from: PG */
public class RealtimeSinceBootClock implements Kq {

    /* renamed from: a */
    public static final RealtimeSinceBootClock f247a = new RealtimeSinceBootClock();

    @gq
    public static RealtimeSinceBootClock get() {
        return f247a;
    }

    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
