package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;
import com.google.search.now.feed.client.StreamDataProto$StreamSharedState;

/* compiled from: PG */
public interface StreamDataProto$StreamSharedStateOrBuilder extends cO {
    String getContentId();

    ByteString getContentIdBytes();

    CT getPietSharedStateItem();

    StreamDataProto$StreamSharedState.ShareStateCase getShareStateCase();

    boolean hasContentId();

    boolean hasPietSharedStateItem();
}
