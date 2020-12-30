package com.microsoft.applications.experimentation.afd;

import java.util.ArrayList;

/* compiled from: PG */
public interface IAFDClientConfiguration {
    void enableAFDClientTelemetry(boolean z);

    void enableVerbose(boolean z);

    String getAccountType();

    String getClientId();

    int getCorpnet();

    long getDefaultExpiryTimeInMin();

    int getExistingUser();

    String getFlight();

    String getImpressionGuid();

    String getMarket();

    ArrayList<String> getServerUrls();

    boolean isAFDClientTelemetryEnabled();

    boolean isVerbose();

    void setAccountType(String str);

    void setClientId(String str);

    void setCorpnet(int i);

    void setDefaultExpiryTimeInMin(long j);

    void setExistingUser(int i);

    void setFlight(String str);

    void setImpressionGuid(String str);

    void setMarket(String str);

    void setServerUrls(ArrayList<String> arrayList);
}
