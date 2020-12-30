package com.facebook.react.views.viewpager;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;

@Ay(name = "AndroidViewPager")
/* compiled from: PG */
public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager> {
    public static final int COMMAND_SET_PAGE = 1;
    public static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    public static final String REACT_CLASS = "AndroidViewPager";

    public Map<String, Integer> getCommandsMap() {
        return hy.a("setPage", 1, "setPageWithoutAnimation", 2);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return hy.a("topPageScroll", hy.a("registrationName", "onPageScroll"), "topPageScrollStateChanged", hy.a("registrationName", "onPageScrollStateChanged"), "topPageSelected", hy.a("registrationName", "onPageSelected"));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @eC(defaultFloat = 0.0f, name = "pageMargin")
    public void setPageMargin(ReactViewPager reactViewPager, float f) {
        reactViewPager.setPageMargin((int) GA.b(f));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @eC(defaultBoolean = false, name = "peekEnabled")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPeekEnabled(com.facebook.react.views.viewpager.ReactViewPager r1, boolean r2) {
        /*
            r0 = this;
            r2 = r2 ^ 1
            r1.setClipToPadding(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.viewpager.ReactViewPagerManager.setPeekEnabled(com.facebook.react.views.viewpager.ReactViewPager, boolean):void");
    }

    @eC(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactViewPager reactViewPager, boolean z) {
        reactViewPager.setScrollEnabled(z);
    }

    public void addView(ReactViewPager reactViewPager, View view, int i) {
        reactViewPager.mo1789a(view, i);
    }

    public ReactViewPager createViewInstance(WA wa) {
        return new ReactViewPager(wa);
    }

    public View getChildAt(ReactViewPager reactViewPager, int i) {
        return reactViewPager.mo1790c(i);
    }

    public int getChildCount(ReactViewPager reactViewPager) {
        return reactViewPager.mo1792e();
    }

    public void receiveCommand(ReactViewPager reactViewPager, int i, ReadableArray readableArray) {
        Kw.a(reactViewPager);
        Kw.a(readableArray);
        if (i == 1) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), true);
        } else if (i == 2) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), false);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(i), ReactViewPagerManager.class.getSimpleName()}));
        }
    }

    public void removeViewAt(ReactViewPager reactViewPager, int i) {
        reactViewPager.mo1791d(i);
    }
}
