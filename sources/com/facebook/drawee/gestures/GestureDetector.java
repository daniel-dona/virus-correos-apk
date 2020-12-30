package com.facebook.drawee.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* compiled from: PG */
public class GestureDetector {

    /* renamed from: a */
    public ClickListener f310a;

    /* renamed from: b */
    public final float f311b;

    /* renamed from: c */
    public boolean f312c;

    /* renamed from: d */
    public boolean f313d;

    /* renamed from: e */
    public long f314e;

    /* renamed from: f */
    public float f315f;

    /* renamed from: g */
    public float f316g;

    /* compiled from: PG */
    public interface ClickListener {
        boolean onClick();
    }

    public GestureDetector(Context context) {
        this.f311b = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        mo403a();
    }

    /* renamed from: a */
    public void mo403a() {
        this.f310a = null;
        this.f312c = false;
        this.f313d = false;
    }

    /* renamed from: a */
    public void mo404a(ClickListener clickListener) {
        this.f310a = clickListener;
    }

    /* renamed from: a */
    public boolean mo405a(MotionEvent motionEvent) {
        ClickListener clickListener;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f312c = true;
            this.f313d = true;
            this.f314e = motionEvent.getEventTime();
            this.f315f = motionEvent.getX();
            this.f316g = motionEvent.getY();
        } else if (action == 1) {
            this.f312c = false;
            if (Math.abs(motionEvent.getX() - this.f315f) > this.f311b || Math.abs(motionEvent.getY() - this.f316g) > this.f311b) {
                this.f313d = false;
            }
            if (this.f313d && motionEvent.getEventTime() - this.f314e <= ((long) ViewConfiguration.getLongPressTimeout()) && (clickListener = this.f310a) != null) {
                clickListener.onClick();
            }
            this.f313d = false;
        } else if (action != 2) {
            if (action == 3) {
                this.f312c = false;
                this.f313d = false;
            }
        } else if (Math.abs(motionEvent.getX() - this.f315f) > this.f311b || Math.abs(motionEvent.getY() - this.f316g) > this.f311b) {
            this.f313d = false;
        }
        return true;
    }
}
