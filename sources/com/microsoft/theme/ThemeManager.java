package com.microsoft.theme;

import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ObserverList;

/* compiled from: PG */
public class ThemeManager {

    /* renamed from: g */
    public static int f1299g = 200;

    /* renamed from: h */
    public static ThemeManager f1300h = new ThemeManager();

    /* renamed from: a */
    public Theme f1301a = Theme.Default;

    /* renamed from: b */
    public Zt0 f1302b;

    /* renamed from: c */
    public Zt0 f1303c;

    /* renamed from: d */
    public int f1304d = f1299g;

    /* renamed from: e */
    public HashSet<xu0> f1305e = new HashSet<>();

    /* renamed from: f */
    public final ObserverList<OnThemeChangedListener> f1306f = new ObserverList<>();

    /* renamed from: c */
    public static ThemeManager m1319c() {
        return f1300h;
    }

    /* renamed from: d */
    public static boolean m1320d() {
        return f1300h.mo4505b() == Theme.Dark;
    }

    /* renamed from: a */
    public void mo4504a(zu0 zu0) {
        mo4503a((xu0) new Au0(zu0));
    }

    /* renamed from: b */
    public Theme mo4505b() {
        Theme a;
        Zt0 zt0 = this.f1303c;
        if (zt0 != null && (a = zt0.a()) != Theme.Null) {
            return a;
        }
        Zt0 zt02 = this.f1302b;
        if (zt02 != null) {
            return zt02.a();
        }
        return this.f1301a;
    }

    /* renamed from: a */
    public void mo4503a(xu0 xu0) {
        if (this.f1305e.add(xu0)) {
            xu0.a(mo4505b());
        }
        if (this.f1305e.size() > this.f1304d) {
            Iterator<xu0> it = this.f1305e.iterator();
            while (it.hasNext()) {
                if (it.next().a()) {
                    it.remove();
                }
            }
            this.f1304d += 100;
        }
    }

    /* renamed from: a */
    public void mo4502a(View view, int i, int i2) {
        wu0 wu0 = new wu0(view, new ArrayList(Arrays.asList(new ru0[]{su0.a(i, i2)})));
        this.f1305e.add(wu0);
        if (mo4505b() != Theme.Default) {
            wu0.a(mo4505b());
        }
    }

    /* renamed from: a */
    public void mo4500a() {
        Theme a;
        Zt0 zt0 = this.f1302b;
        if (zt0 != null && (a = zt0.a()) != this.f1301a) {
            this.f1301a = a;
            Mm0[] mm0Arr = new xu0[this.f1305e.size()];
            this.f1305e.toArray(mm0Arr);
            for (Mm0 mm0 : mm0Arr) {
                if (mm0.a()) {
                    this.f1305e.remove(mm0);
                } else {
                    mm0.a(mo4505b());
                }
            }
            Iterator<OnThemeChangedListener> it = this.f1306f.iterator();
            while (it.hasNext()) {
                it.next().onThemeChanged();
            }
        }
    }

    /* renamed from: a */
    public void mo4501a(Zt0 zt0) {
        this.f1302b = zt0;
    }
}
