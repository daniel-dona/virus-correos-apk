package com.facebook.jni;

import java.util.Iterator;
import java.util.Map;

@Qw
/* compiled from: PG */
public class MapIteratorHelper {
    @Qw
    public final Iterator<Map.Entry> mIterator;
    @Qw
    public Object mKey;
    @Qw
    public Object mValue;

    @Qw
    public MapIteratorHelper(Map map) {
        this.mIterator = map.entrySet().iterator();
    }

    @Qw
    public boolean hasNext() {
        if (this.mIterator.hasNext()) {
            Map.Entry next = this.mIterator.next();
            this.mKey = next.getKey();
            this.mValue = next.getValue();
            return true;
        }
        this.mKey = null;
        this.mValue = null;
        return false;
    }
}
