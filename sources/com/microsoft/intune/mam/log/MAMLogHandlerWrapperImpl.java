package com.microsoft.intune.mam.log;

import com.citrix.loggersdk.BuildConfig;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* compiled from: PG */
public class MAMLogHandlerWrapperImpl extends Handler implements MAMLogHandlerWrapper {
    public static final String ADAL_LOGGING_PACKAGE_NAME = "com.microsoft.intune.mam.aad.adal";
    public static final String MSMAM_PACKAGE_NAME = "MSMAM - com.microsoft.intune.mam";
    public static final int NO_FLAGS = 0;
    public static final int PRESERVE_PARAMETERS_WHEN_LOGGING_FLAG = 2;
    public static final int WANTS_PII_FLAG = 1;
    public Map<Handler, Integer> mHandlers;
    public final ReentrantReadWriteLock mHandlersLock;

    /* renamed from: com.microsoft.intune.mam.log.MAMLogHandlerWrapperImpl$a */
    /* compiled from: PG */
    public class C0345a implements Filter {
        public C0345a(MAMLogHandlerWrapperImpl mAMLogHandlerWrapperImpl) {
        }

        public boolean isLoggable(LogRecord logRecord) {
            return logRecord.getLoggerName().startsWith(MAMLogHandlerWrapperImpl.MSMAM_PACKAGE_NAME);
        }
    }

    public MAMLogHandlerWrapperImpl() {
        this.mHandlers = null;
        this.mHandlersLock = new ReentrantReadWriteLock();
        this.mHandlers = new HashMap();
        setFilter(new C0345a(this));
        Logger logger = Logger.getLogger(MSMAM_PACKAGE_NAME);
        for (Handler handler : logger.getHandlers()) {
            if (handler.getClass().getName().equals(MAMLogHandlerWrapperImpl.class.getName())) {
                logger.removeHandler(handler);
            }
        }
        logger.addHandler(this);
        logger.setUseParentHandlers(false);
    }

    private LogRecord buildNoPIIRecord(LogRecord logRecord) {
        return messageFormatRecord(logRecord);
    }

    private LogRecord buildNoPIIRecordPreserveParameters(LogRecord logRecord) {
        return copyPrimitiveLogRecordComponents(logRecord, logRecord.getMessage(), logRecord.getParameters());
    }

    private LogRecord buildPIIRecord(LogRecord logRecord, boolean z) {
        Object[] parameters = logRecord.getParameters();
        if (parameters == null) {
            return messageFormatRecord(logRecord, (Object[]) null);
        }
        Object[] objArr = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof Qd0) {
                objArr[i] = ((Qd0) parameters[i]).a();
            } else {
                objArr[i] = parameters[i];
            }
        }
        if (z) {
            return copyPrimitiveLogRecordComponents(logRecord, logRecord.getMessage(), objArr);
        }
        return messageFormatRecord(logRecord, objArr);
    }

    private LogRecord copyPrimitiveLogRecordComponents(LogRecord logRecord, String str) {
        LogRecord logRecord2 = new LogRecord(logRecord.getLevel(), str);
        logRecord2.setLoggerName(logRecord.getLoggerName());
        logRecord2.setMillis(logRecord.getMillis());
        logRecord2.setResourceBundle(logRecord.getResourceBundle());
        logRecord2.setResourceBundleName(logRecord.getResourceBundleName());
        logRecord2.setSequenceNumber(logRecord.getSequenceNumber());
        logRecord2.setSourceClassName(logRecord.getSourceClassName());
        logRecord2.setSourceMethodName(logRecord.getSourceMethodName());
        logRecord2.setThreadID(logRecord.getThreadID());
        logRecord2.setThrown(logRecord.getThrown());
        return logRecord2;
    }

    private LogRecord messageFormatRecord(LogRecord logRecord) {
        return messageFormatRecord(logRecord, logRecord.getParameters());
    }

    private boolean oddSingleQuoteCount(String str) {
        if (!str.contains("'")) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '\'') {
                i++;
            }
        }
        return i % 2 == 1;
    }

    public void addHandler(Handler handler, boolean z) {
        this.mHandlersLock.writeLock().lock();
        try {
            this.mHandlers.put(handler, Integer.valueOf(z ? 1 : 0));
        } finally {
            this.mHandlersLock.writeLock().unlock();
        }
    }

    public void close() {
        this.mHandlersLock.readLock().lock();
        try {
            for (Map.Entry<Handler, Integer> key : this.mHandlers.entrySet()) {
                ((Handler) key.getKey()).close();
            }
        } finally {
            this.mHandlersLock.readLock().unlock();
        }
    }

    public void flush() {
        this.mHandlersLock.readLock().lock();
        try {
            for (Map.Entry<Handler, Integer> key : this.mHandlers.entrySet()) {
                ((Handler) key.getKey()).flush();
            }
        } finally {
            this.mHandlersLock.readLock().unlock();
        }
    }

    public void publish(LogRecord logRecord) {
        int i;
        this.mHandlersLock.readLock().lock();
        try {
            LogRecord buildNoPIIRecord = buildNoPIIRecord(logRecord);
            Iterator<Map.Entry<Handler, Integer>> it = this.mHandlers.entrySet().iterator();
            LogRecord logRecord2 = null;
            LogRecord logRecord3 = null;
            LogRecord logRecord4 = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if ((((Integer) next.getValue()).intValue() & 2) == 2) {
                    if ((((Integer) next.getValue()).intValue() & 1) == 1) {
                        if (logRecord4 == null) {
                            logRecord4 = buildPIIRecord(logRecord, true);
                        }
                        ((Handler) next.getKey()).publish(logRecord4);
                    } else {
                        if (logRecord3 == null) {
                            logRecord3 = buildNoPIIRecordPreserveParameters(logRecord);
                        }
                        ((Handler) next.getKey()).publish(logRecord3);
                    }
                } else if ((((Integer) next.getValue()).intValue() & 1) == 1) {
                    if (logRecord2 == null) {
                        logRecord2 = buildPIIRecord(logRecord, false);
                    }
                    ((Handler) next.getKey()).publish(logRecord2);
                } else {
                    ((Handler) next.getKey()).publish(buildNoPIIRecord);
                }
            }
            for (Handler handler : Logger.getLogger(BuildConfig.FLAVOR).getHandlers()) {
                if (!handler.getClass().getName().equals("com.android.internal.logging.AndroidHandler")) {
                    if (!this.mHandlers.keySet().contains(handler)) {
                        handler.publish(buildNoPIIRecord);
                    }
                }
            }
        } finally {
            this.mHandlersLock.readLock().unlock();
        }
    }

    public void removeHandler(Handler handler) {
        this.mHandlersLock.writeLock().lock();
        try {
            this.mHandlers.remove(handler);
        } finally {
            this.mHandlersLock.writeLock().unlock();
        }
    }

    public void setLogcatPII(boolean z) {
        this.mHandlersLock.writeLock().lock();
        try {
            Iterator<Map.Entry<Handler, Integer>> it = this.mHandlers.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (next.getKey() instanceof Jd0) {
                    addHandler((Handler) next.getKey(), z ? 1 : 0);
                    break;
                }
            }
        } finally {
            this.mHandlersLock.writeLock().unlock();
        }
    }

    private LogRecord messageFormatRecord(LogRecord logRecord, Object[] objArr) {
        String message = logRecord.getMessage();
        if (!(objArr == null || message == null || !message.contains("{0"))) {
            try {
                if (oddSingleQuoteCount(message)) {
                    message = message.replaceAll("'", "''");
                }
                message = MessageFormat.format(message, objArr);
            } catch (Exception unused) {
                message = logRecord.getMessage();
            }
        }
        return copyPrimitiveLogRecordComponents(logRecord, message);
    }

    public void addHandler(Handler handler, int i) {
        this.mHandlersLock.writeLock().lock();
        try {
            this.mHandlers.put(handler, Integer.valueOf(i));
        } finally {
            this.mHandlersLock.writeLock().unlock();
        }
    }

    private LogRecord copyPrimitiveLogRecordComponents(LogRecord logRecord, String str, Object[] objArr) {
        LogRecord copyPrimitiveLogRecordComponents = copyPrimitiveLogRecordComponents(logRecord, str);
        copyPrimitiveLogRecordComponents.setParameters(objArr);
        return copyPrimitiveLogRecordComponents;
    }
}
