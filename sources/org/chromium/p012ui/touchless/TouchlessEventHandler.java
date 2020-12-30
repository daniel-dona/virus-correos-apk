package org.chromium.p012ui.touchless;

import android.os.StrictMode;
import org.chromium.base.BuildInfo;

/* renamed from: org.chromium.ui.touchless.TouchlessEventHandler */
/* compiled from: PG */
public abstract class TouchlessEventHandler {
    static {
        StrictMode.ThreadPolicy threadPolicy = null;
        if (!BuildInfo.m1372a()) {
            threadPolicy = StrictMode.allowThreadDiskReads();
        }
        try {
            TouchlessEventHandler touchlessEventHandler = (TouchlessEventHandler) Class.forName("org.chromium.ui.touchless.TouchlessEventHandlerInternal").newInstance();
            if (threadPolicy == null) {
                return;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException unused) {
            if (threadPolicy == null) {
                return;
            }
        } catch (Throwable th) {
            if (threadPolicy != null) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
            throw th;
        }
        StrictMode.setThreadPolicy(threadPolicy);
    }

    /* renamed from: a */
    public static void m3754a(vK3 vk3) {
    }

    /* renamed from: a */
    public static boolean m3757a() {
        return false;
    }

    /* renamed from: a */
    public static boolean m3758a(int i) {
        throw null;
    }

    /* renamed from: b */
    public static void m3759b(vK3 vk3) {
    }

    /* renamed from: a */
    public static void m3756a(boolean z, boolean z2, boolean z3, boolean z4) {
        throw null;
    }

    /* renamed from: a */
    public static void m3755a(boolean z) {
        throw null;
    }
}
