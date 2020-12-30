package com.facebook.react.views.picker;

import android.content.Context;

@Ay(name = "AndroidDialogPicker")
/* compiled from: PG */
public class ReactDialogPickerManager extends ReactPickerManager {
    public static final String REACT_CLASS = "AndroidDialogPicker";

    public String getName() {
        return REACT_CLASS;
    }

    public ReactPicker createViewInstance(WA wa) {
        return new ReactPicker((Context) wa, 0);
    }
}
