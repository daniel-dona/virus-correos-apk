package com.microsoft.aad.adal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationStrategy;
import java.io.UnsupportedEncodingException;

@SuppressLint({"InflateParams", "SetJavaScriptEnabled", "ClickableViewAccessibility"})
/* compiled from: PG */
public class AuthenticationDialog {
    public static final String TAG = "AuthenticationDialog";
    public final AcquireTokenRequest mAcquireTokenRequest;
    public final Context mContext;
    public Dialog mDialog;
    public final Handler mHandlerInView;
    public final AuthenticationRequest mRequest;
    public WebView mWebView;

    /* compiled from: PG */
    public class DialogWebViewClient extends BasicWebViewClient {
        public DialogWebViewClient(Context context, String str, AuthenticationRequest authenticationRequest) {
            super(context, str, authenticationRequest, (UIEvent) null);
        }

        public void cancelWebViewRequest() {
            AuthenticationDialog.this.cancelFlow();
        }

        public void postRunnable(Runnable runnable) {
            AuthenticationDialog.this.mHandlerInView.post(runnable);
        }

        public void prepareForBrokerResumeRequest() {
        }

        public boolean processInvalidUrl(WebView webView, String str) {
            return false;
        }

        public void processRedirectUrl(WebView webView, String str) {
            Intent intent = new Intent();
            intent.putExtra("com.microsoft.aad.adal:BrowserFinalUrl", str);
            intent.putExtra("com.microsoft.aad.adal:BrowserRequestInfo", this.mRequest);
            intent.putExtra("com.microsoft.aad.adal:RequestId", this.mRequest.getRequestId());
            sendResponse(2003, intent);
            webView.stopLoading();
        }

        public void sendResponse(int i, Intent intent) {
            AuthenticationDialog.this.mDialog.dismiss();
            AuthenticationDialog.this.mAcquireTokenRequest.onActivityResult(AuthorizationStrategy.BROWSER_FLOW, i, intent);
        }

        public void setPKeyAuthStatus(boolean z) {
        }

        public void showSpinner(final boolean z) {
            if (AuthenticationDialog.this.mHandlerInView != null) {
                AuthenticationDialog.this.mHandlerInView.post(new Runnable() {
                    public void run() {
                        ProgressBar progressBar;
                        if (AuthenticationDialog.this.mDialog != null && AuthenticationDialog.this.mDialog.isShowing() && (progressBar = (ProgressBar) AuthenticationDialog.this.mDialog.findViewById(AuthenticationDialog.this.getResourceId("com_microsoft_aad_adal_progressBar", "id"))) != null) {
                            progressBar.setVisibility(z ? 0 : 4);
                        }
                    }
                });
            }
        }
    }

    public AuthenticationDialog(Handler handler, Context context, AcquireTokenRequest acquireTokenRequest, AuthenticationRequest authenticationRequest) {
        this.mHandlerInView = handler;
        this.mContext = context;
        this.mAcquireTokenRequest = acquireTokenRequest;
        this.mRequest = authenticationRequest;
    }

    /* access modifiers changed from: private */
    public void cancelFlow() {
        Logger.m1248i(TAG, "Cancelling dialog", BuildConfig.FLAVOR);
        Intent intent = new Intent();
        intent.putExtra("com.microsoft.aad.adal:RequestId", this.mRequest.getRequestId());
        this.mAcquireTokenRequest.onActivityResult(AuthorizationStrategy.BROWSER_FLOW, 2001, intent);
        Handler handler = this.mHandlerInView;
        if (handler != null) {
            handler.post(new Runnable() {
                public void run() {
                    if (AuthenticationDialog.this.mDialog != null && AuthenticationDialog.this.mDialog.isShowing()) {
                        AuthenticationDialog.this.mDialog.dismiss();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public int getResourceId(String str, String str2) {
        return gs1.b(this.mContext.getResources(), str, str2, this.mContext.getPackageName());
    }

    public void show() {
        this.mHandlerInView.post(new Runnable() {
            public void run() {
                View view;
                LayoutInflater layoutInflater = (LayoutInflater) AuthenticationDialog.this.mContext.getSystemService("layout_inflater");
                AlertDialog.Builder builder = new AlertDialog.Builder(AuthenticationDialog.this.mContext);
                try {
                    view = layoutInflater.inflate(AuthenticationDialog.this.getResourceId("dialog_authentication", "layout"), (ViewGroup) null);
                } catch (InflateException e) {
                    Logger.m1246e(AuthenticationDialog.TAG, "Failed to inflate authentication dialog", BuildConfig.FLAVOR, ADALError.DEVELOPER_DIALOG_INFLATION_ERROR, e);
                    view = null;
                }
                if (view != null) {
                    AuthenticationDialog authenticationDialog = AuthenticationDialog.this;
                    WebView unused = authenticationDialog.mWebView = (WebView) view.findViewById(authenticationDialog.getResourceId("com_microsoft_aad_adal_webView1", "id"));
                }
                if (AuthenticationDialog.this.mWebView == null) {
                    Logger.m1245e("AuthenticationDialog:show", "Expected resource name for webview is com_microsoft_aad_adal_webView1. It is not in your layout file", BuildConfig.FLAVOR, ADALError.DEVELOPER_DIALOG_LAYOUT_INVALID);
                    Intent intent = new Intent();
                    intent.putExtra("com.microsoft.aad.adal:RequestId", AuthenticationDialog.this.mRequest.getRequestId());
                    AuthenticationDialog.this.mAcquireTokenRequest.onActivityResult(AuthorizationStrategy.BROWSER_FLOW, 2001, intent);
                    if (AuthenticationDialog.this.mHandlerInView != null) {
                        AuthenticationDialog.this.mHandlerInView.post(new Runnable() {
                            public void run() {
                                if (AuthenticationDialog.this.mDialog != null && AuthenticationDialog.this.mDialog.isShowing()) {
                                    AuthenticationDialog.this.mDialog.dismiss();
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                if (!AuthenticationSettings.INSTANCE.getDisableWebViewHardwareAcceleration()) {
                    AuthenticationDialog.this.mWebView.setLayerType(1, (Paint) null);
                    Logger.m1243d("AuthenticationDialog:show", "Hardware acceleration is disabled in WebView");
                }
                AuthenticationDialog.this.mWebView.getSettings().setJavaScriptEnabled(true);
                AuthenticationDialog.this.mWebView.requestFocus(130);
                String userAgentString = AuthenticationDialog.this.mWebView.getSettings().getUserAgentString();
                AuthenticationDialog.this.mWebView.getSettings().setUserAgentString(userAgentString + " PKeyAuth/1.0");
                Logger.m1250v("AuthenticationDialog:show", "UserAgent:" + AuthenticationDialog.this.mWebView.getSettings().getUserAgentString());
                AuthenticationDialog.this.mWebView.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        int action = motionEvent.getAction();
                        if ((action != 0 && action != 1) || view.hasFocus()) {
                            return false;
                        }
                        view.requestFocus();
                        return false;
                    }
                });
                AuthenticationDialog.this.mWebView.getSettings().setLoadWithOverviewMode(true);
                AuthenticationDialog.this.mWebView.getSettings().setDomStorageEnabled(true);
                AuthenticationDialog.this.mWebView.getSettings().setUseWideViewPort(true);
                AuthenticationDialog.this.mWebView.getSettings().setBuiltInZoomControls(true);
                try {
                    final String codeRequestUrl = new Oauth2(AuthenticationDialog.this.mRequest).getCodeRequestUrl();
                    AuthenticationDialog.this.mWebView.setWebViewClient(new DialogWebViewClient(AuthenticationDialog.this.mContext, AuthenticationDialog.this.mRequest.getRedirectUri(), AuthenticationDialog.this.mRequest));
                    AuthenticationDialog.this.mWebView.post(new Runnable() {
                        public void run() {
                            AuthenticationDialog.this.mWebView.loadUrl("about:blank");
                            AuthenticationDialog.this.mWebView.loadUrl(codeRequestUrl);
                        }
                    });
                } catch (UnsupportedEncodingException e2) {
                    Logger.m1246e("AuthenticationDialog:show", "Encoding error", BuildConfig.FLAVOR, ADALError.ENCODING_IS_NOT_SUPPORTED, e2);
                }
                builder.setView(view).setCancelable(true);
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        AuthenticationDialog.this.cancelFlow();
                    }
                });
                Dialog unused2 = AuthenticationDialog.this.mDialog = builder.create();
                Logger.m1248i("AuthenticationDialog:show", "Showing authenticationDialog", BuildConfig.FLAVOR);
                AuthenticationDialog.this.mDialog.show();
            }
        });
    }
}
