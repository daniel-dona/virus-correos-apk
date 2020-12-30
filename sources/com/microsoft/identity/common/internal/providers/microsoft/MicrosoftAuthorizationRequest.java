package com.microsoft.identity.common.internal.providers.microsoft;

import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Pair;
import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.net.ObjectMapper;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectorySlice;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationRequest;
import com.microsoft.identity.common.internal.providers.oauth2.PkceChallenge;
import com.microsoft.identity.common.internal.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: PG */
public abstract class MicrosoftAuthorizationRequest<T extends MicrosoftAuthorizationRequest<T>> extends AuthorizationRequest<T> {
    public static final String INSTANCE_AWARE = "instance_aware";
    public static final String TAG = MicrosoftAuthorizationRequest.class.getSimpleName();
    public static final long serialVersionUID = 6873634931996113294L;
    public transient URL mAuthority;
    @bK("code_challenge")
    public String mCodeChallenge = this.mPkceChallenge.getCodeChallenge();
    @bK("code_challenge_method")
    public String mCodeChallengeMethod = this.mPkceChallenge.getCodeChallengeMethod();
    @ZJ
    @bK("client-request-id")
    public UUID mCorrelationId;
    @ZJ
    @bK("x-client-CPU")
    public String mDiagnosticCPU;
    @ZJ
    @bK("x-client-DM")
    public String mDiagnosticDM;
    @ZJ
    @bK("x-client-OS")
    public String mDiagnosticOS;
    public transient Map<String, String> mFlightParameters;
    @ZJ
    @bK("x-client-SKU")
    public String mLibraryName;
    @ZJ
    @bK("x-client-Ver")
    public String mLibraryVersion;
    @bK("login_hint")
    public String mLoginHint;
    @ZJ
    @bK("instance_aware")
    public Boolean mMultipleCloudAware;
    public transient PkceChallenge mPkceChallenge = PkceChallenge.newPkceChallenge();
    public transient AzureActiveDirectorySlice mSlice;

    /* compiled from: PG */
    public static abstract class Builder<B extends Builder<B>> extends AuthorizationRequest.Builder<B> {
        public URL mAuthority;
        public Map<String, String> mFlightParameters = new HashMap();
        public String mLibraryName;
        public String mLibraryVersion;
        public Boolean mMultipleCloudAware;
        public PkceChallenge mPkceChallenge;
        public AzureActiveDirectorySlice mSlice;

        public abstract B self();

        public B setAuthority(URL url) {
            this.mAuthority = url;
            return self();
        }

        public B setFlightParameters(Map<String, String> map) {
            this.mFlightParameters = map;
            return self();
        }

        public B setLibraryName(String str) {
            this.mLibraryName = str;
            return self();
        }

        public B setLibraryVersion(String str) {
            this.mLibraryVersion = str;
            return self();
        }

        public B setMultipleCloudAware(boolean z) {
            this.mMultipleCloudAware = Boolean.valueOf(z);
            return self();
        }

        public B setPkceChallenge(PkceChallenge pkceChallenge) {
            this.mPkceChallenge = pkceChallenge;
            return self();
        }

        public B setSlice(AzureActiveDirectorySlice azureActiveDirectorySlice) {
            this.mSlice = azureActiveDirectorySlice;
            return self();
        }
    }

    public MicrosoftAuthorizationRequest(Builder builder) {
        super(builder);
        this.mAuthority = builder.mAuthority;
        this.mLoginHint = builder.mLoginHint;
        this.mCorrelationId = builder.mCorrelationId;
        this.mState = generateEncodedState();
        if (builder.mSlice != null) {
            this.mSlice = builder.mSlice;
        }
        this.mFlightParameters = builder.mFlightParameters;
        this.mMultipleCloudAware = builder.mMultipleCloudAware;
        this.mLibraryVersion = builder.mLibraryVersion;
        this.mLibraryName = builder.mLibraryName;
        this.mDiagnosticOS = String.valueOf(Build.VERSION.SDK_INT);
        this.mDiagnosticDM = Build.MODEL;
        if (Build.VERSION.SDK_INT < 21) {
            this.mDiagnosticCPU = Build.CPU_ABI;
            return;
        }
        String[] strArr = Build.SUPPORTED_ABIS;
        if (strArr != null && strArr.length > 0) {
            this.mDiagnosticCPU = strArr[0];
        }
    }

    public static String decodeState(String str) {
        if (!StringUtil.isEmpty(str)) {
            return new String(Base64.decode(str, 9), Charset.defaultCharset());
        }
        Logger.warn(TAG, "Decode state failed because the input state is empty.");
        return null;
    }

    public static String generateEncodedState() {
        UUID randomUUID = UUID.randomUUID();
        UUID randomUUID2 = UUID.randomUUID();
        try {
            return Base64.encodeToString((randomUUID.toString() + CacheKeyValueDelegate.CACHE_VALUE_SEPARATOR + randomUUID2.toString()).getBytes("UTF-8"), 11);
        } catch (Exception e) {
            throw new IllegalStateException("Error generating encoded state parameter for Authorization Request", e);
        }
    }

    public URL getAuthority() {
        return this.mAuthority;
    }

    public Uri getAuthorizationRequestAsHttpRequest() throws UnsupportedEncodingException {
        Uri.Builder buildUpon = Uri.parse(getAuthorizationEndpoint()).buildUpon();
        for (Map.Entry next : ObjectMapper.serializeObjectHashMap(this).entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), next.getValue().toString());
        }
        if (getExtraQueryParams() != null && !getExtraQueryParams().isEmpty()) {
            for (Pair next2 : getExtraQueryParams()) {
                buildUpon.appendQueryParameter((String) next2.first, (String) next2.second);
            }
        }
        return buildUpon.build();
    }

    public String getCodeChallenge() {
        return this.mCodeChallenge;
    }

    public String getCodeChallengeMethod() {
        return this.mCodeChallengeMethod;
    }

    public UUID getCorrelationId() {
        return this.mCorrelationId;
    }

    public String getDiagnosticCPU() {
        return this.mDiagnosticCPU;
    }

    public String getDiagnosticDM() {
        return this.mDiagnosticDM;
    }

    public String getDiagnosticOS() {
        return this.mDiagnosticOS;
    }

    public String getLibraryName() {
        return this.mLibraryName;
    }

    public String getLibraryVersion() {
        return this.mLibraryVersion;
    }

    public String getLoginHint() {
        return this.mLoginHint;
    }

    public Boolean getMultipleCloudAware() {
        return this.mMultipleCloudAware;
    }

    public PkceChallenge getPkceChallenge() {
        return this.mPkceChallenge;
    }
}
