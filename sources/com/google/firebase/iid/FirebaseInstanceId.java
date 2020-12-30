package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.Keep;
import android.util.Base64;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
public class FirebaseInstanceId {

    /* renamed from: h */
    public static final Executor f904h = rJ.a;

    /* renamed from: i */
    public static final long f905i = TimeUnit.HOURS.toSeconds(8);

    /* renamed from: j */
    public static cJ f906j;

    /* renamed from: k */
    public static final Executor f907k = Executors.newCachedThreadPool();
    @VisibleForTesting

    /* renamed from: l */
    public static ScheduledThreadPoolExecutor f908l;

    /* renamed from: m */
    public static final Executor f909m = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a */
    public final FirebaseApp f910a;

    /* renamed from: b */
    public final TI f911b;

    /* renamed from: c */
    public IRpc f912c;

    /* renamed from: d */
    public final WI f913d = new WI();

    /* renamed from: e */
    public final gJ f914e;

    /* renamed from: f */
    public boolean f915f = false;

    /* renamed from: g */
    public boolean f916g;

    public FirebaseInstanceId(FirebaseApp firebaseApp, TI ti) {
        boolean z;
        ApplicationInfo applicationInfo;
        if (TI.a(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (f906j == null) {
                    firebaseApp.mo1997b();
                    f906j = new cJ(firebaseApp.f896a);
                }
            }
            this.f910a = firebaseApp;
            this.f911b = ti;
            if (this.f912c == null) {
                sJ sJVar = (IRpc) firebaseApp.mo1995a(IRpc.class);
                this.f912c = sJVar == null ? new sJ(firebaseApp, ti, f909m) : sJVar;
            }
            this.f912c = this.f912c;
            this.f914e = new gJ(f906j);
            FirebaseApp firebaseApp2 = this.f910a;
            firebaseApp2.mo1997b();
            Context context = firebaseApp2.f896a;
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                z = sharedPreferences.getBoolean("auto_init", true);
            } else {
                try {
                    PackageManager packageManager = context.getPackageManager();
                    if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), ByteString.CONCATENATE_BY_COPY_SIZE)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                        z = applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                try {
                    Class.forName("FJ");
                } catch (ClassNotFoundException unused2) {
                    FirebaseApp firebaseApp3 = this.f910a;
                    firebaseApp3.mo1997b();
                    Context context2 = firebaseApp3.f896a;
                    Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                    intent.setPackage(context2.getPackageName());
                    ResolveInfo resolveService = context2.getPackageManager().resolveService(intent, 0);
                    if (resolveService == null || resolveService.serviceInfo == null) {
                        z = false;
                    }
                }
                z = true;
            }
            this.f916g = z;
            if (mo2032g()) {
                dJ c = mo2028c();
                if (c == null || c.a(this.f911b.c()) || this.f914e.a()) {
                    mo2020a();
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    /* renamed from: a */
    public static void m807a(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (f908l == null) {
                f908l = new ScheduledThreadPoolExecutor(1);
            }
            f908l.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        Class<FirebaseInstanceId> cls = FirebaseInstanceId.class;
        synchronized (cls) {
            firebaseApp.mo1997b();
            firebaseInstanceId = (FirebaseInstanceId) firebaseApp.f899d.get(cls);
        }
        return firebaseInstanceId;
    }

    /* renamed from: h */
    public static FirebaseInstanceId m808h() {
        return getInstance(FirebaseApp.m799d());
    }

    /* renamed from: i */
    public static String m809i() {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(f906j.b(BuildConfig.FLAVOR).a.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    /* renamed from: j */
    public static boolean m810j() {
        if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
        }
        return true;
    }

    /* renamed from: a */
    public final /* synthetic */ Task mo2017a(String str, String str2, String str3) {
        return this.f912c.getToken(str, str2, str3);
    }

    /* renamed from: a */
    public final <T> T mo2018a(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    mo2030e();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    /* renamed from: a */
    public String mo2019a(String str, String str2) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String str3 = (str2.isEmpty() || str2.equalsIgnoreCase("fcm") || str2.equalsIgnoreCase("gcm")) ? "*" : str2;
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            f907k.execute(new oJ(this, str, str2, taskCompletionSource, str3));
            return (String) mo2018a(taskCompletionSource.getTask());
        }
        throw new IOException("MAIN_THREAD");
    }

    /* renamed from: a */
    public final synchronized void mo2020a() {
        if (!this.f915f) {
            mo2021a(0);
        }
    }

    /* renamed from: a */
    public final synchronized void mo2021a(long j) {
        m807a((Runnable) new eJ(this, this.f911b, this.f914e, Math.min(Math.max(30, j << 1), f905i)), j);
        this.f915f = true;
    }

    /* renamed from: a */
    public final void mo2022a(String str) throws IOException {
        dJ c = mo2028c();
        if (c == null || c.a(this.f911b.c())) {
            throw new IOException("token not available");
        }
        mo2018a(this.f912c.subscribeToTopic(m809i(), c.a, str));
    }

    /* renamed from: a */
    public final /* synthetic */ void mo2023a(String str, String str2, TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            String str3 = (String) task.getResult();
            f906j.a(BuildConfig.FLAVOR, str, str2, str3, this.f911b.c());
            taskCompletionSource.setResult(str3);
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    /* renamed from: a */
    public final /* synthetic */ void mo2024a(String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        dJ a = f906j.a(BuildConfig.FLAVOR, str, str2);
        if (a == null || a.a(this.f911b.c())) {
            this.f913d.a(str, str3, new pJ(this, m809i(), str, str3)).addOnCompleteListener(f907k, new qJ(this, str, str3, taskCompletionSource));
            return;
        }
        taskCompletionSource.setResult(a.a);
    }

    /* renamed from: a */
    public final synchronized void mo2025a(boolean z) {
        this.f915f = z;
    }

    /* renamed from: b */
    public final FirebaseApp mo2026b() {
        return this.f910a;
    }

    /* renamed from: b */
    public final void mo2027b(String str) throws IOException {
        dJ c = mo2028c();
        if (c == null || c.a(this.f911b.c())) {
            throw new IOException("token not available");
        }
        mo2018a(this.f912c.unsubscribeFromTopic(m809i(), c.a, str));
    }

    /* renamed from: c */
    public final dJ mo2028c() {
        return f906j.a(BuildConfig.FLAVOR, TI.a(this.f910a), "*");
    }

    /* renamed from: d */
    public final String mo2029d() throws IOException {
        return mo2019a(TI.a(this.f910a), "*");
    }

    /* renamed from: e */
    public final synchronized void mo2030e() {
        f906j.c();
        if (mo2032g()) {
            mo2020a();
        }
    }

    /* renamed from: f */
    public final void mo2031f() {
        f906j.c(BuildConfig.FLAVOR);
        mo2020a();
    }

    @VisibleForTesting
    /* renamed from: g */
    public final synchronized boolean mo2032g() {
        return this.f916g;
    }
}
