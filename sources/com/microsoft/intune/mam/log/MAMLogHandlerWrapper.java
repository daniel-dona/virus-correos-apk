package com.microsoft.intune.mam.log;

import java.util.logging.Handler;

/* compiled from: PG */
public interface MAMLogHandlerWrapper {
    void addHandler(Handler handler, boolean z);

    void removeHandler(Handler handler);

    void setLogcatPII(boolean z);
}
