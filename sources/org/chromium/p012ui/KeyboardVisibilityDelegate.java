package org.chromium.p012ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ObserverList;

/* renamed from: org.chromium.ui.KeyboardVisibilityDelegate */
/* compiled from: PG */
public class KeyboardVisibilityDelegate {

    /* renamed from: b */
    public static KeyboardVisibilityDelegate f2588b = new KeyboardVisibilityDelegate();

    /* renamed from: a */
    public final ObserverList<KeyboardVisibilityListener> f2589a = new ObserverList<>();

    /* renamed from: a */
    public static void m3624a(KeyboardVisibilityDelegate keyboardVisibilityDelegate) {
        f2588b = keyboardVisibilityDelegate;
    }

    /* renamed from: c */
    public static KeyboardVisibilityDelegate m3625c() {
        return f2588b;
    }

    /* renamed from: a */
    public void mo9943a() {
    }

    /* renamed from: b */
    public void mo9946b() {
    }

    /* renamed from: b */
    public boolean mo9949b(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    public void mo9951d(View view) {
        new JH3(this, view, new AtomicInteger(), new Handler()).run();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        r4 = r4.getRootWindowInsets();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo9942a(android.view.View r4) {
        /*
            r3 = this;
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r4.getWindowVisibleDisplayFrame(r0)
            int r1 = r0.top
            int r2 = r4.getHeight()
            int r0 = r0.height()
            int r0 = r0 + r1
            int r2 = r2 - r0
            if (r2 > 0) goto L_0x0018
            r4 = 0
            return r4
        L_0x0018:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 < r1) goto L_0x0029
            android.view.WindowInsets r4 = r4.getRootWindowInsets()
            if (r4 == 0) goto L_0x0029
            int r4 = r4.getStableInsetBottom()
            int r2 = r2 - r4
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.p012ui.KeyboardVisibilityDelegate.mo9942a(android.view.View):int");
    }

    /* renamed from: c */
    public boolean mo9950c(View view) {
        return mo9949b(view);
    }

    /* renamed from: b */
    public boolean mo9948b(Context context, View view) {
        return mo9945a(context, view);
    }

    /* renamed from: b */
    public void mo9947b(KeyboardVisibilityListener keyboardVisibilityListener) {
        this.f2589a.mo7869b(keyboardVisibilityListener);
        if (this.f2589a.isEmpty()) {
            mo9946b();
        }
    }

    /* renamed from: a */
    public boolean mo9945a(Context context, View view) {
        int i;
        View rootView = view.getRootView();
        if (rootView != null) {
            int a = mo9942a(rootView);
            Rect rect = new Rect();
            rootView.getWindowVisibleDisplayFrame(rect);
            if (!(rect.width() != rootView.getWidth()) && Build.VERSION.SDK_INT < 23) {
                i = (int) (context.getResources().getDisplayMetrics().density * 100.0f);
            } else {
                i = 0;
            }
            if (a > i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo9944a(KeyboardVisibilityListener keyboardVisibilityListener) {
        if (this.f2589a.isEmpty()) {
            mo9943a();
        }
        this.f2589a.mo7868a(keyboardVisibilityListener);
    }
}
