package com.microsoft.aad.adal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.TelemetryUtils;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.broker.BrokerValidator;
import com.microsoft.identity.common.internal.cache.SharedPreferencesFileManager;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

@TargetApi(14)
/* compiled from: PG */
public class BrokerProxy implements IBrokerProxy {
    public static final int ACCOUNT_MANAGER_ERROR_CODE_BAD_AUTHENTICATION = 9;
    public static final String AUTHENTICATOR_CANCELS_REQUEST = "Authenticator cancels the request";
    public static final String DATA_USER_INFO = "com.microsoft.workaccount.user.info";
    public static final String KEY_ACCOUNT_LIST_DELIM = "|";
    public static final String KEY_APP_ACCOUNTS_FOR_TOKEN_REMOVAL = "AppAccountsForTokenRemoval";
    public static final String KEY_SHARED_PREF_ACCOUNT_LIST = "com.microsoft.aad.adal.account.list";
    public static final String TAG = "BrokerProxy";
    public AccountManager mAcctManager;
    public BrokerValidator mBrokerValidator;
    public Context mContext;
    public Handler mHandler;

    /* compiled from: PG */
    public enum SwitchToBroker {
        CAN_SWITCH_TO_BROKER,
        CANNOT_SWITCH_TO_BROKER,
        NEED_PERMISSIONS_TO_SWITCH_TO_BROKER
    }

    public BrokerProxy() {
    }

    private boolean checkAccount(AccountManager accountManager, String str, String str2) {
        for (AuthenticatorDescription authenticatorDescription : accountManager.getAuthenticatorTypes()) {
            if (authenticatorDescription.type.equals("com.microsoft.workaccount")) {
                Account[] accountsByType = this.mAcctManager.getAccountsByType("com.microsoft.workaccount");
                if (authenticatorDescription.packageName.equalsIgnoreCase("com.azure.authenticator") || authenticatorDescription.packageName.equalsIgnoreCase("com.microsoft.windowsintune.companyportal") || authenticatorDescription.packageName.equalsIgnoreCase(AuthenticationSettings.INSTANCE.getBrokerPackageName())) {
                    if (hasSupportToAddUserThroughBroker(authenticatorDescription.packageName)) {
                        return true;
                    }
                    if (accountsByType.length > 0) {
                        return verifyAccount(accountsByType, str, str2);
                    }
                }
            }
        }
        return false;
    }

    private String checkPermission(String str) {
        if (this.mContext.getPackageManager().checkPermission(str, this.mContext.getPackageName()) == 0) {
            return BuildConfig.FLAVOR;
        }
        Logger.m1253w(TAG, Eo.a("Broker related permissions are missing for ", str), BuildConfig.FLAVOR, ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING);
        return str + ' ';
    }

    private Account findAccount(String str, Account[] accountArr) {
        String str2;
        if (accountArr == null) {
            return null;
        }
        for (Account account : accountArr) {
            if (account != null && (str2 = account.name) != null && str2.equalsIgnoreCase(str)) {
                return account;
            }
        }
        return null;
    }

    private UserInfo findUserInfo(String str, UserInfo[] userInfoArr) {
        if (userInfoArr == null) {
            return null;
        }
        for (UserInfo userInfo : userInfoArr) {
            if (userInfo != null && !TextUtils.isEmpty(userInfo.getUserId()) && userInfo.getUserId().equalsIgnoreCase(str)) {
                return userInfo;
            }
        }
        return null;
    }

    private Bundle getAuthTokenFromAccountManager(AuthenticationRequest authenticationRequest, Bundle bundle) throws AuthenticationException {
        Account targetAccount = getTargetAccount(authenticationRequest);
        if (targetAccount != null) {
            try {
                AccountManagerFuture<Bundle> authToken = this.mAcctManager.getAuthToken(targetAccount, "adal.authtoken.type", bundle, false, (AccountManagerCallback) null, this.mHandler);
                Logger.m1250v("BrokerProxy:getAuthTokenFromAccountManager", "Received result from broker");
                Bundle result = authToken.getResult();
                Logger.m1250v("BrokerProxy:getAuthTokenFromAccountManager", "Returning result from broker");
                return result;
            } catch (OperationCanceledException e) {
                Logger.m1246e("BrokerProxy:getAuthTokenFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.AUTH_FAILED_CANCELLED, e);
                throw new AuthenticationException(ADALError.AUTH_FAILED_CANCELLED, e.getMessage(), (Throwable) e);
            } catch (AuthenticatorException e2) {
                if (StringExtensions.isNullOrBlank(e2.getMessage()) || !e2.getMessage().contains("invalid_grant")) {
                    Logger.m1245e("BrokerProxy:getAuthTokenFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.BROKER_AUTHENTICATOR_ERROR_GETAUTHTOKEN);
                    throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_ERROR_GETAUTHTOKEN, e2.getMessage());
                }
                Logger.m1245e("BrokerProxy:getAuthTokenFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, "Acquire token failed with 'invalid grant' error, cannot proceed with silent request.", ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED);
                throw new AuthenticationException(ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED, e2.getMessage());
            } catch (IOException e3) {
                Logger.m1245e("BrokerProxy:getAuthTokenFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.BROKER_AUTHENTICATOR_IO_EXCEPTION);
                if (e3.getMessage() != null && e3.getMessage().contains(ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE.getDescription())) {
                    ADALError aDALError = ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE;
                    StringBuilder a = Eo.a("Received error from broker, errorCode: ");
                    a.append(e3.getMessage());
                    throw new AuthenticationException(aDALError, a.toString());
                } else if (e3.getMessage() == null || !e3.getMessage().contains(ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION.getDescription())) {
                    throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_IO_EXCEPTION, e3.getMessage(), (Throwable) e3);
                } else {
                    ADALError aDALError2 = ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION;
                    StringBuilder a2 = Eo.a("Received error from broker, errorCode: ");
                    a2.append(e3.getMessage());
                    throw new AuthenticationException(aDALError2, a2.toString());
                }
            }
        } else {
            Logger.m1250v("BrokerProxy:getAuthTokenFromAccountManager", "Target account is not found");
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: com.microsoft.aad.adal.AuthenticationException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException} */
    /* JADX WARNING: type inference failed for: r9v4, types: [com.microsoft.aad.adal.AuthenticationException] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microsoft.aad.adal.AuthenticationException getAuthenticationExceptionForResult(java.lang.String r8, java.lang.String r9, android.os.Bundle r10) {
        /*
            r7 = this;
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            r0[r1] = r8
            r8 = 1
            r0[r8] = r9
            java.lang.String r8 = "Received error from broker, errorCode: %s; ErrorDescription: %s"
            java.lang.String r8 = java.lang.String.format(r8, r0)
            java.lang.String r9 = "response_body"
            java.io.Serializable r9 = r10.getSerializable(r9)
            com.microsoft.aad.adal.TelemetryUtils$CliTelemInfo r0 = r7.getCliTelemInfoFromBundle(r10)
            if (r9 == 0) goto L_0x006d
            boolean r1 = r9 instanceof java.util.HashMap
            if (r1 == 0) goto L_0x006d
            java.util.HashMap r9 = (java.util.HashMap) r9
            java.lang.String r1 = "error"
            java.lang.Object r1 = r9.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "suberror"
            java.lang.Object r9 = r9.get(r2)
            java.lang.String r9 = (java.lang.String) r9
            boolean r2 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r1)
            if (r2 != 0) goto L_0x006d
            boolean r2 = com.microsoft.identity.common.adal.internal.util.StringExtensions.isNullOrBlank(r9)
            if (r2 != 0) goto L_0x006d
            java.lang.String r2 = "unauthorized_client"
            int r1 = r2.compareTo(r1)
            if (r1 != 0) goto L_0x006d
            java.lang.String r1 = "protection_policy_required"
            int r9 = r1.compareTo(r9)
            if (r9 != 0) goto L_0x006d
            java.lang.String r9 = "account.name"
            java.lang.String r3 = r10.getString(r9)
            java.lang.String r9 = "account.userinfo.userid"
            java.lang.String r4 = r10.getString(r9)
            java.lang.String r9 = "account.userinfo.tenantid"
            java.lang.String r5 = r10.getString(r9)
            java.lang.String r9 = "account.authority"
            java.lang.String r6 = r10.getString(r9)
            com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException r9 = new com.microsoft.aad.adal.IntuneAppProtectionPolicyRequiredException
            r1 = r9
            r2 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x006e
        L_0x006d:
            r9 = 0
        L_0x006e:
            if (r9 != 0) goto L_0x0077
            com.microsoft.aad.adal.AuthenticationException r9 = new com.microsoft.aad.adal.AuthenticationException
            com.microsoft.aad.adal.ADALError r10 = com.microsoft.aad.adal.ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED
            r9.<init>(r10, r8)
        L_0x0077:
            java.lang.String r8 = r0.getSpeRing()
            r9.setSpeRing(r8)
            java.lang.String r8 = r0.getRefreshTokenAge()
            r9.setRefreshTokenAge(r8)
            java.lang.String r8 = r0.getServerErrorCode()
            r9.setCliTelemErrorCode(r8)
            java.lang.String r8 = r0.getServerSubErrorCode()
            r9.setCliTelemSubErrorCode(r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.aad.adal.BrokerProxy.getAuthenticationExceptionForResult(java.lang.String, java.lang.String, android.os.Bundle):com.microsoft.aad.adal.AuthenticationException");
    }

    private Bundle getBrokerOptions(AuthenticationRequest authenticationRequest) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.microsoft.aad.adal:RequestId", authenticationRequest.getRequestId());
        bundle.putInt("expiration.buffer", AuthenticationSettings.INSTANCE.getExpirationBuffer());
        bundle.putString("account.authority", authenticationRequest.getAuthority());
        bundle.putString("account.resource", authenticationRequest.getResource());
        bundle.putString("account.redirect", authenticationRequest.getRedirectUri());
        bundle.putString("account.clientid.key", authenticationRequest.getClientId());
        bundle.putString("adal.version.key", authenticationRequest.getVersion());
        bundle.putString("account.userinfo.userid", authenticationRequest.getUserId());
        bundle.putString("account.extra.query.param", authenticationRequest.getExtraQueryParamsAuthentication());
        if (authenticationRequest.getCorrelationId() != null) {
            bundle.putString("account.correlationid", authenticationRequest.getCorrelationId().toString());
        }
        String brokerAccountName = authenticationRequest.getBrokerAccountName();
        if (StringExtensions.isNullOrBlank(brokerAccountName)) {
            brokerAccountName = authenticationRequest.getLoginHint();
        }
        bundle.putString("account.login.hint", brokerAccountName);
        bundle.putString("account.name", brokerAccountName);
        if (authenticationRequest.getPrompt() != null) {
            bundle.putString("account.prompt", authenticationRequest.getPrompt().name());
        }
        if (authenticationRequest.isClaimsChallengePresent() || authenticationRequest.getClientCapabilities() != null) {
            bundle.putString("account.claims", AuthenticationContext.mergeClaimsWithClientCapabilities(authenticationRequest.getClaimsChallenge(), authenticationRequest.getClientCapabilities()));
        }
        if (authenticationRequest.getForceRefresh() || authenticationRequest.isClaimsChallengePresent()) {
            bundle.putString("force.refresh", Boolean.toString(true));
        }
        bundle.putString(MicrosoftTokenRequest.CLIENT_APP_VERSION, authenticationRequest.getAppVersion());
        bundle.putString(MicrosoftTokenRequest.CLIENT_APP_NAME, authenticationRequest.getAppName());
        return bundle;
    }

    private TelemetryUtils.CliTelemInfo getCliTelemInfoFromBundle(Bundle bundle) {
        TelemetryUtils.CliTelemInfo cliTelemInfo = new TelemetryUtils.CliTelemInfo();
        cliTelemInfo._setServerErrorCode(bundle.getString("cliteleminfo.server_error"));
        cliTelemInfo._setServerSubErrorCode(bundle.getString("cliteleminfo.server_suberror"));
        cliTelemInfo._setRefreshTokenAge(bundle.getString("cliteleminfo.rt_age"));
        cliTelemInfo._setSpeRing(bundle.getString("cliteleminfo.spe_ring"));
        return cliTelemInfo;
    }

    private Intent getIntentForBrokerActivityFromAccountManager(Bundle bundle) {
        try {
            return (Intent) this.mAcctManager.addAccount("com.microsoft.workaccount", "adal.authtoken.type", (String[]) null, bundle, (Activity) null, (AccountManagerCallback) null, this.mHandler).getResult().getParcelable("intent");
        } catch (OperationCanceledException e) {
            Logger.m1246e("BrokerProxy:getIntentForBrokerActivityFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.AUTH_FAILED_CANCELLED, e);
        } catch (AuthenticatorException e2) {
            Logger.m1246e("BrokerProxy:getIntentForBrokerActivityFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, e2);
        } catch (IOException e3) {
            Logger.m1246e("BrokerProxy:getIntentForBrokerActivityFromAccountManager", AUTHENTICATOR_CANCELS_REQUEST, BuildConfig.FLAVOR, ADALError.BROKER_AUTHENTICATOR_IO_EXCEPTION, e3);
        }
        return null;
    }

    private AuthenticationResult getResultFromBrokerResponse(Bundle bundle, AuthenticationRequest authenticationRequest) throws AuthenticationException {
        Date date;
        ADALError aDALError;
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            int i = bundle2.getInt("errorCode");
            String string = bundle2.getString("errorMessage");
            String string2 = bundle2.getString(AuthorizationResultFactory.ERROR);
            String string3 = bundle2.getString(AuthorizationResultFactory.ERROR_DESCRIPTION);
            TelemetryUtils.CliTelemInfo cliTelemInfoFromBundle = getCliTelemInfoFromBundle(bundle);
            if (!StringExtensions.isNullOrBlank(string)) {
                if (i != 3) {
                    if (i == 4) {
                        aDALError = ADALError.AUTH_FAILED_CANCELLED;
                    } else if (i == 6) {
                        aDALError = ADALError.BROKER_AUTHENTICATOR_UNSUPPORTED_OPERATION;
                    } else if (i == 7) {
                        aDALError = ADALError.BROKER_AUTHENTICATOR_BAD_ARGUMENTS;
                    } else if (i != 9) {
                        aDALError = ADALError.BROKER_AUTHENTICATOR_ERROR_GETAUTHTOKEN;
                    } else {
                        aDALError = ADALError.BROKER_AUTHENTICATOR_BAD_AUTHENTICATION;
                    }
                } else if (string.contains(ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION.getDescription())) {
                    aDALError = ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION;
                } else if (string.contains(ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE.getDescription())) {
                    aDALError = ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE;
                } else {
                    aDALError = ADALError.BROKER_AUTHENTICATOR_IO_EXCEPTION;
                }
                AuthenticationException authenticationException = new AuthenticationException(aDALError, string);
                authenticationException.setSpeRing(cliTelemInfoFromBundle.getSpeRing());
                authenticationException.setRefreshTokenAge(cliTelemInfoFromBundle.getRefreshTokenAge());
                authenticationException.setCliTelemErrorCode(cliTelemInfoFromBundle.getServerErrorCode());
                authenticationException.setCliTelemSubErrorCode(cliTelemInfoFromBundle.getServerSubErrorCode());
                throw authenticationException;
            } else if (StringExtensions.isNullOrBlank(string2) || !authenticationRequest.isSilent()) {
                if (bundle2.getBoolean("account.initial.request")) {
                    return AuthenticationResult.createResultForInitialRequest(authenticationRequest.getClientId());
                }
                UserInfo userInfoFromBrokerResult = UserInfo.getUserInfoFromBrokerResult(bundle);
                String string4 = bundle2.getString("account.userinfo.tenantid", BuildConfig.FLAVOR);
                if (bundle2.getLong("account.expiredate") == 0) {
                    Logger.m1250v("BrokerProxy:getResultFromBrokerResponse", "Broker doesn't return expire date, set it current date plus one hour");
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.add(13, 3600);
                    date = gregorianCalendar.getTime();
                } else {
                    date = new Date(bundle2.getLong("account.expiredate"));
                }
                AuthenticationResult authenticationResult = new AuthenticationResult(bundle2.getString("authtoken"), BuildConfig.FLAVOR, date, false, userInfoFromBrokerResult, string4, BuildConfig.FLAVOR, (Date) null, authenticationRequest.getClientId());
                authenticationResult.setCliTelemInfo(cliTelemInfoFromBundle);
                return authenticationResult;
            } else {
                AuthenticationException authenticationExceptionForResult = getAuthenticationExceptionForResult(string2, string3, bundle2);
                Serializable serializable = bundle2.getSerializable("response_body");
                Serializable serializable2 = bundle2.getSerializable("response_headers");
                if (serializable != null && (serializable instanceof HashMap)) {
                    authenticationExceptionForResult.setHttpResponseBody((HashMap) serializable);
                }
                if (serializable2 != null && (serializable2 instanceof HashMap)) {
                    authenticationExceptionForResult.setHttpResponseHeaders((HashMap) serializable2);
                }
                authenticationExceptionForResult.setServiceStatusCode(bundle2.getInt("status_code"));
                throw authenticationExceptionForResult;
            }
        } else {
            throw new IllegalArgumentException("bundleResult");
        }
    }

    private Account getTargetAccount(AuthenticationRequest authenticationRequest) {
        Account[] accountsByType = this.mAcctManager.getAccountsByType("com.microsoft.workaccount");
        if (!TextUtils.isEmpty(authenticationRequest.getBrokerAccountName())) {
            return findAccount(authenticationRequest.getBrokerAccountName(), accountsByType);
        }
        try {
            UserInfo findUserInfo = findUserInfo(authenticationRequest.getUserId(), getBrokerUsers());
            if (findUserInfo != null) {
                return findAccount(findUserInfo.getDisplayableId(), accountsByType);
            }
            return null;
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            Logger.m1246e("BrokerProxy:getTargetAccount", "Exception is thrown when trying to get target account.", e.getMessage(), ADALError.BROKER_AUTHENTICATOR_IO_EXCEPTION, e);
            return null;
        }
    }

    private UserInfo[] getUserInfoFromAccountManager() throws OperationCanceledException, AuthenticatorException, IOException {
        Account[] accountsByType = this.mAcctManager.getAccountsByType("com.microsoft.workaccount");
        Bundle bundle = new Bundle();
        bundle.putBoolean(DATA_USER_INFO, true);
        StringBuilder a = Eo.a("Retrieve all the accounts from account manager with broker account type, and the account length is: ");
        a.append(accountsByType.length);
        Logger.m1250v("BrokerProxy:getUserInfoFromAccountManager", a.toString());
        UserInfo[] userInfoArr = new UserInfo[accountsByType.length];
        for (int i = 0; i < accountsByType.length; i++) {
            AccountManagerFuture<Bundle> updateCredentials = this.mAcctManager.updateCredentials(accountsByType[i], "adal.authtoken.type", bundle, (Activity) null, (AccountManagerCallback) null, (Handler) null);
            Logger.m1250v("BrokerProxy:getUserInfoFromAccountManager", "Waiting for userinfo retrieval result from Broker.");
            Bundle result = updateCredentials.getResult();
            userInfoArr[i] = new UserInfo(result.getString("account.userinfo.userid"), result.getString("account.userinfo.given.name"), result.getString("account.userinfo.family.name"), result.getString("account.userinfo.identity.provider"), result.getString("account.userinfo.userid.displayable"));
        }
        return userInfoArr;
    }

    private boolean hasSupportToAddUserThroughBroker(String str) {
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setClassName(str, str + ".ui.AccountChooserActivity");
        if (this.mContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isBrokerAccountServiceSupported() {
        return isServiceSupported(this.mContext, BrokerAccountServiceHandler.getIntentForBrokerAccountService(this.mContext));
    }

    private boolean isBrokerWithPRTSupport(Intent intent) {
        if (intent != null) {
            return "v2".equalsIgnoreCase(intent.getStringExtra("broker.version"));
        }
        throw new IllegalArgumentException("intent");
    }

    private boolean isServiceSupported(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices;
        if (intent == null || (queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0)) == null || queryIntentServices.size() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void removeAccountFromAccountManager() {
        Logger.m1250v("BrokerProxy:removeAccountFromAccountManager", "Try to remove account from account manager.");
        Account[] accountsByType = this.mAcctManager.getAccountsByType("com.microsoft.workaccount");
        if (accountsByType.length != 0) {
            for (Account account : accountsByType) {
                StringBuilder a = Eo.a("Account: ");
                a.append(account.name);
                Logger.m1251v("BrokerProxy:removeAccountFromAccountManager", "Remove tokens for account. ", a.toString(), (ADALError) null);
                Bundle bundle = new Bundle();
                bundle.putString("account.remove.tokens", "account.remove.tokens.value");
                this.mAcctManager.getAuthToken(account, "adal.authtoken.type", bundle, false, (AccountManagerCallback) null, this.mHandler);
            }
        }
    }

    private boolean verifyAccount(Account[] accountArr, String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str)) {
            return str.equalsIgnoreCase(accountArr[0].name);
        }
        if (StringExtensions.isNullOrBlank(str2)) {
            return true;
        }
        try {
            if (findUserInfo(str2, getBrokerUsers()) != null) {
                return true;
            }
            return false;
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            Logger.m1246e("BrokerProxy:verifyAccount", "Exception thrown when verifying accounts in broker. ", e.getMessage(), ADALError.BROKER_AUTHENTICATOR_EXCEPTION, e);
            Logger.m1250v("BrokerProxy:verifyAccount", "It could not check the uniqueid from broker. It is not using broker");
            return false;
        }
    }

    private boolean verifyAuthenticator(AccountManager accountManager) {
        for (AuthenticatorDescription authenticatorDescription : accountManager.getAuthenticatorTypes()) {
            if (authenticatorDescription.type.equals("com.microsoft.workaccount") && this.mBrokerValidator.verifySignature(authenticatorDescription.packageName)) {
                return true;
            }
        }
        return false;
    }

    private void verifyNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.mContext.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Logger.m1246e(TAG, "calling this from your main thread can lead to deadlock and/or ANRs", BuildConfig.FLAVOR, ADALError.DEVELOPER_CALLING_ON_MAIN_THREAD, illegalStateException);
            if (this.mContext.getApplicationInfo().targetSdkVersion >= 8) {
                throw illegalStateException;
            }
        }
    }

    public SwitchToBroker canSwitchToBroker(String str) {
        try {
            boolean z = true;
            boolean z2 = AuthenticationSettings.INSTANCE.getUseBroker() && verifyAuthenticator(this.mAcctManager) && !UrlExtensions.isADFSAuthority(new URL(str));
            if (!z2) {
                Logger.m1250v("BrokerProxy:canSwitchToBroker", "Broker auth is turned off or no valid broker is available on the device, cannot switch to broker.");
                return SwitchToBroker.CANNOT_SWITCH_TO_BROKER;
            }
            if (!isBrokerAccountServiceSupported()) {
                if (!z2 || !checkAccount(this.mAcctManager, BuildConfig.FLAVOR, BuildConfig.FLAVOR)) {
                    z = false;
                }
                if (!z) {
                    Logger.m1250v("BrokerProxy:canSwitchToBroker", "No valid account existed in broker, cannot switch to broker for auth.");
                    return SwitchToBroker.CANNOT_SWITCH_TO_BROKER;
                }
                try {
                    verifyBrokerPermissionsAPI23AndHigher();
                } catch (UsageAuthenticationException unused) {
                    Logger.m1250v("BrokerProxy:canSwitchToBroker", "Missing GET_ACCOUNTS permission, cannot switch to broker.");
                    return SwitchToBroker.NEED_PERMISSIONS_TO_SWITCH_TO_BROKER;
                }
            }
            return SwitchToBroker.CAN_SWITCH_TO_BROKER;
        } catch (MalformedURLException unused2) {
            ADALError aDALError = ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL;
            throw new IllegalArgumentException("DEVELOPER_AUTHORITY_IS_NOT_VALID_URL");
        }
    }

    public boolean canUseLocalCache(String str) {
        if (canSwitchToBroker(str) == SwitchToBroker.CANNOT_SWITCH_TO_BROKER) {
            Logger.m1250v("BrokerProxy:canUseLocalCache", "It does not use broker");
            return true;
        }
        if (!this.mBrokerValidator.verifySignature(this.mContext.getPackageName())) {
            return false;
        }
        Logger.m1250v("BrokerProxy:canUseLocalCache", "Broker installer can use local cache");
        return true;
    }

    public AuthenticationResult getAuthTokenInBackground(AuthenticationRequest authenticationRequest, BrokerEvent brokerEvent) throws AuthenticationException {
        Bundle bundle;
        verifyNotOnMainThread();
        Bundle brokerOptions = getBrokerOptions(authenticationRequest);
        if (isBrokerAccountServiceSupported()) {
            bundle = BrokerAccountServiceHandler.getInstance().getAuthToken(this.mContext, brokerOptions, brokerEvent);
        } else {
            bundle = getAuthTokenFromAccountManager(authenticationRequest, brokerOptions);
        }
        if (bundle != null) {
            return getResultFromBrokerResponse(bundle, authenticationRequest);
        }
        Logger.m1250v(TAG, "No bundle result returned from broker for silent request.");
        return null;
    }

    public String getBrokerAppVersion(String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
        StringBuilder a = Eo.a("VersionName=");
        a.append(packageInfo.versionName);
        a.append(";VersonCode=");
        return Eo.a(a, packageInfo.versionCode, ".");
    }

    public UserInfo[] getBrokerUsers() throws OperationCanceledException, AuthenticatorException, IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalArgumentException("Calling getBrokerUsers on main thread");
        } else if (isBrokerAccountServiceSupported()) {
            return BrokerAccountServiceHandler.getInstance().getBrokerUsers(this.mContext);
        } else {
            return getUserInfoFromAccountManager();
        }
    }

    public String getCurrentActiveBrokerPackageName() {
        for (AuthenticatorDescription authenticatorDescription : this.mAcctManager.getAuthenticatorTypes()) {
            if (authenticatorDescription.type.equals("com.microsoft.workaccount")) {
                return authenticatorDescription.packageName;
            }
        }
        return null;
    }

    public String getCurrentUser() {
        if (isBrokerAccountServiceSupported()) {
            verifyNotOnMainThread();
            try {
                UserInfo[] brokerUsers = BrokerAccountServiceHandler.getInstance().getBrokerUsers(this.mContext);
                if (brokerUsers.length == 0) {
                    return null;
                }
                return brokerUsers[0].getDisplayableId();
            } catch (IOException e) {
                Logger.m1246e("BrokerProxy:getCurrentUser", "No current user could be retrieved.", BuildConfig.FLAVOR, (ADALError) null, e);
                return null;
            }
        } else {
            Account[] accountsByType = this.mAcctManager.getAccountsByType("com.microsoft.workaccount");
            if (accountsByType.length > 0) {
                return accountsByType[0].name;
            }
            return null;
        }
    }

    public Intent getIntentForBrokerActivity(AuthenticationRequest authenticationRequest, BrokerEvent brokerEvent) throws AuthenticationException {
        Intent intent;
        Bundle brokerOptions = getBrokerOptions(authenticationRequest);
        if (isBrokerAccountServiceSupported()) {
            intent = BrokerAccountServiceHandler.getInstance().getIntentForInteractiveRequest(this.mContext, brokerEvent);
            if (intent != null) {
                intent.putExtras(brokerOptions);
            } else {
                Logger.m1245e(TAG, "Received null intent from broker interactive request.", (String) null, ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING);
                throw new AuthenticationException(ADALError.BROKER_AUTHENTICATOR_NOT_RESPONDING, "Received null intent from broker interactive request.");
            }
        } else {
            intent = getIntentForBrokerActivityFromAccountManager(brokerOptions);
        }
        if (intent != null) {
            intent.putExtra("com.microsoft.aadbroker.adal.broker.request", "com.microsoft.aadbroker.adal.broker.request");
            if (!isBrokerWithPRTSupport(intent) && PromptBehavior.FORCE_PROMPT == authenticationRequest.getPrompt()) {
                Logger.m1250v("BrokerProxy:getIntentForBrokerActivity", "FORCE_PROMPT is set for broker auth via old version of broker app, reset to ALWAYS.");
                PromptBehavior promptBehavior = PromptBehavior.Always;
                intent.putExtra("account.prompt", "Always");
            }
        }
        return intent;
    }

    public void removeAccounts() {
        new Thread(new Runnable() {
            public void run() {
                if (BrokerProxy.this.isBrokerAccountServiceSupported()) {
                    BrokerAccountServiceHandler.getInstance().removeAccounts(BrokerProxy.this.mContext);
                } else {
                    BrokerProxy.this.removeAccountFromAccountManager();
                }
            }
        }).start();
    }

    public void saveAccount(String str) {
        if (str != null && !str.isEmpty()) {
            SharedPreferencesFileManager sharedPreferencesFileManager = new SharedPreferencesFileManager(this.mContext, KEY_SHARED_PREF_ACCOUNT_LIST);
            String string = sharedPreferencesFileManager.getString(KEY_APP_ACCOUNTS_FOR_TOKEN_REMOVAL);
            if (string == null) {
                string = BuildConfig.FLAVOR;
            }
            if (!string.contains(KEY_ACCOUNT_LIST_DELIM + str)) {
                sharedPreferencesFileManager.putString(KEY_APP_ACCOUNTS_FOR_TOKEN_REMOVAL, string + KEY_ACCOUNT_LIST_DELIM + str);
            }
        }
    }

    public boolean verifyBrokerForSilentRequest(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        SwitchToBroker canSwitchToBroker = canSwitchToBroker(authenticationRequest.getAuthority());
        if (canSwitchToBroker == SwitchToBroker.CAN_SWITCH_TO_BROKER) {
            return verifyUser(authenticationRequest.getLoginHint(), authenticationRequest.getUserId());
        }
        if (canSwitchToBroker != SwitchToBroker.NEED_PERMISSIONS_TO_SWITCH_TO_BROKER) {
            return false;
        }
        throw new UsageAuthenticationException(ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING, "Broker related permissions are missing for GET_ACCOUNTS");
    }

    public boolean verifyBrokerPermissionsAPI22AndLess() throws UsageAuthenticationException {
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT < 23) {
            sb.append(checkPermission("android.permission.GET_ACCOUNTS"));
            sb.append(checkPermission("android.permission.MANAGE_ACCOUNTS"));
            sb.append(checkPermission("android.permission.USE_CREDENTIALS"));
            if (sb.length() == 0) {
                return true;
            }
            ADALError aDALError = ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING;
            StringBuilder a = Eo.a("Broker related permissions are missing for ");
            a.append(sb.toString());
            throw new UsageAuthenticationException(aDALError, a.toString());
        }
        Logger.m1250v(TAG, "Device runs on 23 and above, skip the check for 22 and below.");
        return true;
    }

    @TargetApi(23)
    public boolean verifyBrokerPermissionsAPI23AndHigher() throws UsageAuthenticationException {
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= 23) {
            sb.append(checkPermission("android.permission.GET_ACCOUNTS"));
            if (sb.length() == 0) {
                return true;
            }
            ADALError aDALError = ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING;
            StringBuilder a = Eo.a("Broker related permissions are missing for ");
            a.append(sb.toString());
            throw new UsageAuthenticationException(aDALError, a.toString());
        }
        Logger.m1250v(TAG, "Device is lower than 23, skip the GET_ACCOUNTS permission check.");
        return true;
    }

    public boolean verifyUser(String str, String str2) {
        if (!isBrokerAccountServiceSupported()) {
            return checkAccount(this.mAcctManager, str, str2);
        }
        return true;
    }

    public BrokerProxy(Context context) {
        this.mContext = context;
        this.mAcctManager = AccountManager.get(this.mContext);
        this.mHandler = new Handler(this.mContext.getMainLooper());
        this.mBrokerValidator = new BrokerValidator(context);
    }
}
