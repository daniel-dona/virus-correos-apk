package com.microsoft.aad.adal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationStrategy;
import java.io.IOException;
import java.net.MalformedURLException;

/* compiled from: PG */
public final class AcquireTokenInteractiveRequest {
    public static final String TAG = "AcquireTokenInteractiveRequest";
    public final AuthenticationRequest mAuthRequest;
    public final Context mContext;
    public final TokenCacheAccessor mTokenCacheAccessor;

    public AcquireTokenInteractiveRequest(Context context, AuthenticationRequest authenticationRequest, TokenCacheAccessor tokenCacheAccessor) {
        this.mContext = context;
        this.mTokenCacheAccessor = tokenCacheAccessor;
        this.mAuthRequest = authenticationRequest;
    }

    private Intent getAuthenticationActivityIntent() {
        Intent intent = new Intent();
        if (AuthenticationSettings.INSTANCE.getActivityPackageName() != null) {
            intent.setClassName(AuthenticationSettings.INSTANCE.getActivityPackageName(), AuthenticationActivity.class.getName());
        } else {
            intent.setClass(this.mContext, AuthenticationActivity.class);
        }
        intent.putExtra("com.microsoft.aad.adal:BrowserRequestMessage", this.mAuthRequest);
        return intent;
    }

    private String getCorrelationInfo() {
        return String.format(" CorrelationId: %s", new Object[]{this.mAuthRequest.getCorrelationId().toString()});
    }

    private boolean resolveIntent(Intent intent) {
        return this.mContext.getPackageManager().resolveActivity(intent, 0) != null;
    }

    private boolean startAuthenticationActivity(IWindowComponent iWindowComponent) {
        Intent authenticationActivityIntent = getAuthenticationActivityIntent();
        if (!resolveIntent(authenticationActivityIntent)) {
            Logger.m1245e(Eo.a(new StringBuilder(), TAG, ":startAuthenticationActivity"), "Intent is not resolved", BuildConfig.FLAVOR, ADALError.DEVELOPER_ACTIVITY_IS_NOT_RESOLVED);
            return false;
        }
        try {
            iWindowComponent.startActivityForResult(authenticationActivityIntent, AuthorizationStrategy.BROWSER_FLOW);
            return true;
        } catch (ActivityNotFoundException e) {
            Logger.m1246e(Eo.a(new StringBuilder(), TAG, ":startAuthenticationActivity"), "Activity login is not found after resolving intent", BuildConfig.FLAVOR, ADALError.DEVELOPER_ACTIVITY_IS_NOT_RESOLVED, e);
            return false;
        }
    }

    public void acquireToken(IWindowComponent iWindowComponent, AuthenticationDialog authenticationDialog) throws AuthenticationException {
        HttpWebRequest.throwIfNetworkNotAvailable(this.mContext);
        if (PromptBehavior.FORCE_PROMPT == this.mAuthRequest.getPrompt()) {
            Eo.c(new StringBuilder(), TAG, ":acquireToken", "FORCE_PROMPT is set for embedded flow, reset it as Always.");
            this.mAuthRequest.setPrompt(PromptBehavior.Always);
        }
        if (authenticationDialog != null) {
            authenticationDialog.show();
        } else if (!startAuthenticationActivity(iWindowComponent)) {
            throw new AuthenticationException(ADALError.DEVELOPER_ACTIVITY_IS_NOT_RESOLVED);
        }
    }

    public AuthenticationResult acquireTokenWithAuthCode(String str) throws AuthenticationException {
        TokenCacheAccessor tokenCacheAccessor;
        Logger.m1251v(Eo.a(new StringBuilder(), TAG, ":acquireTokenWithAuthCode"), "Start token acquisition with auth code.", this.mAuthRequest.getLogInfo(), (ADALError) null);
        try {
            AuthenticationResult token = new Oauth2(this.mAuthRequest, new WebRequestHandler()).getToken(str);
            Logger.m1250v(TAG + ":acquireTokenWithAuthCode", "OnActivityResult processed the result.");
            if (token == null) {
                String a = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithAuthCode");
                StringBuilder a2 = Eo.a("Returned result with exchanging auth code for token is null");
                a2.append(getCorrelationInfo());
                Logger.m1245e(a, a2.toString(), BuildConfig.FLAVOR, ADALError.AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN);
                throw new AuthenticationException(ADALError.AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN, getCorrelationInfo());
            } else if (StringExtensions.isNullOrBlank(token.getErrorCode())) {
                if (!StringExtensions.isNullOrBlank(token.getAccessToken()) && (tokenCacheAccessor = this.mTokenCacheAccessor) != null) {
                    try {
                        tokenCacheAccessor.updateTokenCache(this.mAuthRequest, token);
                    } catch (MalformedURLException e) {
                        throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
                    }
                }
                return token;
            } else {
                String a3 = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithAuthCode");
                StringBuilder a4 = Eo.a(" ErrorCode:");
                a4.append(token.getErrorCode());
                String sb = a4.toString();
                StringBuilder a5 = Eo.a(" ErrorDescription:");
                a5.append(token.getErrorDescription());
                Logger.m1245e(a3, sb, a5.toString(), ADALError.AUTH_FAILED);
                ADALError aDALError = ADALError.AUTH_FAILED;
                StringBuilder a6 = Eo.a(" ErrorCode:");
                a6.append(token.getErrorCode());
                throw new AuthenticationException(aDALError, a6.toString());
            }
        } catch (AuthenticationException | IOException e2) {
            StringBuilder a7 = Eo.a("Error in processing code to get token. ");
            a7.append(getCorrelationInfo());
            throw new AuthenticationException(ADALError.AUTHORIZATION_CODE_NOT_EXCHANGED_FOR_TOKEN, a7.toString(), e2);
        }
    }
}
