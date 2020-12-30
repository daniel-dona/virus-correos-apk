package com.microsoft.identity.common.internal.providers.microsoft.microsoftsts;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.microsoft.identity.common.internal.net.ObjectMapper;
import com.microsoft.identity.common.internal.providers.microsoft.MicrosoftAuthorizationRequest;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.AzureActiveDirectorySlice;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* compiled from: PG */
public class MicrosoftStsAuthorizationRequest extends MicrosoftAuthorizationRequest<MicrosoftStsAuthorizationRequest> {
    public static final String AUTHORIZATION_ENDPOINT = "/oAuth2/v2.0/authorize";
    public static final long serialVersionUID = 6545759826515911472L;
    public transient String mDisplayableId;
    @ZJ
    @bK("prompt")
    public String mPrompt;
    @bK("login_req")
    public String mUid;
    @bK("domain_req")
    public String mUtid;

    public MicrosoftStsAuthorizationRequest(Builder builder) {
        super(builder);
        this.mPrompt = builder.mPrompt;
        this.mUid = Builder.access$000(builder);
        this.mUtid = Builder.access$100(builder);
        this.mDisplayableId = Builder.access$200(builder);
    }

    public String getAuthorizationEndpoint() {
        return Uri.parse(getAuthority().toString()).buildUpon().appendPath(AUTHORIZATION_ENDPOINT).build().toString();
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
        for (Map.Entry next3 : this.mFlightParameters.entrySet()) {
            buildUpon.appendQueryParameter((String) next3.getKey(), (String) next3.getValue());
        }
        AzureActiveDirectorySlice azureActiveDirectorySlice = this.mSlice;
        if (azureActiveDirectorySlice != null) {
            if (!TextUtils.isEmpty(azureActiveDirectorySlice.getSlice())) {
                buildUpon.appendQueryParameter(AzureActiveDirectorySlice.SLICE_PARAMETER, this.mSlice.getSlice());
            }
            if (!TextUtils.isEmpty(this.mSlice.getDC())) {
                buildUpon.appendQueryParameter(AzureActiveDirectorySlice.DC_PARAMETER, this.mSlice.getDC());
            }
        }
        return buildUpon.build();
    }

    public String getDisplayableId() {
        return this.mDisplayableId;
    }

    public String getPrompt() {
        return this.mPrompt;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getUtid() {
        return this.mUtid;
    }
}
