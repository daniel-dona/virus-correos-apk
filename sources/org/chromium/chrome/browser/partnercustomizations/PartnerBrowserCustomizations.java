package org.chromium.chrome.browser.partnercustomizations;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.edge_ntp.NewTabPage;
import org.chromium.chrome.browser.util.UrlUtilities;

/* compiled from: PG */
public class PartnerBrowserCustomizations {

    /* renamed from: a */
    public static volatile String f2136a;

    /* renamed from: b */
    public static volatile boolean f2137b;

    /* renamed from: c */
    public static volatile boolean f2138c;

    /* renamed from: d */
    public static boolean f2139d;

    /* renamed from: e */
    public static List<Runnable> f2140e = new ArrayList();

    /* compiled from: PG */
    public interface Provider {
        String getHomepage();

        boolean isBookmarksEditingDisabled();

        boolean isIncognitoModeDisabled();
    }

    /* renamed from: org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations$b */
    /* compiled from: PG */
    public static class C0426b implements Provider {

        /* renamed from: a */
        public static Boolean f2141a;

        /* renamed from: a */
        public final boolean mo8986a() {
            if (f2141a == null) {
                PackageManager packageManager = RN0.a.getPackageManager();
                PartnerBrowserCustomizations.m2701a();
                boolean z = false;
                ProviderInfo c = Vc0.c(packageManager, "com.android.partnerbrowsercustomizations", 0);
                if (c != null) {
                    if ((c.applicationInfo.flags & 1) == 0) {
                        VN0.c("PartnerCustomize", Eo.a(Eo.a("Browser Customizations content provider package, "), c.packageName, ", is not a system package. This could be a malicious attempt from a third party app, so skip reading the browser content provider."), new Object[0]);
                    } else {
                        z = true;
                    }
                }
                f2141a = Boolean.valueOf(z);
            }
            return f2141a.booleanValue();
        }

        public String getHomepage() {
            String str = null;
            if (!mo8986a()) {
                return null;
            }
            Cursor a = Tc0.a(RN0.a.getContentResolver(), PartnerBrowserCustomizations.m2700a("homepage"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (a != null && a.moveToFirst() && a.getColumnCount() == 1) {
                str = a.getString(0);
            }
            if (a != null) {
                a.close();
            }
            return str;
        }

        public boolean isBookmarksEditingDisabled() {
            boolean z = false;
            if (!mo8986a()) {
                return false;
            }
            Cursor a = Tc0.a(RN0.a.getContentResolver(), PartnerBrowserCustomizations.m2700a("disablebookmarksediting"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (a != null && a.moveToFirst() && a.getColumnCount() == 1 && a.getInt(0) == 1) {
                z = true;
            }
            if (a != null) {
                a.close();
            }
            return z;
        }

        public boolean isIncognitoModeDisabled() {
            boolean z = false;
            if (!mo8986a()) {
                return false;
            }
            Cursor a = Tc0.a(RN0.a.getContentResolver(), PartnerBrowserCustomizations.m2700a("disableincognitomode"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (a != null && a.moveToFirst() && a.getColumnCount() == 1 && a.getInt(0) == 1) {
                z = true;
            }
            if (a != null) {
                a.close();
            }
            return z;
        }
    }

    /* renamed from: a */
    public static Uri m2700a(String str) {
        return new Uri.Builder().scheme("content").authority("com.android.partnerbrowsercustomizations").appendPath(str).build();
    }

    /* renamed from: a */
    public static /* synthetic */ String m2701a() {
        return "com.android.partnerbrowsercustomizations";
    }

    /* renamed from: b */
    public static String m2704b() {
        return null;
    }

    /* renamed from: b */
    public static boolean m2705b(String str) {
        if (str == null) {
            return false;
        }
        if (!UrlUtilities.m3285c(str) && !NewTabPage.m2272b(str)) {
            VN0.c("PartnerCustomize", "Partner homepage must be HTTP(S) or NewTabPage. Got invalid URL \"%s\"", new Object[]{str});
            return false;
        } else if (str.length() <= 1000) {
            return true;
        } else {
            VN0.c("PartnerCustomize", "The homepage URL \"%s\" is too long.", new Object[]{str});
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m2706c() {
        return true;
    }

    @CalledByNative
    public static boolean isIncognitoDisabled() {
        return f2137b;
    }

    /* renamed from: a */
    public static void m2703a(Context context, long j) {
        f2139d = false;
        a aVar = new a(AppHooks.get().mo8001z(), context);
        aVar.a(AP0.f);
        PostTask.m1566a(iQ2.a, new Ai2(aVar), j);
    }
}
