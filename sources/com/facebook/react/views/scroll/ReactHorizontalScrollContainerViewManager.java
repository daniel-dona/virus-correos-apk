package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.ViewGroupManager;

@Ay(name = "AndroidHorizontalScrollContentView")
/* compiled from: PG */
public class ReactHorizontalScrollContainerViewManager extends ViewGroupManager<ReactHorizontalScrollContainerView> {
    public static final String REACT_CLASS = "AndroidHorizontalScrollContentView";

    public String getName() {
        return REACT_CLASS;
    }

    public ReactHorizontalScrollContainerView createViewInstance(WA wa) {
        return new ReactHorizontalScrollContainerView(wa);
    }
}
