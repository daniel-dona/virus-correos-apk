package com.microsoft.applications.experimentation.afd;

import com.citrix.loggersdk.BuildConfig;
import java.util.ArrayList;

/* compiled from: PG */
public class AFDClientConfiguration implements IAFDClientConfiguration {
    public String accountType;
    public String clientId = BuildConfig.FLAVOR;
    public int corpnet = 1;
    public long defaultExpiryInMin = 1440;
    public boolean enableAFDClientTelemetry = false;
    public int existingUser;
    public String flight;
    public String impressionGuid;
    public String market;
    public ArrayList<String> serverUrls;
    public boolean verbose = false;

    public void enableAFDClientTelemetry(boolean z) {
        this.enableAFDClientTelemetry = z;
    }

    public void enableVerbose(boolean z) {
        this.verbose = z;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getClientId() {
        return this.clientId;
    }

    public int getCorpnet() {
        return this.corpnet;
    }

    public long getDefaultExpiryTimeInMin() {
        return this.defaultExpiryInMin;
    }

    public int getExistingUser() {
        return this.existingUser;
    }

    public String getFlight() {
        return this.flight;
    }

    public String getImpressionGuid() {
        return this.impressionGuid;
    }

    public String getMarket() {
        return this.market;
    }

    public ArrayList<String> getServerUrls() {
        return this.serverUrls;
    }

    public boolean isAFDClientTelemetryEnabled() {
        return this.enableAFDClientTelemetry;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setAccountType(String str) {
        this.accountType = str;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setCorpnet(int i) {
        this.corpnet = i;
    }

    public void setDefaultExpiryTimeInMin(long j) {
        this.defaultExpiryInMin = j;
    }

    public void setExistingUser(int i) {
        this.existingUser = i;
    }

    public void setFlight(String str) {
        this.flight = str;
    }

    public void setImpressionGuid(String str) {
        this.impressionGuid = str;
    }

    public void setMarket(String str) {
        this.market = str;
    }

    public void setServerUrls(ArrayList<String> arrayList) {
        this.serverUrls = arrayList;
    }
}
