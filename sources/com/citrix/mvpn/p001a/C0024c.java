package com.citrix.mvpn.p001a;

import android.content.Context;
import android.text.TextUtils;
import com.citrix.mvpn.MAM.Android.AuthSSO.b.f;
import com.citrix.mvpn.helper.C0051c;
import com.citrix.sdk.logging.api.LoggingAPI;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* renamed from: com.citrix.mvpn.a.c */
/* compiled from: PG */
public class C0024c implements Interceptor {

    /* renamed from: a */
    public final Context f134a;

    public C0024c(Context context) {
        this.f134a = context;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        String a = f.a(this.f134a);
        LoggingAPI loggingAPI = C0051c.f218b;
        StringBuilder a2 = Eo.a("Updating user agent. Is valid user agent=");
        a2.append(!TextUtils.isEmpty(a));
        loggingAPI.debug5("MVPN-UserAgentInterceptor", a2.toString());
        return chain.proceed(chain.request().newBuilder().header("User-Agent", a).build());
    }
}
