package com.facebook.imageformat;

/* compiled from: PG */
public class ImageFormat {

    /* renamed from: b */
    public static final ImageFormat f325b = new ImageFormat("UNKNOWN", (String) null);

    /* renamed from: a */
    public final String f326a;

    /* compiled from: PG */
    public interface FormatChecker {
        ImageFormat determineFormat(byte[] bArr, int i);

        int getHeaderSize();
    }

    public ImageFormat(String str, String str2) {
        this.f326a = str;
    }

    public String toString() {
        return this.f326a;
    }
}
