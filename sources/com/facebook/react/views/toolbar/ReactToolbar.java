package com.facebook.react.views.toolbar;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: PG */
public class ReactToolbar extends Toolbar {

    /* renamed from: U3 */
    public final ns f758U3 = new ns(mo1662H());

    /* renamed from: V3 */
    public final ns f759V3 = new ns(mo1662H());

    /* renamed from: W3 */
    public final ns f760W3 = new ns(mo1662H());

    /* renamed from: X3 */
    public final os<es> f761X3 = new os<>();

    /* renamed from: Y3 */
    public C0182f f762Y3 = new C0177a(this.f758U3);

    /* renamed from: Z3 */
    public C0182f f763Z3 = new C0178b(this.f759V3);

    /* renamed from: a4 */
    public C0182f f764a4 = new C0179c(this.f760W3);

    /* renamed from: b4 */
    public final Runnable f765b4 = new C0180d();

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$a */
    /* compiled from: PG */
    public class C0177a extends C0182f {
        public C0177a(ns nsVar) {
            super(ReactToolbar.this, nsVar);
        }

        /* renamed from: a */
        public void mo1676a(Drawable drawable) {
            ReactToolbar.this.setLogo(drawable);
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$b */
    /* compiled from: PG */
    public class C0178b extends C0182f {
        public C0178b(ns nsVar) {
            super(ReactToolbar.this, nsVar);
        }

        /* renamed from: a */
        public void mo1676a(Drawable drawable) {
            ReactToolbar.this.setNavigationIcon(drawable);
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$c */
    /* compiled from: PG */
    public class C0179c extends C0182f {
        public C0179c(ns nsVar) {
            super(ReactToolbar.this, nsVar);
        }

        /* renamed from: a */
        public void mo1676a(Drawable drawable) {
            ReactToolbar.this.setOverflowIcon(drawable);
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$d */
    /* compiled from: PG */
    public class C0180d implements Runnable {
        public C0180d() {
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r2v2, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r3v2, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        public void run() {
            ? r0 = ReactToolbar.this;
            r0.measure(View.MeasureSpec.makeMeasureSpec(r0.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactToolbar.this.getHeight(), 1073741824));
            ? r02 = ReactToolbar.this;
            r02.layout(r02.getLeft(), ReactToolbar.this.getTop(), ReactToolbar.this.getRight(), ReactToolbar.this.getBottom());
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$e */
    /* compiled from: PG */
    public class C0181e extends C0182f {

        /* renamed from: d */
        public final MenuItem f770d;

        public C0181e(MenuItem menuItem, ns nsVar) {
            super(ReactToolbar.this, nsVar);
            this.f770d = menuItem;
        }

        /* renamed from: a */
        public void mo1676a(Drawable drawable) {
            this.f770d.setIcon(drawable);
            ReactToolbar.this.requestLayout();
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$f */
    /* compiled from: PG */
    public abstract class C0182f extends Er<Yt> {

        /* renamed from: b */
        public final ns f772b;

        /* renamed from: c */
        public C0183g f773c;

        public C0182f(ReactToolbar reactToolbar, ns nsVar) {
            this.f772b = nsVar;
        }

        /* renamed from: a */
        public abstract void mo1676a(Drawable drawable);

        /* renamed from: a */
        public void mo1678a(String str, Object obj, Animatable animatable) {
            Yt yt = (Yt) obj;
            Yt yt2 = this.f773c;
            if (yt2 == null) {
                yt2 = yt;
            }
            mo1676a(new lE(this.f772b.d(), yt2));
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbar$g */
    /* compiled from: PG */
    public static class C0183g implements Yt {

        /* renamed from: a */
        public int f774a;

        /* renamed from: b */
        public int f775b;

        public C0183g(int i, int i2) {
            this.f774a = i;
            this.f775b = i2;
        }

        public int getHeight() {
            return this.f775b;
        }

        public int getWidth() {
            return this.f774a;
        }
    }

    public ReactToolbar(Context context) {
        super(context);
    }

    /* renamed from: G */
    public final void mo1661G() {
        this.f758U3.f();
        this.f759V3.f();
        this.f760W3.f();
        os<es> osVar = this.f761X3;
        if (!osVar.a) {
            osVar.a = true;
            for (int i = 0; i < osVar.b.size(); i++) {
                ((ns) osVar.b.get(i)).f();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* renamed from: H */
    public final es mo1662H() {
        fs fsVar = new fs(getResources());
        fsVar.l = ScalingUtils.ScaleType.FIT_CENTER;
        fsVar.b = 0;
        return fsVar.a();
    }

    /* renamed from: I */
    public final void mo1663I() {
        this.f758U3.g();
        this.f759V3.g();
        this.f760W3.g();
        os<es> osVar = this.f761X3;
        if (osVar.a) {
            osVar.a = false;
            for (int i = 0; i < osVar.b.size(); i++) {
                ((ns) osVar.b.get(i)).g();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.support.v7.widget.Toolbar, com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* renamed from: a */
    public void mo1666a(ReadableArray readableArray) {
        Menu q = q();
        q.clear();
        os<es> osVar = this.f761X3;
        if (osVar.a) {
            for (int i = 0; i < osVar.b.size(); i++) {
                ((ns) osVar.b.get(i)).g();
            }
        }
        osVar.b.clear();
        if (readableArray != null) {
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                ReadableMap map = readableArray.getMap(i2);
                MenuItem add = q.add(0, 0, i2, map.getString("title"));
                if (map.hasKey("icon")) {
                    ReadableMap map2 = map.getMap("icon");
                    es H = mo1662H();
                    getContext();
                    ns nsVar = new ns(H);
                    C0181e eVar = new C0181e(add, nsVar);
                    eVar.f773c = mo1665a(map2);
                    mo1667a(map2, eVar, nsVar);
                    os<es> osVar2 = this.f761X3;
                    int size = osVar2.b.size();
                    kq.a(size, osVar2.b.size() + 1);
                    osVar2.b.add(size, nsVar);
                    if (osVar2.a) {
                        nsVar.f();
                    }
                }
                int i3 = map.hasKey("show") ? map.getInt("show") : 0;
                if (map.hasKey("showWithText") && map.getBoolean("showWithText")) {
                    i3 |= 4;
                }
                add.setShowAsAction(i3);
            }
        }
    }

    /* renamed from: b */
    public void mo1668b(ReadableMap readableMap) {
        mo1667a(readableMap, this.f762Y3, this.f758U3);
    }

    /* renamed from: c */
    public void mo1669c(ReadableMap readableMap) {
        mo1667a(readableMap, this.f763Z3, this.f759V3);
    }

    /* renamed from: d */
    public void mo1670d(ReadableMap readableMap) {
        mo1667a(readableMap, this.f764a4, this.f760W3);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    public void onAttachedToWindow() {
        ReactToolbar.super.onAttachedToWindow();
        mo1661G();
    }

    public void onDetachedFromWindow() {
        ReactToolbar.super.onDetachedFromWindow();
        mo1663I();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    public void onFinishTemporaryDetach() {
        ReactToolbar.super.onFinishTemporaryDetach();
        mo1661G();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    public void onStartTemporaryDetach() {
        ReactToolbar.super.onStartTemporaryDetach();
        mo1663I();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    public void requestLayout() {
        ReactToolbar.super.requestLayout();
        post(this.f765b4);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* renamed from: a */
    public final void mo1667a(ReadableMap readableMap, C0182f fVar, ns nsVar) {
        Drawable drawable = null;
        String string = readableMap != null ? readableMap.getString("uri") : null;
        if (string == null) {
            fVar.f773c = null;
            fVar.mo1676a((Drawable) null);
        } else if (string.startsWith("http://") || string.startsWith("https://") || string.startsWith("file://")) {
            fVar.f773c = mo1665a(readableMap);
            kr b = ir.b();
            b.a(Uri.parse(string));
            b.f272h = fVar;
            b.f276l = nsVar.e;
            nsVar.a(b.mo361a());
            nsVar.d().setVisible(true, true);
        } else {
            if (mo1664a(string) != 0) {
                drawable = getResources().getDrawable(mo1664a(string));
            }
            fVar.mo1676a(drawable);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* renamed from: a */
    public final int mo1664a(String str) {
        return gs1.b(getResources(), str, "drawable", getContext().getPackageName());
    }

    /* renamed from: a */
    public final C0183g mo1665a(ReadableMap readableMap) {
        if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
            return null;
        }
        return new C0183g(Math.round(GA.b((float) readableMap.getInt("width"))), Math.round(GA.b((float) readableMap.getInt("height"))));
    }
}
