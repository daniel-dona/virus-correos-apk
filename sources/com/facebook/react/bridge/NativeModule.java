package com.facebook.react.bridge;

@Qw
/* compiled from: PG */
public interface NativeModule {

    /* compiled from: PG */
    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    String getName();

    void initialize();

    void onCatalystInstanceDestroy();
}
