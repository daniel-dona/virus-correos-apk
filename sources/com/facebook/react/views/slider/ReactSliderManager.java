package com.facebook.react.views.slider;

import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.Map;

/* compiled from: PG */
public class ReactSliderManager extends SimpleViewManager<ReactSlider> {
    public static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER = new C0162a();
    public static final String REACT_CLASS = "RCTSlider";
    public static final int STYLE = 16842875;

    /* renamed from: com.facebook.react.views.slider.ReactSliderManager$a */
    /* compiled from: PG */
    public static class C0162a implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new sD(seekBar.getId(), ((ReactSlider) seekBar).mo1470a(i), z));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new tD(seekBar.getId(), ((ReactSlider) seekBar).mo1470a(seekBar.getProgress())));
        }
    }

    /* renamed from: com.facebook.react.views.slider.ReactSliderManager$b */
    /* compiled from: PG */
    public static class C0163b extends xA implements YogaMeasureFunction {

        /* renamed from: w */
        public int f691w;

        /* renamed from: x */
        public int f692x;

        /* renamed from: y */
        public boolean f693y;

        public /* synthetic */ C0163b(C0162a aVar) {
            this.t.mo1888a((YogaMeasureFunction) this);
        }

        /* JADX WARNING: type inference failed for: r1v4, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.f693y) {
                ? reactSlider = new ReactSlider(j(), (AttributeSet) null, ReactSliderManager.STYLE);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
                reactSlider.measure(makeMeasureSpec, makeMeasureSpec);
                this.f691w = reactSlider.getMeasuredWidth();
                this.f692x = reactSlider.getMeasuredHeight();
                this.f693y = true;
            }
            return fF.a(this.f691w, this.f692x);
        }
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return hy.a("topSlidingComplete", hy.a("registrationName", "onSlidingComplete"));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class getShadowNodeClass() {
        return C0163b.class;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultBoolean = true, name = "enabled")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setEnabled(com.facebook.react.views.slider.ReactSlider r1, boolean r2) {
        /*
            r0 = this;
            r1.setEnabled(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.setEnabled(com.facebook.react.views.slider.ReactSlider, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "maximumTrackTintColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMaximumTrackTintColor(com.facebook.react.views.slider.ReactSlider r2, java.lang.Integer r3) {
        /*
            r1 = this;
            android.graphics.drawable.Drawable r2 = r2.getProgressDrawable()
            android.graphics.drawable.Drawable r2 = r2.getCurrent()
            android.graphics.drawable.LayerDrawable r2 = (android.graphics.drawable.LayerDrawable) r2
            r0 = 16908288(0x1020000, float:2.387723E-38)
            android.graphics.drawable.Drawable r2 = r2.findDrawableByLayerId(r0)
            if (r3 != 0) goto L_0x0016
            r2.clearColorFilter()
            goto L_0x001f
        L_0x0016:
            int r3 = r3.intValue()
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            r2.setColorFilter(r3, r0)
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.setMaximumTrackTintColor(com.facebook.react.views.slider.ReactSlider, java.lang.Integer):void");
    }

    @eC(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider reactSlider, double d) {
        reactSlider.mo1472a(d);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "minimumTrackTintColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMinimumTrackTintColor(com.facebook.react.views.slider.ReactSlider r2, java.lang.Integer r3) {
        /*
            r1 = this;
            android.graphics.drawable.Drawable r2 = r2.getProgressDrawable()
            android.graphics.drawable.Drawable r2 = r2.getCurrent()
            android.graphics.drawable.LayerDrawable r2 = (android.graphics.drawable.LayerDrawable) r2
            r0 = 16908301(0x102000d, float:2.3877265E-38)
            android.graphics.drawable.Drawable r2 = r2.findDrawableByLayerId(r0)
            if (r3 != 0) goto L_0x0017
            r2.clearColorFilter()
            goto L_0x0020
        L_0x0017:
            int r3 = r3.intValue()
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            r2.setColorFilter(r3, r0)
        L_0x0020:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.setMinimumTrackTintColor(com.facebook.react.views.slider.ReactSlider, java.lang.Integer):void");
    }

    @eC(defaultDouble = 0.0d, name = "minimumValue")
    public void setMinimumValue(ReactSlider reactSlider, double d) {
        reactSlider.mo1474b(d);
    }

    @eC(defaultDouble = 0.0d, name = "step")
    public void setStep(ReactSlider reactSlider, double d) {
        reactSlider.mo1476c(d);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "thumbTintColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setThumbTintColor(com.facebook.react.views.slider.ReactSlider r2, java.lang.Integer r3) {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x000a
            android.graphics.drawable.Drawable r2 = r2.getThumb()
            r2.clearColorFilter()
            goto L_0x0017
        L_0x000a:
            android.graphics.drawable.Drawable r2 = r2.getThumb()
            int r3 = r3.intValue()
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            r2.setColorFilter(r3, r0)
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.setThumbTintColor(com.facebook.react.views.slider.ReactSlider, java.lang.Integer):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultDouble = 0.0d, name = "value")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(com.facebook.react.views.slider.ReactSlider r2, double r3) {
        /*
            r1 = this;
            r0 = 0
            r2.setOnSeekBarChangeListener(r0)
            r2.mo1477d(r3)
            android.widget.SeekBar$OnSeekBarChangeListener r3 = ON_CHANGE_LISTENER
            r2.setOnSeekBarChangeListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.setValue(com.facebook.react.views.slider.ReactSlider, double):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.SeekBar, com.facebook.react.views.slider.ReactSlider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addEventEmitters(WA r1, com.facebook.react.views.slider.ReactSlider r2) {
        /*
            r0 = this;
            android.widget.SeekBar$OnSeekBarChangeListener r1 = ON_CHANGE_LISTENER
            r2.setOnSeekBarChangeListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.slider.ReactSliderManager.addEventEmitters(WA, com.facebook.react.views.slider.ReactSlider):void");
    }

    public xA createShadowNodeInstance() {
        return new C0163b((C0162a) null);
    }

    public ReactSlider createViewInstance(WA wa) {
        return new ReactSlider(wa, (AttributeSet) null, STYLE);
    }
}
