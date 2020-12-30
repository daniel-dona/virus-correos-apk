package org.chromium.chrome.browser.customtabs.dynamicmodule;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
public abstract class ModuleLoader {

    /* compiled from: PG */
    public interface DexClassLoaderProvider {
        ClassLoader createClassLoader(File file);
    }

    /* compiled from: PG */
    public interface DexInputStreamProvider {
        InputStream createInputStream(String str, Context context) throws IOException;
    }

    /* renamed from: a */
    public abstract void mo8477a(int i);
}
