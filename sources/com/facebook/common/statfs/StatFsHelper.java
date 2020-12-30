package com.facebook.common.statfs;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PG */
public class StatFsHelper {

    /* renamed from: h */
    public static StatFsHelper f238h;

    /* renamed from: i */
    public static final long f239i = TimeUnit.MINUTES.toMillis(2);

    /* renamed from: a */
    public volatile StatFs f240a = null;

    /* renamed from: b */
    public volatile File f241b;

    /* renamed from: c */
    public volatile StatFs f242c = null;

    /* renamed from: d */
    public volatile File f243d;

    /* renamed from: e */
    public long f244e;

    /* renamed from: f */
    public final Lock f245f = new ReentrantLock();

    /* renamed from: g */
    public volatile boolean f246g = false;

    /* compiled from: PG */
    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    /* renamed from: c */
    public static synchronized StatFsHelper m247c() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            if (f238h == null) {
                f238h = new StatFsHelper();
            }
            statFsHelper = f238h;
        }
        return statFsHelper;
    }

    /* renamed from: a */
    public final void mo325a() {
        if (!this.f246g) {
            this.f245f.lock();
            try {
                if (!this.f246g) {
                    this.f241b = Environment.getDataDirectory();
                    this.f243d = Environment.getExternalStorageDirectory();
                    mo326b();
                    this.f246g = true;
                }
            } finally {
                this.f245f.unlock();
            }
        }
    }

    /* renamed from: b */
    public final void mo326b() {
        this.f240a = mo324a(this.f240a, this.f241b);
        this.f242c = mo324a(this.f242c, this.f243d);
        this.f244e = SystemClock.uptimeMillis();
    }

    /* renamed from: a */
    public final StatFs mo324a(StatFs statFs, File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (statFs == null) {
            try {
                return new StatFs(file.getAbsolutePath());
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Throwable th) {
                oq.a(th);
                throw null;
            }
        } else {
            statFs.restat(file.getAbsolutePath());
            return statFs;
        }
    }
}
