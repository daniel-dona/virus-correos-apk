package com.facebook.react.views.picker;

import android.content.Context;

@Ay(name = "AndroidDropdownPicker")
/* compiled from: PG */
public class ReactDropdownPickerManager extends ReactPickerManager {
    public static final String REACT_CLASS = "AndroidDropdownPicker";

    public String getName() {
        return REACT_CLASS;
    }

    public ReactPicker createViewInstance(WA wa) {
        return new ReactPicker((Context) wa, 1);
    }
}
