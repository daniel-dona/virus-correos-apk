package com.microsoft.aad.adal;

import android.content.Context;
import android.util.Log;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.net.IWebRequestHandler;
import com.microsoft.identity.common.adal.internal.net.WebRequestHandler;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.io.IOException;
import java.net.MalformedURLException;

/* compiled from: PG */
public class AcquireTokenSilentHandler {
    public static final String TAG = "AcquireTokenSilentHandler";
    public boolean mAttemptedWithMRRT = false;
    public final AuthenticationRequest mAuthRequest;
    public final Context mContext;
    public TokenCacheItem mMrrtTokenCacheItem;
    public final TokenCacheAccessor mTokenCacheAccessor;
    public IWebRequestHandler mWebRequestHandler = null;

    public AcquireTokenSilentHandler(Context context, AuthenticationRequest authenticationRequest, TokenCacheAccessor tokenCacheAccessor) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (authenticationRequest != null) {
            this.mContext = context;
            this.mAuthRequest = authenticationRequest;
            this.mTokenCacheAccessor = tokenCacheAccessor;
            this.mWebRequestHandler = new WebRequestHandler();
        } else {
            throw new IllegalArgumentException("authRequest");
        }
    }

    private AuthenticationResult acquireTokenWithCachedItem(TokenCacheItem tokenCacheItem) throws AuthenticationException {
        if (StringExtensions.isNullOrBlank(tokenCacheItem.getRefreshToken())) {
            Logger.m1251v(Eo.a(new StringBuilder(), TAG, ":acquireTokenWithCachedItem"), "Token cache item contains empty refresh token, cannot continue refresh token request", this.mAuthRequest.getLogInfo(), (ADALError) null);
            return null;
        }
        AuthenticationResult acquireTokenWithRefreshToken = acquireTokenWithRefreshToken(tokenCacheItem.getRefreshToken());
        if (acquireTokenWithRefreshToken != null && !acquireTokenWithRefreshToken.isExtendedLifeTimeToken()) {
            this.mTokenCacheAccessor.updateCachedItemWithResult(this.mAuthRequest, acquireTokenWithRefreshToken, tokenCacheItem);
        }
        return acquireTokenWithRefreshToken;
    }

    private boolean isMRRTEntryExisted() throws AuthenticationException {
        try {
            TokenCacheItem mRRTItem = this.mTokenCacheAccessor.getMRRTItem(this.mAuthRequest.getClientId(), this.mAuthRequest.getUserFromRequest());
            return mRRTItem != null && !StringExtensions.isNullOrBlank(mRRTItem.getRefreshToken());
        } catch (MalformedURLException e) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
        }
    }

    private boolean isTokenRequestFailed(AuthenticationResult authenticationResult) {
        return authenticationResult != null && !StringExtensions.isNullOrBlank(authenticationResult.getErrorCode());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r5 = useMRRT();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microsoft.aad.adal.AuthenticationResult tryFRT(java.lang.String r4, com.microsoft.aad.adal.AuthenticationResult r5) throws com.microsoft.aad.adal.AuthenticationException {
        /*
            r3 = this;
            com.microsoft.aad.adal.TokenCacheAccessor r0 = r3.mTokenCacheAccessor     // Catch:{ MalformedURLException -> 0x0049 }
            com.microsoft.aad.adal.AuthenticationRequest r1 = r3.mAuthRequest     // Catch:{ MalformedURLException -> 0x0049 }
            java.lang.String r1 = r1.getUserFromRequest()     // Catch:{ MalformedURLException -> 0x0049 }
            com.microsoft.aad.adal.TokenCacheItem r4 = r0.getFRTItem(r4, r1)     // Catch:{ MalformedURLException -> 0x0049 }
            java.lang.String r0 = ":tryFRT"
            if (r4 != 0) goto L_0x0026
            boolean r4 = r3.mAttemptedWithMRRT
            if (r4 != 0) goto L_0x0025
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = TAG
            java.lang.String r1 = "FRT cache item does not exist, fall back to try MRRT."
            Eo.c(r4, r5, r0, r1)
            com.microsoft.aad.adal.AuthenticationResult r4 = r3.useMRRT()
            return r4
        L_0x0025:
            return r5
        L_0x0026:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = TAG
            java.lang.String r2 = "Send request to use FRT for new AT."
            Eo.c(r5, r1, r0, r2)
            com.microsoft.aad.adal.AuthenticationResult r4 = r3.acquireTokenWithCachedItem(r4)
            boolean r5 = r3.isTokenRequestFailed(r4)
            if (r5 == 0) goto L_0x0048
            boolean r5 = r3.mAttemptedWithMRRT
            if (r5 != 0) goto L_0x0048
            com.microsoft.aad.adal.AuthenticationResult r5 = r3.useMRRT()
            if (r5 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r4 = r5
        L_0x0048:
            return r4
        L_0x0049:
            r4 = move-exception
            com.microsoft.aad.adal.AuthenticationException r5 = new com.microsoft.aad.adal.AuthenticationException
            com.microsoft.aad.adal.ADALError r0 = com.microsoft.aad.adal.ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL
            java.lang.String r1 = r4.getMessage()
            r5.<init>((com.microsoft.aad.adal.ADALError) r0, (java.lang.String) r1, (java.lang.Throwable) r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.aad.adal.AcquireTokenSilentHandler.tryFRT(java.lang.String, com.microsoft.aad.adal.AuthenticationResult):com.microsoft.aad.adal.AuthenticationResult");
    }

    private AuthenticationResult tryMRRT() throws AuthenticationException {
        try {
            this.mMrrtTokenCacheItem = this.mTokenCacheAccessor.getMRRTItem(this.mAuthRequest.getClientId(), this.mAuthRequest.getUserFromRequest());
            TokenCacheItem tokenCacheItem = this.mMrrtTokenCacheItem;
            String str = "1";
            if (tokenCacheItem == null) {
                Eo.c(new StringBuilder(), TAG, ":tryMRRT", "MRRT token does not exist, try with FRT");
                return tryFRT(str, (AuthenticationResult) null);
            } else if (tokenCacheItem.isFamilyToken()) {
                Eo.c(new StringBuilder(), TAG, ":tryMRRT", "MRRT item exists but it's also a FRT, try with FRT.");
                return tryFRT(this.mMrrtTokenCacheItem.getFamilyClientId(), (AuthenticationResult) null);
            } else {
                AuthenticationResult useMRRT = useMRRT();
                if (isTokenRequestFailed(useMRRT)) {
                    if (!StringExtensions.isNullOrBlank(this.mMrrtTokenCacheItem.getFamilyClientId())) {
                        str = this.mMrrtTokenCacheItem.getFamilyClientId();
                    }
                    useMRRT = tryFRT(str, useMRRT);
                }
                if (!StringExtensions.isNullOrBlank(this.mAuthRequest.getUserFromRequest()) || !this.mTokenCacheAccessor.isMultipleMRRTsMatchingGivenApp(this.mAuthRequest.getClientId())) {
                    return useMRRT;
                }
                throw new AuthenticationException(ADALError.AUTH_FAILED_USER_MISMATCH, "No User provided and multiple MRRTs exist for the given client id");
            }
        } catch (MalformedURLException e) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
        }
    }

    private AuthenticationResult tryRT() throws AuthenticationException {
        try {
            TokenCacheItem regularRefreshTokenCacheItem = this.mTokenCacheAccessor.getRegularRefreshTokenCacheItem(this.mAuthRequest.getResource(), this.mAuthRequest.getClientId(), this.mAuthRequest.getUserFromRequest());
            if (regularRefreshTokenCacheItem == null) {
                Eo.c(new StringBuilder(), TAG, ":tryRT", "Regular token cache entry does not exist, try with MRRT.");
                return tryMRRT();
            } else if (regularRefreshTokenCacheItem.getIsMultiResourceRefreshToken() || isMRRTEntryExisted()) {
                Eo.c(new StringBuilder(), TAG, ":tryRT", regularRefreshTokenCacheItem.getIsMultiResourceRefreshToken() ? "Found RT and it's also a MRRT, retry with MRRT" : "RT is found and there is a MRRT entry existed, try with MRRT");
                return tryMRRT();
            } else if (!StringExtensions.isNullOrBlank(this.mAuthRequest.getUserFromRequest()) || !this.mTokenCacheAccessor.isMultipleRTsMatchingGivenAppAndResource(this.mAuthRequest.getClientId(), this.mAuthRequest.getResource())) {
                Eo.c(new StringBuilder(), TAG, ":tryRT", "Send request to use regular RT for new AT.");
                return acquireTokenWithCachedItem(regularRefreshTokenCacheItem);
            } else {
                throw new AuthenticationException(ADALError.AUTH_FAILED_USER_MISMATCH, "Multiple refresh tokens exists for the given client id and resource");
            }
        } catch (MalformedURLException e) {
            throw new AuthenticationException(ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL, e.getMessage(), (Throwable) e);
        }
    }

    private AuthenticationResult useMRRT() throws AuthenticationException {
        Eo.c(new StringBuilder(), TAG, ":useMRRT", "Send request to use MRRT for new AT.");
        this.mAttemptedWithMRRT = true;
        TokenCacheItem tokenCacheItem = this.mMrrtTokenCacheItem;
        if (tokenCacheItem != null) {
            return acquireTokenWithCachedItem(tokenCacheItem);
        }
        Eo.c(new StringBuilder(), TAG, ":useMRRT", "MRRT does not exist, cannot proceed with MRRT for new AT.");
        return null;
    }

    public AuthenticationResult acquireTokenWithRefreshToken(String str) throws AuthenticationException {
        Logger.m1251v(Eo.a(new StringBuilder(), TAG, ":acquireTokenWithRefreshToken"), "Try to get new access token with the found refresh token.", this.mAuthRequest.getLogInfo(), (ADALError) null);
        HttpWebRequest.throwIfNetworkNotAvailable(this.mContext);
        try {
            AuthenticationResult refreshToken = new Oauth2(this.mAuthRequest, this.mWebRequestHandler, new JWSBuilder()).refreshToken(str);
            if (refreshToken != null && StringExtensions.isNullOrBlank(refreshToken.getRefreshToken())) {
                Logger.m1248i(TAG + ":acquireTokenWithRefreshToken", "Refresh token is not returned or empty", BuildConfig.FLAVOR);
                refreshToken.setRefreshToken(str);
            }
            return refreshToken;
        } catch (ServerRespondingWithRetryableException e) {
            String a = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithRefreshToken");
            StringBuilder a2 = Eo.a("The server is not responding after the retry with error code: ");
            a2.append(e.getCode());
            Logger.m1248i(a, a2.toString(), BuildConfig.FLAVOR);
            TokenCacheItem staleToken = this.mTokenCacheAccessor.getStaleToken(this.mAuthRequest);
            if (staleToken != null) {
                AuthenticationResult createExtendedLifeTimeResult = AuthenticationResult.createExtendedLifeTimeResult(staleToken);
                Logger.m1248i(TAG + ":acquireTokenWithRefreshToken", "The result with stale access token is returned.", BuildConfig.FLAVOR);
                return createExtendedLifeTimeResult;
            }
            String a3 = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithRefreshToken");
            StringBuilder a4 = Eo.a("Request: ");
            a4.append(this.mAuthRequest.getLogInfo());
            a4.append(" ");
            a4.append(ExceptionExtensions.getExceptionMessage(e));
            a4.append(" ");
            a4.append(Log.getStackTraceString(e));
            Logger.m1246e(a3, "Error in refresh token for request. ", a4.toString(), ADALError.AUTH_FAILED_NO_TOKEN, (Throwable) null);
            throw new AuthenticationException(ADALError.AUTH_FAILED_NO_TOKEN, ExceptionExtensions.getExceptionMessage(e), (Throwable) new AuthenticationException(ADALError.SERVER_ERROR, e.getMessage(), (Throwable) e));
        } catch (AuthenticationException | IOException e2) {
            String a5 = Eo.a(new StringBuilder(), TAG, ":acquireTokenWithRefreshToken");
            StringBuilder a6 = Eo.a("Request: ");
            a6.append(this.mAuthRequest.getLogInfo());
            a6.append(" ");
            a6.append(ExceptionExtensions.getExceptionMessage(e2));
            a6.append(" ");
            a6.append(Log.getStackTraceString(e2));
            Logger.m1246e(a5, "Error in refresh token for request.", a6.toString(), ADALError.AUTH_FAILED_NO_TOKEN, (Throwable) null);
            throw new AuthenticationException(ADALError.AUTH_FAILED_NO_TOKEN, ExceptionExtensions.getExceptionMessage(e2), (Throwable) new AuthenticationException(ADALError.SERVER_ERROR, e2.getMessage(), (Throwable) e2));
        }
    }

    public AuthenticationResult getAccessToken() throws AuthenticationException {
        TokenCacheAccessor tokenCacheAccessor = this.mTokenCacheAccessor;
        if (tokenCacheAccessor == null) {
            return null;
        }
        TokenCacheItem aTFromCache = tokenCacheAccessor.getATFromCache(this.mAuthRequest.getResource(), this.mAuthRequest.getClientId(), this.mAuthRequest.getUserFromRequest());
        if (aTFromCache == null || this.mAuthRequest.getForceRefresh() || this.mAuthRequest.isClaimsChallengePresent()) {
            Eo.c(new StringBuilder(), TAG, ":getAccessToken", "No valid access token exists, try with refresh token.");
            return tryRT();
        }
        Logger.m1250v(TAG + ":getAccessToken", "Return AT from cache.");
        return AuthenticationResult.createResult(aTFromCache);
    }

    public void setWebRequestHandler(IWebRequestHandler iWebRequestHandler) {
        this.mWebRequestHandler = iWebRequestHandler;
    }
}
