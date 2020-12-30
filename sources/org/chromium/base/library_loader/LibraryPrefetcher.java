package org.chromium.base.library_loader;

import android.annotation.SuppressLint;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.CommandLine;
import org.chromium.base.SysUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.task.PostTask;

@MainDex
/* compiled from: PG */
public class LibraryPrefetcher {

    /* renamed from: a */
    public static final AtomicBoolean f1508a = new AtomicBoolean();

    /* compiled from: PG */
    public static final class OrderedCodeInfo {

        /* renamed from: a */
        public final String f1509a;

        /* renamed from: b */
        public final long f1510b;

        /* renamed from: c */
        public final long f1511c;

        public OrderedCodeInfo(String str, long j, long j2) {
            this.f1509a = str;
            this.f1510b = j;
            this.f1511c = j2;
        }

        public String toString() {
            StringBuilder a = Eo.a("filename = ");
            a.append(this.f1509a);
            a.append(" startOffset = ");
            a.append(this.f1510b);
            a.append(" length = ");
            a.append(this.f1511c);
            return a.toString();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m1512a(Throwable th, TraceEvent traceEvent) {
        if (th != null) {
            try {
                traceEvent.close();
            } catch (Throwable th2) {
                qI.a.a(th, th2);
            }
        } else {
            traceEvent.close();
        }
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: b */
    public static void m1513b() {
        TraceEvent B = TraceEvent.m1469B("LibraryPrefetcher::maybePinOrderedCodeInMemory");
        OrderedCodeInfo nativeGetOrderedCodeInfo = nativeGetOrderedCodeInfo();
        if (nativeGetOrderedCodeInfo != null) {
            TraceEvent.m1473d("pinOrderedCodeInMemory", nativeGetOrderedCodeInfo.toString());
            Object systemService = RN0.a.getSystemService("pinner");
            if (systemService == null) {
                VN0.c("LibraryPrefetcher", "Cannot get PinnerService.", new Object[0]);
                if (B != null) {
                    m1512a((Throwable) null, B);
                    return;
                }
                return;
            }
            try {
                if (!((Boolean) systemService.getClass().getMethod("pinRangeFromFile", new Class[]{String.class, Integer.TYPE, Integer.TYPE}).invoke(systemService, new Object[]{nativeGetOrderedCodeInfo.f1509a, Integer.valueOf((int) nativeGetOrderedCodeInfo.f1510b), Integer.valueOf((int) nativeGetOrderedCodeInfo.f1511c)})).booleanValue()) {
                    VN0.a("LibraryPrefetcher", "Not allowed to call the method, should not happen", new Object[0]);
                } else {
                    VN0.b("LibraryPrefetcher", "Successfully pinned ordered code", new Object[0]);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                VN0.c("LibraryPrefetcher", "Error invoking the method. " + e.getMessage(), new Object[0]);
            }
            if (B != null) {
                m1512a((Throwable) null, B);
                return;
            }
            return;
        } else if (B != null) {
            m1512a((Throwable) null, B);
            return;
        } else {
            return;
        }
        try {
        } catch (Throwable th) {
            if (B != null) {
                m1512a(r1, B);
            }
            throw th;
        }
    }

    public static native void nativeForkAndPrefetchNativeLibrary();

    public static native OrderedCodeInfo nativeGetOrderedCodeInfo();

    public static native int nativePercentageOfResidentNativeLibraryCode();

    public static native void nativePeriodicallyCollectResidency();

    /* renamed from: a */
    public static void m1511a() {
        SysUtils.nativeLogPageFaultCountToTracing();
        boolean compareAndSet = f1508a.compareAndSet(false, true);
        if (!compareAndSet || !CommandLine.m1384c().mo7859c("log-native-library-residency")) {
            PostTask.m1566a(VP0.l, new wO0(compareAndSet), 0);
        } else {
            new Thread(vO0.a).start();
        }
    }
}
