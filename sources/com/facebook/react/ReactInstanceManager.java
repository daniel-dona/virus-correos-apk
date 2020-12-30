package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.fabric.ReactFabric;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
public class ReactInstanceManager {

    /* renamed from: a */
    public final Set<ReactRootView> f419a = Collections.synchronizedSet(new HashSet());

    /* renamed from: b */
    public volatile LifecycleState f420b;

    /* renamed from: c */
    public tx f421c;

    /* renamed from: d */
    public volatile Thread f422d;

    /* renamed from: e */
    public final JavaScriptExecutorFactory f423e;

    /* renamed from: f */
    public final JSBundleLoader f424f;

    /* renamed from: g */
    public final String f425g;

    /* renamed from: h */
    public final List<wx> f426h;

    /* renamed from: i */
    public final sy f427i;

    /* renamed from: j */
    public final boolean f428j;

    /* renamed from: k */
    public final NotThreadSafeBridgeIdleDebugListener f429k;

    /* renamed from: l */
    public final Object f430l = new Object();

    /* renamed from: m */
    public volatile ReactContext f431m;

    /* renamed from: n */
    public final Context f432n;

    /* renamed from: o */
    public Gy f433o;

    /* renamed from: p */
    public Activity f434p;

    /* renamed from: q */
    public final Collection<ReactInstanceEventListener> f435q = Collections.synchronizedSet(new HashSet());

    /* renamed from: r */
    public volatile boolean f436r = false;

    /* renamed from: s */
    public volatile Boolean f437s = false;

    /* renamed from: t */
    public final cx f438t;

    /* renamed from: u */
    public final NativeModuleCallExceptionHandler f439u;

    /* renamed from: v */
    public final JSIModulePackage f440v;

    /* renamed from: w */
    public List<ViewManager> f441w;

    /* compiled from: PG */
    public interface ReactInstanceEventListener {
        void onReactContextInitialized(ReactContext reactContext);
    }

    public ReactInstanceManager(Context context, Activity activity, Gy gy, JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader, String str, List list, boolean z, NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, cB cBVar, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, boolean z2, int i, int i2, JSIModulePackage jSIModulePackage, Map map) {
        my myVar;
        Context context2 = context;
        boolean z3 = z;
        SoLoader.m688a(context2, false);
        rA.b(context);
        this.f432n = context2;
        this.f434p = activity;
        this.f433o = gy;
        this.f423e = javaScriptExecutorFactory;
        this.f424f = jSBundleLoader;
        this.f425g = str;
        this.f426h = new ArrayList();
        this.f428j = z3;
        Trace.beginSection("ReactInstanceManager.initDevSupportManager");
        jx jxVar = new jx(this);
        String str2 = this.f425g;
        if (!z3) {
            myVar = new my();
        } else {
            try {
                myVar = (sy) Class.forName("com.facebook.react.devsupport" + "." + "DevSupportManagerImpl").getConstructor(new Class[]{Context.class, py.class, String.class, Boolean.TYPE, RedBoxHandler.class, ry.class, Integer.TYPE, Map.class}).newInstance(new Object[]{context2, jxVar, str2, true, null, null, Integer.valueOf(i), map});
            } catch (Exception e) {
                throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e);
            }
        }
        this.f427i = myVar;
        Trace.endSection();
        this.f429k = notThreadSafeBridgeIdleDebugListener;
        this.f420b = lifecycleState;
        this.f438t = new cx(context2);
        this.f439u = nativeModuleCallExceptionHandler;
        synchronized (this.f426h) {
            fr.a.a(gr.a, "RNCore: Use Split Packages");
            this.f426h.add(new Uw(this, new ix(this), z2, i2));
            if (this.f428j) {
                this.f426h.add(new Xw());
            }
            this.f426h.addAll(list);
        }
        this.f440v = jSIModulePackage;
        if (ReactChoreographer.f == null) {
            ReactChoreographer.f = new ReactChoreographer();
        }
        if (this.f428j) {
            this.f427i.e();
        }
    }

    /* renamed from: i */
    public static ux m420i() {
        return new ux();
    }

    /* renamed from: a */
    public final void mo613a(wx wxVar, dx dxVar) {
        dF dFVar = cF.a;
        dFVar.a("className", wxVar.getClass().getSimpleName());
        dF dFVar2 = dFVar;
        boolean z = wxVar instanceof zx;
        if (z) {
            Uw uw = (Uw) wxVar;
            ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START);
        }
        dxVar.a(wxVar);
        if (z) {
            Uw uw2 = (Uw) wxVar;
            ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END);
        }
        cF.a.a();
    }

    /* renamed from: b */
    public final void mo617b(ReactRootView reactRootView) {
        WritableMap writableMap;
        Trace.beginSection("attachRootViewToInstance");
        UIManager a = dB.a(this.f431m, reactRootView.mo646k());
        Bundle e = reactRootView.mo639e();
        if (e == null) {
            writableMap = new WritableNativeMap();
        } else {
            writableMap = Arguments.fromBundle(e);
        }
        int addRootView = a.addRootView(reactRootView, writableMap, reactRootView.mo642g());
        reactRootView.setRootViewTag(addRootView);
        reactRootView.mo648m();
        UiThreadUtil.runOnUiThread(new sx(this, addRootView, reactRootView));
        Trace.endSection();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new java.util.HashSet();
        r2 = r6.f426h.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r2.hasNext() == false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r4 = cF.a;
        r4.a("Package", r2.next().getClass().getSimpleName());
        r4 = r4;
        cF.a.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        android.os.Trace.endSection();
        r2 = new java.util.ArrayList(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r1 = r6.f426h;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> mo619c() {
        /*
            r6 = this;
            java.lang.String r0 = "ReactInstanceManager.getViewManagerNames"
            android.os.Trace.beginSection(r0)
            java.lang.Object r0 = r6.f430l
            monitor-enter(r0)
            com.facebook.react.bridge.ReactContext r1 = r6.mo615b()     // Catch:{ all -> 0x0059 }
            com.facebook.react.bridge.ReactApplicationContext r1 = (com.facebook.react.bridge.ReactApplicationContext) r1     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0056
            boolean r1 = r1.hasActiveCatalystInstance()     // Catch:{ all -> 0x0059 }
            if (r1 != 0) goto L_0x0017
            goto L_0x0056
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            java.util.List<wx> r1 = r6.f426h
            monitor-enter(r1)
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x0053 }
            r0.<init>()     // Catch:{ all -> 0x0053 }
            java.util.List<wx> r2 = r6.f426h     // Catch:{ all -> 0x0053 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0053 }
        L_0x0026:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0049
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0053 }
            wx r3 = (wx) r3     // Catch:{ all -> 0x0053 }
            cF$a r4 = cF.a     // Catch:{ all -> 0x0053 }
            java.lang.String r5 = "Package"
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x0053 }
            java.lang.String r3 = r3.getSimpleName()     // Catch:{ all -> 0x0053 }
            r4.a(r5, r3)     // Catch:{ all -> 0x0053 }
            dF r4 = (dF) r4     // Catch:{ all -> 0x0053 }
            cF$a r3 = cF.a     // Catch:{ all -> 0x0053 }
            r3.a()     // Catch:{ all -> 0x0053 }
            goto L_0x0026
        L_0x0049:
            android.os.Trace.endSection()     // Catch:{ all -> 0x0053 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0053 }
            r2.<init>(r0)     // Catch:{ all -> 0x0053 }
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            return r2
        L_0x0053:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0053 }
            throw r0
        L_0x0056:
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            return r1
        L_0x0059:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0059 }
            goto L_0x005d
        L_0x005c:
            throw r1
        L_0x005d:
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManager.mo619c():java.util.List");
    }

    /* renamed from: d */
    public final void mo621d() {
        UiThreadUtil.assertOnUiThread();
        Gy gy = this.f433o;
        if (gy != null) {
            gy.g();
        }
    }

    /* renamed from: e */
    public final synchronized void mo622e() {
        if (this.f420b == LifecycleState.RESUMED) {
            mo614a(true);
        }
    }

    /* renamed from: f */
    public final synchronized void mo623f() {
        ReactContext b = mo615b();
        if (b != null) {
            if (this.f420b == LifecycleState.RESUMED) {
                b.onHostPause();
                this.f420b = LifecycleState.BEFORE_RESUME;
            }
            if (this.f420b == LifecycleState.BEFORE_RESUME) {
                b.onHostDestroy();
            }
        }
        this.f420b = LifecycleState.BEFORE_CREATE;
    }

    /* renamed from: g */
    public final synchronized void mo624g() {
        ReactContext b = mo615b();
        if (b != null) {
            if (this.f420b == LifecycleState.BEFORE_CREATE) {
                b.onHostResume(this.f434p);
                b.onHostPause();
            } else if (this.f420b == LifecycleState.RESUMED) {
                b.onHostPause();
            }
        }
        this.f420b = LifecycleState.BEFORE_RESUME;
    }

    /* renamed from: h */
    public void mo625h() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.f431m;
        if (reactContext == null) {
            pq.c("ReactNative", "Instance detached from instance manager");
            mo621d();
            return;
        }
        reactContext.getNativeModule(DeviceEventManagerModule.class).emitHardwareBackPressed();
    }

    /* renamed from: b */
    public void mo616b(Activity activity) {
        Kw.a(this.f434p);
        boolean z = activity == this.f434p;
        StringBuilder a = Eo.a("Pausing an activity that is not the current activity, this is incorrect! Current activity: ");
        a.append(this.f434p.getClass().getSimpleName());
        a.append(" Paused activity: ");
        a.append(activity.getClass().getSimpleName());
        Kw.a(z, a.toString());
        UiThreadUtil.assertOnUiThread();
        this.f433o = null;
        if (this.f428j) {
            this.f427i.a(false);
        }
        mo624g();
    }

    /* renamed from: a */
    public void mo604a() {
        Kw.a(!this.f436r, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.f436r = true;
        fr.a.a(gr.a, "RNCore: recreateReactContextInBackground");
        UiThreadUtil.assertOnUiThread();
        if (!this.f428j || this.f425g == null) {
            fr.a.a(gr.a, "RNCore: load from BundleLoader");
            mo610a(this.f423e, this.f424f);
            return;
        }
        this.f427i.a();
        this.f427i.d();
        if (this.f424f == null) {
            this.f427i.c();
            return;
        }
        this.f427i.a(new kx(this));
    }

    /* renamed from: c */
    public void mo620c(ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.f419a) {
            if (this.f419a.contains(reactRootView)) {
                ReactContext b = mo615b();
                this.f419a.remove(reactRootView);
                if (b != null && b.hasActiveCatalystInstance()) {
                    CatalystInstance catalystInstance = b.getCatalystInstance();
                    UiThreadUtil.assertOnUiThread();
                    if (reactRootView.mo646k() == 2) {
                        catalystInstance.getJSModule(ReactFabric.class).unmountComponentAtNode(reactRootView.getId());
                    } else {
                        catalystInstance.getJSModule(AppRegistry.class).unmountApplicationComponentAtRootTag(reactRootView.getId());
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public ReactContext mo615b() {
        ReactContext reactContext;
        synchronized (this.f430l) {
            reactContext = this.f431m;
        }
        return reactContext;
    }

    /* renamed from: b */
    public final void mo618b(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Trace.beginSection("setupReactContext");
        synchronized (this.f419a) {
            synchronized (this.f430l) {
                Kw.a(reactApplicationContext);
                this.f431m = reactApplicationContext;
            }
            CatalystInstance catalystInstance = reactApplicationContext.getCatalystInstance();
            Kw.a(catalystInstance);
            CatalystInstance catalystInstance2 = catalystInstance;
            catalystInstance2.initialize();
            this.f427i.a(reactApplicationContext);
            this.f438t.a.add(catalystInstance2);
            mo622e();
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
            for (ReactRootView b : this.f419a) {
                mo617b(b);
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
        }
        UiThreadUtil.runOnUiThread(new px(this, (ReactInstanceEventListener[]) this.f435q.toArray(new ReactInstanceEventListener[this.f435q.size()]), reactApplicationContext));
        Trace.endSection();
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        reactApplicationContext.runOnJSQueueThread(new qx(this));
        reactApplicationContext.runOnNativeModulesQueueThread(new rx(this));
    }

    /* renamed from: a */
    public void mo608a(Intent intent) {
        UiThreadUtil.assertOnUiThread();
        ReactContext b = mo615b();
        if (b == null) {
            pq.c("ReactNative", "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            b.getNativeModule(DeviceEventManagerModule.class).emitNewIntentReceived(data);
        }
        b.onNewIntent(this.f434p, intent);
    }

    /* renamed from: a */
    public void mo607a(Activity activity, Gy gy) {
        UiThreadUtil.assertOnUiThread();
        this.f433o = gy;
        UiThreadUtil.assertOnUiThread();
        this.f434p = activity;
        if (this.f428j) {
            View decorView = this.f434p.getWindow().getDecorView();
            if (!I9.q(decorView)) {
                decorView.addOnAttachStateChangeListener(new lx(this, decorView));
            } else {
                this.f427i.a(true);
            }
        }
        mo614a(false);
    }

    /* renamed from: a */
    public void mo605a(Activity activity) {
        if (activity == this.f434p) {
            UiThreadUtil.assertOnUiThread();
            if (this.f428j) {
                this.f427i.a(false);
            }
            mo623f();
            this.f434p = null;
        }
    }

    /* renamed from: a */
    public final synchronized void mo614a(boolean z) {
        ReactContext b = mo615b();
        if (b != null && (z || this.f420b == LifecycleState.BEFORE_RESUME || this.f420b == LifecycleState.BEFORE_CREATE)) {
            b.onHostResume(this.f434p);
        }
        this.f420b = LifecycleState.RESUMED;
    }

    /* renamed from: a */
    public void mo606a(Activity activity, int i, int i2, Intent intent) {
        ReactContext b = mo615b();
        if (b != null) {
            b.onActivityResult(activity, i, i2, intent);
        }
    }

    /* renamed from: a */
    public void mo609a(ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        this.f419a.add(reactRootView);
        reactRootView.removeAllViews();
        reactRootView.setId(-1);
        ReactContext b = mo615b();
        if (this.f422d == null && b != null) {
            mo617b(reactRootView);
        }
    }

    /* renamed from: a */
    public List<ViewManager> mo603a(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> list;
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Trace.beginSection("createAllViewManagers");
        try {
            if (this.f441w == null) {
                synchronized (this.f426h) {
                    if (this.f441w == null) {
                        this.f441w = new ArrayList();
                        for (wx a : this.f426h) {
                            this.f441w.addAll(a.a(reactApplicationContext));
                        }
                        list = this.f441w;
                    }
                }
                Trace.endSection();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
                return list;
            }
            list = this.f441w;
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
            return list;
        } catch (Throwable th) {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r4 = r3.f426h.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r4.hasNext() == false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r2 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r0 = r3.f426h;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager mo602a(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r4 = r3.f430l
            monitor-enter(r4)
            com.facebook.react.bridge.ReactContext r0 = r3.mo615b()     // Catch:{ all -> 0x0031 }
            com.facebook.react.bridge.ReactApplicationContext r0 = (com.facebook.react.bridge.ReactApplicationContext) r0     // Catch:{ all -> 0x0031 }
            r1 = 0
            if (r0 == 0) goto L_0x002f
            boolean r0 = r0.hasActiveCatalystInstance()     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0013
            goto L_0x002f
        L_0x0013:
            monitor-exit(r4)     // Catch:{ all -> 0x0031 }
            java.util.List<wx> r0 = r3.f426h
            monitor-enter(r0)
            java.util.List<wx> r4 = r3.f426h     // Catch:{ all -> 0x002c }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x002c }
        L_0x001d:
            boolean r2 = r4.hasNext()     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x002a
            java.lang.Object r2 = r4.next()     // Catch:{ all -> 0x002c }
            wx r2 = (wx) r2     // Catch:{ all -> 0x002c }
            goto L_0x001d
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r1
        L_0x002c:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r4
        L_0x002f:
            monitor-exit(r4)     // Catch:{ all -> 0x0031 }
            return r1
        L_0x0031:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0034:
            throw r0
        L_0x0035:
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManager.mo602a(java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    /* renamed from: a */
    public final void mo610a(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
        UiThreadUtil.assertOnUiThread();
        tx txVar = new tx(this, javaScriptExecutorFactory, jSBundleLoader);
        if (this.f422d == null) {
            mo612a(txVar);
        } else {
            this.f421c = txVar;
        }
    }

    /* renamed from: a */
    public final void mo612a(tx txVar) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.f419a) {
            synchronized (this.f430l) {
                if (this.f431m != null) {
                    mo611a(this.f431m);
                    this.f431m = null;
                }
            }
        }
        this.f422d = new Thread((ThreadGroup) null, new ox(this, txVar), "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.f422d.start();
    }

    /* renamed from: a */
    public final void mo611a(ReactContext reactContext) {
        UiThreadUtil.assertOnUiThread();
        if (this.f420b == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.f419a) {
            for (ReactRootView next : this.f419a) {
                next.removeAllViews();
                next.setId(-1);
            }
        }
        reactContext.destroy();
        this.f427i.b(reactContext);
        cx cxVar = this.f438t;
        cxVar.a.remove(reactContext.getCatalystInstance());
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final ReactApplicationContext mo601a(JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        ReactApplicationContext reactApplicationContext = new ReactApplicationContext(this.f432n);
        sy syVar = this.f439u;
        if (syVar == null) {
            syVar = this.f427i;
        }
        reactApplicationContext.setNativeModuleCallExceptionHandler(syVar);
        CatalystInstanceImpl.Builder nativeModuleCallExceptionHandler = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(mo600a(reactApplicationContext, this.f426h, false)).setJSBundleLoader(jSBundleLoader).setNativeModuleCallExceptionHandler(syVar);
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
        Trace.beginSection("createCatalystInstance");
        try {
            CatalystInstanceImpl build = nativeModuleCallExceptionHandler.build();
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            JSIModulePackage jSIModulePackage = this.f440v;
            if (jSIModulePackage != null) {
                build.addJSIModules(jSIModulePackage.getJSIModules(reactApplicationContext, build.getJavaScriptContextHolder()));
            }
            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.f429k;
            if (notThreadSafeBridgeIdleDebugListener != null) {
                build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
            }
            ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
            Trace.beginSection("runJSBundle");
            build.runJSBundle();
            Trace.endSection();
            reactApplicationContext.initializeWithInstance(build);
            return reactApplicationContext;
        } catch (Throwable th) {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            throw th;
        }
    }

    /* renamed from: a */
    public final NativeModuleRegistry mo600a(ReactApplicationContext reactApplicationContext, List<wx> list, boolean z) {
        dx dxVar = new dx(reactApplicationContext, this);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (this.f426h) {
            for (wx next : list) {
                if (!z || !this.f426h.contains(next)) {
                    Trace.beginSection("createAndProcessCustomReactPackage");
                    if (z) {
                        try {
                            this.f426h.add(next);
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    }
                    mo613a(next, dxVar);
                    Trace.endSection();
                }
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        Trace.beginSection("buildNativeModuleRegistry");
        try {
            return new NativeModuleRegistry(dxVar.a, dxVar.c);
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
        }
    }
}
