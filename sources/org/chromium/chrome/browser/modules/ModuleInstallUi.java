package org.chromium.chrome.browser.modules;

import org.chromium.chrome.browser.tab.Tab;

/* compiled from: PG */
public class ModuleInstallUi {

    /* renamed from: a */
    public final Tab f2110a;

    /* renamed from: b */
    public final int f2111b;

    /* renamed from: c */
    public CK3 f2112c;

    /* compiled from: PG */
    public interface FailureUiListener {
        void onCancel();

        void onRetry();
    }

    public ModuleInstallUi(Tab tab, int i, FailureUiListener failureUiListener) {
        this.f2110a = tab;
        this.f2111b = i;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [org.chromium.chrome.browser.ChromeActivity, android.content.Context] */
    /* renamed from: a */
    public void mo8937a() {
        ? j = this.f2110a.mo9379j();
        if (j != 0) {
            this.f2112c = CK3.a(j, j.getString(wx0.module_install_start_text, new Object[]{j.getString(this.f2111b)}), 0);
            this.f2112c.a();
        }
    }
}
