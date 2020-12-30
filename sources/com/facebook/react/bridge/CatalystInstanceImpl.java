package com.facebook.react.bridge;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Trace;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.systrace.TraceListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Qw
/* compiled from: PG */
public class CatalystInstanceImpl implements CatalystInstance {
    public static final AtomicInteger sNextInstanceIdForTrace = new AtomicInteger(1);
    public volatile boolean mAcceptCalls;
    public final CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener> mBridgeIdleListeners;
    public volatile boolean mDestroyed;
    public final HybridData mHybridData;
    public boolean mInitialized;
    public boolean mJSBundleHasLoaded;
    public final JSBundleLoader mJSBundleLoader;
    public final ArrayList<PendingJSCall> mJSCallsPendingInit;
    public final Object mJSCallsPendingInitLock;
    public final JSIModuleRegistry mJSIModuleRegistry;
    public final JavaScriptModuleRegistry mJSModuleRegistry;
    public JavaScriptContextHolder mJavaScriptContextHolder;
    public final String mJsPendingCallsTitleForTrace;
    public final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    public final NativeModuleRegistry mNativeModuleRegistry;
    public final MessageQueueThread mNativeModulesQueueThread;
    public final AtomicInteger mPendingJSCalls;
    public final ReactQueueConfigurationImpl mReactQueueConfiguration;
    public String mSourceURL;
    public final TraceListener mTraceListener;

    /* compiled from: PG */
    public static class BridgeCallback implements ReactCallback {
        public final WeakReference<CatalystInstanceImpl> mOuter;

        public BridgeCallback(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        public void decrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.decrementPendingJSCalls();
            }
        }

        public void incrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.incrementPendingJSCalls();
            }
        }

        public void onBatchComplete() {
            CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.mNativeModuleRegistry.onBatchComplete();
            }
        }
    }

    /* compiled from: PG */
    public static class Builder {
        public JSBundleLoader mJSBundleLoader;
        public JavaScriptExecutor mJSExecutor;
        public NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
        public ReactQueueConfigurationSpec mReactQueueConfigurationSpec;
        public NativeModuleRegistry mRegistry;

        public CatalystInstanceImpl build() {
            ReactQueueConfigurationSpec reactQueueConfigurationSpec = this.mReactQueueConfigurationSpec;
            Kw.a(reactQueueConfigurationSpec);
            JavaScriptExecutor javaScriptExecutor = this.mJSExecutor;
            Kw.a(javaScriptExecutor);
            NativeModuleRegistry nativeModuleRegistry = this.mRegistry;
            Kw.a(nativeModuleRegistry);
            JSBundleLoader jSBundleLoader = this.mJSBundleLoader;
            Kw.a(jSBundleLoader);
            NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
            Kw.a(nativeModuleCallExceptionHandler);
            return new CatalystInstanceImpl(reactQueueConfigurationSpec, javaScriptExecutor, nativeModuleRegistry, jSBundleLoader, nativeModuleCallExceptionHandler);
        }

        public Builder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
            this.mJSBundleLoader = jSBundleLoader;
            return this;
        }

        public Builder setJSExecutor(JavaScriptExecutor javaScriptExecutor) {
            this.mJSExecutor = javaScriptExecutor;
            return this;
        }

        public Builder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
            this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
            return this;
        }

        public Builder setReactQueueConfigurationSpec(ReactQueueConfigurationSpec reactQueueConfigurationSpec) {
            this.mReactQueueConfigurationSpec = reactQueueConfigurationSpec;
            return this;
        }

        public Builder setRegistry(NativeModuleRegistry nativeModuleRegistry) {
            this.mRegistry = nativeModuleRegistry;
            return this;
        }
    }

    /* compiled from: PG */
    public static class JSProfilerTraceListener implements TraceListener {
        public final WeakReference<CatalystInstanceImpl> mOuter;

        public JSProfilerTraceListener(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        public void onTraceStarted() {
            CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(true);
            }
        }

        public void onTraceStopped() {
            CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(false);
            }
        }
    }

    /* compiled from: PG */
    public class NativeExceptionHandler implements QueueThreadExceptionHandler {
        public NativeExceptionHandler() {
        }

        public void handleException(Exception exc) {
            CatalystInstanceImpl.this.onNativeException(exc);
        }
    }

    /* compiled from: PG */
    public static class PendingJSCall {
        public NativeArray mArguments;
        public String mMethod;
        public String mModule;

        public PendingJSCall(String str, String str2, NativeArray nativeArray) {
            this.mModule = str;
            this.mMethod = str2;
            this.mArguments = nativeArray;
        }

        public void call(CatalystInstanceImpl catalystInstanceImpl) {
            NativeArray nativeArray = this.mArguments;
            if (nativeArray == null) {
                nativeArray = new WritableNativeArray();
            }
            catalystInstanceImpl.jniCallJSFunction(this.mModule, this.mMethod, nativeArray);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.mModule);
            sb.append(".");
            sb.append(this.mMethod);
            sb.append("(");
            NativeArray nativeArray = this.mArguments;
            if (nativeArray == null) {
                str = BuildConfig.FLAVOR;
            } else {
                str = nativeArray.toString();
            }
            return Eo.a(sb, str, ")");
        }
    }

    static {
        ReactBridge.staticInit();
    }

    /* access modifiers changed from: private */
    public void decrementPendingJSCalls() {
        if ((this.mPendingJSCalls.decrementAndGet() == 0) && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() {
                public void run() {
                    Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeIdle();
                    }
                }
            });
        }
    }

    private native long getJavaScriptContext();

    private <T extends NativeModule> String getNameFromAnnotation(Class<T> cls) {
        Ay annotation = cls.getAnnotation(Ay.class);
        if (annotation != null) {
            return annotation.name();
        }
        StringBuilder a = Eo.a("Could not find @ReactModule annotation in ");
        a.append(cls.getCanonicalName());
        throw new IllegalArgumentException(a.toString());
    }

    /* access modifiers changed from: private */
    public void incrementPendingJSCalls() {
        if ((this.mPendingJSCalls.getAndIncrement() == 0) && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() {
                public void run() {
                    Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeBusy();
                    }
                }
            });
        }
    }

    public static native HybridData initHybrid();

    private native void initializeBridge(ReactCallback reactCallback, JavaScriptExecutor javaScriptExecutor, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniCallJSCallback(int i, NativeArray nativeArray);

    /* access modifiers changed from: private */
    public native void jniCallJSFunction(String str, String str2, NativeArray nativeArray);

    private native void jniExtendNativeModules(Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniHandleMemoryPressure(int i);

    private native void jniLoadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    private native void jniLoadScriptFromDeltaBundle(String str, NativeDeltaClient nativeDeltaClient, boolean z);

    private native void jniLoadScriptFromFile(String str, String str2, boolean z);

    private native void jniRegisterSegment(int i, String str);

    private native void jniSetSourceURL(String str);

    /* access modifiers changed from: private */
    public void onNativeException(Exception exc) {
        this.mNativeModuleCallExceptionHandler.handleException(exc);
        this.mReactQueueConfiguration.getUIQueueThread().runOnQueue(new Runnable() {
            public void run() {
                CatalystInstanceImpl.this.destroy();
            }
        });
    }

    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.add(notThreadSafeBridgeIdleDebugListener);
    }

    public void addJSIModules(List<JSIModuleSpec> list) {
        this.mJSIModuleRegistry.registerModules(list);
    }

    public void callFunction(String str, String str2, NativeArray nativeArray) {
        callFunction(new PendingJSCall(str, str2, nativeArray));
    }

    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        if (!this.mDestroyed) {
            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_START);
            this.mDestroyed = true;
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() {
                public void run() {
                    CatalystInstanceImpl.this.mNativeModuleRegistry.notifyJSInstanceDestroy();
                    CatalystInstanceImpl.this.mJSIModuleRegistry.notifyJSInstanceDestroy();
                    boolean z = false;
                    if (CatalystInstanceImpl.this.mPendingJSCalls.getAndSet(0) == 0) {
                        z = true;
                    }
                    if (!CatalystInstanceImpl.this.mBridgeIdleListeners.isEmpty()) {
                        Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                        while (it.hasNext()) {
                            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = (NotThreadSafeBridgeIdleDebugListener) it.next();
                            if (!z) {
                                notThreadSafeBridgeIdleDebugListener.onTransitionToBridgeIdle();
                            }
                            notThreadSafeBridgeIdleDebugListener.onBridgeDestroyed();
                        }
                    }
                    AsyncTask.execute(new Runnable() {
                        public void run() {
                            CatalystInstanceImpl.this.mJavaScriptContextHolder.clear();
                            CatalystInstanceImpl.this.mHybridData.mo569a();
                            CatalystInstanceImpl.this.getReactQueueConfiguration().destroy();
                            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_END);
                        }
                    });
                }
            });
        }
    }

    public void extendNativeModules(NativeModuleRegistry nativeModuleRegistry) {
        this.mNativeModuleRegistry.registerModules(nativeModuleRegistry);
        jniExtendNativeModules(nativeModuleRegistry.getJavaModules(this), nativeModuleRegistry.getCxxModules());
    }

    public <T extends JSIModule> T getJSIModule(Class<T> cls) {
        return this.mJSIModuleRegistry.getModule(cls);
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        return this.mJSModuleRegistry.getJavaScriptModule(this, cls);
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mJavaScriptContextHolder;
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return this.mNativeModuleRegistry.getModule(getNameFromAnnotation(cls));
    }

    public Collection<NativeModule> getNativeModules() {
        return this.mNativeModuleRegistry.getAllModules();
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }

    public String getSourceURL() {
        return this.mSourceURL;
    }

    public void handleMemoryPressure(int i) {
        if (!this.mDestroyed) {
            jniHandleMemoryPressure(i);
        }
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        return this.mNativeModuleRegistry.hasModule(getNameFromAnnotation(cls));
    }

    public boolean hasRunJSBundle() {
        boolean z;
        synchronized (this.mJSCallsPendingInitLock) {
            z = this.mJSBundleHasLoaded && this.mAcceptCalls;
        }
        return z;
    }

    public void initialize() {
        Kw.a(!this.mInitialized, "This catalyst instance has already been initialized");
        Kw.a(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mNativeModulesQueueThread.runOnQueue(new Runnable() {
            public void run() {
                CatalystInstanceImpl.this.mNativeModuleRegistry.notifyJSInstanceInitialized();
            }
        });
    }

    public void invokeCallback(int i, NativeArrayInterface nativeArrayInterface) {
        if (this.mDestroyed) {
            pq.c("ReactNative", "Invoking JS callback after bridge has been destroyed.");
        } else {
            jniCallJSCallback(i, (NativeArray) nativeArrayInterface);
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
        this.mSourceURL = str;
        jniLoadScriptFromAssets(assetManager, str, z);
    }

    public void loadScriptFromDeltaBundle(String str, NativeDeltaClient nativeDeltaClient, boolean z) {
        this.mSourceURL = str;
        jniLoadScriptFromDeltaBundle(str, nativeDeltaClient, z);
    }

    public void loadScriptFromFile(String str, String str2, boolean z) {
        this.mSourceURL = str2;
        jniLoadScriptFromFile(str, str2, z);
    }

    public void registerSegment(int i, String str) {
        jniRegisterSegment(i, str);
    }

    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.remove(notThreadSafeBridgeIdleDebugListener);
    }

    public void runJSBundle() {
        Kw.a(!this.mJSBundleHasLoaded, "JS bundle was already loaded!");
        this.mJSBundleLoader.loadScript(this);
        synchronized (this.mJSCallsPendingInitLock) {
            this.mAcceptCalls = true;
            Iterator<PendingJSCall> it = this.mJSCallsPendingInit.iterator();
            while (it.hasNext()) {
                it.next().call(this);
            }
            this.mJSCallsPendingInit.clear();
            this.mJSBundleHasLoaded = true;
        }
    }

    public native void setGlobalVariable(String str, String str2);

    public void setSourceURLs(String str, String str2) {
        this.mSourceURL = str;
        jniSetSourceURL(str2);
    }

    public CatalystInstanceImpl(ReactQueueConfigurationSpec reactQueueConfigurationSpec, JavaScriptExecutor javaScriptExecutor, NativeModuleRegistry nativeModuleRegistry, JSBundleLoader jSBundleLoader, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mPendingJSCalls = new AtomicInteger(0);
        StringBuilder a = Eo.a("pending_js_calls_instance");
        a.append(sNextInstanceIdForTrace.getAndIncrement());
        this.mJsPendingCallsTitleForTrace = a.toString();
        this.mDestroyed = false;
        this.mJSCallsPendingInit = new ArrayList<>();
        this.mJSCallsPendingInitLock = new Object();
        this.mJSIModuleRegistry = new JSIModuleRegistry();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        Trace.beginSection("createCatalystInstanceImpl");
        this.mHybridData = initHybrid();
        this.mReactQueueConfiguration = ReactQueueConfigurationImpl.create(reactQueueConfigurationSpec, new NativeExceptionHandler());
        this.mBridgeIdleListeners = new CopyOnWriteArrayList<>();
        this.mNativeModuleRegistry = nativeModuleRegistry;
        this.mJSModuleRegistry = new JavaScriptModuleRegistry();
        this.mJSBundleLoader = jSBundleLoader;
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        this.mNativeModulesQueueThread = this.mReactQueueConfiguration.getNativeModulesQueueThread();
        this.mTraceListener = new JSProfilerTraceListener(this);
        Trace.endSection();
        Trace.beginSection("initializeCxxBridge");
        initializeBridge(new BridgeCallback(this), javaScriptExecutor, this.mReactQueueConfiguration.getJSQueueThread(), this.mNativeModulesQueueThread, this.mNativeModuleRegistry.getJavaModules(this), this.mNativeModuleRegistry.getCxxModules());
        Trace.endSection();
        this.mJavaScriptContextHolder = new JavaScriptContextHolder(getJavaScriptContext());
    }

    public void callFunction(PendingJSCall pendingJSCall) {
        if (this.mDestroyed) {
            String pendingJSCall2 = pendingJSCall.toString();
            pq.c("ReactNative", "Calling JS function after bridge has been destroyed: " + pendingJSCall2);
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                if (!this.mAcceptCalls) {
                    this.mJSCallsPendingInit.add(pendingJSCall);
                    return;
                }
            }
        }
        pendingJSCall.call(this);
    }

    public NativeModule getNativeModule(String str) {
        return this.mNativeModuleRegistry.getModule(str);
    }
}
