package com.facebook.common.util;

/* compiled from: PG */
public class ExceptionWithNoStacktrace extends Exception {
    public ExceptionWithNoStacktrace(String str) {
        super(str);
    }

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
