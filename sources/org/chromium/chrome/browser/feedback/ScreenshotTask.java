package org.chromium.chrome.browser.feedback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.p012ui.base.WindowAndroid;

/* compiled from: PG */
public final class ScreenshotTask implements rX1 {

    /* renamed from: a */
    public final Activity f2015a;

    /* renamed from: b */
    public boolean f2016b;

    /* renamed from: c */
    public Runnable f2017c;

    /* renamed from: org.chromium.chrome.browser.feedback.ScreenshotTask$a */
    /* compiled from: PG */
    public class C0421a implements Runnable {
        public C0421a() {
        }

        public void run() {
            ScreenshotTask.m2421a(ScreenshotTask.this, (Bitmap) null);
        }
    }

    public ScreenshotTask(Activity activity) {
        this.f2015a = activity;
    }

    public static native void nativeGrabWindowSnapshotAsync(ScreenshotTask screenshotTask, WindowAndroid windowAndroid, int i, int i2);

    @CalledByNative
    private void onBytesReceived(byte[] bArr) {
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        this.f2016b = true;
        Runnable runnable = this.f2017c;
        if (runnable != null) {
            runnable.run();
        }
        this.f2017c = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo8752a(java.lang.Runnable r6) {
        /*
            r5 = this;
            r5.f2017c = r6
            android.app.Activity r6 = r5.f2015a
            boolean r0 = r6 instanceof org.chromium.chrome.browser.ChromeActivity
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000b
            goto L_0x003b
        L_0x000b:
            r0 = r6
            org.chromium.chrome.browser.ChromeActivity r0 = (org.chromium.chrome.browser.ChromeActivity) r0
            org.chromium.chrome.browser.tab.Tab r3 = r0.mo8172v0()
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r4 = r0.mo8020D0()
            if (r4 == 0) goto L_0x0023
            org.chromium.chrome.browser.widget.bottomsheet.BottomSheet r0 = r0.mo8020D0()
            boolean r0 = r0.x()
            if (r0 == 0) goto L_0x0023
            goto L_0x003b
        L_0x0023:
            if (r3 != 0) goto L_0x0026
            goto L_0x0039
        L_0x0026:
            boolean r0 = r3.isUserInteractable()
            if (r0 != 0) goto L_0x002d
            goto L_0x0039
        L_0x002d:
            lb2 r0 = r3.mo9403v()
            if (r0 != 0) goto L_0x003b
            boolean r0 = Kv2.p(r3)
            if (r0 != 0) goto L_0x003b
        L_0x0039:
            r0 = 1
            goto L_0x003c
        L_0x003b:
            r0 = 0
        L_0x003c:
            if (r0 != 0) goto L_0x0040
            r6 = 0
            goto L_0x0066
        L_0x0040:
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            android.view.Window r3 = r6.getWindow()
            android.view.View r3 = r3.getDecorView()
            android.view.View r3 = r3.getRootView()
            r3.getWindowVisibleDisplayFrame(r0)
            org.chromium.chrome.browser.ChromeActivity r6 = (org.chromium.chrome.browser.ChromeActivity) r6
            QH3 r6 = r6.mo8772S()
            int r3 = r0.width()
            int r0 = r0.height()
            nativeGrabWindowSnapshotAsync(r5, r6, r3, r0)
            r6 = 1
        L_0x0066:
            if (r6 == 0) goto L_0x0069
            return
        L_0x0069:
            android.app.Activity r6 = r5.f2015a
            r3 = 0
            if (r6 != 0) goto L_0x0070
            goto L_0x007b
        L_0x0070:
            VP0 r0 = iQ2.a
            sX1 r1 = new sX1
            r1.<init>(r5, r6)
            org.chromium.base.task.PostTask.m1566a(r0, r1, r3)
            r1 = 1
        L_0x007b:
            if (r1 == 0) goto L_0x007e
            return
        L_0x007e:
            VP0 r6 = iQ2.a
            org.chromium.chrome.browser.feedback.ScreenshotTask$a r0 = new org.chromium.chrome.browser.feedback.ScreenshotTask$a
            r0.<init>()
            org.chromium.base.task.PostTask.m1566a(r6, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feedback.ScreenshotTask.mo8752a(java.lang.Runnable):void");
    }

    /* renamed from: a */
    public boolean mo8753a() {
        return this.f2016b;
    }

    /* renamed from: a */
    public static /* synthetic */ void m2421a(ScreenshotTask screenshotTask, Bitmap bitmap) {
        screenshotTask.f2016b = true;
        Runnable runnable = screenshotTask.f2017c;
        if (runnable != null) {
            runnable.run();
        }
        screenshotTask.f2017c = null;
    }
}
