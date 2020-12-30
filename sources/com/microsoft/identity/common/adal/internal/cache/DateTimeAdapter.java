package com.microsoft.identity.common.adal.internal.cache;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: PG */
public final class DateTimeAdapter implements QJ<Date>, XJ<Date> {
    public static final String TAG = "DateTimeAdapter";
    public final DateFormat mEnUs24HourFormat = buildEnUs24HourDateFormat();
    public final DateFormat mEnUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    public final DateFormat mISO8601Format = buildIso8601Format();
    public final DateFormat mLocal24HourFormat = buildLocal24HourDateFormat();
    public final DateFormat mLocalFormat = DateFormat.getDateTimeInstance(2, 2);

    public static DateFormat buildEnUs24HourDateFormat() {
        return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.US);
    }

    public static DateFormat buildIso8601Format() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public static DateFormat buildLocal24HourDateFormat() {
        return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:15|16|17|18) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        return r2.mLocalFormat.parse(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r2.mLocal24HourFormat.parse(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        return r2.mEnUsFormat.parse(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002c, code lost:
        return r2.mEnUs24HourFormat.parse(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        android.util.Log.e("DateTimeAdapter", "Could not parse date: " + r4.getMessage(), r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        throw new com.google.gson.JsonParseException("Could not parse date: " + r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0015 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0025 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Date deserialize(RJ r3, java.lang.reflect.Type r4, PJ r5) throws com.google.gson.JsonParseException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r3 = r3.d()     // Catch:{ all -> 0x005f }
            java.text.DateFormat r4 = r2.mISO8601Format     // Catch:{ ParseException -> 0x000d }
            java.util.Date r3 = r4.parse(r3)     // Catch:{ ParseException -> 0x000d }
            monitor-exit(r2)
            return r3
        L_0x000d:
            java.text.DateFormat r4 = r2.mLocalFormat     // Catch:{ ParseException -> 0x0015 }
            java.util.Date r3 = r4.parse(r3)     // Catch:{ ParseException -> 0x0015 }
            monitor-exit(r2)
            return r3
        L_0x0015:
            java.text.DateFormat r4 = r2.mLocal24HourFormat     // Catch:{ ParseException -> 0x001d }
            java.util.Date r3 = r4.parse(r3)     // Catch:{ ParseException -> 0x001d }
            monitor-exit(r2)
            return r3
        L_0x001d:
            java.text.DateFormat r4 = r2.mEnUsFormat     // Catch:{ ParseException -> 0x0025 }
            java.util.Date r3 = r4.parse(r3)     // Catch:{ ParseException -> 0x0025 }
            monitor-exit(r2)
            return r3
        L_0x0025:
            java.text.DateFormat r4 = r2.mEnUs24HourFormat     // Catch:{ ParseException -> 0x002d }
            java.util.Date r3 = r4.parse(r3)     // Catch:{ ParseException -> 0x002d }
            monitor-exit(r2)
            return r3
        L_0x002d:
            r4 = move-exception
            java.lang.String r5 = "DateTimeAdapter"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r0.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r1 = "Could not parse date: "
            r0.append(r1)     // Catch:{ all -> 0x005f }
            java.lang.String r1 = r4.getMessage()     // Catch:{ all -> 0x005f }
            r0.append(r1)     // Catch:{ all -> 0x005f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005f }
            android.util.Log.e(r5, r0, r4)     // Catch:{ all -> 0x005f }
            com.google.gson.JsonParseException r4 = new com.google.gson.JsonParseException     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r5.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r0 = "Could not parse date: "
            r5.append(r0)     // Catch:{ all -> 0x005f }
            r5.append(r3)     // Catch:{ all -> 0x005f }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x005f }
            r4.<init>((java.lang.String) r3)     // Catch:{ all -> 0x005f }
            throw r4     // Catch:{ all -> 0x005f }
        L_0x005f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.adal.internal.cache.DateTimeAdapter.deserialize(RJ, java.lang.reflect.Type, PJ):java.util.Date");
    }

    public synchronized RJ serialize(Date date, Type type, WJ wj) {
        return new VJ(this.mISO8601Format.format(date));
    }
}
