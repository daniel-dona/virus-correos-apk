package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.List;

@Qw
/* compiled from: PG */
public class ReactMarker {
    public static final List<MarkerListener> sListeners = new ArrayList();

    /* compiled from: PG */
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i);
    }

    @Qw
    public static void addListener(MarkerListener markerListener) {
        synchronized (sListeners) {
            if (!sListeners.contains(markerListener)) {
                sListeners.add(markerListener);
            }
        }
    }

    @Qw
    public static void clearMarkerListeners() {
        synchronized (sListeners) {
            sListeners.clear();
        }
    }

    @Qw
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @Qw
    public static void removeListener(MarkerListener markerListener) {
        synchronized (sListeners) {
            sListeners.remove(markerListener);
        }
    }

    @Qw
    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    @Qw
    public static void logMarker(String str, String str2) {
        logMarker(str, str2, 0);
    }

    @Qw
    public static void logMarker(String str, String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    @Qw
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @Qw
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    @Qw
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @Qw
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i) {
        synchronized (sListeners) {
            for (MarkerListener logMarker : sListeners) {
                logMarker.logMarker(reactMarkerConstants, str, i);
            }
        }
    }
}
