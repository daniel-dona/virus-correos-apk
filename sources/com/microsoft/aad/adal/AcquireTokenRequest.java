package com.microsoft.aad.adal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.BrokerProxy;
import com.microsoft.aad.adal.TelemetryUtils;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectory;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectoryCloud;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
public class AcquireTokenRequest {
    public static final long AUTHENTICATOR_LLT_VERSION_CODE = 138;
    public static final long CP_LLT_VERSION_CODE = 2950722;
    public static final String TAG = "AcquireTokenRequest";
    public static final ExecutorService THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
    public static Handler sHandler = null;
    public APIEvent mAPIEvent;
    public final AuthenticationContext mAuthContext;
    public final IBrokerProxy mBrokerProxy;
    public final Context mContext;
    public Discovery mDiscovery = new Discovery(this.mContext);
    public TokenCacheAccessor mTokenCacheAccessor;

    /* compiled from: PG */
    public static class CallbackHandler {
        public AuthenticationCallback<AuthenticationResult> mCallback;
        public Handler mRefHandler;

        public CallbackHandler(Handler handler, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
            this.mRefHandler = handler;
            this.mCallback = authenticationCallback;
        }

        public AuthenticationCallback<AuthenticationResult> getCallback() {
            return this.mCallback;
        }

        public void onError(final AuthenticationException authenticationException) {
            AuthenticationCallback<AuthenticationResult> authenticationCallback = this.mCallback;
            if (authenticationCallback != null) {
                Handler handler = this.mRefHandler;
                if (handler != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            CallbackHandler.this.mCallback.onError(authenticationException);
                        }
                    });
                } else {
                    authenticationCallback.onError(authenticationException);
                }
            }
        }

        public void onSuccess(final AuthenticationResult authenticationResult) {
            AuthenticationCallback<AuthenticationResult> authenticationCallback = this.mCallback;
            if (authenticationCallback != null) {
                Handler handler = this.mRefHandler;
                if (handler != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            CallbackHandler.this.mCallback.onSuccess(authenticationResult);
                        }
                    });
                } else {
                    authenticationCallback.onSuccess(authenticationResult);
                }
            }
        }
    }

    public AcquireTokenRequest(Context context, AuthenticationContext authenticationContext, APIEvent aPIEvent) {
        this.mContext = context;
        this.mAuthContext = authenticationContext;
        if (!(authenticationContext.getCache() == null || aPIEvent == null)) {
            this.mTokenCacheAccessor = new TokenCacheAccessor(context.getApplicationContext(), authenticationContext.getCache(), authenticationContext.getAuthority(), aPIEvent.getTelemetryRequestId());
            this.mTokenCacheAccessor.setValidateAuthorityHost(this.mAuthContext.getValidateAuthority());
        }
        this.mBrokerProxy = new BrokerProxy(context);
        this.mAPIEvent = aPIEvent;
    }

    private void acquireTokenInteractiveFlow(CallbackHandler callbackHandler, IWindowComponent iWindowComponent, boolean z, AuthenticationRequest authenticationRequest) throws AuthenticationException {
        if (iWindowComponent != null || z) {
            HttpWebRequest.throwIfNetworkNotAvailable(this.mContext);
            int hashCode = callbackHandler.getCallback().hashCode();
            authenticationRequest.setRequestId(hashCode);
            this.mAuthContext.putWaitingRequest(hashCode, new AuthenticationRequestState(hashCode, authenticationRequest, callbackHandler.getCallback(), this.mAPIEvent));
            BrokerProxy.SwitchToBroker canSwitchToBroker = this.mBrokerProxy.canSwitchToBroker(authenticationRequest.getAuthority());
            AuthenticationDialog authenticationDialog = null;
            if (canSwitchToBroker == BrokerProxy.SwitchToBroker.CANNOT_SWITCH_TO_BROKER || !this.mBrokerProxy.verifyUser(authenticationRequest.getLoginHint(), authenticationRequest.getUserId())) {
                String a = Eo.a(new StringBuilder(), TAG, ":acquireTokenInteractiveFlow");
                StringBuilder a2 = Eo.a(" Callback is: ");
                a2.append(callbackHandler.getCallback().hashCode());
                Logger.m1251v(a, "Starting Authentication Activity for embedded flow. ", a2.toString(), (ADALError) null);
                AcquireTokenInteractiveRequest acquireTokenInteractiveRequest = new AcquireTokenInteractiveRequest(this.mContext, authenticationRequest, this.mTokenCacheAccessor);
                if (z) {
                    authenticationDialog = new AuthenticationDialog(getHandler(), this.mContext, this, authenticationRequest);
                }
                acquireTokenInteractiveRequest.acquireToken(iWindowComponent, authenticationDialog);
            } else if (canSwitchToBroker != BrokerProxy.SwitchToBroker.NEED_PERMISSIONS_TO_SWITCH_TO_BROKER) {
                String a3 = Eo.a(new StringBuilder(), TAG, ":acquireTokenInteractiveFlow");
                StringBuilder a4 = Eo.a(BuildConfig.FLAVOR);
                a4.append(callbackHandler.getCallback().hashCode());
                Logger.m1251v(a3, "Launch activity for interactive authentication via broker with callback. ", a4.toString(), (ADALError) null);
                new AcquireTokenWithBrokerRequest(authenticationRequest, this.mBrokerProxy).acquireTokenWithBrokerInteractively(iWindowComponent);
            } else {
                throw new UsageAuthenticationException(ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING, "Broker related permissions are missing for GET_ACCOUNTS");
            }
        } else {
            ADALError aDALError = ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED;
            throw new AuthenticationException(aDALError, authenticationRequest.getLogInfo() + " Cannot launch webview, activity is null.");
        }
    }

    private AuthenticationResult acquireTokenSilentFlow(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        boolean verifyBrokerForSilentRequest = this.mBrokerProxy.verifyBrokerForSilentRequest(authenticationRequest);
        if ((authenticationRequest.getForceRefresh() || authenticationRequest.isClaimsChallengePresent()) && verifyBrokerForSilentRequest) {
            return tryAcquireTokenSilentWithBroker(authenticationRequest);
        }
        AuthenticationResult tryAcquireTokenSilentLocally = tryAcquireTokenSilentLocally(authenticationRequest);
        return (!isAccessTokenReturned(tryAcquireTokenSilentLocally) && verifyBrokerForSilentRequest) ? tryAcquireTokenSilentWithBroker(authenticationRequest) : tryAcquireTokenSilentLocally;
    }

    private void addHttpInfoToException(AuthenticationResult authenticationResult, AuthenticationException authenticationException) {
        if (authenticationResult != null && authenticationException != null) {
            if (authenticationResult.getHttpResponseHeaders() != null) {
                authenticationException.setHttpResponseHeaders(authenticationResult.getHttpResponseHeaders());
            }
            if (authenticationResult.getHttpResponseBody() != null) {
                authenticationException.setHttpResponseBody(authenticationResult.getHttpResponseBody());
            }
            authenticationException.setServiceStatusCode(authenticationResult.getServiceStatusCode());
        }
    }

    private boolean checkIfBrokerHasLltChanges() {
        int i;
        PackageManager packageManager = this.mContext.getPackageManager();
        int i2 = Integer.MAX_VALUE;
        try {
            i = packageManager.getPackageInfo("com.azure.authenticator", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = Integer.MAX_VALUE;
        }
        try {
            i2 = packageManager.getPackageInfo("com.microsoft.windowsintune.companyportal", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        if (((long) i) < 138 || ((long) i2) < CP_LLT_VERSION_CODE) {
            return false;
        }
        return true;
    }

    private synchronized Handler getHandler() {
        if (sHandler == null) {
            sHandler = new Handler(Looper.getMainLooper());
        }
        return sHandler;
    }

    private boolean isAccessTokenReturned(AuthenticationResult authenticationResult) {
        return authenticationResult != null && !StringExtensions.isNullOrBlank(authenticationResult.getAccessToken());
    }

    /* access modifiers changed from: private */
    public void performAcquireTokenRequest(CallbackHandler callbackHandler, IWindowComponent iWindowComponent, boolean z, AuthenticationRequest authenticationRequest) throws AuthenticationException {
        AuthenticationResult tryAcquireTokenSilent = tryAcquireTokenSilent(authenticationRequest);
        if (isAccessTokenReturned(tryAcquireTokenSilent)) {
            this.mAPIEvent.setWasApiCallSuccessful(true, (Exception) null);
            this.mAPIEvent.setCorrelationId(authenticationRequest.getCorrelationId().toString());
            this.mAPIEvent.setIdToken(tryAcquireTokenSilent.getIdToken());
            this.mAPIEvent.stopTelemetryAndFlush();
            callbackHandler.onSuccess(tryAcquireTokenSilent);
            return;
        }
        Logger.m1243d(TAG + ":performAcquireTokenRequest", "Trying to acquire token interactively.");
        acquireTokenInteractiveFlow(callbackHandler, iWindowComponent, z, authenticationRequest);
    }

    private void performAuthorityValidation(AuthenticationRequest authenticationRequest, URL url) throws AuthenticationException {
        C0336Telemetry.getInstance().startEvent(authenticationRequest.getTelemetryRequestId(), "Microsoft.ADAL.authority_validation");
        APIEvent aPIEvent = new APIEvent("Microsoft.ADAL.authority_validation");
        aPIEvent.setCorrelationId(authenticationRequest.getCorrelationId().toString());
        aPIEvent.setRequestId(authenticationRequest.getTelemetryRequestId());
        if (this.mAuthContext.getValidateAuthority()) {
            try {
                validateAuthority(url, authenticationRequest.getUpnSuffix(), authenticationRequest.isSilent(), authenticationRequest.getCorrelationId());
                aPIEvent.setValidationStatus("yes");
                C0336Telemetry.getInstance().stopEvent(authenticationRequest.getTelemetryRequestId(), aPIEvent, "Microsoft.ADAL.authority_validation");
            } catch (AuthenticationException e) {
                if (e.getCode() == null || (!e.getCode().equals(ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE) && !e.getCode().equals(ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION))) {
                    aPIEvent.setValidationStatus("no");
                } else {
                    aPIEvent.setValidationStatus("not_done");
                }
                throw e;
            } catch (Throwable th) {
                C0336Telemetry.getInstance().stopEvent(authenticationRequest.getTelemetryRequestId(), aPIEvent, "Microsoft.ADAL.authority_validation");
                throw th;
            }
        } else {
            if (!UrlExtensions.isADFSAuthority(url) && !AuthorityValidationMetadataCache.containsAuthorityHost(url)) {
                try {
                    this.mDiscovery.validateAuthority(url);
                } catch (AuthenticationException unused) {
                    AuthorityValidationMetadataCache.updateInstanceDiscoveryMap(url.getHost(), new InstanceDiscoveryMetadata(false));
                    AzureActiveDirectory.putCloud(url.getHost(), new AzureActiveDirectoryCloud(false));
                    Eo.c(new StringBuilder(), TAG, ":performAuthorityValidation", "Fail to get authority validation metadata back. Ignore the failure since authority validation is turned off.");
                }
            }
            aPIEvent.setValidationStatus("not_done");
            C0336Telemetry.getInstance().stopEvent(authenticationRequest.getTelemetryRequestId(), aPIEvent, "Microsoft.ADAL.authority_validation");
        }
        InstanceDiscoveryMetadata cachedInstanceDiscoveryMetadata = AuthorityValidationMetadataCache.getCachedInstanceDiscoveryMetadata(url);
        if (cachedInstanceDiscoveryMetadata != null && cachedInstanceDiscoveryMetadata.isValidated()) {
            updatePreferredNetworkLocation(url, authenticationRequest, cachedInstanceDiscoveryMetadata);
        }
    }

    private void removeTokensForUser(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String str;
        if (this.mTokenCacheAccessor != null) {
            if (!StringExtensions.isNullOrBlank(authenticationRequest.getUserId())) {
                str = authenticationRequest.getUserId();
            } else {
                str = authenticationRequest.getLoginHint();
            }
            try {
                TokenCacheItem fRTItem = this.mTokenCacheAccessor.getFRTItem("1", str);
                if (fRTItem != null) {
                    this.mTokenCacheAccessor.removeTokenCacheItem(fRTItem, authenticationRequest.getResource());
                }
                try {
                    TokenCacheItem mRRTItem = this.mTokenCacheAccessor.getMRRTItem(authenticationRequest.getClientId(), str);
                    TokenCacheItem regularRefreshTokenCacheItem = this.mTokenCacheAccessor.getRegularRefreshTokenCacheItem(authenticationRequest.getResource(), authenticationRequest.getClientId(), str);
                    if (mRRTItem != null) {
                        this.mTokenCacheAccessor.removeTokenCacheItem(mRRTItem, authenticationRequest.getResource());
                    } else if (regularRefreshTokenCacheItem != null) {
                        this.mTokenCacheAccessor.removeTokenCacheItem(regularRefreshTokenCacheItem, authenticationRequest.getResource());
                    } else {
                        Eo.c(new StringBuilder(), TAG, ":removeTokensForUser", "No token items need to be deleted for the user.");
                    }
                } catch (MalformedURLException e) {
                    throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
                }
            } catch (MalformedURLException e2) {
                throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e2.getMessage(), (Throwable) e2);
            }
        }
    }

    private boolean shouldTrySilentFlow(AuthenticationRequest authenticationRequest) {
        boolean checkIfBrokerHasLltChanges = authenticationRequest.isClaimsChallengePresent() ? checkIfBrokerHasLltChanges() : true;
        if (authenticationRequest.isSilent()) {
            return true;
        }
        if (!checkIfBrokerHasLltChanges || authenticationRequest.getPrompt() != PromptBehavior.Auto) {
            return false;
        }
        return true;
    }

    private AuthenticationResult tryAcquireTokenSilent(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String str;
        if (!shouldTrySilentFlow(authenticationRequest)) {
            return null;
        }
        Eo.c(new StringBuilder(), TAG, ":tryAcquireTokenSilent", "Try to acquire token silently, return valid AT or use RT in the cache.");
        AuthenticationResult acquireTokenSilentFlow = acquireTokenSilentFlow(authenticationRequest);
        boolean isAccessTokenReturned = isAccessTokenReturned(acquireTokenSilentFlow);
        if (!isAccessTokenReturned && authenticationRequest.isSilent()) {
            if (acquireTokenSilentFlow == null) {
                str = "No result returned from acquireTokenSilent";
            } else {
                StringBuilder a = Eo.a(" ErrorCode:");
                a.append(acquireTokenSilentFlow.getErrorCode());
                str = a.toString();
            }
            Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":tryAcquireTokenSilent"), Eo.a("Prompt is not allowed and failed to get token. ", str), authenticationRequest.getLogInfo(), ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED);
            ADALError aDALError = ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED;
            AuthenticationException authenticationException = new AuthenticationException(aDALError, authenticationRequest.getLogInfo() + " " + str);
            addHttpInfoToException(acquireTokenSilentFlow, authenticationException);
            throw authenticationException;
        } else if (!isAccessTokenReturned) {
            return acquireTokenSilentFlow;
        } else {
            Eo.c(new StringBuilder(), TAG, ":tryAcquireTokenSilent", "Token is successfully returned from silent flow. ");
            return acquireTokenSilentFlow;
        }
    }

    private AuthenticationResult tryAcquireTokenSilentLocally(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        Logger.m1250v(TAG + ":tryAcquireTokenSilentLocally", "Try to silently get token from local cache.");
        return new AcquireTokenSilentHandler(this.mContext, authenticationRequest, this.mTokenCacheAccessor).getAccessToken();
    }

    private AuthenticationResult tryAcquireTokenSilentWithBroker(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        Logger.m1243d(TAG + ":tryAcquireTokenSilentWithBroker", "Either could not get tokens from local cache or is force refresh request, switch to Broker for auth, clear tokens from local cache for the user.");
        removeTokensForUser(authenticationRequest);
        return new AcquireTokenWithBrokerRequest(authenticationRequest, this.mBrokerProxy).acquireTokenWithBrokerSilent();
    }

    private void updatePreferredNetworkLocation(URL url, AuthenticationRequest authenticationRequest, InstanceDiscoveryMetadata instanceDiscoveryMetadata) throws AuthenticationException {
        if (instanceDiscoveryMetadata != null && instanceDiscoveryMetadata.isValidated() && instanceDiscoveryMetadata.getPreferredNetwork() != null && !url.getHost().equalsIgnoreCase(instanceDiscoveryMetadata.getPreferredNetwork())) {
            try {
                authenticationRequest.setAuthority(Discovery.constructAuthorityUrl(url, instanceDiscoveryMetadata.getPreferredNetwork()).toString());
            } catch (MalformedURLException unused) {
                Logger.m1248i(TAG, "preferred network is invalid", "use exactly the same authority url that is passed");
            }
        }
    }

    /* access modifiers changed from: private */
    public void validateAcquireTokenRequest(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        URL url = StringExtensions.getUrl(authenticationRequest.getAuthority());
        if (url != null) {
            performAuthorityValidation(authenticationRequest, url);
            BrokerProxy.SwitchToBroker canSwitchToBroker = this.mBrokerProxy.canSwitchToBroker(authenticationRequest.getAuthority());
            if (canSwitchToBroker != BrokerProxy.SwitchToBroker.CANNOT_SWITCH_TO_BROKER && this.mBrokerProxy.verifyUser(authenticationRequest.getLoginHint(), authenticationRequest.getUserId()) && !authenticationRequest.isSilent()) {
                if (canSwitchToBroker != BrokerProxy.SwitchToBroker.NEED_PERMISSIONS_TO_SWITCH_TO_BROKER) {
                    verifyBrokerRedirectUri(authenticationRequest);
                    return;
                }
                throw new UsageAuthenticationException(ADALError.DEVELOPER_BROKER_PERMISSIONS_MISSING, "Broker related permissions are missing for GET_ACCOUNTS.");
            }
            return;
        }
        throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL);
    }

    private void validateAuthority(URL url, String str, boolean z, UUID uuid) throws AuthenticationException {
        boolean isADFSAuthority = UrlExtensions.isADFSAuthority(url);
        if (AuthorityValidationMetadataCache.isAuthorityValidated(url)) {
            return;
        }
        if (!isADFSAuthority || !this.mAuthContext.getIsAuthorityValidated()) {
            Eo.c(new StringBuilder(), TAG, ":validateAuthority", "Start validating authority");
            this.mDiscovery.setCorrelationId(uuid);
            Discovery.verifyAuthorityValidInstance(url);
            if (z || !isADFSAuthority || str == null) {
                if (z && UrlExtensions.isADFSAuthority(url)) {
                    Eo.c(new StringBuilder(), TAG, ":validateAuthority", "Silent request. Skipping AD FS authority validation");
                }
                this.mDiscovery.validateAuthority(url);
            } else {
                this.mDiscovery.validateAuthorityADFS(url, str);
            }
            Eo.c(new StringBuilder(), TAG, ":validateAuthority", "The passed in authority is valid.");
            this.mAuthContext.setIsAuthorityValidated(true);
        }
    }

    private void verifyBrokerRedirectUri(AuthenticationRequest authenticationRequest) throws UsageAuthenticationException {
        String redirectUri = authenticationRequest.getRedirectUri();
        String redirectUriForBroker = this.mAuthContext.getRedirectUriForBroker();
        if (StringExtensions.isNullOrBlank(redirectUri)) {
            Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":verifyBrokerRedirectUri"), "The redirectUri is null or blank. ", Eo.a("The redirect uri is expected to be:", redirectUriForBroker), ADALError.DEVELOPER_REDIRECTURI_INVALID);
            throw new UsageAuthenticationException(ADALError.DEVELOPER_REDIRECTURI_INVALID, "The redirectUri is null or blank.");
        } else if (redirectUri.equalsIgnoreCase("urn:ietf:wg:oauth:2.0:oob")) {
            Logger.info(TAG + ":verifyBrokerRedirectUri", "This is a broker redirectUri. Bypass the check.");
        } else if (redirectUri.startsWith("msauth://")) {
            PackageHelper packageHelper = new PackageHelper(this.mContext);
            try {
                String encode = URLEncoder.encode(this.mContext.getPackageName(), "UTF-8");
                String encode2 = URLEncoder.encode(packageHelper.getCurrentSignatureForPackage(this.mContext.getPackageName()), "UTF-8");
                if (!redirectUri.startsWith("msauth://" + encode + "/")) {
                    Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":verifyBrokerRedirectUri"), "The base64 url encoded package name component of the redirect uri does not match the expected value. ", Eo.a("This apps package name is: ", encode, " so the redirect uri is expected to be: ", redirectUriForBroker), ADALError.DEVELOPER_REDIRECTURI_INVALID);
                    throw new UsageAuthenticationException(ADALError.DEVELOPER_REDIRECTURI_INVALID, "The base64 url encoded package name component of the redirect uri does not match the expected value. ");
                } else if (redirectUri.equalsIgnoreCase(redirectUriForBroker)) {
                    Eo.c(new StringBuilder(), TAG, ":verifyBrokerRedirectUri", "The broker redirect URI is valid.");
                } else {
                    Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":verifyBrokerRedirectUri"), "The base64 url encoded signature component of the redirect uri does not match the expected value. ", Eo.a("This apps signature is: ", encode2, " so the redirect uri is expected to be: ", redirectUriForBroker), ADALError.DEVELOPER_REDIRECTURI_INVALID);
                    throw new UsageAuthenticationException(ADALError.DEVELOPER_REDIRECTURI_INVALID, "The base64 url encoded signature component of the redirect uri does not match the expected value.");
                }
            } catch (UnsupportedEncodingException e) {
                Logger.m1246e(Eo.a(new StringBuilder(), TAG, ":verifyBrokerRedirectUri"), ADALError.ENCODING_IS_NOT_SUPPORTED.getDescription(), e.getMessage(), ADALError.ENCODING_IS_NOT_SUPPORTED, e);
                throw new UsageAuthenticationException(ADALError.ENCODING_IS_NOT_SUPPORTED, "The verifying BrokerRedirectUri process failed because the base64 url encoding is not supported.", e);
            }
        } else {
            Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":verifyBrokerRedirectUri"), "The prefix of the redirect uri does not match the expected value. ", Eo.a(" The valid broker redirect URI prefix: msauth so the redirect uri is expected to be: ", redirectUriForBroker), ADALError.DEVELOPER_REDIRECTURI_INVALID);
            throw new UsageAuthenticationException(ADALError.DEVELOPER_REDIRECTURI_INVALID, "The prefix of the redirect uri does not match the expected value.");
        }
    }

    private void waitingRequestOnError(AuthenticationRequestState authenticationRequestState, int i, AuthenticationException authenticationException) {
        waitingRequestOnError((CallbackHandler) null, authenticationRequestState, i, authenticationException);
    }

    public void acquireToken(IWindowComponent iWindowComponent, boolean z, AuthenticationRequest authenticationRequest, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        final CallbackHandler callbackHandler = new CallbackHandler(getHandler(), authenticationCallback);
        Logger.setCorrelationId(authenticationRequest.getCorrelationId());
        String a = Eo.a(new StringBuilder(), TAG, ":acquireToken");
        StringBuilder a2 = Eo.a("Sending async task from thread:");
        a2.append(Process.myTid());
        Logger.m1250v(a, a2.toString());
        final AuthenticationRequest authenticationRequest2 = authenticationRequest;
        final IWindowComponent iWindowComponent2 = iWindowComponent;
        final boolean z2 = z;
        THREAD_EXECUTOR.execute(new Runnable() {
            public void run() {
                Logger.setCorrelationId(authenticationRequest2.getCorrelationId());
                StringBuilder a = Eo.a("Running task in thread:");
                a.append(Process.myTid());
                Logger.m1250v(AcquireTokenRequest.TAG + ":acquireToken", a.toString());
                try {
                    AcquireTokenRequest.this.validateAcquireTokenRequest(authenticationRequest2);
                    AcquireTokenRequest.this.performAcquireTokenRequest(callbackHandler, iWindowComponent2, z2, authenticationRequest2);
                } catch (AuthenticationException e) {
                    AcquireTokenRequest.this.mAPIEvent.setSpeRing(e.getSpeRing());
                    AcquireTokenRequest.this.mAPIEvent.setRefreshTokenAge(e.getRefreshTokenAge());
                    AcquireTokenRequest.this.mAPIEvent.setServerErrorCode(e.getCliTelemErrorCode());
                    AcquireTokenRequest.this.mAPIEvent.setServerSubErrorCode(e.getCliTelemSubErrorCode());
                    AcquireTokenRequest.this.mAPIEvent.setWasApiCallSuccessful(false, e);
                    AcquireTokenRequest.this.mAPIEvent.setCorrelationId(authenticationRequest2.getCorrelationId().toString());
                    AcquireTokenRequest.this.mAPIEvent.stopTelemetryAndFlush();
                    callbackHandler.onError(e);
                }
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        int i3 = i2;
        Intent intent2 = intent;
        if (i == 1001) {
            getHandler();
            if (intent2 == null || intent.getExtras() == null) {
                Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), "BROWSER_FLOW data is null.", BuildConfig.FLAVOR, ADALError.ON_ACTIVITY_RESULT_INTENT_NULL);
                return;
            }
            Bundle extras = intent.getExtras();
            final int i4 = extras.getInt("com.microsoft.aad.adal:RequestId");
            try {
                AuthenticationRequestState waitingRequest = this.mAuthContext.getWaitingRequest(i4);
                Logger.m1250v(TAG + ":onActivityResult", "Waiting request found. RequestId:" + i4);
                String correlationInfoFromWaitingRequest = this.mAuthContext.getCorrelationInfoFromWaitingRequest(waitingRequest);
                if (i3 == 2004) {
                    String stringExtra = intent2.getStringExtra("account.access.token");
                    this.mBrokerProxy.saveAccount(intent2.getStringExtra("account.name"));
                    Date date = new Date(intent2.getLongExtra("account.expiredate", 0));
                    String stringExtra2 = intent2.getStringExtra("account.idtoken");
                    String stringExtra3 = intent2.getStringExtra("account.userinfo.tenantid");
                    UserInfo userInfoFromBrokerResult = UserInfo.getUserInfoFromBrokerResult(intent.getExtras());
                    String stringExtra4 = intent2.getStringExtra("cliteleminfo.server_error");
                    String stringExtra5 = intent2.getStringExtra("cliteleminfo.server_suberror");
                    String stringExtra6 = intent2.getStringExtra("cliteleminfo.rt_age");
                    String stringExtra7 = intent2.getStringExtra("cliteleminfo.spe_ring");
                    AuthenticationRequest request = waitingRequest.getRequest();
                    AuthenticationResult authenticationResult = new AuthenticationResult(stringExtra, (String) null, date, false, userInfoFromBrokerResult, stringExtra3, stringExtra2, (Date) null, request != null ? request.getClientId() : null);
                    authenticationResult.setAuthority(intent2.getStringExtra("account.authority"));
                    TelemetryUtils.CliTelemInfo cliTelemInfo = new TelemetryUtils.CliTelemInfo();
                    cliTelemInfo._setServerErrorCode(stringExtra4);
                    cliTelemInfo._setServerSubErrorCode(stringExtra5);
                    cliTelemInfo._setRefreshTokenAge(stringExtra6);
                    cliTelemInfo._setSpeRing(stringExtra7);
                    authenticationResult.setCliTelemInfo(cliTelemInfo);
                    if (authenticationResult.getAccessToken() != null) {
                        waitingRequest.getAPIEvent().setWasApiCallSuccessful(true, (Exception) null);
                        waitingRequest.getAPIEvent().setCorrelationId(waitingRequest.getRequest().getCorrelationId().toString());
                        waitingRequest.getAPIEvent().setIdToken(authenticationResult.getIdToken());
                        waitingRequest.getAPIEvent().setServerErrorCode(cliTelemInfo.getServerErrorCode());
                        waitingRequest.getAPIEvent().setServerSubErrorCode(cliTelemInfo.getServerSubErrorCode());
                        waitingRequest.getAPIEvent().setRefreshTokenAge(cliTelemInfo.getRefreshTokenAge());
                        waitingRequest.getAPIEvent().setSpeRing(cliTelemInfo.getSpeRing());
                        waitingRequest.getAPIEvent().stopTelemetryAndFlush();
                        waitingRequest.getDelegate().onSuccess(authenticationResult);
                    }
                } else if (i3 == 2001) {
                    Logger.m1250v(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), "User cancelled the flow. RequestId:" + i4 + " " + correlationInfoFromWaitingRequest);
                    waitingRequestOnError(waitingRequest, i4, new AuthenticationCancelError(Eo.a("User cancelled the flow RequestId:", i4, correlationInfoFromWaitingRequest)));
                } else if (i3 == 2006) {
                    Logger.m1250v(TAG + ":onActivityResult", "Device needs to have broker installed, we expect the apps to call usback when the broker is installed");
                    waitingRequestOnError(waitingRequest, i4, new AuthenticationException(ADALError.BROKER_APP_INSTALLATION_STARTED));
                } else if (i3 == 2005) {
                    Serializable serializable = extras.getSerializable("com.microsoft.aad.adal:AuthenticationException");
                    if (serializable == null || !(serializable instanceof AuthenticationException)) {
                        waitingRequestOnError(waitingRequest, i4, new AuthenticationException(ADALError.WEBVIEW_RETURNED_INVALID_AUTHENTICATION_EXCEPTION, correlationInfoFromWaitingRequest));
                        return;
                    }
                    AuthenticationException authenticationException = (AuthenticationException) serializable;
                    Logger.m1253w(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), "Webview returned exception.", authenticationException.getMessage(), ADALError.WEBVIEW_RETURNED_AUTHENTICATION_EXCEPTION);
                    waitingRequestOnError(waitingRequest, i4, authenticationException);
                } else if (i3 == 2002) {
                    String string = extras.getString("com.microsoft.aad.adal:BrowserErrorCode");
                    String string2 = extras.getString("com.microsoft.aad.adal:BrowserErrorMessage");
                    Logger.m1251v(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), "Error info:" + string + " for requestId: " + i4 + " " + correlationInfoFromWaitingRequest, string2, (ADALError) null);
                    String format = String.format("%s %s %s", new Object[]{string, string2, correlationInfoFromWaitingRequest});
                    if (!StringExtensions.isNullOrBlank(string)) {
                        ADALError aDALError = ADALError.AUTH_FAILED_INTUNE_POLICY_REQUIRED;
                        if ("AUTH_FAILED_INTUNE_POLICY_REQUIRED".compareTo(string) == 0) {
                            waitingRequestOnError(waitingRequest, i4, new IntuneAppProtectionPolicyRequiredException(format, extras.getString("account.name"), extras.getString("account.userinfo.userid"), extras.getString("account.userinfo.tenantid"), extras.getString("account.authority")));
                            return;
                        }
                    }
                    waitingRequestOnError(waitingRequest, i4, new AuthenticationException(ADALError.SERVER_INVALID_REQUEST, format));
                } else if (i3 == 2003) {
                    AuthenticationRequest authenticationRequest = (AuthenticationRequest) extras.getSerializable("com.microsoft.aad.adal:BrowserRequestInfo");
                    String string3 = extras.getString("com.microsoft.aad.adal:BrowserFinalUrl", BuildConfig.FLAVOR);
                    if (string3.isEmpty()) {
                        StringBuilder sb = new StringBuilder("Webview did not reach the redirectUrl. ");
                        if (authenticationRequest != null) {
                            sb.append(authenticationRequest.getLogInfo());
                        }
                        sb.append(correlationInfoFromWaitingRequest);
                        AuthenticationException authenticationException2 = new AuthenticationException(ADALError.WEBVIEW_RETURNED_EMPTY_REDIRECT_URL, sb.toString());
                        Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), BuildConfig.FLAVOR, authenticationException2.getMessage(), authenticationException2.getCode());
                        waitingRequestOnError(waitingRequest, i4, authenticationException2);
                        return;
                    }
                    final AuthenticationRequestState authenticationRequestState = waitingRequest;
                    final String str = string3;
                    final CallbackHandler callbackHandler = new CallbackHandler(getHandler(), waitingRequest.getDelegate());
                    THREAD_EXECUTOR.execute(new Runnable() {
                        public void run() {
                            try {
                                AuthenticationResult acquireTokenWithAuthCode = new AcquireTokenInteractiveRequest(AcquireTokenRequest.this.mContext, authenticationRequestState.getRequest(), AcquireTokenRequest.this.mTokenCacheAccessor).acquireTokenWithAuthCode(str);
                                authenticationRequestState.getAPIEvent().setWasApiCallSuccessful(true, (Exception) null);
                                authenticationRequestState.getAPIEvent().setCorrelationId(authenticationRequestState.getRequest().getCorrelationId().toString());
                                authenticationRequestState.getAPIEvent().setIdToken(acquireTokenWithAuthCode.getIdToken());
                                authenticationRequestState.getAPIEvent().stopTelemetryAndFlush();
                                if (authenticationRequestState.getDelegate() != null) {
                                    Logger.m1251v(AcquireTokenRequest.TAG + ":onActivityResult", "Sending result to callback. ", authenticationRequestState.getRequest().getLogInfo(), (ADALError) null);
                                    callbackHandler.onSuccess(acquireTokenWithAuthCode);
                                }
                            } catch (AuthenticationException e) {
                                StringBuilder sb = new StringBuilder(e.getMessage());
                                if (e.getCause() != null) {
                                    sb.append(e.getCause().getMessage());
                                }
                                String str = AcquireTokenRequest.TAG + ":onActivityResult";
                                Logger.m1246e(str, (e.getCode() == null ? ADALError.AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN : e.getCode()).getDescription(), sb.toString() + ' ' + ExceptionExtensions.getExceptionMessage(e) + ' ' + Log.getStackTraceString(e), ADALError.AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN, (Throwable) null);
                                AcquireTokenRequest.this.waitingRequestOnError(callbackHandler, authenticationRequestState, i4, e);
                            }
                        }
                    });
                }
            } catch (AuthenticationException unused) {
                Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":onActivityResult"), Eo.b("Failed to find waiting request. RequestId:", i4), BuildConfig.FLAVOR, ADALError.ON_ACTIVITY_RESULT_INTENT_NULL);
            }
        }
    }

    public void refreshTokenWithoutCache(final String str, final AuthenticationRequest authenticationRequest, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        Logger.setCorrelationId(authenticationRequest.getCorrelationId());
        Logger.m1250v(TAG + ":refreshTokenWithoutCache", "Refresh token without cache");
        final CallbackHandler callbackHandler = new CallbackHandler(getHandler(), authenticationCallback);
        THREAD_EXECUTOR.execute(new Runnable() {
            public void run() {
                try {
                    AcquireTokenRequest.this.validateAcquireTokenRequest(authenticationRequest);
                    AuthenticationResult acquireTokenWithRefreshToken = new AcquireTokenSilentHandler(AcquireTokenRequest.this.mContext, authenticationRequest, AcquireTokenRequest.this.mTokenCacheAccessor).acquireTokenWithRefreshToken(str);
                    AcquireTokenRequest.this.mAPIEvent.setWasApiCallSuccessful(true, (Exception) null);
                    AcquireTokenRequest.this.mAPIEvent.setIdToken(acquireTokenWithRefreshToken.getIdToken());
                    callbackHandler.onSuccess(acquireTokenWithRefreshToken);
                } catch (AuthenticationException e) {
                    AcquireTokenRequest.this.mAPIEvent.setWasApiCallSuccessful(false, e);
                    callbackHandler.onError(e);
                } catch (Throwable th) {
                    AcquireTokenRequest.this.mAPIEvent.setCorrelationId(authenticationRequest.getCorrelationId().toString());
                    AcquireTokenRequest.this.mAPIEvent.stopTelemetryAndFlush();
                    throw th;
                }
                AcquireTokenRequest.this.mAPIEvent.setCorrelationId(authenticationRequest.getCorrelationId().toString());
                AcquireTokenRequest.this.mAPIEvent.stopTelemetryAndFlush();
            }
        });
    }

    /* access modifiers changed from: private */
    public void waitingRequestOnError(CallbackHandler callbackHandler, AuthenticationRequestState authenticationRequestState, int i, AuthenticationException authenticationException) {
        if (authenticationRequestState != null) {
            try {
                if (authenticationRequestState.getDelegate() != null) {
                    Logger.m1250v(TAG + ":waitingRequestOnError", "Sending error to callback" + this.mAuthContext.getCorrelationInfoFromWaitingRequest(authenticationRequestState));
                    authenticationRequestState.getAPIEvent().setWasApiCallSuccessful(false, authenticationException);
                    authenticationRequestState.getAPIEvent().setCorrelationId(authenticationRequestState.getRequest().getCorrelationId().toString());
                    authenticationRequestState.getAPIEvent().stopTelemetryAndFlush();
                    if (callbackHandler != null) {
                        callbackHandler.onError(authenticationException);
                    } else {
                        authenticationRequestState.getDelegate().onError(authenticationException);
                    }
                }
            } catch (Throwable th) {
                if (authenticationException != null) {
                    this.mAuthContext.removeWaitingRequest(i);
                }
                throw th;
            }
        }
        if (authenticationException != null) {
            this.mAuthContext.removeWaitingRequest(i);
        }
    }
}
