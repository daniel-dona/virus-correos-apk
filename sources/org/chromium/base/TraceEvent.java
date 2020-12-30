package org.chromium.base;

import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import com.citrix.loggersdk.BuildConfig;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* compiled from: PG */
public class TraceEvent implements AutoCloseable {

    /* renamed from: b */
    public static volatile boolean f1480b;

    /* renamed from: a */
    public final String f1481a;

    /* renamed from: org.chromium.base.TraceEvent$b */
    /* compiled from: PG */
    public static class C0371b implements Printer {

        /* renamed from: b */
        public static final int f1482b = 18;

        /* renamed from: a */
        public String f1483a;

        static {
            Class<TraceEvent> cls = TraceEvent.class;
        }

        public /* synthetic */ C0371b(C0370a aVar) {
        }

        /* renamed from: a */
        public void mo7879a(String str) {
            int i;
            int i2;
            boolean e = EarlyTraceEvent.m1425e();
            if (TraceEvent.f1480b || e) {
                StringBuilder a = Eo.a("Looper.dispatch: ");
                int indexOf = str.indexOf(40, f1482b);
                if (indexOf == -1) {
                    i = -1;
                } else {
                    i = str.indexOf(41, indexOf);
                }
                String str2 = BuildConfig.FLAVOR;
                a.append(i != -1 ? str.substring(indexOf + 1, i) : str2);
                a.append("(");
                int indexOf2 = str.indexOf(125, f1482b);
                if (indexOf2 == -1) {
                    i2 = -1;
                } else {
                    i2 = str.indexOf(58, indexOf2);
                }
                if (i2 == -1) {
                    i2 = str.length();
                }
                if (indexOf2 != -1) {
                    str2 = str.substring(indexOf2 + 2, i2);
                }
                this.f1483a = Eo.a(a, str2, ")");
                if (TraceEvent.f1480b) {
                    TraceEvent.nativeBeginToplevel(this.f1483a);
                } else {
                    EarlyTraceEvent.m1417a(this.f1483a);
                }
            }
        }

        /* renamed from: b */
        public void mo7880b(String str) {
            boolean e = EarlyTraceEvent.m1425e();
            if ((TraceEvent.f1480b || e) && this.f1483a != null) {
                if (TraceEvent.f1480b) {
                    TraceEvent.nativeEndToplevel(this.f1483a);
                } else {
                    EarlyTraceEvent.m1420b(this.f1483a);
                }
            }
            this.f1483a = null;
        }

        public void println(String str) {
            if (str.startsWith(">")) {
                mo7879a(str);
            } else {
                mo7880b(str);
            }
        }
    }

    /* renamed from: org.chromium.base.TraceEvent$d */
    /* compiled from: PG */
    public static final class C0373d {

        /* renamed from: a */
        public static final C0371b f1490a = (CommandLine.m1384c().mo7859c("enable-idle-tracing") ? new C0372c((C0370a) null) : new C0371b((C0370a) null));
    }

    public TraceEvent(String str, String str2) {
        this.f1481a = str;
        m1472c(str, str2);
    }

    /* renamed from: A */
    public static void m1468A(String str) {
        if (f1480b) {
            nativeInstant(str, (String) null);
        }
    }

    /* renamed from: B */
    public static TraceEvent m1469B(String str) {
        return m1474e(str, (String) null);
    }

    /* renamed from: a */
    public static void m1470a(String str, long j) {
        EarlyTraceEvent.m1418a(str, j);
        if (f1480b) {
            nativeFinishAsync(str, j);
        }
    }

    /* renamed from: b */
    public static void m1471b(String str, long j) {
        EarlyTraceEvent.m1421b(str, j);
        if (f1480b) {
            nativeStartAsync(str, j);
        }
    }

    /* renamed from: c */
    public static void m1472c(String str, String str2) {
        EarlyTraceEvent.m1417a(str);
        if (f1480b) {
            nativeBegin(str, str2);
        }
    }

    /* renamed from: d */
    public static void m1473d(String str, String str2) {
        if (f1480b) {
            nativeInstant(str, str2);
        }
    }

    /* renamed from: e */
    public static TraceEvent m1474e(String str, String str2) {
        if (EarlyTraceEvent.m1423c() || f1480b) {
            return new TraceEvent(str, str2);
        }
        return null;
    }

    public static native void nativeBegin(String str, String str2);

    public static native void nativeBeginToplevel(String str);

    public static native void nativeEnd(String str, String str2);

    public static native void nativeEndToplevel(String str);

    public static native void nativeFinishAsync(String str, long j);

    public static native void nativeInstant(String str, String str2);

    public static native void nativeRegisterEnabledObserver();

    public static native void nativeStartATrace();

    public static native void nativeStartAsync(String str, long j);

    public static native void nativeStopATrace();

    @CalledByNative
    public static void setEnabled(boolean z) {
        if (z) {
            EarlyTraceEvent.m1416a();
        }
        if (f1480b != z) {
            f1480b = z;
            ThreadUtils.m1466f().setMessageLogging(z ? C0373d.f1490a : null);
        }
    }

    /* renamed from: z */
    public static void m1475z(String str) {
        EarlyTraceEvent.m1420b(str);
        if (f1480b) {
            nativeEnd(str, (String) null);
        }
    }

    public void close() {
        m1475z(this.f1481a);
    }

    /* renamed from: org.chromium.base.TraceEvent$c */
    /* compiled from: PG */
    public static final class C0372c extends C0371b implements MessageQueue.IdleHandler {

        /* renamed from: c */
        public long f1484c;

        /* renamed from: d */
        public long f1485d;

        /* renamed from: e */
        public int f1486e;

        /* renamed from: f */
        public int f1487f;

        /* renamed from: g */
        public int f1488g;

        /* renamed from: h */
        public boolean f1489h;

        public /* synthetic */ C0372c(C0370a aVar) {
            super((C0370a) null);
        }

        /* renamed from: a */
        public final void mo7882a() {
            if (TraceEvent.f1480b && !this.f1489h) {
                this.f1484c = SystemClock.elapsedRealtime();
                Looper.myQueue().addIdleHandler(this);
                this.f1489h = true;
            } else if (this.f1489h && !TraceEvent.f1480b) {
                Looper.myQueue().removeIdleHandler(this);
                this.f1489h = false;
            }
        }

        /* renamed from: b */
        public final void mo7880b(String str) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.f1485d;
            if (elapsedRealtime > 16) {
                String str2 = "observed a task that took " + elapsedRealtime + "ms: " + str;
                TraceEvent.m1473d("TraceEvent.LooperMonitor:IdleStats", str2);
                Log.println(5, "TraceEvent.LooperMonitor", str2);
            }
            super.mo7880b(str);
            mo7882a();
            this.f1486e++;
            this.f1488g++;
        }

        public final boolean queueIdle() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.f1484c == 0) {
                this.f1484c = elapsedRealtime;
            }
            long j = elapsedRealtime - this.f1484c;
            this.f1487f++;
            TraceEvent.m1472c("Looper.queueIdle", this.f1488g + " tasks since last idle.");
            if (j > 48) {
                String str = this.f1486e + " tasks and " + this.f1487f + " idles processed so far, " + this.f1488g + " tasks bursted and " + j + "ms elapsed since last idle";
                TraceEvent.m1473d("TraceEvent.LooperMonitor:IdleStats", str);
                Log.println(3, "TraceEvent.LooperMonitor", str);
            }
            this.f1484c = elapsedRealtime;
            this.f1488g = 0;
            return true;
        }

        /* renamed from: a */
        public final void mo7879a(String str) {
            if (this.f1488g == 0) {
                TraceEvent.m1475z("Looper.queueIdle");
            }
            this.f1485d = SystemClock.elapsedRealtime();
            mo7882a();
            super.mo7879a(str);
        }
    }
}
