package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;
import com.google.search.now.feed.client.StreamDataProto$StreamPayload;

/* compiled from: PG */
public interface StreamDataProto$StreamPayloadOrBuilder extends cO {
    VS getConsistencyToken();

    StreamDataProto$StreamPayload.PayloadCase getPayloadCase();

    ByteString getSemanticData();

    StreamDataProto$StreamFeature getStreamFeature();

    eQ getStreamSessions();

    StreamDataProto$StreamSharedState getStreamSharedState();

    hQ getStreamToken();

    boolean hasConsistencyToken();

    boolean hasSemanticData();

    boolean hasStreamFeature();

    boolean hasStreamSessions();

    boolean hasStreamSharedState();

    boolean hasStreamToken();
}
