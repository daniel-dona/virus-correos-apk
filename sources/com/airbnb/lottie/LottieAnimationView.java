package com.airbnb.lottie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
public class LottieAnimationView extends AppCompatImageView {

    /* renamed from: r3 */
    public static final String f25r3 = LottieAnimationView.class.getSimpleName();

    /* renamed from: a */
    public final Wh<vh> f26a = new C0001a();

    /* renamed from: b */
    public final Wh<Throwable> f27b = new C0002b(this);

    /* renamed from: c */
    public final Uh f28c = new Uh();

    /* renamed from: d */
    public String f29d;

    /* renamed from: e */
    public int f30e;

    /* renamed from: k */
    public boolean f31k = false;

    /* renamed from: n */
    public boolean f32n = false;

    /* renamed from: p */
    public boolean f33p = false;

    /* renamed from: q */
    public RenderMode f34q = RenderMode.AUTOMATIC;

    /* renamed from: q3 */
    public vh f35q3;

    /* renamed from: x */
    public Set<Yh> f36x = new HashSet();

    /* renamed from: y */
    public ci<vh> f37y;

    /* compiled from: PG */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0000a();

        /* renamed from: a */
        public String f38a;

        /* renamed from: b */
        public int f39b;

        /* renamed from: c */
        public float f40c;

        /* renamed from: d */
        public boolean f41d;

        /* renamed from: e */
        public String f42e;

        /* renamed from: k */
        public int f43k;

        /* renamed from: n */
        public int f44n;

        /* renamed from: com.airbnb.lottie.LottieAnimationView$SavedState$a */
        /* compiled from: PG */
        public static class C0000a implements Parcelable.Creator<SavedState> {
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (C0001a) null);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f38a);
            parcel.writeFloat(this.f40c);
            parcel.writeInt(this.f41d ? 1 : 0);
            parcel.writeString(this.f42e);
            parcel.writeInt(this.f43k);
            parcel.writeInt(this.f44n);
        }

        public /* synthetic */ SavedState(Parcel parcel, C0001a aVar) {
            super(parcel);
            this.f38a = parcel.readString();
            this.f40c = parcel.readFloat();
            this.f41d = parcel.readInt() != 1 ? false : true;
            this.f42e = parcel.readString();
            this.f43k = parcel.readInt();
            this.f44n = parcel.readInt();
        }
    }

    /* renamed from: com.airbnb.lottie.LottieAnimationView$a */
    /* compiled from: PG */
    public class C0001a implements Wh<vh> {
        public C0001a() {
        }

        public void onResult(Object obj) {
            LottieAnimationView.this.setComposition((vh) obj);
        }
    }

    /* renamed from: com.airbnb.lottie.LottieAnimationView$b */
    /* compiled from: PG */
    public class C0002b implements Wh<Throwable> {
        public C0002b(LottieAnimationView lottieAnimationView) {
        }

        public void onResult(Object obj) {
            throw new IllegalStateException("Unable to parse composition", (Throwable) obj);
        }
    }

    /* renamed from: com.airbnb.lottie.LottieAnimationView$c */
    /* compiled from: PG */
    public class C0003c extends qk<T> {

        /* renamed from: c */
        public final /* synthetic */ sk f46c;

        public C0003c(LottieAnimationView lottieAnimationView, sk skVar) {
            this.f46c = skVar;
        }

        /* renamed from: a */
        public T mo95a(pk<T> pkVar) {
            return new PorterDuffColorFilter(this.f46c.a, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        mo42a((AttributeSet) null);
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    /* renamed from: a */
    public final void mo42a(AttributeSet attributeSet) {
        String string;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, yx0.LottieAnimationView);
        boolean z = false;
        if (!isInEditMode()) {
            boolean hasValue = obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_rawRes);
            boolean hasValue2 = obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_fileName);
            boolean hasValue3 = obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_url);
            if (hasValue && hasValue2) {
                throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
            } else if (hasValue) {
                int resourceId = obtainStyledAttributes.getResourceId(yx0.LottieAnimationView_lottie_rawRes, 0);
                if (resourceId != 0) {
                    setAnimation(resourceId);
                }
            } else if (hasValue2) {
                String string2 = obtainStyledAttributes.getString(yx0.LottieAnimationView_lottie_fileName);
                if (string2 != null) {
                    setAnimation(string2);
                }
            } else if (hasValue3 && (string = obtainStyledAttributes.getString(yx0.LottieAnimationView_lottie_url)) != null) {
                setAnimationFromUrl(string);
            }
        }
        if (obtainStyledAttributes.getBoolean(yx0.LottieAnimationView_lottie_autoPlay, false)) {
            this.f32n = true;
            this.f33p = true;
        }
        if (obtainStyledAttributes.getBoolean(yx0.LottieAnimationView_lottie_loop, false)) {
            this.f28c.c.setRepeatCount(-1);
        }
        if (obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_repeatMode)) {
            setRepeatMode(obtainStyledAttributes.getInt(yx0.LottieAnimationView_lottie_repeatMode, 1));
        }
        if (obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_repeatCount)) {
            setRepeatCount(obtainStyledAttributes.getInt(yx0.LottieAnimationView_lottie_repeatCount, -1));
        }
        if (obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_speed)) {
            setSpeed(obtainStyledAttributes.getFloat(yx0.LottieAnimationView_lottie_speed, 1.0f));
        }
        setImageAssetsFolder(obtainStyledAttributes.getString(yx0.LottieAnimationView_lottie_imageAssetsFolder));
        setProgress(obtainStyledAttributes.getFloat(yx0.LottieAnimationView_lottie_progress, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        mo44a(obtainStyledAttributes.getBoolean(yx0.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        if (obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_colorFilter)) {
            di diVar = new di(obtainStyledAttributes.getColor(yx0.LottieAnimationView_lottie_colorFilter, 0));
            mo40a(new Vi(new String[]{"**"}), Zh.B, new qk(diVar));
        }
        if (obtainStyledAttributes.hasValue(yx0.LottieAnimationView_lottie_scale)) {
            Uh uh = this.f28c;
            uh.d = obtainStyledAttributes.getFloat(yx0.LottieAnimationView_lottie_scale, 1.0f);
            uh.f();
        }
        obtainStyledAttributes.recycle();
        Uh uh2 = this.f28c;
        if (nk.a(getContext()) != BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            z = true;
        }
        uh2.a(Boolean.valueOf(z));
        mo48f();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void buildDrawingCache(boolean z) {
        LottieAnimationView.super.buildDrawingCache(z);
        if (getLayerType() == 1 && getDrawingCache(z) == null) {
            setRenderMode(RenderMode.HARDWARE);
        }
    }

    /* renamed from: d */
    public void mo46d() {
        this.f31k = false;
        Uh uh = this.f28c;
        uh.k.clear();
        uh.c.cancel();
        mo48f();
    }

    /* renamed from: e */
    public final void mo47e() {
        ci<vh> ciVar = this.f37y;
        if (ciVar != null) {
            ciVar.d(this.f26a);
            this.f37y.c(this.f27b);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    /* renamed from: f */
    public final void mo48f() {
        vh vhVar;
        int ordinal = this.f34q.ordinal();
        int i = 2;
        if (ordinal == 0) {
            vh vhVar2 = this.f35q3;
            boolean z = false;
            if ((vhVar2 == null || !vhVar2.n || Build.VERSION.SDK_INT >= 28) && ((vhVar = this.f35q3) == null || vhVar.o <= 4)) {
                z = true;
            }
            if (!z) {
                i = 1;
            }
            setLayerType(i, (Paint) null);
        } else if (ordinal == 1) {
            setLayerType(2, (Paint) null);
        } else if (ordinal == 2) {
            setLayerType(1, (Paint) null);
        }
    }

    /* renamed from: g */
    public boolean mo49g() {
        return this.f28c.c.y;
    }

    /* renamed from: h */
    public void mo50h() {
        this.f32n = false;
        this.f31k = false;
        Uh uh = this.f28c;
        uh.k.clear();
        uh.c.b(true);
        mo48f();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    /* renamed from: i */
    public void mo51i() {
        if (isShown()) {
            this.f28c.d();
            mo48f();
            return;
        }
        this.f31k = true;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void invalidateDrawable(Drawable drawable) {
        Uh drawable2 = getDrawable();
        Uh uh = this.f28c;
        if (drawable2 == uh) {
            LottieAnimationView.super.invalidateDrawable(uh);
        } else {
            LottieAnimationView.super.invalidateDrawable(drawable);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    /* renamed from: j */
    public void mo53j() {
        if (isShown()) {
            this.f28c.e();
            mo48f();
            return;
        }
        this.f31k = true;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void onAttachedToWindow() {
        LottieAnimationView.super.onAttachedToWindow();
        if (this.f33p && this.f32n) {
            mo51i();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void onDetachedFromWindow() {
        if (mo49g()) {
            mo46d();
            this.f32n = true;
        }
        LottieAnimationView.super.onDetachedFromWindow();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            LottieAnimationView.super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        LottieAnimationView.super.onRestoreInstanceState(savedState.getSuperState());
        this.f29d = savedState.f38a;
        if (!TextUtils.isEmpty(this.f29d)) {
            setAnimation(this.f29d);
        }
        this.f30e = savedState.f39b;
        int i = this.f30e;
        if (i != 0) {
            setAnimation(i);
        }
        setProgress(savedState.f40c);
        if (savedState.f41d) {
            mo51i();
        }
        this.f28c.p = savedState.f42e;
        setRepeatMode(savedState.f43k);
        setRepeatCount(savedState.f44n);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(LottieAnimationView.super.onSaveInstanceState());
        savedState.f38a = this.f29d;
        savedState.f39b = this.f30e;
        savedState.f40c = this.f28c.c.b();
        Uh uh = this.f28c;
        kk kkVar = uh.c;
        savedState.f41d = kkVar.y;
        savedState.f42e = uh.p;
        savedState.f43k = kkVar.getRepeatMode();
        savedState.f44n = this.f28c.c.getRepeatCount();
        return savedState;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void onVisibilityChanged(View view, int i) {
        if (this.f28c != null) {
            if (isShown()) {
                if (this.f31k) {
                    mo53j();
                    this.f31k = false;
                }
            } else if (mo49g()) {
                mo50h();
                this.f31k = true;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void setAnimation(int i) {
        this.f30e = i;
        this.f29d = null;
        mo43a((ci<vh>) Dh.a(getContext(), i));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        setAnimationFromJson(str, (String) null);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void setAnimationFromUrl(String str) {
        mo43a((ci<vh>) Dh.c(getContext(), str));
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.airbnb.lottie.LottieAnimationView, android.graphics.drawable.Drawable$Callback, android.widget.ImageView] */
    public void setComposition(vh vhVar) {
        this.f28c.setCallback(this);
        this.f35q3 = vhVar;
        Uh uh = this.f28c;
        boolean z = false;
        if (uh.b != vhVar) {
            uh.s3 = false;
            uh.b();
            uh.b = vhVar;
            uh.a();
            kk kkVar = uh.c;
            if (kkVar.x == null) {
                z = true;
            }
            kkVar.x = vhVar;
            if (z) {
                kkVar.a((float) ((int) Math.max(kkVar.p, vhVar.k)), (float) ((int) Math.min(kkVar.q, vhVar.l)));
            } else {
                kkVar.a((float) ((int) vhVar.k), (float) ((int) vhVar.l));
            }
            float f = kkVar.k;
            kkVar.k = BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
            kkVar.a((int) f);
            uh.c(uh.c.getAnimatedFraction());
            uh.d = uh.d;
            uh.f();
            uh.f();
            Iterator it = new ArrayList(uh.k).iterator();
            while (it.hasNext()) {
                ((Th) it.next()).a(vhVar);
                it.remove();
            }
            uh.k.clear();
            vhVar.b(uh.r3);
            z = true;
        }
        mo48f();
        if (getDrawable() != this.f28c || z) {
            setImageDrawable((Drawable) null);
            setImageDrawable(this.f28c);
            requestLayout();
            for (Yh a : this.f36x) {
                a.a(vhVar);
            }
        }
    }

    public void setFontAssetDelegate(sh shVar) {
        Qi qi = this.f28c.q;
        if (qi != null) {
            qi.a(shVar);
        }
    }

    public void setFrame(int i) {
        this.f28c.a(i);
    }

    public void setImageAssetDelegate(th thVar) {
        Ri ri = this.f28c.n;
        if (ri != null) {
            ri.a(thVar);
        }
    }

    public void setImageAssetsFolder(String str) {
        this.f28c.p = str;
    }

    public void setImageBitmap(Bitmap bitmap) {
        mo47e();
        LottieAnimationView.super.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable) {
        mo47e();
        LottieAnimationView.super.setImageDrawable(drawable);
    }

    public void setImageResource(int i) {
        mo47e();
        LottieAnimationView.super.setImageResource(i);
    }

    public void setMaxFrame(int i) {
        this.f28c.b(i);
    }

    public void setMaxProgress(float f) {
        this.f28c.a(f);
    }

    public void setMinAndMaxFrame(String str) {
        this.f28c.b(str);
    }

    public void setMinAndMaxProgress(float f, float f2) {
        this.f28c.a(f, f2);
    }

    public void setMinFrame(int i) {
        this.f28c.c(i);
    }

    public void setMinProgress(float f) {
        this.f28c.b(f);
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        Uh uh = this.f28c;
        uh.r3 = z;
        vh vhVar = uh.b;
        if (vhVar != null) {
            vhVar.b(z);
        }
    }

    public void setProgress(float f) {
        this.f28c.c(f);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.f34q = renderMode;
        mo48f();
    }

    public void setRepeatCount(int i) {
        this.f28c.c.setRepeatCount(i);
    }

    public void setRepeatMode(int i) {
        this.f28c.c.setRepeatMode(i);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void setScale(float f) {
        Uh uh = this.f28c;
        uh.d = f;
        uh.f();
        if (getDrawable() == this.f28c) {
            setImageDrawable((Drawable) null);
            setImageDrawable(this.f28c);
        }
    }

    public void setSpeed(float f) {
        this.f28c.c.a(f);
    }

    public void setTextDelegate(ei eiVar) {
        this.f28c.a(eiVar);
    }

    public void setAnimationFromJson(String str, String str2) {
        setAnimation(new JsonReader(new StringReader(str)), str2);
    }

    public void setMaxFrame(String str) {
        this.f28c.a(str);
    }

    public void setMinAndMaxFrame(int i, int i2) {
        this.f28c.a(i, i2);
    }

    public void setMinFrame(String str) {
        this.f28c.c(str);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.airbnb.lottie.LottieAnimationView, android.widget.ImageView] */
    public void setAnimation(String str) {
        this.f29d = str;
        this.f30e = 0;
        mo43a((ci<vh>) Dh.a(getContext(), str));
    }

    public void setAnimation(JsonReader jsonReader, String str) {
        mo43a((ci<vh>) Dh.a(jsonReader, str));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo42a(attributeSet);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo42a(attributeSet);
    }

    /* renamed from: a */
    public void mo44a(boolean z) {
        Uh uh = this.f28c;
        if (uh.x != z) {
            uh.x = z;
            if (uh.b != null) {
                uh.a();
            }
        }
    }

    /* renamed from: a */
    public <T> void mo40a(Vi vi, T t, qk<T> qkVar) {
        this.f28c.a(vi, t, qkVar);
    }

    /* renamed from: a */
    public <T> void mo41a(Vi vi, T t, sk<T> skVar) {
        this.f28c.a(vi, t, new C0003c(this, skVar));
    }

    /* renamed from: a */
    public final void mo43a(ci<vh> ciVar) {
        this.f35q3 = null;
        this.f28c.b();
        mo47e();
        ciVar.b(this.f26a);
        ciVar.a(this.f27b);
        this.f37y = ciVar;
    }
}
