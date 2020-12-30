package com.facebook.drawee.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;

/* compiled from: PG */
public class SimpleDraweeView extends GenericDraweeView {

    /* renamed from: p */
    public static mq<? extends AbstractDraweeControllerBuilder> f323p;

    /* renamed from: n */
    public AbstractDraweeControllerBuilder f324n;

    public SimpleDraweeView(Context context) {
        super(context);
        mo432b(context, (AttributeSet) null);
    }

    /* renamed from: b */
    public final void mo432b(Context context, AttributeSet attributeSet) {
        int resourceId;
        if (!isInEditMode()) {
            kq.a(f323p, "SimpleDraweeView was not initialized!");
            this.f324n = (AbstractDraweeControllerBuilder) f323p.get();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yx0.SimpleDraweeView);
                try {
                    if (obtainStyledAttributes.hasValue(yx0.SimpleDraweeView_actualImageUri)) {
                        setImageURI(Uri.parse(obtainStyledAttributes.getString(yx0.SimpleDraweeView_actualImageUri)), (Object) null);
                    } else if (obtainStyledAttributes.hasValue(yx0.SimpleDraweeView_actualImageResource) && (resourceId = obtainStyledAttributes.getResourceId(yx0.SimpleDraweeView_actualImageResource, -1)) != -1) {
                        setActualImageResource(resourceId);
                    }
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
        }
    }

    public void setActualImageResource(int i) {
        setActualImageResource(i, (Object) null);
    }

    public void setImageRequest(ImageRequest imageRequest) {
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = this.f324n;
        abstractDraweeControllerBuilder.f268d = imageRequest;
        abstractDraweeControllerBuilder.f276l = mo410c();
        setController(abstractDraweeControllerBuilder.mo361a());
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
    }

    public void setImageURI(Uri uri) {
        setImageURI(uri, (Object) null);
    }

    public void setActualImageResource(int i, Object obj) {
        setImageURI(Oq.a(i), obj);
    }

    public void setImageURI(String str) {
        setImageURI(str, (Object) null);
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo432b(context, attributeSet);
    }

    public void setImageURI(Uri uri, Object obj) {
        kr krVar = this.f324n;
        krVar.f267c = obj;
        kr krVar2 = krVar;
        krVar2.a(uri);
        krVar2.f276l = mo410c();
        setController(krVar2.mo361a());
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo432b(context, attributeSet);
    }

    public void setImageURI(String str, Object obj) {
        setImageURI(str != null ? Uri.parse(str) : null, obj);
    }
}
