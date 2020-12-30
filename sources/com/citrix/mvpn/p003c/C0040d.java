package com.citrix.mvpn.p003c;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.citrix.mvpn.MAM.Android.AuthSSO.a.ac;
import com.citrix.mvpn.MAM.Android.AuthSSO.d.d;
import com.citrix.mvpn.exception.TunnelConfigException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.helper.MvpnProxy;
import com.citrix.sdk.appcore.api.MamSdk;
import com.citrix.sdk.config.api.PolicyAPI;
import com.citrix.sdk.config.exception.PolicyConfigException;
import com.citrix.sdk.config.model.Policies;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.citrix.mvpn.c.d */
/* compiled from: PG */
public abstract class C0040d implements Parcelable {

    /* renamed from: B */
    public static boolean f152B = false;

    /* renamed from: C */
    public static final LoggingAPI f153C = MamSdk.getLogger();

    /* renamed from: D */
    public static C0040d f154D = null;

    /* renamed from: E */
    public static String f155E = "MVPN-TunnelConfiguration";

    /* renamed from: A */
    public Policies.MamSdkMvpnNetworkAccess f156A = Policies.MamSdkMvpnNetworkAccess.MvpnNetworkAccessTunneledWebSSO;

    /* renamed from: a */
    public String f157a = null;

    /* renamed from: b */
    public boolean f158b = false;

    /* renamed from: c */
    public String f159c = null;

    /* renamed from: d */
    public String f160d = null;

    /* renamed from: e */
    public boolean f161e = false;

    /* renamed from: f */
    public String f162f = null;

    /* renamed from: g */
    public byte[] f163g = null;

    /* renamed from: h */
    public char[] f164h = null;

    /* renamed from: i */
    public boolean f165i = false;

    /* renamed from: j */
    public boolean f166j = false;

    /* renamed from: k */
    public String f167k = null;

    /* renamed from: l */
    public boolean f168l;

    /* renamed from: m */
    public int f169m = -1;

    /* renamed from: n */
    public HashMap<String, ArrayList<String>> f170n = null;

    /* renamed from: o */
    public ac f171o = null;

    /* renamed from: p */
    public ac.a f172p = null;

    /* renamed from: q */
    public List<String> f173q = new ArrayList();

    /* renamed from: r */
    public List<String> f174r = new ArrayList();

    /* renamed from: s */
    public List<String> f175s = new ArrayList();

    /* renamed from: t */
    public List<String> f176t = new ArrayList();

    /* renamed from: u */
    public List<String> f177u = new ArrayList();

    /* renamed from: v */
    public List<String> f178v = Arrays.asList(new String[]{"127.0.0.1/8"});

    /* renamed from: w */
    public List<String> f179w = new ArrayList();

    /* renamed from: x */
    public boolean f180x = false;

    /* renamed from: y */
    public boolean f181y = false;

    /* renamed from: z */
    public Policies.ManagementMode f182z = Policies.ManagementMode.LegacyWrapping;

    /* renamed from: com.citrix.mvpn.c.d$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C00411 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f183a = new int[PolicyAPI.ProviderType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0018 */
        static {
            /*
                com.citrix.sdk.config.api.PolicyAPI$ProviderType[] r0 = com.citrix.sdk.config.api.PolicyAPI.ProviderType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f183a = r0
                r0 = 1
                r1 = 2
                int[] r2 = f183a     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.citrix.sdk.config.api.PolicyAPI$ProviderType r3 = com.citrix.sdk.config.api.PolicyAPI.ProviderType.AUTHMANLITE     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r2 = f183a     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.citrix.sdk.config.api.PolicyAPI$ProviderType r3 = com.citrix.sdk.config.api.PolicyAPI.ProviderType.SECURE_HUB     // Catch:{ NoSuchFieldError -> 0x0018 }
                r3 = 0
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                int[] r1 = f183a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.citrix.sdk.config.api.PolicyAPI$ProviderType r2 = com.citrix.sdk.config.api.PolicyAPI.ProviderType.INTUNE_COMPANY_PORTAL     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 3
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.p003c.C0040d.C00411.<clinit>():void");
        }
    }

    public C0040d() {
    }

    public C0040d(Parcel parcel) {
        this.f161e = parcel.readByte() != 0;
        this.f165i = parcel.readByte() != 0;
        this.f160d = parcel.readString();
        this.f167k = parcel.readString();
        this.f162f = parcel.readString();
        this.f168l = parcel.readByte() != 0;
        this.f163g = parcel.createByteArray();
        this.f164h = parcel.createCharArray();
        this.f169m = parcel.readInt();
        this.f157a = parcel.readString();
        this.f158b = parcel.readByte() != 0;
        this.f166j = parcel.readByte() != 0;
        this.f181y = parcel.readByte() != 0;
        this.f180x = parcel.readByte() != 0;
        this.f175s = parcel.createStringArrayList();
        this.f174r = parcel.createStringArrayList();
        int readInt = parcel.readInt();
        this.f173q = parcel.createStringArrayList();
        this.f178v = parcel.createStringArrayList();
        this.f177u = parcel.createStringArrayList();
        this.f176t = parcel.createStringArrayList();
        this.f179w = parcel.createStringArrayList();
        if (readInt > -1) {
            this.f172p = ac.a.values()[readInt];
        }
        List<String> list = this.f178v;
        if (list == null || list.isEmpty()) {
            this.f178v = Arrays.asList(new String[]{"127.0.0.1/8"});
        } else if (!this.f178v.contains("127.0.0.1/8")) {
            this.f178v.add("127.0.0.1/8");
        }
        this.f171o = new ac(this);
    }

    /* renamed from: a */
    public static synchronized void m97a(PolicyAPI.ProviderType providerType) {
        synchronized (C0040d.class) {
            if (PolicyAPI.getConfigProviderType() != providerType) {
                PolicyAPI.setConfigProviderType(providerType);
                m98c();
            }
        }
    }

    /* renamed from: c */
    public static void m98c() {
        f153C.debug5(f155E, "Resetting Tunnel Configuration.");
        f154D = null;
        f152B = false;
        C0050b.m180a((MvpnProxy) null);
        C0050b.m179a(C0051c.m201b());
        C0050b.m181a((Policies) null);
        C0050b.m182a((Boolean) null);
        C0050b.m184b((Boolean) null);
        d.b();
    }

    /* renamed from: d */
    public static synchronized C0040d m99d() {
        C0040d dVar;
        C0040d a;
        synchronized (C0040d.class) {
            if (f154D == null) {
                f153C.debug5(f155E, "Creating new instance for Tunnel Configuration.");
                int i = C00411.f183a[PolicyAPI.getConfigProviderType().ordinal()];
                if (i == 1) {
                    a = C0034a.m76a();
                } else if (i == 2) {
                    a = C0038c.m88a();
                } else if (i == 3) {
                    a = C0036b.m80a();
                } else {
                    throw new UnsupportedOperationException("Operation not supported.");
                }
                f154D = a;
            }
            dVar = f154D;
        }
        return dVar;
    }

    /* renamed from: A */
    public boolean mo189A() {
        return this.f180x;
    }

    /* renamed from: B */
    public Policies.MamSdkMvpnNetworkAccess mo190B() {
        return this.f156A;
    }

    /* renamed from: a */
    public abstract C0040d mo172a(Context context) throws TunnelConfigException, PolicyConfigException;

    /* renamed from: a */
    public void mo191a(ac.a aVar) {
        this.f172p = aVar;
    }

    /* renamed from: a */
    public void mo192a(ac acVar) {
        this.f171o = acVar;
    }

    /* renamed from: a */
    public void mo193a(Policies.MamSdkMvpnNetworkAccess mamSdkMvpnNetworkAccess) {
        this.f156A = mamSdkMvpnNetworkAccess;
    }

    /* renamed from: a */
    public void mo194a(Policies.ManagementMode managementMode) {
        this.f182z = managementMode;
    }

    /* renamed from: a */
    public void mo195a(String str) {
        this.f157a = str;
    }

    /* renamed from: a */
    public void mo196a(boolean z) {
        this.f158b = z;
    }

    /* renamed from: a */
    public void mo197a(byte[] bArr) {
        this.f163g = bArr;
    }

    /* renamed from: a */
    public void mo198a(char[] cArr) {
        this.f164h = cArr;
    }

    /* renamed from: b */
    public String mo184b() {
        return this.f159c;
    }

    /* renamed from: b */
    public void mo199b(String str) {
        this.f160d = str;
    }

    /* renamed from: b */
    public void mo200b(List<String> list) {
        this.f173q = list;
    }

    /* renamed from: b */
    public void mo201b(boolean z) {
        this.f165i = z;
    }

    /* renamed from: c */
    public void mo202c(String str) {
        this.f162f = str;
    }

    /* renamed from: c */
    public void mo203c(List<String> list) {
        this.f174r = list;
    }

    /* renamed from: c */
    public void mo204c(boolean z) {
        this.f166j = z;
    }

    /* renamed from: d */
    public void mo205d(String str) {
        this.f167k = str;
    }

    /* renamed from: d */
    public void mo206d(List<String> list) {
        this.f176t = list;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public void mo208e(List<String> list) {
        this.f179w = list;
    }

    /* renamed from: e */
    public boolean mo209e() {
        return !TextUtils.isEmpty(this.f157a) && !this.f158b && !TextUtils.isEmpty(this.f167k);
    }

    /* renamed from: f */
    public boolean mo210f() {
        return mo190B() == Policies.MamSdkMvpnNetworkAccess.MvpnNetworkAccessTunneledWebSSO;
    }

    /* renamed from: g */
    public String mo211g() {
        return this.f157a;
    }

    /* renamed from: h */
    public boolean mo212h() {
        return this.f158b;
    }

    /* renamed from: i */
    public String mo213i() {
        return this.f160d;
    }

    /* renamed from: j */
    public boolean mo214j() {
        return this.f161e;
    }

    /* renamed from: k */
    public String mo215k() {
        return this.f162f;
    }

    /* renamed from: l */
    public byte[] mo216l() {
        return this.f163g;
    }

    /* renamed from: m */
    public char[] mo217m() {
        return this.f164h;
    }

    /* renamed from: n */
    public boolean mo218n() {
        return this.f165i;
    }

    /* renamed from: o */
    public boolean mo219o() {
        return this.f166j;
    }

    /* renamed from: p */
    public String mo220p() {
        return this.f167k;
    }

    /* renamed from: q */
    public boolean mo221q() {
        return this.f168l;
    }

    /* renamed from: r */
    public HashMap<String, ArrayList<String>> mo222r() {
        return this.f170n;
    }

    /* renamed from: s */
    public ac mo223s() {
        return this.f171o;
    }

    /* renamed from: t */
    public ac.a mo224t() {
        return this.f172p;
    }

    public String toString() {
        StringBuilder c = Eo.c("TunnelConfiguration:\n", "  url = ");
        Eo.a(c, this.f160d, 10, "  ag = ");
        Eo.a(c, this.f167k, 10, "  cookie valid = ");
        c.append(!TextUtils.isEmpty(this.f157a));
        c.append(10);
        c.append("  cookieExpired = ");
        c.append(this.f158b);
        c.append(10);
        c.append("  fipsMode = ");
        c.append(this.f165i);
        c.append(10);
        c.append("  modeSwitching = ");
        c.append(this.f161e);
        c.append(10);
        c.append("  userAgent = ");
        Eo.a(c, this.f162f, 10, "  direct = ");
        c.append(this.f180x);
        c.append(10);
        c.append("  reverse = ");
        c.append(this.f181y);
        c.append(10);
        c.append("  debugEnabled = ");
        c.append(this.f168l);
        c.append(10);
        c.append("  numPinnedKeys = ");
        c.append(this.f169m);
        c.append(10);
        c.append("  splitTunnelMode = ");
        c.append(this.f172p);
        c.append(10);
        c.append("  tunnelExcludeDomainList = ");
        c.append(this.f178v);
        c.append(10);
        c.append("  tunnelExcludeIpList = ");
        c.append(this.f177u);
        c.append(10);
        c.append("  nsIntranetAppList = ");
        c.append(this.f175s);
        c.append(10);
        c.append("  nsIntranetIpList = ");
        c.append(this.f174r);
        c.append(10);
        c.append("  nsSuffixList = ");
        c.append(this.f173q);
        c.append(10);
        c.append("  xmsReverseExcludedDomainList = ");
        c.append(this.f176t);
        c.append(10);
        c.append("  xmsBackgroundServices = ");
        c.append(this.f179w);
        return c.toString();
    }

    /* renamed from: u */
    public List<String> mo226u() {
        return this.f174r;
    }

    /* renamed from: v */
    public List<String> mo227v() {
        return this.f175s;
    }

    /* renamed from: w */
    public List<String> mo228w() {
        return this.f176t;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f161e ? (byte) 1 : 0);
        parcel.writeByte(this.f165i ? (byte) 1 : 0);
        parcel.writeString(this.f160d);
        parcel.writeString(this.f167k);
        parcel.writeString(this.f162f);
        parcel.writeByte(this.f168l ? (byte) 1 : 0);
        parcel.writeByteArray(this.f163g);
        parcel.writeCharArray(this.f164h);
        parcel.writeInt(this.f169m);
        parcel.writeString(this.f157a);
        parcel.writeByte(this.f158b ? (byte) 1 : 0);
        parcel.writeByte(this.f166j ? (byte) 1 : 0);
        parcel.writeByte(this.f181y ? (byte) 1 : 0);
        parcel.writeByte(this.f180x ? (byte) 1 : 0);
        parcel.writeStringList(this.f175s);
        parcel.writeStringList(this.f174r);
        parcel.writeInt(this.f172p.ordinal());
        parcel.writeStringList(this.f173q);
        parcel.writeStringList(this.f178v);
        parcel.writeStringList(this.f177u);
        parcel.writeStringList(this.f176t);
        parcel.writeStringList(this.f179w);
    }

    /* renamed from: x */
    public List<String> mo230x() {
        return this.f177u;
    }

    /* renamed from: y */
    public List<String> mo231y() {
        return this.f178v;
    }

    /* renamed from: z */
    public List<String> mo232z() {
        return this.f179w;
    }
}
