package com.microsoft.aad.adal;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.citrix.loggersdk.BuildConfig;
import com.googleblog.android_developers.PRNGFixes;
import com.microsoft.aad.adal.AuthenticationRequest;
import com.microsoft.aad.adal.BrokerProxy;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftTokenRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class AuthenticationContext {
    public static final SparseArray<AuthenticationRequestState> DELEGATE_MAP = new SparseArray<>();
    public static final int EXCLUDE_INDEX = 8;
    public static final String REQUEST_ID = "requestId:";
    public static final String TAG = "AuthenticationContext";
    public String mAuthority;
    public BrokerProxy mBrokerProxy = null;
    public List<String> mClientCapabilites = null;
    public Context mContext;
    public boolean mExtendedLifetimeEnabled = false;
    public boolean mIsAuthorityValidated;
    public UUID mRequestCorrelationId = null;
    public ITokenCacheStore mTokenCacheStore;
    public boolean mValidateAuthority;

    /* compiled from: PG */
    public static final class SettableFuture<V> extends FutureTask<V> {
        public SettableFuture() {
            super(new 1());
        }

        public void set(V v) {
            super.set(v);
        }

        public void setException(Throwable th) {
            super.setException(th);
        }
    }

    public AuthenticationContext(Context context, String str, boolean z) {
        PRNGFixes.a();
        initialize(context, str, new DefaultTokenCacheStore(context), z, true);
    }

    private boolean checkADFSValidationRequirements(String str) throws AuthenticationException {
        URL url = StringExtensions.getUrl(this.mAuthority);
        if (this.mAuthority == null || url == null) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL);
        } else if (!UrlExtensions.isADFSAuthority(url) || !this.mValidateAuthority || this.mIsAuthorityValidated || str != null) {
            return true;
        } else {
            ADALError aDALError = ADALError.DEVELOPER_AUTHORITY_CAN_NOT_BE_VALIDED;
            StringBuilder a = Eo.a("AD FS validation requires a loginHint be provided or an ");
            a.append(getClass().getSimpleName());
            a.append(" in which the current authority has previously been validated.");
            throw new AuthenticationException(aDALError, a.toString());
        }
    }

    private void checkInternetPermission() {
        if (this.mContext.getPackageManager().checkPermission("android.permission.INTERNET", this.mContext.getPackageName()) != 0) {
            throw new IllegalStateException(new AuthenticationException(ADALError.DEVELOPER_INTERNET_PERMISSION_MISSING));
        }
    }

    private boolean checkPreRequirements(String str, String str2) throws AuthenticationException {
        if (this.mContext != null) {
            if (AuthenticationSettings.INSTANCE.getUseBroker()) {
                this.mBrokerProxy.verifyBrokerPermissionsAPI22AndLess();
            }
            if (StringExtensions.isNullOrBlank(str)) {
                throw new IllegalArgumentException("resource");
            } else if (!StringExtensions.isNullOrBlank(str2)) {
                return true;
            } else {
                throw new IllegalArgumentException("clientId");
            }
        } else {
            throw new IllegalArgumentException("context", new AuthenticationException(ADALError.DEVELOPER_CONTEXT_IS_NOT_PROVIDED));
        }
    }

    private AcquireTokenRequest createAcquireTokenRequest(APIEvent aPIEvent) {
        return new AcquireTokenRequest(this.mContext, this, aPIEvent);
    }

    private APIEvent createApiEvent(Context context, String str, String str2, String str3) {
        APIEvent aPIEvent = new APIEvent("Microsoft.ADAL.api_event", context, str);
        aPIEvent.setRequestId(str2);
        aPIEvent.setAPIId(str3);
        aPIEvent.setAuthority(getAuthority());
        C0336Telemetry.getInstance().startEvent(str2, aPIEvent.getEventName());
        return aPIEvent;
    }

    public static String extractAuthority(String str) {
        int indexOf;
        int i;
        int indexOf2;
        if (!StringExtensions.isNullOrBlank(str) && (indexOf = str.indexOf(47, 8)) >= 0 && indexOf != str.length() - 1 && ((indexOf2 = str.indexOf("/", i)) < 0 || indexOf2 > (i = indexOf + 1))) {
            return indexOf2 >= 0 ? str.substring(0, indexOf2) : str;
        }
        throw new IllegalArgumentException("authority");
    }

    private String getRedirectUri(String str) {
        return StringExtensions.isNullOrBlank(str) ? this.mContext.getApplicationContext().getPackageName() : str;
    }

    public static String getVersionName() {
        return "1.16.3-hf2";
    }

    private void initialize(Context context, String str, ITokenCacheStore iTokenCacheStore, boolean z, boolean z2) {
        if (context == null) {
            throw new IllegalArgumentException("appContext");
        } else if (str != null) {
            this.mBrokerProxy = new BrokerProxy(context);
            if (z2 || this.mBrokerProxy.canUseLocalCache(str)) {
                this.mContext = context;
                checkInternetPermission();
                this.mAuthority = extractAuthority(str);
                this.mValidateAuthority = z;
                this.mTokenCacheStore = iTokenCacheStore;
                return;
            }
            throw new UnsupportedOperationException("Local cache is not supported for broker usage");
        } else {
            throw new IllegalArgumentException("authority");
        }
    }

    public static String mergeClaimsWithClientCapabilities(String str, List<String> list) {
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("values", jSONArray);
                jSONObject.put("xms_cc", jSONObject2);
                if (!TextUtils.isEmpty(str)) {
                    JSONObject jSONObject3 = new JSONObject(str);
                    if (jSONObject3.has("access_token")) {
                        JSONObject jSONObject4 = jSONObject3.getJSONObject("access_token");
                        jSONObject4.put("xms_cc", jSONObject2);
                        jSONObject3.put("access_token", jSONObject4);
                    } else {
                        jSONObject3.put("access_token", jSONObject);
                    }
                    return jSONObject3.toString();
                }
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("access_token", jSONObject);
                return jSONObject5.toString();
            } catch (JSONException e) {
                Logger.m1247e(TAG, "Invalid json format for claims or Client capabilities ", e);
            }
        }
        return str;
    }

    private void setAppInfoToRequest(AuthenticationRequest authenticationRequest) {
        String packageName = this.mContext.getPackageName();
        authenticationRequest.setAppName(packageName);
        try {
            authenticationRequest.setAppVersion(this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            qI.a.a(e);
        }
    }

    private void throwIfClaimsInBothExtraQpAndClaimsParameter(String str, String str2) {
        if (!StringExtensions.isNullOrBlank(str) && !StringExtensions.isNullOrBlank(str2) && str2.contains(MicrosoftTokenRequest.CLAIMS)) {
            throw new IllegalArgumentException("claims cannot be sent in claims parameter and extra qp.");
        }
    }

    private void validateClaims(String str) throws AuthenticationException {
        try {
            if (!TextUtils.isEmpty(str)) {
                new JSONObject(str);
            }
        } catch (JSONException e) {
            throw new AuthenticationException(ADALError.JSON_PARSE_ERROR, "Invalid claims request parameters", (Throwable) e);
        }
    }

    private IWindowComponent wrapActivity(final Activity activity) {
        if (activity != null) {
            return new IWindowComponent() {
                public Activity mRefActivity = activity;

                public void startActivityForResult(Intent intent, int i) {
                    Activity activity = this.mRefActivity;
                    if (activity != null) {
                        activity.startActivityForResult(intent, i);
                    }
                }
            };
        }
        throw new IllegalArgumentException("activity");
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, String str4, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, PromptBehavior.Auto, (String) null, (String) null, authenticationCallback, "100", wrapActivity(activity), false);
    }

    @Deprecated
    public void acquireTokenByRefreshToken(String str, String str2, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        if (checkADFSValidationRequirements((String) null, authenticationCallback)) {
            if (StringExtensions.isNullOrBlank(str)) {
                throw new IllegalArgumentException("Refresh token is not provided");
            } else if (StringExtensions.isNullOrBlank(str2)) {
                throw new IllegalArgumentException("ClientId is not provided");
            } else if (authenticationCallback != null) {
                String registerNewRequest = C0336Telemetry.registerNewRequest();
                APIEvent createApiEvent = createApiEvent(this.mContext, str2, registerNewRequest, "4");
                createApiEvent.setPromptBehavior(PromptBehavior.Auto.toString());
                createApiEvent.setIsDeprecated(true);
                AuthenticationRequest authenticationRequest = new AuthenticationRequest(this.mAuthority, (String) null, str2, getRequestCorrelationId(), getExtendedLifetimeEnabled());
                authenticationRequest.setSilent(true);
                authenticationRequest.setTelemetryRequestId(registerNewRequest);
                createAcquireTokenRequest(createApiEvent).refreshTokenWithoutCache(str, authenticationRequest, authenticationCallback);
            } else {
                throw new IllegalArgumentException("Callback is not provided");
            }
        }
    }

    @Deprecated
    public Future<AuthenticationResult> acquireTokenSilent(String str, String str2, String str3, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        SettableFuture settableFuture = new SettableFuture();
        try {
            checkPreRequirements(str, str2);
            checkADFSValidationRequirements((String) null);
            String registerNewRequest = C0336Telemetry.registerNewRequest();
            final APIEvent createApiEvent = createApiEvent(this.mContext, str2, registerNewRequest, "2");
            createApiEvent.setIsDeprecated(true);
            AuthenticationRequest authenticationRequest = new AuthenticationRequest(this.mAuthority, str, str2, str3, getRequestCorrelationId(), getExtendedLifetimeEnabled());
            authenticationRequest.setSilent(true);
            authenticationRequest.setPrompt(PromptBehavior.Auto);
            authenticationRequest.setUserIdentifierType(AuthenticationRequest.UserIdentifierType.UniqueId);
            authenticationRequest.setTelemetryRequestId(registerNewRequest);
            setAppInfoToRequest(authenticationRequest);
            final AuthenticationRequest authenticationRequest2 = authenticationRequest;
            final AuthenticationCallback<AuthenticationResult> authenticationCallback2 = authenticationCallback;
            final SettableFuture settableFuture2 = settableFuture;
            createAcquireTokenRequest(createApiEvent).acquireToken((IWindowComponent) null, false, authenticationRequest, new AuthenticationCallback<AuthenticationResult>() {
                public void onError(Exception exc) {
                    createApiEvent.setWasApiCallSuccessful(false, exc);
                    createApiEvent.setCorrelationId(authenticationRequest2.getCorrelationId().toString());
                    createApiEvent.stopTelemetryAndFlush();
                    AuthenticationCallback authenticationCallback = authenticationCallback2;
                    if (authenticationCallback != null) {
                        authenticationCallback.onError(exc);
                    }
                    settableFuture2.setException(exc);
                }

                public void onSuccess(AuthenticationResult authenticationResult) {
                    createApiEvent.setWasApiCallSuccessful(true, (Exception) null);
                    createApiEvent.setCorrelationId(authenticationRequest2.getCorrelationId().toString());
                    createApiEvent.setIdToken(authenticationResult.getIdToken());
                    createApiEvent.stopTelemetryAndFlush();
                    AuthenticationCallback authenticationCallback = authenticationCallback2;
                    if (authenticationCallback != null) {
                        authenticationCallback.onSuccess(authenticationResult);
                    }
                    settableFuture2.set(authenticationResult);
                }
            });
            return settableFuture;
        } catch (AuthenticationException e) {
            authenticationCallback.onError(e);
            settableFuture.setException(e);
            return settableFuture;
        }
    }

    public void acquireTokenSilentAsync(String str, String str2, String str3, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireTokenSilentAsync(str, str2, str3, false, (String) null, "3", authenticationCallback);
    }

    public AuthenticationResult acquireTokenSilentSync(String str, String str2, String str3) throws AuthenticationException, InterruptedException {
        return acquireTokenSilentSync(str, str2, str3, false, (String) null, "1");
    }

    public boolean cancelAuthenticationActivity(int i) throws AuthenticationException {
        String str;
        AuthenticationRequestState waitingRequest = getWaitingRequest(i);
        if (waitingRequest == null || waitingRequest.getDelegate() == null) {
            Logger.m1250v("AuthenticationContext:cancelAuthenticationActivity", "Current callback is empty. There is not any active authentication.");
            return true;
        }
        if (waitingRequest.getRequest() != null) {
            str = String.format(" CorrelationId: %s", new Object[]{waitingRequest.getRequest().getCorrelationId().toString()});
        } else {
            str = "No correlation id associated with waiting request";
        }
        Logger.m1250v("AuthenticationContext:cancelAuthenticationActivity", "Current callback is not empty. There is an active authentication Activity." + str);
        Intent intent = new Intent("com.microsoft.aad.adal:BrowserCancel");
        intent.putExtras(new Bundle());
        intent.putExtra("com.microsoft.aad.adal:RequestId", i);
        boolean a = Y5.a(this.mContext).a(intent);
        if (a) {
            Logger.m1250v("AuthenticationContext:cancelAuthenticationActivity", "Cancel broadcast message was successful." + str);
            waitingRequest.setCancelled(true);
            waitingRequest.getDelegate().onError(new AuthenticationCancelError("Cancel broadcast message was successful."));
        } else {
            Logger.m1253w("AuthenticationContext:cancelAuthenticationActivity", Eo.a("Cancel broadcast message was not successful.", str), BuildConfig.FLAVOR, ADALError.BROADCAST_CANCEL_NOT_SUCCESSFUL);
        }
        return a;
    }

    public void deserialize(String str) throws AuthenticationException {
        if (StringExtensions.isNullOrBlank(str)) {
            throw new IllegalArgumentException("serializedBlob");
        } else if (this.mBrokerProxy.canSwitchToBroker(this.mAuthority) == BrokerProxy.SwitchToBroker.CANNOT_SWITCH_TO_BROKER) {
            TokenCacheItem deserialize = SSOStateSerializer.deserialize(str);
            if (StringExtensions.isNullOrBlank(deserialize.getAuthority()) || (StringExtensions.isNullOrBlank(deserialize.getClientId()) && StringExtensions.isNullOrBlank(deserialize.getFamilyClientId()))) {
                throw new DeserializationAuthenticationException("Failed to deserialize the blob because authority or client id is null/empty.");
            }
            getCache().setItem(CacheKey.createCacheKey(deserialize), deserialize);
        } else {
            throw new UsageAuthenticationException(ADALError.FAIL_TO_IMPORT, "Failed to import the serialized blob because broker is enabled.");
        }
    }

    public String getAuthority() {
        return this.mAuthority;
    }

    public String getBrokerUser() {
        BrokerProxy brokerProxy = this.mBrokerProxy;
        if (brokerProxy != null) {
            return brokerProxy.getCurrentUser();
        }
        return null;
    }

    public UserInfo[] getBrokerUsers() throws OperationCanceledException, AuthenticatorException, IOException {
        BrokerProxy brokerProxy = this.mBrokerProxy;
        if (brokerProxy != null) {
            return brokerProxy.getBrokerUsers();
        }
        return null;
    }

    public ITokenCacheStore getCache() {
        return this.mTokenCacheStore;
    }

    public List<String> getClientCapabilites() {
        return this.mClientCapabilites;
    }

    public String getCorrelationInfoFromWaitingRequest(AuthenticationRequestState authenticationRequestState) {
        UUID requestCorrelationId = getRequestCorrelationId();
        if (authenticationRequestState.getRequest() != null) {
            requestCorrelationId = authenticationRequestState.getRequest().getCorrelationId();
        }
        return String.format(" CorrelationId: %s", new Object[]{requestCorrelationId.toString()});
    }

    public boolean getExtendedLifetimeEnabled() {
        return this.mExtendedLifetimeEnabled;
    }

    public boolean getIsAuthorityValidated() {
        return this.mIsAuthorityValidated;
    }

    public String getRedirectUriForBroker() {
        PackageHelper packageHelper = new PackageHelper(this.mContext);
        String packageName = this.mContext.getPackageName();
        String currentSignatureForPackage = packageHelper.getCurrentSignatureForPackage(packageName);
        String brokerRedirectUrl = PackageHelper.getBrokerRedirectUrl(packageName, currentSignatureForPackage);
        StringBuilder a = Eo.a("Broker redirectUri:", brokerRedirectUrl, " packagename:", packageName, " signatureDigest:");
        a.append(currentSignatureForPackage);
        Logger.m1251v("AuthenticationContext:getRedirectUriForBroker", "Get expected redirect Uri. ", a.toString(), (ADALError) null);
        return brokerRedirectUrl;
    }

    public UUID getRequestCorrelationId() {
        UUID uuid = this.mRequestCorrelationId;
        return uuid == null ? UUID.randomUUID() : uuid;
    }

    public boolean getValidateAuthority() {
        return this.mValidateAuthority;
    }

    public AuthenticationRequestState getWaitingRequest(int i) throws AuthenticationException {
        AuthenticationRequestState authenticationRequestState;
        Logger.m1250v("AuthenticationContext:getWaitingRequest", "Get waiting request. requestId:" + i);
        synchronized (DELEGATE_MAP) {
            authenticationRequestState = DELEGATE_MAP.get(i);
        }
        if (authenticationRequestState != null) {
            return authenticationRequestState;
        }
        Logger.m1245e("AuthenticationContext:getWaitingRequest", Eo.b("Request callback is not available. requestId:", i), (String) null, ADALError.CALLBACK_IS_NOT_FOUND);
        throw new AuthenticationException(ADALError.CALLBACK_IS_NOT_FOUND, Eo.b("Request callback is not available for requestId:", i));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AuthenticationRequestState authenticationRequestState;
        if (i != 1001) {
            return;
        }
        if (intent == null) {
            Logger.m1245e("AuthenticationContext:onActivityResult", "onActivityResult BROWSER_FLOW data is null.", BuildConfig.FLAVOR, ADALError.ON_ACTIVITY_RESULT_INTENT_NULL);
            return;
        }
        int i3 = intent.getExtras().getInt("com.microsoft.aad.adal:RequestId");
        synchronized (DELEGATE_MAP) {
            authenticationRequestState = DELEGATE_MAP.get(i3);
        }
        if (authenticationRequestState != null) {
            new AcquireTokenRequest(this.mContext, this, authenticationRequestState.getAPIEvent()).onActivityResult(i, i2, intent);
        } else {
            Logger.m1245e("AuthenticationContext:onActivityResult", Eo.b("onActivityResult did not find the waiting request. requestId:", i3), (String) null, ADALError.ON_ACTIVITY_RESULT_INTENT_NULL);
        }
    }

    public void putWaitingRequest(int i, AuthenticationRequestState authenticationRequestState) {
        if (authenticationRequestState != null) {
            StringBuilder b = Eo.b("Put waiting request. requestId:", i, " ");
            b.append(getCorrelationInfoFromWaitingRequest(authenticationRequestState));
            Logger.m1250v(TAG, b.toString());
            synchronized (DELEGATE_MAP) {
                DELEGATE_MAP.put(i, authenticationRequestState);
            }
        }
    }

    public void removeWaitingRequest(int i) {
        Logger.m1250v(TAG, "Remove waiting request. requestId:" + i);
        synchronized (DELEGATE_MAP) {
            DELEGATE_MAP.remove(i);
        }
    }

    public String serialize(String str) throws AuthenticationException {
        if (StringExtensions.isNullOrBlank(str)) {
            throw new IllegalArgumentException("uniqueUserId");
        } else if (this.mBrokerProxy.canSwitchToBroker(this.mAuthority) == BrokerProxy.SwitchToBroker.CANNOT_SWITCH_TO_BROKER) {
            try {
                TokenCacheItem fRTItem = new TokenCacheAccessor(this.mContext.getApplicationContext(), this.mTokenCacheStore, getAuthority(), C0336Telemetry.registerNewRequest()).getFRTItem("1", str);
                if (fRTItem == null) {
                    Logger.m1248i("AuthenticationContext:serialize", "Cannot find the family token cache item for this userID", BuildConfig.FLAVOR);
                    throw new UsageAuthenticationException(ADALError.FAIL_TO_EXPORT, "Failed to export the FID because no family token cache item is found.");
                } else if (!StringExtensions.isNullOrBlank(fRTItem.getFamilyClientId())) {
                    return SSOStateSerializer.serialize(fRTItem);
                } else {
                    throw new UsageAuthenticationException(ADALError.FAIL_TO_EXPORT, "tokenItem does not contain family refresh token");
                }
            } catch (MalformedURLException e) {
                throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
            }
        } else {
            throw new UsageAuthenticationException(ADALError.FAIL_TO_EXPORT, "Failed to export the family refresh token cache item because broker is enabled.");
        }
    }

    public void setClientCapabilites(List<String> list) {
        this.mClientCapabilites = list;
    }

    public void setExtendedLifetimeEnabled(boolean z) {
        this.mExtendedLifetimeEnabled = z;
    }

    public void setIsAuthorityValidated(boolean z) {
        this.mIsAuthorityValidated = z;
    }

    public void setRequestCorrelationId(UUID uuid) {
        this.mRequestCorrelationId = uuid;
        Logger.setCorrelationId(uuid);
    }

    public void acquireTokenSilentAsync(String str, String str2, String str3, boolean z, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireTokenSilentAsync(str, str2, str3, z, (String) null, "14", authenticationCallback);
    }

    public AuthenticationResult acquireTokenSilentSync(String str, String str2, String str3, String str4) throws AuthenticationException, InterruptedException {
        return acquireTokenSilentSync(str, str2, str3, false, str4, "15");
    }

    public void acquireTokenSilentAsync(String str, String str2, String str3, String str4, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireTokenSilentAsync(str, str2, str3, false, str4, "16", authenticationCallback);
    }

    public AuthenticationResult acquireTokenSilentSync(String str, String str2, String str3, boolean z) throws AuthenticationException, InterruptedException {
        return acquireTokenSilentSync(str, str2, str3, z, (String) null, "13");
    }

    private void acquireTokenSilentAsync(String str, String str2, String str3, boolean z, String str4, String str5, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        String str6 = str2;
        AuthenticationCallback<AuthenticationResult> authenticationCallback2 = authenticationCallback;
        if (checkPreRequirements(str, str6, authenticationCallback2) && checkADFSValidationRequirements((String) null, authenticationCallback2)) {
            try {
                validateClaims(str4);
                String registerNewRequest = C0336Telemetry.registerNewRequest();
                APIEvent createApiEvent = createApiEvent(this.mContext, str6, registerNewRequest, str5);
                createApiEvent.setPromptBehavior(PromptBehavior.Auto.toString());
                AuthenticationRequest authenticationRequest = new AuthenticationRequest(this.mAuthority, str, str2, str3, getRequestCorrelationId(), getExtendedLifetimeEnabled(), z, str4);
                authenticationRequest.setSilent(true);
                authenticationRequest.setPrompt(PromptBehavior.Auto);
                authenticationRequest.setUserIdentifierType(AuthenticationRequest.UserIdentifierType.UniqueId);
                authenticationRequest.setClientCapabilities(this.mClientCapabilites);
                setAppInfoToRequest(authenticationRequest);
                authenticationRequest.setTelemetryRequestId(registerNewRequest);
                createAcquireTokenRequest(createApiEvent).acquireToken((IWindowComponent) null, false, authenticationRequest, authenticationCallback2);
            } catch (AuthenticationException e) {
                authenticationCallback2.onError(e);
            }
        }
    }

    private AuthenticationResult acquireTokenSilentSync(String str, String str2, String str3, boolean z, String str4, String str5) throws AuthenticationException, InterruptedException {
        String str6 = str4;
        validateClaims(str6);
        checkPreRequirements(str, str2);
        checkADFSValidationRequirements((String) null);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        String registerNewRequest = C0336Telemetry.registerNewRequest();
        String str7 = str2;
        APIEvent createApiEvent = createApiEvent(this.mContext, str7, registerNewRequest, str5);
        createApiEvent.setPromptBehavior(PromptBehavior.Auto.toString());
        AuthenticationRequest authenticationRequest = r1;
        APIEvent aPIEvent = createApiEvent;
        AuthenticationRequest authenticationRequest2 = new AuthenticationRequest(this.mAuthority, str, str7, str3, getRequestCorrelationId(), getExtendedLifetimeEnabled(), z, str6);
        authenticationRequest.setSilent(true);
        authenticationRequest.setPrompt(PromptBehavior.Auto);
        authenticationRequest.setUserIdentifierType(AuthenticationRequest.UserIdentifierType.UniqueId);
        authenticationRequest.setTelemetryRequestId(registerNewRequest);
        authenticationRequest.setClientCapabilities(this.mClientCapabilites);
        setAppInfoToRequest(authenticationRequest);
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.mContext.getMainLooper()) {
            Logger.m1247e("AuthenticationContext:acquireTokenSilentSync", "Sync network calls must not be invoked in main thread. This method will throw android.os.NetworkOnMainThreadException in next major release", new NetworkOnMainThreadException());
        }
        createAcquireTokenRequest(aPIEvent).acquireToken((IWindowComponent) null, false, authenticationRequest, new AuthenticationCallback<AuthenticationResult>() {
            public void onError(Exception exc) {
                atomicReference2.set(exc);
                countDownLatch.countDown();
            }

            public void onSuccess(AuthenticationResult authenticationResult) {
                atomicReference.set(authenticationResult);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        Exception exc = (Exception) atomicReference2.get();
        if (exc == null) {
            return (AuthenticationResult) atomicReference.get();
        }
        if (exc instanceof AuthenticationException) {
            throw ((AuthenticationException) exc);
        } else if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        } else if (exc.getCause() == null) {
            throw new AuthenticationException(ADALError.ERROR_SILENT_REQUEST, exc.getMessage(), (Throwable) exc);
        } else if (exc.getCause() instanceof AuthenticationException) {
            throw ((AuthenticationException) exc.getCause());
        } else if (exc.getCause() instanceof RuntimeException) {
            throw ((RuntimeException) exc.getCause());
        } else {
            throw new AuthenticationException(ADALError.ERROR_SILENT_REQUEST, exc.getCause().getMessage(), exc.getCause());
        }
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, String str4, String str5, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, PromptBehavior.Auto, str5, (String) null, authenticationCallback, "104", wrapActivity(activity), false);
    }

    private boolean checkADFSValidationRequirements(String str, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        try {
            return checkADFSValidationRequirements(str);
        } catch (AuthenticationException e) {
            authenticationCallback.onError(e);
            return false;
        }
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, PromptBehavior promptBehavior, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, (String) null, promptBehavior, (String) null, (String) null, authenticationCallback, "108", wrapActivity(activity), false);
    }

    public AuthenticationContext(Context context, String str, boolean z, ITokenCacheStore iTokenCacheStore) {
        initialize(context, str, iTokenCacheStore, z, false);
    }

    private boolean checkPreRequirements(String str, String str2, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        if (authenticationCallback != null) {
            try {
                return checkPreRequirements(str, str2);
            } catch (AuthenticationException e) {
                authenticationCallback.onError(e);
                return false;
            }
        } else {
            throw new IllegalArgumentException("callback");
        }
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, PromptBehavior promptBehavior, String str4, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, (String) null, promptBehavior, str4, (String) null, authenticationCallback, "111", wrapActivity(activity), false);
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, (String) null, authenticationCallback, "115", wrapActivity(activity), false);
    }

    public void acquireToken(Activity activity, String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, String str6, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, str6, authenticationCallback, "118", wrapActivity(activity), false);
    }

    public AuthenticationContext(Context context, String str, ITokenCacheStore iTokenCacheStore) {
        initialize(context, str, iTokenCacheStore, true, false);
    }

    public void acquireToken(IWindowComponent iWindowComponent, String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, (String) null, authenticationCallback, "116", iWindowComponent, false);
    }

    public void acquireToken(IWindowComponent iWindowComponent, String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, String str6, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, str6, authenticationCallback, "119", iWindowComponent, false);
    }

    @Deprecated
    public void acquireTokenByRefreshToken(String str, String str2, String str3, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        if (checkADFSValidationRequirements((String) null, authenticationCallback)) {
            if (StringExtensions.isNullOrBlank(str)) {
                throw new IllegalArgumentException("Refresh token is not provided");
            } else if (StringExtensions.isNullOrBlank(str2)) {
                throw new IllegalArgumentException("ClientId is not provided");
            } else if (authenticationCallback != null) {
                String registerNewRequest = C0336Telemetry.registerNewRequest();
                APIEvent createApiEvent = createApiEvent(this.mContext, str2, registerNewRequest, "5");
                createApiEvent.setPromptBehavior(PromptBehavior.Auto.toString());
                createApiEvent.setIsDeprecated(true);
                AuthenticationRequest authenticationRequest = new AuthenticationRequest(this.mAuthority, str3, str2, getRequestCorrelationId(), getExtendedLifetimeEnabled());
                authenticationRequest.setTelemetryRequestId(registerNewRequest);
                authenticationRequest.setSilent(true);
                createAcquireTokenRequest(createApiEvent).refreshTokenWithoutCache(str, authenticationRequest, authenticationCallback);
            } else {
                throw new IllegalArgumentException("Callback is not provided");
            }
        }
    }

    public void acquireToken(String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, (String) null, authenticationCallback, "117", (IWindowComponent) null, true);
    }

    public void acquireToken(String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, String str6, AuthenticationCallback<AuthenticationResult> authenticationCallback) {
        acquireToken(str, str2, str3, str4, promptBehavior, str5, str6, authenticationCallback, "120", (IWindowComponent) null, false);
    }

    private void acquireToken(String str, String str2, String str3, String str4, PromptBehavior promptBehavior, String str5, String str6, AuthenticationCallback<AuthenticationResult> authenticationCallback, String str7, IWindowComponent iWindowComponent, boolean z) {
        String str8 = str2;
        String str9 = str4;
        String str10 = str6;
        AuthenticationCallback<AuthenticationResult> authenticationCallback2 = authenticationCallback;
        throwIfClaimsInBothExtraQpAndClaimsParameter(str10, str5);
        try {
            validateClaims(str10);
            if (checkPreRequirements(str, str8, authenticationCallback2) && checkADFSValidationRequirements(str9, authenticationCallback2)) {
                String redirectUri = getRedirectUri(str3);
                String registerNewRequest = C0336Telemetry.registerNewRequest();
                APIEvent createApiEvent = createApiEvent(this.mContext, str8, registerNewRequest, str7);
                PromptBehavior promptBehavior2 = promptBehavior;
                createApiEvent.setPromptBehavior(promptBehavior2);
                AuthenticationRequest authenticationRequest = r2;
                APIEvent aPIEvent = createApiEvent;
                AuthenticationRequest authenticationRequest2 = new AuthenticationRequest(this.mAuthority, str, str2, redirectUri, str4, promptBehavior2, str5, getRequestCorrelationId(), getExtendedLifetimeEnabled(), str6);
                authenticationRequest.setTelemetryRequestId(registerNewRequest);
                setAppInfoToRequest(authenticationRequest);
                authenticationRequest.setClientCapabilities(this.mClientCapabilites);
                if (!StringExtensions.isNullOrBlank(str4)) {
                    aPIEvent.setLoginHint(str9);
                    authenticationRequest.setUserIdentifierType(AuthenticationRequest.UserIdentifierType.LoginHint);
                }
                createAcquireTokenRequest(aPIEvent).acquireToken(iWindowComponent, z, authenticationRequest, authenticationCallback);
            }
        } catch (AuthenticationException e) {
            authenticationCallback2.onError(e);
        }
    }
}
