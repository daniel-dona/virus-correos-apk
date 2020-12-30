package com.microsoft.aad.adal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Process;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.TelemetryUtils;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationStrategy;

/* compiled from: PG */
public final class AcquireTokenWithBrokerRequest {
    public static final String TAG = "AcquireTokenWithBrokerRequest";
    public final AuthenticationRequest mAuthRequest;
    public final IBrokerProxy mBrokerProxy;

    public AcquireTokenWithBrokerRequest(AuthenticationRequest authenticationRequest, IBrokerProxy iBrokerProxy) {
        this.mAuthRequest = authenticationRequest;
        this.mBrokerProxy = iBrokerProxy;
    }

    private void logBrokerVersion(BrokerEvent brokerEvent) {
        String str;
        String currentActiveBrokerPackageName = this.mBrokerProxy.getCurrentActiveBrokerPackageName();
        if (StringExtensions.isNullOrBlank(currentActiveBrokerPackageName)) {
            Logger.m1248i(TAG + ":logBrokerVersion", "Broker app package name is empty.", BuildConfig.FLAVOR);
            return;
        }
        brokerEvent.setBrokerAppName(currentActiveBrokerPackageName);
        try {
            str = this.mBrokerProxy.getBrokerAppVersion(currentActiveBrokerPackageName);
        } catch (PackageManager.NameNotFoundException unused) {
            str = "N/A";
        }
        brokerEvent.setBrokerAppVersion(str);
        Logger.m1248i(TAG + ":logBrokerVersion", "Broker app is: " + currentActiveBrokerPackageName + ";Broker app version: " + str, BuildConfig.FLAVOR);
    }

    private BrokerEvent startBrokerTelemetryRequest(String str) {
        BrokerEvent brokerEvent = new BrokerEvent(str);
        brokerEvent.setRequestId(this.mAuthRequest.getTelemetryRequestId());
        C0336Telemetry.getInstance().startEvent(this.mAuthRequest.getTelemetryRequestId(), str);
        return brokerEvent;
    }

    public void acquireTokenWithBrokerInteractively(IWindowComponent iWindowComponent) throws AuthenticationException {
        Eo.c(new StringBuilder(), TAG, ":acquireTokenWithBrokerInteractively", "Launch activity for interactive authentication via broker.");
        BrokerEvent startBrokerTelemetryRequest = startBrokerTelemetryRequest("Microsoft.ADAL.broker_request_interactive");
        logBrokerVersion(startBrokerTelemetryRequest);
        Intent intentForBrokerActivity = this.mBrokerProxy.getIntentForBrokerActivity(this.mAuthRequest, startBrokerTelemetryRequest);
        if (iWindowComponent == null) {
            throw new AuthenticationException(ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED);
        } else if (intentForBrokerActivity != null) {
            String a = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithBrokerInteractively");
            StringBuilder a2 = Eo.a("Calling activity. Pid:");
            a2.append(Process.myPid());
            a2.append(" tid:");
            a2.append(Process.myTid());
            a2.append("uid:");
            a2.append(Process.myUid());
            Logger.m1250v(a, a2.toString());
            C0336Telemetry.getInstance().stopEvent(startBrokerTelemetryRequest.getTelemetryRequestId(), startBrokerTelemetryRequest, "Microsoft.ADAL.broker_request_interactive");
            iWindowComponent.startActivityForResult(intentForBrokerActivity, AuthorizationStrategy.BROWSER_FLOW);
        } else {
            throw new AuthenticationException(ADALError.DEVELOPER_ACTIVITY_IS_NOT_RESOLVED);
        }
    }

    public AuthenticationResult acquireTokenWithBrokerSilent() throws AuthenticationException {
        AuthenticationResult authenticationResult;
        this.mAuthRequest.setVersion(AuthenticationContext.getVersionName());
        AuthenticationRequest authenticationRequest = this.mAuthRequest;
        authenticationRequest.setBrokerAccountName(authenticationRequest.getLoginHint());
        BrokerEvent startBrokerTelemetryRequest = startBrokerTelemetryRequest("Microsoft.ADAL.broker_request_silent");
        logBrokerVersion(startBrokerTelemetryRequest);
        if (!StringExtensions.isNullOrBlank(this.mAuthRequest.getBrokerAccountName()) || !StringExtensions.isNullOrBlank(this.mAuthRequest.getUserId())) {
            Eo.c(new StringBuilder(), TAG, ":acquireTokenWithBrokerSilent", "User is specified for background(silent) token request, trying to acquire token silently.");
            authenticationResult = this.mBrokerProxy.getAuthTokenInBackground(this.mAuthRequest, startBrokerTelemetryRequest);
            if (!(authenticationResult == null || authenticationResult.getCliTelemInfo() == null)) {
                TelemetryUtils.CliTelemInfo cliTelemInfo = authenticationResult.getCliTelemInfo();
                startBrokerTelemetryRequest.setSpeRing(cliTelemInfo.getSpeRing());
                startBrokerTelemetryRequest.setRefreshTokenAge(cliTelemInfo.getRefreshTokenAge());
                startBrokerTelemetryRequest.setServerErrorCode(cliTelemInfo.getServerErrorCode());
                startBrokerTelemetryRequest.setServerSubErrorCode(cliTelemInfo.getServerSubErrorCode());
            }
        } else {
            Eo.c(new StringBuilder(), TAG, ":acquireTokenWithBrokerSilent", "User is not specified, skipping background(silent) token request.");
            authenticationResult = null;
        }
        C0336Telemetry.getInstance().stopEvent(startBrokerTelemetryRequest.getTelemetryRequestId(), startBrokerTelemetryRequest, "Microsoft.ADAL.broker_request_silent");
        return authenticationResult;
    }
}
