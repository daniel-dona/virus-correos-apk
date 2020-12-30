package com.facebook.jni;

@Qw
/* compiled from: PG */
public class CppSystemErrorException extends CppException {
    public int errorCode;

    @Qw
    public CppSystemErrorException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
