package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public interface StreamDataProto$StreamUploadableActionOrBuilder extends cO {
    String getFeatureContentId();

    ByteString getFeatureContentIdBytes();

    QS getPayload();

    long getTimestampSeconds();

    int getUploadAttempts();

    boolean hasFeatureContentId();

    boolean hasPayload();

    boolean hasTimestampSeconds();

    boolean hasUploadAttempts();
}
