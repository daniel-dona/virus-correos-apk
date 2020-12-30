package com.microsoft.aad.adal;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.logging.DiagnosticContext;
import com.microsoft.identity.common.internal.logging.ILoggerCallback;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.logging.RequestContext;
import java.util.UUID;

/* compiled from: PG */
public class Logger {
    public static Logger sINSTANCE = new Logger();
    public String mCorrelationId = null;
    public ILogger mExternalLogger = null;

    /* compiled from: PG */
    public interface ILogger {
        void Log(String str, String str2, String str3, LogLevel logLevel, ADALError aDALError);
    }

    /* compiled from: PG */
    public enum LogLevel {
        Error(0),
        Warn(1),
        Info(2),
        Verbose(3),
        Debug(4);
        
        public int mValue;

        /* access modifiers changed from: public */
        LogLevel(int i) {
            this.mValue = i;
        }
    }

    private void commonCoreWrapper(String str, String str2, String str3, LogLevel logLevel, ADALError aDALError, Throwable th) {
        String str4;
        String str5;
        String str6;
        String str7;
        int ordinal = logLevel.ordinal();
        String str8 = BuildConfig.FLAVOR;
        if (ordinal == 0) {
            if (!StringExtensions.isNullOrBlank(str2)) {
                String correlationId = getInstance().getCorrelationId();
                StringBuilder sb = new StringBuilder();
                if (aDALError == null) {
                    str4 = str8;
                } else {
                    str4 = aDALError.name() + ":";
                }
                sb.append(str4);
                sb.append(formatMessage(str2));
                com.microsoft.identity.common.internal.logging.Logger.error(str, correlationId, sb.toString(), (Throwable) null);
            }
            if (!StringExtensions.isNullOrBlank(str3)) {
                String correlationId2 = getInstance().getCorrelationId();
                StringBuilder sb2 = new StringBuilder();
                if (aDALError != null) {
                    str8 = aDALError.name() + ":";
                }
                sb2.append(str8);
                sb2.append(formatMessage(str3));
                com.microsoft.identity.common.internal.logging.Logger.errorPII(str, correlationId2, sb2.toString(), th);
            }
        } else if (ordinal == 1) {
            if (!StringExtensions.isNullOrBlank(str2)) {
                String correlationId3 = getInstance().getCorrelationId();
                StringBuilder sb3 = new StringBuilder();
                if (aDALError == null) {
                    str5 = str8;
                } else {
                    str5 = aDALError.name() + ":";
                }
                sb3.append(str5);
                sb3.append(formatMessage(str2));
                com.microsoft.identity.common.internal.logging.Logger.warn(str, correlationId3, sb3.toString());
            }
            if (!StringExtensions.isNullOrBlank(str3)) {
                String correlationId4 = getInstance().getCorrelationId();
                StringBuilder sb4 = new StringBuilder();
                if (aDALError != null) {
                    str8 = aDALError.name() + ":";
                }
                sb4.append(str8);
                sb4.append(formatMessage(str3));
                com.microsoft.identity.common.internal.logging.Logger.warnPII(str, correlationId4, sb4.toString());
            }
        } else if (ordinal == 2) {
            if (!StringExtensions.isNullOrBlank(str2)) {
                String correlationId5 = getInstance().getCorrelationId();
                StringBuilder sb5 = new StringBuilder();
                if (aDALError == null) {
                    str6 = str8;
                } else {
                    str6 = aDALError.name() + ":";
                }
                sb5.append(str6);
                sb5.append(formatMessage(str2));
                com.microsoft.identity.common.internal.logging.Logger.info(str, correlationId5, sb5.toString());
            }
            if (!StringExtensions.isNullOrBlank(str3)) {
                String correlationId6 = getInstance().getCorrelationId();
                StringBuilder sb6 = new StringBuilder();
                if (aDALError != null) {
                    str8 = aDALError.name() + ":";
                }
                sb6.append(str8);
                sb6.append(formatMessage(str3));
                com.microsoft.identity.common.internal.logging.Logger.infoPII(str, correlationId6, sb6.toString());
            }
        } else if (ordinal == 3) {
            if (!StringExtensions.isNullOrBlank(str2)) {
                String correlationId7 = getInstance().getCorrelationId();
                StringBuilder sb7 = new StringBuilder();
                if (aDALError == null) {
                    str7 = str8;
                } else {
                    str7 = aDALError.name() + ":";
                }
                sb7.append(str7);
                sb7.append(formatMessage(str2));
                com.microsoft.identity.common.internal.logging.Logger.verbose(str, correlationId7, sb7.toString());
            }
            if (!StringExtensions.isNullOrBlank(str3)) {
                String correlationId8 = getInstance().getCorrelationId();
                StringBuilder sb8 = new StringBuilder();
                if (aDALError != null) {
                    str8 = aDALError.name() + ":";
                }
                sb8.append(str8);
                sb8.append(formatMessage(str3));
                com.microsoft.identity.common.internal.logging.Logger.verbosePII(str, correlationId8, sb8.toString());
            }
        } else if (ordinal == 4) {
            com.microsoft.identity.common.internal.logging.Logger.info(str, getInstance().mCorrelationId, formatMessage(str2));
        } else {
            throw new IllegalArgumentException("Unknown loglevel");
        }
    }

    @Deprecated
    /* renamed from: d */
    public static void m1243d(String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str2)) {
            getInstance().commonCoreWrapper(str, str2, (String) null, LogLevel.Debug, (ADALError) null, (Throwable) null);
        }
    }

    @Deprecated
    /* renamed from: e */
    public static void m1245e(String str, String str2, String str3, ADALError aDALError) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Error, aDALError, (Throwable) null);
    }

    private String formatMessage(String str) {
        StringBuilder c = Eo.c(str, " ver:");
        c.append(AuthenticationContext.getVersionName());
        return c.toString();
    }

    public static Logger getInstance() {
        return sINSTANCE;
    }

    @Deprecated
    /* renamed from: i */
    public static void m1248i(String str, String str2, String str3) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Info, (ADALError) null, (Throwable) null);
    }

    public static void setCorrelationId(UUID uuid) {
        getInstance().mCorrelationId = BuildConfig.FLAVOR;
        if (uuid != null) {
            String uuid2 = uuid.toString();
            getInstance().mCorrelationId = uuid2;
            RequestContext requestContext = new RequestContext();
            requestContext.put(DiagnosticContext.CORRELATION_ID, uuid2);
            DiagnosticContext.setRequestContext(requestContext);
        }
    }

    @Deprecated
    /* renamed from: v */
    public static void m1250v(String str, String str2) {
        getInstance().commonCoreWrapper(str, str2, (String) null, LogLevel.Verbose, (ADALError) null, (Throwable) null);
    }

    @Deprecated
    /* renamed from: w */
    public static void m1253w(String str, String str2, String str3, ADALError aDALError) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Warn, aDALError, (Throwable) null);
    }

    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    public void setAndroidLogEnabled(boolean z) {
        com.microsoft.identity.common.internal.logging.Logger.setAllowLogcat(z);
    }

    public void setEnablePII(boolean z) {
        com.microsoft.identity.common.internal.logging.Logger.setAllowPii(z);
    }

    public synchronized void setExternalLogger(ILogger iLogger) {
        com.microsoft.identity.common.internal.logging.Logger.getInstance().setExternalLogger(new ILoggerCallback() {
            private ADALError mapMessageToAdalError(String str) {
                for (ADALError aDALError : ADALError.values()) {
                    if (str != null) {
                        if (str.contains(aDALError.name() + ":")) {
                            return aDALError;
                        }
                    }
                }
                return null;
            }

            public void log(String str, Logger.LogLevel logLevel, String str2, boolean z) {
                if (Logger.this.mExternalLogger == null) {
                    return;
                }
                if (com.microsoft.identity.common.internal.logging.Logger.getAllowPii() || !z) {
                    ADALError mapMessageToAdalError = mapMessageToAdalError(str2);
                    int ordinal = logLevel.ordinal();
                    if (ordinal == 0) {
                        Logger.this.mExternalLogger.Log(str, str2, (String) null, LogLevel.Error, mapMessageToAdalError);
                    } else if (ordinal == 1) {
                        Logger.this.mExternalLogger.Log(str, str2, (String) null, LogLevel.Warn, mapMessageToAdalError);
                    } else if (ordinal == 2) {
                        Logger.this.mExternalLogger.Log(str, str2, (String) null, LogLevel.Info, mapMessageToAdalError);
                    } else if (ordinal == 3) {
                        Logger.this.mExternalLogger.Log(str, str2, (String) null, LogLevel.Verbose, mapMessageToAdalError);
                    } else {
                        throw new IllegalArgumentException("Unknown logLevel");
                    }
                }
            }
        });
        this.mExternalLogger = iLogger;
    }

    public void setLogLevel(LogLevel logLevel) {
        int ordinal = logLevel.ordinal();
        if (ordinal == 0) {
            com.microsoft.identity.common.internal.logging.Logger.getInstance().setLogLevel(Logger.LogLevel.ERROR);
        } else if (ordinal == 1) {
            com.microsoft.identity.common.internal.logging.Logger.getInstance().setLogLevel(Logger.LogLevel.WARN);
        } else if (ordinal == 2) {
            com.microsoft.identity.common.internal.logging.Logger.getInstance().setLogLevel(Logger.LogLevel.INFO);
        } else if (ordinal == 3) {
            com.microsoft.identity.common.internal.logging.Logger.getInstance().setLogLevel(Logger.LogLevel.VERBOSE);
        } else if (ordinal == 4) {
            com.microsoft.identity.common.internal.logging.Logger.getInstance().setLogLevel(Logger.LogLevel.INFO);
        } else {
            throw new IllegalArgumentException("Unknown logLevel");
        }
    }

    @Deprecated
    /* renamed from: e */
    public static void m1246e(String str, String str2, String str3, ADALError aDALError, Throwable th) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Error, aDALError, th);
    }

    @Deprecated
    /* renamed from: i */
    public static void m1249i(String str, String str2, String str3, ADALError aDALError) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Info, aDALError, (Throwable) null);
    }

    @Deprecated
    /* renamed from: v */
    public static void m1251v(String str, String str2, String str3, ADALError aDALError) {
        getInstance().commonCoreWrapper(str, str2, str3, LogLevel.Verbose, aDALError, (Throwable) null);
    }

    @Deprecated
    /* renamed from: w */
    public static void m1252w(String str, String str2) {
        getInstance().commonCoreWrapper(str, str2, (String) null, LogLevel.Warn, (ADALError) null, (Throwable) null);
    }

    @Deprecated
    /* renamed from: e */
    public static void m1247e(String str, String str2, Throwable th) {
        getInstance().commonCoreWrapper(str, str2, (String) null, LogLevel.Error, (ADALError) null, th);
    }

    /* renamed from: e */
    public static void m1244e(String str, String str2) {
        getInstance().commonCoreWrapper(str, str2, (String) null, LogLevel.Error, (ADALError) null, (Throwable) null);
    }
}
