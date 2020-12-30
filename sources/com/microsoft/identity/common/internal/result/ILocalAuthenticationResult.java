package com.microsoft.identity.common.internal.result;

import com.microsoft.identity.common.internal.dto.AccessTokenRecord;
import com.microsoft.identity.common.internal.dto.IAccountRecord;
import java.util.Date;

/* compiled from: PG */
public interface ILocalAuthenticationResult {
    String getAccessToken();

    AccessTokenRecord getAccessTokenRecord();

    IAccountRecord getAccountRecord();

    Date getExpiresOn();

    String getFamilyId();

    String getIdToken();

    String getRefreshToken();

    String getRefreshTokenAge();

    String[] getScope();

    String getSpeRing();

    String getTenantId();

    String getUniqueId();
}
