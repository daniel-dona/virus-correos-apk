package com.facebook.imagepipeline.nativecode;

import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@gq
/* compiled from: PG */
public class WebpTranscoderImpl implements Du {
    @gq
    public static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i) throws IOException;

    @gq
    public static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException;

    /* renamed from: a */
    public boolean mo532a(ImageFormat imageFormat) {
        if (imageFormat == qs.e) {
            return true;
        }
        if (imageFormat == qs.f || imageFormat == qs.g || imageFormat == qs.h) {
            return Qq.b;
        }
        if (imageFormat == qs.i) {
            return false;
        }
        throw new IllegalArgumentException("Image format is not a WebP.");
    }

    /* renamed from: a */
    public void mo531a(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        Cu.a();
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            nativeTranscodeWebpToJpeg(inputStream, outputStream, i);
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public void mo530a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Cu.a();
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            nativeTranscodeWebpToPng(inputStream, outputStream);
        } else {
            throw new NullPointerException();
        }
    }
}
