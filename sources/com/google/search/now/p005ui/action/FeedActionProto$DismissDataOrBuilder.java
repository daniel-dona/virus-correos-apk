package com.google.search.now.p005ui.action;

import com.google.search.now.wire.feed.DataOperationProto;
import java.util.List;

/* renamed from: com.google.search.now.ui.action.FeedActionProto$DismissDataOrBuilder */
/* compiled from: PG */
public interface FeedActionProto$DismissDataOrBuilder extends cO {
    YS getContentId();

    DataOperationProto.DataOperation getDataOperations(int i);

    int getDataOperationsCount();

    List<DataOperationProto.DataOperation> getDataOperationsList();

    QS getPayload();

    zQ getUndoAction();

    boolean hasContentId();

    boolean hasPayload();

    boolean hasUndoAction();
}
