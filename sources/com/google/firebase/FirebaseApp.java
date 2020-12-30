package com.google.firebase;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.protobuf.ByteString;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
public class FirebaseApp {

    /* renamed from: i */
    public static final List<String> f888i = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});

    /* renamed from: j */
    public static final List<String> f889j = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");

    /* renamed from: k */
    public static final List<String> f890k = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});

    /* renamed from: l */
    public static final List<String> f891l = Arrays.asList(new String[0]);

    /* renamed from: m */
    public static final Set<String> f892m = Collections.emptySet();

    /* renamed from: n */
    public static final Object f893n = new Object();

    /* renamed from: o */
    public static final Executor f894o = new sI((byte) 0);

    /* renamed from: p */
    public static final Map<String, FirebaseApp> f895p = new ArrayMap();

    /* renamed from: a */
    public final Context f896a;

    /* renamed from: b */
    public final String f897b;

    /* renamed from: c */
    public final uI f898c;

    /* renamed from: d */
    public final CI f899d;

    /* renamed from: e */
    public final SharedPreferences f900e;

    /* renamed from: f */
    public final AtomicBoolean f901f = new AtomicBoolean(false);

    /* renamed from: g */
    public final AtomicBoolean f902g = new AtomicBoolean();

    /* renamed from: h */
    public final List<BackgroundStateChangeListener> f903h;

    @KeepForSdk
    /* compiled from: PG */
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    /* compiled from: PG */
    public interface IdTokenListener {
        void onIdTokenChanged(EJ ej);
    }

    @KeepForSdk
    /* compiled from: PG */
    public interface IdTokenListenersCountChangedListener {
        void onListenerCountChanged(int i);
    }

    public FirebaseApp(Context context, String str, uI uIVar) {
        boolean z;
        ApplicationInfo applicationInfo;
        new CopyOnWriteArrayList();
        this.f903h = new CopyOnWriteArrayList();
        new CopyOnWriteArrayList();
        this.f896a = (Context) Preconditions.checkNotNull(context);
        this.f897b = Preconditions.checkNotEmpty(str);
        this.f898c = (uI) Preconditions.checkNotNull(uIVar);
        this.f900e = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        if (this.f900e.contains("firebase_automatic_data_collection_enabled")) {
            z = this.f900e.getBoolean("firebase_automatic_data_collection_enabled", true);
        } else {
            try {
                PackageManager packageManager = this.f896a.getPackageManager();
                if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.f896a.getPackageName(), ByteString.CONCATENATE_BY_COPY_SIZE)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_automatic_data_collection_enabled"))) {
                    z = applicationInfo.metaData.getBoolean("firebase_automatic_data_collection_enabled");
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            z = true;
        }
        new AtomicBoolean(z);
        List<String> a = new BI((byte) 0).a(context);
        ArrayList arrayList = new ArrayList();
        for (String str2 : a) {
            try {
                Class<?> cls = Class.forName(str2);
                if (!yI.class.isAssignableFrom(cls)) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", new Object[]{str2, "com.google.firebase.components.ComponentRegistrar"}));
                } else {
                    arrayList.add(cls.newInstance());
                }
            } catch (ClassNotFoundException e) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", new Object[]{str2}), e);
            } catch (IllegalAccessException e2) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{str2}), e2);
            } catch (InstantiationException e3) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{str2}), e3);
            }
        }
        this.f899d = new CI(f894o, arrayList, new vI[]{vI.a(context, Context.class, new Class[0]), vI.a(this, FirebaseApp.class, new Class[0]), vI.a(uIVar, uI.class, new Class[0])});
        KI ki = (KI) this.f899d.get(KI.class);
    }

    /* renamed from: d */
    public static FirebaseApp m799d() {
        FirebaseApp firebaseApp;
        synchronized (f893n) {
            firebaseApp = f895p.get("[DEFAULT]");
            if (firebaseApp == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        return firebaseApp;
    }

    /* renamed from: a */
    public boolean mo1996a() {
        mo1997b();
        return "[DEFAULT]".equals(this.f897b);
    }

    /* renamed from: b */
    public final void mo1997b() {
        Preconditions.checkState(!this.f902g.get(), "FirebaseApp was deleted");
    }

    /* renamed from: c */
    public final void mo1998c() {
        Class<FirebaseApp> cls = FirebaseApp.class;
        boolean e = U5.e(this.f896a);
        if (e) {
            Context context = this.f896a;
            if (tI.b.get() == null) {
                tI tIVar = new tI(context);
                if (tI.b.compareAndSet((Object) null, tIVar)) {
                    context.registerReceiver(tIVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        } else {
            CI ci = this.f899d;
            boolean a = mo1996a();
            for (vI vIVar : ci.a) {
                boolean z = false;
                if (!(vIVar.c == 1)) {
                    if (vIVar.c == 2) {
                        z = true;
                    }
                    if (z) {
                        if (!a) {
                        }
                    }
                }
                ci.get((Class) vIVar.a.iterator().next());
            }
            ci.c.a();
        }
        m797a(cls, this, f888i, e);
        if (mo1996a()) {
            m797a(cls, this, f889j, e);
            m797a(Context.class, this.f896a, f890k, e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        String str = this.f897b;
        FirebaseApp firebaseApp = (FirebaseApp) obj;
        firebaseApp.mo1997b();
        return str.equals(firebaseApp.f897b);
    }

    public int hashCode() {
        return this.f897b.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.f897b).add("options", this.f898c).toString();
    }

    /* renamed from: a */
    public static FirebaseApp m795a(Context context) {
        synchronized (f893n) {
            if (f895p.containsKey("[DEFAULT]")) {
                FirebaseApp d = m799d();
                return d;
            }
            uI a = uI.a(context);
            if (a == null) {
                return null;
            }
            FirebaseApp a2 = m796a(context, a, "[DEFAULT]");
            return a2;
        }
    }

    /* renamed from: a */
    public static void m798a(boolean z) {
        synchronized (f893n) {
            Iterator it = new ArrayList(f895p.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.f901f.get()) {
                    for (BackgroundStateChangeListener onBackgroundStateChanged : firebaseApp.f903h) {
                        onBackgroundStateChanged.onBackgroundStateChanged(z);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public <T> T mo1995a(Class<T> cls) {
        mo1997b();
        return this.f899d.get(cls);
    }

    /* renamed from: a */
    public static <T> void m797a(Class<T> cls, T t, Iterable<String> iterable, boolean z) {
        for (String next : iterable) {
            if (z) {
                try {
                    if (!f891l.contains(next)) {
                    }
                } catch (ClassNotFoundException unused) {
                    if (!f892m.contains(next)) {
                        Eo.f(next, " is not linked. Skipping initialization.");
                    } else {
                        throw new IllegalStateException(Eo.a(next, " is missing, but is required. Check if it has been removed by Proguard."));
                    }
                } catch (NoSuchMethodException unused2) {
                    throw new IllegalStateException(Eo.a(next, "#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e);
                } catch (IllegalAccessException e2) {
                    Log.wtf("FirebaseApp", "Failed to initialize " + next, e2);
                }
            }
            Method method = Class.forName(next).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke((Object) null, new Object[]{t});
            }
        }
    }

    /* renamed from: a */
    public static FirebaseApp m796a(Context context, uI uIVar, String str) {
        FirebaseApp firebaseApp;
        if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
            BackgroundDetector.initialize((Application) context.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new rI());
        }
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (f893n) {
            boolean z = !f895p.containsKey(trim);
            Preconditions.checkState(z, "FirebaseApp name " + trim + " already exists!");
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, trim, uIVar);
            f895p.put(trim, firebaseApp);
        }
        firebaseApp.mo1998c();
        return firebaseApp;
    }
}
