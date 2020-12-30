package com.microsoft.intune.mam.log;

import android.content.Intent;
import com.microsoft.intune.mam.client.identity.MAMIdentity;
import java.io.File;

/* compiled from: PG */
public interface MAMLogPIIFactory {
    Qd0 getPIIADAL(String str);

    Qd0 getPIIFilePath(File file);

    Qd0 getPIIFilePath(String str);

    Qd0 getPIIIntent(Intent intent);

    Qd0 getPIIIntent(String str);

    Qd0 getPIIUPN(MAMIdentity mAMIdentity);

    Qd0 getPIIUPN(String str);
}
