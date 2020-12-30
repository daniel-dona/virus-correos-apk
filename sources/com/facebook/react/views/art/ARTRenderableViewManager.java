package com.facebook.react.views.art;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;

/* compiled from: PG */
public class ARTRenderableViewManager extends ViewManager<View, NA> {
    public static final String CLASS_GROUP = "ARTGroup";
    public static final String CLASS_SHAPE = "ARTShape";
    public static final String CLASS_TEXT = "ARTText";
    public final String mClassName;

    public ARTRenderableViewManager(String str) {
        this.mClassName = str;
    }

    public static ARTRenderableViewManager createARTGroupViewManager() {
        return new ARTGroupViewManager();
    }

    public static ARTRenderableViewManager createARTShapeViewManager() {
        return new ARTShapeViewManager();
    }

    public static ARTRenderableViewManager createARTTextViewManager() {
        return new ARTTextViewManager();
    }

    public NA createShadowNodeInstance() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return new JC();
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return new KC();
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return new MC();
        }
        StringBuilder a = Eo.a("Unexpected type ");
        a.append(this.mClassName);
        throw new IllegalStateException(a.toString());
    }

    public View createViewInstance(WA wa) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }

    public String getName() {
        return this.mClassName;
    }

    public Class<? extends NA> getShadowNodeClass() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return JC.class;
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return KC.class;
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return MC.class;
        }
        StringBuilder a = Eo.a("Unexpected type ");
        a.append(this.mClassName);
        throw new IllegalStateException(a.toString());
    }

    public void updateExtraData(View view, Object obj) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
}
