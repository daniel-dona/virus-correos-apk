package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public interface StreamDataProto$StreamSessionOrBuilder extends cO {
    long getLegacyTimeMillis();

    String getSessionId();

    ByteString getSessionIdBytes();

    WP getSessionMetadata();

    boolean hasLegacyTimeMillis();

    boolean hasSessionId();

    boolean hasSessionMetadata();
}
