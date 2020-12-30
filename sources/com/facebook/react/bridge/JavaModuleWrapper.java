package com.facebook.react.bridge;

import android.os.Trace;
import com.facebook.react.bridge.NativeModule;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Qw
/* compiled from: PG */
public class JavaModuleWrapper {
    public final ArrayList<MethodDescriptor> mDescs = new ArrayList<>();
    public final JSInstance mJSInstance;
    public final ArrayList<NativeModule.NativeMethod> mMethods = new ArrayList<>();
    public final ModuleHolder mModuleHolder;

    @Qw
    /* compiled from: PG */
    public class MethodDescriptor {
        @Qw
        public Method method;
        @Qw
        public String name;
        @Qw
        public String signature;
        @Qw
        public String type;

        public MethodDescriptor() {
        }
    }

    public JavaModuleWrapper(JSInstance jSInstance, ModuleHolder moduleHolder) {
        this.mJSInstance = jSInstance;
        this.mModuleHolder = moduleHolder;
    }

    @Qw
    private void findMethods() {
        Trace.beginSection("findMethods");
        HashSet hashSet = new HashSet();
        Class<?> cls = this.mModuleHolder.getModule().getClass();
        Class<? super Object> superclass = cls.getSuperclass();
        if (ReactModuleWithSpec.class.isAssignableFrom(superclass)) {
            cls = superclass;
        }
        for (Method method : cls.getDeclaredMethods()) {
            ReactMethod reactMethod = (ReactMethod) method.getAnnotation(ReactMethod.class);
            if (reactMethod != null) {
                String name = method.getName();
                if (!hashSet.contains(name)) {
                    MethodDescriptor methodDescriptor = new MethodDescriptor();
                    JavaMethodWrapper javaMethodWrapper = new JavaMethodWrapper(this, method, reactMethod.isBlockingSynchronousMethod());
                    methodDescriptor.name = name;
                    methodDescriptor.type = javaMethodWrapper.getType();
                    if (methodDescriptor.type == BaseJavaModule.METHOD_TYPE_SYNC) {
                        methodDescriptor.signature = javaMethodWrapper.getSignature();
                        methodDescriptor.method = method;
                    }
                    this.mMethods.add(javaMethodWrapper);
                    this.mDescs.add(methodDescriptor);
                } else {
                    StringBuilder a = Eo.a("Java Module ");
                    a.append(getName());
                    a.append(" method name already registered: ");
                    a.append(name);
                    throw new IllegalArgumentException(a.toString());
                }
            }
        }
        Trace.endSection();
    }

    @Qw
    public NativeMap getConstants() {
        if (!this.mModuleHolder.getHasConstants()) {
            return null;
        }
        String name = getName();
        dF dFVar = cF.a;
        dFVar.a("moduleName", name);
        dF dFVar2 = dFVar;
        ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_START, name);
        BaseJavaModule module = getModule();
        Trace.beginSection("module.getConstants");
        Map<String, Object> constants = module.getConstants();
        Trace.endSection();
        Trace.beginSection("create WritableNativeMap");
        ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_START, name);
        try {
            return Arguments.makeNativeMap(constants);
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_END, name);
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_END, name);
            cF.a.a();
        }
    }

    @Qw
    public List<MethodDescriptor> getMethodDescriptors() {
        if (this.mDescs.isEmpty()) {
            findMethods();
        }
        return this.mDescs;
    }

    @Qw
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @Qw
    public String getName() {
        return this.mModuleHolder.getName();
    }

    @Qw
    public void invoke(int i, ReadableNativeArray readableNativeArray) {
        ArrayList<NativeModule.NativeMethod> arrayList = this.mMethods;
        if (arrayList != null && i < arrayList.size()) {
            this.mMethods.get(i).invoke(this.mJSInstance, readableNativeArray);
        }
    }
}
