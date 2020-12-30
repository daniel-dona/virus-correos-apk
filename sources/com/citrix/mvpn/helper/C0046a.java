package com.citrix.mvpn.helper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.citrix.auth.api.AuthSDK;
import com.citrix.auth.exception.AuthenticationException;
import com.citrix.mvpn.api.MicroVPNSDK;
import com.citrix.mvpn.api.ResponseStatusCode;
import com.citrix.mvpn.d.a.a;
import com.citrix.mvpn.d.a.c;
import com.citrix.mvpn.d.a.d;
import com.citrix.mvpn.exception.NetworkTunnelStartFailedException;
import com.citrix.mvpn.exception.NsgAuthException;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.mvpn.p003c.C0036b;
import com.citrix.mvpn.p003c.C0040d;
import com.citrix.mvpn.service.MITMService;
import com.citrix.sdk.config.api.PolicyAPI;
import com.citrix.sdk.config.exception.PolicyConfigException;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.util.List;
import java.util.Map;

/* renamed from: com.citrix.mvpn.helper.a */
/* compiled from: PG */
public class C0046a extends AsyncTask<Void, Void, Exception> {

    /* renamed from: a */
    public c.a f192a = c.a.a;

    /* renamed from: b */
    public Context f193b;

    /* renamed from: c */
    public Messenger f194c;

    /* renamed from: d */
    public String f195d;

    /* renamed from: e */
    public boolean f196e;

    /* renamed from: f */
    public boolean f197f = false;

    /* renamed from: g */
    public C0049b f198g;

    /* renamed from: h */
    public Message f199h;

    /* renamed from: i */
    public Messenger f200i;

    /* renamed from: j */
    public boolean f201j = false;

    /* renamed from: k */
    public ServiceConnection f202k = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            C0051c.f218b.debug("MVPN-TunnelHelper", "Connected to MITM service.");
            Messenger unused = C0046a.this.f200i = new Messenger(iBinder);
            boolean unused2 = C0046a.this.f201j = true;
            C0051c.f218b.debug("MVPN-TunnelHelper", "Initializing MITM service.");
            C0046a aVar = C0046a.this;
            aVar.m163a(aVar.f200i, C0046a.this.f199h);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            C0051c.f218b.debug("MVPN-TunnelHelper", "Disconnected from MITM service.");
            Messenger unused = C0046a.this.f200i = null;
            boolean unused2 = C0046a.this.f201j = false;
        }
    };

    /* renamed from: com.citrix.mvpn.helper.a$a */
    /* compiled from: PG */
    public static class C0048a extends Handler {

        /* renamed from: a */
        public Context f204a;

        /* renamed from: b */
        public Messenger f205b;

        public C0048a(Context context, Messenger messenger) {
            this.f204a = context;
            this.f205b = messenger;
        }

        public void handleMessage(Message message) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Within AuthenticationHandler handleMessage():");
            a.append(message.what);
            loggingAPI.debug("MVPN-TunnelHelper", a.toString());
            int i = message.what;
            if (i == 0) {
                try {
                    C0040d a2 = C0040d.m99d().mo172a(this.f204a);
                    if (a2 != null) {
                        LoggingAPI loggingAPI2 = C0051c.f218b;
                        loggingAPI2.debug("MVPN-TunnelHelper", "Tunnel Config: " + a2.toString());
                        C0050b.m175a(this.f204a, this.f205b);
                    } else {
                        C0051c.f218b.error("MVPN-TunnelHelper", "Could not retrieve Tunnel Config.");
                        throw new TunnelConfigException(C0053d.m203a(this.f204a, wx0.MVPN_APPINFO_BUNDLE_EXCEPTION));
                    }
                } catch (TunnelConfigException | PolicyConfigException e) {
                    C0051c.f218b.error("MVPN-TunnelHelper", e.getMessage(), e);
                    message.what = ResponseStatusCode.START_TUNNEL_FAILED.getValue();
                }
            } else if (i == 1) {
                C0051c.f218b.error("MVPN-TunnelHelper", "Authentication Failed...");
            }
            if (message.what != 0) {
                try {
                    this.f205b.send(Message.obtain(message));
                    C0051c.f218b.debug("MVPN-TunnelHelper", "Sent message using messenger successfully!");
                } catch (RemoteException e2) {
                    C0051c.f218b.error("MVPN-TunnelHelper", e2.getMessage(), e2);
                }
            }
        }
    }

    /* renamed from: com.citrix.mvpn.helper.a$b */
    /* compiled from: PG */
    public static class C0049b extends Handler {

        /* renamed from: a */
        public Context f206a;

        /* renamed from: b */
        public ServiceConnection f207b;

        public C0049b(Context context, ServiceConnection serviceConnection) {
            this.f206a = context;
            this.f207b = serviceConnection;
        }

        public void handleMessage(Message message) {
            ServiceConnection serviceConnection;
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Within MitmHandler handleMessage:");
            a.append(message.what);
            loggingAPI.debug10("MVPN-TunnelHelper", a.toString());
            int i = message.what;
            if (i == 0) {
                LoggingAPI loggingAPI2 = C0051c.f218b;
                StringBuilder a2 = Eo.a("Received Init Complete = ");
                a2.append(message.what);
                loggingAPI2.debug("MVPN-TunnelHelper", a2.toString());
            } else if (i != 1) {
                LoggingAPI loggingAPI3 = C0051c.f218b;
                StringBuilder a3 = Eo.a("Received unknown message = ");
                a3.append(message.what);
                loggingAPI3.debug("MVPN-TunnelHelper", a3.toString());
                super.handleMessage(message);
            }
            Context context = this.f206a;
            if (context != null && (serviceConnection = this.f207b) != null) {
                try {
                    context.unbindService(serviceConnection);
                } catch (IllegalArgumentException unused) {
                    C0051c.f218b.debug("MVPN-TunnelHelper", "Service is not registered, nothing to unbind.");
                }
            }
        }
    }

    public C0046a(Context context, Messenger messenger) {
        C0040d.m97a(PolicyAPI.ProviderType.SECURE_HUB);
        m161a(context, messenger);
    }

    public C0046a(Context context, Messenger messenger, List<Map<String, String>> list, String str) {
        C0040d.m97a(PolicyAPI.ProviderType.INTUNE_COMPANY_PORTAL);
        m161a(context, messenger);
        this.f195d = str;
        this.f197f = true;
        C0036b.m80a().mo178a(list);
    }

    /* renamed from: a */
    private void m161a(Context context, Messenger messenger) {
        this.f199h = new Message();
        this.f193b = context;
        this.f194c = messenger;
        this.f198g = new C0049b(this.f193b, this.f202k);
    }

    /* renamed from: a */
    private synchronized void m162a(Context context, Messenger messenger, C0040d dVar, Handler handler) throws NetworkTunnelStartFailedException {
        C0051c.f218b.debug10("MVPN-TunnelHelper", "Starting proxy service...");
        Intent intent = new Intent(context, MITMService.class);
        intent.setClassName(context.getPackageName(), MITMService.class.getCanonicalName());
        intent.setAction("com.citrix.mam.intent.action.StartProxySvc");
        Messenger messenger2 = new Messenger(handler);
        Bundle bundle = new Bundle();
        bundle.putString("ProcessKey", C0053d.m202a(context));
        bundle.putParcelable("Args", dVar);
        bundle.putParcelable("MVPNMessenger", messenger);
        this.f199h.what = 1;
        this.f199h.replyTo = messenger2;
        this.f199h.setData(bundle);
        if (!this.f201j) {
            C0051c.f218b.debug("MVPN-TunnelHelper", "Binding to MITM service.");
            if (!context.bindService(intent, this.f202k, 1)) {
                throw new NetworkTunnelStartFailedException(C0053d.m203a(context, wx0.MVPN_UNABLE_TO_START_MITM_PROXY_SERVICE));
            }
        } else {
            C0051c.f218b.debug("MVPN-TunnelHelper", "Initializing MITM service.");
            m163a(this.f200i, this.f199h);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m163a(Messenger messenger, Message message) {
        LoggingAPI loggingAPI;
        StringBuilder sb;
        Messenger messenger2 = new Messenger(this.f198g);
        Message message2 = null;
        try {
            messenger.send(message);
            C0051c.f218b.debug("MVPN-TunnelHelper", "Initialize message sent to MITM service.");
            return;
            sb.append("Could not send MITM_INITFAIL message to handler.");
            sb.append(e.getMessage());
            loggingAPI.error("MVPN-TunnelHelper", sb.toString());
        } catch (NullPointerException e) {
            message2 = Message.obtain((Handler) null, 1);
            LoggingAPI loggingAPI2 = C0051c.f218b;
            loggingAPI2.error("MVPN-TunnelHelper", "Could not send init message to MITM service." + e.getMessage());
            if (message2 != null) {
                try {
                    messenger2.send(message2);
                } catch (RemoteException e2) {
                    e = e2;
                    loggingAPI = C0051c.f218b;
                    sb = new StringBuilder();
                }
            }
        } catch (RemoteException e3) {
            message2 = Message.obtain((Handler) null, 1);
            LoggingAPI loggingAPI3 = C0051c.f218b;
            loggingAPI3.error("MVPN-TunnelHelper", "Could not send init message to MITM service." + e3.getMessage());
            if (message2 != null) {
                try {
                    messenger2.send(message2);
                } catch (RemoteException e4) {
                    e = e4;
                    loggingAPI = C0051c.f218b;
                    sb = new StringBuilder();
                }
            }
        } catch (Throwable th) {
            if (message2 != null) {
                try {
                    messenger2.send(message2);
                } catch (RemoteException e5) {
                    LoggingAPI loggingAPI4 = C0051c.f218b;
                    StringBuilder a = Eo.a("Could not send MITM_INITFAIL message to handler.");
                    a.append(e5.getMessage());
                    loggingAPI4.error("MVPN-TunnelHelper", a.toString());
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public Exception doInBackground(Void... voidArr) {
        TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
        MicroVPNSDK.initialize(this.f193b);
        try {
            C0051c.f218b.info("MVPN-TunnelHelper", "Starting Network Tunnel Async Task...");
            if (!C0054e.m206a(this.f193b, this.f194c)) {
                return null;
            }
            C0040d a = C0040d.m99d().mo172a(this.f193b);
            if (this.f197f) {
                mo263a(a, new d());
            }
            if (a != null && a.mo209e()) {
                LoggingAPI loggingAPI = C0051c.f218b;
                loggingAPI.debug("MVPN-TunnelHelper", "Using Tunnel Config: " + a.toString());
                m162a(this.f193b, this.f194c, a, this.f198g);
                return null;
            } else if (this.f197f) {
                return null;
            } else {
                C0051c.f218b.debug("MVPN-TunnelHelper", "Logon is Required.");
                this.f196e = true;
                C0040d.m98c();
                return null;
            }
        } catch (Exception e) {
            LoggingAPI loggingAPI2 = C0051c.f218b;
            StringBuilder a2 = Eo.a("Exception Occurred while executing Network Tunnel Async Task...");
            a2.append(e.getMessage());
            loggingAPI2.error("MVPN-TunnelHelper", a2.toString());
            return e;
        }
    }

    /* renamed from: a */
    public void mo263a(C0040d dVar, d dVar2) throws NsgAuthException, TunnelConfigException {
        if (dVar != null) {
            a aVar = new a();
            if (!TextUtils.isEmpty(dVar.mo220p())) {
                aVar.a(dVar.mo220p());
                aVar.b(this.f195d);
                this.f192a = dVar2.a(aVar);
                c.a aVar2 = this.f192a;
                if (aVar2 == c.a.a) {
                    C0036b.m80a().mo177a(dVar2);
                    return;
                }
                throw new NsgAuthException(c.a(aVar2));
            }
            throw new TunnelConfigException("Intune Gateway FQDN is missing.");
        }
        throw new TunnelConfigException("Intune App Config Data is null.");
    }

    /* renamed from: a */
    public void onPostExecute(Exception e) {
        super.onPostExecute(e);
        LoggingAPI loggingAPI = C0051c.f218b;
        StringBuilder a = Eo.a("Calling post execute. Login Required: ");
        a.append(this.f196e);
        loggingAPI.debug10("MVPN-TunnelHelper", a.toString());
        if (e == null && this.f196e) {
            Context context = this.f193b;
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing()) {
                    try {
                        AuthSDK.getInstance().login(activity, new Messenger(new C0048a(activity, this.f194c)));
                    } catch (AuthenticationException e2) {
                        e = e2;
                        LoggingAPI loggingAPI2 = C0051c.f218b;
                        StringBuilder a2 = Eo.a("Authentication Exception Occurred: ");
                        a2.append(e.getMessage());
                        loggingAPI2.error("MVPN-TunnelHelper", a2.toString());
                    }
                } else {
                    e = new TunnelConfigException(C0053d.m203a(this.f193b, wx0.MVPN_LOGIN_ACTIVITY_FINISHING_EXCEPTION));
                }
            } else {
                C0051c.f218b.warning("MVPN-TunnelHelper", "Context has to be an activity for authentication.");
                e = new AuthenticationException(C0053d.m203a(this.f193b, wx0.MVPN_LOGIN_REQUIRED_EXCEPTION));
            }
        }
        if (e != null) {
            LoggingAPI loggingAPI3 = C0051c.f218b;
            StringBuilder a3 = Eo.a("Calling handle start tunnel exception: ");
            a3.append(e.getMessage());
            loggingAPI3.debug5("MVPN-TunnelHelper", a3.toString());
            mo266b(e);
        }
    }

    /* renamed from: a */
    public boolean mo265a() {
        if (!this.f197f) {
            return true;
        }
        try {
            C0040d a = C0040d.m99d().mo172a(this.f193b);
            return a != null && !TextUtils.isEmpty(a.mo220p());
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public void mo266b(Exception exc) {
        Context context;
        Messenger messenger;
        ResponseStatusCode responseStatusCode;
        c.a aVar;
        LoggingAPI loggingAPI = C0051c.f218b;
        loggingAPI.warning("MVPN-TunnelHelper", C0053d.m203a(this.f193b, wx0.MVPN_GENERIC_EXCEPTION) + " Exception:" + exc.getMessage());
        if (!mo265a()) {
            context = this.f193b;
            messenger = this.f194c;
            responseStatusCode = ResponseStatusCode.INVALID_APP_CONFIGURATION_DATA;
        } else if ((exc instanceof NsgAuthException) && ((aVar = this.f192a) == c.a.e || aVar == c.a.j)) {
            context = this.f193b;
            messenger = this.f194c;
            responseStatusCode = ResponseStatusCode.INVALID_OAUTH_TOKEN;
        } else if (exc instanceof AuthenticationException) {
            context = this.f193b;
            messenger = this.f194c;
            responseStatusCode = ResponseStatusCode.SESSION_EXPIRED;
        } else {
            context = this.f193b;
            messenger = this.f194c;
            responseStatusCode = ResponseStatusCode.START_TUNNEL_FAILED;
        }
        C0054e.m204a(context, messenger, responseStatusCode);
    }
}
