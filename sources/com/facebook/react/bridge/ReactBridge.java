package com.facebook.react.bridge;

import android.os.SystemClock;
import android.os.Trace;
import com.facebook.soloader.SoLoader;

/* compiled from: PG */
public class ReactBridge {
    public static boolean sDidInit;
    public static volatile long sLoadEndTime;
    public static volatile long sLoadStartTime;

    public static long getLoadEndTime() {
        return sLoadEndTime;
    }

    public static long getLoadStartTime() {
        return sLoadStartTime;
    }

    public static synchronized void staticInit() {
        synchronized (ReactBridge.class) {
            if (!sDidInit) {
                sDidInit = true;
                sLoadStartTime = SystemClock.uptimeMillis();
                Trace.beginSection("ReactBridge.staticInit::load:reactnativejni");
                ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START);
                SoLoader.m690a("reactnativejni");
                ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END);
                Trace.endSection();
                sLoadEndTime = SystemClock.uptimeMillis();
            }
        }
    }
}
