package com.microsoft.aad.adal;

import android.content.Context;
import com.microsoft.identity.common.adal.internal.net.HttpWebResponse;
import com.microsoft.identity.common.adal.internal.util.HashMapExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;

/* compiled from: PG */
public class AuthenticationException extends Exception {
    public static final long serialVersionUID = 1;
    public String mCliTelemErrorCode;
    public String mCliTelemSubErrorCode;
    public ADALError mCode;
    public HashMap<String, String> mHttpResponseBody;
    public HashMap<String, List<String>> mHttpResponseHeaders;
    public String mRefreshTokenAge;
    public int mServiceStatusCode;
    public String mSpeRing;

    public AuthenticationException() {
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
    }

    public String getCliTelemErrorCode() {
        return this.mCliTelemErrorCode;
    }

    public String getCliTelemSubErrorCode() {
        return this.mCliTelemSubErrorCode;
    }

    public ADALError getCode() {
        return this.mCode;
    }

    public HashMap<String, String> getHttpResponseBody() {
        return this.mHttpResponseBody;
    }

    public HashMap<String, List<String>> getHttpResponseHeaders() {
        return this.mHttpResponseHeaders;
    }

    public String getLocalizedMessage(Context context) {
        if (!StringExtensions.isNullOrBlank(super.getMessage())) {
            return super.getMessage();
        }
        ADALError aDALError = this.mCode;
        if (aDALError != null) {
            return aDALError.getLocalizedDescription(context);
        }
        return null;
    }

    public String getMessage() {
        return getLocalizedMessage((Context) null);
    }

    public String getRefreshTokenAge() {
        return this.mRefreshTokenAge;
    }

    public int getServiceStatusCode() {
        return this.mServiceStatusCode;
    }

    public String getSpeRing() {
        return this.mSpeRing;
    }

    public void setCliTelemErrorCode(String str) {
        this.mCliTelemErrorCode = str;
    }

    public void setCliTelemSubErrorCode(String str) {
        this.mCliTelemSubErrorCode = str;
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

    public void setRefreshTokenAge(String str) {
        this.mRefreshTokenAge = str;
    }

    public void setServiceStatusCode(int i) {
        this.mServiceStatusCode = i;
    }

    public void setSpeRing(String str) {
        this.mSpeRing = str;
    }

    public AuthenticationException(ADALError aDALError) {
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = aDALError;
    }

    public void setHttpResponse(AuthenticationResult authenticationResult) {
        if (authenticationResult != null) {
            this.mHttpResponseBody = authenticationResult.getHttpResponseBody();
            this.mHttpResponseHeaders = authenticationResult.getHttpResponseHeaders();
            this.mServiceStatusCode = authenticationResult.getServiceStatusCode();
        }
    }

    public AuthenticationException(ADALError aDALError, String str) {
        super(str);
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = aDALError;
    }

    public AuthenticationException(ADALError aDALError, String str, Throwable th) {
        super(str, th);
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = aDALError;
        if (th != null && (th instanceof AuthenticationException)) {
            AuthenticationException authenticationException = (AuthenticationException) th;
            this.mServiceStatusCode = authenticationException.getServiceStatusCode();
            if (authenticationException.getHttpResponseBody() != null) {
                this.mHttpResponseBody = new HashMap<>(authenticationException.getHttpResponseBody());
            }
            if (authenticationException.getHttpResponseHeaders() != null) {
                this.mHttpResponseHeaders = new HashMap<>(authenticationException.getHttpResponseHeaders());
            }
        }
    }

    public AuthenticationException(ADALError aDALError, String str, HttpWebResponse httpWebResponse) {
        super(str);
        this.mHttpResponseBody = null;
        this.mServiceStatusCode = -1;
        this.mHttpResponseHeaders = null;
        this.mCode = aDALError;
        setHttpResponse(httpWebResponse);
    }

    public AuthenticationException(ADALError aDALError, String str, HttpWebResponse httpWebResponse, Throwable th) {
        this(aDALError, str, th);
        setHttpResponse(httpWebResponse);
    }
}
