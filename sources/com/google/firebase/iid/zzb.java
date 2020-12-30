package com.google.firebase.iid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
public abstract class zzb extends Service {

    /* renamed from: a */
    public final ExecutorService f919a;

    /* renamed from: b */
    public Binder f920b;

    /* renamed from: c */
    public final Object f921c;

    /* renamed from: d */
    public int f922d;

    /* renamed from: e */
    public int f923e;

    public zzb() {
        String simpleName = getClass().getSimpleName();
        this.f919a = Executors.newSingleThreadExecutor(new NamedThreadFactory(simpleName.length() != 0 ? "Firebase-".concat(simpleName) : new String("Firebase-")));
        this.f921c = new Object();
        this.f923e = 0;
    }

    /* renamed from: a */
    public final void mo2047a(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.a(intent);
        }
        synchronized (this.f921c) {
            this.f923e--;
            if (this.f923e == 0) {
                stopSelfResult(this.f922d);
            }
        }
    }

    /* renamed from: b */
    public Intent mo2036b(Intent intent) {
        return intent;
    }

    /* renamed from: c */
    public boolean mo2048c(Intent intent) {
        return false;
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    /* renamed from: d */
    public abstract void mo2037d(Intent intent);

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public final synchronized IBinder onBind(Intent intent) {
        boolean isLoggable = Log.isLoggable("EnhancedIntentService", 3);
        if (this.f920b == null) {
            this.f920b = new kJ(this);
        }
        return this.f920b;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.f921c) {
            this.f922d = i2;
            this.f923e++;
        }
        Intent b = mo2036b(intent);
        if (b == null) {
            mo2047a(intent);
            return 2;
        } else if (mo2048c(b)) {
            mo2047a(intent);
            return 2;
        } else {
            this.f919a.execute(new hJ(this, b, intent));
            return 3;
        }
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
