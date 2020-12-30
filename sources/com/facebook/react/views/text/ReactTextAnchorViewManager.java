package com.facebook.react.views.text;

import CD;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;

/* compiled from: PG */
public abstract class ReactTextAnchorViewManager<T extends View, C extends CD> extends BaseViewManager<T, C> {
    public static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};

    @fC(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactTextView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
        } else {
            reactTextView.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, String str) {
        reactTextView.setBorderStyle(str);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        reactTextView.setBorderWidth(SPACING_TYPES[i], f);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultBoolean = false, name = "disabled")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDisabled(com.facebook.react.views.text.ReactTextView r1, boolean r2) {
        /*
            r0 = this;
            r2 = r2 ^ 1
            r1.setEnabled(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextAnchorViewManager.setDisabled(com.facebook.react.views.text.ReactTextView, boolean):void");
    }

    @eC(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView reactTextView, String str) {
        if (str == null || str.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
        } else if (str.equals("head")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
        } else if (str.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else if (str.equals("clip")) {
            reactTextView.setEllipsizeLocation((TextUtils.TruncateAt) null);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("Invalid ellipsizeMode: ", str));
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultBoolean = true, name = "includeFontPadding")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setIncludeFontPadding(com.facebook.react.views.text.ReactTextView r1, boolean r2) {
        /*
            r0 = this;
            r1.setIncludeFontPadding(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextAnchorViewManager.setIncludeFontPadding(com.facebook.react.views.text.ReactTextView, boolean):void");
    }

    @eC(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(name = "selectable")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectable(com.facebook.react.views.text.ReactTextView r1, boolean r2) {
        /*
            r0 = this;
            r1.setTextIsSelectable(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextAnchorViewManager.setSelectable(com.facebook.react.views.text.ReactTextView, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.TextView, com.facebook.react.views.text.ReactTextView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(customType = "Color", name = "selectionColor")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectionColor(com.facebook.react.views.text.ReactTextView r2, java.lang.Integer r3) {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x0015
            android.content.Context r3 = r2.getContext()
            r0 = 16842905(0x1010099, float:2.3693987E-38)
            android.content.res.ColorStateList r3 = AD.a(r3, r0)
            int r3 = r3.getDefaultColor()
            r2.setHighlightColor(r3)
            goto L_0x001c
        L_0x0015:
            int r3 = r3.intValue()
            r2.setHighlightColor(r3)
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextAnchorViewManager.setSelectionColor(com.facebook.react.views.text.ReactTextView, java.lang.Integer):void");
    }

    @eC(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView reactTextView, String str) {
        if (str == null || "auto".equals(str)) {
            reactTextView.mo1528b(0);
        } else if ("top".equals(str)) {
            reactTextView.mo1528b(48);
        } else if ("bottom".equals(str)) {
            reactTextView.mo1528b(80);
        } else if ("center".equals(str)) {
            reactTextView.mo1528b(16);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.a("Invalid textAlignVertical: ", str));
        }
    }
}
