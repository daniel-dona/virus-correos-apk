package org.chromium.chrome.browser.browserservices;

import android.app.Notification;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.customtabs.trusted.TrustedWebActivityServiceConnectionManager;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.Oauth2;
import org.chromium.chrome.browser.ChromeApplication;

/* compiled from: PG */
public class TrustedWebActivityClient {

    /* renamed from: a */
    public final TrustedWebActivityServiceConnectionManager f1706a;

    /* renamed from: b */
    public final Oz1 f1707b;

    public TrustedWebActivityClient(TrustedWebActivityServiceConnectionManager trustedWebActivityServiceConnectionManager, Oz1 oz1) {
        this.f1706a = trustedWebActivityServiceConnectionManager;
        this.f1707b = oz1;
    }

    /* renamed from: a */
    public boolean mo8339a(yz1 yz1, NotificationPermissionCheckCallback notificationPermissionCheckCallback) {
        return this.f1706a.a(yz1.a, yz1.toString(), new Jz1(notificationPermissionCheckCallback, RN0.a.getResources().getString(wx0.notification_category_group_general)));
    }

    /* renamed from: a */
    public void mo8336a(Uri uri, String str, int i, tc2 tc2, Gc2 gc2) {
        Uri uri2 = uri;
        String string = RN0.a.getResources().getString(wx0.notification_category_group_general);
        yz1 yz1 = new yz1(uri);
        this.f1706a.a(uri, yz1.toString(), new Kz1(this, string, yz1, tc2, str, i, gc2));
    }

    /* renamed from: a */
    public boolean mo8338a(Uri uri) {
        Uri uri2;
        TrustedWebActivityServiceConnectionManager trustedWebActivityServiceConnectionManager = this.f1706a;
        if (uri == null || uri.getScheme() == null || uri.getAuthority() == null) {
            uri2 = Uri.EMPTY;
        } else {
            int port = uri.getPort();
            String scheme = uri.getScheme();
            if (scheme.equals("http") && port == 80) {
                port = -1;
            }
            if (scheme.equals(Oauth2.HTTPS_PROTOCOL_STRING) && port == 443) {
                port = -1;
            }
            String host = uri.getHost();
            if (port != -1) {
                host = host + ":" + port;
            }
            try {
                uri2 = uri.normalizeScheme().buildUpon().opaquePart(BuildConfig.FLAVOR).fragment(BuildConfig.FLAVOR).path(BuildConfig.FLAVOR).encodedAuthority(host).clearQuery().build();
            } catch (UnsupportedOperationException unused) {
                uri2 = Uri.EMPTY;
            }
        }
        String uri3 = uri2.toString();
        if (trustedWebActivityServiceConnectionManager.b.get(uri) == null && trustedWebActivityServiceConnectionManager.a(trustedWebActivityServiceConnectionManager.a, uri, uri3, false) == null) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8337a(String str, yz1 yz1, tc2 tc2, String str2, int i, Gc2 gc2, K1 k1) throws RemoteException {
        if (!k1.a(str)) {
            ChromeApplication.m1775e().n().a(yz1, k1.b, false);
            return;
        }
        if (!tc2.b() || !tc2.c()) {
            int p = k1.a.p();
            if (p == -1) {
                this.f1707b.a(1);
            } else {
                this.f1707b.a(tc2.b() ? 2 : 3);
                Bitmap bitmap = (Bitmap) k1.a.h().getParcelable("android.support.customtabs.trusted.SMALL_ICON_BITMAP");
                if (!tc2.c()) {
                    tc2.a(p, bitmap, k1.b.getPackageName());
                }
                if (!tc2.b()) {
                    tc2.a(bitmap);
                }
            }
        } else {
            this.f1707b.a(0);
        }
        Notification notification = tc2.a(new yc2(13, str2, i)).a;
        Bundle bundle = new Bundle();
        bundle.putString("android.support.customtabs.trusted.PLATFORM_TAG", str2);
        bundle.putInt("android.support.customtabs.trusted.PLATFORM_ID", i);
        bundle.putParcelable("android.support.customtabs.trusted.NOTIFICATION", notification);
        bundle.putString("android.support.customtabs.trusted.CHANNEL_NAME", str);
        Bundle c = k1.a.c(bundle);
        K1.a(c, "android.support.customtabs.trusted.NOTIFICATION_SUCCESS");
        c.getBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS");
        gc2.a(13, notification);
    }
}
