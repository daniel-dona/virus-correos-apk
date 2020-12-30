package com.google.search.now.feed.client;

import com.google.protobuf.ByteString;
import com.google.search.now.feed.client.StreamDataProto$StreamFeature;
import com.google.search.now.ui.stream.StreamStructureProto;

/* compiled from: PG */
public interface StreamDataProto$StreamFeatureOrBuilder extends cO {
    GS getCard();

    IS getCluster();

    StreamStructureProto.Content getContent();

    String getContentId();

    ByteString getContentIdBytes();

    StreamDataProto$StreamFeature.FeaturePayloadCase getFeaturePayloadCase();

    ZP getLegacyContent();

    String getParentId();

    ByteString getParentIdBytes();

    OS getStream();

    boolean hasCard();

    boolean hasCluster();

    boolean hasContent();

    boolean hasContentId();

    boolean hasLegacyContent();

    boolean hasParentId();

    boolean hasStream();
}
