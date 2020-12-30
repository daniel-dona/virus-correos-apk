package com.microsoft.aad.adal;

import com.google.gson.Gson;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.net.IWebRequestHandler;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import java.util.UUID;

/* compiled from: PG */
public abstract class AbstractMetadataRequestor<MetadataType, MetadataRequestOptions> {
    public UUID mCorrelationId;
    public Gson mGson;
    public final IWebRequestHandler mWebrequestHandler = new WebRequestHandler();

    public final UUID getCorrelationId() {
        return this.mCorrelationId;
    }

    public IWebRequestHandler getWebrequestHandler() {
        return this.mWebrequestHandler;
    }

    public abstract MetadataType parseMetadata(HttpWebResponse httpWebResponse) throws Exception;

    public synchronized Gson parser() {
        if (this.mGson == null) {
            this.mGson = new Gson();
        }
        return this.mGson;
    }

    public abstract MetadataType requestMetadata(MetadataRequestOptions metadatarequestoptions) throws Exception;

    public final void setCorrelationId(UUID uuid) {
        this.mCorrelationId = uuid;
    }
}
