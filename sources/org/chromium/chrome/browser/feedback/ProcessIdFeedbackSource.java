package org.chromium.chrome.browser.feedback;

import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class ProcessIdFeedbackSource implements TW1 {

    /* renamed from: c */
    public static final Map<String, Integer> f2012c = new HashMap();

    /* renamed from: a */
    public Map<String, String> f2013a;

    /* renamed from: b */
    public boolean f2014b;

    static {
        f2012c.put("renderer", 3);
        f2012c.put("utility", 6);
        f2012c.put("gpu-process", 9);
    }

    public static native long nativeGetCurrentPid();

    private native long[] nativeGetProcessIdsForType(long j, int i);

    public static native void nativeStart(ProcessIdFeedbackSource processIdFeedbackSource);

    @CalledByNative
    private void prepareCompleted(long j) {
        this.f2013a = new HashMap();
        for (Map.Entry next : f2012c.entrySet()) {
            long[] nativeGetProcessIdsForType = nativeGetProcessIdsForType(j, ((Integer) next.getValue()).intValue());
            if (nativeGetProcessIdsForType.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (long j2 : nativeGetProcessIdsForType) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(String.valueOf(j2));
                }
                this.f2013a.put("Process IDs (" + ((String) next.getKey()) + ")", sb.toString());
            }
        }
        this.f2013a.put("Process IDs (" + "browser" + ")", Long.toString(nativeGetCurrentPid()));
        this.f2014b = true;
    }

    /* renamed from: a */
    public void mo8749a(Runnable runnable) {
        nativeStart(this);
    }

    /* renamed from: b */
    public Map<String, String> mo8751b() {
        return this.f2013a;
    }

    /* renamed from: a */
    public boolean mo8750a() {
        return this.f2014b;
    }
}
