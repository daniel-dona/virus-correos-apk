package com.airbnb.lottie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public class PerformanceTracker {

    /* renamed from: a */
    public boolean f47a = false;

    /* renamed from: b */
    public final Set<FrameListener> f48b = new J8(0, false);

    /* renamed from: c */
    public final Map<String, lk> f49c = new HashMap();

    /* compiled from: PG */
    public interface FrameListener {
        void onFrameRendered(float f);
    }
}
