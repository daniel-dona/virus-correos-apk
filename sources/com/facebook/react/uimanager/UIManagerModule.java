package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Trace;
import android.util.ArrayMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ay(name = "UIManager")
/* compiled from: PG */
public class UIManagerModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener, UIManager {
    public static final boolean DEBUG = false;
    public static final String NAME = "UIManager";
    public int mBatchId;
    public final Map<String, Object> mCustomDirectEvents;
    public final qC mEventDispatcher;
    public final List<hB> mListeners;
    public final C0144e mMemoryTrimCallback;
    public final Map<String, Object> mModuleConstants;
    public final UIImplementation mUIImplementation;
    public Map<String, WritableMap> mViewManagerConstantsCache;
    public volatile int mViewManagerConstantsCacheSize;
    public final OB mViewManagerRegistry;

    /* compiled from: PG */
    public interface CustomEventNamesResolver {
        String resolveCustomEventName(String str);
    }

    /* compiled from: PG */
    public interface ViewManagerResolver {
        ViewManager getViewManager(String str);

        List<String> getViewManagerNames();
    }

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$a */
    /* compiled from: PG */
    public class C0140a implements CustomEventNamesResolver {
        public C0140a() {
        }

        public String resolveCustomEventName(String str) {
            Map map = (Map) UIManagerModule.this.mCustomDirectEvents.get(str);
            return map != null ? (String) map.get("registrationName") : str;
        }
    }

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$b */
    /* compiled from: PG */
    public class C0141b implements SizeMonitoringFrameLayout.OnSizeChangedListener {

        /* renamed from: a */
        public final /* synthetic */ ReactApplicationContext f529a;

        /* renamed from: b */
        public final /* synthetic */ int f530b;

        public C0141b(ReactApplicationContext reactApplicationContext, int i) {
            this.f529a = reactApplicationContext;
            this.f530b = i;
        }

        public void onSizeChanged(int i, int i2, int i3, int i4) {
            ReactApplicationContext reactApplicationContext = this.f529a;
            reactApplicationContext.runOnNativeModulesQueueThread(new eB(this, reactApplicationContext, i, i2));
        }
    }

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$c */
    /* compiled from: PG */
    public class C0142c extends GuardedRunnable {

        /* renamed from: a */
        public final /* synthetic */ int f532a;

        /* renamed from: b */
        public final /* synthetic */ Object f533b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0142c(ReactContext reactContext, int i, Object obj) {
            super(reactContext);
            this.f532a = i;
            this.f533b = obj;
        }

        public void runGuarded() {
            UIImplementation access$200 = UIManagerModule.this.mUIImplementation;
            int i = this.f532a;
            Object obj = this.f533b;
            UA ua = access$200.f521c;
            ua.c.a();
            NA na = (NA) ua.a.get(i);
            if (na == null) {
                pq.c("ReactNative", "Attempt to set local data for view with unknown tag: " + i);
                return;
            }
            na.a(obj);
            access$200.mo1103a();
        }
    }

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$d */
    /* compiled from: PG */
    public class C0143d extends GuardedRunnable {

        /* renamed from: a */
        public final /* synthetic */ int f535a;

        /* renamed from: b */
        public final /* synthetic */ int f536b;

        /* renamed from: c */
        public final /* synthetic */ int f537c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0143d(ReactContext reactContext, int i, int i2, int i3) {
            super(reactContext);
            this.f535a = i;
            this.f536b = i2;
            this.f537c = i3;
        }

        public void runGuarded() {
            UIImplementation access$200 = UIManagerModule.this.mUIImplementation;
            int i = this.f535a;
            int i2 = this.f536b;
            int i3 = this.f537c;
            UA ua = access$200.f521c;
            ua.c.a();
            NA na = (NA) ua.a.get(i);
            if (na == null) {
                pq.c("ReactNative", "Tried to update non-existent root tag: " + i);
            } else {
                access$200.mo1114a(na, i2, i3);
            }
            UIManagerModule.this.mUIImplementation.mo1104a(-1);
        }
    }

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$e */
    /* compiled from: PG */
    public class C0144e implements ComponentCallbacks2 {
        public /* synthetic */ C0144e(UIManagerModule uIManagerModule, C0140a aVar) {
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                dC.a().mo1000a();
            }
        }
    }

    static {
        fr.a.a(gr.d);
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, int i) {
        this(reactApplicationContext, viewManagerResolver, new cB(), i);
    }

    private WritableMap computeConstantsForViewManager(String str) {
        ViewManager a = str != null ? this.mUIImplementation.f523e.a(str) : null;
        if (a == null) {
            return null;
        }
        dF dFVar = cF.a;
        dFVar.a("ViewManager", a.getName());
        dF dFVar2 = dFVar;
        try {
            return Arguments.makeNativeMap((Map<String, Object>) gB.a(a, (Map) null, (Map) null, (Map) null, this.mCustomDirectEvents));
        } finally {
            cF.a.a();
        }
    }

    public static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        dF dFVar = cF.a;
        dFVar.a("Lazy", true);
        dF dFVar2 = dFVar;
        try {
            Map<String, Object> b = fB.b();
            b.put("ViewManagerNames", viewManagerResolver.getViewManagerNames());
            b.put("LazyViewManagersEnabled", true);
            return b;
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    public void addAnimation(int i, int i2, Callback callback) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.mo1110a(i, "addAnimation");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new kB(uIViewOperationQueue, i, i2, callback, (iB) null));
    }

    public <T extends SizeMonitoringFrameLayout & gC> int addRootView(T t, WritableMap writableMap, String str) {
        Trace.beginSection("UIManagerModule.addRootView");
        int a = MA.a();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        this.mUIImplementation.mo1116a(t, a, new WA(reactApplicationContext, t.getContext()));
        t.setOnSizeChangedListener(new C0141b(reactApplicationContext, a));
        Trace.endSection();
        return a;
    }

    public void addUIBlock(aB aBVar) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new CB(uIViewOperationQueue, aBVar));
    }

    public void addUIManagerListener(hB hBVar) {
        this.mListeners.add(hBVar);
    }

    @ReactMethod
    public void clearJSResponder() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new mB(uIViewOperationQueue, 0, 0, true, false));
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new nB(uIViewOperationQueue, readableMap, (iB) null));
    }

    @ReactMethod
    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.createView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            pq.a("ReactNative", str2);
            fr.a.a(gr.d, str2);
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        OA createShadowNodeInstance = uIImplementation.f523e.a(str).createShadowNodeInstance(uIImplementation.f520b);
        UA ua = uIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i2);
        Kw.a(oa, "Root node with tag " + i2 + " doesn't exist");
        OA oa2 = createShadowNodeInstance;
        oa2.d(i);
        oa2.a(str);
        OA oa3 = oa;
        oa2.c = oa3.a;
        oa2.a(oa3.j());
        UA ua2 = uIImplementation.f521c;
        ua2.c.a();
        ua2.a.put(oa2.a, oa2);
        PA pa = null;
        if (readableMap != null) {
            pa = new PA(readableMap);
            oa2.a(pa);
        }
        if (!oa2.a()) {
            uIImplementation.f525g.a(oa2, oa2.j(), pa);
        }
    }

    @ReactMethod
    public void dismissPopupMenu() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new pB(uIViewOperationQueue, (iB) null));
    }

    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.mo1110a(i, "dispatchViewManagerCommand");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new qB(uIViewOperationQueue, i, i2, readableArray));
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int i, int i2, ReadableArray readableArray) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        int i3 = 2;
        if (i % 2 != 0) {
            i3 = 1;
        }
        dB.a(reactApplicationContext, i3).dispatchCommand(i, i2, readableArray);
    }

    @ReactMethod
    public void findSubviewIn(int i, ReadableArray readableArray, Callback callback) {
        UIImplementation uIImplementation = this.mUIImplementation;
        float round = (float) Math.round(GA.a(readableArray.getDouble(0)));
        float round2 = (float) Math.round(GA.a(readableArray.getDouble(1)));
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new sB(uIViewOperationQueue, i, round, round2, callback, (iB) null));
    }

    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        Map<String, WritableMap> map = this.mViewManagerConstantsCache;
        if (map == null || !map.containsKey(str)) {
            return computeConstantsForViewManager(str);
        }
        WritableMap writableMap = this.mViewManagerConstantsCache.get(str);
        int i = this.mViewManagerConstantsCacheSize - 1;
        this.mViewManagerConstantsCacheSize = i;
        if (i <= 0) {
            this.mViewManagerConstantsCache = null;
        }
        return writableMap;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return Arguments.makeNativeMap((Map<String, Object>) hy.a("bubblingEventTypes", fB.a(), "directEventTypes", fB.c()));
    }

    public CustomEventNamesResolver getDirectEventNamesResolver() {
        return new C0140a();
    }

    public qC getEventDispatcher() {
        return this.mEventDispatcher;
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Long> getPerformanceCounters() {
        return this.mUIImplementation.f524f.mo1177b();
    }

    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }

    @Deprecated
    public OB getViewManagerRegistry_DO_NOT_USE() {
        return this.mViewManagerRegistry;
    }

    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
        this.mEventDispatcher.s3.register(1, (RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class));
    }

    public void invalidateNodeLayout(int i) {
        UA ua = this.mUIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (oa == null) {
            pq.c("ReactNative", "Warning : attempted to dirty a non-existent react shadow node. reactTag=" + i);
            return;
        }
        oa.d();
        this.mUIImplementation.mo1104a(-1);
    }

    @ReactMethod
    public void manageChildren(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (DEBUG) {
            String str = "(UIManager.manageChildren) tag: " + i + ", moveFrom: " + readableArray + ", moveTo: " + readableArray2 + ", addTags: " + readableArray3 + ", atIndices: " + readableArray4 + ", removeFrom: " + readableArray5;
            pq.a("ReactNative", str);
            fr.a.a(gr.d, str);
        }
        this.mUIImplementation.mo1109a(i, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @ReactMethod
    public void measure(int i, Callback callback) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new vB(uIViewOperationQueue, i, callback, (iB) null));
    }

    @ReactMethod
    public void measureInWindow(int i, Callback callback) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new uB(uIViewOperationQueue, i, callback, (iB) null));
    }

    @ReactMethod
    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        this.mUIImplementation.mo1105a(i, i2, callback, callback2);
    }

    @ReactMethod
    public void measureLayoutRelativeToParent(int i, Callback callback, Callback callback2) {
        this.mUIImplementation.mo1108a(i, callback, callback2);
    }

    public void onBatchComplete() {
        int i = this.mBatchId;
        this.mBatchId = i + 1;
        dF dFVar = cF.a;
        dFVar.a("BatchId", i);
        dF dFVar2 = dFVar;
        for (hB willDispatchViewUpdates : this.mListeners) {
            willDispatchViewUpdates.willDispatchViewUpdates(this);
        }
        try {
            this.mUIImplementation.mo1104a(i);
        } finally {
            Trace.endSection();
        }
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.b();
        getReactApplicationContext().unregisterComponentCallbacks(this.mMemoryTrimCallback);
        dC.a().mo1000a();
        ViewManagerPropertyUpdater.m535a();
    }

    public void onHostDestroy() {
        this.mUIImplementation.mo1118b();
    }

    public void onHostPause() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f550l = false;
        ReactChoreographer.b().b(ReactChoreographer.CallbackType.DISPATCH_UI, uIViewOperationQueue.f544f);
        uIViewOperationQueue.mo1170a();
    }

    public void onHostResume() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f550l = true;
        ReactChoreographer.b().a(ReactChoreographer.CallbackType.DISPATCH_UI, uIViewOperationQueue.f544f);
    }

    @ReactMethod
    public void playTouchSound() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.playSoundEffect(0);
        }
    }

    @Deprecated
    public void preComputeConstantsForViewManager(List<String> list) {
        ArrayMap arrayMap = new ArrayMap();
        for (String next : list) {
            WritableMap computeConstantsForViewManager = computeConstantsForViewManager(next);
            if (computeConstantsForViewManager != null) {
                arrayMap.put(next, computeConstantsForViewManager);
            }
        }
        this.mViewManagerConstantsCacheSize = list.size();
        this.mViewManagerConstantsCache = Collections.unmodifiableMap(arrayMap);
    }

    public void prependUIBlock(aB aBVar) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(0, new CB(uIViewOperationQueue, aBVar));
    }

    public void profileNextBatch() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f552n = true;
        uIViewOperationQueue.f554p = 0;
    }

    public void registerAnimation(ey eyVar) {
        this.mUIImplementation.f524f.mo1176a(eyVar);
        throw null;
    }

    public void removeAnimation(int i, int i2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.mo1110a(i, "removeAnimation");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new xB(uIViewOperationQueue, i2, (iB) null));
    }

    @ReactMethod
    public void removeRootView(int i) {
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        if (i != -1) {
            if (ua.b.get(i)) {
                ua.a.remove(i);
                ua.b.delete(i);
            } else {
                throw new IllegalViewOperationException(Eo.a("View with tag ", i, " is not registered as a root view"));
            }
        }
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new yB(uIViewOperationQueue, i));
    }

    @ReactMethod
    public void removeSubviewsFromContainerWithID(int i) {
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (oa != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i2 = 0; i2 < oa.f(); i2++) {
                createArray.pushInt(i2);
            }
            uIImplementation.mo1109a(i, (ReadableArray) null, (ReadableArray) null, (ReadableArray) null, (ReadableArray) null, createArray);
            return;
        }
        throw new IllegalViewOperationException(Eo.b("Trying to remove subviews of an unknown view tag: ", i));
    }

    public void removeUIManagerListener(hB hBVar) {
        this.mListeners.remove(hBVar);
    }

    @ReactMethod
    public void replaceExistingNonRootView(int i, int i2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        if (!ua.b.get(i)) {
            UA ua2 = uIImplementation.f521c;
            ua2.c.a();
            if (!ua2.b.get(i2)) {
                UA ua3 = uIImplementation.f521c;
                ua3.c.a();
                OA oa = (NA) ua3.a.get(i);
                if (oa != null) {
                    OA oa2 = oa.h;
                    if (oa2 != null) {
                        int b = oa2.b(oa);
                        if (b >= 0) {
                            WritableArray createArray = Arguments.createArray();
                            createArray.pushInt(i2);
                            WritableArray createArray2 = Arguments.createArray();
                            createArray2.pushInt(b);
                            WritableArray createArray3 = Arguments.createArray();
                            createArray3.pushInt(b);
                            uIImplementation.mo1109a(oa2.a, (ReadableArray) null, (ReadableArray) null, createArray, createArray2, createArray3);
                            return;
                        }
                        throw new IllegalStateException("Didn't find child tag in parent");
                    }
                    throw new IllegalViewOperationException(Eo.b("Node is not attached to a parent: ", i));
                }
                throw new IllegalViewOperationException(Eo.b("Trying to replace unknown view tag: ", i));
            }
        }
        throw new IllegalViewOperationException("Trying to add or replace a root tag!");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int resolveRootTagFromReactTag(int r6) {
        /*
            r5 = this;
            int r0 = r6 % 10
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            if (r0 == 0) goto L_0x000c
            goto L_0x0048
        L_0x000c:
            com.facebook.react.uimanager.UIImplementation r0 = r5.mUIImplementation
            UA r3 = r0.f521c
            iy r4 = r3.c
            r4.a()
            android.util.SparseBooleanArray r3 = r3.b
            boolean r3 = r3.get(r6)
            if (r3 == 0) goto L_0x001e
            goto L_0x0048
        L_0x001e:
            NA r0 = r0.mo1117b((int) r6)
            if (r0 == 0) goto L_0x0031
            OA r0 = (OA) r0
            int r6 = r0.c
            if (r6 == 0) goto L_0x002b
            r1 = 1
        L_0x002b:
            Kw.a(r1)
            int r1 = r0.c
            goto L_0x0047
        L_0x0031:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Warning : attempted to resolve a non-existent react shadow node. reactTag="
            r0.append(r2)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "ReactNative"
            pq.c(r0, r6)
        L_0x0047:
            r6 = r1
        L_0x0048:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIManagerModule.resolveRootTagFromReactTag(int):int");
    }

    @ReactMethod
    public void sendAccessibilityEvent(int i, int i2) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new zB(uIViewOperationQueue, i, i2, (iB) null));
    }

    @ReactMethod
    public void setChildren(int i, ReadableArray readableArray) {
        if (DEBUG) {
            String str = "(UIManager.setChildren) tag: " + i + ", children: " + readableArray;
            pq.a("ReactNative", str);
            fr.a.a(gr.d, str);
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        NA na = (NA) ua.a.get(i);
        int i2 = 0;
        while (i2 < readableArray.size()) {
            NA a = uIImplementation.f521c.a(readableArray.getInt(i2));
            if (a != null) {
                na.a(a, i2);
                i2++;
            } else {
                StringBuilder a2 = Eo.a("Trying to add unknown view tag: ");
                a2.append(readableArray.getInt(i2));
                throw new IllegalViewOperationException(a2.toString());
            }
        }
        if (!na.a() && !na.c()) {
            uIImplementation.f525g.a(na, readableArray);
        }
    }

    @ReactMethod
    public void setJSResponder(int i, boolean z) {
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (oa != null) {
            while (true) {
                if (!oa.a()) {
                    OA oa2 = oa;
                    if (!oa2.i) {
                        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
                        uIViewOperationQueue.f546h.add(new mB(uIViewOperationQueue, oa2.a, i, false, z));
                        return;
                    }
                }
                oa = oa.h;
            }
        }
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new AB(uIViewOperationQueue, z, (iB) null));
    }

    public void setViewHierarchyUpdateDebugListener(hC hCVar) {
        this.mUIImplementation.f524f.f549k = hCVar;
    }

    public void setViewLocalData(int i, Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnUiQueueThread();
        reactApplicationContext.runOnNativeModulesQueueThread(new C0142c(reactApplicationContext, i, obj));
    }

    @ReactMethod
    public void showPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.mo1110a(i, "showPopupMenu");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.f524f;
        uIViewOperationQueue.f546h.add(new BB(uIViewOperationQueue, i, readableArray, callback, callback2));
    }

    public void updateNodeSize(int i, int i2, int i3) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        if (oa == null) {
            pq.c("ReactNative", "Tried to update size of non-existent tag: " + i);
            return;
        }
        OA oa2 = oa;
        oa2.t.mo1936q((float) i2);
        oa2.t.mo1916g((float) i3);
        uIImplementation.mo1103a();
    }

    public void updateRootLayoutSpecs(int i, int i2, int i3) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.runOnNativeModulesQueueThread(new C0143d(reactApplicationContext, i, i2, i3));
    }

    @ReactMethod
    public void updateView(int i, String str, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.updateView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            pq.a("ReactNative", str2);
            fr.a.a(gr.d, str2);
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        if (uIImplementation.f523e.a(str) != null) {
            UA ua = uIImplementation.f521c;
            ua.c.a();
            OA oa = (NA) ua.a.get(i);
            if (oa == null) {
                throw new IllegalViewOperationException(Eo.b("Trying to update non-existent view with tag ", i));
            } else if (readableMap != null) {
                PA pa = new PA(readableMap);
                oa.a(pa);
                if (!oa.a()) {
                    uIImplementation.f525g.a(oa, str, pa);
                }
            }
        } else {
            throw new IllegalViewOperationException(Eo.a("Got unknown view type: ", str));
        }
    }

    @ReactMethod
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        UIImplementation uIImplementation = this.mUIImplementation;
        UA ua = uIImplementation.f521c;
        ua.c.a();
        OA oa = (NA) ua.a.get(i);
        UA ua2 = uIImplementation.f521c;
        ua2.c.a();
        OA oa2 = (NA) ua2.a.get(i2);
        boolean z = true;
        if (oa == null || oa2 == null) {
            callback.invoke(false);
            return;
        }
        Object[] objArr = new Object[1];
        OA oa3 = oa2;
        OA oa4 = oa.h;
        while (true) {
            if (oa4 == null) {
                z = false;
                break;
            } else if (oa4 == oa3) {
                break;
            } else {
                oa4 = oa4.h;
            }
        }
        objArr[0] = Boolean.valueOf(z);
        callback.invoke(objArr);
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int i) {
        this(reactApplicationContext, list, new cB(), i);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, cB cBVar, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new C0144e(this, (C0140a) null);
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        rA.b(reactApplicationContext);
        this.mEventDispatcher = new qC(reactApplicationContext);
        this.mModuleConstants = createConstants(viewManagerResolver);
        this.mCustomDirectEvents = fB.c();
        this.mViewManagerRegistry = new OB(viewManagerResolver);
        this.mUIImplementation = cBVar.a(reactApplicationContext, this.mViewManagerRegistry, this.mEventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public <T extends SizeMonitoringFrameLayout & gC> int addRootView(T t) {
        return addRootView(t, (WritableMap) null, (String) null);
    }

    public static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        dF dFVar = cF.a;
        dFVar.a("Lazy", false);
        dF dFVar2 = dFVar;
        try {
            return gB.a(list, map, map2);
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, cB cBVar, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new C0144e(this, (C0140a) null);
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        rA.b(reactApplicationContext);
        this.mEventDispatcher = new qC(reactApplicationContext);
        this.mCustomDirectEvents = new HashMap();
        this.mModuleConstants = createConstants(list, (Map<String, Object>) null, this.mCustomDirectEvents);
        this.mViewManagerRegistry = new OB(list);
        this.mUIImplementation = cBVar.a(reactApplicationContext, this.mViewManagerRegistry, this.mEventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }
}
