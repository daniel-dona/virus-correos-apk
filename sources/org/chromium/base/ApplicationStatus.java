package org.chromium.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class ApplicationStatus {

    /* renamed from: a */
    public static final Map<Activity, d> f1393a = Collections.synchronizedMap(new HashMap());

    /* renamed from: b */
    public static final e<Activity> f1394b = new e<>();
    @SuppressLint({"SupportAnnotationUsage"})

    /* renamed from: c */
    public static int f1395c = 0;
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: d */
    public static Activity f1396d;

    /* renamed from: e */
    public static ApplicationStateListener f1397e;

    /* renamed from: f */
    public static final ObserverList<ActivityStateListener> f1398f = new ObserverList<>();

    /* renamed from: g */
    public static final ObserverList<ApplicationStateListener> f1399g = new ObserverList<>();

    /* renamed from: h */
    public static final ObserverList<WindowFocusChangedListener> f1400h = new ObserverList<>();

    /* compiled from: PG */
    public interface ActivityStateListener {
        void onActivityStateChange(Activity activity, int i);
    }

    /* compiled from: PG */
    public interface ApplicationStateListener {
        void onApplicationStateChange(int i);
    }

    /* renamed from: a */
    public static void m1362a(Application application) {
        synchronized (f1393a) {
            f1395c = 4;
        }
        f1400h.mo7868a(new a());
        application.registerActivityLifecycleCallbacks(new b());
    }

    /* renamed from: b */
    public static List<Activity> m1366b() {
        ArrayList arrayList;
        synchronized (f1393a) {
            arrayList = new ArrayList(f1393a.keySet());
        }
        return arrayList;
    }

    /* renamed from: c */
    public static List<WeakReference<Activity>> m1367c() {
        return f1394b.a();
    }

    /* renamed from: d */
    public static boolean m1368d() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    /* renamed from: e */
    public static boolean m1369e() {
        return f1393a.isEmpty();
    }

    @CalledByNative
    public static int getStateForApplication() {
        int i;
        synchronized (f1393a) {
            i = f1395c;
        }
        return i;
    }

    public static native void nativeOnApplicationStateChange(int i);

    @CalledByNative
    public static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.m1461b((Runnable) new c());
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0085 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1361a(android.app.Activity r13, int r14) {
        /*
            if (r13 == 0) goto L_0x00dc
            android.app.Activity r0 = f1396d
            r1 = 2
            r2 = 3
            r3 = 1
            if (r0 == 0) goto L_0x000f
            if (r14 == r3) goto L_0x000f
            if (r14 == r2) goto L_0x000f
            if (r14 != r1) goto L_0x0011
        L_0x000f:
            f1396d = r13
        L_0x0011:
            int r0 = getStateForApplication()
            java.util.Map<android.app.Activity, org.chromium.base.ApplicationStatus$d> r4 = f1393a
            monitor-enter(r4)
            r5 = 0
            if (r14 != r3) goto L_0x0025
            java.util.Map<android.app.Activity, org.chromium.base.ApplicationStatus$d> r6 = f1393a     // Catch:{ all -> 0x00d9 }
            org.chromium.base.ApplicationStatus$d r7 = new org.chromium.base.ApplicationStatus$d     // Catch:{ all -> 0x00d9 }
            r7.<init>(r5)     // Catch:{ all -> 0x00d9 }
            r6.put(r13, r7)     // Catch:{ all -> 0x00d9 }
        L_0x0025:
            java.util.Map<android.app.Activity, org.chromium.base.ApplicationStatus$d> r6 = f1393a     // Catch:{ all -> 0x00d9 }
            java.lang.Object r6 = r6.get(r13)     // Catch:{ all -> 0x00d9 }
            org.chromium.base.ApplicationStatus$d r6 = (org.chromium.base.ApplicationStatus.d) r6     // Catch:{ all -> 0x00d9 }
            r6.a = r14     // Catch:{ all -> 0x00d9 }
            r7 = 6
            if (r14 != r7) goto L_0x003d
            java.util.Map<android.app.Activity, org.chromium.base.ApplicationStatus$d> r8 = f1393a     // Catch:{ all -> 0x00d9 }
            r8.remove(r13)     // Catch:{ all -> 0x00d9 }
            android.app.Activity r8 = f1396d     // Catch:{ all -> 0x00d9 }
            if (r13 != r8) goto L_0x003d
            f1396d = r5     // Catch:{ all -> 0x00d9 }
        L_0x003d:
            r5 = 0
            if (r14 == r3) goto L_0x004b
            if (r14 != r2) goto L_0x0043
            goto L_0x004b
        L_0x0043:
            if (r14 != r7) goto L_0x005a
            org.chromium.base.ApplicationStatus$e<android.app.Activity> r8 = f1394b     // Catch:{ all -> 0x00d9 }
            r8.a(r13)     // Catch:{ all -> 0x00d9 }
            goto L_0x005a
        L_0x004b:
            org.chromium.base.ApplicationStatus$e<android.app.Activity> r8 = f1394b     // Catch:{ all -> 0x00d9 }
            r8.a(r13)     // Catch:{ all -> 0x00d9 }
            java.util.List r8 = r8.a     // Catch:{ all -> 0x00d9 }
            java.lang.ref.WeakReference r9 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00d9 }
            r9.<init>(r13)     // Catch:{ all -> 0x00d9 }
            r8.add(r5, r9)     // Catch:{ all -> 0x00d9 }
        L_0x005a:
            java.util.Map<android.app.Activity, org.chromium.base.ApplicationStatus$d> r8 = f1393a     // Catch:{ all -> 0x00d9 }
            java.util.Collection r8 = r8.values()     // Catch:{ all -> 0x00d9 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00d9 }
            r9 = 0
        L_0x0065:
            boolean r10 = r8.hasNext()     // Catch:{ all -> 0x00d9 }
            r11 = 4
            if (r10 == 0) goto L_0x0085
            java.lang.Object r10 = r8.next()     // Catch:{ all -> 0x00d9 }
            org.chromium.base.ApplicationStatus$d r10 = (org.chromium.base.ApplicationStatus.d) r10     // Catch:{ all -> 0x00d9 }
            int r10 = r10.a     // Catch:{ all -> 0x00d9 }
            r12 = 5
            if (r10 == r11) goto L_0x007d
            if (r10 == r12) goto L_0x007d
            if (r10 == r7) goto L_0x007d
            r1 = 1
            goto L_0x008d
        L_0x007d:
            if (r10 != r11) goto L_0x0081
            r5 = 1
            goto L_0x0065
        L_0x0081:
            if (r10 != r12) goto L_0x0065
            r9 = 1
            goto L_0x0065
        L_0x0085:
            if (r5 == 0) goto L_0x0088
            goto L_0x008d
        L_0x0088:
            if (r9 == 0) goto L_0x008c
            r1 = 3
            goto L_0x008d
        L_0x008c:
            r1 = 4
        L_0x008d:
            f1395c = r1     // Catch:{ all -> 0x00d9 }
            monitor-exit(r4)     // Catch:{ all -> 0x00d9 }
            org.chromium.base.ObserverList r1 = r6.b
            java.util.Iterator r1 = r1.iterator()
        L_0x0096:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00a6
            java.lang.Object r2 = r1.next()
            org.chromium.base.ApplicationStatus$ActivityStateListener r2 = (org.chromium.base.ApplicationStatus.ActivityStateListener) r2
            r2.onActivityStateChange(r13, r14)
            goto L_0x0096
        L_0x00a6:
            org.chromium.base.ObserverList<org.chromium.base.ApplicationStatus$ActivityStateListener> r1 = f1398f
            java.util.Iterator r1 = r1.iterator()
        L_0x00ac:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00bc
            java.lang.Object r2 = r1.next()
            org.chromium.base.ApplicationStatus$ActivityStateListener r2 = (org.chromium.base.ApplicationStatus.ActivityStateListener) r2
            r2.onActivityStateChange(r13, r14)
            goto L_0x00ac
        L_0x00bc:
            int r13 = getStateForApplication()
            if (r13 == r0) goto L_0x00d8
            org.chromium.base.ObserverList<org.chromium.base.ApplicationStatus$ApplicationStateListener> r14 = f1399g
            java.util.Iterator r14 = r14.iterator()
        L_0x00c8:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L_0x00d8
            java.lang.Object r0 = r14.next()
            org.chromium.base.ApplicationStatus$ApplicationStateListener r0 = (org.chromium.base.ApplicationStatus.ApplicationStateListener) r0
            r0.onApplicationStateChange(r13)
            goto L_0x00c8
        L_0x00d8:
            return
        L_0x00d9:
            r13 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00d9 }
            throw r13
        L_0x00dc:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "null activity is not supported"
            r13.<init>(r14)
            goto L_0x00e5
        L_0x00e4:
            throw r13
        L_0x00e5:
            goto L_0x00e4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ApplicationStatus.m1361a(android.app.Activity, int):void");
    }

    /* renamed from: a */
    public static Activity m1360a() {
        return f1396d;
    }

    /* renamed from: a */
    public static int m1359a(Activity activity) {
        d dVar;
        if (activity == null || (dVar = f1393a.get(activity)) == null) {
            return 6;
        }
        return dVar.a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m1364a(ActivityStateListener activityStateListener, Activity activity) {
        f1393a.get(activity).b.mo7868a(activityStateListener);
    }

    /* renamed from: a */
    public static void m1363a(ActivityStateListener activityStateListener) {
        f1398f.mo7869b(activityStateListener);
        synchronized (f1393a) {
            for (d dVar : f1393a.values()) {
                dVar.b.mo7869b(activityStateListener);
            }
        }
    }

    /* renamed from: a */
    public static void m1365a(ApplicationStateListener applicationStateListener) {
        f1399g.mo7868a(applicationStateListener);
    }
}
