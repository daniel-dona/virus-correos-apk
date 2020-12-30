package com.facebook.react.views.checkbox;

import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;

/* compiled from: PG */
public class ReactCheckBoxManager extends SimpleViewManager<PC> {
    public static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new C0146a();
    public static final String REACT_CLASS = "AndroidCheckBox";

    /* renamed from: com.facebook.react.views.checkbox.ReactCheckBoxManager$a */
    /* compiled from: PG */
    public static class C0146a implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ReactContext reactContext;
            tg context = compoundButton.getContext();
            if (context instanceof tg) {
                reactContext = (ReactContext) context.getBaseContext();
            } else {
                reactContext = (ReactContext) compoundButton.getContext();
            }
            ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new QC(compoundButton.getId(), z));
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    @eC(defaultBoolean = true, name = "enabled")
    public void setEnabled(PC pc, boolean z) {
        pc.setEnabled(z);
    }

    @eC(name = "on")
    public void setOn(PC pc, boolean z) {
        pc.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        pc.a(z);
        pc.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    public void addEventEmitters(WA wa, PC pc) {
        pc.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    public PC createViewInstance(WA wa) {
        return new PC(wa);
    }
}
