package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.search.now.feed.client.StreamDataProto$StreamStructure;

/* compiled from: PG */
public interface StreamDataProto$StreamStructureOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<StreamDataProto$StreamStructure, StreamDataProto$StreamStructure.C0299a> {
    String getContentId();

    ByteString getContentIdBytes();

    StreamDataProto$StreamStructure.Operation getOperation();

    String getParentContentId();

    ByteString getParentContentIdBytes();

    boolean hasContentId();

    boolean hasOperation();

    boolean hasParentContentId();
}
