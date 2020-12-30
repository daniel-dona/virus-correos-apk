package com.microsoft.applications.experimentation.afd;

import android.content.Context;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.applications.experimentation.common.EXPClient;
import com.microsoft.applications.experimentation.common.EXPClientState;
import com.microsoft.applications.experimentation.common.EXPConfigSource;
import com.microsoft.applications.experimentation.common.EXPConfigUpdate;
import com.microsoft.applications.experimentation.common.Preconditions;
import com.microsoft.applications.experimentation.common.TraceHelper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class AFDClient extends EXPClient<AFDConfig, IAFDClientCallback> implements IAFDClient {
    public static final String LOG_TAG = Eo.a(AFDClient.class, Eo.a("[AFD]:"));
    public AFDClientConfiguration configuration;
    public AFDHttpClientManager httpClientManager = new AFDHttpClientManager(this, this.configuration, 5);
    public AFDPersistentStorageManager persistentStorageManager;

    public AFDClient(Context context, AFDClientConfiguration aFDClientConfiguration) {
        super(context, aFDClientConfiguration.getClientId(), "stub_cv", aFDClientConfiguration.isAFDClientTelemetryEnabled());
        TraceHelper.TraceInformation(LOG_TAG, "AFD Client created");
        Preconditions.isNotNull(context, "context can't be null");
        validateConfig(aFDClientConfiguration);
        this.configuration = aFDClientConfiguration;
        setHeadersAndQueryParamFromConfiguration();
        this.persistentStorageManager = new AFDPersistentStorageManager(context, this.configuration.getClientId());
    }

    private void setHeadersAndQueryParamFromConfiguration() {
        if (this.configuration.getExistingUser() == 1) {
            this.activeRequestHeaders.put("X-MSEDGE-EXISTINGUSER", String.valueOf(this.configuration.getExistingUser()));
        }
        this.activeQueryParametersAsString = getQueryParameters();
    }

    private void validateConfig(AFDClientConfiguration aFDClientConfiguration) {
        Preconditions.isNotNull(aFDClientConfiguration, "configuration can't be null.");
        Preconditions.isTrue(aFDClientConfiguration.getDefaultExpiryTimeInMin() >= 5, "The configuration defaultExpiryTimeInMin should be greater than or equal to 5 min.");
        Preconditions.isNotNullOrEmpty(aFDClientConfiguration.getClientId(), "The configuration clientId can't be null or empty");
        if (aFDClientConfiguration.getServerUrls() == null || aFDClientConfiguration.getServerUrls().isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("http://test-exp-s2s.msedge.net/ab");
            arrayList.add("http://test-exp-s2s.msedge.net/ab");
            aFDClientConfiguration.setServerUrls(arrayList);
        }
    }

    public /* bridge */ /* synthetic */ boolean addListener(IAFDClientCallback iAFDClientCallback) {
        return super.addListener(iAFDClientCallback);
    }

    public boolean alwaysForceFetchOnStart() {
        return true;
    }

    public void checkServerAsyncForConfig() {
        AFDHttpClientManager aFDHttpClientManager = this.httpClientManager;
        String str = this.activeQueryParametersAsString;
        HashMap hashMap = new HashMap(this.activeRequestHeaders);
        T t = this.activeConfig;
        aFDHttpClientManager.checkServerAsyncForConfig(str, hashMap, t == null ? BuildConfig.FLAVOR : ((AFDConfig) t).ETag, BuildConfig.FLAVOR);
    }

    public AFDConfig getActiveConfig() {
        return (AFDConfig) this.activeConfig;
    }

    public String getActiveConfigClientVersion() {
        return "stub_cv";
    }

    public String getActiveConfigETag() {
        return ((AFDConfig) this.activeConfig).ETag;
    }

    public long getActiveConfigExpireTime() {
        return ((AFDConfig) this.activeConfig).ExpireTimeInSec;
    }

    public String getActiveConfigImpressionId() {
        return ((AFDConfig) this.activeConfig).ImpressionId;
    }

    public JSONObject getActiveConfigJSON() {
        T t = this.activeConfig;
        if (!(t == null || ((AFDConfig) t).JSONResponse == null)) {
            try {
                return new JSONObject(((AFDConfig) t).JSONResponse);
            } catch (JSONException unused) {
                TraceHelper.TraceError(LOG_TAG, "Could not parse JSON.");
            }
        }
        return new JSONObject();
    }

    public String getActiveQueryParametersAsString() {
        return this.activeQueryParametersAsString;
    }

    public String getConfigIds(String str) {
        if (!this.configuration.isVerbose()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : ((AFDConfig) this.activeConfig).Flights) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public HashMap<String, String> getConfigs() {
        return new HashMap<>(((AFDConfig) this.activeConfig).Configs);
    }

    public String getEXPClientUpdateEventName() {
        return "afdclientstate";
    }

    public String getEXPConfigUpdateEventName() {
        return "afdconfigupdate";
    }

    public ArrayList<String> getEventNamesForWhichToSetConfigIds(String str) {
        return null;
    }

    public String getEventSpecificConfigIds(String str, String str2) {
        return null;
    }

    public String[] getFeatures() {
        return ((AFDConfig) this.activeConfig).Features;
    }

    public String[] getFlights() {
        return ((AFDConfig) this.activeConfig).Flights;
    }

    public String getQueryParameters() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("clientid");
            sb.append("=");
            sb.append(URLEncoder.encode(this.configuration.getClientId(), "UTF-8"));
            if (this.configuration.getImpressionGuid() != null) {
                if (!this.configuration.getImpressionGuid().isEmpty()) {
                    sb.append("&");
                    sb.append("ig");
                    sb.append("=");
                    sb.append(URLEncoder.encode(this.configuration.getImpressionGuid(), "UTF-8"));
                }
            }
            if (this.configuration.getMarket() != null && !this.configuration.getMarket().isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("mkt");
                sb.append("=");
                sb.append(URLEncoder.encode(this.configuration.getMarket(), "UTF-8"));
            }
            if (this.configuration.getAccountType() != null && !this.configuration.getAccountType().isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("accounttype");
                sb.append("=");
                sb.append(URLEncoder.encode(this.configuration.getAccountType(), "UTF-8"));
            }
            if (this.configuration.getCorpnet() == 0) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("corpnet");
                sb.append("=");
                sb.append(URLEncoder.encode(String.valueOf(this.configuration.getCorpnet()), "UTF-8"));
            }
            if (this.configuration.getFlight() != null && !this.configuration.getFlight().isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("setflight");
                sb.append("=");
                sb.append(URLEncoder.encode(this.configuration.getFlight(), "UTF-8"));
            }
            for (String next : this.activeRequestParameter.keySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(next);
                sb.append("=");
                sb.append(URLEncoder.encode(this.activeRequestParameter.get(next), "UTF-8"));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            TraceHelper.TraceError(LOG_TAG, "UTF-8 url encoding not supported. Empty query parameters will be used.", e);
            return BuildConfig.FLAVOR;
        }
    }

    public HashMap<String, String> getRequestHeadersFromActiveConfig() {
        return ((AFDConfig) this.activeConfig).RequestHeaders;
    }

    public String getRequestParametersFromActiveConfig() {
        return ((AFDConfig) this.activeConfig).RequestParameters;
    }

    public boolean isStarted() {
        return this.started;
    }

    public /* bridge */ /* synthetic */ boolean removeListener(IAFDClientCallback iAFDClientCallback) {
        return super.removeListener(iAFDClientCallback);
    }

    public boolean restartOnRequestParameterChange() {
        return true;
    }

    public void sendOutCallbacks(boolean z, long j, String str, HashMap<String, String> hashMap, boolean z2) {
        TraceHelper.TraceInformation(LOG_TAG, String.format("AFDClient CallbackListeners Succeeded: %s, QueryParams: %s", new Object[]{Boolean.valueOf(z), str}));
        Iterator<T2> it = this.listeners.iterator();
        while (it.hasNext()) {
            IAFDClientCallback iAFDClientCallback = (IAFDClientCallback) it.next();
            AFDClientEventType aFDClientEventType = z ? AFDClientEventType.ET_CONFIG_UPDATE_SUCCEEDED : AFDClientEventType.ET_CONFIG_UPDATE_FAILED;
            String clientId = this.configuration.getClientId();
            T t = this.activeConfig;
            iAFDClientCallback.onAFDClientEvent(aFDClientEventType, new AFDClientEventContext(j, clientId, str, hashMap, z2, ((AFDConfig) t).FlightingVersion, ((AFDConfig) t).ImpressionId));
        }
    }

    public boolean setRequestHeaders(Map<String, String> map) {
        Preconditions.isNotNull(map, "requestHeaders can't be null");
        this.activeRequestHeaders.clear();
        if (this.configuration.getExistingUser() == 1) {
            this.activeRequestHeaders.put("X-MSEDGE-EXISTINGUSER", String.valueOf(this.configuration.getExistingUser()));
        }
        this.activeRequestHeaders.putAll(map);
        logEXPClientUpdate(EXPClientState.REQUEST_PARAMETER_CHANGED);
        if (restartOnRequestParameterChange()) {
            restartIfAlreadyStarted();
        }
        return true;
    }

    public AFDConfig getConfigFromStorage() {
        return this.persistentStorageManager.getConfig(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    public void onConfigurationReturnedFromServer(AFDConfig aFDConfig, String str, HashMap<String, String> hashMap) {
        if (aFDConfig == null) {
            logEXPConfigUpdate(EXPConfigUpdate.FAILED, EXPConfigSource.SERVER);
            TraceHelper.TraceInformation(LOG_TAG, String.format("Failed to update config from server.  QueryParameters: %s", new Object[]{str}));
            scheduleUpdate(true);
            sendOutCallbacks(false, 1800 + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), str, hashMap, false);
        } else {
            logEXPConfigUpdate(EXPConfigUpdate.SUCCEEDED, EXPConfigSource.SERVER);
            TraceHelper.TraceInformation(LOG_TAG, String.format("Update the current active config.  QueryParameters: %s", new Object[]{str}));
            if (aFDConfig.Flights == null || aFDConfig.Features == null) {
                ((AFDConfig) this.activeConfig).ExpireTimeInSec = aFDConfig.ExpireTimeInSec;
            } else {
                setActiveConfig(aFDConfig);
                this.configuration.setImpressionGuid(((AFDConfig) this.activeConfig).ImpressionId);
                this.activeQueryParametersAsString = getQueryParameters();
            }
            addConfigInformationToLoggers();
            T t = this.activeConfig;
            ((AFDConfig) t).RequestParameters = str;
            ((AFDConfig) t).RequestHeaders = hashMap;
            this.persistentStorageManager.storeConfig(BuildConfig.FLAVOR, BuildConfig.FLAVOR, BuildConfig.FLAVOR, t);
            scheduleUpdate(false);
            sendOutCallbacks(true, ((AFDConfig) this.activeConfig).ExpireTimeInSec - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), str, hashMap, true);
        }
        synchronized (this.objectToNotify) {
            this.objectToNotify.notifyAll();
        }
    }

    public void setActiveConfig(AFDConfig aFDConfig) {
        this.activeConfig = aFDConfig;
    }
}
