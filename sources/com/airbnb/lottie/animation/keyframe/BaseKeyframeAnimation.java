package com.airbnb.lottie.animation.keyframe;

import com.facebook.react.uimanager.BaseViewManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public abstract class BaseKeyframeAnimation<K, A> {

    /* renamed from: a */
    public final List<AnimationListener> f50a = new ArrayList(1);

    /* renamed from: b */
    public boolean f51b = false;

    /* renamed from: c */
    public final List<? extends ok<K>> f52c;

    /* renamed from: d */
    public float f53d = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;

    /* renamed from: e */
    public qk<A> f54e;

    /* renamed from: f */
    public ok<K> f55f;

    /* renamed from: g */
    public ok<K> f56g;

    /* renamed from: h */
    public float f57h = -1.0f;

    /* renamed from: i */
    public A f58i = null;

    /* renamed from: j */
    public float f59j = -1.0f;

    /* renamed from: k */
    public float f60k = -1.0f;

    /* compiled from: PG */
    public interface AnimationListener {
        void onValueChanged();
    }

    public BaseKeyframeAnimation(List<? extends ok<K>> list) {
        this.f52c = list;
    }

    /* renamed from: a */
    public abstract A mo97a(ok<K> okVar, float f);

    /* renamed from: a */
    public void mo99a(float f) {
        if (!this.f52c.isEmpty()) {
            ok a = mo98a();
            if (f < mo104e()) {
                f = mo104e();
            } else if (f > mo101b()) {
                f = mo101b();
            }
            if (f != this.f53d) {
                this.f53d = f;
                ok a2 = mo98a();
                if (a != a2 || !a2.c()) {
                    mo106g();
                }
            }
        }
    }

    /* renamed from: b */
    public float mo101b() {
        float f;
        if (this.f60k == -1.0f) {
            if (this.f52c.isEmpty()) {
                f = 1.0f;
            } else {
                List<? extends ok<K>> list = this.f52c;
                f = list.get(list.size() - 1).a();
            }
            this.f60k = f;
        }
        return this.f60k;
    }

    /* renamed from: c */
    public float mo102c() {
        ok a = mo98a();
        if (a.c()) {
            return BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        }
        return a.d.getInterpolation(mo103d());
    }

    /* renamed from: d */
    public float mo103d() {
        if (this.f51b) {
            return BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        }
        ok a = mo98a();
        if (a.c()) {
            return BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        }
        return (this.f53d - a.b()) / (a.a() - a.b());
    }

    /* renamed from: e */
    public final float mo104e() {
        if (this.f59j == -1.0f) {
            this.f59j = this.f52c.isEmpty() ? BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER : this.f52c.get(0).b();
        }
        return this.f59j;
    }

    /* renamed from: f */
    public A mo105f() {
        ok<K> a = mo98a();
        float c = mo102c();
        if (this.f54e == null && a == this.f56g && this.f57h == c) {
            return this.f58i;
        }
        this.f56g = a;
        this.f57h = c;
        A a2 = mo97a(a, c);
        this.f58i = a2;
        return a2;
    }

    /* renamed from: g */
    public void mo106g() {
        for (int i = 0; i < this.f50a.size(); i++) {
            this.f50a.get(i).onValueChanged();
        }
    }

    /* renamed from: a */
    public ok<K> mo98a() {
        ok<K> okVar = this.f55f;
        if (okVar != null && okVar.a(this.f53d)) {
            return this.f55f;
        }
        List<? extends ok<K>> list = this.f52c;
        ok<K> okVar2 = (ok) list.get(list.size() - 1);
        if (this.f53d < okVar2.b()) {
            int size = this.f52c.size();
            do {
                size--;
                if (size < 0) {
                    break;
                }
                okVar2 = (ok) this.f52c.get(size);
            } while (!okVar2.a(this.f53d));
        }
        this.f55f = okVar2;
        return okVar2;
    }

    /* renamed from: a */
    public void mo100a(qk<A> qkVar) {
        qk<A> qkVar2 = this.f54e;
        this.f54e = qkVar;
    }
}
