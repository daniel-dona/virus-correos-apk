package com.facebook.react.uimanager;

import android.view.View;

/* compiled from: PG */
public abstract class SimpleViewManager<T extends View> extends BaseViewManager<T, xA> {
    public Class<xA> getShadowNodeClass() {
        return xA.class;
    }

    public void updateExtraData(T t, Object obj) {
    }

    public xA createShadowNodeInstance() {
        return new xA();
    }
}
