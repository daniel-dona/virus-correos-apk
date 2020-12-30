package com.facebook.react.views.toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;

/* compiled from: PG */
public class ReactToolbarManager extends ViewGroupManager<ReactToolbar> {
    public static final int COMMAND_DISMISS_POPUP_MENUS = 1;
    public static final String REACT_CLASS = "ToolbarAndroid";

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbarManager$a */
    /* compiled from: PG */
    public class C0184a implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ qC f776a;

        /* renamed from: b */
        public final /* synthetic */ ReactToolbar f777b;

        public C0184a(ReactToolbarManager reactToolbarManager, qC qCVar, ReactToolbar reactToolbar) {
            this.f776a = qCVar;
            this.f777b = reactToolbar;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        public void onClick(View view) {
            this.f776a.b(new mE(this.f777b.getId(), -1));
        }
    }

    /* renamed from: com.facebook.react.views.toolbar.ReactToolbarManager$b */
    /* compiled from: PG */
    public class C0185b implements Toolbar.OnMenuItemClickListener {

        /* renamed from: a */
        public final /* synthetic */ qC f778a;

        /* renamed from: b */
        public final /* synthetic */ ReactToolbar f779b;

        public C0185b(ReactToolbarManager reactToolbarManager, qC qCVar, ReactToolbar reactToolbar) {
            this.f778a = qCVar;
            this.f779b = reactToolbar;
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
        public boolean onMenuItemClick(MenuItem menuItem) {
            this.f778a.b(new mE(this.f779b.getId(), menuItem.getOrder()));
            return true;
        }
    }

    public static int[] getDefaultColors(Context context) {
        TypedArray typedArray;
        TypedArray typedArray2;
        TypedArray typedArray3;
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray4 = null;
        try {
            typedArray3 = theme.obtainStyledAttributes(new int[]{getIdentifier(context, "toolbarStyle")});
            try {
                obtainStyledAttributes = theme.obtainStyledAttributes(typedArray3.getResourceId(0, 0), new int[]{getIdentifier(context, "titleTextAppearance"), getIdentifier(context, "subtitleTextAppearance")});
            } catch (Throwable th) {
                th = th;
                typedArray = null;
                typedArray2 = null;
                recycleQuietly(typedArray3);
                recycleQuietly(typedArray4);
                recycleQuietly(typedArray2);
                recycleQuietly(typedArray);
                throw th;
            }
            try {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
                typedArray2 = theme.obtainStyledAttributes(resourceId, new int[]{16842904});
                try {
                    typedArray4 = theme.obtainStyledAttributes(resourceId2, new int[]{16842904});
                    int[] iArr = {typedArray2.getColor(0, -16777216), typedArray4.getColor(0, -16777216)};
                    recycleQuietly(typedArray3);
                    recycleQuietly(obtainStyledAttributes);
                    recycleQuietly(typedArray2);
                    recycleQuietly(typedArray4);
                    return iArr;
                } catch (Throwable th2) {
                    th = th2;
                    TypedArray typedArray5 = typedArray4;
                    typedArray4 = obtainStyledAttributes;
                    typedArray = typedArray5;
                    recycleQuietly(typedArray3);
                    recycleQuietly(typedArray4);
                    recycleQuietly(typedArray2);
                    recycleQuietly(typedArray);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                typedArray2 = null;
                typedArray4 = obtainStyledAttributes;
                typedArray = null;
                recycleQuietly(typedArray3);
                recycleQuietly(typedArray4);
                recycleQuietly(typedArray2);
                recycleQuietly(typedArray);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            typedArray = null;
            typedArray3 = null;
            typedArray2 = null;
            recycleQuietly(typedArray3);
            recycleQuietly(typedArray4);
            recycleQuietly(typedArray2);
            recycleQuietly(typedArray);
            throw th;
        }
    }

    private int[] getDefaultContentInsets(Context context) {
        TypedArray typedArray;
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray2 = null;
        try {
            typedArray = theme.obtainStyledAttributes(new int[]{getIdentifier(context, "toolbarStyle")});
            try {
                typedArray2 = theme.obtainStyledAttributes(typedArray.getResourceId(0, 0), new int[]{getIdentifier(context, "contentInsetStart"), getIdentifier(context, "contentInsetEnd")});
                int[] iArr = {typedArray2.getDimensionPixelSize(0, 0), typedArray2.getDimensionPixelSize(1, 0)};
                recycleQuietly(typedArray);
                recycleQuietly(typedArray2);
                return iArr;
            } catch (Throwable th) {
                th = th;
                recycleQuietly(typedArray);
                recycleQuietly(typedArray2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            typedArray = null;
            recycleQuietly(typedArray);
            recycleQuietly(typedArray2);
            throw th;
        }
    }

    public static int getIdentifier(Context context, String str) {
        return gs1.b(context.getResources(), str, "attr", context.getPackageName());
    }

    public static void recycleQuietly(TypedArray typedArray) {
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    public Map<String, Integer> getCommandsMap() {
        return hy.a("dismissPopupMenus", 1);
    }

    public Map<String, Object> getExportedViewConstants() {
        return hy.a("ShowAsAction", hy.a("never", 0, "always", 2, "ifRoom", 1));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @eC(name = "nativeActions")
    public void setActions(ReactToolbar reactToolbar, ReadableArray readableArray) {
        reactToolbar.mo1666a(readableArray);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.widget.Toolbar, com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultFloat = Float.NaN, name = "contentInsetEnd")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setContentInsetEnd(com.facebook.react.views.toolbar.ReactToolbar r2, float r3) {
        /*
            r1 = this;
            boolean r0 = java.lang.Float.isNaN(r3)
            if (r0 == 0) goto L_0x0012
            android.content.Context r3 = r2.getContext()
            int[] r3 = r1.getDefaultContentInsets(r3)
            r0 = 1
            r3 = r3[r0]
            goto L_0x001a
        L_0x0012:
            float r3 = GA.b(r3)
            int r3 = java.lang.Math.round(r3)
        L_0x001a:
            int r0 = r2.l()
            r2.setContentInsetsRelative(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.toolbar.ReactToolbarManager.setContentInsetEnd(com.facebook.react.views.toolbar.ReactToolbar, float):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.widget.Toolbar, com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultFloat = Float.NaN, name = "contentInsetStart")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setContentInsetStart(com.facebook.react.views.toolbar.ReactToolbar r2, float r3) {
        /*
            r1 = this;
            boolean r0 = java.lang.Float.isNaN(r3)
            if (r0 == 0) goto L_0x0012
            android.content.Context r3 = r2.getContext()
            int[] r3 = r1.getDefaultContentInsets(r3)
            r0 = 0
            r3 = r3[r0]
            goto L_0x001a
        L_0x0012:
            float r3 = GA.b(r3)
            int r3 = java.lang.Math.round(r3)
        L_0x001a:
            int r0 = r2.k()
            r2.setContentInsetsRelative(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.toolbar.ReactToolbarManager.setContentInsetStart(com.facebook.react.views.toolbar.ReactToolbar, float):void");
    }

    @eC(name = "logo")
    public void setLogo(ReactToolbar reactToolbar, ReadableMap readableMap) {
        reactToolbar.mo1668b(readableMap);
    }

    @eC(name = "navIcon")
    public void setNavIcon(ReactToolbar reactToolbar, ReadableMap readableMap) {
        reactToolbar.mo1669c(readableMap);
    }

    @eC(name = "overflowIcon")
    public void setOverflowIcon(ReactToolbar reactToolbar, ReadableMap readableMap) {
        reactToolbar.mo1670d(readableMap);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.facebook.react.views.toolbar.ReactToolbar, android.view.View] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(name = "rtl")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRtl(com.facebook.react.views.toolbar.ReactToolbar r2, boolean r3) {
        /*
            r1 = this;
            H9 r0 = I9.a
            r0.e(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.toolbar.ReactToolbarManager.setRtl(com.facebook.react.views.toolbar.ReactToolbar, boolean):void");
    }

    @eC(name = "subtitle")
    public void setSubtitle(ReactToolbar reactToolbar, String str) {
        reactToolbar.setSubtitle(str);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.widget.Toolbar, com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "subtitleColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSubtitleColor(com.facebook.react.views.toolbar.ReactToolbar r2, java.lang.Integer r3) {
        /*
            r1 = this;
            android.content.Context r0 = r2.getContext()
            int[] r0 = getDefaultColors(r0)
            if (r3 == 0) goto L_0x0012
            int r3 = r3.intValue()
            r2.setSubtitleTextColor(r3)
            goto L_0x0018
        L_0x0012:
            r3 = 1
            r3 = r0[r3]
            r2.setSubtitleTextColor(r3)
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.toolbar.ReactToolbarManager.setSubtitleColor(com.facebook.react.views.toolbar.ReactToolbar, java.lang.Integer):void");
    }

    @eC(name = "title")
    public void setTitle(ReactToolbar reactToolbar, String str) {
        reactToolbar.setTitle(str);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.widget.Toolbar, com.facebook.react.views.toolbar.ReactToolbar, android.view.ViewGroup] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "titleColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTitleColor(com.facebook.react.views.toolbar.ReactToolbar r2, java.lang.Integer r3) {
        /*
            r1 = this;
            android.content.Context r0 = r2.getContext()
            int[] r0 = getDefaultColors(r0)
            if (r3 == 0) goto L_0x0012
            int r3 = r3.intValue()
            r2.setTitleTextColor(r3)
            goto L_0x0018
        L_0x0012:
            r3 = 0
            r3 = r0[r3]
            r2.setTitleTextColor(r3)
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.toolbar.ReactToolbarManager.setTitleColor(com.facebook.react.views.toolbar.ReactToolbar, java.lang.Integer):void");
    }

    public void addEventEmitters(WA wa, ReactToolbar reactToolbar) {
        qC eventDispatcher = ((UIManagerModule) wa.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        reactToolbar.setNavigationOnClickListener(new C0184a(this, eventDispatcher, reactToolbar));
        reactToolbar.setOnMenuItemClickListener(new C0185b(this, eventDispatcher, reactToolbar));
    }

    public ReactToolbar createViewInstance(WA wa) {
        return new ReactToolbar(wa);
    }

    public void receiveCommand(ReactToolbar reactToolbar, int i, ReadableArray readableArray) {
        if (i == 1) {
            reactToolbar.e();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(i), ReactToolbarManager.class.getSimpleName()}));
        }
    }
}
