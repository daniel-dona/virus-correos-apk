package com.facebook.imagepipeline.decoder;

/* compiled from: PG */
public class DecodeException extends RuntimeException {
    public final Xt mEncodedImage;

    public DecodeException(String str, Xt xt) {
        super(str);
        this.mEncodedImage = xt;
    }

    public Xt getEncodedImage() {
        return this.mEncodedImage;
    }

    public DecodeException(String str, Throwable th, Xt xt) {
        super(str, th);
        this.mEncodedImage = xt;
    }
}
