package com.facebook.react.views.viewpager;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public class ReactViewPager extends ViewPager {

    /* renamed from: a */
    public final qC f825a;

    /* renamed from: b */
    public boolean f826b;

    /* renamed from: c */
    public boolean f827c = true;

    /* renamed from: d */
    public final Runnable f828d = new C0188a();

    /* renamed from: com.facebook.react.views.viewpager.ReactViewPager$a */
    /* compiled from: PG */
    public class C0188a implements Runnable {
        public C0188a() {
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r2v2, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r3v2, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        public void run() {
            ? r0 = ReactViewPager.this;
            r0.measure(View.MeasureSpec.makeMeasureSpec(r0.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getHeight(), 1073741824));
            ? r02 = ReactViewPager.this;
            r02.layout(r02.getLeft(), ReactViewPager.this.getTop(), ReactViewPager.this.getRight(), ReactViewPager.this.getBottom());
        }
    }

    /* renamed from: com.facebook.react.views.viewpager.ReactViewPager$b */
    /* compiled from: PG */
    public class C0189b extends u9 {

        /* renamed from: a */
        public final List<View> f830a = new ArrayList();

        /* renamed from: b */
        public boolean f831b = false;

        public /* synthetic */ C0189b(C0188a aVar) {
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.f830a.size();
        }

        public int getItemPosition(Object obj) {
            if (this.f831b || !this.f830a.contains(obj)) {
                return -2;
            }
            return this.f830a.indexOf(obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = this.f830a.get(i);
            viewGroup.addView(view, 0, ReactViewPager.this.generateDefaultLayoutParams());
            return view;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    /* renamed from: com.facebook.react.views.viewpager.ReactViewPager$c */
    /* compiled from: PG */
    public class C0190c implements ViewPager.OnPageChangeListener {
        public /* synthetic */ C0190c(C0188a aVar) {
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        public void onPageScrollStateChanged(int i) {
            String str;
            if (i == 0) {
                str = "idle";
            } else if (i == 1) {
                str = "dragging";
            } else if (i == 2) {
                str = "settling";
            } else {
                throw new IllegalStateException("Unsupported pageScrollState");
            }
            ? r0 = ReactViewPager.this;
            r0.f825a.b(new sE(r0.getId(), str));
        }

        /* JADX WARNING: type inference failed for: r5v1, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        public void onPageScrolled(int i, float f, int i2) {
            ? r5 = ReactViewPager.this;
            r5.f825a.b(new rE(r5.getId(), i, f));
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.view.ViewGroup] */
        public void onPageSelected(int i) {
            ? r0 = ReactViewPager.this;
            if (!r0.f826b) {
                r0.f825a.b(new tE(r0.getId(), i));
            }
        }
    }

    public ReactViewPager(ReactContext reactContext) {
        super(reactContext);
        this.f825a = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.f826b = false;
        setOnPageChangeListener(new C0190c((C0188a) null));
        setAdapter(new C0189b((C0188a) null));
    }

    /* renamed from: c */
    public View mo1790c(int i) {
        return getAdapter().f830a.get(i);
    }

    /* renamed from: d */
    public void mo1791d(int i) {
        C0189b adapter = getAdapter();
        adapter.f830a.remove(i);
        adapter.notifyDataSetChanged();
        ReactViewPager.this.setOffscreenPageLimit(adapter.f830a.size());
    }

    /* renamed from: e */
    public int mo1792e() {
        return getAdapter().getCount();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.support.v4.view.ViewPager, android.view.ViewGroup] */
    public void onAttachedToWindow() {
        ReactViewPager.super.onAttachedToWindow();
        requestLayout();
        post(this.f828d);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.facebook.react.views.viewpager.ReactViewPager, android.support.v4.view.ViewPager, android.view.View] */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f827c) {
            return false;
        }
        try {
            if (ReactViewPager.super.onInterceptTouchEvent(motionEvent)) {
                rC.a(this, motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e) {
            pq.b("ReactNative", "Error intercepting touch event.", e);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f827c) {
            return false;
        }
        try {
            return ReactViewPager.super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            pq.b("ReactNative", "Error handling touch event.", e);
            return false;
        }
    }

    public void setCurrentItemFromJs(int i, boolean z) {
        this.f826b = true;
        setCurrentItem(i, z);
        this.f826b = false;
    }

    public void setScrollEnabled(boolean z) {
        this.f827c = z;
    }

    public void setViews(List<View> list) {
        C0189b adapter = getAdapter();
        adapter.f830a.clear();
        adapter.f830a.addAll(list);
        adapter.notifyDataSetChanged();
        adapter.f831b = false;
    }

    /* renamed from: a */
    public void mo1789a(View view, int i) {
        C0189b adapter = getAdapter();
        adapter.f830a.add(i, view);
        adapter.notifyDataSetChanged();
        ReactViewPager.this.setOffscreenPageLimit(adapter.f830a.size());
    }

    public C0189b getAdapter() {
        return ReactViewPager.super.getAdapter();
    }
}
