package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;

@Ay(name = "RCTRawText")
/* compiled from: PG */
public class ReactRawTextManager extends ViewManager<View, GD> {
    public static final String REACT_CLASS = "RCTRawText";

    public String getName() {
        return REACT_CLASS;
    }

    public Class<GD> getShadowNodeClass() {
        return GD.class;
    }

    public void updateExtraData(View view, Object obj) {
    }

    public GD createShadowNodeInstance() {
        return new GD();
    }

    public ReactTextView createViewInstance(WA wa) {
        throw new IllegalStateException("Attempt to create a native view for RCTRawText");
    }
}
