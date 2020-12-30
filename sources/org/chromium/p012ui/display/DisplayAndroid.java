package org.chromium.p012ui.display;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;

/* renamed from: org.chromium.ui.display.DisplayAndroid */
/* compiled from: PG */
public abstract class DisplayAndroid {

    /* renamed from: m */
    public static final DisplayAndroidObserver[] f2647m = new DisplayAndroidObserver[0];

    /* renamed from: a */
    public final WeakHashMap<DisplayAndroidObserver, Object> f2648a = new WeakHashMap<>();

    /* renamed from: b */
    public final int f2649b;

    /* renamed from: c */
    public Point f2650c = new Point();

    /* renamed from: d */
    public float f2651d;

    /* renamed from: e */
    public int f2652e;

    /* renamed from: f */
    public int f2653f;

    /* renamed from: g */
    public int f2654g;

    /* renamed from: h */
    public float f2655h;

    /* renamed from: i */
    public Display.Mode f2656i;

    /* renamed from: j */
    public List<Display.Mode> f2657j;

    /* renamed from: k */
    public boolean f2658k;

    /* renamed from: l */
    public boolean f2659l;

    /* renamed from: org.chromium.ui.display.DisplayAndroid$DisplayAndroidObserver */
    /* compiled from: PG */
    public interface DisplayAndroidObserver {
        void onCurrentModeChanged(Display.Mode mode);

        void onDIPScaleChanged(float f);

        void onDisplayModesChanged(List<Display.Mode> list);

        void onRefreshRateChanged(float f);

        void onRotationChanged(int i);
    }

    public DisplayAndroid(int i) {
        this.f2649b = i;
    }

    /* renamed from: a */
    public static DisplayAndroid m3727a(Context context) {
        return m3728c().mo10046a(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
    }

    /* renamed from: c */
    public static DisplayAndroidManager m3728c() {
        ThreadUtils.m1462c();
        if (DisplayAndroidManager.f2660e == null) {
            DisplayAndroidManager.f2660e = new DisplayAndroidManager();
            DisplayAndroidManager.f2660e.mo10047a();
        }
        return DisplayAndroidManager.f2660e;
    }

    /* renamed from: a */
    public float mo10042a() {
        return 1.0f;
    }

    /* renamed from: b */
    public final DisplayAndroidObserver[] mo10045b() {
        return (DisplayAndroidObserver[]) this.f2648a.keySet().toArray(f2647m);
    }

    /* renamed from: a */
    public void mo10044a(DisplayAndroidObserver displayAndroidObserver) {
        this.f2648a.put(displayAndroidObserver, (Object) null);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public void mo10043a(Point point, Float f, Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, Float f2, Display.Mode mode, List<Display.Mode> list) {
        List<Display.Mode> list2;
        Point point2 = point;
        Display.Mode mode2 = mode;
        List<Display.Mode> list3 = list;
        boolean z = true;
        boolean z2 = point2 != null && !this.f2650c.equals(point2);
        boolean z3 = (f == null || this.f2651d == f.floatValue()) ? false : true;
        boolean z4 = (num == null || this.f2652e == num.intValue()) ? false : true;
        boolean z5 = (num2 == null || this.f2653f == num2.intValue()) ? false : true;
        boolean z6 = (num3 == null || this.f2654g == num3.intValue()) ? false : true;
        boolean z7 = (bool == null || this.f2658k == bool.booleanValue()) ? false : true;
        boolean z8 = (bool2 == null || this.f2659l == bool2.booleanValue()) ? false : true;
        boolean z9 = (f2 == null || this.f2655h == f2.floatValue()) ? false : true;
        boolean z10 = list3 != null && ((list2 = this.f2657j) == null || list2.equals(list3));
        boolean z11 = mode2 != null && !mode2.equals(this.f2656i);
        if (!z2 && !z3 && !z4 && !z5 && !z6 && !z7 && !z8 && !z9 && !z10 && !z11) {
            z = false;
        }
        if (z) {
            if (z2) {
                this.f2650c = point2;
            }
            if (z3) {
                this.f2651d = f.floatValue();
            }
            if (z4) {
                this.f2652e = num.intValue();
            }
            if (z5) {
                this.f2653f = num2.intValue();
            }
            if (z6) {
                this.f2654g = num3.intValue();
            }
            if (z7) {
                this.f2658k = bool.booleanValue();
            }
            if (z8) {
                this.f2659l = bool2.booleanValue();
            }
            if (z9) {
                this.f2655h = f2.floatValue();
            }
            if (z10) {
                this.f2657j = list3;
            }
            if (z11) {
                this.f2656i = mode2;
            }
            m3728c().mo10048a(this);
            if (z6) {
                for (DisplayAndroidObserver onRotationChanged : mo10045b()) {
                    onRotationChanged.onRotationChanged(this.f2654g);
                }
            }
            if (z3) {
                for (DisplayAndroidObserver onDIPScaleChanged : mo10045b()) {
                    onDIPScaleChanged.onDIPScaleChanged(this.f2651d);
                }
            }
            if (z9) {
                for (DisplayAndroidObserver onRefreshRateChanged : mo10045b()) {
                    onRefreshRateChanged.onRefreshRateChanged(this.f2655h);
                }
            }
            if (z10) {
                for (DisplayAndroidObserver onDisplayModesChanged : mo10045b()) {
                    onDisplayModesChanged.onDisplayModesChanged(this.f2657j);
                }
            }
            if (z11) {
                for (DisplayAndroidObserver onCurrentModeChanged : mo10045b()) {
                    onCurrentModeChanged.onCurrentModeChanged(this.f2656i);
                }
            }
        }
    }
}
