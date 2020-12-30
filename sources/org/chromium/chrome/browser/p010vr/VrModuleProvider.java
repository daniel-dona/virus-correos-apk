package org.chromium.chrome.browser.p010vr;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.BundleUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.modules.ModuleInstallUi;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: org.chromium.chrome.browser.vr.VrModuleProvider */
/* compiled from: PG */
public class VrModuleProvider implements ModuleInstallUi.FailureUiListener {

    /* renamed from: c */
    public static eF2 f2349c;

    /* renamed from: d */
    public static final List<jF2> f2350d = new ArrayList();

    /* renamed from: a */
    public long f2351a;

    /* renamed from: b */
    public Tab f2352b;

    public VrModuleProvider(long j) {
        this.f2351a = j;
    }

    /* renamed from: a */
    public static cF2 m3290a() {
        return m3292b().a;
    }

    /* renamed from: b */
    public static eF2 m3292b() {
        if (f2349c == null) {
            if (!kF2.a()) {
                f2349c = new fF2();
            } else {
                f2349c = (eF2) kF2.a.a();
            }
        }
        return f2349c;
    }

    /* renamed from: c */
    public static gF2 m3293c() {
        return m3292b().b;
    }

    @CalledByNative
    public static VrModuleProvider create(long j) {
        return new VrModuleProvider(j);
    }

    /* renamed from: d */
    public static void m3294d() {
        if (BundleUtils.isBundle() && !kF2.a() && m3290a().d()) {
            kF2.a.b();
            throw null;
        }
    }

    @CalledByNative
    private void installModule(Tab tab) {
        this.f2352b = tab;
        ModuleInstallUi moduleInstallUi = new ModuleInstallUi(this.f2352b, wx0.vr_module_title, this);
        moduleInstallUi.mo8937a();
        m3291a(new mF2(this, moduleInstallUi));
        throw null;
    }

    @CalledByNative
    public static boolean isModuleInstalled() {
        return kF2.a();
    }

    public static native void nativeInit();

    private native void nativeOnInstalledModule(long j, boolean z);

    public static native void nativeRegisterJni();

    @CalledByNative
    private void onNativeDestroy() {
        this.f2351a = 0;
    }

    public void onCancel() {
        long j = this.f2351a;
        if (j != 0) {
            nativeOnInstalledModule(j, false);
        }
    }

    public void onRetry() {
        if (this.f2351a != 0) {
            installModule(this.f2352b);
        }
    }

    /* renamed from: a */
    public static void m3291a(lL2 ll2) {
        kF2.a.a(new lF2(ll2));
        throw null;
    }
}
