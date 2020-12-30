package org.chromium.chrome.browser;

import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.cache.AbstractAccountCredentialCache;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.net.HttpUtil;

/* compiled from: PG */
public class IntentHandler {

    /* renamed from: d */
    public static ComponentName f1663d;

    /* renamed from: e */
    public static final Object f1664e = new Object();

    /* renamed from: f */
    public static Pair<Integer, String> f1665f;

    /* renamed from: g */
    public static int f1666g;

    /* renamed from: h */
    public static String f1667h;

    /* renamed from: i */
    public static boolean f1668i;

    /* renamed from: j */
    public static final /* synthetic */ boolean f1669j = false;

    /* renamed from: a */
    public final IntentHandlerDelegate f1670a;

    /* renamed from: b */
    public final String f1671b;

    /* renamed from: c */
    public DelayedScreenLockIntentHandler f1672c;

    public IntentHandler(IntentHandlerDelegate intentHandlerDelegate, String str) {
        this.f1670a = intentHandlerDelegate;
        this.f1671b = str;
    }

    /* renamed from: b */
    public static ComponentName m1913b(String str) {
        synchronized (f1664e) {
            if (f1663d == null) {
                f1663d = new ComponentName(str, "FakeClass");
            }
        }
        return f1663d;
    }

    /* renamed from: e */
    public static int m1919e(String str) {
        if (str.equals("com.google.android.apps.plus")) {
            return 3;
        }
        if (str.equals("com.google.android.gm")) {
            return 1;
        }
        if (str.equals("com.google.android.talk")) {
            return 6;
        }
        if (str.equals("com.google.android.apps.messaging")) {
            return 7;
        }
        if (str.equals("jp.naver.line.android")) {
            return 9;
        }
        if (str.equals("com.whatsapp")) {
            return 10;
        }
        if (str.equals("com.google.android.googlequicksearchbox")) {
            return 11;
        }
        if (str.equals(RN0.a.getPackageName())) {
            return 5;
        }
        if (str.startsWith("org.chromium.webapk")) {
            return 12;
        }
        if (str.equals("com.yahoo.mobile.client.android.mail")) {
            return 13;
        }
        return str.equals("com.viber.voip") ? 14 : 0;
    }

    /* renamed from: f */
    public static int m1921f(Intent intent) {
        String e = PE2.e(intent, "com.android.browser.application_id");
        if (e != null) {
            return m1919e(e);
        }
        String g = m1922g(intent);
        String k = m1926k(intent);
        if (g != null && g.startsWith("http://t.co/")) {
            return 4;
        }
        if ("android-app://m.facebook.com".equals(k)) {
            return 2;
        }
        if (g != null && g.startsWith("http://news.google.com/news/url?")) {
            return 8;
        }
        Bundle b = PE2.b(intent, "com.android.browser.headers");
        if (b == null || !"http://m.facebook.com".equals(b.get("Referer"))) {
            return 0;
        }
        return 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0094  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1922g(android.content.Intent r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r5.getAction()
            java.lang.String r2 = "android.speech.action.VOICE_SEARCH_RESULTS"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0012
        L_0x0010:
            r1 = r0
            goto L_0x006c
        L_0x0012:
            java.lang.String r1 = "android.speech.extras.VOICE_SEARCH_RESULT_STRINGS"
            java.util.ArrayList r2 = PE2.d(r5, r1)
            if (r2 != 0) goto L_0x002c
            boolean r3 = f1668i
            if (r3 == 0) goto L_0x002c
            java.lang.String r1 = PE2.e(r5, r1)
            if (r1 == 0) goto L_0x002c
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r2.add(r1)
        L_0x002c:
            if (r2 == 0) goto L_0x0010
            int r1 = r2.size()
            if (r1 == 0) goto L_0x0010
            r1 = 1
            org.chromium.content_public.browser.BrowserStartupController r1 = OP2.a(r1)
            boolean r1 = r1.mo9662a()
            if (r1 != 0) goto L_0x0040
            goto L_0x0010
        L_0x0040:
            r1 = 0
            java.lang.Object r2 = r2.get(r1)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController.nativeQualifyPartialURLQuery(r2)
            if (r3 != 0) goto L_0x006b
            java.lang.String r3 = "android.speech.extras.VOICE_SEARCH_RESULT_URLS"
            java.util.ArrayList r3 = PE2.d(r5, r3)
            if (r3 == 0) goto L_0x0062
            int r4 = r3.size()
            if (r4 <= 0) goto L_0x0062
            java.lang.Object r1 = r3.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x006c
        L_0x0062:
            org.chromium.components.search_engines.TemplateUrlService r1 = org.chromium.chrome.browser.search_engines.TemplateUrlServiceFactory.m2927a()
            java.lang.String r1 = r1.mo9625c((java.lang.String) r2)
            goto L_0x006c
        L_0x006b:
            r1 = r3
        L_0x006c:
            if (r1 != 0) goto L_0x008b
            android.net.Uri r1 = r5.getData()
            if (r1 != 0) goto L_0x0075
            goto L_0x008a
        L_0x0075:
            android.net.Uri r1 = r5.getData()
            java.lang.String r2 = r1.getScheme()
            java.lang.String r3 = "customtab"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x008a
            java.lang.String r1 = r1.getQuery()
            goto L_0x008b
        L_0x008a:
            r1 = r0
        L_0x008b:
            if (r1 != 0) goto L_0x0091
            java.lang.String r1 = r5.getDataString()
        L_0x0091:
            if (r1 != 0) goto L_0x0094
            return r0
        L_0x0094:
            java.lang.String r5 = r1.trim()
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 == 0) goto L_0x009f
            r5 = r0
        L_0x009f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.IntentHandler.m1922g(android.content.Intent):java.lang.String");
    }

    /* renamed from: h */
    public static String m1923h(Intent intent) {
        return m1906a(intent, false);
    }

    /* renamed from: i */
    public static int m1924i(Intent intent) {
        return PE2.a(intent, "com.microsoft.emmx.open_hub", -1);
    }

    /* renamed from: j */
    public static int m1925j(Intent intent) {
        int a = PE2.a(intent, "android.support.browser.extra.referrer_policy", 1);
        if (a < 0 || a >= 8) {
            return 1;
        }
        return a;
    }

    /* renamed from: k */
    public static String m1926k(Intent intent) {
        Uri uri = (Uri) PE2.c(intent, "android.intent.extra.REFERRER");
        if (uri == null) {
            String e = PE2.e(intent, "android.intent.extra.REFERRER_NAME");
            uri = e != null ? Uri.parse(e) : null;
        }
        if (uri == null) {
            return null;
        }
        boolean z = false;
        int a = PE2.a(intent, "org.chromium.chrome.browser.referrer_id", 0);
        Pair<Integer, String> pair = f1665f;
        String str = (pair == null || ((Integer) pair.first).intValue() != a) ? null : (String) f1665f.second;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (m1911a(uri)) {
            return uri.toString();
        }
        if (!m1932q(intent)) {
            CustomTabActivity.C0384b a2 = ChromeApplication.m1775e().j().a(intent);
            if (a2 != null && a2.mo8405a(uri)) {
                z = true;
            }
            if (!z) {
                return null;
            }
        }
        return uri.toString();
    }

    /* renamed from: l */
    public static String m1927l(Intent intent) {
        String k = m1926k(intent);
        if (k != null) {
            return k;
        }
        Bundle b = PE2.b(intent, "com.android.browser.headers");
        if (b == null) {
            return null;
        }
        for (String str : b.keySet()) {
            String string = b.getString(str);
            if (string != null && "referer".equals(str.toLowerCase(Locale.US))) {
                Uri normalizeScheme = Uri.parse(string).normalizeScheme();
                if (m1911a(normalizeScheme)) {
                    return normalizeScheme.toString();
                }
            }
        }
        return null;
    }

    /* renamed from: m */
    public static Integer m1928m(Intent intent) {
        Serializable serializable = null;
        try {
            serializable = intent.getSerializableExtra("org.chromium.chrome.browser.tab_launch_type");
        } catch (ClassCastException e) {
            VN0.a("IntentUtils", Eo.a("Invalide class for Serializable: ", "org.chromium.chrome.browser.tab_launch_type"), new Object[]{e});
        } catch (Throwable unused) {
            VN0.a("IntentUtils", Eo.a("getSerializableExtra failed on intent ", intent), new Object[0]);
        }
        return (Integer) serializable;
    }

    /* renamed from: n */
    public static long m1929n(Intent intent) {
        return intent.getLongExtra("org.chromium.chrome.browser.timestamp", -1);
    }

    /* renamed from: o */
    public static String m1930o(Intent intent) {
        return m1922g(intent);
    }

    /* renamed from: p */
    public static long m1931p(Intent intent) {
        return intent.getLongExtra("org.chromium.chrome.browser.webapk_launch_time", -1);
    }

    @Deprecated
    /* renamed from: q */
    public static boolean m1932q(Intent intent) {
        PendingIntent pendingIntent;
        if (intent == null || (pendingIntent = (PendingIntent) PE2.c(intent, "trusted_application_code_extra")) == null) {
            return false;
        }
        if (m1912b().equals(pendingIntent)) {
            return true;
        }
        LV1 lv1 = LV1.a;
        pendingIntent.getCreatorPackage();
        lv1.a();
        return false;
    }

    /* renamed from: r */
    public static void m1933r(Intent intent) {
        if (intent.getData() != null) {
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
            f1667h = intent.getDataString();
        }
    }

    /* renamed from: s */
    public static void m1934s(Intent intent) {
        m1915b(intent, ChromeLauncherActivity.class.getName());
    }

    /* renamed from: t */
    public static boolean m1935t(Intent intent) {
        PendingIntent pendingIntent;
        if (intent == null || (pendingIntent = (PendingIntent) PE2.c(intent, "trusted_application_code_extra")) == null) {
            return false;
        }
        return m1912b().equals(pendingIntent);
    }

    /* renamed from: a */
    public boolean mo8287a() {
        Context context = RN0.a;
        boolean z = false;
        if (!ON0.c(context)) {
            return false;
        }
        if (context.getContentResolver() == null || Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0) {
            z = true;
        }
        if (!z) {
            return true;
        }
        return !((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:73:0x011c, code lost:
        if (r7.equals("mht") == false) goto L_0x0120;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8290c(android.content.Intent r14) {
        /*
            r13 = this;
            org.chromium.chrome.browser.DelayedScreenLockIntentHandler r0 = r13.f1672c
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r0.a(r1)
        L_0x0008:
            java.lang.String r3 = m1922g(r14)
            r0 = 1
            if (r3 == 0) goto L_0x002d
            java.util.Locale r2 = java.util.Locale.getDefault()
            java.lang.String r2 = r3.toLowerCase(r2)
            java.lang.String r4 = "microsoft-edge:signin"
            boolean r4 = r2.startsWith(r4)
            if (r4 != 0) goto L_0x0027
            java.lang.String r4 = "microsoft-edge://mamenroll"
            boolean r2 = r2.startsWith(r4)
            if (r2 == 0) goto L_0x002d
        L_0x0027:
            org.chromium.chrome.browser.IntentHandler$IntentHandlerDelegate r14 = r13.f1670a
            r14.processDeepLinkViewIntent(r3)
            return r0
        L_0x002d:
            cW1 r2 = cW1.b()
            byte[] r4 = r2.c
            r5 = 0
            if (r4 == 0) goto L_0x006f
            java.lang.String r4 = r2.d
            if (r4 != 0) goto L_0x003b
            goto L_0x006f
        L_0x003b:
            java.lang.String r4 = "org.chromium.chrome.browser.user_gesture_token"
            byte[] r4 = r14.getByteArrayExtra(r4)     // Catch:{ all -> 0x0042 }
            goto L_0x0050
        L_0x0042:
            java.lang.String r4 = "getByteArrayExtra failed on intent "
            java.lang.String r4 = Eo.a(r4, r14)
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.String r7 = "IntentUtils"
            VN0.a(r7, r4, r6)
            r4 = r1
        L_0x0050:
            if (r4 == 0) goto L_0x0068
            byte[] r6 = r2.c
            boolean r4 = java.util.Arrays.equals(r4, r6)
            if (r4 == 0) goto L_0x0068
            java.lang.String r4 = r2.d
            java.lang.String r6 = m1922g(r14)
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0068
            r4 = 1
            goto L_0x0069
        L_0x0068:
            r4 = 0
        L_0x0069:
            r2.c = r1
            r2.d = r1
            r8 = r4
            goto L_0x0071
        L_0x006f:
            r1 = 0
            r8 = 0
        L_0x0071:
            java.lang.String r1 = "REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB"
            boolean r1 = PE2.a(r14, r1, r5)
            java.lang.String r2 = "com.android.browser.application_id"
            java.lang.String r4 = "BRING_TAB_TO_FRONT"
            r6 = -1
            r7 = 5
            if (r1 == 0) goto L_0x0081
            r1 = 1
            goto L_0x00b9
        L_0x0081:
            java.lang.String r1 = "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB"
            boolean r1 = PE2.a(r14, r1, r5)
            if (r1 == 0) goto L_0x008b
            r1 = 5
            goto L_0x00b9
        L_0x008b:
            int r1 = PE2.a(r14, r4, r6)
            if (r1 == r6) goto L_0x0093
            r1 = 4
            goto L_0x00b9
        L_0x0093:
            java.lang.String r1 = PE2.e(r14, r2)
            if (r1 == 0) goto L_0x00b8
            java.lang.String r9 = "create_new_tab"
            boolean r9 = PE2.a(r14, r9, r5)
            if (r9 == 0) goto L_0x00a2
            goto L_0x00b8
        L_0x00a2:
            java.lang.String r9 = "REUSE_TAB_MATCHING_ID"
            int r9 = PE2.a(r14, r9, r6)
            if (r9 == r6) goto L_0x00ac
            r1 = 6
            goto L_0x00b9
        L_0x00ac:
            java.lang.String r9 = r13.f1671b
            boolean r1 = r9.equals(r1)
            if (r1 == 0) goto L_0x00b6
            r1 = 3
            goto L_0x00b9
        L_0x00b6:
            r1 = 2
            goto L_0x00b9
        L_0x00b8:
            r1 = 0
        L_0x00b9:
            int r9 = PE2.a(r14, r4, r6)
            if (r3 != 0) goto L_0x00c8
            if (r9 != r6) goto L_0x00c8
            if (r1 == r7) goto L_0x00c8
            boolean r14 = r13.mo8288a((android.content.Intent) r14)
            return r14
        L_0x00c8:
            java.lang.String r4 = m1927l(r14)
            java.lang.String r6 = m1906a((android.content.Intent) r14, (boolean) r0)
            java.lang.String r7 = m1922g(r14)
            if (r7 != 0) goto L_0x00d7
            goto L_0x0120
        L_0x00d7:
            java.lang.String r10 = m1916c((java.lang.String) r7)
            java.lang.String r11 = "content"
            boolean r11 = android.text.TextUtils.equals(r10, r11)
            java.lang.String r12 = "file"
            boolean r10 = android.text.TextUtils.equals(r10, r12)
            if (r11 != 0) goto L_0x00ec
            if (r10 != 0) goto L_0x00ec
            goto L_0x0120
        L_0x00ec:
            java.lang.String r11 = r14.getType()
            if (r11 == 0) goto L_0x00f9
            boolean r12 = m1918d((java.lang.String) r11)
            if (r12 == 0) goto L_0x00f9
            goto L_0x011e
        L_0x00f9:
            if (r10 == 0) goto L_0x0120
            boolean r10 = android.text.TextUtils.isEmpty(r11)
            if (r10 != 0) goto L_0x010a
            java.lang.String r10 = "application/octet-stream"
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x010a
            goto L_0x0120
        L_0x010a:
            java.lang.String r7 = UN0.a(r7)
            java.lang.String r10 = "mhtml"
            boolean r10 = r7.equals(r10)
            if (r10 != 0) goto L_0x011e
            java.lang.String r10 = "mht"
            boolean r7 = r7.equals(r10)
            if (r7 == 0) goto L_0x0120
        L_0x011e:
            r7 = 1
            goto L_0x0121
        L_0x0120:
            r7 = 0
        L_0x0121:
            if (r7 == 0) goto L_0x014e
            if (r1 != 0) goto L_0x014e
            if (r4 != 0) goto L_0x014e
            if (r6 != 0) goto L_0x014e
            Bd2.d()
            vu1 r1 = new vu1
            r1.<init>(r13, r14)
            wd2 r14 = Bd2.b()
            org.chromium.chrome.browser.profiles.Profile r2 = org.chromium.chrome.browser.profiles.Profile.m2911j()
            xd2 r14 = (xd2) r14
            org.chromium.chrome.browser.offlinepages.OfflinePageBridge r14 = r14.a(r2)
            if (r14 != 0) goto L_0x014a
            org.chromium.content_public.browser.LoadUrlParams r14 = new org.chromium.content_public.browser.LoadUrlParams
            r14.<init>(r3, r5)
            r1.onResult(r14)
            goto L_0x014d
        L_0x014a:
            r14.a(r3, r1)
        L_0x014d:
            return r0
        L_0x014e:
            PE2.e(r14, r2)
            r2 = r13
            r5 = r6
            r6 = r1
            r7 = r9
            r9 = r14
            r2.mo8286a(r3, r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.IntentHandler.mo8290c(android.content.Intent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x00db A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f0 A[RETURN] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8291d(android.content.Intent r7) {
        /*
            r6 = this;
            r0 = 1
            boolean r1 = r6.mo8289b((android.content.Intent) r7)     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r7 != 0) goto L_0x000b
            goto L_0x0030
        L_0x000b:
            java.lang.String r1 = r7.getDataString()     // Catch:{ all -> 0x00f1 }
            if (r1 == 0) goto L_0x0029
            java.lang.String r2 = "http-intunemam://"
            boolean r2 = r1.startsWith(r2)     // Catch:{ all -> 0x00f1 }
            if (r2 != 0) goto L_0x0021
            java.lang.String r2 = "https-intunemam://"
            boolean r2 = r1.startsWith(r2)     // Catch:{ all -> 0x00f1 }
            if (r2 == 0) goto L_0x0029
        L_0x0021:
            java.lang.String r2 = "-intunemam://"
            java.lang.String r3 = "://"
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x00f1 }
        L_0x0029:
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ all -> 0x00f1 }
            r7.setData(r1)     // Catch:{ all -> 0x00f1 }
        L_0x0030:
            boolean r1 = m1932q(r7)     // Catch:{ all -> 0x00f1 }
            boolean r2 = m1935t(r7)     // Catch:{ all -> 0x00f1 }
            r3 = 0
            if (r2 != 0) goto L_0x0054
            java.lang.String r2 = "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB"
            boolean r2 = PE2.a(r7, r2, r3)     // Catch:{ all -> 0x00f1 }
            if (r2 == 0) goto L_0x0054
            java.lang.String r2 = f1667h     // Catch:{ all -> 0x00f1 }
            if (r2 == 0) goto L_0x0053
            java.lang.String r2 = f1667h     // Catch:{ all -> 0x00f1 }
            java.lang.String r4 = r7.getDataString()     // Catch:{ all -> 0x00f1 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x00f1 }
            if (r2 != 0) goto L_0x0054
        L_0x0053:
            return r0
        L_0x0054:
            java.lang.String r2 = m1922g(r7)     // Catch:{ all -> 0x00f1 }
            if (r2 != 0) goto L_0x0067
            java.lang.String r4 = "android.intent.action.MAIN"
            java.lang.String r5 = r7.getAction()     // Catch:{ all -> 0x00f1 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00f1 }
            if (r4 == 0) goto L_0x0067
            return r3
        L_0x0067:
            java.lang.String r4 = m1916c((java.lang.String) r2)     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x00cd
            if (r4 == 0) goto L_0x00cd
            java.lang.String r5 = "android.intent.category.BROWSABLE"
            boolean r5 = r7.hasCategory(r5)     // Catch:{ all -> 0x00f1 }
            if (r5 != 0) goto L_0x0085
            java.lang.String r5 = "android.intent.category.DEFAULT"
            boolean r5 = r7.hasCategory(r5)     // Catch:{ all -> 0x00f1 }
            if (r5 != 0) goto L_0x0085
            java.util.Set r5 = r7.getCategories()     // Catch:{ all -> 0x00f1 }
            if (r5 != 0) goto L_0x00cd
        L_0x0085:
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x00f1 }
            java.lang.String r4 = r4.toLowerCase(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = "chrome"
            boolean r5 = r5.equals(r4)     // Catch:{ all -> 0x00f1 }
            if (r5 != 0) goto L_0x00a3
            java.lang.String r5 = "chrome-native"
            boolean r5 = r5.equals(r4)     // Catch:{ all -> 0x00f1 }
            if (r5 != 0) goto L_0x00a3
            java.lang.String r5 = "about"
            boolean r4 = r5.equals(r4)     // Catch:{ all -> 0x00f1 }
            if (r4 == 0) goto L_0x00cd
        L_0x00a3:
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ all -> 0x00f1 }
            java.lang.String r7 = r2.toLowerCase(r7)     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = "about:blank"
            boolean r1 = r1.equals(r7)     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x00cc
            java.lang.String r1 = "about://blank"
            boolean r1 = r1.equals(r7)     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x00cc
            java.lang.String r1 = "chrome://dino"
            boolean r7 = r1.equals(r7)     // Catch:{ all -> 0x00f1 }
            if (r7 == 0) goto L_0x00c2
            goto L_0x00cc
        L_0x00c2:
            java.lang.String r7 = "IntentHandler"
            java.lang.String r1 = "Ignoring internal Chrome URL from untrustworthy source."
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x00f1 }
            VN0.c(r7, r1, r2)     // Catch:{ all -> 0x00f1 }
            return r0
        L_0x00cc:
            return r3
        L_0x00cd:
            if (r1 != 0) goto L_0x00d8
            boolean r1 = r6.mo8287a()     // Catch:{ all -> 0x00f1 }
            if (r1 == 0) goto L_0x00d6
            goto L_0x00d8
        L_0x00d6:
            r1 = 0
            goto L_0x00d9
        L_0x00d8:
            r1 = 1
        L_0x00d9:
            if (r1 != 0) goto L_0x00f0
            org.chromium.chrome.browser.DelayedScreenLockIntentHandler r1 = r6.f1672c     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x00e8
            if (r7 == 0) goto L_0x00e8
            org.chromium.chrome.browser.DelayedScreenLockIntentHandler r1 = new org.chromium.chrome.browser.DelayedScreenLockIntentHandler     // Catch:{ all -> 0x00f1 }
            r1.<init>()     // Catch:{ all -> 0x00f1 }
            r6.f1672c = r1     // Catch:{ all -> 0x00f1 }
        L_0x00e8:
            org.chromium.chrome.browser.DelayedScreenLockIntentHandler r1 = r6.f1672c     // Catch:{ all -> 0x00f1 }
            if (r1 == 0) goto L_0x00ef
            r1.a(r7)     // Catch:{ all -> 0x00f1 }
        L_0x00ef:
            return r0
        L_0x00f0:
            return r3
        L_0x00f1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.IntentHandler.mo8291d(android.content.Intent):boolean");
    }

    /* renamed from: b */
    public static PendingIntent m1912b() {
        Intent intent = new Intent();
        Context context = RN0.a;
        intent.setComponent(m1913b(context.getPackageName()));
        return Bb0.a(context, 0, intent, 0);
    }

    /* renamed from: a */
    public static boolean m1911a(Uri uri) {
        if (uri == null) {
            return false;
        }
        Uri normalizeScheme = uri.normalizeScheme();
        if (!TextUtils.equals(normalizeScheme.getScheme(), "android-app") || TextUtils.isEmpty(normalizeScheme.getHost())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static oQ2 m1907a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new oQ2(new Uri.Builder().scheme("android-app").authority(str).build().toString(), 1);
    }

    /* renamed from: b */
    public static void m1915b(Intent intent, String str) {
        Context d = RN0.d();
        Intent intent2 = new Intent(intent);
        if (str != null) {
            if (f1669j || intent2.getComponent() == null) {
                intent2.setComponent(new ComponentName(d.getPackageName(), str));
            } else {
                throw new AssertionError();
            }
        }
        if (f1669j || (intent2.getFlags() & 268435456) != 0) {
            m1920e(intent2);
            d.startActivity(intent2);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public static void m1920e(Intent intent) {
        if (XV1.d(intent, true)) {
            intent.setPackage(RN0.a.getPackageName());
            intent.putExtra("trusted_application_code_extra", m1912b());
        }
    }

    /* renamed from: a */
    public boolean mo8288a(Intent intent) {
        if (intent == null) {
            return false;
        }
        String str = null;
        String action = intent.getAction();
        if ("android.intent.action.SEARCH".equals(action) || "android.intent.action.MEDIA_SEARCH".equals(action)) {
            str = PE2.e(intent, "query");
        }
        if (str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        this.f1670a.processWebSearchIntent(str);
        return true;
    }

    /* renamed from: b */
    public boolean mo8289b(Intent intent) {
        String g = m1922g(intent);
        if (g == null) {
            return false;
        }
        String c = m1916c(g);
        if (c != null && (c.toLowerCase(Locale.US).equals("javascript") || c.toLowerCase(Locale.US).equals("jar"))) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo8285a(Intent intent, LoadUrlParams loadUrlParams) {
        mo8286a(loadUrlParams.mo9735q(), (String) null, loadUrlParams.mo9737s(), 0, 0, false, intent);
    }

    /* renamed from: a */
    public static void m1910a(Map<String, String> map, Intent intent) {
        if (map == null || map.isEmpty()) {
            intent.removeExtra("com.android.browser.headers");
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry next : map.entrySet()) {
            bundle.putString((String) next.getKey(), (String) next.getValue());
        }
        intent.putExtra("com.android.browser.headers", bundle);
    }

    /* renamed from: b */
    public static void m1914b(Intent intent, int i) {
        intent.putExtra("com.microsoft.emmx.open_hub", i);
    }

    /* renamed from: a */
    public static String m1906a(Intent intent, boolean z) {
        Bundle b = PE2.b(intent, "com.android.browser.headers");
        if (b == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        IntentHeadersRecorder intentHeadersRecorder = z ? new IntentHeadersRecorder() : null;
        for (String str : b.keySet()) {
            String string = b.getString(str);
            if (!"x-chrome-intent-type".equals(str.toLowerCase(Locale.US))) {
                if (z) {
                    intentHeadersRecorder.a(str, string);
                }
                if (HttpUtil.nativeIsAllowedHeader(str, string)) {
                    if (sb.length() != 0) {
                        sb.append(AbstractAccountCredentialCache.NEW_LINE);
                    }
                    Eo.b(sb, str, ": ", string);
                }
            }
        }
        if (z) {
            intentHeadersRecorder.a(m1932q(intent));
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static boolean m1918d(String str) {
        return str.equals("multipart/related") || str.equals("message/rfc822");
    }

    /* renamed from: a */
    public static void m1908a(Intent intent, long j) {
        intent.putExtra("org.chromium.chrome.browser.timestamp", j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8286a(java.lang.String r13, java.lang.String r14, java.lang.String r15, int r16, int r17, boolean r18, android.content.Intent r19) {
        /*
            r12 = this;
            r0 = r15
            r9 = r19
            if (r9 == 0) goto L_0x003a
            if (r13 != 0) goto L_0x0008
            goto L_0x003a
        L_0x0008:
            java.lang.String r1 = m1916c((java.lang.String) r13)
            java.lang.String r2 = "content"
            boolean r1 = android.text.TextUtils.equals(r1, r2)
            if (r1 != 0) goto L_0x0015
            goto L_0x003a
        L_0x0015:
            java.lang.String r1 = r19.getType()
            if (r1 == 0) goto L_0x003a
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0022
            goto L_0x003a
        L_0x0022:
            boolean r2 = m1918d((java.lang.String) r1)
            if (r2 != 0) goto L_0x0029
            goto L_0x003a
        L_0x0029:
            java.lang.String r2 = "X-Chrome-intent-type: "
            java.lang.String r1 = Eo.a(r2, r1)
            if (r0 != 0) goto L_0x0034
            r10 = r12
            r3 = r1
            goto L_0x003c
        L_0x0034:
            java.lang.String r2 = "\n"
            java.lang.String r0 = Eo.b(r15, r2, r1)
        L_0x003a:
            r10 = r12
            r3 = r0
        L_0x003c:
            org.chromium.chrome.browser.IntentHandler$IntentHandlerDelegate r0 = r10.f1670a
            java.lang.String r11 = "com.android.browser.application_id"
            java.lang.String r5 = PE2.e(r9, r11)
            r1 = r13
            r2 = r14
            r4 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r0.processUrlViewIntent(r1, r2, r3, r4, r5, r6, r7, r8)
            int r0 = m1921f(r19)
            r1 = 15
            java.lang.String r2 = "MobileIntent.PageLoadDueToExternalApp"
            org.chromium.base.metrics.RecordHistogram.m1539a(r2, r0, r1)
            if (r0 != 0) goto L_0x006d
            java.lang.String r0 = PE2.e(r9, r11)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x006d
            java.lang.String r1 = "Android.PageLoadDueToExternalApp"
            org.chromium.chrome.browser.rappor.RapporServiceBridge.nativeSampleString(r1, r0)
        L_0x006d:
            java.lang.String r0 = "org.chromium.chrome.browser.eenp"
            java.util.ArrayList r0 = PE2.d(r9, r0)
            if (r0 == 0) goto L_0x0091
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x0091
            java.util.Iterator r0 = r0.iterator()
        L_0x007f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0091
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "Android.ExternalNavigationNotChosen"
            org.chromium.chrome.browser.rappor.RapporServiceBridge.nativeSampleString(r2, r1)
            goto L_0x007f
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.IntentHandler.mo8286a(java.lang.String, java.lang.String, java.lang.String, int, int, boolean, android.content.Intent):void");
    }

    /* renamed from: c */
    public static String m1916c(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(":")) < 0) {
            return null;
        }
        boolean z = false;
        String trim = str.substring(0, indexOf).toLowerCase(Locale.US).trim();
        char[] charArray = trim.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            char c = charArray[i];
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '+' && c != '.') {
                z = true;
                break;
            }
            i++;
        }
        return z ? trim.replaceAll("[^a-z0-9.+-]", BuildConfig.FLAVOR) : trim;
    }

    /* renamed from: c */
    public static void m1917c(Intent intent, int i) {
        intent.putExtra("org.chromium.chrome.browser.tab_launch_type", i);
    }

    /* renamed from: a */
    public static void m1909a(Intent intent, String str) {
        intent.putExtra("android.intent.extra.REFERRER", Uri.parse(str));
        int i = f1666g + 1;
        f1666g = i;
        intent.putExtra("org.chromium.chrome.browser.referrer_id", i);
        f1665f = new Pair<>(Integer.valueOf(f1666g), str);
    }

    /* renamed from: a */
    public static int m1904a(Intent intent, int i) {
        if (intent == null) {
            return i;
        }
        int a = PE2.a(intent, "com.google.chrome.transition_type", 0);
        if (a == 1) {
            return a;
        }
        return (a == 0 || !m1932q(intent)) ? i : a;
    }

    /* renamed from: a */
    public static Intent m1905a(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(SE2.a));
        intent.setClass(context, ChromeLauncherActivity.class);
        intent.putExtra("create_new_tab", true);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", z);
        m1920e(intent);
        return intent;
    }
}
