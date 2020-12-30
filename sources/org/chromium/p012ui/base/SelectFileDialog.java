package org.chromium.p012ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.bridge.PromiseImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.task.PostTask;
import org.chromium.p012ui.base.WindowAndroid;
import org.chromium.ui.UiUtils;

/* renamed from: org.chromium.ui.base.SelectFileDialog */
/* compiled from: PG */
public class SelectFileDialog implements WindowAndroid.IntentCallback, KH3 {

    /* renamed from: q3 */
    public static final String[] f2608q3 = {".asf", ".avhcd", ".avi", ".divx", ".flv", ".mov", ".mp4", ".mpeg", ".mpg", ".swf", ".wmv", ".webm", ".mkv"};

    /* renamed from: r3 */
    public static SH3 f2609r3;

    /* renamed from: x */
    public static final long f2610x = TimeUnit.HOURS.toMillis(1);

    /* renamed from: y */
    public static final String[] f2611y = {".apng", ".bmp", ".gif", ".jpeg", ".jpg", ".pdf", ".png", ".tif", ".tiff", ".xcf", ".webp"};

    /* renamed from: a */
    public final long f2612a;

    /* renamed from: b */
    public List<String> f2613b;

    /* renamed from: c */
    public boolean f2614c;

    /* renamed from: d */
    public boolean f2615d;

    /* renamed from: e */
    public Uri f2616e;

    /* renamed from: k */
    public WindowAndroid f2617k;

    /* renamed from: n */
    public boolean f2618n;

    /* renamed from: p */
    public boolean f2619p;

    /* renamed from: q */
    public boolean f2620q;

    public SelectFileDialog(long j) {
        this.f2612a = j;
    }

    @CalledByNative
    public static SelectFileDialog create(long j) {
        return new SelectFileDialog(j);
    }

    /* renamed from: h */
    public static void m3666h() {
        PostTask.m1566a(VP0.i, XH3.a, 0);
    }

    /* renamed from: i */
    public static final /* synthetic */ void m3667i() {
        File[] listFiles;
        try {
            File a = UiUtils.a(RN0.a);
            if (a.isDirectory() && (listFiles = a.listFiles()) != null) {
                long currentTimeMillis = System.currentTimeMillis();
                for (File file : listFiles) {
                    if (currentTimeMillis - file.lastModified() > f2610x && !file.delete()) {
                        VN0.a("SelectFileDialog", "Failed to delete: " + file, new Object[0]);
                    }
                }
            }
        } catch (IOException e) {
            VN0.c("SelectFileDialog", "Failed to delete captured camera files.", new Object[]{e});
        }
    }

    private native void nativeOnContactsSelected(long j, String str);

    private native void nativeOnFileNotSelected(long j);

    private native void nativeOnFileSelected(long j, String str, String str2);

    private native void nativeOnMultipleFilesSelected(long j, String[] strArr, String[] strArr2);

    @TargetApi(18)
    @CalledByNative
    private void selectFile(String[] strArr, boolean z, boolean z2, WindowAndroid windowAndroid) {
        this.f2613b = new ArrayList(Arrays.asList(strArr));
        this.f2614c = z;
        this.f2615d = z2;
        this.f2617k = windowAndroid;
        this.f2618n = this.f2617k.mo10003a(new Intent("android.media.action.IMAGE_CAPTURE"));
        this.f2619p = this.f2617k.mo10003a(new Intent("android.media.action.VIDEO_CAPTURE"));
        this.f2620q = this.f2617k.mo10003a(new Intent("android.provider.MediaStore.RECORD_SOUND"));
        ArrayList arrayList = new ArrayList();
        boolean g = mo9986g();
        if (!g) {
            if (((this.f2618n && mo9979a("image/*", "image/")) || (this.f2619p && mo9979a("video/*", "video/"))) && !windowAndroid.hasPermission("android.permission.CAMERA")) {
                arrayList.add("android.permission.CAMERA");
            }
            if (this.f2620q && mo9979a("audio/*", "audio/") && !windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
                arrayList.add("android.permission.RECORD_AUDIO");
            }
        } else if (!windowAndroid.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            mo9983d();
            return;
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        windowAndroid.mo10001a(strArr2, new VH3(this, g, strArr2, "android.permission.READ_EXTERNAL_STORAGE"));
    }

    /* renamed from: c */
    public final boolean mo9982c() {
        return m3660a(this.f2613b) != null;
    }

    /* renamed from: d */
    public final void mo9983d() {
        boolean hasPermission = this.f2617k.hasPermission("android.permission.CAMERA");
        if (!this.f2618n || !hasPermission) {
            mo9974a((Intent) null);
        } else {
            new a(this, false, this.f2617k, this).a(AP0.f);
        }
    }

    /* renamed from: e */
    public final boolean mo9984e() {
        return this.f2613b.size() != 1 || this.f2613b.contains("*/*");
    }

    /* renamed from: f */
    public final void mo9985f() {
        long j = this.f2612a;
        if (mo9982c()) {
            RecordHistogram.m1544b("Android.SelectFileDialogImgCount", 0);
        }
        nativeOnFileNotSelected(j);
    }

    /* renamed from: g */
    public final boolean mo9986g() {
        List<String> a = m3660a(this.f2613b);
        if (!mo9977a() && a != null) {
            if (!(UiUtils.b != null) || this.f2617k.mo10006b().get() == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @TargetApi(18)
    public void onIntentCompleted(WindowAndroid windowAndroid, int i, Intent intent) {
        if (i != -1) {
            mo9985f();
        } else if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
            mo9973a(this.f2612a, PromiseImpl.STACK_FRAME_KEY_FILE.equals(this.f2616e.getScheme()) ? this.f2616e.getPath() : this.f2616e.toString(), this.f2616e.getLastPathSegment());
            windowAndroid.mo10007b(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.f2616e));
        } else {
            if (intent.getData() != null || intent.getClipData() == null) {
                Activity activity = (Activity) windowAndroid.mo10006b().get();
                we0 we0 = f2609r3;
                if (!(we0 == null || activity == null)) {
                    if (!we0.a(activity, intent.getData())) {
                        mo9985f();
                        windowAndroid.mo9991a(wx0.opening_file_error);
                        return;
                    }
                }
                if (PromiseImpl.STACK_FRAME_KEY_FILE.equals(intent.getData().getScheme())) {
                    mo9973a(this.f2612a, intent.getData().getSchemeSpecificPart(), BuildConfig.FLAVOR);
                } else if ("content".equals(intent.getScheme())) {
                    new b(this, RN0.a, false, new Uri[]{intent.getData()}).a(AP0.f);
                } else {
                    mo9985f();
                    windowAndroid.mo9991a(wx0.opening_file_error);
                }
            } else {
                ClipData clipData = intent.getClipData();
                int itemCount = clipData.getItemCount();
                if (itemCount == 0) {
                    mo9985f();
                    return;
                }
                Uri[] uriArr = new Uri[itemCount];
                for (int i2 = 0; i2 < itemCount; i2++) {
                    uriArr[i2] = clipData.getItemAt(i2).getUri();
                }
                new b(this, RN0.a, true, uriArr).a(AP0.f);
            }
        }
    }

    /* renamed from: b */
    public int mo9980b() {
        int i;
        boolean z;
        if (this.f2613b.size() == 0) {
            return 0;
        }
        int b = mo9981b("image/");
        int b2 = mo9981b("video/");
        if (this.f2613b.size() > b + b2) {
            for (String next : this.f2613b) {
                String[] strArr = f2611y;
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        i = b;
                        z = false;
                        break;
                    } else if (next.equalsIgnoreCase(strArr[i2])) {
                        i = b + 1;
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    String[] strArr2 = f2608q3;
                    int length2 = strArr2.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length2) {
                            break;
                        } else if (next.equalsIgnoreCase(strArr2[i3])) {
                            b2++;
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
                b = i;
            }
        }
        if ((this.f2613b.size() - b) - b2 > 0) {
            return 0;
        }
        if (b2 > 0) {
            return b == 0 ? 2 : 3;
        }
        return 1;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9975a(boolean z, String[] strArr, String str, String[] strArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == -1) {
                if (this.f2614c) {
                    mo9985f();
                    return;
                }
                if (z) {
                    if (strArr2.length != strArr.length) {
                        throw new RuntimeException(String.format("Permissions arrays misaligned: %d != %d", new Object[]{Integer.valueOf(strArr2.length), Integer.valueOf(strArr.length)}));
                    } else if (!strArr2[i].equals(strArr[i])) {
                        throw new RuntimeException(String.format("Permissions arrays don't match: %s != %s", new Object[]{strArr2[i], strArr[i]}));
                    }
                }
                if (z && strArr2[i].equals(str)) {
                    mo9985f();
                    return;
                }
            }
        }
        mo9983d();
    }

    /* renamed from: b */
    public final int mo9981b(String str) {
        int i = 0;
        for (String startsWith : this.f2613b) {
            if (startsWith.startsWith(str)) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: a */
    public final void mo9974a(Intent intent) {
        boolean z;
        RecordHistogram.m1539a("Android.SelectFileDialogScope", mo9980b(), 4);
        Intent intent2 = null;
        Intent intent3 = (!this.f2619p || !this.f2617k.hasPermission("android.permission.CAMERA")) ? null : new Intent("android.media.action.VIDEO_CAPTURE");
        boolean hasPermission = this.f2617k.hasPermission("android.permission.RECORD_AUDIO");
        if (this.f2620q && hasPermission) {
            intent2 = new Intent("android.provider.MediaStore.RECORD_SOUND");
        }
        if (!mo9977a() || intent == null) {
            if (!(this.f2614c && mo9978a("video/*")) || intent3 == null) {
                if ((this.f2614c && mo9978a("audio/*")) && intent2 != null && this.f2617k.mo10013b(intent2, (WindowAndroid.IntentCallback) this, Integer.valueOf(wx0.low_memory_error))) {
                    return;
                }
            } else if (this.f2617k.mo10013b(intent3, (WindowAndroid.IntentCallback) this, Integer.valueOf(wx0.low_memory_error))) {
                return;
            }
        } else if (this.f2617k.mo10013b(intent, (WindowAndroid.IntentCallback) this, Integer.valueOf(wx0.low_memory_error))) {
            return;
        }
        Activity activity = (Activity) this.f2617k.mo10006b().get();
        List<String> a = m3660a(this.f2613b);
        if (mo9986g()) {
            boolean z2 = this.f2615d;
            UiUtils.PhotoPickerDelegate photoPickerDelegate = UiUtils.b;
            if (photoPickerDelegate == null) {
                z = false;
            } else {
                photoPickerDelegate.showPhotoPicker(activity, this, z2, a);
                z = true;
            }
            if (z) {
                return;
            }
        }
        Intent intent4 = new Intent("android.intent.action.GET_CONTENT");
        if (this.f2615d) {
            intent4.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        ArrayList arrayList = new ArrayList();
        if (!mo9984e()) {
            if (mo9979a("image/*", "image/")) {
                if (intent != null) {
                    arrayList.add(intent);
                }
                intent4.setType("image/*");
            } else if (mo9979a("video/*", "video/")) {
                if (intent3 != null) {
                    arrayList.add(intent3);
                }
                intent4.setType("video/*");
            } else if (mo9979a("audio/*", "audio/")) {
                if (intent2 != null) {
                    arrayList.add(intent2);
                }
                intent4.setType("audio/*");
            }
            intent4.addCategory("android.intent.category.OPENABLE");
        }
        if (arrayList.isEmpty()) {
            intent4.setType("*/*");
            if (intent != null) {
                arrayList.add(intent);
            }
            if (intent3 != null) {
                arrayList.add(intent3);
            }
            if (intent2 != null) {
                arrayList.add(intent2);
            }
        }
        Intent intent5 = new Intent("android.intent.action.CHOOSER");
        if (!arrayList.isEmpty()) {
            intent5.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Intent[0]));
        }
        intent5.putExtra("android.intent.extra.INTENT", intent4);
        if (!this.f2617k.mo10013b(intent5, (WindowAndroid.IntentCallback) this, Integer.valueOf(wx0.low_memory_error))) {
            mo9985f();
        }
    }

    /* renamed from: a */
    public static List<String> m3660a(List<String> list) {
        if (list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.length() == 0) {
                next = BuildConfig.FLAVOR;
            } else {
                String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(next);
                if (fileExtensionFromUrl.length() > 0 && (next = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl)) == null) {
                    next = "application/octet-stream";
                }
            }
            if (!next.startsWith("image/") && (!UiUtils.b() || !next.startsWith("video/"))) {
                return null;
            }
            if (!arrayList.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo9972a(int i, Uri[] uriArr) {
        if (i != 0) {
            boolean z = false;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        if (this.f2615d) {
                            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                        }
                        intent.setAction("android.intent.action.GET_CONTENT");
                        this.f2617k.mo9989a(intent, (WindowAndroid.IntentCallback) this, Integer.valueOf(wx0.low_memory_error));
                    }
                } else if (!this.f2617k.hasPermission("android.permission.CAMERA")) {
                    this.f2617k.mo10001a(new String[]{"android.permission.CAMERA"}, new WH3(this));
                } else {
                    new a(this, true, this.f2617k, this).a(AP0.f);
                }
            } else if (uriArr.length == 0) {
                mo9985f();
            } else {
                Context context = RN0.a;
                if (uriArr.length > 1) {
                    z = true;
                }
                new b(this, context, z, uriArr).a(AP0.f);
            }
        } else {
            mo9985f();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ void mo9976a(int[] iArr) {
        if (iArr[0] == -1) {
            mo9985f();
        } else {
            new a(this, true, this.f2617k, this).a(AP0.f);
        }
    }

    /* renamed from: a */
    public final File mo9971a(Context context) throws IOException {
        return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", UiUtils.a(context));
    }

    /* renamed from: a */
    public static void m3661a(SH3 sh3) {
        f2609r3 = sh3;
    }

    /* renamed from: a */
    public final boolean mo9979a(String str, String str2) {
        if (mo9984e() || this.f2613b.contains(str) || mo9981b(str2) > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo9978a(String str) {
        return this.f2613b.size() == 1 && TextUtils.equals(this.f2613b.get(0), str);
    }

    /* renamed from: a */
    public final boolean mo9977a() {
        return this.f2614c && mo9978a("image/*");
    }

    /* renamed from: a */
    public final void mo9973a(long j, String str, String str2) {
        if (mo9982c()) {
            RecordHistogram.m1544b("Android.SelectFileDialogImgCount", 1);
        }
        nativeOnFileSelected(j, str, str2);
    }

    /* renamed from: a */
    public static /* synthetic */ void m3663a(SelectFileDialog selectFileDialog, long j, String[] strArr, String[] strArr2) {
        if (selectFileDialog.mo9982c()) {
            RecordHistogram.m1544b("Android.SelectFileDialogImgCount", strArr.length);
        }
        selectFileDialog.nativeOnMultipleFilesSelected(j, strArr, strArr2);
    }
}
