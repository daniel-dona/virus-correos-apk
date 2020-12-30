package com.microsoft.aad.adal;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.aad.adal.TelemetryUtils;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.util.HashMapExtensions;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;

/* compiled from: PG */
public class AuthenticationResult implements Serializable {
    public static final long serialVersionUID = 2243372613182536368L;
    public String mAccessToken;
    public String mAuthority;
    public TelemetryUtils.CliTelemInfo mCliTelemInfo;
    public String mClientId;
    public ClientInfo mClientInfo;
    public String mCode;
    public String mErrorCode;
    public String mErrorCodes;
    public String mErrorDescription;
    public Long mExpiresIn;
    public Date mExpiresOn;
    public Date mExtendedExpiresOn;
    public String mFamilyClientId;
    public HashMap<String, String> mHttpResponseBody;
    public HashMap<String, List<String>> mHttpResponseHeaders;
    public String mIdToken;
    public boolean mInitialRequest;
    public boolean mIsExtendedLifeTimeToken;
    public boolean mIsMultiResourceRefreshToken;
    public String mRefreshToken;
    public String mResource;
    public Long mResponseReceived;
    public int mServiceStatusCode;
    public AuthenticationStatus mStatus;
    public String mTenantId;
    public String mTokenType;
    public UserInfo mUserInfo;

    /* compiled from: PG */
    public enum AuthenticationStatus {
        Cancelled,
        Failed,
        Succeeded
    }

    public AuthenticationResult() {
        this.mStatus = AuthenticationStatus.Failed;
        this.mIsExtendedLifeTimeToken = false;
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = null;
    }

    public static AuthenticationResult createExtendedLifeTimeResult(TokenCacheItem tokenCacheItem) {
        AuthenticationResult createResult = createResult(tokenCacheItem);
        createResult.setExpiresOn(createResult.getExtendedExpiresOn());
        createResult.setIsExtendedLifeTimeToken(true);
        return createResult;
    }

    public static AuthenticationResult createResult(TokenCacheItem tokenCacheItem) {
        if (tokenCacheItem == null) {
            AuthenticationResult authenticationResult = new AuthenticationResult();
            authenticationResult.mStatus = AuthenticationStatus.Failed;
            return authenticationResult;
        }
        AuthenticationResult authenticationResult2 = new AuthenticationResult(tokenCacheItem.getAccessToken(), tokenCacheItem.getRefreshToken(), tokenCacheItem.getExpiresOn(), tokenCacheItem.getIsMultiResourceRefreshToken(), tokenCacheItem.getUserInfo(), tokenCacheItem.getTenantId(), tokenCacheItem.getRawIdToken(), tokenCacheItem.getExtendedExpiresOn(), tokenCacheItem.getClientId());
        TelemetryUtils.CliTelemInfo cliTelemInfo = new TelemetryUtils.CliTelemInfo();
        cliTelemInfo._setSpeRing(tokenCacheItem.getSpeRing());
        authenticationResult2.setCliTelemInfo(cliTelemInfo);
        return authenticationResult2;
    }

    public static AuthenticationResult createResultForInitialRequest(String str) {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.mInitialRequest = true;
        authenticationResult.mClientId = str;
        return authenticationResult;
    }

    public String createAuthorizationHeader() {
        StringBuilder a = Eo.a("Bearer ");
        a.append(getAccessToken());
        return a.toString();
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getAccessTokenType() {
        return this.mTokenType;
    }

    public final String getAuthority() {
        return this.mAuthority;
    }

    public final TelemetryUtils.CliTelemInfo getCliTelemInfo() {
        return this.mCliTelemInfo;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public ClientInfo getClientInfo() {
        return this.mClientInfo;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String[] getErrorCodes() {
        String str = this.mErrorCodes;
        if (str != null) {
            return str.replaceAll("[\\[\\]]", BuildConfig.FLAVOR).split("([^,]),");
        }
        return null;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }

    public String getErrorLogInfo() {
        StringBuilder a = Eo.a(" ErrorCode:");
        a.append(getErrorCode());
        return a.toString();
    }

    public Long getExpiresIn() {
        return this.mExpiresIn;
    }

    public Date getExpiresOn() {
        return DateExtensions.createCopy(this.mExpiresOn);
    }

    public final Date getExtendedExpiresOn() {
        return this.mExtendedExpiresOn;
    }

    public final String getFamilyClientId() {
        return this.mFamilyClientId;
    }

    public HashMap<String, String> getHttpResponseBody() {
        return this.mHttpResponseBody;
    }

    public HashMap<String, List<String>> getHttpResponseHeaders() {
        return this.mHttpResponseHeaders;
    }

    public String getIdToken() {
        return this.mIdToken;
    }

    public boolean getIsMultiResourceRefreshToken() {
        return this.mIsMultiResourceRefreshToken;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public String getResource() {
        return this.mResource;
    }

    public Long getResponseReceived() {
        return this.mResponseReceived;
    }

    public int getServiceStatusCode() {
        return this.mServiceStatusCode;
    }

    public AuthenticationStatus getStatus() {
        return this.mStatus;
    }

    public String getTenantId() {
        return this.mTenantId;
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    public boolean isExpired() {
        if (this.mIsExtendedLifeTimeToken) {
            return TokenCacheItem.isTokenExpired(getExtendedExpiresOn());
        }
        return TokenCacheItem.isTokenExpired(getExpiresOn());
    }

    public boolean isExtendedLifeTimeToken() {
        return this.mIsExtendedLifeTimeToken;
    }

    public boolean isInitialRequest() {
        return this.mInitialRequest;
    }

    public final void setAuthority(String str) {
        if (!StringExtensions.isNullOrBlank(str)) {
            this.mAuthority = str;
        }
    }

    public final void setCliTelemInfo(TelemetryUtils.CliTelemInfo cliTelemInfo) {
        this.mCliTelemInfo = cliTelemInfo;
    }

    public void setClientId(String str) {
        this.mClientId = str;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.mClientInfo = clientInfo;
    }

    public void setCode(String str) {
        this.mCode = str;
    }

    public void setExpiresIn(Long l) {
        this.mExpiresIn = l;
    }

    public final void setExpiresOn(Date date) {
        this.mExpiresOn = date;
    }

    public final void setExtendedExpiresOn(Date date) {
        this.mExtendedExpiresOn = date;
    }

    public final void setFamilyClientId(String str) {
        this.mFamilyClientId = str;
    }

    public void setHttpResponse(HttpWebResponse httpWebResponse) {
        if (httpWebResponse != null) {
            this.mServiceStatusCode = httpWebResponse.getStatusCode();
            if (httpWebResponse.getResponseHeaders() != null) {
                this.mHttpResponseHeaders = new HashMap<>(httpWebResponse.getResponseHeaders());
            }
            if (httpWebResponse.getBody() != null) {
                try {
                    this.mHttpResponseBody = new HashMap<>(HashMapExtensions.getJsonResponse(httpWebResponse));
                } catch (JSONException e) {
                    Logger.m1245e(AuthenticationException.class.getSimpleName(), "Json exception", ExceptionExtensions.getExceptionMessage(e), ADALError.SERVER_INVALID_JSON_RESPONSE);
                }
            }
        }
    }

    public void setHttpResponseBody(HashMap<String, String> hashMap) {
        this.mHttpResponseBody = hashMap;
    }

    public void setHttpResponseHeaders(HashMap<String, List<String>> hashMap) {
        this.mHttpResponseHeaders = hashMap;
    }

    public void setIdToken(String str) {
        this.mIdToken = str;
    }

    public final void setIsExtendedLifeTimeToken(boolean z) {
        this.mIsExtendedLifeTimeToken = z;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public void setResource(String str) {
        this.mResource = str;
    }

    public void setResponseReceived(Long l) {
        this.mResponseReceived = l;
    }

    public void setServiceStatusCode(int i) {
        this.mServiceStatusCode = i;
    }

    public void setTenantId(String str) {
        this.mTenantId = str;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public AuthenticationResult(String str, String str2) {
        this.mStatus = AuthenticationStatus.Failed;
        this.mIsExtendedLifeTimeToken = false;
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mClientId = str;
        this.mCode = str2;
        this.mStatus = AuthenticationStatus.Succeeded;
        this.mAccessToken = null;
        this.mRefreshToken = null;
    }

    public AuthenticationResult(String str, String str2, Date date, boolean z, UserInfo userInfo, String str3, String str4, Date date2, String str5) {
        this.mStatus = AuthenticationStatus.Failed;
        this.mIsExtendedLifeTimeToken = false;
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = null;
        this.mAccessToken = str;
        this.mRefreshToken = str2;
        this.mExpiresOn = date;
        this.mIsMultiResourceRefreshToken = z;
        this.mStatus = AuthenticationStatus.Succeeded;
        this.mUserInfo = userInfo;
        this.mTenantId = str3;
        this.mIdToken = str4;
        this.mExtendedExpiresOn = date2;
        this.mClientId = str5;
    }

    public AuthenticationResult(String str, String str2, String str3) {
        AuthenticationStatus authenticationStatus = AuthenticationStatus.Failed;
        this.mStatus = authenticationStatus;
        this.mIsExtendedLifeTimeToken = false;
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mErrorCode = str;
        this.mErrorDescription = str2;
        this.mErrorCodes = str3;
        this.mStatus = authenticationStatus;
    }
}
