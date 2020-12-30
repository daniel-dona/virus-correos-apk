package org.chromium.components.crash;

import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class CrashKeys {

    /* renamed from: c */
    public static final String[] f2356c = {"loaded_dynamic_module", "active_dynamic_module", "application_status", "installed_modules", "emulated_modules", "dynamic_module_dex_name"};

    /* renamed from: a */
    public final AtomicReferenceArray<String> f2357a = new AtomicReferenceArray<>(f2356c.length);

    /* renamed from: b */
    public boolean f2358b;

    /* renamed from: org.chromium.components.crash.CrashKeys$b */
    /* compiled from: PG */
    public static class C0431b {

        /* renamed from: a */
        public static final CrashKeys f2359a = new CrashKeys((C0430a) null);
    }

    public /* synthetic */ CrashKeys(C0430a aVar) {
    }

    @CalledByNative
    public static CrashKeys getInstance() {
        return C0431b.f2359a;
    }

    private native void nativeSet(int i, String str);

    /* renamed from: a */
    public AtomicReferenceArray<String> mo9596a() {
        return this.f2357a;
    }

    @CalledByNative
    public void flushToNative() {
        ThreadUtils.m1462c();
        for (int i = 0; i < this.f2357a.length(); i++) {
            nativeSet(i, this.f2357a.getAndSet(i, (Object) null));
        }
        this.f2358b = true;
    }

    @CalledByNative
    public void set(int i, String str) {
        ThreadUtils.m1462c();
        if (this.f2358b) {
            nativeSet(i, str);
        } else {
            this.f2357a.set(i, str);
        }
    }
}
