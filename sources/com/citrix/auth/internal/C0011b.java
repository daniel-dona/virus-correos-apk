package com.citrix.auth.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Messenger;
import com.citrix.auth.api.AuthSDK;
import com.citrix.auth.exception.AuthenticationException;

/* renamed from: com.citrix.auth.internal.b */
/* compiled from: PG */
public class C0011b extends AuthSDK {
    /* renamed from: a */
    public static synchronized C0011b m49a() {
        C0011b bVar;
        synchronized (C0011b.class) {
            if (AuthSDK.instance == null) {
                AuthSDK.instance = new C0011b();
            }
            bVar = (C0011b) AuthSDK.instance;
        }
        return bVar;
    }

    public void login(Activity activity, Messenger messenger) throws AuthenticationException {
        if (messenger == null || activity == null) {
            throw new IllegalArgumentException("Activity or Messenger cannot be null.");
        }
        try {
            Intent intent = new Intent(activity, SecureHubAuthHelperActivity.class);
            intent.putExtra("MVPNMessenger", messenger);
            activity.startActivityForResult(intent, 0);
        } catch (Exception e) {
            throw new AuthenticationException("Failed to Authenticate with SecureHub", e);
        }
    }

    public void logout(Activity activity, Messenger messenger) {
        throw new UnsupportedOperationException("doLogout() method is not supported yet");
    }
}
