package com.facebook.react.devsupport;

@Qw
/* compiled from: PG */
public class JSException extends Exception {
    public final String mStack;

    @Qw
    public JSException(String str, String str2, Throwable th) {
        super(str, th);
        this.mStack = str2;
    }

    public String getStack() {
        return this.mStack;
    }

    public JSException(String str, String str2) {
        super(str);
        this.mStack = str2;
    }
}
