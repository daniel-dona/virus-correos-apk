package com.microsoft.authentication;

import android.content.SharedPreferences;
import com.citrix.loggersdk.BuildConfig;
import com.google.gson.Gson;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.ThreadUtils;

/* compiled from: PG */
public class OAuthTokenProvider {

    /* renamed from: d */
    public static final String f1252d = OAuthTokenProvider.class.toString();

    /* renamed from: a */
    public volatile String f1253a;

    /* renamed from: b */
    public final Listener f1254b;

    /* renamed from: c */
    public final ConcurrentHashMap<String, n20> f1255c;

    /* compiled from: PG */
    public interface AccessTokenCallback<T> {
        void onError(Throwable th);

        void onSuccess(T t);
    }

    /* compiled from: PG */
    public interface Listener {
        void onCredentialUpdateRequired();

        void onOneDriveRefreshTokenUpdated(String str);
    }

    public OAuthTokenProvider(String str, Listener listener) {
        this.f1253a = str;
        this.f1254b = listener;
        ConcurrentHashMap<String, n20> concurrentHashMap = new ConcurrentHashMap<>();
        String string = QN0.a.getString("oauth_access_token_map_key", BuildConfig.FLAVOR);
        if (!string.isEmpty()) {
            try {
                concurrentHashMap = (ConcurrentHashMap) new Gson().mo2098a(string, new m20(this).getType());
            } catch (Exception unused) {
                VN0.a(f1252d, "load Access Token map failed.", new Object[0]);
            }
        }
        this.f1255c = concurrentHashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b9, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f1, code lost:
        return r0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.microsoft.authentication.OAuthToken mo3469a(java.lang.String r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x0012
            do0 r0 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = "OAuthTokenProvider"
            java.lang.String r2 = "requestAccessToken() called"
            jo0 r0 = r0.a     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x0012
            r0.a(r1, r2)     // Catch:{ all -> 0x00f2 }
        L_0x0012:
            java.lang.String r0 = r7.f1253a     // Catch:{ all -> 0x00f2 }
            boolean r0 = C20.b(r0)     // Catch:{ all -> 0x00f2 }
            r1 = 0
            if (r0 == 0) goto L_0x002e
            boolean r8 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x002c
            do0 r8 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r0 = "OAuthTokenProvider"
            java.lang.String r2 = "_allInOneRefreshToken empty, return"
            jo0 r8 = r8.a     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x002c
            r8.a(r0, r2)     // Catch:{ all -> 0x00f2 }
        L_0x002c:
            monitor-exit(r7)
            return r1
        L_0x002e:
            java.lang.String r0 = "https://login.live.com/oauth20_token.srf"
            java.lang.String r2 = r7.f1253a     // Catch:{ all -> 0x00f2 }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x00f2 }
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f2 }
            r5 = 0
            java.lang.String r6 = "refresh_token"
            r4[r5] = r6     // Catch:{ all -> 0x00f2 }
            r5 = 1
            java.lang.String r6 = "000000004C1BC462"
            r4[r5] = r6     // Catch:{ all -> 0x00f2 }
            r5 = 2
            r4[r5] = r8     // Catch:{ all -> 0x00f2 }
            r5 = 3
            r4[r5] = r2     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = "grant_type=%s&client_id=%s&scope=%s&refresh_token=%s"
            java.lang.String r2 = java.lang.String.format(r3, r2, r4)     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = ""
            com.microsoft.authentication.OAuthToken r0 = C20.a(r0, r2, r8, r3)     // Catch:{ all -> 0x00f2 }
            boolean r2 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x007f
            do0 r2 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = "OAuthTokenProvider"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            r4.<init>()     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "requestOAuthToken result = ["
            r4.append(r5)     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x006a
            java.lang.String r5 = "null"
            goto L_0x006c
        L_0x006a:
            java.lang.String r5 = "non-null"
        L_0x006c:
            r4.append(r5)     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "]"
            r4.append(r5)     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00f2 }
            jo0 r2 = r2.a     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x007f
            r2.a(r3, r4)     // Catch:{ all -> 0x00f2 }
        L_0x007f:
            if (r0 == 0) goto L_0x00f0
            boolean r2 = r0.isRefreshTokenExpired()     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x00a1
            boolean r2 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x0098
            do0 r2 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = "OAuthTokenProvider"
            java.lang.String r4 = "oAuthToken.isRefreshTokenExpired"
            jo0 r2 = r2.a     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x0098
            r2.a(r3, r4)     // Catch:{ all -> 0x00f2 }
        L_0x0098:
            com.microsoft.authentication.OAuthTokenProvider$Listener r2 = r7.f1254b     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x00a1
            com.microsoft.authentication.OAuthTokenProvider$Listener r2 = r7.f1254b     // Catch:{ all -> 0x00f2 }
            r2.onCredentialUpdateRequired()     // Catch:{ all -> 0x00f2 }
        L_0x00a1:
            boolean r2 = r0.isValidOAuthToken()     // Catch:{ all -> 0x00f2 }
            if (r2 != 0) goto L_0x00ba
            boolean r8 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00b8
            do0 r8 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r0 = "OAuthTokenProvider"
            java.lang.String r2 = "oAuthToken not valid oauth token"
            jo0 r8 = r8.a     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00b8
            r8.a(r0, r2)     // Catch:{ all -> 0x00f2 }
        L_0x00b8:
            monitor-exit(r7)
            return r1
        L_0x00ba:
            java.lang.String r1 = "service::ssl.live.com::MBI_SSL"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00f0
            java.lang.String r8 = r0.getRefreshToken()     // Catch:{ all -> 0x00f2 }
            boolean r8 = C20.b(r8)     // Catch:{ all -> 0x00f2 }
            if (r8 != 0) goto L_0x00f0
            com.microsoft.authentication.OAuthTokenProvider$Listener r8 = r7.f1254b     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00f0
            boolean r8 = e20.b     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00e1
            do0 r8 = e20.a     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = "OAuthTokenProvider"
            java.lang.String r2 = "oAuthToken update refresh token"
            jo0 r8 = r8.a     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00e1
            r8.a(r1, r2)     // Catch:{ all -> 0x00f2 }
        L_0x00e1:
            java.lang.String r8 = r0.getRefreshToken()     // Catch:{ all -> 0x00f2 }
            r7.f1253a = r8     // Catch:{ all -> 0x00f2 }
            com.microsoft.authentication.OAuthTokenProvider$Listener r8 = r7.f1254b     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = r0.getRefreshToken()     // Catch:{ all -> 0x00f2 }
            r8.onOneDriveRefreshTokenUpdated(r1)     // Catch:{ all -> 0x00f2 }
        L_0x00f0:
            monitor-exit(r7)
            return r0
        L_0x00f2:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.authentication.OAuthTokenProvider.mo3469a(java.lang.String):com.microsoft.authentication.OAuthToken");
    }

    /* renamed from: b */
    public void mo3475b(String str) {
        if (this.f1253a == null || !this.f1253a.equals(str)) {
            this.f1255c.clear();
            mo3471a();
        }
        this.f1253a = str;
    }

    /* renamed from: a */
    public String mo3470a(String str, boolean z) {
        n20 n20;
        String str2;
        jo0 jo0;
        jo0 jo02;
        jo0 jo03;
        ThreadUtils.m1460b();
        if (e20.b) {
            String str3 = "getAccessToken() called with: forceRefresh = [" + z + "]";
            jo0 jo04 = e20.a.a;
            if (jo04 != null) {
                jo04.a("OAuthTokenProvider", str3);
            }
        }
        if (!z && this.f1255c.containsKey(str) && this.f1255c.get(str) != null) {
            if (!mo3474a(Long.valueOf(this.f1255c.get(str).c), this.f1255c.get(str).b)) {
                if (e20.b && (jo03 = e20.a.a) != null) {
                    jo03.a("OAuthTokenProvider", "getAccessToken() cached token not expired, OK");
                }
                return this.f1255c.get(str).a;
            } else if (e20.b && (jo02 = e20.a.a) != null) {
                jo02.a("OAuthTokenProvider", "getAccessToken() cached token expired");
            }
        }
        if (e20.b && (jo0 = e20.a.a) != null) {
            jo0.a("OAuthTokenProvider", "fetchAccessToken() called");
        }
        OAuthToken a = mo3469a(str);
        String str4 = "null";
        if (e20.b) {
            do0 do0 = e20.a;
            StringBuilder a2 = Eo.a("requestAccessToken result = [");
            if (a == null) {
                str2 = str4;
            } else {
                str2 = "non-null";
            }
            String a3 = Eo.a(a2, str2, "]");
            jo0 jo05 = do0.a;
            if (jo05 != null) {
                jo05.a("OAuthTokenProvider", a3);
            }
        }
        if (a == null) {
            n20 = null;
        } else {
            n20 = new n20(a.getAccessToken(), a.getExpiresIn(), System.currentTimeMillis() / 1000);
        }
        if (e20.b) {
            do0 do02 = e20.a;
            StringBuilder a4 = Eo.a("fetchAccessToken result = [");
            if (n20 != null) {
                str4 = "non-null";
            }
            String a5 = Eo.a(a4, str4, "]");
            jo0 jo06 = do02.a;
            if (jo06 != null) {
                jo06.a("OAuthTokenProvider", a5);
            }
        }
        if (n20 == null) {
            return null;
        }
        this.f1255c.put(str, n20);
        mo3471a();
        return n20.a;
    }

    /* renamed from: a */
    public void mo3473a(String str, boolean z, AccessTokenCallback<String> accessTokenCallback) {
        if (z || !this.f1255c.containsKey(str)) {
            mo3472a(str, accessTokenCallback);
            return;
        }
        long j = this.f1255c.get(str).c;
        if (!mo3474a(Long.valueOf(j), this.f1255c.get(str).b)) {
            accessTokenCallback.onSuccess(this.f1255c.get(str).a);
        } else {
            mo3472a(str, accessTokenCallback);
        }
    }

    /* renamed from: a */
    public final void mo3472a(String str, AccessTokenCallback<String> accessTokenCallback) {
        new k20(this, str, accessTokenCallback).a(Us0.c);
    }

    /* renamed from: a */
    public static /* synthetic */ String m1254a(OAuthTokenProvider oAuthTokenProvider, String str) {
        OAuthToken a = oAuthTokenProvider.mo3469a(str);
        if (a == null) {
            return null;
        }
        oAuthTokenProvider.f1255c.put(str, new n20(a.getAccessToken(), a.getExpiresIn(), System.currentTimeMillis() / 1000));
        oAuthTokenProvider.mo3471a();
        return a.getAccessToken();
    }

    /* renamed from: a */
    public final boolean mo3474a(Long l, int i) {
        Long valueOf = Long.valueOf(System.currentTimeMillis() / 1000);
        return valueOf.longValue() - l.longValue() >= ((long) (i + -60)) || valueOf.longValue() - l.longValue() < 0;
    }

    /* renamed from: a */
    public final void mo3471a() {
        try {
            String a = new Gson().mo2101a((Object) this.f1255c, new l20(this).getType());
            SharedPreferences.Editor edit = QN0.a.edit();
            edit.putString("oauth_access_token_map_key", a);
            edit.apply();
        } catch (Exception unused) {
            VN0.a(f1252d, "Gson serialise AccessTokenMap to json error", new Object[0]);
        }
    }
}
