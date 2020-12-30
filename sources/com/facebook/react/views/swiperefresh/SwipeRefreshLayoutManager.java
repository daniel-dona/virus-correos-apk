package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import hy;
import java.util.Map;

@Ay(name = "AndroidSwipeRefreshLayout")
/* compiled from: PG */
public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout> {
    public static final String REACT_CLASS = "AndroidSwipeRefreshLayout";

    /* renamed from: com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager$a */
    /* compiled from: PG */
    public class C0164a implements SwipeRefreshLayout.OnRefreshListener {

        /* renamed from: a */
        public final /* synthetic */ WA f700a;

        /* renamed from: b */
        public final /* synthetic */ ReactSwipeRefreshLayout f701b;

        public C0164a(SwipeRefreshLayoutManager swipeRefreshLayoutManager, WA wa, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
            this.f700a = wa;
            this.f701b = reactSwipeRefreshLayout;
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout, android.view.ViewGroup] */
        public void onRefresh() {
            ((UIManagerModule) this.f700a.getNativeModule(UIManagerModule.class)).getEventDispatcher().b(new uD(this.f701b.getId()));
        }
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        hy.a a = hy.a();
        a.a("topRefresh", hy.a("registrationName", "onRefresh"));
        return a.a();
    }

    public Map<String, Object> getExportedViewConstants() {
        return hy.a("SIZE", hy.a("DEFAULT", 1, "LARGE", 0));
    }

    public String getName() {
        return REACT_CLASS;
    }

    @eC(customType = "ColorArray", name = "colors")
    public void setColors(ReactSwipeRefreshLayout reactSwipeRefreshLayout, ReadableArray readableArray) {
        if (readableArray != null) {
            int[] iArr = new int[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                iArr[i] = readableArray.getInt(i);
            }
            reactSwipeRefreshLayout.setColorSchemeColors(iArr);
            return;
        }
        reactSwipeRefreshLayout.setColorSchemeColors(new int[0]);
    }

    @eC(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        reactSwipeRefreshLayout.setEnabled(z);
    }

    @eC(customType = "Color", defaultInt = 0, name = "progressBackgroundColor")
    public void setProgressBackgroundColor(ReactSwipeRefreshLayout reactSwipeRefreshLayout, int i) {
        reactSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(i);
    }

    @eC(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(ReactSwipeRefreshLayout reactSwipeRefreshLayout, float f) {
        reactSwipeRefreshLayout.setProgressViewOffset(f);
    }

    @eC(name = "refreshing")
    public void setRefreshing(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        reactSwipeRefreshLayout.setRefreshing(z);
    }

    @eC(defaultInt = 1, name = "size")
    public void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, int i) {
        reactSwipeRefreshLayout.setSize(i);
    }

    public void addEventEmitters(WA wa, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        reactSwipeRefreshLayout.setOnRefreshListener(new C0164a(this, wa, reactSwipeRefreshLayout));
    }

    public ReactSwipeRefreshLayout createViewInstance(WA wa) {
        return new ReactSwipeRefreshLayout(wa);
    }
}
