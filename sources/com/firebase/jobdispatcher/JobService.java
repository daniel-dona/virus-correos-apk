package com.firebase.jobdispatcher;

import CF;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import wF;

/* compiled from: PG */
public abstract class JobService extends Service {

    /* renamed from: d */
    public static final Handler f868d = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public final ExecutorService f869a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: b */
    public final T8<String, C0197b> f870b = new T8<>(1);

    /* renamed from: c */
    public final wF.a f871c = new C0196a();

    /* renamed from: com.firebase.jobdispatcher.JobService$b */
    /* compiled from: PG */
    public static final class C0197b {

        /* renamed from: a */
        public final DF f873a;

        /* renamed from: b */
        public final uF f874b;

        /* renamed from: c */
        public final long f875c;

        public /* synthetic */ C0197b(DF df, uF uFVar, long j, C0196a aVar) {
            this.f873a = df;
            this.f874b = uFVar;
            this.f875c = j;
        }

        /* renamed from: a */
        public void mo1987a(int i) {
            try {
                uF uFVar = this.f874b;
                AF af = GooglePlayReceiver.f860n;
                DF df = this.f873a;
                Bundle bundle = new Bundle();
                af.a(df, bundle);
                uFVar.a(bundle, i);
            } catch (RemoteException e) {
                Log.e("FJD.JobService", "Failed to send result to driver", e);
            }
        }
    }

    /* renamed from: com.firebase.jobdispatcher.JobService$c */
    /* compiled from: PG */
    public static class C0198c implements Runnable {

        /* renamed from: a */
        public final int f876a;

        /* renamed from: b */
        public final JobService f877b;

        /* renamed from: c */
        public final DF f878c;

        /* renamed from: d */
        public final uF f879d;

        /* renamed from: e */
        public final C0197b f880e;

        /* renamed from: k */
        public final int f881k;

        /* renamed from: n */
        public final boolean f882n;

        /* renamed from: p */
        public final Intent f883p;

        public C0198c(int i, JobService jobService, DF df, uF uFVar, C0197b bVar, Intent intent, boolean z, int i2) {
            this.f876a = i;
            this.f877b = jobService;
            this.f878c = df;
            this.f879d = uFVar;
            this.f880e = bVar;
            this.f883p = intent;
            this.f882n = z;
            this.f881k = i2;
        }

        /* renamed from: a */
        public static C0198c m788a(JobService jobService, C0197b bVar, boolean z, int i) {
            return new C0198c(2, jobService, (DF) null, (uF) null, bVar, (Intent) null, z, i);
        }

        public void run() {
            switch (this.f876a) {
                case 1:
                    JobService.m769a(this.f877b, this.f878c);
                    return;
                case 2:
                    this.f877b.mo1967a(this.f880e, this.f882n, this.f881k);
                    return;
                case 3:
                    this.f877b.mo1963a();
                    return;
                case 4:
                    this.f877b.mo1965a(this.f878c, this.f879d);
                    return;
                case 5:
                    this.f877b.mo1966a(this.f878c, this.f882n);
                    return;
                case 6:
                    this.f880e.mo1987a(this.f881k);
                    return;
                case 7:
                    this.f877b.mo1964a(this.f878c, this.f881k);
                    return;
                default:
                    throw new AssertionError("unreachable");
            }
        }

        /* renamed from: a */
        public static C0198c m787a(JobService jobService, DF df, int i) {
            return new C0198c(7, jobService, df, (uF) null, (C0197b) null, (Intent) null, false, i);
        }
    }

    /* renamed from: a */
    public abstract boolean mo1969a(DF df);

    /* renamed from: b */
    public final void mo1970b(DF df, boolean z) {
        if (df == null) {
            Log.e("FJD.JobService", "jobFinished called with a null JobParameters");
        } else {
            this.f869a.execute(C0198c.m787a(this, df, z ? 1 : 0));
        }
    }

    /* renamed from: b */
    public abstract boolean mo1971b(DF df);

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        mo1968a(printWriter);
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public final IBinder onBind(Intent intent) {
        return this.f871c;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public final void onStart(Intent intent, int i) {
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        stopSelf(i2);
        return 2;
    }

    public final void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    public final boolean onUnbind(Intent intent) {
        this.f869a.execute(new C0198c(3, this, (DF) null, (uF) null, (C0197b) null, intent, false, 0));
        return super.onUnbind(intent);
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }

    /* renamed from: a */
    public final void mo1965a(DF df, uF uFVar) {
        synchronized (this.f870b) {
            if (this.f870b.containsKey(df.getTag())) {
                Log.w("FJD.JobService", String.format(Locale.US, "Job with tag = %s was already running.", new Object[]{df.getTag()}));
                return;
            }
            this.f870b.put(df.getTag(), new C0197b(df, uFVar, SystemClock.elapsedRealtime(), (C0196a) null));
            f868d.post(new C0198c(1, this, df, (uF) null, (C0197b) null, (Intent) null, false, 0));
        }
    }

    /* renamed from: com.firebase.jobdispatcher.JobService$a */
    /* compiled from: PG */
    public class C0196a extends wF.a {
        public C0196a() {
        }

        /* renamed from: a */
        public void mo1985a(Bundle bundle, uF uFVar) {
            CF.a a = GooglePlayReceiver.f860n.a(bundle);
            if (a == null) {
                Log.wtf("FJD.JobService", "start: unknown invocation provided");
                return;
            }
            JobService jobService = JobService.this;
            jobService.f869a.execute(new C0198c(4, jobService, a.a(), uFVar, (C0197b) null, (Intent) null, false, 0));
        }

        /* renamed from: a */
        public void mo1986a(Bundle bundle, boolean z) {
            CF.a a = GooglePlayReceiver.f860n.a(bundle);
            if (a == null) {
                Log.wtf("FJD.JobService", "stop: unknown invocation provided");
                return;
            }
            JobService jobService = JobService.this;
            jobService.f869a.execute(new C0198c(5, jobService, a.a(), (uF) null, (C0197b) null, (Intent) null, z, 0));
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m769a(JobService jobService, DF df) {
        if (!jobService.mo1969a(df)) {
            jobService.f869a.execute(C0198c.m787a(jobService, df, 0));
        }
    }

    /* renamed from: a */
    public final void mo1966a(DF df, boolean z) {
        synchronized (this.f870b) {
            C0197b bVar = (C0197b) this.f870b.remove(df.getTag());
            if (bVar == null) {
                boolean isLoggable = Log.isLoggable("FJD.JobService", 3);
            } else {
                f868d.post(C0198c.m788a(this, bVar, z, 0));
            }
        }
    }

    /* renamed from: a */
    public final void mo1967a(C0197b bVar, boolean z, int i) {
        boolean b = mo1971b(bVar.f873a);
        if (z) {
            this.f869a.execute(new C0198c(6, (JobService) null, (DF) null, (uF) null, bVar, (Intent) null, false, b ? 1 : i));
        }
    }

    /* renamed from: a */
    public final void mo1964a(DF df, int i) {
        synchronized (this.f870b) {
            C0197b bVar = (C0197b) this.f870b.remove(df.getTag());
            if (bVar != null) {
                bVar.mo1987a(i);
            }
        }
    }

    /* renamed from: a */
    public final void mo1963a() {
        synchronized (this.f870b) {
            for (int size = this.f870b.size() - 1; size >= 0; size--) {
                C0197b bVar = (C0197b) this.f870b.remove(this.f870b.c(size));
                if (bVar != null) {
                    f868d.post(C0198c.m788a(this, bVar, true, 2));
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo1968a(PrintWriter printWriter) {
        synchronized (this.f870b) {
            if (this.f870b.isEmpty()) {
                printWriter.println("No running jobs");
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            printWriter.println("Running jobs:");
            for (int i = 0; i < this.f870b.size(); i++) {
                C0197b bVar = (C0197b) this.f870b.get(this.f870b.c(i));
                String quote = JSONObject.quote(bVar.f873a.getTag());
                String formatElapsedTime = DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(elapsedRealtime - bVar.f875c));
                printWriter.println("    * " + quote + " has been running for " + formatElapsedTime);
            }
        }
    }
}
