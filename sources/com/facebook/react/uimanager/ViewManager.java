package com.facebook.react.uimanager;

import NA;
import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;

/* compiled from: PG */
public abstract class ViewManager<T extends View, C extends NA> extends BaseJavaModule {
    public void addEventEmitters(WA wa, T t) {
    }

    public C createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    public final T createView(WA wa, hA hAVar) {
        T createViewInstance = createViewInstance(wa);
        addEventEmitters(wa, createViewInstance);
        if (createViewInstance instanceof kA) {
            ((kA) createViewInstance).setOnInterceptTouchEventListener(hAVar);
        }
        return createViewInstance;
    }

    public abstract T createViewInstance(WA wa);

    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public abstract String getName();

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.m534a((Class<? extends ViewManager>) getClass(), (Class<? extends NA>) getShadowNodeClass());
    }

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        return 0;
    }

    public void onAfterUpdateTransaction(T t) {
    }

    public void onDropViewInstance(T t) {
    }

    public void receiveCommand(T t, int i, ReadableArray readableArray) {
    }

    public abstract void updateExtraData(T t, Object obj);

    public Object updateLocalData(T t, PA pa, PA pa2) {
        return null;
    }

    public final void updateProperties(T t, PA pa) {
        ViewManagerPropertyUpdater.m537a(this, t, pa);
        onAfterUpdateTransaction(t);
    }

    public C createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        return createShadowNodeInstance();
    }
}
