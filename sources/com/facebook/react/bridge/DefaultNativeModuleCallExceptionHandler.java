package com.facebook.react.bridge;

/* compiled from: PG */
public class DefaultNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
    public void handleException(Exception exc) {
        if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        }
        throw new RuntimeException(exc);
    }
}
