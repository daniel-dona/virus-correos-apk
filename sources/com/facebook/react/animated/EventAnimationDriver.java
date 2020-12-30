package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;

/* compiled from: PG */
public class EventAnimationDriver implements RCTEventEmitter {
    public List<String> mEventPath;
    public dy mValueNode;

    public EventAnimationDriver(List<String> list, dy dyVar) {
        this.mEventPath = list;
        this.mValueNode = dyVar;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        if (writableMap != null) {
            int i2 = 0;
            ReadableMap readableMap = writableMap;
            while (i2 < this.mEventPath.size() - 1) {
                i2++;
                readableMap = readableMap.getMap(this.mEventPath.get(i2));
            }
            dy dyVar = this.mValueNode;
            List<String> list = this.mEventPath;
            dyVar.e = readableMap.getDouble(list.get(list.size() - 1));
            return;
        }
        throw new IllegalArgumentException("Native animated events must have event data.");
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
