package com.facebook.react.uimanager;

import android.view.ViewGroup;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;

/* compiled from: PG */
public class RootViewManager extends ViewGroupManager<ViewGroup> {
    public static final String REACT_CLASS = "RootView";

    public String getName() {
        return REACT_CLASS;
    }

    public ViewGroup createViewInstance(WA wa) {
        return new SizeMonitoringFrameLayout(wa);
    }
}
