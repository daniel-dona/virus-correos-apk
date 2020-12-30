package com.microsoft.applications.experimentation.afd;

import com.microsoft.applications.telemetry.ILogger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: PG */
public interface IAFDClient {
    boolean addListener(IAFDClientCallback iAFDClientCallback);

    JSONObject getActiveConfigJSON();

    HashMap<String, String> getConfigs();

    String[] getFeatures();

    String[] getFlights();

    boolean registerLogger(ILogger iLogger, String str);

    boolean removeListener(IAFDClientCallback iAFDClientCallback);

    boolean resume();

    boolean resume(boolean z);

    boolean setRequestHeaders(Map<String, String> map);

    boolean setRequestParameters(Map<String, String> map);

    boolean start();

    boolean start(long j);

    boolean stop();

    boolean suspend();
}
