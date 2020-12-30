package com.microsoft.aad.adal;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.microsoft.aad.adal.BrokerProxy;
import java.io.IOException;

/* compiled from: PG */
public interface IBrokerProxy {
    BrokerProxy.SwitchToBroker canSwitchToBroker(String str);

    boolean canUseLocalCache(String str);

    AuthenticationResult getAuthTokenInBackground(AuthenticationRequest authenticationRequest, BrokerEvent brokerEvent) throws AuthenticationException;

    String getBrokerAppVersion(String str) throws PackageManager.NameNotFoundException;

    UserInfo[] getBrokerUsers() throws OperationCanceledException, AuthenticatorException, IOException;

    String getCurrentActiveBrokerPackageName();

    String getCurrentUser();

    Intent getIntentForBrokerActivity(AuthenticationRequest authenticationRequest, BrokerEvent brokerEvent) throws AuthenticationException;

    void removeAccounts();

    void saveAccount(String str);

    boolean verifyBrokerForSilentRequest(AuthenticationRequest authenticationRequest) throws AuthenticationException;

    boolean verifyUser(String str, String str2) throws AuthenticationException;
}
