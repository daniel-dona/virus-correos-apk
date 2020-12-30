package com.microsoft.identity.common.internal.cache;

import android.text.TextUtils;
import com.microsoft.aad.adal.IdToken;
import com.microsoft.identity.common.adal.internal.util.StringExtensions;
import com.microsoft.identity.common.exception.ServiceException;
import com.microsoft.identity.common.internal.dto.CredentialType;
import com.microsoft.identity.common.internal.logging.Logger;
import com.microsoft.identity.common.internal.providers.microsoft.azureactivedirectory.ClientInfo;
import com.microsoft.identity.common.internal.providers.oauth2.IDToken;
import com.nimbusds.jwt.JWTClaimsSet;
import java.util.Map;

/* compiled from: PG */
public final class SchemaUtil {
    public static final String EXCEPTION_CONSTRUCTING_IDTOKEN = "Exception constructing IDToken. ";
    public static final String TAG = "SchemaUtil";

    public static String getAlternativeAccountId(IDToken iDToken) {
        String str = null;
        if (iDToken != null) {
            Map<String, String> tokenClaims = iDToken.getTokenClaims();
            if (tokenClaims != null) {
                str = tokenClaims.get("altsecid");
                String a = Eo.a(new StringBuilder(), TAG, ":", "getAlternativeAccountId");
                Logger.verbosePII(a, "alternative_account_id: " + str);
                if (str == null) {
                    Logger.warn(TAG + ":" + "getAlternativeAccountId", "alternative_account_id was null.");
                }
            } else {
                Logger.warn(TAG + ":" + "getAlternativeAccountId", "IDToken claims were null.");
            }
        } else {
            Logger.warn(TAG + ":" + "getAlternativeAccountId", "IDToken was null.");
        }
        return str;
    }

    public static String getAuthority(IDToken iDToken) {
        String str = null;
        if (iDToken != null) {
            Map<String, String> tokenClaims = iDToken.getTokenClaims();
            if (tokenClaims != null) {
                str = tokenClaims.get(JWTClaimsSet.ISSUER_CLAIM);
                String a = Eo.a(new StringBuilder(), TAG, ":", "getAuthority");
                Logger.verbosePII(a, "Issuer: " + str);
                if (str == null) {
                    Logger.warn(TAG + ":" + "getAuthority", "Environment was null or could not be parsed.");
                }
            } else {
                Logger.warn(TAG + ":" + "getAuthority", "IDToken claims were null");
            }
        } else {
            Logger.warn(TAG + ":" + "getAuthority", "IDToken was null");
        }
        return str;
    }

    public static String getAvatarUrl(IDToken iDToken) {
        String str = null;
        if (iDToken != null) {
            Map<String, String> tokenClaims = iDToken.getTokenClaims();
            if (tokenClaims != null) {
                str = tokenClaims.get(IDToken.PICTURE);
                String a = Eo.a(new StringBuilder(), TAG, ":", "getAvatarUrl");
                Logger.verbosePII(a, "Avatar URL: " + str);
                if (str == null) {
                    Logger.warn(TAG + ":" + "getAvatarUrl", "Avatar URL was null.");
                }
            } else {
                Logger.warn(TAG + ":" + "getAvatarUrl", "IDToken claims were null.");
            }
        } else {
            Logger.warn(TAG + ":" + "getAvatarUrl", "IDToken was null.");
        }
        return str;
    }

    public static String getCredentialTypeFromVersion(String str) {
        CredentialType credentialType = CredentialType.IdToken;
        if (TextUtils.isEmpty(str)) {
            return IdToken.TAG;
        }
        try {
            String str2 = new IDToken(str).getTokenClaims().get("ver");
            if (TextUtils.isEmpty(str2) || !str2.equalsIgnoreCase("1.0")) {
                return IdToken.TAG;
            }
            CredentialType credentialType2 = CredentialType.V1IdToken;
            return "V1IdToken";
        } catch (ServiceException e) {
            String a = Eo.a(new StringBuilder(), TAG, ":", "getCredentialTypeFromVersion");
            StringBuilder a2 = Eo.a(EXCEPTION_CONSTRUCTING_IDTOKEN);
            a2.append(e.getMessage());
            Logger.warn(a, a2.toString());
            return IdToken.TAG;
        }
    }

    public static String getHomeAccountId(ClientInfo clientInfo) {
        String str = null;
        if (clientInfo != null) {
            String uid = clientInfo.getUid();
            String utid = clientInfo.getUtid();
            if (StringExtensions.isNullOrBlank(uid)) {
                Logger.warn(TAG + ":" + ":getHomeAccountId", "uid was null/blank");
            }
            if (StringExtensions.isNullOrBlank(utid)) {
                Logger.warn(TAG + ":" + ":getHomeAccountId", "utid was null/blank");
            }
            if (!StringExtensions.isNullOrBlank(uid) && !StringExtensions.isNullOrBlank(utid)) {
                str = Eo.b(uid, ".", utid);
            }
            String a = Eo.a(new StringBuilder(), TAG, ":", ":getHomeAccountId");
            Logger.verbosePII(a, "home_account_id: " + str);
        } else {
            Logger.warn(TAG + ":" + ":getHomeAccountId", "ClientInfo was null.");
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009e A[Catch:{ ServiceException -> 0x00d2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIdentityProvider(java.lang.String r6) {
        /*
            java.lang.String r0 = "getIdentityProvider"
            java.lang.String r1 = ":"
            r2 = 0
            if (r6 == 0) goto L_0x00f3
            com.microsoft.identity.common.internal.providers.oauth2.IDToken r3 = new com.microsoft.identity.common.internal.providers.oauth2.IDToken     // Catch:{ ServiceException -> 0x00d2 }
            r3.<init>(r6)     // Catch:{ ServiceException -> 0x00d2 }
            java.util.Map r6 = r3.getTokenClaims()     // Catch:{ ServiceException -> 0x00d2 }
            if (r6 == 0) goto L_0x00b8
            java.lang.String r3 = "ver"
            java.lang.Object r3 = r6.get(r3)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ ServiceException -> 0x00d2 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r5 = "iss"
            if (r4 != 0) goto L_0x005f
            java.lang.String r4 = "1.0"
            boolean r4 = r3.equalsIgnoreCase(r4)     // Catch:{ ServiceException -> 0x00d2 }
            if (r4 == 0) goto L_0x005f
            java.lang.String r3 = "idp"
            java.lang.Object r3 = r6.get(r3)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ ServiceException -> 0x00d2 }
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ ServiceException -> 0x005b }
            if (r2 == 0) goto L_0x0059
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ServiceException -> 0x005b }
            r2.<init>()     // Catch:{ ServiceException -> 0x005b }
            java.lang.String r4 = TAG     // Catch:{ ServiceException -> 0x005b }
            r2.append(r4)     // Catch:{ ServiceException -> 0x005b }
            r2.append(r1)     // Catch:{ ServiceException -> 0x005b }
            r2.append(r0)     // Catch:{ ServiceException -> 0x005b }
            java.lang.String r2 = r2.toString()     // Catch:{ ServiceException -> 0x005b }
            java.lang.String r4 = "idp claim was null, using iss claim"
            com.microsoft.identity.common.internal.logging.Logger.info(r2, r4)     // Catch:{ ServiceException -> 0x005b }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ ServiceException -> 0x005b }
            r2 = r6
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ ServiceException -> 0x005b }
            goto L_0x0074
        L_0x0059:
            r2 = r3
            goto L_0x0074
        L_0x005b:
            r6 = move-exception
            r2 = r3
            goto L_0x00d3
        L_0x005f:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ ServiceException -> 0x00d2 }
            if (r4 != 0) goto L_0x0074
            java.lang.String r4 = "2.0"
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch:{ ServiceException -> 0x00d2 }
            if (r3 == 0) goto L_0x0074
            java.lang.Object r6 = r6.get(r5)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ ServiceException -> 0x00d2 }
            r2 = r6
        L_0x0074:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ServiceException -> 0x00d2 }
            r6.<init>()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = TAG     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r3)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r1)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r0)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r6 = r6.toString()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ServiceException -> 0x00d2 }
            r3.<init>()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r4 = "idp: "
            r3.append(r4)     // Catch:{ ServiceException -> 0x00d2 }
            r3.append(r2)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = r3.toString()     // Catch:{ ServiceException -> 0x00d2 }
            com.microsoft.identity.common.internal.logging.Logger.verbosePII(r6, r3)     // Catch:{ ServiceException -> 0x00d2 }
            if (r2 != 0) goto L_0x010c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ServiceException -> 0x00d2 }
            r6.<init>()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = TAG     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r3)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r1)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r0)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r6 = r6.toString()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = "idp claim was null."
            com.microsoft.identity.common.internal.logging.Logger.warn(r6, r3)     // Catch:{ ServiceException -> 0x00d2 }
            goto L_0x010c
        L_0x00b8:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ServiceException -> 0x00d2 }
            r6.<init>()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = TAG     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r3)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r1)     // Catch:{ ServiceException -> 0x00d2 }
            r6.append(r0)     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r6 = r6.toString()     // Catch:{ ServiceException -> 0x00d2 }
            java.lang.String r3 = "IDToken claims were null."
            com.microsoft.identity.common.internal.logging.Logger.warn(r6, r3)     // Catch:{ ServiceException -> 0x00d2 }
            goto L_0x010c
        L_0x00d2:
            r6 = move-exception
        L_0x00d3:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = TAG
            java.lang.String r0 = Eo.a(r3, r4, r1, r0)
            java.lang.String r1 = "Exception constructing IDToken. "
            java.lang.StringBuilder r1 = Eo.a(r1)
            java.lang.String r6 = r6.getMessage()
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.microsoft.identity.common.internal.logging.Logger.warn(r0, r6)
            goto L_0x010c
        L_0x00f3:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r3 = TAG
            r6.append(r3)
            r6.append(r1)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "IDToken was null."
            com.microsoft.identity.common.internal.logging.Logger.warn(r6, r0)
        L_0x010c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.identity.common.internal.cache.SchemaUtil.getIdentityProvider(java.lang.String):java.lang.String");
    }

    public static String getTenantId(String str, String str2) {
        String utid;
        try {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return null;
            }
            IDToken iDToken = new IDToken(str2);
            ClientInfo clientInfo = new ClientInfo(str);
            Map<String, String> tokenClaims = iDToken.getTokenClaims();
            if (!TextUtils.isEmpty(tokenClaims.get("tid"))) {
                utid = tokenClaims.get("tid");
            } else if (!TextUtils.isEmpty(clientInfo.getUtid())) {
                Logger.warn(TAG, "realm is not returned from server. Use utid as realm.");
                utid = clientInfo.getUtid();
            } else {
                Logger.warn(TAG, "realm and utid is not returned from server. Using empty string as default tid.");
                return null;
            }
            return utid;
        } catch (ServiceException e) {
            Logger.errorPII(TAG, "Failed to construct IDToken or ClientInfo", e);
            return null;
        }
    }
}
