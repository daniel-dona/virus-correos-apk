package com.microsoft.identity.common.internal.providers.oauth2;

import android.content.Intent;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.OAuth2Strategy;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/* compiled from: PG */
public abstract class AuthorizationStrategy<GenericOAuth2Strategy extends OAuth2Strategy, GenericAuthorizationRequest extends AuthorizationRequest> {
    public static final String AUTHORIZATION_FINAL_URL = "com.microsoft.identity.client.final.url";
    public static final int BROWSER_FLOW = 1001;
    public static final String CUSTOM_TAB_REDIRECT = "com.microsoft.identity.customtab.redirect";
    public static final String REQUEST_CODE = "com.microsoft.identity.client.request.code";
    public static final String REQUEST_ID = "com.microsoft.identity.request.id";
    public static final String REQUEST_URL_KEY = "com.microsoft.identity.request.url.key";
    public static final String RESULT_CODE = "com.microsoft.identity.client.result.code";

    public abstract void completeAuthorization(int i, int i2, Intent intent);

    public abstract Future<AuthorizationResult> requestAuthorization(GenericAuthorizationRequest genericauthorizationrequest, GenericOAuth2Strategy genericoauth2strategy) throws ClientException, UnsupportedEncodingException;
}
