package com.microsoft.aad.adal;

import android.accounts.AccountAuthenticatorResponse;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.security.KeyChain;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.cache.StorageHelper;
import com.microsoft.identity.common.adal.internal.net.IWebRequestHandler;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Locale;
import java.util.UUID;

@SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
/* compiled from: PG */
public class AuthenticationActivity extends Activity {
    public static final int BACK_PRESSED_CANCEL_DIALOG_STEPS = -2;
    public static final String TAG = "AuthenticationActivity";
    public AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
    public AuthenticationRequest mAuthRequest;
    public Bundle mAuthenticatorResultBundle = null;
    public String mCallingPackage;
    public int mCallingUID;
    public final IJWSBuilder mJWSBuilder = new JWSBuilder();
    public boolean mPkeyAuthRedirect = false;
    public ActivityBroadcastReceiver mReceiver = null;
    public String mRedirectUrl;
    public boolean mRegisterReceiver = false;
    public ProgressDialog mSpinner;
    public String mStartUrl;
    public StorageHelper mStorageHelper;
    public UIEvent mUIEvent = null;
    public int mWaitingRequestId;
    public final IWebRequestHandler mWebRequestHandler = new WebRequestHandler();
    public WebView mWebView;

    /* compiled from: PG */
    public class ActivityBroadcastReceiver extends BroadcastReceiver {
        public int mWaitingRequestId;

        public ActivityBroadcastReceiver() {
            this.mWaitingRequestId = -1;
        }

        public void onReceive(Context context, Intent intent) {
            Logger.m1250v("AuthenticationActivity:onReceive", "ActivityBroadcastReceiver onReceive");
            if (intent.getAction().equalsIgnoreCase("com.microsoft.aad.adal:BrowserCancel")) {
                Logger.m1250v("AuthenticationActivity:onReceive", "ActivityBroadcastReceiver onReceive action is for cancelling Authentication Activity");
                if (intent.getIntExtra("com.microsoft.aad.adal:RequestId", 0) == this.mWaitingRequestId) {
                    Logger.m1250v("AuthenticationActivity:onReceive", "Waiting requestId is same and cancelling this activity");
                    AuthenticationActivity.this.finish();
                }
            }
        }
    }

    /* compiled from: PG */
    public class CustomWebViewClient extends BasicWebViewClient {
        public CustomWebViewClient() {
            super(AuthenticationActivity.this, AuthenticationActivity.this.mRedirectUrl, AuthenticationActivity.this.mAuthRequest, AuthenticationActivity.this.mUIEvent);
        }

        public void cancelWebViewRequest() {
            AuthenticationActivity.this.cancelRequest();
        }

        @TargetApi(21)
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            Logger.m1250v("AuthenticationActivity:onReceivedClientCertRequest", "Webview receives client TLS request.");
            Principal[] principals = clientCertRequest.getPrincipals();
            if (principals != null) {
                for (Principal name : principals) {
                    if (name.getName().contains("CN=MS-Organization-Access")) {
                        Logger.m1250v("AuthenticationActivity:onReceivedClientCertRequest", "Cancelling the TLS request, not respond to TLS challenge triggered by device authentication.");
                        clientCertRequest.cancel();
                        return;
                    }
                }
            }
            KeyChain.choosePrivateKeyAlias(AuthenticationActivity.this, new 1(this, clientCertRequest), clientCertRequest.getKeyTypes(), clientCertRequest.getPrincipals(), clientCertRequest.getHost(), clientCertRequest.getPort(), (String) null);
        }

        public void postRunnable(Runnable runnable) {
            AuthenticationActivity.this.mWebView.post(runnable);
        }

        public void prepareForBrokerResumeRequest() {
            AuthenticationActivity.this.prepareForBrokerResume();
        }

        public boolean processInvalidUrl(WebView webView, String str) {
            AuthenticationActivity authenticationActivity = AuthenticationActivity.this;
            if (authenticationActivity.isBrokerRequest(authenticationActivity.getIntent()) && str.startsWith("msauth")) {
                Logger.m1245e("AuthenticationActivity:processInvalidUrl", "The RedirectUri is not as expected.", String.format("Received %s and expected %s", new Object[]{str, AuthenticationActivity.this.mRedirectUrl}), ADALError.DEVELOPER_REDIRECTURI_INVALID);
                AuthenticationActivity authenticationActivity2 = AuthenticationActivity.this;
                authenticationActivity2.returnError(ADALError.DEVELOPER_REDIRECTURI_INVALID, String.format("The RedirectUri is not as expected. Received %s and expected %s", new Object[]{str, authenticationActivity2.mRedirectUrl}));
                webView.stopLoading();
                return true;
            } else if (str.toLowerCase(Locale.US).equals("about:blank")) {
                Logger.m1250v("AuthenticationActivity:processInvalidUrl", "It is an blank page request");
                return true;
            } else if (str.toLowerCase(Locale.US).startsWith("https://")) {
                return false;
            } else {
                Logger.m1245e("AuthenticationActivity:processInvalidUrl", "The webview was redirected to an unsafe URL.", BuildConfig.FLAVOR, ADALError.WEBVIEW_REDIRECTURL_NOT_SSL_PROTECTED);
                AuthenticationActivity.this.returnError(ADALError.WEBVIEW_REDIRECTURL_NOT_SSL_PROTECTED, "The webview was redirected to an unsafe URL.");
                webView.stopLoading();
                return true;
            }
        }

        public void processRedirectUrl(WebView webView, String str) {
            AuthenticationActivity authenticationActivity = AuthenticationActivity.this;
            if (!authenticationActivity.isBrokerRequest(authenticationActivity.getIntent())) {
                Logger.m1248i("AuthenticationActivity:processRedirectUrl", "It is not a broker request", BuildConfig.FLAVOR);
                Intent intent = new Intent();
                intent.putExtra("com.microsoft.aad.adal:BrowserFinalUrl", str);
                intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", AuthenticationActivity.this.mAuthRequest);
                AuthenticationActivity.this.returnToCaller(2003, intent);
                webView.stopLoading();
                return;
            }
            Logger.m1248i("AuthenticationActivity:processRedirectUrl", "It is a broker request", BuildConfig.FLAVOR);
            AuthenticationActivity authenticationActivity2 = AuthenticationActivity.this;
            authenticationActivity2.displaySpinnerWithMessage(authenticationActivity2.getText(gs1.b(authenticationActivity2.getResources(), "broker_processing", "string", AuthenticationActivity.this.getPackageName())));
            webView.stopLoading();
            AuthenticationActivity authenticationActivity3 = AuthenticationActivity.this;
            new TokenTask(authenticationActivity3, authenticationActivity3.mWebRequestHandler, AuthenticationActivity.this.mAuthRequest, AuthenticationActivity.this.mCallingPackage, AuthenticationActivity.this.mCallingUID).execute(new String[]{str});
        }

        public void sendResponse(int i, Intent intent) {
            AuthenticationActivity.this.returnToCaller(i, intent);
        }

        public void setPKeyAuthStatus(boolean z) {
            boolean unused = AuthenticationActivity.this.mPkeyAuthRedirect = z;
        }

        public void showSpinner(boolean z) {
            AuthenticationActivity.this.displaySpinner(z);
        }
    }

    /* access modifiers changed from: private */
    public void cancelRequest() {
        Logger.m1250v(TAG, "Sending intent to cancel authentication activity");
        Intent intent = new Intent();
        UIEvent uIEvent = this.mUIEvent;
        if (uIEvent != null) {
            uIEvent.setUserCancel();
        }
        returnToCaller(2001, intent);
    }

    /* access modifiers changed from: private */
    public void displaySpinner(boolean z) {
        if (!isFinishing() && !isChangingConfigurations() && this.mSpinner != null) {
            Logger.m1250v("AuthenticationActivity:displaySpinner", "DisplaySpinner:" + z + " showing:" + this.mSpinner.isShowing());
            if (z && !this.mSpinner.isShowing()) {
                this.mSpinner.show();
            }
            if (!z && this.mSpinner.isShowing()) {
                this.mSpinner.dismiss();
            }
        }
    }

    /* access modifiers changed from: private */
    public void displaySpinnerWithMessage(CharSequence charSequence) {
        ProgressDialog progressDialog;
        if (!isFinishing() && (progressDialog = this.mSpinner) != null) {
            progressDialog.show();
            this.mSpinner.setMessage(charSequence);
        }
    }

    private AuthenticationRequest getAuthenticationRequestFromIntent(Intent intent) {
        UUID uuid = null;
        if (isBrokerRequest(intent)) {
            Logger.m1250v("AuthenticationActivity:getAuthenticationRequestFromIntent", "It is a broker request. Get request info from bundle extras.");
            String stringExtra = intent.getStringExtra("account.authority");
            String stringExtra2 = intent.getStringExtra("account.resource");
            String stringExtra3 = intent.getStringExtra("account.redirect");
            String stringExtra4 = intent.getStringExtra("account.login.hint");
            String stringExtra5 = intent.getStringExtra("account.name");
            String stringExtra6 = intent.getStringExtra("account.clientid.key");
            String stringExtra7 = intent.getStringExtra("account.correlationid");
            String stringExtra8 = intent.getStringExtra("account.prompt");
            PromptBehavior promptBehavior = PromptBehavior.Auto;
            if (!StringExtensions.isNullOrBlank(stringExtra8)) {
                promptBehavior = PromptBehavior.valueOf(stringExtra8);
            }
            PromptBehavior promptBehavior2 = promptBehavior;
            this.mWaitingRequestId = intent.getIntExtra("com.microsoft.aad.adal:RequestId", 0);
            if (!StringExtensions.isNullOrBlank(stringExtra7)) {
                try {
                    uuid = UUID.fromString(stringExtra7);
                } catch (IllegalArgumentException unused) {
                    Logger.m1245e("AuthenticationActivity:getAuthenticationRequestFromIntent", Eo.a("CorrelationId is malformed: ", stringExtra7), BuildConfig.FLAVOR, ADALError.CORRELATION_ID_FORMAT);
                }
            }
            AuthenticationRequest authenticationRequest = new AuthenticationRequest(stringExtra, stringExtra2, stringExtra6, stringExtra3, stringExtra4, uuid, false);
            authenticationRequest.setBrokerAccountName(stringExtra5);
            authenticationRequest.setPrompt(promptBehavior2);
            authenticationRequest.setRequestId(this.mWaitingRequestId);
            return authenticationRequest;
        }
        Serializable serializableExtra = intent.getSerializableExtra("com.microsoft.aad.adal:BrowserRequestMessage");
        if (serializableExtra instanceof AuthenticationRequest) {
            return (AuthenticationRequest) serializableExtra;
        }
        return null;
    }

    private String getBrokerStartUrl(String str, String str2, String str3) {
        if (!StringExtensions.isNullOrBlank(str2) && !StringExtensions.isNullOrBlank(str3)) {
            try {
                return str + "&package_name=" + URLEncoder.encode(str2, "UTF-8") + "&signature=" + URLEncoder.encode(str3, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Logger.m1247e(TAG, "Encoding", e);
            }
        }
        return str;
    }

    private void hideKeyBoard() {
        if (this.mWebView != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.mWebView.getApplicationWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public boolean isBrokerRequest(Intent intent) {
        return intent != null && !StringExtensions.isNullOrBlank(intent.getStringExtra("com.microsoft.aadbroker.adal.broker.request"));
    }

    private boolean isCallerBrokerInstaller() {
        PackageHelper packageHelper = new PackageHelper(this);
        String callingPackage = getCallingPackage();
        if (StringExtensions.isNullOrBlank(callingPackage)) {
            return false;
        }
        if (callingPackage.equals(AuthenticationSettings.INSTANCE.getBrokerPackageName())) {
            Logger.m1250v("AuthenticationActivity:isCallerBrokerInstaller", "Same package as broker.");
            return true;
        }
        String currentSignatureForPackage = packageHelper.getCurrentSignatureForPackage(callingPackage);
        StringBuilder a = Eo.a("Check signature for ", callingPackage, " signature:", currentSignatureForPackage, " brokerSignature:");
        a.append(AuthenticationSettings.INSTANCE.getBrokerSignature());
        Logger.m1251v("AuthenticationActivity:isCallerBrokerInstaller", "Checking broker signature. ", a.toString(), (ADALError) null);
        if (currentSignatureForPackage.equals(AuthenticationSettings.INSTANCE.getBrokerSignature()) || currentSignatureForPackage.equals("ho040S3ffZkmxqtQrSwpTVOn9r0=")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void prepareForBrokerResume() {
        Logger.m1250v("AuthenticationActivity:prepareForBrokerResume", "Return to caller with BROKER_REQUEST_RESUME, and waiting for result.");
        returnToCaller(2006, new Intent());
    }

    /* access modifiers changed from: private */
    public void returnError(ADALError aDALError, String str) {
        Logger.m1252w(TAG, "Argument error:" + str);
        Intent intent = new Intent();
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorCode", aDALError.name());
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorMessage", str);
        if (this.mAuthRequest != null) {
            intent.putExtra("com.microsoft.aad.adal:RequestId", this.mWaitingRequestId);
            intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", this.mAuthRequest);
        }
        setResult(2002, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void returnResult(int i, Intent intent) {
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(i, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void returnToCaller(int i, Intent intent) {
        Logger.m1250v("AuthenticationActivity:returnToCaller", "Return To Caller:" + i);
        displaySpinner(false);
        if (intent == null) {
            intent = new Intent();
        }
        if (this.mAuthRequest == null) {
            Logger.m1253w("AuthenticationActivity:returnToCaller", "Request object is null", BuildConfig.FLAVOR, ADALError.ACTIVITY_REQUEST_INTENT_DATA_IS_NULL);
        } else {
            StringBuilder a = Eo.a("Set request id related to response. REQUEST_ID for caller returned to:");
            a.append(this.mAuthRequest.getRequestId());
            Logger.m1250v("AuthenticationActivity:returnToCaller", a.toString());
            intent.putExtra("com.microsoft.aad.adal:RequestId", this.mAuthRequest.getRequestId());
        }
        setResult(i, intent);
        finish();
    }

    private void setAccountAuthenticatorResult(Bundle bundle) {
        this.mAuthenticatorResultBundle = bundle;
    }

    private void setupWebView() {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.requestFocus(130);
        this.mWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if ((action != 0 && action != 1) || view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            }
        });
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.setWebViewClient(new CustomWebViewClient());
        this.mWebView.setVisibility(4);
    }

    public Context createConfigurationContext(Configuration configuration) {
        boolean d = gs1.d();
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        return !d ? createConfigurationContext : gs1.f(createConfigurationContext);
    }

    public void finish() {
        if (isBrokerRequest(getIntent()) && this.mAccountAuthenticatorResponse != null) {
            Logger.m1250v(TAG, "It is a broker request");
            Bundle bundle = this.mAuthenticatorResultBundle;
            if (bundle == null) {
                this.mAccountAuthenticatorResponse.onError(4, "canceled");
            } else {
                this.mAccountAuthenticatorResponse.onResult(bundle);
            }
            this.mAccountAuthenticatorResponse = null;
        }
        super.finish();
    }

    public AssetManager getAssets() {
        return !gs1.d() ? super.getAssets() : gs1.g(this);
    }

    public Resources getResources() {
        return !gs1.d() ? super.getResources() : gs1.h(this);
    }

    public Resources.Theme getTheme() {
        return !gs1.d() ? super.getTheme() : gs1.i(this);
    }

    public void onBackPressed() {
        Logger.m1250v(TAG, "Back button is pressed");
        if (this.mPkeyAuthRedirect || !this.mWebView.canGoBackOrForward(-2)) {
            cancelRequest();
        } else {
            this.mWebView.goBack();
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(gs1.b(getResources(), "activity_authentication", "layout", getPackageName()));
        CookieSyncManager.createInstance(getApplicationContext());
        CookieSyncManager.getInstance().sync();
        CookieManager.getInstance().setAcceptCookie(true);
        Logger.m1250v("AuthenticationActivity:onCreate", "AuthenticationActivity was created.");
        this.mAuthRequest = getAuthenticationRequestFromIntent(getIntent());
        AuthenticationRequest authenticationRequest = this.mAuthRequest;
        if (authenticationRequest == null) {
            Logger.m1243d("AuthenticationActivity:onCreate", "Intent for Authentication Activity doesn't have the request details, returning to caller");
            Intent intent = new Intent();
            intent.putExtra("com.microsoft.aad.adal:BrowserErrorCode", "Invalid request");
            intent.putExtra("com.microsoft.aad.adal:BrowserErrorMessage", "Intent does not have request details");
            returnToCaller(2002, intent);
        } else if (authenticationRequest.getAuthority() == null || this.mAuthRequest.getAuthority().isEmpty()) {
            returnError(ADALError.ARGUMENT_EXCEPTION, "account.authority");
        } else if (this.mAuthRequest.getResource() == null || this.mAuthRequest.getResource().isEmpty()) {
            returnError(ADALError.ARGUMENT_EXCEPTION, "account.resource");
        } else if (this.mAuthRequest.getClientId() == null || this.mAuthRequest.getClientId().isEmpty()) {
            returnError(ADALError.ARGUMENT_EXCEPTION, "account.clientid.key");
        } else if (this.mAuthRequest.getRedirectUri() == null || this.mAuthRequest.getRedirectUri().isEmpty()) {
            returnError(ADALError.ARGUMENT_EXCEPTION, "account.redirect");
        } else {
            this.mRedirectUrl = this.mAuthRequest.getRedirectUri();
            C0336Telemetry.getInstance().startEvent(this.mAuthRequest.getTelemetryRequestId(), "Microsoft.ADAL.ui_event");
            this.mUIEvent = new UIEvent("Microsoft.ADAL.ui_event");
            this.mUIEvent.setRequestId(this.mAuthRequest.getTelemetryRequestId());
            this.mUIEvent.setCorrelationId(this.mAuthRequest.getCorrelationId().toString());
            this.mWebView = (WebView) findViewById(gs1.b(getResources(), "webView1", "id", getPackageName()));
            if (!AuthenticationSettings.INSTANCE.getDisableWebViewHardwareAcceleration()) {
                this.mWebView.setLayerType(1, (Paint) null);
                Logger.m1243d("AuthenticationActivity:onCreate", "Hardware acceleration is disabled in WebView");
            }
            this.mStartUrl = "about:blank";
            try {
                this.mStartUrl = new Oauth2(this.mAuthRequest).getCodeRequestUrl();
                StringBuilder a = Eo.a("Init broadcastReceiver with request. RequestId:");
                a.append(this.mAuthRequest.getRequestId());
                Logger.m1251v("AuthenticationActivity:onCreate", a.toString(), this.mAuthRequest.getLogInfo(), (ADALError) null);
                this.mReceiver = new ActivityBroadcastReceiver();
                int unused = this.mReceiver.mWaitingRequestId = this.mAuthRequest.getRequestId();
                Y5.a(this).a(this.mReceiver, new IntentFilter("com.microsoft.aad.adal:BrowserCancel"));
                String userAgentString = this.mWebView.getSettings().getUserAgentString();
                WebSettings settings = this.mWebView.getSettings();
                settings.setUserAgentString(userAgentString + " PKeyAuth/1.0");
                String userAgentString2 = this.mWebView.getSettings().getUserAgentString();
                Logger.m1251v("AuthenticationActivity:onCreate", BuildConfig.FLAVOR, "UserAgent:" + userAgentString2, (ADALError) null);
                if (isBrokerRequest(getIntent())) {
                    this.mCallingPackage = getCallingPackage();
                    if (this.mCallingPackage == null) {
                        Logger.m1250v("AuthenticationActivity:onCreate", "Calling package is null, startActivityForResult is not used to call this activity");
                        Intent intent2 = new Intent();
                        intent2.putExtra("com.microsoft.aad.adal:BrowserErrorCode", "Invalid request");
                        intent2.putExtra("com.microsoft.aad.adal:BrowserErrorMessage", "startActivityForResult is not used to call this activity");
                        returnToCaller(2002, intent2);
                        return;
                    }
                    StringBuilder a2 = Eo.a("It is a broker request for package:");
                    a2.append(this.mCallingPackage);
                    Logger.m1248i("AuthenticationActivity:onCreate", a2.toString(), BuildConfig.FLAVOR);
                    this.mAccountAuthenticatorResponse = (AccountAuthenticatorResponse) getIntent().getParcelableExtra("accountAuthenticatorResponse");
                    AccountAuthenticatorResponse accountAuthenticatorResponse = this.mAccountAuthenticatorResponse;
                    if (accountAuthenticatorResponse != null) {
                        accountAuthenticatorResponse.onRequestContinued();
                    }
                    PackageHelper packageHelper = new PackageHelper(this);
                    this.mCallingPackage = getCallingPackage();
                    this.mCallingUID = packageHelper.getUIDForPackage(this.mCallingPackage);
                    String currentSignatureForPackage = packageHelper.getCurrentSignatureForPackage(this.mCallingPackage);
                    this.mStartUrl = getBrokerStartUrl(this.mStartUrl, this.mCallingPackage, currentSignatureForPackage);
                    if (!isCallerBrokerInstaller()) {
                        Logger.m1250v("AuthenticationActivity:onCreate", "Caller needs to be verified using special redirectUri");
                        this.mRedirectUrl = PackageHelper.getBrokerRedirectUrl(this.mCallingPackage, currentSignatureForPackage);
                    }
                    StringBuilder a3 = Eo.a("Broker redirectUrl: ");
                    a3.append(this.mRedirectUrl);
                    a3.append(" The calling package is: ");
                    Eo.b(a3, this.mCallingPackage, " Signature hash for calling package is: ", currentSignatureForPackage, " Current context package: ");
                    a3.append(getPackageName());
                    a3.append(" Start url: ");
                    a3.append(this.mStartUrl);
                    Logger.m1251v("AuthenticationActivity:onCreate", BuildConfig.FLAVOR, a3.toString(), (ADALError) null);
                } else {
                    StringBuilder a4 = Eo.a("Non-broker request for package ");
                    a4.append(getCallingPackage());
                    String sb = a4.toString();
                    StringBuilder a5 = Eo.a(" Start url: ");
                    a5.append(this.mStartUrl);
                    Logger.m1251v("AuthenticationActivity:onCreate", sb, a5.toString(), (ADALError) null);
                }
                this.mRegisterReceiver = false;
                final String str = this.mStartUrl;
                StringBuilder a6 = Eo.a("Device info:");
                a6.append(Build.VERSION.RELEASE);
                a6.append(" ");
                a6.append(Build.MANUFACTURER);
                a6.append(Build.MODEL);
                Logger.m1248i("AuthenticationActivity:onCreate", a6.toString(), BuildConfig.FLAVOR);
                this.mStorageHelper = new StorageHelper(getApplicationContext());
                setupWebView();
                if (this.mAuthRequest.getCorrelationId() == null) {
                    Logger.m1250v("AuthenticationActivity:onCreate", "Null correlation id in the request.");
                } else {
                    StringBuilder a7 = Eo.a("Correlation id for request sent is:");
                    a7.append(this.mAuthRequest.getCorrelationId().toString());
                    Logger.m1250v("AuthenticationActivity:onCreate", a7.toString());
                }
                if (bundle == null) {
                    this.mWebView.post(new Runnable() {
                        public void run() {
                            Logger.m1250v("AuthenticationActivity:onCreate", "Launching webview for acquiring auth code.");
                            AuthenticationActivity.this.mWebView.loadUrl("about:blank");
                            AuthenticationActivity.this.mWebView.loadUrl(str);
                        }
                    });
                } else {
                    Logger.m1250v("AuthenticationActivity:onCreate", "Reuse webview");
                }
            } catch (UnsupportedEncodingException e) {
                Logger.m1251v("AuthenticationActivity:onCreate", "Encoding format is not supported. ", e.getMessage(), (ADALError) null);
                Intent intent3 = new Intent();
                intent3.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", this.mAuthRequest);
                returnToCaller(2002, intent3);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mUIEvent != null) {
            C0336Telemetry.getInstance().stopEvent(this.mAuthRequest.getTelemetryRequestId(), this.mUIEvent, "Microsoft.ADAL.ui_event");
        }
    }

    public void onPause() {
        Logger.m1250v("AuthenticationActivity:onPause", "AuthenticationActivity onPause unregister receiver");
        super.onPause();
        if (this.mReceiver != null) {
            Y5.a(this).a(this.mReceiver);
        }
        this.mRegisterReceiver = true;
        if (this.mSpinner != null) {
            Logger.m1250v("AuthenticationActivity:onPause", "Spinner at onPause will dismiss");
            this.mSpinner.dismiss();
        }
        hideKeyBoard();
    }

    public void onRestart() {
        Logger.m1250v(TAG, "AuthenticationActivity onRestart");
        super.onRestart();
        this.mRegisterReceiver = true;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mWebView.restoreState(bundle);
    }

    public void onResume() {
        super.onResume();
        if (this.mRegisterReceiver) {
            StringBuilder a = Eo.a("StartUrl: ");
            a.append(this.mStartUrl);
            Logger.m1251v("AuthenticationActivity:onResume", "Webview onResume will register receiver. ", a.toString(), (ADALError) null);
            if (this.mReceiver != null) {
                StringBuilder a2 = Eo.a("Webview onResume register broadcast receiver for request. RequestId: ");
                a2.append(this.mReceiver.mWaitingRequestId);
                Logger.m1250v("AuthenticationActivity:onResume", a2.toString());
                Y5.a(this).a(this.mReceiver, new IntentFilter("com.microsoft.aad.adal:BrowserCancel"));
            }
        }
        this.mRegisterReceiver = false;
        this.mSpinner = new ProgressDialog(this);
        this.mSpinner.requestWindowFeature(1);
        this.mSpinner.setMessage(getText(gs1.b(getResources(), "app_loading", "string", getPackageName())));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mWebView.saveState(bundle);
    }

    public void setTheme(int i) {
        if (!gs1.d()) {
            super.setTheme(i);
        } else {
            gs1.b(this, i);
        }
    }
}
