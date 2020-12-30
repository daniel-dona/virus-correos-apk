package com.facebook.react.devsupport;

/* compiled from: PG */
public enum DevServerHelper$BundleType {
    BUNDLE("bundle"),
    DELTA("delta"),
    MAP("map");
    
    public final String mTypeID;

    /* access modifiers changed from: public */
    DevServerHelper$BundleType(String str) {
        this.mTypeID = str;
    }

    public String typeID() {
        return this.mTypeID;
    }
}
