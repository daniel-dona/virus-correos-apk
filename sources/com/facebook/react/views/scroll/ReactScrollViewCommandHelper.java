package com.facebook.react.views.scroll;

import com.facebook.react.bridge.ReadableArray;

/* compiled from: PG */
public abstract class ReactScrollViewCommandHelper {

    /* compiled from: PG */
    public interface ScrollCommandHandler<T> {
        void flashScrollIndicators(T t);

        void scrollTo(T t, C0160a aVar);

        void scrollToEnd(T t, C0161b bVar);
    }

    /* renamed from: com.facebook.react.views.scroll.ReactScrollViewCommandHelper$a */
    /* compiled from: PG */
    public static class C0160a {

        /* renamed from: a */
        public final int f682a;

        /* renamed from: b */
        public final int f683b;

        /* renamed from: c */
        public final boolean f684c;

        public C0160a(int i, int i2, boolean z) {
            this.f682a = i;
            this.f683b = i2;
            this.f684c = z;
        }
    }

    /* renamed from: com.facebook.react.views.scroll.ReactScrollViewCommandHelper$b */
    /* compiled from: PG */
    public static class C0161b {

        /* renamed from: a */
        public final boolean f685a;

        public C0161b(boolean z) {
            this.f685a = z;
        }
    }

    /* renamed from: a */
    public static <T> void m585a(ScrollCommandHandler<T> scrollCommandHandler, T t, int i, ReadableArray readableArray) {
        Kw.a(scrollCommandHandler);
        Kw.a(t);
        Kw.a(readableArray);
        if (i == 1) {
            scrollCommandHandler.scrollTo(t, new C0160a(Math.round(GA.a(readableArray.getDouble(0))), Math.round(GA.a(readableArray.getDouble(1))), readableArray.getBoolean(2)));
        } else if (i == 2) {
            scrollCommandHandler.scrollToEnd(t, new C0161b(readableArray.getBoolean(0)));
        } else if (i == 3) {
            scrollCommandHandler.flashScrollIndicators(t);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(i), scrollCommandHandler.getClass().getSimpleName()}));
        }
    }
}
