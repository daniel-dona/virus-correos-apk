package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public interface StreamDataProto$StreamLocalActionOrBuilder extends cO {
    int getAction();

    String getFeatureContentId();

    ByteString getFeatureContentIdBytes();

    long getTimestampSeconds();

    boolean hasAction();

    boolean hasFeatureContentId();

    boolean hasTimestampSeconds();
}
