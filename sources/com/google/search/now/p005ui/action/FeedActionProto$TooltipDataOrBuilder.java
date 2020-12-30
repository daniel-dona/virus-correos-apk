package com.google.search.now.p005ui.action;

import com.google.protobuf.ByteString;
import com.google.search.now.p005ui.action.FeedActionProto$TooltipData;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$TooltipDataOrBuilder */
/* compiled from: PG */
public interface FeedActionProto$TooltipDataOrBuilder extends cO {
    String getAccessibilityLabel();

    ByteString getAccessibilityLabelBytes();

    FeedActionProto$TooltipData.FeatureName getFeatureName();

    pQ getInsets();

    String getLabel();

    ByteString getLabelBytes();

    boolean hasAccessibilityLabel();

    boolean hasFeatureName();

    boolean hasInsets();

    boolean hasLabel();
}
