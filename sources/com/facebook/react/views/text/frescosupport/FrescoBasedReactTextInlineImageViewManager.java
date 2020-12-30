package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.uimanager.ViewManager;

@Ay(name = "RCTTextInlineImage")
/* compiled from: PG */
public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, UD> {
    public static final String REACT_CLASS = "RCTTextInlineImage";
    public final Object mCallerContext;
    public final AbstractDraweeControllerBuilder mDraweeControllerBuilder;

    public FrescoBasedReactTextInlineImageViewManager() {
        this((AbstractDraweeControllerBuilder) null, (Object) null);
    }

    public View createViewInstance(WA wa) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<UD> getShadowNodeClass() {
        return UD.class;
    }

    public void updateExtraData(View view, Object obj) {
    }

    public FrescoBasedReactTextInlineImageViewManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    public UD createShadowNodeInstance() {
        kr krVar = this.mDraweeControllerBuilder;
        if (krVar == null) {
            krVar = ir.b();
        }
        return new UD(krVar, this.mCallerContext);
    }
}
