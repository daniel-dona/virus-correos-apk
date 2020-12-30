package com.microsoft.identity.common.internal.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public final class DateUtilities {
    public static Date createCopy(Date date) {
        return date != null ? new Date(date.getTime()) : date;
    }

    public static long getExpiresOn(long j) {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + j;
    }
}
