package com.facebook.imagepipeline.nativecode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@gq
/* compiled from: PG */
public class JpegTranscoder {
    static {
        Bu.a();
    }

    /* renamed from: a */
    public static void m383a(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException {
        boolean z = false;
        kq.a(i2 >= 1);
        kq.a(i2 <= 16);
        kq.a(i3 >= 0);
        kq.a(i3 <= 100);
        kq.a(i >= 0 && i <= 270 && i % 90 == 0);
        if (!(i2 == 8 && i == 0)) {
            z = true;
        }
        kq.a(z, "no transformation requested");
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            nativeTranscodeJpeg(inputStream, outputStream, i, i2, i3);
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: b */
    public static void m384b(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException {
        boolean z;
        boolean z2 = false;
        kq.a(i2 >= 1);
        kq.a(i2 <= 16);
        kq.a(i3 >= 0);
        kq.a(i3 <= 100);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        kq.a(z);
        if (!(i2 == 8 && i == 1)) {
            z2 = true;
        }
        kq.a(z2, "no transformation requested");
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            nativeTranscodeJpegWithExifOrientation(inputStream, outputStream, i, i2, i3);
        } else {
            throw new NullPointerException();
        }
    }

    @gq
    public static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @gq
    public static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;
}
