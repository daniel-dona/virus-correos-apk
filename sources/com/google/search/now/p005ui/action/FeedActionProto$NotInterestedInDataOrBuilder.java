package com.google.search.now.p005ui.action;

import com.google.search.now.wire.feed.DataOperationProto;
import java.util.List;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$NotInterestedInDataOrBuilder */
/* compiled from: PG */
public interface FeedActionProto$NotInterestedInDataOrBuilder extends cO {
    DataOperationProto.DataOperation getDataOperations(int i);

    int getDataOperationsCount();

    List<DataOperationProto.DataOperation> getDataOperationsList();

    int getInterestTypeValue();

    QS getPayload();

    zQ getUndoAction();

    boolean hasInterestTypeValue();

    boolean hasPayload();

    boolean hasUndoAction();
}
