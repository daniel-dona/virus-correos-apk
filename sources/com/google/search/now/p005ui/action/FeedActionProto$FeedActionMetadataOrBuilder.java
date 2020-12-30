package com.google.search.now.p005ui.action;

import com.google.search.now.p005ui.action.FeedActionProto$FeedActionMetadata;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$FeedActionMetadataOrBuilder */
/* compiled from: PG */
public interface FeedActionProto$FeedActionMetadataOrBuilder extends cO {
    FeedActionProto$FeedActionMetadata.DataCase getDataCase();

    mQ getDismissData();

    int getElementTypeValue();

    FeedActionProto$NotInterestedInData getNotInterestedInData();

    uQ getOpenContextMenuData();

    wQ getOpenUrlData();

    FeedActionProto$TooltipData getTooltipData();

    FeedActionProto$FeedActionMetadata.Type getType();

    boolean hasDismissData();

    boolean hasElementTypeValue();

    boolean hasNotInterestedInData();

    boolean hasOpenContextMenuData();

    boolean hasOpenUrlData();

    boolean hasTooltipData();

    boolean hasType();
}
