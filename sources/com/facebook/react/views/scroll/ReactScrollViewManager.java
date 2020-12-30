package com.facebook.react.views.scroll;

import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import hy;
import java.util.ArrayList;
import java.util.Map;

@Ay(name = "RCTScrollView")
/* compiled from: PG */
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ReactScrollViewCommandHelper.ScrollCommandHandler<ReactScrollView> {
    public static final String REACT_CLASS = "RCTScrollView";
    public static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    public nD mFpsListener;

    public ReactScrollViewManager() {
        this((nD) null);
    }

    public static Map<String, Object> createExportedCustomDirectEventTypeConstants() {
        hy.a a = hy.a();
        a.a(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), hy.a("registrationName", "onScroll"));
        a.a(ScrollEventType.getJSEventName(ScrollEventType.BEGIN_DRAG), hy.a("registrationName", "onScrollBeginDrag"));
        a.a(ScrollEventType.getJSEventName(ScrollEventType.END_DRAG), hy.a("registrationName", "onScrollEndDrag"));
        a.a(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_BEGIN), hy.a("registrationName", "onMomentumScrollBegin"));
        a.a(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_END), hy.a("registrationName", "onMomentumScrollEnd"));
        return a.a();
    }

    public Map<String, Integer> getCommandsMap() {
        return hy.a("scrollTo", 1, "scrollToEnd", 2, "flashScrollIndicators", 3);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    public String getName() {
        return REACT_CLASS;
    }

    @fC(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactScrollView reactScrollView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactScrollView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactScrollView reactScrollView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactScrollView.setBorderRadius(f);
        } else {
            reactScrollView.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderStyle")
    public void setBorderStyle(ReactScrollView reactScrollView, String str) {
        reactScrollView.setBorderStyle(str);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactScrollView reactScrollView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        reactScrollView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @eC(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactScrollView reactScrollView, int i) {
        reactScrollView.setEndFillColor(i);
    }

    @eC(name = "decelerationRate")
    public void setDecelerationRate(ReactScrollView reactScrollView, float f) {
        reactScrollView.setDecelerationRate(f);
    }

    @eC(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        I9.a.b(reactScrollView, z);
    }

    @eC(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverScrollMode(pD.a(str));
    }

    @eC(name = "overflow")
    public void setOverflow(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverflow(str);
    }

    @eC(name = "pagingEnabled")
    public void setPagingEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setPagingEnabled(z);
    }

    @eC(name = "persistentScrollbar")
    public void setPersistentScrollbar(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollbarFadingEnabled(!z);
    }

    @eC(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setRemoveClippedSubviews(z);
    }

    @eC(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollEnabled(z);
    }

    @eC(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactScrollView reactScrollView, String str) {
        reactScrollView.setScrollPerfTag(str);
    }

    @eC(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSendMomentumEvents(z);
    }

    @eC(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setVerticalScrollBarEnabled(z);
    }

    @eC(name = "snapToEnd")
    public void setSnapToEnd(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToEnd(z);
    }

    @eC(name = "snapToInterval")
    public void setSnapToInterval(ReactScrollView reactScrollView, float f) {
        reactScrollView.setSnapInterval((int) (f * rA.b.density));
    }

    @eC(name = "snapToOffsets")
    public void setSnapToOffsets(ReactScrollView reactScrollView, ReadableArray readableArray) {
        DisplayMetrics displayMetrics = rA.b;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            double d = readableArray.getDouble(i);
            double d2 = (double) displayMetrics.density;
            Double.isNaN(d2);
            arrayList.add(Integer.valueOf((int) (d * d2)));
        }
        reactScrollView.setSnapOffsets(arrayList);
    }

    @eC(name = "snapToStart")
    public void setSnapToStart(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToStart(z);
    }

    public ReactScrollViewManager(nD nDVar) {
    }

    public ReactScrollView createViewInstance(WA wa) {
        return new ReactScrollView(wa);
    }

    public void flashScrollIndicators(ReactScrollView reactScrollView) {
        reactScrollView.mo1411b();
    }

    public void receiveCommand(ReactScrollView reactScrollView, int i, ReadableArray readableArray) {
        ReactScrollViewCommandHelper.m585a(this, reactScrollView, i, readableArray);
    }

    public void scrollTo(ReactScrollView reactScrollView, ReactScrollViewCommandHelper.C0160a aVar) {
        if (aVar.f684c) {
            reactScrollView.smoothScrollTo(aVar.f682a, aVar.f683b);
        } else {
            reactScrollView.scrollTo(aVar.f682a, aVar.f683b);
        }
    }

    public void scrollToEnd(ReactScrollView reactScrollView, ReactScrollViewCommandHelper.C0161b bVar) {
        int paddingBottom = reactScrollView.getPaddingBottom() + reactScrollView.getChildAt(0).getHeight();
        if (bVar.f685a) {
            reactScrollView.smoothScrollTo(reactScrollView.getScrollX(), paddingBottom);
        } else {
            reactScrollView.scrollTo(reactScrollView.getScrollX(), paddingBottom);
        }
    }
}
