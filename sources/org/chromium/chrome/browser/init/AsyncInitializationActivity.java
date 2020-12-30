package org.chromium.chrome.browser.init;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.chrome.browser.ChromeApplication;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.IntentHandler;
import org.chromium.chrome.browser.WarmupManager;
import org.chromium.chrome.browser.multiwindow.MultiWindowModeStateDispatcher;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.p012ui.base.DeviceFormFactor;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.p012ui.display.DisplayAndroid;
import org.chromium.ui.modaldialog.ModalDialogManager;

/* compiled from: PG */
public abstract class AsyncInitializationActivity extends ChromeBaseAppCompatActivity implements V12, R12, pI3 {

    /* renamed from: A3 */
    public boolean f2027A3;

    /* renamed from: B3 */
    public final b f2028B3;

    /* renamed from: d */
    public final Handler f2029d;

    /* renamed from: e */
    public final x22 f2030e = new x22(this);

    /* renamed from: k */
    public final I12 f2031k = new I12();

    /* renamed from: n */
    public final fb2 f2032n = new fb2(this);

    /* renamed from: p */
    public long f2033p;

    /* renamed from: q */
    public QH3 f2034q;

    /* renamed from: q3 */
    public int f2035q3;

    /* renamed from: r3 */
    public boolean f2036r3;

    /* renamed from: s3 */
    public long f2037s3;

    /* renamed from: t3 */
    public boolean f2038t3;

    /* renamed from: u3 */
    public boolean f2039u3;

    /* renamed from: v3 */
    public boolean f2040v3;

    /* renamed from: w3 */
    public boolean f2041w3 = true;

    /* renamed from: x */
    public ModalDialogManager f2042x;

    /* renamed from: x3 */
    public boolean f2043x3;

    /* renamed from: y */
    public Bundle f2044y;

    /* renamed from: y3 */
    public boolean f2045y3;

    /* renamed from: z3 */
    public Runnable f2046z3;

    static {
        Class<AsyncInitializationActivity> cls = AsyncInitializationActivity.class;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [V12, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public AsyncInitializationActivity() {
        this.f2028B3 = Build.VERSION.SDK_INT == 21 ? new b(this, (a) null) : null;
        this.f2029d = new Handler();
    }

    /* renamed from: A */
    public final void mo8765A() {
        mo8088b0();
    }

    /* renamed from: C */
    public ModalDialogManager mo8766C() {
        return this.f2042x;
    }

    /* renamed from: J */
    public void mo8032J() {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity, android.support.v7.app.AppCompatActivity] */
    /* renamed from: K */
    public final void mo8767K() {
        WindowManager windowManager = getWindowManager();
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            int i = this.f2035q3;
            this.f2035q3 = getResources().getConfiguration().orientation;
            int i2 = this.f2035q3;
            if (i != i2) {
                mo8097e(i2);
            }
        }
    }

    /* renamed from: L */
    public ModalDialogManager mo8037L() {
        return null;
    }

    /* renamed from: M */
    public QH3 mo8040M() {
        return null;
    }

    /* renamed from: N */
    public long mo8768N() {
        return this.f2037s3;
    }

    /* renamed from: O */
    public Q52 mo8769O() {
        return this.f2031k;
    }

    /* renamed from: P */
    public MultiWindowModeStateDispatcher mo8770P() {
        return this.f2032n;
    }

    /* renamed from: Q */
    public Bundle mo8771Q() {
        return this.f2044y;
    }

    /* renamed from: R */
    public View mo8048R() {
        return findViewById(16908290);
    }

    /* renamed from: S */
    public QH3 mo8772S() {
        return this.f2034q;
    }

    /* renamed from: T */
    public boolean mo8773T() {
        return this.f2039u3;
    }

    /* renamed from: U */
    public void mo8052U() {
    }

    /* renamed from: V */
    public boolean mo8774V() {
        return this.f2027A3;
    }

    /* renamed from: W */
    public boolean mo8775W() {
        return this.f2038t3;
    }

    /* renamed from: X */
    public boolean mo8776X() {
        return this.f2040v3;
    }

    /* renamed from: Y */
    public final /* synthetic */ void mo8777Y() {
        this.f2045y3 = true;
        if (!this.f2043x3) {
            TraceEvent.m1468A("onFirstDrawComplete");
            x22 x22 = this.f2030e;
            x22.g = true;
            x22.b();
        }
    }

    /* renamed from: Z */
    public void mo8058Z() {
        Runnable runnable = this.f2046z3;
        if (runnable != null) {
            runnable.run();
            this.f2046z3 = null;
            this.f2027A3 = true;
        }
    }

    /* renamed from: a */
    public Bundle mo8217a(Bundle bundle) {
        return bundle;
    }

    /* renamed from: a0 */
    public void mo8078a0() {
        u22 u22 = new u22(mo8048R(), new O12(this));
        r22 r22 = new r22(u22);
        t22 t22 = new t22(u22);
        u22.a.getViewTreeObserver().addOnPreDrawListener(r22);
        u22.a.getViewTreeObserver().addOnDrawListener(t22);
    }

    /* renamed from: b */
    public void mo8082b() {
        I12 i12 = this.f2031k;
        i12.j = 4;
        Iterator it = i12.c.iterator();
        while (it.hasNext()) {
            ((X52) it.next()).b();
        }
    }

    /* renamed from: b */
    public boolean mo8230b(Intent intent) {
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, org.chromium.chrome.browser.init.AsyncInitializationActivity] */
    /* renamed from: b0 */
    public void mo8088b0() {
        this.f2038t3 = DeviceFormFactor.m3641c(this);
        this.f2039u3 = LibraryLoader.f1501h.mo7904a();
        Iterator it = this.f2031k.a.iterator();
        while (it.hasNext()) {
            ((U52) it.next()).l();
        }
    }

    /* renamed from: c */
    public int mo8232c(Intent intent) {
        return 0;
    }

    /* renamed from: c */
    public void mo8090c() {
        I12 i12 = this.f2031k;
        i12.j = 5;
        Iterator it = i12.d.iterator();
        while (it.hasNext()) {
            ((Z52) it.next()).c();
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.microsoft.theme.entity.ThemeCompatActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: c0 */
    public void mo8780c0() {
        boolean z = true;
        try {
            Field field = Settings.Secure.class.getField("ACCESSIBILITY_DISPLAY_MAGNIFICATION_ENABLED");
            field.setAccessible(true);
            if (field.getType() == String.class) {
                if (Settings.Secure.getInt(getContentResolver(), (String) field.get((Object) null)) == 1) {
                    z = false;
                }
            }
        } catch (Settings.SettingNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
        }
        if (z) {
            getWindow().setBackgroundDrawable((Drawable) null);
            mo4508F();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.ChromeBaseAppCompatActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: d */
    public final void mo8781d(int i) {
        super.onMAMCreate((Bundle) null);
        if (i == 1) {
            finish();
            return;
        }
        ON0.a(this);
        int i2 = Build.VERSION.SDK_INT;
        if ((i2 == 21 || i2 == 22) && !isFinishing()) {
            finish();
            if (!isFinishing()) {
                Process.killProcess(Process.myPid());
            }
        }
        overridePendingTransition(0, ex0.no_anim);
    }

    /* renamed from: d */
    public boolean mo8394d(Intent intent) {
        return true;
    }

    /* renamed from: d0 */
    public void mo8782d0() {
        this.f2044y = null;
    }

    /* renamed from: e */
    public void mo8783e() {
        ChromeApplication.m1772a(new ProcessInitException(4));
    }

    /* renamed from: e */
    public void mo8097e(int i) {
    }

    /* renamed from: e */
    public boolean mo8784e(Intent intent) {
        return false;
    }

    /* renamed from: e0 */
    public boolean mo8395e0() {
        return !WarmupManager.m1950e().mo8311d();
    }

    /* renamed from: f */
    public Intent mo8785f(Intent intent) {
        return intent;
    }

    /* renamed from: f */
    public void mo8100f() {
        I12 i12 = this.f2031k;
        i12.j = 2;
        Iterator it = i12.d.iterator();
        while (it.hasNext()) {
            ((Z52) it.next()).f();
        }
    }

    /* renamed from: f0 */
    public boolean mo8786f0() {
        return false;
    }

    /* renamed from: g0 */
    public abstract void mo8105g0();

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: i */
    public Intent mo8787i() {
        return getIntent();
    }

    /* renamed from: m */
    public void mo8124m() {
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: o */
    public void mo8788o() {
        try {
            TraceEvent.m1472c("maybePreconnect", (String) null);
            ft0.a(getIntent());
            Intent intent = getIntent();
            if (intent != null) {
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    String g = IntentHandler.m1922g(intent);
                    if (g == null) {
                        TraceEvent.m1475z("maybePreconnect");
                        return;
                    }
                    WarmupManager.m1950e().mo8305a(Profile.m2911j(), g);
                    TraceEvent.m1475z("maybePreconnect");
                }
            }
        } finally {
            TraceEvent.m1475z("maybePreconnect");
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator it = this.f2031k.i.iterator();
        while (it.hasNext()) {
            ((Jv1) it.next()).i();
        }
    }

    public void onContextMenuClosed(Menu menu) {
        QH3 qh3 = this.f2034q;
        if (qh3 != null) {
            qh3.mo10028m();
        }
    }

    public void onMAMActivityResult(int i, int i2, Intent intent) {
        x22 x22 = this.f2030e;
        if (x22.i) {
            x22.a.a(i, i2, intent);
        } else {
            if (x22.e == null) {
                x22.e = new ArrayList(1);
            }
            x22.e.add(new w22(i, i2, intent));
        }
        AsyncInitializationActivity.super.onMAMActivityResult(i, i2, intent);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, R12, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        qI.a.a(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ab, code lost:
        throw r1;
     */
    @android.annotation.SuppressLint({"MissingSuperCall"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMAMCreate(android.os.Bundle r7) {
        /*
            r6 = this;
            mt0 r0 = mt0.d
            java.lang.String r1 = "chrome_create"
            java.lang.String r2 = "action_start"
            r0.a(r1, r2)
            r0 = 0
            java.lang.String r2 = "AsyncInitializationActivity.onCreate()"
            org.chromium.base.TraceEvent.m1472c(r2, r0)
            r6.mo8032J()
            r6.mo8052U()
            android.content.Intent r0 = r6.getIntent()
            android.content.Intent r0 = r6.mo8785f(r0)
            r6.setIntent(r0)
            android.content.Intent r0 = r6.getIntent()
            int r0 = r6.mo8232c(r0)
            if (r0 == 0) goto L_0x002e
            r6.mo8781d((int) r0)
            goto L_0x0093
        L_0x002e:
            android.content.Intent r0 = r6.getIntent()
            boolean r3 = r6.mo8230b(r0)
            r4 = 2
            if (r3 != 0) goto L_0x003d
            r6.mo8781d((int) r4)
            goto L_0x0093
        L_0x003d:
            boolean r3 = r6.mo8394d((android.content.Intent) r0)
            if (r3 == 0) goto L_0x0052
            r3 = 0
            boolean r5 = r6.mo8784e((android.content.Intent) r0)
            boolean r0 = AX1.a(r6, r0, r3, r5)
            if (r0 == 0) goto L_0x0052
            r6.mo8781d((int) r4)
            goto L_0x0093
        L_0x0052:
            pO0 r0 = pO0.a()
            android.os.Bundle r3 = r6.mo8217a((android.os.Bundle) r7)     // Catch:{ all -> 0x009e }
            super.onMAMCreate(r3)     // Catch:{ all -> 0x009e }
            r0.close()
            long r3 = android.os.SystemClock.elapsedRealtime()
            r6.f2033p = r3
            android.os.SystemClock.uptimeMillis()
            r6.f2044y = r7
            QH3 r7 = r6.mo8040M()
            r6.f2034q = r7
            QH3 r7 = r6.f2034q
            if (r7 == 0) goto L_0x0080
            QH3 r7 = r6.mo8772S()
            android.os.Bundle r0 = r6.mo8771Q()
            r7.mo9994a((android.os.Bundle) r0)
        L_0x0080:
            org.chromium.ui.modaldialog.ModalDialogManager r7 = r6.mo8037L()
            r6.f2042x = r7
            boolean r7 = r6.mo8786f0()
            r6.f2043x3 = r7
            org.chromium.chrome.browser.init.ChromeBrowserInitializer r7 = org.chromium.chrome.browser.init.ChromeBrowserInitializer.m2488f()
            r7.mo8796a((R12) r6)
        L_0x0093:
            org.chromium.base.TraceEvent.m1475z(r2)
            mt0 r7 = mt0.d
            java.lang.String r0 = "action_end"
            r7.a(r1, r0)
            return
        L_0x009e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x00a5 }
            goto L_0x00ab
        L_0x00a5:
            r0 = move-exception
            kI r2 = qI.a
            r2.a(r7, r0)
        L_0x00ab:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.init.AsyncInitializationActivity.onMAMCreate(android.os.Bundle):void");
    }

    public void onMAMDestroy() {
        this.f2036r3 = true;
        QH3 qh3 = this.f2034q;
        if (qh3 != null) {
            qh3.mo9990a();
            this.f2034q = null;
        }
        ModalDialogManager modalDialogManager = this.f2042x;
        if (modalDialogManager != null) {
            modalDialogManager.a();
            this.f2042x = null;
        }
        super.onMAMDestroy();
        I12 i12 = this.f2031k;
        i12.j = 6;
        Iterator it = i12.e.iterator();
        while (it.hasNext()) {
            ((T52) it.next()).destroy();
        }
        i12.a.clear();
        i12.c.clear();
        i12.d.clear();
        i12.b.clear();
        i12.f.clear();
        i12.g.clear();
        i12.h.clear();
        i12.i.clear();
        i12.e.clear();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onMAMNewIntent(Intent intent) {
        if (intent != null) {
            x22 x22 = this.f2030e;
            if (x22.i) {
                x22.a.a(intent);
            } else {
                if (x22.d == null) {
                    x22.d = new ArrayList(1);
                }
                x22.d.add(intent);
            }
            setIntent(intent);
        }
    }

    public void onMAMPause() {
        x22 x22 = this.f2030e;
        x22.c = false;
        if (x22.i) {
            x22.a.b();
        }
        AsyncInitializationActivity.super.onMAMPause();
        b bVar = this.f2028B3;
        if (bVar != null) {
            bVar.a = true;
        }
    }

    public void onMAMResume() {
        super.onMAMResume();
        this.f2040v3 = !this.f2041w3 || mo8773T();
        this.f2041w3 = false;
        x22 x22 = this.f2030e;
        if (x22.i) {
            x22.a.d();
        } else {
            x22.c = true;
        }
        b bVar = this.f2028B3;
        if (bVar != null) {
            bVar.a = false;
            bVar.a().setVisibility(0);
        }
    }

    public void onMAMSaveInstanceState(Bundle bundle) {
        AsyncInitializationActivity.super.onMAMSaveInstanceState(bundle);
        QH3 qh3 = this.f2034q;
        if (qh3 != null) {
            qh3.mo10008b(bundle);
        }
        Iterator it = this.f2031k.f.iterator();
        while (it.hasNext()) {
            ((Y52) it.next()).onSaveInstanceState(bundle);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        AsyncInitializationActivity.super.onMultiWindowModeChanged(z);
        Iterator it = this.f2032n.b.iterator();
        while (it.hasNext()) {
            ((MultiWindowModeStateDispatcher.MultiWindowModeObserver) it.next()).onMultiWindowModeChanged(z);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        QH3 qh3 = this.f2034q;
        if (qh3 == null || !qh3.mo10002a(i, strArr, iArr)) {
            AsyncInitializationActivity.super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void onStart() {
        AsyncInitializationActivity.super.onStart();
        x22 x22 = this.f2030e;
        if (x22.i) {
            x22.c();
        } else {
            x22.b = true;
        }
    }

    public void onStop() {
        AsyncInitializationActivity.super.onStop();
        x22 x22 = this.f2030e;
        x22.b = false;
        if (x22.i) {
            x22.a.c();
        }
    }

    public void onUserInteraction() {
        this.f2037s3 = SystemClock.elapsedRealtime();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    public void onWindowFocusChanged(boolean z) {
        AsyncInitializationActivity.super.onWindowFocusChanged(z);
        Iterator it = this.f2031k.g.iterator();
        while (it.hasNext()) {
            JL1 jl1 = ((GL1) it.next()).a;
            if (jl1 != null) {
                try {
                    jl1.e(z);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    /* renamed from: p */
    public final void mo8792p() {
        mo8078a0();
        Iterator it = this.f2031k.a.iterator();
        while (it.hasNext()) {
            ((U52) it.next()).k();
        }
    }

    /* renamed from: t */
    public void mo8165t() {
        mo8767K();
        findViewById(16908290).addOnLayoutChangeListener(new a(this));
        x22 x22 = this.f2030e;
        x22.i = true;
        if (x22.b) {
            x22.b = false;
            x22.c();
        }
        if (x22.c) {
            x22.c = false;
            x22.a();
        }
        LibraryLoader.f1501h.mo7908c();
        Iterator it = this.f2031k.b.iterator();
        while (it.hasNext()) {
            ((W52) it.next()).j();
        }
    }

    /* renamed from: w */
    public boolean mo8793w() {
        return false;
    }

    /* renamed from: x */
    public final void mo8794x() {
        this.f2031k.j = 1;
        try {
            ChromeBrowserInitializer.m2488f().mo8799a(true, this);
        } catch (ProcessInitException e) {
            ChromeApplication.m1772a(e);
        }
    }

    /* renamed from: y */
    public void mo8282y() {
        t();
    }

    /* renamed from: z */
    public void mo8180z() {
    }

    /* renamed from: a */
    public boolean mo8199a(Context context, Configuration configuration) {
        int i;
        super.mo8199a(context, configuration);
        if (Og0.d()) {
            i = 1;
        } else {
            DisplayAndroid a = DisplayAndroid.m3727a(context);
            Point point = a.f2650c;
            int i2 = point.x;
            int i3 = point.y;
            if (i2 < i3) {
                i3 = i2;
            }
            i = (int) ((((float) i3) / a.f2651d) + 0.5f);
        }
        configuration.smallestScreenWidthDp = i;
        configuration.fontScale = context.getResources().getConfiguration().fontScale;
        return true;
    }

    /* renamed from: d */
    public void mo8093d() {
        I12 i12 = this.f2031k;
        i12.j = 3;
        Iterator it = i12.c.iterator();
        while (it.hasNext()) {
            ((X52) it.next()).d();
        }
    }

    /* renamed from: a */
    public final void mo8778a(Runnable runnable) {
        if (!this.f2043x3) {
            this.f2030e.a(mo8395e0());
        }
        this.f2046z3 = runnable;
        mo8105g0();
        b bVar = this.f2028B3;
        if (bVar != null) {
            bVar.b().addOnPreDrawListener(bVar.b);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.chromium.chrome.browser.init.AsyncInitializationActivity, android.app.Activity] */
    /* renamed from: a */
    public boolean mo8779a() {
        return this.f2036r3 || isFinishing();
    }

    /* renamed from: a */
    public void mo8065a(Intent intent) {
        ft0.a(intent);
    }

    /* renamed from: a */
    public boolean mo8075a(int i, int i2, Intent intent) {
        QH3 qh3 = this.f2034q;
        if (qh3 != null) {
            WindowAndroid.IntentCallback intentCallback = qh3.f2626e.get(i);
            qh3.f2626e.delete(i);
            String remove = qh3.f2628n.remove(Integer.valueOf(i));
            if (intentCallback != null) {
                intentCallback.onIntentCompleted(qh3, i2, intent);
                return true;
            } else if (remove == null) {
                return false;
            } else {
                qh3.mo10009b(remove);
                return true;
            }
        } else {
            Iterator it = this.f2031k.h.iterator();
            while (it.hasNext()) {
                ((R52) it.next()).a(i, i2, intent);
            }
            return false;
        }
    }
}
