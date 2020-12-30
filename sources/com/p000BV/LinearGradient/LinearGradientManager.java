package com.p000BV.LinearGradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;

/* renamed from: com.BV.LinearGradient.LinearGradientManager */
/* compiled from: PG */
public class LinearGradientManager extends SimpleViewManager<LinearGradientView> {
    public static final String PROP_ANGLE = "angle";
    public static final String PROP_ANGLE_CENTER = "angleCenter";
    public static final String PROP_BORDER_RADII = "borderRadii";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_END_POS = "endPoint";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POS = "startPoint";
    public static final String PROP_USE_ANGLE = "useAngle";
    public static final String REACT_CLASS = "BVLinearGradient";

    public String getName() {
        return REACT_CLASS;
    }

    @eC(defaultFloat = 45.0f, name = "angle")
    public void setAngle(LinearGradientView linearGradientView, float f) {
        linearGradientView.setAngle(f);
    }

    @eC(name = "angleCenter")
    public void setAngleCenter(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setAngleCenter(readableArray);
    }

    @eC(name = "borderRadii")
    public void setBorderRadii(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setBorderRadii(readableArray);
    }

    @eC(name = "colors")
    public void setColors(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setColors(readableArray);
    }

    @eC(name = "endPoint")
    public void setEndPosition(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setEndPosition(readableArray);
    }

    @eC(name = "locations")
    public void setLocations(LinearGradientView linearGradientView, ReadableArray readableArray) {
        if (readableArray != null) {
            linearGradientView.setLocations(readableArray);
        }
    }

    @eC(name = "startPoint")
    public void setStartPosition(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setStartPosition(readableArray);
    }

    @eC(defaultBoolean = false, name = "useAngle")
    public void setUseAngle(LinearGradientView linearGradientView, boolean z) {
        linearGradientView.setUseAngle(z);
    }

    public LinearGradientView createViewInstance(WA wa) {
        return new LinearGradientView(wa);
    }
}
