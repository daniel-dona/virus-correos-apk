package com.microsoft.bing.visualsearch.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* compiled from: PG */
public @interface PageType {
    public static final int AUTO = 1;
    public static final int BARCODE = 0;
    public static final int SHOPPING = 2;
}
