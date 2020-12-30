package org.chromium.p012ui.modelutil;

import java.util.Set;
import org.chromium.base.Callback;

/* renamed from: org.chromium.ui.modelutil.RecyclerViewAdapter$Delegate */
/* compiled from: PG */
public interface RecyclerViewAdapter$Delegate<VH, P> extends ListObservable<P> {
    String describeItemForTesting(int i);

    void dismissItem(int i, Callback<String> callback);

    int getItemCount();

    Set<Integer> getItemDismissalGroup(int i);

    int getItemViewType(int i);

    void onBindViewHolder(VH vh, int i, P p);

    void onViewRecycled(VH vh);
}
