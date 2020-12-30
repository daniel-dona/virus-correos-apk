package org.chromium.chrome.browser.tabmodel;

import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: PG */
public interface TabModel extends Mw2 {
    /* renamed from: a */
    void mo9418a();

    /* renamed from: a */
    void mo9419a(int i);

    /* renamed from: a */
    void mo9420a(int i, int i2);

    /* renamed from: a */
    void mo9421a(Tw2 tw2);

    /* renamed from: a */
    void mo9422a(List<Tab> list, boolean z);

    /* renamed from: a */
    void mo9423a(boolean z, boolean z2);

    /* renamed from: a */
    boolean mo9424a(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3);

    /* renamed from: a */
    boolean mo9425a(Tab tab, boolean z, boolean z2, boolean z3);

    /* renamed from: b */
    Tab mo9426b(int i);

    /* renamed from: b */
    void mo9427b(int i, int i2);

    /* renamed from: b */
    void mo9428b(Tw2 tw2);

    /* renamed from: b */
    void mo9429b(Tab tab, int i, int i2);

    /* renamed from: d */
    void mo9430d(int i);

    void destroy();

    /* renamed from: e */
    void mo9432e();

    /* renamed from: e */
    boolean mo9433e(Tab tab);

    /* renamed from: g */
    Profile mo9434g();

    /* renamed from: g */
    void mo9435g(Tab tab);

    /* renamed from: h */
    Mw2 mo9436h();

    boolean isCurrentModel();

    /* renamed from: m */
    boolean mo9438m();

    /* renamed from: o */
    boolean mo9439o();

    /* renamed from: p */
    void mo9440p();
}
