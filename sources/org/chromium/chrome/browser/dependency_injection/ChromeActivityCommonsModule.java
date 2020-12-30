package org.chromium.chrome.browser.dependency_injection;

import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.content.browser.ScreenOrientationProviderImpl;

/* compiled from: PG */
public class ChromeActivityCommonsModule {

    /* renamed from: a */
    public final ChromeActivity<?> f1759a;

    /* renamed from: b */
    public final Q52 f1760b;

    public ChromeActivityCommonsModule(ChromeActivity<?> chromeActivity, Q52 q52) {
        this.f1759a = chromeActivity;
        this.f1760b = q52;
    }

    /* renamed from: a */
    public eQ2 mo8480a() {
        return ScreenOrientationProviderImpl.getInstance();
    }
}
