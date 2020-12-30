package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import pv;

/* compiled from: PG */
public interface NetworkFetcher<FETCH_STATE extends pv> {

    /* compiled from: PG */
    public interface Callback {
        void onCancellation();

        void onFailure(Throwable th);

        void onResponse(InputStream inputStream, int i) throws IOException;
    }

    /* renamed from: a */
    FETCH_STATE mo541a(Zu<Xt> zu, aw awVar);

    /* renamed from: a */
    void mo542a(FETCH_STATE fetch_state, int i);

    /* renamed from: a */
    void mo543a(FETCH_STATE fetch_state, Callback callback);

    /* renamed from: b */
    Map<String, String> mo544b(FETCH_STATE fetch_state, int i);
}
