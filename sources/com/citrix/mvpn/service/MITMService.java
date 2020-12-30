package com.citrix.mvpn.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.citrix.mvpn.MAM.Android.AuthSSO.c.d;
import com.citrix.mvpn.MAM.Android.AuthSSO.proxy.Helper;
import com.citrix.mvpn.api.ResponseStatusCode;
import com.citrix.mvpn.exception.NetworkTunnelStartFailedException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p001a.C0025d;
import com.citrix.mvpn.p002b.C0027a;
import com.citrix.mvpn.p002b.C0028b;
import com.citrix.mvpn.p002b.C0030d;
import com.citrix.mvpn.p003c.C0042e;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
public class MITMService extends Service {

    /* renamed from: a */
    public static ExecutorService f222a = Executors.newFixedThreadPool(1);

    /* renamed from: com.citrix.mvpn.service.MITMService$a */
    /* compiled from: PG */
    public static class C0055a extends Handler {

        /* renamed from: a */
        public Context f223a;

        public C0055a(Context context) {
            this.f223a = context.getApplicationContext();
            C0051c.f218b.debug("MVPN-MITMService", "Creating incoming handler.");
        }

        public void handleMessage(Message message) {
            if (message.what != 1) {
                LoggingAPI loggingAPI = C0051c.f218b;
                StringBuilder a = Eo.a("Unknown message received: ");
                a.append(message.what);
                loggingAPI.debug("MVPN-MITMService", a.toString());
                super.handleMessage(message);
                return;
            }
            LoggingAPI loggingAPI2 = C0051c.f218b;
            StringBuilder a2 = Eo.a("Start message received: ");
            a2.append(message.what);
            loggingAPI2.debug("MVPN-MITMService", a2.toString());
            MITMService.f222a.execute(new C0056b(message.getData(), message.replyTo, this.f223a));
        }
    }

    /* renamed from: com.citrix.mvpn.service.MITMService$b */
    /* compiled from: PG */
    public static class C0056b implements Runnable {

        /* renamed from: a */
        public Bundle f224a;

        /* renamed from: b */
        public Messenger f225b;

        /* renamed from: c */
        public Context f226c;

        public C0056b(Bundle bundle, Messenger messenger, Context context) {
            this.f226c = context;
            this.f224a = bundle;
            this.f225b = messenger;
        }

        /* renamed from: a */
        public void mo285a() {
            try {
                C0025d.m63a(this.f226c.getApplicationContext());
            } catch (Exception e) {
                LoggingAPI loggingAPI = C0051c.f218b;
                StringBuilder a = Eo.a("Failed to set up proxy:");
                a.append(e.getMessage());
                loggingAPI.error("MVPN-MITMService", a.toString());
            }
        }

        public void run() {
            C0027a aVar;
            C0028b bVar;
            C0051c.f218b.info("MVPN-MITMService", "Starting MITM Proxy service...");
            C0050b.m183a(this.f224a.getString("ProcessKey"), (Messenger) this.f224a.getParcelable("MVPNMessenger"));
            if (!C0050b.m185b(this.f226c)) {
                TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
                try {
                    C0050b.m177a(this.f224a, this.f226c);
                    C0042e eVar = new C0042e(System.currentTimeMillis(), Helper.d(), Helper.c(), !Helper.a(), Helper.a());
                    C0050b.m172a(this.f226c, eVar);
                    if (eVar.mo241d()) {
                        mo285a();
                        bVar = C0028b.START_TUNNEL;
                        aVar = new C0027a(this.f226c, eVar, ResponseStatusCode.START_TUNNEL_SUCCESS);
                    } else {
                        bVar = C0028b.START_TUNNEL;
                        aVar = new C0027a(this.f226c, eVar, ResponseStatusCode.START_TUNNEL_FAILED);
                    }
                    C0030d.m70a(bVar, aVar);
                } catch (NetworkTunnelStartFailedException e) {
                    d a = d.a();
                    StringBuilder a2 = Eo.a("Exception occurred in MITM worker:");
                    a2.append(e.getMessage());
                    a.a("MVPN-MITMService", a2.toString(), e);
                }
                try {
                    Message obtain = Message.obtain((Handler) null, 0);
                    d a3 = d.a();
                    a3.c("MVPN-MITMService", "Sending MITM init completed message with status: " + obtain.what);
                    this.f225b.send(obtain);
                } catch (RemoteException unused) {
                    d.a().a("MVPN-MITMService", "Failed to send MITM Init completed message.");
                }
            } else {
                C0051c.f218b.debug("MVPN-MITMService", "Tunnel is already running.");
                C0030d.m70a(C0028b.START_TUNNEL, new C0027a(this.f226c, C0050b.m187d(this.f226c), ResponseStatusCode.TUNNEL_ALREADY_RUNNING));
            }
        }
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
        C0051c.f218b.debug("MVPN-MITMService", "Now bound to MVPN-MITMService, creating messenger...");
        return new Messenger(new C0055a(this)).getBinder();
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
