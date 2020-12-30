package com.microsoft.bing.answerprovidersdk.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* compiled from: PG */
public @interface ErrorCode {
    public static final int HTTP_ERROR = -4;
    public static final int IO_ERROR = -5;
    public static final int NO_NETWORK = -3;
    public static final int NO_PERMISSION = -1;
    public static final int PARSE_ERROR = -2;
    public static final int UNKNOWN = -6;
}
