package com.facebook.react.bridge;

import android.os.Trace;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class NativeModuleRegistry {
    public final Map<String, ModuleHolder> mModules;
    public final ReactApplicationContext mReactApplicationContext;

    public NativeModuleRegistry(ReactApplicationContext reactApplicationContext, Map<String, ModuleHolder> map) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mModules = map;
    }

    private Map<String, ModuleHolder> getModuleMap() {
        return this.mModules;
    }

    private ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    public List<NativeModule> getAllModules() {
        ArrayList arrayList = new ArrayList();
        for (ModuleHolder module : this.mModules.values()) {
            arrayList.add(module.getModule());
        }
        return arrayList;
    }

    public Collection<ModuleHolder> getCxxModules() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mModules.entrySet()) {
            if (((ModuleHolder) next.getValue()).isCxxModule()) {
                arrayList.add(next.getValue());
            }
        }
        return arrayList;
    }

    public Collection<JavaModuleWrapper> getJavaModules(JSInstance jSInstance) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mModules.entrySet()) {
            if (!((ModuleHolder) next.getValue()).isCxxModule()) {
                arrayList.add(new JavaModuleWrapper(jSInstance, (ModuleHolder) next.getValue()));
            }
        }
        return arrayList;
    }

    public <T extends NativeModule> T getModule(Class<T> cls) {
        Ay annotation = cls.getAnnotation(Ay.class);
        if (annotation != null) {
            ModuleHolder moduleHolder = this.mModules.get(annotation.name());
            Kw.a(moduleHolder, annotation.name() + " could not be found. Is it defined in " + cls.getName());
            return moduleHolder.getModule();
        }
        StringBuilder a = Eo.a("Could not find @ReactModule annotation in class ");
        a.append(cls.getName());
        throw new IllegalArgumentException(a.toString());
    }

    public <T extends NativeModule> boolean hasModule(Class<T> cls) {
        return this.mModules.containsKey(cls.getAnnotation(Ay.class).name());
    }

    public void notifyJSInstanceDestroy() {
        this.mReactApplicationContext.assertOnNativeModulesQueueThread();
        Trace.beginSection("NativeModuleRegistry_notifyJSInstanceDestroy");
        try {
            for (ModuleHolder destroy : this.mModules.values()) {
                destroy.destroy();
            }
        } finally {
            Trace.endSection();
        }
    }

    public void notifyJSInstanceInitialized() {
        this.mReactApplicationContext.assertOnNativeModulesQueueThread("From version React Native v0.44, native modules are explicitly not initialized on the UI thread. See https://github.com/facebook/react-native/wiki/Breaking-Changes#d4611211-reactnativeandroidbreaking-move-nativemodule-initialization-off-ui-thread---aaachiuuu  for more details.");
        ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START);
        Trace.beginSection("NativeModuleRegistry_notifyJSInstanceInitialized");
        try {
            for (ModuleHolder markInitializable : this.mModules.values()) {
                markInitializable.markInitializable();
            }
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_END);
        }
    }

    public void onBatchComplete() {
        ModuleHolder moduleHolder = this.mModules.get(UIManagerModule.NAME);
        if (moduleHolder != null && moduleHolder.hasInstance()) {
            ((OnBatchCompleteListener) moduleHolder.getModule()).onBatchComplete();
        }
    }

    public void registerModules(NativeModuleRegistry nativeModuleRegistry) {
        Kw.a(this.mReactApplicationContext.equals(nativeModuleRegistry.getReactApplicationContext()), "Extending native modules with non-matching application contexts.");
        for (Map.Entry next : nativeModuleRegistry.getModuleMap().entrySet()) {
            String str = (String) next.getKey();
            if (!this.mModules.containsKey(str)) {
                this.mModules.put(str, (ModuleHolder) next.getValue());
            }
        }
    }

    public boolean hasModule(String str) {
        return this.mModules.containsKey(str);
    }

    public NativeModule getModule(String str) {
        ModuleHolder moduleHolder = this.mModules.get(str);
        Kw.a(moduleHolder, "Could not find module with name " + str);
        return moduleHolder.getModule();
    }
}
