package com.facebook.react.bridge;

import android.app.Activity;

/* compiled from: PG */
public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    public final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    public final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    public final ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }
}
