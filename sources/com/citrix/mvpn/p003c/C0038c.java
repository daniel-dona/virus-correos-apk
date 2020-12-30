package com.citrix.mvpn.p003c;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.citrix.MAM.Android.ManagedAppHelper.Interface.MAMAppInfo;
import com.citrix.mvpn.MAM.Android.AuthSSO.a.ac;
import com.citrix.mvpn.d.a.e;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.mvpn.helper.C0053d;
import com.citrix.sdk.config.api.PolicyAPI;
import com.citrix.sdk.config.exception.PolicyConfigException;
import com.citrix.sdk.config.model.Policies;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.citrix.mvpn.c.c */
/* compiled from: PG */
public class C0038c extends C0040d {
    public static final Parcelable.Creator<C0038c> CREATOR = new Parcelable.Creator<C0038c>() {
        /* renamed from: a */
        public C0038c createFromParcel(Parcel parcel) {
            return new C0038c(parcel);
        }

        /* renamed from: a */
        public C0038c[] newArray(int i) {
            return new C0038c[i];
        }
    };

    public C0038c() {
    }

    public C0038c(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: C */
    private void m86C() {
        C0040d.f153C.debug5("MVPN-SHTunnelConfig", "Resetting NSG Configurations");
        mo195a((String) null);
        mo199b((String) null);
        mo202c((String) null);
        mo197a((byte[]) null);
        mo198a((char[]) null);
        mo201b(false);
        mo204c(false);
        mo205d((String) null);
        this.f173q = new ArrayList();
        this.f174r = new ArrayList();
        this.f175s = new ArrayList();
        this.f178v = new ArrayList();
        this.f177u = new ArrayList();
        this.f172p = null;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x00b4 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Bundle m87a(android.content.Context r11, java.lang.String r12, boolean r13, boolean r14, java.lang.String r15) throws com.citrix.mvpn.exception.TunnelConfigException {
        /*
            r10 = this;
            android.content.ContentResolver r0 = r11.getContentResolver()
            java.lang.String r1 = "com.citrix.work.MDXProvider"
            android.content.ContentProviderClient r0 = r0.acquireContentProviderClient(r1)
            java.lang.String r8 = "MVPN-SHTunnelConfig"
            if (r0 == 0) goto L_0x00e7
            r9 = 0
            android.net.Uri$Builder r2 = new android.net.Uri$Builder     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r3 = "citrix"
            android.net.Uri$Builder r3 = r2.scheme(r3)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            android.net.Uri$Builder r3 = r3.path(r12)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r3.authority(r1)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r1 = "pkgName"
            java.lang.String r11 = r11.getPackageName()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r2.appendQueryParameter(r1, r11)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            if (r15 == 0) goto L_0x0031
            java.lang.String r11 = "pkgId"
            r2.appendQueryParameter(r11, r15)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
        L_0x0031:
            if (r14 == 0) goto L_0x003a
            java.lang.String r11 = "policyRefresh"
            java.lang.String r14 = "false"
            r2.appendQueryParameter(r11, r14)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
        L_0x003a:
            if (r13 == 0) goto L_0x0043
            java.lang.String r11 = "getKeys"
            java.lang.String r13 = "true"
            r2.appendQueryParameter(r11, r13)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
        L_0x0043:
            java.lang.String r11 = "appType"
            java.lang.String r13 = "PREMIUM"
            r2.appendQueryParameter(r11, r13)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            android.net.Uri r3 = r2.build()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            if (r11 == 0) goto L_0x009b
            boolean r13 = r11.moveToFirst()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            if (r13 == 0) goto L_0x0081
            java.lang.String r13 = "AppInfoBundle"
            int r13 = r11.getColumnIndex(r13)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r13 = r11.getString(r13)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r14 = 0
            byte[] r13 = android.util.Base64.decode(r13, r14)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            android.os.Parcel r15 = android.os.Parcel.obtain()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            int r1 = r13.length     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r15.unmarshall(r13, r14, r1)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r15.setDataPosition(r14)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            android.os.Bundle r9 = r15.readBundle()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r15.recycle()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            goto L_0x0097
        L_0x0081:
            com.citrix.sdk.logging.api.LoggingAPI r13 = com.citrix.mvpn.p003c.C0040d.f153C     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r14.<init>()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r15 = "Failed to read any results from call to "
            r14.append(r15)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r14.append(r12)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r14 = r14.toString()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r13.error(r8, r14)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
        L_0x0097:
            r11.close()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            goto L_0x00df
        L_0x009b:
            com.citrix.sdk.logging.api.LoggingAPI r11 = com.citrix.mvpn.p003c.C0040d.f153C     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r13.<init>()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r14 = "Failed to get any results from call to "
            r13.append(r14)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r13.append(r12)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            java.lang.String r13 = r13.toString()     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            r11.error(r8, r13)     // Catch:{ IllegalArgumentException -> 0x00cb, RemoteException -> 0x00b4 }
            goto L_0x00df
        L_0x00b2:
            r11 = move-exception
            goto L_0x00e3
        L_0x00b4:
            com.citrix.sdk.logging.api.LoggingAPI r11 = com.citrix.mvpn.p003c.C0040d.f153C     // Catch:{ all -> 0x00b2 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r13.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r14 = "Got remote exception from MDXProviderClient path = "
            r13.append(r14)     // Catch:{ all -> 0x00b2 }
            r13.append(r12)     // Catch:{ all -> 0x00b2 }
            java.lang.String r12 = r13.toString()     // Catch:{ all -> 0x00b2 }
        L_0x00c7:
            r11.error(r8, r12)     // Catch:{ all -> 0x00b2 }
            goto L_0x00df
        L_0x00cb:
            com.citrix.sdk.logging.api.LoggingAPI r11 = com.citrix.mvpn.p003c.C0040d.f153C     // Catch:{ all -> 0x00b2 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r13.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r14 = "Got illegal argument exception from MDXProviderClient path = "
            r13.append(r14)     // Catch:{ all -> 0x00b2 }
            r13.append(r12)     // Catch:{ all -> 0x00b2 }
            java.lang.String r12 = r13.toString()     // Catch:{ all -> 0x00b2 }
            goto L_0x00c7
        L_0x00df:
            r0.release()
            return r9
        L_0x00e3:
            r0.release()
            throw r11
        L_0x00e7:
            com.citrix.sdk.logging.api.LoggingAPI r12 = com.citrix.mvpn.p003c.C0040d.f153C
            int r13 = wx0.MVPN_MISSING_PROVIDER
            java.lang.String r13 = com.citrix.mvpn.helper.C0053d.m203a(r11, r13)
            r12.error(r8, r13)
            com.citrix.mvpn.exception.TunnelConfigException r12 = new com.citrix.mvpn.exception.TunnelConfigException
            int r13 = wx0.MVPN_MISSING_PROVIDER
            java.lang.String r11 = com.citrix.mvpn.helper.C0053d.m203a(r11, r13)
            r12.<init>((java.lang.String) r11)
            goto L_0x00ff
        L_0x00fe:
            throw r12
        L_0x00ff:
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.p003c.C0038c.m87a(android.content.Context, java.lang.String, boolean, boolean, java.lang.String):android.os.Bundle");
    }

    /* renamed from: a */
    public static synchronized C0038c m88a() {
        C0038c cVar;
        synchronized (C0038c.class) {
            if (C0040d.f154D == null) {
                C0040d.f153C.debug10("MVPN-SHTunnelConfig", "Creating new instance of SecureHub Tunnel Configuration");
                C0040d.f154D = new C0038c();
            }
            cVar = (C0038c) C0040d.f154D;
        }
        return cVar;
    }

    /* renamed from: a */
    private void m89a(Bundle bundle) {
        ac.a aVar;
        C0040d.f153C.debug5("MVPN-SHTunnelConfig", "Refreshing Netscaler Gateway config.");
        String string = bundle.getString("COOKIE");
        String string2 = bundle.getString(MAMAppInfo.KEY_TRANSLATED_URL);
        String string3 = bundle.getString(MAMAppInfo.KEY_AG_ADDRESS);
        mo195a(string);
        mo199b(string2);
        mo205d(string3);
        LoggingAPI loggingAPI = C0040d.f153C;
        StringBuilder a = Eo.a("Cookie is valid: ");
        a.append(!TextUtils.isEmpty(string));
        loggingAPI.debug10("MVPN-SHTunnelConfig", a.toString());
        LoggingAPI loggingAPI2 = C0040d.f153C;
        loggingAPI2.debug10("MVPN-SHTunnelConfig", "Traslated Url: " + string2);
        LoggingAPI loggingAPI3 = C0040d.f153C;
        loggingAPI3.debug10("MVPN-SHTunnelConfig", "AG address: " + string3);
        mo202c(bundle.getString("User-Agent", e.a()));
        mo197a(bundle.getByteArray("USER_ACCEPTED_CERTS_KEY_STORE"));
        mo198a(bundle.getCharArray("USER_ACCEPTED_CERTS_KEY_STORE_PASSWORD"));
        mo201b(bundle.getBoolean("fips", false));
        mo204c(bundle.getBoolean("TrustAllCerts", false));
        if (bundle.getStringArray(MAMAppInfo.KEY_SUFFIX_LIST) != null) {
            this.f173q = Arrays.asList(bundle.getStringArray(MAMAppInfo.KEY_SUFFIX_LIST));
        }
        if (bundle.getStringArray("IntranetIpList") != null) {
            this.f174r = Arrays.asList(bundle.getStringArray("IntranetIpList"));
        }
        if (bundle.getStringArray("IntranetAppList") != null) {
            this.f175s = Arrays.asList(bundle.getStringArray("IntranetAppList"));
        }
        if (bundle.getStringArrayList(MAMAppInfo.KEY_TUNNEL_EXCLUDE_DOMAINS) != null) {
            this.f178v = bundle.getStringArrayList(MAMAppInfo.KEY_TUNNEL_EXCLUDE_DOMAINS);
        }
        if (bundle.getStringArrayList(MAMAppInfo.KEY_TUNNEL_EXCLUDE_IPS) != null) {
            this.f177u = bundle.getStringArrayList(MAMAppInfo.KEY_TUNNEL_EXCLUDE_IPS);
        }
        if (!bundle.getBoolean(MAMAppInfo.KEY_SPLIT_TUNNEL_OFF)) {
            if (bundle.getBoolean("SplitTunnelOn")) {
                aVar = ac.a.b;
            } else if (bundle.getBoolean(MAMAppInfo.KEY_SPLIT_TUNNEL_REVERSE)) {
                aVar = ac.a.c;
            }
            this.f172p = aVar;
            this.f171o = new ac(this);
        }
        aVar = ac.a.a;
        this.f172p = aVar;
        this.f171o = new ac(this);
    }

    /* renamed from: b */
    private void m90b(Bundle bundle) throws PolicyConfigException {
        C0040d.f153C.debug5("MVPN-SHTunnelConfig", "Refreshing XMS policies.");
        Policies policies = PolicyAPI.getInstance().getPolicies(bundle);
        if (policies != null) {
            mo206d((List<String>) policies.getMvpnExcludeDomains());
            this.f179w = policies.getBackgroundServicesList();
            this.f182z = policies.getManagementMode();
            this.f156A = policies.getMvpnNetworkAccess();
            C0040d.f153C.debug5("MVPN-SHTunnelConfig", "XMS policies refresh successful!");
            return;
        }
        throw new PolicyConfigException("Unable to retrieve XMS Policies");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1 = com.citrix.mvpn.p003c.C0040d.f153C;
        r1.error("MVPN-SHTunnelConfig", "Got remote exception from MDXProvider path = " + "getTransLatedUrl");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x009b, code lost:
        r11.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x009e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x007d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x007f */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Bundle m91c(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "getTransLatedUrl"
            android.content.ContentResolver r11 = r11.getContentResolver()
            java.lang.String r1 = "com.citrix.work.MDXProvider"
            android.content.ContentProviderClient r11 = r11.acquireContentProviderClient(r1)
            r8 = 0
            if (r11 == 0) goto L_0x009f
            android.net.Uri$Builder r2 = new android.net.Uri$Builder     // Catch:{ RemoteException -> 0x007f }
            r2.<init>()     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r3 = "citrix"
            android.net.Uri$Builder r3 = r2.scheme(r3)     // Catch:{ RemoteException -> 0x007f }
            android.net.Uri$Builder r3 = r3.path(r0)     // Catch:{ RemoteException -> 0x007f }
            r3.authority(r1)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r1 = "urltorewrite"
            java.lang.String r3 = "/AGServices/rewriteMode"
            r2.appendQueryParameter(r1, r3)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r1 = "SBCapable"
            r9 = 0
            java.lang.String r3 = java.lang.Boolean.toString(r9)     // Catch:{ RemoteException -> 0x007f }
            r2.appendQueryParameter(r1, r3)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r1 = "prefixAG"
            r3 = 1
            java.lang.String r3 = java.lang.Boolean.toString(r3)     // Catch:{ RemoteException -> 0x007f }
            r2.appendQueryParameter(r1, r3)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r1 = "certPinningVersion"
            java.lang.String r3 = "2"
            r2.appendQueryParameter(r1, r3)     // Catch:{ RemoteException -> 0x007f }
            android.net.Uri r3 = r2.build()     // Catch:{ RemoteException -> 0x007f }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r11
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ RemoteException -> 0x007f }
            if (r1 == 0) goto L_0x0097
            boolean r2 = r1.moveToFirst()     // Catch:{ RemoteException -> 0x007f }
            if (r2 == 0) goto L_0x0079
            java.lang.String r2 = "translatedtoken"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ RemoteException -> 0x007f }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ RemoteException -> 0x007f }
            r3 = 2
            byte[] r2 = android.util.Base64.decode(r2, r3)     // Catch:{ RemoteException -> 0x007f }
            android.os.Parcel r3 = android.os.Parcel.obtain()     // Catch:{ RemoteException -> 0x007f }
            int r4 = r2.length     // Catch:{ RemoteException -> 0x007f }
            r3.unmarshall(r2, r9, r4)     // Catch:{ RemoteException -> 0x007f }
            r3.setDataPosition(r9)     // Catch:{ RemoteException -> 0x007f }
            android.os.Bundle r8 = r3.readBundle()     // Catch:{ RemoteException -> 0x007f }
            r3.recycle()     // Catch:{ RemoteException -> 0x007f }
        L_0x0079:
            r1.close()     // Catch:{ RemoteException -> 0x007f }
            goto L_0x0097
        L_0x007d:
            r0 = move-exception
            goto L_0x009b
        L_0x007f:
            com.citrix.sdk.logging.api.LoggingAPI r1 = com.citrix.mvpn.p003c.C0040d.f153C     // Catch:{ all -> 0x007d }
            java.lang.String r2 = "MVPN-SHTunnelConfig"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r3.<init>()     // Catch:{ all -> 0x007d }
            java.lang.String r4 = "Got remote exception from MDXProvider path = "
            r3.append(r4)     // Catch:{ all -> 0x007d }
            r3.append(r0)     // Catch:{ all -> 0x007d }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x007d }
            r1.error(r2, r0)     // Catch:{ all -> 0x007d }
        L_0x0097:
            r11.release()
            goto L_0x009f
        L_0x009b:
            r11.release()
            throw r0
        L_0x009f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.p003c.C0038c.m91c(android.content.Context):android.os.Bundle");
    }

    /* renamed from: b */
    public synchronized C0038c mo172a(Context context) throws TunnelConfigException, PolicyConfigException {
        if (!C0040d.f152B) {
            C0040d.f153C.debug("MVPN-SHTunnelConfig", "Before calling get App Info");
            Bundle a = m87a(context, "appRefresh", false, false, (String) null);
            if (a != null) {
                m90b(a);
                C0040d.f153C.debug("MVPN-SHTunnelConfig", "Before retrieving SecureHub Translated Url bundle.");
                Bundle c = m91c(context);
                if (c != null) {
                    C0040d.f153C.debug10("MVPN-SHTunnelConfig", "Updating Netscaler config.");
                    m89a(c);
                    C0040d.f152B = true;
                } else {
                    m86C();
                    C0040d.f153C.error("MVPN-SHTunnelConfig", "Unable to retrieve SecureHub Translated Url bundle.");
                }
            } else {
                throw new TunnelConfigException("AppInfo:" + C0053d.m203a(context, wx0.MVPN_APPINFO_BUNDLE_EXCEPTION));
            }
        }
        return this;
    }

    /* renamed from: b */
    public String mo184b() {
        if (this.f159c == null) {
            this.f159c = URI.create(this.f160d).getHost();
        }
        return this.f159c;
    }
}
