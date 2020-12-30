package com.microsoft.intune.mam.log;

import java.io.File;

/* compiled from: PG */
public interface MAMLogManager {
    File[] getLogFiles();

    void init();
}
