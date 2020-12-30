package com.facebook.react.bridge;

import com.facebook.react.module.model.ReactModuleInfo;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

@Qw
/* compiled from: PG */
public class ModuleHolder {
    public static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);
    public boolean mInitializable;
    public final int mInstanceKey = sInstanceKeyCounter.getAndIncrement();
    public boolean mIsCreating;
    public boolean mIsInitializing;
    public NativeModule mModule;
    public final String mName;
    public Provider<? extends NativeModule> mProvider;
    public final ReactModuleInfo mReactModuleInfo;

    public ModuleHolder(ReactModuleInfo reactModuleInfo, Provider<? extends NativeModule> provider) {
        this.mName = reactModuleInfo.mo1068f();
        this.mProvider = provider;
        this.mReactModuleInfo = reactModuleInfo;
        if (reactModuleInfo.mo1069g()) {
            this.mModule = create();
        }
    }

    private NativeModule create() {
        boolean z = true;
        SoftAssertions.assertCondition(this.mModule == null, "Creating an already created module.");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, this.mName, this.mInstanceKey);
        dF dFVar = cF.a;
        dFVar.a("name", this.mName);
        dF dFVar2 = dFVar;
        fr.a.a(gr.c, "NativeModule init: %s", new Object[]{this.mName});
        try {
            Provider<? extends NativeModule> provider = this.mProvider;
            Kw.a(provider);
            NativeModule nativeModule = (NativeModule) provider.get();
            this.mProvider = null;
            synchronized (this) {
                this.mModule = nativeModule;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                }
            }
            if (z) {
                doInitialize(nativeModule);
            }
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END, this.mName, this.mInstanceKey);
            cF.a.a();
            return nativeModule;
        } catch (Throwable th) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END, this.mName, this.mInstanceKey);
            cF.a.a();
            throw th;
        }
    }

    private void doInitialize(NativeModule nativeModule) {
        boolean z;
        dF dFVar = cF.a;
        dFVar.a("name", this.mName);
        dF dFVar2 = dFVar;
        ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_START, this.mName, this.mInstanceKey);
        try {
            synchronized (this) {
                z = true;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                } else {
                    this.mIsInitializing = true;
                }
            }
            if (z) {
                nativeModule.initialize();
                synchronized (this) {
                    this.mIsInitializing = false;
                }
            }
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
            cF.a.a();
        } catch (Throwable th) {
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
            cF.a.a();
            throw th;
        }
    }

    public synchronized void destroy() {
        if (this.mModule != null) {
            this.mModule.onCatalystInstanceDestroy();
        }
    }

    public boolean getCanOverrideExistingModule() {
        return this.mReactModuleInfo.mo1063a();
    }

    public String getClassName() {
        return this.mReactModuleInfo.mo1064b();
    }

    public boolean getHasConstants() {
        return this.mReactModuleInfo.mo1065c();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r1 == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        r0 = create();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.mIsCreating = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0025, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0028, code lost:
        if (r3.mModule == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r0 = r3.mModule;
        Kw.a(r0);
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0039, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x003a, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0026 */
    @Qw
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.NativeModule getModule() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0009
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003e }
            monitor-exit(r3)     // Catch:{ all -> 0x003e }
            return r0
        L_0x0009:
            boolean r0 = r3.mIsCreating     // Catch:{ all -> 0x003e }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0012
            r3.mIsCreating = r1     // Catch:{ all -> 0x003e }
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            monitor-exit(r3)     // Catch:{ all -> 0x003e }
            if (r1 == 0) goto L_0x0025
            com.facebook.react.bridge.NativeModule r0 = r3.create()
            monitor-enter(r3)
            r3.mIsCreating = r2     // Catch:{ all -> 0x0022 }
            r3.notifyAll()     // Catch:{ all -> 0x0022 }
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            return r0
        L_0x0022:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            throw r0
        L_0x0025:
            monitor-enter(r3)
        L_0x0026:
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0032
            boolean r0 = r3.mIsCreating     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0032
            r3.wait()     // Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0026
        L_0x0032:
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003b }
            Kw.a(r0)     // Catch:{ all -> 0x003b }
            com.facebook.react.bridge.NativeModule r0 = (com.facebook.react.bridge.NativeModule) r0     // Catch:{ all -> 0x003b }
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            return r0
        L_0x003b:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            throw r0
        L_0x003e:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x0041:
            throw r0
        L_0x0042:
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ModuleHolder.getModule():com.facebook.react.bridge.NativeModule");
    }

    @Qw
    public String getName() {
        return this.mName;
    }

    public synchronized boolean hasInstance() {
        return this.mModule != null;
    }

    public boolean isCxxModule() {
        return this.mReactModuleInfo.mo1066d();
    }

    public boolean isTurboModule() {
        return this.mReactModuleInfo.mo1067e();
    }

    public void markInitializable() {
        boolean z;
        NativeModule nativeModule;
        synchronized (this) {
            z = true;
            this.mInitializable = true;
            boolean z2 = false;
            if (this.mModule != null) {
                if (!this.mIsInitializing) {
                    z2 = true;
                }
                Kw.a(z2);
                nativeModule = this.mModule;
            } else {
                nativeModule = null;
                z = false;
            }
        }
        if (z) {
            doInitialize(nativeModule);
        }
    }

    public ModuleHolder(NativeModule nativeModule) {
        this.mName = nativeModule.getName();
        this.mReactModuleInfo = new ReactModuleInfo(nativeModule.getName(), nativeModule.getClass().getSimpleName(), nativeModule.canOverrideExistingModule(), true, true, CxxModuleWrapper.class.isAssignableFrom(nativeModule.getClass()), lA.class.isAssignableFrom(nativeModule.getClass()));
        this.mModule = nativeModule;
        fr.a.a(gr.c, "NativeModule init: %s", new Object[]{this.mName});
    }
}
