package com.facebook.react.views.scroll;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/* compiled from: PG */
public class ReactHorizontalScrollContainerView extends ViewGroup {

    /* renamed from: a */
    public int f627a;

    /* renamed from: b */
    public int f628b = 0;

    public ReactHorizontalScrollContainerView(Context context) {
        super(context);
        this.f627a = Ty.a().b(context) ? 1 : 0;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f627a == 1) {
            setLeft(0);
            setRight((i3 - i) + 0);
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getParent();
            horizontalScrollView.scrollTo((getWidth() + horizontalScrollView.getScrollX()) - this.f628b, horizontalScrollView.getScrollY());
        }
        this.f628b = getWidth();
    }
}
