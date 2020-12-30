package com.microsoft.bing.answerprovidersdk.api.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* compiled from: PG */
public @interface EntityType {
    public static final long CURRENCY = 16;
    public static final long ENTITIES = 2;
    public static final long FINANCE = 8;
    public static final long WEATHER = 4;
    public static final long WEB_PAGES = 1;
}
