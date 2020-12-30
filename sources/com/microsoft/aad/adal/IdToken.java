package com.microsoft.aad.adal;

import android.util.Base64;
import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.adal.internal.util.HashMapExtensions;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;

/* compiled from: PG */
public class IdToken {
    public static final String TAG = "IdToken";
    public String mEmail;
    public String mFamilyName;
    public String mGivenName;
    public String mIdentityProvider;
    public String mObjectId;
    public String mPasswordChangeUrl;
    public long mPasswordExpiration;
    public String mSubject;
    public String mTenantId;
    public String mUpn;

    public IdToken(String str) throws AuthenticationException {
        Map<String, String> parseJWT = parseJWT(str);
        if (parseJWT != null && !parseJWT.isEmpty()) {
            this.mSubject = parseJWT.get("sub");
            this.mTenantId = parseJWT.get("tid");
            this.mUpn = parseJWT.get("upn");
            this.mEmail = parseJWT.get(IDToken.EMAIL);
            this.mGivenName = parseJWT.get(IDToken.GIVEN_NAME);
            this.mFamilyName = parseJWT.get(IDToken.FAMILY_NAME);
            this.mIdentityProvider = parseJWT.get("idp");
            this.mObjectId = parseJWT.get("oid");
            String str2 = parseJWT.get("pwd_exp");
            if (!StringExtensions.isNullOrBlank(str2)) {
                this.mPasswordExpiration = Long.parseLong(str2);
            }
            this.mPasswordChangeUrl = parseJWT.get("pwd_url");
        }
    }

    private String extractJWTBody(String str) throws AuthenticationException {
        int indexOf = str.indexOf(46);
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(46, i);
        if (str.indexOf(46, indexOf2 + 1) == -1 && indexOf > 0 && indexOf2 > 0) {
            return str.substring(i, indexOf2);
        }
        throw new AuthenticationException(ADALError.IDTOKEN_PARSING_FAILURE, "Failed to extract the ClientID");
    }

    private Map<String, String> parseJWT(String str) throws AuthenticationException {
        try {
            return HashMapExtensions.jsonStringAsMap(new String(Base64.decode(extractJWTBody(str), 8), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Logger.m1246e("IdToken:parseJWT", "The encoding is not supported.", BuildConfig.FLAVOR, ADALError.ENCODING_IS_NOT_SUPPORTED, e);
            throw new AuthenticationException(ADALError.ENCODING_IS_NOT_SUPPORTED, e.getMessage(), (Throwable) e);
        } catch (JSONException e2) {
            Logger.m1246e("IdToken:parseJWT", "Failed to parse the decoded body into JsonObject.", BuildConfig.FLAVOR, ADALError.JSON_PARSE_ERROR, e2);
            throw new AuthenticationException(ADALError.JSON_PARSE_ERROR, e2.getMessage(), (Throwable) e2);
        }
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getFamilyName() {
        return this.mFamilyName;
    }

    public String getGivenName() {
        return this.mGivenName;
    }

    public String getIdentityProvider() {
        return this.mIdentityProvider;
    }

    public String getObjectId() {
        return this.mObjectId;
    }

    public String getPasswordChangeUrl() {
        return this.mPasswordChangeUrl;
    }

    public long getPasswordExpiration() {
        return this.mPasswordExpiration;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public String getTenantId() {
        return this.mTenantId;
    }

    public String getUpn() {
        return this.mUpn;
    }
}
