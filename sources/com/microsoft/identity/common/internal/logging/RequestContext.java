package com.microsoft.identity.common.internal.logging;

import com.google.gson.Gson;
import java.util.HashMap;

/* compiled from: PG */
public class RequestContext extends HashMap<String, String> implements IRequestContext {
    public final Gson mGson = new Gson();

    public String toJsonString() {
        return this.mGson.mo2100a((Object) this);
    }
}
