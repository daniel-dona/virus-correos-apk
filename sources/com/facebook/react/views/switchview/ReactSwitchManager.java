package com.facebook.react.views.switchview;

import android.view.View;
import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

/* compiled from: PG */
public class ReactSwitchManager extends SimpleViewManager<vD> {
    public static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new C0165a();
    public static final String REACT_CLASS = "AndroidSwitch";

    /* renamed from: com.facebook.react.views.switchview.ReactSwitchManager$a */
    /* compiled from: PG */
    public static class C0165a implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ((UIManagerModule) ((ReactContext) compoundButton.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new wD(compoundButton.getId(), z));
        }
    }

    /* renamed from: com.facebook.react.views.switchview.ReactSwitchManager$b */
    /* compiled from: PG */
    public static class C0166b extends xA implements YogaMeasureFunction {

        /* renamed from: w */
        public int f702w;

        /* renamed from: x */
        public int f703x;

        /* renamed from: y */
        public boolean f704y;

        public /* synthetic */ C0166b(C0165a aVar) {
            this.t.mo1888a((YogaMeasureFunction) this);
        }

        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.f704y) {
                vD vDVar = new vD(j());
                vDVar.setShowText(false);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                vDVar.measure(makeMeasureSpec, makeMeasureSpec);
                this.f702w = vDVar.getMeasuredWidth();
                this.f703x = vDVar.getMeasuredHeight();
                this.f704y = true;
            }
            return fF.a(this.f702w, this.f703x);
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class getShadowNodeClass() {
        return C0166b.class;
    }

    @eC(defaultBoolean = false, name = "disabled")
    public void setDisabled(vD vDVar, boolean z) {
        vDVar.setEnabled(!z);
    }

    @eC(defaultBoolean = true, name = "enabled")
    public void setEnabled(vD vDVar, boolean z) {
        vDVar.setEnabled(z);
    }

    @eC(name = "on")
    public void setOn(vD vDVar, boolean z) {
        setValue(vDVar, z);
    }

    @eC(customType = "Color", name = "thumbColor")
    public void setThumbColor(vD vDVar, Integer num) {
        vDVar.a(vDVar.a, num);
    }

    @eC(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(vD vDVar, Integer num) {
        setThumbColor(vDVar, num);
    }

    @eC(customType = "Color", name = "trackColorForFalse")
    public void setTrackColorForFalse(vD vDVar, Integer num) {
        if (num != vDVar.U3) {
            vDVar.U3 = num;
            if (!vDVar.isChecked()) {
                vDVar.a(vDVar.k, vDVar.U3);
            }
        }
    }

    @eC(customType = "Color", name = "trackColorForTrue")
    public void setTrackColorForTrue(vD vDVar, Integer num) {
        if (num != vDVar.V3) {
            vDVar.V3 = num;
            if (vDVar.isChecked()) {
                vDVar.a(vDVar.k, vDVar.V3);
            }
        }
    }

    @eC(customType = "Color", name = "trackTintColor")
    public void setTrackTintColor(vD vDVar, Integer num) {
        vDVar.a(vDVar.k, num);
    }

    @eC(name = "value")
    public void setValue(vD vDVar, boolean z) {
        vDVar.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        vDVar.a(z);
        vDVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    public void addEventEmitters(WA wa, vD vDVar) {
        vDVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    public xA createShadowNodeInstance() {
        return new C0166b((C0165a) null);
    }

    public vD createViewInstance(WA wa) {
        vD vDVar = new vD(wa);
        vDVar.setShowText(false);
        return vDVar;
    }
}
