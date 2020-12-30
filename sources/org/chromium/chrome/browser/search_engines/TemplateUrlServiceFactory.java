package org.chromium.chrome.browser.search_engines;

import org.chromium.base.ThreadUtils;
import org.chromium.components.search_engines.TemplateUrlService;

/* compiled from: PG */
public class TemplateUrlServiceFactory {

    /* renamed from: a */
    public static TemplateUrlService f2200a;

    /* renamed from: a */
    public static TemplateUrlService m2927a() {
        ThreadUtils.m1462c();
        if (f2200a == null) {
            f2200a = nativeGetTemplateUrlService();
        }
        return f2200a;
    }

    public static native boolean nativeDoesDefaultSearchEngineHaveLogo();

    public static native TemplateUrlService nativeGetTemplateUrlService();
}
