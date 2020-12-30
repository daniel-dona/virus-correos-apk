package org.chromium.base;

import android.os.StrictMode;
import java.util.TimeZone;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class TimezoneUtils {
    @CalledByNative
    public static String getDefaultTimeZoneId() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        String id = TimeZone.getDefault().getID();
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return id;
    }
}
