package com.citrix.mvpn.p001a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.citrix.loggersdk.BuildConfig;
import com.citrix.mvpn.MAM.Android.AuthSSO.b.f;
import com.citrix.mvpn.exception.ClientConfigurationException;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p004e.C0044b;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.citrix.mvpn.a.d */
/* compiled from: PG */
public class C0025d<T extends WebViewClient> {
    /* renamed from: a */
    public static <T extends WebViewClient> WebView m61a(WebView webView, T t, Context context) throws ClientConfigurationException {
        m64a(webView, context);
        webView.setWebViewClient(m62a(context, t));
        m63a(webView.getContext().getApplicationContext());
        return webView;
    }

    /* renamed from: a */
    public static <T extends WebViewClient> WebViewClient m62a(Context context, T t) {
        C0051c.f218b.debug5("MVPN-WebViewConfig", "Setting WebView Client");
        if (t == null || !ho.a(t.getClass())) {
            C0051c.f218b.debug10("MVPN-WebViewConfig", "Creating Proxy WebViewClient");
            if (t == null) {
                t = new WebViewClient();
            }
            return (WebViewClient) C0044b.m155a(context, t);
        }
        C0051c.f218b.debug10("MVPN-WebViewConfig", "WebViewClient is already a proxy object.");
        return t;
    }

    /* renamed from: a */
    public static void m63a(Context context) throws ClientConfigurationException {
        m65a(context, "127.0.0.1", f.b(context));
    }

    /* renamed from: a */
    public static void m64a(WebView webView, Context context) {
        C0051c.f218b.debug5("MVPN-WebViewConfig", "Setting UserAgent.");
        WebSettings settings = webView.getSettings();
        String userAgentString = settings.getUserAgentString();
        String a = f.a(context);
        if (!TextUtils.isEmpty(a) && !userAgentString.contains(a)) {
            settings.setUserAgentString(userAgentString + a);
        }
        C0051c.f218b.debug5("MVPN-WebViewConfig", "Setting UserAgent successful!");
    }

    @TargetApi(21)
    /* renamed from: a */
    public static boolean m65a(Context context, String str, int i) throws ClientConfigurationException {
        C0051c.f218b.debug5("MVPN-WebViewConfig", "Setting proxy...");
        try {
            Field field = Class.forName("android.app.Application").getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(context);
            Field declaredField = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
            declaredField.setAccessible(true);
            boolean z = false;
            for (ArrayMap keySet : ((ArrayMap) declaredField.get(obj)).values()) {
                for (Object next : keySet.keySet()) {
                    Class<?> cls = next.getClass();
                    if (cls.getName().contains("ProxyChangeListener")) {
                        Method declaredMethod = cls.getDeclaredMethod("onReceive", new Class[]{Context.class, Intent.class});
                        ProxyInfo buildDirectProxy = ProxyInfo.buildDirectProxy(str, i);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("android.intent.extra.PROXY_INFO", buildDirectProxy);
                        Intent intent = new Intent("android.intent.action.PROXY_CHANGE");
                        intent.putExtras(bundle);
                        declaredMethod.invoke(next, new Object[]{context, intent});
                        LoggingAPI loggingAPI = C0051c.f218b;
                        loggingAPI.debug5("MVPN-WebViewConfig", "Setting proxy to port " + i + " done for receiver = " + next);
                        z = true;
                    }
                }
            }
            if (!z) {
                C0051c.f218b.debug5("MVPN-WebViewConfig", "Failed to set proxy.");
            }
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            C0051c.f218b.info("MVPN-WebViewConfig", e.getMessage());
            throw new ClientConfigurationException("Unable to modify WebView object for network tunnel", e);
        }
    }

    /* renamed from: b */
    public static void m66b(Context context) throws ClientConfigurationException {
        m65a(context, BuildConfig.FLAVOR, 0);
    }
}
