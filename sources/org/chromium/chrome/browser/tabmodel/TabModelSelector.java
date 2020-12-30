package org.chromium.chrome.browser.tabmodel;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* compiled from: PG */
public interface TabModelSelector {

    /* compiled from: PG */
    public interface CloseAllTabsDelegate {
        boolean closeAllTabsRequest(boolean z);
    }

    /* renamed from: a */
    Tab mo9441a(int i);

    /* renamed from: a */
    Tab mo9442a(LoadUrlParams loadUrlParams, int i, Tab tab, boolean z);

    /* renamed from: a */
    TabModel mo9443a(boolean z);

    /* renamed from: a */
    void mo9444a();

    /* renamed from: a */
    void mo9445a(CloseAllTabsDelegate closeAllTabsDelegate);

    /* renamed from: b */
    int mo9446b();

    /* renamed from: b */
    void mo9447b(boolean z);

    /* renamed from: c */
    TabModel mo9448c();

    /* renamed from: d */
    void mo9449d();

    void destroy();

    /* renamed from: e */
    void mo9451e();

    /* renamed from: f */
    boolean mo9452f();
}
