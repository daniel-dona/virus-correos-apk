package com.microsoft.authentication.adal;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.CollectionUtil;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.microsoft_signin.MicrosoftSigninManager;

/* compiled from: PG */
public class SovereignCloudManager {

    /* renamed from: c */
    public static final Map<String, Map<UrlType, String>> f1256c;

    /* renamed from: a */
    public String f1257a;

    /* renamed from: b */
    public AtomicBoolean f1258b = new AtomicBoolean();

    /* compiled from: PG */
    public enum UrlType {
        GRAPH,
        AUTHORITY,
        APP_PROXY_RESOURCE,
        APP_PROXY_ENDPOINT
    }

    /* renamed from: com.microsoft.authentication.adal.SovereignCloudManager$b */
    /* compiled from: PG */
    public static class C0340b {

        /* renamed from: a */
        public static SovereignCloudManager f1259a = new SovereignCloudManager((C0339a) null);
    }

    static {
        UrlType urlType = UrlType.GRAPH;
        UrlType urlType2 = UrlType.AUTHORITY;
        UrlType urlType3 = UrlType.APP_PROXY_RESOURCE;
        UrlType urlType4 = UrlType.APP_PROXY_ENDPOINT;
        ArrayMap arrayMap = new ArrayMap(2);
        arrayMap.put(urlType, "https://graph.microsoft.com/");
        arrayMap.put(urlType2, "https://login.microsoftonline.com/common");
        arrayMap.put(urlType3, "https://proxy.cloudwebappproxy.net/registerapp");
        arrayMap.put(urlType4, "https://%s.msgraph.msappproxy.net:8082/userqueries/getapplications");
        Map unmodifiableMap = Collections.unmodifiableMap(arrayMap);
        UrlType urlType5 = UrlType.GRAPH;
        UrlType urlType6 = UrlType.AUTHORITY;
        UrlType urlType7 = UrlType.APP_PROXY_RESOURCE;
        UrlType urlType8 = UrlType.APP_PROXY_ENDPOINT;
        ArrayMap arrayMap2 = new ArrayMap(2);
        arrayMap2.put(urlType5, "https://graph.microsoft.us/");
        arrayMap2.put(urlType6, "https://login.microsoftonline.us/common");
        arrayMap2.put(urlType7, "https://proxy.msappproxy.us/registerapp");
        arrayMap2.put(urlType8, "https://%s.msgraph.msappproxy.us:8082/userqueries/getapplications");
        f1256c = CollectionUtil.m1378a("https://login.microsoftonline.com/common", unmodifiableMap, "https://login.microsoftonline.us/common", Collections.unmodifiableMap(arrayMap2));
    }

    public /* synthetic */ SovereignCloudManager(C0339a aVar) {
        mo3481a(MicrosoftSigninManager.C0424c.f2104a.mo8910h());
    }

    /* renamed from: d */
    public static SovereignCloudManager m1262d() {
        return C0340b.f1259a;
    }

    @CalledByNative
    public static String getAuthorityUrl() {
        try {
            return new URL(C0340b.f1259a.mo3480a(UrlType.AUTHORITY)).getHost();
        } catch (MalformedURLException e) {
            Log.e("SovereignCloudManager", BuildConfig.FLAVOR, e);
            return BuildConfig.FLAVOR;
        }
    }

    @CalledByNative
    public static boolean isOn() {
        return C0340b.f1259a.mo3482a();
    }

    private native void nativeInit();

    /* renamed from: a */
    public void mo3481a(String str) {
        Log.i("SovereignCloudManager", "setAuthority: " + str);
        this.f1257a = str;
        this.f1258b.set("https://login.microsoftonline.us/common".equals(this.f1257a));
    }

    /* renamed from: b */
    public void mo3483b() {
        mo3481a((String) null);
    }

    /* renamed from: c */
    public void mo3484c() {
        Log.i("SovereignCloudManager", "onFinishNativeInitialization:");
        nativeInit();
    }

    /* renamed from: a */
    public boolean mo3482a() {
        return this.f1258b.get();
    }

    /* renamed from: a */
    public String mo3480a(UrlType urlType) {
        String str = this.f1257a;
        if (!"https://login.microsoftonline.us/common".equals(str)) {
            str = "https://login.microsoftonline.com/common";
        }
        return f1256c.containsKey(str) ? (String) f1256c.get(str).get(urlType) : BuildConfig.FLAVOR;
    }
}
