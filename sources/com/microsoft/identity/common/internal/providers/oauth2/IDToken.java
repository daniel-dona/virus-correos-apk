package com.microsoft.identity.common.internal.providers.oauth2;

import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ClientException;
import com.microsoft.identity.common.exception.ServiceException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class IDToken {
    public static final String ADDRESS = "address";
    public static final String BIRTHDATE = "birthdate";
    public static final String EMAIL = "email";
    public static final String EMAIL_VERIFIED = "email_verified";
    public static final String FAMILY_NAME = "family_name";
    public static final String GENDER = "gender";
    public static final String GIVEN_NAME = "given_name";
    public static final String LOCALE = "locale";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String NAME = "name";
    public static final String NICKNAME = "nickname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PHONE_NUMBER_VERIFIED = "phone_number_verified";
    public static final String PICTURE = "picture";
    public static final String PREFERRED_USERNAME = "preferred_username";
    public static final String PROFILE = "profile";
    public static final String SUBJECT = "sub";
    public static final String UPDATED_AT = "updated_at";
    public static final String WEBSITE = "website";
    public static final String ZONEINFO = "zoneinfo";
    public final String mRawIdToken;
    public Map<String, String> mTokenClaims = null;

    public IDToken(String str) throws ServiceException {
        if (!StringExtensions.isNullOrBlank(str)) {
            this.mRawIdToken = str;
            this.mTokenClaims = parseJWT(str);
            return;
        }
        throw new IllegalArgumentException("null or empty raw idtoken");
    }

    public static Map<String, String> parseJWT(String str) throws ServiceException {
        try {
            Map<String, Object> claims = fv0.a(str).getJWTClaimsSet().getClaims();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : claims.entrySet()) {
                hashMap.put(next.getKey(), next.getValue().toString());
            }
            return hashMap;
        } catch (ParseException e) {
            throw new ServiceException("Failed to parse JWT", ClientException.INVALID_JWT, e);
        }
    }

    public String getRawIDToken() {
        return this.mRawIdToken;
    }

    public Map<String, String> getTokenClaims() {
        return Collections.unmodifiableMap(this.mTokenClaims);
    }
}
