package com.citrix.auth.internal;

import android.app.Activity;
import android.os.Messenger;
import android.util.Log;
import com.citrix.auth.api.AuthSDK;
import com.citrix.auth.exception.AuthenticationException;
import java.net.URL;
import java.util.HashMap;

/* renamed from: com.citrix.auth.internal.a */
/* compiled from: PG */
public class C0010a extends AuthSDK {

    /* renamed from: a */
    public static C0010a f129a;

    /* renamed from: b */
    public HashMap<String, String> f130b = new HashMap<>();

    /* renamed from: a */
    public static synchronized C0010a m48a() {
        C0010a aVar;
        synchronized (C0010a.class) {
            if (f129a == null) {
                f129a = new C0010a();
            }
            aVar = f129a;
        }
        return aVar;
    }

    public void login(Activity activity, Messenger messenger) throws AuthenticationException {
        Log.i("AuthManListSdkImpl", "FIXME: login called in AuthManLiteSdkImpl");
        try {
            new URL("https://FIXME.xmqa.cloud.com/Citrix/Store/resources/v2");
        } catch (Exception e) {
            Log.i("AuthManListSdkImpl", "FIXME: Exception thrown from login", e);
            qI.a.a(e);
        }
        Log.i("AuthManListSdkImpl", "FIXME: returning from login called in AuthManLiteSdkImpl");
    }

    public void logout(Activity activity, Messenger messenger) throws AuthenticationException {
    }
}
