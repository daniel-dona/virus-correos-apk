package org.chromium.components.viz.service.p011gl;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.components.viz.service.gl.ThrowUncaughtException */
/* compiled from: PG */
public abstract class ThrowUncaughtException {

    /* renamed from: org.chromium.components.viz.service.gl.ThrowUncaughtException$a */
    /* compiled from: PG */
    public class C0434a implements Runnable {
        public void run() {
            throw new RuntimeException("Intentional exception not caught by JNI");
        }
    }

    @CalledByNative
    public static void post() {
        ThreadUtils.m1457a((Runnable) new C0434a());
    }
}
