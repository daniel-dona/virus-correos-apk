package com.microsoft.bing.visualsearch.camerasearchv2.content.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* compiled from: PG */
public @interface OCRItem$OCRActionType {
    public static final String OCR_ADDRESS = "address";
    public static final String OCR_EMAIL = "mailto";
    public static final String OCR_NONE = "none";
    public static final String OCR_OTHERS = "others";
    public static final String OCR_PHONE = "tel";
    public static final String OCR_WEBSITE = "website";
}
