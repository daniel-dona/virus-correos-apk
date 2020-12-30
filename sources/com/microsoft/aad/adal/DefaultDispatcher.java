package com.microsoft.aad.adal;

import android.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class DefaultDispatcher {
    public final IDispatcher mDispatcher;
    public final Map<String, List<IEvents>> mObjectsToBeDispatched;

    public DefaultDispatcher() {
        this.mObjectsToBeDispatched = new HashMap();
        this.mDispatcher = null;
    }

    public synchronized void flush(String str) {
    }

    public IDispatcher getDispatcher() {
        return this.mDispatcher;
    }

    public Map<String, List<IEvents>> getObjectsToBeDispatched() {
        return this.mObjectsToBeDispatched;
    }

    public void receive(String str, IEvents iEvents) {
        if (this.mDispatcher != null) {
            HashMap hashMap = new HashMap();
            for (Pair next : iEvents.getEvents()) {
                hashMap.put(next.first, next.second);
            }
            this.mDispatcher.dispatchEvent(hashMap);
        }
    }

    public DefaultDispatcher(IDispatcher iDispatcher) {
        this.mObjectsToBeDispatched = new HashMap();
        this.mDispatcher = iDispatcher;
    }
}
