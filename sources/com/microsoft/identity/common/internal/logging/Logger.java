package com.microsoft.identity.common.internal.logging;

import android.os.Build;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: PG */
public final class Logger {
    public static final String CUSTOM_LOG_ERROR = "Custom log failed to log message:%s";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Logger INSTANCE = new Logger();
    public static boolean sAllowLogcat = false;
    public static boolean sAllowPii = false;
    public ILoggerCallback mExternalLogger;
    public final Object mLock = new Object();
    public LogLevel mLogLevel = LogLevel.VERBOSE;

    /* compiled from: PG */
    public enum LogLevel {
        ERROR,
        WARN,
        INFO,
        VERBOSE
    }

    public static void error(String str, String str2, Throwable th) {
        getInstance().log(str, LogLevel.ERROR, DiagnosticContext.getRequestContext().toJsonString(), str2, th, false);
    }

    public static void errorPII(String str, String str2, Throwable th) {
        getInstance().log(str, LogLevel.ERROR, DiagnosticContext.getRequestContext().toJsonString(), str2, th, true);
    }

    private String formatMessage(String str, String str2, Throwable th) {
        String str3;
        if (StringExtensions.isNullOrBlank(str2)) {
            str2 = "N/A";
        }
        StringBuilder a = Eo.a(" [");
        a.append(getUTCDateTimeAsString());
        String str4 = "] ";
        if (!StringExtensions.isNullOrBlank(str)) {
            str4 = Eo.b(" - ", str, str4);
        }
        Eo.b(a, str4, str2, " Android ");
        a.append(Build.VERSION.SDK_INT);
        if (th == null) {
            str3 = BuildConfig.FLAVOR;
        } else {
            str3 = 10 + Log.getStackTraceString(th);
        }
        a.append(str3);
        return a.toString();
    }

    public static boolean getAllowLogcat() {
        return sAllowLogcat;
    }

    public static boolean getAllowPii() {
        return sAllowPii;
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public static String getUTCDateTimeAsString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }

    public static void info(String str, String str2) {
        getInstance().log(str, LogLevel.INFO, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, false);
    }

    public static void infoPII(String str, String str2) {
        getInstance().log(str, LogLevel.INFO, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002d, code lost:
        if (sAllowPii != false) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void log(java.lang.String r2, com.microsoft.identity.common.internal.logging.Logger.LogLevel r3, java.lang.String r4, java.lang.String r5, java.lang.Throwable r6, boolean r7) {
        /*
            r1 = this;
            com.microsoft.identity.common.internal.logging.Logger$LogLevel r0 = r1.mLogLevel
            int r0 = r3.compareTo(r0)
            if (r0 <= 0) goto L_0x0009
            return
        L_0x0009:
            boolean r0 = sAllowPii
            if (r0 != 0) goto L_0x0010
            if (r7 == 0) goto L_0x0010
            return
        L_0x0010:
            java.lang.String r4 = r1.formatMessage(r4, r5, r6)
            boolean r5 = sAllowLogcat
            if (r5 == 0) goto L_0x001b
            r1.sendLogcatLogs(r2, r3, r4)
        L_0x001b:
            java.lang.Object r5 = r1.mLock
            monitor-enter(r5)
            com.microsoft.identity.common.internal.logging.ILoggerCallback r6 = r1.mExternalLogger     // Catch:{ all -> 0x0040 }
            if (r6 == 0) goto L_0x003e
            com.microsoft.identity.common.internal.logging.ILoggerCallback r6 = r1.mExternalLogger     // Catch:{ Exception -> 0x0028 }
            r6.log(r2, r3, r4, r7)     // Catch:{ Exception -> 0x0028 }
            goto L_0x003e
        L_0x0028:
            if (r7 == 0) goto L_0x002f
            boolean r3 = sAllowPii     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x003e
        L_0x002f:
            java.lang.String r3 = "Custom log failed to log message:%s"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x0040 }
            r7 = 0
            r6[r7] = r4     // Catch:{ all -> 0x0040 }
            java.lang.String r3 = java.lang.String.format(r3, r6)     // Catch:{ all -> 0x0040 }
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r5)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r2 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0040 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.logging.Logger.log(java.lang.String, com.microsoft.identity.common.internal.logging.Logger$LogLevel, java.lang.String, java.lang.String, java.lang.Throwable, boolean):void");
    }

    private void sendLogcatLogs(String str, LogLevel logLevel, String str2) {
        int ordinal = logLevel.ordinal();
        if (ordinal == 0) {
            Log.e(str, str2);
        } else if (ordinal == 1) {
            Log.w(str, str2);
        } else if (ordinal == 2) {
            Log.i(str, str2);
        } else if (ordinal != 3) {
            throw new IllegalArgumentException("Unknown log level");
        }
    }

    public static void setAllowLogcat(boolean z) {
        sAllowLogcat = z;
    }

    public static void setAllowPii(boolean z) {
        sAllowPii = z;
    }

    public static void verbose(String str, String str2) {
        getInstance().log(str, LogLevel.VERBOSE, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, false);
    }

    public static void verbosePII(String str, String str2) {
        getInstance().log(str, LogLevel.VERBOSE, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, true);
    }

    public static void warn(String str, String str2) {
        getInstance().log(str, LogLevel.WARN, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, false);
    }

    public static void warnPII(String str, String str2) {
        getInstance().log(str, LogLevel.WARN, DiagnosticContext.getRequestContext().toJsonString(), str2, (Throwable) null, true);
    }

    public void setExternalLogger(ILoggerCallback iLoggerCallback) {
        synchronized (this.mLock) {
            this.mExternalLogger = iLoggerCallback;
        }
    }

    public void setLogLevel(LogLevel logLevel) {
        this.mLogLevel = logLevel;
    }

    public static void error(String str, String str2, String str3, Throwable th) {
        getInstance().log(str, LogLevel.ERROR, str2, str3, th, false);
    }

    public static void errorPII(String str, String str2, String str3, Throwable th) {
        getInstance().log(str, LogLevel.ERROR, str2, str3, th, true);
    }

    public static void info(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.INFO, str2, str3, (Throwable) null, false);
    }

    public static void infoPII(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.INFO, str2, str3, (Throwable) null, true);
    }

    public static void verbose(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.VERBOSE, str2, str3, (Throwable) null, false);
    }

    public static void verbosePII(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.VERBOSE, str2, str3, (Throwable) null, true);
    }

    public static void warn(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.WARN, str2, str3, (Throwable) null, false);
    }

    public static void warnPII(String str, String str2, String str3) {
        getInstance().log(str, LogLevel.WARN, str2, str3, (Throwable) null, true);
    }
}
