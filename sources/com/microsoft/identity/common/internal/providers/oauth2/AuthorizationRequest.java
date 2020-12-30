package com.microsoft.identity.common.internal.providers.oauth2;

import android.net.Uri;
import android.util.Pair;
import com.microsoft.identity.common.internal.net.ObjectMapper;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* compiled from: PG */
public abstract class AuthorizationRequest<T extends AuthorizationRequest<T>> implements Serializable {
    public static final long serialVersionUID = 6171895895590170062L;
    @ZJ
    @bK("claims")
    public String mClaims;
    @ZJ
    @bK("client_id")
    public String mClientId;
    public transient List<Pair<String, String>> mExtraQueryParams;
    @bK("redirect_uri")
    public String mRedirectUri;
    public transient HashMap<String, String> mRequestHeaders;
    @ZJ
    @bK("response_type")
    public String mResponseType;
    @ZJ
    @bK("scope")
    public String mScope;
    @ZJ
    @bK("state")
    public String mState;

    /* compiled from: PG */
    public static abstract class Builder<B extends Builder<B>> {
        public String mClaims;
        public String mClientId;
        public UUID mCorrelationId;
        public List<Pair<String, String>> mExtraQueryParams;
        public String mLoginHint;
        public String mPrompt;
        public String mRedirectUri;
        public HashMap<String, String> mRequestHeaders;
        public String mResponseType = "code";
        public String mScope;
        public String mState;

        public abstract AuthorizationRequest build();

        public abstract B self();

        public B setClaims(String str) {
            this.mClaims = str;
            return self();
        }

        public B setClientId(String str) {
            this.mClientId = str;
            return self();
        }

        public B setCorrelationId(UUID uuid) {
            this.mCorrelationId = uuid;
            return self();
        }

        public B setExtraQueryParams(List<Pair<String, String>> list) {
            this.mExtraQueryParams = list;
            return self();
        }

        public B setLoginHint(String str) {
            this.mLoginHint = str;
            return self();
        }

        public B setPrompt(String str) {
            this.mPrompt = str;
            return self();
        }

        public B setRedirectUri(String str) {
            this.mRedirectUri = str;
            return self();
        }

        public B setRequestHeaders(HashMap<String, String> hashMap) {
            this.mRequestHeaders = hashMap;
            return self();
        }

        public B setResponseType(String str) {
            this.mResponseType = str;
            return self();
        }

        public B setScope(String str) {
            this.mScope = str;
            return self();
        }

        public B setState(String str) {
            this.mState = str;
            return self();
        }
    }

    public AuthorizationRequest(Builder builder) {
        this.mResponseType = builder.mResponseType;
        this.mClientId = builder.mClientId;
        this.mRedirectUri = builder.mRedirectUri;
        this.mState = builder.mState;
        this.mScope = builder.mScope;
        this.mExtraQueryParams = builder.mExtraQueryParams;
        this.mClaims = builder.mClaims;
        this.mRequestHeaders = builder.mRequestHeaders;
    }

    public abstract String getAuthorizationEndpoint();

    public Uri getAuthorizationRequestAsHttpRequest() throws UnsupportedEncodingException {
        Uri.Builder buildUpon = Uri.parse(getAuthorizationEndpoint()).buildUpon();
        for (Map.Entry next : ObjectMapper.serializeObjectHashMap(this).entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), next.getValue().toString());
        }
        List<Pair<String, String>> list = this.mExtraQueryParams;
        if (list != null && !list.isEmpty()) {
            for (Pair next2 : this.mExtraQueryParams) {
                buildUpon.appendQueryParameter((String) next2.first, (String) next2.second);
            }
        }
        return buildUpon.build();
    }

    public String getClientId() {
        return this.mClientId;
    }

    public List<Pair<String, String>> getExtraQueryParams() {
        return this.mExtraQueryParams;
    }

    public String getRedirectUri() {
        return this.mRedirectUri;
    }

    public HashMap<String, String> getRequestHeaders() {
        return this.mRequestHeaders;
    }

    public String getResponseType() {
        return this.mResponseType;
    }

    public String getScope() {
        return this.mScope;
    }

    public String getState() {
        return this.mState;
    }

    public String toString() {
        StringBuilder a = Eo.a("AuthorizationRequest{mResponseType='");
        Eo.a(a, this.mResponseType, '\'', ", mClientId='");
        Eo.a(a, this.mClientId, '\'', ", mRedirectUri='");
        Eo.a(a, this.mRedirectUri, '\'', ", mScope='");
        Eo.a(a, this.mScope, '\'', ", mState='");
        a.append(this.mState);
        a.append('\'');
        a.append('}');
        return a.toString();
    }
}
