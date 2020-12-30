package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ViewGroupManager;
import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import java.util.Locale;
import java.util.Map;

@Ay(name = "RCTView")
/* compiled from: PG */
public class ReactViewManager extends ViewGroupManager<ReactViewGroup> {
    public static final int CMD_HOTSPOT_UPDATE = 1;
    public static final int CMD_SET_PRESSED = 2;
    public static final String REACT_CLASS = "RCTView";
    public static final int[] SPACING_TYPES = {8, 0, 2, 1, 3, 4, 5};

    public Map<String, Integer> getCommandsMap() {
        return hy.a("hotspotUpdate", 1, "setPressed", 2);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @eC(name = "accessible")
    public void setAccessible(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setFocusable(z);
    }

    @eC(name = "backfaceVisibility")
    public void setBackfaceVisibility(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setBackfaceVisibility(str);
    }

    @fC(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor", "borderStartColor", "borderEndColor"})
    public void setBorderColor(ReactViewGroup reactViewGroup, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactViewGroup.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius", "borderTopStartRadius", "borderTopEndRadius", "borderBottomStartRadius", "borderBottomEndRadius"})
    public void setBorderRadius(ReactViewGroup reactViewGroup, int i, float f) {
        if (!eF.a(f) && f < BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            f = Float.NaN;
        }
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        if (i == 0) {
            reactViewGroup.setBorderRadius(f);
        } else {
            reactViewGroup.setBorderRadius(f, i - 1);
        }
    }

    @eC(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setBorderStyle(str);
    }

    @fC(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth", "borderStartWidth", "borderEndWidth"})
    public void setBorderWidth(ReactViewGroup reactViewGroup, int i, float f) {
        if (!eF.a(f) && f < BaseViewManager.CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
            f = Float.NaN;
        }
        if (!eF.a(f)) {
            f = GA.b(f);
        }
        reactViewGroup.setBorderWidth(SPACING_TYPES[i], f);
    }

    @eC(name = "collapsable")
    public void setCollapsable(ReactViewGroup reactViewGroup, boolean z) {
    }

    @eC(name = "hitSlop")
    public void setHitSlop(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        if (readableMap == null) {
            reactViewGroup.setHitSlopRect((Rect) null);
            return;
        }
        int i = 0;
        int a = readableMap.hasKey("left") ? (int) GA.a(readableMap.getDouble("left")) : 0;
        int a2 = readableMap.hasKey("top") ? (int) GA.a(readableMap.getDouble("top")) : 0;
        int a3 = readableMap.hasKey("right") ? (int) GA.a(readableMap.getDouble("right")) : 0;
        if (readableMap.hasKey("bottom")) {
            i = (int) GA.a(readableMap.getDouble("bottom"));
        }
        reactViewGroup.setHitSlopRect(new Rect(a, a2, a3, i));
    }

    @eC(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        reactViewGroup.setTranslucentBackgroundDrawable(readableMap == null ? null : pE.a(reactViewGroup.getContext(), readableMap));
    }

    @TargetApi(23)
    @eC(name = "nativeForegroundAndroid")
    public void setNativeForeground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        reactViewGroup.setForeground(readableMap == null ? null : pE.a(reactViewGroup.getContext(), readableMap));
    }

    @eC(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setNeedsOffscreenAlphaCompositing(z);
    }

    @eC(name = "overflow")
    public void setOverflow(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setOverflow(str);
    }

    @eC(name = "pointerEvents")
    public void setPointerEvents(ReactViewGroup reactViewGroup, String str) {
        if (str == null) {
            reactViewGroup.mo1724a(PointerEvents.AUTO);
        } else {
            reactViewGroup.mo1724a(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace(CacheKeyValueDelegate.CACHE_VALUE_SEPARATOR, "_")));
        }
    }

    @eC(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setRemoveClippedSubviews(z);
    }

    @eC(name = "hasTVPreferredFocus")
    public void setTVPreferredFocus(ReactViewGroup reactViewGroup, boolean z) {
        if (z) {
            reactViewGroup.setFocusable(true);
            reactViewGroup.setFocusableInTouchMode(true);
            reactViewGroup.requestFocus();
        }
    }

    public void addView(ReactViewGroup reactViewGroup, View view, int i) {
        if (reactViewGroup.mo1725a()) {
            reactViewGroup.mo1723a(view, i);
        } else {
            reactViewGroup.addView(view, i);
        }
    }

    public ReactViewGroup createViewInstance(WA wa) {
        return new ReactViewGroup(wa);
    }

    public View getChildAt(ReactViewGroup reactViewGroup, int i) {
        if (reactViewGroup.mo1725a()) {
            return reactViewGroup.mo1728b(i);
        }
        return reactViewGroup.getChildAt(i);
    }

    public int getChildCount(ReactViewGroup reactViewGroup) {
        if (reactViewGroup.mo1725a()) {
            return reactViewGroup.mo1737f();
        }
        return reactViewGroup.getChildCount();
    }

    public void receiveCommand(ReactViewGroup reactViewGroup, int i, ReadableArray readableArray) {
        if (i != 1) {
            if (i == 2) {
                if (readableArray == null || readableArray.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                reactViewGroup.setPressed(readableArray.getBoolean(0));
            }
        } else if (readableArray == null || readableArray.size() != 2) {
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
        } else if (Build.VERSION.SDK_INT >= 21) {
            reactViewGroup.drawableHotspotChanged(GA.a(readableArray.getDouble(0)), GA.a(readableArray.getDouble(1)));
        }
    }

    public void removeAllViews(ReactViewGroup reactViewGroup) {
        if (reactViewGroup.mo1725a()) {
            reactViewGroup.mo1740h();
        } else {
            reactViewGroup.removeAllViews();
        }
    }

    public void removeViewAt(ReactViewGroup reactViewGroup, int i) {
        if (reactViewGroup.mo1725a()) {
            View childAt = getChildAt(reactViewGroup, i);
            if (childAt.getParent() != null) {
                reactViewGroup.removeView(childAt);
            }
            reactViewGroup.mo1722a(childAt);
            return;
        }
        reactViewGroup.removeViewAt(i);
    }

    public void setOpacity(ReactViewGroup reactViewGroup, float f) {
        reactViewGroup.setOpacityIfPossible(f);
    }

    public void setTransform(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        super.setTransform(reactViewGroup, readableArray);
        reactViewGroup.setBackfaceVisibilityDependantOpacity();
    }
}
