package org.chromium.chrome.browser.feedback;

import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: PG */
public final class ConnectivityChecker {

    /* compiled from: PG */
    public interface ConnectivityCheckerCallback {
        void onResult(int i);
    }

    /* renamed from: org.chromium.chrome.browser.feedback.ConnectivityChecker$a */
    /* compiled from: PG */
    public class C0420a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ ConnectivityCheckerCallback f2010a;

        /* renamed from: b */
        public final /* synthetic */ int f2011b;

        public C0420a(ConnectivityCheckerCallback connectivityCheckerCallback, int i) {
            this.f2010a = connectivityCheckerCallback;
            this.f2011b = i;
        }

        public void run() {
            this.f2010a.onResult(this.f2011b);
        }
    }

    /* renamed from: a */
    public static void m2415a(ConnectivityCheckerCallback connectivityCheckerCallback, int i) {
        PostTask.m1566a(iQ2.a, new C0420a(connectivityCheckerCallback, i), 0);
    }

    @CalledByNative
    public static void executeCallback(Object obj, int i) {
        ((ConnectivityCheckerCallback) obj).onResult(i);
    }

    public static native void nativeCheckConnectivity(Profile profile, String str, long j, ConnectivityCheckerCallback connectivityCheckerCallback);

    public static native boolean nativeIsUrlValid(String str);

    /* renamed from: a */
    public static void m2417a(boolean z, int i, ConnectivityCheckerCallback connectivityCheckerCallback) {
        String str = z ? "https://clients4.google.com/generate_204" : "http://clients4.google.com/generate_204";
        if (!nativeIsUrlValid(str)) {
            VN0.c("feedback", "Predefined URL invalid.", new Object[0]);
            m2415a(connectivityCheckerCallback, 4);
            return;
        }
        try {
            new VW1(new URL(str), i, connectivityCheckerCallback).a(AP0.f);
        } catch (MalformedURLException e) {
            VN0.c("feedback", "Failed to parse predefined URL: " + e, new Object[0]);
            m2415a(connectivityCheckerCallback, 4);
        }
    }

    /* renamed from: a */
    public static void m2416a(Profile profile, boolean z, int i, ConnectivityCheckerCallback connectivityCheckerCallback) {
        String str = z ? "https://clients4.google.com/generate_204" : "http://clients4.google.com/generate_204";
        ThreadUtils.m1462c();
        nativeCheckConnectivity(profile, str, (long) i, connectivityCheckerCallback);
    }
}
