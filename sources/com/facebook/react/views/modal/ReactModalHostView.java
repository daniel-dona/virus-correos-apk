package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;

/* compiled from: PG */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {

    /* renamed from: a */
    public C0153b f606a;

    /* renamed from: b */
    public Dialog f607b;

    /* renamed from: c */
    public boolean f608c;

    /* renamed from: d */
    public String f609d;

    /* renamed from: e */
    public boolean f610e;

    /* renamed from: k */
    public boolean f611k;

    /* renamed from: n */
    public DialogInterface.OnShowListener f612n;

    /* renamed from: p */
    public OnRequestCloseListener f613p;

    /* compiled from: PG */
    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    /* renamed from: com.facebook.react.views.modal.ReactModalHostView$a */
    /* compiled from: PG */
    public class C0152a implements DialogInterface.OnKeyListener {
        public C0152a() {
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 1) {
                return false;
            }
            if (i == 4) {
                Kw.a(ReactModalHostView.this.f613p, "setOnRequestCloseListener must be called by the manager");
                ReactModalHostView.this.f613p.onRequestClose(dialogInterface);
                return true;
            }
            Activity currentActivity = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
            if (currentActivity != null) {
                return currentActivity.onKeyUp(i, keyEvent);
            }
            return false;
        }
    }

    /* renamed from: com.facebook.react.views.modal.ReactModalHostView$b */
    /* compiled from: PG */
    public static class C0153b extends ReactViewGroup implements SA {

        /* renamed from: x3 */
        public final uA f615x3 = new uA(this);

        public C0153b(Context context) {
            super(context);
        }

        /* renamed from: i */
        public final qC mo1307i() {
            return ((UIManagerModule) mo1308j().getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        /* renamed from: j */
        public final ReactContext mo1308j() {
            return (ReactContext) getContext();
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.f615x3.b(motionEvent, mo1307i());
            return super.onInterceptTouchEvent(motionEvent);
        }

        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (getChildCount() > 0) {
                int id = getChildAt(0).getId();
                ReactContext j = mo1308j();
                j.runOnNativeModulesQueueThread(new hD(this, j, id, i, i2));
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.f615x3.b(motionEvent, mo1307i());
            super.onTouchEvent(motionEvent);
            return true;
        }

        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        /* renamed from: a */
        public void mo1306a(Throwable th) {
            mo1308j().handleException(new RuntimeException(th));
        }

        /* renamed from: a */
        public void mo1305a(MotionEvent motionEvent) {
            uA uAVar = this.f615x3;
            qC i = mo1307i();
            if (!uAVar.c) {
                uAVar.a(motionEvent, i);
                uAVar.c = true;
                uAVar.a = -1;
            }
        }
    }

    public ReactModalHostView(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.f606a = new C0153b(context);
    }

    /* renamed from: a */
    public final void mo1286a() {
        Context baseContext;
        Dialog dialog = this.f607b;
        if (dialog != null) {
            if (dialog.isShowing()) {
                Context context = this.f607b.getContext();
                Class<Activity> cls = Activity.class;
                while (true) {
                    if (cls.isInstance(context)) {
                        break;
                    } else if (!(context instanceof ContextWrapper) || context == (baseContext = ((ContextWrapper) context).getBaseContext())) {
                        context = null;
                    } else {
                        context = baseContext;
                    }
                }
                context = null;
                Activity activity = (Activity) context;
                if (activity == null || !activity.isFinishing()) {
                    this.f607b.dismiss();
                }
            }
            this.f607b = null;
            ((ViewGroup) this.f606a.getParent()).removeViewAt(0);
        }
    }

    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    public void addView(View view, int i) {
        this.f606a.addView(view, i);
    }

    /* renamed from: b */
    public void mo1293b() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        mo1286a();
    }

    /* renamed from: c */
    public void mo1295c() {
        if (this.f607b != null) {
            if (this.f611k) {
                mo1286a();
            } else {
                mo1296d();
                return;
            }
        }
        this.f611k = false;
        int i = xx0.Theme_FullScreenDialog;
        if (this.f609d.equals("fade")) {
            i = xx0.Theme_FullScreenDialogAnimatedFade;
        } else if (this.f609d.equals("slide")) {
            i = xx0.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = ((ReactContext) getContext()).getCurrentActivity();
        this.f607b = new Dialog(currentActivity == null ? getContext() : currentActivity, i);
        Dialog dialog = this.f607b;
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.f606a);
        frameLayout.setFitsSystemWindows(true);
        dialog.setContentView(frameLayout);
        mo1296d();
        this.f607b.setOnShowListener(this.f612n);
        this.f607b.setOnKeyListener(new C0152a());
        this.f607b.getWindow().setSoftInputMode(16);
        if (this.f610e) {
            this.f607b.getWindow().addFlags(16777216);
        }
        if (currentActivity != null && !currentActivity.isFinishing()) {
            this.f607b.show();
        }
    }

    /* renamed from: d */
    public final void mo1296d() {
        Kw.a(this.f607b, "mDialog must exist when we call updateProperties");
        Activity currentActivity = ((ReactContext) getContext()).getCurrentActivity();
        if (currentActivity != null) {
            if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
                this.f607b.getWindow().addFlags(1024);
            } else {
                this.f607b.getWindow().clearFlags(1024);
            }
        }
        if (this.f608c) {
            this.f607b.getWindow().clearFlags(2);
            return;
        }
        this.f607b.getWindow().setDimAmount(0.5f);
        this.f607b.getWindow().setFlags(2, 2);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.f606a.dispatchProvideStructure(viewStructure);
    }

    public View getChildAt(int i) {
        return this.f606a.getChildAt(i);
    }

    public int getChildCount() {
        return this.f606a.getChildCount();
    }

    public void onHostDestroy() {
        mo1293b();
    }

    public void onHostPause() {
    }

    public void onHostResume() {
        mo1295c();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public void removeView(View view) {
        this.f606a.removeView(view);
    }

    public void removeViewAt(int i) {
        this.f606a.removeView(getChildAt(i));
    }

    /* renamed from: b */
    public void mo1294b(boolean z) {
        this.f608c = z;
    }

    /* renamed from: a */
    public void mo1288a(OnRequestCloseListener onRequestCloseListener) {
        this.f613p = onRequestCloseListener;
    }

    /* renamed from: a */
    public void mo1287a(DialogInterface.OnShowListener onShowListener) {
        this.f612n = onShowListener;
    }

    /* renamed from: a */
    public void mo1289a(String str) {
        this.f609d = str;
        this.f611k = true;
    }

    /* renamed from: a */
    public void mo1290a(boolean z) {
        this.f610e = z;
        this.f611k = true;
    }
}
