package com.microsoft.authentication;

import com.citrix.loggersdk.BuildConfig;
import com.microsoft.identity.common.internal.providers.oauth2.AuthorizationResultFactory;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
public class OAuthToken implements Serializable {
    public String _accessToken;
    public String _anid;
    public int _expiresIn;
    public boolean _isRefreshTokenExpired;
    public String _refreshToken;
    public String _scope;
    public String _tokenType;
    public String _userId;

    public OAuthToken(String str, String str2, String str3, int i, String str4, String str5, String str6) {
        this._tokenType = BuildConfig.FLAVOR;
        this._scope = BuildConfig.FLAVOR;
        this._accessToken = BuildConfig.FLAVOR;
        this._refreshToken = BuildConfig.FLAVOR;
        this._userId = BuildConfig.FLAVOR;
        this._anid = BuildConfig.FLAVOR;
        this._accessToken = str;
        this._refreshToken = str2;
        this._scope = str3;
        this._expiresIn = i;
        this._tokenType = str4;
        this._userId = str5;
        this._anid = str6;
        this._isRefreshTokenExpired = false;
    }

    public String getANID() {
        return this._anid;
    }

    public String getAccessToken() {
        return this._accessToken;
    }

    public int getExpiresIn() {
        return this._expiresIn;
    }

    public String getInvalidFieldForOAuthToken() {
        if (C20.b(this._tokenType)) {
            return "TokenType";
        }
        if (this._expiresIn <= 0) {
            return "ExpiresIn";
        }
        if (C20.b(this._scope)) {
            return "Scope";
        }
        if (C20.b(this._accessToken)) {
            return "AccessToken";
        }
        return C20.b(this._userId) ? "UserId" : "NoFieldIsInvalid";
    }

    public String getRefreshToken() {
        return this._refreshToken;
    }

    public String getScope() {
        return this._scope;
    }

    public String getTokenType() {
        return this._tokenType;
    }

    public String getUserId() {
        return this._userId;
    }

    public boolean isRefreshTokenExpired() {
        return this._isRefreshTokenExpired;
    }

    public boolean isValidOAuthToken() {
        if ("wns.connect".equals(this._scope)) {
            if (C20.b(this._tokenType) || this._expiresIn <= 0 || C20.b(this._accessToken)) {
                return false;
            }
            return true;
        } else if ("service::http://Passport.NET/purpose::PURPOSE_GETKEYDATA_ANAHEIM".equals(this._scope)) {
            if (this._expiresIn <= 0 || C20.b(this._accessToken) || C20.b(this._userId)) {
                return false;
            }
            return true;
        } else if (C20.b(this._tokenType) || this._expiresIn <= 0 || C20.b(this._scope) || C20.b(this._accessToken) || C20.b(this._userId)) {
            return false;
        } else {
            return true;
        }
    }

    public OAuthToken(String str, String str2, String str3) throws JSONException {
        this._tokenType = BuildConfig.FLAVOR;
        this._scope = BuildConfig.FLAVOR;
        this._accessToken = BuildConfig.FLAVOR;
        this._refreshToken = BuildConfig.FLAVOR;
        this._userId = BuildConfig.FLAVOR;
        this._anid = BuildConfig.FLAVOR;
        this._isRefreshTokenExpired = false;
        this._scope = str2;
        this._anid = str3;
        JSONObject jSONObject = new JSONObject(str);
        if (!jSONObject.has(AuthorizationResultFactory.ERROR)) {
            String optString = jSONObject.optString("lpt");
            if (C20.b(optString)) {
                this._tokenType = jSONObject.optString("token_type");
                this._expiresIn = jSONObject.optInt("expires_in");
                this._accessToken = jSONObject.optString("access_token");
                this._refreshToken = jSONObject.optString("refresh_token");
                this._userId = jSONObject.optString("user_id");
                return;
            }
            this._expiresIn = 300;
            this._accessToken = optString;
            this._refreshToken = jSONObject.optString("refresh_token");
            this._userId = jSONObject.optString("user_id");
        } else if ("invalid_grant".equalsIgnoreCase(jSONObject.getString(AuthorizationResultFactory.ERROR))) {
            this._isRefreshTokenExpired = true;
        }
    }
}
