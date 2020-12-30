package com.microsoft.aad.adal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.ChallengeResponseBuilder;
import com.microsoft.aad.adal.HttpAuthDialog;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: PG */
public abstract class BasicWebViewClient extends WebViewClient {
    public static final String BLANK_PAGE = "about:blank";
    public static final String INSTALL_URL_KEY = "app_link";
    public static final String TAG = "BasicWebViewClient";
    public final Context mCallingContext;
    public String mRedirect;
    public final AuthenticationRequest mRequest;
    public final UIEvent mUIEvent;

    public BasicWebViewClient(Context context, String str, AuthenticationRequest authenticationRequest, UIEvent uIEvent) {
        this.mCallingContext = context;
        this.mRedirect = str;
        this.mRequest = authenticationRequest;
        this.mUIEvent = uIEvent;
    }

    private void logPageStartLoadingUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.m1250v("BasicWebViewClient:logPageStartLoadingUrl", "onPageStarted: Null url for page to load.");
            return;
        }
        Uri parse = Uri.parse(str);
        if (parse.isOpaque()) {
            Logger.m1251v("BasicWebViewClient:logPageStartLoadingUrl", "onPageStarted: Non-hierarchical loading uri. ", "Url: " + str, (ADALError) null);
        } else if (StringExtensions.isNullOrBlank(parse.getQueryParameter("code"))) {
            StringBuilder a = Eo.a(" Host: ");
            a.append(parse.getHost());
            a.append(" Path: ");
            a.append(parse.getPath());
            a.append(" Full loading url is: ");
            a.append(str);
            Logger.m1251v("BasicWebViewClient:logPageStartLoadingUrl", "Webview starts loading. ", a.toString(), (ADALError) null);
        } else {
            StringBuilder a2 = Eo.a(" Host: ");
            a2.append(parse.getHost());
            a2.append(" Path: ");
            a2.append(parse.getPath());
            a2.append(" Auth code is returned for the loading url.");
            Logger.m1251v("BasicWebViewClient:logPageStartLoadingUrl", "Webview starts loading. ", a2.toString(), (ADALError) null);
        }
    }

    public abstract void cancelWebViewRequest();

    public final Context getCallingContext() {
        return this.mCallingContext;
    }

    public boolean hasCancelError(String str) {
        HashMap<String, String> urlParameters = StringExtensions.getUrlParameters(str);
        String str2 = urlParameters.get(AuthorizationResultFactory.ERROR);
        String str3 = urlParameters.get(AuthorizationResultFactory.ERROR_DESCRIPTION);
        if (StringExtensions.isNullOrBlank(str2)) {
            return false;
        }
        Logger.m1253w(TAG, "Cancel error: " + str2, str3, (ADALError) null);
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        webView.setVisibility(0);
        if (!str.startsWith("about:blank")) {
            showSpinner(false);
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        logPageStartLoadingUrl(str);
        super.onPageStarted(webView, str, bitmap);
        showSpinner(true);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        showSpinner(false);
        Logger.m1245e(TAG, "Webview received an error. ErrorCode:" + i, str, ADALError.ERROR_WEBVIEW);
        Intent intent = new Intent();
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorCode", "Error Code:" + i);
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorMessage", str);
        intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", this.mRequest);
        sendResponse(2002, intent);
    }

    public void onReceivedHttpAuthRequest(WebView webView, final HttpAuthHandler httpAuthHandler, String str, String str2) {
        Logger.m1248i("BasicWebViewClient:onReceivedHttpAuthRequest", "Start. ", "Host:" + str);
        UIEvent uIEvent = this.mUIEvent;
        if (uIEvent != null) {
            uIEvent.setNTLM(true);
        }
        HttpAuthDialog httpAuthDialog = new HttpAuthDialog(this.mCallingContext, str, str2);
        httpAuthDialog.setOkListener(new HttpAuthDialog.OkListener() {
            public void onOk(String str, String str2, String str3, String str4) {
                Logger.m1248i("BasicWebViewClient:onReceivedHttpAuthRequest", "Handler proceed. ", "Host: " + str);
                httpAuthHandler.proceed(str3, str4);
            }
        });
        httpAuthDialog.setCancelListener(new HttpAuthDialog.CancelListener() {
            public void onCancel() {
                Logger.m1248i("BasicWebViewClient:onReceivedHttpAuthRequest", "Handler cancelled", BuildConfig.FLAVOR);
                httpAuthHandler.cancel();
                BasicWebViewClient.this.cancelWebViewRequest();
            }
        });
        Logger.m1248i("BasicWebViewClient:onReceivedHttpAuthRequest", "Show dialog. ", BuildConfig.FLAVOR);
        httpAuthDialog.show();
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        showSpinner(false);
        sslErrorHandler.cancel();
        Logger.m1245e(TAG, "Received ssl error. ", BuildConfig.FLAVOR, ADALError.ERROR_FAILED_SSL_HANDSHAKE);
        Intent intent = new Intent();
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorCode", "Code:-11");
        intent.putExtra("com.microsoft.aad.adal:BrowserErrorMessage", sslError.toString());
        intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", this.mRequest);
        sendResponse(2002, intent);
    }

    public void openLinkInBrowser(String str) {
        this.mCallingContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str.replace("browser://", "https://"))));
    }

    public abstract void postRunnable(Runnable runnable);

    public abstract void prepareForBrokerResumeRequest();

    public abstract boolean processInvalidUrl(WebView webView, String str);

    public abstract void processRedirectUrl(WebView webView, String str);

    public abstract void sendResponse(int i, Intent intent);

    public abstract void setPKeyAuthStatus(boolean z);

    public boolean shouldOverrideUrlLoading(final WebView webView, final String str) {
        Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "Navigation is detected");
        if (str.startsWith("urn:http-auth:PKeyAuth")) {
            Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "Webview detected request for pkeyauth challenge.");
            webView.stopLoading();
            setPKeyAuthStatus(true);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ChallengeResponseBuilder.ChallengeResponse challengeResponseFromUri = new ChallengeResponseBuilder(new JWSBuilder()).getChallengeResponseFromUri(str);
                        HashMap hashMap = new HashMap();
                        hashMap.put("Authorization", challengeResponseFromUri.getAuthorizationHeaderValue());
                        BasicWebViewClient.this.postRunnable(new 1(this, challengeResponseFromUri, hashMap));
                    } catch (AuthenticationServerProtocolException e) {
                        Logger.m1246e("BasicWebViewClient:shouldOverrideUrlLoading", "Argument exception. ", e.getMessage(), ADALError.ARGUMENT_EXCEPTION, e);
                        Intent intent = new Intent();
                        intent.putExtra("com.microsoft.aad.adal:AuthenticationException", e);
                        AuthenticationRequest authenticationRequest = BasicWebViewClient.this.mRequest;
                        if (authenticationRequest != null) {
                            intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", authenticationRequest);
                        }
                        BasicWebViewClient.this.sendResponse(2005, intent);
                    } catch (AuthenticationException e2) {
                        Logger.m1246e("BasicWebViewClient:shouldOverrideUrlLoading", "It is failed to create device certificate response", e2.getMessage(), ADALError.DEVICE_CERTIFICATE_RESPONSE_FAILED, e2);
                        Intent intent2 = new Intent();
                        intent2.putExtra("com.microsoft.aad.adal:AuthenticationException", e2);
                        AuthenticationRequest authenticationRequest2 = BasicWebViewClient.this.mRequest;
                        if (authenticationRequest2 != null) {
                            intent2.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", authenticationRequest2);
                        }
                        BasicWebViewClient.this.sendResponse(2005, intent2);
                    }
                }
            }).start();
            return true;
        } else if (str.toLowerCase(Locale.US).startsWith(this.mRedirect.toLowerCase(Locale.US))) {
            Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "Navigation starts with the redirect uri.");
            if (hasCancelError(str)) {
                Logger.m1248i("BasicWebViewClient:shouldOverrideUrlLoading", "Sending intent to cancel authentication activity", BuildConfig.FLAVOR);
                webView.stopLoading();
                cancelWebViewRequest();
                return true;
            }
            processRedirectUrl(webView, str);
            return true;
        } else if (str.startsWith("browser://")) {
            Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "It is an external website request");
            openLinkInBrowser(str);
            webView.stopLoading();
            cancelWebViewRequest();
            return true;
        } else if (!str.startsWith("msauth://")) {
            return processInvalidUrl(webView, str);
        } else {
            Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "It is an install request");
            HashMap<String, String> urlParameters = StringExtensions.getUrlParameters(str);
            prepareForBrokerResumeRequest();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException unused) {
                Logger.m1250v("BasicWebViewClient:shouldOverrideUrlLoading", "Error occurred when having thread sleeping for 1 second.");
            }
            openLinkInBrowser(urlParameters.get(INSTALL_URL_KEY));
            webView.stopLoading();
            return true;
        }
    }

    public abstract void showSpinner(boolean z);
}
