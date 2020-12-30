package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;

@Ay(name = "RCTVirtualText")
/* compiled from: PG */
public class ReactVirtualTextViewManager extends BaseViewManager<View, ND> {
    public static final String REACT_CLASS = "RCTVirtualText";

    public View createViewInstance(WA wa) {
        throw new IllegalStateException("Attempt to create a native view for RCTVirtualText");
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<ND> getShadowNodeClass() {
        return ND.class;
    }

    public void updateExtraData(View view, Object obj) {
    }

    public ND createShadowNodeInstance() {
        return new ND();
    }
}
