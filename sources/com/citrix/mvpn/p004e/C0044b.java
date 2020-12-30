package com.citrix.mvpn.p004e;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.mvpn.p001a.C0025d;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.citrix.mvpn.e.b */
/* compiled from: PG */
public class C0044b<T> implements InvocationHandler {

    /* renamed from: a */
    public static final Map<Class, Method[]> f190a = new HashMap();

    /* renamed from: b */
    public T f191b;

    public C0044b(T t) {
        this.f191b = t;
    }

    /* renamed from: a */
    public static <T> T m155a(Context context, T t) {
        try {
            return C0043a.m153a(context, t.getClass(), new C0044b(t), m157a(t.getClass())).a();
        } catch (IOException e) {
            C0051c.f218b.error("MVPN-WebViewClientProxy", "Failed to create WebViewClientProxy.", e);
            return t;
        }
    }

    /* renamed from: a */
    private boolean m156a(Object[] objArr) {
        if (objArr == null || objArr.length != 3 || !(objArr[1] instanceof SslErrorHandler) || !(objArr[2] instanceof SslError) || !objArr[2].getCertificate().getIssuedBy().getCName().equals("MITM")) {
            return false;
        }
        objArr[1].proceed();
        return true;
    }

    /* renamed from: a */
    public static <T> Method[] m157a(Class<T> cls) {
        Method[] methodArr = f190a.get(cls);
        if (methodArr != null) {
            return methodArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Method method : cls.getMethods()) {
            if (!Modifier.isFinal(method.getModifiers())) {
                try {
                    cls.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    arrayList.add(method);
                } catch (NoClassDefFoundError e) {
                    LoggingAPI loggingAPI = C0051c.f218b;
                    StringBuilder a = Eo.a("Cannot proxy method:");
                    a.append(method.getName());
                    a.append(":");
                    a.append(e.getMessage());
                    loggingAPI.error("MVPN-WebViewClientProxy", a.toString());
                } catch (NoSuchMethodException unused) {
                    arrayList.add(method);
                }
            }
        }
        Method[] methodArr2 = (Method[]) arrayList.toArray(new Method[0]);
        f190a.put(cls, methodArr2);
        return methodArr2;
    }

    /* renamed from: b */
    private void m158b(Object[] objArr) {
        if (objArr != null && objArr.length > 1 && (objArr[0] instanceof WebView)) {
            try {
                C0025d.m63a(objArr[0].getContext().getApplicationContext());
            } catch (Exception e) {
                LoggingAPI loggingAPI = C0051c.f218b;
                StringBuilder a = Eo.a("Error while setting proxy:");
                a.append(e.getMessage());
                loggingAPI.error("MVPN-WebViewClientProxy", a.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r4, java.lang.reflect.Method r5, java.lang.Object[] r6) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r4 = r5.getName()
            int r0 = r4.hashCode()
            r1 = -1384959341(0xffffffffad733293, float:-1.38241805E-11)
            r2 = 1
            if (r0 == r1) goto L_0x001e
            r1 = 1303813212(0x4db69c5c, float:3.8296256E8)
            if (r0 == r1) goto L_0x0014
            goto L_0x0028
        L_0x0014:
            java.lang.String r0 = "onReceivedSslError"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0028
            r4 = 0
            goto L_0x0029
        L_0x001e:
            java.lang.String r0 = "onLoadResource"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0028
            r4 = 1
            goto L_0x0029
        L_0x0028:
            r4 = -1
        L_0x0029:
            if (r4 == 0) goto L_0x0032
            if (r4 == r2) goto L_0x002e
            goto L_0x003a
        L_0x002e:
            r3.m158b(r6)
            goto L_0x003a
        L_0x0032:
            boolean r4 = r3.m156a((java.lang.Object[]) r6)
            if (r4 == 0) goto L_0x003a
            r4 = 0
            return r4
        L_0x003a:
            T r4 = r3.f191b
            java.lang.Object r4 = r5.invoke(r4, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.citrix.mvpn.p004e.C0044b.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
