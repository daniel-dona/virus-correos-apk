package com.facebook.react.bridge;

import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;

/* compiled from: PG */
public interface UIManager extends JSIModule, PerformanceCounter {
    <T extends SizeMonitoringFrameLayout & gC> int addRootView(T t, WritableMap writableMap, String str);

    void clearJSResponder();

    void dispatchCommand(int i, int i2, ReadableArray readableArray);

    void removeRootView(int i);

    void setJSResponder(int i, boolean z);

    void updateRootLayoutSpecs(int i, int i2, int i3);
}
