package com.facebook.react.bridge;

import java.util.Map;

/* compiled from: PG */
public interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}
