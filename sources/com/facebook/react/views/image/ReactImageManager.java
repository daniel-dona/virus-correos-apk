package com.facebook.react.views.image;

import android.graphics.PorterDuff;
import android.graphics.Shader;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import java.util.Map;

@Ay(name = "RCTImageView")
/* compiled from: PG */
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final String REACT_CLASS = "RCTImageView";
    public final Object mCallerContext;
    public AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    public WC mGlobalImageLoadListener;

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this(abstractDraweeControllerBuilder, (WC) null, obj);
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = ir.b();
        }
        return this.mDraweeControllerBuilder;
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return hy.a(XC.b(4), hy.a("registrationName", "onLoadStart"), XC.b(2), hy.a("registrationName", "onLoad"), XC.b(1), hy.a("registrationName", "onError"), XC.b(3), hy.a("registrationName", "onLoadEnd"));
    }

    public String getName() {
        return REACT_CLASS;
    }

    @eC(name = "blurRadius")
    public void setBlurRadius(ReactImageView reactImageView, float f) {
        reactImageView.setBlurRadius(f);
    }

    @eC(customType = "Color", name = "borderColor")
    public void setBorderColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.setBorderColor(0);
        } else {
            reactImageView.setBorderColor(num.intValue());
        }
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactImageView reactImageView, int i, float f) {
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactImageView.setBorderRadius(f);
        } else {
            reactImageView.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderWidth")
    public void setBorderWidth(ReactImageView reactImageView, float f) {
        reactImageView.setBorderWidth(f);
    }

    @eC(name = "defaultSrc")
    public void setDefaultSource(ReactImageView reactImageView, String str) {
        reactImageView.setDefaultSource(str);
    }

    @eC(name = "fadeDuration")
    public void setFadeDuration(ReactImageView reactImageView, int i) {
        reactImageView.setFadeDuration(i);
    }

    @eC(name = "headers")
    public void setHeaders(ReactImageView reactImageView, ReadableMap readableMap) {
        reactImageView.setHeaders(readableMap);
    }

    @eC(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView reactImageView, boolean z) {
        reactImageView.setShouldNotifyLoadEvents(z);
    }

    @eC(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView reactImageView, String str) {
        reactImageView.setLoadingIndicatorSource(str);
    }

    @eC(customType = "Color", name = "overlayColor")
    public void setOverlayColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.setOverlayColor(0);
        } else {
            reactImageView.setOverlayColor(num.intValue());
        }
    }

    @eC(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView reactImageView, boolean z) {
        reactImageView.setProgressiveRenderingEnabled(z);
    }

    @eC(name = "resizeMethod")
    public void setResizeMethod(ReactImageView reactImageView, String str) {
        if (str == null || "auto".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.AUTO);
        } else if ("resize".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.RESIZE);
        } else if ("scale".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.SCALE);
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.b("Invalid resize method: '", str, "'"));
        }
    }

    @eC(name = "resizeMode")
    public void setResizeMode(ReactImageView reactImageView, String str) {
        ScalingUtils.ScaleType scaleType;
        Shader.TileMode tileMode;
        if ("contain".equals(str)) {
            scaleType = ScalingUtils.ScaleType.FIT_CENTER;
        } else if ("cover".equals(str)) {
            scaleType = ScalingUtils.ScaleType.CENTER_CROP;
        } else if ("stretch".equals(str)) {
            scaleType = ScalingUtils.ScaleType.FIT_XY;
        } else if ("center".equals(str)) {
            scaleType = ScalingUtils.ScaleType.CENTER_INSIDE;
        } else if ("repeat".equals(str)) {
            scaleType = aD.a;
        } else if (str == null) {
            scaleType = YC.b();
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.b("Invalid resize mode: '", str, "'"));
        }
        reactImageView.setScaleType(scaleType);
        if ("contain".equals(str) || "cover".equals(str) || "stretch".equals(str) || "center".equals(str)) {
            tileMode = Shader.TileMode.CLAMP;
        } else if ("repeat".equals(str)) {
            tileMode = Shader.TileMode.REPEAT;
        } else if (str == null) {
            tileMode = YC.a();
        } else {
            throw new JSApplicationIllegalArgumentException(Eo.b("Invalid resize mode: '", str, "'"));
        }
        reactImageView.setTileMode(tileMode);
    }

    @eC(name = "src")
    public void setSource(ReactImageView reactImageView, ReadableArray readableArray) {
        reactImageView.setSource(readableArray);
    }

    @eC(customType = "Color", name = "tintColor")
    public void setTintColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.clearColorFilter();
        } else {
            reactImageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, WC wc, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    public ReactImageView createViewInstance(WA wa) {
        return new ReactImageView(wa, getDraweeControllerBuilder(), getCallerContext());
    }

    public void onAfterUpdateTransaction(ReactImageView reactImageView) {
        super.onAfterUpdateTransaction(reactImageView);
        reactImageView.mo1252j();
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }
}
