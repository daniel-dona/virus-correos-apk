package org.chromium.chrome.browser.night_mode;

import java.util.Iterator;
import org.chromium.base.ObserverList;

/* compiled from: PG */
public class SystemNightModeMonitor {

    /* renamed from: c */
    public static SystemNightModeMonitor f2123c;

    /* renamed from: a */
    public final ObserverList<Observer> f2124a = new ObserverList<>();

    /* renamed from: b */
    public boolean f2125b;

    public SystemNightModeMonitor() {
        mo8960a();
    }

    /* renamed from: c */
    public static SystemNightModeMonitor m2671c() {
        if (f2123c == null) {
            f2123c = new SystemNightModeMonitor();
        }
        return f2123c;
    }

    /* renamed from: a */
    public final void mo8960a() {
        this.f2125b = (RN0.a.getResources().getConfiguration().uiMode & 48) == 32;
    }

    /* renamed from: b */
    public void mo8962b() {
        boolean z = this.f2125b;
        mo8960a();
        if (z != this.f2125b) {
            Iterator<Observer> it = this.f2124a.iterator();
            while (it.hasNext()) {
                it.next().onSystemNightModeChanged();
            }
        }
    }

    /* renamed from: a */
    public void mo8961a(Observer observer) {
        this.f2124a.mo7869b(observer);
    }
}
