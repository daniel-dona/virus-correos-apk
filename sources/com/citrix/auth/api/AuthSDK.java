package com.citrix.auth.api;

import android.app.Activity;
import android.os.Messenger;
import com.citrix.auth.exception.AuthenticationException;
import com.citrix.auth.internal.C0010a;
import com.citrix.auth.internal.C0011b;

/* compiled from: PG */
public abstract class AuthSDK {
    public static final String TAG = "AUTH-AuthSDK";
    public static AuthProviderType authProviderType = AuthProviderType.SECUREHUB;
    public static AuthSDK instance;

    /* renamed from: com.citrix.auth.api.AuthSDK$1 */
    /* compiled from: PG */
    public static /* synthetic */ class C00091 {
        public static final /* synthetic */ int[] $SwitchMap$com$citrix$auth$api$AuthProviderType = new int[AuthProviderType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0018 */
        static {
            /*
                com.citrix.auth.api.AuthProviderType[] r0 = com.citrix.auth.api.AuthProviderType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$citrix$auth$api$AuthProviderType = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$citrix$auth$api$AuthProviderType     // Catch:{ NoSuchFieldError -> 0x0011 }
                com.citrix.auth.api.AuthProviderType r2 = com.citrix.auth.api.AuthProviderType.SECUREHUB     // Catch:{ NoSuchFieldError -> 0x0011 }
                r2 = 0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                r1 = 2
                int[] r2 = $SwitchMap$com$citrix$auth$api$AuthProviderType     // Catch:{ NoSuchFieldError -> 0x0018 }
                com.citrix.auth.api.AuthProviderType r3 = com.citrix.auth.api.AuthProviderType.AUTHMANLITE     // Catch:{ NoSuchFieldError -> 0x0018 }
                r2[r0] = r1     // Catch:{ NoSuchFieldError -> 0x0018 }
            L_0x0018:
                int[] r0 = $SwitchMap$com$citrix$auth$api$AuthProviderType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.citrix.auth.api.AuthProviderType r2 = com.citrix.auth.api.AuthProviderType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.citrix.auth.api.AuthSDK.C00091.<clinit>():void");
        }
    }

    public static synchronized AuthSDK getInstance() {
        AuthSDK authSDK;
        synchronized (AuthSDK.class) {
            if (instance == null) {
                int ordinal = authProviderType.ordinal();
                if (ordinal == 0) {
                    instance = C0011b.m49a();
                } else if (ordinal == 1) {
                    instance = C0010a.m48a();
                } else if (ordinal == 2) {
                    throw new UnsupportedOperationException("Operation not supported.");
                }
            }
            authSDK = instance;
        }
        return authSDK;
    }

    public static void setAuthProviderType(AuthProviderType authProviderType2) {
        authProviderType = authProviderType2;
    }

    public abstract void login(Activity activity, Messenger messenger) throws AuthenticationException;

    public abstract void logout(Activity activity, Messenger messenger) throws AuthenticationException;
}
