package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public interface StreamDataProto$StreamTokenOrBuilder extends cO {
    String getContentId();

    ByteString getContentIdBytes();

    ByteString getNextPageToken();

    String getParentId();

    ByteString getParentIdBytes();

    boolean hasContentId();

    boolean hasNextPageToken();

    boolean hasParentId();
}
