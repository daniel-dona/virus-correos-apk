package org.chromium.content.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.bridge.PromiseImpl;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;

/* compiled from: PG */
public class TracingControllerAndroidImpl implements hQ2 {

    /* renamed from: a */
    public final Context f2412a;

    /* renamed from: b */
    public final C0439a f2413b;

    /* renamed from: c */
    public final TracingIntentFilter f2414c;

    /* renamed from: d */
    public boolean f2415d;

    /* renamed from: e */
    public boolean f2416e = true;

    /* renamed from: f */
    public String f2417f;

    /* renamed from: g */
    public boolean f2418g;

    /* renamed from: h */
    public long f2419h;

    /* compiled from: PG */
    public static class TracingIntentFilter extends IntentFilter {
        public TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + "." + "GPU_PROFILER_START");
            addAction(context.getPackageName() + "." + "GPU_PROFILER_STOP");
            addAction(context.getPackageName() + "." + "GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    /* renamed from: org.chromium.content.browser.TracingControllerAndroidImpl$a */
    /* compiled from: PG */
    public class C0439a extends BroadcastReceiver {
        public C0439a() {
        }

        public void onReceive(Context context, Intent intent) {
            String str;
            if (intent.getAction().endsWith("GPU_PROFILER_START")) {
                String stringExtra = intent.getStringExtra("categories");
                if (TextUtils.isEmpty(stringExtra)) {
                    str = TracingControllerAndroidImpl.this.nativeGetDefaultCategories();
                } else {
                    str = stringExtra.replaceFirst("_DEFAULT_CHROME_CATEGORIES", TracingControllerAndroidImpl.this.nativeGetDefaultCategories());
                }
                String str2 = str;
                String str3 = intent.getStringExtra("continuous") == null ? "record-until-full" : "record-continuously";
                String stringExtra2 = intent.getStringExtra(PromiseImpl.STACK_FRAME_KEY_FILE);
                if (stringExtra2 != null) {
                    TracingControllerAndroidImpl.this.mo9680a(stringExtra2, true, str2, str3, false);
                } else {
                    TracingControllerAndroidImpl.this.mo9681a(true, str2, str3);
                }
            } else if (intent.getAction().endsWith("GPU_PROFILER_STOP")) {
                TracingControllerAndroidImpl.this.mo9679a((Callback<Void>) null);
            } else if (intent.getAction().endsWith("GPU_PROFILER_LIST_CATEGORIES")) {
                TracingControllerAndroidImpl.this.mo9685c();
            } else {
                VN0.a("cr.TracingController", "Unexpected intent: %s", new Object[]{intent});
            }
        }
    }

    public TracingControllerAndroidImpl(Context context) {
        this.f2412a = context;
        this.f2413b = new C0439a();
        this.f2414c = new TracingIntentFilter(context);
    }

    @CalledByNative
    public static String generateTracingFilePath() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        StringBuilder a = Eo.a("chrome-profile-results-");
        a.append(simpleDateFormat.format(new Date()));
        return new File(externalStoragePublicDirectory, a.toString()).getPath();
    }

    private native void nativeDestroy(long j);

    /* access modifiers changed from: private */
    public native String nativeGetDefaultCategories();

    private native boolean nativeGetKnownCategoriesAsync(long j, Callback<String[]> callback);

    private native boolean nativeGetTraceBufferUsageAsync(long j, Callback<Pair<Float, Long>> callback);

    private native long nativeInit();

    private native boolean nativeStartTracing(long j, String str, String str2);

    private native void nativeStopTracing(long j, String str, boolean z, Callback<Void> callback);

    /* renamed from: b */
    public IntentFilter mo9682b() {
        return this.f2414c;
    }

    /* renamed from: c */
    public void mo9685c() {
        if (!mo9684b((Callback<String[]>) null)) {
            VN0.a("cr.TracingController", "Unable to fetch tracing category list.", new Object[0]);
        }
    }

    /* renamed from: d */
    public final void mo9687d() {
        if (this.f2419h == 0) {
            this.f2419h = nativeInit();
        }
    }

    public void destroy() {
        long j = this.f2419h;
        if (j != 0) {
            nativeDestroy(j);
            this.f2419h = 0;
        }
    }

    /* renamed from: e */
    public boolean mo9689e() {
        return this.f2415d;
    }

    @CalledByNative
    public void onKnownCategoriesReceived(String[] strArr, Object obj) {
        if (obj != null) {
            ((Callback) obj).onResult(strArr);
        }
    }

    @CalledByNative
    public void onTraceBufferUsageReceived(float f, long j, Object obj) {
        ((Callback) obj).onResult(new Pair(Float.valueOf(f), Long.valueOf(j)));
    }

    @CalledByNative
    public void onTracingStopped(Object obj) {
        if (!mo9689e()) {
            VN0.a("cr.TracingController", "Received onTracingStopped, but we aren't tracing", new Object[0]);
            return;
        }
        VN0.b("cr.TracingController", String.format("Profiler finished. Results are in %s.", new Object[]{this.f2417f}), new Object[0]);
        mo9683b(this.f2412a.getString(wx0.profiler_stopped_toast, new Object[]{this.f2417f}));
        this.f2415d = false;
        this.f2417f = null;
        this.f2418g = false;
        if (obj != null) {
            ((Callback) obj).onResult(null);
        }
    }

    /* renamed from: a */
    public BroadcastReceiver mo9676a() {
        return this.f2413b;
    }

    /* renamed from: b */
    public boolean mo9684b(Callback<String[]> callback) {
        mo9687d();
        return nativeGetKnownCategoriesAsync(this.f2419h, callback);
    }

    /* renamed from: a */
    public void mo9677a(Context context) {
        context.registerReceiver(mo9676a(), mo9682b());
    }

    /* renamed from: c */
    public boolean mo9686c(Callback<Pair<Float, Long>> callback) {
        mo9687d();
        return nativeGetTraceBufferUsageAsync(this.f2419h, callback);
    }

    /* renamed from: a */
    public boolean mo9681a(boolean z, String str, String str2) {
        return mo9680a((String) null, z, str, str2, false);
    }

    /* renamed from: b */
    public final void mo9683b(String str) {
        if (this.f2416e) {
            CK3.a(this.f2412a, str, 0).a();
        }
    }

    /* renamed from: a */
    public boolean mo9680a(String str, boolean z, String str2, String str3, boolean z2) {
        this.f2416e = z;
        if (str == null && (str = generateTracingFilePath()) == null) {
            mo9678a(this.f2412a.getString(wx0.profiler_no_storage_toast));
            return false;
        } else if (mo9689e()) {
            VN0.a("cr.TracingController", "Received startTracing, but we're already tracing", new Object[0]);
            return false;
        } else {
            mo9687d();
            if (!nativeStartTracing(this.f2419h, str2, str3)) {
                mo9678a(this.f2412a.getString(wx0.profiler_error_toast));
                return false;
            }
            VN0.b("cr.TracingController", String.format("Profiler started: %s", new Object[]{str2}), new Object[0]);
            mo9683b(this.f2412a.getString(wx0.profiler_started_toast) + ": " + str2);
            this.f2417f = str;
            this.f2418g = z2;
            this.f2415d = true;
            return true;
        }
    }

    /* renamed from: a */
    public void mo9679a(Callback<Void> callback) {
        if (mo9689e()) {
            nativeStopTracing(this.f2419h, this.f2417f, this.f2418g, callback);
        }
    }

    /* renamed from: a */
    public final void mo9678a(String str) {
        VN0.a("cr.TracingController", str, new Object[0]);
        if (this.f2416e) {
            CK3.a(this.f2412a, str, 0).a();
        }
    }
}
