package org.chromium.content_public.browser;

import org.chromium.base.library_loader.ProcessInitException;

/* compiled from: PG */
public interface BrowserStartupController {

    /* compiled from: PG */
    public interface StartupCallback {
        void onFailure();

        void onSuccess();
    }

    /* renamed from: a */
    int mo9657a(boolean z);

    /* renamed from: a */
    void mo9659a(StartupCallback startupCallback);

    /* renamed from: a */
    void mo9661a(boolean z, boolean z2, StartupCallback startupCallback) throws ProcessInitException;

    /* renamed from: a */
    boolean mo9662a();

    /* renamed from: b */
    void mo9664b(boolean z) throws ProcessInitException;

    /* renamed from: b */
    boolean mo9665b();

    /* renamed from: c */
    boolean mo9668c();
}
