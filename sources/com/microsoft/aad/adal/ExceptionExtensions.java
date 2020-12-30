package com.microsoft.aad.adal;

import java.io.PrintWriter;
import java.io.StringWriter;

/* compiled from: PG */
public final class ExceptionExtensions {
    public static String getExceptionMessage(Exception exc) {
        if (exc == null) {
            return null;
        }
        String message = exc.getMessage();
        if (message != null) {
            return message;
        }
        StringWriter stringWriter = new StringWriter();
        qI.a.a(exc, new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
