package com.microsoft.mmx.experiment;

import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public interface FeatureManager$FeatureHandler<T> {
    T fromJson(JSONObject jSONObject, FeatureManager$Feature featureManager$Feature, String str) throws JSONException;

    void remove(SharedPreferences.Editor editor, FeatureManager$Feature featureManager$Feature);

    T restore(SharedPreferences sharedPreferences, FeatureManager$Feature featureManager$Feature, T t);

    void save(SharedPreferences.Editor editor, FeatureManager$Feature featureManager$Feature, T t);
}
