package com.google.firebase.iid;

/* compiled from: PG */
public final class zzaf extends Exception {
    public final int errorCode;

    public zzaf(int i, String str) {
        super(str);
        this.errorCode = i;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
