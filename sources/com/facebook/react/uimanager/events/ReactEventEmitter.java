package com.facebook.react.uimanager.events;

import android.util.SparseArray;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/* compiled from: PG */
public class ReactEventEmitter implements RCTEventEmitter {
    public static final String TAG = "ReactEventEmitter";
    public final SparseArray<RCTEventEmitter> mEventEmitters = new SparseArray<>();
    public final ReactApplicationContext mReactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
    }

    private RCTEventEmitter getEventEmitter(int i) {
        int i2 = 2;
        if (i % 2 != 0) {
            i2 = 1;
        }
        RCTEventEmitter rCTEventEmitter = this.mEventEmitters.get(i2);
        return rCTEventEmitter == null ? (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class) : rCTEventEmitter;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        getEventEmitter(i).receiveEvent(i, str, writableMap);
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Kw.a(writableArray.size() > 0);
        getEventEmitter(writableArray.getMap(0).getInt("target")).receiveTouches(str, writableArray, writableArray2);
    }

    public void register(int i, RCTEventEmitter rCTEventEmitter) {
        this.mEventEmitters.put(i, rCTEventEmitter);
    }

    public void unregister(int i) {
        this.mEventEmitters.remove(i);
    }
}
