package com.facebook.common.time;

import android.os.SystemClock;

@gq
/* compiled from: PG */
public class AwakeTimeSinceBootClock implements Kq {
    @gq
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @gq
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @gq
    public long now() {
        return SystemClock.uptimeMillis();
    }
}
