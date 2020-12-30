package com.facebook.react.views.scroll;

import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import java.util.ArrayList;

@Ay(name = "AndroidHorizontalScrollView")
/* compiled from: PG */
public class ReactHorizontalScrollViewManager extends ViewGroupManager<ReactHorizontalScrollView> implements ReactScrollViewCommandHelper.ScrollCommandHandler<ReactHorizontalScrollView> {
    public static final String REACT_CLASS = "AndroidHorizontalScrollView";
    public static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    public nD mFpsListener;

    public ReactHorizontalScrollViewManager() {
        this((nD) null);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @fC(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactHorizontalScrollView reactHorizontalScrollView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactHorizontalScrollView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactHorizontalScrollView reactHorizontalScrollView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactHorizontalScrollView.setBorderRadius(f);
        } else {
            reactHorizontalScrollView.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderStyle")
    public void setBorderStyle(ReactHorizontalScrollView reactHorizontalScrollView, String str) {
        reactHorizontalScrollView.setBorderStyle(str);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactHorizontalScrollView reactHorizontalScrollView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        reactHorizontalScrollView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @eC(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactHorizontalScrollView reactHorizontalScrollView, int i) {
        reactHorizontalScrollView.setEndFillColor(i);
    }

    @eC(name = "decelerationRate")
    public void setDecelerationRate(ReactHorizontalScrollView reactHorizontalScrollView, float f) {
        reactHorizontalScrollView.setDecelerationRate(f);
    }

    @eC(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        I9.a.b(reactHorizontalScrollView, z);
    }

    @eC(name = "overScrollMode")
    public void setOverScrollMode(ReactHorizontalScrollView reactHorizontalScrollView, String str) {
        reactHorizontalScrollView.setOverScrollMode(pD.a(str));
    }

    @eC(name = "overflow")
    public void setOverflow(ReactHorizontalScrollView reactHorizontalScrollView, String str) {
        reactHorizontalScrollView.setOverflow(str);
    }

    @eC(name = "pagingEnabled")
    public void setPagingEnabled(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setPagingEnabled(z);
    }

    @eC(name = "persistentScrollbar")
    public void setPersistentScrollbar(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setScrollbarFadingEnabled(!z);
    }

    @eC(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setRemoveClippedSubviews(z);
    }

    @eC(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setScrollEnabled(z);
    }

    @eC(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactHorizontalScrollView reactHorizontalScrollView, String str) {
        reactHorizontalScrollView.setScrollPerfTag(str);
    }

    @eC(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setSendMomentumEvents(z);
    }

    @eC(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setHorizontalScrollBarEnabled(z);
    }

    @eC(name = "snapToEnd")
    public void setSnapToEnd(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setSnapToEnd(z);
    }

    @eC(name = "snapToInterval")
    public void setSnapToInterval(ReactHorizontalScrollView reactHorizontalScrollView, float f) {
        reactHorizontalScrollView.setSnapInterval((int) (f * rA.b.density));
    }

    @eC(name = "snapToOffsets")
    public void setSnapToOffsets(ReactHorizontalScrollView reactHorizontalScrollView, ReadableArray readableArray) {
        DisplayMetrics displayMetrics = rA.b;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            double d = readableArray.getDouble(i);
            double d2 = (double) displayMetrics.density;
            Double.isNaN(d2);
            arrayList.add(Integer.valueOf((int) (d * d2)));
        }
        reactHorizontalScrollView.setSnapOffsets(arrayList);
    }

    @eC(name = "snapToStart")
    public void setSnapToStart(ReactHorizontalScrollView reactHorizontalScrollView, boolean z) {
        reactHorizontalScrollView.setSnapToStart(z);
    }

    public ReactHorizontalScrollViewManager(nD nDVar) {
    }

    public ReactHorizontalScrollView createViewInstance(WA wa) {
        return new ReactHorizontalScrollView(wa, (nD) null);
    }

    public void flashScrollIndicators(ReactHorizontalScrollView reactHorizontalScrollView) {
        reactHorizontalScrollView.mo1347b();
    }

    public void receiveCommand(ReactHorizontalScrollView reactHorizontalScrollView, int i, ReadableArray readableArray) {
        ReactScrollViewCommandHelper.m585a(this, reactHorizontalScrollView, i, readableArray);
    }

    public void scrollTo(ReactHorizontalScrollView reactHorizontalScrollView, ReactScrollViewCommandHelper.C0160a aVar) {
        if (aVar.f684c) {
            reactHorizontalScrollView.smoothScrollTo(aVar.f682a, aVar.f683b);
        } else {
            reactHorizontalScrollView.scrollTo(aVar.f682a, aVar.f683b);
        }
    }

    public void scrollToEnd(ReactHorizontalScrollView reactHorizontalScrollView, ReactScrollViewCommandHelper.C0161b bVar) {
        int paddingRight = reactHorizontalScrollView.getPaddingRight() + reactHorizontalScrollView.getChildAt(0).getWidth();
        if (bVar.f685a) {
            reactHorizontalScrollView.smoothScrollTo(paddingRight, reactHorizontalScrollView.getScrollY());
        } else {
            reactHorizontalScrollView.scrollTo(paddingRight, reactHorizontalScrollView.getScrollY());
        }
    }
}
