package org.chromium.components.minidump_uploader;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.PersistableBundle;
import org.chromium.components.minidump_uploader.MinidumpUploader;

@TargetApi(21)
/* compiled from: PG */
public abstract class MinidumpUploadJobService extends JobService {

    /* renamed from: a */
    public MinidumpUploader f2360a;

    /* renamed from: b */
    public final Object f2361b = new Object();

    /* renamed from: c */
    public boolean f2362c;

    static {
        Class<MinidumpUploadJobService> cls = MinidumpUploadJobService.class;
    }

    /* renamed from: a */
    public final MinidumpUploader.UploadsFinishedCallback mo9599a(JobParameters jobParameters) {
        return new a(this, jobParameters);
    }

    /* renamed from: a */
    public abstract MinidumpUploader mo8340a(PersistableBundle persistableBundle);

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
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

    public void onDestroy() {
        this.f2360a = null;
        super.onDestroy();
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 114 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStartJob(android.app.job.JobParameters r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f2361b
            monitor-enter(r0)
            r1 = 1
            r2.f2362c = r1     // Catch:{ all -> 0x001f }
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            android.os.PersistableBundle r0 = r3.getExtras()
            org.chromium.components.minidump_uploader.MinidumpUploader r0 = r2.mo8340a((android.os.PersistableBundle) r0)
            r2.f2360a = r0
            org.chromium.components.minidump_uploader.MinidumpUploader r0 = r2.f2360a
            org.chromium.components.minidump_uploader.MinidumpUploader$UploadsFinishedCallback r3 = r2.mo9599a((android.app.job.JobParameters) r3)
            dL2 r0 = (dL2) r0
            r0.a(r3)
            return r1
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r3
        L_0x001f:
            r3 = move-exception
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.minidump_uploader.MinidumpUploadJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public boolean onStopJob(JobParameters jobParameters) {
        VN0.b("MinidumpJobService", "Canceling pending uploads due to change in networking status.", new Object[0]);
        this.f2360a.a();
        synchronized (this.f2361b) {
            this.f2362c = false;
        }
        return true;
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
