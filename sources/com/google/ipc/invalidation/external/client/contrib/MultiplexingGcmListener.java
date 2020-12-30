package com.google.ipc.invalidation.external.client.contrib;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMBroadcastReceiver;
import com.google.ipc.invalidation.external.client.SystemResources;
import com.google.protobuf.ByteString;

/* compiled from: PG */
public class MultiplexingGcmListener extends GCMBaseIntentService {

    /* renamed from: p */
    public static final SystemResources.Logger f1118p = cL.b("MplexGcmListener");

    /* compiled from: PG */
    public static class GCMReceiver extends GCMBroadcastReceiver {
        /* renamed from: a */
        public String mo2348a(Context context) {
            return MultiplexingGcmListener.class.getName();
        }
    }

    /* renamed from: a */
    public void mo2328a(Context context, int i) {
        Intent intent = new Intent();
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.DELETED_MSGS", true);
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.NUM_DELETED_MSGS", i);
        mo2330a(intent);
    }

    /* renamed from: b */
    public void mo2332b(Context context, Intent intent) {
        Intent intent2 = new Intent();
        intent2.putExtra("com.google.ipc.invalidation.gcmmplex.MESSAGE", true);
        intent2.putExtras(intent);
        mo2330a(intent2);
    }

    /* renamed from: c */
    public void mo2334c(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.UNREGISTERED", true);
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.REGID", str);
        mo2330a(intent);
    }

    /* renamed from: a */
    public void mo2329a(Context context, String str) {
        f1118p.c("GCM error: %s", new Object[]{str});
    }

    /* renamed from: b */
    public void mo2333b(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.REGISTERED", true);
        intent.putExtra("com.google.ipc.invalidation.gcmmplex.REGID", str);
        mo2330a(intent);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.IntentService, com.google.ipc.invalidation.external.client.contrib.MultiplexingGcmListener] */
    /* renamed from: a */
    public final void mo2330a(Intent intent) {
        intent.setAction("com.google.ipc.invalidation.gcmmplex.EVENT");
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.ipc.invalidation.external.client.contrib.MultiplexingGcmListener, android.content.Context] */
    /* renamed from: a */
    public String[] mo2331a(Context context) {
        try {
            ServiceInfo serviceInfo = getPackageManager().getServiceInfo(new ComponentName(this, MultiplexingGcmListener.class), ByteString.CONCATENATE_BY_COPY_SIZE);
            if (serviceInfo.metaData != null) {
                String string = serviceInfo.metaData.getString("sender_ids");
                if (string != null) {
                    return string.split(",");
                }
                throw new RuntimeException("Service does not have the sender-ids metadata");
            }
            throw new RuntimeException("Service has no metadata");
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not read service info from manifest", e);
        }
    }

    /* compiled from: PG */
    public static abstract class AbstractListener extends IntentService {

        /* compiled from: PG */
        public static abstract class Receiver extends BroadcastReceiver {
            /* renamed from: a */
            public abstract Class<?> mo2346a();

            public final void onReceive(Context context, Intent intent) {
                Class<?> a = mo2346a();
                if (AbstractListener.class.isAssignableFrom(a)) {
                    String a2 = AbstractListener.m997a(a);
                    intent.setClass(context, a);
                    intent.putExtra("com.google.ipc.invalidation.gcmmplex.listener.WAKELOCK_NAME", a2);
                    YL.a(context).a(a2, 30000);
                    context.startService(intent);
                    return;
                }
                throw new RuntimeException("Service class is not a subclass of AbstractListener: " + a);
            }
        }

        public AbstractListener(String str) {
            super(str);
            setIntentRedelivery(true);
        }

        /* renamed from: a */
        public abstract void mo2335a(int i);

        /* renamed from: a */
        public final void mo2336a(Intent intent) {
            if (!"com.google.ipc.invalidation.gcmmplex.EVENT".equals(intent.getAction())) {
                MultiplexingGcmListener.f1118p.c("Ignoring intent with unknown action: %s", new Object[]{intent});
            } else if (intent.hasExtra("com.google.ipc.invalidation.gcmmplex.MESSAGE")) {
                mo2338b(intent);
            } else if (intent.hasExtra("com.google.ipc.invalidation.gcmmplex.REGISTERED")) {
                mo2337a(intent.getStringExtra("com.google.ipc.invalidation.gcmmplex.REGID"));
            } else if (intent.hasExtra("com.google.ipc.invalidation.gcmmplex.UNREGISTERED")) {
                mo2339b(intent.getStringExtra("com.google.ipc.invalidation.gcmmplex.REGID"));
            } else if (intent.hasExtra("com.google.ipc.invalidation.gcmmplex.DELETED_MSGS")) {
                int intExtra = intent.getIntExtra("com.google.ipc.invalidation.gcmmplex.NUM_DELETED_MSGS", -1);
                if (intExtra == -1) {
                    MultiplexingGcmListener.f1118p.c("Could not parse num-deleted field of GCM broadcast: %s", new Object[]{intent});
                    return;
                }
                mo2335a(intExtra);
            } else {
                MultiplexingGcmListener.f1118p.c("Broadcast GCM intent with no known operation: %s", new Object[]{intent});
            }
        }

        /* renamed from: a */
        public abstract void mo2337a(String str);

        /* renamed from: b */
        public abstract void mo2338b(Intent intent);

        /* renamed from: b */
        public abstract void mo2339b(String str);

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

        public final void onHandleIntent(Intent intent) {
            if (intent != null) {
                try {
                    mo2336a(intent);
                } finally {
                    String stringExtra = intent.getStringExtra("com.google.ipc.invalidation.gcmmplex.listener.WAKELOCK_NAME");
                    String a = m997a(getClass());
                    if (!a.equals(stringExtra)) {
                        MultiplexingGcmListener.f1118p.c("Receiver acquired wakelock '%s' but releasing '%s'", new Object[]{stringExtra, a});
                    }
                    YL.a(this).b(a);
                }
            }
        }

        public void setTheme(int i) {
            if (!gs1.d()) {
                super.setTheme(i);
            } else {
                gs1.b(this, i);
            }
        }

        /* renamed from: a */
        public static String m997a(Class<?> cls) {
            StringBuilder a = Eo.a("multiplexing-gcm-listener:");
            a.append(cls.getName());
            return a.toString();
        }
    }
}
