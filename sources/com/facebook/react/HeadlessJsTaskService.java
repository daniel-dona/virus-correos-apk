package com.facebook.react;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.PowerManager;
import com.facebook.react.bridge.ReactContext;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: PG */
public abstract class HeadlessJsTaskService extends Service implements zy {

    /* renamed from: b */
    public static PowerManager.WakeLock f416b;

    /* renamed from: a */
    public final Set<Integer> f417a = new CopyOnWriteArraySet();

    /* renamed from: a */
    public vx mo574a() {
        return getApplication().a();
    }

    /* renamed from: b */
    public void mo575b() {
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        ReactContext b;
        super.onDestroy();
        if (mo574a().c() && (b = mo574a().a().mo615b()) != null) {
            yy.a(b).b.remove(this);
        }
        PowerManager.WakeLock wakeLock = f416b;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    public void onHeadlessJsTaskFinish(int i) {
        this.f417a.remove(Integer.valueOf(i));
        if (this.f417a.size() == 0) {
            stopSelf();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        mo575b();
        return 2;
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
