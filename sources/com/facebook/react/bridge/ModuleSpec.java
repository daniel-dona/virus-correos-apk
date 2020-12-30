package com.facebook.react.bridge;

import javax.inject.Provider;

/* compiled from: PG */
public class ModuleSpec {
    public static final String TAG = "ModuleSpec";
    public final String mName;
    public final Provider<? extends NativeModule> mProvider;
    public final Class<? extends NativeModule> mType = null;

    public ModuleSpec(Provider<? extends NativeModule> provider) {
        this.mProvider = provider;
        this.mName = null;
    }

    public static ModuleSpec nativeModuleSpec(Class<? extends NativeModule> cls, Provider<? extends NativeModule> provider) {
        Ay annotation = cls.getAnnotation(Ay.class);
        if (annotation != null) {
            return new ModuleSpec(provider, annotation.name());
        }
        StringBuilder a = Eo.a("Could not find @ReactModule annotation on ");
        a.append(cls.getName());
        a.append(". So creating the module eagerly to get the name. Consider adding an annotation to make this Lazy");
        pq.c(TAG, a.toString());
        return new ModuleSpec(provider, ((NativeModule) provider.get()).getName());
    }

    public static ModuleSpec viewManagerSpec(Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider);
    }

    public String getName() {
        return this.mName;
    }

    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }

    public Class<? extends NativeModule> getType() {
        return this.mType;
    }

    public ModuleSpec(Provider<? extends NativeModule> provider, String str) {
        this.mProvider = provider;
        this.mName = str;
    }

    public static ModuleSpec nativeModuleSpec(String str, Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider, str);
    }
}
