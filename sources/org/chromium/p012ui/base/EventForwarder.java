package org.chromium.p012ui.base;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.uimanager.BaseViewManager;
import com.microsoft.intune.mam.client.view.DragEventManagementBehavior;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex
/* renamed from: org.chromium.ui.base.EventForwarder */
/* compiled from: PG */
public class EventForwarder {

    /* renamed from: a */
    public final boolean f2600a;

    /* renamed from: b */
    public long f2601b;

    /* renamed from: c */
    public float f2602c;

    /* renamed from: d */
    public float f2603d;

    /* renamed from: e */
    public int f2604e;

    public EventForwarder(long j, boolean z) {
        this.f2601b = j;
        this.f2600a = z;
    }

    @CalledByNative
    public static EventForwarder create(long j, boolean z) {
        return new EventForwarder(j, z);
    }

    @CalledByNative
    private void destroy() {
        this.f2601b = 0;
    }

    private native void nativeCancelFling(long j, long j2, boolean z);

    private native boolean nativeDispatchKeyEvent(long j, KeyEvent keyEvent);

    private native void nativeDoubleTap(long j, long j2, int i, int i2);

    private native WindowAndroid nativeGetJavaWindowAndroid(long j);

    private native void nativeOnDragEvent(long j, int i, int i2, int i3, int i4, int i5, String[] strArr, String str);

    private native boolean nativeOnGenericMotionEvent(long j, MotionEvent motionEvent, long j2);

    private native boolean nativeOnGestureEvent(long j, int i, long j2, float f);

    private native boolean nativeOnKeyUp(long j, KeyEvent keyEvent, int i);

    private native void nativeOnMouseEvent(long j, long j2, int i, float f, float f2, int i2, float f3, float f4, float f5, int i3, int i4, int i5, int i6);

    private native boolean nativeOnTouchEvent(long j, MotionEvent motionEvent, long j2, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, int i5, int i6, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int i7, int i8, int i9, int i10, boolean z);

    private native void nativeScrollBy(long j, float f, float f2);

    private native void nativeScrollTo(long j, float f, float f2);

    private native void nativeStartFling(long j, long j2, float f, float f2, boolean z, boolean z2);

    /* renamed from: a */
    public final float mo9955a() {
        nativeGetJavaWindowAndroid(this.f2601b).mo10018e().mo10042a();
        return 1.0f;
    }

    /* renamed from: b */
    public final boolean mo9964b() {
        return (this.f2602c == BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER && this.f2603d == BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) ? false : true;
    }

    /* renamed from: c */
    public void mo9966c(float f, float f2) {
        this.f2602c = f;
        this.f2603d = f2;
    }

    /* renamed from: d */
    public boolean mo9968d(MotionEvent motionEvent) {
        TraceEvent.m1472c("sendMouseEvent", (String) null);
        boolean z = false;
        try {
            if (mo9964b()) {
                motionEvent = mo9956a(motionEvent);
                z = true;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 11 || actionMasked == 12) {
                this.f2604e = motionEvent.getButtonState();
            }
            return mo9970f(motionEvent);
        } finally {
            if (z) {
                motionEvent.recycle();
            }
            TraceEvent.m1475z("sendMouseEvent");
        }
    }

    /* renamed from: e */
    public boolean mo9969e(MotionEvent motionEvent) {
        String str;
        long j;
        boolean z;
        MotionEvent motionEvent2 = motionEvent;
        boolean z2 = false;
        if (motionEvent2.getToolType(0) == 3) {
            int i = Build.VERSION.SDK_INT;
            boolean z3 = motionEvent.getButtonState() == 0 && (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 2 || motionEvent.getActionMasked() == 1);
            if (i >= 23 && !z3) {
                return mo9968d(motionEvent);
            }
        }
        TraceEvent.m1472c("sendTouchEvent", (String) null);
        try {
            if (motionEvent.getHistorySize() > 0) {
                j = motionEvent2.getHistoricalEventTime(0);
            } else {
                j = motionEvent.getEventTime();
            }
            int a = UH3.a(motionEvent.getActionMasked());
            if (!(a == 0 || a == 1 || a == 3 || a == 2 || a == 5 || a == 6)) {
                TraceEvent.m1475z("sendTouchEvent");
            } else {
                if (mo9964b()) {
                    motionEvent2 = mo9956a(motionEvent);
                    z = true;
                } else {
                    z = false;
                }
                int pointerCount = motionEvent2.getPointerCount();
                float[] fArr = new float[2];
                fArr[0] = motionEvent2.getTouchMajor();
                fArr[1] = pointerCount > 1 ? motionEvent2.getTouchMajor(1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                float[] fArr2 = new float[2];
                fArr2[0] = motionEvent2.getTouchMinor();
                fArr2[1] = pointerCount > 1 ? motionEvent2.getTouchMinor(1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                for (int i2 = 0; i2 < 2; i2++) {
                    if (fArr[i2] < fArr2[i2]) {
                        float f = fArr[i2];
                        fArr[i2] = fArr2[i2];
                        fArr2[i2] = f;
                    }
                }
                float x = pointerCount > 1 ? motionEvent2.getX(1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                float y = pointerCount > 1 ? motionEvent2.getY(1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
                mo9955a();
                str = "sendTouchEvent";
                try {
                    z2 = nativeOnTouchEvent(this.f2601b, motionEvent2, j, a, pointerCount, motionEvent2.getHistorySize(), motionEvent2.getActionIndex(), motionEvent2.getX() / 1.0f, motionEvent2.getY() / 1.0f, x / 1.0f, y / 1.0f, motionEvent2.getPointerId(0), pointerCount > 1 ? motionEvent2.getPointerId(1) : -1, fArr[0] / 1.0f, fArr[1] / 1.0f, fArr2[0] / 1.0f, fArr2[1] / 1.0f, motionEvent2.getOrientation(), pointerCount > 1 ? motionEvent2.getOrientation(1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, motionEvent2.getAxisValue(25), pointerCount > 1 ? motionEvent2.getAxisValue(25, 1) : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, motionEvent2.getRawX() / 1.0f, motionEvent2.getRawY() / 1.0f, motionEvent2.getToolType(0), pointerCount > 1 ? motionEvent2.getToolType(1) : 0, motionEvent2.getButtonState(), motionEvent2.getMetaState(), false);
                    if (z) {
                        motionEvent2.recycle();
                    }
                    TraceEvent.m1475z(str);
                } catch (Throwable th) {
                    th = th;
                    TraceEvent.m1475z(str);
                    throw th;
                }
            }
            return z2;
        } catch (Throwable th2) {
            th = th2;
            str = "sendTouchEvent";
            TraceEvent.m1475z(str);
            throw th;
        }
    }

    /* renamed from: f */
    public final boolean mo9970f(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9 || actionMasked == 10) {
            return false;
        }
        if (actionMasked == 0 || actionMasked == 1) {
            return true;
        }
        mo9955a();
        nativeOnMouseEvent(this.f2601b, motionEvent.getEventTime(), actionMasked, motionEvent.getX() / 1.0f, motionEvent.getY() / 1.0f, motionEvent2.getPointerId(0), motionEvent2.getPressure(0), motionEvent2.getOrientation(0), motionEvent2.getAxisValue(25, 0), Build.VERSION.SDK_INT >= 23 ? motionEvent.getActionButton() : 0, motionEvent.getButtonState(), motionEvent.getMetaState(), motionEvent2.getToolType(0));
        return true;
    }

    /* renamed from: a */
    public MotionEvent mo9956a(MotionEvent motionEvent) {
        if (!mo9964b()) {
            return motionEvent;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(this.f2602c, this.f2603d);
        return obtain;
    }

    /* renamed from: b */
    public boolean mo9965b(MotionEvent motionEvent) {
        int actionMasked;
        if (this.f2601b == 0) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            this.f2604e = motionEvent.getButtonState();
        }
        return nativeOnGenericMotionEvent(this.f2601b, motionEvent, motionEvent.getEventTime());
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c1  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo9967c(android.view.MotionEvent r24) {
        /*
            r23 = this;
            r15 = r23
            r0 = 0
            java.lang.String r14 = "onHoverEvent"
            org.chromium.base.TraceEvent.m1472c(r14, r0)
            r13 = 0
            boolean r0 = r23.mo9964b()     // Catch:{ all -> 0x00b6 }
            r1 = 1
            if (r0 == 0) goto L_0x0020
            android.view.MotionEvent r0 = r23.mo9956a((android.view.MotionEvent) r24)     // Catch:{ all -> 0x0018 }
            r12 = r0
            r17 = 1
            goto L_0x0024
        L_0x0018:
            r0 = move-exception
            r21 = r24
            r22 = r14
            r1 = r15
            goto L_0x00bd
        L_0x0020:
            r12 = r24
            r17 = 0
        L_0x0024:
            int r0 = r12.getActionMasked()     // Catch:{ all -> 0x00ae }
            r2 = 9
            if (r0 != r2) goto L_0x009b
            int r0 = r15.f2604e     // Catch:{ all -> 0x0094 }
            if (r0 != r1) goto L_0x0086
            r23.mo9955a()     // Catch:{ all -> 0x007e }
            r0 = 1065353216(0x3f800000, float:1.0)
            long r2 = r15.f2601b     // Catch:{ all -> 0x007e }
            long r4 = r12.getEventTime()     // Catch:{ all -> 0x007e }
            r6 = 12
            float r1 = r12.getX()     // Catch:{ all -> 0x007e }
            float r7 = r1 / r0
            float r1 = r12.getY()     // Catch:{ all -> 0x007e }
            float r8 = r1 / r0
            int r9 = r12.getPointerId(r13)     // Catch:{ all -> 0x007e }
            float r10 = r12.getPressure(r13)     // Catch:{ all -> 0x007e }
            float r11 = r12.getOrientation(r13)     // Catch:{ all -> 0x007e }
            r0 = 25
            float r0 = r12.getAxisValue(r0, r13)     // Catch:{ all -> 0x007e }
            r16 = 1
            int r18 = r12.getButtonState()     // Catch:{ all -> 0x007e }
            int r19 = r12.getMetaState()     // Catch:{ all -> 0x007e }
            int r20 = r12.getToolType(r13)     // Catch:{ all -> 0x007e }
            r1 = r23
            r21 = r12
            r12 = r0
            r13 = r16
            r22 = r14
            r14 = r18
            r15 = r19
            r16 = r20
            r1.nativeOnMouseEvent(r2, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x007c }
            goto L_0x008a
        L_0x007c:
            r0 = move-exception
            goto L_0x0083
        L_0x007e:
            r0 = move-exception
            r21 = r12
            r22 = r14
        L_0x0083:
            r1 = r23
            goto L_0x00bf
        L_0x0086:
            r21 = r12
            r22 = r14
        L_0x008a:
            r2 = 0
            r1 = r23
            r1.f2604e = r2     // Catch:{ all -> 0x0092 }
            r2 = r21
            goto L_0x009f
        L_0x0092:
            r0 = move-exception
            goto L_0x00bf
        L_0x0094:
            r0 = move-exception
            r21 = r12
            r22 = r14
            r1 = r15
            goto L_0x00bf
        L_0x009b:
            r22 = r14
            r1 = r15
            r2 = r12
        L_0x009f:
            boolean r0 = r1.mo9970f(r2)     // Catch:{ all -> 0x00ac }
            if (r17 == 0) goto L_0x00a8
            r2.recycle()
        L_0x00a8:
            org.chromium.base.TraceEvent.m1475z(r22)
            return r0
        L_0x00ac:
            r0 = move-exception
            goto L_0x00b3
        L_0x00ae:
            r0 = move-exception
            r2 = r12
            r22 = r14
            r1 = r15
        L_0x00b3:
            r21 = r2
            goto L_0x00bf
        L_0x00b6:
            r0 = move-exception
            r22 = r14
            r1 = r15
            r2 = 0
            r21 = r24
        L_0x00bd:
            r17 = 0
        L_0x00bf:
            if (r17 == 0) goto L_0x00c4
            r21.recycle()
        L_0x00c4:
            org.chromium.base.TraceEvent.m1475z(r22)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.p012ui.base.EventForwarder.mo9967c(android.view.MotionEvent):boolean");
    }

    @TargetApi(24)
    /* renamed from: a */
    public boolean mo9961a(DragEvent dragEvent, View view) {
        String[] strArr;
        if (this.f2601b == 0 || Build.VERSION.SDK_INT <= 23) {
            return false;
        }
        ClipDescription clipDescription = dragEvent.getClipDescription();
        if (clipDescription == null) {
            strArr = new String[0];
        } else {
            strArr = clipDescription.filterMimeTypes("text/*");
        }
        String[] strArr2 = strArr;
        if (dragEvent.getAction() != 1) {
            StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
            if (dragEvent.getAction() == 3) {
                ClipData clipData = ((DragEventManagementBehavior) vd0.a.a()).getClipData(dragEvent);
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    sb.append(clipData.getItemAt(i).coerceToStyledText(view.getContext()));
                }
            }
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int x = (int) (dragEvent.getX() + this.f2602c);
            int y = (int) (dragEvent.getY() + this.f2603d);
            int i2 = iArr[0] + x;
            int i3 = iArr[1] + y;
            mo9955a();
            long j = this.f2601b;
            nativeOnDragEvent(j, dragEvent.getAction(), (int) (((float) x) / 1.0f), (int) (((float) y) / 1.0f), (int) (((float) i2) / 1.0f), (int) (((float) i3) / 1.0f), strArr2, sb.toString());
            return true;
        } else if (strArr2 == null || strArr2.length <= 0 || !this.f2600a) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: b */
    public void mo9963b(float f, float f2) {
        long j = this.f2601b;
        if (j != 0) {
            nativeScrollTo(j, f, f2);
        }
    }

    /* renamed from: a */
    public boolean mo9959a(int i, long j, float f) {
        long j2 = this.f2601b;
        if (j2 == 0) {
            return false;
        }
        return nativeOnGestureEvent(j2, i, j, f);
    }

    /* renamed from: a */
    public boolean mo9960a(int i, KeyEvent keyEvent) {
        long j = this.f2601b;
        if (j == 0) {
            return false;
        }
        return nativeOnKeyUp(j, keyEvent, i);
    }

    /* renamed from: a */
    public boolean mo9962a(KeyEvent keyEvent) {
        long j = this.f2601b;
        if (j == 0) {
            return false;
        }
        return nativeDispatchKeyEvent(j, keyEvent);
    }

    /* renamed from: a */
    public void mo9957a(float f, float f2) {
        long j = this.f2601b;
        if (j != 0) {
            nativeScrollBy(j, f, f2);
        }
    }

    /* renamed from: a */
    public void mo9958a(long j, float f, float f2, boolean z, boolean z2) {
        long j2 = this.f2601b;
        if (j2 != 0) {
            nativeStartFling(j2, j, f, f2, z, z2);
        }
    }
}
