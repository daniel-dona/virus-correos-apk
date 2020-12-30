package com.facebook.react.bridge;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* compiled from: PG */
public @interface ReactMethod {
    boolean isBlockingSynchronousMethod() default false;
}
