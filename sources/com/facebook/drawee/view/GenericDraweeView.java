package com.facebook.drawee.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.react.uimanager.BaseViewManager;

/* compiled from: PG */
public class GenericDraweeView extends DraweeView<es> {
    public GenericDraweeView(Context context, es esVar) {
        super(context);
        setHierarchy(esVar);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public void mo431a(Context context, AttributeSet attributeSet) {
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        boolean z4;
        int i2;
        boolean z5;
        boolean z6;
        boolean z7;
        int i3;
        int i4;
        boolean z8;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        fs fsVar = new fs(context.getResources());
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, yx0.GenericDraweeHierarchy);
            try {
                boolean z9 = true;
                int i5 = 0;
                int i6 = 0;
                boolean z10 = true;
                boolean z11 = true;
                boolean z12 = true;
                boolean z13 = true;
                boolean z14 = true;
                boolean z15 = true;
                boolean z16 = true;
                int i7 = 0;
                for (int indexCount = obtainStyledAttributes.getIndexCount(); i5 < indexCount; indexCount = i3) {
                    int index = obtainStyledAttributes.getIndex(i5);
                    if (index == yx0.GenericDraweeHierarchy_actualImageScaleType) {
                        fsVar.a(gs.a(obtainStyledAttributes, index));
                    } else if (index == yx0.GenericDraweeHierarchy_placeholderImage) {
                        fsVar.d = gs.a(context2, obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_pressedStateOverlayImage) {
                        fsVar.b(gs.a(context2, obtainStyledAttributes, index));
                    } else if (index == yx0.GenericDraweeHierarchy_progressBarImage) {
                        fsVar.c(gs.a(context2, obtainStyledAttributes, index));
                    } else if (index == yx0.GenericDraweeHierarchy_fadeDuration) {
                        fsVar.a(obtainStyledAttributes.getInt(index, 0));
                    } else if (index == yx0.GenericDraweeHierarchy_viewAspectRatio) {
                        fsVar.c = obtainStyledAttributes.getFloat(index, BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
                    } else if (index == yx0.GenericDraweeHierarchy_placeholderImageScaleType) {
                        fsVar.e = gs.a(obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_retryImage) {
                        fsVar.f = gs.a(context2, obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_retryImageScaleType) {
                        fsVar.g = gs.a(obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_failureImage) {
                        fsVar.h = gs.a(context2, obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_failureImageScaleType) {
                        fsVar.i = gs.a(obtainStyledAttributes, index);
                    } else if (index == yx0.GenericDraweeHierarchy_progressBarImageScaleType) {
                        fsVar.k = gs.a(obtainStyledAttributes, index);
                    } else {
                        if (index == yx0.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
                            i6 = obtainStyledAttributes.getInteger(index, i6);
                            i3 = indexCount;
                        } else if (index == yx0.GenericDraweeHierarchy_backgroundImage) {
                            fsVar.o = gs.a(context2, obtainStyledAttributes, index);
                        } else if (index == yx0.GenericDraweeHierarchy_overlayImage) {
                            fsVar.a(gs.a(context2, obtainStyledAttributes, index));
                        } else if (index == yx0.GenericDraweeHierarchy_roundAsCircle) {
                            if (fsVar.r == null) {
                                fsVar.a(new RoundingParams());
                            }
                            i3 = indexCount;
                            fsVar.r.f303b = obtainStyledAttributes.getBoolean(index, false);
                            z8 = z16;
                            i4 = i7;
                            i7 = i4;
                            z16 = z8;
                            i5++;
                            context2 = context;
                        } else {
                            i3 = indexCount;
                            if (index == yx0.GenericDraweeHierarchy_roundedCornerRadius) {
                                i7 = obtainStyledAttributes.getDimensionPixelSize(index, i7);
                            } else {
                                int i8 = i7;
                                if (index == yx0.GenericDraweeHierarchy_roundTopLeft) {
                                    z10 = obtainStyledAttributes.getBoolean(index, z10);
                                } else if (index == yx0.GenericDraweeHierarchy_roundTopRight) {
                                    z13 = obtainStyledAttributes.getBoolean(index, z13);
                                } else if (index == yx0.GenericDraweeHierarchy_roundBottomLeft) {
                                    z16 = obtainStyledAttributes.getBoolean(index, z16);
                                } else {
                                    z8 = z16;
                                    if (index == yx0.GenericDraweeHierarchy_roundBottomRight) {
                                        z16 = z8;
                                        z14 = obtainStyledAttributes.getBoolean(index, z14);
                                    } else if (index == yx0.GenericDraweeHierarchy_roundTopStart) {
                                        z16 = z8;
                                        z11 = obtainStyledAttributes.getBoolean(index, z11);
                                    } else if (index == yx0.GenericDraweeHierarchy_roundTopEnd) {
                                        z16 = z8;
                                        z12 = obtainStyledAttributes.getBoolean(index, z12);
                                    } else if (index == yx0.GenericDraweeHierarchy_roundBottomStart) {
                                        z16 = z8;
                                        z9 = obtainStyledAttributes.getBoolean(index, z9);
                                    } else if (index == yx0.GenericDraweeHierarchy_roundBottomEnd) {
                                        z16 = z8;
                                        z15 = obtainStyledAttributes.getBoolean(index, z15);
                                    } else {
                                        if (index == yx0.GenericDraweeHierarchy_roundWithOverlayColor) {
                                            if (fsVar.r == null) {
                                                fsVar.a(new RoundingParams());
                                            }
                                            i4 = i8;
                                            fsVar.r.mo398a(obtainStyledAttributes.getColor(index, 0));
                                        } else {
                                            i4 = i8;
                                            if (index == yx0.GenericDraweeHierarchy_roundingBorderWidth) {
                                                if (fsVar.r == null) {
                                                    fsVar.a(new RoundingParams());
                                                }
                                                fsVar.r.mo396a((float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
                                            } else if (index == yx0.GenericDraweeHierarchy_roundingBorderColor) {
                                                if (fsVar.r == null) {
                                                    fsVar.a(new RoundingParams());
                                                }
                                                fsVar.r.f307f = obtainStyledAttributes.getColor(index, 0);
                                            } else if (index == yx0.GenericDraweeHierarchy_roundingBorderPadding) {
                                                if (fsVar.r == null) {
                                                    fsVar.a(new RoundingParams());
                                                }
                                                fsVar.r.mo400c((float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
                                                i7 = i4;
                                                z16 = z8;
                                                i5++;
                                                context2 = context;
                                            }
                                        }
                                        i7 = i4;
                                        z16 = z8;
                                        i5++;
                                        context2 = context;
                                    }
                                }
                                i7 = i8;
                            }
                        }
                        i5++;
                        context2 = context;
                    }
                    i3 = indexCount;
                    z8 = z16;
                    i4 = i7;
                    i7 = i4;
                    z16 = z8;
                    i5++;
                    context2 = context;
                }
                boolean z17 = z16;
                int i9 = i7;
                boolean z18 = false;
                obtainStyledAttributes.recycle();
                if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
                    z4 = z10 && z12;
                    z6 = z13 && z11;
                    z7 = z14 && z9;
                    if (!z17 || !z15) {
                        z = z7;
                        z5 = z6;
                        z3 = z5;
                        i2 = i9;
                        z2 = z18;
                        i = i6;
                    }
                } else {
                    z4 = z10 && z11;
                    z6 = z13 && z12;
                    z = z14 && z15;
                    if (z17 && z9) {
                        z7 = z;
                    }
                    z5 = z6;
                    z3 = z5;
                    i2 = i9;
                    z2 = z18;
                    i = i6;
                }
                z = z7;
                z5 = z6;
                z18 = true;
                z3 = z5;
                i2 = i9;
                z2 = z18;
                i = i6;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                context.getResources().getConfiguration().getLayoutDirection();
                throw th;
            }
        } else {
            z3 = true;
            i = 0;
            i2 = 0;
            z4 = true;
            z2 = true;
            z = true;
        }
        Drawable drawable = fsVar.j;
        if (drawable != null && i > 0) {
            fsVar.c(new Mr(drawable, i));
        }
        if (i2 > 0) {
            if (fsVar.r == null) {
                fsVar.a(new RoundingParams());
            }
            fsVar.r.mo397a(z4 ? (float) i2 : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, z3 ? (float) i2 : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, z ? (float) i2 : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER, z2 ? (float) i2 : BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
        setAspectRatio(fsVar.c);
        setHierarchy(fsVar.a());
    }

    public GenericDraweeView(Context context) {
        super(context);
        mo431a(context, (AttributeSet) null);
    }

    public GenericDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo431a(context, attributeSet);
    }

    public GenericDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo431a(context, attributeSet);
    }
}
