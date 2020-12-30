package org.chromium.base.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class RecordHistogram {

    /* renamed from: a */
    public static Map<String, Long> f1517a = Collections.synchronizedMap(new HashMap());

    /* renamed from: a */
    public static int m1536a(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    /* renamed from: a */
    public static long m1537a(String str) {
        Long l = f1517a.get(str);
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    /* renamed from: b */
    public static void m1544b(String str, int i) {
        m1540a(str, i, 1, 100, 50);
    }

    /* renamed from: c */
    public static void m1547c(String str, int i) {
        m1540a(str, i, 1, 1000000, 50);
    }

    /* renamed from: d */
    public static void m1550d(String str, long j) {
        m1542a(str, j, 1, 10000, 50);
    }

    /* renamed from: e */
    public static void m1551e(String str, int i) {
        long a = m1537a(str);
        long nativeRecordEnumeratedHistogram = nativeRecordEnumeratedHistogram(str, a, i, 101);
        if (nativeRecordEnumeratedHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordEnumeratedHistogram));
        }
    }

    /* renamed from: f */
    public static void m1552f(String str, int i) {
        long a = m1537a(str);
        long nativeRecordSparseHistogram = nativeRecordSparseHistogram(str, a, i);
        if (nativeRecordSparseHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordSparseHistogram));
        }
    }

    public static native int nativeGetHistogramTotalCountForTesting(String str);

    public static native int nativeGetHistogramValueCountForTesting(String str, int i);

    public static native long nativeRecordBooleanHistogram(String str, long j, boolean z);

    public static native long nativeRecordCustomCountHistogram(String str, long j, int i, int i2, int i3, int i4);

    public static native long nativeRecordCustomTimesHistogramMilliseconds(String str, long j, int i, int i2, int i3, int i4);

    public static native long nativeRecordEnumeratedHistogram(String str, long j, int i, int i2);

    public static native long nativeRecordLinearCountHistogram(String str, long j, int i, int i2, int i3, int i4);

    public static native long nativeRecordSparseHistogram(String str, long j, int i);

    /* renamed from: b */
    public static void m1545b(String str, int i, int i2, int i3, int i4) {
        long a = m1537a(str);
        long nativeRecordLinearCountHistogram = nativeRecordLinearCountHistogram(str, a, i, i2, i3, i4);
        if (nativeRecordLinearCountHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordLinearCountHistogram));
        }
    }

    /* renamed from: c */
    public static void m1548c(String str, long j) {
        m1542a(str, j, 10, 180000, 50);
    }

    /* renamed from: d */
    public static void m1549d(String str, int i) {
        m1540a(str, i, 1000, 500000, 50);
    }

    /* renamed from: a */
    public static void m1543a(String str, boolean z) {
        long a = m1537a(str);
        long nativeRecordBooleanHistogram = nativeRecordBooleanHistogram(str, a, z);
        if (nativeRecordBooleanHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordBooleanHistogram));
        }
    }

    /* renamed from: b */
    public static void m1546b(String str, long j) {
        m1542a(str, j, 1, 3600000, 100);
    }

    /* renamed from: a */
    public static void m1539a(String str, int i, int i2) {
        long a = m1537a(str);
        long nativeRecordEnumeratedHistogram = nativeRecordEnumeratedHistogram(str, a, i, i2);
        if (nativeRecordEnumeratedHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordEnumeratedHistogram));
        }
    }

    /* renamed from: a */
    public static void m1538a(String str, int i) {
        m1540a(str, i, 1, 1000, 50);
    }

    /* renamed from: a */
    public static void m1540a(String str, int i, int i2, int i3, int i4) {
        long a = m1537a(str);
        long nativeRecordCustomCountHistogram = nativeRecordCustomCountHistogram(str, a, i, i2, i3, i4);
        if (nativeRecordCustomCountHistogram != a) {
            f1517a.put(str, Long.valueOf(nativeRecordCustomCountHistogram));
        }
    }

    /* renamed from: a */
    public static void m1541a(String str, long j) {
        m1542a(str, j, 1, 3600000, 50);
    }

    /* renamed from: a */
    public static void m1542a(String str, long j, long j2, long j3, int i) {
        long a = m1537a(str);
        long nativeRecordCustomTimesHistogramMilliseconds = nativeRecordCustomTimesHistogramMilliseconds(str, a, m1536a(j), m1536a(j2), m1536a(j3), i);
        if (nativeRecordCustomTimesHistogramMilliseconds != a) {
            String str2 = str;
            f1517a.put(str, Long.valueOf(nativeRecordCustomTimesHistogramMilliseconds));
        }
    }
}
