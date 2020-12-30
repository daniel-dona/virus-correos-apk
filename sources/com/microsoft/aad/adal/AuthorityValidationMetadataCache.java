package com.microsoft.aad.adal;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public final class AuthorityValidationMetadataCache {
    public static final String ALIASES = "aliases";
    public static final String META_DATA = "metadata";
    public static final String PREFERRED_CACHE = "preferred_cache";
    public static final String PREFERRED_NETWORK = "preferred_network";
    public static final String TAG = "AuthorityValidationMetadataCache";
    public static final String TENANT_DISCOVERY_ENDPOINT = "tenant_discovery_endpoint";
    public static ConcurrentMap<String, InstanceDiscoveryMetadata> sAadAuthorityHostMetadata = new ConcurrentHashMap();

    public static void clearAuthorityValidationCache() {
        sAadAuthorityHostMetadata.clear();
    }

    public static boolean containsAuthorityHost(URL url) {
        return sAadAuthorityHostMetadata.containsKey(url.getHost().toLowerCase(Locale.US));
    }

    public static Map<String, InstanceDiscoveryMetadata> getAuthorityValidationMetadataCache() {
        return Collections.unmodifiableMap(sAadAuthorityHostMetadata);
    }

    public static InstanceDiscoveryMetadata getCachedInstanceDiscoveryMetadata(URL url) {
        return (InstanceDiscoveryMetadata) sAadAuthorityHostMetadata.get(url.getHost().toLowerCase(Locale.US));
    }

    public static boolean isAuthorityValidated(URL url) {
        return containsAuthorityHost(url) && getCachedInstanceDiscoveryMetadata(url).isValidated();
    }

    public static void processInstanceDiscoveryMetadata(URL url, Map<String, String> map) throws JSONException {
        boolean containsKey = map.containsKey("tenant_discovery_endpoint");
        String str = map.get("metadata");
        String lowerCase = url.getHost().toLowerCase(Locale.US);
        if (!containsKey) {
            sAadAuthorityHostMetadata.put(lowerCase, new InstanceDiscoveryMetadata(false));
        } else if (StringExtensions.isNullOrBlank(str)) {
            Eo.c(new StringBuilder(), TAG, ":processInstanceDiscoveryMetadata", "No metadata returned from instance discovery.");
            sAadAuthorityHostMetadata.put(lowerCase, new InstanceDiscoveryMetadata(lowerCase, lowerCase));
        } else {
            processInstanceDiscoveryResponse(str);
        }
    }

    public static void processInstanceDiscoveryResponse(String str) throws JSONException {
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            InstanceDiscoveryMetadata processSingleJsonArray = processSingleJsonArray(new JSONObject(jSONArray.get(i).toString()));
            for (String lowerCase : processSingleJsonArray.getAliases()) {
                sAadAuthorityHostMetadata.put(lowerCase.toLowerCase(Locale.US), processSingleJsonArray);
            }
        }
    }

    public static InstanceDiscoveryMetadata processSingleJsonArray(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString(PREFERRED_NETWORK);
        String string2 = jSONObject.getString(PREFERRED_CACHE);
        JSONArray jSONArray = jSONObject.getJSONArray(ALIASES);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return new InstanceDiscoveryMetadata(string, string2, arrayList);
    }

    public static void updateInstanceDiscoveryMap(String str, InstanceDiscoveryMetadata instanceDiscoveryMetadata) {
        sAadAuthorityHostMetadata.put(str.toLowerCase(Locale.US), instanceDiscoveryMetadata);
    }
}
