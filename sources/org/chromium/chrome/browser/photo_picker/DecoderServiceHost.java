package org.chromium.chrome.browser.photo_picker;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemClock;
import com.citrix.loggersdk.BuildConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;

/* compiled from: PG */
public class DecoderServiceHost extends bl2 implements DecodeVideoTask$VideoDecodingCallback {

    /* renamed from: a */
    public ContentResolver f2145a;

    /* renamed from: b */
    public int f2146b;

    /* renamed from: c */
    public int f2147c;

    /* renamed from: d */
    public int f2148d;

    /* renamed from: e */
    public int f2149e;

    /* renamed from: f */
    public int f2150f;

    /* renamed from: g */
    public int f2151g;

    /* renamed from: h */
    public int f2152h;

    /* renamed from: i */
    public int f2153i;

    /* renamed from: j */
    public DecodeVideoTask f2154j;

    /* renamed from: k */
    public Zk2 f2155k;

    /* renamed from: l */
    public ServiceConnection f2156l = new Wk2(this);

    /* renamed from: m */
    public LinkedHashMap<String, Xk2> f2157m = new LinkedHashMap<>();

    /* renamed from: n */
    public LinkedHashMap<String, Xk2> f2158n = new LinkedHashMap<>();

    /* renamed from: o */
    public LinkedHashMap<String, Xk2> f2159o = new LinkedHashMap<>();

    /* renamed from: p */
    public List<ServiceReadyCallback> f2160p = new ArrayList();

    /* renamed from: q */
    public boolean f2161q;

    /* renamed from: r */
    public final Context f2162r;

    static {
        Class<DecoderServiceHost> cls = DecoderServiceHost.class;
    }

    public DecoderServiceHost(ServiceReadyCallback serviceReadyCallback, Context context) {
        this.f2160p.add(serviceReadyCallback);
        this.f2162r = context;
        this.f2145a = this.f2162r.getContentResolver();
    }

    /* renamed from: a */
    public void mo8997a(Uri uri, int i, int i2, ImagesDecodedCallback imagesDecodedCallback) {
        this.f2157m.put(uri.getPath(), new Xk2(uri, i2, i, imagesDecodedCallback));
        if (this.f2157m.size() == 1) {
            mo9002y();
        }
    }

    /* renamed from: e */
    public void mo8999e(Bundle bundle) {
        PostTask.m1565a(iQ2.a, (Runnable) new Vk2(this, bundle));
    }

    /* renamed from: h */
    public final /* synthetic */ void mo9000h(Bundle bundle) {
        long j;
        ArrayList arrayList;
        String str;
        String str2;
        boolean z;
        DecoderServiceHost decoderServiceHost;
        long j2;
        String str3;
        Bitmap bitmap;
        long j3;
        try {
            String string = bundle.getString("file_path");
            try {
                bitmap = Boolean.valueOf(bundle.getBoolean("success")).booleanValue() ? (Bitmap) bundle.getParcelable("image_bitmap") : null;
                j3 = bundle.getLong("decode_time");
            } catch (RuntimeException unused) {
                arrayList = null;
                str3 = string;
                j2 = -1;
                this.f2147c++;
                z = false;
                str2 = null;
                decoderServiceHost = this;
                decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
            } catch (OutOfMemoryError unused2) {
                arrayList = null;
                str = string;
                j = -1;
                this.f2148d++;
                z = false;
                str2 = null;
                decoderServiceHost = this;
                decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
            } catch (Throwable th) {
                th = th;
                arrayList = null;
                str = string;
                j = -1;
                mo8998a(str, false, arrayList, (String) null, j);
                throw th;
            }
            try {
                this.f2146b++;
                arrayList = new ArrayList(1);
                try {
                    arrayList.add(bitmap);
                    z = false;
                    str2 = null;
                    decoderServiceHost = this;
                    str = string;
                    j = j3;
                } catch (RuntimeException unused3) {
                    str3 = string;
                    j2 = j3;
                    this.f2147c++;
                    z = false;
                    str2 = null;
                    decoderServiceHost = this;
                    decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
                } catch (OutOfMemoryError unused4) {
                    str = string;
                    j = j3;
                    this.f2148d++;
                    z = false;
                    str2 = null;
                    decoderServiceHost = this;
                    decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
                } catch (Throwable th2) {
                    th = th2;
                    str = string;
                    j = j3;
                    mo8998a(str, false, arrayList, (String) null, j);
                    throw th;
                }
            } catch (RuntimeException unused5) {
                arrayList = null;
                str3 = string;
                j2 = j3;
                this.f2147c++;
                z = false;
                str2 = null;
                decoderServiceHost = this;
                decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
            } catch (OutOfMemoryError unused6) {
                arrayList = null;
                str = string;
                j = j3;
                this.f2148d++;
                z = false;
                str2 = null;
                decoderServiceHost = this;
                decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
            } catch (Throwable th3) {
                th = th3;
                arrayList = null;
                str = string;
                j = j3;
                mo8998a(str, false, arrayList, (String) null, j);
                throw th;
            }
        } catch (RuntimeException unused7) {
            j2 = -1;
            arrayList = null;
            str3 = BuildConfig.FLAVOR;
            this.f2147c++;
            z = false;
            str2 = null;
            decoderServiceHost = this;
            decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
        } catch (OutOfMemoryError unused8) {
            j = -1;
            arrayList = null;
            str = BuildConfig.FLAVOR;
            this.f2148d++;
            z = false;
            str2 = null;
            decoderServiceHost = this;
            decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
        } catch (Throwable th4) {
            th = th4;
            mo8998a(str, false, arrayList, (String) null, j);
            throw th;
        }
        decoderServiceHost.mo8998a(str, z, arrayList, str2, j);
    }

    public void videoDecodedCallback(Uri uri, List<Bitmap> list, String str, int i) {
        if (i != 0) {
            if (i == 1) {
                this.f2150f++;
            } else if (i == 2) {
                this.f2151g++;
            } else if (i == 3) {
                this.f2152h++;
            }
        } else if (list == null || list.size() == 0) {
            this.f2153i++;
        } else {
            this.f2149e++;
        }
        mo8998a(uri.getPath(), true, list, str, -1);
    }

    /* renamed from: x */
    public void mo9001x() {
        Intent intent = new Intent(this.f2162r, DecoderService.class);
        intent.setAction(Zk2.class.getName());
        this.f2162r.bindService(intent, this.f2156l, 1);
    }

    /* renamed from: y */
    public final void mo9002y() {
        Xk2 xk2;
        boolean hasNext = this.f2157m.entrySet().iterator().hasNext();
        if (!hasNext) {
            Iterator<Xk2> it = this.f2158n.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    xk2 = null;
                    break;
                }
                Xk2 next = it.next();
                String path = next.a.getPath();
                if (this.f2159o.get(path) == null) {
                    this.f2158n.remove(path);
                    xk2 = next;
                    break;
                }
            }
        } else {
            xk2 = (Xk2) this.f2157m.entrySet().iterator().next().getValue();
            this.f2157m.remove(xk2.a.getPath());
            int i = xk2.c;
            if (i == 3) {
                this.f2158n.put(xk2.a.getPath(), new Xk2(xk2.a, xk2.b, i, xk2.d));
            }
        }
        if (xk2 != null) {
            this.f2159o.put(xk2.a.getPath(), xk2);
            xk2.e = SystemClock.elapsedRealtime();
            if (xk2.c != 3) {
                Bundle bundle = new Bundle();
                StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    ParcelFileDescriptor parcelFileDescriptor = Tc0.a().openAssetFileDescriptor(this.f2145a, xk2.a, "r").getParcelFileDescriptor();
                    if (parcelFileDescriptor == null) {
                        mo8998a(xk2.a.getPath(), false, (List<Bitmap>) null, (String) null, -1);
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        return;
                    }
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    bundle.putString("file_path", xk2.a.getPath());
                    bundle.putParcelable("file_descriptor", parcelFileDescriptor);
                    bundle.putInt("size", xk2.b);
                    try {
                        this.f2155k.a(bundle, this);
                        parcelFileDescriptor.close();
                    } catch (RemoteException e) {
                        VN0.a("ImageDecoderHost", "Communications failed (Remote): " + e, new Object[0]);
                        mo8998a(xk2.a.getPath(), false, (List<Bitmap>) null, (String) null, -1);
                    } catch (IOException e2) {
                        VN0.a("ImageDecoderHost", Eo.a("Communications failed (IO): ", e2), new Object[0]);
                        mo8998a(xk2.a.getPath(), false, (List<Bitmap>) null, (String) null, -1);
                    }
                } catch (FileNotFoundException e3) {
                    VN0.a("ImageDecoderHost", "Unable to obtain FileDescriptor: " + e3, new Object[0]);
                    mo8998a(xk2.a.getPath(), false, (List<Bitmap>) null, (String) null, -1);
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    throw th;
                }
            } else {
                this.f2154j = new DecodeVideoTask(this, this.f2145a, xk2.a, xk2.b, hasNext ? 1 : 10, (long) 2000);
                this.f2154j.a(AP0.f);
            }
        } else if (!this.f2159o.entrySet().iterator().hasNext()) {
            int i2 = this.f2146b;
            int i3 = this.f2147c;
            int i4 = i2 + i3 + this.f2148d;
            if (i4 > 0) {
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostFailureRuntime", (i3 * 100) / i4);
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostFailureOutOfMemory", (this.f2148d * 100) / i4);
                this.f2146b = 0;
                this.f2147c = 0;
                this.f2148d = 0;
            }
            int i5 = this.f2149e;
            int i6 = this.f2150f;
            int i7 = i5 + i6 + this.f2151g + this.f2152h + this.f2153i;
            if (i7 > 0) {
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostVideoFileError", (i6 * 100) / i7);
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostVideoRuntimeError", (this.f2151g * 100) / i7);
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostVideoIoError", (this.f2152h * 100) / i7);
                RecordHistogram.m1551e("Android.PhotoPicker.DecoderHostVideoUnknownError", (this.f2153i * 100) / i7);
                this.f2149e = 0;
                this.f2150f = 0;
                this.f2151g = 0;
                this.f2152h = 0;
                this.f2153i = 0;
            }
        }
    }

    /* renamed from: a */
    public void mo8998a(String str, boolean z, List<Bitmap> list, String str2, long j) {
        Xk2 xk2 = this.f2159o.get(str);
        if (xk2 != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!z) {
                RecordHistogram.m1550d("Android.PhotoPicker.RequestProcessTime", elapsedRealtime - xk2.e);
            } else if (list.size() > 1) {
                RecordHistogram.m1550d("Android.PhotoPicker.RequestProcessTimeAnimation", elapsedRealtime - xk2.e);
            } else {
                RecordHistogram.m1550d("Android.PhotoPicker.RequestProcessTimeThumbnail", elapsedRealtime - xk2.e);
            }
            xk2.d.imagesDecodedCallback(str, z, list, str2);
            if (!(j == -1 || list == null || list.get(0) == null)) {
                int byteCount = list.get(0).getByteCount() / 1024;
                if (!z) {
                    RecordHistogram.m1550d("Android.PhotoPicker.ImageDecodeTime", j);
                    RecordHistogram.m1540a("Android.PhotoPicker.ImageByteCount", byteCount, 1, 100000, 50);
                } else if (list.size() > 1) {
                    RecordHistogram.m1550d("Android.PhotoPicker.VideoDecodeTimeAnimation", j);
                } else {
                    RecordHistogram.m1550d("Android.PhotoPicker.VideoDecodeTimeThumbnail", j);
                    RecordHistogram.m1540a("Android.PhotoPicker.VideoByteCount", byteCount, 1, 100000, 50);
                }
            }
            this.f2159o.remove(str);
        }
        mo9002y();
    }
}
