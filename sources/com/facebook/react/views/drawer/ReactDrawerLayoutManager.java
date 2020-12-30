package com.facebook.react.views.drawer;

import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;

@Ay(name = "AndroidDrawerLayout")
/* compiled from: PG */
public class ReactDrawerLayoutManager extends ViewGroupManager<RC> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    public static final String REACT_CLASS = "AndroidDrawerLayout";

    /* compiled from: PG */
    public static class DrawerEventEmitter implements DrawerLayout.DrawerListener {

        /* renamed from: a */
        public final DrawerLayout f566a;

        /* renamed from: b */
        public final qC f567b;

        public DrawerEventEmitter(DrawerLayout drawerLayout, qC qCVar) {
            this.f566a = drawerLayout;
            this.f567b = qCVar;
        }

        public void onDrawerClosed(View view) {
            this.f567b.b(new SC(this.f566a.getId()));
        }

        public void onDrawerOpened(View view) {
            this.f567b.b(new TC(this.f566a.getId()));
        }

        public void onDrawerSlide(View view, float f) {
            this.f567b.b(new UC(this.f566a.getId(), f));
        }

        public void onDrawerStateChanged(int i) {
            this.f567b.b(new VC(this.f566a.getId(), i));
        }
    }

    public Map<String, Integer> getCommandsMap() {
        return hy.a("openDrawer", 1, "closeDrawer", 2);
    }

    @eC(defaultFloat = Float.NaN, name = "drawerWidth")
    public void getDrawerWidth(RC rc, float f) {
        int i;
        if (Float.isNaN(f)) {
            i = -1;
        } else {
            i = Math.round(GA.b(f));
        }
        rc.S3 = i;
        rc.f();
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return hy.a("topDrawerSlide", hy.a("registrationName", "onDrawerSlide"), "topDrawerOpened", hy.a("registrationName", "onDrawerOpen"), "topDrawerClosed", hy.a("registrationName", "onDrawerClose"), "topDrawerStateChanged", hy.a("registrationName", "onDrawerStateChanged"));
    }

    public Map getExportedViewConstants() {
        return hy.a("DrawerPosition", hy.a("Left", 8388611, "Right", 8388613));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @eC(name = "drawerLockMode")
    public void setDrawerLockMode(RC rc, String str) {
        if (str == null || "unlocked".equals(str)) {
            rc.setDrawerLockMode(0);
        } else if ("locked-closed".equals(str)) {
            rc.setDrawerLockMode(1);
        } else if ("locked-open".equals(str)) {
            rc.setDrawerLockMode(2);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("Unknown drawerLockMode ", str));
        }
    }

    @eC(defaultInt = 8388611, name = "drawerPosition")
    public void setDrawerPosition(RC rc, int i) {
        if (8388611 == i || 8388613 == i) {
            rc.R3 = i;
            rc.f();
            return;
        }
        throw new JSApplicationIllegalArgumentException(Eo.b("Unknown drawerPosition ", i));
    }

    public void addEventEmitters(WA wa, RC rc) {
        rc.setDrawerListener(new DrawerEventEmitter(rc, ((UIManagerModule) wa.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    public void addView(RC rc, View view, int i) {
        if (getChildCount(rc) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (i == 0 || i == 1) {
            rc.addView(view, i);
            rc.f();
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("The only valid indices for drawer's child are 0 or 1. Got ", i, " instead."));
        }
    }

    public RC createViewInstance(WA wa) {
        return new RC(wa);
    }

    public void receiveCommand(RC rc, int i, ReadableArray readableArray) {
        if (i == 1) {
            rc.f(rc.R3);
        } else if (i == 2) {
            rc.a(rc.R3);
        }
    }

    public void setElevation(RC rc, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            Class<RC> cls = RC.class;
            try {
                cls.getMethod("setDrawerElevation", new Class[]{Float.TYPE}).invoke(rc, new Object[]{Float.valueOf(GA.b(f))});
            } catch (Exception e) {
                pq.b("ReactNative", "setDrawerElevation is not available in this version of the support lib.", e);
            }
        }
    }
}
