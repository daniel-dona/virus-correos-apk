package com.facebook.react.bridge;

import com.facebook.react.bridge.JSIModule;

/* compiled from: PG */
public interface JSIModuleSpec<T extends JSIModule> {
    Class<? extends JSIModule> getJSIModuleClass();

    JSIModuleProvider<T> getJSIModuleProvider();
}
