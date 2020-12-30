package com.citrix.mvpn.api;

import android.os.Handler;
import android.os.Looper;

/* compiled from: PG */
public class MvpnDefaultHandler extends Handler {
    public static final String TAG = "MVPN-DefaultHandler";

    /* renamed from: a */
    public boolean f135a;

    /* renamed from: b */
    public boolean f136b;

    /* renamed from: c */
    public boolean f137c = true;

    /* renamed from: d */
    public boolean f138d = true;

    /* renamed from: com.citrix.mvpn.api.MvpnDefaultHandler$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C00261 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f139a = new int[ResponseStatusCode.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x004b */
        static {
            /*
                com.citrix.mvpn.api.ResponseStatusCode[] r0 = com.citrix.mvpn.api.ResponseStatusCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f139a = r0
                r0 = 1
                int[] r1 = f139a     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.citrix.mvpn.api.ResponseStatusCode r2 = com.citrix.mvpn.api.ResponseStatusCode.START_TUNNEL_SUCCESS     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                r1 = 2
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.START_TUNNEL_FAILED     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                r0 = 3
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.TUNNEL_ALREADY_RUNNING     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r1 = 4
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x0026 }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.SESSION_EXPIRED     // Catch:{ NoSuchFieldError -> 0x0026 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0026 }
            L_0x0026:
                r0 = 5
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x002d }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.FOUND_LEGACY_MODE     // Catch:{ NoSuchFieldError -> 0x002d }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                r1 = 6
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x0034 }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.FOUND_NON_WEBSSO_MODE     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                r0 = 7
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x003b }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.FOUND_NON_MANAGED_APP     // Catch:{ NoSuchFieldError -> 0x003b }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                r1 = 8
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.NO_NETWORK_CONNECTION     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                r0 = 9
                int[] r2 = f139a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.citrix.mvpn.api.ResponseStatusCode r3 = com.citrix.mvpn.api.ResponseStatusCode.INVALID_APP_CONFIGURATION_DATA     // Catch:{ NoSuchFieldError -> 0x004b }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r1 = f139a     // Catch:{ NoSuchFieldError -> 0x0053 }
                com.citrix.mvpn.api.ResponseStatusCode r2 = com.citrix.mvpn.api.ResponseStatusCode.INVALID_OAUTH_TOKEN     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2 = 10
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.api.MvpnDefaultHandler.C00261.<clinit>():void");
        }
    }

    public MvpnDefaultHandler() {
    }

    public MvpnDefaultHandler(Looper looper) {
        super(looper);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0065, code lost:
        r6.info(TAG, r0);
        r5.f135a = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
        android.util.Log.e(TAG, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r6) {
        /*
            r5 = this;
            com.citrix.sdk.logging.api.LoggingAPI r0 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.String r1 = "Within handleMessage(): Status Code: "
            java.lang.StringBuilder r1 = Eo.a(r1)
            int r2 = r6.what
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MVPN-DefaultHandler"
            r0.debug(r2, r1)
            int r0 = r6.what
            com.citrix.mvpn.api.ResponseStatusCode r0 = com.citrix.mvpn.api.ResponseStatusCode.fromId(r0)
            r1 = 0
            r5.f135a = r1
            r5.f136b = r1
            r3 = 1
            r5.f137c = r3
            r5.f138d = r3
            int[] r4 = com.citrix.mvpn.api.MvpnDefaultHandler.C00261.f139a
            int r0 = r0.ordinal()
            r0 = r4[r0]
            switch(r0) {
                case 1: goto L_0x0061;
                case 2: goto L_0x0059;
                case 3: goto L_0x0054;
                case 4: goto L_0x004a;
                case 5: goto L_0x0044;
                case 6: goto L_0x0041;
                case 7: goto L_0x003e;
                case 8: goto L_0x003b;
                case 9: goto L_0x0038;
                case 10: goto L_0x0035;
                default: goto L_0x0031;
            }
        L_0x0031:
            super.handleMessage(r6)
            goto L_0x006a
        L_0x0035:
            r5.f138d = r1
            goto L_0x006a
        L_0x0038:
            r5.f137c = r1
            goto L_0x006a
        L_0x003b:
            java.lang.String r6 = "Failed to start tunnel. No Network!!!"
            goto L_0x0046
        L_0x003e:
            java.lang.String r6 = "Could not retrieve policies!!! \n This could be because of the following reasons: \n\t 1. SecureHub is not installed.\n\t 2. SecureHub enrollment is not completed.\n\t 3. App is not managed through CEM."
            goto L_0x0046
        L_0x0041:
            java.lang.String r6 = "Cannot start tunnel for NetworkAccess mode other than Tunneled - Web SSO!!!"
            goto L_0x0046
        L_0x0044:
            java.lang.String r6 = "Cannot start tunnel for Legacy ManagementMode!!!"
        L_0x0046:
            android.util.Log.e(r2, r6)
            goto L_0x006a
        L_0x004a:
            com.citrix.sdk.logging.api.LoggingAPI r6 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.String r0 = "Session Expired!!!"
            r6.info(r2, r0)
            r5.f136b = r3
            goto L_0x006a
        L_0x0054:
            com.citrix.sdk.logging.api.LoggingAPI r6 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.String r0 = "Tunnel is already running."
            goto L_0x0065
        L_0x0059:
            com.citrix.sdk.logging.api.LoggingAPI r6 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.String r0 = "Failed to start tunnel!!!"
            r6.error(r2, r0)
            goto L_0x006a
        L_0x0061:
            com.citrix.sdk.logging.api.LoggingAPI r6 = com.citrix.mvpn.helper.C0051c.f218b
            java.lang.String r0 = "Tunnel started successfully!!!"
        L_0x0065:
            r6.info(r2, r0)
            r5.f135a = r3
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.api.MvpnDefaultHandler.handleMessage(android.os.Message):void");
    }

    public boolean isAppConfigDataValid() {
        return this.f137c;
    }

    public boolean isNetworkTunnelRunning() {
        return this.f135a;
    }

    public boolean isOauthTokenValid() {
        return this.f138d;
    }

    public boolean isSessionExpired() {
        return this.f136b;
    }
}
