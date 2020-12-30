package com.facebook.react.bridge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class JSIModuleRegistry {
    public final Map<Class, JSIModuleHolder> mModules = new HashMap();

    public <T extends JSIModule> T getModule(Class<T> cls) {
        JSIModuleHolder jSIModuleHolder = this.mModules.get(cls);
        if (jSIModuleHolder != null) {
            T jSIModule = jSIModuleHolder.getJSIModule();
            Kw.a(jSIModule);
            return (JSIModule) jSIModule;
        }
        throw new IllegalArgumentException("Unable to find JSIModule for class " + cls);
    }

    public void notifyJSInstanceDestroy() {
        for (JSIModuleHolder notifyJSInstanceDestroy : this.mModules.values()) {
            notifyJSInstanceDestroy.notifyJSInstanceDestroy();
        }
    }

    public void registerModules(List<JSIModuleSpec> list) {
        for (JSIModuleSpec next : list) {
            this.mModules.put(next.getJSIModuleClass(), new JSIModuleHolder(next));
        }
    }
}
