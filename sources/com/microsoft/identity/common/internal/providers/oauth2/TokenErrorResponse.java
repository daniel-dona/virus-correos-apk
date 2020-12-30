package com.microsoft.identity.common.internal.providers.oauth2;

/* compiled from: PG */
public class TokenErrorResponse implements IErrorResponse {
    @ZJ
    @bK("error")
    public String mError;
    @ZJ
    @bK("error_description")
    public String mErrorDescription;
    @ZJ
    @bK("error_uri")
    public String mErrorUri;
    public String mResponseBody;
    @ZJ
    public String mResponseHeadersJson;
    @ZJ
    public int mStatusCode;
    @ZJ
    @bK("suberror")
    public String mSubError;

    public String getError() {
        return this.mError;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }

    public String getErrorUri() {
        return this.mErrorUri;
    }

    public String getResponseBody() {
        return this.mResponseBody;
    }

    public String getResponseHeadersJson() {
        return this.mResponseHeadersJson;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getSubError() {
        return this.mSubError;
    }

    public void setError(String str) {
        this.mError = str;
    }

    public void setErrorDescription(String str) {
        this.mErrorDescription = str;
    }

    public void setErrorUri(String str) {
        this.mErrorUri = str;
    }

    public void setResponseBody(String str) {
        this.mResponseBody = str;
    }

    public void setResponseHeadersJson(String str) {
        this.mResponseHeadersJson = str;
    }

    public void setStatusCode(int i) {
        this.mStatusCode = i;
    }

    public void setSubError(String str) {
        this.mSubError = str;
    }

    public String toString() {
        StringBuilder a = Eo.a("TokenErrorResponse{mStatusCode=");
        a.append(this.mStatusCode);
        a.append(", mResponseBody='");
        Eo.a(a, this.mResponseBody, '\'', ", mResponseHeadersJson=");
        a.append(this.mResponseHeadersJson);
        a.append(", mError='");
        Eo.a(a, this.mError, '\'', ", mSubError='");
        Eo.a(a, this.mSubError, '\'', ", mErrorDescription='");
        Eo.a(a, this.mErrorDescription, '\'', ", mErrorUri='");
        a.append(this.mErrorUri);
        a.append('\'');
        a.append('}');
        return a.toString();
    }
}
