package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;

@Ay(name = "AndroidProgressBar")
/* compiled from: PG */
public class ReactProgressBarViewManager extends BaseViewManager<lD, mD> {
    public static final String DEFAULT_STYLE = "Normal";
    public static final String PROP_ANIMATING = "animating";
    public static final String PROP_INDETERMINATE = "indeterminate";
    public static final String PROP_PROGRESS = "progress";
    public static final String PROP_STYLE = "styleAttr";
    public static final String REACT_CLASS = "AndroidProgressBar";
    public static Object sProgressBarCtorLock = new Object();

    public static ProgressBar createProgressBar(Context context, int i) {
        ProgressBar progressBar;
        synchronized (sProgressBarCtorLock) {
            progressBar = new ProgressBar(context, (AttributeSet) null, i);
        }
        return progressBar;
    }

    public static int getStyleFromString(String str) {
        if (str == null) {
            throw new JSApplicationIllegalArgumentException("ProgressBar needs to have a style, null received");
        } else if (str.equals("Horizontal")) {
            return 16842872;
        } else {
            if (str.equals("Small")) {
                return 16842873;
            }
            if (str.equals("Large")) {
                return 16842874;
            }
            if (str.equals("Inverse")) {
                return 16843399;
            }
            if (str.equals("SmallInverse")) {
                return 16843400;
            }
            if (str.equals("LargeInverse")) {
                return 16843401;
            }
            if (str.equals(DEFAULT_STYLE)) {
                return 16842871;
            }
            throw new JSApplicationIllegalArgumentException(Eo.a("Unknown ProgressBar style: ", str));
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Class<mD> getShadowNodeClass() {
        return mD.class;
    }

    @eC(name = "animating")
    public void setAnimating(lD lDVar, boolean z) {
        lDVar.c = z;
    }

    @eC(customType = "Color", name = "color")
    public void setColor(lD lDVar, Integer num) {
        lDVar.a = num;
    }

    @eC(name = "indeterminate")
    public void setIndeterminate(lD lDVar, boolean z) {
        lDVar.b = z;
    }

    @eC(name = "progress")
    public void setProgress(lD lDVar, double d) {
        lDVar.d = d;
    }

    @eC(name = "styleAttr")
    public void setStyle(lD lDVar, String str) {
        lDVar.a(str);
    }

    public void updateExtraData(lD lDVar, Object obj) {
    }

    public mD createShadowNodeInstance() {
        return new mD();
    }

    public lD createViewInstance(WA wa) {
        return new lD(wa);
    }

    public void onAfterUpdateTransaction(lD lDVar) {
        Drawable drawable;
        ProgressBar progressBar = lDVar.e;
        if (progressBar != null) {
            progressBar.setIndeterminate(lDVar.b);
            ProgressBar progressBar2 = lDVar.e;
            if (progressBar2.isIndeterminate()) {
                drawable = progressBar2.getIndeterminateDrawable();
            } else {
                drawable = progressBar2.getProgressDrawable();
            }
            if (drawable != null) {
                Integer num = lDVar.a;
                if (num != null) {
                    drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                } else {
                    drawable.clearColorFilter();
                }
            }
            lDVar.e.setProgress((int) (lDVar.d * 1000.0d));
            if (lDVar.c) {
                lDVar.e.setVisibility(0);
            } else {
                lDVar.e.setVisibility(8);
            }
        } else {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
    }
}
