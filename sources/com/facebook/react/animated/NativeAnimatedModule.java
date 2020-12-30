package com.facebook.react.animated;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Ay(name = "NativeAnimatedModule")
/* compiled from: PG */
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, hB {
    public static final String NAME = "NativeAnimatedModule";
    public final tA mAnimatedFrameCallback;
    public Qx mNodesManager;
    public ArrayList<C0101v> mOperations = new ArrayList<>();
    public ArrayList<C0101v> mPreOperations = new ArrayList<>();
    public final ReactChoreographer mReactChoreographer = ReactChoreographer.b();

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$a */
    /* compiled from: PG */
    public class C0080a implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f464a;

        /* renamed from: b */
        public final /* synthetic */ double f465b;

        public C0080a(NativeAnimatedModule nativeAnimatedModule, int i, double d) {
            this.f464a = i;
            this.f465b = d;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f464a;
            double d = this.f465b;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            dyVar.f = d;
            qx.c.put(i, dyVar);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$b */
    /* compiled from: PG */
    public class C0081b implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f466a;

        public C0081b(NativeAnimatedModule nativeAnimatedModule, int i) {
            this.f466a = i;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f466a;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            dy dyVar2 = dyVar;
            dyVar2.e += dyVar2.f;
            dyVar2.f = 0.0d;
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$c */
    /* compiled from: PG */
    public class C0082c implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f467a;

        public C0082c(NativeAnimatedModule nativeAnimatedModule, int i) {
            this.f467a = i;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f467a;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            dy dyVar2 = dyVar;
            dyVar2.f += dyVar2.e;
            dyVar2.e = 0.0d;
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$d */
    /* compiled from: PG */
    public class C0083d implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f468a;

        /* renamed from: b */
        public final /* synthetic */ int f469b;

        /* renamed from: c */
        public final /* synthetic */ ReadableMap f470c;

        /* renamed from: d */
        public final /* synthetic */ Callback f471d;

        public C0083d(NativeAnimatedModule nativeAnimatedModule, int i, int i2, ReadableMap readableMap, Callback callback) {
            this.f468a = i;
            this.f469b = i2;
            this.f470c = readableMap;
            this.f471d = callback;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            qx.a(this.f468a, this.f469b, this.f470c, this.f471d);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$e */
    /* compiled from: PG */
    public class C0084e implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f472a;

        public C0084e(NativeAnimatedModule nativeAnimatedModule, int i) {
            this.f472a = i;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f472a;
            for (int i2 = 0; i2 < qx.b.size(); i2++) {
                Hx hx = (Hx) qx.b.valueAt(i2);
                if (hx.d == i) {
                    if (hx.c != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", false);
                        hx.c.invoke(createMap);
                    }
                    qx.b.removeAt(i2);
                    return;
                }
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$f */
    /* compiled from: PG */
    public class C0085f implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f473a;

        /* renamed from: b */
        public final /* synthetic */ int f474b;

        public C0085f(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            this.f473a = i;
            this.f474b = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f473a;
            int i2 = this.f474b;
            Fx fx = (Fx) qx.a.get(i);
            if (fx != null) {
                Fx fx2 = (Fx) qx.a.get(i2);
                if (fx2 != null) {
                    if (fx.a == null) {
                        fx.a = new ArrayList(1);
                    }
                    List list = fx.a;
                    Kw.a(list);
                    list.add(fx2);
                    fx2.a(fx);
                    qx.c.put(i2, fx2);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i2, " does not exists"));
            }
            throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists"));
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$g */
    /* compiled from: PG */
    public class C0086g implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f475a;

        /* renamed from: b */
        public final /* synthetic */ int f476b;

        public C0086g(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            this.f475a = i;
            this.f476b = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f475a;
            int i2 = this.f476b;
            Fx fx = (Fx) qx.a.get(i);
            if (fx != null) {
                Fx fx2 = (Fx) qx.a.get(i2);
                if (fx2 != null) {
                    if (fx.a != null) {
                        fx2.b(fx);
                        fx.a.remove(fx2);
                    }
                    qx.c.put(i2, fx2);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i2, " does not exists"));
            }
            throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists"));
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$h */
    /* compiled from: PG */
    public class C0087h implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f477a;

        /* renamed from: b */
        public final /* synthetic */ int f478b;

        public C0087h(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            this.f477a = i;
            this.f478b = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f477a;
            int i2 = this.f478b;
            Rx rx = (Fx) qx.a.get(i);
            if (rx == null) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists"));
            } else if (rx instanceof Rx) {
                Rx rx2 = rx;
                if (rx2.e == -1) {
                    rx2.e = i2;
                    qx.c.put(i, rx);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Eo.a(Eo.a("Animated node "), rx2.d, " is already attached to a view"));
            } else {
                StringBuilder a = Eo.a("Animated node connected to view should beof type ");
                a.append(Rx.class.getName());
                throw new JSApplicationIllegalArgumentException(a.toString());
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$i */
    /* compiled from: PG */
    public class C0088i implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f479a;

        /* renamed from: b */
        public final /* synthetic */ int f480b;

        public C0088i(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            this.f479a = i;
            this.f480b = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            Rx rx = (Fx) qx.a.get(this.f479a);
            if (rx != null) {
                if (rx instanceof Rx) {
                    Rx rx2 = rx;
                    ReadableMapKeySetIterator keySetIterator = rx2.i.keySetIterator();
                    while (keySetIterator.hasNextKey()) {
                        rx2.i.putNull(keySetIterator.nextKey());
                    }
                    rx2.g.mo1107a(rx2.e, rx2.j);
                    return;
                }
                StringBuilder a = Eo.a("Animated node connected to view should beof type ");
                a.append(Rx.class.getName());
                throw new JSApplicationIllegalArgumentException(a.toString());
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$j */
    /* compiled from: PG */
    public class C0089j implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f481a;

        /* renamed from: b */
        public final /* synthetic */ int f482b;

        public C0089j(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            this.f481a = i;
            this.f482b = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f481a;
            int i2 = this.f482b;
            Rx rx = (Fx) qx.a.get(i);
            if (rx == null) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists"));
            } else if (rx instanceof Rx) {
                Rx rx2 = rx;
                if (rx2.e == i2) {
                    rx2.e = -1;
                    return;
                }
                throw new JSApplicationIllegalArgumentException("Attempting to disconnect view that has not been connected with the given animated node");
            } else {
                StringBuilder a = Eo.a("Animated node connected to view should beof type ");
                a.append(Rx.class.getName());
                throw new JSApplicationIllegalArgumentException(a.toString());
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$k */
    /* compiled from: PG */
    public class C0090k extends tA {
        public C0090k(ReactContext reactContext) {
            super(reactContext);
        }

        /* renamed from: b */
        public void mo692b(long j) {
            Qx access$000 = NativeAnimatedModule.this.getNodesManager();
            if (access$000.b.size() > 0 || access$000.c.size() > 0) {
                UiThreadUtil.assertOnUiThread();
                for (int i = 0; i < access$000.c.size(); i++) {
                    access$000.h.add((Fx) access$000.c.valueAt(i));
                }
                access$000.c.clear();
                boolean z = false;
                for (int i2 = 0; i2 < access$000.b.size(); i2++) {
                    Hx hx = (Hx) access$000.b.valueAt(i2);
                    hx.a(j);
                    access$000.h.add(hx.b);
                    if (hx.a) {
                        z = true;
                    }
                }
                access$000.a(access$000.h);
                access$000.h.clear();
                if (z) {
                    for (int size = access$000.b.size() - 1; size >= 0; size--) {
                        Hx hx2 = (Hx) access$000.b.valueAt(size);
                        if (hx2.a) {
                            if (hx2.c != null) {
                                WritableMap createMap = Arguments.createMap();
                                createMap.putBoolean("finished", true);
                                hx2.c.invoke(createMap);
                            }
                            access$000.b.removeAt(size);
                        }
                    }
                }
            }
            ReactChoreographer access$200 = NativeAnimatedModule.this.mReactChoreographer;
            Kw.a(access$200);
            access$200.a(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.this.mAnimatedFrameCallback);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$l */
    /* compiled from: PG */
    public class C0091l implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f484a;

        /* renamed from: b */
        public final /* synthetic */ String f485b;

        /* renamed from: c */
        public final /* synthetic */ ReadableMap f486c;

        public C0091l(NativeAnimatedModule nativeAnimatedModule, int i, String str, ReadableMap readableMap) {
            this.f484a = i;
            this.f485b = str;
            this.f486c = readableMap;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            qx.a(this.f484a, this.f485b, this.f486c);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$m */
    /* compiled from: PG */
    public class C0092m implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f487a;

        /* renamed from: b */
        public final /* synthetic */ String f488b;

        /* renamed from: c */
        public final /* synthetic */ int f489c;

        public C0092m(NativeAnimatedModule nativeAnimatedModule, int i, String str, int i2) {
            this.f487a = i;
            this.f488b = str;
            this.f489c = i2;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            qx.a(this.f487a, this.f488b, this.f489c);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$n */
    /* compiled from: PG */
    public class C0093n implements aB {

        /* renamed from: a */
        public final /* synthetic */ ArrayList f490a;

        public C0093n(ArrayList arrayList) {
            this.f490a = arrayList;
        }

        /* renamed from: a */
        public void mo693a(CA ca) {
            Qx access$000 = NativeAnimatedModule.this.getNodesManager();
            Iterator it = this.f490a.iterator();
            while (it.hasNext()) {
                ((C0101v) it.next()).mo691a(access$000);
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$o */
    /* compiled from: PG */
    public class C0094o implements aB {

        /* renamed from: a */
        public final /* synthetic */ ArrayList f492a;

        public C0094o(ArrayList arrayList) {
            this.f492a = arrayList;
        }

        /* renamed from: a */
        public void mo694a(CA ca) {
            Qx access$000 = NativeAnimatedModule.this.getNodesManager();
            Iterator it = this.f492a.iterator();
            while (it.hasNext()) {
                ((C0101v) it.next()).mo691a(access$000);
            }
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$p */
    /* compiled from: PG */
    public class C0095p implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f494a;

        /* renamed from: b */
        public final /* synthetic */ ReadableMap f495b;

        public C0095p(NativeAnimatedModule nativeAnimatedModule, int i, ReadableMap readableMap) {
            this.f494a = i;
            this.f495b = readableMap;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            Vx vx;
            int i = this.f494a;
            ReadableMap readableMap = this.f495b;
            if (qx.a.get(i) == null) {
                String string = readableMap.getString("type");
                if ("style".equals(string)) {
                    vx = new Vx(readableMap, qx);
                } else if ("value".equals(string)) {
                    vx = new dy(readableMap);
                } else if ("props".equals(string)) {
                    vx = new Rx(readableMap, qx, qx.f);
                } else if ("interpolation".equals(string)) {
                    vx = new Mx(readableMap);
                } else if ("addition".equals(string)) {
                    vx = new Ex(readableMap, qx);
                } else if ("subtraction".equals(string)) {
                    vx = new Wx(readableMap, qx);
                } else if ("division".equals(string)) {
                    vx = new Kx(readableMap, qx);
                } else if ("multiplication".equals(string)) {
                    vx = new Ox(readableMap, qx);
                } else if ("modulus".equals(string)) {
                    vx = new Nx(readableMap, qx);
                } else if ("diffclamp".equals(string)) {
                    vx = new Jx(readableMap, qx);
                } else if (BaseViewManager.PROP_TRANSFORM.equals(string)) {
                    vx = new cy(readableMap, qx);
                } else if ("tracking".equals(string)) {
                    vx = new Xx(readableMap, qx);
                } else {
                    throw new JSApplicationIllegalArgumentException(Eo.a("Unsupported node type: ", string));
                }
                vx.d = i;
                qx.a.put(i, vx);
                qx.c.put(i, vx);
                return;
            }
            throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " already exists"));
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$q */
    /* compiled from: PG */
    public class C0096q implements Gx {

        /* renamed from: a */
        public final /* synthetic */ int f496a;

        public C0096q(int i) {
            this.f496a = i;
        }

        /* renamed from: a */
        public void mo695a(double d) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("tag", this.f496a);
            createMap.putDouble("value", d);
            NativeAnimatedModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onAnimatedValueUpdate", createMap);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$r */
    /* compiled from: PG */
    public class C0097r implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f498a;

        /* renamed from: b */
        public final /* synthetic */ Gx f499b;

        public C0097r(NativeAnimatedModule nativeAnimatedModule, int i, Gx gx) {
            this.f498a = i;
            this.f499b = gx;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f498a;
            Gx gx = this.f499b;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            dyVar.a(gx);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$s */
    /* compiled from: PG */
    public class C0098s implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f500a;

        public C0098s(NativeAnimatedModule nativeAnimatedModule, int i) {
            this.f500a = i;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f500a;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            dyVar.a((Gx) null);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$t */
    /* compiled from: PG */
    public class C0099t implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f501a;

        public C0099t(NativeAnimatedModule nativeAnimatedModule, int i) {
            this.f501a = i;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f501a;
            qx.a.remove(i);
            qx.c.remove(i);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$u */
    /* compiled from: PG */
    public class C0100u implements C0101v {

        /* renamed from: a */
        public final /* synthetic */ int f502a;

        /* renamed from: b */
        public final /* synthetic */ double f503b;

        public C0100u(NativeAnimatedModule nativeAnimatedModule, int i, double d) {
            this.f502a = i;
            this.f503b = d;
        }

        /* renamed from: a */
        public void mo691a(Qx qx) {
            int i = this.f502a;
            double d = this.f503b;
            dy dyVar = (Fx) qx.a.get(i);
            if (dyVar == null || !(dyVar instanceof dy)) {
                throw new JSApplicationIllegalArgumentException(Eo.a("Animated node with tag ", i, " does not exists or is not a 'value' node"));
            }
            qx.a(dyVar);
            dyVar.e = d;
            qx.c.put(i, dyVar);
        }
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$v */
    /* compiled from: PG */
    public interface C0101v {
        /* renamed from: a */
        void mo691a(Qx qx);
    }

    public NativeAnimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAnimatedFrameCallback = new C0090k(reactApplicationContext);
    }

    private void clearFrameCallback() {
        ReactChoreographer reactChoreographer = this.mReactChoreographer;
        Kw.a(reactChoreographer);
        reactChoreographer.b(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ReactChoreographer reactChoreographer = this.mReactChoreographer;
        Kw.a(reactChoreographer);
        reactChoreographer.a(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    /* access modifiers changed from: private */
    public Qx getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new Qx((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class));
        }
        return this.mNodesManager;
    }

    @ReactMethod
    public void addAnimatedEventToView(int i, String str, ReadableMap readableMap) {
        this.mOperations.add(new C0091l(this, i, str, readableMap));
    }

    @ReactMethod
    public void connectAnimatedNodeToView(int i, int i2) {
        this.mOperations.add(new C0087h(this, i, i2));
    }

    @ReactMethod
    public void connectAnimatedNodes(int i, int i2) {
        this.mOperations.add(new C0085f(this, i, i2));
    }

    @ReactMethod
    public void createAnimatedNode(int i, ReadableMap readableMap) {
        this.mOperations.add(new C0095p(this, i, readableMap));
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(int i, int i2) {
        this.mPreOperations.add(new C0088i(this, i, i2));
        this.mOperations.add(new C0089j(this, i, i2));
    }

    @ReactMethod
    public void disconnectAnimatedNodes(int i, int i2) {
        this.mOperations.add(new C0086g(this, i, i2));
    }

    @ReactMethod
    public void dropAnimatedNode(int i) {
        this.mOperations.add(new C0099t(this, i));
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(int i) {
        this.mOperations.add(new C0082c(this, i));
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(int i) {
        this.mOperations.add(new C0081b(this, i));
    }

    public String getName() {
        return NAME;
    }

    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.addLifecycleEventListener(this);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIManagerListener(this);
    }

    public void onHostDestroy() {
    }

    public void onHostPause() {
        clearFrameCallback();
    }

    public void onHostResume() {
        enqueueFrameCallback();
    }

    @ReactMethod
    public void removeAnimatedEventFromView(int i, String str, int i2) {
        this.mOperations.add(new C0092m(this, i, str, i2));
    }

    @ReactMethod
    public void setAnimatedNodeOffset(int i, double d) {
        this.mOperations.add(new C0080a(this, i, d));
    }

    @ReactMethod
    public void setAnimatedNodeValue(int i, double d) {
        this.mOperations.add(new C0100u(this, i, d));
    }

    public void setNodesManager(Qx qx) {
        this.mNodesManager = qx;
    }

    @ReactMethod
    public void startAnimatingNode(int i, int i2, ReadableMap readableMap, Callback callback) {
        this.mOperations.add(new C0083d(this, i, i2, readableMap, callback));
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(int i) {
        this.mOperations.add(new C0097r(this, i, new C0096q(i)));
    }

    @ReactMethod
    public void stopAnimation(int i) {
        this.mOperations.add(new C0084e(this, i));
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(int i) {
        this.mOperations.add(new C0098s(this, i));
    }

    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (!this.mOperations.isEmpty() || !this.mPreOperations.isEmpty()) {
            ArrayList<C0101v> arrayList = this.mPreOperations;
            ArrayList<C0101v> arrayList2 = this.mOperations;
            this.mPreOperations = new ArrayList<>();
            this.mOperations = new ArrayList<>();
            uIManagerModule.prependUIBlock(new C0093n(arrayList));
            uIManagerModule.addUIBlock(new C0094o(arrayList2));
        }
    }
}
