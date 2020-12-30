package com.microsoft.identity.common.adal.internal.util;

import java.util.Date;

/* compiled from: PG */
public final class DateExtensions {
    public static Date createCopy(Date date) {
        return date != null ? new Date(date.getTime()) : date;
    }
}
