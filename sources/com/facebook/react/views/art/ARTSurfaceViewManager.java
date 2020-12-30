package com.facebook.react.views.art;

import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

@Ay(name = "ARTSurfaceView")
/* compiled from: PG */
public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, LC> {
    public static final YogaMeasureFunction MEASURE_FUNCTION = new C0145a();
    public static final String REACT_CLASS = "ARTSurfaceView";

    /* renamed from: com.facebook.react.views.art.ARTSurfaceViewManager$a */
    /* compiled from: PG */
    public static class C0145a implements YogaMeasureFunction {
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<LC> getShadowNodeClass() {
        return LC.class;
    }

    public void setBackgroundColor(ARTSurfaceView aRTSurfaceView, int i) {
    }

    public LC createShadowNodeInstance() {
        LC lc = new LC();
        lc.t.mo1888a(MEASURE_FUNCTION);
        return lc;
    }

    public ARTSurfaceView createViewInstance(WA wa) {
        return new ARTSurfaceView(wa);
    }

    public void updateExtraData(ARTSurfaceView aRTSurfaceView, Object obj) {
        aRTSurfaceView.setSurfaceTextureListener((LC) obj);
    }
}
