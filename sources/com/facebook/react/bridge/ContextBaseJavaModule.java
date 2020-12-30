package com.facebook.react.bridge;

import android.content.Context;

/* compiled from: PG */
public abstract class ContextBaseJavaModule extends BaseJavaModule {
    public final Context mContext;

    public ContextBaseJavaModule(Context context) {
        this.mContext = context;
    }

    public final Context getContext() {
        return this.mContext;
    }
}
