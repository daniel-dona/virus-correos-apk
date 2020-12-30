package com.facebook.react.bridge;

import com.facebook.react.bridge.JSIModule;

/* compiled from: PG */
public interface JSIModuleProvider<T extends JSIModule> {
    T get();
}
