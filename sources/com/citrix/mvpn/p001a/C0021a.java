package com.citrix.mvpn.p001a;

import android.content.Context;
import com.citrix.mvpn.MAM.Android.AuthSSO.b.f;
import com.citrix.mvpn.exception.ClientConfigurationException;
import com.citrix.mvpn.helper.C0050b;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.Version;

/* renamed from: com.citrix.mvpn.a.a */
/* compiled from: PG */
public class C0021a {

    /* renamed from: com.citrix.mvpn.a.a$a */
    /* compiled from: PG */
    public enum C0022a {
        OK_HTTP_3_X,
        OK_HTTP_4_X
    }

    /* renamed from: a */
    public static C0022a m50a() {
        try {
            return (Version.userAgent() == null || !Version.userAgent().startsWith("okhttp/3.")) ? C0022a.OK_HTTP_3_X : C0022a.OK_HTTP_3_X;
        } catch (Throwable unused) {
            return C0022a.OK_HTTP_4_X;
        }
    }

    /* renamed from: a */
    public static Object m51a(Object obj, Context context) throws ClientConfigurationException {
        OkHttpClient okHttpClient = (OkHttpClient) obj;
        m53a(okHttpClient, context);
        m52a(okHttpClient);
        m56b(okHttpClient);
        m58c(okHttpClient, context);
        return okHttpClient;
    }

    /* renamed from: a */
    public static void m52a(OkHttpClient okHttpClient) throws ClientConfigurationException {
        C0051c.f218b.debug5("MVPN-OkHttpConfig", "Setting OkHttpClient hostnameVerifier.");
        m54a(okHttpClient, "hostnameVerifier", C0051c.f219c);
    }

    /* renamed from: a */
    public static void m53a(OkHttpClient okHttpClient, Context context) throws ClientConfigurationException {
        try {
            m57b(okHttpClient, context);
        } catch (Exception e) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder a = Eo.a("Unable to set UserAgent string:");
            a.append(e.getMessage());
            loggingAPI.error("MVPN-OkHttpConfig", a.toString());
            throw new ClientConfigurationException("Unable to set UserAgent string", e);
        }
    }

    /* renamed from: a */
    public static void m54a(OkHttpClient okHttpClient, String str, Object obj) throws ClientConfigurationException {
        try {
            Field declaredField = okHttpClient.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Field declaredField2 = Field.class.getDeclaredField("accessFlags");
            declaredField2.setAccessible(true);
            declaredField2.setInt(declaredField, declaredField.getModifiers() & -17);
            declaredField.set(okHttpClient, obj);
            declaredField.setAccessible(false);
            declaredField2.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LoggingAPI loggingAPI = C0051c.f218b;
            StringBuilder c = Eo.c("Unable to modify OkHttpClient object for ", str, ":");
            c.append(e.getMessage());
            loggingAPI.error("MVPN-OkHttpConfig", c.toString());
            throw new ClientConfigurationException("Unable to modify OkHttpClient object for network tunnel", e);
        }
    }

    /* renamed from: a */
    public static boolean m55a(Object obj) {
        Response response = (Response) obj;
        return (response.code() == 403 || response.code() == 401) && Boolean.parseBoolean(response.header("X-Citrix-Session-Expired"));
    }

    /* renamed from: b */
    public static void m56b(OkHttpClient okHttpClient) throws ClientConfigurationException {
        String str;
        C0051c.f218b.debug5("MVPN-OkHttpConfig", "Setting OkHttpClient sslSocketFactory.");
        try {
            SSLContext instance = SSLContext.getInstance("TLSv1.2");
            instance.init((KeyManager[]) null, C0050b.m193j((Context) null), (SecureRandom) null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (m50a() == C0022a.OK_HTTP_4_X) {
                str = "sslSocketFactoryOrNull";
            } else if (m50a() == C0022a.OK_HTTP_3_X) {
                str = "sslSocketFactory";
            } else {
                return;
            }
            m54a(okHttpClient, str, socketFactory);
        } catch (GeneralSecurityException e) {
            C0051c.f218b.error("MVPN-OkHttpConfig", "Failed to update ssl socket factory.");
            StringBuilder a = Eo.a("Failed to update ssl socket factory:");
            a.append(e.getMessage());
            throw new ClientConfigurationException(a.toString(), e);
        }
    }

    /* renamed from: b */
    public static void m57b(OkHttpClient okHttpClient, Context context) throws ClientConfigurationException {
        C0051c.f218b.debug5("MVPN-OkHttpConfig", "Setting OkHttpClient interceptors.");
        ArrayList arrayList = new ArrayList(okHttpClient.interceptors());
        arrayList.add(new C0024c(context));
        m54a(okHttpClient, "interceptors", arrayList);
    }

    /* renamed from: c */
    public static void m58c(OkHttpClient okHttpClient, Context context) throws ClientConfigurationException {
        Proxy proxy;
        C0051c.f218b.debug5("MVPN-OkHttpConfig", "Setting OkHttpClient proxy.");
        int b = f.b(context);
        Proxy proxy2 = Proxy.NO_PROXY;
        if (b != -1) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", b));
        } else {
            LoggingAPI loggingAPI = C0051c.f218b;
            loggingAPI.error("MVPN-OkHttpConfig", "Invalid Port:" + b);
            proxy = proxy2;
        }
        m54a(okHttpClient, "proxy", proxy);
    }
}
