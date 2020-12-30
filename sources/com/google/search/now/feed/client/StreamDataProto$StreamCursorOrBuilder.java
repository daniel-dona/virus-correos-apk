package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;

/* compiled from: PG */
public interface StreamDataProto$StreamCursorOrBuilder extends cO {
    String getLastAccessedContent();

    ByteString getLastAccessedContentBytes();

    String getParentContentId();

    ByteString getParentContentIdBytes();

    boolean hasLastAccessedContent();

    boolean hasParentContentId();
}
