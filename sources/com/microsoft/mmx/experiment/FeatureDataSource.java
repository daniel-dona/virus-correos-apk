package com.microsoft.mmx.experiment;

import java.util.List;

/* compiled from: PG */
public interface FeatureDataSource {

    /* compiled from: PG */
    public interface FeatureUpdateListener {
        void onFeaturesUpdated(FeatureDataSource featureDataSource, List<FeatureManager$Feature> list);
    }
}
