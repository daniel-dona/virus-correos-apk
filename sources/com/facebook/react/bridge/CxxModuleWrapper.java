package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;
import java.io.IOException;

@Qw
/* compiled from: PG */
public class CxxModuleWrapper extends CxxModuleWrapperBase {
    public CxxModuleWrapper(HybridData hybridData) {
        super(hybridData);
    }

    public static CxxModuleWrapper makeDso(String str, String str2) {
        SoLoader.m690a(str);
        SoLoader.m684a();
        try {
            return makeDsoNative(SoLoader.m693b(System.mapLibraryName(str)).getAbsolutePath(), str2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static native CxxModuleWrapper makeDsoNative(String str, String str2);
}
